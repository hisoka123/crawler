package com.storm.function;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.crawler.domain.json.StatusCodeDef;
import com.crawler.pbccrc.htmlparser.PbcCreditFeedParser;
import com.crawler.pbccrc.utils.Constants;
import com.crawler.pbccrc.utils.EncryptUtil;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.PbccrcParam;
import com.storm.domian.WebParam;
import com.storm.util.IPUtil;

public class PbccrcFunction {
	private static final String LOGGEDIN_PAGE = "https://ipcrs.pbccrc.org.cn/login.do"; //ipcrs.pbccrc.org.cn	https://ipcrs.pbccrc.org.cn  https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp
	
	public class ResultData {
		public String username;
		public String ip;
		public String hostName;
		public String statusCode;
		public String message;
		public Set<Cookie> cookies;
		public String imageUrl;
		public String reportFileURL;
		public String html;
	}
	
	
	private String parseResultDataToJson(ResultData resultData) {
		return new GsonBuilder().create().toJson(resultData);
	}
	@SuppressWarnings("deprecation")
	private void clearCookies(WebClient webClient, URL url) throws MalformedURLException {
		CookieManager manager = webClient.getCookieManager();
		Set<Cookie> cookies = manager.getCookies(url);
		Set<String> cookieNames = new HashSet<String>();
		for (Cookie cookie : cookies) {
			cookieNames.add(cookie.getName());
		}
		for (String cookieName : cookieNames) {
			Cookie cookie = manager.getCookie(cookieName);
			manager.removeCookie(cookie);
		}
	}
	private void addCookies(WebClient webClient, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			if (StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			webClient.getCookieManager().addCookie(cookie);
		}
	}
	
	
	/**
	 * 登陆页：获取登陆页cookies和图片验证码
	 * @return ResultData（包含登陆页cookies、验证码图片URL）
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation"})
	public String loginPageHandle(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(PbccrcFunction.class, webParam.getLogback());
		
		ResultData resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getOptions().setUseInsecureSSL(true); //
		
		//清除cookies
		clearCookies(webClient, new URL(PbcCreditFeedParser.PBCCRC_LOGIN));
		
		//重设cookies
		HtmlPage loginPage = webClient.getPage(PbcCreditFeedParser.PBCCRC_LOGIN);
		Set<Cookie> cookies = webClient.getCookieManager().getCookies(new URL(PbcCreditFeedParser.PBCCRC_LOGIN));
		resultData.cookies = cookies;
		
		//imageUrl
		HtmlImage image = (HtmlImage) loginPage.getElementById("imgrc");
		if (image==null) {
			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "由于原网站出现问题，页面中验证码图片无法获取！";
			resultData.ip = IPUtil.getIp();
			resultData.hostName = IPUtil.getHostName();
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of PbccrcFunction.loginPageHandle is:"+resultDataToJson);
			LOGGER.returnRedisResource();
			return resultDataToJson;
		}
		
		File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
		parentDirFile.setReadable(true); //
		parentDirFile.setWritable(true); //
		parentDirFile.canRead();  //
		parentDirFile.canWrite(); //
		if (!parentDirFile.exists()) {
			parentDirFile.mkdirs();
		}
		String imageName = UUID.randomUUID() + ".jpg";
		File codeImageFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + imageName); //new File("F:/TCode/testimgs/pbccrc/1111.jpg"); //
		codeImageFile.setReadable(true); //
		codeImageFile.setWritable(true); //
		LOGGER.info("The codeImageFile of PbccrcFunction.loginPageHandle is:"+codeImageFile.getAbsolutePath());
		image.saveAs(codeImageFile);
		LOGGER.info("----codeImageFile saved!----");
		resultData.imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
		LOGGER.info("The imageUrl is: "+resultData.imageUrl);
		
		//状态信息
		resultData.statusCode = StatusCodeDef.SCCCESS;
		resultData.message = "成功获取登陆页cookies及图片验证码";
		
		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of PbccrcFunction.loginPageHandle is:"+resultDataToJson);
		
		LOGGER.returnRedisResource();
		
		return resultDataToJson;
	}
	
	
	/**
	 * 登录
	 * @param pbccrcParam（登陆页cookies、username、password、imageCode）
	 * @return ResultData
	 * @throws IOException 
	 * @throws FailingHttpStatusCodeException 
	 */
	@SuppressWarnings("deprecation")
	public String login(PbccrcParam pbccrcParam, WebParam webParam) throws FailingHttpStatusCodeException, IOException {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(PbccrcFunction.class, webParam.getLogback());
		
		ResultData resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getOptions().setUseInsecureSSL(true); //
		
		//pbccrcParam
		Set<Cookie> cookies = pbccrcParam.getCookies();
		String username = pbccrcParam.getUsername();
		String password = pbccrcParam.getPassword();
		String imageCode = pbccrcParam.getImageCode();
		if (cookies==null || cookies.isEmpty()) {
			resultData.statusCode = StatusCodeDef.COOKIE_ERROR;
			resultData.message = "没有登录页面的cookie！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of PbccrcFunction.login is:"+resultDataToJson);
			return resultDataToJson;
		}
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			resultData.statusCode = StatusCodeDef.USERNAME_OR_PASSWORD_ERROR;
			resultData.message = "用户名或密码为空！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of PbccrcFunction.login is:"+resultDataToJson);
			return resultDataToJson;
		}
		if (StringUtils.isEmpty(imageCode)) {
			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "登录图片验证码为空！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of PbccrcFunction.login is:"+resultDataToJson);
			return resultDataToJson;
		}
		
		//初始化webClient，并post请求登录
		webClient.addRequestHeader("Origin", "https://ipcrs.pbccrc.org.cn");
		webClient.addRequestHeader("Referer", "https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp");
		addCookies(webClient, cookies);
		WebRequest requestLogin = new WebRequest(new URL("https://ipcrs.pbccrc.org.cn/login.do;"), HttpMethod.POST);
		ArrayList<NameValuePair> nameValuePairsLogin = new ArrayList<NameValuePair>();
		nameValuePairsLogin.add(new NameValuePair("loginname", username));  //
		nameValuePairsLogin.add(new NameValuePair("password", password));   //
		nameValuePairsLogin.add(new NameValuePair("_@IMGRC@_", imageCode)); //
		nameValuePairsLogin.add(new NameValuePair("method", "login")); //
		requestLogin.setRequestParameters(nameValuePairsLogin);
		HtmlPage loggedPage = webClient.getPage(requestLogin);
		resultData.html = loggedPage.asXml();
		//后处理
		webClient.removeRequestHeader("Origin");
		webClient.removeRequestHeader("Referer");
		
		//判断是否已登录
		if (resultData.html.contains("验证码输入错误,请重新输入") || resultData.html.contains("验证码不能为空或空格")) {
			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "登录验证码输入有误！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of PbccrcFunction.login is:"+resultDataToJson);
			return resultDataToJson;
		}
		if (resultData.html.contains("登录名或密码错误！") || resultData.html.contains("密码不能为空或空格") || resultData.html.contains("登录名不能为空或空格")) {
			resultData.statusCode = StatusCodeDef.USERNAME_OR_PASSWORD_ERROR;
			resultData.message = "登录名或密码错误！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of PbccrcFunction.login is:"+ resultDataToJson);
			return resultDataToJson;
		}
		DomElement welcomeFrame = loggedPage.getElementById("mainFrame");
		if (welcomeFrame==null || !"https://ipcrs.pbccrc.org.cn/welcome.do".equals(welcomeFrame.getAttribute("src"))) {
			resultData.statusCode = StatusCodeDef.FAILURE;
			resultData.message = "登录失败！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of PbccrcFunction.login is:"+resultDataToJson);
			return resultDataToJson;
		}
		
		//获取登录操作后的数据
		resultData.statusCode = StatusCodeDef.SCCCESS;
		resultData.message = "登录成功";
		webClient.getCookieManager().addCookie(new Cookie("ipcrs.pbccrc.org.cn", "username", username, "/", null, false, false));
		webClient.getCookieManager().addCookie(new Cookie("ipcrs.pbccrc.org.cn", "zs", EncryptUtil.sha256Salt(password, Constants.SHA256_SALT), "/", null, false, false));
		Set<Cookie> cookies2 = webClient.getCookieManager().getCookies(new URL(LOGGEDIN_PAGE)); 
		resultData.cookies = cookies2;
		
		//返回
		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of PbccrcFunction.login is:"+resultDataToJson);
		
		LOGGER.returnRedisResource();
		
		return resultDataToJson;
	}
	
	
	/**
	 * 获取征信报告(JSON)
	 * @param pbccrcParam（登录cookies、tradeCode）
	 * @return ResultData
	 * @throws IOException 
	 * @throws FailingHttpStatusCodeException 
	 */
	public String getReport(PbccrcParam pbccrcParam, WebParam webParam) throws FailingHttpStatusCodeException, IOException {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(PbccrcFunction.class, webParam.getLogback());
		
		ResultData resultData;
		resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getOptions().setUseInsecureSSL(true); //
		
		//pbccrcParam
		Set<Cookie> cookies = pbccrcParam.getCookies();
		String tradeCode = pbccrcParam.getTradeCode();
		if (cookies==null || cookies.isEmpty()) {
			resultData.statusCode = StatusCodeDef.COOKIE_ERROR;
			resultData.message = "没有登录后的cookie！";
			return parseResultDataToJson(resultData);
		}
		if (StringUtils.isEmpty(tradeCode)) {
			resultData.statusCode = StatusCodeDef.MOBILE_VERICODE_ERROR;
			resultData.message = "授权码为空！";
			return parseResultDataToJson(resultData);
		}
		
		//初始化webClient，并post请求获取报告
		addCookies(webClient, cookies);
		
		Cookie usernameCookie = webClient.getCookieManager().getCookie("username");
		String username = null;
		if (usernameCookie!=null) {
			username = usernameCookie.getValue();
			username = StringUtils.isEmpty(username) ? "unknown" : username;
		}
		
		WebRequest request = new WebRequest(new URL("https://ipcrs.pbccrc.org.cn/simpleReport.do?method=viewReport"), HttpMethod.POST);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("reportformat", "21"));
		nameValuePairs.add(new NameValuePair("tradeCode", tradeCode)); //身份验证码
		request.setRequestParameters(nameValuePairs);
		HtmlPage reportPage = webClient.getPage(request);
		resultData.html = reportPage.asXml();
		
		//获取操作后的数据
		if (resultData.html.contains("报告编号：") && resultData.html.contains("姓名：")) {
			resultData.statusCode = StatusCodeDef.SCCCESS;
			resultData.message = "查询成功";
		}
		if (resultData.html.contains("查询码输入错误，请重新输入。")) {
			resultData.statusCode = StatusCodeDef.MOBILE_VERICODE_ERROR;
			resultData.message = "查询码输入有误或已经过期！";
		}
		
		resultData.username = username;
		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of PbccrcFunction.getReport is:"+resultDataToJson);
		
		LOGGER.returnRedisResource();
		
		return resultDataToJson;
	}
	
	
	/**
	 * 获取PDF征信报告前获取图片验证码
	 * @param pbccrcParam （登录cookies）
	 * @return
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException
	 * @throws IOException
	 */
	public String getPDFReportImageCode(PbccrcParam pbccrcParam, WebParam webParam) throws FailingHttpStatusCodeException, MalformedURLException, IOException  {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(PbccrcFunction.class, webParam.getLogback());
		
		LOGGER.info("------------PbccrcFunction.getPDFReportImageCode start!------------");
		ResultData resultData;
		resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getOptions().setUseInsecureSSL(true); //
		
		//pbccrcParam
		Set<Cookie> cookies = pbccrcParam.getCookies();
		if (cookies==null || cookies.isEmpty()) {
			resultData.statusCode = StatusCodeDef.COOKIE_ERROR;
			resultData.message = "没有登录后的cookie！";
			return parseResultDataToJson(resultData);
		}
		resultData.cookies = pbccrcParam.getCookies();
		
		//imageUrl
		webClient.addRequestHeader("Host", "ipcrs.pbccrc.org.cn");
		webClient.addRequestHeader("Referer", "https://ipcrs.pbccrc.org.cn/simpleReport.do?method=viewReport");
		addCookies(webClient, cookies);
		HtmlPage pdfImageCodePage = webClient.getPage("https://ipcrs.pbccrc.org.cn/reportAction.do?method=downloadwelcome");
		resultData.html = pdfImageCodePage.asXml();
		HtmlImage image = (HtmlImage) pdfImageCodePage.getElementById("imgrc");
		File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
		parentDirFile.setReadable(true); //
		parentDirFile.setWritable(true); //
		parentDirFile.canRead();  //
		parentDirFile.canWrite(); //
		if (!parentDirFile.exists()) {
			parentDirFile.mkdirs();
		}
		String imageName = UUID.randomUUID() + ".jpg";
		File codeImageFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + imageName);
		codeImageFile.setReadable(true); //
		codeImageFile.setWritable(true); //
		LOGGER.info("The codeImageFile of PbccrcFunction.getPDFReportImageCode is:"+codeImageFile.getAbsolutePath());
		image.saveAs(codeImageFile);
		LOGGER.info("----codeImageFile saved!----");
		resultData.imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
		LOGGER.info("The imageUrl of PbccrcFunction.getPDFReportImageCode is: "+resultData.imageUrl);
		
		//后处理
		webClient.removeRequestHeader("Host");
		webClient.removeRequestHeader("Referer");
		
		//状态信息
		resultData.statusCode = StatusCodeDef.SCCCESS;
		resultData.message = "成功获取PDF征信报告的图片验证码";
		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of PbccrcFunction.loginPageHandle is:"+resultDataToJson);
		
		LOGGER.returnRedisResource();
		
		return resultDataToJson;
	}
	
	/**
	 * 获取征信报告（PDF）
	 * @param pbccrcParam （登录cookies，获取pdf报告的图片验证码imageCode）
	 * @return
	 * @throws IOException 
	 * @throws FailingHttpStatusCodeException 
	 */
	public String getPDFReport(PbccrcParam pbccrcParam, WebParam webParam) throws FailingHttpStatusCodeException, IOException {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(PbccrcFunction.class, webParam.getLogback());
		
		LOGGER.info("------------PbccrcFunction.getPDFReport start!------------");
		ResultData resultData;
		resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getOptions().setUseInsecureSSL(true); //
		
		//pbccrcParam
		Set<Cookie> cookies = pbccrcParam.getCookies();
		String imageCode = pbccrcParam.getImageCode();
		if (cookies==null || cookies.isEmpty()) {
			resultData.statusCode = StatusCodeDef.COOKIE_ERROR;
			resultData.message = "没有登录后的cookie！";
			return parseResultDataToJson(resultData);
		}
		if (StringUtils.isEmpty(imageCode)) {
			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "获取PDF征信报告的图片验证码为空！";
			return parseResultDataToJson(resultData);
		}
		
		//初始化webClient，并post请求获取pdf报告
		webClient.addRequestHeader("Origin", "https://ipcrs.pbccrc.org.cn");
		webClient.addRequestHeader("Referer", "https://ipcrs.pbccrc.org.cn/reportAction.do?method=downloadwelcome");
		webClient.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		addCookies(webClient, cookies);
		WebRequest pdfReportRequest = new WebRequest(new URL("https://ipcrs.pbccrc.org.cn/reportAction.do"), HttpMethod.POST);
		ArrayList<NameValuePair> nameValuePairsPDFReport = new ArrayList<NameValuePair>();
		nameValuePairsPDFReport.add(new NameValuePair("_@IMGRC@_", imageCode)); //
		nameValuePairsPDFReport.add(new NameValuePair("method", "download")); //
		nameValuePairsPDFReport.add(new NameValuePair("count", "")); //
		pdfReportRequest.setRequestParameters(nameValuePairsPDFReport);
		Page pdfPage = webClient.getPage(pdfReportRequest);
		WebResponse webResponse = pdfPage.getWebResponse();
		InputStream is = webResponse.getContentAsStream(); //is
		String html = webResponse.getContentAsString(); //html
		
		if (!StringUtils.isEmpty(html) && html.contains("验证码输入错误，请重新输入。")) {
			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "获取PDF征信报告的图片验证码输入错误！";
			resultData.html = html;
			return parseResultDataToJson(resultData);
		}
		if (!StringUtils.isEmpty(html) && html.contains("24小时内下载超过10次。")) {
			resultData.statusCode = StatusCodeDef.FREQUENCY_LIMITED;
			resultData.message = "24小时内下载超过10次";
			resultData.html = html;
			return parseResultDataToJson(resultData);
		}
		
		Cookie usernameCookie = webClient.getCookieManager().getCookie("username");
		String username = null;
		if (usernameCookie!=null) {
			username = usernameCookie.getValue();
			username = StringUtils.isEmpty(username) ? "unknown" : username;
		}
		
		//后处理
		webClient.removeRequestHeader("Origin");
		webClient.removeRequestHeader("Referer");
		webClient.removeRequestHeader("Content-Type");
		
		//流处理（将pdf报告存到共享文件服务器）
		String pdfReportFileName = UUID.randomUUID() + ".pdf";
		FileUtils.copyInputStreamToFile(is, new File(StormTopologyConfig.getNfs_filepath() + "/" + pdfReportFileName));
		
		resultData.username = username;
		resultData.statusCode = StatusCodeDef.SCCCESS;
		resultData.message = "成功获取PDF征信报告";
		resultData.reportFileURL = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + pdfReportFileName;
		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of PbccrcFunction.getReport is:"+resultDataToJson);
		
		LOGGER.returnRedisResource();
		
		return resultDataToJson;
	}
	
	
	/*public static void main(String[] args) throws Exception {
		PbccrcFunction pf = new PbccrcFunction();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type resultDataType = new TypeToken<PbccrcFunction.ResultData>(){}.getType();
		PbccrcFunction.ResultData resultData = null;
		
		//1
		String resultDataJson1 = pf.loginPageHandle();
		resultData = gson.fromJson(resultDataJson1, resultDataType);
		
		//获取验证码
		System.out.println("请输入验证码：");
		Scanner sc = new Scanner(System.in);
		String imageCode = sc.next();
		
		//2
		String resultDataJson2 = pf.login(new PbccrcParam("kinglyjn1", "12qwaszx", imageCode, null, resultData.cookies));
		resultData = gson.fromJson(resultDataJson2, resultDataType);
		System.out.println(gson.toJson(resultData));
		
		//3
		String resultDataJson3 = pf.getReport(new PbccrcParam(null, null, null, "rendxr", resultData.cookies));
		resultData = gson.fromJson(resultDataJson3, resultDataType);
		System.out.println(resultData);
	}*/
}


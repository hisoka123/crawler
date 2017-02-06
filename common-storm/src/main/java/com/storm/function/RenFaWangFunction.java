package com.storm.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.SerializedAllIn;
import com.gargoylesoftware.htmlunit.DefaultPageCreator;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.PageCreator;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.ocr.utils.OCRConnectUtils;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.WebParam;
import com.storm.util.IPUtil;


public class RenFaWangFunction {
	private String hostName = IPUtil.getHostName();
	private String ip = IPUtil.getIp();
	
	public class ResultData {
		public String statusCode;
		public String message;
		public String html;
	}
	
	private void addCookies(WebClient webClient, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			if (StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			webClient.getCookieManager().addCookie(cookie);
		}
	}
	
	
	public String getSerializedAllIn(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(RenFaWangFunction.class, webParam.getLogback());
		
		LOGGER.info("==================RenFaWangFunction.getSerializedAllIn start!======================");
		SerializedAllIn serializedAllIn = new SerializedAllIn(); //
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getCookieManager().clearCookies();
		Gson gson = new GsonBuilder().create();
		Map<String, String> resultMap = new HashMap<String, String>();
		
		String searchPageUrl = webParam.getSearchPage();
		if (StringUtils.isEmpty(searchPageUrl)) {
			LOGGER.error("The searchPageUrl is not defined!");
			return null;
		}
		WebRequest webRequest = new WebRequest(new URL(searchPageUrl), HttpMethod.GET);
		HtmlPage searchPage = webClient.getPage(webRequest);
		
		WebResponse webResponse = searchPage.getWebResponse();
		HtmlImage image = searchPage.getFirstByXPath(webParam.getCodeImageId());
		if (image==null) {
			LOGGER.error("The image element is not found!");
			resultMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			resultMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + searchPage.asXml());
			resultMap.put("isImageNull", "true");
			return gson.toJson(resultMap);
		}
		
		File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
		parentDirFile.setReadable(true);
		parentDirFile.setWritable(true);
		if (!parentDirFile.exists()) {
			parentDirFile.mkdirs();
		}
		String imageName = UUID.randomUUID() + ".jpg";
		File codeImageFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + imageName);
		codeImageFile.setReadable(true);
		codeImageFile.setWritable(true);
		
		LOGGER.info("The codeImageFile of GsxtFunction.getSerializedAllIn is:"+codeImageFile.getAbsolutePath());
		try {
			image.saveAs(codeImageFile);
		} catch (IOException e) {
			LOGGER.error("No image detected in response!");
			resultMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			resultMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + searchPage.asXml());
			resultMap.put("isImageNull", "true");
			return gson.toJson(resultMap);
		}
		LOGGER.info("----codeImageFile saved!----");
		
		//imageUrl
		serializedAllIn.setImageUrl("http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName);
		LOGGER.info("The serializedAllIn.imageUrl is: "+ serializedAllIn.getImageUrl());
		
		//cookies
		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
		serializedAllIn.setCookies(cookies);
		
		//webResponse
		serializedAllIn.setWebResponse(webResponse);
		
		//序列化操作
		String serializedAllInFileName = UUID.randomUUID().toString();
		File serializedAllInFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedAllInFile));
		oos.writeObject(serializedAllIn);
		oos.close();
		LOGGER.info("The serializedAllInFileName is: "+ serializedAllInFileName);
		
		//返回 序列化文件名 和 验证码图片的URL
		resultMap.put("codeImageUrl", serializedAllIn.getImageUrl());
		resultMap.put("serializedFileName", serializedAllInFileName);
		
		LOGGER.returnRedisResource();
		
		return gson.toJson(resultMap);
	}
	
	public String search(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(RenFaWangFunction.class, webParam.getLogback());
		
		LOGGER.info("==================RenFaWangFunction.search start!======================");
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		
		String searchPageUrl = webParam.getSearchPage();
		if (StringUtils.isEmpty(searchPageUrl)) {
			LOGGER.error("The searchPageUrl is not defined!");
			return null;
		}
		
		//反序列化操作
		String serializedAllInFileName = webParam.getSerializedFileName();
		File serializedAllInFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedAllInFile));
		SerializedAllIn serializedAllIn = (SerializedAllIn) ois.readObject();
		ois.close();
		
		//cookies
		Set<Cookie> cookies = serializedAllIn.getCookies();
		addCookies(webClient, cookies);

		//打开上次的会话窗口
		WebResponse webResponse = serializedAllIn.getWebResponse();
		PageCreator pageCreator = new DefaultPageCreator();
		HtmlPage searchPage = (HtmlPage) pageCreator.createPage(webResponse, webClient.openWindow(new URL(searchPageUrl), "windowName"));
		
		//请求参数		
		Map<String, String> params = webParam.getParams();
		String pname = params.get("pname"); 
		String cardNum = params.get("cardNum"); 
		String j_captcha = params.get("j_captcha");
		String searchCourtName = params.get("searchCourtName");
		String selectCourtId = params.get("selectCourtId");
		String selectCourtArrange = params.get("selectCourtArrange");
		String keywordXpath1 = params.get("pnameXpath");
		String keywordXpath2 = params.get("cardNumXpath");
		String imagecodeXpath = params.get("imagecodeXpath");
		String searchCourtNameXpath = params.get("searchCourtNameXpath");
		String selectCourtIdXpath = params.get("selectCourtIdXpath");
		String selectCourtArrangeXpath = params.get("selectCourtArrangeXpath");
		HtmlTextInput inputKeyword1 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath1); 
		HtmlTextInput inputKeyword2 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath2);
		HtmlTextInput inputImagecode = (HtmlTextInput)searchPage.getFirstByXPath(imagecodeXpath); //querySelector("#checkcodeAlt");
		HtmlHiddenInput inputCourtName = (HtmlHiddenInput)searchPage.getFirstByXPath(searchCourtNameXpath); //querySelector("#checkcodeAlt");
		HtmlHiddenInput inputCourtId = (HtmlHiddenInput)searchPage.getFirstByXPath(selectCourtIdXpath); //querySelector("#checkcodeAlt");
		HtmlHiddenInput inputCourtArrange = (HtmlHiddenInput)searchPage.getFirstByXPath(selectCourtArrangeXpath); //querySelector("#checkcodeAlt");
		inputKeyword1.setText(pname);		
		inputKeyword2.setText(cardNum);
		inputImagecode.setText(j_captcha);
		inputCourtName.setDefaultValue(searchCourtName);
		inputCourtId.setDefaultValue(selectCourtId);
		inputCourtArrange.setDefaultValue(selectCourtArrange);
		
	
		//点击提交请求
		String submitBtnXpath = params.get("submitBtnXpath");
		HtmlElement submitButton = (HtmlElement)searchPage.getFirstByXPath(submitBtnXpath); //querySelector("input[onclick*=fn_search]");
		HtmlPage loggedPage = submitButton.click();
		
		FrameWindow window=loggedPage.getFrameByName("contentFrame");
		HtmlPage iframePage =(HtmlPage) window.getEnclosedPage();
		
		return iframePage.asXml();
	}
	
	public String getDetailJson(WebClient webClient, WebParam webParam) throws IOException {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(RenFaWangFunction.class, webParam.getLogback());
		
		Page page = webClient.getPage("http://zhixing.court.gov.cn/search/security/jcaptcha.jpg");
		InputStream is = page.getWebResponse().getContentAsStream();
		String imageName = UUID.randomUUID() + ".jpg";
		File file = new File(StormTopologyConfig.getNfs_filepath() + "/" + imageName);
		FileUtils.copyInputStreamToFile(is, file);
		
		LOGGER.info("The serializedAllIn.imageUrl is: "+ file.getAbsolutePath());
		String imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
		
		String codevalue = "";
		try {
			codevalue = OCRConnectUtils.getVerifycode("/cjy/getVerifycode", imageUrl, "4005");
			//codevalue = new Scanner(System.in).next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		WebRequest webRequest = new WebRequest(new URL(webParam.getUrl()+"&j_captcha="+codevalue+"&16"), HttpMethod.GET);
		WebResponse webResponse = webClient.getPage(webRequest).getWebResponse();
			
		LOGGER.returnRedisResource();
		return webResponse.getContentAsString();
	}
	
	public String getDetailData(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(RenFaWangFunction.class, webParam.getLogback());
		
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		//反序列化操作
		String serializedAllInFileName = webParam.getSerializedFileName();
		File serializedAllInFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedAllInFile));
		SerializedAllIn serializedAllIn = (SerializedAllIn) ois.readObject();
		ois.close();
		
		//cookies
		Set<Cookie> cookies = serializedAllIn.getCookies();
		addCookies(webClient, cookies);
		LOGGER.info("cookies:" + cookies);
		
		String resultJson = "";
		int count = 0;//验证码识别错了，3次重新识别机会
		do {
			resultJson = getDetailJson(webClient, webParam);
			count++;
		} while ("{}".equals(resultJson) && count < 3);
		
		ResultData resultData = new ResultData();
		resultData.html = resultJson;
		String json = new GsonBuilder().create().toJson(resultData);
		LOGGER.info("renfawang-resultJson:"+json);
		
		LOGGER.returnRedisResource();
		
		return json;
	}
	
	public String searchWitchOCR(WebParam webParam) throws Exception{
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(RenFaWangFunction.class, webParam.getLogback());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("ip", ip);
		result.put("hostName", hostName);
		
		String queryType = webParam.getParams().get("queryType");
		String keyword = webParam.getParams().get("keyword");
		
		String serializedAllIn = getSerializedAllIn(webParam);
		
		Gson gson = new GsonBuilder().create();
		Type mapType = new TypeToken<HashMap<String,String>>(){}.getType();
		Map<String, String> data = gson.fromJson(serializedAllIn, mapType);
		
		if (data.get("isImageNull") != null) {
			result.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			result.put("searchPageHtml", data.get("searchPageHtml"));
			result.put("isImageNull", "true");
			return new GsonBuilder().setPrettyPrinting().create().toJson(result);
		}
		
		String serializedFileName = data.get("serializedFileName");
		String codeImageUrl = data.get("codeImageUrl");
		String verifycode = "";
		try {
			verifycode = OCRConnectUtils.getVerifycode("/cjy/getVerifycode", codeImageUrl, "4005");
//			verifycode = new Scanner(System.in).next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result.put("imageUrl", codeImageUrl);
		result.put("imagecode", verifycode);
		
		WebParam webParam2 = new WebParam();
		webParam2.setSearchPage("http://zhixing.court.gov.cn/search/");
		webParam2.setUrl("http://zhixing.court.gov.cn/search/newsearch");
		webParam2.setSerializedFileName(serializedFileName);
		webParam2.addParam("j_captcha", verifycode);
		webParam2.addParam("searchCourtName", "全国法院（包含地方各级法院）");
		webParam2.addParam("selectCourtId", "1");
		webParam2.addParam("selectCourtArrange", "1");
		webParam2.addParam("pnameXpath", "//input[@id='pname']");
		webParam2.addParam("cardNumXpath", "//input[@id='cardNum']");
		webParam2.addParam("searchCourtNameXpath", "//input[@id='searchCourtName']");
		webParam2.addParam("selectCourtIdXpath", "//input[@id='selectCourtId']");
		webParam2.addParam("selectCourtArrangeXpath", "//input[@id='selectCourtArrange']");
		webParam2.addParam("imagecodeXpath", "//input[@id='j_captcha']");
		webParam2.addParam("submitBtnXpath", "//button[@id='button']");
		
		if ("公司名称".equals(queryType)) {
			webParam2.addParam("pname", keyword);
			webParam2.addParam("cardNum", "");
		} else if ("身份证号".equals(queryType)) {
			webParam2.addParam("cardNum", keyword);
			webParam2.addParam("pname", "");
		}
		
		String listHtml = search(webParam2);
		LOGGER.info("listHtml:" + listHtml);
		
		if (listHtml.contains("验证码错误")) {
			result.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			result.put("searchPageHtml", IPUtil.getHostAndIpStr() + listHtml);
			result.put("isImageNull", "false");
			return gson.toJson(result);
		}
		
		String alertMsg = WebCrawler.getAlertMsg();
		System.err.println(alertMsg);
		if ("被执行人姓名/名称中不能含有空格或其他特殊字符".equals(alertMsg)) {
			result.put("statusCodeDef", StatusCodeDef.ILLEGAL_CHAR);
			return gson.toJson(result);
		} else if ("输入身份证号/组织结构代码不合法".equals(alertMsg)) {
			result.put("statusCodeDef", StatusCodeDef.ILLEGAL_PARAM);
			return gson.toJson(result);
		}
		
		List<String> listResult = new ArrayList<String>();
		
		//解析页面获得查询详情的id
		Document doc = Jsoup.parse(listHtml);
		Element table = doc.getElementById("Resultlist");
		if (table != null) {
			Elements tbodys = table.select("tbody");
			if (tbodys != null && !tbodys.isEmpty()) {
				Element tbody = tbodys.get(0);
				if (tbody != null) {
					Elements trs = tbody.select("tr");
					if (trs.size() == 1) {
						result.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
						return gson.toJson(result);
					}
					
					int count = 0;
					for (Element tr : trs) {
						Elements tds = tr.select("td");
						for (Element td : tds) {
							Elements anchors = td.getElementsByClass("View");
							for (Element anchor : anchors) {
								if (count == 5) break;
								String idAttr = anchor.attr("id");
								WebParam webParam3 = new WebParam();
								webParam3.setSerializedFileName(serializedFileName);
								webParam3.setUrl("http://zhixing.court.gov.cn/search/newdetail?id="+idAttr);
								
								String detailData = getDetailData(webParam3);
								listResult.add(detailData);
								count++;
							}
						}
					}
				}
			}
		}
		
		result.put("statusCodeDef", StatusCodeDef.SCCCESS);
		result.put("detailData", listResult);
		
		LOGGER.returnRedisResource();
		
		return gson.toJson(result);
	}
			
//	public static void main(String[] args) throws Exception {
//		WebParam webParam = new WebParam();
//		webParam.setSearchPage("http://zhixing.court.gov.cn/search/"); 
//		webParam.setCodeImageId("//img[@id='captchaImg']");
//		webParam.addParam("keyword", "532722197511040914");
//		webParam.addParam("queryType", "身份证号");
//		
//		RenFaWangFunction fun = new RenFaWangFunction();
//		String result = fun.searchWitchOCR(webParam);
//		FileUtils.writeStringToFile(new File("C:\\TCode\\renfawang\\result.json"), result);
//		FileUtils.writeStringToFile(new File("C:\\TCode\\renfawang\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		Type mapType = new TypeToken<HashMap<String,String>>(){}.getType();
//		Map<String, String> data = gson.fromJson(serializedAllIn, mapType);
//		webParam.setSerializedFileName(data.get("serializedFileName"));
//		
//		java.util.Scanner sc = new java.util.Scanner(System.in);
//		String j_captcha = sc.next();
//		webParam.addParam("pname", "王刚");
//		webParam.addParam("cardNum", "");
//		webParam.addParam("j_captcha", j_captcha);
//		webParam.addParam("searchCourtName", "全国法院（包含地方各级法院）");
//		webParam.addParam("selectCourtId", "1");
//		webParam.addParam("selectCourtArrange", "1");
//		webParam.addParam("pnameXpath", "//input[@id='pname']");
//		webParam.addParam("cardNumXpath", "//input[@id='cardNum']");
//		webParam.addParam("imagecodeXpath", "//input[@id='j_captcha']");
//		webParam.addParam("submitBtnXpath", "//button[@id='button']");
//		webParam.addParam("searchCourtNameXpath", "//input[@id='searchCourtName']");
//		webParam.addParam("selectCourtIdXpath", "//input[@id='selectCourtId']");
//		webParam.addParam("selectCourtArrangeXpath", "//input[@id='selectCourtArrange']");
//		
//		String html = fun.search(webParam);
//		
//		System.out.println(html);
//	}	
	
/*	public static void main(String[] args) throws Exception {
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		Page page = webClient.getPage("http://zhixing.court.gov.cn/search/security/jcaptcha.jpg");
		InputStream is = page.getWebResponse().getContentAsStream();
		FileUtils.copyInputStreamToFile(is, new File("C:/TCode/gsxt/1111.jpg"));
	}*/
}


package com.storm.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.SerializedAllIn;
import com.gargoylesoftware.htmlunit.DefaultPageCreator;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.PageCreator;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
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
import com.storm.util.CommandUtil;
import com.storm.util.IPUtil;

import java.lang.reflect.Type;


public class IecmsFunction {
	private String hostName = IPUtil.getHostName();
	private String ip = IPUtil.getIp();	
	private void addCookies(WebClient webClient, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			if (StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			webClient.getCookieManager().addCookie(cookie);
		}
	}
	
	
	public String getSerializedAllIn(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(IecmsFunction.class, webParam.getLogback());
		
		LOGGER.info("==================IECMSFunction.getSerializedAllIn start!======================");
		SerializedAllIn serializedAllIn = new SerializedAllIn(); //
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		Gson gson = new GsonBuilder().create();
		
		String searchPageUrl = webParam.getSearchPage();
		if (StringUtils.isEmpty(searchPageUrl)) {
			LOGGER.error("The searchPageUrl is not defined!");
			return null;
		}
		WebRequest webRequest = new WebRequest(new URL(searchPageUrl), HttpMethod.GET);
		HtmlPage searchPage = webClient.getPage(webRequest);
		System.out.println(searchPage.asXml());
		WebResponse webResponse = searchPage.getWebResponse();
		HtmlImage image = searchPage.getFirstByXPath(webParam.getCodeImageId());
		if (image==null) {
			LOGGER.error("The image element is not found!");
			return null;
		}
		
		File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
		parentDirFile.setReadable(true); //
		parentDirFile.setWritable(true); //
		if (!parentDirFile.exists()) {
			parentDirFile.mkdirs();
		}
		String imageName = UUID.randomUUID() + ".jpg";
		File codeImageFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + imageName);
		codeImageFile.setReadable(true); //
		codeImageFile.setWritable(true); //
		
		LOGGER.info("The codeImageFile of GsxtFunction.getSerializedAllIn is:"+codeImageFile.getAbsolutePath());
		image.saveAs(codeImageFile);
		LOGGER.info("----codeImageFile saved!----");
		
		//imageUrl
		serializedAllIn.setImageUrl("http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName);
		LOGGER.info("The serializedAllIn.imageUrl is:"+serializedAllIn.getImageUrl());
		
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
		LOGGER.info("The serializedAllInFileName is:"+serializedAllInFileName);
		
		//返回 序列化文件名 和 验证码图片的URL
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("codeImageUrl", serializedAllIn.getImageUrl());
		resultMap.put("serializedFileName", serializedAllInFileName);
		
		LOGGER.returnRedisResource();
		
		return gson.toJson(resultMap);
	}
	
	public String search(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(IecmsFunction.class, webParam.getLogback());
		
		LOGGER.info("==================iecmsFunction.search start!======================");
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
//		String keyword = params.get("keyword"); //关键字
		String keywordXpath1 = params.get("keywordXpath1");
		String keywordXpath2 = params.get("keywordXpath2");
		String keywordXpath3 = params.get("keywordXpath3");	
		String corp_code = params.get("corp_code"); //关键字
		String corp_name = params.get("corp_name"); //关键字
		String sc_code = params.get("sc_code"); //关键字
		HtmlTextInput inputKeyword1 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath1); //querySelector("#keyword");
		HtmlTextInput inputKeyword2 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath2); //querySelector("#keyword");
		HtmlTextInput inputKeyword3 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath3); //querySelector("#keyword");
		inputKeyword1.setText(corp_code);		
		inputKeyword2.setText(corp_name);
		inputKeyword3.setText(sc_code);
		
		String codea =  params.get("codea"); //图片验证码
		String imagecodeXpath = params.get("imagecodeXpath");
		HtmlTextInput inputImagecode = (HtmlTextInput)searchPage.getFirstByXPath(imagecodeXpath); //querySelector("#checkcodeAlt");
		inputImagecode.setText(codea);
	
		//点击提交请求
		String loginBtnXpath = params.get("loginBtnXpath");
		HtmlElement loginButton = (HtmlElement)searchPage.getFirstByXPath(loginBtnXpath); //querySelector("input[onclick*=fn_search]");
		HtmlPage loggedPage = loginButton.click();
		
		LOGGER.returnRedisResource();
		
		return loggedPage.asXml();
	}
	
	public String searchWithOCR(WebParam webParam) throws Exception {
		WebParam webParam1 = new WebParam();
		Map<String, Object> result = new HashMap<String, Object>();
		webParam1.setSearchPage("http://iecms.ec.com.cn/iecms/corp/index4.jsp");
		webParam1.setCodeImageId("//img[@id='randImage']");
		String serializedAllIn = getSerializedAllIn(webParam1);		
		Gson gson = new GsonBuilder().create();
		Type mapType = new TypeToken<HashMap<String,String>>(){}.getType();
		Map<String, String> data = gson.fromJson(serializedAllIn, mapType);		
		String serializedFileName = data.get("serializedFileName");
		String codeImageUrl = data.get("codeImageUrl");
		String verifycode = "";
		
		if (data.get("isImageNull") != null) {
			result.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			result.put("searchPageHtml", data.get("searchPageHtml"));
			result.put("isImageNull", "true");
			return new GsonBuilder().setPrettyPrinting().create().toJson(result);
		}

		try {
			verifycode = OCRConnectUtils.getVerifycode("/cjy/getVerifycode", codeImageUrl, "1104");
			//verifycode = new Scanner(System.in).next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result.put("imageUrl", codeImageUrl);
		result.put("imagecode", verifycode);			
		result=this.searchonce(webParam, serializedFileName, verifycode,result);	
		return new GsonBuilder().setPrettyPrinting().create().toJson(result);
	}
	
	
	public Map<String, Object> searchonce(WebParam webParam,String filename,String code,Map<String, Object> result) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(IecmsFunction.class, webParam.getLogback());
		//
		result.put("ip", ip);
		result.put("hostName", hostName);
		
		LOGGER.info("==================iecmsFunction.search start!======================");
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		
		String searchPageUrl = webParam.getSearchPage();
		if (StringUtils.isEmpty(searchPageUrl)) {
			LOGGER.error("The searchPageUrl is not defined!");
			return null;
		}
		
		//反序列化操作
		String serializedAllInFileName = filename;
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
//		String keyword = params.get("keyword"); //关键字
		String keywordXpath1 = params.get("keywordXpath1");
		String keywordXpath2 = params.get("keywordXpath2");
		String keywordXpath3 = params.get("keywordXpath3");	
		String corp_code = params.get("corp_code"); //关键字
		String corp_name = params.get("corp_name"); //关键字
		String sc_code = params.get("sc_code"); //关键字
		HtmlTextInput inputKeyword1 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath1); //querySelector("#keyword");
		HtmlTextInput inputKeyword2 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath2); //querySelector("#keyword");
		HtmlTextInput inputKeyword3 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath3); //querySelector("#keyword");
		inputKeyword1.setText(corp_code);		
		inputKeyword2.setText(corp_name);
		inputKeyword3.setText(sc_code);
		
		String codea =code; //图片验证码
		String imagecodeXpath = params.get("imagecodeXpath");
		HtmlTextInput inputImagecode = (HtmlTextInput)searchPage.getFirstByXPath(imagecodeXpath); //querySelector("#checkcodeAlt");
		inputImagecode.setText(codea);
	
		//点击提交请求
		String loginBtnXpath = params.get("loginBtnXpath");
		HtmlElement loginButton = (HtmlElement)searchPage.getFirstByXPath(loginBtnXpath); //querySelector("input[onclick*=fn_search]");
		HtmlPage loggedPage = loginButton.click();						
		if (loggedPage.asXml().contains("验证码输入不正确")) {
			result.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			result.put("isImageNull", "false");
		} else if(loggedPage.asXml().contains("暂时没有数据"))  {
			result.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			return result;
		}else{
			result.put("dwmybaxt",loggedPage.asXml());
		}
		
		List<HtmlAnchor> anchors_gdxx_detail = (List<HtmlAnchor>)loggedPage.getByXPath("//p/table[@class='listTableClass']/tbody/tr/td/a");
		if(anchors_gdxx_detail.size()>0){
			List<String> gdxx_list = new ArrayList<String>();
			for (HtmlAnchor htmlAnchor : anchors_gdxx_detail) {
				String attribute = htmlAnchor.getAttribute("href");
				String myattribute=attribute.split("\"")[1];
	            String nburldetail="http://iecms.ec.com.cn/iecms/corp/Corp_view.jsp?corp_code="+myattribute;
	            String[] command2 = {"casperjs", "/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js", "--web-security=no", "--url="+nburldetail}; 
	    		String dwmybaxt_detail = CommandUtil.runCommand(command2);
				//HtmlPage nb_detail = htmlAnchor.click();
	    		gdxx_list.add(dwmybaxt_detail);
			}
			
			result.put("dwmybaxt_detail", gdxx_list);
			result.put("statusCodeDef", StatusCodeDef.SCCCESS);
		}		
		LOGGER.returnRedisResource();	
		return result;
	}
			
//	public static void main(String[] args) throws Exception {
//	WebParam webParam = new WebParam();
//	webParam.setSearchPage("http://iecms.ec.com.cn/iecms/corp/index4.jsp"); //("http://iecms.ec.com.cn/iecms/index.jsp");
//	webParam.setCodeImageId("//img[@id='randImage']");
//	
//	IecmsFunction fun = new IecmsFunction();
//	String serializedAllIn = fun.searchWithOCR(webParam);
//	System.out.println("--------" + serializedAllIn);
	
//	
//	Gson gson = new GsonBuilder().setPrettyPrinting().create();
//	Type mapType = new TypeToken<HashMap<String,String>>(){}.getType();
//	Map<String, String> data = gson.fromJson(serializedAllIn, mapType);
//	webParam.setSerializedFileName(data.get("corp_code"));
//	webParam.setSerializedFileName(data.get("corp_name"));
//	webParam.setSerializedFileName(data.get("sc_code"));	
//	webParam.setSerializedFileName(data.get("codea"));
//	webParam.addParam("keywordXpath1", "");
//	webParam.addParam("keywordXpath2", "科技公司");
//	webParam.addParam("keywordXpath3", "");
//	java.util.Scanner sc = new java.util.Scanner(System.in);
//	String codea = sc.next();
//	webParam.addParam("imagecodeXpath", "//input[@name='codea']");
//	webParam.addParam("submitBtnXpath", "//input[@type='submit']");
//	
//	System.out.println(fun.search(webParam));
	
//	public static void main(String[] args) throws Exception {
//		String s="javascript:ViewItem(\"5000203190465\")";
//		String myattribute=s.split("\"")[1];
//		System.out.println(myattribute);
//	}
}	



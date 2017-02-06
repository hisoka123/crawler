package com.storm.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.crawler.domain.json.StatusCodeDef;
import com.crawler.gsxt.domain.json.chongqing.History;
import com.crawler.gsxt.domain.json.chongqing.YearReport;
import com.crawler.htmlparser.AbstractParser;
import com.crawler.storm.def.SerializedAllIn;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.DefaultPageCreator;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.PageCreator;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebResponseData;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.ocr.utils.OCRConnectUtils;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.WebParam;
import com.storm.util.CommandUtil;
import com.storm.util.IPUtil;

public class GsxtFunction {
	private static final String DEFAULT_CHAOJIYING_CODETYPE = "6003";
	private String hostName = IPUtil.getHostName();
	private String ip = IPUtil.getIp();
	
	@SuppressWarnings({ "deprecation" } )
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
			webClient.getCookieManager().addCookie(cookie);
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public String getSerializedAllIn(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(GsxtFunction.class, webParam.getLogback());
		Gson gson = new GsonBuilder().create();
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		try {
			LOGGER.info("==================GsxtFunction.getSerializedAllIn start!======================");
			SerializedAllIn serializedAllIn = new SerializedAllIn(); //
			WebClient webClient = WebCrawler.getInstance().getWebClient();
			
			String searchPageUrl = webParam.getSearchPage();
			if (StringUtils.isEmpty(searchPageUrl)) {
				LOGGER.error("The searchPageUrl is not defined!");
				return null;
			}
			
			//清除之前的cookie
			clearCookies(webClient, new URL(searchPageUrl));
			
			WebRequest webRequest = new WebRequest(new URL(searchPageUrl), HttpMethod.GET);
			HtmlPage searchPage = webClient.getPage(webRequest);
			String searchPageHtml = searchPage.asXml();
			LOGGER.info("searchPageHtml:"+ searchPageHtml);
			
			if (searchPageHtml.contains("可能访问过于频繁或非正常访问") || searchPageHtml.contains("您的访问过于频繁")) {  //在此加各个地区访问过于频繁的异常处理代码 （用“或”关系连接条件）
				resultMap.put("statusCodeDef", StatusCodeDef.FREQUENCY_LIMITED);
				resultMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + searchPageHtml);
				return gson.toJson(resultMap);
			}
			
			//imageUrl
			HtmlImage image = searchPage.getFirstByXPath(webParam.getCodeImageId());
			Map<String, String> webParamParams = webParam.getParams();
			
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
			LOGGER.info("The codeImageFile of GsxtFunction.getSerializedAllIn is:"+ codeImageFile.getAbsolutePath());
			
			if (searchPageUrl.contains("gsxt.cqgs.gov.cn")) { //重庆
				Page page = webClient.getPage("http://gsxt.cqgs.gov.cn/sc.action?width=130&height=40&fs=23");
				InputStream is = page.getWebResponse().getContentAsStream();
				FileUtils.copyInputStreamToFile(is, codeImageFile);
				LOGGER.info("----codeImageFile saved!----");
				LOGGER.info("The codeImageFile of GsxtFunction.getSerializedAllIn is:{}", codeImageFile.getAbsolutePath());
				serializedAllIn.setImageUrl("http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName);
				LOGGER.info("The serializedAllIn.imageUrl is: "+ serializedAllIn.getImageUrl());
			} else {
				if (image==null && webParamParams!=null && !StringUtils.isEmpty(webParamParams.get("imagecodeIframeSrc"))) { //验证码图片标签为空 说明验证码图片和搜索页的关系为iframe-parent关系
					HtmlPage imagecodeIframePage = webClient.getPage(webParamParams.get("imagecodeIframeSrc"));
					image = imagecodeIframePage.getFirstByXPath(webParam.getCodeImageId());
				} else if (image!=null && StringUtils.isEmpty(image.getAttribute("src"))) { //验证码图片标签不为空，而其属性src为空，验证码特殊处理
					image.click();
					
					HtmlElement hyzBtn1 = searchPage.getFirstByXPath("//div[@id='woaicss_con1']/ul/li[2]/div[2]/a");//宁夏换一张按钮
					HtmlElement hyzBtn2 = searchPage.getFirstByXPath("//div[@id='codeWindow']/div/ul/li[2]/div[2]/a");//贵州换一张按钮
					if (StringUtils.isEmpty(image.getAttribute("src")) && hyzBtn1!=null) { //宁夏
						hyzBtn1.click();
					} else if (StringUtils.isEmpty(image.getAttribute("src")) && hyzBtn2!=null) {
						hyzBtn2.click();
					} 
				}
				
				if (searchPageHtml.contains("浙江省工商行政管理局")) { //浙江
					LOGGER.info("====================点击浙江换一张按钮====================");
					HtmlElement hyzBtn3 = searchPage.getFirstByXPath("//a[@id='kaptchaText']"); //浙江换一张按钮
					hyzBtn3.click();
				}
				
				if (image==null) {
					resultMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					resultMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + searchPageHtml);
					resultMap.put("isImageNull", "true");
					return gson.toJson(resultMap);
				}
				
				try {
					image.saveAs(codeImageFile);
					LOGGER.info("----codeImageFile saved!----");
					LOGGER.info("The codeImageFile of GsxtFunction.getSerializedAllIn is:{}", codeImageFile.getAbsolutePath());
					serializedAllIn.setImageUrl("http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName);
					LOGGER.info("The serializedAllIn.imageUrl is: "+ serializedAllIn.getImageUrl());
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info("----codeImageFile saveAs fail----");
					resultMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					resultMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + "保存图片（saveAs）方法异常！");
					return gson.toJson(resultMap);
				}
			}
			
			
			//cookies
			Set<Cookie> cookies = webClient.getCookieManager().getCookies(new URL(searchPageUrl));
			serializedAllIn.setCookies(cookies);
			
			//webResponse
			WebResponse webResponse = searchPage.getWebResponse();
			serializedAllIn.setWebResponse(webResponse);
			
			//序列化操作
			String serializedAllInFileName = UUID.randomUUID().toString() + StatusCodeDef.SERIALIZED_FILE_SUFFIX;
			File serializedAllInFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedAllInFile));
			oos.writeObject(serializedAllIn);
			oos.close();
			LOGGER.info("The serializedAllInFileName is: "+ serializedAllInFileName);
			
			//返回 序列化文件名 和 验证码图片的URL
			resultMap.put("ip", ip);
			resultMap.put("hostName", hostName);
			resultMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			resultMap.put("codeImageUrl", serializedAllIn.getImageUrl());
			resultMap.put("serializedFileName", serializedAllInFileName);
		} finally {
			LOGGER.returnRedisResource();
		}
		
		return gson.toJson(resultMap);
	}
	
	/*====================================================================================*/
	private HtmlPage getSearchPageBySerialize(WebClient webClient, WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(GsxtFunction.class, webParam.getLogback());
		HtmlPage searchPage = null;
		try {
			String searchPageUrl = webParam.getSearchPage();
			if (StringUtils.isEmpty(searchPageUrl)) {
				LOGGER.error("The searchPageUrl is not defined!");
				return null;
			}
			
	//		String serializedAllInFileName = webParam.getSerializedFileName();
	//		File parentAllInDir = new File("C:\\TCode\\gsxt");
	//		if (!parentAllInDir.exists()) {
	//			parentAllInDir.mkdirs();
	//		}
	//		File serializedAllInFile = new File(parentAllInDir, serializedAllInFileName); //new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName);
	//		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedAllInFile));
	//		SerializedAllIn serializedAllIn = (SerializedAllIn) ois.readObject();
	//		ois.close();
			
			//反序列化操作
			String serializedAllInFileName = webParam.getSerializedFileName();
			/*File parentAllInDir = new File("C:\\TCode\\gsxt");
			if (!parentAllInDir.exists()) {
				parentAllInDir.mkdirs();
			}*/
			//File serializedAllInFile = new File(parentAllInDir, serializedAllInFileName);
			File serializedAllInFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName); //new File(parentAllInDir, serializedAllInFileName);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedAllInFile));
			SerializedAllIn serializedAllIn = (SerializedAllIn) ois.readObject();
			ois.close();
			
			//cookies
			Set<Cookie> cookies = serializedAllIn.getCookies();
			clearCookies(webClient, new URL(searchPageUrl));
			addCookies(webClient, cookies);
			
			//打开上次的会话窗口
			WebResponse webResponse = serializedAllIn.getWebResponse();
			PageCreator pageCreator = new DefaultPageCreator();
			searchPage = (HtmlPage) pageCreator.createPage(webResponse, webClient.openWindow(new URL(searchPageUrl), "windowName"));
		} finally {
			LOGGER.returnRedisResource();
		}
		
		return searchPage;
	}
	
	private HtmlPage getSearchPageByUrl(WebClient webClient, WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(GsxtFunction.class, webParam.getLogback());
		HtmlPage searchPage = null;
		
		try {
			String searchPageUrl = webParam.getSearchPage();
			if (StringUtils.isEmpty(searchPageUrl)) {
				LOGGER.error("The searchPageUrl is not defined!");
				return null;
			}
			//清除之前的cookie
			clearCookies(webClient, new URL(searchPageUrl));
			
			String searchPageHtml = "";
			WebRequest webRequest = new WebRequest(new URL(searchPageUrl), HttpMethod.GET);
			if ("shanxi".equals(webParam.getParams().get("area").toString())) {
				WebWindow window = webClient.openWindow(new URL(searchPageUrl), "shanxi");
				searchPage = webClient.getPage(window, webRequest);
			} else {
				searchPage = webClient.getPage(webRequest);
			}
			searchPageHtml = searchPage.asXml();
			LOGGER.info("searchPageHtml:"+ searchPageHtml);
			
			if (searchPageHtml.contains("可能访问过于频繁或非正常访问")) {  //在此加各个地区访问过于频繁的异常处理代码 （用“或”关系连接条件）
				webParam.getParams().put("statusCodeDef", StatusCodeDef.FREQUENCY_LIMITED);
				webParam.getParams().put("searchPageHtml", IPUtil.getHostAndIpStr() + searchPageHtml);
				return searchPage;
			}
			
			// 您的访问过于频繁，如有疑问请联系工商行政管理局
			if (!StringUtils.isEmpty(webParam.getParams().get("area")) && "ningxia".equals(webParam.getParams().get("area")) && searchPageHtml.contains("您的访问过于频繁")) {
				webParam.getParams().put("statusCodeDef", StatusCodeDef.FREQUENCY_LIMITED);
				webParam.getParams().put("searchPageHtml", IPUtil.getHostAndIpStr() + searchPageHtml);
				return searchPage;
			}
			
			//imageUrl
			HtmlImage image = searchPage.getFirstByXPath(webParam.getCodeImageId());
			Map<String, String> webParamParams = webParam.getParams();
			
			File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
			parentDirFile.setReadable(true); //
			parentDirFile.setWritable(true); //
			if (!parentDirFile.exists()) {
				parentDirFile.mkdirs();
			}
			String imageName = null;
			if("chongqing".equals(webParam.getParams().get("area").toString())){
				imageName = UUID.randomUUID() + ".png";
			}else{
				imageName = UUID.randomUUID() + ".jpg";
			}
			File codeImageFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + imageName);
			codeImageFile.setReadable(true); //
			codeImageFile.setWritable(true); //
			
			if (searchPageUrl.contains("gsxt.cqgs.gov.cn")) { //重庆
				Page page = webClient.getPage("http://gsxt.cqgs.gov.cn/sc.action?width=130&height=40&fs=23");
				InputStream is = null;
				try {
					is = page.getWebResponse().getContentAsStream();
					FileUtils.copyInputStreamToFile(is, codeImageFile);
					LOGGER.info("----codeImageFile saved!----");
					LOGGER.info("The codeImageFile of GsxtFunction.getSearchPageByUrl is:{}", codeImageFile.getAbsolutePath());
					String imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
					LOGGER.info("The imageUrl is: "+ imageUrl);
					webParam.getParams().put("imageUrl", imageUrl);
					webParam.getParams().put("statusCodeDef", StatusCodeDef.SCCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info("----codeImageFile saved fail!----");
					webParam.getParams().put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					webParam.getParams().put("searchPageHtml", IPUtil.getHostAndIpStr() + "保存图片（copyInputStreamToFile）方法异常！");
				}
			} else if (searchPageUrl.contains("fjaic.gov.cn")) { //福建
				Page page = webClient.getPage("http://wsgs.fjaic.gov.cn/creditpub/captcha?preset=str-01,math-01&ra="+Math.random());
				InputStream is = null;
				try {
					is = page.getWebResponse().getContentAsStream();
					FileUtils.copyInputStreamToFile(is, codeImageFile);
					LOGGER.info("----codeImageFile saved!----");
					LOGGER.info("The codeImageFile of GsxtFunction.getSearchPageByUrl is:{}", codeImageFile.getAbsolutePath());
					String imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
					LOGGER.info("The imageUrl is: "+ imageUrl);
					webParam.getParams().put("imageUrl", imageUrl);
					webParam.getParams().put("statusCodeDef", StatusCodeDef.SCCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info("----codeImageFile saved fail!----");
					webParam.getParams().put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					webParam.getParams().put("searchPageHtml", IPUtil.getHostAndIpStr() + "保存图片（copyInputStreamToFile）方法异常！");
				}
			} else if (searchPageUrl.contains("gsxt.hnaic.gov.cn")) { //湖南
				Page page = webClient.getPage("http://gsxt.hnaic.gov.cn/notice/captcha?preset=&ra="+Math.random());
				InputStream is = null;
				try {
					is = page.getWebResponse().getContentAsStream();
					FileUtils.copyInputStreamToFile(is, codeImageFile);
					LOGGER.info("----codeImageFile saved!----");
					LOGGER.info("The codeImageFile of GsxtFunction.getSearchPageByUrl is:{}", codeImageFile.getAbsolutePath());
					String imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
					LOGGER.info("The imageUrl is: "+ imageUrl);
					webParam.getParams().put("imageUrl", imageUrl);
					webParam.getParams().put("statusCodeDef", StatusCodeDef.SCCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info("----codeImageFile saved fail!----");
					webParam.getParams().put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					webParam.getParams().put("searchPageHtml", IPUtil.getHostAndIpStr() + "保存图片（copyInputStreamToFile）方法异常！");
				}	
			} else if (searchPageUrl.contains("218.95.241.36")) { //青海
				Page page = webClient.getPage("http://218.95.241.36/validateCode.jspx?type=0&id=0.9730435636142166");
				InputStream is = null;
				try {
					is = page.getWebResponse().getContentAsStream();
					FileUtils.copyInputStreamToFile(is, codeImageFile);
					LOGGER.info("----codeImageFile saved!----");
					LOGGER.info("The codeImageFile of GsxtFunction.getSearchPageByUrl is:{}", codeImageFile.getAbsolutePath());
					String imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
					LOGGER.info("The imageUrl is: "+ imageUrl);
					webParam.getParams().put("imageUrl", imageUrl);
					webParam.getParams().put("statusCodeDef", StatusCodeDef.SCCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info("----codeImageFile saved fail!----");
					webParam.getParams().put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					webParam.getParams().put("searchPageHtml", IPUtil.getHostAndIpStr() + "保存图片（copyInputStreamToFile）方法异常！");
				}	
			}else {
				if (image==null && webParamParams!=null && !StringUtils.isEmpty(webParamParams.get("imagecodeIframeSrc"))) { //验证码图片标签为空 说明验证码图片和搜索页的关系为iframe-parent关系
					HtmlPage imagecodeIframePage = webClient.getPage(webParamParams.get("imagecodeIframeSrc"));
					image = imagecodeIframePage.getFirstByXPath(webParam.getCodeImageId());
				} else if (image!=null && StringUtils.isEmpty(image.getAttribute("src"))) { //验证码图片标签不为空，而其属性src为空，验证码特殊处理
					image.click();
					
					HtmlElement hyzBtn1 = searchPage.getFirstByXPath("//div[@id='woaicss_con1']/ul/li[2]/div[2]/a");//宁夏换一张按钮
					HtmlElement hyzBtn2 = searchPage.getFirstByXPath("//div[@id='codeWindow']/div/ul/li[2]/div[2]/a");//贵州换一张按钮
					if (StringUtils.isEmpty(image.getAttribute("src")) && hyzBtn1!=null) { //宁夏
						hyzBtn1.click();
					} else if (StringUtils.isEmpty(image.getAttribute("src")) && hyzBtn2!=null) {
						hyzBtn2.click();
					}
				} else if (searchPageUrl.contains("218.57.139.24")) { //山东换一张
					HtmlElement zdmBtn = searchPage.getFirstByXPath("//a[@onclick='zdm()']");
					if (zdmBtn!=null) {
						zdmBtn.click();
					}
				}
				
				if (image==null) {
					webParam.getParams().put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					webParam.getParams().put("searchPageHtml", IPUtil.getHostAndIpStr() + searchPageHtml);
					webParam.getParams().put("isImageNull", "true");
					return searchPage;
				}
				
				try {
					image.saveAs(codeImageFile);
					LOGGER.info("----codeImageFile saved!----");
					LOGGER.info("The codeImageFile of GsxtFunction.getSearchPageByUrl is:"+ codeImageFile.getAbsolutePath());
					String imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
					LOGGER.info("The imageUrl is: "+ imageUrl);
					webParam.getParams().put("imageUrl", imageUrl);
					webParam.getParams().put("statusCodeDef", StatusCodeDef.SCCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info("----codeImageFile saveAs fail!----");
					webParam.getParams().put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					webParam.getParams().put("searchPageHtml", IPUtil.getHostAndIpStr() + "保存图片（saveAs）方法异常！");
				}
			}
		} finally {
			LOGGER.returnRedisResource();
		}
		
		return searchPage;
	}
	
	private HtmlPage getFirstInfoPage(WebClient webClient, WebParam webParam, HtmlPage searchPage) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(GsxtFunction.class, webParam.getLogback());
		HtmlPage firstInfoPage = null;
		try {
			//请求参数
			Map<String, String> params = webParam.getParams();
			String keyword = params.get("keyword").trim(); //关键字
			String keywordXpath = params.get("keywordXpath");
			HtmlInput inputKeyword = (HtmlInput)searchPage.getFirstByXPath(keywordXpath); //querySelector("#keyword");
			inputKeyword.reset();
			inputKeyword.setAttribute("value", keyword);
			
			String imagecodeXpath = params.get("imagecodeXpath");
			String imagecode = params.get("imagecode"); //图片验证码
			if (StringUtils.isEmpty(imagecode)) {
				imagecode = "0000";
			}
			HtmlInput inputImagecode = (HtmlInput)searchPage.getFirstByXPath(imagecodeXpath); //querySelector("#checkcodeAlt");
			
			if (inputImagecode!=null && imagecode!=null) {
				inputImagecode.reset();
				inputImagecode.setValueAttribute(imagecode);
				
				String loginBtnXpath = params.get("loginBtnXpath");
				HtmlElement loginButton = (HtmlElement) searchPage.getFirstByXPath(loginBtnXpath);
				
				if ("hubei".equals(params.get("area").toLowerCase().trim())) { //湖北验证码多加一步操作
					/*searchPage.executeJavaScript("yzm='" + imagecode + "';");
					inputImagecode.setDefaultValue(imagecode);
					firstInfoPage = loginButton.click();*/
					
					WebRequest webRequest = new WebRequest(new URL("http://xyjg.egs.gov.cn/ECPS_HB/searchList.jspx?checkNo="+URLEncoder.encode(imagecode)+"&entName="+URLEncoder.encode(keyword)), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("liaoning".equals(params.get("area").toLowerCase().trim())) { //辽宁 提交前特殊操作
					searchPage.executeJavaScript("path='http://gsxt.lngs.gov.cn/saicpub';");
					firstInfoPage = loginButton.click();
				} else if ("ningxia".equals(params.get("area").toLowerCase().trim())) { //宁夏特殊处理
					loginButton.removeEventHandler("onclick");
					loginButton.setEventHandler("onclick", "$('#queryXyxx').attr('action','http://gsxt.ngsh.gov.cn/ECPS/qyxxgsAction_queryXyxx.action'); $('#password').attr('value', '"+imagecode+"'); $('#queryXyxx').submit();");
					firstInfoPage = loginButton.click();
				} else if ("gansu".equals(params.get("area").toLowerCase().trim())) {
					Thread.sleep(2500);//
					loginButton.removeEventHandler("onclick");
					loginButton.setEventHandler("onclick", "$('#infoForm').attr('action','/gsxygs/pub!list.do').submit();");
					firstInfoPage = loginButton.click();
				} else if ("beijing".equals(params.get("area").toLowerCase().trim())) {
					DomElement creditTicketEle = searchPage.getElementById("credit_ticket");
					String credit_ticket = creditTicketEle.getAttribute("value");
					DomElement currentTimeMillisEle = searchPage.getElementById("currentTimeMillis");
					String currentTimeMillis = currentTimeMillisEle.getAttribute("value");
					WebRequest webRequest = new WebRequest(new URL("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml?keyword="+URLEncoder.encode(keyword)+"&checkcode="+URLEncoder.encode(imagecode)
							+"&credit_ticket="+credit_ticket+"&currentTimeMillis="+currentTimeMillis), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("guizhou".equals(params.get("area").toLowerCase().trim())) {
					WebRequest webRequest = new WebRequest(new URL("http://gsxt.gzgs.gov.cn/search!searchSczt.shtml?q="+URLEncoder.encode(keyword)+"&validCode="+URLEncoder.encode(imagecode)), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("jiangxi".equals(params.get("area").toLowerCase().trim())) {
					//WebRequest webRequest = new WebRequest(new URL("http://gsxt.jxaic.gov.cn/ECPS/home/home_homeSearch.pt?yzm="+URLEncoder.encode(imagecode)+"&search="+URLEncoder.encode(keyword)), HttpMethod.POST);
					WebRequest webRequest = new WebRequest(new URL("http://gsxt.jxaic.gov.cn/ECPS/home/home_homeSearch.pt?searchPage=yes&searchtext="+URLEncoder.encode(keyword)+"&pageFlag=1&limit=15&page=1&yzm="+URLEncoder.encode(imagecode)), HttpMethod.POST);	
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("hainan".equals(params.get("area").toLowerCase().trim())) {
					WebRequest webRequest = new WebRequest(new URL("http://aic.hainan.gov.cn:1888/searchList.jspx?entName="+URLEncoder.encode(keyword)+"&checkNo="+URLEncoder.encode(imagecode)), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("zhejiang".equals(params.get("area").toLowerCase().trim())) {
					//WebRequest webRequest = new WebRequest(new URL("http://gsxt.zjaic.gov.cn/search/doGetAppSearchResult.do?clickType=1&name="+URLEncoder.encode(keyword)+"&verifyCode="+URLEncoder.encode(imagecode)), HttpMethod.POST);
					//webRequest.setCharset("utf-8");
					//firstInfoPage = webClient.getPage(webRequest);
					
					String url = "http://gsxt.zjaic.gov.cn/search/doValidatorVerifyCode.do?verifyCode="+URLEncoder.encode(imagecode)+"&name="+URLEncoder.encode(keyword);
					WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.GET);
					Page verifyPage = webClient.getPage(webRequest);
					LOGGER.info("验证返回结果：" + verifyPage.getWebResponse().getContentAsString());
					
					String url2 = "http://gsxt.zjaic.gov.cn/search/doGetAppSearchResult.do?clickType=1&verifyCode="+URLEncoder.encode(imagecode)+"&name="+URLEncoder.encode(keyword);
					WebRequest webRequest2 = new WebRequest(new URL(url2), HttpMethod.GET);
					webRequest2.setAdditionalHeader("Upgrade-Insecure-Requests", "1");
					webRequest2.setAdditionalHeader("Origin", "http://gsxt.zjaic.gov.cn");
					webRequest2.setAdditionalHeader("Referer", "http://gsxt.zjaic.gov.cn/search/doEnGeneralQueryPage.do");
					firstInfoPage = webClient.getPage(webRequest2);
					webClient.removeRequestHeader("Upgrade-Insecure-Requests");
					webClient.removeRequestHeader("Origin");
					webClient.removeRequestHeader("Referer");
				} else if ("chongqing".equals(params.get("area").toLowerCase().trim())) {
					WebRequest webRequest = new WebRequest(new URL("http://gsxt.cqgs.gov.cn/search.action?key="+URLEncoder.encode(keyword)+"&code="+URLEncoder.encode(imagecode)), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("guangdong".equals(params.get("area").toLowerCase().trim())) {
					String requestUrl1 = "http://gsxt.gdgs.gov.cn/aiccips/CheckEntContext/checkCode.html?textfield="+URLEncoder.encode(keyword)+"&code="+URLEncoder.encode(imagecode);
					WebRequest webRequest1 = new WebRequest(new URL(requestUrl1), HttpMethod.POST);
					webRequest1.setCharset("utf-8");
					Page page1 = webClient.getPage(webRequest1);
					String page1JsonStr = page1.getWebResponse().getContentAsString();
					LOGGER.info("page1JsonStr:" + page1JsonStr);
					
					JsonElement textfieldJEle = null;
					try {
						textfieldJEle = new JsonParser().parse(page1JsonStr).getAsJsonObject().get("textfield");
					} catch (IllegalStateException e) {
						textfieldJEle = null;
					}
					if (textfieldJEle!=null) {
						String textfield = textfieldJEle.getAsString();
						WebRequest webRequest2 = new WebRequest(new URL("http://gsxt.gdgs.gov.cn/aiccips/CheckEntContext/showInfo.html?textfield="+textfield+"&code="+URLEncoder.encode(imagecode)), HttpMethod.POST);
						webRequest2.setCharset("utf-8");
						firstInfoPage = webClient.getPage(webRequest2);
					} else {
						//验证码错误  或  cookie超时 或...
						LOGGER.info("=========================验证码错误  或  cookie超时================================= ");
						LOGGER.info("checkCode response: " + page1JsonStr);
						LOGGER.info("imagecode is: " + imagecode);
						LOGGER.info("=========================textfieldJEle is null=================================");
						LOGGER.info("=========================firstInfoPage is null=================================");
						return new HtmlPage(new URL(requestUrl1), new WebResponse(new WebResponseData("验证没有通过，可能是验证码错误的原因".getBytes(), 500, "验证没有通过，可能是验证码错误的原因", new ArrayList<NameValuePair>()), new WebRequest(new URL(requestUrl1)), System.currentTimeMillis()), webClient.getCurrentWindow());
					}
				} else if ("shaanxi".equals(params.get("area").toLowerCase().trim())) {
					String url = "http://xygs.snaic.gov.cn/ztxy.do?method=list&djjg=&random="+new Date().getTime()+"&yzm="+URLEncoder.encode(imagecode, "GBK")+"&maent.entname="+URLEncoder.encode(keyword, "GBK");
					WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("shanxi".equals(params.get("area").toLowerCase().trim())) {
					String url = "http://gsxt.fc12319.com/searchList.jspx?checkNo="+URLEncoder.encode(imagecode)+"&entName="+URLEncoder.encode(keyword);
					WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("qinghai".equals(params.get("area").toLowerCase().trim())) {
					String url = "http://218.95.241.36/searchList.jspx?checkNo="+URLEncoder.encode(imagecode)+"&entName="+URLEncoder.encode(keyword);
					WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("xizang".equals(params.get("area").toLowerCase().trim())) {
					String url = "http://gsxt.xzaic.gov.cn/searchList.jspx?checkNo="+URLEncoder.encode(imagecode)+"&entName="+URLEncoder.encode(keyword);
					WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("neimenggu".equals(params.get("area").toLowerCase().trim())) {
					String url1 = "http://www.nmgs.gov.cn:7001/aiccips/CheckEntContext/checkCode.html?textfield="+URLEncoder.encode(keyword)+"&code="+URLEncoder.encode(imagecode);
					WebRequest webRequest1 = new WebRequest(new URL(url1), HttpMethod.POST);
					webRequest1.setCharset("UTF-8");
					Page page1 = webClient.getPage(webRequest1);
					String page1JsonStr = page1.getWebResponse().getContentAsString();
					JsonElement textfieldJEle = new JsonParser().parse(page1JsonStr).getAsJsonObject().get("textfield");
					if (textfieldJEle!=null) {
						String textfield = textfieldJEle.getAsString();
						String url2 = "http://www.nmgs.gov.cn:7001/aiccips/CheckEntContext/showInfo.html?textfield="+textfield+"&code="+URLEncoder.encode(imagecode);
						WebRequest webRequest2 = new WebRequest(new URL(url2), HttpMethod.POST);
						webRequest2.setCharset("utf-8");
						firstInfoPage = webClient.getPage(webRequest2);
					}
				} else {
					firstInfoPage = loginButton.click();
					//firstInfoPage.executeJavaScript("loadres();");
					if ("shandong".equals(params.get("area").toLowerCase().trim())) { //山东
//						Thread.sleep(2000L);
						String enckeyword = null;
						try {
							enckeyword = new AbstractParser() {}.getSubStringByRegex(firstInfoPage.asXml(), "enckeyword='\\w*';").get(0);
							enckeyword = enckeyword.replaceAll("enckeyword='", "").replaceAll("';", "");
						} catch (Exception e) {
							String listPageUrl = "http://218.57.139.24/pub/indsearch";
							LOGGER.info("没有获取到enckeyword！");
							return new HtmlPage(new URL(listPageUrl), new WebResponse(new WebResponseData("没有获取到enckeyword！".getBytes(), 500, "没有获取到enckeyword！", new ArrayList<NameValuePair>()), new WebRequest(new URL(listPageUrl)), System.currentTimeMillis()), webClient.getCurrentWindow());
						}
						String url = "http://218.57.139.24/pub/search?param=" + enckeyword;
						WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
						webRequest.setCharset("utf-8");
						webRequest.setAdditionalHeader("Host", "218.57.139.24");
						webRequest.setAdditionalHeader("Origin", "http://218.57.139.24");
						webRequest.setAdditionalHeader("Referer", "http://218.57.139.24/pub/indsearch");
						webClient.getPage(webRequest);
					}
				}
				
			} else { //验证码图片和搜索页是iframe和prarent的关系
				if ("hebei".equals(params.get("area").toLowerCase().trim())) { //河北
	//				HtmlHiddenInput inputCaptcha = (HtmlHiddenInput) searchPage.getElementById("captcha");
	//				inputCaptcha.reset();
	//				inputCaptcha.setValueAttribute(imagecode);
	//				HtmlForm formFormInfo = (HtmlForm) searchPage.getElementById("formInfo");
	//				formFormInfo.setActionAttribute("http://www.hebscztxyxx.gov.cn/notice/search/ent_info_list");
	//				formFormInfo.setEventHandler("onclick", "$('#formInfo').submit();");
	//				firstInfoPage = formFormInfo.click();
					HtmlElement sessionTokenInput = searchPage
							.getFirstByXPath("//input[@name='session.token']");
					String sessionToken = sessionTokenInput.getAttribute("value");
					String url = "http://www.hebscztxyxx.gov.cn/notice/search/ent_info_list?searchType=1&captcha="
							+ URLEncoder.encode(imagecode)
							+ "&condition.keyword="
							+ URLEncoder.encode(keyword)
							+ "&session.token="
							+ sessionToken;
					firstInfoPage = webClient.getPage(new WebRequest(new URL(url),
							HttpMethod.POST));
				} else if ("shanghai".equals(params.get("area").toLowerCase().trim())) { //上海
					/*HtmlHiddenInput inputCaptcha = (HtmlHiddenInput) searchPage.getElementById("captcha");
					inputCaptcha.reset();
					inputCaptcha.setValueAttribute(imagecode);
					HtmlForm formFormInfo = (HtmlForm) searchPage.getElementById("formInfo");
					formFormInfo.setActionAttribute("https://www.sgs.gov.cn/notice/search/ent_info_list");
					formFormInfo.setEventHandler("onclick", "$('#formInfo').submit();");
					firstInfoPage = formFormInfo.click();*/
					
					HtmlHiddenInput sessionTokenInput = (HtmlHiddenInput) searchPage.getFirstByXPath("//input[@type='hidden'][@name='session.token']");
					String sessionToken = sessionTokenInput.getValueAttribute();
					WebRequest webRequest = new WebRequest(new URL("https://www.sgs.gov.cn/notice/search/ent_info_list?searchType=1&captcha=" 
					+ URLEncoder.encode(imagecode) + "&session.token=" + sessionToken + "&condition.keyword=" + URLEncoder.encode(keyword)), HttpMethod.POST);
					webRequest.setCharset("utf-8");
					firstInfoPage = webClient.getPage(webRequest);
				} else if ("fujian".equals(params.get("area").toLowerCase().trim())) { //福建
	//				HtmlHiddenInput inputCaptcha = (HtmlHiddenInput) searchPage.getElementById("captcha");
	//				inputCaptcha.reset();
	//				inputCaptcha.setValueAttribute(imagecode);
	//				HtmlForm formFormInfo = (HtmlForm) searchPage.getElementById("formInfo");
	//				formFormInfo.setActionAttribute("http://wsgs.fjaic.gov.cn/creditpub/search/ent_info_list");
	//				formFormInfo.setEventHandler("onclick", "$('#formInfo').submit();");
	//				firstInfoPage = formFormInfo.click();
					HtmlElement sessionTokenInput = searchPage.getFirstByXPath("//input[@name='session.token']");
					String sessionToken = sessionTokenInput.getAttribute("value");
					String url = "http://wsgs.fjaic.gov.cn/creditpub/search/ent_info_list?searchType=1&captcha="+URLEncoder.encode(imagecode)+"&condition.keyword="+URLEncoder.encode(keyword)+"&session.token="+sessionToken;
					firstInfoPage = webClient.getPage(new WebRequest(new URL(url), HttpMethod.POST));
	//				String asXml = firstInfoPage.asXml();
	//				System.out.println(asXml);
				} else if ("hunan".equals(params.get("area").toLowerCase().trim())) { //湖南
	//				HtmlHiddenInput inputCaptcha = (HtmlHiddenInput) searchPage.getElementById("captcha");
	//				inputCaptcha.reset();
	//				inputCaptcha.setValueAttribute(imagecode);
	//				HtmlForm formFormInfo = (HtmlForm) searchPage.getElementById("formInfo");
	//				formFormInfo.setActionAttribute("http://gsxt.hnaic.gov.cn/notice/search/ent_info_list");
	//				formFormInfo.setEventHandler("onclick", "$('#formInfo').submit();");
	//				firstInfoPage = formFormInfo.click();
					HtmlElement sessionTokenEle = searchPage.getFirstByXPath("//input[@name='session.token']");
					String sessionToken = sessionTokenEle.getAttribute("value");
					String url = "http://gsxt.hnaic.gov.cn/notice/search/ent_info_list?searchType=1&captcha="+URLEncoder.encode(imagecode)+"&session.token="+sessionToken+"&condition.keyword="+URLEncoder.encode(keyword);
					WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
					webRequest.setCharset("UTF-8");
					firstInfoPage = webClient.getPage(webRequest);
	
				} else if ("yunnan".equals(params.get("area").toLowerCase().trim())) { //云南
					/*HtmlHiddenInput inputCaptcha = (HtmlHiddenInput) searchPage.getElementById("captcha");
					inputCaptcha.reset();
					inputCaptcha.setValueAttribute(imagecode);
					HtmlForm formFormInfo = (HtmlForm) searchPage.getElementById("formInfo");
					formFormInfo.setActionAttribute("http://gsxt.ynaic.gov.cn/notice/search/popup_captcha");
					formFormInfo.setEventHandler("onclick", "$('#formInfo').attr('action', 'http://gsxt.ynaic.gov.cn/notice/search/ent_info_list'); $('#formInfo').submit();");
					firstInfoPage = formFormInfo.click();*/
					
					HtmlElement sessionTokenEle = searchPage.getFirstByXPath("//input[@name='session.token']");
					String sessionToken = sessionTokenEle.getAttribute("value");
					String url = "http://gsxt.ynaic.gov.cn/notice/search/ent_info_list?searchType=1&captcha="+URLEncoder.encode(imagecode)+"&session.token="+sessionToken+"&condition.keyword="+URLEncoder.encode(keyword);
					WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
					webRequest.setCharset("UTF-8");
					firstInfoPage = webClient.getPage(webRequest);
				}
			}
			
			LOGGER.info("The firstInfoPage is:"+ firstInfoPage.asXml());
			LOGGER.info("The firstInfoPage Url is:"+ firstInfoPage.getUrl());
		} finally {
			LOGGER.returnRedisResource();
		}
		
		return firstInfoPage;
	}
	
	
	public String search(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(GsxtFunction.class, webParam.getLogback());
		Map<String, Object> resultHtmlMap = null;
		try {
			LOGGER.info("==================GsxtFunction.search start!======================");
			WebClient webClient = WebCrawler.getInstance().getWebClient();
			
			HtmlPage searchPage = getSearchPageBySerialize(webClient, webParam);
			HtmlPage firstInfoPage = getFirstInfoPage(webClient, webParam, searchPage);
			
			Map<String, String> params = webParam.getParams();
			String area = params.get("area"); //地区
			String keyword = params.get("keyword").trim(); //关键字
			resultHtmlMap = getHtmlInfoMap(area, firstInfoPage, keyword, LOGGER);
			resultHtmlMap.put("ip", ip);
			resultHtmlMap.put("hostName", hostName);
			
			String alertMsg = WebCrawler.getAlertMsg();
			if (firstInfoPage.asText().contains("非法字符") || (alertMsg!=null&&alertMsg.contains("非法字符"))) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.ILLEGAL_CHAR);
				resultHtmlMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + firstInfoPage.asXml());
			}
			if (firstInfoPage.asText().contains("可能访问过于频繁或非正常访问")) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
				resultHtmlMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + firstInfoPage.asXml());
			}
		} finally {
			LOGGER.returnRedisResource();
		}
		
		return new GsonBuilder().setPrettyPrinting().create().toJson(resultHtmlMap);
	}
	
	
	public String searchWithOCR(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(GsxtFunction.class, webParam.getLogback());
		Map<String, Object> resultHtmlMap = null;
		try {
			LOGGER.info("==================GsxtFunction.searchWithOCR start!======================");
			WebClient webClient = WebCrawler.getInstance().getWebClient();
			
			Map<String, String> params = webParam.getParams();
			String area = params.get("area"); //地区
			String keyword = params.get("keyword").trim(); //关键字
			
			if ("jilin".equals(webParam.getParams().get("area"))) { //吉林使用casperjs获取列表页数据
				resultHtmlMap = getHtmlInfoMap(area, null, keyword, LOGGER);
				if (resultHtmlMap==null) {
					resultHtmlMap = new LinkedHashMap<String, Object>();
				}
				resultHtmlMap.put("ip", ip);
				resultHtmlMap.put("hostName", hostName);
				return new GsonBuilder().setPrettyPrinting().create().toJson(resultHtmlMap);
			}
			
			HtmlPage searchPage = null;
			try {
				webClient.getOptions().setTimeout(120000); //得到搜索页超时时间均设置为120s
				searchPage = getSearchPageByUrl(webClient, webParam);
			} finally {
				webClient.getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
			}
			String statusCodeDefOfGetSearchPageByUrl = webParam.getParams().get("statusCodeDef");
			if (StatusCodeDef.SCCCESS.equals(statusCodeDefOfGetSearchPageByUrl)) {
				String imageUrl = webParam.getParams().get("imageUrl");
				
				String verifycode = null;
				if (!StringUtils.isEmpty(area)) {
					verifycode = OCRConnectUtils.getVerifycode("/gsxt/"+area+"/getVerifycode", imageUrl); //OCR
//					Scanner scanner = new Scanner(System.in);
//					verifycode = scanner.next();
				} else {
					verifycode = OCRConnectUtils.getVerifycode("/cjy/getVerifycode", imageUrl, DEFAULT_CHAOJIYING_CODETYPE); //默认使用6003识别方式
				}
				
				webParam.getParams().put("imagecode", verifycode);
				HtmlPage firstInfoPage = null;
				try {
					webClient.getOptions().setTimeout(120000); //得到列表页超时时间均设置为120s
					firstInfoPage = getFirstInfoPage(webClient, webParam, searchPage);
				} finally {
					webClient.getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				}
				
				//得到目标信息
				resultHtmlMap = getHtmlInfoMap(area, firstInfoPage, keyword, LOGGER);
				if (resultHtmlMap==null) {
					resultHtmlMap = new LinkedHashMap<String, Object>();
				}
				resultHtmlMap.put("ip", ip);
				resultHtmlMap.put("hostName", hostName);
				
				String alertMsg = WebCrawler.getAlertMsg();
				if (firstInfoPage.asText().contains("非法字符") || (alertMsg!=null&&alertMsg.contains("非法字符"))) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.ILLEGAL_CHAR);
					resultHtmlMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + firstInfoPage.asXml());
				}
				if (firstInfoPage.asText().contains("可能访问过于频繁或非正常访问")) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.FREQUENCY_LIMITED);
					resultHtmlMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + firstInfoPage.asXml());
					if ("beijing".equals(webParam.getParams().get("area"))) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.ILLEGAL_CHAR);
					}
				}
				if (firstInfoPage.getWebResponse().getContentAsString("utf-8").contains("验证没有通过，可能是验证码错误的原因")) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
					resultHtmlMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + " 自定义页面：" + firstInfoPage.asText());
				}
				if (firstInfoPage.asText().contains("没有获取到enckeyword！")) {
					resultHtmlMap.put("statusCodeDef", "100020");
					resultHtmlMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + " 自定义页面：" + firstInfoPage.asText());
				}
				resultHtmlMap.put("imageUrl", imageUrl);
				resultHtmlMap.put("imagecode", verifycode);
			} else {
				resultHtmlMap = new LinkedHashMap<String, Object>();
				resultHtmlMap.put("statusCodeDef", statusCodeDefOfGetSearchPageByUrl);
				
				resultHtmlMap.put("ip", ip);
				resultHtmlMap.put("hostName", hostName);
				
				String searchPageHtml = webParam.getParams().get("searchPageHtml");
				resultHtmlMap.put("searchPageHtml", searchPageHtml);
				
				String isImageNull = webParam.getParams().get("isImageNull");
				resultHtmlMap.put("isImageNull", isImageNull);
			}
		} finally {
			LOGGER.returnRedisResource();
		}
		return new GsonBuilder().setPrettyPrinting().create().toJson(resultHtmlMap);
	}
	
	
	/**
	 * 请求若干次，得到目标页信息
	 * @param area 			地区（地区拼音）
	 * @param firstInfoPage 点击列表页搜索按钮得到的页面
	 * @param keyword		查询的关键字（精确匹配，不然得不到数据！！！）
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getHtmlInfoMap(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		if (StringUtils.isEmpty(area)) {
			throw new RuntimeException("参数area不能为空！");
		}
		Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
		
		if ("beijing".equals(area)) { //北京（beijing）
			resultHtmlMap = getHtmlInfoMapOfBeijing(area, firstInfoPage, keyword, LOGGER);
		} else if ("tianjin".equals(area)) { //天津（tianjin）
			resultHtmlMap = getHtmlInfoMapOfTianjin(area, firstInfoPage, keyword, LOGGER);
		}else if ("guangxi".equals(area)) { //广西（guangxi）
			resultHtmlMap = getHtmlInfoMapOfGuangxi(area, firstInfoPage, keyword, LOGGER);
		}else if ("henan".equals(area)) { //河南（henan）
			resultHtmlMap = getHtmlInfoMapOfHenan(area, firstInfoPage, keyword, LOGGER);
		}else if ("neimenggu".equals(area)) { //内蒙古（neimenggu）
			resultHtmlMap = getHtmlInfoMapOfNeimenggu(area, firstInfoPage, keyword, LOGGER);
		}else if ("hubei".equals(area)) { //湖北（hubei）
			resultHtmlMap = getHtmlInfoMapOfHubei(area, firstInfoPage, keyword, LOGGER);
		} else if ("yunnan".equals(area)) { //云南（yunnan）
			resultHtmlMap = getHtmlInfoMapOfYunnan(area, firstInfoPage, keyword, LOGGER);
		}else if ("shanxi".equals(area)) { //山西（shanxi）
			resultHtmlMap = getHtmlInfoMapOfShanxi(area, firstInfoPage, keyword, LOGGER);
		} else if ("shandong".equals(area)) { //山东 （shangdong）
			resultHtmlMap = getHtmlInfoMapOfShandong(area, firstInfoPage, keyword, LOGGER);
		} else if ("jiangsu".equals(area)){ //江苏（jiangsu）
			resultHtmlMap = getHtmlInfoMapOfJiangsu(area, firstInfoPage, keyword, LOGGER);
		}else if ("anhui".equals(area)){ //安徽（anhui）
			resultHtmlMap = getHtmlInfoMapOfAnhui(area, firstInfoPage, keyword, LOGGER);
		}else if ("heilongjiang".equals(area)){ //黑龙江（heilongjiang）
			resultHtmlMap = getHtmlInfoMapOfHeilongjiang(area, firstInfoPage, keyword, LOGGER);
		}else if ("ningxia".equals(area)){ //宁夏（ningxia）
			resultHtmlMap = getHtmlInfoMapOfNingxia(area, firstInfoPage, keyword, LOGGER);
		}else if ("liaoning".equals(area)){ //辽宁（liaoning）
			resultHtmlMap = getHtmlInfoMapOfLiaoning(area, firstInfoPage, keyword, LOGGER);
		}else if ("hebei".equals(area)){ //河北（hebei）
			resultHtmlMap = getHtmlInfoMapOfHebei(area, firstInfoPage, keyword, LOGGER);
		}else if ("gansu".equals(area)){ //甘肃（gansu）
			resultHtmlMap = getHtmlInfoMapOfGansu(area, firstInfoPage, keyword, LOGGER);
		}else if("sichuan".equals(area)){ //四川（sichuan）
			resultHtmlMap = getHtmlInfoMapOfSichuan(area, firstInfoPage, keyword, LOGGER);
		}else if("qinghai".equals(area)){ //青海（qinghai）
			resultHtmlMap = getHtmlInfoMapOfQinghai(area, firstInfoPage, keyword, LOGGER);
		}else if("xizang".equals(area)){ //西藏（xizang）
			resultHtmlMap = getHtmlInfoMapOfXizang(area, firstInfoPage, keyword, LOGGER);
		}else if("shaanxi".equals(area)){ //陕西（shaanxi）
			resultHtmlMap = getHtmlInfoMapOfShaanxi(area, firstInfoPage, keyword, LOGGER);
		}else if("shanghai".equals(area)){ //上海（shanghai）
			resultHtmlMap = getHtmlInfoMapOfShanghai(area, firstInfoPage, keyword, LOGGER);
		}else if("xinjiang".equals(area)){ //新疆（xingjiang）
			resultHtmlMap = getHtmlInfoMapOfXinjiang(area, firstInfoPage, keyword, LOGGER);
		}else if("fujian".equals(area)){ //福建（fujian）
			resultHtmlMap = getHtmlInfoMapOfFujian(area, firstInfoPage, keyword, LOGGER);
		}else if("hunan".equals(area)){ //湖南（hunan）
			resultHtmlMap =getHtmlInfoMapOfHunan(area, firstInfoPage, keyword, LOGGER);
		}else if("guizhou".equals(area)){ //贵州（guizhou）
			resultHtmlMap =getHtmlInfoMapOfGuizhou(area, firstInfoPage, keyword, LOGGER);
		}else if("hainan".equals(area)){ //海南（hainan）
			resultHtmlMap =getHtmlInfoMapOfHainan(area, firstInfoPage, keyword, LOGGER);
		}else if("jiangxi".equals(area)){ //江西（jiangxi）
			resultHtmlMap =getHtmlInfoMapOfJiangxi(area, firstInfoPage, keyword, LOGGER);
		} else if ("jilin".equals(area)) { //吉林（jilin）
			resultHtmlMap =getHtmlInfoMapOfJilin(area, keyword, LOGGER);
		}else if("guangdong".equals(area)){ //广东（guangdong）
			resultHtmlMap =getHtmlInfoMapOfGuangdong(area, firstInfoPage, keyword, LOGGER);
		}else if("zhejiang".equals(area)){ //浙江（zhejiang）
			resultHtmlMap =getHtmlInfoMapOfZhejiang(area, firstInfoPage, keyword, LOGGER);
		}else if("chongqing".equals(area)){ //重庆（chongqing）
			resultHtmlMap =getHtmlInfoMapOfChongqing(area, firstInfoPage, keyword, LOGGER);
		}
		return resultHtmlMap;
	}

	private Map<String, Object> getHtmlInfoMapOfChongqing(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
//		final String HOST_OF_CHONGQING = "http://gsxt.cqgs.gov.cn/";

		HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='item']"));
		if (divByXPath == null) {//TODO ？？？？？
			DomElement checkcode = firstInfoPage.getElementById("checkcode");
			resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
		} else {
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@id='tip']"));
			if(null != firstByXPath){
				String textContent = firstByXPath.getTextContent();
				if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				}else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				}
			}else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}

		}
		//

		@SuppressWarnings("unchecked")
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='item']/a");
		LOGGER.info(anchors.toString());
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();

				if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
					matchFlag = true;
					//公共参数

					//点击列表页目标条目，获取 工商公示信息->登记信息->基本信息
					HtmlPage gsgsxx = anchor.click();
					DomElement djxx_html = gsgsxx.getElementsByTagName("html").get(0);
					String ngInit = djxx_html.getAttribute("ng-init");

					String id = "";
					String name = "";
					String entId = "";
					String type = "";
					String[] ngInits = ngInit.split(";");
					for (String item : ngInits) {
						item = item.trim();
						if (item.startsWith("id")) {
							id = item.split("=")[1].replaceAll("'", "");
							continue;
						}

						if (item.startsWith("name")) {
							name = item.trim().split("=")[1].replaceAll("'", "");
							continue;
						}

						if (item.startsWith("type")) {
							type = item.trim().split("=")[1].replaceAll("'", "");
							continue;
						}

						if (item.startsWith("entId")) {
							entId = item.split("=")[1].replaceAll("'", "");
							continue;
						}

					}

//					System.out.println(id + "   " + "   " + name + "   " + entId);

					WebClient webClient = firstInfoPage.getWebClient();

					String gsgsxx_url = "http://gsxt.cqgs.gov.cn/search_getEnt.action?entId=" + entId + "&id=" + id + "&type=" + type;
					HtmlPage gsgsxxData = webClient.getPage(gsgsxx_url);
					resultHtmlMap.put("saicData", gsgsxxData.asText().substring(6));

					String yearReport_url = "http://gsxt.cqgs.gov.cn/search_getYearReport.action?id=" + id + "&type=1";
					HtmlPage yearReportJson = webClient.getPage(yearReport_url);
					resultHtmlMap.put("yearReport", yearReportJson.asText().substring(6));

					Gson gson = new Gson();
					YearReport yearReport = gson.fromJson(resultHtmlMap.get("yearReport").toString(), YearReport.class);
					List<History> histories = yearReport.getHistory();
					for(int i = 0 ; i < histories.size() ; i++){
						History history = histories.get(i);
						String annulReport_url = "http://gsxt.cqgs.gov.cn/search_getYearReportDetail.action?id=" +id+ "&type=" + type + "&year=" + history.getYear();
						HtmlPage annulReport = webClient.getPage(annulReport_url);
						resultHtmlMap.put(history.getYear() + "年度报告", annulReport.asText().toString());
					}

					String dailyInvsub_url = "http://gsxt.cqgs.gov.cn/search_getDaily.action?id=" + id + "&jtype=invsub";
					HtmlPage dailyInvsub = webClient.getPage(dailyInvsub_url);
					resultHtmlMap.put("dailyInvsub", dailyInvsub.asText().substring(6));

					String dailyTransinfo_url = "http://gsxt.cqgs.gov.cn/search_getDaily.action?id=" + id + "&jtype=transinfo";
					HtmlPage dailyTransinfo = webClient.getPage(dailyTransinfo_url);
					resultHtmlMap.put("dailyTransinfo", dailyTransinfo.asText().substring(6));

					String dailyLicinfo_url = "http://gsxt.cqgs.gov.cn/search_getDaily.action?id=" + id + "&jtype=licinfo";
					HtmlPage dailyLicinfo = webClient.getPage(dailyLicinfo_url);
					resultHtmlMap.put("dailyLicinfo", dailyLicinfo.asText().substring(6));

					String dailyPleinfo_url = "http://gsxt.cqgs.gov.cn/search_getDaily.action?id=" + id + "&jtype=pleinfo";
					HtmlPage dailyPleinfo = webClient.getPage(dailyPleinfo_url);
					resultHtmlMap.put("dailyPleinfo", dailyPleinfo.asText().substring(6));

					String dailyPeninfo_url = "http://gsxt.cqgs.gov.cn/search_getDaily.action?id=" + id + "&jtype=peninfo";
					HtmlPage dailyPeninfo = webClient.getPage(dailyPeninfo_url);
					resultHtmlMap.put("dailyPeninfo", dailyPeninfo.asText().substring(6));

					String qlicinfo_url = "http://gsxt.cqgs.gov.cn/search_getOtherSectors.action?entId=" + entId
							+ "&id=" + id + "&qtype=Qlicinfo&type=" + type;
					HtmlPage qlicinfo = webClient.getPage(qlicinfo_url);
					resultHtmlMap.put("qlicinfo", qlicinfo.asText().substring(6));

					String qpeninfo_url = "http://gsxt.cqgs.gov.cn/search_getOtherSectors.action?entId=" + entId
							+ "&id=" + id + "&qtype=Qpeninfo&type=" + type;
					HtmlPage qpeninfo = webClient.getPage(qpeninfo_url);
					resultHtmlMap.put("qpeninfo", qpeninfo.asText().substring(6));

					String SFXZ_url = "http://gsxt.cqgs.gov.cn/search_getSFXZ.action?entId=" + entId
							+ "&id=" + id + "&type=" + type;
					HtmlPage SFXZ = webClient.getPage(SFXZ_url);
					resultHtmlMap.put("SFXZ", SFXZ.asText().substring(6));

					String SFXZGDBG_url = "http://gsxt.cqgs.gov.cn/search_getSFXZGDBG.action?entId=" + entId + "&id=" + id + "&type=" + type;
					HtmlPage SFXZGDBG = webClient.getPage(SFXZGDBG_url);
					resultHtmlMap.put("SFXZGDBG", SFXZGDBG.asText().substring(6));

					break;
				}
			}

			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}

		}
		return resultHtmlMap;
	}
	
	// 吉林数据
	private Map<String, Object> getHtmlInfoMapOfJilin(String area,
			String keyword, ChannelLogger LOGGER) throws Exception {

		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();

		String[] command = { "casperjs",
				"/home/ubuntu/nfs-images/casperjscode/jilin.js",
				"--web-security=no", "--keyword=" + keyword };
		String casperjsResult = CommandUtil.runCommand(command);

		Elements divDataItems = Jsoup.parse(casperjsResult).getElementsByClass(
				"list");
		Elements divNoDataItems = Jsoup.parse(casperjsResult)
				.getElementsByClass("list-a");

		if (divDataItems.isEmpty() && !divNoDataItems.isEmpty()) { // 没有搜索到关键字
			resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
		} else if (divDataItems.isEmpty() && divDataItems.isEmpty()) { // 验证码错误
			// （可能包含被屏蔽的情况待验证）
			if (casperjsResult.contains("计算错误")) {
				resultHtmlMap.put("statusCodeDef",
						StatusCodeDef.IMAGECODE_ERROR);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
			}
		} else if (!divDataItems.isEmpty() && divNoDataItems.isEmpty()) { // 正常有数据
																			// 包含匹配第一条，没有包含匹配到取第一条
			Element nowCookies = Jsoup.parse(casperjsResult).getElementById(
					"nextParams");
			Elements tokenEts = Jsoup.parse(casperjsResult)
					.getElementsByAttributeValue("name", "_csrf");
			if (null == nowCookies || null == tokenEts || tokenEts.isEmpty()) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.COOKIE_ERROR);
				return resultHtmlMap;
			}
			String nowCookiesJson = nowCookies.text().trim();
			String nowCookiesStr = ((String) new GsonBuilder().create()
					.fromJson(nowCookiesJson, Map.class).get("Cookie")).trim();
			String tokenStr = tokenEts.get(0).attr("content");
			String HOST_OF_JILIN = "http://211.141.74.198:8081/aiccips/pub/";
			String HOST_OF_XQ = "http://211.141.74.198:8081/";
			String htmlAnchorHref = "";
			for (Element divDataItem : divDataItems) {
				Element htmlAnchor = divDataItem.getElementsByTag("a").get(0);
				String htmlAnchorText = htmlAnchor.text();
				if (htmlAnchorText.contains(keyword)) {
					htmlAnchorHref = HOST_OF_JILIN + htmlAnchor.attr("href");
					break;
				}
			}
			if (StringUtils.isEmpty(htmlAnchorHref)) {
				htmlAnchorHref = "http://211.141.74.198:8081/aiccips/pub/"
						+ divDataItems.get(0).getElementsByTag("a").get(0)
								.attr("href");
			}
			String commonUrl = htmlAnchorHref.split("gsgsdetail")[1];
			String commonUrlZ = htmlAnchorHref.substring(
					htmlAnchorHref.lastIndexOf("/") + 1,
					htmlAnchorHref.length());

			// 工商公示信息->登记信息
			String[] command11 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js",
					"--web-security=no", "--url=" + htmlAnchorHref };
			String casperjsResult11 = CommandUtil.runCommand(command11);
			resultHtmlMap.put("gsgsxx", casperjsResult11);
			Thread.sleep(1000);

			// 工商公示信息->备案信息->主要人员信息
			String baxxZyryxxUrl = HOST_OF_JILIN + "gsryxx/1151?encrpripid="
					+ commonUrlZ;
			String[] command121 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + htmlAnchorHref, "--tokenStr=" + tokenStr,
					"--url=" + baxxZyryxxUrl };
			String casperjsResult121 = CommandUtil.runCommand(command121);
			resultHtmlMap.put("gsgsxx_baxx_zyryxx", casperjsResult121);

			// 工商公示信息->备案信息->分支机构信息
			String baxxFzjgxxUrl = HOST_OF_JILIN + "gsfzjg/1151?encrpripid="
					+ commonUrlZ;
			String[] command123 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + htmlAnchorHref, "--tokenStr=" + tokenStr,
					"--url=" + baxxFzjgxxUrl };
			String casperjsResult123 = CommandUtil.runCommand(command123);
			resultHtmlMap.put("gsgsxx_baxx_fzjgxx", casperjsResult123);

			// 工商公示信息->动产抵押登记信息->动产抵押登记信息
			String dcdydjxxDcdydjxxUrl = HOST_OF_JILIN + "gsdcdy?encrpripid="
					+ commonUrlZ;
			String[] command131 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + htmlAnchorHref, "--tokenStr=" + tokenStr,
					"--url=" + dcdydjxxDcdydjxxUrl };
			String casperjsResult131 = CommandUtil.runCommand(command131);
			resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx", casperjsResult131);

			// 工商公示信息->股权出质登记信息->股权出质登记信息
			String gqczdjxxGqczdjxxUrl = HOST_OF_JILIN + "gsgqcz?encrpripid="
					+ commonUrlZ;
			String[] command141 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + htmlAnchorHref, "--tokenStr=" + tokenStr,
					"--url=" + gqczdjxxGqczdjxxUrl };
			String casperjsResult141 = CommandUtil.runCommand(command141);
			resultHtmlMap.put("gsgsxx_gqczdjxx_gqczdjxx", casperjsResult141);

			// 工商公示信息->行政处罚信息->行政处罚信息
			String xzcfxxXzcfxxUrl = HOST_OF_JILIN + "gsxzcfxx?encrpripid="
					+ commonUrlZ;
			String[] command151 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + htmlAnchorHref, "--tokenStr=" + tokenStr,
					"--url=" + xzcfxxXzcfxxUrl };
			String casperjsResult151 = CommandUtil.runCommand(command151);
			resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx", casperjsResult151);

			// 工商公示信息->经营异常信息->经营异常信息
			String jyycxxJyycxxUrl = HOST_OF_JILIN + "jyyc/1151?encrpripid="
					+ commonUrlZ;
			String[] command161 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + htmlAnchorHref, "--tokenStr=" + tokenStr,
					"--url=" + jyycxxJyycxxUrl };
			String casperjsResult161 = CommandUtil.runCommand(command161);
			resultHtmlMap.put("gsgsxx_jyycxx_jyycxx", casperjsResult161);

			// 工商公示信息->严重违法信息->严重违法信息
			String yzwfxxYzwfxxUrl = HOST_OF_JILIN + "yzwfqy?encrpripid="
					+ commonUrlZ;
			String[] command171 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + htmlAnchorHref, "--tokenStr=" + tokenStr,
					"--url=" + yzwfxxYzwfxxUrl };
			String casperjsResult171 = CommandUtil.runCommand(command171);
			resultHtmlMap.put("gsgsxx_yzwfxx_yzwfxx", casperjsResult171);

			// 工商公示信息->抽查检查信息->抽查检查信息
			String ccjcxxCcjcxxUrl = HOST_OF_JILIN + "ccjcxx?encrpripid="
					+ commonUrlZ;
			String[] command181 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + htmlAnchorHref, "--tokenStr=" + tokenStr,
					"--url=" + ccjcxxCcjcxxUrl };
			String casperjsResult181 = CommandUtil.runCommand(command181);
			resultHtmlMap.put("gsgsxx_ccjcxx_ccjcxx", casperjsResult181);

			// 企业公示信息
			String qygsUrl = HOST_OF_JILIN + "qygsdetail" + commonUrl;
			String[] command2 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js",
					"--web-security=no", "--url=" + qygsUrl };
			String casperjsResult2 = CommandUtil.runCommand(command2);
			resultHtmlMap.put("qygsxx_list", casperjsResult2);

			// 请求获取 企业公示信息->企业年报->详情
			Document qygsxxHtml = Jsoup.parseBodyFragment(casperjsResult2);
			Element qynbDiv = qygsxxHtml.getElementById("qiyenianbao");
			if (null != qynbDiv) {
				Elements qynb_trs = qynbDiv.select("tbody").get(0).select("tr");
				if (null != qynb_trs && qynb_trs.size() > 2) {
					List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
					for (int i = 2; i < qynb_trs.size(); i++) {
						Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
						Element wdd = qynb_trs.get(i).select("td").get(1)
								.select("a").get(0);
						String qygsxx_qynb_list_a_text = wdd.text();
						String qygsxx_qynb_list_pubdate = qynb_trs.get(i)
								.select("td").get(2).text();
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",
								qygsxx_qynb_list_a_text);
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",
								qygsxx_qynb_list_pubdate);
						String qynbxqUrl = HOST_OF_XQ + wdd.attr("href");
						String[] command21 = {
								"casperjs",
								"/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js",
								"--web-security=no", "--url=" + qynbxqUrl };
						String casperjsResult21 = CommandUtil
								.runCommand(command21);
						qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",
								casperjsResult21);
						qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
					}
					resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);
				}
			}
			Thread.sleep(1000);

			// 企业公示信息->股东及出资信息->股东及出资信息
			String gdjczxxGdjczxxUrl = HOST_OF_JILIN
					+ "qygsjsxxxzczxx?encrpripid=" + commonUrlZ;
			String[] command221 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + qygsUrl, "--tokenStr=" + tokenStr,
					"--url=" + gdjczxxGdjczxxUrl };
			String casperjsResult221 = CommandUtil.runCommand(command221);
			resultHtmlMap.put("qygsxx_gdjczxx_gdjczxx", casperjsResult221);

			// 企业公示信息->股东及出资信息->变更信息
			String gdjczxxBgxxUrl = HOST_OF_JILIN
					+ "qygsjsxxczxxbgsx?encrpripid=" + commonUrlZ;
			String[] command222 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + qygsUrl, "--tokenStr=" + tokenStr,
					"--url=" + gdjczxxBgxxUrl };
			String casperjsResult222 = CommandUtil.runCommand(command222);
			resultHtmlMap.put("qygsxx_gdjczxx_bgxx", casperjsResult222);

			// 企业公示信息->股权变更信息->股权变更信息
			String gqbgxxGqbgxxUrl = HOST_OF_JILIN + "qygsJsxxgqbg?encrpripid="
					+ commonUrlZ;
			String[] command231 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + qygsUrl, "--tokenStr=" + tokenStr,
					"--url=" + gqbgxxGqbgxxUrl };
			String casperjsResult231 = CommandUtil.runCommand(command231);
			resultHtmlMap.put("qygsxx_gqbgxx_gqbgxx", casperjsResult231);

			// 企业公示信息->行政许可信息->行政许可信息
			String xzxkxxXzxkxxUrl = HOST_OF_JILIN + "qygsjsxxxzxk?encrpripid="
					+ commonUrlZ;
			String[] command241 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + qygsUrl, "--tokenStr=" + tokenStr,
					"--url=" + xzxkxxXzxkxxUrl };
			String casperjsResult241 = CommandUtil.runCommand(command241);
			resultHtmlMap.put("qygsxx_xzxkxx_xzxkxx", casperjsResult241);

			// 企业公示信息->知识产权出质登记信息->知识产权出质登记信息
			String zscqczZscqczUrl = HOST_OF_JILIN
					+ "/qygsjsxxzscqcz?encrpripid=" + commonUrlZ;
			String[] command251 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + qygsUrl, "--tokenStr=" + tokenStr,
					"--url=" + zscqczZscqczUrl };
			String casperjsResult251 = CommandUtil.runCommand(command251);
			resultHtmlMap.put("qygsxx_zscqcz_zscqcz", casperjsResult251);

			// 企业公示信息->行政处罚信息->行政处罚信息
			String qygsxxXzcfxxUrl = HOST_OF_JILIN
					+ "qygsjsxxxzcfxx?encrpripid=" + commonUrlZ;
			String[] command261 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/postJilinSimpleRequestPage.js",
					"--web-security=no", "--cookieStr=" + nowCookiesStr,
					"--refererStr=" + qygsUrl, "--tokenStr=" + tokenStr,
					"--url=" + qygsxxXzcfxxUrl };
			String casperjsResult261 = CommandUtil.runCommand(command261);
			resultHtmlMap.put("qygsxx_zscqcz_zscqcz", casperjsResult261);

			// 其它部门公示信息
			String qtbmUrl = HOST_OF_JILIN + "qtgsdetail" + commonUrl;
			String[] command3 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js",
					"--web-security=no", "--url=" + qtbmUrl };
			String casperjsResult3 = CommandUtil.runCommand(command3);
			resultHtmlMap.put("qtbmgsxx", casperjsResult3);

			// 司法协助公示信息
			String sfxzUrl = HOST_OF_JILIN + "sfgsdetail" + commonUrl;
			String[] command4 = {
					"casperjs",
					"/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js",
					"--web-security=no", "--url=" + sfxzUrl };
			String casperjsResult4 = CommandUtil.runCommand(command4);
			resultHtmlMap.put("sfxzgsxx_list", casperjsResult4);

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			
		}

		return resultHtmlMap;

	}
	
	//北京数据
	private Map<String, Object> getHtmlInfoMapOfBeijing(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
		final String HOST_OF_BEIJING = "http://qyxy.baic.gov.cn";
		
		//
		//URL url = firstInfoPage.getUrl();
		//if (!url.toString().contains("getBjQyList.dhtml")) {
		HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']"));
		if (divByXPath == null) {
			DomElement checkcode = firstInfoPage.getElementById("checkcode");
//			String val = checkcode.getAttribute("value");
//			if (!StringUtils.isEmpty(val)) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
//			} else {
//				resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
//			}
		} else {
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
			String textContent = firstByXPath.getTextContent();
			if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}
		}
		//
		
		@SuppressWarnings("unchecked")
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
		LOGGER.info(anchors.toString());
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false; 
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				
				if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
					matchFlag = true;
					//公共参数
					String ent_id = "";
					
					//保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info", target_item_info.asXml());
					
					//点击列表页目标条目，获取 工商公示信息->登记信息->基本信息
					HtmlPage gsgsxx_djxx_jbxx = anchor.click();
					resultHtmlMap.put("gsgsxx_djxx_jbxx", gsgsxx_djxx_jbxx.asXml());
					
					HtmlElement gsgsxx_djxx_tab = (HtmlElement) gsgsxx_djxx_jbxx.getFirstByXPath("//div[@id='tabs']/ul/li[@id='0']");
					String baxx_tab_onclick = gsgsxx_djxx_tab.getAttribute("onclick");
					if (!StringUtils.isEmpty(baxx_tab_onclick)) {
						ent_id = baxx_tab_onclick.split(",")[1].replace("'", "").replace(")", "");
					}
					
					//请求获取 工商公示信息->登记信息->投资人信息
					HtmlElement tzrFrame = (HtmlElement) gsgsxx_djxx_jbxx.getFirstByXPath("//iframe[@id='tzrFrame']");
					if (tzrFrame!=null) {
						String tzr_frame_src = HOST_OF_BEIJING + tzrFrame.getAttribute("src");
						HtmlPage tzrPage = firstInfoPage.getWebClient().getPage(tzr_frame_src);
						
						List<HtmlAnchor> pageAnchorList = (List<HtmlAnchor>)tzrPage.getByXPath("//table/tbody[@id='table2']/tr/th/a");
						//pageNo表示分页个数（从第二页开始），去掉上一页、下一页
						int pageNo = pageAnchorList.size()-2;
						for (int i = 2; i < pageNo+2; i++) {
							HtmlPage tzrNextPage = firstInfoPage.getWebClient().getPage(tzr_frame_src+"&pageNo="+i);
							List<HtmlElement> htmlTrElements = (List<HtmlElement>)tzrNextPage.getByXPath("//table/tbody[@id='table2']/tr[@id='tr1']");
							DomElement tbodyElement = tzrPage.getElementById("table2");
							for (HtmlElement trElement : htmlTrElements) {
								tbodyElement.appendChild(trElement);
							}
						}
						
						resultHtmlMap.put("gsgsxx_djxx_tzrxx", tzrPage.asXml());
						
						//投资人详情
						DomElement touzirenDiv = tzrPage.getElementById("touziren");
						DomNodeList<HtmlElement> touzirenA = touzirenDiv.getElementsByTagName("a");
						List<String> tzrxqList = new ArrayList<String>();
						for (HtmlElement anchorTouziren : touzirenA) {
							String textContent = anchorTouziren.getTextContent();
							if ("详情".equals(textContent)) {
								//HtmlPage xqPage = (HtmlPage)anchorTouziren.click();
								String onclickValue = anchorTouziren.getAttribute("onclick");
								String chr_id = onclickValue.substring(onclickValue.indexOf("'")+1, onclickValue.lastIndexOf("'"));
								String tzrxx_src = "http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!touzirenInfo.dhtml?chr_id=" + chr_id + "&timeStamp=" + new Date().getTime();
								HtmlPage xqPage = firstInfoPage.getWebClient().getPage(tzrxx_src);
								tzrxqList.add(xqPage.asXml());
							}
						}
						resultHtmlMap.put("gsgsxx_djxx_tzrxx_xq", tzrxqList);
					}
					
					//请求获取 工商公示信息->登记信息->变更信息
					HtmlElement bgxxFrame = (HtmlElement) gsgsxx_djxx_jbxx.getFirstByXPath("//iframe[@id='bgxxFrame']");
					if (bgxxFrame!=null) {
						String bgxx_frame_src = HOST_OF_BEIJING + bgxxFrame.getAttribute("src");
						HtmlPage bgxxPage = firstInfoPage.getWebClient().getPage(bgxx_frame_src);
						resultHtmlMap.put("gsgsxx_djxx_bgxx", bgxxPage.asXml());
						
						//变更详情
						DomElement bgxqTable = bgxxPage.getElementById("touziren");
						DomNodeList<HtmlElement> bgxqA = bgxqTable.getElementsByTagName("a");
						List<String> bgxqList = new ArrayList<String>();
						for (HtmlElement anchorBgxq : bgxqA) {
							String textContent = anchorBgxq.getTextContent();
							if ("详细".equals(textContent)) {
								HtmlPage xqPage = (HtmlPage)anchorBgxq.click();
								bgxqList.add(xqPage.asXml());
							}
						}
						resultHtmlMap.put("gsgsxx_djxx_bgxx_xq", bgxqList);
					}
					
					//请求获取 工商公示信息->备案信息->主要人员信息
					String gsgsxx_baxx_zyryxx_url = "http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!zyryFrame.dhtml?pageSize=50&ent_id=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage gsgsxx_baxx_zyryxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_baxx_zyryxx_url);
					resultHtmlMap.put("gsgsxx_baxx_zyryxx", gsgsxx_baxx_zyryxx_page.asXml());
					
					//请求获取 工商公示信息->备案信息->分支机构信息
					String gsgsxx_baxx_fzjgxx_url = "http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!fzjgFrame.dhtml?ent_id=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage gsgsxx_baxx_fzjgxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_baxx_fzjgxx_url);
					resultHtmlMap.put("gsgsxx_baxx_fzjgxx", gsgsxx_baxx_fzjgxx_page.asXml());
					
					//请求获取 工商公示信息->备案信息->清算信息
					String gsgsxx_baxx_qsxx_url = "http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!qsxxFrame.dhtml?ent_id=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					TextPage gsgsxx_baxx_qsxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_baxx_qsxx_url);
					resultHtmlMap.put("gsgsxx_baxx_qsxx", gsgsxx_baxx_qsxx_page.getContent());
					
					//请求获取 工商公示信息->备案信息->主管部门（出资人）信息
					String gsgsxx_baxx_zgbmxx_url = "http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!zgbmFrame.dhtml?ent_id=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage gsgsxx_baxx_zgbmxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_baxx_zgbmxx_url);
					resultHtmlMap.put("gsgsxx_baxx_zgbmxx", gsgsxx_baxx_zgbmxx_page.asXml());
					
					//请求获取 工商公示信息->动产抵押登记信息->动产抵押登记信息
					String gsgsxx_dcdydjxx_dcdydjxx_url = "http://qyxy.baic.gov.cn/gjjbjTab/gjjTabQueryCreditAction!dcdyFrame.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage gsgsxx_dcdydjxx_dcdydjxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_dcdydjxx_dcdydjxx_url);
					resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx", gsgsxx_dcdydjxx_dcdydjxx_page.asXml());
					//请求获取 工商公示信息->动产抵押登记信息->动产抵押登记信息->详情
					List<HtmlElement> gsgsxx_dcdydjxx_list_as = (List<HtmlElement>) gsgsxx_dcdydjxx_dcdydjxx_page.getByXPath("//table/tbody/tr/td/a");
					List<Map<String, String>> gsgsxx_dcdydjxx_details = new ArrayList<Map<String, String>>();
					for (HtmlElement gsgsxx_dcdydjxx_list_a : gsgsxx_dcdydjxx_list_as) {
						Map<String, String> detail_map = new HashMap<String, String>();
						String gsgsxx_dcdydjxx_list_a_href = "http://qyxy.baic.gov.cn" + gsgsxx_dcdydjxx_list_a.getAttribute("href");
						
						//获取动产抵押登记信息详情 (动产抵押登记信息|被担保债权概况|注销)
						HtmlPage gsgsxx_dcdydjxx_detail_page = firstInfoPage.getWebClient().getPage(gsgsxx_dcdydjxx_list_a_href);
						detail_map.put("gsgsxx_dcdydjxx_detail_page", gsgsxx_dcdydjxx_detail_page.asXml());
						
						//获取动产抵押登记信息详情  抵押权人概况
						HtmlElement gsgsxx_dcdydjxx_detail_dyqrgk_iframe = (HtmlElement) gsgsxx_dcdydjxx_detail_page.getFirstByXPath("//iframe[@id='dyqrgkFrame']");
						if (gsgsxx_dcdydjxx_detail_dyqrgk_iframe!=null) {
							String gsgsxx_dcdydjxx_detail_dyqrgk_url = "http://qyxy.baic.gov.cn" + gsgsxx_dcdydjxx_detail_dyqrgk_iframe.getAttribute("src");
							HtmlPage gsgsxx_dcdydjxx_detail_dyqrgk_page = firstInfoPage.getWebClient().getPage(gsgsxx_dcdydjxx_detail_dyqrgk_url);
							detail_map.put("gsgsxx_dcdydjxx_detail_dyqrgk_page", gsgsxx_dcdydjxx_detail_dyqrgk_page.asXml());
						}
						
						//获取动产抵押登记信息详情   抵押物概况
						HtmlElement gsgsxx_dcdydjxx_detail_dywgk_iframe = (HtmlElement) gsgsxx_dcdydjxx_detail_page.getFirstByXPath("//iframe[@id='dywgkFrame']");
						if (gsgsxx_dcdydjxx_detail_dywgk_iframe!=null) {
							String gsgsxx_dcdydjxx_detail_dywgk_url = "http://qyxy.baic.gov.cn" + gsgsxx_dcdydjxx_detail_dywgk_iframe.getAttribute("src");
							HtmlPage gsgsxx_dcdydjxx_detail_dywgk_page = firstInfoPage.getWebClient().getPage(gsgsxx_dcdydjxx_detail_dywgk_url);
							detail_map.put("gsgsxx_dcdydjxx_detail_dywgk_page", gsgsxx_dcdydjxx_detail_dywgk_page.asXml());
						}
						
						//获取动产抵押登记信息详情  变更
						HtmlElement gsgsxx_dcdydjxx_detail_dcdybg_iframe = (HtmlElement) gsgsxx_dcdydjxx_detail_page.getFirstByXPath("//iframe[@id='dcdybgFrame']");
						if (gsgsxx_dcdydjxx_detail_dcdybg_iframe!=null) {
							String gsgsxx_dcdydjxx_detail_dcdybg_url = "http://qyxy.baic.gov.cn" + gsgsxx_dcdydjxx_detail_dcdybg_iframe.getAttribute("src");
							HtmlPage gsgsxx_dcdydjxx_detail_dcdybg_page = firstInfoPage.getWebClient().getPage(gsgsxx_dcdydjxx_detail_dcdybg_url);
							detail_map.put("gsgsxx_dcdydjxx_detail_dcdybg_page", gsgsxx_dcdydjxx_detail_dcdybg_page.asXml());
						}
						
						gsgsxx_dcdydjxx_details.add(detail_map);
					}
					resultHtmlMap.put("gsgsxx_dcdydjxx_details", gsgsxx_dcdydjxx_details);
					
					//请求获取 工商公示信息->股权出质登记信息->股权出质登记信息
					String gsgsxx_gqczdjxx_gqczdjxx_url = "http://qyxy.baic.gov.cn/gdczdj/gdczdjAction!gdczdjFrame.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage gsgsxx_gqczdjxx_gqczdjxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_gqczdjxx_gqczdjxx_url);
					resultHtmlMap.put("gsgsxx_gqczdjxx_gqczdjxx", gsgsxx_gqczdjxx_gqczdjxx_page.asXml());
					
					//请求获取 工商公示信息->行政处罚信息->行政处罚信息
					String gsgsxx_xzcfxx_xzcfxx_url = "http://qyxy.baic.gov.cn/gsgs/gsxzcfAction!list.dhtml?entId="+ ent_id +"&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage gsgsxx_xzcfxx_xzcfxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_xzcfxx_xzcfxx_url);
					resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx", gsgsxx_xzcfxx_xzcfxx_page.asXml());
					
					//行政处罚信息详情
					DomElement xzcfxxDiv = gsgsxx_xzcfxx_xzcfxx_page.getElementById("xzcf");
					DomNodeList<HtmlElement> xzcfxqA = xzcfxxDiv.getElementsByTagName("a");
					List<String> xzcfxqList = new ArrayList<String>();
					for (HtmlElement anchorXxcf : xzcfxqA) {
						String textContent = anchorXxcf.getTextContent();
						if ("详情".equals(textContent)) {
							HtmlPage xqPage = (HtmlPage)anchorXxcf.click();
							xzcfxqList.add(xqPage.asXml());
						}
					}
					resultHtmlMap.put("gsgsxx_djxx_xxcfxx_xq", xzcfxqList);
					
					//请求获取 工商公示信息->经营异常信息->经营异常信息
					String gsgsxx_jyycxx_jyycxx_url = "http://qyxy.baic.gov.cn/gsgs/gsxzcfAction!list_jyycxx.dhtml?entId=" + ent_id;
					HtmlPage gsgsxx_jyycxx_jyycxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_jyycxx_jyycxx_url);
					resultHtmlMap.put("gsgsxx_jyycxx_jyycxx", gsgsxx_jyycxx_jyycxx_page.asXml());
					
					//请求获取 工商公示信息->严重违法信息->严重违法信息
					String gsgsxx_yzwfxx_yzwfxx_url = "http://qyxy.baic.gov.cn/gsgs/gsxzcfAction!list_yzwfxx.dhtml?ent_id=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage gsgsxx_yzwfxx_yzwfxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_yzwfxx_yzwfxx_url);
					resultHtmlMap.put("gsgsxx_yzwfxx_yzwfxx", gsgsxx_yzwfxx_yzwfxx_page.asXml());
					
					//请求获取 工商公示信息->抽查检查信息->抽查检查信息
					String gsgsxx_ccjcxx_ccjcxx_url = "http://qyxy.baic.gov.cn/gsgs/gsxzcfAction!list_ccjcxx.dhtml?ent_id=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage gsgsxx_ccjcxx_ccjcxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_ccjcxx_ccjcxx_url);
					resultHtmlMap.put("gsgsxx_ccjcxx_ccjcxx", gsgsxx_ccjcxx_ccjcxx_page.asXml());
					
					//请求获取 企业公示信息->企业年报->列表 （列表页有a标签，但是获取不到，如个体工商户  北京信和餐厅）
					String qygsxx_qynb_list_url = "http://qyxy.baic.gov.cn/qynb/entinfoAction!qyxx.dhtml?entid=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage qygsxx_qynb_list_page = firstInfoPage.getWebClient().getPage(qygsxx_qynb_list_url);
					
					//请求获取 企业公示信息->企业年报->详情 （1_3包含的情况较多 不一定全  样本：北京信和餐厅  北京上容恒沛酒店管理有限公司  湖南上容信息技术有限公司北京分公司）
					@SuppressWarnings("unchecked")
					List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_list_page.getByXPath("//div[@id='qiyenianbao']/table/tbody/tr/td[2]/a");
					List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
					if (qygsxx_qynb_list_as!=null && !qygsxx_qynb_list_as.isEmpty()) {
						for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
							Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
							String qygsxx_qynb_list_a_href = "http://qyxy.baic.gov.cn" + qygsxx_qynb_list_a.getAttribute("href");
							String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a.getTextContent();
							String qygsxx_qynb_list_pubdate = qygsxx_qynb_list_a.getParentNode().getParentNode().getTextContent();
							String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
							Pattern pattern = Pattern.compile(eL);
							Matcher matcher = pattern.matcher(qygsxx_qynb_list_pubdate);
						    String dateStr = "";
						    if(matcher.find()){
						    	dateStr = matcher.group(0);
						    }
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_href", qygsxx_qynb_list_a_href);
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text", qygsxx_qynb_list_a_text);
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate", dateStr);
							//获取企业年报详情  企业基本信息 & (企业资产状况信息|生产经营情况)
							HtmlPage qygsxx_qynb_info_1_3_page = firstInfoPage.getWebClient().getPage(qygsxx_qynb_list_a_href);
							qygsxx_qynb_info_map.put("qygsxx_qynb_info_1_3_page", qygsxx_qynb_info_1_3_page.asXml());
							//获取企业年报详情  股东及出资信息
							HtmlElement qygsxx_qynb_info_gdjczxx_iframe = (HtmlElement) qygsxx_qynb_info_1_3_page.getFirstByXPath("//iframe[@id='gdczFrame']");
							if (qygsxx_qynb_info_gdjczxx_iframe!=null) {
								String qygsxx_qynb_info_gdjczxx_url = "http://qyxy.baic.gov.cn" + qygsxx_qynb_info_gdjczxx_iframe.getAttribute("src");
								HtmlPage qygsxx_qynb_info_gdjczxx_page = firstInfoPage.getWebClient().getPage(qygsxx_qynb_info_gdjczxx_url);
								
								List<HtmlAnchor> pageAnchorList = (List<HtmlAnchor>)qygsxx_qynb_info_gdjczxx_page.getByXPath("//table[@id='touziren']/tbody/tr/th/a");
								//pageNo表示分页个数（从第二页开始），去掉上一页、下一页
								int pageNo = pageAnchorList.size()-2;
								for (int i = 2; i < pageNo+2; i++) {
									HtmlPage tzrNextPage = firstInfoPage.getWebClient().getPage(qygsxx_qynb_info_gdjczxx_url+"&pageNo="+i);
									List<HtmlElement> htmlTrElements = (List<HtmlElement>)tzrNextPage.getByXPath("//table[@id='touziren']/tbody/tr");
									DomElement tableElement = qygsxx_qynb_info_gdjczxx_page.getElementById("touziren");
									DomElement tbodyElement = (DomElement)tableElement.getFirstByXPath("//tbody");
									for (HtmlElement trElement : htmlTrElements) {
										tbodyElement.appendChild(trElement);
									}
								}
								
								qygsxx_qynb_info_map.put("qygsxx_qynb_info_gdjczxx_page", qygsxx_qynb_info_gdjczxx_page.asXml());
							}
							//获取企业年报详情  对外提供保证担保信息
							HtmlElement qygsxx_qynb_info_dwtgbzdbxx_iframe = (HtmlElement) qygsxx_qynb_info_1_3_page.getFirstByXPath("//iframe[@id='dwdbFrame']");
							if (qygsxx_qynb_info_dwtgbzdbxx_iframe!=null) {
								String qygsxx_qynb_info_dwtgbzdbxx_url = "http://qyxy.baic.gov.cn" + qygsxx_qynb_info_dwtgbzdbxx_iframe.getAttribute("src");
								HtmlPage qygsxx_qynb_info_dwtgbzdbxx_page = firstInfoPage.getWebClient().getPage(qygsxx_qynb_info_dwtgbzdbxx_url);
								qygsxx_qynb_info_map.put("qygsxx_qynb_info_dwtgbzdbxx_page", qygsxx_qynb_info_dwtgbzdbxx_page.asXml());
							}
							//获取企业年报详情  修改记录
							HtmlElement qygsxx_qynb_info_xgjl_iframe = (HtmlElement) qygsxx_qynb_info_1_3_page.getFirstByXPath("//iframe[@id='xgFrame']");
							if (qygsxx_qynb_info_xgjl_iframe!=null) {
								String qygsxx_qynb_info_xgjl_url = "http://qyxy.baic.gov.cn" + qygsxx_qynb_info_xgjl_iframe.getAttribute("src");
								HtmlPage qygsxx_qynb_info_xgjl_page = firstInfoPage.getWebClient().getPage(qygsxx_qynb_info_xgjl_url);
								
								//List<HtmlAnchor> pageAnchorList = (List<HtmlAnchor>)qygsxx_qynb_info_xgjl_page.getByXPath("//table[@id='touziren']/tbody/tr/th/a");
								DomElement pagescountHidden = qygsxx_qynb_info_xgjl_page.getElementById("pagescount");
								if (pagescountHidden != null) {
									String pagescount = pagescountHidden.getAttribute("value");
									int totalPage = Integer.valueOf(pagescount);
									for (int i = 2; i < totalPage+1; i++) {
										HtmlPage tzrNextPage = firstInfoPage.getWebClient().getPage(qygsxx_qynb_info_xgjl_url+"&pageNo="+i);
										List<HtmlElement> htmlTrElements = (List<HtmlElement>)tzrNextPage.getByXPath("//table[@id='touziren']/tbody/tr");
										DomElement tableElement = qygsxx_qynb_info_xgjl_page.getElementById("touziren");
										DomElement tbodyElement = (DomElement)tableElement.getFirstByXPath("//tbody");
										for (HtmlElement trElement : htmlTrElements) {
											tbodyElement.appendChild(trElement);
										}
									}
								}
								
								qygsxx_qynb_info_map.put("qygsxx_qynb_info_xgjl_page", qygsxx_qynb_info_xgjl_page.asXml());
							}
							//获取企业年报详情  网站或网店信息
							HtmlElement qygsxx_qynb_info_wzhwdxx_iframe = (HtmlElement) qygsxx_qynb_info_1_3_page.getFirstByXPath("//iframe[@id='wzFrame']");
							if (qygsxx_qynb_info_wzhwdxx_iframe!=null) {
								String qygsxx_qynb_info_wzhwdxx_url = "http://qyxy.baic.gov.cn" + qygsxx_qynb_info_wzhwdxx_iframe.getAttribute("src");
								HtmlPage qygsxx_qynb_info_wzhwdxx_page = firstInfoPage.getWebClient().getPage(qygsxx_qynb_info_wzhwdxx_url);
								qygsxx_qynb_info_map.put("qygsxx_qynb_info_wzhwdxx_page", qygsxx_qynb_info_wzhwdxx_page.asXml());
							}
							//获取企业年报详情  对外投资信息 
							HtmlElement qygsxx_qynb_info_dwtzxx_iframe = (HtmlElement) qygsxx_qynb_info_1_3_page.getFirstByXPath("//iframe[@id='dwtzFrame']");
							if (qygsxx_qynb_info_dwtzxx_iframe!=null) {
								String qygsxx_qynb_info_dwtzxx_url = "http://qyxy.baic.gov.cn" + qygsxx_qynb_info_dwtzxx_iframe.getAttribute("src");
								HtmlPage qygsxx_qynb_info_dwtzxx_page = firstInfoPage.getWebClient().getPage(qygsxx_qynb_info_dwtzxx_url);
								qygsxx_qynb_info_map.put("qygsxx_qynb_info_dwtzxx_page", qygsxx_qynb_info_dwtzxx_page.asXml());
							}							
							qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
						}
					}
					resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);
					
					//请求获取 企业公示信息->股东及出资信息
					String qygsxx_gdjczxx_url = "http://qyxy.baic.gov.cn/gdcz/gdczAction!list_index.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage qygsxx_gdjczxx_page = firstInfoPage.getWebClient().getPage(qygsxx_gdjczxx_url);
					resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx_page.asXml());
					
					//请求获取 企业公示信息->股权变更信息
					String qygsxx_gqbgxx_url = "http://qyxy.baic.gov.cn/gdgq/gdgqAction!gdgqzrxxFrame.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage qygsxx_gqbgxx_page = firstInfoPage.getWebClient().getPage(qygsxx_gqbgxx_url);
					resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx_page.asXml());
					
					//请求获取 企业公示信息->行政许可信息
					String qygsxx_xzxkxx_url = "http://qyxy.baic.gov.cn/xzxk/xzxkAction!list_index.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage qygsxx_xzxkxx_page = firstInfoPage.getWebClient().getPage(qygsxx_xzxkxx_url);
					resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx_page.asXml());
					//请求获取 企业公示信息->行政许可信息->详情
					List<HtmlElement> yqgsxx_xzxkxx_list_as = (List<HtmlElement>) qygsxx_xzxkxx_page.getByXPath("//table/tbody/tr/td/a");
					List<Map<String, String>> yqgsxx_xzxkxx_details = new ArrayList<Map<String, String>>();
					for (HtmlElement yqgsxx_xzxkxx_list_a : yqgsxx_xzxkxx_list_as) {
						Map<String, String> detail_map = new HashMap<String, String>();
						String yqgsxx_xzxkxx_list_a_href = "http://qyxy.baic.gov.cn" + yqgsxx_xzxkxx_list_a.getAttribute("href");
						HtmlPage yqgsxx_xzxkxx_detail_page = firstInfoPage.getWebClient().getPage(yqgsxx_xzxkxx_list_a_href);
						detail_map.put("yqgsxx_xzxkxx_detail_page", yqgsxx_xzxkxx_detail_page.asXml());
						
						yqgsxx_xzxkxx_details.add(detail_map);
					}
					resultHtmlMap.put("yqgsxx_xzxkxx_details", yqgsxx_xzxkxx_details);
					
					//请求获取 企业公示信息->知识产权出质登记信息
					String qygsxx_zscqczdjxx_url = "http://qyxy.baic.gov.cn/zscqczdj/zscqczdjAction!list_index.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage qygsxx_zscqczdjxx_page = firstInfoPage.getWebClient().getPage(qygsxx_zscqczdjxx_url);
					resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx_page.asXml());
					
					//请求获取 企业公示信息->行政处罚信息
					String qygsxx_xzcfxx_url = "http://qyxy.baic.gov.cn/gdgq/gdgqAction!qyxzcfFrame.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage qygsxx_xzcfxx_page = firstInfoPage.getWebClient().getPage(qygsxx_xzcfxx_url);
					resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx_page.asXml());
					
					//请求获取  其他部门公示信息->行政许可信息
					String qtbmgsxx_xzxkxx_url = "http://qyxy.baic.gov.cn/qtbm/qtbmAction!list_xzxk.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage qtbmgsxx_xzxkxx_page = firstInfoPage.getWebClient().getPage(qtbmgsxx_xzxkxx_url);
					resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx_page.asXml());
					
					//请求获取  其他部门公示信息->行政处罚信息
					String qtbmgsxx_xzcfxx_url = "http://qyxy.baic.gov.cn/qtbm/qtbmAction!list_xzcf.dhtml?entId=" + ent_id + "&clear=true&timeStamp=" + new Date().getTime();
					HtmlPage qtbmgsxx_xzcfxx_page = firstInfoPage.getWebClient().getPage(qtbmgsxx_xzcfxx_url);
					resultHtmlMap.put("qtbmgsxx_xzcfxx", qtbmgsxx_xzcfxx_page.asXml());
					
					break;//
				}
			}
			
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
			
		}
		return resultHtmlMap;
	}
	
	//广东数据
	private Map<String, Object> getHtmlInfoMapOfGuangdong(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		String HOST_OF_GUANGDONG = "http://gsxt.gzaic.gov.cn/aiccips";
		WebClient webClient = firstInfoPage.getWebClient();
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
		
		HtmlElement divByXPath = firstInfoPage.getFirstByXPath("//div[@class='list']");
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
		LOGGER.info(anchors.toString());
		
		if (divByXPath == null) {
			String htmlContent = firstInfoPage.getWebResponse().getContentAsString("utf-8");
			if (htmlContent != null && htmlContent.contains("验证没有通过")) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				if (firstInfoPage.asXml().contains("未查询到")) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				} else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
				}
			}
		} else {
			if (anchors.size() == 0) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}
		}
		
		//获得所有数据页
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false; 
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				
				if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
					matchFlag = true;
					//获得参数entNo、entType、regOrg
					String entNo = firstInfoPage.getElementById("entNo").getAttribute("value");
					String entType = firstInfoPage.getElementById("entType").getAttribute("value");
					String regOrg = firstInfoPage.getElementById("regOrg").getAttribute("value");
					
					//保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info", target_item_info.asXml());
					
					String gsgsxx_djxx_url = "";
					String href = anchor.getAttribute("href");
 
					if (href.contains("http://www.szcredit.com.cn")) {
						HOST_OF_GUANGDONG = "http://www.szcredit.com.cn/web/GSZJGSPT/";
						gsgsxx_djxx_url = href;
						
						//工商公示信息（包含了全部子项信息）
						HtmlPage gsgsxx_djxx_page = webClient.getPage(gsgsxx_djxx_url);
						resultHtmlMap.put("gsgsxx_page", gsgsxx_djxx_page.asXml());
						
						//请求获取 工商公示信息->登记信息->股东详情（投资人详情）
						DomElement touzirenTable = gsgsxx_djxx_page.getElementById("Table1");
						if (touzirenTable != null) {
							DomNodeList<HtmlElement> touzirenA = touzirenTable.getElementsByTagName("a");
							List<String> tzrxqList = new ArrayList<String>();
							for (HtmlElement anchorTouziren : touzirenA) {
								String textContent = anchorTouziren.getTextContent();
								if ("查看详情".equals(textContent) || "详情".equals(textContent)) {
									String hrefVal = anchorTouziren.getAttribute("href");
									String tzrxx_src = HOST_OF_GUANGDONG + hrefVal;
									HtmlPage xqPage = webClient.getPage(tzrxx_src);
									tzrxqList.add(xqPage.asXml());
								}
							}
							resultHtmlMap.put("gsgsxx_djxx_tzrxx_xq", tzrxqList);
						}
						
						//企业公示信息（包含了全部子项信息）
						String qygsxx_url = gsgsxx_djxx_url.replace("QyxyDetail", "QynbDetail");
						HtmlPage qygsxx_page = webClient.getPage(qygsxx_url);
						resultHtmlMap.put("qygsxx_page", qygsxx_page.asXml());
						
						//其他公示信息（包含了全部子项信息）
						String qtgsxx_url = gsgsxx_djxx_url.replace("QyxyDetail", "QtbmDetail");
						HtmlPage qtgsxx_page = webClient.getPage(qtgsxx_url);
						resultHtmlMap.put("qtgsxx_page", qtgsxx_page.asXml());
						
					} else {
						if (href.contains("..")) {
							HOST_OF_GUANGDONG = "http://gsxt.gdgs.gov.cn/aiccips";
							gsgsxx_djxx_url = HOST_OF_GUANGDONG + href.replace("..", "");
						} else if(href.contains("http://gsxt.gzaic.gov.cn/aiccips")){
							gsgsxx_djxx_url = href;
						}
						
						//点击列表页目标条目，获取 工商公示信息->登记信息（包括基本信息、股东信息、变更信息）
						//HtmlPage gsgsxx_djxx_page = anchor.click();
						HtmlPage gsgsxx_djxx_page = webClient.getPage(gsgsxx_djxx_url);
						resultHtmlMap.put("gsgsxx_djxx_page", gsgsxx_djxx_page.asXml());
						
						//请求获取 工商公示信息->登记信息->股东详情（投资人详情）
						DomElement touzirenTable = gsgsxx_djxx_page.getElementById("touziren");
						if (touzirenTable != null) {
							DomNodeList<HtmlElement> touzirenA = touzirenTable.getElementsByTagName("a");
							List<String> tzrxqList = new ArrayList<String>();
							for (HtmlElement anchorTouziren : touzirenA) {
								String textContent = anchorTouziren.getTextContent();
								if ("查看详情".equals(textContent) || "详情".equals(textContent)) {
									String hrefVal = anchorTouziren.getAttribute("href");
									if ("#".equals(hrefVal)) {
										String onclickVal = anchorTouziren.getAttribute("onclick");
										if (onclickVal.contains("window.open")) {
											String[] split = onclickVal.split("'");
											HtmlPage xqPage = webClient.getPage(split[1]);
											tzrxqList.add(xqPage.asXml());
										} else {
											tzrxqList.add("");
										}
									} else {
										String tzrxx_src = hrefVal;
										HtmlPage xqPage = webClient.getPage(tzrxx_src);
										tzrxqList.add(xqPage.asXml());
									}
								}
							}
							resultHtmlMap.put("gsgsxx_djxx_tzrxx_xq", tzrxqList);
						}
						
						if ("".equals(entNo) || "".equals(entType) || "".equals(regOrg)) {
							if ("".equals(gsgsxx_djxx_page.asXml())) {
								break;
							}
							entNo = gsgsxx_djxx_page.getElementById("entNo").getAttribute("value");
							entType = gsgsxx_djxx_page.getElementById("entType").getAttribute("value");
							regOrg = gsgsxx_djxx_page.getElementById("regOrg").getAttribute("value");
						}
						
						//获取 工商公示信息->备案信息
						String gsgsxx_baxx_url = HOST_OF_GUANGDONG + "/GSpublicity/GSpublicityList.html?service=entCheckInfo&entNo="+entNo+"&regOrg="+regOrg;
						HtmlPage gsgsxx_baxx_page = webClient.getPage(gsgsxx_baxx_url);
						resultHtmlMap.put("gsgsxx_baxx_page", gsgsxx_baxx_page.asXml());
						
						//获取 工商公示信息->备案信息->主要人员信息
//						String gsgsxx_baxx_zyry_url = HOST_OF_GUANGDONG + "/GSpublicity/vipInfoPage?pageNo=1&entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
//						WebResponse webResponse = webClient.getPage(gsgsxx_baxx_zyry_url).getWebResponse();
//						
//						System.err.println(webResponse.getContentAsString());
//						resultHtmlMap.put("gsgsxx_baxx_page", webResponse.getContentAsString());
						
						//获取 工商公示信息->动产抵押信息
						String gsgsxx_dcdyxx_url = HOST_OF_GUANGDONG + "/GSpublicity/GSpublicityList.html?service=pleInfo&entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage gsgsxx_dcdyxx_page = webClient.getPage(gsgsxx_dcdyxx_url);
						resultHtmlMap.put("gsgsxx_dcdyxx_page", gsgsxx_dcdyxx_page.asXml());
						
						//获取 工商公示信息->股权出质登记信息
						String gsgsxx_gqczdjxx_url = HOST_OF_GUANGDONG + "/GSpublicity/GSpublicityList.html?service=curStoPleInfo&entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						webClient.getOptions().setJavaScriptEnabled(false);
						HtmlPage gsgsxx_gqczdjxx_page = webClient.getPage(gsgsxx_gqczdjxx_url);
						webClient.getOptions().setJavaScriptEnabled(true);
						resultHtmlMap.put("gsgsxx_gqczdjxx_page", gsgsxx_gqczdjxx_page.asXml());
						
						//获取 工商公示信息->行政处罚信息
						String gsgsxx_xzcfxx_url = HOST_OF_GUANGDONG + "/GSpublicity/GSpublicityList.html?service=cipPenaltyInfo&entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						webClient.getOptions().setJavaScriptEnabled(false);
						HtmlPage gsgsxx_xzcfxx_page = webClient.getPage(gsgsxx_xzcfxx_url);
						webClient.getOptions().setJavaScriptEnabled(true);
						resultHtmlMap.put("gsgsxx_xzcfxx_page", gsgsxx_xzcfxx_page.asXml());
						
						//获取 工商公示信息->经营异常信息
						String gsgsxx_jyycxx_url = HOST_OF_GUANGDONG + "/GSpublicity/GSpublicityList.html?service=cipUnuDirInfo&entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						webClient.getOptions().setJavaScriptEnabled(false);
						HtmlPage gsgsxx_jyycxx_page = webClient.getPage(gsgsxx_jyycxx_url);
						webClient.getOptions().setJavaScriptEnabled(true);
						resultHtmlMap.put("gsgsxx_jyycxx_page", gsgsxx_jyycxx_page.asXml());
						
						//获取 工商公示信息->严重违法失信信息
						String gsgsxx_yzwfsxxx_url = HOST_OF_GUANGDONG + "/GSpublicity/GSpublicityList.html?service=cipBlackInfo&entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						webClient.getOptions().setJavaScriptEnabled(false);
						HtmlPage gsgsxx_yzwfsxxx_page = webClient.getPage(gsgsxx_yzwfsxxx_url);
						webClient.getOptions().setJavaScriptEnabled(true);
						resultHtmlMap.put("gsgsxx_yzwfsxxx_page", gsgsxx_yzwfsxxx_page.asXml());
						
						//获取 工商公示信息->抽查检查信息
						String gsgsxx_ccjcxx_url = HOST_OF_GUANGDONG + "/GSpublicity/GSpublicityList.html?service=cipSpotCheInfo&entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						webClient.getOptions().setJavaScriptEnabled(false);
						HtmlPage gsgsxx_ccjcxx_page = webClient.getPage(gsgsxx_ccjcxx_url);
						webClient.getOptions().setJavaScriptEnabled(true);
						resultHtmlMap.put("gsgsxx_ccjcxx_page", gsgsxx_ccjcxx_page.asXml());
						
						//获取 企业公示信息->企业年报
						String qygsxx_qynb_url = HOST_OF_GUANGDONG + "/BusinessAnnals/BusinessAnnalsList.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage qygsxx_qynb_page = webClient.getPage(qygsxx_qynb_url);
						resultHtmlMap.put("qygsxx_qynb_page", qygsxx_qynb_page.asXml());
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_page.getByXPath("//div[@id='qiyenianbao']/table[@id='t01']/tbody/tr/td[2]/a");
						List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
						if (qygsxx_qynb_list_as!=null && !qygsxx_qynb_list_as.isEmpty()) {
							for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
								Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
								String qygsxx_qynb_list_a_href = qygsxx_qynb_list_a.getAttribute("href");
								String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a.getTextContent();
								String qygsxx_qynb_list_pubdate = qygsxx_qynb_list_a.getParentNode().getParentNode().getTextContent();
								String eL = "[0-9]{4}年[0-9]{2}月[0-9]{2}日";
								Pattern pattern = Pattern.compile(eL);
								Matcher matcher = pattern.matcher(qygsxx_qynb_list_pubdate);
							    String dateStr = "";
							    if(matcher.find()){
							    	dateStr = matcher.group(0);
							    }
								qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_href", qygsxx_qynb_list_a_href);
								qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text", qygsxx_qynb_list_a_text);
								qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate", dateStr);
								//获取企业年报详情
								HtmlPage qygsxx_qynb_info = firstInfoPage.getWebClient().getPage(qygsxx_qynb_list_a_href);
								qygsxx_qynb_info_map.put("qygsxx_qynb_info", qygsxx_qynb_info.asXml());

								qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
							}
						}
						resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);
						
						//获取 企业公示信息->股东及出资信息
						String qygsxx_gdjczxx_url = HOST_OF_GUANGDONG + "/ContributionCapitalMsg.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage qygsxx_gdjczxx_page = webClient.getPage(qygsxx_gdjczxx_url);
						resultHtmlMap.put("qygsxx_gdjczxx_page", qygsxx_gdjczxx_page.asXml());
						
						//获取 企业公示信息->股权变更信息
						String qygsxx_gqbgxx_url = HOST_OF_GUANGDONG + "/GDGQTransferMsg/shareholderTransferMsg.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage qygsxx_gqbgxx_page = webClient.getPage(qygsxx_gqbgxx_url);
						resultHtmlMap.put("qygsxx_gqbgxx_page", qygsxx_gqbgxx_page.asXml());
						
						//获取 企业公示信息->行政许可信息
						String qygsxx_xzxkxx_url = HOST_OF_GUANGDONG + "/AppPerInformation.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage qygsxx_xzxkxx_page = webClient.getPage(qygsxx_xzxkxx_url);
						resultHtmlMap.put("qygsxx_xzxkxx_page", qygsxx_xzxkxx_page.asXml());
						//获取 企业公示信息->行政许可信息->详情
						List<HtmlElement> yqgsxx_xzxkxx_list_as = (List<HtmlElement>) qygsxx_xzxkxx_page.getByXPath("//table/tbody/tr/td/a");
						List<Map<String, String>> yqgsxx_xzxkxx_details = new ArrayList<Map<String, String>>();
						for (HtmlElement yqgsxx_xzxkxx_list_a : yqgsxx_xzxkxx_list_as) {
							Map<String, String> detail_map = new HashMap<String, String>();
							String onclickAttr = yqgsxx_xzxkxx_list_a.getAttribute("onclick");
							String[] split = onclickAttr.split("'");
							String yqgsxx_xzxkxx_list_a_href = HOST_OF_GUANGDONG + "/aiccips/detailedness?id="+split[1]+"&status=" + split[3];
							HtmlPage yqgsxx_xzxkxx_detail_page = firstInfoPage.getWebClient().getPage(yqgsxx_xzxkxx_list_a_href);
							detail_map.put("yqgsxx_xzxkxx_detail_page", yqgsxx_xzxkxx_detail_page.asXml());
							
							yqgsxx_xzxkxx_details.add(detail_map);
						}
						resultHtmlMap.put("yqgsxx_xzxkxx_details", yqgsxx_xzxkxx_details);
						
						//获取 企业公示信息->知识产权出质登记信息
						String qygsxx_zscqczdjxx_url = HOST_OF_GUANGDONG + "/intPropertyMsg.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage qygsxx_zscqczdjxx_page = webClient.getPage(qygsxx_zscqczdjxx_url);
						resultHtmlMap.put("qygsxx_zscqczdjxx_page", qygsxx_zscqczdjxx_page.asXml());
						
						//获取 企业公示信息->行政处罚信息
						String qygsxx_xzcfxx_url = HOST_OF_GUANGDONG + "/XZPunishmentMsg.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage qygsxx_xzcfxx_page = webClient.getPage(qygsxx_xzcfxx_url);
						resultHtmlMap.put("qygsxx_xzcfxx_page", qygsxx_xzcfxx_page.asXml());
						
						//获取 其他部门公示信息->行政许可及变更信息  和  行政处罚信息
						String qtbmgsxx_url = HOST_OF_GUANGDONG + "/OtherPublicity/environmentalProtection.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage qtbmgsxx_page = webClient.getPage(qtbmgsxx_url);
						resultHtmlMap.put("qtbmgsxx_page", qtbmgsxx_page.asXml());
						
						//获取 司法协助公示信息->股权冻结信息
						String sfxzgsxx_gqdjxx_url = HOST_OF_GUANGDONG + "/judiciaryAssist/judiciaryAssistInit.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage sfxzgsxx_gqdjxx_page = webClient.getPage(sfxzgsxx_gqdjxx_url);
						resultHtmlMap.put("sfxzgsxx_gqdjxx_page", sfxzgsxx_gqdjxx_page.asXml());
						
						//获取 司法协助公示信息->股东变更信息
						String sfxzgsxx_gqbgxx_url = HOST_OF_GUANGDONG + "/sfGuQuanChange/guQuanChange.html?entNo="+entNo+"&entType="+entType+"&regOrg="+regOrg;
						HtmlPage sfxzgsxx_gqbgxx_page = webClient.getPage(sfxzgsxx_gqbgxx_url);
						resultHtmlMap.put("sfxzgsxx_gqbgxx_page", sfxzgsxx_gqbgxx_page.asXml());
					}
						
					break;
				}
			}
			
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
		}
		
        
		return resultHtmlMap;
	}
	
	//上海数据
	private Map<String, Object> getHtmlInfoMapOfShanghai(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
		//
		HtmlElement divByXPath = firstInfoPage.getFirstByXPath("//div[@class='list-info']");
		HtmlElement divByXPath2 = firstInfoPage.getFirstByXPath("//div[@class='list-stat']");
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list-item']/div/a");
		if (divByXPath == null && divByXPath2 == null) {
			List<?> byXPath = firstInfoPage.getByXPath("div[role='dialog']");
			if (byXPath.size() == 2) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
			}
		} else {
			if (anchors.size() == 0) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}
		}
		//
		
		LOGGER.info(anchors.toString());
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				
				if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
					matchFlag = true;
					//保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info", target_item_info.asXml());
					
					//获得公共url
					String pubUrl = "";
					String hrefAttr = anchor.getAttribute("href");
					if (!StringUtils.isEmpty(hrefAttr)) {
						int index = hrefAttr.indexOf("tab=");
						pubUrl = hrefAttr.substring(0, index);
					}
					
					//一、获取工商公示信息
					String href = anchor.getAttribute("href");
					HtmlPage gsgsxx_page = null;
					try {
						//HtmlPage gsgsxx_page = anchor.click();
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_page = firstInfoPage.getWebClient().getPage(href);
					} catch (ClassCastException e) {
						gsgsxx_page = WebClient.getCustomHtmlPage(href, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}
					resultHtmlMap.put("gsgsxx", gsgsxx_page.asXml());
					
					//工商公示信息-->股东信息-->详情
					List<HtmlAnchor> anchors_gdxx_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//table[@id='investorTable']/tbody/tr/td/a");
					List<String> gdxx_list = new ArrayList<String>();
					for (HtmlAnchor htmlAnchor : anchors_gdxx_detail) {
						HtmlPage gdxx_detail = null;
						try {
							gdxx_detail = htmlAnchor.click();
						} catch (Exception e) {
							gdxx_detail = WebClient.getCustomHtmlPage(href, firstInfoPage.getWebClient().getCurrentWindow());
						}
						gdxx_list.add(gdxx_detail.asXml());
					}
					resultHtmlMap.put("gsgsxx_gdxx_detail", gdxx_list);
					
					List<?> liElements = gsgsxx_page.getByXPath("//div[@class='cont-l']/ul/li");
					//二、获取企业公示信息
					//HtmlElement qygsxx_tab = (HtmlElement)liElements.get(1);
					//HtmlPage qygsxx_page = (HtmlPage)qygsxx_tab.click();
					String qygsxx_url = pubUrl+"tab=02";
					HtmlPage qygsxx_page = null;
					try {
						qygsxx_page = firstInfoPage.getWebClient().getPage(qygsxx_url);
					} catch (Exception e) {
						qygsxx_page = WebClient.getCustomHtmlPage(qygsxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					}
					resultHtmlMap.put("qygsxx", qygsxx_page.asXml());
					
					//企业公示信息-->企业年报-->详情
					List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@rel='layout-02_01']/table/tbody/tr/td/a");
					List<String> nbxx_list = new ArrayList<String>();
					for (HtmlAnchor htmlAnchor : anchors_detail) {
						HtmlPage nb_detail = null;
						try {
							nb_detail = htmlAnchor.click();
						} catch (Exception e) {
							nb_detail = WebClient.getCustomHtmlPage(qygsxx_url, firstInfoPage.getWebClient().getCurrentWindow());
						}
						nbxx_list.add(nb_detail.asXml());
					}
					resultHtmlMap.put("qygsxx_qynb_detail", nbxx_list);
					
					//企业公示信息-->行政许可信息-->详情
					List<HtmlAnchor> anchors_xzxkdetail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@rel='layout-02_02']/table/tbody/tr/td/a");
					List<String> qygsxx_xzxkDetail_list = new ArrayList<String>();
					for (HtmlAnchor htmlAnchor : anchors_xzxkdetail) {
						HtmlPage xzxk_detail = null;
						try {
							xzxk_detail = htmlAnchor.click();
						} catch (Exception e) {
							xzxk_detail = WebClient.getCustomHtmlPage(qygsxx_url, firstInfoPage.getWebClient().getCurrentWindow());
						}
						qygsxx_xzxkDetail_list.add(xzxk_detail.asXml());
					}
					resultHtmlMap.put("qygsxx_xzxkDetail_list", qygsxx_xzxkDetail_list);
					
					//三、获取其他部门公示信息
//					HtmlElement qtbmgsxx_tab = (HtmlElement)liElements.get(2);
//					HtmlPage qtbmgsxx_page = (HtmlPage)qtbmgsxx_tab.click();
//					resultHtmlMap.put("qtbmgsxx", qtbmgsxx_page.asXml());
					
					String qibmgsxx_url = pubUrl+"tab=03";
					HtmlPage qibmgsxx_page = null;
					try {
						qibmgsxx_page = firstInfoPage.getWebClient().getPage(qibmgsxx_url);
					} catch (Exception e) {
						qibmgsxx_page = WebClient.getCustomHtmlPage(qibmgsxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					}
					resultHtmlMap.put("qtbmgsxx", qibmgsxx_page.asXml());
					
					//四、获取 司法协助公示信息
					if(liElements.size()>3) {
//						HtmlElement sfxzgsxx_tab = (HtmlElement)liElements.get(3);
//						HtmlPage sfxzgsxx_page = (HtmlPage)sfxzgsxx_tab.click();
//						resultHtmlMap.put("sfxzgsxx", sfxzgsxx_page.asXml());
						
						String sfxzxx_url = pubUrl+"tab=06";
						HtmlPage sfxzxx_page = null;
						try {
							sfxzxx_page = firstInfoPage.getWebClient().getPage(sfxzxx_url);
						} catch (Exception e) {
							sfxzxx_page = WebClient.getCustomHtmlPage(sfxzxx_url, firstInfoPage.getWebClient().getCurrentWindow());
						}
						resultHtmlMap.put("sfxzgsxx", sfxzxx_page.asXml());
					}
					
					break;
				}
		
			}
			
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
		}
		
		return resultHtmlMap;
	}
	
	//天津数据（dyx）
	private Map<String, Object> getHtmlInfoMapOfTianjin(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
		//校验验证码和是否有列表数据
//		System.out.println(firstInfoPage.asXml());
		WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();	
		@SuppressWarnings("unchecked")
		List<HtmlAnchor> divByXPath = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='result-item']");
		HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='content']/div[@style='font-size:12px']"));
		if (divByXPath.size() == 0 && firstByXPath==null) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
		} else {
			if(firstByXPath!=null){
				String textContent = firstByXPath.getTextContent();
				if (textContent.indexOf("您查询的信息多于 0 条记录") > 0) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				}else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				} 
			}else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			} 
		}
		@SuppressWarnings("unchecked")
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='result-item']/div/a");
		LOGGER.info(anchors.toString());
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
					matchFlag = true;
					//保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info", target_item_info.asXml());
					
					//*****************工商公示信息   开始 *****************
					//点击列表页目标条目，获取 工商公示信息->登记信息->基本信息
					//获取entId
					//公共参数
					String ent_id=anchor.getAttribute("href");
					if (!StringUtils.isEmpty(ent_id)) {
						ent_id =ent_id.split("=")[1];
					}
					if (!StringUtils.isEmpty(ent_id)) {
					/*HtmlPage gsgsxx_djxx_jbxx = anchor.click();
					Thread.sleep(3000);
					resultHtmlMap.put("gsgsxx_djxx_jbxx", gsgsxx_djxx_jbxx.asXml());
					*/
					String gsgsxx_djxx_jbxx_url = "http://tjcredit.gov.cn/platform/saic/baseInfo.json?entId="+ent_id+"&departmentId=scjgw&infoClassId=dj";
					Page gsgsxx_djxx_jbxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_djxx_jbxx_url)));
					resultHtmlMap.put("gsgsxx_djxx_jbxx", gsgsxx_djxx_jbxx.getWebResponse().getContentAsString("utf-8"));
					
					//点击列表页目标条目，获取 工商公示信息->登记信息->基本信息->股东信息->详情
					Document qygsxxHtml = Jsoup.parseBodyFragment(gsgsxx_djxx_jbxx.getWebResponse().getContentAsString("utf-8"));
					if(qygsxxHtml!=null){
						Element qynbDiv = qygsxxHtml.getElementById("touziren");
						if(qynbDiv!=null){
							Elements qynb_trs = qynbDiv.select("tbody").select("tr").select("td").select("a");
							if(qynb_trs.size()!=0){
								List<String> list=new ArrayList<String>();
								for(int i=0;i<qynb_trs.size();i++){
									//System.out.println(qynb_trs.get(i).toString());
									//System.out.println(qynb_trs.get(i).toString().split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[0]);
									if(qynb_trs.get(i).toString()!=null && qynb_trs.get(i).toString().split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[0]!=null){
									String gsgsxx_djxx_gdxx_detail_url = "http://tjcredit.gov.cn/saicpf/gsgdcz?gdczid="+qynb_trs.get(i).toString().split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[0]+"&entid="+ent_id+"&issaic=1&hasInfo=0";
									Page gsgsxx_djxx_gdxx_detail = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_djxx_gdxx_detail_url)));
									list.add(gsgsxx_djxx_gdxx_detail.getWebResponse().getContentAsString("utf-8"));
									}
								}
								if(list.size()!=0){
									resultHtmlMap.put("gsgsxx_djxx_gdxx", list);
								}
							}
						}
					}
					
					
					//请求获取 工商公示信息->备案信息->主要人员信息 
					String gsgsxx_baxx_zyryxx_url = "http://tjcredit.gov.cn/platform/saic/baseInfo.json?entId="+ent_id+"&departmentId=scjgw&infoClassId=ba";
					Page gsgsxx_baxx_zyryxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_baxx_zyryxx_url)));
					resultHtmlMap.put("gsgsxx_baxx_zyryxx", gsgsxx_baxx_zyryxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 工商公示信息->动产抵押登记信息->动产抵押登记信息
					String gsgsxx_dcdydjxx_dcdydjxx_url = "http://tjcredit.gov.cn/platform/saic/baseInfo.json?entId="+ent_id+"&departmentId=scjgw&infoClassId=dcdydjxx";
					Page gsgsxx_dcdydjxx_dcdydjxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_dcdydjxx_dcdydjxx_url)));
					resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx", gsgsxx_dcdydjxx_dcdydjxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 工商公示信息->股权出质登记信息->股权出质登记信息
					String gsgsxx_gqczdjxx_gqczdjxx_url = "http://tjcredit.gov.cn/platform/saic/baseInfo.json?entId="+ent_id+"&departmentId=scjgw&infoClassId=gqczdjxx";
					Page gsgsxx_gqczdjxx_gqczdjxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_gqczdjxx_gqczdjxx_url)));
					resultHtmlMap.put("gsgsxx_gqczdjxx_gqczdjxx", gsgsxx_gqczdjxx_gqczdjxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 工商公示信息->行政处罚信息->行政处罚信息
					String gsgsxx_xzcfxx_xzcfxx_url = "http://tjcredit.gov.cn/platform/saic/baseInfo.json?entId="+ent_id+"&departmentId=scjgw&infoClassId=xzcf";
					Page gsgsxx_xzcfxx_xzcfxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_xzcfxx_xzcfxx_url)));
					resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx", gsgsxx_xzcfxx_xzcfxx.getWebResponse().getContentAsString("utf-8"));
					
					//行政处罚详情
					Document xzcfxxHtml = Jsoup.parseBodyFragment(gsgsxx_xzcfxx_xzcfxx.getWebResponse().getContentAsString("utf-8"));
					if(xzcfxxHtml!=null){
						Elements qynbDiv = xzcfxxHtml.getElementsByClass("result-table");
						if(qynbDiv!=null && qynbDiv.size()!=0){
							Elements qynb_trs = qynbDiv.get(0).select("tbody").select("tr").select("td").select("a");
							if(qynb_trs.size()!=0){
								List<String> list=new ArrayList<String>();
								for(int i=0;i<qynb_trs.size();i++){
									if(qynb_trs.get(i).toString()!=null && qynb_trs.get(i).toString().split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[0]!=null){
									String gsgsxx_djxx_gdxx_detail_url = "http://tjcredit.gov.cn/saicpf/gsxzcf?id="+qynb_trs.get(i).toString().split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[0]+"&entid="+ent_id+"&issaic=1&hasInfo=0";
									Page gsgsxx_djxx_gdxx_detail = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_djxx_gdxx_detail_url)));
									list.add(gsgsxx_djxx_gdxx_detail.getWebResponse().getContentAsString("utf-8"));
									}
								}
								if(list.size()!=0){
									resultHtmlMap.put("gsgsxx_xzcfxx_detail", list);
								}
							}
						}
					}
					
					
					
					//请求获取 工商公示信息->经营异常信息->经营异常信息
					String gsgsxx_jyycxx_jyycxx_url = "http://tjcredit.gov.cn/platform/saic/baseInfo.json?entId="+ent_id+"&departmentId=scjgw&infoClassId=qyjyycmlxx";
					Page gsgsxx_jyycxx_jyycxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_jyycxx_jyycxx_url)));
					resultHtmlMap.put("gsgsxx_jyycxx_jyycxx", gsgsxx_jyycxx_jyycxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 工商公示信息->严重违法信息->严重违法信息
					String gsgsxx_yzwfxx_yzwfxx_url = "http://tjcredit.gov.cn/platform/saic/baseInfo.json?entId="+ent_id+"&departmentId=scjgw&infoClassId=yzwfqyxx";
					Page gsgsxx_yzwfxx_yzwfxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_yzwfxx_yzwfxx_url)));
					resultHtmlMap.put("gsgsxx_yzwfxx_yzwfxx", gsgsxx_yzwfxx_yzwfxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 工商公示信息->抽查检查信息->抽查检查信息
					String gsgsxx_ccjcxx_ccjcxx_url = "http://tjcredit.gov.cn/platform/saic/baseInfo.json?entId="+ent_id+"&departmentId=scjgw&infoClassId=ccjcxx";
					Page gsgsxx_ccjcxx_ccjcxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_ccjcxx_ccjcxx_url)));
					resultHtmlMap.put("gsgsxx_ccjcxx_ccjcxx", gsgsxx_ccjcxx_ccjcxx.getWebResponse().getContentAsString("utf-8"));
					
					//*****************工商公示信息   结束*****************
					//*****************企业公示信息   开始*****************
					
					//请求获取 企业公示信息->企业年报->列表 
					String qygsxx_qynb_list_url = "http://tjcredit.gov.cn/report/nblist?entid="+ent_id;
					Page qygsxx_qynb_list = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qygsxx_qynb_list_url)));
					resultHtmlMap.put("qygsxx_qynb_list", qygsxx_qynb_list.getWebResponse().getContentAsString("utf-8"));
					//请求获取 企业公示信息->企业年报->列表->详情
					
					//企业详情
					Document qynbHtml = Jsoup.parseBodyFragment(qygsxx_qynb_list.getWebResponse().getContentAsString("utf-8"));
					if(qynbHtml!=null){
						Element qynbDiv = qynbHtml.getElementById("touziren");
						if(qynbDiv!=null){
							Elements qynb_trs = qynbDiv.select("tbody").select("tr").select("td").select("a");
							if(qynb_trs.size()!=0){
								List<String> list=new ArrayList<String>();
								for(int i=0;i<qynb_trs.size();i++){
									//System.out.println(qynb_trs.get(i).toString());
									//System.out.println(qynb_trs.get(i).toString().split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[0]);
									if(qynb_trs.get(i).toString()!=null && qynb_trs.get(i).toString().split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[1]!=null){
									String gsgsxx_djxx_gdxx_detail_url = "http://tjcredit.gov.cn/report/annals?entid="+ent_id+"&year="+qynb_trs.get(i).toString().split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[1]+"&hasInfo=0";
									Page gsgsxx_djxx_gdxx_detail = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_djxx_gdxx_detail_url)));
									System.out.println(gsgsxx_djxx_gdxx_detail.getWebResponse().getContentAsString("utf-8"));
									list.add(gsgsxx_djxx_gdxx_detail.getWebResponse().getContentAsString("utf-8"));
									}
								}
								if(list.size()!=0){
									resultHtmlMap.put("qygsxx_qynb_detail", list);
								}
							}
						}
					}
					
					//请求获取 企业公示信息->行政许可信息
					String qygsxx_xzxkxx_url = "http://tjcredit.gov.cn/report/xzxk?entid="+ent_id;
					Page qygsxx_xzxkxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qygsxx_xzxkxx_url)));
					resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 企业公示信息->股东及出资信息
					String qygsxx_gdjczxx_url = "http://tjcredit.gov.cn/report/gdcz?entid="+ent_id;
					Page qygsxx_gdjczxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qygsxx_gdjczxx_url)));
					resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 企业公示信息->股权变更信息
					String qygsxx_gqbgxx_url = "http://tjcredit.gov.cn/report/gqbg?entid="+ent_id;
					Page qygsxx_gqbgxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qygsxx_gqbgxx_url)));
					resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 企业公示信息->知识产权出质登记信息
					String qygsxx_zscqczdjxx_url = "http://tjcredit.gov.cn/report/zscq?entid="+ent_id;
					Page qygsxx_zscqczdjxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qygsxx_zscqczdjxx_url)));
					resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx.getWebResponse().getContentAsString("utf-8"));
					
					//请求获取 企业公示信息->行政处罚信息
					String qygsxx_xzcfxx_url = "http://tjcredit.gov.cn/report/xzcf?entid="+ent_id;
					Page qygsxx_xzcfxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qygsxx_xzcfxx_url)));
					resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx.getWebResponse().getContentAsString("utf-8"));
					
					
					//*****************企业公示信息   结束*****************
					//*****************司法协助公示信息   开始*****************
					//请求获取 司法协助公示信息->股权冻结信息
					String sfxzgsxx_gqdjxx_list_url = "http://tjcredit.gov.cn/report/gddjlist?entid="+ent_id;
					Page sfxzgsxx_gqdjxx_list = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(sfxzgsxx_gqdjxx_list_url)));
					resultHtmlMap.put("sfxzgsxx_gqdjxx_list", sfxzgsxx_gqdjxx_list.getWebResponse().getContentAsString("utf-8"));
				/*	//请求获取 司法协助公示信息->股权冻结信息->列表->详情
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> anchors4 = (List<HtmlAnchor>) sfxzgsxx_gqdjxx_list_page.getByXPath("//table[@id='touziren']/tbody[@id='table2']/tr/td/a");
					if (anchors4!=null && !anchors4.isEmpty()) {
						List<String> detail=new ArrayList<String>();
						for (@SuppressWarnings("unused") HtmlAnchor anchor4 : anchors4) {
							HtmlPage sfxzgsxx_gqdjxx_detail = anchor4.click();
//							System.out.println("gsgsxx_qynb_detail.asXml()="+gsgsxx_qynb_detail.asXml());
							detail.add(sfxzgsxx_gqdjxx_detail.asXml());
						}
						resultHtmlMap.put("sfxzgsxx_gqdjxx_details",detail);
					}*/
					//请求获取 企业公示信息->股东变更信息
					String qygsxx_gdbgxx_list_url = "http://tjcredit.gov.cn/report/gdbglist?entid="+ent_id;
					Page qygsxx_gdbgxx_list = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qygsxx_gdbgxx_list_url)));
					resultHtmlMap.put("qygsxx_gdbgxx_list", qygsxx_gdbgxx_list.getWebResponse().getContentAsString("utf-8"));
					//请求获取 企业公示信息->股东变更信息->列表->详情
				/*	@SuppressWarnings("unchecked")
					List<HtmlAnchor> anchors5 = (List<HtmlAnchor>) qygsxx_gdbgxx_page.getByXPath("//table[@id='touziren']/tbody[@id='table2']/tr/td/a");
					if (anchors5!=null && !anchors5.isEmpty()) {
						List<String> detail=new ArrayList<String>();
						for (@SuppressWarnings("unused") HtmlAnchor anchor5 : anchors5) {
							HtmlPage qygsxx_gdbgxx_detail = anchor5.click();
//							System.out.println("gsgsxx_qynb_detail.asXml()="+gsgsxx_qynb_detail.asXml());
							detail.add(qygsxx_gdbgxx_detail.asXml());
						}
						resultHtmlMap.put("qygsxx_gdbgxx_details",detail);
					}*/
					
					//*****************司法协助公示信息   结束*****************
					}
					break;//
				}
			}
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
		}
		
		return resultHtmlMap;
	}
	
		//广西数据(dyx)
		private Map<String, Object> getHtmlInfoMapOfGuangxi(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
			Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
			//校验验证码和是否有列表数据
//			System.out.println(firstInfoPage.asXml());
			WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> divByXPath = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']");
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
			if (divByXPath.size() == 0 && firstByXPath==null) {
				DomElement checkcode = firstInfoPage.getElementById("checkNo");
				String val = checkcode.getAttribute("value");
				if (!StringUtils.isEmpty(val)) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
				} else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
				}
			} else {
				if(firstByXPath!=null){
					String textContent = firstByXPath.getTextContent();
					if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					} else {//只有N条数据的时候，有list-a
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
					}
				}else {//只有一条数据的时候，没有list-a
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				}
			}
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='center-1']/div/div[@class='list']/ul/li/a");
			LOGGER.info(anchors.toString());
			if (anchors!=null && !anchors.isEmpty()) {
				boolean matchFlag = false;
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
						matchFlag = true;
						//保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
						resultHtmlMap.put("target_item_info", target_item_info.asXml());
						
						//*****************工商公示信息   开始 *****************
						HtmlPage gsgsxx_djxx = anchor.click();
						@SuppressWarnings("unchecked")
						List<HtmlElement> tabs = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='leftTabs']/ul/li");//获取主页面左侧按钮

						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements1 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']");
						if (htmlElements1!=null && !htmlElements1.isEmpty()) {
							//##############登记信息   开始 ##############
							resultHtmlMap.put("gsgsxx_djxx", htmlElements1.get(0).asXml());
							//##############登记信息   结束 ##############
							
							//股东详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']/div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									HtmlPage gsgsxx_xzcfxx_detail = htmlEle.click();
									gdxxdetail.add(gsgsxx_xzcfxx_detail.asXml());
									
								}
								resultHtmlMap.put("gsgsxx_gdxx_detail",gdxxdetail);
							}
						}
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements22 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='beian']");
						if (htmlElements22!=null && !htmlElements22.isEmpty()) {
							//##############备案信息   开始 ##############
							resultHtmlMap.put("gsgsxx_baxx", htmlElements22.get(0).asXml());
							//##############备案信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements3 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='dongchandiya']");
						if (htmlElements3!=null && !htmlElements3.isEmpty()) {
							//##############动产抵押登记信息   开始 ##############
							resultHtmlMap.put("gsgsxx_dcdydjxx", htmlElements3.get(0).asXml());
							//##############动产抵押登记信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements4 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xingzhengchufa']");
						if (htmlElements4!=null && !htmlElements4.isEmpty()) {
							//##############行政处罚信息   开始 ##############
							resultHtmlMap.put("gsgsxx_xzcfxx", htmlElements4.get(0).asXml());
							//##############行政处罚信息   结束 ##############
							
							//行政处罚详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xingzhengchufa']/div[@id='punDiv']/table[@class='detailsList']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									HtmlPage gsgsxx_xzcfxx_detail = htmlEle.click();
									gdxxdetail.add(gsgsxx_xzcfxx_detail.asXml());
									
								}
								resultHtmlMap.put("gsgsxx_xzcfxx_detail",gdxxdetail);
							}
							
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements5 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jingyingyichangminglu']");
						if (htmlElements5!=null && !htmlElements5.isEmpty()) {
							//##############经营异常信息   开始 ##############
							resultHtmlMap.put("gsgsxx_jyycxx", htmlElements5.get(0).asXml());
							//##############经营异常信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements6 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='chouchaxinxi']");
						if (htmlElements6!=null && !htmlElements6.isEmpty()) {
							//##############抽查检查信息   开始 ##############
							resultHtmlMap.put("gsgsxx_ccjcxx", htmlElements6.get(0).asXml());
							//##############抽查检查信息   结束 ##############
						}
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements7 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='guquanchuzhi']");
						if (htmlElements7!=null && !htmlElements7.isEmpty()) {
							//##############股权出资信息   开始 ##############
							resultHtmlMap.put("gsgsxx_gqczxx", htmlElements7.get(0).asXml());
							//##############股权出资信息   结束 ##############
						}
						
						//*****************工商公示信息   结束*****************
						
						
						//*****************个体工商户公示信息   开始 *****************
						//##############个体工商户年报   开始 ##############
						HtmlPage gtgshgs_gtgshnb =(HtmlPage)tabs.get(1).click();
						resultHtmlMap.put("gtgshgs_gtgshnb", gtgshgs_gtgshnb.asXml());
						
						//年报详情
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gtgshgs_gtgshnb.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
						if (htmlEles!=null && !htmlEles.isEmpty()) {
							List<String> gdxxdetail=new ArrayList<String>();
							for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
								HtmlPage gtgshgs_gtgshnb_detail = htmlEle.click();
								gdxxdetail.add(gtgshgs_gtgshnb_detail.asXml());
								
							}
							resultHtmlMap.put("gtgshgs_gtgshnb_detail",gdxxdetail);
						}
						
						//##############个体工商户年报   结束 ##############
						//*****************个体工商户公示信息   结束*****************
						
						
						//*****************其他部门公示信息   开始 *****************
						String urlString=gtgshgs_gtgshnb.toString();
						String idString=(urlString.split("id=")[1]).split("\\)")[0];
						//##############行政许可信息   开始 ##############
						String qtbmgsxx_xzxkxx_url = "http://gxqyxygs.gov.cn/otherDepartment.jspx?id="+idString;
						HtmlPage qtbmgsxx_xzxkxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qtbmgsxx_xzxkxx_url)));
						resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx.asXml());
						//##############行政许可信息   结束 ##############
						
						//##############行政处罚信息   开始 ##############
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements2 = (List<HtmlElement>)qtbmgsxx_xzxkxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div");
						if (htmlElements2!=null && !htmlElements2.isEmpty()) {
							resultHtmlMap.put("qtbmgsxx_xzcfxx", htmlElements2.get(2).asXml());
						}else{
							resultHtmlMap.put("qtbmgsxx_xzcfxx", null);
						}
						//##############行政处罚信息   结束 ##############
						
						//*****************其他部门公示信息   结束*****************
				
						break;
					}
				}
				if (!matchFlag) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}
			
			return resultHtmlMap;
		}
	
		//河南数据（dyx）
		private Map<String, Object> getHtmlInfoMapOfHenan(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
			Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
			//校验验证码和是否有列表数据
//			System.out.println(firstInfoPage.asXml());
			WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();	
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> divByXPath = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']");
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
			if (divByXPath.size() == 0 && firstByXPath==null) {
				DomElement checkcode = firstInfoPage.getElementById("checkNo");
				String val = checkcode.getAttribute("value");
				if (!StringUtils.isEmpty(val)) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
				} else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
				}
			} else {
				if(firstByXPath!=null){
					String textContent = firstByXPath.getTextContent();
					if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					} else {//只有N条数据的时候，有list-a
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
					}
				}else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				}
			}
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='center-1']/div/div[@class='list']/ul/li/a");
			LOGGER.info(anchors.toString());
			if (anchors!=null && !anchors.isEmpty()) {
				boolean matchFlag = false;
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
						matchFlag = true;
						//保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
						resultHtmlMap.put("target_item_info", target_item_info.asXml());
						
						//*****************工商公示信息   开始 *****************
						HtmlPage gsgsxx_djxx = anchor.click();
						@SuppressWarnings("unchecked")
						List<HtmlElement> tabs = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='leftTabs']/ul/li");//获取主页面左侧按钮

						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements1 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']");
						if (htmlElements1!=null && !htmlElements1.isEmpty()) {
							//##############登记信息   开始 ##############
							resultHtmlMap.put("gsgsxx_djxx", htmlElements1.get(0).asXml());
							//##############登记信息   结束 ##############
							
							//股东详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']/div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									HtmlPage gsgsxx_xzcfxx_detail = htmlEle.click();
									gdxxdetail.add(gsgsxx_xzcfxx_detail.asXml());
									
								}
								resultHtmlMap.put("gsgsxx_gdxx_detail",gdxxdetail);
							}
						}
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements22 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='beian']");
						if (htmlElements22!=null && !htmlElements22.isEmpty()) {
							//##############备案信息   开始 ##############
							resultHtmlMap.put("gsgsxx_baxx", htmlElements22.get(0).asXml());
							//##############备案信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements3 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='dongchandiya']");
						if (htmlElements3!=null && !htmlElements3.isEmpty()) {
							//##############动产抵押登记信息   开始 ##############
							resultHtmlMap.put("gsgsxx_dcdydjxx", htmlElements3.get(0).asXml());
							//##############动产抵押登记信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements4 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xingzhengchufa']");
						if (htmlElements4!=null && !htmlElements4.isEmpty()) {
							//##############行政处罚信息   开始 ##############
							resultHtmlMap.put("gsgsxx_xzcfxx", htmlElements4.get(0).asXml());
							//##############行政处罚信息   结束 ##############
							
							//行政处罚详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xingzhengchufa']/div[@id='punDiv']/table[@class='detailsList']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									HtmlPage gsgsxx_xzcfxx_detail = htmlEle.click();
									gdxxdetail.add(gsgsxx_xzcfxx_detail.asXml());
									
								}
								resultHtmlMap.put("gsgsxx_xzcfxx_detail",gdxxdetail);
							}
							
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements5 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jingyingyichangminglu']");
						if (htmlElements5!=null && !htmlElements5.isEmpty()) {
							//##############经营异常信息   开始 ##############
							resultHtmlMap.put("gsgsxx_jyycxx", htmlElements5.get(0).asXml());
							//##############经营异常信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements6 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='chouchaxinxi']");
						if (htmlElements6!=null && !htmlElements6.isEmpty()) {
							//##############抽查检查信息   开始 ##############
							resultHtmlMap.put("gsgsxx_ccjcxx", htmlElements6.get(0).asXml());
							//##############抽查检查信息   结束 ##############
						}
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements7 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='guquanchuzhi']");
						if (htmlElements7!=null && !htmlElements7.isEmpty()) {
							//##############股权出资信息   开始 ##############
							resultHtmlMap.put("gsgsxx_gqczxx", htmlElements7.get(0).asXml());
							//##############股权出资信息   结束 ##############
						}
						
						
						//*****************工商公示信息   结束*****************
						
						
						//*****************个体工商户公示信息   开始 *****************
						//##############个体工商户年报   开始 ##############
						HtmlPage gtgshgs_gtgshnb =(HtmlPage)tabs.get(1).click();
						resultHtmlMap.put("gtgshgs_gtgshnb", gtgshgs_gtgshnb.asXml());
						
						//年报详情
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gtgshgs_gtgshnb.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
						if (htmlEles!=null && !htmlEles.isEmpty()) {
							List<String> gdxxdetail=new ArrayList<String>();
							for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
								HtmlPage gtgshgs_gtgshnb_detail = htmlEle.click();
								gdxxdetail.add(gtgshgs_gtgshnb_detail.asXml());
								
							}
							resultHtmlMap.put("gtgshgs_gtgshnb_detail",gdxxdetail);
						}
						
						
						
						//股权变更信息
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements20 = (List<HtmlElement>)gtgshgs_gtgshnb.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='gudongguquan']");
						if (htmlElements20!=null && !htmlElements20.isEmpty()) {
							//##############股权变更信息   开始 ##############
							resultHtmlMap.put("qygsxx_gqbgxx", htmlElements20.get(0).asXml());
							//##############股权变更信息   结束 ##############
						}
						//##############个体工商户年报   结束 ##############
						//*****************个体工商户公示信息   结束*****************
						
						
						//*****************其他部门公示信息   开始 *****************
						String urlString=gtgshgs_gtgshnb.toString();
						String idString=(urlString.split("id=")[1]).split("\\)")[0];
						//##############行政许可信息   开始 ##############
						String qtbmgsxx_xzxkxx_url = "http://222.143.24.157/otherDepartment.jspx?id="+idString;
						HtmlPage qtbmgsxx_xzxkxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qtbmgsxx_xzxkxx_url)));
						resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx.asXml());
						//##############行政许可信息   结束 ##############
						
						//##############行政处罚信息   开始 ##############
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements2 = (List<HtmlElement>)qtbmgsxx_xzxkxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div");
						if (htmlElements2!=null && !htmlElements2.isEmpty()) {
							resultHtmlMap.put("qtbmgsxx_xzcfxx", htmlElements2.get(2).asXml());
						}else{
							resultHtmlMap.put("qtbmgsxx_xzcfxx", null);
						}
						//##############行政处罚信息   结束 ##############
						
						//*****************其他部门公示信息   结束*****************
				
						//*****************司法协助公示信息   开始 *****************
						String sfxz_gqdj_url = "http://222.143.24.157/justiceAssistance.jspx?id="+idString;
						HtmlPage sfxz_gqdj = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(sfxz_gqdj_url)));
						if(sfxz_gqdj!=null){
							@SuppressWarnings("unchecked")
							List<HtmlElement> htmlElements40 = (List<HtmlElement>)sfxz_gqdj.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='EquityFreeze']");
							if (htmlElements40!=null && !htmlElements40.isEmpty()) {
								//##############股权冻结信息   开始 ##############
								resultHtmlMap.put("sfxz_gqdj", htmlElements40.get(0).asXml());
								//##############股权冻结信息   结束 ##############
							}
						}
						
						//*****************司法协助公示信息    结束 *****************
						break;
					}
				}
				if (!matchFlag) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}
			
			return resultHtmlMap;
		}
		//内蒙古数据（dyx）
		private Map<String, Object> getHtmlInfoMapOfNeimenggu(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
			Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
			//校验验证码和是否有列表数据
//			System.out.println(firstInfoPage.asXml());
			WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();	
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> divByXPath = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']");
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//dt"));
			if (divByXPath.size() == 0 && firstByXPath==null) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				if(firstByXPath!=null){
					String textContent = firstByXPath.getTextContent();
					if (textContent.equals("暂未查询到相关记录。")) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					} else {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
					}
				}else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				}
			}
			
			@SuppressWarnings("unchecked")
			List<HtmlElement> anchors = (List<HtmlElement>) firstInfoPage.getByXPath("//div[@class='da']/div[@class='center-1']/div[@class='list']/ul/li/a");
			LOGGER.info(anchors.toString());
			if (anchors!=null && !anchors.isEmpty()) {
				boolean matchFlag = false;
				for (HtmlElement anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
						matchFlag = true;
						//保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
						resultHtmlMap.put("target_item_info", target_item_info.asXml());
						//*****************工商公示信息   开始 *****************
						
						//##############登记信息   开始 ##############
						HtmlPage gsgsxx_djxx = anchor.click();
						System.out.println(gsgsxx_djxx.asXml());
						//公共参数
						HtmlHiddenInput entNo = (HtmlHiddenInput) gsgsxx_djxx.getElementById("entNo");
						HtmlHiddenInput entType = (HtmlHiddenInput) gsgsxx_djxx.getElementById("entType");
						HtmlHiddenInput regOrg = (HtmlHiddenInput) gsgsxx_djxx.getElementById("regOrg");
						List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						nameValuePairs.add(new NameValuePair("entNo", entNo.getDefaultValue()));
						nameValuePairs.add(new NameValuePair("entType", entType.getDefaultValue()));
						nameValuePairs.add(new NameValuePair("regOrg", regOrg.getDefaultValue()));
						
						//********基本信息 开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> djxxtab1 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']/table[@id='baseinfo']");
						if(djxxtab1!=null && !djxxtab1.isEmpty()){
							resultHtmlMap.put("gsgsxx_jbxx", djxxtab1.get(0).asXml());
						}
						//********基本信息 结束
						//********股东信息 开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> djxxtab2 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']/div[@id='invInfo']");
						if(djxxtab2!=null && !djxxtab2.isEmpty()){
							resultHtmlMap.put("gsgsxx_gdxx", djxxtab2.get(0).asXml());
							
							//股东信息详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']/div[@id='invInfo']/table[@id='touziren']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail2=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									String urlString=htmlEle.asXml();
									urlString=urlString.replace("&amp;","&");
//									System.err.println(urlString.split("\\(\\'")[1].split("\\'\\)")[0]);
									urlString=(urlString.split("\\(\\'")[1].split("\\'\\)")[0]);
									//该公司的股东及出资信息在2014年3月1日后发生变化的,股东详情企业自主公示
									if(!urlString.contains("股东详情企业自主公示")){
										WebRequest bawebRequest = new WebRequest(new URL(urlString), HttpMethod.POST);
										bawebRequest.setRequestParameters(nameValuePairs);
										bawebRequest.setCharset("utf-8");
										HtmlPage gsgsxx_gdxx_detail = firstInfoPage.getWebClient().getPage(window,bawebRequest); 										 							
//										System.out.println(gsgsxx_baxx.asXml());
									/*	HtmlPage gsgsxx_gdxx_detail = htmlEle.click();
										System.out.println(gsgsxx_gdxx_detail.asXml());*/
										gdxxdetail2.add(gsgsxx_gdxx_detail.asXml());
									}
									
								}
								if(gdxxdetail2.size()!=0){
									resultHtmlMap.put("gsgsxx_gdxx_detail",gdxxdetail2);
								}
							
							}
						}
						//********股东信息 结束
						//********变更信息 开始
					/*	@SuppressWarnings("unchecked")
						List<HtmlElement> djxxtab3 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']/div[@id='biangeng']");
						if(djxxtab3!=null && !djxxtab3.isEmpty()){
							resultHtmlMap.put("gsgsxx_bgxx", djxxtab3.get(0).asXml());
							
							//详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> djxxtab31 = (List<HtmlAnchor>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']/div[@id='biangeng']/table/tbody/tr/td/a");
							if (djxxtab31!=null && !djxxtab31.isEmpty()) {
								List<String> gdxxdetail2=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : djxxtab31) {
									String urlString=htmlEle.asXml();
									urlString=urlString.replace("&amp;","&");
									urlString=(urlString.split("\\(\\'")[1].split("\\'\\)")[0]);
										WebRequest bawebRequest = new WebRequest(new URL(urlString), HttpMethod.POST);
										bawebRequest.setRequestParameters(nameValuePairs);
										bawebRequest.setCharset("utf-8");
										HtmlPage gsgsxx_gdxx_detail = firstInfoPage.getWebClient().getPage(window,bawebRequest); 	
										gdxxdetail2.add(gsgsxx_gdxx_detail.asXml());
								}
								if(gdxxdetail2.size()!=0){
									resultHtmlMap.put("gsgsxx_bgxx_detail",gdxxdetail2);
								}
							
							}
						}*/
						
						//基本信息-变更信息
						String bgurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/entChaPage";
						WebRequest bgRequest = new WebRequest(new URL(bgurl), HttpMethod.POST);
						List<NameValuePair> nameValuePairs3 = new ArrayList<NameValuePair>();
						nameValuePairs3.add(new NameValuePair("entNo", entNo.getDefaultValue()));
						nameValuePairs3.add(new NameValuePair("entType", entType.getDefaultValue()));
						nameValuePairs3.add(new NameValuePair("regOrg", regOrg.getDefaultValue()));
						nameValuePairs3.add(new NameValuePair("pageNo", "1"));
						bgRequest.setRequestParameters(nameValuePairs3);
						bgRequest.setCharset("utf-8");
						Page gsgsxx_bgxx = firstInfoPage.getWebClient().getPage(window,bgRequest); 										 							
//						System.out.println(gsgsxx_bgxx.getWebResponse().getContentAsString("utf-8"));
						if(gsgsxx_bgxx.getWebResponse().getContentAsString("utf-8")!=null){
							resultHtmlMap.put("gsgsxx_jbbgxx", gsgsxx_bgxx.getWebResponse().getContentAsString("utf-8"));
						}
						
						//********变更信息 结束
						
					
						//##############登记信息   结束 ##############
						
						//##############备案信息   开始 ##############
						String baurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/GSpublicityList.html?service=entCheckInfo";
						WebRequest bawebRequest = new WebRequest(new URL(baurl), HttpMethod.POST);
						bawebRequest.setRequestParameters(nameValuePairs);
						bawebRequest.setCharset("utf-8");
						HtmlPage gsgsxx_baxx = firstInfoPage.getWebClient().getPage(window,bawebRequest); 										 							
						System.out.println(gsgsxx_baxx.asXml());
						
						//********主要人员信息 开始
					/*	@SuppressWarnings("unchecked")
						List<HtmlElement> baxxtab1 = (List<HtmlElement>)gsgsxx_baxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='beian']/div[@id='zyry']");
						if(baxxtab1!=null && !baxxtab1.isEmpty()){
							resultHtmlMap.put("gsgsxx_zyryxx", baxxtab1.get(0).asXml());
						}*/
						
						String zyryxxurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/vipInfoPage";
						WebRequest zyryxxRequest = new WebRequest(new URL(zyryxxurl), HttpMethod.POST);
						nameValuePairs.add(new NameValuePair("pageNo", "1"));
						zyryxxRequest.setRequestParameters(nameValuePairs);
						zyryxxRequest.setCharset("utf-8");
						Page gsgsxx_zyryxx2 = firstInfoPage.getWebClient().getPage(window,zyryxxRequest); 
						System.out.println(gsgsxx_zyryxx2.getWebResponse().getContentAsString("utf-8"));
						resultHtmlMap.put("gsgsxx_zyryxx",gsgsxx_zyryxx2.getWebResponse().getContentAsString("utf-8"));
						
						//********主要人员信息 结束
						//********分支机构信息 开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> baxxtab2 = (List<HtmlElement>)gsgsxx_baxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='beian']/div[@id='branch']");
						if(baxxtab2!=null && !baxxtab2.isEmpty()){
							resultHtmlMap.put("gsgsxx_fzjgxx", baxxtab2.get(0).asXml());
						}
						//********分支机构信息 结束
						//********清算信息 开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> baxxtab3 = (List<HtmlElement>)gsgsxx_baxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='beian']/table[@class='detailsList']");
						if(baxxtab3.size()==3){
							resultHtmlMap.put("gsgsxx_qsxx", baxxtab3.get(2).asXml());
						}
						//********清算信息 结束
						
						//##############备案信息   结束 ##############
						
						
						//##############动产抵押登记信息   开始 ##############
						String dcdydjurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/GSpublicityList.html?service=pleInfo";
						WebRequest dcdydjRequest = new WebRequest(new URL(dcdydjurl), HttpMethod.POST);
						dcdydjRequest.setRequestParameters(nameValuePairs);
						dcdydjRequest.setCharset("utf-8");
						HtmlPage gsgsxx_dcdydjxx = firstInfoPage.getWebClient().getPage(window,dcdydjRequest); 										 							
//						System.out.println(gsgsxx_dcdydjxx.asXml());
						
						//********动产抵押登记信息 开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> dcdydjxxtab1 = (List<HtmlElement>)gsgsxx_dcdydjxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='dongchandiya']/table[@class='detailsList']");
						if(dcdydjxxtab1!=null && !dcdydjxxtab1.isEmpty()){
							resultHtmlMap.put("gsgsxx_dcdydjxx", dcdydjxxtab1.get(0).asXml());
							
							//动产抵押登记详情
							@SuppressWarnings("unchecked")
						List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gsgsxx_dcdydjxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='dongchandiya']/table[@class='detailsList']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									String urlString=htmlEle.asXml();
//									System.err.println(urlString);
//									System.err.println(urlString.split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[0]);
//									System.err.println(urlString.split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[1]);
										String dcdydjurl2 = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/GSpublicityList.html?service=pleInfoData";
										WebRequest bawebRequest2 = new WebRequest(new URL(dcdydjurl2), HttpMethod.POST);
										List<NameValuePair> nameValuePairs2 = new ArrayList<NameValuePair>();
										nameValuePairs2.add(new NameValuePair("entNo", entNo.getDefaultValue()));
										nameValuePairs2.add(new NameValuePair("entType", entType.getDefaultValue()));
										nameValuePairs2.add(new NameValuePair("regOrg", regOrg.getDefaultValue()));
										nameValuePairs2.add(new NameValuePair("pledgeid", urlString.split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[0]));
										nameValuePairs2.add(new NameValuePair("type", urlString.split("\\(\\'")[1].split("\\'\\)")[0].split("\\'\\,\\'")[1]));
										bawebRequest2.setRequestParameters(nameValuePairs2);
										bawebRequest2.setCharset("utf-8");
										HtmlPage gsgsxx_gdxx_detail = firstInfoPage.getWebClient().getPage(window,bawebRequest2); 
										System.out.println(gsgsxx_gdxx_detail.asXml());
										gdxxdetail.add(gsgsxx_gdxx_detail.asXml());
									
								}
								resultHtmlMap.put("gsgsxx_dcdydjxx_detail",gdxxdetail);
							}
							
						}
						//********动产抵押登记信息 结束
						
						//##############动产抵押登记信息   结束 ##############
						
						
						//##############股权出资登记信息   开始 ##############
						String gqczdjurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/GSpublicityList.html?service=curStoPleInfo";
						WebRequest gqczdjRequest = new WebRequest(new URL(gqczdjurl), HttpMethod.POST);
						gqczdjRequest.setRequestParameters(nameValuePairs);
						gqczdjRequest.setCharset("utf-8");
						HtmlPage gsgsxx_gqczdjxx = firstInfoPage.getWebClient().getPage(window,gqczdjRequest); 										 							
//						System.out.println(gsgsxx_dcdydjxx.asXml());
						
						//********股权出资登记信息 开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> gqczdjxxtab1 = (List<HtmlElement>)gsgsxx_gqczdjxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='guquanchuzhi']/div/table[@class='detailsList']");
						if(gqczdjxxtab1!=null && !gqczdjxxtab1.isEmpty()){
							resultHtmlMap.put("gsgsxx_gqczdjxx", gqczdjxxtab1.get(0).asXml());
						}
						//********股权出资登记信息 结束
						//##############股权出资登记信息   结束 ##############
						
						//##############行政处罚信息  开始 ##############
						String xzcfurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/GSpublicityList.html?service=cipPenaltyInfo";
						WebRequest xzcfRequest = new WebRequest(new URL(xzcfurl), HttpMethod.POST);
						xzcfRequest.setRequestParameters(nameValuePairs);
						xzcfRequest.setCharset("utf-8");
						HtmlPage gsgsxx_xzcfxx = firstInfoPage.getWebClient().getPage(window,xzcfRequest); 										 							
//						System.out.println(gsgsxx_dcdydjxx.asXml());
						
						//********行政处罚信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzcftab1 = (List<HtmlElement>)gsgsxx_xzcfxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xingzhengchufa']/table[@class='detailsList']");
						if(xzcftab1!=null && !xzcftab1.isEmpty()){
							resultHtmlMap.put("gsgsxx_xzcfxx", xzcftab1.get(0).asXml());
						}
						//********行政处罚信息结束
						//##############行政处罚信息  结束 ##############
						
						//##############经营异常信息  开始 ##############
						String jyycurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/GSpublicityList.html?service=cipUnuDirInfo";
						WebRequest jyycRequest = new WebRequest(new URL(jyycurl), HttpMethod.POST);
						jyycRequest.setRequestParameters(nameValuePairs);
						jyycRequest.setCharset("utf-8");
						HtmlPage gsgsxx_jyycxx = firstInfoPage.getWebClient().getPage(window,jyycRequest); 										 							
//						System.out.println(gsgsxx_dcdydjxx.asXml());
						
						//********经营异常信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> jyyctab1 = (List<HtmlElement>)gsgsxx_jyycxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='yichangminglu']/table[@class='detailsList']");
						if(jyyctab1!=null && !jyyctab1.isEmpty()){
							resultHtmlMap.put("gsgsxx_jyycxx", jyyctab1.get(0).asXml());
						}
						//********经营异常信息结束
						
						//##############经营异常信息  结束 ##############
						
						
						//##############严重违法信息  开始 ##############
						String yzwfurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/GSpublicityList.html?service=cipBlackInfo";
						WebRequest yzwfRequest = new WebRequest(new URL(yzwfurl), HttpMethod.POST);
						yzwfRequest.setRequestParameters(nameValuePairs);
						yzwfRequest.setCharset("utf-8");
						HtmlPage gsgsxx_yzwfxx = firstInfoPage.getWebClient().getPage(window,yzwfRequest); 										 							
//						System.out.println(gsgsxx_dcdydjxx.asXml());
						
						//********严重违法信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> yzwftab1 = (List<HtmlElement>)gsgsxx_yzwfxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='heimingdan']/table[@class='detailsList']");
						if(yzwftab1!=null && !yzwftab1.isEmpty()){
							resultHtmlMap.put("gsgsxx_yzwfxx", yzwftab1.get(0).asXml());
						}
						//********严重违法信息结束
						
						
						//##############严重违法信息  结束 ##############
						
						//##############抽查检查信息  开始 ##############
						String ccjcfurl = "http://www.nmgs.gov.cn:7001/aiccips/GSpublicity/GSpublicityList.html?service=cipSpotCheInfo";
						WebRequest ccjcRequest = new WebRequest(new URL(ccjcfurl), HttpMethod.POST);
						ccjcRequest.setRequestParameters(nameValuePairs);
						ccjcRequest.setCharset("utf-8");
						HtmlPage gsgsxx_ccjcxx = firstInfoPage.getWebClient().getPage(window,ccjcRequest); 										 							
//						System.out.println(gsgsxx_dcdydjxx.asXml());
						
						//********抽查检查信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> ccjctab1 = (List<HtmlElement>)gsgsxx_ccjcxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='chouchajiancha']/table[@class='detailsList']");
						if(ccjctab1!=null && !ccjctab1.isEmpty()){
							resultHtmlMap.put("gsgsxx_ccjcxx", ccjctab1.get(0).asXml());
						}
						//********抽查检查信息结束
						
					
						//##############抽查检查信息  结束 ##############
						
						//*****************工商公示信息   结束*****************
						
						
						//*****************企业公示信息   开始 *****************
						//##############企业年报  开始 ##############
						String qynburl = "http://www.nmgs.gov.cn:7001/aiccips/BusinessAnnals/BusinessAnnalsList.html";
						WebRequest qynbRequest = new WebRequest(new URL(qynburl), HttpMethod.POST);
						qynbRequest.setRequestParameters(nameValuePairs);
						qynbRequest.setCharset("utf-8");
						HtmlPage qygsxx_qynb = firstInfoPage.getWebClient().getPage(window,qynbRequest); 										 							
//						System.out.println(gsgsxx_qynb.asXml());
						//********企业年报开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> qynbtab1 = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='qiyenianbao']/table[@class='detailsList']");
						if(qynbtab1!=null && !qynbtab1.isEmpty()){
							resultHtmlMap.put("qygsxx_qynb", qynbtab1.get(0).asXml());
							
							//年报详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)qygsxx_qynb.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									HtmlPage gtgshgs_gtgshnb_detail = htmlEle.click();
//									System.out.println(gtgshgs_gtgshnb_detail.asXml());
									gdxxdetail.add(gtgshgs_gtgshnb_detail.asXml());
									
								}
								resultHtmlMap.put("qygsxx_qynb_detail",gdxxdetail);
							}
							
						}
						//********企业年报结束
						//##############企业年报  结束 ##############
						
						
						//##############股东及出资信息  开始 ##############
						String gdjczurl = "http://www.nmgs.gov.cn:7001/aiccips/ContributionCapitalMsg.html";
						WebRequest gdjczRequest = new WebRequest(new URL(gdjczurl), HttpMethod.POST);
						gdjczRequest.setRequestParameters(nameValuePairs);
						gdjczRequest.setCharset("utf-8");
						HtmlPage qygsxx_gdjczxx = firstInfoPage.getWebClient().getPage(window,gdjczRequest); 										 							
//						System.out.println(gsgsxx_qynb.asXml());
						
						//********股东及出资信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> gdjcztab1 = (List<HtmlElement>)qygsxx_gdjczxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='sifapanding']/table[@class='detailsList']");
						if(gdjcztab1!=null && !gdjcztab1.isEmpty()){
							resultHtmlMap.put("qygsxx_gdjczxx", gdjcztab1.get(0).asXml());
						}
						//********股东及出资信息结束

						//********变更信息开始
						if(gdjcztab1!=null && !gdjcztab1.isEmpty() && gdjcztab1.size()==2){
							resultHtmlMap.put("qygsxx_bgxx", gdjcztab1.get(1).asXml());
						}
						
						//********变更信息结束
						
						//##############股东及出资信息  结束 ##############
						
						//##############股权变更  开始 ##############
						String gqbgurl = "http://www.nmgs.gov.cn:7001/aiccips/GDGQTransferMsg/shareholderTransferMsg.html";
						WebRequest gqbgRequest = new WebRequest(new URL(gqbgurl), HttpMethod.POST);
						gqbgRequest.setRequestParameters(nameValuePairs);
						gqbgRequest.setCharset("utf-8");
						HtmlPage qygsxx_gqbgxx = firstInfoPage.getWebClient().getPage(window,gqbgRequest); 										 							
//						System.out.println(gsgsxx_qynb.asXml());
						
						//********股权变更开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> gqbgtab1 = (List<HtmlElement>)qygsxx_gqbgxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='sifapanding']/table[@class='detailsList']");
						if(gqbgtab1!=null && !gqbgtab1.isEmpty()){
							resultHtmlMap.put("qygsxx_gqbgxx", gqbgtab1.get(0).asXml());
						}
						//********股权变更结束
						//##############股权变更  结束 ##############
						
						//##############行政许可  开始 ##############
						String xzxkurl = "http://www.nmgs.gov.cn:7001/aiccips/AppPerInformation.html";
						WebRequest xzxkRequest = new WebRequest(new URL(xzxkurl), HttpMethod.POST);
						xzxkRequest.setRequestParameters(nameValuePairs);
						xzxkRequest.setCharset("utf-8");
						HtmlPage qygsxx_xzxkxx = firstInfoPage.getWebClient().getPage(window,xzxkRequest); 										 							
//						System.out.println(gsgsxx_qynb.asXml());
						//********行政许可开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzxktab1 = (List<HtmlElement>)qygsxx_xzxkxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='appPer']/table[@class='detailsList']");
						if(xzxktab1!=null && !xzxktab1.isEmpty()){
							resultHtmlMap.put("qygsxx_xzxkxx", xzxktab1.get(0).asXml());
						}
						//********行政许可结束
						//##############行政许可  结束 ##############
						
						//##############知识产权出资登记信息  开始 ##############
						String zscqczdjurl = "http://www.nmgs.gov.cn:7001/aiccips/intPropertyMsg.html";
						WebRequest zscqczdjRequest = new WebRequest(new URL(zscqczdjurl), HttpMethod.POST);
						zscqczdjRequest.setRequestParameters(nameValuePairs);
						zscqczdjRequest.setCharset("utf-8");
						HtmlPage qygsxx_zscqczdj = firstInfoPage.getWebClient().getPage(window,zscqczdjRequest); 										 							
//						System.out.println(gsgsxx_qynb.asXml());
						
						//********知识产权出资登记信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> zscqczdjtab1 = (List<HtmlElement>)qygsxx_zscqczdj.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='inproper']/table[@class='detailsList']");
						if(zscqczdjtab1!=null && !zscqczdjtab1.isEmpty()){
							resultHtmlMap.put("qygsxx_zscqczdj", zscqczdjtab1.get(0).asXml());
						}
						//********知识产权出资登记信息结束
						
						//##############知识产权出资登记信息  结束 ##############
						
						//##############行政处罚信息  开始 ##############
						String xzcfurl2 = "http://www.nmgs.gov.cn:7001/aiccips/XZPunishmentMsg.html";
						WebRequest xzcfRequest2 = new WebRequest(new URL(xzcfurl2), HttpMethod.POST);
						xzcfRequest2.setRequestParameters(nameValuePairs);
						xzcfRequest2.setCharset("utf-8");
						HtmlPage qygsxx_xzcf = firstInfoPage.getWebClient().getPage(window,xzcfRequest2); 										 							
//						System.out.println(gsgsxx_qynb.asXml());
						
						//********行政处罚信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzcftab2 = (List<HtmlElement>)qygsxx_xzcf.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xzpun']/table[@class='detailsList']");
						if(xzcftab2!=null && !xzcftab2.isEmpty()){
							resultHtmlMap.put("qygsxx_xzcf", xzcftab2.get(0).asXml());
						}
						//********行政处罚信息结束
						//##############行政处罚信息  结束 ##############
						//*****************企业公示信息   结束 *****************
						
				
						
						//*****************其他部门公示信息   开始 *****************
						//##############行政许可信息  开始 ##############
						String xzxkurl2 = "http://www.nmgs.gov.cn:7001/aiccips/OtherPublicity/otherDeptInfo.html";
						WebRequest xzxkRequest2 = new WebRequest(new URL(xzxkurl2), HttpMethod.POST);
						xzxkRequest2.setRequestParameters(nameValuePairs);
						xzxkRequest2.setCharset("utf-8");
						HtmlPage qtbmgsxx_xzxk = firstInfoPage.getWebClient().getPage(window,xzxkRequest2); 										 							
//						System.out.println(gsgsxx_qynb.asXml());
						//********行政许可信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzxktab2 = (List<HtmlElement>)qtbmgsxx_xzxk.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='czcf']/table[@class='detailsList']");
						if(xzxktab2!=null && !xzxktab2.isEmpty()){
							resultHtmlMap.put("qtbmgsxx_xzxk", xzxktab2.get(0).asXml());
						}
						//********行政许可信息结束
						//##############行政许可信息  结束 ##############
					
						
						//##############行政处罚信息  开始 ##############
						//********行政处罚信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzcftab = (List<HtmlElement>)qtbmgsxx_xzxk.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xzxk']/table[@class='detailsList']");
						if(xzcftab!=null && !xzcftab.isEmpty()){
							resultHtmlMap.put("qtbmgsxx_xzcf", xzcftab.get(0).asXml());
						}
						//********行政处罚信息结束
						//##############行政处罚信息  结束 ##############
						//*****************其他部门公示信息   结束*****************
						
						
						//*****************司法协助公示信息   开始 *****************
						String gqdjurl = "http://www.nmgs.gov.cn:7001/aiccips/OtherPublicity/highCourt.html";
						WebRequest gqdjRequest = new WebRequest(new URL(gqdjurl), HttpMethod.POST);
						gqdjRequest.setRequestParameters(nameValuePairs);
						gqdjRequest.setCharset("utf-8");
						HtmlPage sfxzgsxx_gqdj = firstInfoPage.getWebClient().getPage(window,gqdjRequest); 										 							
//						System.out.println(gsgsxx_qynb.asXml());
						
						//##############股权冻结  开始 ##############
						//********股权冻结开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> gqdjtab = (List<HtmlElement>)sfxzgsxx_gqdj.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='czcf']/table[@class='detailsList']");
						if(gqdjtab!=null && !gqdjtab.isEmpty()){
							resultHtmlMap.put("sfxzgsxx_gqdj", gqdjtab.get(0).asXml());
						}
						//********股权冻结结束
						//##############股权冻结  结束 ##############
						
						//##############股权变更  开始 ##############
						
						//********司法股东变革登记开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> sfgdbgdjtab = (List<HtmlElement>)sfxzgsxx_gqdj.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xzxk']/table[@class='detailsList']");
						if(sfgdbgdjtab!=null && !sfgdbgdjtab.isEmpty()){
							resultHtmlMap.put("sfxzgsxx_sfgdbgdj", sfgdbgdjtab.get(0).asXml());
						}
						//********司法股东变革登记结束
						//##############股权变更  结束 ##############
						//*****************司法协助公示信息   结束*****************
						
						
						break;
					}
				}
				if (!matchFlag) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}
			
			return resultHtmlMap;
		}
		
		//湖北数据（dyx）
		private Map<String, Object> getHtmlInfoMapOfHubei(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
			Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
			//校验验证码和是否有列表数据
//			System.out.println(firstInfoPage.asXml());
			WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> divByXPath = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']");
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
			if (divByXPath.size() == 0 && firstByXPath==null) {
				DomElement checkcode = firstInfoPage.getElementById("checkNo");
				String val = checkcode.getAttribute("value");
				if (!StringUtils.isEmpty(val)) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
				} else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
				}
			} else {
				if(firstByXPath!=null){
					String textContent = firstByXPath.getTextContent();
					if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					} else {//只有N条数据的时候，有list-a
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
					}
				}else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				}
			}
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='center-1']/div/div[@class='list']/ul/li/a");
			LOGGER.info(anchors.toString());
			if (anchors!=null && !anchors.isEmpty()) {
				boolean matchFlag = false;
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
						matchFlag = true;
						//保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
						resultHtmlMap.put("target_item_info", target_item_info.asXml());
						
						//*****************工商公示信息   开始 *****************
						
						//变更信息url
						String ent_id=anchor.getAttribute("href");
						if (!StringUtils.isEmpty(ent_id)) {
							ent_id =ent_id.split("=")[1];
							if (!StringUtils.isEmpty(ent_id)) {
								List<String> detail=new ArrayList<String>();
								String bgxx_url = "http://xyjg.egs.gov.cn/ECPS_HB/QueryAltList.jspx?pno=1&mainId="+ent_id;
								String bgxx_url2 = "http://xyjg.egs.gov.cn/ECPS_HB/QueryAltList.jspx?pno=2&mainId="+ent_id;
								Page bgxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(bgxx_url)));
								Page bgxx2 = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(bgxx_url2)));
								detail.add(bgxx.getWebResponse().getContentAsString("utf-8"));
								detail.add(bgxx2.getWebResponse().getContentAsString("utf-8"));
								resultHtmlMap.put("gsgsxx_bgxx", detail);
							}
						}
						
						HtmlPage gsgsxx_djxx = anchor.click();
						@SuppressWarnings("unchecked")
						List<HtmlElement> tabs = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='leftTabs']/ul/li");//获取主页面左侧按钮

						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements1 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']");
						if (htmlElements1!=null && !htmlElements1.isEmpty()) {
							//##############登记信息   开始 ##############
							resultHtmlMap.put("gsgsxx_djxx", htmlElements1.get(0).asXml());
							//##############登记信息   结束 ##############
							
							//股东详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jibenxinxi']/div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									HtmlPage gsgsxx_xzcfxx_detail = htmlEle.click();
									gdxxdetail.add(gsgsxx_xzcfxx_detail.asXml());
									
								}
								resultHtmlMap.put("gsgsxx_gdxx_detail",gdxxdetail);
							}
						}
						
						//http://xyjg.egs.gov.cn/ECPS_HB/QueryMemList.jspx?pno=1&mainId=913B82E8D1EA323661CC17F9D5D14933
							if (!StringUtils.isEmpty(ent_id)) {
								List<String> detail=new ArrayList<String>();
								String bgxx_url1 = "http://xyjg.egs.gov.cn/ECPS_HB/QueryMemList.jspx?pno=1&mainId="+ent_id;
								String detail1=firstInfoPage.getWebClient().getPage(bgxx_url1).getWebResponse().getContentAsString();
								if(!detail1.contains("class=detailsList></table>")){
									String bgxx_url2 = "http://xyjg.egs.gov.cn/ECPS_HB/QueryMemList.jspx?pno=2&mainId="+ent_id;
									String detail2=firstInfoPage.getWebClient().getPage(bgxx_url2).getWebResponse().getContentAsString();
									String matchString = detail2.split("width=20%")[1].split("width=20%")[0];
									if(!detail1.contains(matchString)){
										detail.add(detail1);
										detail.add(detail2);
									}else{
										detail.add(detail1);
									}
								}
								if(detail.size()!=0){
									resultHtmlMap.put("gsgsxx_zyryxx", detail);
								}
							}
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements22 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='beian']");
						if (htmlElements22!=null && !htmlElements22.isEmpty()) {
							//##############备案信息   开始 ##############
							resultHtmlMap.put("gsgsxx_baxx", htmlElements22.get(0).asXml());
							//##############备案信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements3 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='dongchandiya']");
						if (htmlElements3!=null && !htmlElements3.isEmpty()) {
							//##############动产抵押登记信息   开始 ##############
							resultHtmlMap.put("gsgsxx_dcdydjxx", htmlElements3.get(0).asXml());
							//##############动产抵押登记信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements4 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xingzhengchufa']");
						if (htmlElements4!=null && !htmlElements4.isEmpty()) {
							//##############行政处罚信息   开始 ##############
							resultHtmlMap.put("gsgsxx_xzcfxx", htmlElements4.get(0).asXml());
							//##############行政处罚信息   结束 ##############
							
							//行政处罚详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='xingzhengchufa']/div[@id='punDiv']/table[@class='detailsList']/tbody/tr/td/a");
							if (htmlEles!=null && !htmlEles.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
									HtmlPage gsgsxx_xzcfxx_detail = htmlEle.click();
									gdxxdetail.add(gsgsxx_xzcfxx_detail.asXml());
									
								}
								resultHtmlMap.put("gsgsxx_xzcfxx_detail",gdxxdetail);
							}
							
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements5 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='jingyingyichangminglu']");
						if (htmlElements5!=null && !htmlElements5.isEmpty()) {
							//##############经营异常信息   开始 ##############
							resultHtmlMap.put("gsgsxx_jyycxx", htmlElements5.get(0).asXml());
							//##############经营异常信息   结束 ##############
						}
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements6 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='chouchaxinxi']");
						if (htmlElements6!=null && !htmlElements6.isEmpty()) {
							//##############抽查检查信息   开始 ##############
							resultHtmlMap.put("gsgsxx_ccjcxx", htmlElements6.get(0).asXml());
							//##############抽查检查信息   结束 ##############
						}
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements7 = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='guquanchuzhi']");
						if (htmlElements7!=null && !htmlElements7.isEmpty()) {
							//##############股权出资信息   开始 ##############
							resultHtmlMap.put("gsgsxx_gqczxx", htmlElements7.get(0).asXml());
							//##############股权出资信息   结束 ##############
						}
						
						
						
						//*****************工商公示信息   结束*****************
						
						
						//*****************个体工商户公示信息   开始 *****************
						//##############个体工商户年报   开始 ##############
//						HtmlPage gtgshgs_gtgshnb =(HtmlPage)tabs.get(1).click();
						String gtgshgs_gtgshnb_url = "http://xyjg.egs.gov.cn/ECPS_HB/enterprisePublicity.jspx?id="+ent_id;
						HtmlPage gtgshgs_gtgshnb = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gtgshgs_gtgshnb_url)));
						resultHtmlMap.put("gtgshgs_gtgshnb", gtgshgs_gtgshnb.asXml());
						
						//年报详情
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)gtgshgs_gtgshnb.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
						if (htmlEles!=null && !htmlEles.isEmpty()) {
							List<String> gdxxdetail=new ArrayList<String>();
							for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
								HtmlPage gtgshgs_gtgshnb_detail = htmlEle.click();
								gdxxdetail.add(gtgshgs_gtgshnb_detail.asXml());
								
							}
							resultHtmlMap.put("gtgshgs_gtgshnb_detail",gdxxdetail);
						}
						
						//##############个体工商户年报   结束 ##############
						
						//##############股东及出资信息  开始 ##############
						
						//********股东及出资信息开始
						@SuppressWarnings("unchecked")
						List<HtmlElement> gdjczxxElements = (List<HtmlElement>)gtgshgs_gtgshnb.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div[@id='touziren']");
						if (gdjczxxElements!=null && !gdjczxxElements.isEmpty()) {
						resultHtmlMap.put("qygsxx_gdjczxx", gdjczxxElements.get(0).asXml());
						}
						//********股东及出资信息结束
						
						//##############股东及出资信息  结束 ##############
						
						
						
						//*****************个体工商户公示信息   结束*****************
						
						
						//*****************其他部门公示信息   开始 *****************
						//##############行政许可信息   开始 ##############
						String qtbmgsxx_xzxkxx_url = "http://xyjg.egs.gov.cn/ECPS_HB/otherDepartment.jspx?id="+ent_id;
						HtmlPage qtbmgsxx_xzxkxx = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(qtbmgsxx_xzxkxx_url)));
						resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx.asXml());
						//##############行政许可信息   结束 ##############
						
						//##############行政处罚信息   开始 ##############
						@SuppressWarnings("unchecked")
						List<HtmlElement> htmlElements2 = (List<HtmlElement>)qtbmgsxx_xzxkxx.getByXPath("//div[@id='details']/div[@id='detailsCon']/div[@class='dConBox']/div");
						if (htmlElements2!=null && !htmlElements2.isEmpty()) {
							resultHtmlMap.put("qtbmgsxx_xzcfxx", htmlElements2.get(2).asXml());
						}else{
							resultHtmlMap.put("qtbmgsxx_xzcfxx", null);
						}
						//##############行政处罚信息   结束 ##############
						
						//*****************其他部门公示信息   结束*****************
				
						break;
					}
				}
				if (!matchFlag) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}
			
			return resultHtmlMap;
		}
				
		//云南数据（dyx）
		private Map<String, Object> getHtmlInfoMapOfYunnan(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
			Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
			//校验验证码和是否有列表数据
//			System.out.println(firstInfoPage.asXml());
			WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> divByXPath = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list-item']");
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-stat']"));
			if (divByXPath.size() == 0 && firstByXPath==null) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				if(firstByXPath!=null){
					String textContent = firstByXPath.getTextContent();
					if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					} else {//只有N条数据的时候，有list-a
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
					}
				}else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				}
			}
			@SuppressWarnings("unchecked")
			List<HtmlElement> anchors = (List<HtmlElement>) firstInfoPage.getByXPath("//div[@class='main']/div[@class='search']/div[@class='list-info']/div[@class='list-item']/div[@class='link']/a");
			LOGGER.info(anchors.toString());
			if (anchors!=null && !anchors.isEmpty()) {
				boolean matchFlag = false;
				for (HtmlElement anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
						matchFlag = true;
						//保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
//						System.out.println(target_item_info.asXml());
						resultHtmlMap.put("target_item_info", target_item_info.asXml());
						//公共参数
						String urlString=anchor.getAttribute("href").split("tab=")[0];
						//*****************工商公示信息   开始 *****************
						HtmlPage gsgsxx_djxx = anchor.click();
//						System.out.println(gsgsxx_djxx.asXml());
					/*	HtmlPage test1=firstInfoPage.getWebClient().getPage(anchor.getAttribute("href"));
						System.out.println(test1.asXml());*/
						@SuppressWarnings("unchecked")
						List<HtmlElement> tabs = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-l']/ul/li");//获取主页面左侧按钮

						@SuppressWarnings("unchecked")
						List<HtmlElement> djxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_01']");
						//##############登记信息   开始 ##############
						if (djxxElements!=null && !djxxElements.isEmpty()) {
						
						//********基本信息 开始
						resultHtmlMap.put("gsgsxx_jbxx", djxxElements.get(0).asXml());
						//********基本信息 结束
						if(djxxElements.size()==3){
							//********股东信息 开始
							resultHtmlMap.put("gsgsxx_gdxx", djxxElements.get(1).asXml());
							
							//股东详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> elements = (List<HtmlAnchor>)djxxElements.get(1).getByXPath("//table/tbody/tr/td/a");;
							if (elements!=null && !elements.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for(HtmlAnchor htmlAnchor:elements){
									HtmlPage detail = htmlAnchor.click();
//									System.out.println(detail.asXml());
									gdxxdetail.add(detail.asXml());
								}
								resultHtmlMap.put("gsgsxx_gdxx_detail",gdxxdetail);
							}
							
							//********股东信息 结束
							//********变更信息 开始
							resultHtmlMap.put("gsgsxx_bgxx", djxxElements.get(2).asXml());
							//********变更信息 结束
						}else if(djxxElements.size()==2){
							//********变更信息 开始
							resultHtmlMap.put("gsgsxx_bgxx", djxxElements.get(1).asXml());
							//********变更信息 结束
						}
						
						
						}
						//##############登记信息   结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> baxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_02']");
						//##############备案信息   开始 ##############
						if (baxxElements!=null && !baxxElements.isEmpty() && baxxElements.size()==3) {
						
						//********主要人员信息 开始
						resultHtmlMap.put("gsgsxx_zyryxx", baxxElements.get(0).asXml());
						//********主要人员信息 结束
						//********分支机构信息 开始
						resultHtmlMap.put("gsgsxx_fzjgxx", baxxElements.get(1).asXml());
						//********分支机构信息 结束
						//********清算信息 开始
						resultHtmlMap.put("gsgsxx_qsxx", baxxElements.get(2).asXml());
						//********清算信息 结束
						
						}
						
						if(baxxElements!=null && !baxxElements.isEmpty() && baxxElements.size()==1){
							//********主管部门（出资人）信息 开始
							resultHtmlMap.put("gsgsxx_zgbmxx", baxxElements.get(0).asXml());
							//********主管部门（出资人）信息 结束
						}
						//##############备案信息   结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> gqczdjxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_03']");
						//##############股权出资登记信息   开始 ##############
						if (gqczdjxxElements!=null && !gqczdjxxElements.isEmpty()) {
						
						//********股权出资登记信息 开始
						resultHtmlMap.put("gsgsxx_gqczdjxx", gqczdjxxElements.get(0).asXml());
						//********股权出资登记信息 结束
						
						}
						//##############股权出资登记信息   结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> dcdydjxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_04']");
						//##############动产抵押登记信息   开始 ##############
						if (dcdydjxxElements!=null && !dcdydjxxElements.isEmpty()) {
						
						//********动产抵押登记信息 开始
						resultHtmlMap.put("gsgsxx_dcdydjxx", dcdydjxxElements.get(0).asXml());
						//********动产抵押登记信息 结束
						
						}
						//##############动产抵押登记信息   结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> jyycxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_05']");
						//##############经营异常信息  开始 ##############
						if (jyycxxElements!=null && !jyycxxElements.isEmpty()) {
						
						//********经营异常信息开始
						resultHtmlMap.put("gsgsxx_jyycxx", jyycxxElements.get(0).asXml());
						//********经营异常信息结束
						
						}
						//##############经营异常信息  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> yzwfxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_06']");
						//##############严重违法信息  开始 ##############
						if (yzwfxxElements!=null && !yzwfxxElements.isEmpty()) {
						
						//********严重违法信息开始
						resultHtmlMap.put("gsgsxx_yzwfxx", yzwfxxElements.get(0).asXml());
						//********严重违法信息结束
						
						}
						//##############严重违法信息  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzcfxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_07']");
						//##############行政处罚信息  开始 ##############
						if (xzcfxxElements!=null && !xzcfxxElements.isEmpty()) {
						
						//********行政处罚信息开始
						resultHtmlMap.put("gsgsxx_xzcfxx", xzcfxxElements.get(0).asXml());
						//********行政处罚信息结束
						
						}
						//##############行政处罚信息  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> ccjcxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_08']");
						//##############抽查检查信息  开始 ##############
						if (ccjcxxElements!=null && !ccjcxxElements.isEmpty()) {
						
						//********抽查检查信息开始
						resultHtmlMap.put("gsgsxx_ccjcxx", ccjcxxElements.get(0).asXml());
						//********抽查检查信息结束
						
						}
						//##############抽查检查信息  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> xsfcxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_09']");
						//##############享受扶持信息  开始 ##############
						if (xsfcxxElements!=null && !xsfcxxElements.isEmpty()) {
						
						//********享受扶持信息开始
						resultHtmlMap.put("gsgsxx_xsfcxx", xsfcxxElements.get(0).asXml());
						//********享受扶持信息结束
						
						}
						//##############享受扶持信息  结束 ##############
						
						//*****************工商公示信息   结束*****************
						
						
						//*****************企业公示信息   开始 *****************
						HtmlPage qygsxx_qynb =firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(urlString+"tab=02")));
//						System.out.println(gtgshgs_gtgshnb.asXml());
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> qynbElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_01']");
						//##############企业年报  开始 ##############
						if (qynbElements!=null && !qynbElements.isEmpty()) {
						
						//********企业年报开始
						resultHtmlMap.put("qygsxx_qynb", qynbElements.get(0).asXml());
						//********企业年报结束
						
						//年报详情
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_01']/table/tbody/tr/td/a");
						if (htmlEles!=null && !htmlEles.isEmpty()) {
							List<String> gdxxdetail=new ArrayList<String>();
							for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
								HtmlPage gtgshgs_gtgshnb_detail = htmlEle.click();
								gdxxdetail.add(gtgshgs_gtgshnb_detail.asXml());
								
							}
							resultHtmlMap.put("qygsxx_qynb_detail",gdxxdetail);
						}
						
						}
						//##############企业年报  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> gdjczxxElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_04']");
						//##############股东及出资信息  开始 ##############
						if (gdjczxxElements!=null && !gdjczxxElements.isEmpty()) {
						
						//********股东及出资信息开始
						resultHtmlMap.put("qygsxx_gdjczxx", gdjczxxElements.get(0).asXml());
						//********股东及出资信息结束

						//********变更信息开始
						resultHtmlMap.put("qygsxx_bgxx", gdjczxxElements.get(1).asXml());
						//********变更信息结束
						
						}
						//##############股东及出资信息  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> gqbgxxElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_06']");
						//##############股权变更  开始 ##############
						if (gqbgxxElements!=null && !gqbgxxElements.isEmpty()) {
						
						//********股权变更开始
						resultHtmlMap.put("qygsxx_gqbgxx", gqbgxxElements.get(0).asXml());
						//********股权变更结束
						
						}
						//##############股权变更  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzxkxxElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_02']");
						//##############行政许可  开始 ##############
						if (xzxkxxElements!=null && !xzxkxxElements.isEmpty()) {
						
						//********行政许可开始
						resultHtmlMap.put("qygsxx_xzxkxx", xzxkxxElements.get(0).asXml());
						//********行政许可结束
						
						}
						//##############行政许可  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> zscqczdjElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_03']");
						//##############知识产权出资登记信息  开始 ##############
						if (zscqczdjElements!=null && !zscqczdjElements.isEmpty()) {
						
						//********知识产权出资登记信息开始
						resultHtmlMap.put("qygsxx_zscqczdj", zscqczdjElements.get(0).asXml());
						//********知识产权出资登记信息结束
						
						}
						//##############知识产权出资登记信息  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzcfElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_05']");
						//##############行政处罚信息  开始 ##############
						if (xzcfElements!=null && !xzcfElements.isEmpty()) {
						
						//********行政处罚信息开始
						resultHtmlMap.put("qygsxx_xzcf", xzcfElements.get(0).asXml());
						//********行政处罚信息结束
						
						}
						//##############行政处罚信息  结束 ##############
						
						//*****************企业公示信息   结束*****************
						
						
						//*****************其他部门公示信息   开始 *****************
						HtmlPage qtbmgsxx =firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(urlString+"tab=03")));
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> qtbmgs_xzxkElements = (List<HtmlElement>)qtbmgsxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-03_01']");
						//##############行政许可信息  开始 ##############
						if (qtbmgs_xzxkElements!=null && !qtbmgs_xzxkElements.isEmpty()) {
						
						//********行政许可信息开始
						resultHtmlMap.put("qtbmgsxx_xzxk", qtbmgs_xzxkElements.get(0).asXml());
						//********行政许可信息结束
						
						}
						//##############行政许可信息  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> qtbmgs_xzcfElements = (List<HtmlElement>)qtbmgsxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-03_02']");
						//##############行政处罚信息  开始 ##############
						if (qtbmgs_xzcfElements!=null && !qtbmgs_xzcfElements.isEmpty()) {
						
						//********行政处罚信息开始
						resultHtmlMap.put("qtbmgsxx_xzcf", qtbmgs_xzcfElements.get(0).asXml());
						//********行政处罚信息结束
						
						}
						//##############行政处罚信息  结束 ##############
						//*****************其他部门公示信息   结束*****************
						
						
						
						//*****************司法协助公示信息   开始 *****************
						HtmlPage sfxzgsxx =firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(urlString+"tab=06")));
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> sfxzgsxx_gqdjElements = (List<HtmlElement>)sfxzgsxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-06_01']");
						//##############股权冻结  开始 ##############
						if (sfxzgsxx_gqdjElements!=null && !sfxzgsxx_gqdjElements.isEmpty()) {
						
						//********股权冻结开始
						resultHtmlMap.put("sfxzgsxx_gqdj", sfxzgsxx_gqdjElements.get(0).asXml());
						//********股权冻结结束
						
						}
						//##############股权冻结  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> sfxzgsxx_gqbgElements = (List<HtmlElement>)sfxzgsxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-06_02']");
						//##############股权变更  开始 ##############
						if (sfxzgsxx_gqbgElements!=null && !sfxzgsxx_gqbgElements.isEmpty()) {
						
						//********司法股东变革登记开始
						resultHtmlMap.put("sfxzgsxx_sfgdbgdj", sfxzgsxx_gqbgElements.get(0).asXml());
						//********司法股东变革登记结束
						
						}
						//##############股权变更  结束 ##############
						//*****************司法协助公示信息   结束*****************
						
						
				
						break;
					}
				}
				if (!matchFlag) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}
			
			return resultHtmlMap;
		}
				
	
	//山西数据
	@SuppressWarnings("unchecked")
	private Map<String, Object> getHtmlInfoMapOfShanxi(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
		
		//
		HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']"));
		HtmlElement divByXPath2 = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
		if (divByXPath == null && divByXPath2 == null) {
			//DomElement checkcode = firstInfoPage.getElementById("checkNo");
			//String val = checkcode.getAttribute("value");
			if (firstInfoPage.asXml().contains("验证码不正确或已失效")) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
			}
		} else {
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
			if (anchors.size() == 0) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}
		}
		//
		
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
		LOGGER.info(anchors.toString());
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				
				if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
					matchFlag = true;
					//保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info", target_item_info.asXml());
					
					//获得公共参数id
					String idParam = "";
					String hrefAttr = anchor.getAttribute("href");
					if (!StringUtils.isEmpty(hrefAttr)) {
						int index = hrefAttr.indexOf("?");
						idParam = hrefAttr.substring(index+1);
					}
					
					//一、获取工商公示信息
					HtmlPage gsgsxx_page = anchor.click();
					resultHtmlMap.put("gsgsxx", gsgsxx_page.asXml());
					
					//工商公示信息-->登记信息-->股东信息-->详情
//					List<HtmlAnchor> anchors_gdxx_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
//					List<String> gdxx_list = new ArrayList<String>();
//					for (HtmlAnchor htmlAnchor : anchors_gdxx_detail) {
//						HtmlPage gdxx_detail = htmlAnchor.click();
//						gdxx_list.add(gdxx_detail.asXml());
//					}
//					resultHtmlMap.put("gsgsxx_gdxx_detail", gdxx_list);
					
					//工商公示信息-->登记信息-->股东信息(以及详情信息)
					String mainIdParam = idParam.replace("id", "mainId");
					List<String> gdxxList = new ArrayList<String>();
					List<String> gdxxDetailList = new ArrayList<String>();
					int gdxxPageNo = 1;
					while (true) {
						String gsgsxx_djxx_gdxx_url = "http://gsxt.fc12319.com/QueryInvList.jspx?pno="+gdxxPageNo+"&"+mainIdParam;
						String gsgsxx_djxx_gdxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_djxx_gdxx_url).getWebResponse().getContentAsString();
						Document gsgsxxGdxxDoc = Jsoup.parse(gsgsxx_djxx_gdxx_page);
						if (gsgsxxGdxxDoc != null) {
							Elements aElements = gsgsxxGdxxDoc.select("a");
							for (Element aElement : aElements) {
								String attr = aElement.attr("onclick");
								if (attr != null && !"".equals(attr)) {
									String[] split = attr.split("'");
									if (split.length > 2) {
										String detailUrl = "http://gsxt.fc12319.com" + split[1];
										HtmlPage gsgsxx_gdxx_detail_page = null;
										try {
											gsgsxx_gdxx_detail_page = firstInfoPage.getWebClient().getPage(detailUrl);
										} catch (Exception e) {
											gsgsxx_gdxx_detail_page = WebClient.getCustomHtmlPage(detailUrl, firstInfoPage.getWebClient().getCurrentWindow());
										}
										gdxxDetailList.add(gsgsxx_gdxx_detail_page.asXml());
									}
								}
							}
						}
						
						if (!gdxxList.contains(gsgsxx_djxx_gdxx_page)) {
							gdxxList.add(gsgsxx_djxx_gdxx_page);
							gdxxPageNo++;
						} else {
							break;
						}
						
					}
					resultHtmlMap.put("gsgsxx_gdxx_page", gdxxList);
					resultHtmlMap.put("gsgsxx_gdxx_detail", gdxxDetailList);
					
					//工商公示信息-->登记信息-->变更信息
					List<String> bgxxList = new ArrayList<String>();
					int bgxxPageNo = 1;
					while (true) {
						String gsgsxx_bgxx_url = "http://gsxt.fc12319.com/QueryAltList.jspx?pno="+bgxxPageNo+"&"+mainIdParam;
						String gsgsxx_bgxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_bgxx_url).getWebResponse().getContentAsString();
						if (!bgxxList.contains(gsgsxx_bgxx_page)) {
							bgxxList.add(gsgsxx_bgxx_page);
							bgxxPageNo++;
						} else {
							break;
						}
						
					}
					resultHtmlMap.put("gsgsxx_bgxx_page", bgxxList);
					
					//工商公示信息-->备案信息-->主要人员信息
					List<String> zyryxxList = new ArrayList<String>();
					int zyryPageNo = 1;
					while (true) {
						DomElement pageNoDom = gsgsxx_page.getElementById("spanmem"+zyryPageNo);
						if (pageNoDom != null) {
							String gsgsxx_baxx_zyryxx_url = "http://gsxt.fc12319.com/QueryMemList.jspx?pno="+zyryPageNo+"&"+mainIdParam;
							String gsgsxx_baxx_zyryxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_baxx_zyryxx_url).getWebResponse().getContentAsString();
							if (!zyryxxList.contains(gsgsxx_baxx_zyryxx_page)) {
								zyryxxList.add(gsgsxx_baxx_zyryxx_page);
								zyryPageNo++;
							} else {
								break;
							}
						}else {
							break;
						}
					}
					resultHtmlMap.put("gsgsxx_baxx_zyryxx_page", zyryxxList);
					
					//http://gsxt.fc12319.com/QueryMortList.jspx?pno=1&mainId=DB5C6077C0A57243E7C56BA05CD252C0
					//工商公示信息-->动产抵押登记信息-->详情
					List<HtmlAnchor> anchors_dcdyxx_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='mortDiv']/table[@class='detailsList']/tbody/tr/td/a");
					List<String> gsgs_dcdyxx_list = new ArrayList<String>();
					for (HtmlAnchor htmlAnchor : anchors_dcdyxx_detail) {
						String attribute = htmlAnchor.getAttribute("onclick");
						String detailUrl = "http://gsxt.fc12319.com" + attribute.split("'")[1];
						HtmlPage dcdyxx_detail_page = null;
						try {
							dcdyxx_detail_page = firstInfoPage.getWebClient().getPage(detailUrl);
						} catch (Exception e) {
							dcdyxx_detail_page = WebClient.getCustomHtmlPage(detailUrl, firstInfoPage.getWebClient().getCurrentWindow());
						}
						gsgs_dcdyxx_list.add(dcdyxx_detail_page.asXml());
					}
					resultHtmlMap.put("gsgsxx_dcdydjxx_details", gsgs_dcdyxx_list);
					
					//工商公示信息-->经营异常信息
					String gsgsxx_jyycxx_url = "http://gsxt.fc12319.com/QueryExcList.jspx?pno=1&"+mainIdParam;
					String gsgsxx_jyycxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_jyycxx_url).getWebResponse().getContentAsString();
					resultHtmlMap.put("gsgsxx_jyycxx_page", gsgsxx_jyycxx_page);
					
					List<?> liElements = gsgsxx_page.getByXPath("//div[@id='leftTabs']/ul/li");
					//二、获取企业公示信息
					String qygsxx_url = "http://gsxt.fc12319.com/enterprisePublicity.jspx?"+idParam;
					HtmlPage qygsxx_page = firstInfoPage.getWebClient().getPage(qygsxx_url);
					resultHtmlMap.put("qygsxx", qygsxx_page.asXml());
					
					//企业公示信息-->企业年报-->详情
					List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
					List<String> nbxx_list = new ArrayList<String>();
					for (HtmlAnchor htmlAnchor : anchors_detail) {
						HtmlPage nb_detail = htmlAnchor.click();
						nbxx_list.add(nb_detail.asXml());
					}
					resultHtmlMap.put("qygsxx_qynb_detail", nbxx_list);
					
					//企业公示信息-->行政许可信息
					String qygsxx_xzxkxx_url = "http://gsxt.fc12319.com/QueryLicenseRegList.jspx?pno=1&"+mainIdParam;
					String gsgsxx_xzxkxx_page = firstInfoPage.getWebClient().getPage(qygsxx_xzxkxx_url).getWebResponse().getContentAsString();
					resultHtmlMap.put("gsgsxx_xzxkxx_page", gsgsxx_xzxkxx_page);
					
					//三、获取其他部门公示信息
					String qtbmgsxx_url = "http://gsxt.fc12319.com/otherDepartment.jspx?"+mainIdParam;
					HtmlPage qtbmgsxx_page = firstInfoPage.getWebClient().getPage(qtbmgsxx_url);
					resultHtmlMap.put("qtbmgsxx", qtbmgsxx_page.asXml());	
					
					//四、获取 司法协助公示信息
					if(liElements.size()>3) {
						String sfxzxx_url = "http://gsxt.fc12319.com/justiceAssistance.jspx?"+idParam;
						HtmlPage sfxzxx_page = firstInfoPage.getWebClient().getPage(sfxzxx_url);
						resultHtmlMap.put("sfxzgsxx", sfxzxx_page.asXml());
					}
					
					break;
				}
		
			}
			
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
		}
		
		return resultHtmlMap;
	}
	
	//陕西数据
	@SuppressWarnings("unchecked")
	private Map<String, Object> getHtmlInfoMapOfShaanxi(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
		
		//
		HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
		if (divByXPath == null) {
			DomElement inputElement = firstInfoPage.getElementById("entname");
			String val = inputElement.getAttribute("style");
			if (val.contains("#BDBDBD")) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
			}
		} else {
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='center-1']/div/ul/li/a");
			if (anchors.size()-1 == 0) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}
		}
		//
		
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='center-1']/div/ul/li/a");
		LOGGER.info(anchors.toString());
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				String liTextContent = anchor.getParentNode().getTextContent().toString().trim();
				
				//保存列表页目标条目信息
				HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
				resultHtmlMap.put("target_item_info", target_item_info.asXml());
				
				if (liTextContent.contains("已吊销")) {
					matchFlag = true;
					break;
				} else {
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
						matchFlag = true;
						String onclickVal = anchor.getAttribute("onclick");
						String maentPripid = onclickVal.substring(onclickVal.indexOf("(")+1, onclickVal.indexOf(")")).split(",")[0].replace("'", "");
						
						//---------------------------------------工商公示信息  start---------------------------------//
						//点击列表页目标条目，获取 工商公示信息->登记信息
						HtmlPage gsgsxx_djxx_jbxx = anchor.click();
						resultHtmlMap.put("gsgsxx_djxx", gsgsxx_djxx_jbxx.asXml());
						
						//点击列表页目标条目，获取 工商公示信息->登记信息->基本信息->股东信息->详情
						List<HtmlAnchor> anchors2 = (List<HtmlAnchor>) gsgsxx_djxx_jbxx.getByXPath("//table[@id='table_fr']/tbody/tr/td/a");
						if (anchors2!=null && !anchors2.isEmpty()) {
							List<String> gdxxdetail=new ArrayList<String>();
							for (HtmlAnchor anchor2 : anchors2) {
								HtmlPage gsgsxx_djxx_gdxx_detail = anchor2.click();
								gdxxdetail.add(gsgsxx_djxx_gdxx_detail.asXml());
							}
							resultHtmlMap.put("gsgsxx_djxx_gdxx", gdxxdetail);
						}
						
						//获得工商公示信息下的：备案信息、动产抵押登记信息、股权出质登记信息、经营异常信息、严重违法信息、行政处罚信息、抽查检查信息
						List<HtmlElement> byXPath = (List<HtmlElement>)gsgsxx_djxx_jbxx.getByXPath("//div[@id='tabs']/ul/li");
						for (HtmlElement htmlElement : byXPath) {
							String textContent = htmlElement.getTextContent().trim();
							switch (textContent) {
							case "备案信息":
								Page gsgs_baxx_page = htmlElement.click();
								resultHtmlMap.put("gsgs_baxx_page", 
										gsgs_baxx_page.getWebResponse().getContentAsString());
								
								break;
							case "动产抵押登记信息":
								Page gsgs_dcdydjxx_page = htmlElement.click();
								resultHtmlMap.put("gsgs_dcdydjxx_page", 
										gsgs_dcdydjxx_page.getWebResponse().getContentAsString());
								
								break;
							case "股权出质登记信息":
								Page gsgs_gqczdjxx_page = htmlElement.click();
								resultHtmlMap.put("gsgs_gqczdjxx_page", 
										gsgs_gqczdjxx_page.getWebResponse().getContentAsString());
								
								break;
							case "经营异常信息":
								//Page gsgs_jyycxx_page = htmlElement.click();
								String gsgs_jyycxx_url = "http://xygs.snaic.gov.cn/ztxy.do?czmk=czmk6&maent.pripid="+maentPripid+"&method=jyycInfo&random="+new Date().getTime();
								HtmlPage gsgs_jyycxx_page = firstInfoPage.getWebClient().getPage(gsgs_jyycxx_url);
								resultHtmlMap.put("gsgs_jyycxx_page", gsgs_jyycxx_page.asXml());
								
								break;
							case "严重违法信息":
								Page gsgs_yzwfxx_page = htmlElement.click();
								resultHtmlMap.put("gsgs_yzwfxx_page", 
										gsgs_yzwfxx_page.getWebResponse().getContentAsString());
								
								break;
							case "行政处罚信息":
								Page gsgs_xzcfxx_page = htmlElement.click();
								resultHtmlMap.put("gsgs_xzcfxx_page", 
										gsgs_xzcfxx_page.getWebResponse().getContentAsString());
								
								break;	
							case "抽查检查信息":
								Page gsgs_ccjcxx_page = htmlElement.click();
								resultHtmlMap.put("gsgs_ccjcxx_page", 
										gsgs_ccjcxx_page.getWebResponse().getContentAsString());
								
								break;
							default:
								break;
							}
						}
						//---------------------------------------工商公示信息  end---------------------------------//
						
						//------------------企业公示信息、其他部门公示信息、司法协助公示信息  start---------------------------------//
						List<HtmlElement> leftTabs = (List<HtmlElement>)gsgsxx_djxx_jbxx.getByXPath("//div[@id='leftTabs']/ul/li");
						for (HtmlElement leftTab : leftTabs) {
							HtmlPage qygs_page = (HtmlPage)leftTab.click();
							String textContent = leftTab.getAttribute("id");
							if ("gs2".equals(textContent)) {//企业公示信息
								List<HtmlElement> qygsTabs = (List<HtmlElement>)qygs_page.getByXPath("//div[@id='qygsTabs']/ul/li");
								for (HtmlElement qygsTab : qygsTabs) {
									String qygsTabText = qygsTab.getTextContent().trim();
									switch (qygsTabText) {
									case "企业年报":
										HtmlPage qygs_qynbxx_page = (HtmlPage)qygsTab.click();
										resultHtmlMap.put("qygs_qynbxx_page", qygs_qynbxx_page.asXml());
										//企业公示信息-->企业年报-->详情
										List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygs_qynbxx_page.getByXPath("//table[@id='t30']/tbody/tr/td/a");
										List<String> nbxx_list = new ArrayList<String>();
										for (HtmlAnchor htmlAnchor : anchors_detail) {
											HtmlPage nb_detail = htmlAnchor.click();
											nbxx_list.add(nb_detail.asXml());
										}
										resultHtmlMap.put("qygs_qynbxx_detail", nbxx_list);
										break;
									case "股东及出资信息":
										HtmlPage qygs_gdjczxx_page = (HtmlPage)qygsTab.click();
										resultHtmlMap.put("qygs_gdjczxx_page", qygs_gdjczxx_page.asXml());
										break;
									case "股权变更信息":
										HtmlPage qygs_gqbgxx_page = (HtmlPage)qygsTab.click();
										resultHtmlMap.put("qygs_gqbgxx_page", qygs_gqbgxx_page.asXml());
										break;
									case "行政许可信息":
										HtmlPage qygs_xzxkxx_page = (HtmlPage)qygsTab.click();
										resultHtmlMap.put("qygs_xzxkxx_page", qygs_xzxkxx_page.asXml());
										break;
									case "知识产权出质登记信息":
										HtmlPage qygs_zscqczdjxx_page = (HtmlPage)qygsTab.click();
										resultHtmlMap.put("qygs_zscqczdjxx_page", qygs_zscqczdjxx_page.asXml());
										break;
									case "行政处罚信息":
										HtmlPage qygs_xzcfxx_page = (HtmlPage)qygsTab.click();
										resultHtmlMap.put("qygs_xzcfxx_page", qygs_xzcfxx_page.asXml());
										break;
									default:
										break;
									}
								}
							} else if ("gs3".equals(textContent)) {//其他部门公示信息
								List<HtmlElement> qtbmgsTabs = (List<HtmlElement>)qygs_page.getByXPath("//div[@id='qtbmgsTabs']/ul/li");
								for (HtmlElement qtbmgsTab : qtbmgsTabs) {
									String qtbmgsTabText = qtbmgsTab.getTextContent().trim();
									if ("行政许可信息".equals(qtbmgsTabText)) {
										HtmlPage qtbmgs_xzxkxx_page = (HtmlPage)qtbmgsTab.click();
										resultHtmlMap.put("qtbmgs_xzxkxx_page", qtbmgs_xzxkxx_page.asXml());
									} else if ("行政处罚信息".equals(qtbmgsTabText)) {
										HtmlPage qtbmgs_xzcfxx_page = (HtmlPage)qtbmgsTab.click();
										resultHtmlMap.put("qtbmgs_xzcfxx_page", qtbmgs_xzcfxx_page.asXml());
									}
								}
							} else if ("gs4".equals(textContent)) {//司法协助公示信息
								List<HtmlElement> sfxzgsTabs = (List<HtmlElement>)qygs_page.getByXPath("//div[@id='sfxzgsTabs']/ul/li");
								for (HtmlElement sfxzgsTab : sfxzgsTabs) {
									String sfxzgsTabText = sfxzgsTab.getTextContent().trim();
									if ("股权冻结信息".equals(sfxzgsTabText)) {
										HtmlPage sfxzgs_gqdjxx_page = (HtmlPage)sfxzgsTab.click();
										resultHtmlMap.put("sfxzgs_gqdjxx_page", sfxzgs_gqdjxx_page.asXml());
									} else if ("股东变更信息".equals(sfxzgsTabText)) {
										HtmlPage sfxzgs_gdbgxx_page = (HtmlPage)sfxzgsTab.click();
										resultHtmlMap.put("sfxzgs_gdbgxx_page", sfxzgs_gdbgxx_page.asXml());
									}
								}
							}
						}
						
						//------------------企业公示信息、其他部门公示信息、司法协助公示信息  end---------------------------------//
						
						break;
					}
				}
			}
			
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
		}
		
		return resultHtmlMap;
	}	
	
	//山东数据
	private Map<String, Object> getHtmlInfoMapOfShandong(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
			//DomElement checkcode = firstInfoPage.getElementById("checkNo");
		WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();
		   //验证码成功或者失败
			HtmlElement divByXPathyzm =  (HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='input-center3']/font");
			if (divByXPathyzm!=null) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				HtmlElement divByXPath =  ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']"));				
				//成功
				if (divByXPath !=null) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				} else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				}
			}
													
				
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
			LOGGER.info(anchors.toString());
			if (anchors!=null && !anchors.isEmpty()) {
				boolean matchFlag = false;
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目	
						matchFlag = true;
						//保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
						resultHtmlMap.put("target_item_info", target_item_info.asXml());
						
						Elements e1=Jsoup.parseBodyFragment(target_item_info.asXml()).getElementsByClass("font16");
						Element element1=e1.get(0);
						Element aElement = element1.select("a").get(0);
						//String hrefElement = getElementAttr(aElement, "href");
						String hrefElement = aElement.hasAttr("href")?aElement.attr("href"):"";
						String encrpripid = hrefElement.substring(hrefElement.lastIndexOf("/")+1);
						String enttype=hrefElement.split("/")[1];
						String gsurl="http://218.57.139.24/pub/"+hrefElement;
						
						//System.out.println(hrefElement);
						String mystr=hrefElement.split("gsgsdetail")[1];
						//System.out.println(mystr);
				
						//点击列表页目标条目，获取 工商公示信息->登记信息
//						
						HtmlPage qyxx_page = anchor.click();
						if(!qyxx_page.asXml().contains("登记信息")){
							return resultHtmlMap;
						}
						resultHtmlMap.put("qyxx_gsgsxx", qyxx_page.asXml());
					
						
			    		String[] command = {"casperjs", "/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js", "--web-security=no", "--url="+gsurl}; 
			    		String qyxx_gsgsxx_djxx = CommandUtil.runCommand(command);
			    		resultHtmlMap.put("qyxx_gsgsxx_djxx", qyxx_gsgsxx_djxx);
						
						//获取 工商公示信息->备案信息->主要人员信息var url = webroot+"pub/gsczxx";
						WebRequest czxxWebRequest = new WebRequest(new URL("http://218.57.139.24/pub/gsryxx/"+enttype+"?encrpripid="+encrpripid), HttpMethod.POST);
						DomElement csrfMetaEle = qyxx_page.getFirstByXPath("//meta[@name='_csrf']");
						String csrfToken = "";
						if (csrfMetaEle!=null) {
							csrfToken = csrfMetaEle.getAttribute("content");
						}
						czxxWebRequest.setAdditionalHeader("X-CSRF-TOKEN", csrfToken); //window, new WebRequest(new URL(
						Page zyryxxPage = qyxx_page.getWebClient().getPage(window,czxxWebRequest);
						resultHtmlMap.put("qyxx_gsgsxx_baxx_zyryxx", zyryxxPage.getWebResponse().getContentAsString());					
						//System.out.println(zyryxxPage.getWebResponse().getContentAsString());
						
						//获取 工商公示信息->备案信息->分支机构
						WebRequest czxxWebRequestfzjg = new WebRequest(new URL("http://218.57.139.24/pub/gsfzjg/"+enttype+"?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestfzjg.setAdditionalHeader("X-CSRF-TOKEN", csrfToken); //
						Page czxxPage = qyxx_page.getWebClient().getPage(window,czxxWebRequestfzjg);
						resultHtmlMap.put("qyxx_gsgsxx_baxx_fzjgxx", czxxPage.getWebResponse().getContentAsString());					
						//System.out.println(czxxPage.getWebResponse().getContentAsString());
						
						//获取 工商公示信息->动产抵押登记信息
						WebRequest czxxWebRequestdcdydjxx = new WebRequest(new URL("http://218.57.139.24/pub/gsdcdy?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestdcdydjxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken); //
						Page czxxPagedcdydjxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestdcdydjxx);
						resultHtmlMap.put("qyxx_gsgsxx_dcdydjxx", czxxPagedcdydjxx.getWebResponse().getContentAsString());					
						//System.out.println(czxxPage.getWebResponse().getContentAsString());
						
						//获取 工商公示信息->股权出质登记信息
						WebRequest czxxWebRequestgqczdjxx = new WebRequest(new URL("http://218.57.139.24/pub/gsgqcz?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestgqczdjxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken); //
						Page czxxPagegqczdjxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestdcdydjxx);
						resultHtmlMap.put("qyxx_gsgsxx_gqczdjxx", czxxPagegqczdjxx.getWebResponse().getContentAsString());					
						//System.out.println(czxxPage.getWebResponse().getContentAsString());
						
						//获取 工商公示信息->行政处罚信息
						WebRequest czxxWebRequestxzcfxx= new WebRequest(new URL("http://218.57.139.24/pub/gsxzcfxx?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestxzcfxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken); //
						Page czxxPagegxzcfxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestxzcfxx);
						resultHtmlMap.put("qyxx_gsgsxx_xzcfxx", czxxPagegxzcfxx.getWebResponse().getContentAsString());	
						
						//获取 工商公示信息->经营异常信息var url = webroot+"pub/jyyc/"+enttype;
						WebRequest czxxWebRequestjyjcxx= new WebRequest(new URL("http://218.57.139.24/pub/jyyc/"+enttype+"?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestjyjcxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken); //
						Page czxxPagegjyjcxx= qyxx_page.getWebClient().getPage(window,czxxWebRequestjyjcxx);
						resultHtmlMap.put("qyxx_gsgsxx_jyjcxx", czxxPagegjyjcxx.getWebResponse().getContentAsString());	
						//System.out.println( czxxPagegjyjcxx.getWebResponse().getContentAsString());
						
						//获取 工商公示信息->严重违法信息var url = webroot+"pub/yzwfqy";
						WebRequest czxxWebRequestyzwfxx= new WebRequest(new URL("http://218.57.139.24/pub/yzwfqy?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestyzwfxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken); //
						Page czxxPagegyzwfxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestyzwfxx);
						resultHtmlMap.put("qyxx_gsgsxx_yzwfxx", czxxPagegyzwfxx.getWebResponse().getContentAsString());	
						
						//获取 工商公示信息->抽查检查信息	var url = webroot+"pub/ccjcxx";
						WebRequest czxxWebRequestcxjcxx= new WebRequest(new URL("http://218.57.139.24/pub/ccjcxx?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestcxjcxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken); //
						Page czxxPagegcxjcxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestyzwfxx);
						resultHtmlMap.put("qyxx_gsgsxx_cxjcxx", czxxPagegcxjcxx.getWebResponse().getContentAsString());	
						
						
						//二、获取企业公示信息
//						HtmlElement qyxx_qygsxx = (HtmlElement)qyxx_page.getByXPath("//div[@id='leftTabs']/ul/li").get(1);
//						HtmlPage qygsxx_page = (HtmlPage)qyxx_qygsxx.click();
//						resultHtmlMap.put("qygsxx", qygsxx_page.asXml());
//						System.out.println(qygsxx_page.asXml());
						//http://218.57.139.24/pub/qygsdetail/1100/2396ed6cd3e0e1a30bc8098cadaef458e48f827ea3353ac3b826876e37a1ca6f
						String gsgsxx_sfxzgsxx_url_hqqygsxx = "http://218.57.139.24/pub/qygsdetail"+mystr ;				
						HtmlPage qygsxx_page =firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_sfxzgsxx_url_hqqygsxx)));
						resultHtmlMap.put("qygsxx", qygsxx_page.asXml());
						
//						String[] command2 = {"casperjs", "/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js", "--web-security=no", "--url="+gsgsxx_sfxzgsxx_url_hqqygsxx}; 
//			    		String qygsxx = CommandUtil.runCommand(command2);
//			    		resultHtmlMap.put("qygsxx", qygsxx);
						//<a href="http://218.57.139.24/pub/nb/detail/1100/0453801D2A010583E05012AC9E011868" target="_blank">
											
						List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
						List<String> nbxx_list = new ArrayList<String>();
						for (HtmlAnchor htmlAnchor : anchors_detail) {
							String attribute = htmlAnchor.getAttribute("href");
                            String nburldetail="http://218.57.139.24"+attribute;
                            String[] command2 = {"casperjs", "/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js", "--web-security=no", "--url="+nburldetail}; 
    			    		String nianbaodetail = CommandUtil.runCommand(command2);
							//HtmlPage nb_detail = htmlAnchor.click();
							nbxx_list.add(nianbaodetail);
						}
						resultHtmlMap.put("qygsxx_qynb_detail", nbxx_list);
						
						//企业公示信息->股东及出资信息->股东及出资信息		var url = webroot+"pub/qygsjsxxxzczxx";	
						WebRequest czxxWebRequestgdjczxx= new WebRequest(new URL("http://218.57.139.24/pub/qygsjsxxxzczxx?encrpripid="+encrpripid), HttpMethod.POST);
						DomElement csrfMetaEle2 = qygsxx_page.getFirstByXPath("//meta[@name='_csrf']");
						String csrfToken2 = "";
						if (csrfMetaEle2!=null) {
							csrfToken2 = csrfMetaEle.getAttribute("content");
						}
						czxxWebRequestgdjczxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken2); //
						Page czxxPageggdjczxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestgdjczxx);
						resultHtmlMap.put("qyxx_qygsxx_gdjczxx", czxxPageggdjczxx.getWebResponse().getContentAsString());	
						
						//企业公示信息->股东及出资信息->变更信息		var url = webroot+"pub/qygsjsxxczxxbgsx";
						WebRequest czxxWebRequestbgxx= new WebRequest(new URL("http://218.57.139.24/pub/qygsjsxxczxxbgsx?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestbgxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken2); //
						Page czxxPagegbgxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestgdjczxx);
						resultHtmlMap.put("qyxx_qygsxx_gdjczxx_bgxx", czxxPagegbgxx.getWebResponse().getContentAsString());	
						
						
						//企业公示信息->股权变更信息		var url = webroot+"pub/qygsJsxxgqbg";
						WebRequest czxxWebRequestgqbgxx= new WebRequest(new URL("http://218.57.139.24/pub/qygsJsxxgqbg?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestgqbgxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken2); //
						Page czxxPageggqbgxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestgqbgxx);
						resultHtmlMap.put("qyxx_qygsxx_gqbgxx", czxxPageggqbgxx.getWebResponse().getContentAsString());	
						
						//企业公示信息->行政许可信息		var url = webroot+"pub/qygsjsxxxzxk";
						WebRequest czxxWebRequestxzxkxx= new WebRequest(new URL("http://218.57.139.24/pub/qygsjsxxxzxk?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestxzxkxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken2); //
						Page czxxPagegxzxkxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestxzxkxx);
						resultHtmlMap.put("qyxx_qygsxx_xzxkxx", czxxPagegxzxkxx.getWebResponse().getContentAsString());	
						
						//企业公示信息->知识产权出质登记信息		var url = webroot+"pub/qygsjsxxzscqcz";
						WebRequest czxxWebRequestzscqczdjxx= new WebRequest(new URL("http://218.57.139.24/pub/qygsjsxxzscqcz?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestzscqczdjxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken2); //
						Page czxxPagegzscqczdjxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestzscqczdjxx);
						resultHtmlMap.put("qyxx_qygsxx_zscqczdjxx", czxxPagegzscqczdjxx.getWebResponse().getContentAsString());	
						
						//企业公示信息->行政处罚信息	var url = webroot+"pub/qygsjsxxxzcfxx";
						WebRequest czxxWebRequestxzcfxxx= new WebRequest(new URL("http://218.57.139.24/pub/qygsjsxxxzcfxx?encrpripid="+encrpripid), HttpMethod.POST);
						czxxWebRequestxzcfxxx.setAdditionalHeader("X-CSRF-TOKEN", csrfToken2); //
						Page czxxPagegxzcfxxx = qyxx_page.getWebClient().getPage(window,czxxWebRequestxzcfxxx);
						resultHtmlMap.put("qyxx_qygsxx_xzcfxxx", czxxPagegxzcfxxx.getWebResponse().getContentAsString());	
						
						
						//三、获取其他部门公示信息
						String gsgsxx_sfxzgsxx_url_hqqtbmgsxx = "http://218.57.139.24/pub/qtgsdetail"+mystr ;				
						HtmlPage qtbmgsxx_page =firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_sfxzgsxx_url_hqqtbmgsxx)));
						resultHtmlMap.put("qtbmgsxx", qtbmgsxx_page.asXml());
						
//						String[] command3 = {"casperjs", "/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js", "--web-security=no", "--url="+gsgsxx_sfxzgsxx_url_hqqtbmgsxx}; 
//			    		String qtbmgsxx = CommandUtil.runCommand(command3);
//			    		resultHtmlMap.put("qygsxx", qtbmgsxx);
						
						
						//四、获取司法协助公示信息
						//http://218.57.139.24/pub/sfgsdetail/1130/95f6c493f094da93009e08daa27616d8
						//String gsgsxx_sfxzgsxx_url = "http://218.57.139.24/pub/sfgsdetail"+mystr ;
						//HtmlPage gsgsxx_baxx_zyryxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_sfxzgsxx_url);
						//resultHtmlMap.put("gsgsxx_baxx_zyryxx", gsgsxx_baxx_zyryxx_page.asXml());
						
						
						//四、获取司法协助公示信息
						//HtmlElement sfxzgsxx_tab = (HtmlElement)qyxx_page.getByXPath("//div[@id='leftTabs']/ul/li").get(3);
						String gsgsxx_sfxzgsxx_url = "http://218.57.139.24/pub/sfgsdetail"+mystr ;
						HtmlPage sfxzgsxx_page = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_sfxzgsxx_url)));
						resultHtmlMap.put("sfxzgsxx_page", sfxzgsxx_page.asXml());
						
//						String[] command4 = {"casperjs", "/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js", "--web-security=no", "--url="+gsgsxx_sfxzgsxx_url}; 
//			    		String sfxzgsxx = CommandUtil.runCommand(command4);
//			    		resultHtmlMap.put("sfxzgsxx_page", sfxzgsxx);
						
						//http://218.57.139.24/pub/sfgsgqxxdetail/95f6c493f094da93009e08daa27616d8/1130/12D1EA5D6111126BE054/1
						String mystrdetil="";
						if(mystr!=null&&!"".equals(mystr)){
							String mystrspill[]=mystr.split("/");
							 mystrdetil="/"+mystrspill[2]+"/"+mystrspill[1];
						}
						String mystrdetilurl="http://218.57.139.24/pub/sfgsgqxxdetail"+mystrdetil;
						String urlstring=sfxzgsxx_page.asXml();
						List<String> sfxzxxlist = new AbstractParser() {}.getSubStringByRegex(urlstring, "var gqxxliststr ='\\[.*\\]");
										
						String sfxzgsxxzzfc = sfxzxxlist.get(0).substring(19,sfxzxxlist.get(0).length() - 1);
						if(!"".equals(sfxzgsxxzzfc)&&null!=sfxzgsxxzzfc){						
						String[] sfxzgsxxzzfcsplil = sfxzgsxxzzfc.split(",");
						List<String> urllist=new ArrayList<String>();
						String num="";
						for (int m = 0; m < sfxzgsxxzzfcsplil.length; m++) {
							String strname = sfxzgsxxzzfcsplil[m];
							String strname1[] = strname.split(":");
							String myname = strname1[0];
							String strna = strname1[1];
							String namesss = "";
							if ("\"frozstate\"".equals(myname)) {
								// 状态
								namesss = strna.substring(1, strna.length() - 1);
								num=namesss;
							}
							if ("\"pid\"".equals(myname)) {
								// 协助公示通知书文号
								namesss = strna.substring(1, strna.length() - 1);
								mystrdetilurl=mystrdetilurl+"/"+ namesss;
								mystrdetilurl = mystrdetilurl + "/" + num;			
								urllist.add(mystrdetilurl);
							}

						}
						List<String> gqdjxx_list = new ArrayList<String>();
						for(int i=0;i<urllist.size();i++){
							HtmlPage sfxzgsxx_page_detail = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(urllist.get(i))));
							gqdjxx_list.add(sfxzgsxx_page_detail.asXml());
						}
						resultHtmlMap.put("sfxzgsxx_gqdjxx_detail", gqdjxx_list);
						}						
						break;
					}
				}
				if (!matchFlag) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}
			return resultHtmlMap;
	}	

	// 宁夏数据
	private Map<String, Object> getHtmlInfoMapOfNingxia(String area,
			HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER)
			throws Exception {

		LOGGER.info("=========" + area + "=========" + keyword + "=========");

		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();

		if (null == firstInfoPage) {

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);

		} else {

			final String HOST_OF_NINGXIA = "http://gsxt.ngsh.gov.cn/ECPS/";

			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage
					.getByXPath("//dl[@id='qyList']/div/dt/a");
			DomNodeList<DomElement> dt_list = firstInfoPage
					.getElementsByTagName("dt");

			if (null == anchors || anchors.isEmpty()) {
				if (null == dt_list || dt_list.isEmpty()) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.IMAGECODE_ERROR);
				} else {
					boolean flag1 = false;
					for (DomElement dtElement : dt_list) {
						if (dtElement.asXml().contains("您搜索的条件无查询结果")) {
							flag1 = true;
							break;
						}
					}
					if (flag1) {
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.NO_DATA_FOUND);
					} else {
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.IMAGECODE_ERROR);
					}
				}
			}

			HtmlAnchor htmlAnchor = null;
			boolean flag = false;

			if (anchors != null && !anchors.isEmpty()) {
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString()
							.trim();
					if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目
						htmlAnchor = anchor;
						flag = true;
						break;
					}
				}
				if (!flag) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}

			if (flag) {

				// 保存列表页目标条目信息
				HtmlElement target_item_info = (HtmlElement) htmlAnchor
						.getParentNode().getParentNode();
				resultHtmlMap.put("target_item_info", target_item_info.asXml());

				// 点击列表页目标条目，获取工商公示信息
				HtmlPage gsgsxx = htmlAnchor.click();
				resultHtmlMap.put("gsgsxx", gsgsxx.asXml());

				HtmlElement nbxh_he = (HtmlElement) gsgsxx
						.getFirstByXPath("//input[@id='nbxh']");
				HtmlElement qylx_he = (HtmlElement) gsgsxx
						.getFirstByXPath("//input[@id='qylx']");
				HtmlElement qymc_he = (HtmlElement) gsgsxx
						.getFirstByXPath("//input[@id='qymc']");
				HtmlElement zch_he = (HtmlElement) gsgsxx
						.getFirstByXPath("//input[@id='zch']");
				HtmlElement qylxFlag_he = (HtmlElement) gsgsxx
						.getFirstByXPath("//input[@id='qylxFlag']");
				String nbxh = nbxh_he.getAttribute("value");
				String wd = htmlAnchor.getAttribute("href");
				if (null != wd) {
					nbxh = wd.split("=")[2].split("&")[0];
				}
				String qylx = qylx_he.getAttribute("value");
				String qymc = URLEncoder.encode(qymc_he.getAttribute("value"),
						"UTF-8");
				String zch = zch_he.getAttribute("value");
				String qylxFlag = qylxFlag_he.getAttribute("value");

				// 点击列表页目标条目，获取工商公示信息->登记信息->基本信息
				HtmlElement qyjbqk = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='qyjbqk']");
				String qyjbqkPage_str = null;
				if (null != qyjbqk) {
					String qyjbqk_src = HOST_OF_NINGXIA
							+ qyjbqk.getAttribute("src");
					HtmlPage qyjbqkPage = firstInfoPage.getWebClient().getPage(
							qyjbqk_src);
					qyjbqkPage_str = qyjbqkPage.asXml();
				} else {
					HtmlElement jbxx_tb = (HtmlElement) gsgsxx
							.getFirstByXPath("//div[@id='jibenxinxi']/iframe[1]");
					if (null != jbxx_tb) {
						String jbxx_tb_src = HOST_OF_NINGXIA
								+ jbxx_tb.getAttribute("src");
						HtmlPage jbxx_tbPage = firstInfoPage.getWebClient()
								.getPage(jbxx_tb_src);
						qyjbqkPage_str = jbxx_tbPage.asXml();
					}
				}
				resultHtmlMap.put("gsgsxx_djxx_jbxx", qyjbqkPage_str);

				// 请求获取工商公示信息->登记信息->投资人信息
				HtmlElement tzrFrame = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='tzrczxx']");
				if (null != tzrFrame) {
					String tzr_frame_src = HOST_OF_NINGXIA
							+ tzrFrame.getAttribute("src");
					HtmlPage tzrPage = firstInfoPage.getWebClient().getPage(
							tzr_frame_src);
					resultHtmlMap.put("gsgsxx_djxx_tzrxx", tzrPage.asXml());
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> htmlAnchors7 = (List<HtmlAnchor>) tzrPage
							.getByXPath("//table[1]/tbody/tr/td/a");
					if (null != htmlAnchors7 && !htmlAnchors7.isEmpty()) {
						List<Map<String, Object>> tzrxqs = new ArrayList<Map<String, Object>>();
						for (HtmlAnchor anchor2 : htmlAnchors7) {
							Map<String, Object> tzrxqMap = new HashMap<String, Object>();
							Page wdc = anchor2.click();
							HtmlPage tzrxq = null;
							if (null != wdc && wdc.isHtmlPage()) {
								tzrxq = (HtmlPage) wdc;
								tzrxqMap.put("gsgsxx_djxx_tzrxx_xq", tzrxq.asXml());
								tzrxqs.add(tzrxqMap);
							}
						}
						resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs", tzrxqs);
					}
				}

				// 请求获取工商公示信息->登记信息->变更信息
				HtmlElement bgxxFrame = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='qybgxx']");
				String bgxxPage_str = null;
				if (null != bgxxFrame) {
					// String bgxx_frame_src = HOST_OF_NINGXIA +
					// bgxxFrame.getAttribute("src");
					// HtmlPage bgxxPage =
					// firstInfoPage.getWebClient().getPage(bgxx_frame_src);
					String bgxx_frame_src = "http://gsxt.ngsh.gov.cn/ECPS/qybgxxAction_query.action?currPage=1&maxPerPage=1000&"
							+ bgxxFrame.getAttribute("src").split("\\?")[1];
					HtmlPage bgxxPage = firstInfoPage.getWebClient().getPage(
							bgxx_frame_src);
					bgxxPage_str = bgxxPage.asXml();
				}
				resultHtmlMap.put("gsgsxx_djxx_bgxx", bgxxPage_str);

				// 请求获取工商公示信息->备案信息->主要人员信息
				HtmlElement gsgsxx_baxx_zyryxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='qybaxxzyryxx']");
				String gsgsxx_baxx_zyryxx_str = null;
				if (null != gsgsxx_baxx_zyryxx) {
					String gsgsxx_baxx_zyryxx_src = HOST_OF_NINGXIA
							+ gsgsxx_baxx_zyryxx.getAttribute("src");
					HtmlPage gsgsxx_baxx_zyryxx_page = firstInfoPage
							.getWebClient().getPage(gsgsxx_baxx_zyryxx_src);
					gsgsxx_baxx_zyryxx_str = gsgsxx_baxx_zyryxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_baxx_zyryxx", gsgsxx_baxx_zyryxx_str);

				// 请求获取工商公示信息->备案信息->分支机构信息
				HtmlElement gsgsxx_baxx_fzjgxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='qybaxxfgsxx']");
				String gsgsxx_baxx_fzjgxx_str = null;
				if (null != gsgsxx_baxx_fzjgxx) {
					String gsgsxx_baxx_fzjgxx_src = HOST_OF_NINGXIA
							+ gsgsxx_baxx_fzjgxx.getAttribute("src");
					HtmlPage gsgsxx_baxx_fzjgxx_page = firstInfoPage
							.getWebClient().getPage(gsgsxx_baxx_fzjgxx_src);
					gsgsxx_baxx_fzjgxx_str = gsgsxx_baxx_fzjgxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_baxx_fzjgxx", gsgsxx_baxx_fzjgxx_str);

				// 请求获取工商公示信息->备案信息->清算信息
				HtmlElement gsgsxx_baxx_qsxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='qybaxxqsxx']");
				String gsgsxx_baxx_qsxx_str = null;
				if (null != gsgsxx_baxx_qsxx) {
					String gsgsxx_baxx_qsxx_src = HOST_OF_NINGXIA
							+ gsgsxx_baxx_qsxx.getAttribute("src");
					HtmlPage gsgsxx_baxx_qsxx_page = firstInfoPage
							.getWebClient().getPage(gsgsxx_baxx_qsxx_src);
					gsgsxx_baxx_qsxx_str = gsgsxx_baxx_qsxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_baxx_qsxx", gsgsxx_baxx_qsxx_str);

				// 请求获取工商公示信息->动产抵押登记信息->动产抵押登记信息
				HtmlElement gsgsxx_dcdydjxx_dcdydjxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='dcdyxx']");
				String gsgsxx_dcdydjxx_dcdydjxx_string = null;
				if (null != gsgsxx_dcdydjxx_dcdydjxx) {
					String gsgsxx_dcdydjxx_dcdydjxx_src = HOST_OF_NINGXIA
							+ gsgsxx_dcdydjxx_dcdydjxx.getAttribute("src");
					HtmlPage gsgsxx_dcdydjxx_dcdydjxx_page = firstInfoPage
							.getWebClient().getPage(
									gsgsxx_dcdydjxx_dcdydjxx_src);
					gsgsxx_dcdydjxx_dcdydjxx_string = gsgsxx_dcdydjxx_dcdydjxx_page
							.asXml();
					// 请求获取工商公示信息->动产抵押登记信息->动产抵押登记信息->详情
					if (null != gsgsxx_dcdydjxx_dcdydjxx_page) {
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> wcqs = (List<HtmlAnchor>) gsgsxx_dcdydjxx_dcdydjxx_page
								.getByXPath("//table[1]/tbody/tr/td/a");
						if (null != wcqs && wcqs.size() > 0) {
							List<Map<String, Object>> gsgsxx_dcdydjxx_xqs = new ArrayList<Map<String, Object>>();
							for (HtmlAnchor wcq : wcqs) {
								Map<String, Object> wdq = new HashMap<String, Object>();
								HtmlPage gsgsxx_dcdydjxx_xq = wcq.click();
								// 动产抵押登记信息
								HtmlElement dcdydjxx = gsgsxx_dcdydjxx_xq
										.getFirstByXPath("//iframe[@id='dydjxx']");
								String dcdydjxx_str = null;
								if (null != dcdydjxx) {
									String dcdydjxx_src = HOST_OF_NINGXIA
											+ dcdydjxx.getAttribute("src");
									HtmlPage dcdydjxx_page = firstInfoPage
											.getWebClient().getPage(
													dcdydjxx_src);
									dcdydjxx_str = dcdydjxx_page.asXml();
								}
								wdq.put("dcdydjxx", dcdydjxx_str);
								// 抵押权人概况
								HtmlElement dyqrgk = gsgsxx_dcdydjxx_xq
										.getFirstByXPath("//iframe[@id='dyqrgk']");
								String dyqrgk_str = null;
								if (null != dyqrgk) {
									String dyqrgk_src = HOST_OF_NINGXIA
											+ dyqrgk.getAttribute("src");
									HtmlPage dyqrgk_page = firstInfoPage
											.getWebClient().getPage(dyqrgk_src);
									dyqrgk_str = dyqrgk_page.asXml();
								}
								wdq.put("dyqrgk", dyqrgk_str);
								// 被担保债权概况
								HtmlElement bdbzqgk = gsgsxx_dcdydjxx_xq
										.getFirstByXPath("//iframe[@id='bdbzqgk']");
								String bdbzqgk_str = null;
								if (null != bdbzqgk) {
									String bdbzqgk_src = HOST_OF_NINGXIA
											+ bdbzqgk.getAttribute("src");
									HtmlPage bdbzqgk_page = firstInfoPage
											.getWebClient()
											.getPage(bdbzqgk_src);
									bdbzqgk_str = bdbzqgk_page.asXml();
								}
								wdq.put("bdbzqgk", bdbzqgk_str);
								// 抵押物概况
								HtmlElement dywgk = gsgsxx_dcdydjxx_xq
										.getFirstByXPath("//iframe[@id='dywgk']");
								String dywgk_str = null;
								if (null != dywgk) {
									String dywgk_src = HOST_OF_NINGXIA
											+ dywgk.getAttribute("src");
									HtmlPage dywgk_page = firstInfoPage
											.getWebClient().getPage(dywgk_src);
									dywgk_str = dywgk_page.asXml();
								}
								wdq.put("dywgk", dywgk_str);
								// 变更
								HtmlElement bg = gsgsxx_dcdydjxx_xq
										.getFirstByXPath("//iframe[@id='dybgxx']");
								String bg_str = null;
								if (null != bg) {
									String bg_src = HOST_OF_NINGXIA
											+ bg.getAttribute("src");
									HtmlPage bg_page = firstInfoPage
											.getWebClient().getPage(bg_src);
									bg_str = bg_page.asXml();
								}
								wdq.put("bg", bg_str);
								// 注销
								HtmlElement zx = gsgsxx_dcdydjxx_xq
										.getFirstByXPath("//iframe[@id='zxxx']");
								String zx_str = null;
								if (null != zx) {
									String zx_src = HOST_OF_NINGXIA
											+ bg.getAttribute("src");
									HtmlPage zx_page = firstInfoPage
											.getWebClient().getPage(zx_src);
									bg_str = zx_page.asXml();
								}
								wdq.put("zx", zx_str);
								gsgsxx_dcdydjxx_xqs.add(wdq);
							}
							resultHtmlMap.put("gsgsxx_dcdydjxx_xqs",
									gsgsxx_dcdydjxx_xqs);
						}
					}
				}
				resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx",
						gsgsxx_dcdydjxx_dcdydjxx_string);

				// 请求获取工商公示信息->股权出质登记信息->股权出质登记信息
				HtmlElement gsgsxx_gqczdjxx_gqczdjxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='gqczxx']");
				String gsgsxx_gqczdjxx_gqczdjxx_string = null;
				if (null != gsgsxx_gqczdjxx_gqczdjxx) {
					String gsgsxx_gqczdjxx_gqczdjxx_src = HOST_OF_NINGXIA
							+ gsgsxx_gqczdjxx_gqczdjxx.getAttribute("src");
					HtmlPage gsgsxx_gqczdjxx_gqczdjxx_page = firstInfoPage
							.getWebClient().getPage(
									gsgsxx_gqczdjxx_gqczdjxx_src);
					gsgsxx_gqczdjxx_gqczdjxx_string = gsgsxx_gqczdjxx_gqczdjxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_gqczdjxx_gqczdjxx",
						gsgsxx_gqczdjxx_gqczdjxx_string);

				// 请求获取工商公示信息->行政处罚信息->行政处罚信息
				HtmlElement gsgsxx_xzcfxx_xzcfxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='xzcfxx']");
				String gsgsxx_xzcfxx_xzcfxx_string = null;
				if (null != gsgsxx_xzcfxx_xzcfxx) {
					String gsgsxx_xzcfxx_xzcfxx_src = HOST_OF_NINGXIA
							+ gsgsxx_xzcfxx_xzcfxx.getAttribute("src");
					HtmlPage gsgsxx_xzcfxx_xzcfxx_page = firstInfoPage
							.getWebClient().getPage(gsgsxx_xzcfxx_xzcfxx_src);
					gsgsxx_xzcfxx_xzcfxx_string = gsgsxx_xzcfxx_xzcfxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx",
						gsgsxx_xzcfxx_xzcfxx_string);

				// 请求获取工商公示信息->经营异常信息->经营异常信息
				HtmlElement gsgsxx_jyycxx_jyycxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='jyycxx']");
				String gsgsxx_jyycxx_jyycxx_string = null;
				if (null != gsgsxx_jyycxx_jyycxx) {
					String gsgsxx_jyycxx_jyycxx_src = HOST_OF_NINGXIA
							+ gsgsxx_jyycxx_jyycxx.getAttribute("src");
					HtmlPage gsgsxx_jyycxx_jyycxx_page = firstInfoPage
							.getWebClient().getPage(gsgsxx_jyycxx_jyycxx_src);
					gsgsxx_jyycxx_jyycxx_string = gsgsxx_jyycxx_jyycxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_jyycxx_jyycxx",
						gsgsxx_jyycxx_jyycxx_string);

				// 请求获取工商公示信息->严重违法信息->严重违法信息
				HtmlElement gsgsxx_yzwfxx_yzwfxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='yzwfxx']");
				String gsgsxx_yzwfxx_yzwfxx_str = null;
				if (null != gsgsxx_yzwfxx_yzwfxx) {
					String gsgsxx_yzwfxx_yzwfxx_src = HOST_OF_NINGXIA
							+ gsgsxx_yzwfxx_yzwfxx.getAttribute("src");
					HtmlPage gsgsxx_yzwfxx_yzwfxx_page = firstInfoPage
							.getWebClient().getPage(gsgsxx_yzwfxx_yzwfxx_src);
					gsgsxx_yzwfxx_yzwfxx_str = gsgsxx_yzwfxx_yzwfxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_yzwfxx_yzwfxx",
						gsgsxx_yzwfxx_yzwfxx_str);

				// 请求获取工商公示信息->抽查检查信息->抽查检查信息
				HtmlElement gsgsxx_ccjcxx_ccjcxx = (HtmlElement) gsgsxx
						.getFirstByXPath("//iframe[@id='ccjcxx']");
				String gsgsxx_ccjcxx_ccjcxx_str = null;
				if (null != gsgsxx_ccjcxx_ccjcxx) {
					String gsgsxx_ccjcxx_ccjcxx_src = HOST_OF_NINGXIA
							+ gsgsxx_ccjcxx_ccjcxx.getAttribute("src");
					HtmlPage gsgsxx_ccjcxx_ccjcxx_page = firstInfoPage
							.getWebClient().getPage(gsgsxx_ccjcxx_ccjcxx_src);
					gsgsxx_ccjcxx_ccjcxx_str = gsgsxx_ccjcxx_ccjcxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_ccjcxx_ccjcxx",
						gsgsxx_ccjcxx_ccjcxx_str);

				// 请求获取企业公示信息->企业年报->列表
				String qygsxx_url = "http://gsxt.ngsh.gov.cn/ECPS/qygsAction_initQygsMain.action?nbxh="
						+ nbxh
						+ "&qylxFlag="
						+ qylxFlag
						+ "&qymc="
						+ qymc
						+ "&qylx=" + qylx + "&zch=" + zch;
				HtmlPage qygsxx = firstInfoPage.getWebClient().getPage(
						qygsxx_url);

				// 请求获取企业公示信息->企业年报->列表
				HtmlElement qynb = (HtmlElement) qygsxx
						.getFirstByXPath("//*[@id='qynb']/iframe");
				if (null != qynb) {
					String qynb_src = HOST_OF_NINGXIA
							+ qynb.getAttribute("src");
					HtmlPage qygsxx_qynb_list_page = firstInfoPage
							.getWebClient().getPage(qynb_src);
					resultHtmlMap.put("qygsxx_qynb_list_page", qygsxx_qynb_list_page.asXml());

					// 请求获取 企业公示信息->企业年报->详情 （1_3包含的情况较多 不一定全 ）
					@SuppressWarnings("unchecked")
					List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_list_page
							.getByXPath("//table/tbody/tr/td/a");
					List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
					if (qygsxx_qynb_list_as != null
							&& !qygsxx_qynb_list_as.isEmpty()) {
						for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
							Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
							String qygsxx_qynb_list_a_href = HOST_OF_NINGXIA
									+ qygsxx_qynb_list_a.getAttribute("href");
							String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a
									.getTextContent();
							// String qygsxx_qynb_list_pubdate = ((HtmlElement) qygsxx_qynb_list_a
									// .getParentNode().getNextSibling()).getTextContent();
//							String qygsxx_qynb_list_pubdate = "";
//							try {
//								qygsxx_qynb_list_pubdate = String.valueOf(((HtmlElement) qygsxx_qynb_list_a
//										.getParentNode().getNextSibling())
//										.getTextContent());
//							} catch (Exception e) {
//								String wdd = qygsxx_qynb_list_a.getParentNode().getParentNode().getTextContent();
//								String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
//								Pattern pattern = Pattern.compile(eL);
//								Matcher matcher = pattern.matcher(wdd);
//							    if(matcher.find()){
//							    	qygsxx_qynb_list_pubdate = matcher.group(0);
//							    }
//							}
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_href",
									qygsxx_qynb_list_a_href);
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",
									qygsxx_qynb_list_a_text);
							// qygsxx_qynb_info_map.put(
									// "qygsxx_qynb_list_pubdate", qygsxx_qynb_list_pubdate);
							HtmlPage qygsxx_qynb_info_page = firstInfoPage
									.getWebClient().getPage(
											qygsxx_qynb_list_a_href);
							// 获取企业年报详情 企业基本信息
							HtmlElement qygsxx_qynb_info_qyjbqk_iframe = (HtmlElement) qygsxx_qynb_info_page
									.getFirstByXPath("//iframe[@id='qyjbqk']");
							String qygsxx_qynb_info_qyjbqk_str = null;
							if (null != qygsxx_qynb_info_qyjbqk_iframe) {
								String qygsxx_qynb_info_qyjbqk_src = HOST_OF_NINGXIA
										+ qygsxx_qynb_info_qyjbqk_iframe
												.getAttribute("src");
								HtmlPage qygsxx_qynb_info_qyjbqk_page = firstInfoPage
										.getWebClient().getPage(
												qygsxx_qynb_info_qyjbqk_src);
								qygsxx_qynb_info_qyjbqk_str = qygsxx_qynb_info_qyjbqk_page
										.asXml();
							}
							qygsxx_qynb_info_map.put("qygsxx_qynb_info_qyjbqk",
									qygsxx_qynb_info_qyjbqk_str);
							// 获取企业年报详情 企业资产状况信息|生产经营情况
							HtmlElement qygsxx_qynb_info_qyzcxx_iframe = (HtmlElement) qygsxx_qynb_info_page
									.getFirstByXPath("//iframe[@id='qyzcxx']");
							String qygsxx_qynb_info_qyzcxx_str = null;
							if (null != qygsxx_qynb_info_qyzcxx_iframe) {
								String qygsxx_qynb_info_qyzcxx_src = HOST_OF_NINGXIA
										+ qygsxx_qynb_info_qyzcxx_iframe
												.getAttribute("src");
								Page qygsxx_qynb_info_qyzcxx_wc = firstInfoPage
										.getWebClient().getPage(
												qygsxx_qynb_info_qyzcxx_src);
								if (null != qygsxx_qynb_info_qyzcxx_wc && qygsxx_qynb_info_qyzcxx_wc.isHtmlPage()) {
									HtmlPage qygsxx_qynb_info_qyzcxx_page = (HtmlPage) qygsxx_qynb_info_qyzcxx_wc;
									qygsxx_qynb_info_qyzcxx_str = qygsxx_qynb_info_qyzcxx_page
											.asXml();
								}
							}
							qygsxx_qynb_info_map.put("qygsxx_qynb_info_qyzcxx",
									qygsxx_qynb_info_qyzcxx_str);
							// 获取企业年报详情 股东及出资信息
							HtmlElement qygsxx_qynb_info_gdjczxx_iframe = (HtmlElement) qygsxx_qynb_info_page
									.getFirstByXPath("//iframe[@id='tzrczxx']");
							String qygsxx_qynb_info_gdjczxx_str = null;
							if (qygsxx_qynb_info_gdjczxx_iframe != null) {
								String qygsxx_qynb_info_gdjczxx_url = HOST_OF_NINGXIA
										+ qygsxx_qynb_info_gdjczxx_iframe
												.getAttribute("src");
								HtmlPage qygsxx_qynb_info_gdjczxx_page = firstInfoPage
										.getWebClient().getPage(
												qygsxx_qynb_info_gdjczxx_url);
								qygsxx_qynb_info_gdjczxx_str = qygsxx_qynb_info_gdjczxx_page
										.asXml();
							}
							qygsxx_qynb_info_map.put(
									"qygsxx_qynb_info_gdjczxx_page",
									qygsxx_qynb_info_gdjczxx_str);
							// 获取企业年报详情 对外投资信息
							HtmlElement qygsxx_qynb_info_dwtzxx_iframe = (HtmlElement) qygsxx_qynb_info_page
									.getFirstByXPath("//iframe[@id='dwtzxx']");
							String qygsxx_qynb_info_dwtzxx_str = null;
							if (qygsxx_qynb_info_dwtzxx_iframe != null) {
								String qygsxx_qynb_info_dwtzxx_url = HOST_OF_NINGXIA
										+ qygsxx_qynb_info_dwtzxx_iframe
												.getAttribute("src");
								HtmlPage qygsxx_qynb_info_dwtzxx_page = firstInfoPage
										.getWebClient()
										.getPage(
												qygsxx_qynb_info_dwtzxx_url);
								qygsxx_qynb_info_dwtzxx_str = qygsxx_qynb_info_dwtzxx_page
										.asXml();
							}
							qygsxx_qynb_info_map.put(
									"qygsxx_qynb_info_dwtzxx_page",
									qygsxx_qynb_info_dwtzxx_str);
							// 获取企业年报详情 对外提供保证担保信息
							HtmlElement qygsxx_qynb_info_dwtgbzdbxx_iframe = (HtmlElement) qygsxx_qynb_info_page
									.getFirstByXPath("//iframe[@id='gqbgxx']");
							String qygsxx_qynb_info_dwtgbzdbxx_str = null;
							if (qygsxx_qynb_info_dwtgbzdbxx_iframe != null) {
								String qygsxx_qynb_info_dwtgbzdbxx_url = HOST_OF_NINGXIA
										+ qygsxx_qynb_info_dwtgbzdbxx_iframe
												.getAttribute("src");
								HtmlPage qygsxx_qynb_info_dwtgbzdbxx_page = firstInfoPage
										.getWebClient()
										.getPage(
												qygsxx_qynb_info_dwtgbzdbxx_url);
								qygsxx_qynb_info_dwtgbzdbxx_str = qygsxx_qynb_info_dwtgbzdbxx_page
										.asXml();
							}
							qygsxx_qynb_info_map.put(
									"qygsxx_qynb_info_dwtgbzdbxx_page",
									qygsxx_qynb_info_dwtgbzdbxx_str);
							// 获取企业年报详情 修改记录
							HtmlElement qygsxx_qynb_info_xgjl_iframe = (HtmlElement) qygsxx_qynb_info_page
									.getFirstByXPath("//iframe[@id='xgjlxx']");
							String qygsxx_qynb_info_xgjl_str = null;
							if (qygsxx_qynb_info_xgjl_iframe != null) {
								String qygsxx_qynb_info_xgjl_url = HOST_OF_NINGXIA
										+ qygsxx_qynb_info_xgjl_iframe
												.getAttribute("src");
								HtmlPage qygsxx_qynb_info_xgjl_page = firstInfoPage
										.getWebClient().getPage(
												qygsxx_qynb_info_xgjl_url);
								qygsxx_qynb_info_xgjl_str = qygsxx_qynb_info_xgjl_page
										.asXml();
							}
							qygsxx_qynb_info_map.put(
									"qygsxx_qynb_info_xgjl_page",
									qygsxx_qynb_info_xgjl_str);
							// 获取企业年报详情 网站或网店信息
							HtmlElement qygsxx_qynb_info_wzhwdxx_iframe = (HtmlElement) qygsxx_qynb_info_page
									.getFirstByXPath("//iframe[@id='wzxx']");
							String qygsxx_qynb_info_wzhwdxx_str = null;
							if (qygsxx_qynb_info_wzhwdxx_iframe != null) {
								String qygsxx_qynb_info_wzhwdxx_url = HOST_OF_NINGXIA
										+ qygsxx_qynb_info_wzhwdxx_iframe
												.getAttribute("src");
								HtmlPage qygsxx_qynb_info_wzhwdxx_page = firstInfoPage
										.getWebClient().getPage(
												qygsxx_qynb_info_wzhwdxx_url);
								qygsxx_qynb_info_wzhwdxx_str = qygsxx_qynb_info_wzhwdxx_page
										.asXml();
							}
							qygsxx_qynb_info_map.put(
									"qygsxx_qynb_info_wzhwdxx_page",
									qygsxx_qynb_info_wzhwdxx_str);
							qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
						}
					}
					resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);
				}

				// 请求获取 企业公示信息->股东及出资信息
				HtmlElement qygsxx_gdjczxx = (HtmlElement) qygsxx
						.getFirstByXPath("//iframe[@id='tzrxxframe']");
				String qygsxx_gdjczxx_str = null;
				if (null != qygsxx_gdjczxx) {
					String qygsxx_gdjczxx_src = HOST_OF_NINGXIA
							+ qygsxx_gdjczxx.getAttribute("src");
					HtmlPage qygsxx_gdjczxx_page = firstInfoPage.getWebClient()
							.getPage(qygsxx_gdjczxx_src);
					qygsxx_gdjczxx_str = qygsxx_gdjczxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx_str);

				// 请求获取 企业公示信息->股权变更信息
				HtmlElement qygsxx_gqbgxx = (HtmlElement) qygsxx
						.getFirstByXPath("//div[@id='gqbg']/iframe[1]");
				String qygsxx_gqbgxx_str = null;
				if (null != qygsxx_gqbgxx) {
					String qygsxx_gqbgxx_src = HOST_OF_NINGXIA
							+ qygsxx_gqbgxx.getAttribute("src");
					HtmlPage qygsxx_gqbgxx_page = firstInfoPage.getWebClient()
							.getPage(qygsxx_gqbgxx_src);
					qygsxx_gqbgxx_str = qygsxx_gqbgxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx_str);

				// 请求获取 企业公示信息->行政许可信息
				HtmlElement qygsxx_xzxkxx = (HtmlElement) qygsxx
						.getFirstByXPath("//iframe[@id='xzxkxx']");
				String qygsxx_xzxkxx_str = null;
				if (null != qygsxx_xzxkxx) {
					String qygsxx_xzxkxx_src = HOST_OF_NINGXIA
							+ qygsxx_xzxkxx.getAttribute("src");
					HtmlPage qygsxx_xzxkxx_page = firstInfoPage.getWebClient()
							.getPage(qygsxx_xzxkxx_src);
					qygsxx_xzxkxx_str = qygsxx_xzxkxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx_str);

				// 请求获取 企业公示信息->知识产权出质登记信息
				HtmlElement qygsxx_zscqczdjxx = (HtmlElement) qygsxx
						.getFirstByXPath("//iframe[@id='zscq']");
				String qygsxx_zscqczdjxx_str = null;
				if (null != qygsxx_zscqczdjxx) {
					String qygsxx_zscqczdjxx_src = HOST_OF_NINGXIA
							+ qygsxx_zscqczdjxx.getAttribute("src");
					HtmlPage qygsxx_zscqczdjxx_page = firstInfoPage
							.getWebClient().getPage(qygsxx_zscqczdjxx_src);
					qygsxx_zscqczdjxx_str = qygsxx_zscqczdjxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx_str);

				// 请求获取企业公示信息->行政处罚信息
				HtmlElement qygsxx_xzcfxx = (HtmlElement) qygsxx
						.getFirstByXPath("//div[@id='xzcf']/iframe[1]");
				String qygsxx_xzcfxx_str = null;
				if (null != qygsxx_xzcfxx) {
					String qygsxx_xzcfxx_src = HOST_OF_NINGXIA
							+ qygsxx_xzcfxx.getAttribute("src");
					HtmlPage qygsxx_xzcfxx_page = firstInfoPage.getWebClient()
							.getPage(qygsxx_xzcfxx_src);
					qygsxx_xzcfxx_str = qygsxx_xzcfxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx_str);

				// 请求获取 其他部门公示信息
				String qtbmgsxx_xzxkxx_url = "http://gsxt.ngsh.gov.cn/ECPS/qtgsAction_initQtgsMain.action?nbxh="
						+ nbxh
						+ "&qylxFlag="
						+ qylxFlag
						+ "&qymc="
						+ qymc
						+ "&qylx=" + qylx + "&zch=" + zch;
				HtmlPage qtbmgsxx = firstInfoPage.getWebClient().getPage(
						qtbmgsxx_xzxkxx_url);

				// 请求获取 其他部门公示信息->行政许可信息
				HtmlElement qtbmgsxx_xzxkxx = (HtmlElement) qtbmgsxx
						.getFirstByXPath("//iframe[@id='xzxkxx']");
				String qtbmgsxx_xzxkxx_str = null;
				if (null != qtbmgsxx_xzxkxx) {
					String qtbmgsxx_xzxkxx_src = HOST_OF_NINGXIA
							+ qtbmgsxx_xzxkxx.getAttribute("src");
					HtmlPage qtbmgsxx_xzxkxx_page = firstInfoPage
							.getWebClient().getPage(qtbmgsxx_xzxkxx_src);
					qtbmgsxx_xzxkxx_str = qtbmgsxx_xzxkxx_page.asXml();
				}
				resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx_str);

				// 请求获取其他部门公示信息->行政处罚信息
				HtmlElement qtbmgsxx_xzcfxx = (HtmlElement) qtbmgsxx
						.getFirstByXPath("//iframe[@id='xzcfxx']");
				String qtbmgsxx_xzcfxx_str = null;
				if (null != qtbmgsxx_xzcfxx) {
					String qtbmgsxx_xzcfxx_src = HOST_OF_NINGXIA
							+ qtbmgsxx_xzcfxx.getAttribute("src");
					HtmlPage qtbmgsxx_xzcfxx_page = firstInfoPage
							.getWebClient().getPage(qtbmgsxx_xzcfxx_src);
					qtbmgsxx_xzcfxx_str = qtbmgsxx_xzcfxx_page.asXml();
				}
				resultHtmlMap.put("qtbmgsxx_xzcfxx", qtbmgsxx_xzcfxx_str);

				// 请求获取司法协助公示信息
				String sfxzgsxx_url = "http://gsxt.ngsh.gov.cn/ECPS/sfxzAction_initSfxzMain.action?nbxh="
						+ nbxh + "&qylxFlag=" + qylxFlag;
				HtmlPage sfxzgsxx_page = firstInfoPage.getWebClient().getPage(
						sfxzgsxx_url);
				String sfxzgsxx_str = null;
				if (null != sfxzgsxx_page) {
					sfxzgsxx_str = sfxzgsxx_page.asXml();
				}
				resultHtmlMap.put("sfxzgsxx", sfxzgsxx_str);

				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);

			}
		}

		LOGGER.returnRedisResource();

		return resultHtmlMap;

	}

	// 辽宁数据
	private Map<String, Object> getHtmlInfoMapOfLiaoning(String area,
			HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER)
			throws Exception {

		LOGGER.info("=========" + area + "=========" + keyword + "=========");

		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();

		if (null == firstInfoPage) {

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);

		} else {

			WebWindow webWindow = firstInfoPage.getWebClient()
					.getCurrentWindow();

			final String HOST_OF_LIAONING = "http://gsxt.lngs.gov.cn";

			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage
					.getByXPath("//div[@id='listContent']/div/ul/li/a");
			HtmlElement div_none = firstInfoPage
					.getFirstByXPath("//div[@class='list-a']");

			if (null == anchors || anchors.isEmpty()) {
				if (null == div_none) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.IMAGECODE_ERROR);
				} else {
					if (div_none.asXml().contains("您搜索的条件无查询结果")) {
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.NO_DATA_FOUND);
					} else {
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.IMAGECODE_ERROR);
					}
				}
			}

			HtmlAnchor htmlAnchor = null;
			boolean flag = false;

			if (anchors != null && !anchors.isEmpty()) {
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString()
							.trim();
					if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目
						htmlAnchor = anchor;
						flag = true;
						break;
					}
				}
				if (!flag) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}

			if (flag) {

				// 公共参数
				String pripid = "";
				String type = "";

				// 保存列表页目标条目信息
				HtmlElement target_item_info = (HtmlElement) htmlAnchor
						.getParentNode().getParentNode();
				resultHtmlMap.put("target_item_info", target_item_info.asXml());
				String liaoning_onclick = htmlAnchor.getAttribute("onclick");
				if (!StringUtils.isEmpty(liaoning_onclick)) {
					pripid = liaoning_onclick.split(",")[2].replace("'", "")
							.replace("'", "");
					type = liaoning_onclick.split(",")[1].replace("'", "")
							.replace("'", "");
				}

				// 点击列表页目标条目，获取工商公示信息
				HtmlPage gsgsxx = htmlAnchor.click();
				Thread.sleep(3000);
				Document gsgsxx_dm = Jsoup.parseBodyFragment(gsgsxx.asXml());
				Element s_gs_dj_1 = gsgsxx_dm.getElementById("s_gs_dj_1");
				// Element s_gs_dj_2 = gsgsxx_dm.getElementById("s_gs_dj_2");

				// 点击列表页目标条目，获取工商公示信息->登记信息->基本信息
				// String jbxx_url =
				// "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getJbxxAction.action?pripid="
				// + pripid + "&type=" + type;
				// HtmlPage gsgsxx_djxx_jbxx = firstInfoPage.getWebClient()
				// .getPage(jbxx_url);
				String gsgsxx_djxx_jbxx_str = null;
				if (null != s_gs_dj_1) {
					gsgsxx_djxx_jbxx_str = s_gs_dj_1.html();
				}
				resultHtmlMap.put("gsgsxx_djxx_jbxx", gsgsxx_djxx_jbxx_str);

				// 请求获取工商公示信息->登记信息->投资人信息
				//String gsgsxx_djxx_tzrxx_str = null;
				//if (null != s_gs_dj_2) {
				//	gsgsxx_djxx_tzrxx_str = s_gs_dj_2.html();
				//}
				//resultHtmlMap.put("gsgsxx_djxx_tzrxx", gsgsxx_djxx_tzrxx_str);
				// 点击列表页目标条目，获取工商公示信息（登记信息->股东信息->详情）
				@SuppressWarnings("unchecked")
				List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx
						.getByXPath("//tbody[@id='tzr_itemContainer']/tr/td/a");
				if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
					List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
					for (HtmlAnchor touziren_anchor : touziren_anchors) {
						Map<String, Object> gsgsxx_djxx_tzrxx_xq = new LinkedHashMap<String, Object>();
						HtmlPage gsgsxx_djxx_tzrxx_xq_page = touziren_anchor
								.click();
						gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",
								gsgsxx_djxx_tzrxx_xq_page.asXml());
						gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
					}
					resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",
							gsgsxx_djxx_tzrxx_xqs);
				}
				
				WebClient wc = firstInfoPage.getWebClient();
				wc.getOptions().setJavaScriptEnabled(false);
				// 请求获取工商公示信息->登记信息->投资人信息
				String tzr_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getTzrxxAction.action?pripid=" + pripid + "&type=" + type;
				HtmlPage tzrPage = wc.getPage(tzr_url);
				String gsgsxx_djxx_tzrxx_str = null;
				if (null != tzrPage) {
					gsgsxx_djxx_tzrxx_str = tzrPage.asXml();
				}
				resultHtmlMap.put("gsgsxx_djxx_tzrxx", gsgsxx_djxx_tzrxx_str);

				// 请求获取工商公示信息->登记信息->变更信息
				String bgxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getBgxxAction.action?pripid=" + pripid + "&type=" + type;
				HtmlPage bgxxPage = wc.getPage(webWindow,
						new WebRequest(new URL(bgxx_url)));
				// 请求获取工商公示信息->登记信息->变更信息
				String gsgsxx_djxx_bgxx_str = null;
				if (null != bgxxPage) {
					gsgsxx_djxx_bgxx_str = bgxxPage.asXml();
				}
				resultHtmlMap.put("gsgsxx_djxx_bgxx", gsgsxx_djxx_bgxx_str);
				// String[] command = {"casperjs",
				// "/home/ubuntu/nfs-images/casperjscode/getSimpleRequestPage.js",
				// "--web-security=no", "--url=" + bgxx_url};
				// String casperjsResult = CommandUtil.runCommand(command);
				// resultHtmlMap.put("gsgsxx_djxx_bgxx", casperjsResult);

				// 请求获取工商公示信息->备案信息->主要人员信息
				String gsgsxx_baxx_zyryxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getZyryxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_baxx_zyryxx_page = wc.getPage(webWindow,
						new WebRequest(new URL(gsgsxx_baxx_zyryxx_url)));
				String gsgsxx_baxx_zyryxx_str = null;
				if (null != gsgsxx_baxx_zyryxx_page) {
					gsgsxx_baxx_zyryxx_str = gsgsxx_baxx_zyryxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_baxx_zyryxx", gsgsxx_baxx_zyryxx_str);

				// 请求获取工商公示信息->备案信息->主管部门（出资人）信息
				String gsgsxx_baxx_zgbmxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getTzrxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_baxx_zgbmxx_page = wc.getPage(webWindow,
						new WebRequest(new URL(gsgsxx_baxx_zgbmxx_url)));
				String gsgsxx_baxx_zgbmxx_str = null;
				if (null != gsgsxx_baxx_zgbmxx_page) {
					gsgsxx_baxx_zgbmxx_str = gsgsxx_baxx_zgbmxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_baxx_zgbmxx", gsgsxx_baxx_zgbmxx_str);

				wc.getOptions().setJavaScriptEnabled(true);
				// 请求获取工商公示信息->备案信息->分支机构信息
				String gsgsxx_baxx_fzjgxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getFgsxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_baxx_fzjgxx_page = firstInfoPage.getWebClient()
						.getPage(gsgsxx_baxx_fzjgxx_url);
				String gsgsxx_baxx_fzjgxx_str = null;
				if (null != gsgsxx_baxx_fzjgxx_page) {
					gsgsxx_baxx_fzjgxx_str = gsgsxx_baxx_fzjgxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_baxx_fzjgxx", gsgsxx_baxx_fzjgxx_str);

				// 请求获取工商公示信息->备案信息->清算信息
				String gsgsxx_baxx_qsxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getQsxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_baxx_qsxx_page = firstInfoPage.getWebClient()
						.getPage(gsgsxx_baxx_qsxx_url);
				String gsgsxx_baxx_qsxx_str = null;
				if (null != gsgsxx_baxx_qsxx_page) {
					gsgsxx_baxx_qsxx_str = gsgsxx_baxx_qsxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_baxx_qsxx", gsgsxx_baxx_qsxx_str);

				// 请求获取工商公示信息->动产抵押登记信息->动产抵押登记信息
				String gsgsxx_dcdydjxx_dcdydjxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getDcdydjAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_dcdydjxx_dcdydjxx_page = firstInfoPage
						.getWebClient().getPage(gsgsxx_dcdydjxx_dcdydjxx_url);
				String gsgsxx_dcdydjxx_dcdydjxx_str = null;
				if (null != gsgsxx_dcdydjxx_dcdydjxx_page) {
					gsgsxx_dcdydjxx_dcdydjxx_str = gsgsxx_dcdydjxx_dcdydjxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx",
						gsgsxx_dcdydjxx_dcdydjxx_str);

				// 请求获取工商公示信息->股权出质登记信息->股权出质登记信息
				String gsgsxx_gqczdjxx_gqczdjxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getGsgsGqczxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_gqczdjxx_gqczdjxx_page = firstInfoPage
						.getWebClient().getPage(gsgsxx_gqczdjxx_gqczdjxx_url);
				String gsgsxx_gqczdjxx_gqczdjxx_str = null;
				if (null != gsgsxx_gqczdjxx_gqczdjxx_page) {
					gsgsxx_gqczdjxx_gqczdjxx_str = gsgsxx_gqczdjxx_gqczdjxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_gqczdjxx_gqczdjxx",
						gsgsxx_gqczdjxx_gqczdjxx_str);

				// 请求获取工商公示信息->行政处罚信息->行政处罚信息
				String gsgsxx_xzcfxx_xzcfxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getXzcfxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_xzcfxx_xzcfxx_page = firstInfoPage
						.getWebClient().getPage(gsgsxx_xzcfxx_xzcfxx_url);
				String gsgsxx_xzcfxx_xzcfxx_str = null;
				if (null != gsgsxx_xzcfxx_xzcfxx_page) {
					gsgsxx_xzcfxx_xzcfxx_str = gsgsxx_xzcfxx_xzcfxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx",
						gsgsxx_xzcfxx_xzcfxx_str);

				// 请求获取工商公示信息->经营异常信息->经营异常信息
				String gsgsxx_jyycxx_jyycxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getJyycxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_jyycxx_jyycxx_page = firstInfoPage
						.getWebClient().getPage(gsgsxx_jyycxx_jyycxx_url);
				String gsgsxx_jyycxx_jyycxx_str = null;
				if (null != gsgsxx_jyycxx_jyycxx_page) {
					gsgsxx_jyycxx_jyycxx_str = gsgsxx_jyycxx_jyycxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_jyycxx_jyycxx",
						gsgsxx_jyycxx_jyycxx_str);

				// 请求获取工商公示信息->严重违法信息->严重违法信息
				String gsgsxx_yzwfxx_yzwfxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getYzwfxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_yzwfxx_yzwfxx_page = firstInfoPage
						.getWebClient().getPage(gsgsxx_yzwfxx_yzwfxx_url);
				String gsgsxx_yzwfxx_yzwfxx_str = null;
				if (null != gsgsxx_yzwfxx_yzwfxx_page) {
					gsgsxx_yzwfxx_yzwfxx_str = gsgsxx_yzwfxx_yzwfxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_yzwfxx_yzwfxx",
						gsgsxx_yzwfxx_yzwfxx_str);

				// 请求获取工商公示信息->抽查检查信息->抽查检查信息
				String gsgsxx_ccjcxx_ccjcxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getCcjcxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gsgsxx_ccjcxx_ccjcxx_page = firstInfoPage
						.getWebClient().getPage(gsgsxx_ccjcxx_ccjcxx_url);
				String gsgsxx_ccjcxx_ccjcxx_str = null;
				if (null != gsgsxx_ccjcxx_ccjcxx_page) {
					gsgsxx_ccjcxx_ccjcxx_str = gsgsxx_ccjcxx_ccjcxx_page
							.asXml();
				}
				resultHtmlMap.put("gsgsxx_ccjcxx_ccjcxx",
						gsgsxx_ccjcxx_ccjcxx_str);

				// 请求获取企业公示信息->企业年报->列表
				String qygsxx_qynb_list_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getQygsQynbxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage qygsxx_qynb_list_page = firstInfoPage.getWebClient()
						.getPage(qygsxx_qynb_list_url);
				resultHtmlMap.put("qygsxx_qynb_list_page",
						qygsxx_qynb_list_page.asXml());

				// 请求获取企业公示信息->企业年报->详情 （1_3包含的情况较多 不一定全 样本：北京信和餐厅
				// 北京上容恒沛酒店管理有限公司 湖南上容信息技术有限公司北京分公司）
				@SuppressWarnings("unchecked")
				List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_list_page
						.getByXPath("//tbody[@id='qynbItemContainer']/tr/td[2]/a");
				List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
				if (qygsxx_qynb_list_as != null
						&& !qygsxx_qynb_list_as.isEmpty()) {
					for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
						Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
						String qygsxx_qynb_list_a_href = HOST_OF_LIAONING
								+ qygsxx_qynb_list_a.getAttribute("href");
						String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a
								.getTextContent();
						// String qygsxx_qynb_list_pubdate = ((HtmlElement)
						// qygsxx_qynb_list_a
						// .getParentNode().getNextSibling()).getTextContent();
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_href",
								qygsxx_qynb_list_a_href);
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",
								qygsxx_qynb_list_a_text);
						// qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",
						// qygsxx_qynb_list_pubdate);
						// 获取企业年报详情企业基本信息&(企业资产状况信息|生产经营情况)
						// 获取企业年报详情股东及出资信息
						// 获取企业年报详情 对外提供保证担保信息
						// 获取企业年报详情 修改记录
						// 获取企业年报详情 网站或网店信息
						HtmlPage qygsxx_qynb_info_page = firstInfoPage
								.getWebClient()
								.getPage(qygsxx_qynb_list_a_href);
						qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",
								qygsxx_qynb_info_page.asXml());
						qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
					}
				}
				resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

				// 请求获取企业公示信息->股东及出资信息
				String qygsxx_gdjczxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getQygsJsGdjczxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage qygsxx_gdjczxx_page = firstInfoPage.getWebClient()
						.getPage(qygsxx_gdjczxx_url);
				String qygsxx_gdjczxx_str = null;
				if (null != qygsxx_gdjczxx_page) {
					qygsxx_gdjczxx_str = qygsxx_gdjczxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx_str);

				// 请求获取企业公示信息->股东及出资信息->变更信息
				String qygsxx_gdjczxx_bgxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getQygsJsGdjczbgxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage qygsxx_gdjczxx_bgxx_page = firstInfoPage
						.getWebClient().getPage(qygsxx_gdjczxx_bgxx_url);
				String qygsxx_gdjczxx_bgxx_str = null;
				if (null != qygsxx_gdjczxx_page) {
					qygsxx_gdjczxx_bgxx_str = qygsxx_gdjczxx_bgxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_gdjczxx_bgxx",
						qygsxx_gdjczxx_bgxx_str);

				// 请求获取 企业公示信息->股权变更信息
				String qygsxx_gqbgxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getQygsJsGqbgxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage qygsxx_gqbgxx_page = firstInfoPage.getWebClient()
						.getPage(qygsxx_gqbgxx_url);
				String qygsxx_gqbgxx_str = null;
				if (null != qygsxx_gqbgxx_page) {
					qygsxx_gqbgxx_str = qygsxx_gqbgxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx_str);

				// 请求获取 企业公示信息->行政许可信息
				String qygsxx_xzxkxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getQygsJsXzxkxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage qygsxx_xzxkxx_page = firstInfoPage.getWebClient()
						.getPage(qygsxx_xzxkxx_url);
				String qygsxx_xzxkxx_str = null;
				if (null != qygsxx_xzxkxx_page) {
					qygsxx_xzxkxx_str = qygsxx_xzxkxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx_str);

				// 请求获取企业公示信息->知识产权出质登记信息
				String qygsxx_zscqczdjxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getQygsJsZscqczxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage qygsxx_zscqczdjxx_page = firstInfoPage.getWebClient()
						.getPage(qygsxx_zscqczdjxx_url);
				String qygsxx_zscqczdjxx_str = null;
				if (null != qygsxx_zscqczdjxx_page) {
					qygsxx_zscqczdjxx_str = qygsxx_zscqczdjxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx_str);

				// 请求获取企业公示信息->行政处罚信息
				String qygsxx_xzcfxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getQygsJsXzcfxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage qygsxx_xzcfxx_page = firstInfoPage.getWebClient()
						.getPage(qygsxx_xzcfxx_url);
				String qygsxx_xzcfxx_str = null;
				if (null != qygsxx_xzcfxx_page) {
					qygsxx_xzcfxx_str = qygsxx_xzcfxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx_str);

				// 请求获取司法协助公示信息->股权冻结信息
				String gqdjxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getSfgsGqdjxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gqdjxx_page = firstInfoPage.getWebClient().getPage(
						gqdjxx_url);
				String sfxzgsxx_gqdjxx_str = null;
				if (null != gqdjxx_page) {
					sfxzgsxx_gqdjxx_str = gqdjxx_page.asXml();
				}
				resultHtmlMap.put("sfxzgsxx_gqdjxx", sfxzgsxx_gqdjxx_str);

				// 请求获取司法协助公示信息->股东变更信息
				String gdbgxx_url = "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/getSfgsGdbgxxAction.action?pripid="
						+ pripid + "&type=" + type;
				HtmlPage gdbgxx_page = firstInfoPage.getWebClient().getPage(
						gdbgxx_url);
				String sfxzgsxx_gdbgxx_str = null;
				if (null != gdbgxx_page) {
					sfxzgsxx_gdbgxx_str = gdbgxx_page.asXml();
				}
				resultHtmlMap.put("sfxzgsxx_gdbgxx", sfxzgsxx_gdbgxx_str);

				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);

			}
		}

		LOGGER.returnRedisResource();

		return resultHtmlMap;

	}

	// 河北数据
	private Map<String, Object> getHtmlInfoMapOfHebei(String area,
			HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER)
			throws Exception {

		LOGGER.info("=========" + area + "=========" + keyword + "=========");

		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();

		if (null == firstInfoPage) {

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);

		} else {

			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage
					.getByXPath("//div[@class='search']/div[@class='list-info']/div[@class='list-item']/div[@class='link']/a");
			HtmlElement div_none = firstInfoPage
					.getFirstByXPath("//div[@class='page-container clearfix']");
			if (null == div_none) {
				resultHtmlMap.put("statusCodeDef",
						StatusCodeDef.IMAGECODE_ERROR);
			} else {
				resultHtmlMap.put("statusCodeDef",
						StatusCodeDef.NO_DATA_FOUND);
//				if (div_none.asXml().contains("您搜索的条件无查询结果")) {
//					resultHtmlMap.put("statusCodeDef",
//							StatusCodeDef.NO_DATA_FOUND);
//				} else {
//					resultHtmlMap.put("statusCodeDef",
//							StatusCodeDef.IMAGECODE_ERROR);
//				}
			}

			HtmlAnchor htmlAnchor = null;
			boolean flag = false;

			if (anchors != null && !anchors.isEmpty()) {
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString()
							.trim();
					if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目
						htmlAnchor = anchor;
						flag = true;
						break;
					}
				}
				if (!flag) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}

			if (flag) {

				// 保存列表页目标条目信息
				HtmlElement target_item_info = (HtmlElement) htmlAnchor
						.getParentNode().getParentNode();
				resultHtmlMap.put("target_item_info", target_item_info.asXml());

				String hebei_url = htmlAnchor.getHrefAttribute().toString();

				// 点击列表页目标条目，获取工商公示信息
				HtmlPage gsgsxx = firstInfoPage.getWebClient().getPage(
						hebei_url);
				resultHtmlMap.put("gsgsxx", gsgsxx.asXml());

				// 点击列表页目标条目，获取工商公示信息（登记信息->股东信息->详情）
				@SuppressWarnings("unchecked")
				List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx
						.getByXPath("//table[@id='investorTable']/tbody/tr/td/a");
				if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
					List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
					for (HtmlAnchor touziren_anchor : touziren_anchors) {
						Map<String, Object> gsgsxx_djxx_tzrxx_xq = new LinkedHashMap<String, Object>();
						Page tt = firstInfoPage.getWebClient().getPage(touziren_anchor.getHrefAttribute());
						if (null != tt && tt.isHtmlPage()) {
							HtmlPage gsgsxx_djxx_tzrxx_xq_page = (HtmlPage) tt;
							gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",
									gsgsxx_djxx_tzrxx_xq_page.asXml());
							gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
						}
//						HtmlPage gsgsxx_djxx_tzrxx_xq_page = touziren_anchor
//								.click();
//						gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",
//								gsgsxx_djxx_tzrxx_xq_page.asXml());
//						gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
					}
					resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",
							gsgsxx_djxx_tzrxx_xqs);
				}
				
				// 点击列表页目标条目，获取工商公示信息（动产抵押登记信息->动产抵押登记信息->详情）
				@SuppressWarnings("unchecked")
				List<HtmlAnchor> dcdydjxx_anchors = (List<HtmlAnchor>) gsgsxx
						.getByXPath("//table[@id='mortageTable']/tbody/tr/td/a");
				if (null != dcdydjxx_anchors && !dcdydjxx_anchors.isEmpty()) {
					List<Map<String, Object>> gsgsxx_dcdydjxx_dcdydjxx_xqs = new ArrayList<Map<String, Object>>();
					for (HtmlAnchor dcdydjxx_anchor : dcdydjxx_anchors) {
						Map<String, Object> gsgsxx_dcdydjxx_dcdydjxx_xq = new LinkedHashMap<String, Object>();
						// HtmlPage gsgsxx_dcdydjxx_dcdydjxx_xq_page = dcdydjxx_anchor.click();
						Page wd = firstInfoPage.getWebClient().getPage(dcdydjxx_anchor.getHrefAttribute());
						if(null != wd && wd.isHtmlPage()){
							HtmlPage wdHtmlPage = (HtmlPage) wd;
							gsgsxx_dcdydjxx_dcdydjxx_xq.put("gsgsxx_dcdydjxx_dcdydjxx_xq",
									wdHtmlPage.asXml());
							gsgsxx_dcdydjxx_dcdydjxx_xqs.add(gsgsxx_dcdydjxx_dcdydjxx_xq);
						}
					}
					resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx_xqs",
							gsgsxx_dcdydjxx_dcdydjxx_xqs);
				}

				// 请求获取 工商公示信息（行政处罚信息->行政处罚信息详情）
				// @SuppressWarnings("unchecked")
				// List<HtmlAnchor> xingzhengchufa_anchors =
				// (List<HtmlAnchor>) gsgsxx
				// .getByXPath("//div[@id='xingzhengchufa']/table/tbody/tr/td/a");
				// List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs = new
				// ArrayList<Map<String, Object>>();
				// if (null != xingzhengchufa_anchors
				// && xingzhengchufa_anchors.size() > 0) {
				// for (HtmlAnchor xingzhengchufa_anchor :
				// xingzhengchufa_anchors) {
				// Map<String, Object> gsgsxx_xzcfxx_xzcfxx_xq = new
				// LinkedHashMap<String, Object>();
				// HtmlPage gsgsxx_xzcfxx_xzcfxx_xq_page =
				// xingzhengchufa_anchor
				// .click();
				// gsgsxx_xzcfxx_xzcfxx_xq.put(
				// "gsgsxx_xzcfxx_xzcfxx_xq",
				// gsgsxx_xzcfxx_xzcfxx_xq_page.asXml());
				// gsgsxx_xzcfxx_xzcfxx_xqs
				// .add(gsgsxx_xzcfxx_xzcfxx_xq);
				// }
				// }
				// resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx_xqs",
				// gsgsxx_xzcfxx_xzcfxx_xqs);

				// 请求获取企业公示信息
				HtmlPage qygsxx_list_page = firstInfoPage.getWebClient()
						.getPage(hebei_url.replace("&tab=01", "&tab=02"));
				resultHtmlMap.put("qygsxx_list", qygsxx_list_page.asXml());

				// 请求获取 企业公示信息->企业年报->详情
				@SuppressWarnings("unchecked")
				List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_list_page
						.getByXPath("//div/div[@rel='layout-02_01']/table/tbody/tr/td/a");
				List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
				if (null != qygsxx_qynb_list_as
						&& !qygsxx_qynb_list_as.isEmpty()) {
					for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
						Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
						String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a
								.getTextContent().toString().trim();
						// String qygsxx_qynb_list_pubdate = String
								// .valueOf(((HtmlElement) qygsxx_qynb_list_a
										// .getParentNode().getNextSibling())
										// .getTextContent());
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",
								qygsxx_qynb_list_a_text);
						// qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",
								// qygsxx_qynb_list_pubdate);
						HtmlPage qygsxx_qynb_info_page = (HtmlPage) qygsxx_qynb_list_a
								.click();
						qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",
								qygsxx_qynb_info_page.asXml());
						qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
					}
				}
				resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

				// 请求获取 其他部门公示信息
				HtmlPage qtbmgsxx_page = firstInfoPage.getWebClient().getPage(
						hebei_url.replace("&tab=01", "&tab=03"));
				String qtbmgsxx_str = null;
				if (null != qtbmgsxx_page) {
					qtbmgsxx_str = qtbmgsxx_page.asXml();
				}
				resultHtmlMap.put("qtbmgsxx", qtbmgsxx_str);

				// 请求获取 司法协助公示信息
				HtmlPage sfxzgsxx_list_page = firstInfoPage.getWebClient()
						.getPage(hebei_url.replace("&tab=01", "&tab=06"));
				String sfxzgsxx_list_str = null;
				if (null != sfxzgsxx_list_page) {
					sfxzgsxx_list_str = sfxzgsxx_list_page.asXml();
				}
				resultHtmlMap.put("sfxzgsxx_list", sfxzgsxx_list_str);

				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);

			}
		}

		LOGGER.returnRedisResource();

		return resultHtmlMap;

	}

	// 甘肃数据
	private Map<String, Object> getHtmlInfoMapOfGansu(String area,
			HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER)
			throws Exception {

		LOGGER.info("=========" + area + "=========" + keyword + "=========");

		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();

		if (null == firstInfoPage) {

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);

		} else {

			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage
					.getByXPath("//div[@class='list']/ul/li/a");

			if (null == anchors || anchors.isEmpty()) {
				boolean flag1 = false;
				if (StringUtils.isEmpty(firstInfoPage.asText())) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.ILLEGAL_CHAR);
					resultHtmlMap.put("searchPageHtml", firstInfoPage.asXml());
					return resultHtmlMap;
				} else if (firstInfoPage.asXml().contains(
						"尊敬的用户： 您输入的查询条件有误，请重新输入查询条件")
						|| firstInfoPage.asXml()
								.contains("尊敬的用户： 请您输入更精确的查询条件")) {
					flag1 = true;
				}
				if (flag1) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.NO_DATA_FOUND);
				} else {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.IMAGECODE_ERROR);
				}
			}

			HtmlAnchor htmlAnchor = null;
			boolean flag = false;

			if (anchors != null && !anchors.isEmpty()) {
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString()
							.trim();
					if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目
						htmlAnchor = anchor;
						htmlAnchor.click();
						String alertMsg = WebCrawler.getAlertMsg();
						if (null != alertMsg && alertMsg.contains("尊敬的用户") && alertMsg.contains("暂时无法查看企业详细信息")) {
							continue;
						} else {
							flag = true;
							break;
						}
					}
				}
				if (!flag) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				} else {
					htmlAnchor.click();
					String alertMsg = WebCrawler.getAlertMsg();
					if (null != alertMsg && alertMsg.contains("尊敬的用户") && alertMsg.contains("暂时无法查看企业详细信息")) {
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.NO_DATA_FOUND);
						LOGGER.info("查到了结果，但是该公司咱不能查看详情！");
						return resultHtmlMap;
					}
				}
			}

			if (flag) {

				// 保存列表页目标条目信息
				HtmlElement target_item_info = (HtmlElement) htmlAnchor
						.getParentNode().getParentNode();
				resultHtmlMap.put("target_item_info", target_item_info.asXml());

				String regno = htmlAnchor.getAttribute("id");
				String entcate = htmlAnchor.getAttribute("_entcate");
				String gsgs_of_gansu = "http://xygs.gsaic.gov.cn/gsxygs/pub!view.do?regno="
						+ regno + "&entcate=" + entcate;

				// 点击列表页目标条目，获取工商公示信息
				HtmlPage gsgsxx = firstInfoPage.getWebClient().getPage(
						gsgs_of_gansu);
				resultHtmlMap.put("gsgsxx", gsgsxx.asXml());

				HtmlElement ul_li = gsgsxx
						.getFirstByXPath("//div[@id='leftTabs']/ul/li[2]");
				String ul_li_onclick = ul_li.getAttribute("onclick");
				String pripid = ul_li_onclick.replace("'", "").split(",")[1];
				String qygs_of_gansu = "http://xygs.gsaic.gov.cn/gsxygs/pub!viewE.do?regno="
						+ regno + "&entcate=" + entcate + "&pripid=" + pripid;
				String qtbm_of_gansu = "http://xygs.gsaic.gov.cn/gsxygs/pub!viewO.do?regno="
						+ regno + "&entcate=" + entcate + "&pripid=" + pripid;
				String sfxz_of_gansu = "http://xygs.gsaic.gov.cn/gsxygs/pub!viewS.do?regno="
						+ regno + "&entcate=" + entcate + "&pripid=" + pripid;

				// 点击列表页目标条目，获取工商公示信息（登记信息->股东信息->详情）
				List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
				HtmlElement gsgs_djxx_gdxx = gsgsxx
						.getFirstByXPath("//table[@id='invTab']");
				if (null != gsgs_djxx_gdxx) {
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx
							.getByXPath("//table[@id='invTab']/tbody[2]/tr/td/a");
					if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
						for (HtmlAnchor touziren_anchor : touziren_anchors) {
							Map<String, Object> gsgsxx_djxx_tzrxx_xq = new LinkedHashMap<String, Object>();
							HtmlPage gsgsxx_djxx_tzrxx_xq_page = touziren_anchor
									.click();
							gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",
									gsgsxx_djxx_tzrxx_xq_page.asXml());
							gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
						}
					}
				}
				resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",
						gsgsxx_djxx_tzrxx_xqs);

				// 请求获取 工商公示信息（行政处罚信息->行政处罚信息详情）
				// @SuppressWarnings("unchecked")
				// List<HtmlAnchor> xingzhengchufa_anchors =
				// (List<HtmlAnchor>) gsgsxx
				// .getByXPath("//div[@id='xingzhengchufa']/table/tbody/tr/td/a");
				// List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs = new
				// ArrayList<Map<String, Object>>();
				// if (null != xingzhengchufa_anchors
				// && xingzhengchufa_anchors.size() > 0) {
				// for (HtmlAnchor xingzhengchufa_anchor :
				// xingzhengchufa_anchors) {
				// Map<String, Object> gsgsxx_xzcfxx_xzcfxx_xq = new
				// LinkedHashMap<String, Object>();
				// HtmlPage gsgsxx_xzcfxx_xzcfxx_xq_page =
				// xingzhengchufa_anchor
				// .click();
				// gsgsxx_xzcfxx_xzcfxx_xq.put(
				// "gsgsxx_xzcfxx_xzcfxx_xq",
				// gsgsxx_xzcfxx_xzcfxx_xq_page.asXml());
				// gsgsxx_xzcfxx_xzcfxx_xqs
				// .add(gsgsxx_xzcfxx_xzcfxx_xq);
				// }
				// }
				// resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx_xqs",
				// gsgsxx_xzcfxx_xzcfxx_xqs);

				// 请求获取企业公示信息
				HtmlPage qygsxx_list_page = firstInfoPage.getWebClient()
						.getPage(qygs_of_gansu);
				resultHtmlMap.put("qygsxx_list", qygsxx_list_page.asXml());

				// 请求获取 企业公示信息->企业年报->详情
				List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
				HtmlElement qiyenianbao = qygsxx_list_page
						.getFirstByXPath("//div[@id='qiyenianbao']");
				if (null != qiyenianbao) {
					@SuppressWarnings("unchecked")
					List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_list_page
							.getByXPath("//div[@id='qiyenianbao']/table/tbody/tr/td/a");
					if (null != qygsxx_qynb_list_as
							&& !qygsxx_qynb_list_as.isEmpty()) {
						for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
							Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
							String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a
									.getTextContent().toString().trim();
							// String qygsxx_qynb_list_pubdate = String
									// .valueOf(((HtmlElement) qygsxx_qynb_list_a
											// .getParentNode().getNextSibling())
											// .getTextContent());
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",
									qygsxx_qynb_list_a_text);
							// qygsxx_qynb_info_map.put(
									// "qygsxx_qynb_list_pubdate",
									// qygsxx_qynb_list_pubdate);
							HtmlPage qygsxx_qynb_info_page = (HtmlPage) qygsxx_qynb_list_a
									.click();
							qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",
									qygsxx_qynb_info_page.asXml());
							qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
						}
					}
				}
				resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

				// 请求获取 其他部门公示信息
				HtmlPage qtbmgsxx_page = firstInfoPage.getWebClient().getPage(
						qtbm_of_gansu);
				String qtbmgsxx_str = null;
				if (null != qtbmgsxx_page) {
					qtbmgsxx_str = qtbmgsxx_page.asXml();
				}
				resultHtmlMap.put("qtbmgsxx", qtbmgsxx_str);

				// 请求获取 司法协助公示信息
				HtmlPage sfxzgsxx_list_page = firstInfoPage.getWebClient()
						.getPage(sfxz_of_gansu);
				String sfxzgsxx_list_str = null;
				if (null != sfxzgsxx_list_page) {
					sfxzgsxx_list_str = sfxzgsxx_list_page.asXml();
				}
				resultHtmlMap.put("sfxzgsxx_list", sfxzgsxx_list_str);

				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);

			}
		}

		LOGGER.returnRedisResource();

		return resultHtmlMap;

	}

	// 江苏数据
	private Map<String, Object> getHtmlInfoMapOfJiangsu(String area,
			HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER)
			throws Exception {

		LOGGER.info("=========" + area + "=========" + keyword + "=========");
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();

		String alertMsg = WebCrawler.getAlertMsg();

		if (null == firstInfoPage) {

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);

		} else if (null != alertMsg
				&& alertMsg.contains("该IP在一天内超过了查询的限定次数，限制其访问3天")) {

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.FREQUENCY_LIMITED);

		} else if (null != alertMsg && alertMsg.contains("验证码输入错误")) {

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);

		} else if (null != alertMsg && alertMsg.contains("非法字符")) {
			
			resultHtmlMap.put("statusCodeDef", StatusCodeDef.ILLEGAL_CHAR);
			resultHtmlMap.put("searchPageHtml", firstInfoPage.asXml());
			
		} else {

			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage
					.getByXPath("//div[@id='punishResult']/dl/dt/a");

			if (null == anchors || anchors.isEmpty()) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			} else {
				HtmlAnchor htmlAnchor = null;
				boolean flag = false;

				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString()
							.trim();
					if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目
						htmlAnchor = anchor;
						flag = true;
						break;
					}
				}

				if (!flag) {
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.NO_DATA_FOUND);
				}

				if (flag) {
					
					WebWindow webWindow = firstInfoPage.getWebClient().getCurrentWindow();

					// 保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) htmlAnchor
							.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info",
							target_item_info.asXml());

					// 点击列表页目标条目，获取 工商公示信息（登记信息->基本信息->投资人信息->变更信息）
					Page gdwd = htmlAnchor.click();
					Thread.sleep(3000);
					HtmlPage gsgsxx_djxx = (HtmlPage) gdwd;
					Document commonText = Jsoup.parseBodyFragment(gsgsxx_djxx.asXml());
					String corp_id = commonText.getElementById("id").attr("value");
					String corp_org = commonText.getElementById("org").attr("value");
					resultHtmlMap.put("gsgsxx_djxx", gsgsxx_djxx.asXml());
					// 点击列表页目标条目，获取 工商公示信息（登记信息->投资人信息->详情）
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx_djxx
							.getByXPath("//table[@id='touziren']/tbody/tr/td/a");
					List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
					if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
						for (HtmlAnchor touziren_anchor : touziren_anchors) {
							Map<String, Object> gsgsxx_djxx_tzrxx_xq = new LinkedHashMap<String, Object>();
							HtmlPage gsgsxx_djxx_tzrxx_xq_page = touziren_anchor
									.click();
							gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",
									gsgsxx_djxx_tzrxx_xq_page.asXml());
							gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
						}
					}
					resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",
							gsgsxx_djxx_tzrxx_xqs);

					// 请求获取 工商公示信息（备案信息->主要人员信息->分支机构信息->清算信息）
					HtmlElement js_tab2 = (HtmlElement) gsgsxx_djxx
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='2']");
					String gsgsxx_baxx = null;
					if (null != js_tab2 && !StringUtils.isEmpty(js_tab2)) {
						HtmlPage gsgsxx_baxx_page = js_tab2.click();
						gsgsxx_baxx = gsgsxx_baxx_page.asXml();
					}
					resultHtmlMap.put("gsgsxx_baxx", gsgsxx_baxx);

					// 请求获取 工商公示信息（动产抵押登记信息->动产抵押登记信息）
					HtmlElement js_tab4 = (HtmlElement) gsgsxx_djxx
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='4']");
					String gsgsxx_dcdydjxx_dcdydjxx = null;
					if (null != js_tab4 && !StringUtils.isEmpty(js_tab4)) {
						HtmlPage gsgsxx_dcdydjxx_dcdydjxx_page = js_tab4
								.click();
						gsgsxx_dcdydjxx_dcdydjxx = gsgsxx_dcdydjxx_dcdydjxx_page
								.asXml();
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> wcqs = (List<HtmlAnchor>)gsgsxx_dcdydjxx_dcdydjxx_page.getByXPath("//div[@id='dongchandiya']/table[1]/tbody/tr/td/a");
						if(null != wcqs && wcqs.size() > 0){
							List<Map<String, Object>> gsgsxx_dcdydjxx_xqs = new ArrayList<Map<String, Object>>();
							for (HtmlAnchor wcq : wcqs) {
								Map<String,Object> wd = new HashMap<String, Object>();
								HtmlPage gsgsxx_dcdydjxx_xq = wcq.click();
								wd.put("gsgsxx_dcdydjxx_xq", gsgsxx_dcdydjxx_xq.asXml());
								gsgsxx_dcdydjxx_xqs.add(wd);
							}
							resultHtmlMap.put("gsgsxx_dcdydjxx_xqs",
									gsgsxx_dcdydjxx_xqs);
						}
					}
					resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx",
							gsgsxx_dcdydjxx_dcdydjxx);

					// 请求获取 工商公示信息（股权出质登记信息->股权出质登记信息）
					HtmlElement js_tab3 = (HtmlElement) gsgsxx_djxx
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='3']");
					String gsgsxx_gqczdjxx_gqczdjxx = null;
					if (null != js_tab3 && !StringUtils.isEmpty(js_tab3)) {
						HtmlPage gsgsxx_gqczdjxx_gqczdjxx_page = js_tab3
								.click();
						gsgsxx_gqczdjxx_gqczdjxx = gsgsxx_gqczdjxx_gqczdjxx_page
								.asXml();
					}
					resultHtmlMap.put("gsgsxx_gqczdjxx_gqczdjxx",
							gsgsxx_gqczdjxx_gqczdjxx);

					// 请求获取 工商公示信息（行政处罚信息->行政处罚信息）
					HtmlElement js_tab7 = (HtmlElement) gsgsxx_djxx
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='7']");
					String gsgsxx_xzcfxx_xzcfxx = null;
					if (null != js_tab7 && !StringUtils.isEmpty(js_tab7)) {
						HtmlPage gsgsxx_xzcfxx_xzcfxx_page = js_tab7.click();
						gsgsxx_xzcfxx_xzcfxx = gsgsxx_xzcfxx_xzcfxx_page
								.asXml();
						// 请求获取 工商公示信息（行政处罚信息->行政处罚信息详情）
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> xingzhengchufa_anchors = (List<HtmlAnchor>) gsgsxx_xzcfxx_xzcfxx_page
								.getByXPath("//div[@id='xingzhengchufa']/table/tbody/tr/td/a");
						List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs = new ArrayList<Map<String, Object>>();
						if (null != xingzhengchufa_anchors
								&& xingzhengchufa_anchors.size() > 0) {
							for (HtmlAnchor xingzhengchufa_anchor : xingzhengchufa_anchors) {
								Map<String, Object> gsgsxx_xzcfxx_xzcfxx_xq = new LinkedHashMap<String, Object>();
								HtmlPage gsgsxx_xzcfxx_xzcfxx_xq_page = xingzhengchufa_anchor
										.click();
								gsgsxx_xzcfxx_xzcfxx_xq.put(
										"gsgsxx_xzcfxx_xzcfxx_xq",
										gsgsxx_xzcfxx_xzcfxx_xq_page.asXml());
								gsgsxx_xzcfxx_xzcfxx_xqs
										.add(gsgsxx_xzcfxx_xzcfxx_xq);
							}
						}
						resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx_xqs",
								gsgsxx_xzcfxx_xzcfxx_xqs);
					}
					resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx",
							gsgsxx_xzcfxx_xzcfxx);

					// 请求获取 工商公示信息->经营异常信息->经营异常信息
					HtmlElement js_tab5 = (HtmlElement) gsgsxx_djxx
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='5']");
					String gsgsxx_jyycxx_jyycxx = null;
					if (null != js_tab5 && !StringUtils.isEmpty(js_tab5)) {
						HtmlPage gsgsxx_jyycxx_jyycxx_page = js_tab5.click();
						gsgsxx_jyycxx_jyycxx = gsgsxx_jyycxx_jyycxx_page
								.asXml();
					}
					resultHtmlMap.put("gsgsxx_jyycxx_jyycxx",
							gsgsxx_jyycxx_jyycxx);

					// 请求获取 工商公示信息->严重违法信息->严重违法信息
					HtmlElement js_tab6 = (HtmlElement) gsgsxx_djxx
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='6']");
					String gsgsxx_yzwfxx_yzwfxx = null;
					if (null != js_tab6 && !StringUtils.isEmpty(js_tab6)) {
						HtmlPage gsgsxx_yzwfxx_yzwfxx_page = js_tab6.click();
						gsgsxx_yzwfxx_yzwfxx = gsgsxx_yzwfxx_yzwfxx_page
								.asXml();
					}
					resultHtmlMap.put("gsgsxx_yzwfxx_yzwfxx",
							gsgsxx_yzwfxx_yzwfxx);

					// 请求获取 工商公示信息->抽查检查信息->抽查检查信息
					HtmlElement js_tab8 = (HtmlElement) gsgsxx_djxx
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='8']");
					String gsgsxx_ccjcxx_ccjcxx = null;
					if (null != js_tab8 && !StringUtils.isEmpty(js_tab8)) {
						HtmlPage gsgsxx_ccjcxx_ccjcxx_page = js_tab8.click();
						gsgsxx_ccjcxx_ccjcxx = gsgsxx_ccjcxx_ccjcxx_page
								.asXml();
					}
					resultHtmlMap.put("gsgsxx_ccjcxx_ccjcxx",
							gsgsxx_ccjcxx_ccjcxx);

					// 请求获取 企业公示信息->企业年报->列表
					HtmlElement js_left2 = (HtmlElement) gsgsxx_djxx
							.getByXPath("//div[@id='leftTabs']/ul/li").get(1);
					HtmlPage qygsxx_qynb_list_page = js_left2.click();
					resultHtmlMap.put("qygsxx_qynb_list_page", qygsxx_qynb_list_page.asXml());

					// 请求获取 企业公示信息->企业年报->详情
					@SuppressWarnings("unchecked")
					List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_list_page
							.getByXPath("//div[@id='qiyenianbao']/table/tbody/tr/td/a");
					List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
					if (null != qygsxx_qynb_list_as
							&& !qygsxx_qynb_list_as.isEmpty()) {
						for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
							Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
							String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a
									.getTextContent().toString().trim();
							// String qygsxx_qynb_list_pubdate = String
									// .valueOf(((HtmlElement) qygsxx_qynb_list_a
											// .getParentNode().getNextSibling())
											// .getTextContent());
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",
									qygsxx_qynb_list_a_text);
							// qygsxx_qynb_info_map.put(
									// "qygsxx_qynb_list_pubdate",
									// qygsxx_qynb_list_pubdate);
							HtmlPage qygsxx_qynb_info_page = (HtmlPage) qygsxx_qynb_list_a
									.click();
							qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",
									qygsxx_qynb_info_page.asXml());
							qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
						}
					}
					resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

					// 请求获取 企业公示信息->投资人及出资信息
					HtmlElement js_lefttab3 = (HtmlElement) qygsxx_qynb_list_page
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='3']");
					String qygsxx_gdjczxx = null;
					if (null != js_lefttab3
							&& !StringUtils.isEmpty(js_lefttab3)) {
						HtmlPage qygsxx_gdjczxx_page = js_lefttab3.click();
						qygsxx_gdjczxx = qygsxx_gdjczxx_page.asXml();
					}
					resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx);

					// 请求获取 企业公示信息->股权变更信息
					HtmlElement js_lefttab6 = (HtmlElement) qygsxx_qynb_list_page
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='6']");
					String qygsxx_gqbgxx = null;
					if (null != js_lefttab6
							&& !StringUtils.isEmpty(js_lefttab6)) {
						HtmlPage qygsxx_gqbgxx_page = js_lefttab6.click();
						qygsxx_gqbgxx = qygsxx_gqbgxx_page.asXml();
					}
					resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx);

					// 请求获取 企业公示信息->行政许可信息
					HtmlElement js_lefttab1 = (HtmlElement) qygsxx_qynb_list_page
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='1']");
					String qygsxx_xzxkxx = null;
					if (null != js_lefttab1
							&& !StringUtils.isEmpty(js_lefttab1)) {
						HtmlPage qygsxx_xzxkxx_page = js_lefttab1.click();
						qygsxx_xzxkxx = qygsxx_xzxkxx_page.asXml();
					}
					resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx);

					// 请求获取 企业公示信息->知识产权出质登记信息
					HtmlElement js_lefttab2 = (HtmlElement) qygsxx_qynb_list_page
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='2']");
					String qygsxx_zscqczdjxx = null;
					if (null != js_lefttab2
							&& !StringUtils.isEmpty(js_lefttab2)) {
						HtmlPage qygsxx_zscqczdjxx_page = js_lefttab2.click();
						qygsxx_zscqczdjxx = qygsxx_zscqczdjxx_page.asXml();
					}
					resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx);

					// 请求获取 企业公示信息->行政处罚信息
					HtmlElement js_lefttab4 = (HtmlElement) qygsxx_qynb_list_page
							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='4']");
					String qygsxx_xzcfxx = null;
					if (null != js_lefttab4
							&& !StringUtils.isEmpty(js_lefttab4)) {
						HtmlPage qygsxx_xzcfxx_page = js_lefttab4.click();
						qygsxx_xzcfxx = qygsxx_xzcfxx_page.asXml();
					}
					resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx);

					// 请求获取其他部门公示信息
//					HtmlElement js_left3 = (HtmlElement) gsgsxx_djxx
//							.getByXPath("//div[@id='leftTabs']/ul/li").get(2);
//					HtmlPage qtbmgsxx_xzxkxx_page = js_left3.click();

					// 请求获取 其他部门公示信息->行政许可信息
					WebClient wc = firstInfoPage.getWebClient();
					wc.getOptions().setJavaScriptEnabled(false);
					HtmlPage firstByXPath = wc.getPage(webWindow, new WebRequest(new URL("http://www.jsgsj.gov.cn:58888/ecipplatform/manyCommonFnQueryServlet.json?query_xingzhengxuke=true&corp_id=" + corp_id + "&corp_org=" + corp_org + "&pageNo=1&pageSize=100")));
					String qtbmgsxx_xzxkxx = null;
					if (null != firstByXPath
							&& !StringUtils.isEmpty(firstByXPath)) {
						qtbmgsxx_xzxkxx = firstByXPath.asXml();
					}
					resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx);
//					HtmlElement firstByXPath = (HtmlElement) qtbmgsxx_xzxkxx_page
//							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='1']");
//					String qtbmgsxx_xzxkxx = null;
//					if (null != firstByXPath
//							&& !StringUtils.isEmpty(firstByXPath)) {
//						HtmlPage click = firstByXPath.click();
//						qtbmgsxx_xzxkxx = click.asXml();
//					}
//					resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx);

					// 请求获取 其他部门公示信息->行政处罚信息
					HtmlPage qtbm_xzcfxx = wc.getPage(webWindow, new WebRequest(new URL("http://www.jsgsj.gov.cn:58888/ecipplatform/manyCommonFnQueryServlet.json?query_xingzhengchufa=true&corp_id=" + corp_id + "&corp_org=" + corp_org + "&pageNo=1&pageSize=100")));
					String qtbmgsxx_xzcfxx = null;
					if (null != qtbm_xzcfxx
							&& !StringUtils.isEmpty(qtbm_xzcfxx)) {
						qtbmgsxx_xzcfxx = qtbm_xzcfxx.asXml();
					}
					resultHtmlMap.put("qtbmgsxx_xzcfxx", qtbmgsxx_xzcfxx);
//					HtmlElement js_lefttab32 = (HtmlElement) qtbmgsxx_xzxkxx_page
//							.getFirstByXPath("//div[@id='tabs']/ul/li[@id='2']");
//					String qtbmgsxx_xzcfxx = null;
//					if (null != js_lefttab32
//							&& !StringUtils.isEmpty(js_lefttab32)) {
//						HtmlPage qtbmgsxx_xzcfxx_page = js_lefttab32.click();
//						qtbmgsxx_xzcfxx = qtbmgsxx_xzcfxx_page.asXml();
//					}
//					resultHtmlMap.put("qtbmgsxx_xzcfxx", qtbmgsxx_xzcfxx);

					wc.getOptions().setJavaScriptEnabled(true);
					// 请求获取司法协助公示信息
					int wd = gsgsxx_djxx.getByXPath(
							"//div[@id='leftTabs']/ul/li").size();
					HtmlElement js_left23 = null;
					if (wd > 3) {
						js_left23 = (HtmlElement) gsgsxx_djxx.getByXPath(
								"//div[@id='leftTabs']/ul/li").get(3);
					}
					if (null != js_left23) {

						HtmlPage sfxzgsxx_gqdjxx_list_page = js_left23.click();
						Thread.sleep(2000);

						// 请求获取 司法协助公示信息->股权冻结信息
						HtmlElement firstByXPath2 = (HtmlElement) sfxzgsxx_gqdjxx_list_page
								.getFirstByXPath("//div[@id='tabs']/ul/li[@id='0']");
						String sfxzgsxx_gqdjxx_list = null;
						if (null != firstByXPath2
								&& !StringUtils.isEmpty(firstByXPath2)) {
							HtmlPage click1 = firstByXPath2.click();
							sfxzgsxx_gqdjxx_list = click1.asXml();
						}
						resultHtmlMap.put("sfxzgsxx_gqdjxx_list",
								sfxzgsxx_gqdjxx_list);

						// 请求获取 司法协助公示信息->股东变更信息
						HtmlElement js_lefttab231 = (HtmlElement) sfxzgsxx_gqdjxx_list_page
								.getFirstByXPath("//div[@id='tabs']/ul/li[@id='1']");
						String sfxzgsxx_gqbgxx_list = null;
						if (null != js_lefttab231
								&& !StringUtils.isEmpty(js_lefttab231)) {
							HtmlPage sfxzgsxx_gqbgxx_list_page = js_lefttab231
									.click();
							sfxzgsxx_gqbgxx_list = sfxzgsxx_gqbgxx_list_page
									.asXml();
						}
						resultHtmlMap.put("sfxzgsxx_gqbgxx_list",
								sfxzgsxx_gqbgxx_list);

					}

					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);

				}
			}

		}

		LOGGER.returnRedisResource();

		return resultHtmlMap;

	}
		
	//安徽数据
@SuppressWarnings("unchecked")
 private Map<String, Object> getHtmlInfoMapOfAnhui(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {	
			Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();	
			HtmlElement divByXPath =  ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']"));		
			if (divByXPath == null) {					
				HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
				if (firstByXPath!=null) {
					  String textContent=firstByXPath.getTextContent();
					if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					} 	
				}else{					
					DomElement checkcode = firstInfoPage.getElementById("checkNo");
					if (checkcode!=null) {
						String val = checkcode.getAttribute("value");
						if (!StringUtils.isEmpty(val)) {
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
						} else {
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
						}
					}
			  }				
			} else {				
				String textContent = divByXPath.getTextContent();
				if (textContent !=null) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				}			
			}
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
			LOGGER.info(anchors.toString());		
			if (anchors!=null && !anchors.isEmpty()) {
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
						//公共参数			
						String id = anchor.getAttribute("href");
						if (id !=null) {
							id=id.substring(id.length()-32);
						}		
						//保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
						resultHtmlMap.put("target_item_info", target_item_info.asXml());
						
						//一、获取工商公示信息
						HtmlPage gsgsxx_page = anchor.click();
						resultHtmlMap.put("gsgsxx", gsgsxx_page.asXml());
						//展开更多
						List<HtmlAnchor> more_anchors = (List<HtmlAnchor>) gsgsxx_page.getByXPath("//table[@id='altTab']/tbody/tr/td/a");
						for (HtmlAnchor moreAnchor : more_anchors) {
							moreAnchor.click();
						}
						
						//工商公示信息-->股东信息-->详情
						List<HtmlAnchor> anchors_gdxx_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
						List<String> gdxx_list = new ArrayList<String>();
						for (HtmlAnchor htmlAnchor : anchors_gdxx_detail) {
							HtmlPage gdxx_detail = htmlAnchor.click();
							gdxx_list.add(gdxx_detail.asXml());
						}
						resultHtmlMap.put("gsgsxx_gdxx_detail", gdxx_list);					
						
						
						//工商公示信息-->动产抵押登记信息-->详情
						List<HtmlAnchor> anchors_dcdy_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='mortDiv']/table[@class='detailsList']/tbody/tr/td/a");
						List<String> dcdy_list = new ArrayList<String>();
						for (HtmlAnchor htmlAnchor : anchors_dcdy_detail) {
							HtmlPage dcdy_detail = htmlAnchor.click();
							gdxx_list.add(dcdy_detail.asXml());
						}
						resultHtmlMap.put("gsgsxx_dcdy_detail", dcdy_list);
								
						//工商公示信息-->行政处罚信息-->详情
						List<HtmlAnchor> anchors_xzcf_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='punDiv']/table[@class='detailsList']/tbody/tr/td/a");
						List<String> xzcf_list = new ArrayList<String>();
						for (HtmlAnchor htmlAnchor : anchors_xzcf_detail) {
							HtmlPage xzcf_detail = htmlAnchor.click();
							gdxx_list.add(xzcf_detail.asXml());
						}
						resultHtmlMap.put("gsgsxx_xzcf_detail", xzcf_list);
						
						
						//二、获取企业公示信息
						List<?> liElements = gsgsxx_page.getByXPath("//div[@id='leftTabs']/ul/li");
						
						HtmlElement qygsxx_tab = (HtmlElement)liElements.get(1);
						HtmlPage qygsxx_page = (HtmlPage)qygsxx_tab.click();
						resultHtmlMap.put("qygsxx", qygsxx_page.asXml());
						
						//企业公示信息-->企业年报-->详情
						List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
						List<String> nbxx_list = new ArrayList<String>();
						for (HtmlAnchor htmlAnchor : anchors_detail) {
							HtmlPage nb_detail = htmlAnchor.click();
							nbxx_list.add(nb_detail.asXml());
						}
						resultHtmlMap.put("qygsxx_qynb_detail", nbxx_list);
						
						
						
						//企业公示信息-->行政许可信息-->详情
						List<HtmlAnchor> anchors_xzxk_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@id='licenseRegDiv']/table[@class='licenseRegTab']/tbody/tr/td/a");
						List<String> qygs_xzxk_list = new ArrayList<String>();
						for (HtmlAnchor htmlAnchor : anchors_xzxk_detail) {
							HtmlPage xzxk_detail = htmlAnchor.click();
							qygs_xzxk_list.add(xzxk_detail.asXml());
						}
						resultHtmlMap.put("qygsxx_dcdy_detail", qygs_xzxk_list);
								
						
						//三 请求获取 其他部门公示信息->全部   
						String gsgsxx_qtbm_info_url = "http://www.ahcredit.gov.cn/otherDepartment.jspx?id=" + id ;
						HtmlPage gsgsxx_qtbm_page = firstInfoPage.getWebClient().getPage(gsgsxx_qtbm_info_url);
						resultHtmlMap.put("qtbmgsxx", gsgsxx_qtbm_page.asXml());
						
						//其他部门公示信息-->行政许可信息-->详情
						List<HtmlAnchor> qtbm_xzxk_detail = (List<HtmlAnchor>)gsgsxx_qtbm_page.getByXPath("//div[@id='licenseRegDiv']/table[@class='licenseRegTab']/tbody/tr/td/a");
						List<String> qtbm_xzxk_list = new ArrayList<String>();
						for (HtmlAnchor htmlAnchor : qtbm_xzxk_detail) {
							HtmlPage xzxk_detail = htmlAnchor.click();
							qtbm_xzxk_list.add(xzxk_detail.asXml());
						}
						resultHtmlMap.put("qtbm_xzxk_detail", qtbm_xzxk_list);
								
			
						//请求获取 工商司法协助信息->全部     
						
						if (liElements.size()>3) {
							String gsgsxx_sfxz_url = "http://www.ahcredit.gov.cn/justiceAssistance.jspx?id=" + id ;
							HtmlPage gsgsxx_sfxz_page = firstInfoPage.getWebClient().getPage(gsgsxx_sfxz_url);
							resultHtmlMap.put("sfxzgsxx", gsgsxx_sfxz_page.asXml());
							
							//工商司法协助信息-->股权变更信息-->详情
							List<HtmlAnchor> sfxz_gqbg_detail = (List<HtmlAnchor>)gsgsxx_sfxz_page.getByXPath("//div[@id='EquityFreezeDiv']/table[@class='detailsList']/tbody/tr/td/a");
							List<String> sfxz_gqbg_list = new ArrayList<String>();
							for (HtmlAnchor htmlAnchor : sfxz_gqbg_detail) {
								HtmlPage gqbg_detail = htmlAnchor.click();
								sfxz_gqbg_list.add(gqbg_detail.asXml());
							}
							resultHtmlMap.put("sfxz_gqbg_detail", sfxz_gqbg_list);
									
							//工商司法协助信息-->股东变更信息-->详情
							List<HtmlAnchor> sfxz_gdbg_detail = (List<HtmlAnchor>)gsgsxx_sfxz_page.getByXPath("//div[@id='xzcfDiv']/table[@class='detailsList']/tbody/tr/td/a");
							List<String> sfxz_gdbg_list = new ArrayList<String>();
							for (HtmlAnchor htmlAnchor : sfxz_gdbg_detail) {
								HtmlPage gdbg_detail = htmlAnchor.click();
								sfxz_gdbg_list.add(gdbg_detail.asXml());
							}
							resultHtmlMap.put("sfxz_gdbg_detail", sfxz_gdbg_list);	
							
						}
						
						break;
					}
			
				}
			}
			return resultHtmlMap;
		}

	//黑龙江数据
	@SuppressWarnings("unchecked")
	private Map<String, Object> getHtmlInfoMapOfHeilongjiang(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
		WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();	
		HtmlElement divByXPath =  ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']"));		
		if (divByXPath == null) {
			DomElement checkcode = firstInfoPage.getElementById("checkNo");
			String val = checkcode.getAttribute("value");
			if (!StringUtils.isEmpty(val)) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
			}
		} else {				
			String textContent = divByXPath.getTextContent();
			if (textContent !=null) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}else{					
				HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
				textContent=firstByXPath.getTextContent();
				if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				} 					
			}				
		}
		
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
		LOGGER.info(anchors.toString());
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				
				if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
					matchFlag = true;
					//保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info", target_item_info.asXml());
					
					//获得公共参数id
					String idParam = "";
					String hrefAttr = anchor.getAttribute("href");
					if (!StringUtils.isEmpty(hrefAttr)) {
						int index = hrefAttr.indexOf("?");
						idParam = hrefAttr.substring(index+1);
					}
					
					//一、获取工商公示信息
					HtmlPage gsgsxx_page = anchor.click();
					resultHtmlMap.put("gsgsxx", gsgsxx_page.asXml());
					
					//工商公示信息-->登记信息-->股东信息-->详情
//					List<HtmlAnchor> anchors_gdxx_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
//					List<String> gdxx_list = new ArrayList<String>();
//					for (HtmlAnchor htmlAnchor : anchors_gdxx_detail) {
//						HtmlPage gdxx_detail = htmlAnchor.click();
//						gdxx_list.add(gdxx_detail.asXml());
//					}
//					resultHtmlMap.put("gsgsxx_gdxx_detail", gdxx_list);
					
					//工商公示信息-->登记信息-->股东信息(以及详情信息)
					String mainIdParam = idParam.replace("id", "mainId");
					List<String> gdxxList = new ArrayList<String>();
					List<String> gdxxDetailList = new ArrayList<String>();
					int gdxxPageNo = 1;
					while (true) {
						String gsgsxx_djxx_gdxx_url = "http://gsxt.hljaic.gov.cn/QueryInvList.jspx?pno="+gdxxPageNo+"&"+mainIdParam;
						String gsgsxx_djxx_gdxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_djxx_gdxx_url).getWebResponse().getContentAsString();
						Document gsgsxxGdxxDoc = Jsoup.parse(gsgsxx_djxx_gdxx_page);
						if (gsgsxxGdxxDoc != null) {
							Elements aElements = gsgsxxGdxxDoc.select("a");
							for (Element aElement : aElements) {
								String attr = aElement.attr("onclick");
								if (attr != null && !"".equals(attr)) {
									String[] split = attr.split("'");
									if (split.length > 2) {
										String detailUrl = "http://gsxt.hljaic.gov.cn" + split[1];
										HtmlPage gsgsxx_gdxx_detail_page = null;
										try {
											gsgsxx_gdxx_detail_page = firstInfoPage.getWebClient().getPage(detailUrl);
										} catch (Exception e) {
											gsgsxx_gdxx_detail_page = WebClient.getCustomHtmlPage(detailUrl, firstInfoPage.getWebClient().getCurrentWindow());
										}
										gdxxDetailList.add(gsgsxx_gdxx_detail_page.asXml());
									}
								}
							}
						}
						
						if (!gdxxList.contains(gsgsxx_djxx_gdxx_page)) {
							gdxxList.add(gsgsxx_djxx_gdxx_page);
							gdxxPageNo++;
						} else {
							break;
						}
						
					}
					resultHtmlMap.put("gsgsxx_gdxx_page", gdxxList);
					resultHtmlMap.put("gsgsxx_gdxx_detail", gdxxDetailList);
					
					//工商公示信息-->登记信息-->变更信息
					List<String> bgxxList = new ArrayList<String>();
					int bgxxPageNo = 1;
					while (true) {
						String gsgsxx_bgxx_url = "http://gsxt.hljaic.gov.cn/QueryAltList.jspx?pno="+bgxxPageNo+"&"+mainIdParam;
						String gsgsxx_bgxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_bgxx_url).getWebResponse().getContentAsString();
						if (!bgxxList.contains(gsgsxx_bgxx_page)) {
							bgxxList.add(gsgsxx_bgxx_page);
							bgxxPageNo++;
						} else {
							break;
						}
						
					}
					resultHtmlMap.put("gsgsxx_bgxx_page", bgxxList);
					
					//工商公示信息-->备案信息-->主要人员信息
					List<String> zyryxxList = new ArrayList<String>();
					int zyryPageNo = 1;
					while (true) {
						DomElement pageNoDom = gsgsxx_page.getElementById("spanmem"+zyryPageNo);
						if (pageNoDom != null) {
							String gsgsxx_baxx_zyryxx_url = "http://gsxt.hljaic.gov.cn/QueryMemList.jspx?pno="+zyryPageNo+"&"+mainIdParam;
							String gsgsxx_baxx_zyryxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_baxx_zyryxx_url).getWebResponse().getContentAsString();
							if (!zyryxxList.contains(gsgsxx_baxx_zyryxx_page)) {
								zyryxxList.add(gsgsxx_baxx_zyryxx_page);
								zyryPageNo++;
							} else {
								break;
							}
						}else {
							break;
						}
					}
					resultHtmlMap.put("gsgsxx_baxx_zyryxx_page", zyryxxList);
					
					//http://gsxt.fc12319.com/QueryMortList.jspx?pno=1&mainId=DB5C6077C0A57243E7C56BA05CD252C0
					//工商公示信息-->动产抵押登记信息-->详情
					List<HtmlAnchor> anchors_dcdyxx_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='mortDiv']/table[@class='detailsList']/tbody/tr/td/a");
					List<String> gsgs_dcdyxx_list = new ArrayList<String>();
					for (HtmlAnchor htmlAnchor : anchors_dcdyxx_detail) {
						String attribute = htmlAnchor.getAttribute("onclick");
						String detailUrl = "http://gsxt.hljaic.gov.cn" + attribute.split("'")[1];
						HtmlPage dcdyxx_detail_page = null;
						try {
							dcdyxx_detail_page = firstInfoPage.getWebClient().getPage(detailUrl);
						} catch (Exception e) {
							dcdyxx_detail_page = WebClient.getCustomHtmlPage(detailUrl, firstInfoPage.getWebClient().getCurrentWindow());
						}
						gsgs_dcdyxx_list.add(dcdyxx_detail_page.asXml());
					}
					resultHtmlMap.put("gsgsxx_dcdydjxx_details", gsgs_dcdyxx_list);
					
					//工商公示信息-->经营异常信息
					String gsgsxx_jyycxx_url = "http://gsxt.hljaic.gov.cn/QueryExcList.jspx?pno=1&"+mainIdParam;
					String gsgsxx_jyycxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_jyycxx_url).getWebResponse().getContentAsString();
					resultHtmlMap.put("gsgsxx_jyycxx_page", gsgsxx_jyycxx_page);
					
					List<?> liElements = gsgsxx_page.getByXPath("//div[@id='leftTabs']/ul/li");
					//二、获取企业公示信息
					String qygsxx_url = "http://gsxt.hljaic.gov.cn/enterprisePublicity.jspx?"+idParam;
					HtmlPage qygsxx_page = firstInfoPage.getWebClient().getPage(qygsxx_url);
					resultHtmlMap.put("qygsxx", qygsxx_page.asXml());
					
					//企业公示信息-->企业年报-->详情
					List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
					List<String> nbxx_list = new ArrayList<String>();
					for (HtmlAnchor htmlAnchor : anchors_detail) {
						HtmlPage nb_detail = htmlAnchor.click();
						nbxx_list.add(nb_detail.asXml());
					}
					resultHtmlMap.put("qygsxx_qynb_detail", nbxx_list);
					
					//企业公示信息-->行政许可信息
					String qygsxx_xzxkxx_url = "http://gsxt.hljaic.gov.cn/QueryLicenseRegList.jspx?pno=1&"+mainIdParam;
					String gsgsxx_xzxkxx_page = firstInfoPage.getWebClient().getPage(qygsxx_xzxkxx_url).getWebResponse().getContentAsString();
					resultHtmlMap.put("gsgsxx_xzxkxx_page", gsgsxx_xzxkxx_page);
					
					//三、获取其他部门公示信息
//					String gsgsxx_sfxzgsxx_url = "http://gsxt.hljaic.gov.cn/otherDepartment.jspx?id="+mystr ;
					String qtbmgsxx_url = "http://gsxt.hljaic.gov.cn/otherDepartment.jspx?"+mainIdParam;
					HtmlPage qtbmgsxx_page = firstInfoPage.getWebClient().getPage(qtbmgsxx_url);
					resultHtmlMap.put("qtbmgsxx", qtbmgsxx_page.asXml());	
					
					//四、获取 司法协助公示信息
					if(liElements.size()>3) {
						String sfxzxx_url = "http://gsxt.hljaic.gov.cn/justiceAssistance.jspx?"+idParam;
						HtmlPage sfxzxx_page = firstInfoPage.getWebClient().getPage(sfxzxx_url);
						resultHtmlMap.put("sfxzgsxx", sfxzxx_page.asXml());
					}
					
					break;
				}
		
			}
			
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
		}
		
		return resultHtmlMap;
	}
 	
//四川数据
@SuppressWarnings("unchecked")
private Map<String, Object> getHtmlInfoMapOfSichuan(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
				Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();	
				//
		WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();		
				
		HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='center-1']/div/ul/li[@class='font16']"));
		if (divByXPath == null) {									
			HtmlElement	firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//table[@class='d-border']/tbody/tr/td/div"));
				if (firstByXPath!=null && firstByXPath.getTextContent().contains("验证码错误")) {							
					 resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
				} else{							
					firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@style='margin-left: 20px;font-size: 15px;']"));							
				  if (firstByXPath!=null) {
					  String textContent=firstByXPath.getTextContent();
					  if (textContent.contains("您搜索的条件无查询结果")) {
						  resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					}									
				} 	
					
			}				 
		} else {		
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='center-1']/div/ul/li[@class='font16']/a"));
			if (firstByXPath !=null) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}			
		}
		//		
		@SuppressWarnings("unchecked")
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='center-1']/div/ul/li[@class='font16']/a");
		LOGGER.info(anchors.toString());		
		if (anchors!=null && !anchors.isEmpty()) {
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				
				if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目				
					String pripid = anchor.getAttribute("onclick");
					if (pripid !=null) {
						String[] strs = pripid.split("'");  
						pripid=strs[1].toString();
					}		
								// 保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info",target_item_info.asXml());
                    //一工商公示
					// 点击列表页目标条目，获取 工商公示信息->登记信息->基本信息->投资人信息->变更信息
					
					String gsgsxx_djxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qyInfo&maent.pripid=" + pripid+"&czmk=czmk1&random="+new Date().getTime() ;										
					HtmlPage gsgsxx_djxx_page=null;
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_djxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_djxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_djxx_page = WebClient.getCustomHtmlPage(gsgsxx_djxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}					
					resultHtmlMap.put("gsgsxx_djxx", gsgsxx_djxx_page.asXml());
					
					// 点击列表页目标条目，获取 工商公示信息->登记信息->投资人信息->详情
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx_djxx_page.getByXPath("//div[@id='jibenxinxi']/table[@id='table_fr']/tbody/tr/td/a");
					List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
					if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
						for (HtmlAnchor touziren_anchor : touziren_anchors) {
							Map<String, Object> gsgsxx_djxx_tzrxx_xq = new HashMap<String, Object>();
							HtmlPage gsgsxx_djxx_tzrxx_xq_page=null;
							try {
								gsgsxx_djxx_tzrxx_xq_page = touziren_anchor.click();
							} catch (Exception e) {
								gsgsxx_djxx_tzrxx_xq_page=WebClient.getCustomHtmlPage(gsgsxx_djxx_url, firstInfoPage.getWebClient().getCurrentWindow());
							}							
							gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",gsgsxx_djxx_tzrxx_xq_page.asXml());
							gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
						}
					}
					resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",gsgsxx_djxx_tzrxx_xqs);
						
					// 请求获取 工商公示信息->备案信息      主要人员信息->分支机构信息->清算信息					
					String gsgsxx_baxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=baInfo&maent.pripid=" + pripid+"&czmk=czmk2&random="+new Date().getTime() ;
					HtmlPage gsgsxx_baxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_baxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_baxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_baxx_page = WebClient.getCustomHtmlPage(gsgsxx_baxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}											
					resultHtmlMap.put("gsgsxx_baxx", gsgsxx_baxx_page.asXml());

					// 请求获取 工商公示信息->动产抵押登记信息->动产抵押登记信息					
					String gsgsxx_dcdydjxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=dcdyInfo&maent.pripid=" + pripid+"&czmk=czmk4&random="+new Date().getTime() ;				
					HtmlPage gsgsxx_dcdydjxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_dcdydjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_dcdydjxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_dcdydjxx_page = WebClient.getCustomHtmlPage(gsgsxx_dcdydjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}											
					resultHtmlMap.put("gsgsxx_dcdydjxx", gsgsxx_dcdydjxx_page.asXml());
					
					
					// 请求获取 工商公示信息->股权出质登记信息->股权出质登记信息					
					String gsgsxx_gqczdjxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=gqczxxInfo&maent.pripid=" + pripid+"&czmk=czmk4&random="+new Date().getTime() ;				
					HtmlPage gsgsxx_gqczdjxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_gqczdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_gqczdjxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_gqczdjxx_page = WebClient.getCustomHtmlPage(gsgsxx_gqczdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}																
					resultHtmlMap.put("gsgsxx_gqczdjxx", gsgsxx_gqczdjxx_page.asXml());
				

					// 请求获取 工商公示信息->行政处罚信息->行政处罚信息
					String gsgsxx_xzcfxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=cfInfo&maent.pripid=" + pripid+"&czmk=czmk3&random="+new Date().getTime();					
					HtmlPage gsgsxx_xzcfxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_xzcfxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_xzcfxx_page = WebClient.getCustomHtmlPage(gsgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}				
					resultHtmlMap.put("gsgsxx_xzcfxx", gsgsxx_xzcfxx_page.asXml());					
					
				// 请求获取 工商公示信息->行政处罚信息->行政处罚信息详情
					@SuppressWarnings("unchecked")
				   List<HtmlAnchor> xingzhengchufa_anchors = (List<HtmlAnchor>) gsgsxx_xzcfxx_page.getByXPath("//div[@id='gsgsxx_xzcf']/table/tbody/tr/td/a");
						List<Map<String, Object>> gsgsxx_xzcfxx_xqs = new ArrayList<Map<String, Object>>();
						if (null != xingzhengchufa_anchors
								&& xingzhengchufa_anchors.size() > 0) {
							for (HtmlAnchor xingzhengchufa_anchor : xingzhengchufa_anchors) {
								Map<String, Object> gsgsxx_xzcfxx_xq = new LinkedHashMap<String, Object>();
								HtmlPage gsgsxx_xzcfxx_xq_page=null;
								try {
									gsgsxx_xzcfxx_xq_page = xingzhengchufa_anchor.click();
								} catch (Exception e) {
									gsgsxx_xzcfxx_xq_page= WebClient.getCustomHtmlPage(gsgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
								}								
								gsgsxx_xzcfxx_xq.put("gsgsxx_xzcfxx_xq",gsgsxx_xzcfxx_xq_page.asXml());
								gsgsxx_xzcfxx_xqs.add(gsgsxx_xzcfxx_xq);
							}
						}
						resultHtmlMap.put("gsgsxx_xzcfxx_xqs",gsgsxx_xzcfxx_xqs);

					// 请求获取 工商公示信息->经营异常信息->经营异常信息
					String gsgsxx_jyycxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=jyycInfo&maent.pripid=" +pripid+"&czmk=czmk6&random="+new Date().getTime() ;
					HtmlPage gsgsxx_jyycxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_jyycxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_jyycxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_jyycxx_page = WebClient.getCustomHtmlPage(gsgsxx_jyycxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}					
					resultHtmlMap.put("gsgsxx_jyycxx", gsgsxx_jyycxx_page.asXml());			
	
					// 请求获取 工商公示信息->严重违法信息->严重违法信息
					
					String gsgsxx_yzwfxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=yzwfInfo&maent.pripid=" +pripid+"&czmk=czmk4&random="+new Date().getTime() ;					
					HtmlPage gsgsxx_yzwfxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_yzwfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_yzwfxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_yzwfxx_page = WebClient.getCustomHtmlPage(gsgsxx_yzwfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}							
					resultHtmlMap.put("gsgsxx_yzwfxx", gsgsxx_yzwfxx_page.asXml());
	
					// 请求获取 工商公示信息->抽查检查信息->抽查检查信息
					String gsgsxx_ccjcxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=ccjcInfo&maent.pripid=" +pripid+"&czmk=czmk7&random="+new Date().getTime() ;
					HtmlPage gsgsxx_ccjcxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_ccjcxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_ccjcxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_ccjcxx_page = WebClient.getCustomHtmlPage(gsgsxx_ccjcxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}							
					
					resultHtmlMap.put("gsgsxx_ccjcxx", gsgsxx_ccjcxx_page.asXml());
					
					
					
						//二 企业公示
						// 请求获取 企业公示信息->企业年报->列表			
					String qygsxx_qynb_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qygsInfo&maent.pripid=" +pripid+"&czmk=czmk8&random="+new Date().getTime() ;									
					HtmlPage qygsxx_qynb_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_qynb_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_qynb_url)));
					} catch (ClassCastException e) {
						qygsxx_qynb_page = WebClient.getCustomHtmlPage(qygsxx_qynb_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}					
					resultHtmlMap.put("qygsxx_qynb", qygsxx_qynb_page.asXml());
								
					// 请求获取 企业公示信息->企业年报->详情
					@SuppressWarnings("unchecked")
					List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_page
							.getByXPath("//div[@id='sifapanding']/table/tbody/tr/td/a");
					List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
					if (null != qygsxx_qynb_list_as
							&& !qygsxx_qynb_list_as.isEmpty()) {
						for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
							Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
							String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a.getTextContent();
							String qygsxx_qynb_list_pubdate = ((HtmlElement) qygsxx_qynb_list_a.getParentNode().getParentNode().getFirstByXPath("//td[3]")).getTextContent();
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",qygsxx_qynb_list_a_text);
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",qygsxx_qynb_list_pubdate);
							HtmlPage qygsxx_qynb_info_page=null;
							try {
								qygsxx_qynb_info_page =  qygsxx_qynb_list_a.click();
							} catch (Exception e) {
								qygsxx_qynb_info_page = WebClient.getCustomHtmlPage(qygsxx_qynb_url, firstInfoPage.getWebClient().getCurrentWindow());
							}							
							qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",qygsxx_qynb_info_page.asXml());
							qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
						}
					}
					resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

					// 请求获取 企业公示信息->股东及出资信息					
					String qygsxx_gdjczxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qygsForTzrxxInfo&maent.pripid=" +pripid+"&czmk=czmk12&random="+new Date().getTime() ;					
					HtmlPage qygsxx_gdjczxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_gdjczxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_gdjczxx_url)));
					} catch (ClassCastException e) {
						qygsxx_gdjczxx_page = WebClient.getCustomHtmlPage(qygsxx_gdjczxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}							
					resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx_page.asXml());
					

					// 请求获取 企业公示信息->股权变更信息
				    String qygsxx_gqbgxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qygsForTzrbgxxInfo&maent.pripid=" +pripid+"&czmk=czmk15&random="+new Date().getTime() ;
				    HtmlPage qygsxx_gqbgxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_gqbgxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_gqbgxx_url)));
					} catch (ClassCastException e) {
						qygsxx_gqbgxx_page = WebClient.getCustomHtmlPage(qygsxx_gqbgxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}								    
					resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx_page.asXml());
				
					// 请求获取 企业公示信息->行政许可信息	
					String qygsxx_xzxkxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qygsForXzxkInfo&maent.pripid=" +pripid+"&czmk=czmk10&random="+new Date().getTime() ;
					HtmlPage qygsxx_xzxkxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_xzxkxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_xzxkxx_url)));
					} catch (ClassCastException e) {
						qygsxx_xzxkxx_page = WebClient.getCustomHtmlPage(qygsxx_xzxkxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}	
					resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx_page.asXml());
					
					// 请求获取 企业公示信息->行政许可信息->行政许信息详情
					@SuppressWarnings("unchecked")
				    List<HtmlAnchor> xingzhengxuke_anchors = (List<HtmlAnchor>) qygsxx_xzxkxx_page.getByXPath("//div[@id='xzxk']/table/tbody/tr/td/a");
						List<Map<String, Object>> gsgsxx_xzxuke_xqs = new ArrayList<Map<String, Object>>();
						if (null != xingzhengxuke_anchors&& xingzhengxuke_anchors.size() > 0) {
							for (HtmlAnchor xingzhengxuke_anchor : xingzhengxuke_anchors) {
								Map<String, Object> gsgsxx_xzxuke_xq = new LinkedHashMap<String, Object>();
								HtmlPage gsgsxx_xzcfxx_xq_page=null;
								try {
									gsgsxx_xzcfxx_xq_page = xingzhengxuke_anchor.click();
								} catch (Exception e) {
									gsgsxx_xzcfxx_xq_page= WebClient.getCustomHtmlPage(qygsxx_xzxkxx_url, firstInfoPage.getWebClient().getCurrentWindow());
								}		
								gsgsxx_xzxuke_xq.put("qygsxx_xzxkxx_xq",gsgsxx_xzcfxx_xq_page.asXml());
								gsgsxx_xzxuke_xqs.add(gsgsxx_xzxuke_xq);
							}
						}
						resultHtmlMap.put("qygsxx_xzxkxx_xqs",gsgsxx_xzxuke_xqs);	
					// 请求获取 企业公示信息->知识产权出质登记信息				
					String qygsxx_zscqczdjxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qygsForZzcqInfo&maent.pripid=" +pripid+"&czmk=czmk11&random="+new Date().getTime() ;
					HtmlPage qygsxx_zscqczdjxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_zscqczdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_zscqczdjxx_url)));
					} catch (ClassCastException e) {
						qygsxx_zscqczdjxx_page = WebClient.getCustomHtmlPage(qygsxx_zscqczdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}						
					resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx_page.asXml());
					

					// 请求获取 企业公示信息->行政处罚信息
					String qygsxx_xzcfxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qygsForXzcfInfo&maent.pripid=" +pripid+"&czmk=czmk13&random="+new Date().getTime() ;
					HtmlPage qygsxx_xzcfxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_xzcfxx_url)));
					} catch (ClassCastException e) {
						qygsxx_xzcfxx_page = WebClient.getCustomHtmlPage(qygsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}						
					resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx_page.asXml());
					
					
					
                   //三 其他部门信息
					// 请求获取 其他部门公示信息->行政许可信息
					String qtbmgsxx_xzxkxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qtgsInfo&maent.pripid=" +pripid+"&czmk=czmk9&random="+new Date().getTime() ;					
					HtmlPage qtbmgsxx_xzxkxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qtbmgsxx_xzxkxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qtbmgsxx_xzxkxx_url)));
					} catch (ClassCastException e) {
						qtbmgsxx_xzxkxx_page = WebClient.getCustomHtmlPage(qtbmgsxx_xzxkxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}								
					resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx_page.asXml());
			

					// 请求获取 其他部门公示信息->行政处罚信息
					String qtbmgsxx_xzcfxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=qtgsForCfInfo&maent.pripid=" +pripid+"&czmk=czmk16&random="+new Date().getTime() ;
					HtmlPage qtbmgsxx_xzcfxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qtbmgsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qtbmgsxx_xzcfxx_url)));
					} catch (ClassCastException e) {
						qtbmgsxx_xzcfxx_page = WebClient.getCustomHtmlPage(qtbmgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}										
					resultHtmlMap.put("qtbmgsxx_xzcfxx", qtbmgsxx_xzcfxx_page.asXml());
					 //四 司法协助公示信息
					// 请求获取 司法协助公示信息->股权冻结信息		
					String sfxzgsxx_gqdjxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=sfgsInfo&maent.pripid=" +pripid+"&czmk=czmk17&random="+new Date().getTime() ;					
					HtmlPage sfxzgsxx_gqdjxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						sfxzgsxx_gqdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(sfxzgsxx_gqdjxx_url)));
					} catch (ClassCastException e) {
						sfxzgsxx_gqdjxx_page = WebClient.getCustomHtmlPage(sfxzgsxx_gqdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}								
					resultHtmlMap.put("sfxzgsxx_gqdjxx", sfxzgsxx_gqdjxx_page.asXml());

					// 请求获取 司法协助公示信息->股东变更信息
					String sfxzgsxx_gqbgxx_url = "http://gsxt.scaic.gov.cn/ztxy.do?method=sfgsbgInfo&maent.pripid=" +pripid+"&czmk=czmk18&random="+new Date().getTime() ;
					HtmlPage sfxzgsxx_gqbgxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						sfxzgsxx_gqbgxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(sfxzgsxx_gqbgxx_url)));
					} catch (ClassCastException e) {
						sfxzgsxx_gqbgxx_page = WebClient.getCustomHtmlPage(sfxzgsxx_gqbgxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}																				
					resultHtmlMap.put("sfxzgsxx_gqbgxx", sfxzgsxx_gqbgxx_page.asXml());

					break;
				}

			}
		}
		return resultHtmlMap;
	}
//青海数据
@SuppressWarnings("unchecked")
private Map<String, Object> getHtmlInfoMapOfQinghai(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
					Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();		
					WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();		
					//
					HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']"));
					if (divByXPath == null) {								
						 HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
						 if (firstByXPath!=null && firstByXPath.getTextContent().contains("您搜索的条件无查询结果") ) {
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
						}else{
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);	
						}
										
					} else {						
					 HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']/ul/li/a"));					
						  if (firstByXPath!=null) {						
						   resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
															
						} 	
					}
					//			
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
					LOGGER.info(anchors.toString());		
					if (anchors!=null && !anchors.isEmpty()) {
						for (HtmlAnchor anchor : anchors) {
							String anchorTitle = anchor.getTextContent().toString().trim();
							
							if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目		
								//公共参数
								String id = anchor.getAttribute("href");
								if (id !=null) {
									id=id.substring(id.length()-32);
								}		
								// 保存列表页目标条目信息
								HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
								resultHtmlMap.put("target_item_info",target_item_info.asXml());
					
								
								//一、获取工商公示信息
								HtmlPage gsgsxx_page = anchor.click();
								resultHtmlMap.put("gsgsxx", gsgsxx_page.asXml());
								//展开更多
								List<HtmlAnchor> more_anchors = (List<HtmlAnchor>) gsgsxx_page.getByXPath("//table[@id='altTab']/tbody/tr/td/a");
								for (HtmlAnchor moreAnchor : more_anchors) {
									moreAnchor.click();
								}
								
								
								   //获取工商公示信息--登记信息--变更信息（详情）														
								String gsgsxx_djxx_bgxx_url = "http://218.95.241.36/QueryAltList.jspx?pno=1&mainId=" + id;
								TextPage gsgsxx_djxx_bgxx_page =firstInfoPage.getWebClient().getPage(gsgsxx_djxx_bgxx_url);
								resultHtmlMap.put("gsgsxx_djxx_bgxx", gsgsxx_djxx_bgxx_page.getWebResponse().getContentAsString());
																
								
								//工商公示信息-->股东信息-->详情
								List<HtmlAnchor> anchors_gdxx_xqs = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
								List<String> gdxx_xqs = new ArrayList<String>();
								for (HtmlAnchor htmlAnchor : anchors_gdxx_xqs) {
									HtmlPage gdxx_detail = htmlAnchor.click();
									gdxx_xqs.add(gdxx_detail.asXml());
								}
								resultHtmlMap.put("gsgsxx_gdxx_xqs", gdxx_xqs);
								
								List<?> liElements = gsgsxx_page.getByXPath("//div[@id='leftTabs']/ul/li");
								//二、获取个体工商公示信息								
								String qygsxx_page_url = "http://218.95.241.36/enterprisePublicity.jspx?id="+id;					
								HtmlPage qygsxx_page=null;					
								try {						
									firstInfoPage.getWebClient().getOptions().setTimeout(120000);
									qygsxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_page_url)));
								} catch (ClassCastException e) {
									qygsxx_page = WebClient.getCustomHtmlPage(qygsxx_page_url, firstInfoPage.getWebClient().getCurrentWindow());
								} finally {
									firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
								}				
								resultHtmlMap.put("qygsxx", qygsxx_page.asXml());					
								
								
								//企业公示信息-->企业年报-->详情
								List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
								List<String> nbxx_list = new ArrayList<String>();
								for (HtmlAnchor htmlAnchor : anchors_detail) {
									HtmlPage nb_detail = htmlAnchor.click();
									nbxx_list.add(nb_detail.asXml());
								}
								resultHtmlMap.put("qygsxx_qynb_detail", nbxx_list);
								
								
	                             //获取企业公示信息--行政许可信息																					
								String qygsxx_xzxkxx_more_url = "http://218.95.241.36/QueryLicenseRegList.jspx?pno=1&mainId=" + id;
								TextPage qygsxx_xzxkxx_more_page = firstInfoPage.getWebClient().getPage(qygsxx_xzxkxx_more_url);
								resultHtmlMap.put("qygsxx_xzxkxx_more", qygsxx_xzxkxx_more_page.getWebResponse().getContentAsString());
								
								
								//三、获取其他部门公示信息
								String gsgsxx_qtbmxx_url = "http://218.95.241.36/otherDepartment.jspx?id="+id ;
								HtmlPage qtbmgsxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_qtbmxx_url);
								resultHtmlMap.put("qtbmgsxx", qtbmgsxx_page.asXml());	
								
								//四、获取 司法协助公示信息  http://218.95.241.36/justiceAssistance.jspx?id=810F6BFFC4B5D2C42B21346544850A61
								if(liElements.size()>3) {
									String gsgsxx_sfxzgsxx_url = "http://218.95.241.36/justiceAssistance.jspx?id="+id ;
									HtmlPage sfxzxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_sfxzgsxx_url);
									resultHtmlMap.put("sfxzxx", sfxzxx_page.asXml());	
								}
							
								
								break;
							}
					
						}
					}
					return resultHtmlMap;
	}
				
//西藏数据
  @SuppressWarnings("unchecked")
  private Map<String, Object> getHtmlInfoMapOfXizang(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
						Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();	
					
						//
						HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']"));
						if (divByXPath == null) {								
							 HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
							 if (firstByXPath!=null && firstByXPath.getTextContent().contains("您搜索的条件无查询结果") ) {
								resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
							}else{
								resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);	
							}
											
						} else {						
						 HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']/ul/li/a"));					
							  if (firstByXPath!=null) {						
							   resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
																
							} 	
						}
						//				
						List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
						LOGGER.info(anchors.toString());		
						if (anchors!=null && !anchors.isEmpty()) {
							for (HtmlAnchor anchor : anchors) {
								String anchorTitle = anchor.getTextContent().toString().trim();
								
								if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目		
									//公共参数
									String id = anchor.getAttribute("href");
									if (id !=null) {
										id=id.substring(id.length()-32);
									}		
									// 保存列表页目标条目信息
									HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
									resultHtmlMap.put("target_item_info",target_item_info.asXml());
						
									
									//一、获取工商公示信息
									HtmlPage gsgsxx_page = anchor.click();
									resultHtmlMap.put("gsgsxx", gsgsxx_page.asXml());
									//展开更多
									List<HtmlAnchor> more_anchors = (List<HtmlAnchor>) gsgsxx_page.getByXPath("//table[@id='altTab']/tbody/tr/td/a");
									for (HtmlAnchor moreAnchor : more_anchors) {
										moreAnchor.click();
									}
									
		                            //获取工商公示信息--登记信息--变更信息（详情）														
									String gsgsxx_djxx_bgxx_url = "http://gsxt.xzaic.gov.cn/QueryAltList.jspx?pno=1&mainId=" + id;
									TextPage gsgsxx_djxx_bgxx_page =firstInfoPage.getWebClient().getPage(gsgsxx_djxx_bgxx_url);												
									resultHtmlMap.put("gsgsxx_djxx_bgxx", gsgsxx_djxx_bgxx_page.getWebResponse().getContentAsString());
									
									
									//工商公示信息-->股东信息-->详情
									List<HtmlAnchor> anchors_gdxx_xqs = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
									List<String> gdxx_xqs = new ArrayList<String>();
									for (HtmlAnchor htmlAnchor : anchors_gdxx_xqs) {										
										String attribute = htmlAnchor.getAttribute("onclick");
										String detailUrl = "http://gsxt.xzaic.gov.cn" + attribute.split("'")[1];
										HtmlPage dcdyxx_detail_page = null;
										try {
											dcdyxx_detail_page = firstInfoPage.getWebClient().getPage(detailUrl);
										} catch (Exception e) {
											dcdyxx_detail_page = WebClient.getCustomHtmlPage(detailUrl, firstInfoPage.getWebClient().getCurrentWindow());
										}
										gdxx_xqs.add(dcdyxx_detail_page.asXml());
									}
									resultHtmlMap.put("gsgsxx_gdxx_xqs", gdxx_xqs);
									
									List<?> liElements = gsgsxx_page.getByXPath("//div[@id='leftTabs']/ul/li");
									//二、获取个体工商公示信息
									
									String qygsxx_url = "http://gsxt.xzaic.gov.cn/enterprisePublicity.jspx?id="+id ;
									HtmlPage qygsxx_page = firstInfoPage.getWebClient().getPage(qygsxx_url);
									resultHtmlMap.put("qygsxx", qygsxx_page.asXml());	
									//企业公示信息-->企业年报-->详情
									List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
									List<String> nbxx_list = new ArrayList<String>();
									for (HtmlAnchor htmlAnchor : anchors_detail) {
										HtmlPage nb_detail = htmlAnchor.click();
										nbxx_list.add(nb_detail.asXml());
									}
									resultHtmlMap.put("qygsxx_qynb_detail", nbxx_list);
									
									//三、获取其他部门公示信息  http://gsxt.xzaic.gov.cn/otherDepartment.jspx?id=096A5BAB695E65F9CD4EE35BBCA224D2
									String gsgsxx_qtbmxx_url = "http://gsxt.xzaic.gov.cn/otherDepartment.jspx?id="+id ;
									HtmlPage qtbmgsxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_qtbmxx_url);
									resultHtmlMap.put("qtbmgsxx", qtbmgsxx_page.asXml());	
									
									//四、获取 司法协助公示信息 
									if(liElements.size()>3) {
										String gsgsxx_sfxzgsxx_url = "http://gsxt.xzaic.gov.cn/justiceAssistance.jspx?id="+id ;
										HtmlPage sfxzxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_sfxzgsxx_url);
										resultHtmlMap.put("sfxzxx", sfxzxx_page.asXml());	
										}
									
										
										break;
									}
							
								}
							}
			return resultHtmlMap;
  }	
  
//贵州数据    获取请求地址
private String getScztPath(String ztlx, String qylx) {
		String path = "";
		if ("1".equals(ztlx)) {
		    if (qylx.indexOf("12") == 0) {
		        path = "gfgs";
		    } else if (qylx.indexOf("1") == 0) {
		        path = "nzgs";
		    } else if (qylx.indexOf("2") == 0) {
		        path = "nzgsfgs";
		    } else if (qylx.indexOf("3") == 0) {
		        path = "nzqyfr";
		    } else if ("4310".equals(qylx)) {
		        path = "nzyyfz";
		    } else if (("4000".equals(qylx)) || (qylx.indexOf("41") == 0) || (qylx.indexOf("42") == 0) || (qylx.indexOf("43") == 0) || (qylx.indexOf("44") == 0) || (qylx.indexOf("46") == 0) || (qylx.indexOf("47") == 0)) {
		        path = "nzyy";
		    } else if (qylx.indexOf("453") == 0) {
		        path = "nzhh";
		    } else if ("4540".equals(qylx)) {
		        path = "grdzgs";
		    } else if (qylx.indexOf("455") == 0) {
		        path = "nzhhfz";
		    } else if ("4560".equals(qylx)) {
		        path = "grdzfzjg";
		    } else if ((qylx.indexOf("50") == 0) || (qylx.indexOf("51") == 0) || (qylx.indexOf("52") == 0) || (qylx.indexOf("53") == 0) || (qylx.indexOf("60") == 0) || (qylx.indexOf("61") == 0) || (qylx.indexOf("62") == 0) || (qylx.indexOf("63") == 0)) {
		        path = "wstz";
		    } else if (((qylx.indexOf("58") == 0) || (qylx.indexOf("68") == 0) || (qylx.indexOf("70") == 0) || (qylx.indexOf("71") == 0) || ("7310".equals(qylx)) || ("7390".equals(qylx))) && (!"5840".equals(qylx)) && (!"6840".equals(qylx))) {
		        path = "wstzfz";
		    } else if ((qylx.indexOf("54") == 0) || (qylx.indexOf("64") == 0)) {
		        path = "wzhh";
		    } else if ((qylx.indexOf("5840") == 0) || (qylx.indexOf("6840") == 0)) {
		        path = "wzhhfz";
		    } else if ("7200".equals(qylx)) {
		        path = "czdbjg";
		    } else if ("7300".equals(qylx)) {
		        path = "wgqycsjyhd";
		    } else if ("9100".equals(qylx)) {
		        path = "nmzyhzs";
		    } else if ("9200".equals(qylx)) {
		        path = "nmzyhzsfz";
		    }
		} else if ("2".equals(ztlx)) {
		    path = "gtgsh";
		}
	    return "http://gsxt.gzgs.gov.cn/"+path+"/index.jsp";
}
  
//贵州数据
 @SuppressWarnings("unchecked")
private Map<String, Object> getHtmlInfoMapOfGuizhou(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
	 
	 Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();	
		String content = firstInfoPage.getElementsByTagName("body").get(0).getTextContent().trim();
		String successed=new JsonParser().parse(content).getAsJsonObject().get("successed").getAsString();	 								 						
		if (successed.contains("false")) {
			String message=new JsonParser().parse(content).getAsJsonObject().get("message").getAsString();
			if (message.contains("验证码输入不正确")) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			}else{
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
			} 								
		}else {																						
			String count=new JsonParser().parse(content).getAsJsonObject().get("count").getAsString();	 
			if (Integer.parseInt(count)==0) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);	
			}else{
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);	
				//解析返回的json数据
				JsonElement data0Ele = new JsonParser().parse(content).getAsJsonObject().get("data").getAsJsonArray().get(0);
				
				JsonObject data0Object = data0Ele.getAsJsonObject();
				String nbxh = data0Object.get("nbxh").getAsString();
				String zch = data0Object.get("zch").getAsString(); 
				String ztlx = data0Object.get("ztlx").getAsString();
				String qylx = data0Object.get("qylx").getAsString();
				
			   //封装post请求	
				String url = getScztPath(ztlx, qylx);
				WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new NameValuePair("nbxh", nbxh));
				nameValuePairs.add(new NameValuePair("qymc", keyword));
				nameValuePairs.add(new NameValuePair("zch", zch));
				nameValuePairs.add(new NameValuePair("dmmc", "dmmc"));
				webRequest.setRequestParameters(nameValuePairs);
				webRequest.setCharset("utf-8");
			   // 点击列表页目标条目，获取 工商公示信息（登记信息->基本信息->投资人信息->变更信息）
				HtmlPage gsgsxx_djxx = firstInfoPage.getWebClient().getPage(webRequest); 										 							
	            resultHtmlMap.put("gsgsxx_djxx", gsgsxx_djxx.asXml());
	        	// 点击列表页目标条目，获取 工商公示信息（登记信息->投资人信息->详情）
				@SuppressWarnings("unchecked")
				List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx_djxx.getByXPath("//table[@id='tzxxTable']/tbody/tr/td/a");
				List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
				if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
					for (HtmlAnchor touziren_anchor : touziren_anchors) {
						Map<String, Object> gsgsxx_djxx_tzrxx_xq = new HashMap<String, Object>();
						HtmlPage gsgsxx_djxx_tzrxx_xq_page = touziren_anchor.click();
						gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",gsgsxx_djxx_tzrxx_xq_page.asXml());											
						gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
					}
				}						
				resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",gsgsxx_djxx_tzrxx_xqs);

				// 请求获取 工商公示信息（备案信息->主要人员信息->分支机构信息->清算信息）
				HtmlElement js_tab2 = (HtmlElement) gsgsxx_djxx.getFirstByXPath("//li[@id='baxx']");
				String gsgsxx_baxx = null;
				if (null != js_tab2 && !StringUtils.isEmpty(js_tab2)) {
					HtmlPage gsgsxx_baxx_page = js_tab2.click();									
					gsgsxx_baxx = gsgsxx_baxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_baxx", gsgsxx_baxx);

				// 请求获取 工商公示信息（动产抵押登记信息->动产抵押登记信息）
				HtmlElement js_tab4 = (HtmlElement) gsgsxx_djxx.getFirstByXPath("//li[@id='dcdy']");
				String gsgsxx_dcdydjxx_dcdydjxx = null;
				if (null != js_tab4 && !StringUtils.isEmpty(js_tab4)) {
					HtmlPage gsgsxx_dcdydjxx_dcdydjxx_page = js_tab4.click();
					gsgsxx_dcdydjxx_dcdydjxx = gsgsxx_dcdydjxx_dcdydjxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx",gsgsxx_dcdydjxx_dcdydjxx);

				// 请求获取 工商公示信息（股权出质登记信息->股权出质登记信息）
				HtmlElement js_tab3 = (HtmlElement) gsgsxx_djxx.getFirstByXPath("//li[@id='gqcz']");
				String gsgsxx_gqczdjxx_gqczdjxx = null;
				if (null != js_tab3 && !StringUtils.isEmpty(js_tab3)) {
					HtmlPage gsgsxx_gqczdjxx_gqczdjxx_page = js_tab3.click();
					gsgsxx_gqczdjxx_gqczdjxx = gsgsxx_gqczdjxx_gqczdjxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_gqczdjxx_gqczdjxx",gsgsxx_gqczdjxx_gqczdjxx);

				// 请求获取 工商公示信息（行政处罚信息->行政处罚信息）
				HtmlElement js_tab7 = (HtmlElement) gsgsxx_djxx.getFirstByXPath("//li[@id='xzcf']");									
				String gsgsxx_xzcfxx_xzcfxx = null;
				if (null != js_tab7 && !StringUtils.isEmpty(js_tab7)) {
					HtmlPage gsgsxx_xzcfxx_xzcfxx_page = js_tab7.click();
					gsgsxx_xzcfxx_xzcfxx = gsgsxx_xzcfxx_xzcfxx_page.asXml();
					// 请求获取 工商公示信息（行政处罚信息->行政处罚信息详情）
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> xingzhengchufa_anchors = (List<HtmlAnchor>) gsgsxx_xzcfxx_xzcfxx_page
							.getByXPath("//div[@id='xingzhengchufa']/table/tbody/tr/td/a");
					List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs = new ArrayList<Map<String, Object>>();
					if (null != xingzhengchufa_anchors
							&& xingzhengchufa_anchors.size() > 0) {
						for (HtmlAnchor xingzhengchufa_anchor : xingzhengchufa_anchors) {
							Map<String, Object> gsgsxx_xzcfxx_xzcfxx_xq = new LinkedHashMap<String, Object>();
							HtmlPage gsgsxx_xzcfxx_xzcfxx_xq_page = xingzhengchufa_anchor.click();
							gsgsxx_xzcfxx_xzcfxx_xq.put("gsgsxx_xzcfxx_xzcfxx_xq",gsgsxx_xzcfxx_xzcfxx_xq_page.asXml());
							gsgsxx_xzcfxx_xzcfxx_xqs
									.add(gsgsxx_xzcfxx_xzcfxx_xq);
						}
					}
					resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx_xqs",
							gsgsxx_xzcfxx_xzcfxx_xqs);
				}
				resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx", gsgsxx_xzcfxx_xzcfxx);

				// 请求获取 工商公示信息->经营异常信息->经营异常信息
				HtmlElement js_tab5 = (HtmlElement) gsgsxx_djxx
						.getFirstByXPath("//li[@id='jyyc']");
				String gsgsxx_jyycxx_jyycxx = null;
				if (null != js_tab5 && !StringUtils.isEmpty(js_tab5)) {
					HtmlPage gsgsxx_jyycxx_jyycxx_page = js_tab5.click();
					gsgsxx_jyycxx_jyycxx = gsgsxx_jyycxx_jyycxx_page.asXml();
					// 请求获取 工商公示信息（经营异常信息->列入经营异常名录原因详情）
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> jingyingyichang_anchors = (List<HtmlAnchor>) gsgsxx_jyycxx_jyycxx_page.getByXPath("//div[@id='jingyingyichang']/table/tbody/tr/td/a");
					List<Map<String, Object>> gsgsxx_jyycxx_jyycxx_xqs = new ArrayList<Map<String, Object>>();
					if (null != jingyingyichang_anchors
							&& jingyingyichang_anchors.size() > 0) {
						for (HtmlAnchor jingyingyichang_anchor : jingyingyichang_anchors) {
							Map<String, Object> gsgsxx_jyycxx_jyycxx_xq = new LinkedHashMap<String, Object>();
							HtmlPage gsgsxx_jyycxx_jyycxx_xq_page = jingyingyichang_anchor.click();
							gsgsxx_jyycxx_jyycxx_xq.put("gsgsxx_jyycxx_jyycxx_xq",gsgsxx_jyycxx_jyycxx_xq_page.asXml());
							gsgsxx_jyycxx_jyycxx_xqs.add(gsgsxx_jyycxx_jyycxx_xq);
						}
					}
					resultHtmlMap.put("gsgsxx_jyycxx_jyycxx_xqs",gsgsxx_jyycxx_jyycxx_xqs);
				}
				resultHtmlMap.put("gsgsxx_jyycxx_jyycxx", gsgsxx_jyycxx_jyycxx);

				// 请求获取 工商公示信息->严重违法信息->严重违法信息
				HtmlElement js_tab6 = (HtmlElement) gsgsxx_djxx
						.getFirstByXPath("//li[@id='yzwf']");
				String gsgsxx_yzwfxx_yzwfxx = null;
				if (null != js_tab6 && !StringUtils.isEmpty(js_tab6)) {
					HtmlPage gsgsxx_yzwfxx_yzwfxx_page = js_tab6.click();
					gsgsxx_yzwfxx_yzwfxx = gsgsxx_yzwfxx_yzwfxx_page.asXml();
				}
				resultHtmlMap.put("gsgsxx_yzwfxx_yzwfxx", gsgsxx_yzwfxx_yzwfxx);

				// 请求获取 工商公示信息->抽查检查信息->抽查检查信息
				HtmlElement js_tab8 = (HtmlElement) gsgsxx_djxx.getFirstByXPath("//li[@id='ccjc']");
				String gsgsxx_ccjcxx_ccjcxx = null;
				if (null != js_tab8 && !StringUtils.isEmpty(js_tab8)) {
					HtmlPage gsgsxx_ccjcxx_ccjcxx_page = js_tab8.click();
					gsgsxx_ccjcxx_ccjcxx = gsgsxx_ccjcxx_ccjcxx_page.asXml();											
				}
				resultHtmlMap.put("gsgsxx_ccjcxx_ccjcxx", gsgsxx_ccjcxx_ccjcxx);

				// 请求获取 企业公示信息->企业年报->列表
				
				List<?> liElements = gsgsxx_djxx.getByXPath("//div[@id='leftTabs']/ul/li");										
				HtmlElement js_left2 = (HtmlElement) gsgsxx_djxx.getByXPath(
						"//div[@id='leftTabs']/ul/li").get(1);
				HtmlPage qygsxx_qynb_list_page = js_left2.click();
				resultHtmlMap.put("qygsxx_qynb", qygsxx_qynb_list_page.asXml());					

				// 请求获取 企业公示信息->企业年报->详情
				@SuppressWarnings("unchecked")
				List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_list_page.getByXPath("//div[@id='qiyenianbao']/table/tbody/tr/td/a");
				List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
				if (null != qygsxx_qynb_list_as
						&& !qygsxx_qynb_list_as.isEmpty()) {
					for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
						Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
						String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a.getTextContent().toString().trim();
						String qygsxx_qynb_list_pubdate = String.valueOf(((HtmlElement) qygsxx_qynb_list_a.getParentNode().getParentNode().getFirstByXPath("//td[3]")).getTextContent());
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",qygsxx_qynb_list_a_text);
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",qygsxx_qynb_list_pubdate);
						HtmlPage qygsxx_qynb_info_page = (HtmlPage) qygsxx_qynb_list_a.click();
						qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",qygsxx_qynb_info_page.asXml());										
						qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
					}
				}
				resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

				// 请求获取 企业公示信息->投资人及出资信息
				HtmlElement js_lefttab3 = (HtmlElement) qygsxx_qynb_list_page
						.getFirstByXPath("//li[@id='tzxx']");
				String qygsxx_gdjczxx = null;
				if (null != js_lefttab3 && !StringUtils.isEmpty(js_lefttab3)) {
					HtmlPage qygsxx_gdjczxx_page = js_lefttab3.click();
					qygsxx_gdjczxx = qygsxx_gdjczxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx);

				// 请求获取 企业公示信息->股权变更信息
				HtmlElement js_lefttab6 = (HtmlElement) qygsxx_qynb_list_page
						.getFirstByXPath("//li[@id='gqzr']");
				String qygsxx_gqbgxx = null;
				if (null != js_lefttab6 && !StringUtils.isEmpty(js_lefttab6)) {
					HtmlPage qygsxx_gqbgxx_page = js_lefttab6.click();
					qygsxx_gqbgxx = qygsxx_gqbgxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx);

				// 请求获取 企业公示信息->行政许可信息
				HtmlElement js_lefttab1 = (HtmlElement) qygsxx_qynb_list_page
						.getFirstByXPath("//li[@id='xzxk']");
				String qygsxx_xzxkxx = null;
				if (null != js_lefttab1 && !StringUtils.isEmpty(js_lefttab1)) {
					HtmlPage qygsxx_xzxkxx_page = js_lefttab1.click();
					qygsxx_xzxkxx = qygsxx_xzxkxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx);

				// 请求获取 企业公示信息->知识产权出质登记信息
				HtmlElement js_lefttab2 = (HtmlElement) qygsxx_qynb_list_page
						.getFirstByXPath("//li[@id='zscq']");
				String qygsxx_zscqczdjxx = null;
				if (null != js_lefttab2 && !StringUtils.isEmpty(js_lefttab2)) {
					HtmlPage qygsxx_zscqczdjxx_page = js_lefttab2.click();
					qygsxx_zscqczdjxx = qygsxx_zscqczdjxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx);

				// 请求获取 企业公示信息->行政处罚信息
				HtmlElement js_lefttab4 = (HtmlElement) qygsxx_qynb_list_page
						.getFirstByXPath("//li[@id='xzcf']");
				String qygsxx_xzcfxx = null;
				if (null != js_lefttab4 && !StringUtils.isEmpty(js_lefttab4)) {
					HtmlPage qygsxx_xzcfxx_page = js_lefttab4.click();
					qygsxx_xzcfxx = qygsxx_xzcfxx_page.asXml();
				}
				resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx);

				// 请求获取 其他部门公示信息																		
			    // 请求获取 其他部门公示信息->行政许可信息
				List<NameValuePair> nameValuePairs2 = new ArrayList<NameValuePair>();
				nameValuePairs2.add(new NameValuePair("c", "0"));
				nameValuePairs2.add(new NameValuePair("t", "37"));
				nameValuePairs2.add(new NameValuePair("nbxh", nbxh));
				
				String xzxkurl = "http://gsxt.gzgs.gov.cn/nzgs/search!searchOldData.shtml";
				WebRequest xzxkRequest2 = new WebRequest(new URL(xzxkurl), HttpMethod.POST);
				xzxkRequest2.setRequestParameters(nameValuePairs2);
				xzxkRequest2.setCharset("utf-8");
				HtmlPage qtbmgsxx_xzxk = firstInfoPage.getWebClient().getPage(xzxkRequest2); 
				resultHtmlMap.put("qtbmgsxx_xzxk", qtbmgsxx_xzxk.asXml());
				
				// 请求获取 其他部门公示信息->行政处罚信息
				List<NameValuePair> nameValuePairs3 = new ArrayList<NameValuePair>();
				nameValuePairs3.add(new NameValuePair("c", "0"));
				nameValuePairs3.add(new NameValuePair("t", "38"));
				nameValuePairs3.add(new NameValuePair("nbxh", nbxh));
				
				WebRequest xzxkRequest3 = new WebRequest(new URL(xzxkurl), HttpMethod.POST);
				xzxkRequest3.setRequestParameters(nameValuePairs3);
				xzxkRequest3.setCharset("utf-8");
				HtmlPage qtbmgsxx_xzcf = firstInfoPage.getWebClient().getPage(xzxkRequest3); 				
				resultHtmlMap.put("qtbmgsxx_xzcf", qtbmgsxx_xzcf.asXml());

				// 请求获取 司法协助公示信息->股权冻结信息
			
				    if (liElements.size()>3) {
		            HtmlElement js_left23 = (HtmlElement) gsgsxx_djxx.getByXPath("//div[@id='leftTabs']/ul/li").get(3);
		            HtmlPage sfxzgsxx_gqdjxx_list_page = js_left23.click();
					HtmlElement firstByXPath2 = (HtmlElement) sfxzgsxx_gqdjxx_list_page
							.getFirstByXPath("//li[@id='gqdj']");
					String sfxzgsxx_gqdjxx_list = null;
					if (null != firstByXPath2
							&& !StringUtils.isEmpty(firstByXPath2)) {
						HtmlPage click1 = firstByXPath2.click();
						sfxzgsxx_gqdjxx_list = click1.asXml();
					}
					resultHtmlMap.put("sfxzgsxx_gqdjxx_list", sfxzgsxx_gqdjxx_list);

					// 请求获取 司法协助公示信息->股东变更信息
					HtmlElement js_lefttab231 = (HtmlElement) sfxzgsxx_gqdjxx_list_page.getFirstByXPath("//li[@id='gdbg']");
					String sfxzgsxx_gqbgxx_list = null;
					if (null != js_lefttab231
							&& !StringUtils.isEmpty(js_lefttab231)) {
						HtmlPage sfxzgsxx_gqbgxx_list_page = js_lefttab231.click();
						sfxzgsxx_gqbgxx_list = sfxzgsxx_gqbgxx_list_page.asXml();
					}
					resultHtmlMap.put("sfxzgsxx_gqbgxx_list", sfxzgsxx_gqbgxx_list);																				
			}
		}
			
	}			
return resultHtmlMap;
 }			

 
 	//新疆数据
	@SuppressWarnings("unchecked")
	private Map<String, Object> getHtmlInfoMapOfXinjiang(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();	
		WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();	
		//
		HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='center-1']/div/ul/li[@class='font16']"));
		if (divByXPath == null) {									
			HtmlElement	firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//table[@class='d-border']/tbody/tr/td/div"));
				if (firstByXPath!=null && firstByXPath.getTextContent().contains("验证码错误")) {							
					 resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
				} else{							
					firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@style='margin-left: 20px;font-size: 15px;']"));							
				  if (firstByXPath!=null) {
					  String textContent=firstByXPath.getTextContent();
					  if (textContent.contains("您搜索的条件无查询结果")) {
						  resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					}									
				} 	
					
			}				 
		} else {		
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='center-1']/div/ul/li[@class='font16']/a"));
			if (firstByXPath !=null) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}			
		}
		//		
		@SuppressWarnings("unchecked")
		
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='center-1']/div/ul/li[@class='font16']/a");
		LOGGER.info(anchors.toString());		
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				
				if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目	
					matchFlag = true;
					String pripid = anchor.getAttribute("onclick");
					if (pripid !=null) {
						String[] strs = pripid.split("'");  
						pripid=strs[1].toString();
					}		
								// 保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info",target_item_info.asXml());
                 //一工商公示
					// 点击列表页目标条目，获取 工商公示信息->登记信息->基本信息->投资人信息->变更信息
					//"http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=index&random="+new Date().getTime()
			
					 //String gsgsxx_djxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qyInfo&maent.pripid=" + pripid+"&czmk=czmk1&random="+new Date().getTime() ;										
					//HtmlPage gsgsxx_djxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_djxx_url)));
					//resultHtmlMap.put("gsgsxx_djxx", gsgsxx_djxx_page.asXml());
					
					String gsgsxx_djxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qyInfo&maent.pripid=" + pripid+"&czmk=czmk1&random="+new Date().getTime() ;										
					HtmlPage gsgsxx_djxx_page=null;
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_djxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_djxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_djxx_page = WebClient.getCustomHtmlPage(gsgsxx_djxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}					
					resultHtmlMap.put("gsgsxx_djxx", gsgsxx_djxx_page.asXml());
					
					// 点击列表页目标条目，获取 工商公示信息->登记信息->投资人信息->详情
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx_djxx_page.getByXPath("//div[@id='jibenxinxi']/table[@id='table_fr']/tbody/tr/td/a");
					List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
					if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
						for (HtmlAnchor touziren_anchor : touziren_anchors) {
							Map<String, Object> gsgsxx_djxx_tzrxx_xq = new HashMap<String, Object>();
							HtmlPage gsgsxx_djxx_tzrxx_xq_page=null;
							try {
								gsgsxx_djxx_tzrxx_xq_page = touziren_anchor.click();
							} catch (Exception e) {
								gsgsxx_djxx_tzrxx_xq_page=WebClient.getCustomHtmlPage(gsgsxx_djxx_url, firstInfoPage.getWebClient().getCurrentWindow());
							}							
							gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",gsgsxx_djxx_tzrxx_xq_page.asXml());
							gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
						}
					}
					resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",gsgsxx_djxx_tzrxx_xqs);
						
					// 请求获取 工商公示信息->备案信息      主要人员信息->分支机构信息->清算信息					
					String gsgsxx_baxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=baInfo&maent.pripid=" + pripid+"&czmk=czmk2&random="+new Date().getTime() ;
//					HtmlPage gsgsxx_baxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_baxx_url)));
//					resultHtmlMap.put("gsgsxx_baxx", gsgsxx_baxx_page.asXml());
					HtmlPage gsgsxx_baxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_baxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_baxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_baxx_page = WebClient.getCustomHtmlPage(gsgsxx_baxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}											
					resultHtmlMap.put("gsgsxx_baxx", gsgsxx_baxx_page.asXml());

					// 请求获取 工商公示信息->动产抵押登记信息->动产抵押登记信息					
					String gsgsxx_dcdydjxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=dcdyInfo&maent.pripid=" + pripid+"&czmk=czmk4&random="+new Date().getTime() ;
//					HtmlPage gsgsxx_dcdydjxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_dcdydjxx_url)));
//					resultHtmlMap.put("gsgsxx_dcdydjxx", gsgsxx_dcdydjxx_page.asXml());
					HtmlPage gsgsxx_dcdydjxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_dcdydjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_dcdydjxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_dcdydjxx_page = WebClient.getCustomHtmlPage(gsgsxx_dcdydjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}											
					resultHtmlMap.put("gsgsxx_dcdydjxx", gsgsxx_dcdydjxx_page.asXml());
					
					// 请求获取 工商公示信息->股权出质登记信息->股权出质登记信息					
					String gsgsxx_gqczdjxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=gqczxxInfo&maent.pripid=" + pripid+"&czmk=czmk4&random="+new Date().getTime() ;
//					HtmlPage gsgsxx_gqczdjxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_gqczdjxx_url)));
//					resultHtmlMap.put("gsgsxx_gqczdjxx", gsgsxx_gqczdjxx_page.asXml());
					HtmlPage gsgsxx_gqczdjxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_gqczdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_gqczdjxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_gqczdjxx_page = WebClient.getCustomHtmlPage(gsgsxx_gqczdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}																
					resultHtmlMap.put("gsgsxx_gqczdjxx", gsgsxx_gqczdjxx_page.asXml());
					
		

					// 请求获取 工商公示信息->行政处罚信息->行政处罚信息
					String gsgsxx_xzcfxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=cfInfo&maent.pripid=" + pripid+"&czmk=czmk3&random="+new Date().getTime() ;
//					HtmlPage gsgsxx_xzcfxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_xzcfxx_url)));
//					resultHtmlMap.put("gsgsxx_xzcfxx", gsgsxx_xzcfxx_page.asXml());
					HtmlPage gsgsxx_xzcfxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						gsgsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_xzcfxx_url)));
					} catch (ClassCastException e) {
						gsgsxx_xzcfxx_page = WebClient.getCustomHtmlPage(gsgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}				
					resultHtmlMap.put("gsgsxx_xzcfxx", gsgsxx_xzcfxx_page.asXml());		
					
				// 请求获取 工商公示信息->行政处罚信息->行政处罚信息详情
					@SuppressWarnings("unchecked")
				   List<HtmlAnchor> xingzhengchufa_anchors = (List<HtmlAnchor>) gsgsxx_xzcfxx_page.getByXPath("//div[@id='gsgsxx_xzcf']/table/tbody/tr/td/a");
						List<Map<String, Object>> gsgsxx_xzcfxx_xqs = new ArrayList<Map<String, Object>>();
						if (null != xingzhengchufa_anchors
								&& xingzhengchufa_anchors.size() > 0) {
							for (HtmlAnchor xingzhengchufa_anchor : xingzhengchufa_anchors) {
								Map<String, Object> gsgsxx_xzcfxx_xq = new LinkedHashMap<String, Object>();
//								HtmlPage gsgsxx_xzcfxx_xq_page = xingzhengchufa_anchor
//										.click();
//								gsgsxx_xzcfxx_xq.put(
//										"gsgsxx_xzcfxx_xq",gsgsxx_xzcfxx_xq_page.asXml());
//								gsgsxx_xzcfxx_xqs.add(gsgsxx_xzcfxx_xq);
								
								HtmlPage gsgsxx_xzcfxx_xq_page=null;
								try {
									gsgsxx_xzcfxx_xq_page = xingzhengchufa_anchor.click();
								} catch (Exception e) {
									gsgsxx_xzcfxx_xq_page= WebClient.getCustomHtmlPage(gsgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
								}								
								gsgsxx_xzcfxx_xq.put("gsgsxx_xzcfxx_xq",gsgsxx_xzcfxx_xq_page.asXml());
								gsgsxx_xzcfxx_xqs.add(gsgsxx_xzcfxx_xq);
							}
						}
						resultHtmlMap.put("gsgsxx_xzcfxx_xqs",gsgsxx_xzcfxx_xqs);

				// 请求获取 工商公示信息->经营异常信息->经营异常信息
				String gsgsxx_jyycxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=jyycInfo&maent.pripid=" +pripid+"&czmk=czmk6&random="+new Date().getTime() ;
//				HtmlPage gsgsxx_jyycxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_jyycxx_url)));
//				resultHtmlMap.put("gsgsxx_jyycxx", gsgsxx_jyycxx_page.asXml());
				HtmlPage gsgsxx_jyycxx_page=null;					
				try {						
					firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					gsgsxx_jyycxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_jyycxx_url)));
				} catch (ClassCastException e) {
					gsgsxx_jyycxx_page = WebClient.getCustomHtmlPage(gsgsxx_jyycxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				} finally {
					firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				}					
				resultHtmlMap.put("gsgsxx_jyycxx", gsgsxx_jyycxx_page.asXml());	

				// 请求获取 工商公示信息->严重违法信息->严重违法信息
				
				String gsgsxx_yzwfxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=yzwfInfo&maent.pripid=" +pripid+"&czmk=czmk4&random="+new Date().getTime() ;
//				HtmlPage gsgsxx_yzwfxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_yzwfxx_url)));
//				resultHtmlMap.put("gsgsxx_yzwfxx", gsgsxx_yzwfxx_page.asXml());
				HtmlPage gsgsxx_yzwfxx_page=null;					
				try {						
					firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					gsgsxx_yzwfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_yzwfxx_url)));
				} catch (ClassCastException e) {
					gsgsxx_yzwfxx_page = WebClient.getCustomHtmlPage(gsgsxx_yzwfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				} finally {
					firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				}							
				resultHtmlMap.put("gsgsxx_yzwfxx", gsgsxx_yzwfxx_page.asXml());

				// 请求获取 工商公示信息->抽查检查信息->抽查检查信息
				String gsgsxx_ccjcxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=ccjcInfo&maent.pripid=" +pripid+"&czmk=czmk7&random="+new Date().getTime() ;
//				HtmlPage gsgsxx_ccjcxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_ccjcxx_url)));
//				resultHtmlMap.put("gsgsxx_ccjcxx", gsgsxx_ccjcxx_page.asXml());
				HtmlPage gsgsxx_ccjcxx_page=null;					
				try {						
					firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					gsgsxx_ccjcxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_ccjcxx_url)));
				} catch (ClassCastException e) {
					gsgsxx_ccjcxx_page = WebClient.getCustomHtmlPage(gsgsxx_ccjcxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				} finally {
					firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				}							
				
				resultHtmlMap.put("gsgsxx_ccjcxx", gsgsxx_ccjcxx_page.asXml());
				
				
					//二 企业公示
					// 请求获取 企业公示信息->企业年报->列表			
				String qygsxx_qynb_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qygsInfo&maent.pripid=" +pripid+"&czmk=czmk8&random="+new Date().getTime() ;				
//				HtmlPage qygsxx_qynb_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_qynb_url)));
//				resultHtmlMap.put("qygsxx_qynb", qygsxx_qynb_page.asXml());
				HtmlPage qygsxx_qynb_page=null;					
				try {						
					firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					qygsxx_qynb_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_qynb_url)));
				} catch (ClassCastException e) {
					qygsxx_qynb_page = WebClient.getCustomHtmlPage(qygsxx_qynb_url, firstInfoPage.getWebClient().getCurrentWindow());
				} finally {
					firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				}					
				resultHtmlMap.put("qygsxx_qynb", qygsxx_qynb_page.asXml());
							
				// 请求获取 企业公示信息->企业年报->详情
				@SuppressWarnings("unchecked")
				List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_page.getByXPath("//div[@id='sifapanding']/table/tbody/tr/td/a");
				List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
				if (null != qygsxx_qynb_list_as
						&& !qygsxx_qynb_list_as.isEmpty()) {
					for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
//						Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
//						String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a.getTextContent();
//						String qygsxx_qynb_list_pubdate = ((HtmlElement) qygsxx_qynb_list_a.getParentNode().getParentNode().getFirstByXPath("//td[3]")).getTextContent();
//						qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",qygsxx_qynb_list_a_text);
//						qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",qygsxx_qynb_list_pubdate);
//						HtmlPage qygsxx_qynb_info_page = (HtmlPage) qygsxx_qynb_list_a.click();
//						qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",
//								qygsxx_qynb_info_page.asXml());
//						qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
						
						Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
						String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a.getTextContent();
						String qygsxx_qynb_list_pubdate = ((HtmlElement) qygsxx_qynb_list_a.getParentNode().getParentNode().getFirstByXPath("//td[3]")).getTextContent();
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",qygsxx_qynb_list_a_text);
						qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",qygsxx_qynb_list_pubdate);
						HtmlPage qygsxx_qynb_info_page=null;
						try {
							qygsxx_qynb_info_page =  qygsxx_qynb_list_a.click();
						} catch (Exception e) {
							qygsxx_qynb_info_page = WebClient.getCustomHtmlPage(qygsxx_qynb_url, firstInfoPage.getWebClient().getCurrentWindow());
						}							
						qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",qygsxx_qynb_info_page.asXml());
						qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
					}
				}
				resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

					// 请求获取 企业公示信息->股东及出资信息					
					String qygsxx_gdjczxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qygsForTzrxxInfo&maent.pripid=" +pripid+"&czmk=czmk12&random="+new Date().getTime() ;
//					HtmlPage qygsxx_gdjczxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_gdjczxx_url)));
//					resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx_page.asXml());
					HtmlPage qygsxx_gdjczxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_gdjczxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_gdjczxx_url)));
					} catch (ClassCastException e) {
						qygsxx_gdjczxx_page = WebClient.getCustomHtmlPage(qygsxx_gdjczxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}							
					resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx_page.asXml());
					

					// 请求获取 企业公示信息->股权变更信息
				    String qygsxx_gqbgxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qygsForTzrbgxxInfo&maent.pripid=" +pripid+"&czmk=czmk15&random="+new Date().getTime() ;
//					HtmlPage qygsxx_gqbgxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_gqbgxx_url)));
//					resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx_page.asXml());
				    HtmlPage qygsxx_gqbgxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_gqbgxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_gqbgxx_url)));
					} catch (ClassCastException e) {
						qygsxx_gqbgxx_page = WebClient.getCustomHtmlPage(qygsxx_gqbgxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}								    
					resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx_page.asXml());
				
					// 请求获取 企业公示信息->行政许可信息	
					String qygsxx_xzxkxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qygsForXzxkInfo&maent.pripid=" +pripid+"&czmk=czmk10&random="+new Date().getTime() ;
//					HtmlPage qygsxx_xzxkxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_xzxkxx_url)));
//					resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx_page.asXml());
					HtmlPage qygsxx_xzxkxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_xzxkxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_xzxkxx_url)));
					} catch (ClassCastException e) {
						qygsxx_xzxkxx_page = WebClient.getCustomHtmlPage(qygsxx_xzxkxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}	
					resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx_page.asXml());
					
					// 请求获取 企业公示信息->行政许可信息->行政许信息详情
					@SuppressWarnings("unchecked")
				    List<HtmlAnchor> xingzhengxuke_anchors = (List<HtmlAnchor>) qygsxx_xzxkxx_page.getByXPath("//div[@id='xzxk']/table/tbody/tr/td/a");
						List<Map<String, Object>> gsgsxx_xzxuke_xqs = new ArrayList<Map<String, Object>>();
						if (null != xingzhengxuke_anchors&& xingzhengxuke_anchors.size() > 0) {
							for (HtmlAnchor xingzhengxuke_anchor : xingzhengxuke_anchors) {
								Map<String, Object> gsgsxx_xzxuke_xq = new LinkedHashMap<String, Object>();
								HtmlPage gsgsxx_xzcfxx_xq_page=null;
								try {
									gsgsxx_xzcfxx_xq_page = xingzhengxuke_anchor.click();
								} catch (Exception e) {
									gsgsxx_xzcfxx_xq_page= WebClient.getCustomHtmlPage(qygsxx_xzxkxx_url, firstInfoPage.getWebClient().getCurrentWindow());
								}		
								gsgsxx_xzxuke_xq.put("qygsxx_xzxkxx_xq",gsgsxx_xzcfxx_xq_page.asXml());
								gsgsxx_xzxuke_xqs.add(gsgsxx_xzxuke_xq);
							}
						}
						resultHtmlMap.put("qygsxx_xzxkxx_xqs",gsgsxx_xzxuke_xqs);	
					

					// 请求获取 企业公示信息->知识产权出质登记信息				
					String qygsxx_zscqczdjxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qygsForZzcqInfo&maent.pripid=" +pripid+"&czmk=czmk11&random="+new Date().getTime() ;
//					HtmlPage qygsxx_zscqczdjxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_zscqczdjxx_url)));
//					resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx_page.asXml());
					HtmlPage qygsxx_zscqczdjxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_zscqczdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_zscqczdjxx_url)));
					} catch (ClassCastException e) {
						qygsxx_zscqczdjxx_page = WebClient.getCustomHtmlPage(qygsxx_zscqczdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}						
					resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx_page.asXml());
					

					// 请求获取 企业公示信息->行政处罚信息
					String qygsxx_xzcfxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qygsForXzcfInfo&maent.pripid=" +pripid+"&czmk=czmk13&random="+new Date().getTime() ;
//					HtmlPage qygsxx_xzcfxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_xzcfxx_url)));
//					resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx_page.asXml());
					HtmlPage qygsxx_xzcfxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_xzcfxx_url)));
					} catch (ClassCastException e) {
						qygsxx_xzcfxx_page = WebClient.getCustomHtmlPage(qygsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}						
					resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx_page.asXml());
					
					
                //三 其他部门信息
					// 请求获取 其他部门公示信息->行政许可信息
					String qtbmgsxx_xzxkxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qtgsInfo&maent.pripid=" +pripid+"&czmk=czmk9&random="+new Date().getTime() ;
//					HtmlPage qtbmgsxx_xzxkxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qtbmgsxx_xzxkxx_url)));
//					resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx_page.asXml());
					HtmlPage qtbmgsxx_xzxkxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qtbmgsxx_xzxkxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qtbmgsxx_xzxkxx_url)));
					} catch (ClassCastException e) {
						qtbmgsxx_xzxkxx_page = WebClient.getCustomHtmlPage(qtbmgsxx_xzxkxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}								
					resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx_page.asXml());
			

					// 请求获取 其他部门公示信息->行政处罚信息
					String qtbmgsxx_xzcfxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=qtgsForCfInfo&maent.pripid=" +pripid+"&czmk=czmk16&random="+new Date().getTime() ;
//					HtmlPage qtbmgsxx_xzcfxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qtbmgsxx_xzcfxx_url)));			
//					resultHtmlMap.put("qtbmgsxx_xzcfxx", qtbmgsxx_xzcfxx_page.asXml());
					HtmlPage qtbmgsxx_xzcfxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qtbmgsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qtbmgsxx_xzcfxx_url)));
					} catch (ClassCastException e) {
						qtbmgsxx_xzcfxx_page = WebClient.getCustomHtmlPage(qtbmgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}										
					resultHtmlMap.put("qtbmgsxx_xzcfxx", qtbmgsxx_xzcfxx_page.asXml());
					 //四 司法协助公示信息
					// 请求获取 司法协助公示信息->股权冻结信息		
					String sfxzgsxx_gqdjxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=sfgsInfo&maent.pripid=" +pripid+"&czmk=czmk17&random="+new Date().getTime() ;
//					HtmlPage sfxzgsxx_gqdjxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(sfxzgsxx_gqdjxx_url)));			
//					resultHtmlMap.put("sfxzgsxx_gqdjxx", sfxzgsxx_gqdjxx_page.asXml());
					HtmlPage sfxzgsxx_gqdjxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						sfxzgsxx_gqdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(sfxzgsxx_gqdjxx_url)));
					} catch (ClassCastException e) {
						sfxzgsxx_gqdjxx_page = WebClient.getCustomHtmlPage(sfxzgsxx_gqdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}								
					resultHtmlMap.put("sfxzgsxx_gqdjxx", sfxzgsxx_gqdjxx_page.asXml());

					// 请求获取 司法协助公示信息->股东变更信息
					String sfxzgsxx_gqbgxx_url = "http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=sfgsbgInfo&maent.pripid=" +pripid+"&czmk=czmk18&random="+new Date().getTime() ;
//					HtmlPage sfxzgsxx_gqbgxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(sfxzgsxx_gqbgxx_url)));			
//					resultHtmlMap.put("sfxzgsxx_gqbgxx", sfxzgsxx_gqbgxx_page.asXml());
					HtmlPage sfxzgsxx_gqbgxx_page=null;					
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						sfxzgsxx_gqbgxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(sfxzgsxx_gqbgxx_url)));
					} catch (ClassCastException e) {
						sfxzgsxx_gqbgxx_page = WebClient.getCustomHtmlPage(sfxzgsxx_gqbgxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}																				
					resultHtmlMap.put("sfxzgsxx_gqbgxx", sfxzgsxx_gqbgxx_page.asXml());
					
					break;
				}
		
			}
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
		}
		return resultHtmlMap;
	}
	
	// 福建数据
		private Map<String, Object> getHtmlInfoMapOfFujian(String area,
				HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {

		LOGGER.info("=========" + area + "=========" + keyword + "=========");
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
		
		if (null == firstInfoPage) {

			resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);

		} else {

			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='search']/div[@class='list-info']/div[@class='list-item']/div[@class='link']/a");
			@SuppressWarnings("unchecked")
			List<HtmlElement> div_list = (List<HtmlElement>) firstInfoPage.getByXPath("//div[@class='page-container clearfix']");
			if (null == div_list || div_list.isEmpty()) {
				resultHtmlMap.put("statusCodeDef",
						StatusCodeDef.IMAGECODE_ERROR);
			} else {
				if (null == anchors || anchors.isEmpty()) {
					boolean flag1 = false;
					for (HtmlElement htmlElement : div_list) {
						if (htmlElement.asXml().contains("您搜索的条件无查询结果")) {
							flag1 = true;
							break;
						}
					}
					if (flag1) {
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.NO_DATA_FOUND);
					} else {
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.IMAGECODE_ERROR);
					}
				}
			}
			HtmlAnchor htmlAnchor = null;
			boolean flag = false;
		
		//@SuppressWarnings("unchecked")
		//List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='search']/div[@class='list-info']/div[@class='list-item']/div[@class='link']/a");

		if (null != anchors && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();
				if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目
					matchFlag = true;
					// 保存列表页目标条目信息
					HtmlElement target_item_info = (HtmlElement) anchor
							.getParentNode().getParentNode();
					resultHtmlMap.put("target_item_info",
							target_item_info.asXml());

					String hebei_url = anchor.getHrefAttribute().toString();

					// 点击列表页目标条目，获取工商公示信息
					/* HtmlPage gsgsxx = anchor.click(); */
					HtmlPage gsgsxx = firstInfoPage.getWebClient().getPage(
							hebei_url);
					resultHtmlMap.put("gsgsxx", gsgsxx.asXml());
					// 点击列表页目标条目，获取工商公示信息（登记信息->股东信息->详情）
					@SuppressWarnings("unchecked")
					List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx
							.getByXPath("//table[@id='investorTable']/tbody/tr/td/a");
					List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
					if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
						for (HtmlAnchor touziren_anchor : touziren_anchors) {
							Map<String, Object> gsgsxx_djxx_tzrxx_xq = new LinkedHashMap<String, Object>();
							HtmlPage gsgsxx_djxx_tzrxx_xq_page = touziren_anchor
									.click();
							gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",
									gsgsxx_djxx_tzrxx_xq_page.asXml());
							gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
						}
					}
					resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",
							gsgsxx_djxx_tzrxx_xqs);

					/*
					 * // 请求获取 工商公示信息（行政处罚信息->行政处罚信息详情）
					 * 
					 * @SuppressWarnings("unchecked") List<HtmlAnchor>
					 * xingzhengchufa_anchors = (List<HtmlAnchor>)
					 * gsgsxx_xzcfxx_xzcfxx_page
					 * .getByXPath("//div[@id='xingzhengchufa']/table/tbody/tr/td/a"
					 * ); List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs =
					 * new ArrayList<Map<String, Object>>(); if (null !=
					 * xingzhengchufa_anchors && xingzhengchufa_anchors.size() >
					 * 0) { for (HtmlAnchor xingzhengchufa_anchor :
					 * xingzhengchufa_anchors) { Map<String, Object>
					 * gsgsxx_xzcfxx_xzcfxx_xq = new LinkedHashMap<String,
					 * Object>(); HtmlPage gsgsxx_xzcfxx_xzcfxx_xq_page =
					 * xingzhengchufa_anchor .click();
					 * gsgsxx_xzcfxx_xzcfxx_xq.put( "gsgsxx_xzcfxx_xzcfxx_xq",
					 * gsgsxx_xzcfxx_xzcfxx_xq_page.asXml());
					 * gsgsxx_xzcfxx_xzcfxx_xqs .add(gsgsxx_xzcfxx_xzcfxx_xq); }
					 * } resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx_xqs",
					 * gsgsxx_xzcfxx_xzcfxx_xqs);
					 */

					// 请求获取 企业公示信息
					HtmlPage qygsxx_list_page = firstInfoPage.getWebClient()
							.getPage(hebei_url.replace("&tab=01", "&tab=02"));
					resultHtmlMap.put("qygsxx_list", qygsxx_list_page.asXml());

					// 请求获取 企业公示信息->企业年报->详情
					@SuppressWarnings("unchecked")
					List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_list_page.getByXPath("//div/div[@rel='layout-02_01']/table/tbody/tr/td/a");
					/*
					 * List<HtmlElement> qygsxx_qynb_list_as =
					 * (List<HtmlElement>) qygsxx_list_page .getByXPath(
					 * "//div[4]/div/div/div[2]/div[3]/div[1]/table/tbody/tr/td/a"
					 * );
					 */
					List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
					if (null != qygsxx_qynb_list_as
							&& !qygsxx_qynb_list_as.isEmpty()) {
						for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
							Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
							String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a.getTextContent().toString().trim();
							 //String qygsxx_qynb_list_pubdate = String.valueOf(((HtmlElement) qygsxx_qynb_list_a.getParentNode().getParentNode().getFirstByXPath("//td[3]")).getTextContent());
							String qygsxx_qynb_list_pubdate = qygsxx_qynb_list_a.getParentNode().getParentNode().getTextContent();
							String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
							Pattern pattern = Pattern.compile(eL);
							Matcher matcher = pattern.matcher(qygsxx_qynb_list_pubdate);
						    String dateStr = "";
						    if(matcher.find()){
						    	dateStr = matcher.group(0);
						    }
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",qygsxx_qynb_list_a_text);
							//qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",qygsxx_qynb_list_pubdate);
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate", dateStr);
							HtmlPage qygsxx_qynb_info_page = (HtmlPage) qygsxx_qynb_list_a.click();
							qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",qygsxx_qynb_info_page.asXml());
							qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
						}
					}
					resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

					// 请求获取 其他部门公示信息
					HtmlPage qtbmgsxx_page = firstInfoPage.getWebClient()
							.getPage(hebei_url.replace("&tab=01", "&tab=03"));
					String qtbmgsxx = null;
					if (null != qtbmgsxx_page) {
						qtbmgsxx = qtbmgsxx_page.asXml();
					}
					resultHtmlMap.put("qtbmgsxx", qtbmgsxx);

					// 请求获取 司法协助公示信息
					HtmlPage sfxzgsxx_list_page = firstInfoPage.getWebClient()
							.getPage(hebei_url.replace("&tab=01", "&tab=06"));
					String sfxzgsxx_list = null;
					if (null != sfxzgsxx_list_page) {
						sfxzgsxx_list = sfxzgsxx_list_page.asXml();
					}
					resultHtmlMap.put("sfxzgsxx_list", sfxzgsxx_list);
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);					
					break;
				}
			}
			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}
		}
		
		}
		return resultHtmlMap;

	}
		
		//湖南数据
		private Map<String, Object> getHtmlInfoMapOfHunan(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
			Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
			//校验验证码和是否有列表数据
//			System.out.println(firstInfoPage.asXml());
			WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> divByXPath = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list-item']");
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-stat']"));
			if (divByXPath.size() == 0 && firstByXPath==null) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			} else {
				if(firstByXPath!=null){
					String textContent = firstByXPath.getTextContent();
					if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					} else {//只有N条数据的时候，有list-a
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
					}
				}else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
				}
			}
			@SuppressWarnings("unchecked")
			List<HtmlElement> anchors = (List<HtmlElement>) firstInfoPage.getByXPath("//div[@class='main']/div[@class='search']/div[@class='list-info']/div[@class='list-item']/div[@class='link']/a");
			LOGGER.info(anchors.toString());
			if (anchors!=null && !anchors.isEmpty()) {
				boolean matchFlag = false;
				for (HtmlElement anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
						matchFlag = true;
						//保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
//						System.out.println(target_item_info.asXml());
						resultHtmlMap.put("target_item_info", target_item_info.asXml());
						//公共参数
						String urlString=anchor.getAttribute("href").split("tab=")[0];
						//*****************工商公示信息   开始 *****************
						HtmlPage gsgsxx_djxx = anchor.click();
//						System.out.println(gsgsxx_djxx.asXml());
					/*	HtmlPage test1=firstInfoPage.getWebClient().getPage(anchor.getAttribute("href"));
						System.out.println(test1.asXml());*/
						@SuppressWarnings("unchecked")
						List<HtmlElement> tabs = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-l']/ul/li");//获取主页面左侧按钮

						@SuppressWarnings("unchecked")
						List<HtmlElement> djxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_01']");
						//##############登记信息   开始 ##############
						if (djxxElements!=null && !djxxElements.isEmpty()) {
						
						//********基本信息 开始
						resultHtmlMap.put("gsgsxx_jbxx", djxxElements.get(0).asXml());
						//********基本信息 结束
						if(djxxElements.size()==3){
							//********股东信息 开始
							resultHtmlMap.put("gsgsxx_gdxx", djxxElements.get(1).asXml());
							
							//股东详情
							@SuppressWarnings("unchecked")
							List<HtmlAnchor> elements = (List<HtmlAnchor>)djxxElements.get(1).getByXPath("//table[@id='investorTable']/tbody/tr/td/a");;
							if (elements!=null && !elements.isEmpty()) {
								List<String> gdxxdetail=new ArrayList<String>();
								for(HtmlAnchor htmlAnchor:elements){
									HtmlPage detail = htmlAnchor.click();
//									System.out.println(detail.asXml());
									gdxxdetail.add(detail.asXml());
								}
								resultHtmlMap.put("gsgsxx_gdxx_detail",gdxxdetail);
							}
							
							//********股东信息 结束
							//********变更信息 开始
							resultHtmlMap.put("gsgsxx_bgxx", djxxElements.get(2).asXml());
							//********变更信息 结束
						}else if(djxxElements.size()==2){
							//********变更信息 开始
							resultHtmlMap.put("gsgsxx_bgxx", djxxElements.get(1).asXml());
							//********变更信息 结束
						}
						
						
						}
						//##############登记信息   结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> baxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_02']");
						//##############备案信息   开始 ##############
						if (baxxElements!=null && !baxxElements.isEmpty() && baxxElements.size()==3) {
						
						//********主要人员信息 开始
						resultHtmlMap.put("gsgsxx_zyryxx", baxxElements.get(0).asXml());
						//********主要人员信息 结束
						//********分支机构信息 开始
						resultHtmlMap.put("gsgsxx_fzjgxx", baxxElements.get(1).asXml());
						//********分支机构信息 结束
						//********清算信息 开始
						resultHtmlMap.put("gsgsxx_qsxx", baxxElements.get(2).asXml());
						//********清算信息 结束
						
						}
						//##############备案信息   结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> gqczdjxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_03']");
						//##############股权出资登记信息   开始 ##############
						if (gqczdjxxElements!=null && !gqczdjxxElements.isEmpty()) {
						
						//********股权出资登记信息 开始
						resultHtmlMap.put("gsgsxx_gqczdjxx", gqczdjxxElements.get(0).asXml());
						//********股权出资登记信息 结束
						
						}
						//##############股权出资登记信息   结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> dcdydjxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_04']");
						//##############动产抵押登记信息   开始 ##############
						if (dcdydjxxElements!=null && !dcdydjxxElements.isEmpty()) {
						
						//********动产抵押登记信息 开始
						resultHtmlMap.put("gsgsxx_dcdydjxx", dcdydjxxElements.get(0).asXml());
						//********动产抵押登记信息 结束
						
						}
						//##############动产抵押登记信息   结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> jyycxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_05']");
						//##############经营异常信息  开始 ##############
						if (jyycxxElements!=null && !jyycxxElements.isEmpty()) {
						
						//********经营异常信息开始
						resultHtmlMap.put("gsgsxx_jyycxx", jyycxxElements.get(0).asXml());
						//********经营异常信息结束
						
						}
						//##############经营异常信息  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> yzwfxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_06']");
						//##############严重违法信息  开始 ##############
						if (yzwfxxElements!=null && !yzwfxxElements.isEmpty()) {
						
						//********严重违法信息开始
						resultHtmlMap.put("gsgsxx_yzwfxx", yzwfxxElements.get(0).asXml());
						//********严重违法信息结束
						
						}
						//##############严重违法信息  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzcfxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_07']");
						//##############行政处罚信息  开始 ##############
						if (xzcfxxElements!=null && !xzcfxxElements.isEmpty()) {
						
						//********行政处罚信息开始
						resultHtmlMap.put("gsgsxx_xzcfxx", xzcfxxElements.get(0).asXml());
						//********行政处罚信息结束
						
						}
						//##############行政处罚信息  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> ccjcxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_08']");
						//##############抽查检查信息  开始 ##############
						if (ccjcxxElements!=null && !ccjcxxElements.isEmpty()) {
						
						//********抽查检查信息开始
						resultHtmlMap.put("gsgsxx_ccjcxx", ccjcxxElements.get(0).asXml());
						//********抽查检查信息结束
						
						}
						//##############抽查检查信息  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> xsfcxxElements = (List<HtmlElement>)gsgsxx_djxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-01_09']");
						//##############享受扶持信息  开始 ##############
						if (xsfcxxElements!=null && !xsfcxxElements.isEmpty()) {
						
						//********享受扶持信息开始
						resultHtmlMap.put("gsgsxx_xsfcxx", xsfcxxElements.get(0).asXml());
						//********享受扶持信息结束
						
						}
						//##############享受扶持信息  结束 ##############
						
						//*****************工商公示信息   结束*****************
						
						
						//*****************企业公示信息   开始 *****************
						HtmlPage qygsxx_qynb =firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(urlString+"tab=02")));
//						System.out.println(gtgshgs_gtgshnb.asXml());
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> qynbElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_01']");
						//##############企业年报  开始 ##############
						if (qynbElements!=null && !qynbElements.isEmpty()) {
						
						//********企业年报开始
						resultHtmlMap.put("qygsxx_qynb", qynbElements.get(0).asXml());
						//********企业年报结束
						
						//年报详情
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> htmlEles = (List<HtmlAnchor>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_01']/table/tbody/tr/td/a");
						if (htmlEles!=null && !htmlEles.isEmpty()) {
							List<String> gdxxdetail=new ArrayList<String>();
							for (@SuppressWarnings("unused") HtmlAnchor htmlEle : htmlEles) {
								HtmlPage gtgshgs_gtgshnb_detail = htmlEle.click();
								gdxxdetail.add(gtgshgs_gtgshnb_detail.asXml());
								
							}
							resultHtmlMap.put("qygsxx_qynb_detail",gdxxdetail);
						}
						
						}
						//##############企业年报  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> gdjczxxElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_04']");
						//##############股东及出资信息  开始 ##############
						if (gdjczxxElements!=null && !gdjczxxElements.isEmpty()) {
						
						//********股东及出资信息开始
						resultHtmlMap.put("qygsxx_gdjczxx", gdjczxxElements.get(0).asXml());
						//********股东及出资信息结束

						//********变更信息开始
						resultHtmlMap.put("qygsxx_bgxx", gdjczxxElements.get(1).asXml());
						//********变更信息结束
						
						}
						//##############股东及出资信息  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> gqbgxxElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_06']");
						//##############股权变更  开始 ##############
						if (gqbgxxElements!=null && !gqbgxxElements.isEmpty()) {
						
						//********股权变更开始
						resultHtmlMap.put("qygsxx_gqbgxx", gqbgxxElements.get(0).asXml());
						//********股权变更结束
						
						}
						//##############股权变更  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzxkxxElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_02']");
						//##############行政许可  开始 ##############
						if (xzxkxxElements!=null && !xzxkxxElements.isEmpty()) {
						
						//********行政许可开始
						resultHtmlMap.put("qygsxx_xzxkxx", xzxkxxElements.get(0).asXml());
						//********行政许可结束
						
						}
						//##############行政许可  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> zscqczdjElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_03']");
						//##############知识产权出资登记信息  开始 ##############
						if (zscqczdjElements!=null && !zscqczdjElements.isEmpty()) {
						
						//********知识产权出资登记信息开始
						resultHtmlMap.put("qygsxx_zscqczdj", zscqczdjElements.get(0).asXml());
						//********知识产权出资登记信息结束
						
						}
						//##############知识产权出资登记信息  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> xzcfElements = (List<HtmlElement>)qygsxx_qynb.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-02_05']");
						//##############行政处罚信息  开始 ##############
						if (xzcfElements!=null && !xzcfElements.isEmpty()) {
						
						//********行政处罚信息开始
						resultHtmlMap.put("qygsxx_xzcf", xzcfElements.get(0).asXml());
						//********行政处罚信息结束
						
						}
						//##############行政处罚信息  结束 ##############
						
						//*****************企业公示信息   结束*****************
						
						
						//*****************其他部门公示信息   开始 *****************
						HtmlPage qtbmgsxx =firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(urlString+"tab=03")));
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> qtbmgs_xzxkElements = (List<HtmlElement>)qtbmgsxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-03_01']");
						//##############行政许可信息  开始 ##############
						if (qtbmgs_xzxkElements!=null && !qtbmgs_xzxkElements.isEmpty()) {
						
						//********行政许可信息开始
						resultHtmlMap.put("qtbmgsxx_xzxk", qtbmgs_xzxkElements.get(0).asXml());
						//********行政许可信息结束
						
						}
						//##############行政许可信息  结束 ##############
						
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> qtbmgs_xzcfElements = (List<HtmlElement>)qtbmgsxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-03_02']");
						//##############行政处罚信息  开始 ##############
						if (qtbmgs_xzcfElements!=null && !qtbmgs_xzcfElements.isEmpty()) {
						
						//********行政处罚信息开始
						resultHtmlMap.put("qtbmgsxx_xzcf", qtbmgs_xzcfElements.get(0).asXml());
						//********行政处罚信息结束
						
						}
						//##############行政处罚信息  结束 ##############
						//*****************其他部门公示信息   结束*****************
						
						
						
						//*****************司法协助公示信息   开始 *****************
						HtmlPage sfxzgsxx =firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(urlString+"tab=06")));
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> sfxzgsxx_gqdjElements = (List<HtmlElement>)sfxzgsxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-06_01']");
						//##############股权冻结  开始 ##############
						if (sfxzgsxx_gqdjElements!=null && !sfxzgsxx_gqdjElements.isEmpty()) {
						
						//********股权冻结开始
						resultHtmlMap.put("sfxzgsxx_gqdj", sfxzgsxx_gqdjElements.get(0).asXml());
						//********股权冻结结束
						
						}
						//##############股权冻结  结束 ##############
						
						@SuppressWarnings("unchecked")
						List<HtmlElement> sfxzgsxx_gqbgElements = (List<HtmlElement>)sfxzgsxx.getByXPath("//div[@class='cont-r']/div[@class='cont-r-b']/div[@rel='layout-06_02']");
						//##############股权变更  开始 ##############
						if (sfxzgsxx_gqbgElements!=null && !sfxzgsxx_gqbgElements.isEmpty()) {
						
						//********司法股东变革登记开始
						resultHtmlMap.put("sfxzgsxx_sfgdbgdj", sfxzgsxx_gqbgElements.get(0).asXml());
						//********司法股东变革登记结束
						
						}
						//##############股权变更  结束 ##############
						//*****************司法协助公示信息   结束*****************
						
						
				
						break;
					}
				}
				if (!matchFlag) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
				}
			}
			
			return resultHtmlMap;
		}
		
		//海南数据
				@SuppressWarnings("unchecked")
				private Map<String, Object> getHtmlInfoMapOfHainan(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {
					WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();
					Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();								
					HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list']"));
					HtmlElement divByXPath2 = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@class='list-a']"));
					if (divByXPath == null && divByXPath2 == null) {
						//DomElement checkcode = firstInfoPage.getElementById("checkNo");
						//String val = checkcode.getAttribute("value");
						if (firstInfoPage.asXml().contains("验证码不正确")) {
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
						} else {
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
						}
					} else {
						List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
						if (anchors.size() == 0) {
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
						} else {
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
						}
					}
					//
					
					List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@class='list']/ul/li/a");
					LOGGER.info(anchors.toString());
					if (anchors!=null && !anchors.isEmpty()) {
						boolean matchFlag = false;
						for (HtmlAnchor anchor : anchors) {
							String anchorTitle = anchor.getTextContent().toString().trim();
							
							if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
								matchFlag = true;
								//保存列表页目标条目信息
								HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
								resultHtmlMap.put("target_item_info", target_item_info.asXml());
								
								//获得公共参数id
								String idParam = "";
								String hrefAttr = anchor.getAttribute("href");
								if (!StringUtils.isEmpty(hrefAttr)) {
									int index = hrefAttr.indexOf("?");
									idParam = hrefAttr.substring(index+1);
								}
								
								//一、获取工商公示信息
								HtmlPage gsgsxx_page = anchor.click();
								resultHtmlMap.put("gsgsxx", gsgsxx_page.asXml());

								//工商公示信息-->股东信息-->详情
								List<HtmlAnchor> anchors_gdxx_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a");
								List<String> gdxx_list = new ArrayList<String>();
								for (HtmlAnchor htmlAnchor : anchors_gdxx_detail) {
									HtmlPage gdxx_detail = htmlAnchor.click();
									gdxx_list.add(gdxx_detail.asXml());
								}
								resultHtmlMap.put("gsgsxx_gdxx_detail", gdxx_list);
								
								//工商公示信息-->登记信息-->股东信息(以及详情信息)
								String mainIdParam = idParam.replace("id", "mainId");
								List<String> gdxxList = new ArrayList<String>();
								List<String> gdxxDetailList = new ArrayList<String>();
								int gdxxPageNo = 1;
								while (true) {
									//http://aic.hainan.gov.cn:1888/businessPublicity.jspx?id=46C738AE70B9CEF10433B8BCA8443AAD
									String gsgsxx_djxx_gdxx_url = "http://aic.hainan.gov.cn:1888/QueryInvList.jspx?pno="+gdxxPageNo+"&"+mainIdParam;
									String gsgsxx_djxx_gdxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_djxx_gdxx_url).getWebResponse().getContentAsString();
									Document gsgsxxGdxxDoc = Jsoup.parse(gsgsxx_djxx_gdxx_page);
									if (gsgsxxGdxxDoc != null) {
										Elements aElements = gsgsxxGdxxDoc.select("a");
										for (Element aElement : aElements) {
											String attr = aElement.attr("onclick");
											if (attr != null && !"".equals(attr)) {
												String[] split = attr.split("'");
												if (split.length > 2) {
													String detailUrl = "http://aic.hainan.gov.cn:1888" + split[1];
													HtmlPage gsgsxx_gdxx_detail_page = null;
													try {
														gsgsxx_gdxx_detail_page = firstInfoPage.getWebClient().getPage(detailUrl);
													} catch (Exception e) {
														gsgsxx_gdxx_detail_page = WebClient.getCustomHtmlPage(detailUrl, firstInfoPage.getWebClient().getCurrentWindow());
													}
													gdxxDetailList.add(gsgsxx_gdxx_detail_page.asXml());
												}
											}
										}
									}
									
									if (!gdxxList.contains(gsgsxx_djxx_gdxx_page)) {
//										gdxxList.add(gsgsxx_djxx_gdxx_page);
//										gdxxPageNo++;
										if(gdxxList.size()>=1){
											String march1=gdxxList.get(0);
											String matchString = march1.split("width=20%")[1].split("width=23%")[0];										
											if(gsgsxx_djxx_gdxx_page.contains(matchString)){
												break;
											}
										}else{
											gdxxList.add(gsgsxx_djxx_gdxx_page);
											gdxxPageNo++;
										}
									} else {
										break;
									}
									
								}
								resultHtmlMap.put("gsgsxx_gdxx_page", gdxxList);
								resultHtmlMap.put("gsgsxx_gdxx_detail", gdxxDetailList);
								
								//工商公示信息-->登记信息-->变更信息
								List<String> bgxxList = new ArrayList<String>();
								int bgxxPageNo = 1;
								while (true) {
									String gsgsxx_bgxx_url = "http://aic.hainan.gov.cn:1888/QueryAltList.jspx?pno="+bgxxPageNo+"&"+mainIdParam;
									String gsgsxx_bgxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_bgxx_url).getWebResponse().getContentAsString();
									if (!bgxxList.contains(gsgsxx_bgxx_page)) {
										bgxxList.add(gsgsxx_bgxx_page);
										bgxxPageNo++;
									} else {
										break;
									}
									
								}
								resultHtmlMap.put("gsgsxx_bgxx_page", bgxxList);
								
								//工商公示信息-->备案信息-->主要人员信息
								List<String> zyryxxList = new ArrayList<String>();
								int zyryPageNo = 1;
								while (true) {
									DomElement pageNoDom = gsgsxx_page.getElementById("spanmem"+zyryPageNo);
									if (pageNoDom != null) {
										String gsgsxx_baxx_zyryxx_url = "http://aic.hainan.gov.cn:1888/QueryMemList.jspx?pno="+zyryPageNo+"&"+mainIdParam;
										String gsgsxx_baxx_zyryxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_baxx_zyryxx_url).getWebResponse().getContentAsString();
										if (!zyryxxList.contains(gsgsxx_baxx_zyryxx_page)) {
											zyryxxList.add(gsgsxx_baxx_zyryxx_page);
											zyryPageNo++;
										} else {
											break;
										}
									}else {
										break;
									}
								}
								resultHtmlMap.put("gsgsxx_baxx_zyryxx_page", zyryxxList);
								
								//http://gsxt.fc12319.com/QueryMortList.jspx?pno=1&mainId=DB5C6077C0A57243E7C56BA05CD252C0
								//工商公示信息-->动产抵押登记信息-->详情
								List<HtmlAnchor> anchors_dcdyxx_detail = (List<HtmlAnchor>)gsgsxx_page.getByXPath("//div[@id='mortDiv']/table[@class='detailsList']/tbody/tr/td/a");
								List<String> gsgs_dcdyxx_list = new ArrayList<String>();
								for (HtmlAnchor htmlAnchor : anchors_dcdyxx_detail) {
									String attribute = htmlAnchor.getAttribute("onclick");
									String detailUrl = "http://aic.hainan.gov.cn:1888" + attribute.split("'")[1];
									HtmlPage dcdyxx_detail_page = null;
									try {
										dcdyxx_detail_page = firstInfoPage.getWebClient().getPage(detailUrl);
									} catch (Exception e) {
										dcdyxx_detail_page = WebClient.getCustomHtmlPage(detailUrl, firstInfoPage.getWebClient().getCurrentWindow());
									}
									gsgs_dcdyxx_list.add(dcdyxx_detail_page.asXml());
								}
								resultHtmlMap.put("gsgsxx_dcdydjxx_details", gsgs_dcdyxx_list);
								
								//工商公示信息-->经营异常信息
								String gsgsxx_jyycxx_url = "http://aic.hainan.gov.cn:1888/QueryExcList.jspx?pno=1&"+mainIdParam;
								String gsgsxx_jyycxx_page = firstInfoPage.getWebClient().getPage(gsgsxx_jyycxx_url).getWebResponse().getContentAsString();
								resultHtmlMap.put("gsgsxx_jyycxx_page", gsgsxx_jyycxx_page);								
								List<?> liElements = gsgsxx_page.getByXPath("//div[@id='leftTabs']/ul/li");
								//二、获取企业公示信息
//								HtmlElement qygsxx_tab = (HtmlElement)liElements.get(1);
//								HtmlPage qygsxx_page = (HtmlPage)qygsxx_tab.click();
//								resultHtmlMap.put("qygsxx", qygsxx_page.asXml());
								
								String qygsxx_url = "http://aic.hainan.gov.cn:1888/enterprisePublicity.jspx?"+idParam;
								HtmlPage qygsxx_page = firstInfoPage.getWebClient().getPage(qygsxx_url);
								resultHtmlMap.put("qygsxx", qygsxx_page.asXml());
								
								//企业公示信息-->企业年报-->详情
								List<HtmlAnchor> anchors_detail = (List<HtmlAnchor>)qygsxx_page.getByXPath("//div[@id='qiyenianbao']/table[@class='detailsList']/tbody/tr/td/a");
								List<String> nbxx_list = new ArrayList<String>();
								for (HtmlAnchor htmlAnchor : anchors_detail) {
									HtmlPage nb_detail = htmlAnchor.click();
									nbxx_list.add(nb_detail.asXml());
								}
								resultHtmlMap.put("qygsxx_qynb_detail", nbxx_list);
								
								//企业公示信息-->行政许可信息
								String qygsxx_xzxkxx_url = "http://aic.hainan.gov.cn:1888/QueryLicenseRegList.jspx?pno=1&"+mainIdParam;
								String gsgsxx_xzxkxx_page = firstInfoPage.getWebClient().getPage(qygsxx_xzxkxx_url).getWebResponse().getContentAsString();
								resultHtmlMap.put("gsgsxx_xzxkxx_page", gsgsxx_xzxkxx_page);
								
								//三、获取其他部门公示信息
//								HtmlElement qtbmgsxx_tab = (HtmlElement)liElements.get(2);
//								HtmlPage qtbmgsxx_page = (HtmlPage)qtbmgsxx_tab.click();
//								resultHtmlMap.put("qtbmgsxx", qtbmgsxx_page.asXml());
								
								String qtbmgsxx_url = "http://aic.hainan.gov.cn:1888/otherDepartment.jspx?"+mainIdParam;
								HtmlPage qtbmgsxx_page = firstInfoPage.getWebClient().getPage(qtbmgsxx_url);
								resultHtmlMap.put("qtbmgsxx", qtbmgsxx_page.asXml());
								
								//四、获取 司法协助公示信息
								if(liElements.size()>3) {
									//HtmlElement sfxzgsxx_tab = (HtmlElement)liElements.get(3);
									//HtmlPage sfxzgsxx_page = (HtmlPage)sfxzgsxx_tab.click();
									//resultHtmlMap.put("sfxzgsxx", sfxzgsxx_page.asXml());
									//http://aic.hainan.gov.cn:1888/
									String sfxzxx_url = "http://aic.hainan.gov.cn:1888/justiceAssistance.jspx?"+idParam;
									HtmlPage sfxzxx_page = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(sfxzxx_url)));
									resultHtmlMap.put("sfxzgsxx", sfxzxx_page.asXml());
									
								}
								
								break;
							}
					
						}
						
						if (!matchFlag) {
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
							LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
						}
					}
					
					return resultHtmlMap;
				}

//江西数据
@SuppressWarnings("unchecked")
private Map<String, Object> getHtmlInfoMapOfJiangxi(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {			
						//
	        Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();	
			WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();			
			HtmlElement divByXPath =  ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@id='div0']/a"));																					
			if (divByXPath == null) {
				DomElement checkcode = firstInfoPage.getElementById("yzm");
				String val = checkcode.getAttribute("value");
				if (!StringUtils.isEmpty(val)) {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
				} else {
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
				}
			} else {				
				HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@id='div0']"));
				String textContent = firstByXPath.getTextContent();
			    if(textContent.contains("无数据")){
					resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				}else{					
					 firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//div[@id='div0']/a"));
					if (firstByXPath!=null) {
						resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
					} 					
				}				
			}
			//		
			@SuppressWarnings("unchecked")
			List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//div[@id='div0']/a");
			LOGGER.info(anchors.toString());		
			if (anchors!=null && !anchors.isEmpty()) {
				for (HtmlAnchor anchor : anchors) {
					String anchorTitle = anchor.getTextContent().toString().trim();
					
					if (anchorTitle.contains(keyword)) { // 匹配到需要精确搜索的条目				
						String paramSring = anchor.getAttribute("href");
						String qylx="";
						String qyid="";
						String zch="";							
						if (paramSring !=null) {
							String[] strs = paramSring.split("=");  
							qylx=strs[1].toString();
							qyid=strs[2].toString();
							zch=strs[3].toString();
							qylx=qylx.substring(0, qylx.indexOf("&"));
							qyid=qyid.substring(0, qyid.indexOf("&"));
							zch=zch.substring(0, zch.indexOf("&"));
						}		
									// 保存列表页目标条目信息
						HtmlElement target_item_info = (HtmlElement) anchor.getParentNode().getParentNode();
						resultHtmlMap.put("target_item_info",target_item_info.asXml());
	                    //一工商公示
						// 点击列表页目标条目，获取 工商公示信息->登记信息->基本信息->投资人信息->变更信息
						//工商公示信息->登记信息->基本信息
						HtmlPage gsgsxx_page = anchor.click();													
						resultHtmlMap.put("gsgsxx_djxx_jbxx", gsgsxx_page.asXml());
						
						
						//获取 工商公示信息->登记信息->投资人信息->及详情信息
						Thread.sleep(2500);
						String gsgsxx_gdxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewDjxxGdxx.pt?qyid="+qyid;										
						HtmlPage gsgsxx_gdxx_page =null;
			        	try {						
							firstInfoPage.getWebClient().getOptions().setTimeout(120000);
							gsgsxx_gdxx_page = firstInfoPage.getWebClient().getPage(window,new WebRequest(new URL(gsgsxx_gdxx_url)));							
						} catch (ClassCastException e) {
							gsgsxx_gdxx_page = WebClient.getCustomHtmlPage(gsgsxx_gdxx_url, firstInfoPage.getWebClient().getCurrentWindow());
						} finally {
							firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
						}							
						resultHtmlMap.put("gsgsxx_djxx_tzrxx", gsgsxx_gdxx_page.asXml());
						
																			
						// 点击列表页目标条目，获取 工商公示信息->登记信息->投资人信息->详情
						@SuppressWarnings("unchecked")
						List<HtmlAnchor> touziren_anchors = (List<HtmlAnchor>) gsgsxx_gdxx_page.getByXPath("//table[@id='table3']/tbody[@id='table3']/tr/td/a");
						List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = new ArrayList<Map<String, Object>>();
						if (null != touziren_anchors && !touziren_anchors.isEmpty()) {
							for (HtmlAnchor touziren_anchor : touziren_anchors) {
								Map<String, Object> gsgsxx_djxx_tzrxx_xq = new HashMap<String, Object>();
								HtmlPage gsgsxx_djxx_tzrxx_xq_page=null;
								try {
									 gsgsxx_djxx_tzrxx_xq_page =  touziren_anchor.click();
								} catch (Exception e) {
									gsgsxx_djxx_tzrxx_xq_page=WebClient.getCustomHtmlPage(gsgsxx_gdxx_url, firstInfoPage.getWebClient().getCurrentWindow());
								}								
								gsgsxx_djxx_tzrxx_xq.put("gsgsxx_djxx_tzrxx_xq",gsgsxx_djxx_tzrxx_xq_page.asXml());
								gsgsxx_djxx_tzrxx_xqs.add(gsgsxx_djxx_tzrxx_xq);
							}
						}
						resultHtmlMap.put("gsgsxx_djxx_tzrxx_xqs",gsgsxx_djxx_tzrxx_xqs);
							
						 // 点击列表页目标条目，获取 工商公示信息->登记信息->变更信息	
						Thread.sleep(2500);
						String gsgsxx_bgxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewDjxxBgxx.pt?qyid="+qyid;						
					    HtmlPage gsgsxx_bgxx_page = null;
					   	try {						
					   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					   		gsgsxx_bgxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_bgxx_url)));
					   	} catch (ClassCastException e) {
					   		gsgsxx_bgxx_page = WebClient.getCustomHtmlPage(gsgsxx_bgxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					   	} finally {
					   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					   	}																												
						resultHtmlMap.put("gsgsxx_djxx_bgxx", gsgsxx_bgxx_page.asXml());
						
						 // 点击列表页目标条目，获取 工商公示信息->备案信息					
						String gsgsxx_baxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewBaxx.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&showgdxx=true";
						
						HtmlPage gsgsxx_baxx_page = null;
					   	try {						
					   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					   		gsgsxx_baxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_baxx_url)));
					   	} catch (ClassCastException e) {
					   		gsgsxx_baxx_page = WebClient.getCustomHtmlPage(gsgsxx_baxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					   	} finally {
					   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					   	}																								
						resultHtmlMap.put("gsgsxx_baxx", gsgsxx_baxx_page.asXml());	
						
						String gsgsxx_baxx_url2 = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewBaxx.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&balx=3";																							
						HtmlPage gsgsxx_baxx_page2 = null;
					   	try {						
					   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					   		gsgsxx_baxx_page2 =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_baxx_url2)));
					   	} catch (ClassCastException e) {
					   		gsgsxx_baxx_page2 = WebClient.getCustomHtmlPage(gsgsxx_baxx_url2, firstInfoPage.getWebClient().getCurrentWindow());
					   	} finally {
					   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					   	}							
						resultHtmlMap.put("gsgsxx_baxx2", gsgsxx_baxx_page2.asXml());	

						// 请求获取 工商公示信息->动产抵押登记信息->动产抵押登记信息		
						Thread.sleep(2500);
						String gsgsxx_dcdydjxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewDcdydjxx.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&num=0&showgdxx=true" ;														
						HtmlPage gsgsxx_dcdydjxx_page = null;
					   	try {						
					   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					   		gsgsxx_dcdydjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_dcdydjxx_url)));
					   	} catch (ClassCastException e) {
					   		gsgsxx_dcdydjxx_page = WebClient.getCustomHtmlPage(gsgsxx_dcdydjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					   	} finally {
					   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					   	}							
						resultHtmlMap.put("gsgsxx_dcdydjxx", gsgsxx_dcdydjxx_page.asXml());
						
						// 请求获取 工商公示信息->股权出质登记信息->股权出质登记信息					
						String gsgsxx_gqczdjxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewGqczdjxx.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&showgdxx=true";												
						HtmlPage gsgsxx_gqczdjxx_page = null;
					   	try {						
					   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					   		gsgsxx_gqczdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_gqczdjxx_url)));
					   	} catch (ClassCastException e) {
					   		gsgsxx_gqczdjxx_page = WebClient.getCustomHtmlPage(gsgsxx_gqczdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					   	} finally {
					   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					   	}	
						
						resultHtmlMap.put("gsgsxx_gqczdjxx", gsgsxx_gqczdjxx_page.asXml());
													     

						// 请求获取 工商公示信息->行政处罚信息->行政处罚信息
						Thread.sleep(2500);
						String gsgsxx_xzcfxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewXzcfxx.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&num=0&showgdxx=true" ;																	
						HtmlPage gsgsxx_xzcfxx_page = null;
					   	try {						
					   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
					   		gsgsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_xzcfxx_url)));
					   	} catch (ClassCastException e) {
					   		gsgsxx_xzcfxx_page = WebClient.getCustomHtmlPage(gsgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					   	} finally {
					   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					   	}							
						resultHtmlMap.put("gsgsxx_xzcfxx", gsgsxx_xzcfxx_page.asXml());					
						
					// 请求获取 工商公示信息->行政处罚信息->行政处罚信息详情
						@SuppressWarnings("unchecked")
					   List<HtmlAnchor> xingzhengchufa_anchors = (List<HtmlAnchor>) gsgsxx_xzcfxx_page.getByXPath("//div[@id='main']/table/tbody/tr/td/a");
							List<Map<String, Object>> gsgsxx_xzcfxx_xqs = new ArrayList<Map<String, Object>>();
							if (null != xingzhengchufa_anchors&& xingzhengchufa_anchors.size() > 0) {
								for (HtmlAnchor xingzhengchufa_anchor : xingzhengchufa_anchors) {
									Map<String, Object> gsgsxx_xzcfxx_xq = new LinkedHashMap<String, Object>();
									HtmlPage gsgsxx_xzcfxx_xq_page =null;
									try {
										gsgsxx_xzcfxx_xq_page = xingzhengchufa_anchor.click();
									} catch (Exception e) {
										gsgsxx_xzcfxx_xq_page= WebClient.getCustomHtmlPage(gsgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
									}									
									gsgsxx_xzcfxx_xq.put("gsgsxx_xzcfxx_xq",gsgsxx_xzcfxx_xq_page.asXml());
									gsgsxx_xzcfxx_xqs.add(gsgsxx_xzcfxx_xq);
								}
							}
							resultHtmlMap.put("gsgsxx_xzcfxx_xqs",gsgsxx_xzcfxx_xqs);
	
					// 请求获取 工商公示信息->经营异常信息->经营异常信息   http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/ccjcgs_ccjcgsIndexDetail.pt?qyid=3601002011031100051202&qylx=1130&zch=360102210023145&tabName=5
					Thread.sleep(2500);
					String gsgsxx_jyycxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewJyycxx.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&showgdxx=true";													
					HtmlPage gsgsxx_jyycxx_page = null;
				   	try {						
				   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				   		gsgsxx_jyycxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_jyycxx_url)));
				   	} catch (ClassCastException e) {
				   		gsgsxx_jyycxx_page = WebClient.getCustomHtmlPage(gsgsxx_jyycxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				   	} finally {
				   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				   	}			
										
					resultHtmlMap.put("gsgsxx_jyycxx", gsgsxx_jyycxx_page.asXml());			

					// 请求获取 工商公示信息->严重违法信息->严重违法信息
					String gsgsxx_yzwfxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewYzwfxx.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&showgdxx=true";													
					HtmlPage gsgsxx_yzwfxx_page = null;
				   	try {						
				   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				   		gsgsxx_yzwfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_yzwfxx_url)));
				   	} catch (ClassCastException e) {
				   		gsgsxx_yzwfxx_page = WebClient.getCustomHtmlPage(gsgsxx_yzwfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				   	} finally {
				   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				   	}								
					resultHtmlMap.put("gsgsxx_yzwfxx", gsgsxx_yzwfxx_page.asXml());

					// 请求获取 工商公示信息->抽查检查信息->抽查检查信息
					String gsgsxx_ccjcxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/gsgs_viewCcjcxx.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&showgdxx=true";																					
					HtmlPage gsgsxx_ccjcxx_page = null;
				   	try {						
				   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				   		gsgsxx_ccjcxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(gsgsxx_ccjcxx_url)));
				   	} catch (ClassCastException e) {
				   		gsgsxx_ccjcxx_page = WebClient.getCustomHtmlPage(gsgsxx_ccjcxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				   	} finally {
				   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				   	}							    
				    resultHtmlMap.put("gsgsxx_ccjcxx", gsgsxx_ccjcxx_page.asXml());
					
					
					
					//二 企业公示
					// 请求获取 企业公示信息->企业年报->列表	
					Thread.sleep(2500);
					String qygsxx_qynb_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/qygs_ViewQynb.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&num=0&showgdxx=true";														
					HtmlPage qygsxx_qynb_page = null;
				   	try {						
				   		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				   		qygsxx_qynb_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_qynb_url)));
				   	} catch (ClassCastException e) {
				   		qygsxx_qynb_page = WebClient.getCustomHtmlPage(qygsxx_qynb_url, firstInfoPage.getWebClient().getCurrentWindow());
				   	} finally {
				   		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				   	}						  
				    resultHtmlMap.put("qygsxx_qynb", qygsxx_qynb_page.asXml());
								
					// 请求获取 企业公示信息->企业年报->详情
					@SuppressWarnings("unchecked")
					List<HtmlElement> qygsxx_qynb_list_as = (List<HtmlElement>) qygsxx_qynb_page
							.getByXPath("//div[@id='qiyenianbao']/table/tbody/tr/td/a");
					List<Map<String, Object>> qygsxx_qynb_infos = new ArrayList<Map<String, Object>>();
					if (null != qygsxx_qynb_list_as
							&& !qygsxx_qynb_list_as.isEmpty()) {
						for (HtmlElement qygsxx_qynb_list_a : qygsxx_qynb_list_as) {
							Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
							String qygsxx_qynb_list_a_text = qygsxx_qynb_list_a.getTextContent();
							String qygsxx_qynb_list_pubdate = ((HtmlElement) qygsxx_qynb_list_a.getParentNode().getParentNode().getFirstByXPath("//td[3]")).getTextContent();
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text",qygsxx_qynb_list_a_text);
							qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",qygsxx_qynb_list_pubdate);							
							String qygsxx_qynb_list_a_href = "http://gsxt.jxaic.gov.cn" + qygsxx_qynb_list_a.getAttribute("href");
							HtmlPage qygsxx_qynb_info_page =null;
							try {
								firstInfoPage.getWebClient().getOptions().setTimeout(120000);
								qygsxx_qynb_info_page=firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_qynb_list_a_href)));
							} catch (ClassCastException e) {
								qygsxx_qynb_info_page=WebClient.getCustomHtmlPage(qygsxx_qynb_list_a_href, firstInfoPage.getWebClient().getCurrentWindow());
							} finally {
								firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
							}						
							qygsxx_qynb_info_map.put("qygsxx_qynb_info_page",qygsxx_qynb_info_page.asXml());
							qygsxx_qynb_infos.add(qygsxx_qynb_info_map);
						}
					}
					resultHtmlMap.put("qygsxx_qynb_infos", qygsxx_qynb_infos);

					// 请求获取 企业公示信息->股东及出资信息	
				    Thread.sleep(2500);
					String qygsxx_gdjczxx_url = "http://gsxt.jxaic.gov.cn/ECPS/qygs/gdjcz_viewGdjcz.pt?qyid="+qyid+"&qygsxx=1&showgdxx=true";					
				    HtmlPage qygsxx_gdjczxx_page = null;
					try {						
						firstInfoPage.getWebClient().getOptions().setTimeout(120000);
						qygsxx_gdjczxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_gdjczxx_url)));
					} catch (ClassCastException e) {
						qygsxx_gdjczxx_page = WebClient.getCustomHtmlPage(qygsxx_gdjczxx_url, firstInfoPage.getWebClient().getCurrentWindow());
					} finally {
						firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
					}		
					resultHtmlMap.put("qygsxx_gdjczxx", qygsxx_gdjczxx_page.asXml());
										
					// 请求获取 企业公示信息->股权变更信息				
				    String qygsxx_gqbgxx_url = "http://gsxt.jxaic.gov.cn/ECPS/qygs/gqbg_viewGqbg.pt?qyid="+qyid+"&qygsxx=1&showgdxx=true" ;											
				    HtmlPage qygsxx_gqbgxx_page = null;
				 	try {						
				 		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				 		qygsxx_gqbgxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_gqbgxx_url)));
				 	} catch (ClassCastException e) {
				 		qygsxx_gqbgxx_page = WebClient.getCustomHtmlPage(qygsxx_gqbgxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				 	} finally {
				 		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				 	}						    
				    resultHtmlMap.put("qygsxx_gqbgxx", qygsxx_gqbgxx_page.asXml());
				
					// 请求获取 企业公示信息->行政许可信息	
					String qygsxx_xzxkxx_url = "http://gsxt.jxaic.gov.cn/ECPS/qygs/xzxk_viewXzxk.pt?qyid="+qyid+"&qygsxx=1&zch="+zch+"&qylx="+qylx+"&num=0&qymc="+keyword+"&showgdxx=true" ;											
					HtmlPage qygsxx_xzxkxx_page = null;
				 	try {						
				 		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				 		qygsxx_xzxkxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_xzxkxx_url)));
				 	} catch (ClassCastException e) {
				 		qygsxx_xzxkxx_page = WebClient.getCustomHtmlPage(qygsxx_xzxkxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				 	} finally {
				 		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				 	}						
					resultHtmlMap.put("qygsxx_xzxkxx", qygsxx_xzxkxx_page.asXml());
					

					// 请求获取 企业公示信息->知识产权出质登记信息		
					Thread.sleep(2500);
					String qygsxx_zscqczdjxx_url = "http://gsxt.jxaic.gov.cn/ECPS/qygs/zscqczdj_viewZscqczdj.pt?qyid="+qyid+"&qygsxx=1&zch="+zch+"&qylx="+qylx+"&num=0&qymc="+keyword+"&showgdxx=true";									
					HtmlPage qygsxx_zscqczdjxx_page = null;
				 	try {						
				 		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				 		qygsxx_zscqczdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_zscqczdjxx_url)));
				 	} catch (ClassCastException e) {
				 		qygsxx_zscqczdjxx_page = WebClient.getCustomHtmlPage(qygsxx_zscqczdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				 	} finally {
				 		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				 	}	
					resultHtmlMap.put("qygsxx_zscqczdjxx", qygsxx_zscqczdjxx_page.asXml());
					

					// 请求获取 企业公示信息->行政处罚信息
					String qygsxx_xzcfxx_url = "http://gsxt.jxaic.gov.cn/ECPS/qygs/xzcf_viewXzcf.pt?qyid="+qyid+"&qygsxx=1&showgdxx=true";
					HtmlPage qygsxx_xzcfxx_page = null;
				 	try {						
				 		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				 		qygsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_xzcfxx_url)));
				 	} catch (ClassCastException e) {
				 		qygsxx_xzcfxx_page = WebClient.getCustomHtmlPage(qygsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				 	} finally {
				 		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				 	}	
					resultHtmlMap.put("qygsxx_xzcfxx", qygsxx_xzcfxx_page.asXml());
					
					
					
                   //三 其他部门信息
					// 请求获取 其他部门公示信息->行政许可信息
					Thread.sleep(2500);
					String qtbmgsxx_xzxkxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/qygs_ViewQtbmxzxk.pt?qyid="+qyid+"&zch=91360921772383013C&qylx="+qylx+"&num=0";										
					HtmlPage qtbmgsxx_xzxkxx_page = null;
				 	try {						
				 		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				 		qtbmgsxx_xzxkxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qtbmgsxx_xzxkxx_url)));
				 	} catch (ClassCastException e) {
				 		qtbmgsxx_xzxkxx_page = WebClient.getCustomHtmlPage(qtbmgsxx_xzxkxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				 	} finally {
				 		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				 	}				
					resultHtmlMap.put("qtbmgsxx_xzxkxx", qtbmgsxx_xzxkxx_page.asXml());
			

					// 请求获取 其他部门公示信息->行政处罚信息
					String qtbmgsxx_xzcfxx_url = "http://gsxt.jxaic.gov.cn/ECPS/ccjcgs/qygs_ViewQtbmxzcf.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&num=0";								
					HtmlPage qtbmgsxx_xzcfxx_page = null;
				 	try {						
				 		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				 		qtbmgsxx_xzcfxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qtbmgsxx_xzcfxx_url)));
				 	} catch (ClassCastException e) {
				 		qtbmgsxx_xzcfxx_page = WebClient.getCustomHtmlPage(qtbmgsxx_xzcfxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				 	} finally {
				 		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				 	}	
					resultHtmlMap.put("qtbmgsxx_xzcfxx", qtbmgsxx_xzcfxx_page.asXml());
					 //四 司法协助公示信息
					// 请求获取 司法协助公示信息->股权冻结信息	
					Thread.sleep(2500);
					String sfxzgsxx_gqdjxx_url = "http://gsxt.jxaic.gov.cn/ECPS/sfxz/gqdj_gqdjList.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&num=0";											
					HtmlPage sfxzgsxx_gqdjxx_page = null;
				 	try {						
				 		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				 		sfxzgsxx_gqdjxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(sfxzgsxx_gqdjxx_url)));
				 	} catch (ClassCastException e) {
				 		sfxzgsxx_gqdjxx_page = WebClient.getCustomHtmlPage(sfxzgsxx_gqdjxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				 	} finally {
				 		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				 	}	
					resultHtmlMap.put("sfxzgsxx_gqdjxx", sfxzgsxx_gqdjxx_page.asXml());

					// 请求获取 司法协助公示信息->股东变更信息
					String sfxzgsxx_gqbgxx_url = "http://gsxt.jxaic.gov.cn/ECPS/sfxz/gdbg_gdbgList.pt?qyid="+qyid+"&zch="+zch+"&qylx="+qylx+"&num=0";
					HtmlPage sfxzgsxx_gqbgxx_page = null;
				 	try {						
				 		firstInfoPage.getWebClient().getOptions().setTimeout(120000);
				 		sfxzgsxx_gqbgxx_page =firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(sfxzgsxx_gqbgxx_url)));
				 	} catch (ClassCastException e) {
				 		sfxzgsxx_gqbgxx_page = WebClient.getCustomHtmlPage(sfxzgsxx_gqbgxx_url, firstInfoPage.getWebClient().getCurrentWindow());
				 	} finally {
				 		firstInfoPage.getWebClient().getOptions().setTimeout(WebCrawler.DEFAULT_PAGE_TIME_OUT);
				 	}											
					resultHtmlMap.put("sfxzgsxx_gqbgxx", sfxzgsxx_gqbgxx_page.asXml());								
					break;
				}
			}
		}
		
		return resultHtmlMap;
}

	//获取分页结果
	private String getPagination (String WebRequestUrl, String refererUrl,String xxReportNoKey, String xxReportNoValue,String corpid, String reportYear
			, String currentPage,String pageSize, HtmlPage parentHtmlPage) throws IOException {
		WebRequest webRequest = new WebRequest(new URL(WebRequestUrl), HttpMethod.POST);
		webRequest.setAdditionalHeader("Referer", refererUrl);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair(xxReportNoKey, xxReportNoValue));
		nameValuePairs.add(new NameValuePair("corpid", corpid));
		nameValuePairs.add(new NameValuePair("year", reportYear));
		nameValuePairs.add(new NameValuePair("pagination.currentPage", currentPage));
		nameValuePairs.add(new NameValuePair("pagination.pageSize", pageSize));
		webRequest.setCharset("utf-8");
		webRequest.setRequestParameters(nameValuePairs);
		Page page = parentHtmlPage.getWebClient().getPage(webRequest);
		parentHtmlPage.getWebClient().removeRequestHeader("Referer");

		String paginationPage = page.getWebResponse().getContentAsString();
		return paginationPage;
	}

	//获取企业年报详情 -> 对外提供保证担保信息
	private String getPaginationDwtgbzbd (String WebRequestUrl, String refererUrl,String xxReportNoKey, String xxReportNoValue,String corpid, String reportYear
			, String currentPage,String pageSize, HtmlPage parentHtmlPage) throws IOException {
		WebRequest webRequest = new WebRequest(new URL(WebRequestUrl), HttpMethod.POST);
		webRequest.setAdditionalHeader("Referer", refererUrl);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair(xxReportNoKey, xxReportNoValue));
		nameValuePairs.add(new NameValuePair("corpid", corpid));
		nameValuePairs.add(new NameValuePair("year", reportYear));
		nameValuePairs.add(new NameValuePair("guarIsp", "1"));
		nameValuePairs.add(new NameValuePair("pagination.currentPage", currentPage));
		nameValuePairs.add(new NameValuePair("pagination.pageSize", pageSize));
		webRequest.setCharset("utf-8");
		webRequest.setRequestParameters(nameValuePairs);
		Page page = parentHtmlPage.getWebClient().getPage(webRequest);
		parentHtmlPage.getWebClient().removeRequestHeader("Referer");

		String paginationPage = page.getWebResponse().getContentAsString();
		return paginationPage;
	}
	//获取企业年报详情 -> 修改记录
	private String getPaginationUpdateRecord (String WebRequestUrl, String refererUrl,String xxReportNoKey, String xxReportNoValue,String corpid, String reportYear
			, String currentPage,String pageSize, HtmlPage parentHtmlPage) throws IOException {
		WebRequest webRequest = new WebRequest(new URL(WebRequestUrl), HttpMethod.POST);
		webRequest.setAdditionalHeader("Referer", refererUrl);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair(xxReportNoKey, xxReportNoValue));
		nameValuePairs.add(new NameValuePair("corpid", corpid));
		nameValuePairs.add(new NameValuePair("year", reportYear));
		nameValuePairs.add(new NameValuePair("modType", "1"));
		nameValuePairs.add(new NameValuePair("pagination.currentPage", currentPage));
		nameValuePairs.add(new NameValuePair("pagination.pageSize", pageSize));
		webRequest.setCharset("utf-8");
		webRequest.setRequestParameters(nameValuePairs);
		Page page = parentHtmlPage.getWebClient().getPage(webRequest);
		parentHtmlPage.getWebClient().removeRequestHeader("Referer");

		String paginationPage = page.getWebResponse().getContentAsString();
		return paginationPage;
	}

	private String getPagination (String WebRequestUrl, String refererUrl, HtmlPage parentHtmlPage) throws IOException {
		WebRequest webRequest = new WebRequest(new URL(WebRequestUrl), HttpMethod.POST);
		webRequest.setAdditionalHeader("Referer", refererUrl);
		webRequest.setCharset("utf-8");
		Page page = parentHtmlPage.getWebClient().getPage(webRequest);
		parentHtmlPage.getWebClient().removeRequestHeader("Referer");

		String paginationPage = page.getWebResponse().getContentAsString();
		return paginationPage;
	}

	//获取页面上分页的总页数
	private int getPages (HtmlAnchor nextPageAnchor) {
		int pags = 0;
		String textContent = nextPageAnchor.getParentNode().getTextContent();
		String eL = "共[0-9]*页";
		Pattern pattern = Pattern.compile(eL);
		Matcher matcher = pattern.matcher(textContent);
		String pagesStr = "";
		if(matcher.find()){
			pagesStr = matcher.group(0);
			pags = Integer.parseInt(pagesStr.substring(1, pagesStr.length()-1));
		}
		return pags;
	}

	//浙江数据
	@SuppressWarnings("unchecked")
	private Map<String, Object> getHtmlInfoMapOfZhejiang(String area, HtmlPage firstInfoPage, String keyword, ChannelLogger LOGGER) throws Exception {

		WebWindow window = firstInfoPage.getWebClient().getCurrentWindow();
		WebClient fistInfoWc = firstInfoPage.getWebClient();
		fistInfoWc.getOptions().setJavaScriptEnabled(false);
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();
		final String HOST_OF_ZHEJIANG = "http://gsxt.zjaic.gov.cn";
		/* xPath=/html/body/div[2]/div/dl */
		HtmlElement divByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//dl[@class='list']"));
		if (divByXPath == null) {
			DomElement checkcode = firstInfoPage.getElementById("checkcode");
			resultHtmlMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
		} else {
			HtmlElement firstByXPath = ((HtmlElement)firstInfoPage.getFirstByXPath("//dl[@class='list']/dt[last()]"));
			String textContent = firstByXPath.getTextContent();
			if (textContent.indexOf("您搜索的条件无查询结果") > 0) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			} else {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
			}
		}

		@SuppressWarnings("unchecked")
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) firstInfoPage.getByXPath("//dl[@class='list']/dt/a");
		LOGGER.info(anchors.toString());
		if (anchors!=null && !anchors.isEmpty()) {
			boolean matchFlag = false;
			for (HtmlAnchor anchor : anchors) {
				String anchorTitle = anchor.getTextContent().toString().trim();

				if (anchorTitle.contains(keyword)) { //匹配到需要精确搜索的条目
					matchFlag = true;
					//公共参数
					String corpid = "";

					/*保存列表页目标条目信息*/
					HtmlElement target_item_info = (HtmlElement) anchor.getParentNode();
					resultHtmlMap.put("target_item_info", target_item_info.asXml());

					//点击列表页目标条目，获取 工商公示信息->登记信息
					HtmlPage gsgsxx_djxx = anchor.click();
					resultHtmlMap.put("gsgsxx_djxx", gsgsxx_djxx.asXml());

					/*工商公示信息*/
					//点击列表页目标条目，获取 工商公示信息->登记信息
					HtmlElement djRframe = (HtmlElement) gsgsxx_djxx.getByXPath("//iframe[@id='con_nav_ifm_1']").get(0);
					String djRframe_url = djRframe.getAttribute("url");
					if (!StringUtils.isEmpty(djRframe_url)) {
						String key = "corpid";
						//获取公共参数corpid
						corpid = djRframe_url.substring(djRframe_url.indexOf(key) + key.length()+1);
						System.out.println("######### corpid ###" + corpid);

						String gsgsxx_djRframe_url = HOST_OF_ZHEJIANG + djRframe_url;

						Map<String, Object> gsgsDjMap = new HashMap<>();
						//点击列表页目标条目，获取 工商公示信息->登记信息(iframe)-> 基本信息、变更信息(第一页)
						String refererUrl = HOST_OF_ZHEJIANG + "/annualreport/doViewAnnualReportIndex.do?corpid=" + corpid;
//						String hrefUrl = anchor.getAttribute("href");
//						String url = HOST_OF_ZHEJIANG + "/appbasicinfo/doReadAppBasicInfo.do?" + hrefUrl.substring(hrefUrl.lastIndexOf("?")+1);
						WebRequest djWebRequest = new WebRequest(new URL(gsgsxx_djRframe_url), HttpMethod.GET);
						djWebRequest.setAdditionalHeader("Referer", refererUrl);
						HtmlPage gsgsxx_djxx_first = firstInfoPage.getWebClient().getPage(window, djWebRequest);

						gsgsxx_djxx_first.getWebClient().removeRequestHeader("Referer");
						gsgsDjMap.put("gsgsxx_djxx_first",gsgsxx_djxx_first.asXml());
//						System.out.println("gsgsxx_djxx_first: "+gsgsxx_djxx_first.asXml());

						//获取总页数
						int bgPages = 0; //变更信息
						int gdPages = 0; //股东信息
						List<HtmlAnchor> nextPageAnchors = (List<HtmlAnchor>) gsgsxx_djxx_first.getByXPath("//a[@id='nextPage']");
						System.out.println("##===================nextPageAnchor==================================" + nextPageAnchors  );
						//获取当前页面
						if (nextPageAnchors!= null &&  !nextPageAnchors.isEmpty()) {
							String gbAnchorOnclick = "";
							for (HtmlAnchor nextPageAnchor : nextPageAnchors) {
								String anchorOnclick = nextPageAnchor.getAttribute("onclick");
								if (anchorOnclick != null) {
									//goPageCheckAlter('2') 定位到变更信息的id="nextPage"
									if (anchorOnclick.contains("goPageCheckAlter")) {
										bgPages = getPages(nextPageAnchor);
										System.out.println("====bgxx pages========="+bgPages);
										//goPageCheckAlter('2') 定位到股东信息的id="nextPage"  //浙江荣盛控股集团有限公司
									}else if (anchorOnclick.contains("goPageEntInvestor")){
										gdPages = getPages(nextPageAnchor);
										System.out.println("====gdxx pages========="+gdPages);
									}
								}
							}
						}

						//获取变更信息的分页（第二页开始）
						List<String> djxx_bgNextPageList = new ArrayList<>();
						String nextPage_url = HOST_OF_ZHEJIANG + "/appbasicinfo/doReadAppBasicInfo.do?corpid=" + corpid;
						for (int i=2; i <= bgPages; i++) {
							WebRequest nextBgWebRequest = new WebRequest(new URL(nextPage_url), HttpMethod.POST);
							nextBgWebRequest.setAdditionalHeader("Referer", nextPage_url);
							List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
							nameValuePairs.add(new NameValuePair("checkAlterPagination.currentPage", i + ""));
							nameValuePairs.add(new NameValuePair("checkAlterPagination.pageSize", "5"));
							nextBgWebRequest.setCharset("utf-8");
							nextBgWebRequest.setRequestParameters(nameValuePairs);
							HtmlPage djxx_bgNextPage = firstInfoPage.getWebClient().getPage(window, nextBgWebRequest);
							djxx_bgNextPage.getWebClient().removeRequestHeader("Referer");

							djxx_bgNextPageList.add(djxx_bgNextPage.asXml());
//							System.out.println("==djxx_bgNextPage===" + djxx_bgNextPage.asXml());
						}
						gsgsDjMap.put("gsgsxx_djxx_bg_next", djxx_bgNextPageList);
						System.out.println("djxx_bgNextPageList: " + (djxx_bgNextPageList.isEmpty()?null:djxx_bgNextPageList.size()));

						//获取股东信息的分页（第二页开始）
						List<String> djxx_gdNextPageList = new ArrayList<>();
						for (int i=2; i <= gdPages; i++) {
							WebRequest nextBgWebRequest = new WebRequest(new URL(nextPage_url), HttpMethod.POST);
							nextBgWebRequest.setAdditionalHeader("Referer", nextPage_url);
							List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
							nameValuePairs.add(new NameValuePair("entInvestorPagination.currentPage", i + ""));
							nameValuePairs.add(new NameValuePair("entInvestorPagination.pageSize", "5"));
							nextBgWebRequest.setCharset("utf-8");
							nextBgWebRequest.setRequestParameters(nameValuePairs);
							HtmlPage djxx_gdNextPage = firstInfoPage.getWebClient().getPage(window, nextBgWebRequest);
							djxx_gdNextPage.getWebClient().removeRequestHeader("Referer");

							djxx_gdNextPageList.add(djxx_gdNextPage.asXml());
//							System.out.println("==djxx_gdNextPage===" + djxx_gdNextPage.asXml());
						}
						gsgsDjMap.put("gsgsxx_djxx_gd_next", djxx_gdNextPageList);
						System.out.println("djxx_gdNextPageList: " + (djxx_gdNextPageList.isEmpty()?null:djxx_gdNextPageList.size()));

						resultHtmlMap.put("gsgsxx_djxx_info", gsgsDjMap);
					}

					//获取 工商公示信息->备案信息
					HtmlElement baRframe = (HtmlElement) gsgsxx_djxx.getByXPath("//iframe[@id='con_nav_ifm_3']").get(0);
					String baRframe_url = baRframe.getAttribute("url");
					if (!StringUtils.isEmpty(baRframe_url)) {
						Map<String, Object> gsgsDjMap = new HashMap<>();
						//备案信息->主管部门（出资人）信息
						String gsgsxx_baRframe_url = HOST_OF_ZHEJIANG + baRframe_url;
						HtmlPage baPage = fistInfoWc.getPage(window, new WebRequest(new URL(gsgsxx_baRframe_url)));
						resultHtmlMap.put("gsgsxx_baxx_zgbmxx", baPage.asXml());
//						System.out.println("gsgsxx_baxx_zgbmxx:"+baPage.asXml());

						//获取总页数
						int zyryPages = 0; //主要人员
						int fzjgPages = 0; //分支机构信息
						//获取页大小
						String zyryPagesize = "10"; //主要人员 (默认10条)
						List<HtmlElement> pageSizeInputs = (List<HtmlElement>) baPage.getByXPath("//input[@name='entMemberPagination.pageSize']");
						if (pageSizeInputs != null && !pageSizeInputs.isEmpty()) {
							zyryPagesize = pageSizeInputs.get(0).getAttribute("value");
						}
						String fzjgPagesize = "10";//分支机构信息 (默认10条)
						List<HtmlElement> fzjgPSInputs = (List<HtmlElement>) baPage.getByXPath("//input[@name='branchInfoPagination.pageSize']");
						if (fzjgPSInputs != null && !fzjgPSInputs.isEmpty()) {
							fzjgPagesize = fzjgPSInputs.get(0).getAttribute("value");
						}
						System.out.println("=======zyryPagesize========: " + zyryPagesize);
						System.out.println("=======fzjgPagesize========: " + fzjgPagesize);
						List<HtmlAnchor> nextPageAnchors = (List<HtmlAnchor>) baPage.getByXPath("//a[@id='nextPage']");
						System.out.println("##===================nextPageAnchor==================================" + nextPageAnchors  );
						if (nextPageAnchors!= null &&  !nextPageAnchors.isEmpty()) {
							String gbAnchorOnclick = "";
							for (HtmlAnchor nextPageAnchor : nextPageAnchors) {
								String anchorOnclick = nextPageAnchor.getAttribute("onclick");
								if (anchorOnclick != null) {
									//goPageEntMember('2') 定位到主要人员的id="nextPage"
									if (anchorOnclick.contains("goPageEntMember")) {
										zyryPages = getPages(nextPageAnchor);
										System.out.println("====zyryxx pages========="+zyryPages);
										//goPageBranchInfo('2') 定位到分支机构信息的id="nextPage"  //天正集团有限公司
									}else if (anchorOnclick.contains("goPageBranchInfo")){
										fzjgPages = getPages(nextPageAnchor);
										System.out.println("====fzjgxx pages========="+fzjgPages);
									}
								}
							}
						}

						//获取主要人员的分页（从第一页开始）
						List<String> baxx_zyryPageList = new ArrayList<>();
						for (int i=1; i <= zyryPages; i++) {
							WebRequest zyryWebRequest = new WebRequest(new URL(gsgsxx_baRframe_url), HttpMethod.POST);
							zyryWebRequest.setAdditionalHeader("Referer", gsgsxx_baRframe_url);
							List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
							nameValuePairs.add(new NameValuePair("entMemberPagination.currentPage", i + ""));
							nameValuePairs.add(new NameValuePair("entMemberPagination.pageSize", zyryPagesize));
							zyryWebRequest.setCharset("utf-8");
							zyryWebRequest.setRequestParameters(nameValuePairs);
							HtmlPage baxx_zyryPage = firstInfoPage.getWebClient().getPage(window, zyryWebRequest);
							baxx_zyryPage.getWebClient().removeRequestHeader("Referer");

							baxx_zyryPageList.add(baxx_zyryPage.asXml());
//							System.out.println("=====baxx_zyryPage.asXml()===="+baxx_zyryPage.asXml());
						}
						if (zyryPages == 0 ) {
							baxx_zyryPageList.add(baPage.asXml());
						}
						gsgsDjMap.put("gsgsxx_baxx_zyry_pages", baxx_zyryPageList);
						System.out.println("baxx_zyryPageList: " + (baxx_zyryPageList.isEmpty()?null:baxx_zyryPageList.size()));

						//分支机构信息的分页
						List<String> baxx_fzjgPageList = new ArrayList<>();
						for (int i=1; i <= fzjgPages; i++) {
							WebRequest fzjgWebRequest = new WebRequest(new URL(gsgsxx_baRframe_url), HttpMethod.POST);
							fzjgWebRequest.setAdditionalHeader("Referer", gsgsxx_baRframe_url);
							List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
							nameValuePairs.add(new NameValuePair("branchInfoPagination.currentPage", i + ""));
							nameValuePairs.add(new NameValuePair("branchInfoPagination.pageSize", fzjgPagesize));
							fzjgWebRequest.setCharset("utf-8");
							fzjgWebRequest.setRequestParameters(nameValuePairs);
							HtmlPage baxx_fzjgPage = firstInfoPage.getWebClient().getPage(window, fzjgWebRequest);
							baxx_fzjgPage.getWebClient().removeRequestHeader("Referer");

							baxx_fzjgPageList.add(baxx_fzjgPage.asXml());
//							System.out.println("==baxx_fzjgPage===" + baxx_fzjgPage.asXml());
						}
						if (fzjgPages == 0 ) {
							baxx_fzjgPageList.add(baPage.asXml());
						}
						gsgsDjMap.put("gsgsxx_baxx_fzjg_pages", baxx_fzjgPageList);
						System.out.println("baxx_fzjgPageList: " + (baxx_fzjgPageList.isEmpty()?null:baxx_fzjgPageList.size()));

						resultHtmlMap.put("gsgsxx_baxx_info", gsgsDjMap);
					}

					//获取 工商公示信息->动产抵押登记信息->动产抵押登记信息
					HtmlElement dcdyRframe = (HtmlElement) gsgsxx_djxx.getByXPath("//iframe[@id='con_nav_ifm_5']").get(0);
					String dcdyRframe_url = dcdyRframe.getAttribute("url");
					if (!StringUtils.isEmpty(dcdyRframe_url)) {
						List<Map<String, String>> gsgsxx_dcdydjxx_info = new ArrayList<Map<String, String>>();
						Map<String, String> dcdydjxx_info_map = new HashMap<String, String>();
						String gsgsxx_dcdyRframe_url = HOST_OF_ZHEJIANG + dcdyRframe_url;
						HtmlPage dcdyPage = fistInfoWc.getPage(window, new WebRequest(new URL(gsgsxx_dcdyRframe_url)));
//						resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx", dcdyPage.asXml());
						//请求获取 工商公示信息->动产抵押登记信息->动产抵押登记信息->详情
						List<HtmlElement> gsgsxx_dcdydjxx_tr_list = (List<HtmlElement>)  dcdyPage.getByXPath("//div[@id='dongchandiya']/table/tbody/tr");
						for (int i=2; i < gsgsxx_dcdydjxx_tr_list.size(); i++) { //table的第三个tr开始有数据
							HtmlElement gsgsxx_dcdydjxx_tr = gsgsxx_dcdydjxx_tr_list.get(i);
							dcdydjxx_info_map.put("gsgsxx_dcdydjxx_dcdydjxx_tr", gsgsxx_dcdydjxx_tr.asXml());
//							System.out.println("###gsgsxx_dcdydjxx_dcdydjxx_tr:" + gsgsxx_dcdydjxx_tr.asXml());

							List<HtmlElement> dcdydjxx_a_list = (List<HtmlElement>) dcdyPage.getByXPath("//div[@id='dongchandiya']/table/tbody/tr[" + (i + 1) + "]/td[7]/a");
							if (dcdydjxx_a_list!= null && !dcdydjxx_a_list.isEmpty()) {
								String dcdydjxx_href = dcdydjxx_a_list.get(0).getAttribute("href");
								if (dcdydjxx_href != null && !"".equals(dcdydjxx_href)) {
									String gsgsxx_dcdydjxx_list_a_href = HOST_OF_ZHEJIANG + dcdydjxx_href;
									System.out.println("##a href: " + gsgsxx_dcdydjxx_list_a_href);

									//获取动产抵押登记信息详情 (动产抵押登记信息|被担保债权概况|注销)
									HtmlPage gsgsxx_dcdydjxx_detail_page = firstInfoPage.getWebClient().getPage(gsgsxx_dcdydjxx_list_a_href);
									dcdydjxx_info_map.put("gsgsxx_dcdydjxx_detail_page", gsgsxx_dcdydjxx_detail_page.asXml());
//									System.out.println("##gsgsxx_dcdydjxx_detail_page.asXml():" + gsgsxx_dcdydjxx_detail_page.asXml());
								}
							}
							gsgsxx_dcdydjxx_info.add(dcdydjxx_info_map);
						}
						resultHtmlMap.put("gsgsxx_dcdydjxx_dcdydjxx_info", gsgsxx_dcdydjxx_info);
					}

					//获取 工商公示信息->股权出质登记信息
					HtmlElement gqczdjRframe = (HtmlElement) gsgsxx_djxx.getByXPath("//iframe[@id='con_nav_ifm_4']").get(0);
					String gqczdjRframe_url = gqczdjRframe.getAttribute("url");
					if (!StringUtils.isEmpty(gqczdjRframe_url)) {
						String gsgsxx_gqczdjRframe_url = HOST_OF_ZHEJIANG + gqczdjRframe_url;
						HtmlPage gqczdjPage = fistInfoWc.getPage(window, new WebRequest(new URL(gsgsxx_gqczdjRframe_url)));
						resultHtmlMap.put("gsgsxx_gqczdjxx_gqczdjxx", gqczdjPage.asXml());
					}

					//获取 工商公示信息->行政处罚信息
					HtmlElement xzcfRframe = (HtmlElement) gsgsxx_djxx.getByXPath("//iframe[@id='con_nav_ifm_6']").get(0);
					String xzcfRframe_url = xzcfRframe.getAttribute("url");
					if (!StringUtils.isEmpty(xzcfRframe_url)) {
						String gsgsxx_xzcfRframe_url = HOST_OF_ZHEJIANG + xzcfRframe_url;
						HtmlPage xzcfPage = fistInfoWc.getPage(window, new WebRequest(new URL(gsgsxx_xzcfRframe_url)));
						resultHtmlMap.put("gsgsxx_xzcfxx_xzcfxx", xzcfPage.asXml());
					}

					//获取 工商公示信息->经营异常信息
					HtmlElement jyycRframe = (HtmlElement) gsgsxx_djxx.getByXPath("//iframe[@id='con_nav_ifm_7']").get(0);
					String jyycRframe_url = jyycRframe.getAttribute("url");
					if (!StringUtils.isEmpty(jyycRframe_url)) {
						String gsgsxx_jyycRframe_url = HOST_OF_ZHEJIANG + jyycRframe_url;
						HtmlPage jyycPage = fistInfoWc.getPage(window, new WebRequest(new URL(gsgsxx_jyycRframe_url)));
						resultHtmlMap.put("gsgsxx_jyycxx_jyycxx", jyycPage.asXml());
					}

					//获取 工商公示信息->严重违法信息
					HtmlElement yzwfRframe = (HtmlElement) gsgsxx_djxx.getByXPath("//iframe[@id='con_nav_ifm_8']").get(0);
					String yzwfRframe_url = yzwfRframe.getAttribute("url");
					if (!StringUtils.isEmpty(yzwfRframe_url)) {
						String gsgsxx_yzwfRframe_url = HOST_OF_ZHEJIANG + yzwfRframe_url;
						HtmlPage yzwfPage = fistInfoWc.getPage(window, new WebRequest(new URL(gsgsxx_yzwfRframe_url)));
						resultHtmlMap.put("gsgsxx_yzwfxx_yzwfxx", yzwfPage.asXml());
					}

					//获取 工商公示信息->抽查检查信息
					HtmlElement ccjcRframe = (HtmlElement) gsgsxx_djxx.getByXPath("//iframe[@id='con_nav_ifm_9']").get(0);
					String ccjcRframe_url = ccjcRframe.getAttribute("url");
					if (!StringUtils.isEmpty(ccjcRframe_url)) {
						String gsgsxx_ccjcRframe_url = HOST_OF_ZHEJIANG + ccjcRframe_url;
						HtmlPage ccjcPage = fistInfoWc.getPage(window, new WebRequest(new URL(gsgsxx_ccjcRframe_url)));
						resultHtmlMap.put("gsgsxx_ccjcxx_ccjcxx", ccjcPage.asXml());
					}


					/*企业公示信息*/
					//左侧ｔａｂ的ｕｒｌ
					String qygsxx_leftTab_url = HOST_OF_ZHEJIANG + "/annualreport/doViewAnnualReportIndex.do?corpid=" + corpid;
					HtmlPage qygsxx_page = firstInfoPage.getWebClient().getPage(window, new WebRequest(new URL(qygsxx_leftTab_url)));
					System.out.println("========qygsxx_page: "+qygsxx_page.asXml());

					//获取 企业公示信息->企业年报
					HtmlElement qynbRfram = (HtmlElement) qygsxx_page.getByXPath("//iframe[@id='con_nav_ifm_1']").get(0);
					String qynbRfram_url = qynbRfram.getAttribute("url");
					if (!StringUtils.isEmpty(qynbRfram_url)) {
						String qygsxx_qynbxx_url = HOST_OF_ZHEJIANG + qynbRfram_url;

						//获取 企业公示信息->企业年报列表信息
						HtmlPage gygsxx_qynbxx_page = fistInfoWc.getPage(window, new WebRequest(new URL(qygsxx_qynbxx_url)));
						resultHtmlMap.put("qygsxx_qynbxx", gygsxx_qynbxx_page.asXml());

						//年度报告详情
						@SuppressWarnings("unchecked")
						List<Map<String, Object>> qygsxx_ndbgxx_infos = new ArrayList<Map<String, Object>>();
						List<HtmlAnchor> t01TabA = (List<HtmlAnchor>) gygsxx_qynbxx_page.getByXPath("//table[@id='t01']/tbody/tr/td[2]/a");
						if (t01TabA != null && !t01TabA.isEmpty()) {
							for (HtmlElement anchorT01Tab_a : t01TabA) {
								Map<String, Object> qygsxx_qynb_info_map = new LinkedHashMap<String, Object>();
								String qygsxx_qynb_list_a_href = HOST_OF_ZHEJIANG + anchorT01Tab_a.getAttribute("href");
								String qygsxx_qynb_list_a_text = anchorT01Tab_a.getTextContent();
								String qygsxx_qynb_list_pubdate = anchorT01Tab_a.getParentNode().getParentNode().getTextContent();
								String eL = "[0-9]{4}年[0-9]{2}月[0-9]{2}日";
								Pattern pattern = Pattern.compile(eL);
								Matcher matcher = pattern.matcher(qygsxx_qynb_list_pubdate);
								String dateStr = "";
								if(matcher.find()){
									dateStr = matcher.group(0);
								}

								qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_href", qygsxx_qynb_list_a_href);
								qygsxx_qynb_info_map.put("qygsxx_qynb_list_a_text", qygsxx_qynb_list_a_text);
								qygsxx_qynb_info_map.put("qygsxx_qynb_list_pubdate",dateStr);

								//获取企业年报详情  企业基本信息 & (企业资产状况信息/生产经营情况)
								HtmlPage qygsxx_qynb_info_1_4_page = anchorT01Tab_a.click();
								qygsxx_qynb_info_map.put("qygsxx_qynb_info_1_4_page", qygsxx_qynb_info_1_4_page.asXml());

								//报表年份
								String reportYear = qygsxx_qynb_list_a_href.substring(qygsxx_qynb_list_a_href.lastIndexOf("&year")+"&year".length()+1);
								//分页参数xxReportNo （每个公司不一样）
								String rNoEL = "\\d{26}";
								Pattern rNoPattern = Pattern.compile(rNoEL);
								Matcher rNoMatcher = rNoPattern.matcher(anchorT01Tab_a.click().getWebResponse().getContentAsString());
								String xxReportNoValue = "";
								if(rNoMatcher.find()){
									xxReportNoValue = rNoMatcher.group(0);
								}
								System.out.println("xxReportNoValue: " + xxReportNoValue);

								mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);


								//1、获取企业年报详情 -> 网站或网店信息
								//第一页
								List<String> wzhwdPaginations = new ArrayList<>();
								String wzhwd_id = "doReadPubReportBusiWeb" + new Date().getTime();
								String wzhwdxx_url = HOST_OF_ZHEJIANG + "/pubreportbusiweb/doReadPubReportBusiWebListJSON.do?_id=" + wzhwd_id ;
								String wzhwReportNoKey = "webReportNo";
								String wzhwdFistPage = getPagination(wzhwdxx_url,qygsxx_qynb_list_a_href,wzhwReportNoKey,xxReportNoValue,corpid,reportYear,"1"
										,"5",qygsxx_qynb_info_1_4_page);
								JsonNode wzhwdRoot = mapper.readTree(wzhwdFistPage);
                                String wzhwdFistDataList = wzhwdRoot.get("pagination").get("dataList").toString();
								wzhwdPaginations.add(wzhwdFistDataList);

								//获取总页数
								String wzhwdPagesNode = wzhwdRoot.get("pagination").get("pages").toString();
								if(wzhwdPagesNode != null) {
									int pages = Integer.parseInt(wzhwdPagesNode);
									//获取所有分页数据(第二页开始)
									for (int i = 2 ; i <= pages; i++) {
										String wzhwdNextPage = getPagination(wzhwdxx_url,qygsxx_qynb_list_a_href,wzhwReportNoKey,xxReportNoValue,corpid,reportYear,i+""
												,"5",qygsxx_qynb_info_1_4_page);
										JsonNode wzhwdNextRoot = mapper.readTree(wzhwdNextPage);
										String wzhwdNextDataList = wzhwdNextRoot.get("pagination").get("dataList").toString();
										wzhwdPaginations.add(wzhwdNextDataList);
									}
								}
								qygsxx_qynb_info_map.put("qynb_info_wzhwd_pages", wzhwdPaginations);
								System.out.println("wzhwdPaginations: ");
								System.out.println(wzhwdPaginations.isEmpty()?null: wzhwdPaginations.size());

								//2、获取企业年报详情 -> 对外投资信息
								//第一页
								List<String> dwtzPaginations = new ArrayList<>();
								String _id = "doReadPubReportConInfo" + new Date().getTime();
								String dwtzxx_url = HOST_OF_ZHEJIANG + "/pubreportinvestinfo/doReadPubReportInvestInfoListJSON.do?_id=" + _id ;
								String dwtzReportNoKey = "investReportNo";
								String dwtzFistPage = getPagination(dwtzxx_url,qygsxx_qynb_list_a_href,dwtzReportNoKey,xxReportNoValue,corpid,reportYear,"1"
														,"5",qygsxx_qynb_info_1_4_page);
								JsonNode dwtzRoot = mapper.readTree(dwtzFistPage);
								String dwtzFistDataList = dwtzRoot.get("pagination").get("dataList").toString();
								dwtzPaginations.add(dwtzFistDataList);

								//获取总页数
								String dwtzPagesNode = dwtzRoot.get("pagination").get("pages").toString();
								if(dwtzPagesNode != null) {
									int pages = Integer.parseInt(dwtzPagesNode);
									//获取所有分页数据(第二页开始)
									for (int i = 2 ; i <= pages; i++) {
										String dwtzNextPage = getPagination(dwtzxx_url,qygsxx_qynb_list_a_href,dwtzReportNoKey,xxReportNoValue,corpid,reportYear,i+""
												,"5",qygsxx_qynb_info_1_4_page);
										JsonNode dwtzNextRoot = mapper.readTree(dwtzNextPage);
										String dwtzNextDataList = dwtzNextRoot.get("pagination").get("dataList").toString();
										dwtzPaginations.add(dwtzNextDataList);
									}
								}
								qygsxx_qynb_info_map.put("qynb_info_dwtz_pages", dwtzPaginations);
								System.out.println("dwtzPaginations: ");
								System.out.println(dwtzPaginations.isEmpty()?null: dwtzPaginations.size());


								//3、获取企业年报详情 -> 对外提供保证担保信息
								//第一页
								List<String> dwtgbzbdPaginations = new ArrayList<>();
								String dwtgbzbd_id = "doReadPubReportConInfo" + new Date().getTime();
								String dwtgbzbdxx_url = HOST_OF_ZHEJIANG + "/pubreportguaranteeinfo/doReadPubReportGuaranteeInfoListJSON.do?_id=" + dwtgbzbd_id ;
								String dwtgbzbdReportNoKey = "guarReportNo";
								String dwtgbzbdFistPage = getPaginationDwtgbzbd(dwtgbzbdxx_url, qygsxx_qynb_list_a_href, dwtgbzbdReportNoKey, xxReportNoValue, corpid, reportYear, "1"
										, "5", qygsxx_qynb_info_1_4_page);
								JsonNode dwtgbzbdRoot = mapper.readTree(dwtgbzbdFistPage);
								String dwtgbzbdFistDataList = dwtgbzbdRoot.get("pagination").get("dataList").toString();
								dwtgbzbdPaginations.add(dwtgbzbdFistDataList);

								//获取总页数
								String dwtgbzbdPagesNode = dwtgbzbdRoot.get("pagination").get("pages").toString();
								if(dwtgbzbdPagesNode != null) {
									int pages = Integer.parseInt(dwtgbzbdPagesNode);
									//获取所有分页数据(第二页开始)
									for (int i = 2 ; i <= pages; i++) {
										String dwtgbzbdNextPage = getPaginationDwtgbzbd(dwtgbzbdxx_url, qygsxx_qynb_list_a_href, dwtgbzbdReportNoKey, xxReportNoValue, corpid, reportYear, i + ""
												, "5", qygsxx_qynb_info_1_4_page);
										JsonNode dwtgbzbdNextRoot = mapper.readTree(dwtgbzbdNextPage);
										String dwtgbzbdNextDataList = dwtgbzbdNextRoot.get("pagination").get("dataList").toString();
										dwtgbzbdPaginations.add(dwtgbzbdNextDataList);
									}
								}
								qygsxx_qynb_info_map.put("qynb_info_dwtgbzbd_pages", dwtgbzbdPaginations);


								//4、获取企业年报详情 -> 修改记录
								//第一页
								List<String> updateRecordPaginations = new ArrayList<>();
								String updateRecord_id = "doReadPubReportModHis" + new Date().getTime();
								String updateRecordxx_url = HOST_OF_ZHEJIANG + "/pubreportmodhis/doReadPubReportModHisJSON.do?_id=" + updateRecord_id ;
								String updateRecordReportNoKey = "modReportNo";
								String updateRecordFistPage = getPaginationUpdateRecord(updateRecordxx_url, qygsxx_qynb_list_a_href, updateRecordReportNoKey, xxReportNoValue, corpid, reportYear, "1"
										, "5", qygsxx_qynb_info_1_4_page);
								JsonNode updateRecordRoot = mapper.readTree(updateRecordFistPage);
								String updateRecordFistDataList = updateRecordRoot.get("pagination").get("dataList").toString();
								updateRecordPaginations.add(updateRecordFistDataList);

								//获取总页数
								String updateRecordPagesNode = updateRecordRoot.get("pagination").get("pages").toString();
								if(updateRecordPagesNode != null) {
									int pages = Integer.parseInt(updateRecordPagesNode);
									//获取所有分页数据(第二页开始)
									for (int i = 2 ; i <= pages; i++) {
										String updateRecordNextPage = getPaginationUpdateRecord(updateRecordxx_url, qygsxx_qynb_list_a_href, updateRecordReportNoKey, xxReportNoValue, corpid, reportYear, i + ""
												, "5", qygsxx_qynb_info_1_4_page);
										JsonNode updateRecordNextRoot = mapper.readTree(updateRecordNextPage);
										String updateRecordNextDataList = updateRecordNextRoot.get("pagination").get("dataList").toString();
										updateRecordPaginations.add(updateRecordNextDataList);
									}
								}
								System.out.println("=======updateRecordPaginations=====" + updateRecordPaginations);
								qygsxx_qynb_info_map.put("qynb_info_updateRecord_pages", updateRecordPaginations);

								//5、获取企业年报详情 -> 股东及出资信息 (看情况,有的公司,比如: 人民电器集团有限公司)
								//第一页
								List<String> gdjczPaginations = new ArrayList<>();
								String gdjcz_id = "doReadPubReportConInfo" + new Date().getTime();
								String gdjczxx_url = HOST_OF_ZHEJIANG + "/pubreportconinfo/doReadPubReportConInfoListJSON.do?_id=" + gdjcz_id ;
								String gdjczReportNoKey = "conReportNo";
								String gdjczFistPage = getPagination(gdjczxx_url, qygsxx_qynb_list_a_href, gdjczReportNoKey, xxReportNoValue, corpid, reportYear, "1"
										, "5", qygsxx_qynb_info_1_4_page);
								if (gdjczFistPage != null && !"".equals(gdjczFistPage)) {
									JsonNode gdjczRoot = mapper.readTree(gdjczFistPage);

									String gdjczFistDataList = gdjczRoot.get("pagination").get("dataList").toString();
									gdjczPaginations.add(gdjczFistDataList);

									//获取总页数
									String gdjczPagesNode = gdjczRoot.get("pagination").get("pages").toString();
									if (gdjczPagesNode != null) {
										int pages = Integer.parseInt(gdjczPagesNode);
										//获取所有分页数据(第二页开始)
										for (int i = 2; i <= pages; i++) {
											String gdjczNextPage = getPagination(gdjczxx_url, qygsxx_qynb_list_a_href, gdjczReportNoKey, xxReportNoValue, corpid, reportYear, i + ""
													, "5", qygsxx_qynb_info_1_4_page);
											System.out.println("==gdjczNextPage==" + gdjczNextPage);
											if (gdjczNextPage != null && !"".equals(gdjczNextPage)) {
												JsonNode gdjczNextRoot = mapper.readTree(gdjczNextPage);
												String gdjczNextDataList = gdjczNextRoot.get("pagination").get("dataList").toString();
												gdjczPaginations.add(gdjczNextDataList);
											}
										}
									}
								}
								qygsxx_qynb_info_map.put("qynb_info_gdjcz_pages", gdjczPaginations);
								System.out.println("gdjczPaginations: ");
								System.out.println(gdjczPaginations.isEmpty()?null: gdjczPaginations.size());

								//6、获取企业年报详情 -> 股权变更信息 (人民电器集团有限公司)
								//第一页
								List<String> gqbgPaginations = new ArrayList<>();
								String gqbg_id = "doReadPubReportConInfo" + new Date().getTime();
								String gqbgxx_url = HOST_OF_ZHEJIANG + "/pubreportstockinfo/doReadPubReportStockInfoListJSON.do?_id=" + gqbg_id ;
								String gqbgReportNoKey = "stockReportNo";
								String gqbgFistPage = getPagination(gqbgxx_url, qygsxx_qynb_list_a_href, gqbgReportNoKey, xxReportNoValue, corpid, reportYear, "1"
										, "5", qygsxx_qynb_info_1_4_page);
								JsonNode gqbgRoot = mapper.readTree(gqbgFistPage);
								String gqbgFistDataList = gqbgRoot.get("pagination").get("dataList").toString();
								gqbgPaginations.add(gqbgFistDataList);

								//获取总页数
								String gqbgPagesNode = gqbgRoot.get("pagination").get("pages").toString();
								if(gqbgPagesNode != null) {
									int pages = Integer.parseInt(gqbgPagesNode);
									//获取所有分页数据(第二页开始)
									for (int i = 2 ; i <= pages; i++) {
										String gqbgNextPage = getPagination(gqbgxx_url,qygsxx_qynb_list_a_href,gqbgReportNoKey,xxReportNoValue,corpid,reportYear,i+""
												,"5",qygsxx_qynb_info_1_4_page);
										JsonNode gqbgNextRoot = mapper.readTree(gqbgNextPage);
										String gqbgNextDataList = gqbgNextRoot.get("pagination").get("dataList").toString();
										gqbgPaginations.add(gqbgNextDataList);
									}
								}
								qygsxx_qynb_info_map.put("qynb_info_gqbg_pages", gqbgPaginations);
								System.out.println("======gqbgPaginations====" + gqbgPaginations);


								qygsxx_ndbgxx_infos.add(qygsxx_qynb_info_map);
							}
						}
						resultHtmlMap.put("qygsxx_ndbgxx_infos", qygsxx_ndbgxx_infos);
					}

					//获取 企业公示信息->股东及出资信息 -> 股东及出资信息
					List<String> gdjczDataList = new ArrayList<>();
					String qygsxx_gdjczxx_gdjczxx_url = HOST_OF_ZHEJIANG + "/pubfunded/doReadPubFundedJSON.do?_id=doReadPubFunded" + new Date().getTime() + "&corpid=" + corpid + "&fundFlag=1&pagination.currentPage=1&pagination.pageSize=10"; 
					String qygsxx_gdjczxx_gdjczxx_referer = HOST_OF_ZHEJIANG + "/pubfunded/doReadPubFunded.do?pubFunded.corpid=" + corpid;

//  					WebRequest qygsxx_gdjczxx_gdjczxx_webRequest = new WebRequest(new URL(qygsxx_gdjczxx_gdjczxx_url), HttpMethod.POST);
//  					qygsxx_gdjczxx_gdjczxx_webRequest.setAdditionalHeader("Referer", qygsxx_gdjczxx_gdjczxx_referer);
//  					qygsxx_gdjczxx_gdjczxx_webRequest.setCharset("UTF-8");
//  					Page gsgsxx_gdjczxx_page = firstInfoPage.getWebClient().getPage(qygsxx_gdjczxx_gdjczxx_webRequest);
					String gdjczxxStr = getPagination(qygsxx_gdjczxx_gdjczxx_url, qygsxx_gdjczxx_gdjczxx_referer, firstInfoPage);
					JsonNode gdjczxxRoot = mapper.readTree(gdjczxxStr);
					String gdjczxxFistPageData = gdjczxxRoot.get("pagination").get("dataList").toString();
					gdjczDataList.add(gdjczxxFistPageData);
  					System.err.println("===qygsxx_gdjczxx_gdjczxx==="+ gdjczDataList);

					//获取总页数
					String gdjczxxPagesNode = gdjczxxRoot.get("pagination").get("pages").toString();
					if(gdjczxxPagesNode != null) {
						int pages = Integer.parseInt(gdjczxxPagesNode);
						//获取所有分页数据(第二页开始)
						for (int i = 2 ; i <= pages; i++) {
							String qygsxx_gdjczxx_gdjczxx_next_url = HOST_OF_ZHEJIANG + "/pubfunded/doReadPubFundedJSON.do?_id=doReadPubFunded" + new Date().getTime() + "&corpid=" + corpid + "&fundFlag=1&pagination.currentPage="+i+"&pagination.pageSize=10";
							String gdjczxxNextPage = getPagination(qygsxx_gdjczxx_gdjczxx_next_url,qygsxx_gdjczxx_gdjczxx_referer,firstInfoPage);
							JsonNode gdjczxxNextRoot = mapper.readTree(gdjczxxNextPage);
							String gdjczxxNextPageData = gdjczxxNextRoot.get("pagination").get("dataList").toString();
							gdjczDataList.add(gdjczxxNextPageData);
						}
					}
					resultHtmlMap.put("qygsxx_gdjczxx_gdjczxx", gdjczDataList);
					System.err.println("===gdjczxxPagesNode==="+gdjczxxPagesNode);

					//获取 企业公示信息 -> 股东及出资信息 -> 变更信息
					List<String> bgxxDataList = new ArrayList<>();
  					String qygsxx_gdjczxx_bgxx_url = HOST_OF_ZHEJIANG + "/pubreportmodhis/doReadPubReportModHisJSON.do?_id=doReadPubReportModHisJSON" + new Date().getTime() + "&modTable=PubFunded&dicColumn=FUND_FORM&pagination.currentPage=1&pagination.pageSize=5&corpid=" + corpid;
					String bgxxStr= getPagination(qygsxx_gdjczxx_bgxx_url, qygsxx_gdjczxx_gdjczxx_referer, firstInfoPage);
//					System.out.println("====bgxxStr====" + bgxxStr);
					JsonNode bgxxRoot = mapper.readTree(bgxxStr);
					String bgxxFistPageData = bgxxRoot.get("pagination").get("dataList").toString();
					bgxxDataList.add(bgxxFistPageData);
					//获取总页数
					String bgxxPagesNode = bgxxRoot.get("pagination").get("pages").toString();
					if(bgxxPagesNode != null) {
						int pages = Integer.parseInt(bgxxPagesNode);
						//获取所有分页数据(第二页开始)
						for (int i = 2 ; i <= pages; i++) {
							String qygsxx_gdjczxx_bgxx_next_url = HOST_OF_ZHEJIANG + "/pubfunded/doReadPubFundedJSON.do?_id=doReadPubFunded" + new Date().getTime() + "&corpid=" + corpid + "&fundFlag=1&pagination.currentPage="+i+"&pagination.pageSize=10";
							String bgxxNextPage = getPagination(qygsxx_gdjczxx_bgxx_next_url,qygsxx_gdjczxx_gdjczxx_referer,firstInfoPage);
							JsonNode bgxxNextRoot = mapper.readTree(bgxxNextPage);
							String bgxxNextPageData = bgxxNextRoot.get("pagination").get("dataList").toString();
							bgxxDataList.add(bgxxNextPageData);
						}
					}
					resultHtmlMap.put("qygsxx_gdjczxx_bgxx", bgxxDataList);
  					System.err.println("====qygsxx_gdjczxx_bgxx==="+bgxxDataList);

					//获取 企业公示信息->股权变更信息
					List<String> gqbgxxDataList = new ArrayList<>();
					String qygsxx_gqbgxx_referer = HOST_OF_ZHEJIANG + "/pubinstantstock/doReadPubStock.do?pubInstantStock.corpid=" + corpid;
					String qygsxx_gdbgxx_url = HOST_OF_ZHEJIANG + "/pubinstantstock/doReadPubStockJSON.do?_id=doReadPubLicense"+new Date().getTime() + "&corpid=" + corpid
							+ "&stockFlag=1&pagination.currentPage=1&pagination.pageSize=10";
					String gqbgxxStr= getPagination(qygsxx_gdbgxx_url, qygsxx_gqbgxx_referer, firstInfoPage);
//					System.out.println("====gqbgxxStr====" + gqbgxxStr);
					JsonNode gqbgxxRoot = mapper.readTree(gqbgxxStr);
					String gqbgxxFistPageData = gqbgxxRoot.get("pagination").get("dataList").toString();
					gqbgxxDataList.add(gqbgxxFistPageData);
					//获取总页数
					String gqbgxxPagesNode = gqbgxxRoot.get("pagination").get("pages").toString();
					if(gqbgxxPagesNode != null) {
						int pages = Integer.parseInt(gqbgxxPagesNode);
						//获取所有分页数据(第二页开始)
						for (int i = 2 ; i <= pages; i++) {
							String qygsxx_gdjczxx_gqbgxx_next_url = HOST_OF_ZHEJIANG + "/pubinstantstock/doReadPubStockJSON.do?_id=doReadPubLicense"+new Date().getTime() + "&corpid=" + corpid
									+ "&stockFlag=1&pagination.currentPage="+ i +"&pagination.pageSize=10";
							String gqbgxxNextPage = getPagination(qygsxx_gdjczxx_gqbgxx_next_url,qygsxx_gqbgxx_referer,firstInfoPage);
							JsonNode gqbgxxNextRoot = mapper.readTree(gqbgxxNextPage);
							String gqbgxxNextPageData = gqbgxxNextRoot.get("pagination").get("dataList").toString();
							gqbgxxDataList.add(gqbgxxNextPageData);
						}
					}
					resultHtmlMap.put("qygsxx_gdbgxx", gqbgxxDataList);
					System.err.println("====qygsxx_gdjczxx_gqbgxx==="+gqbgxxDataList);

					//获取 企业公示信息->行政许可信息
					List<String> xzxkxxDataList = new ArrayList<>();
					String qygsxx_xzxkxx_referer = HOST_OF_ZHEJIANG + "/publicense/doReadPubLicense.do?pubLicense.corpid=" + corpid;
					String qygsxx_xzxkxx_url = HOST_OF_ZHEJIANG + "/publicense/doReadPubLicenseJSON.do?_id=doReadPubLicense"+new Date().getTime()+ "&corpid=" + corpid
							+ "&licFlag=1&pagination.currentPage=1&pagination.pageSize=10";
					System.err.println(qygsxx_xzxkxx_url);
					String xzxkxxStr= getPagination(qygsxx_xzxkxx_url, qygsxx_xzxkxx_referer, firstInfoPage);
					System.out.println("====xzxkxxStr====" + xzxkxxStr);
					JsonNode xzxkxxRoot = mapper.readTree(xzxkxxStr);
					String xzxkxxFistPageData = xzxkxxRoot.get("pagination").get("dataList").toString();
					xzxkxxDataList.add(xzxkxxFistPageData);
					//获取总页数
					String xzxkxxPagesNode = xzxkxxRoot.get("pagination").get("pages").toString();
					if(xzxkxxPagesNode != null) {
						int pages = Integer.parseInt(xzxkxxPagesNode);
						//获取所有分页数据(第二页开始)
						for (int i = 2 ; i <= pages; i++) {
							String qygsxx_gdjczxx_xzxkxx_next_url = HOST_OF_ZHEJIANG + "/publicense/doReadPubLicenseJSON.do?_id=doReadPubLicense"+new Date().getTime()+ "&corpid=" + corpid
									+ "&licFlag=1&pagination.currentPage="+ i +"&pagination.pageSize=10";
							String xzxkxxNextPage = getPagination(qygsxx_gdjczxx_xzxkxx_next_url,qygsxx_xzxkxx_referer,firstInfoPage);
							JsonNode xzxkxxNextRoot = mapper.readTree(xzxkxxNextPage);
							String xzxkxxNextPageData = xzxkxxNextRoot.get("pagination").get("dataList").toString();
							xzxkxxDataList.add(xzxkxxNextPageData);
						}
					}
					resultHtmlMap.put("qygsxx_xzxkxx", xzxkxxDataList);
					System.err.println("====qygsxx_xzxkxx==="+xzxkxxDataList);

					//获取 企业公示信息->知识产权出质登记信息
					List<String> zscqczdjxxDataList = new ArrayList<>();
					String qygsxx_zscqczdjxx_referer = HOST_OF_ZHEJIANG + "/pubinstantintellectual/doReadPubInstantIntellectual.do?pubInstantIntellectual.corpid=" + corpid;
					String qygsxx_zscqczdjxx_url = HOST_OF_ZHEJIANG + "/pubinstantintellectual/doReadPubInstantIntellectualJSON.do?_id=doReadPubInstantintelNo"+new Date().getTime()
							+ "&corpid=" + corpid + "&intelFlag=1&pagination.currentPage=1&pagination.pageSize=5";
					String zscqczdjxxStr= getPagination(qygsxx_zscqczdjxx_url, qygsxx_zscqczdjxx_referer, firstInfoPage);
//					System.out.println("====zscqczdjxxStr====" + zscqczdjxxStr);
					JsonNode zscqczdjxxRoot = mapper.readTree(zscqczdjxxStr);
					String zscqczdjxxFistPageData = zscqczdjxxRoot.get("pagination").get("dataList").toString();
					zscqczdjxxDataList.add(zscqczdjxxFistPageData);
					//获取总页数
					String zscqczdjxxPagesNode = zscqczdjxxRoot.get("pagination").get("pages").toString();
					if(zscqczdjxxPagesNode != null) {
						int pages = Integer.parseInt(zscqczdjxxPagesNode);
						//获取所有分页数据(第二页开始)
						for (int i = 2 ; i <= pages; i++) {
							String qygsxx_gdjczxx_zscqczdjxx_next_url = HOST_OF_ZHEJIANG + "/pubinstantintellectual/doReadPubInstantIntellectualJSON.do?_id=doReadPubInstantintelNo"+new Date().getTime()
									+ "&corpid=" + corpid + "&intelFlag=1&pagination.currentPage="+ i +"&pagination.pageSize=5";
							String zscqczdjxxNextPage = getPagination(qygsxx_gdjczxx_zscqczdjxx_next_url,qygsxx_zscqczdjxx_referer,firstInfoPage);
							JsonNode zscqczdjxxNextRoot = mapper.readTree(zscqczdjxxNextPage);
							String zscqczdjxxNextPageData = zscqczdjxxNextRoot.get("pagination").get("dataList").toString();
							zscqczdjxxDataList.add(zscqczdjxxNextPageData);
						}
					}

					resultHtmlMap.put("qygsxx_zscqczdjxx", zscqczdjxxDataList);
					System.err.println("====qygsxx_zscqczdjxx==="+zscqczdjxxDataList);

					//获取 企业公示信息->行政处罚信息
					List<String> xzcfxxDataList = new ArrayList<>();
					String qygsxx_xzcfxx_referer = HOST_OF_ZHEJIANG + "/pubinstantpunish/doReadPubInstantPunish.do?pubInstantPunish.corpid=" + corpid;
					String qygsxx_xzcfxx_url =  HOST_OF_ZHEJIANG + "/pubinstantpunish/doReadPubInstantPunishJSON.do?_id=doReadPubInstantPunish"+new Date().getTime()
							+ "&corpid=" +corpid + "&punFlag=1&pagination.currentPage=1&pagination.pageSize=10";
					String xzcfxxStr= getPagination(qygsxx_xzcfxx_url, qygsxx_xzcfxx_referer, firstInfoPage);
//					System.out.println("====xzcfxxStr====" + xzcfxxStr);
					JsonNode xzcfxxRoot = mapper.readTree(xzcfxxStr);
					String xzcfxxFistPageData = xzcfxxRoot.get("pagination").get("dataList").toString();
					xzcfxxDataList.add(xzcfxxFistPageData);
					//获取总页数
					String xzcfxxPagesNode = xzcfxxRoot.get("pagination").get("pages").toString();
					if(xzcfxxPagesNode != null) {
						int pages = Integer.parseInt(xzcfxxPagesNode);
						//获取所有分页数据(第二页开始)
						for (int i = 2 ; i <= pages; i++) {
							String qygsxx_gdjczxx_xzcfxx_next_url = HOST_OF_ZHEJIANG + "/pubinstantpunish/doReadPubInstantPunishJSON.do?_id=doReadPubInstantPunish"+new Date().getTime()
									+ "&corpid=" +corpid + "&punFlag=1&pagination.currentPage="+ i +"&pagination.pageSize=10";
							String xzcfxxNextPage = getPagination(qygsxx_gdjczxx_xzcfxx_next_url,qygsxx_xzcfxx_referer,firstInfoPage);
							JsonNode xzcfxxNextRoot = mapper.readTree(xzcfxxNextPage);
							String xzcfxxNextPageData = xzcfxxNextRoot.get("pagination").get("dataList").toString();
							xzcfxxDataList.add(xzcfxxNextPageData);
						}
					}

					resultHtmlMap.put("qygsxx_xzcfxx", xzcfxxDataList);
					System.err.println("====qygsxx_xzcfxx==="+xzcfxxDataList);

					/*其他部门公示信息*/
					//获取 其他部门公示信息->行政许可及变更信息
					String otherdept_xzxkjbgxx_url =  HOST_OF_ZHEJIANG + "/pubotherlicence/readPubOtherLicenceInfo.do?corpid=" + corpid;
					HtmlPage otherdept_xzxkjbgxx_page = fistInfoWc.getPage(window, new WebRequest(new URL(otherdept_xzxkjbgxx_url)));
					resultHtmlMap.put("otherdept_xzxkjbgxx", otherdept_xzxkjbgxx_page.asXml());

					//获取 其他部门公示信息->行政处罚信息
					String otherdept_xzcfxx_url =  HOST_OF_ZHEJIANG + "/pubotherpunish/readPubOtherPunishInfo.do?corpid=" + corpid;
					HtmlPage otherdept_xzcfxx_page = fistInfoWc.getPage(window, new WebRequest(new URL(otherdept_xzcfxx_url)));
					resultHtmlMap.put("otherdept_xzcfxx", otherdept_xzcfxx_page.asXml());

					/*司法协助公示信息*/
					//获取 司法协助公示信息->股权冻结信息
					String sfxzgsxx_gqdjxx_url =  HOST_OF_ZHEJIANG + "/pubjusticeinfo/doReadFrozJusticeInfo.do?corpid="+corpid+"&justiceInfoType=1&justiceAuditResult=1";
					HtmlPage sfxzgsxx_gqdjxx_page = fistInfoWc.getPage(window, new WebRequest(new URL(sfxzgsxx_gqdjxx_url)));
					resultHtmlMap.put("sfxzgsxx_gqdjxx", sfxzgsxx_gqdjxx_page.asXml());

					//获取 司法协助公示信息->股东强制变更信息
					String sfxzgsxx_gdqzbgxx_url =  HOST_OF_ZHEJIANG + "/pubjusticeinfo/doReadThawJusticeInfo.do?corpid="+corpid+"&justiceInfoType=2&justiceAuditResult=1";
					HtmlPage sfxzgsxx_gdqzbgxx_page = fistInfoWc.getPage(window, new WebRequest(new URL(sfxzgsxx_gdqzbgxx_url)));
					resultHtmlMap.put("sfxzgsxx_gdqzbgxx", sfxzgsxx_gdqzbgxx_page.asXml());

					break;
				}
			}

			if (!matchFlag) {
				resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("查到了结果，但是搜索关键字和结果列表关键字不匹配！");
			}

		}
		fistInfoWc.getOptions().setJavaScriptEnabled(true);

		return resultHtmlMap;
	}



	public static void main(String[] args) throws Exception {
		//内蒙古 BLOCK 获取不到列表页 原因分析中
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("96668f85-ee7e-4ef2-a107-9a8b032502fd");
		webParam.addParam("area", "neimenggu");//
		webParam.setSearchPage("http://www.nmgs.gov.cn:7001/aiccips/");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='textfield']");
		webParam.addParam("imagecode", "六道轮回");
		webParam.addParam("imagecodeXpath", "//input[@id='code']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//内蒙古 searchWithOCR
		/*WebParam webParam = new WebParam();
		webParam.setSearchPage("http://www.nmgs.gov.cn:7001/aiccips/");
		webParam.setCodeImageId("//img[@id='vimg']");
		webParam.addParam("area", "neimenggu");//
		webParam.addParam("keyword", "伊利");	
		webParam.addParam("keywordXpath", "//input[@id='textfield']");
		webParam.addParam("imagecodeXpath", "//input[@id='code']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/
		
		//吉林 BLOCK 获取验证码等待时间过长  后台不断打印日志
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("96668f85-ee7e-4ef2-a107-9a8b032502fd");
		webParam.addParam("area", "jilin");//
		webParam.setSearchPage("http://211.141.74.198:8081/aiccips/");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='searchtxt']");
		webParam.addParam("imagecode", "六道轮回");
		webParam.addParam("imagecodeXpath", "//input[@id='yzminput']");
		webParam.addParam("loginBtnXpath", "//a[@id='searchbtn']");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//浙江 searchWithOCR
/*		WebParam webParam = new WebParam();
		webParam.setSearchPage("http://gsxt.zjaic.gov.cn/search/doGetAppSearchResult.do");
		webParam.setCodeImageId("//img[@id='kaptchaImg']");
		//webParam.setSerializedFileName("f5f9f252-76b8-42d1-b377-e3c00dd23835");
		//webParam.setSearchPage("http://gsxt.zjaic.gov.cn/zhejiang.jsp");
		webParam.addParam("area", "zhejiang");//
//		webParam.addParam("keyword", "杭州钢铁集团公司");
		webParam.addParam("keyword", "浙江东阳中兴墙纸有限公司");
		webParam.addParam("keywordXpath", "//input[@id='name']");
		//webParam.addParam("imagecode", "6");
		webParam.addParam("imagecodeXpath", "//input[@id='verifyCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().searchWithOCR(webParam);

		Gson gson = new Gson();
		Map<String, Object> resultHtmlMap = gson.fromJson(result, new TypeToken<Map<String, Object>>(){}.getType());
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		System.out.println("=======statusCodeDef: " + statusCodeDef);

		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = new GsxtZhejiangParser().zhejiangResultParser(result, true);
			System.out.println("gsxtFeedJson: "+gsxtFeedJson);
		}*/
		
		//浙江 search
		/*WebParam webParam = new WebParam();
		webParam.setSearchPage("http://gsxt.zjaic.gov.cn/search/doGetAppSearchResult.do");
		webParam.setCodeImageId("//img[@id='kaptchaImg']");
		webParam.setSerializedFileName("a354c94a-03b7-42fb-804e-df544b1a3db9");
		webParam.addParam("area", "zhejiang");//
		webParam.addParam("keyword", "杭州钢铁集团公司");	
		webParam.addParam("keywordXpath", "//input[@id='name']");
		webParam.addParam("imagecode", "4");
		webParam.addParam("imagecodeXpath", "//input[@id='verifyCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//江西  BLOCK  [原始网站错误  点击图片验证码下面的搜索按钮没有反应] 验证码在点击搜索按钮才能请求出现
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("7e60503c-1925-4a3d-8605-5b9d9220796a");
		webParam.addParam("area", "jiangxi");//
		webParam.setSearchPage("http://gsxt.jxaic.gov.cn/ECPS/");
		webParam.addParam("keyword", "江西松涛竹业有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='search']");
		webParam.addParam("imagecode", "7");
		webParam.addParam("imagecodeXpath", "//input[@id='yzmSearch']");
		webParam.addParam("loginBtnXpath", "//a[@id='butSearch']");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//广东  BLOCK TextPage cannot be cast to HtmlPage :93行 
/*		WebParam webParam = new WebParam();
		webParam.setSearchPage("http://gsxt.gdgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='vimg']");
		//webParam.setSerializedFileName("51ecd599-aab0-443a-9b38-73e706e7fb85");
		webParam.addParam("area", "guangdong");//
		//webParam.setSearchPage("http://gsxt.gdgs.gov.cn/");
		webParam.addParam("keyword", "广州市福恒纺织带有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='textfield']");
		//webParam.addParam("imagecode", "牛郎织女");
		webParam.addParam("imagecodeXpath", "//input[@id='code']");
		webParam.addParam("loginBtnXpath", "//a[@id='checkBtn']");
		
		String result = new GsxtFunction().searchWithOCR(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result6.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result6.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
*/
		//海南  BLOCK 未知原因
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("b45b09a6-6efa-4d3e-bac7-b57e651b81fc");
		webParam.addParam("area", "hainan");//
		webParam.setSearchPage("http://aic.hainan.gov.cn:1888/search.jspx");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", "swsc");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//重庆 BLOCK 只有点击  验证码只有点击搜索按钮才会请求得到
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("b7490205-e0fa-4ef1-8072-f3091c99974b");
		webParam.addParam("area", "chongqing");//
		webParam.setSearchPage("http://gsxt.cqgs.gov.cn/search.action");
		webParam.setCodeImageId("//img[@id='auth-code-img']");
		webParam.addParam("keyword", "宗申产业集团有限公司武术俱乐部");
		webParam.addParam("keywordXpath", "//input[@id='search-keyword']");
		webParam.addParam("imagecode", "9");
		webParam.addParam("imagecodeXpath", "//input[@id='auth-code']");
		webParam.addParam("loginBtnXpath", "//a[@id='search-button']");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);

		Gson gson = new GsonBuilder().create();
		Map<String, Object> resultHtmlMap = gson.fromJson(result, new TypeToken<Map<String, Object>>(){}.getType());

		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");

		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = new GsxtChongqingParser().chongqingResultParser(result, true);
			System.out.println(gsxtFeedJson);
		}*/

		//北京 OK DONE
//		WebParam webParam = new WebParam();
//		webParam.setSerializedFileName("3219ede3-fcf1-4afd-845c-93ba7bfa64f9");
//		webParam.addParam("area", "beijing");//
//		webParam.setSearchPage("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml");
//		//webParam.addParam("keyword", "北京金九鼎钢结构有限公司");
//		//webParam.addParam("keyword", "北京长源朗弘科技有限公司");
//		webParam.addParam("keyword", "北京华能系统控制工程有限公司");
//		webParam.addParam("keywordXpath", "//input[@id='keyword']");
//		webParam.addParam("imagecode", "65q8");
//		webParam.addParam("imagecodeXpath", "//input[@id='checkcodeAlt']");
//		webParam.addParam("loginBtnXpath", "//li[@class='denglu-an']/a[text()='搜索']");
//		String result = new GsxtFunction().searchWith(webParam);
//		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), result);
//		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
	
		//天津 OK 董亚鑫
		//http://localhost:8080/data/api/gsxt/tianjin/getSearchPage
/*		WebParam webParam = new WebParam();
		webParam.addParam("area", "tianjin");//
		webParam.setSearchPage("http://tjcredit.gov.cn/platform/saic/index.ftl");
		webParam.addParam("keyword", "天津大安土方工程有限公司（内资公司法人）");
		webParam.addParam("keywordXpath", "//input[@id='queryName']");
		webParam.addParam("imagecodeXpath", "//input[@id='vcode']");
		webParam.addParam("loginBtnXpath", "//input[@type='submit'][@value='查询']");
		webParam.setCodeImageId("//img[@id='vcodeimg']");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/

		//山西 OK 冯志勇
/*		WebParam webParam = new WebParam();
		webParam.setSerializedFileName("5609f255-c50c-48d9-8cc2-e752ae8828a6");
		webParam.addParam("area", "shanxi");//
		webParam.setSearchPage("http://218.26.1.108/search.jspx");
		webParam.addParam("keyword", "山西禾嘉禾商贸有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", "wycm");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
		*/
		//江苏 OK 罗俊平
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("4e28505e-60a5-463e-a74d-64c00a6ba1f0");
		webParam.addParam("area", "jiangsu");//
		webParam.setSearchPage("http://www.jsgsj.gov.cn:58888/province/");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='name']");
		webParam.addParam("imagecode", "n6mbnv");
		webParam.addParam("imagecodeXpath", "//input[@id='verifyCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//江苏
		/*WebParam webParam = new WebParam();
		webParam.addParam("area", "jiangsu");//
		webParam.setSearchPage("http://www.jsgsj.gov.cn:58888/province/");
		webParam.setCodeImageId("//span[@id='updateVerifyCode1']/img");
		webParam.addParam("keyword", "信和()");	
		webParam.addParam("keywordXpath", "//input[@id='name']");
		webParam.addParam("imagecodeXpath", "//input[@id='verifyCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/
		
		//安徽 OK 赵春香
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("dce3a835-17db-4209-99ba-32b96d913dc3");
		webParam.addParam("area", "anhui");//
		webParam.setSearchPage("http://www.ahcredit.gov.cn/search.jspx");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", "sbwd");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//安徽
		/*WebParam webParam = new WebParam();
		webParam.addParam("area", "anhui");//
		webParam.setSearchPage("http://www.ahcredit.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		webParam.addParam("keyword", "信和()");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/
		
		//山东  OK 余江海
		/**/
/*		WebParam webParam = new WebParam();
		webParam.setSerializedFileName("632c16d4-88a0-4fb3-a7ae-82a4b37948f7");
		webParam.addParam("area", "shandong");//
		webParam.setSearchPage("http://218.57.139.24/");
		webParam.addParam("keyword", "海尔集团公司");	
		webParam.addParam("keywordXpath", "//input[@id='searchtxt']");
		webParam.addParam("imagecode", "-1");
		webParam.addParam("imagecodeXpath", "//input[@id='yzminput']");
		webParam.addParam("loginBtnXpath", "//a[@id='searchbtn']");
		String result = new GsxtFunction().search(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), new GsonBuilder().create().fromJson(result, Map.class).toString());
		System.out.println(result);
		*/
		/*=================================================================*/
		//广西  OK 董亚鑫
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("631fc872-0c11-4479-be7c-f896b88a96d1");
		webParam.addParam("area", "guangxi");//
		webParam.setSearchPage("http://gxqyxygs.gov.cn/search.jspx");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", "jlsj");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//河南  OK 余江海
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("d32f5337-a580-40ab-ae1f-bb54024538d8");
		webParam.addParam("area", "henan");//
		webParam.setSearchPage("http://222.143.24.157/search.jspx");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", "bjgx");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//湖北 OK 冯志勇
//		WebParam webParam = new WebParam();
//		webParam.setSerializedFileName("c99b7094-0f12-484f-9a9c-79a605a6bc1b");
//		webParam.addParam("area", "hubei");//
//		webParam.setSearchPage("http://xyjg.egs.gov.cn/ECPS_HB/search.jspx");
//		webParam.addParam("keyword", "信和");	
//		webParam.addParam("keywordXpath", "//input[@id='entName']");
//		webParam.addParam("imagecode", "gzyz");
//		webParam.addParam("imagecodeXpath", "//input[@id='checkNo']");
//		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
//		String result = new GsxtFunction().search(webParam);
//		System.out.println(result);
		
		//四川  OK 罗俊平
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("22a9218b-65ba-493a-bbe5-1a9c42a230a7");
		webParam.addParam("area", "sichuan");//
		webParam.setSearchPage("http://gsxt.scaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime()); //1459248471141
		webParam.addParam("keyword", "信和");
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecode", "2");
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//四川
		/*WebParam webParam = new WebParam();
		webParam.addParam("area", "sichuan");//
		webParam.setSearchPage("http://gsxt.scaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime()); //1459248471141
		webParam.setCodeImageId("//img[@id='img']");
		webParam.addParam("keyword", "信和()");	
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/
		
		//西藏  OK 赵春香
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("d867d1f4-8490-46b7-b235-56cbaf67b24b");
		webParam.addParam("area", "xizang");//
		webParam.setSearchPage("http://gsxt.xzaic.gov.cn/search.jspx");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", "zlfk");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//西藏
		/*WebParam webParam = new WebParam();
		webParam.addParam("area", "xizang");//
		webParam.setSearchPage("http://gsxt.xzaic.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		webParam.addParam("keyword", "西藏赛康工贸(集团)有限公司");	 //包含非法字符
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/
		
		/*=========================================================================*/
		//青海  OK
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("22a637b7-a3a5-4f68-9c08-3c578c9f9fbd");
		webParam.addParam("area", "qinghai");//
		webParam.setSearchPage("http://218.95.241.36/search.jspx");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", "wyxt");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//青海
		/*WebParam webParam = new WebParam();
		webParam.addParam("area", "qinghai");//
		webParam.setCodeImageId("//img[@id='valCode']");
		webParam.setSearchPage("http://218.95.241.36/search.jspx");
		webParam.addParam("keyword", "信和()");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/
		
		//新疆  OK
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("b7249d84-d1f9-4bf4-aed2-ec72be54a27e");
		webParam.addParam("area", "xinjiang");//
		webParam.setSearchPage("http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=index&random="+new Date().getTime());
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecode", "16");
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//黑龙江OK
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("33fc8502-9af6-4283-99b0-b3b2f5aeb892");
		webParam.addParam("area", "heilongjiang");//
		webParam.setSearchPage("http://gsxt.hljaic.gov.cn/search.jspx");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", "zlzy");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//陕西  OK [原始网站  无法显示此网页]
/*		WebParam webParam = new WebParam();
		webParam.setSerializedFileName("c1999fec-a69f-4f9e-b5ee-74d77cb493c0");
		webParam.addParam("area", "shaanxi");//
		webParam.setSearchPage("http://xygs.snaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime()); //1459304328194
		webParam.addParam("keyword", "陕西延长石油（集团）有限责任公司");	
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecode", "3");
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
		*/
		//宁夏 OK 验证码只有点击搜索按钮之后才能请求得到
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("980b8e02-f395-4de8-a51f-e88bbbfdc6c7");
		webParam.addParam("area", "ningxia");//
		webParam.setSearchPage("http://gsxt.ngsh.gov.cn/ECPS/index.jsp");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='selectValue']");
		webParam.addParam("imagecode", "0");
		webParam.addParam("imagecodeXpath", "//input[@id='_password']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//宁夏
		/*WebParam webParam = new WebParam();
		webParam.addParam("area", "ningxia");//
		webParam.setSearchPage("http://gsxt.ngsh.gov.cn/ECPS/index.jsp");
		webParam.setCodeImageId("//img[@id='verificationCode1']");
		webParam.addParam("keyword", "信和<>");	
		webParam.addParam("keywordXpath", "//input[@id='selectValue']");
		webParam.addParam("imagecodeXpath", "//input[@id='_password']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/
		
		//辽宁 OK
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("8e0c4898-a582-405d-8334-86f1b7adf154");
		webParam.addParam("area", "liaoning");//
		webParam.setSearchPage("http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/entPublicity/search/searchmain.jsp");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='solrCondition']");
		webParam.addParam("imagecode", "5246");
		webParam.addParam("imagecodeXpath", "//input[@id='authCode-test']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[3]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//河北  OK 验证码只有点击搜索按钮才会请求得到，并且验证码所在dialog和search页面的关系是iframe-parent关系
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("65c67e34-987c-4272-874c-00d7ae087219");
		webParam.addParam("area", "hebei");//
		webParam.setSearchPage("http://www.hebscztxyxx.gov.cn/notice/");
		webParam.addParam("keyword", "长城汽车股份有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", "斑帽冬仙");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//上海 OK 类似于河北，验证码只有点击搜索按钮才会请求得到，并且验证码所在dialog和search页面的关系是iframe-parent关系
/*		WebParam webParam = new WebParam();
		webParam.setSerializedFileName("48179084-b45c-4f11-83fc-3dd61f90c8df");
		webParam.addParam("area", "shanghai");//
		webParam.setSearchPage("https://www.sgs.gov.cn/notice/home");
		webParam.addParam("keyword", "上海汽车集团股份有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", "五湖四海");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		String result = new GsxtFunction().search(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());

		*/
		//福建  OK 验证码只有点击搜索按钮才会请求得到，并且验证码所在dialog和search页面的关系是iframe-parent关系
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("f9d0e822-c1b8-4f18-a25b-10c11ff9815d");
		webParam.addParam("area", "fujian");//
		webParam.setSearchPage("http://wsgs.fjaic.gov.cn/creditpub/home");
		webParam.addParam("keyword", "厦门建发股份有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", "-8");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[4]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//湖南  OK 验证码只有点击搜索按钮才会请求得到，并且验证码所在dialog和search页面的关系是iframe-parent关系  [原始网站  Sorry, Page Not Found]
		/*
		 * 
		
		WebParam webParam = new WebParam();
		webParam.setSerializedFileName("fd6e9379-648e-4688-9712-634a9bdd38f7");
		webParam.addParam("area", "hunan");//
		webParam.setSearchPage("http://gsxt.hnaic.gov.cn/notice/");
		webParam.addParam("keyword", "九芝堂股份有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", "15");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		String result = new GsxtFunction().search(webParam);
		FileUtils.writeStringToFile(new File("C:\\Tcode\\gsxt\\result.json"), result);
		FileUtils.writeStringToFile(new File("C:\\Tcode\\gsxt\\myresult.json"), new GsonBuilder().create().fromJson(result, Map.class).toString());
		 */
		//云南  OK 验证码只有点击搜索按钮才会请求得到，并且验证码所在dialog和search页面的关系是iframe-parent关系
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("275d4925-9cfb-4fe4-9eab-2b35e5f118aa");
		webParam.addParam("area", "yunnan");//
		webParam.setSearchPage("http://gsxt.ynaic.gov.cn/notice/");
		webParam.addParam("keyword", "科技有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", "0");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//甘肃  OK
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("a45894de-4602-4781-9289-2c4d9f2cdbdf");
		webParam.addParam("area", "gansu");//
		webParam.setSearchPage("http://xygs.gsaic.gov.cn/gsxygs/main.jsp");
		webParam.addParam("keyword", "科技有限公司");	
		webParam.addParam("keywordXpath", "//input[@id='text_query']");
		webParam.addParam("imagecode", "0");
		webParam.addParam("imagecodeXpath", "//input[@id='text_code']");
		webParam.addParam("loginBtnXpath", "//a[@id='btn_search']");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//甘肃
		/*WebParam webParam = new WebParam();
		webParam.addParam("area", "gansu");//
		webParam.setSearchPage("http://xygs.gsaic.gov.cn/gsxygs/main.jsp");
		webParam.setCodeImageId("//img[@id='code_']");
		webParam.addParam("keyword", "科技有限公司<>");	
		webParam.addParam("keywordXpath", "//input[@id='text_query']");
		webParam.addParam("imagecodeXpath", "//input[@id='text_code']");
		webParam.addParam("loginBtnXpath", "//a[@id='btn_search']");
		String result = new GsxtFunction().searchWithOCR(webParam);
		System.out.println(result);*/
		
		//贵州  OK 返回数据为json串   只有点击  验证码只有点击搜索按钮才会请求得到
		/*WebParam webParam = new WebParam();
		webParam.setSerializedFileName("16ad8582-8b3d-4b01-b6e7-df635497d205");
		webParam.addParam("area", "guizhou");//
		webParam.setSearchPage("http://gsxt.gzgs.gov.cn/");
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='q']");
		webParam.addParam("imagecode", "13");
		webParam.addParam("imagecodeXpath", "//input[@id='validCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='codeWindow']/div/ul/li[4]/a/img");
		String result = new GsxtFunction().search(webParam);
		System.out.println(result);*/
		
		//上海   searchWithOCR
/*		WebParam webParam = new WebParam();
		
		webParam.setSearchPage("https://www.sgs.gov.cn/notice/home");
		webParam.setCodeImageId("//img[@id='cpt-img']");
		
		webParam.addParam("area", "shanghai");
		webParam.addParam("keyword", "上海尚星文化传播有限公司");
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecodeIframeSrc", "https://www.sgs.gov.cn/notice/search/popup_captcha");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		
		String result = new GsxtFunction().searchWithOCR(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
	*/
	
		//陕西  searchWithOCR
/*		WebParam webParam = new WebParam();
		
		webParam.setSearchPage("http://xygs.snaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime());
		webParam.setCodeImageId("//img[@id='img']");
		
		webParam.addParam("area", "shaanxi");
		webParam.addParam("keyword", "西安市户县后寨玻璃总厂");
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		String result = new GsxtFunction().searchWithOCR(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
	*/
		
		//山西  searchWithOCR
/*		WebParam webParam = new WebParam();
		
		webParam.setSearchPage("http://gsxt.fc12319.com/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		
		webParam.addParam("area", "shanxi");
		webParam.addParam("keyword", "山西省平遥牛肉集团有限公司");
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		String result = new GsxtFunction().searchWithOCR(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result1.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result1.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
	*/
		
		//北京  searchWithOCR
/*		WebParam webParam = new WebParam();
		webParam.setSearchPage("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!toIndex.dhtml");
		webParam.setCodeImageId("//img[@id='MzImgExpPwd']");
		webParam.addParam("area", "beijing");
		webParam.addParam("keyword", "北京金隅红树林环保()技术有限责任公司");
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkcodeAlt']");
		webParam.addParam("loginBtnXpath", "//li[@class='denglu-an']/a[text()='搜索']");
		
		String result = new GsxtFunction().searchWithOCR(webParam);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
		*/
		
//		WebParam webParam = new WebParam();
//		webParam.setSearchPage("http://218.57.139.24/");
//		webParam.setCodeImageId("//img[@id='secimg']");
//		
//		webParam.addParam("area", "shandong");
//		webParam.addParam("keyword", "青岛啤酒实业有限公司化工厂");
//		webParam.addParam("keywordXpath", "//input[@id='searchtxt']");
//		webParam.addParam("imagecodeXpath", "//input[@id='yzminput']");
//		webParam.addParam("loginBtnXpath", "//a[@id='searchbtn']");
//		
//		String result = new GsxtFunction().searchWithOCR(webParam);
//		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.json"), result);
//		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
		
//		WebParam webParam = new WebParam();
//		webParam.setSearchPage("http://gsxt.hnaic.gov.cn/notice/");
//		webParam.setCodeImageId("//img[@id='cpt-img']");
//		webParam.addParam("area", "hunan");
//		webParam.addParam("keyword", "长沙远大空调实业公司");
//        webParam.addParam("keywordXpath", "//input[@id='keyword']");
//		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
//		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
//		String result = new GsxtFunction().searchWithOCR(webParam);
		
//		
//		WebParam webParam = new WebParam();		
//		webParam.setSearchPage("http://218.95.241.36/search.jspx");
//		webParam.setCodeImageId("//img[@id='valCode']");	
//		webParam.addParam("area", "qinghai");//
//		webParam.addParam("keyword", "青海省小岛文化教育发展基地");	
//		webParam.addParam("keywordXpath", "//input[@id='entName']");
//		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
//		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");					
//		String result = new GsxtFunction().searchWithOCR(webParam);
//		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result_qh0713.json"), result);
//		
	
		//江西
//		WebParam webParam = new WebParam();	
//		webParam.addParam("area", "jiangxi");//
//		webParam.setSearchPage("http://gsxt.jxaic.gov.cn/ECPS/");
//		webParam.setCodeImageId("//img[@id='imgYzmSearch']");
//		webParam.addParam("keyword", "江中集团");	
//		webParam.addParam("keywordXpath", "//input[@id='search']");
//		webParam.addParam("imagecodeXpath", "//input[@id='yzmSearch']");
//		webParam.addParam("loginBtnXpath", "//a[@id='butSearch']");
//		String result = new GsxtFunction().searchWithOCR(webParam);
//		FileUtils.writeStringToFile(new File("C:\\TCode\\gsxt\\result_jx0713.json"), result);
		
	}
	
}

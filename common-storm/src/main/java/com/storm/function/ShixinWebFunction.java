package com.storm.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import org.apache.storm.guava.reflect.TypeToken;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

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
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.ocr.utils.OCRConnectUtils;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.WebParam;

public class ShixinWebFunction {
	
	//初始化验证码
	public String getSerializedAllIn(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(ShixinWebFunction.class, webParam.getLogback());
		
		LOGGER.info("==================ShixinWebFunction.getSerializedAllIn start!======================");
		SerializedAllIn serializedAllIn = new SerializedAllIn(); 
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
		LOGGER.info("The serializedAllIn.imageUrl is: "+serializedAllIn.getImageUrl());
		
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
		LOGGER.info("The serializedAllInFileName is: "+serializedAllInFileName);
		
		//返回 序列化文件名 和 验证码图片的URL
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("codeImageUrl", serializedAllIn.getImageUrl());
		resultMap.put("serializedFileName", serializedAllInFileName);
		
		LOGGER.returnRedisResource();
		
		return gson.toJson(resultMap);
	}
	
	public String search(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(ShixinWebFunction.class, webParam.getLogback());
		
		LOGGER.info("==================ShixinWebFunction.search start!======================");
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
//		LOGGER.info("searchPage.asXml()====="+searchPage.asXml());
		//更换获取新的验证码（官方网站30s更换一下）
		HtmlElement imgButton = (HtmlElement)searchPage.getFirstByXPath("//img[@id='captchaImg']"); 
		HtmlPage imgInfo =imgButton.click();
		Map<String, String> params = webParam.getParams();
		//解析新的验证码
		LOGGER.info("解析新的验证码");
		Map<String, String> resultMap =getImgCode(imgInfo, LOGGER);
		LOGGER.info("OCRConnectUtils.getVerifycode()自动识别");
    	String pCode2 = OCRConnectUtils.getVerifycode("/pbccrc/getVerifycode", resultMap.get("codeImageUrl"));
		LOGGER.info("第一次图片解析验证码："+params.get("pCode")+"第二次图片解析验证码："+pCode2);
		
		
		//请求参数		
		HtmlTextInput pName = (HtmlTextInput)(searchPage.getElementsByIdAndOrName("pName").get(0));//姓名
		HtmlTextInput pCardNum = (HtmlTextInput)(searchPage.getElementsByIdAndOrName("pCardNum").get(0));// 证件号
//		HtmlSelect pProvince = (HtmlSelect)(searchPage.getElementsByIdAndOrName("pProvince").get(0)); //省
		pName.setText(params.get("pName"));		
		pCardNum.setText(params.get("pCardNum"));
//	    pProvince.getOptionByText("----------------全部----------------").setSelected(true);
	    
		HtmlTextInput inputImagecode = (HtmlTextInput)(searchPage.getElementsByIdAndOrName("pCode").get(0)); 
		inputImagecode.setText(pCode2);
		
		//点击提交请求
		HtmlElement loginButton = (HtmlElement)searchPage.getFirstByXPath("//div[@class='login_button']"); 
		HtmlPage loginPage = loginButton.click();
//		LOGGER.info("loginPage.asXml()====="+loginPage.asXml());
		//获取iFrame内容
		FrameWindow window=loginPage.getFrameByName("contentFrame");
		HtmlPage loggedPage =(HtmlPage) window.getEnclosedPage();
//		LOGGER.info("loggedPage.asXml()====="+loggedPage.asXml());
		//提交form(post请求)
			WebRequest webRequest = new WebRequest(new URL("http://shixin.court.gov.cn/findd"), HttpMethod.POST);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new NameValuePair("pCode", pCode2));
			nameValuePairs.add(new NameValuePair("pName", params.get("pName")));
			nameValuePairs.add(new NameValuePair("pProvince", "0"));
			nameValuePairs.add(new NameValuePair("pCardNum", params.get("pCardNum")));
			webRequest.setRequestParameters(nameValuePairs);
			webRequest.setCharset("utf-8");
			HtmlPage listPage = loggedPage.getWebClient().getPage(webRequest);
		
			List<Object> resutList= new ArrayList<Object>();
			@SuppressWarnings("unchecked")
			List<HtmlElement> aList=(List<HtmlElement>)listPage.getByXPath("//a[@class='View']");
		    for(int i=0;i<aList.size();i++){
		    	HtmlElement ele =aList.get(i);
		    	String aid=ele.getAttribute("id");
		    	Page aPage = searchPage.getWebClient().getPage("http://shixin.court.gov.cn/findDetai?id="+aid+"&pCode="+pCode2);
		    	resutList.add(aPage.getWebResponse().getContentAsString("utf-8"));
		    }

		LOGGER.returnRedisResource();

		return new GsonBuilder().setPrettyPrinting().create().toJson(resutList);
	}
	
	public Element getElementByIndex(Element e,String selector,int index){
		Element childElement = null;
		Elements es = getElements(e,selector);
		if(es!=null&&es.size()>0){
			childElement = es.get(index);
		}
		return childElement;
	}

	private void addCookies(WebClient webClient, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			if (StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			webClient.getCookieManager().addCookie(cookie);
		}
	}
	
	public Elements getElements(Element e,String selector){
		Elements es = null;
		if(e!=null){
			es = e.select(selector);
		} 
		return es;
	}
	
	
	   //返回验证码信息
		public Map<String, String> getImgCode(HtmlPage searchPage, ChannelLogger LOGGER) throws Exception {
			LOGGER.info("==================ShixinWebFunction.getSerializedAllIn start!======================");
			SerializedAllIn serializedAllIn = new SerializedAllIn(); 
			WebClient webClient = WebCrawler.getInstance().getWebClient();
			WebResponse webResponse = searchPage.getWebResponse();
			HtmlImage image = searchPage.getFirstByXPath("//img[@id='captchaImg']");
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
			return resultMap;
		}
		
		
		public String searchDataOnce(WebParam webParam) throws Exception {
			//获取验证码信息
			ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(ShixinWebFunction.class, webParam.getLogback());
			
			LOGGER.info("==================ShixinWebFunction.searchDataOnce start!======================");
			SerializedAllIn serializedAllIn = new SerializedAllIn(); 
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
			LOGGER.info("The serializedAllIn.imageUrl is: "+serializedAllIn.getImageUrl());
			
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
			LOGGER.info("The serializedAllInFileName is: "+serializedAllInFileName);
			
			//返回 序列化文件名 和 验证码图片的URL
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("codeImageUrl", serializedAllIn.getImageUrl());
			resultMap.put("serializedFileName", serializedAllInFileName);
			//更换获取新的验证码（官方网站30s更换一下）
			HtmlElement imgButton = (HtmlElement)searchPage.getFirstByXPath("//img[@id='captchaImg']"); 
			HtmlPage imgInfo =imgButton.click();
			Map<String, String> params = webParam.getParams();
			//解析新的验证码
			LOGGER.info("解析新的验证码");
			Map<String, String> resultMap2 =getImgCode(imgInfo, LOGGER);
			LOGGER.info("OCRConnectUtils.getVerifycode()自动识别");
	    	String pCode2 = OCRConnectUtils.getVerifycode("/pbccrc/getVerifycode", resultMap2.get("codeImageUrl"));
			LOGGER.info("图片解析验证码："+pCode2);
			
			
			//请求参数		
			HtmlTextInput pName = (HtmlTextInput)(searchPage.getElementsByIdAndOrName("pName").get(0));//姓名
			HtmlTextInput pCardNum = (HtmlTextInput)(searchPage.getElementsByIdAndOrName("pCardNum").get(0));// 证件号
//			HtmlSelect pProvince = (HtmlSelect)(searchPage.getElementsByIdAndOrName("pProvince").get(0)); //省
			pName.setText(params.get("pName"));		
			pCardNum.setText(params.get("pCardNum"));
//		    pProvince.getOptionByText("----------------全部----------------").setSelected(true);
		    
			HtmlTextInput inputImagecode = (HtmlTextInput)(searchPage.getElementsByIdAndOrName("pCode").get(0)); 
			inputImagecode.setText(pCode2);
			
			//点击提交请求
			HtmlElement loginButton = (HtmlElement)searchPage.getFirstByXPath("//div[@class='login_button']"); 
			HtmlPage loginPage = loginButton.click();
//			LOGGER.info("loginPage.asXml()====="+loginPage.asXml());
			//获取iFrame内容
			FrameWindow window=loginPage.getFrameByName("contentFrame");
			HtmlPage loggedPage =(HtmlPage) window.getEnclosedPage();
//			LOGGER.info("loggedPage.asXml()====="+loggedPage.asXml());
			//提交form(post请求)
				WebRequest webRequest2 = new WebRequest(new URL("http://shixin.court.gov.cn/findd"), HttpMethod.POST);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new NameValuePair("pCode", pCode2));
				nameValuePairs.add(new NameValuePair("pName", params.get("pName")));
				nameValuePairs.add(new NameValuePair("pProvince", "0"));
				nameValuePairs.add(new NameValuePair("pCardNum", params.get("pCardNum")));
				webRequest2.setRequestParameters(nameValuePairs);
				webRequest2.setCharset("utf-8");
				HtmlPage listPage = loggedPage.getWebClient().getPage(webRequest2);
			
				List<Object> resutList= new ArrayList<Object>();
				@SuppressWarnings("unchecked")
				List<HtmlElement> aList=(List<HtmlElement>)listPage.getByXPath("//a[@class='View']");
			    for(int i=0;i<aList.size();i++){
			    	HtmlElement ele =aList.get(i);
			    	String aid=ele.getAttribute("id");
			    	Page aPage = searchPage.getWebClient().getPage("http://shixin.court.gov.cn/findDetai?id="+aid+"&pCode="+pCode2);
			    	resutList.add(aPage.getWebResponse().getContentAsString("utf-8"));
			    }

			LOGGER.returnRedisResource();

			return new GsonBuilder().setPrettyPrinting().create().toJson(resutList);
		}
		
		
}

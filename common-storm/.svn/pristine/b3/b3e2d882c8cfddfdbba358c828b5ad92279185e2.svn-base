package com.storm.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.SerializedAllIn;
import com.gargoylesoftware.htmlunit.CookieManager;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.ocr.utils.OCRConnectUtils;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.WebParam;


public class NacaoFunction {			

	/**/
//	public static void main(String[] args) throws Exception {
//		WebParam webParam = new WebParam();
//		webParam.setLogback("");
//		webParam.setSearchPage("http://www.nacao.org.cn/");
//		webParam.addParam("keyword", "信和");		
//		String str = new NacaoFunction().getSerializedAllIn(webParam);
//		System.out.println(str);
//	}
	
	
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
	public Map<String, Object> getSerializedAllIn(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(GsxtFunction.class, webParam.getLogback());		
		LOGGER.info("==================NacaoFunction.getSerializedAllIn start!======================");
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.addRequestHeader("Referer", "http://www.nacao.org.cn/");
		Gson gson = new GsonBuilder().create();
		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();		
		String searchPageUrl = webParam.getSearchPage();
		if (StringUtils.isEmpty(searchPageUrl)) {
			LOGGER.error("The searchPageUrl is not defined!");  
			return null;
		}				
		WebRequest webRequest = new WebRequest(new URL(searchPageUrl), HttpMethod.GET);
		HtmlPage searchPage = webClient.getPage(webRequest);
		String searchPageHtml = searchPage.asXml();
		LOGGER.info("searchPageHtml:"+ searchPageHtml);
		resultHtmlMap.put("firstpage", searchPageHtml);
		
		HtmlElement keywordElement = (HtmlElement)searchPage.getFirstByXPath("//input[@id='tit2']");	
//		//div[@id='invDiv']/table[@class='detailsList']/tbody/tr/td/a
		HtmlElement submitBtn = (HtmlElement)searchPage.getByXPath("//input[@type='image']").get(3);
		HtmlElement submitBtn3 = (HtmlElement)searchPage.getByXPath("//input[@type='image']").get(3);
		HtmlElement submitBtn2 = (HtmlElement)searchPage.getByXPath("//input[@type='image']").get(2);
		keywordElement.setAttribute("value", webParam.getParams().get("keyword"));
        HtmlPage page = submitBtn.click();
		LOGGER.info("======================================================");
		LOGGER.info(page.asXml());
		resultHtmlMap.put("querypage", page.asXml());
		resultHtmlMap.put("submitBtn", submitBtn.asXml());
		resultHtmlMap.put("submitBtn3", submitBtn3.asXml());
		resultHtmlMap.put("submitBtn2", submitBtn2.asXml());
//		
//		String frame_src = "https://s.nacao.org.cn/verifyYzmTest.jsp";
//		HtmlPage yzmPage = page.getWebClient().getPage(frame_src);
//		
//		//调用超级鹰识别验证码  https://s.nacao.org.cn/servlet/ValidateCode
//		//6003
//		String yzmSrc = "https://s.nacao.org.cn/servlet/ValidateCode";
//		String codevalue = OCRConnectUtils.getVerifycode("/cjy/getVerifycode", yzmSrc, "6003");
//				//getVerifycodeByCJY(yzmSrc, "6003");
//		LOGGER.info("codevalue:"+ codevalue);
//		LOGGER.info("yzmSrc:"+ yzmSrc);
//		
//		HtmlElement yzmInput = yzmPage.getFirstByXPath("//input[@id='validateCodeId']");
//		
//		yzmInput.setTextContent(codevalue);
//		
//		HtmlElement submitYzm = yzmPage.getFirstByXPath("//input[@name='sel']");
//		HtmlPage resultPage = submitYzm.click();
		
//		LOGGER.returnRedisResource();
		
		return resultHtmlMap;
	}

	
//	private void addCookies(WebClient webClient, Set<Cookie> cookies) {
//		for (Cookie cookie : cookies) {
//			if (StringUtils.isEmpty(cookie.getName())) {
//				continue;
//			}
//			webClient.getCookieManager().addCookie(cookie);
//		}
//	}
//	
//	
//	
//	public String getSerializedAllIn(WebParam webParam) throws Exception {
//		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(NacaoFunction.class, webParam.getLogback());
//		
//		LOGGER.info("==================NacaoFunction.getSerializedAllIn start!======================");
//		SerializedAllIn serializedAllIn = new SerializedAllIn(); //
//		WebClient webClient = WebCrawler.getInstance().getWebClient();
//		webClient.addRequestHeader("Referer", "http://www.nacao.org.cn/");
//		
//		Gson gson = new GsonBuilder().create();
//		
//		String searchPageUrl = webParam.getSearchPage();
//		if (StringUtils.isEmpty(searchPageUrl)) {
//			LOGGER.error("The searchPageUrl is not defined!");
//			return null;
//		}
//		WebRequest webRequest = new WebRequest(new URL(searchPageUrl), HttpMethod.GET);
//		HtmlPage searchPage = webClient.getPage(webRequest);
//		System.out.println(searchPage.asXml());
//		WebResponse webResponse = searchPage.getWebResponse();
//		//HtmlImage image = searchPage.getFirstByXPath(webParam.getCodeImageId());
////		if (image==null) {
////			LOGGER.error("The image element is not found!");
////			return null;
////		}
//		
//		File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
//		parentDirFile.setReadable(true); //
//		parentDirFile.setWritable(true); //
//		if (!parentDirFile.exists()) {
//			parentDirFile.mkdirs();
//		}
////		String imageName = UUID.randomUUID() + ".jpg";
////		File codeImageFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + imageName);
////		codeImageFile.setReadable(true); //
////		codeImageFile.setWritable(true); //
//		
////		LOGGER.info("The codeImageFile of GsxtFunction.getSerializedAllIn is:"+codeImageFile.getAbsolutePath());
////		image.saveAs(codeImageFile);
//		LOGGER.info("----codeImageFile saved!----");
//		
//		//imageUrl
////		serializedAllIn.setImageUrl("http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName);
////		LOGGER.info("The serializedAllIn.imageUrl is:"+serializedAllIn.getImageUrl());
//		
//		//cookies
//		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
//		serializedAllIn.setCookies(cookies);
//		
//		//webResponse
//		serializedAllIn.setWebResponse(webResponse);
//		
//		//序列化操作
//		String serializedAllInFileName = UUID.randomUUID().toString();
//		File serializedAllInFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName);
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedAllInFile));
//		oos.writeObject(serializedAllIn);
//		oos.close();
//		LOGGER.info("The serializedAllInFileName is:"+serializedAllInFileName);
//		
//		//返回 序列化文件名 和 验证码图片的URL
//		Map<String, String> resultMap = new HashMap<String, String>();
////		resultMap.put("codeImageUrl", serializedAllIn.getImageUrl());
//		resultMap.put("serializedFileName", serializedAllInFileName);
//		
//		LOGGER.returnRedisResource();
//		
//		return gson.toJson(resultMap);
//	}
	
	
	
	public String search(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(NacaoFunction.class, webParam.getLogback());		
		LOGGER.info("==================NacaoFunction.search start!======================");
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
		String keywordXpath1 = params.get("keywordXpath1");
		String tit2 = params.get("tit2"); //关键字
		HtmlTextInput inputKeyword1 = (HtmlTextInput)searchPage.getFirstByXPath(keywordXpath1); //querySelector("#keyword");
		inputKeyword1.setText(tit2);			
		//点击提交请求
		String queryBtnXpath = params.get("queryBtnXpath");
		HtmlElement queryButton = (HtmlElement)searchPage.getFirstByXPath(queryBtnXpath); //querySelector("input[onclick*=fn_search]");
		HtmlPage loggedPage = queryButton.click();		
		
		
		String frame_src = "https://s.nacao.org.cn/verifyYzmTest.jsp";
		HtmlPage tzrPage = loggedPage.getWebClient().getPage(frame_src);
		
//		FrameWindow window = loggedPage.getFrames().get(0);
//		HtmlPage iframePage =(HtmlPage) window.getEnclosedPage();
		
		LOGGER.returnRedisResource();
		
		return tzrPage.asXml();
	}

}


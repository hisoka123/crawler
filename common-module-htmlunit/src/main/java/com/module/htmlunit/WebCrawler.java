package com.module.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.gargoylesoftware.htmlunit.AlertHandler;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UserAgent;
import com.module.htmlunit.definition.UtilDefinition; 

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebCrawler {
	
	private static final Logger log = LoggerFactory.getLogger(WebCrawler.class);
	
	private static WebClient webClient = null; 
	
	private static WebCrawler instance;
	
	private static String alertMsg = "";
	
	public static final int DEFAULT_PAGE_TIME_OUT = 20000; //ms
	public static final int DEFAULT_JS_TIME_OUT = 5000;

	public synchronized static String getAlertMsg() {
		
		String msg = alertMsg;
		alertMsg = "";
		return msg;
		
	}
	
	public WebCrawler(){
		if(webClient==null){
			webClient = getWebClient();
		} 
	} 
	
	public static WebCrawler getInstance() {
		if (instance == null) {
			synchronized (WebCrawler.class) {
				if (instance == null) {
					instance = new WebCrawler();
				}
			}
		}
		return instance;
	}
	
	public WebClient getWebClient() {
		if (webClient == null) { 
			webClient = new WebClient(BrowserVersion.CHROME);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(DEFAULT_PAGE_TIME_OUT);   //15->60 
			webClient.waitForBackgroundJavaScript(DEFAULT_JS_TIME_OUT); //5s
			webClient.setAjaxController(new NicelyResynchronizingAjaxController()); 
			webClient.getOptions().setUseInsecureSSL(true); //
			//webClient.getJavaScriptEngine().getContextFactory().enterContext().setOptimizationLevel(9);
			webClient.setAlertHandler(new AlertHandler() {
				@Override
				public void handleAlert(Page page, String message) {
					alertMsg = message;
				}
			});
			webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {
				@Override
				public void scriptException(HtmlPage htmlPage,
						ScriptException scriptException) {
					log.info(htmlPage.getUrl().toString()+"---scriptException----"+scriptException.getMessage());
				}

				@Override
				public void timeoutError(HtmlPage htmlPage, long allowedTime,
						long executionTime) { 
					log.info(htmlPage.getUrl().toString()+"---timeoutError----"+allowedTime+"-----executionTime----"+executionTime);
				}

				@Override
				public void malformedScriptURL(HtmlPage htmlPage, String url,
						MalformedURLException malformedURLException) {
					log.info(htmlPage.getUrl().toString()+"---malformedScriptURL----"+url+"-----executionTime----"+malformedURLException.getMessage());
				}

				@Override
				public void loadScriptError(HtmlPage htmlPage, URL scriptUrl, Exception exception) {
					log.info(htmlPage.getUrl().toString()+"---loadScriptError----"+scriptUrl.toString()+"-----executionTime----"+exception.getMessage());
				}
			}); 
		}
		return webClient;
	}
	
	public synchronized WebPageResponse getPageInCookiesJsoup(String url,Map<String,String> cookies) throws IOException{
		
		Response response;
		WebPageResponse webPageResponse = null;
		  
		Connection conn =  getConnection(url);
		if(cookies!=null){
			conn.cookies(cookies);
		}
		response = conn.method(Method.GET).execute();
		 
		// successfully responsed
		webPageResponse = new WebPageResponse();
		
		String[] preDefinedCharset = new String[]{"utf-8", "big5-hkscs", "big5", "gbk", "gb2312"};;
		String defaultCharset = preDefinedCharset[0];
		Elements metaTags = Jsoup.parse(response.body()).select("meta[http-equiv=Content-Type],meta[charset]");
		if (metaTags.size() > 0) {
			Element meta = metaTags.get(0);
			for (String cs : preDefinedCharset) {
				if (meta.hasAttr("content") && meta.attr("content").toLowerCase().indexOf("charset="+cs) > 0 
						|| meta.hasAttr("charset") && meta.attr("charset").toLowerCase().indexOf(cs) > 0) {
					defaultCharset = cs;
					break;
				}
			} 
		}
		System.out.println("defaultCharset-----------"+defaultCharset);
		webPageResponse.setHtml(new String(response.bodyAsBytes(), defaultCharset));
		webPageResponse.setUnit(UtilDefinition.JSOUP);
		webPageResponse.setHttpStatusCode(response.statusCode());
		webPageResponse.setUrl(response.url().toString());
	 
		
		return webPageResponse;	
	}
	
	public synchronized WebPageResponse getPageInCookiesHtmlunit(String url,List<Cookie> cookies) throws Exception{
		log.info("getPageInlogin: {}", url);
		WebClient wc = getWebClient(); 
		wc = insertCookie(wc,cookies);
		log.info("=============  注入cookies   ============="); 
		WebPageResponse response = getPage(wc,url);
		return response;
	}
	
	public synchronized WebPageResponse getPage(WebClient webClient,String url) throws Exception{ 
		URL link = new URL(url);
		return getPage(webClient,link);
	}
	
	public WebClient insertCookie(WebClient webClient,List<Cookie> cookies){
		for(Cookie e :cookies){
			webClient.getCookieManager().addCookie(e);
		}
		return webClient;
	}
	
	public synchronized WebPageResponse getPage(WebClient webClient,URL url) throws Exception{ 
		log.info("Using Htmlunit to Send GET Request: {}", url); 
		WebPageResponse wr = new WebPageResponse(); 
		HtmlPage page = null;
		String result = null;
		WebResponse response = null; 
		try {  
			page = webClient.getPage(url); 
			response = page.getWebResponse();
			result = response.getContentAsString(); 
			int httpStatusCode = response.getStatusCode();
			wr.setHtml(result); 
			wr.setHttpStatusCode(httpStatusCode);
			wr.setUrl(page.getUrl()+"");
			wr.setCookies(webClient.getCookieManager().getCookies());
			wr.setUnit(UtilDefinition.HTMLUNIT);
		} catch (FailingHttpStatusCodeException e) { 
			e.printStackTrace();
		} catch (MalformedURLException e) { 
			e.printStackTrace();
		} finally{
			if(webClient!=null){
				//webClient.getCache().clear();
				//webClient.getCookieManager().clearCookies();
				webClient.closeAllWindows();  
			}  
		}   
		return wr;
	}
	 
	public synchronized WebPageResponse getPage(String url) throws Exception{  
		return getPage(getWebClient(),url);
		
	}
	
	public WebPageResponse getPageJsoup(String url) throws IOException {
		log.info("Using Jsoup to Send GET Request: {}", url); 
		return getPageInCookiesJsoup(url,null);
	}
	
	private Connection getConnection(String url) {
		return Jsoup.connect(url)
				.followRedirects(true)
				.ignoreContentType(false)
				.ignoreHttpErrors(false)
				.maxBodySize(2000000)
				.timeout(30000)
				.userAgent(UserAgent.getRandomUserAgent().toString());
	}

}

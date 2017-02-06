package test.http;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.AlertHandler;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class Shanxi {
	
	private static WebClient webClient = null;
	
	private static String url = "http://gsxt.fc12319.com/search.jspx";
	
	
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		
		URL gsUrl  = new URL(url); 
		
		if(webClient==null){
			webClient = getWebClient();
			webClient.getOptions().setUseInsecureSSL(true);
		}  
		
		//WebWindow currentWindow = webClient.getCurrentWindow();
		
		WebWindow window = null;
		try {
	        window = webClient.getWebWindowByName("shanxin"); 
	    } catch (Exception e) {
	        window = webClient.openWindow(gsUrl,"shanxin");
	    }  
		
		webClient.setWebConnection(new SimpleConectionListener(webClient));
		WebRequest  requestSettings = new WebRequest(gsUrl, HttpMethod.GET); 
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		/*requestSettings.getRequestParameters().add(new NameValuePair("Referer", "http://gsxt.fc12319.com/search.jspx?security_verify_data=313336362c373638"));
		requestSettings.getRequestParameters().add(new NameValuePair("Host", "gsxt.fc12319.com"));
		requestSettings.getRequestParameters().add(new NameValuePair("Upgrade-Insecure-Requests", "1"));
		 
		webClient.getCookieManager().addCookie(new Cookie("gsxt.fc12319.com", "JSESSIONID", "0000ZgZbmz0QyD3GKbvndzFT3nh:-1"));
		webClient.getCookieManager().addCookie(new Cookie("gsxt.fc12319.com", "__jsl_clearance", "1465017306.494|0|yWEiUUllHSWaGiAcVue%2BjRrXBvI%3D"));
		webClient.getCookieManager().addCookie(new Cookie("gsxt.fc12319.com", "__jsluid", "fd045aec9f9bac02c7996a6289dee8a2"));
		webClient.getCookieManager().addCookie(new Cookie("gsxt.fc12319.com", "gsxt_Cookie", "20111137"));
		webClient.getCookieManager().addCookie(new Cookie("gsxt.fc12319.com", "security_session_mid_verify", "1ffcb7b67f2c310219545b0113f4ad45"));
		webClient.getCookieManager().addCookie(new Cookie("gsxt.fc12319.com", "srcurl", "687474703a2f2f677378742e666331323331392e636f6d2f7365617263682e6a737078"));
		webClient.getCookieManager().addCookie(new Cookie("gsxt.fc12319.com", "yunsuo_session_verify", "e864d983ade62c2781f8c6468ed7cb80"));*/
		HtmlPage htmlPage = null;
		//htmlPage = webClient.getPage(url); 
		htmlPage = webClient.getPage(window, requestSettings);
		String result = htmlPage.getWebResponse().getContentAsString(); 
		System.out.println(result);
		System.out.println("----------------1111111-----------------");
		HtmlImage image = htmlPage.getFirstByXPath("//img[@id='valCode']");  
		File imageFile = new File("D:\\eclipse\\shanxin1112223333.jpg");
		image.saveAs(imageFile); 
		
		 
		
		
		
		
		
		/*
		WebRequest  requestSettings2 = new WebRequest(gsUrl, HttpMethod.GET); 
		HtmlPage htmlPage2 = null;
		htmlPage2 = webClient.getPage(window, requestSettings2);
		String result2 = htmlPage2.getWebResponse().getContentAsString(); 
		System.out.println(result2);
		System.out.println("----------------22222222222-----------------");
		Thread.sleep(2000);
		
		WebRequest  requestSettings3 = new WebRequest(gsUrl, HttpMethod.GET); 
		HtmlPage htmlPage3 = null;
		htmlPage3 = webClient.getPage(window, requestSettings3);
		String result3 = htmlPage3.getWebResponse().getContentAsString(); 
		System.out.println(result3);
		System.out.println("-------------------33333333333333--------------"); */
		
	}
	
	
	
	
	
	
	
	
	public static WebClient getWebClient() {
		if (webClient == null) { 
			webClient = new WebClient(BrowserVersion.CHROME);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(30000);  
			webClient.setAjaxController(new NicelyResynchronizingAjaxController()); 
			webClient.getJavaScriptEngine().getContextFactory().enterContext().setOptimizationLevel(9);
			webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {

				@Override
				public void scriptException(HtmlPage htmlPage,
						ScriptException scriptException) {
					// TODO Auto-generated method stub
					System.out.println(htmlPage.getUrl().toString()+"---scriptException----"+scriptException.getMessage());
					
				}

				@Override
				public void timeoutError(HtmlPage htmlPage, long allowedTime,
						long executionTime) { 
					System.out.println(htmlPage.getUrl().toString()+"---timeoutError----"+allowedTime+"-----executionTime----"+executionTime);
				}

				@Override
				public void malformedScriptURL(HtmlPage htmlPage, String url,
						MalformedURLException malformedURLException) {
					System.out.println(htmlPage.getUrl().toString()+"---malformedScriptURL----"+url+"-----executionTime----"+malformedURLException.getMessage());
				}

				@Override
				public void loadScriptError(HtmlPage htmlPage, URL scriptUrl, Exception exception) {
					System.out.println(htmlPage.getUrl().toString()+"---loadScriptError----"+scriptUrl.toString()+"-----executionTime----"+exception.getMessage());
				}
				
			}); 
			webClient.setIncorrectnessListener(new IncorrectnessListener() { 
			        @Override
			        public void notify(String arg0, Object arg1) {
			            System.out.println(arg0+"-----IncorrectnessListener-----"+arg1);
			        }
			}); 
			
			webClient.setAlertHandler(new AlertHandler(){

				@Override
				public void handleAlert(Page page, String message) { 
					 System.out.println(page.getUrl()+"-----setAlertHandler-----"+message);
					 
					 
					 System.out.println(page.getWebResponse().getContentAsString());
					 
					 
					 System.out.println("---------------handleAlert--------------------");
				}
				
			});
			
		}
		return webClient;
	}
}

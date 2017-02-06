package test.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.module.htmlunit.WebCrawler;

public class HttpTest {
	
	private static WebClient webClient = null;

	private static final Logger log = LoggerFactory.getLogger(HttpTest.class);

	public static void main(String[] args)  {
		
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",    "org.apache.commons.logging.impl.NoOpLog");
		
		//String url = "https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp";
		
		String url = "https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp";

		//String url = "http://m.weibo.cn/page/btn";
		//WebClient webClient = null;
		
		if(webClient==null){
			webClient = getWebClient();
			webClient.getOptions().setUseInsecureSSL(true);
		} 

		//Page page = null;
		HtmlPage htmlPage = null;
		try {
			htmlPage = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HtmlInput nameInput = htmlPage.getFirstByXPath("//input[@name='date']");
		String date = nameInput.getValueAttribute();
		
		HtmlImage image = htmlPage.getFirstByXPath("//img[@id='imgrc']");
		File imageFile = new File("D:\\eclipse\\imgrc.jpg");
		try {
			image.saveAs(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
		
		/*for(Cookie coo:cookies){
			System.out.println("---------"+coo.toString());
			
		}*/
		
		String result = htmlPage.getWebResponse().getContentAsString(); 

		//System.out.println(result);
		
		//login(cookies);
	}
	
	public static void login(Set<Cookie> cookies) throws IOException{
		System.out.print("verfiy_Code.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		/*
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		String loginname = br1.readLine(); 
		System.out.println("loginname: " + loginname);
		
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		String password = br2.readLine(); 
		System.out.println("password: " + password);
		*/
		
		System.out.print("date.[Enter]:");
		BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
		String date = br3.readLine(); 
		System.out.println("date: " + date);
		
		
		//WebClient webClient = null;
		
		//if(webClient==null){
			webClient = getWebClient();
			webClient.getCookieManager().clearCookies(); 
			for(Cookie co:cookies){
				webClient.getCookieManager().addCookie(co);
			}
			webClient.getOptions().setUseInsecureSSL(true);
			
			URL url  = new URL("https://ipcrs.pbccrc.org.cn/login.do");
			
			WebRequest  requestSettings = new WebRequest(url, HttpMethod.POST);

			// Then we set the request parameters
			requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
			requestSettings.getRequestParameters().add(new NameValuePair("method", "login"));
			requestSettings.getRequestParameters().add(new NameValuePair("_@IMGRC@_", code));
			requestSettings.getRequestParameters().add(new NameValuePair("date", date));
			requestSettings.getRequestParameters().add(new NameValuePair("loginname", "md19841002"));
			requestSettings.getRequestParameters().add(new NameValuePair("password", "md87315450"));
			 
			// Finally, we can get the page
			HtmlPage page = webClient.getPage(requestSettings);
			 
			String html = page.getWebResponse().getContentAsString(); 
			
			System.out.println("----------------------------------------------------------------------------");
			
			System.out.println(html);
			
			System.out.println("----------------------------------------------------------------------------");
			
			Set<Cookie> coos = webClient.getCookieManager().getCookies();
			for(Cookie coo:coos){
				System.out.println("=============="+coo.toString()); 
			}
		 
			System.out.println("----------------------------------------------------------------------------");
			WebRequest request = new WebRequest(new URL("https://ipcrs.pbccrc.org.cn/simpleReport.do?method=viewReport"), HttpMethod.POST);
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new NameValuePair("reportformat", "21"));
			nameValuePairs.add(new NameValuePair("tradeCode", "vfk849")); //身份验证码
			request.setRequestParameters(nameValuePairs);
			HtmlPage reportPage = webClient.getPage(request);
			
			System.out.println(reportPage.asXml());
			
			 
		//} 
		
		
		
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
			webClient.getOptions().setTimeout(10000);   
			webClient.setAjaxController(new NicelyResynchronizingAjaxController()); 
			
		}
		return webClient;
	}


}

package test.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set; 

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class RenTest2 {
	
	public static String loginurl = "https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp";
	
	
	public static void main(String[] args) throws Exception {
		WebClient webClient = getWebClient();
		HtmlPage htmlPage = null;
		htmlPage = webClient.getPage(loginurl); 
		
		List<NameValuePair> nameValuePairs =  htmlPage.getWebResponse().getResponseHeaders();
		System.out.println("--------------header----------------");
		for(NameValuePair nvp:nameValuePairs){
			System.out.println(nvp.toString());
		}
		System.out.println("--------------header----------------");
		String result = htmlPage.getWebResponse().getContentAsString();  
		System.out.println(result);
		
		HtmlImage image = htmlPage.getFirstByXPath("//img[@id='imgrc']");
		File imageFile = new File("D:\\eclipse\\imgrc.jpg");
		image.saveAs(imageFile);
		
		CookieManager cm = webClient.getCookieManager();
		Set<Cookie> cookies = cm.getCookies(); 
		for(Cookie coo:cookies){
			System.out.println("---------"+coo.toString());  
		}
		
		HtmlInput nameInput = htmlPage.getFirstByXPath("//input[@name='date']");
		String date = nameInput.getValueAttribute();
		System.out.println("date----------------"+date);
		
		System.out.print("verfiy_Code.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		
		WebClient wc = getWebClient();
		
		wc.setCookieManager(cm);
		
 
	 
	 
		
		URL url  = new URL("https://ipcrs.pbccrc.org.cn/login.do");
		
		WebRequest  requestSettings = new WebRequest(url, HttpMethod.POST); 
		// Then we set the request parameters
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("method", "login"));
		requestSettings.getRequestParameters().add(new NameValuePair("_@IMGRC@_", code));
		requestSettings.getRequestParameters().add(new NameValuePair("date", date));
		requestSettings.getRequestParameters().add(new NameValuePair("loginname", "md19841002"));
		requestSettings.getRequestParameters().add(new NameValuePair("password", "md87315450"));
		wc.addRequestHeader("Origin", "https://ipcrs.pbccrc.org.cn");
		wc.addRequestHeader("Referer", "https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp");
		// Finally, we can get the page
		
		
		HtmlPage page = wc.getPage(requestSettings); 
		String html = page.getWebResponse().getContentAsString();  
		System.out.println("----------------------------------------------------------------------------"); 
		System.out.println(html);
		
		List<NameValuePair> namePairs =  page.getWebResponse().getResponseHeaders();
		System.out.println("--------------header----------------");
		for(NameValuePair nvp:namePairs){
			System.out.println(nvp.toString());
		}
		System.out.println("--------------header----------------");
	}
	
	
	public static WebClient getWebClient() { 
		WebClient webClient = new WebClient(BrowserVersion.CHROME); 
		webClient.getCookieManager().clearCookies();
		webClient.setRefreshHandler(new ThreadedRefreshHandler());
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setTimeout(10000);   
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());  
		webClient.addRequestHeader("Host", "ipcrs.pbccrc.org.cn");
		webClient.addRequestHeader("Upgrade-Insecure-Requests", "1");
		return webClient;
	}

}

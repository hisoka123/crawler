package test.http;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class CookieTest {

	private static WebClient webClient = null;
	
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "http://www.linkedin.com/profile/view?id=14937505&authToken=652W&authType=name&trk=prof-sb-browse_map-nam";
 
		
		if(webClient==null){
			webClient = getWebClient();
		} 
		//添加cookie
		Cookie cookie = new Cookie("www.linkedin.com","name","value");
		webClient.getCookieManager().addCookie(cookie);

		Page page = null;

		page = webClient.getPage(url);
		
		String result = page.getWebResponse().getContentAsString(); 

		System.out.println(result);

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

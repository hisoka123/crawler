package weibo;

import java.util.Set;

import com.crawler.weibo.login.WeiboLogin;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.htmlunit.WebCrawler;

public class LoginTest {

	public static void main(String[] args) throws Exception {
		WebClient wc = WebCrawler.getInstance().getWebClient();
		wc = WeiboLogin.getInstance().userLogin(wc);
		CookieManager cm = wc.getCookieManager();
		Set<Cookie> cookies = cm.getCookies();
		for(Cookie cookie:cookies){ 
			System.out.println("---------cookie----------"); 
			System.out.println(cookie.toString()); 
			System.out.println("Domain:"+cookie.getDomain()); 
			System.out.println("Name:"+cookie.getName());
			System.out.println("Path:"+cookie.getPath());
			System.out.println("Value:"+cookie.getValue());
			System.out.println("Expires:"+cookie.getExpires()); 
			System.out.println("=========================");  
		}
				
				
				
				
	}

}

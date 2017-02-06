package zhihu;

import java.net.URL;

import com.crawler.zhihu.login.ZhihuLogin;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;

public class LoginTest {
	
	public static void main(String[] args) throws Exception {
		String url = "http://www.zhihu.com"; 
		String domain = "www.zhihu.com";
		WebClient wc = WebCrawler.getInstance().getWebClient();   
		System.out.println("=============================LoginTest================================="); 
		wc.getCookieManager().addCookie(new Cookie(domain,"__utma", "51854390.1008270953.1437459343.1437640605.1437643930.6"));
		wc.getCookieManager().addCookie(new Cookie(domain,"__utmb", "51854390.4.10.1437643930"));
		wc.getCookieManager().addCookie(new Cookie(domain,"__utmc", "51854390"));
		wc.getCookieManager().addCookie(new Cookie(domain,"__utmt", "1"));
		wc.getCookieManager().addCookie(new Cookie(domain,"__utmv", "51854390.100-1|2=registration_date=20140709=1^3=entry_date=20140709=1"));
		wc.getCookieManager().addCookie(new Cookie(domain,"__utmz", "51854390.1437643930.6.5.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/"));
		wc.getCookieManager().addCookie(new Cookie(domain,"_ga", "GA1.2.1008270953.1437459343"));
		wc.getCookieManager().addCookie(new Cookie(domain,"_gat", "1"));
		wc.getCookieManager().addCookie(new Cookie(domain,"_xsrf", "b1e086c4d6bbe05359c0951852138433"));
		wc.getCookieManager().addCookie(new Cookie(domain,"_za", "79924057-2c4b-449e-9d7d-05ff663411b7"));
		wc.getCookieManager().addCookie(new Cookie(domain,"actioncontinuationhash", ""));
		wc.getCookieManager().addCookie(new Cookie(domain,"auth_type", "\"c2luYQ==|1437462578|c62b4adcb7ed2281bf8449e730f721c5a3fb9877\""));
		wc.getCookieManager().addCookie(new Cookie(domain,"client_id", "\"NTAzNTE0MjEyOA==|1437462578|7ced8303d983ab31465347b6edfa13325a37ee00\""));
		wc.getCookieManager().addCookie(new Cookie(domain,"token", "\"Mi4wMG9Ud2tVRkVBNzIyRGEyMzI0M2RlZWZFVjNtVkI=|1437462578|1ffa927eb534e0b53b7e3c138afd562516145de2\""));
		wc.getCookieManager().addCookie(new Cookie(domain,"z_c0", "\"QUFCQW1Kb3lBQUFYQUFBQVlRSlZUYUU0MkZYQXFOOFFlVW1kNGxVbmdSMzVzSGNBYTVpajhBPT0=|1437641633|ce5e9a3c25a6142a58bc768a13314df365fc880a\""));
		//WebClient wcl = ZhihuLogin.getInstance().userLogin(wc);  
		   
		WebPageResponse page = ZhihuLogin.getInstance().getPage(wc, url); 
		System.out.println("---------------------------LoginTest----------------------------");
		System.out.println(page.getHtml());  
	}

}

package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod; 
import org.apache.http.impl.client.BasicCookieStore; 
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.htmlunit.definition.UserAgent;
import com.module.redis.app.App;
import com.module.redis.domain.CookieJson;
import com.storm.persistence.RedisService;

public class CookieTest {

	private static WebClient webClient = null;

	public static void main(String[] args) throws Exception {
		 htmlunit();
		// jsoup();
	}

	 
	public static void jsoup() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		List<Cookie> cookies = RedisService.getInstance().getAllCookies();
		Map<String, String> loginCookies = new HashMap();
		for (Cookie e : cookies) {
			loginCookies.put(e.getName(), e.getValue());
		}
		System.out.println(loginCookies);
		String url = "http://weibo.com/p/1005051645101450/follow";
		Response response = getConnection(url).cookies(loginCookies)
				.method(Method.GET).execute();
		System.out.println(response.body());
	}

	public static void htmlunit() throws  Exception {

		Set<String> domains = new HashSet<String>();
		domains.add("weibo.com");
		domains.add(".weibo.com");
		domains.add("s.weibo.com");
		domains.add(".passport.weibo.com");
		domains.add(".open.weibo.com"); 
		domains.add("open.weibo.com");
		List<CookieJson> cookieset = RedisService.getInstance().getCookiesByDomainS(domains);
		List<Cookie> cookies = RedisService.getInstance().convert2HtmlunitCookie(cookieset);
		System.out.println(cookies);
		//String url = "http://weibo.com/p/1005051645101450/follow";
		String url = "http://s.weibo.com/user/36氪";
		if (webClient == null) {
			webClient = getWebClient();
		}

		webClient = insertCookie(webClient, cookies);

		Page page = null;

		page = webClient.getPage(url);

		String result = page.getWebResponse().getContentAsString();

		System.out.println(result);

	}

	public static WebClient insertCookie(WebClient webClient,
			List<Cookie> cookies) {
		for (Cookie e : cookies) {
			webClient.getCookieManager().addCookie(e);
		}
		return webClient;
	}

	public static WebClient getWebClient() {
		if (webClient == null) {

			webClient = new WebClient(BrowserVersion.CHROME);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(10000);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());

		}
		return webClient;
	}

	private static Connection getConnection(String url) {
		return Jsoup.connect(url).followRedirects(true)
				.ignoreContentType(false).ignoreHttpErrors(false)
				.maxBodySize(2000000).timeout(30000)
				.userAgent(UserAgent.getRandomUserAgent().toString());
	}

}

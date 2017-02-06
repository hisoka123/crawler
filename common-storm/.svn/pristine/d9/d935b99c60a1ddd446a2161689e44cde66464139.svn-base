package test;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.redis.domain.CookieJson;
import com.storm.persistence.RedisService;

public class JsoupCookiesTest {

	private static HashMap<String, HashMap<String, String>> host2cookies = new HashMap<String, HashMap<String, String>>();
	
	public static void main(String[] args) throws Exception {
		String urStr = "http://weibo.com/p/1005051645101450/follow"; 
		
		List<CookieJson> cjs = RedisService.getInstance().getCookiesByDomain(".weibo.com");
		 
		Connection con = Jsoup.connect(urStr).timeout(600000);
		for(CookieJson cj:cjs){
			con.cookie(cj.getName(), cj.getValue());
		}
		 
		/*con.cookie("ALF", "1470301054");
		con.cookie("Apache", "2694283544551.581.1438740638238");
		con.cookie("SINAGLOBAL", "1009110077284.2765.1423622933475");
		con.cookie("SSOLoginState", "1438765055");
		con.cookie("SUB", "_2A254xb-vDeTxGeNO6FcQ9CzNyTSIHXVbspZnrDV8PUNbuNAPLUf2kW-MYsT_D4UYov-7nXcGo6V49vDxZA..");
		con.cookie("SUBP", "0033WrSXqPxfM725Ws9jqgMF55529P9D9WWzQlkm1FyL.vQuM_YNhS_D5JpX5K2t");
		con.cookie("SUE", "es%3D7f7ca90f40965dda79b4aef72ad276ad%26ev%3Dv1%26es2%3Dba947ee842990c5b1a359494c3a6dd2e%26rs0%3DNMHHXFCeqbZgyaOJFCCO5WVh4jmfjk14JCwpFcZVgJHdj%252FBag2jFw%252FeWaAb18IwnGW1DeRLg0g3%252Fql5BU5h06omTYfoCO91WwTzTiCti8Vx7QZ9CTULeiSVv0T%252FBbM4n%252FZHVAe%252Fqwg8TmGLLjE849iZ7FBb2PEmqUqCnH5Ummjo%253D%26rv%3D0");
		con.cookie("SUHB", "0idbBBbBJ7-Fix");
		con.cookie("SUP", "cv%3D1%26bt%3D1438765055%26et%3D1438851455%26d%3Dc909%26i%3Dfe8e%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D0%26st%3D0%26uid%3D5035142128%26name%3D13520800817%26nick%3Dvcspark13520800817%26fmp%3D%26lcp%3D");
		con.cookie("SUS", "SID-5035142128-1438765055-XD-i835k-a9eaa6adc4148d88975081089b42fe8e");
		con.cookie("TC-Page-G0", "2b304d86df6cbca200a4b69b18c732c4");
		con.cookie("TC-Ugrow-G0", "370f21725a3b0b57d0baaf8dd6f16a18");
		con.cookie("TC-V5-G0", "05e7a45f4d2b9f5b065c2bea790496e2");
		con.cookie("ULV", "1438740638244:68:2:2:2694283544551.581.1438740638238:1438567322952");
		con.cookie("UOR", "www.csdn.net,widget.weibo.com,www.infzm.com");
		con.cookie("_s_tentry", "login.sina.com.cn");
		con.cookie("login_sid_t", "52f215ca298fd6e8c8c55902f98ab715");
		con.cookie("myuid", "5035142128");
		con.cookie("un", "13520800817");
		con.cookie("wvr", "6");*/
		
		Document doc = con.get();
		  
		System.out.println(doc.html());
	}
 

	public static String DownloadPage(URL url) throws Exception
	{
	    Connection con = Jsoup.connect(url.toString()).timeout(600000);
	    loadCookiesByHost(url, con);


	    Document doc = con.get();
	    url = con.request().url();

	    storeCookiesByHost(url, con);

	    return doc.html();
	}

	private static void loadCookiesByHost(URL url, Connection con) {
	    try {
	        String host = url.getHost();
	        if (host2cookies.containsKey(host)) {
	            HashMap<String, String> cookies = host2cookies.get(host);
	            for (Entry<String, String> cookie : cookies.entrySet()) {
	                con.cookie(cookie.getKey(), cookie.getValue());
	            }
	        }
	    } catch (Throwable t) {
	        // MTMT move to log
	        System.err.println(t.toString()+":: Error loading cookies to: " + url);
	    }
	}

	private static void storeCookiesByHost(URL url, Connection con) {
	        try {
	            String host = url.getHost();
	            HashMap<String, String> cookies = host2cookies.get(host);
	            if (cookies == null) {
	                cookies = new HashMap<String, String>();
				host2cookies.put(host, cookies);
	            }
	            cookies.putAll(con.response().cookies());
	        } catch (Throwable t) {
	            // MTMT move to log
	            System.err.println(t.toString()+":: Error saving cookies from: " + url);
	        }    
	}   
}

package test.http;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;
import com.module.htmlunit.unit.BaseUnit;

public class WeixinTest {
	
	private static String baseurl = "http://weixin.sogou.com/weixin?type=2&fr=sgsearch&ie=utf8&query=";
	
	
	public static void main(String[] args) throws Exception {
		 
		while(true){ 
			doCrawlerhtmlunit("学习");
			Thread.sleep(5000);
		}
		
	}
	
	public static void doCrawlerhtmlunit(String kw) throws Exception{ 
		String url = baseurl + BaseUnit.encode(kw, "utf8");
		System.out.println("url======"+url);
		WebPageResponse webPageResponse  = WebCrawler.getInstance().getPage(url);
		String html = webPageResponse.getHtml();
		Elements eles = Jsoup.parseBodyFragment(html).select("div.results");
		int code = webPageResponse.getHttpStatusCode();
		System.out.println("code--------------"+code);
		//html = html;
		html = eles.html();
		System.out.println(html);
	}
	
	
	public static void doCrawler(String kw) throws IOException{ 
		String url = baseurl + BaseUnit.encode(kw, "utf8");
		System.out.println("url======"+url);
		WebPageResponse webPageResponse  = WebCrawler.getInstance().getPageJsoup(url);
		String html = webPageResponse.getHtml();
		//Elements eles = Jsoup.parseBodyFragment(html).select("div.results");
		int code = webPageResponse.getHttpStatusCode();
		System.out.println("code--------------"+code);
		html = html;
		System.out.println(html);
	}

}

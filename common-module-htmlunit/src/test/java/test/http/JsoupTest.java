package test.http;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;

public class JsoupTest { 
	 
	public static void main(String[] args) throws IOException {
		String url = "http://gsxt.zjaic.gov.cn/js/widget/jquery/jquery-1.6.4.min.js";
		 
		WebPageResponse re = WebCrawler.getInstance().getPageJsoup(url);
		System.out.println(re.getHtml());
		
		System.out.println(re.getUrl());
		
	}

}

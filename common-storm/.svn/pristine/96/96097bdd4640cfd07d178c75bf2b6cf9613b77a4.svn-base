package test;

import java.net.URL;
import java.util.Arrays;

import com.crawler.weibo.handle.WeiboUserAction;
import com.crawler.weibo.login.WeiboLogin;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.module.htmlunit.WebCrawler;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class WeiboFollower {
	
	private static String uid="1195242865";//杨幂
	
	public static void main(String[] args) throws Exception {
		//follow(args);
		//delAttention(args);
		
		WeiboUserAction wa = new WeiboUserAction();
		
		//wa.attention(uid);
		wa.delAttention(uid);
	}
	
	public static void follow(String[] args) throws Exception {
		
		//String url = "http://m.weibo.cn/main/pages/index?containerid=100103type%3D3%26q%3D360&type=user&queryVal=360";
		 
		String url = "http://m.weibo.cn/searchs";
		
		WebClient  wc = WebCrawler.getInstance().getWebClient(); 
		
		WeiboLogin.getInstance().userLogin(wc);
		
		HtmlPage page = wc.getPage(url);
		
		System.out.println(page.asXml());
		
		WebRequest request = new WebRequest(new URL("http://m.weibo.cn/page/btn?module=follow&uid="+uid),HttpMethod.POST);
		 
        request.setRequestParameters(Arrays.asList( 
                new NameValuePair("sub_type", "0")));
        
        request.setAdditionalHeader("Origin", "http://m.weibo.cn");
        request.setAdditionalHeader("Referer", url);
        request.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
        request.setAdditionalHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        request.setAdditionalHeader("Accept-Encoding", "gzip, deflate");
        request.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        request.setAdditionalHeader("Connection", "keep-alive"); 
        request.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); 
        request.setAdditionalHeader("Host", "m.weibo.cn"); 
        
        Page p = wc.getPage(request);
        System.out.println("------------------------");
        System.out.println(p.getWebResponse().getContentAsString());
		
	}
	
	
	public static void delAttention(String[] args) throws Exception {
		  
		String url = "http://m.weibo.cn/u/"+uid;
		
		WebClient  wc = WebCrawler.getInstance().getWebClient(); 
		
		WeiboLogin.getInstance().userLogin(wc);
		
		HtmlPage page = wc.getPage(url);
		
		System.out.println(page.asXml());
		
		WebRequest request = new WebRequest(new URL("http://m.weibo.cn/attentionDeal/delAttention"),HttpMethod.POST);
		 
        request.setRequestParameters(Arrays.asList( 
                new NameValuePair("uid", uid)));
        
        request.setAdditionalHeader("Origin", "http://m.weibo.cn");
        request.setAdditionalHeader("Referer", url);
        request.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
        request.setAdditionalHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        request.setAdditionalHeader("Accept-Encoding", "gzip, deflate");
        request.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        request.setAdditionalHeader("Connection", "keep-alive"); 
        request.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); 
        request.setAdditionalHeader("Host", "m.weibo.cn"); 
        
        Page p = wc.getPage(request);
        System.out.println("------------------------");
        System.out.println(p.getWebResponse().getContentAsString());
		 
		 
		
		
		
	}

}

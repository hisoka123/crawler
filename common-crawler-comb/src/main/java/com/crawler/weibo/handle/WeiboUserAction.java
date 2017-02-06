package com.crawler.weibo.handle;

import java.net.URL;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawler.weibo.login.WeiboLogin;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.module.domain.WebPageResponse;

public class WeiboUserAction {
	
	private static final Logger log = LoggerFactory.getLogger(WeiboUserAction.class);
	
	private static final String checkPage = "http://m.weibo.cn";
	  
	public String attention(String uid) throws Exception{ 
		String result = null; 
		String attention_url = "http://m.weibo.cn/page/btn?module=follow&uid="+uid; 
		WeiboLogin.getInstance().getPageInlogin(checkPage,true);
		WebRequest request = new WebRequest(new URL(attention_url),HttpMethod.POST);
		request = initRequest(request);
		request.setRequestParameters(Arrays.asList(new NameValuePair("sub_type", "0")));
		request.setAdditionalHeader("Referer", checkPage);
		Page p =  WeiboLogin.getInstance().getPageInlogin(request); 
		result = p.getWebResponse().getContentAsString();
		log.info("attention result:"+result); 
		return result;
	}
	
	public String delAttention(String uid) throws Exception{ 
		String result = null;  
		WeiboLogin.getInstance().getPageInlogin(checkPage,true); 
		WebRequest request = new WebRequest(new URL("http://m.weibo.cn/attentionDeal/delAttention"),HttpMethod.POST);
		request = initRequest(request);
        request.setRequestParameters(Arrays.asList(new NameValuePair("uid", uid)));
        request.setAdditionalHeader("Referer", checkPage); 
        Page p = WeiboLogin.getInstance().getPageInlogin(request); 
        result = p.getWebResponse().getContentAsString();
		log.info("delAttention result:"+result);  
		return result;
	}
	
	
	public WebRequest initRequest(WebRequest request){
		
		request.setAdditionalHeader("Origin", "http://m.weibo.cn"); 
        request.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
        request.setAdditionalHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        request.setAdditionalHeader("Accept-Encoding", "gzip, deflate");
        request.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        request.setAdditionalHeader("Connection", "keep-alive"); 
        request.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); 
        request.setAdditionalHeader("Host", "m.weibo.cn"); 
        
		return request;
	}

}

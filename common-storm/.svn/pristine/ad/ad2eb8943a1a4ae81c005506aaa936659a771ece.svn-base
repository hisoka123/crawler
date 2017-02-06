package com.storm.function;

import com.crawler.weibo.login.WeiboLogin;
import com.module.domain.WebPageResponse;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.storm.domian.WebParam;
import com.storm.domian.WeiboLogonParam;

public class WeiboLogonFunction {
	
	public String getHtml(WeiboLogonParam param, WebParam webEngineParam) throws Exception {
		WebPageResponse response = getPage(param.getUrl(),param.isJsEnable(), webEngineParam.getLogback());
		String html = null;
		if(response!=null){ 
			html = response.getHtml(); 
		}
		return html;
	}
	
	
/*	public WebPageResponse getPage(String url) throws Exception{
		log.info("getPage:{},jsEnable:{}",url,true);
		WebPageResponse response = WeiboLogin.getInstance().getPageInlogin(url,true);   
		return response;
	}*/
	
	public WebPageResponse getPage(String url,boolean jsEnable,String logback) throws Exception{
		ChannelLogger log = ChannelLoggerFactory.getLogger(WeiboLogonFunction.class, logback);
		log.info("getPage:"+url+",jsEnable:"+jsEnable);
		WebPageResponse response = WeiboLogin.getInstance().getPageInlogin(url,jsEnable);   
		log.returnRedisResource();
		return response;
	}
	

}

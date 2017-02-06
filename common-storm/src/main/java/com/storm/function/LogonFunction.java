package com.storm.function;

import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;
import com.module.htmlunit.definition.UtilDefinition;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.redis.domain.CookieJson;
import com.storm.domian.LogonParam;
import com.storm.domian.WebParam;
import com.storm.persistence.RedisService;

public class LogonFunction {

	public String getHtml(LogonParam param, WebParam webParam) throws Exception {
		ChannelLogger log = ChannelLoggerFactory.getLogger(LogonFunction.class, webParam.getLogback());
		
		String url = param.getUrl();
		String unit = param.getUnit();
		List<CookieJson> cjs = RedisService.getInstance().getCookiesByDomainS(param.getDomains());
		WebPageResponse response = null;
		String html = null;
		if(unit.equals(UtilDefinition.HTMLUNIT)){
			log.info("========== HTMLUNIT  HTML Start  ==============");  
			response = getPageByHtmlUnit(url,RedisService.getInstance().convert2HtmlunitCookie(cjs), webParam);
			log.info("========== HttpBolt  HTML End  ==============="); 
		}
		if(unit.equals(UtilDefinition.JSOUP)){
			log.info("========== JSOUP  HTML Start  =============="); 
			response = getPageByJsoup(url,RedisService.getInstance().convert2MapCookie(cjs), webParam);
			log.info("========== HttpBolt  HTML End  ==============="); 
		} 
		if(response!=null){ 
			log.info("url:"+url);
			log.info("unit:"+unit);
			log.info("HttpStatusCode:"+response.getHttpStatusCode()); 
			//log.info(response.getHtml()); 
			html = response.getHtml();
		} 
		
		log.returnRedisResource();
		
		return html; 
	}
	
	public WebPageResponse getPageByJsoup(String url,Map<String,String> cookies, WebParam webParam) throws Exception{
		ChannelLogger log = ChannelLoggerFactory.getLogger(LogonFunction.class, webParam.getLogback());
		
		log.info("=============  getPageByJsoup getPage    ============= "+url);
		WebPageResponse response = WebCrawler.getInstance().getPageInCookiesJsoup(url,cookies);  
		
		log.returnRedisResource();
		
		return response;
	}
	 
	public WebPageResponse getPageByHtmlUnit(String url,List<Cookie> cookies, WebParam webParam) throws Exception{
		ChannelLogger log = ChannelLoggerFactory.getLogger(LogonFunction.class, webParam.getLogback());
		
		log.info("=============  getPageByHtmlUnit getPage    ============= "+url); 
		WebPageResponse response = WebCrawler.getInstance().getPageInCookiesHtmlunit(url,cookies);  
		
		log.returnRedisResource();
		
		return response;
	}
	
}

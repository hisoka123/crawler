package com.storm.function;

import java.io.IOException;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;
import com.module.htmlunit.definition.UtilDefinition;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.storm.domian.WebParam;

public class WebEngineFunction {

	public String getData(WebParam param) throws Exception{
		ChannelLogger log = ChannelLoggerFactory.getLogger(WebEngineFunction.class, param.getLogback());
		String data = null;
		
		String url = param.getUrl();
		String unit = param.getUnit();
		WebPageResponse response = null; 
		if(unit.equals(UtilDefinition.HTMLUNIT)){
			log.info("========== HTMLUNIT  HTML Start  =============="); 
			response = getPageByHtmlUnit(url);
			log.info("========== HttpBolt  HTML End  ==============="); 
		}
		if(unit.equals(UtilDefinition.JSOUP)){
			log.info("========== JSOUP  HTML Start  =============="); 
			response = getPageByJsoup(url);
			log.info("========== HttpBolt  HTML End  ==============="); 
		} 
		if(response!=null){ 
			log.info("url:" + url);
			log.info("unit:" + unit);
			log.info("HttpStatusCode:" + response.getHttpStatusCode()); 
			//log.info(response.getHtml()); 
			data = response.toJson();
		} 
		
		log.returnRedisResource();
		return data;
	}
	
	public String getHtml(WebParam param) throws Exception{
		ChannelLogger log = ChannelLoggerFactory.getLogger(WebEngineFunction.class, param.getLogback());
		
		String url = param.getUrl();
		String unit = param.getUnit();
		WebPageResponse response = null;
		String html = null;
		if(unit.equals(UtilDefinition.HTMLUNIT)){
			log.info("========== HTMLUNIT  HTML Start  =============="); 
			response = getPageByHtmlUnit(url);
			log.info("========== HttpBolt  HTML End  ==============="); 
		}
		if(unit.equals(UtilDefinition.JSOUP)){
			log.info("========== JSOUP  HTML Start  =============="); 
			response = getPageByJsoup(url);
			log.info("========== HttpBolt  HTML End  ==============="); 
		} 
		if(response!=null){ 
			log.info("url:" + url);
			log.info("unit:" + unit);
			log.info("HttpStatusCode:" + response.getHttpStatusCode()); 
			//log.info(response.getHtml()); 
			html = response.getHtml();
		} 
		
		log.returnRedisResource();
		return html;
		
	}
	
	public WebPageResponse getPageByHtmlUnit(String url) throws Exception{
		WebPageResponse response = WebCrawler.getInstance().getPage(url);
		return response;
	}
	
	public WebPageResponse getPageByJsoup(String url) throws IOException{
		WebPageResponse response = WebCrawler.getInstance().getPageJsoup(url);
		return response;
	}

}

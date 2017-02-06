package com.storm.function;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;
import com.module.htmlunit.definition.UtilDefinition;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.storm.domian.WebParam;

public class SxjlcxptFunction {
	
	public String getHtml(WebParam param) throws Exception{
		ChannelLogger log = ChannelLoggerFactory.getLogger(ShixinWebFunction.class, param.getLogback());
		
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
			log.info("url:"+url);
			log.info("unit:"+unit);
			log.info("HttpStatusCode:"+response.getHttpStatusCode()); 
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
	
//	public static void main(String[] args) throws Exception {
//	WebParam webParam = new WebParam();
//	//http://www.315cx.org.cn/search/?searchkey=武汉&searchtype=1
//	webParam.setUrl("www.315cx.org.cn/search/?searchkey=%ba%fe%b1%b1&searchtype=1");
//	String s=URLEncoder.encode("武汉", "gbk");
//	System.out.println(s);
//	SxjlcxptFunction fun = new SxjlcxptFunction();
//	WebPageResponse response=fun.getPageByHtmlUnit("http://www.315cx.org.cn/search/?searchkey=%CE%E4%BA%BA&searchtype=1");
//	String html = response.getHtml();
//	System.out.println("--------" + html);
//
//}	

}

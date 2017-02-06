package com.storm.function;

import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.rss.fetch.RssXMLFetch;
import com.storm.domian.RssParam;
import com.storm.domian.WebParam;

public class RssEngineFunction {
	
	public String getRss(RssParam param, WebParam webParam) throws Exception{ 
		ChannelLogger log = ChannelLoggerFactory.getLogger(RssEngineFunction.class, webParam.getLogback());
		String url = param.getUrl();
		log.info("url:"+url);
		String xml = RssXMLFetch.getInstance().fetch(url); 
		
		log.returnRedisResource();
		
		return xml;
	}
}

package com.crawler.weibo.htmlparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.weibo.domain.json.UserActionResponse;
import com.google.gson.Gson;

@Component
public class WeiboUserActionParser {

	private static final Logger log = LoggerFactory.getLogger(WeiboUserActionParser.class);
	
	public UserActionResponse userActionParser(String json){
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		log.info("userActionParser json --- :"+json);
		UserActionResponse response = null;
		if(json!=null){
			Gson gson = new Gson();  
			response = gson.fromJson(json, UserActionResponse.class);
		}
		return response;
	}
	
}

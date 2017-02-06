package com.crawlermanage.controller.api.weibo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawlermanage.service.weibo.WeiboUserInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/weibo")
public class WeiboUserInfoController {

	private static final Logger log = LoggerFactory.getLogger(WeiboUserInfoController.class);
	
	@Autowired
	private WeiboUserInfoService weiboUserInfoService;
	  
	@RequestMapping(value = "/userinfo")
	@ResponseBody
    public String home(@RequestParam("url") String url, boolean isDebug, String logback) {  
		
		//url = "http://weibo.com/1496825941/follow";
		//url = "http://weibo.com/2393857144/follow?page=2";
		
		log.info("url:{}",url);
		     
		Result<UserFeedJson> result = weiboUserInfoService.getUserInfo(url, isDebug, logback);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		
		String resultJson = gson.toJson(result);
		 
		return resultJson;
	}
	
}

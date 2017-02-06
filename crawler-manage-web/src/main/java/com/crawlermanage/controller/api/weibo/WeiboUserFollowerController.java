package com.crawlermanage.controller.api.weibo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawlermanage.service.weibo.WeiboUserFollowerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/api/weibo")
public class WeiboUserFollowerController {

	private static final Logger log = LoggerFactory.getLogger(WeiboUserFollowerController.class);
	
	@Autowired
	private WeiboUserFollowerService weiboFollowerService;
	  
	@RequestMapping(value = "/follower")
	@ResponseBody
    public String home(@RequestParam("url") String url, boolean isDebug, String logback) {  
		
		//url = "http://weibo.com/1496825941/follow";
		//url = "http://weibo.com/2393857144/follow?page=2";
		
		log.info("url:{}",url);
		     
		Result<List<UserFeedJson>> result = weiboFollowerService.getFollowers(url, isDebug, logback);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);
		 
		return resultJson;
	}
	
}

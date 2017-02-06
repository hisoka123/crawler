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
import com.crawler.weibo.domain.json.TweetJson;
import com.crawlermanage.service.weibo.WeiboTopicService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/api/weibo")
public class WeiboTopicController {
	
	private static final Logger log = LoggerFactory.getLogger(WeiboTopicController.class);
	
	@Autowired
	private WeiboTopicService weiboTopicService;
	  
	@RequestMapping(value = "/topic")
	@ResponseBody
    public String home(@RequestParam("url") String url, boolean isDebug, String logback) {   
		
		log.info("url:{}",url);

		Result<List<TweetJson>> result = weiboTopicService.getTopicTweets(url, isDebug, logback);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String resultJson = gson.toJson(result);
		 
		return resultJson;
	}
	

}

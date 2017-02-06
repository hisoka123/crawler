package com.crawlermanage.controller.api.zhihu;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.zhihu.domain.json.UserFeedJson;
import com.crawler.zhihu.domain.json.ZhihuAnswer;
import com.crawler.zhihu.domain.json.ZhihuQuestion;
import com.crawlermanage.service.zhihu.ZhihuUserProfileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/api/zhihu")
public class ZhihuUserProfileController {
	
	private static final Logger log = LoggerFactory.getLogger(ZhihuUserProfileController.class);
	
	@Autowired
	private ZhihuUserProfileService zhihuUserProfileService;
	
	@RequestMapping(value = "/profile")
	@ResponseBody
    public String home(@RequestParam("url") String url, boolean isDebug) {   
		  
		log.info("url:{}",url);
		
		Result<UserFeedJson> result = zhihuUserProfileService.userProfile(url, isDebug);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);

		return resultJson;
	}
	
	@RequestMapping(value = "/followees")
	@ResponseBody
    public String followees(@RequestParam("url") String url, boolean isDebug, String logback) {   
		  
		log.info("url:{}",url);
		
		Result<List<UserFeedJson>> result = zhihuUserProfileService.userFollowees(url, isDebug, logback);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String resultJson = gson.toJson(result);

		return resultJson;
	}
	
	@RequestMapping(value = "/asks")
	@ResponseBody
    public String asks(@RequestParam("url") String url, boolean isDebug, String logback) {   
		  
		log.info("url:{}",url);
		
		Result<List<ZhihuQuestion>> result = zhihuUserProfileService.userAsks(url, isDebug, logback);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String resultJson = gson.toJson(result);

		return resultJson;
	}
	
	@RequestMapping(value = "/answers")
	@ResponseBody
    public String answers(@RequestParam("url") String url, boolean isDebug, String logback) {   
		  
		log.info("url:{}",url);
		
		Result<List<ZhihuAnswer>> result = zhihuUserProfileService.userAnswers(url, isDebug, logback);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String resultJson = gson.toJson(result);

		return resultJson;
	}
}

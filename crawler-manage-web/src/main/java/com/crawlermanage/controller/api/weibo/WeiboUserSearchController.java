package com.crawlermanage.controller.api.weibo;

import java.util.List;

import com.module.htmlunit.unit.BaseUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawlermanage.service.weibo.WeiboUserSearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/weibo")
public class WeiboUserSearchController {

	private static final Logger log = LoggerFactory.getLogger(WeiboUserSearchController.class);
	
	@Autowired
	private WeiboUserSearchService weiboUserSearchService;
	  
	@RequestMapping(value = "/user")
	@ResponseBody
    public String home(@RequestParam("q") String q, boolean isDebug, String logback) {  
		
		log.info("q:{}",q);
		  
		//q = "{url:\"http://s.weibo.com/user/36%25E6%25B0%25AA\",unit:\"jsoup\"}";
		q = BaseUnit.encode(q, "utf8");
		String url = "http://s.weibo.com/user/"+q; 
		
		log.info("url:{}",url);
		
		Result<List<UserFeedJson>> result = weiboUserSearchService.searchUser(url, isDebug, logback);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);
		 
		return resultJson;
	}
	
}

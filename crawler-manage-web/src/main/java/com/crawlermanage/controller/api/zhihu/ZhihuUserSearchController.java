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
import com.crawlermanage.service.zhihu.ZhihuUserSearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;


@Controller
@RequestMapping("/api/zhihu")
public class ZhihuUserSearchController {
	
	private static final Logger log = LoggerFactory.getLogger(ZhihuUserSearchController.class);
	
	@Autowired
	private ZhihuUserSearchService zhihuUserSearchService;
	
	@RequestMapping(value = "/user")
	@ResponseBody
    public String home(@RequestParam("q") String q, boolean isDebug, String logback) {  
		log.info("q:{}",q);
        q = BaseUnit.encode(q, "utf8");
		String url = "http://www.zhihu.com/search?type=people&q="+q;
		log.info("url:{}",url);
		
		Result<List<UserFeedJson>> result = zhihuUserSearchService.searchUser(url, isDebug, logback);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);
		 
		return resultJson;
	}
	
	

}

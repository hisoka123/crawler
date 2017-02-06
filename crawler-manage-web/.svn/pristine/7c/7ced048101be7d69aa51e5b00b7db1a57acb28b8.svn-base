package com.crawlermanage.controller.api.weixin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.weixin.domain.json.UserFeedJson;
import com.crawlermanage.service.weixin.WeixinUserSearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;


@Controller
@RequestMapping("/api/weixin")
public class WeixinUserSearchController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUserSearchController.class);
	
	@Autowired
	private WeixinUserSearchService weixinUserSearchService;
	
	@RequestMapping(value = "/user")
	@ResponseBody
    public String home(@RequestParam("q") String q, boolean isDebug, String logback) {  
		LOGGER.info("q:{}",q); 
		q = BaseUnit.encode(q, "utf8");
		LOGGER.info("q2:{}",q); 
		String url =  "http://weixin.sogou.com/weixin?type=1&query=" + q; //type=1表示按公共号查询，type=2表示按文章查询
		LOGGER.info("url:{}",url);
		
		Result<List<UserFeedJson>> result = weixinUserSearchService.searchUser(url, isDebug, logback);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);
		 
		return resultJson;
	}
	

}

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
import com.crawler.weixin.domain.json.WeixinArticle;
import com.crawlermanage.service.weixin.WeixinArticleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;


@Controller
@RequestMapping("/api/weixin")
public class WeixinArticleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinArticleController.class);
	
	@Autowired
	private WeixinArticleService weixinArticleService;
	
	@RequestMapping(value = "/article")
	@ResponseBody
    public String home(@RequestParam("q") String q, boolean isDebug) {  
		LOGGER.info("q:{}", q);
        q = BaseUnit.encode(q, "utf8");
        String url = "http://weixin.sogou.com/weixin?type=2&query=" + q;
		LOGGER.info("url:{}", url);
		
		Result<List<WeixinArticle>> result = weixinArticleService.getArticles(url, isDebug);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);
		 
		return resultJson;
	}
	

}

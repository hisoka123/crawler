package com.crawlermanage.controller.api.news;

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
import com.crawlermanage.service.news.NewsFetchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.rss.domain.FeedItem;


@Controller
@RequestMapping("/api/news")
public class NewsController {

	private static final Logger log = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	private NewsFetchService newsFetchService;
	
	
	@RequestMapping(value = "/feed")
	@ResponseBody
    public String home(@RequestParam("q") String q, String domain, boolean isDebug, String logback) {   
		q = BaseUnit.encode(q,"utf-8");
        log.info("q---------"+q);
		log.info("domain---------"+domain);
		Result<List<FeedItem>> result = newsFetchService.searchNews(q, domain, isDebug, logback);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);
		 
		return resultJson;
	}
	
}

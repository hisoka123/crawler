package com.crawlermanage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.weixin.domain.json.UserFeedJson;
import com.crawler.weixin.domain.json.WeixinArticle;
import com.crawlermanage.service.weixin.WeixinArticleService;
import com.crawlermanage.service.weixin.WeixinUserSearchService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
	
	@Autowired
	private WeixinUserSearchService weixinUserSearchService;
	@Autowired
	private WeixinArticleService weixinArticleService;
	
	/*
	 * (1)爬取人物的基本信息			
	 */

	//(1-1)转向”爬取“页，填写爬取人物。
	@RequestMapping("/crawler")
	public String crawler(){
		return "weixin/crawler";
	}
	//(1-2)转向“爬取结果展示”页
	@RequestMapping(value="/crawler_search",method=RequestMethod.POST)
	public String crawlerSearch (@RequestParam("crawlerContent") String crawlerContent,
			                     @RequestParam("weixinOptions") String weixinOptions,
			                     Model model){
	      log.info("微信爬取内容：{}"+crawlerContent);
	      log.info("微信爬取类型：{}"+weixinOptions);
	      model.addAttribute("crawlerContent",crawlerContent+','+weixinOptions);
	      return "weixin/crawler-search";
	}
	//(1-3)得到"公众号爬取结果"
	@RequestMapping(value="/getCrawlerUsers",method=RequestMethod.GET)
	public @ResponseBody List<UserFeedJson> getCrawlerUserContent(@RequestParam("crawlerUserKeyword") String crawlerUserKeyword){
		   try {
			log.info("爬取公众号关键词：{}"+URLDecoder.decode(crawlerUserKeyword,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   String url =  "http://weixin.sogou.com/weixin?type=1&query="+crawlerUserKeyword;
		   log.info("爬取公众号url:{}"+url);
		    Result<List<UserFeedJson>> result= weixinUserSearchService.searchUser(url,false, "");
		    List<UserFeedJson> userfeed=result.getData();
		   Gson gson=new Gson();
		   log.info("爬取公众号结果：{}"+gson.toJson(userfeed));
		   return userfeed;		   
	}
	
	//(1-4)得到"文章爬取结果"
	@RequestMapping(value="/getCrawlerArticles",method=RequestMethod.GET)
	public @ResponseBody List<WeixinArticle> getCrawlerArticles(@RequestParam("crawlerArticleKeyword") String crawlerArticleKeyword){
		    try {
				log.info("爬取文章关键词:{}"+URLDecoder.decode(crawlerArticleKeyword,"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String url = "http://weixin.sogou.com/weixin?type=2&query="+crawlerArticleKeyword;
		    log.info("爬取文章url:{}"+url);
		    Result<List<WeixinArticle>> result = weixinArticleService.getArticles(url,false);
		     List<WeixinArticle> articles=result.getData();
		    Gson gson=new Gson();
		    log.info("爬取文章结果:{}"+gson.toJson(articles));
		    return articles;
	}
			                     
}

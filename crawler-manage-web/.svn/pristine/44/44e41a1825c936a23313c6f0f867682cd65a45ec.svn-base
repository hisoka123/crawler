package com.crawlermanage.controller.modules;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/modules/weixin")
public class AjaxWeixinController {

    private static final Logger log = LoggerFactory.getLogger(AjaxWeixinController.class);
	
	@Autowired
	private WeixinUserSearchService weixinUserSearchService;
	@Autowired
	private WeixinArticleService weixinArticleService;
	
	
	
    
	@RequestMapping(value="/getSearchOfficialAccountsResult",method=RequestMethod.GET)
	public @ResponseBody List<UserFeedJson> getSearchOfficialAccountsResult(@RequestParam("officialAccounts") String officialAccounts){
		   
		   List<UserFeedJson> userfeed=null;
		   
		   try {
			
			   log.info("微信公众号officialAccounts：{}"+URLDecoder.decode(officialAccounts,"utf-8"));
			  
			   String officialAccountsURL =  "http://weixin.sogou.com/weixin?type=1&query="+officialAccounts;
			   log.info("微信公众号URL  officialAccountsURL:{}"+officialAccountsURL);
			   
			   Result<List<UserFeedJson>> result= weixinUserSearchService.searchUser(officialAccountsURL,false, "");
			   userfeed=result.getData();
			   
			   Gson gson=new Gson();
			   log.info("微信公众号搜索结果：{}"+gson.toJson(userfeed));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return userfeed;		   
	}

	//微信文章
	@RequestMapping(value="/getSearchArticleResult",method=RequestMethod.GET)
	public @ResponseBody List<WeixinArticle> getSearchArticleResult(@RequestParam("article") String article){
		    
		    List<WeixinArticle> articleJsonList=null;
		   
		   try {
				log.info("微信文章article:{}"+URLDecoder.decode(article,"utf-8"));
				
				String articleURL = "http://weixin.sogou.com/weixin?type=2&query="+article;
			    log.info("微信文章articleURL:{}"+articleURL);
			    
			    Result<List<WeixinArticle>> result = weixinArticleService.getArticles(articleURL,false);
			    articleJsonList=result.getData();
			    
			    Gson gson=new Gson();
			    log.info("微信文章搜索结果:{}"+gson.toJson(articleJsonList));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return articleJsonList;
	}
	
	
	
	
}

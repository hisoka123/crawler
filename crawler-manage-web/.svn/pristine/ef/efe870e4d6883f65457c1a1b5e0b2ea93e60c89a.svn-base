package com.crawlermanage.controller;

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
import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawlermanage.service.linkedin.LinkedinUserInfoService;
import com.crawlermanage.service.linkedin.LinkedinUserSearchService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



@Controller
@RequestMapping("/linkedIn")
public class LinkedInController {

	private static final Logger log = LoggerFactory.getLogger(LinkedInController.class);
	
	@Autowired
	private LinkedinUserSearchService linkedinUserSearchService;
	
	@Autowired
	private LinkedinUserInfoService linkedinUserInfoService;
	
	
	@RequestMapping("/crawler")
	public String crawler(){
		return "linkedIn/crawler";
	}
	
	//转至搜索结果页
	@RequestMapping(value="/crawlerSearch",method=RequestMethod.POST)
	public String crawlerSearch(@RequestParam("crawlerPerson") String crawlerPerson,Model model){
		   
		   log.info("LinkedIn爬取的人:{}",crawlerPerson);
		   model.addAttribute("crawlerPerson",crawlerPerson);		   
		   return "linkedIn/crawler-search";
	}
	
	
	@RequestMapping("/crawlerSearchGet")
	public @ResponseBody  List<UserFeedJson> crawlerSearchGet(@RequestParam("crawlerPerson") String crawlerPerson){
		
		log.info("LinkedIn爬取crawlerPerson:{}"+crawlerPerson);
        String str=crawlerPerson;
        String url= "http://www.linkedin.com/vsearch/f?type=all&keywords="+str;
        Result<List<UserFeedJson>> result = linkedinUserSearchService.searchUser(url,false);
        
        List<UserFeedJson> user=result.getData();
        
		Gson gson=new Gson();
		String userFeedJson=gson.toJson(user);
		userFeedJson=userFeedJson.replaceAll(LinkedinUserSearchService.getUTF8_MARK(), "\\\\u");
		
		List<UserFeedJson> usersList=gson.fromJson(userFeedJson,new TypeToken<List<UserFeedJson>>(){}.getType());
		
		log.info("爬取结果:{}"+userFeedJson);
		
		return usersList;
	}
	
	//转至详情页
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public String details(@RequestParam("profile") String profile,Model model){
		log.info("profile:{}",profile);
		model.addAttribute("profile",profile);
		return "linkedIn/details";
	}
	
	@RequestMapping(value="/getCrawlerDetails",method=RequestMethod.GET)
	public @ResponseBody UserFeedJson getCrawlerDetails(@RequestParam("profileURL") String profileURL){
		
		    log.info("profileURL{}:"+profileURL);
		    
		    Result<UserFeedJson> result = linkedinUserInfoService.getUserInfo(profileURL,false, "");
		    
		    UserFeedJson user=result.getData();
		    
		    Gson gson=new Gson();
		    log.info("LinkedIn详情{}:"+gson.toJson(user));
		    
		    return user;
		
		
	}
	
	
}

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
import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawlermanage.service.linkedin.LinkedinUserInfoService;
import com.crawlermanage.service.linkedin.LinkedinUserSearchService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/modules/linkedIn")
public class AjaxLinkedInController {

	private static final Logger log = LoggerFactory.getLogger(AjaxLinkedInController.class);
	
	@Autowired
	private LinkedinUserSearchService linkedinUserSearchService;
	
	@Autowired
	private LinkedinUserInfoService linkedinUserInfoService;
	
	
	
	@RequestMapping(value="/getSearchResult",method=RequestMethod.GET)
	public @ResponseBody  List<UserFeedJson> getSearchResult(@RequestParam("person") String person){
		
		List<UserFeedJson> usersList=null;
		
		try {
			
			 log.info("LinkedIn搜索person:{}"+URLDecoder.decode(person,"utf-8"));
			 
			 String personURL= "http://www.linkedin.com/vsearch/f?type=all&keywords="+person;
		     log.info("linkedIn搜索personURL:{}"+personURL);
		        
		     Result<List<UserFeedJson>> result = linkedinUserSearchService.searchUser(personURL,false);
		     List<UserFeedJson> user=result.getData();
		        
		     Gson gson=new Gson();
		     String userFeedJson=gson.toJson(user);

		     userFeedJson=userFeedJson.replaceAll(LinkedinUserSearchService.getUTF8_MARK(), "\\\\u");
			 usersList=gson.fromJson(userFeedJson,new TypeToken<List<UserFeedJson>>(){}.getType());
				
			 log.info("linkedIn搜索结果:{}"+userFeedJson);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersList;
	}
	
	
	@RequestMapping(value="/getLinkedInDetails",method=RequestMethod.POST)
	public @ResponseBody UserFeedJson getCrawlerDetails(@RequestParam("profileURL") String profileURL){
		
		    log.info("profileURL{}:"+profileURL);
		    
		    Result<UserFeedJson> result = linkedinUserInfoService.getUserInfo(profileURL,false, "");
		    
		    UserFeedJson user=result.getData();
		    
		    Gson gson=new Gson();
		    log.info("LinkedIn详情{}:"+gson.toJson(user));
		    
		    return user;
		
		
	} 
	
}

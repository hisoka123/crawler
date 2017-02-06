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
import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawlermanage.service.weibo.WeiboUserSearchService;
import com.google.gson.Gson;


@Controller
@RequestMapping("/modules/sinaweibo")
public class AjaxSinaweiboController {

	private static final Logger log = LoggerFactory.getLogger(AjaxSinaweiboController.class);
	
	@Autowired
	private WeiboUserSearchService weiboUserSearchService;
	
	
	
	//搜索人物
	@RequestMapping(value="/getSearchResults",method=RequestMethod.GET)
	public @ResponseBody List<UserFeedJson> getSearchResults(@RequestParam("person") String person){
		
		 List<UserFeedJson> userFeedJsonList=null;
		 try {
			  
			  log.info("新浪微博person:{}",URLDecoder.decode(person,"utf-8"));

			  String personURL= "http://s.weibo.com/user/"+person;
			  log.info("新浪微博personURL:{}",personURL);
			  
			  Result<List<UserFeedJson>> result = weiboUserSearchService.searchUser(personURL,false,"");          
			  userFeedJsonList=result.getData();
	          
	          Gson gson = new Gson();  		
		      String usersJson = gson.toJson(userFeedJsonList);
		      log.info("新浪微博搜索结果:{}", usersJson);	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userFeedJsonList;
		
	}
}

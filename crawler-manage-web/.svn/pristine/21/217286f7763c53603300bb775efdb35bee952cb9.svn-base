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
import com.crawler.zhihu.domain.json.UserFeedJson;
import com.crawlermanage.service.zhihu.ZhihuUserSearchService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/modules/zhihu")
public class AjaxZhihuController {

    private static final Logger log = LoggerFactory.getLogger(AjaxZhihuController.class);
	
	@Autowired
	private ZhihuUserSearchService zhihuUserSearchService;
	
	
	
	
	//搜索结果
   @RequestMapping(value="/getSearchResult",method=RequestMethod.GET)
   public @ResponseBody List<UserFeedJson> getSearchResult(@RequestParam("person") String person){
		
		 List<UserFeedJson> userFeedJsonList=null;
		 try {
			  
			  log.info("知乎person:{}",URLDecoder.decode(person,"utf-8"));

			  String personURL="http://www.zhihu.com/search?type=people&q="+person;
			  log.info("知乎personURL:{}",personURL);

			  Result<List<UserFeedJson>> result =zhihuUserSearchService.searchUser(personURL,false,"");          
			  userFeedJsonList=result.getData();
	          
	          Gson gson = new Gson();  		
		      log.info("知乎搜索结果:{}",gson.toJson(userFeedJsonList));	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userFeedJsonList;		
	}

	
}

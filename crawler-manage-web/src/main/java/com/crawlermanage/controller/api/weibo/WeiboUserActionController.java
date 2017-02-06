package com.crawlermanage.controller.api.weibo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.weibo.domain.json.UserActionResponse;
import com.crawlermanage.service.weibo.WeiboUserActionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/weibo")
public class WeiboUserActionController {

	private static final Logger log = LoggerFactory.getLogger(WeiboUserActionController.class);
	
	@Autowired
	private WeiboUserActionService weiboUserActionService;
	
	
	
	@RequestMapping(value = "/useraction")
	@ResponseBody
    public String home(@RequestParam("action") String action,@RequestParam("uid") String uid, 
    		boolean isDebug, String logback) {  
		
		log.info("userAction :action"+action+"     uid:"+uid);
		
		Result<UserActionResponse> result = weiboUserActionService.userAction(action, uid, isDebug, logback);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);
		
		return resultJson;
		
	}
	
	
}

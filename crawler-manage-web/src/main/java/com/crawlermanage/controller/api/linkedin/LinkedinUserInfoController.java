/**
 * 
 */
package com.crawlermanage.controller.api.linkedin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawlermanage.service.linkedin.LinkedinUserInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author kingly
 *
 */
@Controller
@RequestMapping("/api/linkedin")
public class LinkedinUserInfoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkedinUserInfoController.class);
	
	@Autowired
	private LinkedinUserInfoService linkedinUserInfoService;
	
	@RequestMapping(value = "/userinfo")
	@ResponseBody
	public String userinfo(@RequestParam("url") String url, boolean isDebug, String logback) {
		LOGGER.info("url:{}",url);
		
		Result<UserFeedJson> result = linkedinUserInfoService.getUserInfo(url, isDebug, logback);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String resultJson = gson.toJson(result);
		
		return resultJson;
	}
	
}

/**
 * 
 */
package com.crawlermanage.controller.api.gsxt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.gsxt.domain.json.AicFeedJson;
import com.crawlermanage.service.gsxt.GsxtService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author kingly
 * @date 2016年3月3日
 * 江西
 */
@Controller
@RequestMapping("/api/gsxt/jiangxi")
public class JiangxiGsxtController {
	
	@Autowired
	private GsxtService gsxtService;
	private static final Logger LOGGER = LoggerFactory.getLogger(JiangxiGsxtController.class);
	
	
	@RequestMapping("/getSearchPage")
	@ResponseBody
	public String getSearchPage(boolean isDebug, String logback) {
		LOGGER.info("==============JiangxiGsxtController.getSearchPage start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<Map<String, String>> result = gsxtService.searchPageHandleOfJiangxi(isDebug, logback);
		
		return gson.toJson(result);
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public String getData(@RequestParam("serializedFileName") String serializedFileName, 
			@RequestParam("verifycode") String verifycode, 
			@RequestParam("keyword") String keyword, boolean isDebug, String logback) {
		LOGGER.info("==============JiangxiGsxtController.getData start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<AicFeedJson> result = gsxtService.getDataOfJiangxi(serializedFileName, verifycode, keyword, isDebug, logback);		
		return gson.toJson(result);
	}
	
	@RequestMapping("/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword, boolean isDebug, String logback) {
		LOGGER.info("==============JiangxiGsxtController.getDataOnce start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<AicFeedJson> result = gsxtService.getDataOfJiangxiOnce(keyword, isDebug, logback);
		
		return gson.toJson(result);
	}
	
	
	
}



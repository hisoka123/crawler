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
 * 贵州
 */
@Controller
@RequestMapping("/api/gsxt/guizhou")
public class GuizhouGsxtController {
	
	@Autowired
	private GsxtService gsxtService;
	private static final Logger LOGGER = LoggerFactory.getLogger(GuizhouGsxtController.class);
	
	
	@RequestMapping("/getSearchPage")
	@ResponseBody
	public String getSearchPage(boolean isDebug, String logback) {
		LOGGER.info("==============GuizhouGsxtController.getSearchPage start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<Map<String, String>> result = gsxtService.searchPageHandleOfGuizhou(isDebug, logback);
		
		return gson.toJson(result);
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public String getData(@RequestParam("serializedFileName") String serializedFileName, 
			@RequestParam("verifycode") String verifycode, 
			@RequestParam("keyword") String keyword, boolean isDebug, String logback) {
		LOGGER.info("==============AnhuiGsxtController.getData start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<AicFeedJson> result = gsxtService.getDataOfGuizhou(serializedFileName, verifycode, keyword, isDebug, logback);		
		return gson.toJson(result);
	}
	
	@RequestMapping("/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword, boolean isDebug, String logback) {
		LOGGER.info("==============AnhuiGsxtController.getDataOnce start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<AicFeedJson> result = gsxtService.getDataOfGuizhouOnce(keyword, isDebug, logback);
		
		return gson.toJson(result);
	}
}



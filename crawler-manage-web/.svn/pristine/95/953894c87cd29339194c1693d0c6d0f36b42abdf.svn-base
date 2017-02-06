/**
 * 
 */
package com.crawlermanage.controller.api.gsxt;

import java.util.Map;

import com.crawler.gsxt.domain.json.AicFeedJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.crawler.domain.json.Result;
import com.crawlermanage.service.gsxt.GsxtService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author kingly
 * @date 2016年3月3日
 * 重庆
 */
@Controller
@RequestMapping("/api/gsxt/chongqing")
public class ChongqingGsxtController {
	
	@Autowired
	private GsxtService gsxtService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ChongqingGsxtController.class);
	
	
	@RequestMapping("/getSearchPage")
	@ResponseBody
	public String getSearchPage(boolean isDebug, String logback) {
		LOGGER.info("==============ChongqingGsxtController.getSearchPage start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<Map<String, String>> result = gsxtService.searchPageHandleOfChongqing(isDebug, logback);
		
		return gson.toJson(result);
	}


	@RequestMapping("/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword, boolean isDebug, String logback) {
		LOGGER.info("==============ChongqingGsxtController.getDataOnce start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Result<AicFeedJson> result = gsxtService.getDataOfChongqingOnce(keyword, isDebug, logback);

		return gson.toJson(result);
	}
}



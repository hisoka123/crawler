package com.crawlermanage.controller.api.dailianmeng;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.dailianmeng.domain.json.LoanUnionFeedJson;
import com.crawler.dailianmeng.domain.json.UserFeedJson;
import com.crawler.domain.json.Result;
import com.crawlermanage.service.dailianmeng.DaiLianMengUserInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/dailianmeng")
public class DaiLianMengUserInfoController {

	private static final Logger log = LoggerFactory.getLogger(DaiLianMengUserInfoController.class);
	
	@Autowired
	private DaiLianMengUserInfoService dailianmengUserInfoService;
	
	@RequestMapping("/getSearchPage")
	@ResponseBody
	public String getSearchPage(String keyword, boolean isDebug, String logback) {
		log.info("==============DaiLianMengUserInfoController.getSearchPage start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<Map<String, String>> result = dailianmengUserInfoService.searchPage(keyword, isDebug, logback);
		
		return gson.toJson(result);
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public String search(@RequestParam("serializedFileName") String serializedFileName, 
			@RequestParam("verifycode") String verifycode, boolean isDebug, String logback) {
		log.info("==============DaiLianMengUserInfoController.search start!=================");
		Result<String> result = dailianmengUserInfoService.getSourceHtml(serializedFileName, verifycode, true, logback);
		
		//解析html
		UserFeedJson parseResult = dailianmengUserInfoService.parseResultHtml(result.getHtml());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		log.info("解析结果：" + gson.toJson(parseResult));
		
		return gson.toJson(parseResult);
	}
	
	@RequestMapping("/getDetailPage")
	@ResponseBody
	public String getDetailPage(@RequestParam("detailUrl") String url, boolean isDebug, String logback) {
		log.info("==============DaiLianMengUserInfoController.getDetailPage start!=================");
		log.info("Detail Url:" + url);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<Map<String, String>> result = dailianmengUserInfoService.searchDetailPage(url, isDebug, logback);
		
		return gson.toJson(result);
	}
	
	@RequestMapping("/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword, boolean isDebug, String logback) {
		log.info("==============DaiLianMengUserInfoController.getDataOnce start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<List<LoanUnionFeedJson>> result = dailianmengUserInfoService.getDataOnce(keyword, isDebug, logback);
		
		return gson.toJson(result);
	}	
}

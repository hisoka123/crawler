/**
 * 
 */
package com.crawlermanage.controller.api.iautos;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.iautos.domain.json.UserFeedJson;
import com.crawlermanage.service.iautos.IautosSearchService;
import com.crawlermanage.service.iautos.IautosTaskService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;


@Controller
@RequestMapping("/api/iautos")
public class IautosSearchController {
	
	@Autowired
	private IautosSearchService iautosSearchService;
		
	@Autowired
	private IautosTaskService iautosTaskService;
	private static final Logger LOGGER = LoggerFactory.getLogger(IautosSearchController.class);
	
	@RequestMapping("/search")
	@ResponseBody
	public String search(@RequestParam("city") String city,@RequestParam("page") int page,@RequestParam("keyword") String keyword
			, boolean isDebug, String logback) {
		LOGGER.info("==============IautosSearchController.search start!=================");

		//http://so.iautos.cn/quanguo/p3asdsvepcatcpbnscac/?kw=%E5%A5%A5%E8%BF%AA%2Fp1asdsv1epcatcpbnscac
		LOGGER.info("keyword:{}",keyword);
		keyword = BaseUnit.encode(keyword, "utf8");
		String url = "http://so.iautos.cn/";
		if (city.equals("quanguo")) {
			url+=city+"/p1asdsvepcatcpbnscac/?kw="+keyword;
		}else{
			url+=city+"/p"+page+"asdsvepcatcpbnscac/?kw="+keyword;
		}
		
		LOGGER.info("url:{}",url);	
		Result<List<UserFeedJson>> result= iautosSearchService.search(url, isDebug, logback);       
		Gson gson = new GsonBuilder().setPrettyPrinting().create();		
		String resultJson = gson.toJson(result);	 
		return resultJson;
	}
	
	
	
	@RequestMapping("/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword
			, @RequestParam("city") String city,boolean isDebug, String logback) {
		LOGGER.info("==============getDataOnce start!=================");
	
		Result<List<UserFeedJson>> result= iautosSearchService.getDataOnce(city,keyword, isDebug, logback);    
		Gson gson = new GsonBuilder().setPrettyPrinting().create();		
		String resultJson = gson.toJson(result);	 
		return resultJson;		
		
	}
	
	@RequestMapping("/iautosTask")
	@ResponseBody
	public String iautosTask() {
		LOGGER.info("==============iautosTask start!=================");		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, Object> result = iautosTaskService.excOneceTask();
		return gson.toJson(result);	
	}
	
}



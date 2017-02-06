/**
 * 
 */
package com.crawlermanage.controller.api.sipo;

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
import com.crawler.sipo.domain.json.SearchEngineSipo;
import com.crawlermanage.service.sipo.SipoSearchService;
import com.crawlermanage.service.sipo.SipoTaskService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;


@Controller
@RequestMapping("/api/sipo")
public class SipoSearchController {
	
	@Autowired
	private SipoSearchService sipoSearchService;
	
	@Autowired
	private SipoTaskService sipoTaskService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SipoSearchController.class);
	
	@RequestMapping("/search")
	@ResponseBody
	public String search(@RequestParam("keyword") String keyword,@RequestParam("type") String type,@RequestParam("page") String page,
			boolean isDebug,String logback) {		
		LOGGER.info("==============SipoSearchController.search start!=================");
		LOGGER.info("keyword:{}",keyword);
		keyword="申请（专利权）人='%"+keyword+"%'";
		keyword = BaseUnit.encode(keyword, "utf8");
	
		String url = "http://epub.sipo.gov.cn/patentoutline.action?showType=1&strWord="+keyword+"&numSortMethod=0"
				+"&strLicenseCode=&selected="+type+"&numFMGB=0&numFMSQ=0&numSYXX=0&numWGSQ=0&pageSize=3&pageNow="+page;
		
		LOGGER.info("url:{}",url);	
		Result<List<SearchEngineSipo>> result= sipoSearchService.search(url, isDebug,logback);       
		Gson gson = new GsonBuilder().setPrettyPrinting().create();		
		String resultJson = gson.toJson(result);	 
		return resultJson;
	}
	
	
	
	
	@RequestMapping("/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword
			, @RequestParam("type") String type,boolean isDebug, String logback) {
		LOGGER.info("==============getDataOnce sipo start!=================");
		LOGGER.info("=======type======="+type+"=======keyword======="+keyword);
		Result<List<SearchEngineSipo>> result= sipoSearchService.getDataOnce(type,keyword, isDebug, logback);    
		Gson gson = new GsonBuilder().setPrettyPrinting().create();		
		String resultJson = gson.toJson(result);	 
		return resultJson;		
		
	}
	
	
	@RequestMapping("/sipoTask")
	@ResponseBody
	public String sipoTask() {
		LOGGER.info("==============sipoTask start!=================");		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, Object> result = sipoTaskService.excOneceTask();
		return gson.toJson(result);	
	}
	
	
}



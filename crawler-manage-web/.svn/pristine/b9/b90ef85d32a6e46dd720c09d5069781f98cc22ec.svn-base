package com.crawlermanage.controller.api.renfawang;

import java.io.IOException;
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
import com.crawler.renfawang.domain.json.PeopleCourtFeedJson;
import com.crawlermanage.service.renfawang.RenFaWangService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/api/renfawang")
public class RenFaWangSearchController {
	@Autowired
	private RenFaWangService rfwService;
    private static final Logger loger = LoggerFactory.getLogger(RenFaWangSearchController.class);

    @RequestMapping(value = "/getSearchPage")
    @ResponseBody
    public String getSearchPage(boolean isDebug, String logback) {  
    	loger.info("RenFaWangSearchController.getSearchPage------start-----");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Result<Map<String,String>> ress= rfwService.searchPageHandle(isDebug, logback);
    
        return gson.toJson(ress);
    	
    }
    
	@RequestMapping(value ="/getSearchData")
	@ResponseBody
	public String getSearchData(
			String pname,
			String cardNum,
			@RequestParam("j_captcha") String j_captcha,
			@RequestParam("serializedFileName") String serializedFileName,
			boolean isDebug,
			String logback) throws IOException {	
		
		if(pname==null){
			pname="";
		}
		if(cardNum==null){
			cardNum="";
		}	
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Result<List<Map<String, String>>> result = rfwService.getSearchData(pname, cardNum, j_captcha, serializedFileName, isDebug,logback);		
		loger.info("解析结果：" + gson.toJson(result));	
		
		return gson.toJson(result);
	}

	@RequestMapping(value ="/getDetailData")
	@ResponseBody
	public String getDetailData(
			@RequestParam("pNum") String pNum, 
			@RequestParam("serializedFileName") String serializedFileName,
			boolean isDebug, String logback) {
		
		Result<Map<String, String>> result = rfwService.getDetailData(pNum, serializedFileName, isDebug, logback);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		loger.info("解析结果：" + gson.toJson(result));
		
		return gson.toJson(result);
	}
	
	@RequestMapping(value ="/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword, @RequestParam("queryType") String queryType, boolean isDebug, String logback) {
		loger.info("==============RenFaWangSearchController.getDataOnce start!=================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<List<PeopleCourtFeedJson>> result = rfwService.getDataOnce(keyword, queryType, isDebug, logback);
		loger.info("解析结果：" + gson.toJson(result));
		
		return gson.toJson(result);
	}
}

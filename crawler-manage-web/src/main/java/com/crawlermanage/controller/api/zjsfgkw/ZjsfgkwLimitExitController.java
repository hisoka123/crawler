package com.crawlermanage.controller.api.zjsfgkw;

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
import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwLimitExitService;
import com.google.gson.Gson;


@Controller
@RequestMapping("/api/zjsfgkw")
public class ZjsfgkwLimitExitController {

	private static final Logger log = LoggerFactory.getLogger(ZjsfgkwLimitExitController.class);
	
	@Autowired
	private ZjsfgkwLimitExitService zjsfgkwLimitExitService;
	
	
	
	/**
	   * 执行惩戒-限制出境
	   * @param txtReallyName 姓名,@param  txtCredentialsNumber 证件号,@param txtAH 案号,@param  drpZXFY 执行法院,@param txtStartLARQ 立案日期起止,@param  txtEndLARQ 立案日期结束
	   */
	@RequestMapping(value = "/limitExit")
	@ResponseBody
    public String execute(@RequestParam("txtReallyName") String txtReallyName,@RequestParam("txtCredentialsNumber") String txtCredentialsNumber,
			@RequestParam("txtAH") String txtAH,@RequestParam("drpZXFY") String drpZXFY,@RequestParam("txtStartLARQ") String txtStartLARQ,
			@RequestParam("txtEndLARQ") String txtEndLARQ, boolean isDebug, String logback){
		
		  String creditJson=null;
		 try {
			  
			  log.info("执行惩戒-限制出境txtReallyName:{}",URLDecoder.decode(txtReallyName,"utf-8"));

			  String url =  "http://www.zjsfgkw.cn/Execute/LimitInfo?txtReallyName="
			  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
					  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
					  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8"); 
			  log.info("执行惩戒-限制出境url:{}",url);
			  
			  Result<List<CreditJson>> result = zjsfgkwLimitExitService.searchLimitExit(url,isDebug,logback);          
	          
	          Gson gson = new Gson();  		
		      creditJson = gson.toJson(result);
		      log.info("执行惩戒-限制出境搜索结果:{}", creditJson);	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creditJson;
		
	}
}

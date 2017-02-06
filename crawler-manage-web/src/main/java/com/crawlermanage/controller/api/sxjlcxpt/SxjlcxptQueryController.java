package com.crawlermanage.controller.api.sxjlcxpt;

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
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawler.sxjlcxpt.domain.json.CreditrRecordQueryPlatformJson;
import com.crawler.sxjlcxpt.domain.json.UserFeedJson;
import com.crawlermanage.service.sxjlcxpt.SxjlcxptUserInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by yujh2016-03-03
 */
@Controller
@RequestMapping("/api/sxjlcxpt")
public class SxjlcxptQueryController {
	@Autowired
	private SxjlcxptUserInfoService sxjlcxptUserInfoService;
    private static final Logger loger = LoggerFactory.getLogger(SxjlcxptQueryController.class);

  //搜索
  		@RequestMapping(value="/getSearchResults",method=RequestMethod.GET)
  		public @ResponseBody String getSearchResults(@RequestParam("keyword") String keyword,boolean isDebug,String logback){
  		Result<UserFeedJson> result = null;
  		  Gson gson = new Gson();
  			try {				  
  				loger.info("失信记录查询平台keyword:{}",URLDecoder.decode(keyword,"utf-8"));
  				result = sxjlcxptUserInfoService.searchPagesx(URLDecoder.decode(keyword,"utf-8"), isDebug, logback);          				    		          		
  			      String usersJson = gson.toJson(result);
  			      loger.info("失信记录查询平台:{}", usersJson);					
  			} catch (UnsupportedEncodingException e) {
  				e.printStackTrace();
  			}
  			return gson.toJson(result);
  			
  		}
  		
		@RequestMapping("/getDataOnce")
		@ResponseBody
		public String getDataOnce(String keyword, boolean isDebug, String logback) {
			loger.info("==============IecmsQueryController.getDataOnce start!=================");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();	
			if(keyword==null){
				keyword="";
			}			
			Result<List<CreditrRecordQueryPlatformJson>> result=null;
			try {
				result = sxjlcxptUserInfoService.getDataOnce(URLDecoder.decode(keyword,"utf-8"), isDebug, logback);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Map<String, Object> excOneceTask = iecmsCompanyTaskService.excOneceTask(type);
//			System.out.println(excOneceTask);
			return gson.toJson(result);
		}


   
}

package com.crawlermanage.controller.api.qiyezhengxin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.zhengxin.domain.json.ZhengxinJson;
import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawlermanage.service.qiyezhengxin.QiyezhengxinApiService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwLimitBiddingService;
import com.google.gson.Gson;


@Controller
@RequestMapping("/api/qiyezhengxin")
public class QiyezhengxinApiController {

	private static final Logger log = LoggerFactory.getLogger(QiyezhengxinApiController.class);
	
	@Autowired
	private QiyezhengxinApiService qiyezhengxinApiService;
	
	
	
	/**
	   * 11315企业征信
	   */
	@RequestMapping(value = "/execute")
	@ResponseBody
    public String execute(@RequestParam("companyName") String companyName,boolean isDebug, String logback){
		
		String creditJson=null;
		 try {
			  
			  log.info("11315企业征信txtReallyName:{}",URLDecoder.decode(companyName,"utf-8"));
			  
			 String url = "http://www.11315.com/newSearch?regionMc="
			 +URLEncoder.encode("选择地区","utf-8")+"&searchType=1&regionDm=&searchTypeHead=1&name="
			 +URLEncoder.encode(companyName,"utf-8")+"@"+URLDecoder.decode(companyName,"utf-8"); 
			  
			  log.info("11315企业征信url:{}",url);
			  
			  Result<List<ZhengxinJson>> result = qiyezhengxinApiService.search(url,isDebug,logback);         
	          
	          Gson gson = new Gson();  		
		     creditJson = gson.toJson(result);
		      log.info("11315企业征信搜索结果:{}", creditJson);	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creditJson;
		
	}
	
	/**
	   * 11315企业征信
	   */
	@RequestMapping(value = "/getZhengxinDataOnce")
	@ResponseBody
  public String execute(@RequestParam("companyName") String companyName){
		
		String creditJson=null;
		 try {
			  
			  log.info("11315企业征信txtReallyName:{}",URLDecoder.decode(companyName,"utf-8"));
			  
			 String url = "http://www.11315.com/newSearch?regionMc="
			 +URLEncoder.encode("选择地区","utf-8")+"&searchType=1&regionDm=&searchTypeHead=1&name="
			 +URLEncoder.encode(companyName,"utf-8")+"@"+URLDecoder.decode(companyName,"utf-8"); 
			  
			  log.info("11315企业征信url:{}",url);
			  
			  Result<List<ZhengxinJson>> result = qiyezhengxinApiService.search(url,true,"");         
	          
	          Gson gson = new Gson();  		
		     creditJson = gson.toJson(result);
		      log.info("11315企业征信搜索结果:{}", creditJson);	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creditJson;
		
	}
}

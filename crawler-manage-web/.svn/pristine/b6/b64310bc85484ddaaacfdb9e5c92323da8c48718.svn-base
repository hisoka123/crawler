package com.crawlermanage.controller.api.zhengxin;

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
import com.crawler.zjsfgkw.domain.json.ExecuteCaseSearchJson;
import com.crawlermanage.service.qiyezhengxin.QiyezhengxinApiService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwExecuteCaseSearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**

* 11315企业征信

* @author dyx

*/
@Controller
@RequestMapping("/api/zhengxin")
public class ZhengxinSearchController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZhengxinSearchController.class);
	@Autowired
	private QiyezhengxinApiService qiyezhengxinApiService;
	
	   /**
	   * 11315企业征信
	   */
	@RequestMapping(value = "/zhengxinSearch")
	@ResponseBody
    public String executeCaseSearch(@RequestParam("companyName") String companyName,boolean isDebug, String logback) {  
		LOGGER.info("11315企业征信companyName:{}",companyName); 
		String resultJson =null;
		try {
			LOGGER.info("11315企业征信companyName:{}",URLDecoder.decode(companyName,"utf-8"));
		
			 String url = "http://www.11315.com/newSearch?regionMc="
					 +URLEncoder.encode("选择地区","utf-8")+"&searchType=1&regionDm=&searchTypeHead=1&name="
					 +URLEncoder.encode(companyName,"utf-8")+"@"+URLDecoder.decode(companyName,"utf-8"); 
		LOGGER.info("url:{}",url);
	
		Result<List<ZhengxinJson>> result  = qiyezhengxinApiService.search(url,isDebug,logback);   
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		resultJson = gson.toJson(result);
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultJson;

	}

}

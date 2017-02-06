package com.crawlermanage.controller.api.zjsfgkw;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.zjsfgkw.domain.json.ExecuteCaseSearchJson;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwExecuteCaseSearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**

* 浙法网执行案件信息查询

* @author dyx

*/
@Controller
@RequestMapping("/api/zjsfgkw")
public class ZjsfgkwExecuteCaseSearchController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZjsfgkwExecuteCaseSearchController.class);
	@Autowired
	private ZjsfgkwExecuteCaseSearchService zjsfgkwExecuteCaseSearchService;
	
	   /**
	   * 执行案件信息查询
	   * @param aH_BH 案号,@param  dSR 当事人
	   */
	@RequestMapping(value = "/zjsfgkwExecuteCaseSearch")
	@ResponseBody
    public String executeCaseSearch(@RequestParam("dSR") String dSR,@RequestParam("aH_BH") String aH_BH,@RequestParam("aH_NH") String aH_NH, 
    		boolean isDebug, String logback) {  
		LOGGER.info("执行案件信息查询aH_BH:{}",aH_BH); 
		String resultJson =null;
		try {
			LOGGER.info("执行案件信息查询aH_BH:{}",URLDecoder.decode(aH_BH,"utf-8"));
		
		String url =  "http://www.zjsfgkw.cn/Execute/ExecuteCaseSearch?dSR="+URLDecoder.decode(dSR,"utf-8")+"&aH_BH="+URLDecoder.decode(aH_BH,"utf-8")+"&aH_NH="+URLDecoder.decode(aH_NH,"utf-8"); 
		LOGGER.info("url:{}",url);
	
		Result<List<ExecuteCaseSearchJson>> result = zjsfgkwExecuteCaseSearchService.searchExecuteCase(url,isDebug,logback);  
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		resultJson = gson.toJson(result);
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultJson;

	}

}

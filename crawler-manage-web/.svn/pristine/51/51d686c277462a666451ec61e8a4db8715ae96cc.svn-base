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
import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwCreditCompanyService;
import com.google.gson.Gson;


@Controller
@RequestMapping("/api/zjsfgkw")
public class ZjsfgkwCreditCompanyController {

	private static final Logger log = LoggerFactory.getLogger(ZjsfgkwCreditCompanyController.class);
	
	@Autowired
	private ZjsfgkwCreditCompanyService zjsfgkwCreditCompanyService;
	
	
	
	/**
	   * 曝光台单位信息查询
	   * @param txtComReallyName 姓名,@param  txtComCredentialsNumber 证件号,@param txtComAH 案号,@param  drpComZXFY 执行法院,@param txtComStartLARQ 立案日期起止,@param  txtComEndLARQ 立案日期结束
	   */
	@RequestMapping(value = "/creditCompany")
	@ResponseBody
    public String execute(@RequestParam("txtComReallyName") String txtComReallyName,@RequestParam("txtComCredentialsNumber") String txtComCredentialsNumber,
			@RequestParam("txtComAH") String txtComAH,@RequestParam("drpComZXFY") String drpComZXFY,@RequestParam("txtComStartLARQ") String txtComStartLARQ,
			@RequestParam("txtComEndLARQ") String txtComEndLARQ, boolean isDebug, String logback){
		
		String creditJson=null;
		 try {
			  
			  log.info("单位未履行生效裁判信息txtComReallyName:{}",URLDecoder.decode(txtComReallyName,"utf-8"));

			  String url =  "http://www.zjsfgkw.cn/Execute/CreditPersonal?txtComReallyName="
			  +URLDecoder.decode(txtComReallyName,"utf-8")+"&txtComCredentialsNumber="+URLDecoder.decode(txtComCredentialsNumber,"utf-8")
					  +"&txtComAH="+URLDecoder.decode(txtComAH,"utf-8")+"&drpComZXFY="+URLDecoder.decode(drpComZXFY,"utf-8")
					  +"&txtComStartLARQ="+URLDecoder.decode(txtComStartLARQ,"utf-8")+"&txtComEndLARQ="+URLDecoder.decode(txtComEndLARQ,"utf-8"); 
			  log.info("单位未履行生效裁判信息url:{}",url);
			  
			  Result<List<CreditJson>> result = zjsfgkwCreditCompanyService.searchCreditCompany(url,isDebug,logback);         
	          
	          Gson gson = new Gson();  		
		      creditJson = gson.toJson(result);
		      log.info("单位未履行生效裁判信息搜索结果:{}", creditJson);	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creditJson;
		
	}
}

package com.crawlermanage.controller.api.iecms;

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
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawler.iecms.domain.json.UserFeedJson;
import com.crawlermanage.service.iecms.IecmsCompanyTaskService;
import com.crawlermanage.service.iecms.IecmsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by yujh2016-03-03
 */
@Controller
@RequestMapping("/api/iecms")
public class IecmsQueryController {
	@Autowired
	private IecmsService iecmsService;
	@Autowired
	private IecmsCompanyTaskService iecmsCompanyTaskService;
    private static final Logger loger = LoggerFactory.getLogger(IecmsQueryController.class);

    @RequestMapping(value = "/querypage")
    @ResponseBody
    public String querypage(boolean isDebug, String logback) {     
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Result<Map<String,String>> ress= iecmsService.searchPageHandle(isDebug, logback); 
    		return gson.toJson(ress);
    	
    }

    /**
	 * @param corp_code
	 * @param corp_name
	 * @param sc_code
	 * @param codea
	 * @param isDebug
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value ="/getcreditiecms")
	@ResponseBody
	public UserFeedJson getCredit(String corp_code, String corp_name, String sc_code,@RequestParam("codea") String codea ,@RequestParam("serializedFileName") String serializedFileName
			, boolean isDebug, String logback) throws IOException {	
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(corp_code==null){
			corp_code="";
		}
		if(corp_name==null){
			corp_name="";
		}
		if(sc_code==null){
			sc_code="";
		}
		Result<String> nameResult = iecmsService.getCname( corp_code, corp_name,sc_code, codea,serializedFileName,isDebug,logback);		
		UserFeedJson ufj=iecmsService.parseResultHtml(nameResult.getData());
		loger.info("解析结果：" + gson.toJson(ufj));			
		return ufj;
	}
	
	 /**
		 * @param corp_code
		 * @param corp_name
		 * @param sc_code
		 * @param codea
		 * @param isDebug
		 * @return
		 * @throws IOException 
		 */
		@RequestMapping(value ="/getcreditiecmss")
		@ResponseBody
		public String getcreditiecmss(String corp_code, String corp_name, String sc_code,@RequestParam("codea") String codea ,@RequestParam("serializedFileName") String serializedFileName
				,boolean isDebug, String logback) throws IOException {	
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			if(corp_code==null){
				corp_code="";
			}
			if(corp_name==null){
				corp_name="";
			}
			if(sc_code==null){
				sc_code="";
			}
			Result<String> nameResult = iecmsService.getCname( corp_code, corp_name,sc_code, codea,serializedFileName,isDebug, logback);		
			UserFeedJson ufj=iecmsService.parseResultHtml(nameResult.getData());
			loger.info("解析结果：" + gson.toJson(ufj));
			Result<UserFeedJson> res=new Result<UserFeedJson>(ufj, nameResult.getHtml(), isDebug);
			return gson.toJson(res);
		}
		
		@RequestMapping("/getDataOnce")
		@ResponseBody
		public String getDataOnce(String keyword, String type, boolean isDebug, String logback) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();	
			if(keyword==null){
				keyword="";
			}
			if(type==null){
				type="";
			}
		
			Result<List<IecmsJson>> result = iecmsService.getDataOnce(keyword ,type, isDebug, logback);	
//			Map<String, Object> excOneceTask = iecmsCompanyTaskService.excOneceTask(type);
//			System.out.println(excOneceTask);
			return gson.toJson(result);
		}

   
}

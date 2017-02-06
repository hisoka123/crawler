package com.crawlermanage.controller.modules;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawler.sxjlcxpt.domain.json.CreditrRecordQueryPlatformJson;
import com.crawler.sxjlcxpt.domain.json.UserFeedJson;
import com.crawlermanage.service.sxjlcxpt.SxjlcxptUserInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/modules/sxjlcxpt")
public class SxjlcxptController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SxjlcxptController.class);
	@Autowired
	private SxjlcxptUserInfoService sxjlcxptUserInfoService;
	
    @Autowired
	   private Env env;
	@Autowired
	@RequestMapping("/tosxjlcxptPage")
	public String tosxjlcxptPage() {
   // Gson gson = new GsonBuilder().setPrettyPrinting().create();
   // Boolean isDebug=true;
     return "/modules/sxjlcxpt/sxjlcxptSearch";
	}
	
	//搜索
		@RequestMapping(value="/getSearchResults",method=RequestMethod.GET)
		public @ResponseBody UserFeedJson getSearchResults(@RequestParam("keyword") String keyword,boolean isDebug){
			UserFeedJson result = null;
			try {				  
				LOGGER.info("失信记录查询平台keyword:{}",URLDecoder.decode(keyword,"utf-8"));
				result = sxjlcxptUserInfoService.searchPage(URLDecoder.decode(keyword,"utf-8"), isDebug);          				  
		          Gson gson = new Gson();  		
			      String usersJson = gson.toJson(result);
			      LOGGER.info("失信记录查询平台:{}", usersJson);					
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return result;
			
		}
		
		   @RequestMapping("/sxjlcxptSearch")
	       public String toSxjlcxptSearch(Model model){
	    	      model.addAttribute("env",env.getEnv());
	    	      return "/modules/sxjlcxpt/sxjlcxptSearchMy";
	       }
		   
		   @RequestMapping(value="/sxjlcxptDetailByInterface",method=RequestMethod.POST)
	       public String toSxjlcxptDetailByInterface(@RequestParam("sxjlcxptDetail") String sxjlcxptDetail,Model model){
	   		
	   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   		   List<CreditrRecordQueryPlatformJson> pcs=gson.fromJson(sxjlcxptDetail,new TypeToken<ArrayList<CreditrRecordQueryPlatformJson>>(){}.getType());
	   		   model.addAttribute("peopleCourts",pcs);
	   		  
	   		   return "/modules/sxjlcxpt/sxjlcxptDetail";
	   	   }
	
		   //调试时用
		   	@RequestMapping(value="/sxjlcxptJson",method=RequestMethod.POST)
		       public String toRenFaWangJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
		   		   
		   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
		   		   
		   		   Result<List<CreditrRecordQueryPlatformJson>> result=new Result<List<CreditrRecordQueryPlatformJson>>();
		   		   
		   		   if(error.equals("eNull")){
		   			   
		   			   result.setData((List<CreditrRecordQueryPlatformJson>)gson.fromJson(data,new TypeToken<List<CreditrRecordQueryPlatformJson>>(){}.getType()));
		   			   
		   		   }else if(data.equals("dNull")){
		   			    
		   			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
		   		   }
		   		  
		   		   model.addAttribute("sxjlcxptDetail",gson.toJson(result));
		   		   
		   		   return "/modules/sxjlcxpt/sxjlcxptJson";
		   	}
}

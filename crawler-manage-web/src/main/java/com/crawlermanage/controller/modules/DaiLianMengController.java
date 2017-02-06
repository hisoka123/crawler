package com.crawlermanage.controller.modules;

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

import com.crawler.dailianmeng.domain.json.LoanUnionFeedJson;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.crawlermanage.service.dailianmeng.DaiLianMengUserInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.dailianmeng.LoanUnion;



@Controller
@RequestMapping("/modules/dailianmeng")
public class DaiLianMengController {

	private static final Logger log = LoggerFactory.getLogger(DaiLianMengController.class);
	
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private DaiLianMengUserInfoService dailianmengUserInfoService;
	
    @Autowired
	   private Env env;
    
    @RequestMapping("")
    public String toDailianmengSearch1(Model model){
 	      model.addAttribute("env",env.getEnv());
 	      return "/modules/dailianmeng/dailianmengSearch";
    }
 
    @RequestMapping("/dailianmengSearch")
    public String toDailianmengSearch2(Model model){
 	      model.addAttribute("env",env.getEnv());
 	      return "/modules/dailianmeng/dailianmengSearch";
    }
    
    @RequestMapping(value="/dailianmengDetailByInterface",method=RequestMethod.POST)
    public String toDailianmengDetailByInterface(@RequestParam("dailianmengDetail") String dailianmengDetail,Model model){
		
		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
		   List<LoanUnionFeedJson> pcs=gson.fromJson(dailianmengDetail,new TypeToken<ArrayList<LoanUnionFeedJson>>(){}.getType());
		   model.addAttribute("loanUnions",pcs);
		  
		   return "/modules/dailianmeng/dailianmengDetail";
	   }

    //调试时用
  	@RequestMapping(value="/dailianmengJson",method=RequestMethod.POST)
    public String toDailianmengJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
  		   
  		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
  		   
  		   Result<List<LoanUnion>> result=new Result<List<LoanUnion>>();
  		   
  		   if(error.equals("eNull")){
  			   
  			   result.setData((List<LoanUnion>)gson.fromJson(data,new TypeToken<List<LoanUnion>>(){}.getType()));
  			   
  		   }else if(data.equals("dNull")){
  			    
  			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
  		   }
  		  
  		   model.addAttribute("dailianmengDetail",gson.toJson(result));
  		   
  		   return "/modules/dailianmeng/dailianmengJson";
  	}
}

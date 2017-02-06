package com.crawlermanage.controller.modules;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.fahaicc.domain.json.FahaiItemFeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.storm.guava.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/modules/fahaicc")
public class FahaiccController {


       private static final Logger log = LoggerFactory.getLogger(FahaiccController.class);

      @Autowired
	  private Env env;
    
      @RequestMapping("/fahaiccSearch")
      public String getSearchPage(Model model){
    	  model.addAttribute("env",env.getEnv());
          return "/modules/fahaicc/fahaiccSearch";
      }
      
      @RequestMapping("")
      public String getSearchPage2(Model model){
    	  model.addAttribute("env",env.getEnv());
          return "/modules/fahaicc/fahaiccSearch";
      }
      
      @RequestMapping(value="/fahaiccDetailByInterface",method=RequestMethod.POST)
      public String toFahaiccDetailByInterface(@RequestParam("fahaiccDetail") String fahaiccDetail,
    		                                   @RequestParam("type") String type,
    		                                   @RequestParam("keyword") String keyword,Model model){
  		
  		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
  		   List<FahaiItemFeed> fahaiccList=gson.fromJson(fahaiccDetail,new TypeToken<ArrayList<FahaiItemFeed>>(){}.getType());
  		   model.addAttribute("fahaiccList",fahaiccList);
  		   model.addAttribute("type",type);
  		   model.addAttribute("keyword",keyword);
  		  
  		   return "/modules/fahaicc/fahaiccDetail";
  	   }

    
    //调试时用
     	@RequestMapping(value="/fahaiccJson",method=RequestMethod.POST)
         public String toFahaiccJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
     		   
     		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
     		   
     		   Result<List<FahaiItemFeed>> result=new Result<List<FahaiItemFeed>>();
     		   
     		   if(error.equals("eNull")){
     			   
     			   result.setData((List<FahaiItemFeed>)gson.fromJson(data,new TypeToken<List<FahaiItemFeed>>(){}.getType()));
     			   
     		   }else if(data.equals("dNull")){
     			    
     			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
     		   }
     		  
     		   model.addAttribute("fahaiccDetail",gson.toJson(result));
     		   
     		   return "/modules/fahaicc/fahaiccJson";
     	}
    
    
    

}

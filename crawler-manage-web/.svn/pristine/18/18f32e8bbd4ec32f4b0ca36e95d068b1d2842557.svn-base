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

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.shixin.domain.json.ShiXinJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.shixin.Shixin;


/**
 * 失信信息查询
 */
@Controller
@RequestMapping("/modules/shixin")
public class ShiXinController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiXinController.class);
	
	 @Autowired
 	 private Env env;
	
	 
	 @RequestMapping("")
	 public String toShixin1(Model model){
		  model.addAttribute("env",env.getEnv());
	      return "/modules/shixin/shixinSearch";
	 }
	 
	 @RequestMapping("/shixinSearch")
	 public String toShixin2(Model model){
		  model.addAttribute("env",env.getEnv());
	      return "/modules/shixin/shixinSearch";
	 }
	 
	 @RequestMapping(value="/shixinDetailByInterface",method=RequestMethod.POST)
     public String toShixinDetailByInterface(@RequestParam("shixinDetail") String shixinDetail,Model model){
 		
 		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
 		   List<ShiXinJson> shixinList=gson.fromJson(shixinDetail,new TypeToken<ArrayList<ShiXinJson>>(){}.getType());
 		   model.addAttribute("shixinList",shixinList);
 		  
 		   return "/modules/shixin/shixinDetail";
 	   }
	 
	 @RequestMapping(value="/shixinDetailByDB",method=RequestMethod.POST)
     public String toShixinDetailByDB(@RequestParam("shixinDetail") String shixinDetail,Model model){
 		
 		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
 		   List<Shixin> shixinList=gson.fromJson(shixinDetail,new TypeToken<ArrayList<Shixin>>(){}.getType());
 		   model.addAttribute("shixinList",shixinList);
 		  
 		   return "/modules/shixin/shixinDetail";
 	   }
	 
	 //调试时用
	   	@RequestMapping(value="/shixinJson",method=RequestMethod.POST)
	       public String toShixinJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
	   		   
	   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   		   
	   		   Result<List<ShiXinJson>> result=new Result<List<ShiXinJson>>();
	   		   
	   		   if(error.equals("eNull")){
	   			   
	   			   result.setData((List<ShiXinJson>)gson.fromJson(data,new TypeToken<List<ShiXinJson>>(){}.getType()));
	   			   
	   		   }else if(data.equals("dNull")){
	   			    
	   			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
	   		   }
	   		  
	   		   model.addAttribute("shixinDetail",gson.toJson(result));
	   		   
	   		   return "/modules/shixin/shixinJson";
	   	}
	
}

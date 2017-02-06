package com.crawlermanage.controller.modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.iautos.domain.json.UserFeedJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.iautos.Iautos;

@Controller
@RequestMapping("/modules/iautos")
public class IautosController {
	
 @Autowired
 private Env env;

 
 @RequestMapping("")
 public String toIautos1(Model model){
	  model.addAttribute("env",env.getEnv());
      return "/modules/iautos/iautosSearch";
 }
 
 @RequestMapping("/iautosSearch")
 public String toIautos2(Model model){
	  model.addAttribute("env",env.getEnv());
      return "/modules/iautos/iautosSearch";
 }
	 
 @RequestMapping(value="/iautosDetailByInterface",method=RequestMethod.POST)
 public String toIautosDetailByInterface(@RequestParam("iautosDetail") String iautosDetail,Model model){
	
	   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   List<UserFeedJson> iautosList=gson.fromJson(iautosDetail,new TypeToken<ArrayList<UserFeedJson>>(){}.getType());
	   model.addAttribute("iautosList",iautosList);
	  
	   return "/modules/iautos/iautosDetail";
   }
	 
 @RequestMapping(value="/iautosDetailByDB",method=RequestMethod.POST)
 public String toShixinDetailByDB(@RequestParam("iautosDetail") String iautosDetail,Model model){
	
	   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   List<Iautos> iautosList=gson.fromJson(iautosDetail,new TypeToken<ArrayList<Iautos>>(){}.getType());
	   model.addAttribute("iautosList",iautosList);
	  
	   return "/modules/iautos/iautosDetail";
   }
 
 //调试时用
@RequestMapping(value="/iautosJson",method=RequestMethod.POST)
public String toShixinJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
	   
	   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   
	   Result<List<UserFeedJson>> result=new Result<List<UserFeedJson>>();
	   
	   if(error.equals("eNull")){
		   
		   result.setData((List<UserFeedJson>)gson.fromJson(data,new TypeToken<List<UserFeedJson>>(){}.getType()));
		   
	   }else if(data.equals("dNull")){
		    
		   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
	   }
	  
	   model.addAttribute("iautosDetail",gson.toJson(result));
	   
	   return "/modules/iautos/iautosJson";
}

	
}

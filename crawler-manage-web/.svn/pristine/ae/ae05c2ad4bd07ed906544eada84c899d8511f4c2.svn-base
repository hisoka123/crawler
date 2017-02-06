package com.crawlermanage.controller.modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.customs.domain.json.Customs;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.renfawang.PeopleCourt;

@Controller
@RequestMapping("/modules/customs")
public class CustomsController {

	   @Autowired
 	   private Env env;
     
	
	   @RequestMapping(value={"","/customsSearch"})
	   public String toCustomsSearch(Model model){
		      model.addAttribute("env",env.getEnv()) ;  
		      return "/modules/customs/customsSearch";
	   }
	   
	   @RequestMapping(value="/customsDetailByInterface",method=RequestMethod.POST)
       public String toCustomsDetailByInterface(@RequestParam("customsDetail") String customsDetail,Model model){
   		
   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
   		   List<Customs> customs=gson.fromJson(customsDetail,new TypeToken<ArrayList<Customs>>(){}.getType());
   		   model.addAttribute("customs",customs);
   		  
   		   return "/modules/customs/customsDetail";
   	   }
	   
	   
	   @RequestMapping(value="/customsDetailByDB",method=RequestMethod.POST)
       public String toCustomsDetailByDB(@RequestParam("customsDetail") String customsDetail,Model model){
   		
   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
   		   List<Customs> customs=gson.fromJson(customsDetail,new TypeToken<ArrayList<Customs>>(){}.getType());
   		   model.addAttribute("customs",customs);
   		  
   		   return "/modules/customs/customsDetail";
   	   }
	   
	 //调试时用
	   	@RequestMapping(value="/customsJson",method=RequestMethod.POST)
	       public String toCustomsJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
	   		   
	   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   		   
	   		   Result<List<Customs>> result=new Result<List<Customs>>();
	   		   
	   		   if(error.equals("eNull")){
	   			   
	   			   result.setData((List<Customs>)gson.fromJson(data,new TypeToken<List<Customs>>(){}.getType()));
	   			   
	   		   }else if(data.equals("dNull")){
	   			    
	   			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
	   		   }
	   		  
	   		   model.addAttribute("customsDetail",gson.toJson(result));
	   		   
	   		   return "/modules/customs/customsJson";
	   	}

}

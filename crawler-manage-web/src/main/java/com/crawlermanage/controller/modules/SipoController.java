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
import com.crawler.sipo.domain.json.SearchEngineSipo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.sipo.Sipo;

@Controller
@RequestMapping("/modules/sipo")
public class SipoController {
	
 @Autowired
 private Env env;

 
 @RequestMapping("")
 public String toSipo1(Model model){
	  model.addAttribute("env",env.getEnv());
      return "/modules/sipo/sipoSearch";
 }
 
 @RequestMapping("/sipoSearch")
 public String toSipo2(Model model){
	  model.addAttribute("env",env.getEnv());
      return "/modules/sipo/sipoSearch";
 }
	 
 @RequestMapping(value="/sipoDetailByInterface",method=RequestMethod.POST)
 public String toShixinDetailByInterface(@RequestParam("siposDetail") String siposDetail,Model model){
	
	   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   List<SearchEngineSipo> siposList=gson.fromJson(siposDetail,new TypeToken<ArrayList<SearchEngineSipo>>(){}.getType());
	   model.addAttribute("siposList",siposList);
	
	  
	   return "/modules/sipo/siposDetail";
   }
	 
 @RequestMapping(value="/siposDetailByDB",method=RequestMethod.POST)
 public String toSipoDetailByDB(@RequestParam("siposDetail") String siposDetail,Model model){
	
	   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   List<Sipo> siposList=gson.fromJson(siposDetail,new TypeToken<ArrayList<Sipo>>(){}.getType());
	   model.addAttribute("siposList",siposList);
	  
	   return "/modules/sipo/siposDetail";
   }
 
 //调试时用
@RequestMapping(value="/sipoJson",method=RequestMethod.POST)
public String toSipoJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
	   
	   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   
	   Result<List<SearchEngineSipo>> result=new Result<List<SearchEngineSipo>>();
	   
	   if(error.equals("eNull")){
		   
		   result.setData((List<SearchEngineSipo>)gson.fromJson(data,new TypeToken<List<SearchEngineSipo>>(){}.getType()));
		   
	   }else if(data.equals("dNull")){
		    
		   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
	   }
	  
	   model.addAttribute("siposDetail",gson.toJson(result));
	   
	   return "/modules/sipo/sipoJson";
}

	
}

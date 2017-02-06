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
import com.crawler.renfawang.domain.json.PeopleCourtFeedJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.renfawang.PeopleCourt;


@Controller
@RequestMapping("/modules/renfawang")
public class RenFaWangController {
	
	
       private static final Logger loger = LoggerFactory.getLogger(RenFaWangController.class);
    
       @Autowired
   	   private Env env;
       
       
       
       @RequestMapping("")
       public String toRenFaWangSearch1(Model model){
    	      model.addAttribute("env",env.getEnv());
    	      return "/modules/renfawang/renfawangSearch";
       }
    
       @RequestMapping("/renfawangSearch")
       public String toRenFaWangSearch2(Model model){
    	      model.addAttribute("env",env.getEnv());
    	      return "/modules/renfawang/renfawangSearch";
       }
	
       @RequestMapping(value="/renfawangDetailByInterface",method=RequestMethod.POST)
       public String toRenFaWangDetailByInterface(@RequestParam("renfawangDetail") String renfawangDetail,Model model){
   		
   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
   		   List<PeopleCourtFeedJson> pcs=gson.fromJson(renfawangDetail,new TypeToken<ArrayList<PeopleCourtFeedJson>>(){}.getType());
   		   model.addAttribute("peopleCourts",pcs);
   		  
   		   return "/modules/renfawang/renfawangDetail";
   	   }
       
       @RequestMapping(value="/renfawangDetailByDB",method=RequestMethod.POST)
       public String toRenFaWangDetailByDB(@RequestParam("renfawangDetail") String renfawangDetail,Model model){
   		
   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
   		   List<PeopleCourt> pcs=gson.fromJson(renfawangDetail,new TypeToken<ArrayList<PeopleCourt>>(){}.getType());
   		   model.addAttribute("peopleCourts",pcs);
   		  
   		   return "/modules/renfawang/renfawangDetail";
   	   }
       
     //调试时用
   	@RequestMapping(value="/renfawangJson",method=RequestMethod.POST)
       public String toRenFaWangJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
   		   
   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
   		   
   		   Result<List<PeopleCourtFeedJson>> result=new Result<List<PeopleCourtFeedJson>>();
   		   
   		   if(error.equals("eNull")){
   			   
   			   result.setData((List<PeopleCourtFeedJson>)gson.fromJson(data,new TypeToken<List<PeopleCourtFeedJson>>(){}.getType()));
   			   
   		   }else if(data.equals("dNull")){
   			    
   			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
   		   }
   		  
   		   model.addAttribute("renfawangDetail",gson.toJson(result));
   		   
   		   return "/modules/renfawang/renfawangJson";
   	}

}

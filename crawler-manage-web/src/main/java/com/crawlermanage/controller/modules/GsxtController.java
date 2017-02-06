package com.crawlermanage.controller.modules;


import javax.servlet.http.HttpServletRequest;

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
import com.crawler.gsxt.domain.json.AicFeedJson;
import com.crawlermanage.service.gsxt.GsxtService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;



@Controller
@RequestMapping("/modules/gsxt")
public class GsxtController {

	private static final Logger log = LoggerFactory.getLogger(GsxtController.class);
	@Autowired
	private GsxtService gsxtService;
	
	@Autowired
	private Env env;
	
	@RequestMapping("/gsxtSearch")
	public String getSearchPage(HttpServletRequest req,Model model){
		
		   model.addAttribute("env",env.getEnv());
		   return "/modules/gsxt/gsxtSearch";
	}
	@RequestMapping("")
	public String getSearchPage2(HttpServletRequest req,Model model){
		
		   model.addAttribute("env",env.getEnv());
		   return "/modules/gsxt/gsxtSearch";
	}
	
	@RequestMapping(value="/gsxtDetail",method=RequestMethod.POST)
	public String toGSXTDetail(@RequestParam("gsxtDetail") String gsxtDetail,Model model){
		
		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
		   AicFeedJson aicFeedJson=gson.fromJson(gsxtDetail,new TypeToken<AicFeedJson>(){}.getType());
		   model.addAttribute("aicFeedJson",aicFeedJson);
		  
		   return "/modules/gsxt/gsxtDetail";
	}
	
	
	//调试时用
	@RequestMapping(value="/gsxtJson",method=RequestMethod.POST)
    public String toGSXTJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
		   
		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
		   
		   Result<AicFeedJson> result=new Result<AicFeedJson>();
		   
		   if(error.equals("eNull")){
			   
			   result.setData((AicFeedJson)gson.fromJson(data,new TypeToken<AicFeedJson>(){}.getType()));
			   
		   }else if(data.equals("dNull")){
			    
			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
		   }
		  
		   model.addAttribute("gsxtDetail",gson.toJson(result));
		   
		   return "/modules/gsxt/gsxtJson";
	}
}

package com.crawlermanage.controller.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawler.renfawang.domain.json.PeopleCourtFeedJson;
import com.crawlermanage.service.iecms.IecmsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/modules/iecms")
public class IecmsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IecmsController.class);
	@Autowired
	private IecmsService iecmsService;
	
    @Autowired
	   private Env env;
//    
	@RequestMapping(value={"", "iecmsAuth"})
	public ModelAndView redirectToSearch1() {
	LOGGER.info("==============IecmsController.getSearchPage start!=================");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Boolean isDebug=true;
    Result<Map<String,String>> ress= iecmsService.searchPageHandle(isDebug, "");
    ModelAndView mdv = new ModelAndView();
    mdv.setViewName("/modules/iecms/iecmsAuth");
    if(ress!=null){
    String serializedFileName=ress.getData().get("serializedFileName");
    String codeImageUrl=ress.getData().get("codeImageUrl");
     mdv.addObject("serializedFileName", serializedFileName);
     mdv.addObject("codeImageUrl", codeImageUrl);
    } 
		return mdv;
	}
	
	   @RequestMapping("/iecmsSearch")
       public String toIecmsSearch(Model model){
    	      model.addAttribute("env",env.getEnv());
    	      return "/modules/iecms/iecmsSearch";
       }
	   
	   @RequestMapping(value="/iecmsDetailByInterface",method=RequestMethod.POST)
       public String toIecmsDetailByInterface(@RequestParam("iecmsDetail") String iecmsDetail,Model model){
   		
   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
   		   List<IecmsJson> pcs=gson.fromJson(iecmsDetail,new TypeToken<ArrayList<IecmsJson>>(){}.getType());
   		   model.addAttribute("peopleCourts",pcs);
   		  
   		   return "/modules/iecms/iecmsDetail";
   	   }
       
	   //调试时用
	   	@RequestMapping(value="/iecmsJson",method=RequestMethod.POST)
	       public String toRenFaWangJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
	   		   
	   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   		   
	   		   Result<List<IecmsJson>> result=new Result<List<IecmsJson>>();
	   		   
	   		   if(error.equals("eNull")){
	   			   
	   			   result.setData((List<IecmsJson>)gson.fromJson(data,new TypeToken<List<IecmsJson>>(){}.getType()));
	   			   
	   		   }else if(data.equals("dNull")){
	   			    
	   			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
	   		   }
	   		  
	   		   model.addAttribute("iecmsDetail",gson.toJson(result));
	   		   
	   		   return "/modules/iecms/iecmsJson";
	   	}

	
}

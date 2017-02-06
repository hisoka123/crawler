package com.crawlermanage.controller.modules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.gsxt.domain.json.AicFeedJson;
import com.crawlermanage.dao.result.GsxtResult;
import com.crawlermanage.service.gsxt.GsxtDBService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.gsxt.Company;
import com.module.dao.entity.gsxt.TResultJson;


@Controller
@RequestMapping("/modules/gsxt")
public class AjaxGsxtController {

	   private static final Logger log=LoggerFactory.getLogger(AjaxGsxtController.class);
	   
	   @Autowired
	   GsxtDBService gsxtDBService;
	   
	   
	   
	
	   //核查企业
	   @RequestMapping(value="/checkGsxtCompany", method = { RequestMethod.GET, RequestMethod.POST })
	   public @ResponseBody  GsxtResult checkGsxtCompany(@RequestParam("city") String city,@RequestParam("name") String name){
		   
		      GsxtResult gsxtResult=new GsxtResult();
		   
		      Company company=gsxtDBService.companyIsExist(city, name);
		      
		     // log.info("checkGsxtCompany:"+company);
		      
		      Gson gson=new Gson();
		      
		      if(company!=null){
		    	  
		    	     
		    	     gsxtResult.setExistCode(1);
		    	     gsxtResult.setCompany_id(company.getId());
		    	     
		    	     List<TResultJson> tResultJson=gsxtDBService.getTResultJson(company.getId()); //查看t_result_json表中是否有数据（包含旧的）
		    	     
		    	    
		    	   //  log.info("tResultJson:"+gson.toJson(tResultJson));
		    	     
		    	     if(tResultJson.size()>0){
		    	    	
		    	    	  gsxtResult.setState(1);
		    	    	 
		    	    	  AicFeedJson result=gson.fromJson(tResultJson.get(0).getResult(),new TypeToken<AicFeedJson>(){}.getType());
		    	    	   
		    	    	  gsxtResult.setAicFeedJson(result);
		    	    	 
		    	     }else{
		    	    	  if(company.getState()==7){
		    	    		    gsxtResult.setState(company.getState());
		    	    	  }else{
		    	    		    gsxtResult.setState(0);
		    	    	  }
		    	    	  
		    	     }
		    	    
		      }else{
		    	     gsxtResult.setExistCode(0);
		    	     gsxtResult.setState(0);
		      }
		   
		      
		      return gsxtResult;
	   }
}

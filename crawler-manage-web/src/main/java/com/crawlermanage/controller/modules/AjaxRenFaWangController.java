package com.crawlermanage.controller.modules;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.dao.result.TimeTaskSearchResult;
import com.crawlermanage.service.renfawang.RenFaWangDBService;
import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.module.dao.entity.renfawang.CompanyOrID;
import com.module.dao.entity.renfawang.PeopleCourt;


@Controller
@RequestMapping("/modules/renfawang")
public class AjaxRenFaWangController {

	   private static final Logger log=LoggerFactory.getLogger(AjaxRenFaWangController.class);
	
	   @Autowired
	   RenFaWangDBService renFaWangDBService;
	
	
	 //核查名称、号码
	   @RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
	   public @ResponseBody TimeTaskSearchResult<List<PeopleCourt>>  checkIsExist(@RequestParam("queryType") String queryType,@RequestParam("content") String content){
		   
		          TimeTaskSearchResult<List<PeopleCourt>> result=new TimeTaskSearchResult<List<PeopleCourt>>();
		       
		          log.info("人法网核查：queryType:"+queryType+"  content:"+content);
		          List<CompanyOrID> coiList=renFaWangDBService.checkIsExistBYName(queryType, content);
		          
		          if(coiList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<coiList.size();i++){
		    	        	      idList.add(coiList.get(i).getId());
		    	          }
		    	    
		    	          result.setTimetask_id(coiList.get(0).getId());
		    	          
		    	          List<PeopleCourt> pcList=renFaWangDBService.getDetailByCOI(idList);
		    	   
		    	           if(pcList.size()>0){
		    	    	         result.setState(1);
		    	    	         result.setTtSearchResult(pcList);
		    	           }else{
		    	    	          if(coiList.get(0).getState()==7){
		    	    		            result.setState(coiList.get(0).getState());
		    	    	          }else{
		    	    		            result.setState(0);
		    	    	          }
		    	           }
		          }else{
		    	            result.setExistCode(0);
		    	            result.setState(0);
		         }
		      
		         return result;
	   }
	
	   
	   
	   
	   
	   
	   
	   
	
}

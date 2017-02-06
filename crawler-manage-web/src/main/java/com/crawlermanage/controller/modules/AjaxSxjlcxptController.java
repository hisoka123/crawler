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
import com.crawlermanage.service.sxjlcxpt.SxjlcxptDBService;
import com.module.dao.entity.crqp.CreditrRecordQueryPlatform;
import com.module.dao.entity.crqp.CreditrRecordQueryPlatformCompany;
import com.module.dao.entity.iecms.Iecms;


@Controller
@RequestMapping("/modules/sxjlcxpt")
public class AjaxSxjlcxptController {

	   private static final Logger log=LoggerFactory.getLogger(AjaxSxjlcxptController.class);
	
	   @Autowired
	   SxjlcxptDBService sxjlcxptDBService;
	
	
	 //核查名称、号码
	   @RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
	   public @ResponseBody TimeTaskSearchResult<List<CreditrRecordQueryPlatform>>  checkIsExist(@RequestParam("content") String content){
		   
		          TimeTaskSearchResult<List<CreditrRecordQueryPlatform>> result=new TimeTaskSearchResult<List<CreditrRecordQueryPlatform>>();
		       
		          log.info("核查： content:"+content);
		          List<CreditrRecordQueryPlatformCompany> coiList=sxjlcxptDBService.checkIsExistBYName(content);
		          
		          if(coiList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<coiList.size();i++){
		    	        	      idList.add(coiList.get(i).getId());
		    	          }
		    	    
		    	          result.setTimetask_id(coiList.get(0).getId());
		    	          
		    	          List<CreditrRecordQueryPlatform> pcList=sxjlcxptDBService.getDetailByCOI(idList);
		    	   
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

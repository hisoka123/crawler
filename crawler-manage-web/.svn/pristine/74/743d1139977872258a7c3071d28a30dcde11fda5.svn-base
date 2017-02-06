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
import com.crawlermanage.service.iecms.IecmsDBService;
import com.module.dao.entity.iecms.Iecms;
import com.module.dao.entity.iecms.IecmsCompany;


@Controller
@RequestMapping("/modules/iecms")
public class AjaxIecmsController {

	   private static final Logger log=LoggerFactory.getLogger(AjaxIecmsController.class);
	
	   @Autowired
	   IecmsDBService iecmsDBService;
	
	
	 //核查名称、号码
	   @RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
	   public @ResponseBody TimeTaskSearchResult<List<Iecms>>  checkIsExist(@RequestParam("queryType") String queryType,@RequestParam("content") String content){
		   
		          TimeTaskSearchResult<List<Iecms>> result=new TimeTaskSearchResult<List<Iecms>>();
		       
		          log.info("核查：queryType:"+queryType+"  content:"+content);
		          List<IecmsCompany> coiList=iecmsDBService.checkIsExistBYName(queryType, content);
		          
		          if(coiList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<coiList.size();i++){
		    	        	      idList.add(coiList.get(i).getId());
		    	          }
		    	    
		    	          result.setTimetask_id(coiList.get(0).getId());
		    	          
		    	          List<Iecms> pcList=iecmsDBService.getDetailByCOI(idList);
		    	   
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

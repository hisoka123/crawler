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
import com.crawlermanage.service.fahaicc.FahaiccDBService;
import com.module.dao.entity.fahaicc.FahaiccResult;
import com.module.dao.entity.fahaicc.FahaiccRoot;

@Controller
@RequestMapping("/modules/fahaicc")
public class AjaxFahaiccController {
	
	   private static final Logger log=LoggerFactory.getLogger(AjaxFahaiccController.class);
	   
	   @Autowired
	   FahaiccDBService fahaiccDBService;
	   
	
	//核查名称、号码
	   @RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
	   public @ResponseBody TimeTaskSearchResult<List<FahaiccResult>>  checkIsExist(@RequestParam("type") String type,
			                                                                      @RequestParam("content") String content){
		   
		          TimeTaskSearchResult<List<FahaiccResult>> result=new TimeTaskSearchResult<List<FahaiccResult>>();
		       
		          log.info("法海风控核查：type:"+type+"  content:"+content);
		          List<FahaiccRoot> fahaiccList=fahaiccDBService.checkIsExistBYName(type, content);
		          
		          if(fahaiccList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<fahaiccList.size();i++){
		    	        	      idList.add(fahaiccList.get(i).getId());
		    	          }
		    	    
		    	          result.setTimetask_id(fahaiccList.get(0).getId());
		    	          
		    	          List<FahaiccResult> fahaiccResultList=fahaiccDBService.getDetailByRootId(idList);
		    	   
		    	           if(fahaiccResultList.size()>0){
		    	    	         result.setState(1);
		    	    	         result.setTtSearchResult(fahaiccResultList);
		    	           }else{
		    	    	          if(fahaiccList.get(0).getState()==7){
		    	    		            result.setState(fahaiccList.get(0).getState());
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

package com.crawlermanage.controller.modules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.customs.domain.json.Customs;
import com.crawlermanage.dao.result.TimeTaskSearchResult;
import com.crawlermanage.service.customs.CustomsDBService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.customs.CUResultJson;
import com.module.dao.entity.customs.CustomsCompany;

@Controller
@RequestMapping("/modules/customs")
public class AjaxCustomsController {
	
	   private static final Logger log=LoggerFactory.getLogger(AjaxCustomsController.class);

	   @Autowired
	   CustomsDBService customsDBService;
	   
	   
	   
	//核查名称、号码
	   @RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
	   public @ResponseBody TimeTaskSearchResult<List<Customs>>  checkIsExist(@RequestParam("content") String content){
		   
		          TimeTaskSearchResult<List<Customs>> result=new TimeTaskSearchResult<List<Customs>>();
		       
		          log.info("海关网核查： content:"+content);
		          List<CustomsCompany> ccList=customsDBService.checkIsExistBYName(content);
		          
		          if(ccList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<ccList.size();i++){
		    	        	      idList.add(ccList.get(i).getId());
		    	          }
		    	    
		    	          result.setTimetask_id(ccList.get(0).getId());
		    	          
		    	          List<CUResultJson> pcList=customsDBService.getDetailByCcId(idList);
		    	   
		    	           if(pcList.size()>0){
		    	    	         result.setState(1);
		    	    	         List<Customs> cusList=new ArrayList<Customs>();
		    	    	         Gson gson=new Gson();
		    	    	         
		    	    	         for(Iterator<CUResultJson> iter=pcList.iterator();iter.hasNext();){
		    	    	        	    String resultStr=iter.next().getResult();
		    	    	        	    System.out.println(resultStr);
		    	    	        	    Customs cus=gson.fromJson(resultStr.substring(1,resultStr.length()-1),new TypeToken<Customs>(){}.getType());
		    	    	        	    cusList.add(cus);
		    	    	         }
		    	    	         result.setTtSearchResult(cusList);
		    	           }else{
		    	    	          if(ccList.get(0).getState()==7){
		    	    		            result.setState(ccList.get(0).getState());
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

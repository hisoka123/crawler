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
import com.crawlermanage.service.shixin.ShixinDBService;
import com.module.dao.entity.renfawang.CompanyOrID;
import com.module.dao.entity.renfawang.PeopleCourt;
import com.module.dao.entity.shixin.Shixin;
import com.module.dao.entity.shixin.ShixinKeyword;


@Controller
@RequestMapping("/modules/shixin")
public class AjaxShixinController {

	   private static final Logger log=LoggerFactory.getLogger(AjaxShixinController.class);
	   
	   
	   @Autowired
	   ShixinDBService shixinDBService;
	   
	   
	   
	   //核查名称、号码
	   @RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
	   public @ResponseBody TimeTaskSearchResult<List<Shixin>>  checkIsExist(@RequestParam("type") String type,
			                                                                 @RequestParam("keyword") String keyword,
			                                                                 @RequestParam("cardNum") String cardNum){
		   
		          TimeTaskSearchResult<List<Shixin>> result=new TimeTaskSearchResult<List<Shixin>>();
		       
		          log.info("失信网核查：queryType:"+type+"  name:"+keyword+" idCard:"+cardNum);
		          
		          
		          List<ShixinKeyword> sxkList=shixinDBService.checkIsExist(type,keyword,cardNum);
		          
		          if(sxkList.size()>0){
		    	   
		    	          result.setExistCode(1);
		    	          
		    	          List<Long> idList=new ArrayList<Long>();
		    	          
		    	          for(int i=0;i<sxkList.size();i++){
		    	        	      idList.add(sxkList.get(i).getId());
		    	          }
		    	          
		    	          result.setTimetask_id(sxkList.get(0).getId());
		    	          
		    	          List<Shixin> sxList=shixinDBService.getDetailByTTId(idList);
		    	   
		    	          System.out.println(sxList.size());
		    	           if(sxList.size()>0){
		    	    	         result.setState(1);
		    	    	         result.setTtSearchResult(sxList);
		    	           }else{
		    	    	          if(sxkList.get(0).getState()==7){
		    	    		            result.setState(sxkList.get(0).getState());
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

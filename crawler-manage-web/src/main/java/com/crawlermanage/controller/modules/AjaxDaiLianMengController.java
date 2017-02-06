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
import com.crawlermanage.service.dailianmeng.DaiLianMengDBService;
import com.module.dao.entity.dailianmeng.CompanyOrIdentity;
import com.module.dao.entity.dailianmeng.LoanUnion;
import com.module.dao.entity.renfawang.PeopleCourt;



@Controller
@RequestMapping("/modules/dailianmeng")
public class AjaxDaiLianMengController {

	private static final Logger log = LoggerFactory.getLogger(AjaxDaiLianMengController.class);
	
	@Autowired
	DaiLianMengDBService daiLianMengDBService;


	//核查名称、号码
	@RequestMapping(value="/checkIsExist",method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TimeTaskSearchResult<List<LoanUnion>>  checkIsExist(@RequestParam("queryType") String queryType,@RequestParam("content") String content){
   
		TimeTaskSearchResult<List<LoanUnion>> result=new TimeTaskSearchResult<List<LoanUnion>>();
       
		log.info("核查：queryType:"+queryType+"  content:"+content);
		List<CompanyOrIdentity> coiList = daiLianMengDBService.checkIsExistBYName(queryType, content);
	          
		if(coiList.size()>0){
	   
			result.setExistCode(1);
  
			List<Long> idList=new ArrayList<Long>();
			  
			for(int i=0;i<coiList.size();i++){
				idList.add(coiList.get(i).getId());
			}
	    
			result.setTimetask_id(coiList.get(0).getId());
	          
			List<LoanUnion> pcList = daiLianMengDBService.getDetailByCOI(idList);
	   
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

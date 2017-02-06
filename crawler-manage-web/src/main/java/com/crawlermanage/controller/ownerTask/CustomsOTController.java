package com.crawlermanage.controller.ownerTask;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.customs.CustomsDBService;
import com.crawlermanage.service.ownerTask.CustomsOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.module.dao.entity.customs.CustomsCompany;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskCustoms;
import com.module.dao.entity.ownerTask.OwnerTaskPeopleCourt;
import com.module.dao.entity.renfawang.CompanyOrID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中国海关网
 */
@Controller
@RequestMapping("/ownerTask")
public class CustomsOTController {
    private static final Logger log = Logger.getLogger(CustomsOTController.class);

    @Autowired
    private CustomsOTService customsOTService;
    
    @Autowired
    CustomsDBService customsDBService;
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;

    /**
     * 页面跳转 (第一车网)
     * @return
     */
    @RequestMapping("/customs")
    public String toCustoms(){
        return "/ownerTask/customs";
    }

    /**
     * 根据条件查询失信网任务
     *
     * @param loginName
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchCustomsTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskCustoms> searchCustomsTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                           @RequestParam("loginName") String loginName,@RequestParam("status") String status,
                                           @RequestParam("keyWord") String keyWord) {
        log.info("=========searchCustomsTask==========");

        List<Integer> stateList=new ArrayList<Integer>();

        if(status.equals("searchStatus_success")){
            stateList.add(1);
            stateList.add(7);
        }else if(status.equals("searchStatus_waiting")){
            stateList.add(0);
        }else if(status.equals("searchStatus_inProcess")){
            stateList.add(-3);
        }else if (status.equals("searchStatus_feedback")) {
            stateList.add(1);
            stateList.add(7);
            stateList.add(0);
            stateList.add(-3);
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loginName",loginName);
        paramMap.put("status",status);
        paramMap.put("stateList",stateList);
        paramMap.put("keyWord",keyWord);

        System.out.println("====param== loginName: " + loginName + ",stateList: " + stateList + ", keyWord: " + keyWord);

        Page<OwnerTaskCustoms> ownerTaskCustomsPage = customsOTService.searchCustomsOT(paramMap, currentPage, pageSize, null);
        List<OwnerTaskCustoms> resultList = ownerTaskCustomsPage.getContent();
        System.out.println("====searchCustoms==" + resultList + "===total:" + ownerTaskCustomsPage.getTotalElements());

        return ownerTaskCustomsPage;
    }
    
  //创建新任务
    @RequestMapping(value="/customsJoinTask",method=RequestMethod.POST)
    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建海关网任务：  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   
    	   //重复新任务，返回
    	   for(OwnerTaskCustoms pcExist:customsOTService.getAllPeopleCourtTask(loginName)){
    		      if(pcExist.getKeyWord().equals(content)){
    		    	     return returnPeopleCourtResult(content,2);
    		      }
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该企业,
    		   
    		   
    		   CustomsCompany coi=new CustomsCompany();
		      
		       coi.setName(content);
		       coi.setState(0);
		       coi.setPriority(1);
		       coi.setNum(0);
		       
		       CustomsCompany recc=customsDBService.saveTimeTask(coi);
		       if(recc!=null){
		    	      timetask_id=recc.getId();
		       }else{
		    	      return returnPeopleCourtResult(content,0);
		       }
	    	  
	      }else{
	    	     CustomsCompany  coi2=customsDBService.getTimeTask(timetaskID);
	    	     coi2.setPriority(1);
	    	     coi2.setNum(0);
	    	    if(customsDBService.saveTimeTask(coi2)==null){
	    	    	 return returnPeopleCourtResult(content,0);
	    	    }
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,
    	   OwnerTaskCustoms otc=new OwnerTaskCustoms();
    	   
    	   otc.setKeyWord(content);
    	   otc.setLoginName(loginName);
    	   otc.setState(0);
    	   otc.setTimeTaskId(timetask_id);
    	   otc.setCreateTaskDate(date);
    	
    	   OwnerTaskCustoms reotc=customsOTService.joinTask(otc);
    	   if(reotc==null){
	    	    return returnPeopleCourtResult(content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "海关网");
    	   if(reTaskAll==null){
    		   return returnPeopleCourtResult(content,0);
    	   }
    	   
    	   
    	   return returnPeopleCourtResult(content,1);
    }
    
     //创建任务,返回结果
	   public OwnerTaskResult returnPeopleCourtResult(String content,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
	               result.setMessage("海关网创建新任务：--"+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("海关网创建新任务：--"+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("海关网新任务：--"+content+" 已存在");
	          }
		       
		     return result;
		   
	   }
}

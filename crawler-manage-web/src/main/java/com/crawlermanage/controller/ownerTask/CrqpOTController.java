package com.crawlermanage.controller.ownerTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.ownerTask.CrqpOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.crawlermanage.service.sxjlcxpt.SxjlcxptDBService;
import com.module.dao.entity.crqp.CreditrRecordQueryPlatformCompany;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskCrqp;

/**
 * 失信记录查询平台
 */
@Controller
@RequestMapping("/ownerTask")
public class CrqpOTController {
    private static final Logger log = Logger.getLogger(CrqpOTController.class);

    @Autowired
    private CrqpOTService crqpOTService;
    
    @Autowired
    private SxjlcxptDBService crqpDBService;
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;

    /**
     * 页面跳转 (认证信息查询网)
     * @return
     */
    @RequestMapping("/crqptask")
    public String toCrqpTask(){
        return "/ownerTask/crqptask";
    }

    /**
     * 根据条件查询认证信息查询网任务
     *
     * @param loginName
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchCrqpTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskCrqp> searchCrqpTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                       @RequestParam("loginName") String loginName,@RequestParam("status") String status,
                                                       @RequestParam("keyWord") String keyWord) {
        log.info("=========searchCrqpTask==========");

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

        Page<OwnerTaskCrqp> ownerTaskCrqpPage = crqpOTService.searchCrqpOT(paramMap, currentPage, pageSize, null);
        List<OwnerTaskCrqp> resultList = ownerTaskCrqpPage.getContent();
        System.out.println("====searchCrqp==" + resultList + "===total:" + ownerTaskCrqpPage.getTotalElements());

        return ownerTaskCrqpPage;
    }
    
    //创建新任务sxjlcxptJoinTask
    @RequestMapping(value="/sxjlcxptJoinTask",method=RequestMethod.POST)
    public @ResponseBody OwnerTaskResult joinTask(
    		                                      @RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建对外贸易任务： content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;    	
    	   
    	   
    	   //重复新任务，返回
    	   //重复新任务，返回crqpOTService
    	   for(OwnerTaskCrqp pcExist:crqpOTService.getAllIecmsTask(loginName)){
    		      if( pcExist.getKeyWord().equals(content)){
    		    	     return returnSxjlcxptResult(content,2);
    		      }
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该企业,写入company表中    		   
    		      		   
    		   CreditrRecordQueryPlatformCompany coi=new CreditrRecordQueryPlatformCompany();
		       coi.setKeyword(content);
		       coi.setState(0);
		       coi.setPriority(1);
		       coi.setNum(0);
		       
		       CreditrRecordQueryPlatformCompany reCoi=crqpDBService.saveTimeTask(coi);
		       if(reCoi!=null){
		    	      timetask_id=reCoi.getId();
		       }else{
		    	   return returnSxjlcxptResult(content,0);
		       }
	    	  
	      }else{
	    	  CreditrRecordQueryPlatformCompany  coi2=crqpDBService.getTimeTask(timetaskID);
	    	     coi2.setPriority(1);
	    	     coi2.setNum(0);
	    	    if(crqpDBService.saveTimeTask(coi2)==null){
	    	    	return returnSxjlcxptResult(content,0);
	    	    }
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,表OwnerTaskCrqp
    	   OwnerTaskCrqp pc=new OwnerTaskCrqp();    	
    	   pc.setKeyWord(content);
    	   pc.setLoginName(loginName);
    	   pc.setState(0);
    	   pc.setTimeTaskId(timetask_id);    	      
    	   pc.setCreateTaskDate(date);
    	
    	   OwnerTaskCrqp rePc=crqpOTService.joinTask(pc);
    	   if(rePc==null){
    		   return returnSxjlcxptResult(content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "失信记录平台");
    	   if(reTaskAll==null){
    		   return returnSxjlcxptResult(content,0);
    	   }
    	   
    	   
    	   return returnSxjlcxptResult(content,1);
    }
    
     //创建任务,返回结果
	   public OwnerTaskResult returnSxjlcxptResult(String content,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
	               result.setMessage("失信记录查询建新任务：--"+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("失信记录查询创建新任务：--"+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("失信记录查询新任务：--"+content+" 已存在");
	          }
		       
		     return result;
		   
	   }



}

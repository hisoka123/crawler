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
import com.crawlermanage.service.cnca.CncaDBService;
import com.crawlermanage.service.ownerTask.AuthenticateOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.module.dao.entity.cnca.CertificateCompany;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskAuthenticate;

/**
 * 失信记录查询平台
 */
@Controller
@RequestMapping("/ownerTask")
public class AuthenticateOTController {
    private static final Logger log = Logger.getLogger(AuthenticateOTController.class);

    @Autowired
    private AuthenticateOTService authenticateOTService;

    @Autowired
    private CncaDBService cncaDBService;
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;
    
    /**
     * 页面跳转 (失信记录查询平台)
     * @return
     */
    @RequestMapping("/authenticate")
    public String toAuthenticate(){
        return "/ownerTask/authenticate";
    }

    /**
     * 根据条件查询失信记录查询平台任务
     *
     * @param loginName
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchAuthenticateTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskAuthenticate> searchAuthenticateTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                             @RequestParam("loginName") String loginName,@RequestParam("status") String status,
                                             @RequestParam("keyWord") String keyWord) {
        log.info("=========searchAuthenticateTask==========");

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

        Page<OwnerTaskAuthenticate> ownerTaskAuthenticatePage = authenticateOTService.searchAuthenticateOT(paramMap, currentPage, pageSize, null);
        List<OwnerTaskAuthenticate> resultList = ownerTaskAuthenticatePage.getContent();
        System.out.println("====searchAuthenticate==" + resultList + "===total:" + ownerTaskAuthenticatePage.getTotalElements());

        return ownerTaskAuthenticatePage;
    }
    
    //创建新任务
    @RequestMapping(value="/cncaJoinTask",method=RequestMethod.POST)
    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建认证网任务： content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   
    	   //重复新任务，返回
    	   for(OwnerTaskAuthenticate pcExist:authenticateOTService.getAllAuthenticateTask(loginName)){
    		      if(pcExist.getKeyWord().equals(content)){
    		    	     return returnCncaResult(content,2);
    		      }
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该企业,写入company表中
    		   
    		   CertificateCompany coi = new CertificateCompany();
		       coi.setName(content);
		       coi.setState(0);
		       coi.setNum(0);
		       coi.setTotalNum(0);
		       coi.setPriority(1);
		       
		       CertificateCompany reCoi = cncaDBService.saveTimeTask(coi);
		       if(reCoi!=null){
		    	      timetask_id=reCoi.getId();
		       }else{
		    	      return returnCncaResult(content,0);
		       }
	    	  
	      }else{
	    	    CertificateCompany  coi2 = cncaDBService.getTimeTask(timetaskID);
	    	  	coi2.setNum(0);
	    	  	coi2.setPriority(1);
				if(cncaDBService.saveTimeTask(coi2)==null){
					 return returnCncaResult(content,0);
				}
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,ownertask_creditunion表
    	   OwnerTaskAuthenticate pc=new OwnerTaskAuthenticate();
    	   pc.setKeyWord(content);
    	   pc.setLoginName(loginName);
    	   pc.setState(0);
    	   pc.setTimeTaskId(timetask_id);
    	   pc.setCreateTaskDate(date);
    	
    	   OwnerTaskAuthenticate rePc = authenticateOTService.joinTask(pc);
    	   if(rePc==null){
	    	    return returnCncaResult(content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll = ownerTaskAllService.save(loginName, "认证网");
    	   if(reTaskAll==null){
    		   return returnCncaResult(content,0);
    	   }
    	   
    	   
    	   return returnCncaResult(content,1);
    }
    
     //创建任务,返回结果
	   public OwnerTaskResult returnCncaResult(String content,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
	               result.setMessage("认证网创建新任务："+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("认证网创建新任务："+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("认证网新任务："+content+" 已存在");
	          }
		       
		     return result;
		   
	   }
}

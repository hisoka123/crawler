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
import com.crawlermanage.service.iecms.IecmsDBService;
import com.crawlermanage.service.ownerTask.IecmsOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.module.dao.entity.iecms.IecmsCompany;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskIecms;

/**
 * 贸易备案
 */
@Controller
@RequestMapping("/ownerTask")
public class IecmsOTController {
    private static final Logger log = Logger.getLogger(IecmsOTController.class);

    @Autowired
    private IecmsOTService iecmsOTService;
    
    @Autowired
    private IecmsDBService iecmsDBService;
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;

    /**
     * 页面跳转 (贸易备案)
     * @return
     */
    @RequestMapping("/iecms")
    public String toIecms(){
        return "/ownerTask/iecms";
    }

    /**
     * 根据条件查询失信网任务
     *
     * @param loginName
     * @param searchType
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchIecmsTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskIecms> searchIecmsTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                   @RequestParam("loginName") String loginName,@RequestParam("searchType") String searchType,
                                                   @RequestParam("status") String status,@RequestParam("keyWord") String keyWord) {
        log.info("=========searchIecmsTask==========");

        if (searchType.equals("searchType_corpName")) {
            searchType = "经营者名称";
        } else if (searchType.equals("searchType_scCode")) {
            searchType = "统一社会信用代码";
        } else if (searchType.equals("searchType_corpCode")) {
            searchType = "13位经营者代码";
        } else if (searchType.equals("searchType_all")){
            searchType = "";
        }
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
        paramMap.put("searchType",searchType);
        paramMap.put("status",status);
        paramMap.put("stateList",stateList);
        paramMap.put("keyWord",keyWord);

        System.out.println("====param== loginName: " + loginName + ",searchType: " + searchType + ",stateList: " + stateList + ", keyWord: " + keyWord);

        Page<OwnerTaskIecms> ownerTaskIecmsPage = iecmsOTService.searchIecmsTask(paramMap, currentPage, pageSize, null);
        List<OwnerTaskIecms> resultList = ownerTaskIecmsPage.getContent();
//        System.out.println("====searchIecms==" + resultList + "===total:" + ownerTaskIecmsPage.getTotalElements());

        return ownerTaskIecmsPage;
    }
    
  //创建新任务
    @RequestMapping(value="/iecmsJoinTask",method=RequestMethod.POST)
    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("type") String type,
    		                                      @RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建对外贸易任务：type:"+type+"  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   String queryType="";
    	   
    	   
    	   //重复新任务，返回
    	   for(OwnerTaskIecms pcExist:iecmsOTService.getAllIecmsTask(loginName)){
    		      if(pcExist.getSearchType().equals(type)&& pcExist.getKeyWord().equals(content)){
    		    	     return returnPeopleCourtResult(type,content,2);
    		      }
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该企业,写入company表中    		   
    		   if(type.equals("经营者名称")){
    	    	   queryType="1";
    	       }else if(type.equals("13位经营者代码")){
    	    	   queryType="2";
    	       }else if(type.equals("统一社会信用代码")){
    	    	   queryType="3";
    	       }
    		   
    		   IecmsCompany coi=new IecmsCompany();
		       coi.setType(queryType);
		       coi.setKeyword(content);
		       coi.setState(0);
		       coi.setPriority(1);
		       coi.setNum(0);
		       
		       IecmsCompany reCoi=iecmsDBService.saveTimeTask(coi);
		       if(reCoi!=null){
		    	      timetask_id=reCoi.getId();
		       }else{
		    	      return returnPeopleCourtResult(type,content,0);
		       }
	    	  
	      }else{
	    	     IecmsCompany  coi2=iecmsDBService.getTimeTask(timetaskID);
	    	     coi2.setPriority(1);
	    	     coi2.setNum(0);
	    	    if(iecmsDBService.saveTimeTask(coi2)==null){
	    	    	 return returnPeopleCourtResult(type,content,0);
	    	    }
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,ownertask_people_court表
    	   OwnerTaskIecms pc=new OwnerTaskIecms();
    	   pc.setSearchType(type);
    	   pc.setKeyWord(content);
    	   pc.setLoginName(loginName);
    	   pc.setState(0);
    	   pc.setTimeTaskId(timetask_id);    	      
    	   pc.setCreateTaskDate(date);
    	
    	   OwnerTaskIecms rePc=iecmsOTService.joinTask(pc);
    	   if(rePc==null){
	    	    return returnPeopleCourtResult(type,content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "贸易备案");
    	   if(reTaskAll==null){
    		   return returnPeopleCourtResult(type,content,0);
    	   }
    	   
    	   
    	   return returnPeopleCourtResult(type,content,1);
    }
    
     //创建任务,返回结果
	   public OwnerTaskResult returnPeopleCourtResult(String queryType,String content,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
	               result.setMessage("对外贸易建新任务："+queryType+"--"+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("对外贸易创建新任务："+queryType+"--"+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("对外贸易新任务："+queryType+"--"+content+" 已存在");
	          }
		       
		     return result;
		   
	   }




}

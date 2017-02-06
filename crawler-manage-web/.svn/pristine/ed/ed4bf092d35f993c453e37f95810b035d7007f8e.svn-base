package com.crawlermanage.controller.ownerTask;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.fahaicc.FahaiccDBService;
import com.crawlermanage.service.ownerTask.FahaiccOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.module.dao.entity.fahaicc.FahaiccResult;
import com.module.dao.entity.fahaicc.FahaiccRoot;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskFahaicc;

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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 法海风控
 */
@Controller
@RequestMapping("/ownerTask")
public class FahaiccOTController {
    private static final Logger log = Logger.getLogger(FahaiccOTController.class);

    @Autowired
    private FahaiccOTService fahaiccOTService;
    
    @Autowired
    private FahaiccDBService fahaiccDBService;
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;

    /**
     * 页面跳转 (法海风控)
     * @return
     */
    @RequestMapping("/fahaiccTask")
    public String toFahaiccTask(){
        return "/ownerTask/fahaiccTask";
    }

    /**
     * 根据条件查询法海网任务
     *
     * @param loginName
     * @param searchType
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchFahaiccTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskFahaicc> searchFahaiccTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                   @RequestParam("loginName") String loginName,@RequestParam("searchType") String searchType,
                                                   @RequestParam("status") String status,@RequestParam("keyWord") String keyWord) {
        log.info("=========searchFahaiccOTTask==========");

        if (searchType.equals("searchType_name")) {
            searchType = "个人/企业名称";
        } else if (searchType.equals("searchType_IDCard")) {
            searchType = "身份证号码/组织机构代码";
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

        Page<OwnerTaskFahaicc> ownerTaskFahaiccOTPage = fahaiccOTService.searchFahaiccOT(paramMap, currentPage, pageSize, null);
        List<OwnerTaskFahaicc> resultList = ownerTaskFahaiccOTPage.getContent();
//        System.out.println("====searchFahaiccOT==" + resultList + "===total:" + ownerTaskFahaiccOTPage.getTotalElements());

        return ownerTaskFahaiccOTPage;
    }

    //创建新任务
    @RequestMapping(value="/fahaiccJoinTask",method=RequestMethod.POST)
    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("type") String type,
    		                                      @RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建法海风控任务：type:"+type+"  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   
    	   
    	   //重复新任务，返回
    	   for(OwnerTaskFahaicc fhExist:fahaiccOTService.getAllTaskByLoginName(loginName)){
    		      if(fhExist.getSearchType().equals(type)&& fhExist.getKeyWord().equals(content)){
    		    	     return returnPeopleCourtResult(type,content,2);
    		      }
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该企业
    		   
    		   FahaiccRoot fhRoot=new FahaiccRoot();
		       fhRoot.setType(type);
		       fhRoot.setKeyword(content);
		       fhRoot.setState(0);
		       fhRoot.setPriority(1);
		       fhRoot.setNum(0);
		       
		       FahaiccRoot refh=fahaiccDBService.saveTimetask(fhRoot);
		       if(refh!=null){
		    	      timetask_id=refh.getId();
		       }else{
		    	      return returnPeopleCourtResult(type,content,0);
		       }
	    	  
	      }else{
	    	     System.out.println(timetaskID);
	    	     FahaiccRoot fhRoot2=fahaiccDBService.getTimetask(timetaskID);
	    	     
	    	     Set<FahaiccResult> set=new HashSet<FahaiccResult>();
	    	     
	    	     System.out.println(fhRoot2);
	    	     fhRoot2.setPriority(1);
	    	     fhRoot2.setNum(0);
	    	     fhRoot2.setFahaiccResults(set);
	    	    if(fahaiccDBService.saveTimetask(fhRoot2)==null){
	    	    	 return returnPeopleCourtResult(type,content,0);
	    	    }
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,OwnerTask_fahaicc表
    	   OwnerTaskFahaicc otf=new OwnerTaskFahaicc();
    	   otf.setSearchType(type);
    	   otf.setKeyWord(content);
           otf.setLoginName(loginName);
    	   otf.setState(0);
    	   otf.setTimetaskId(timetask_id);
    	   otf.setCreateTaskDate(date);

    	   
    	   OwnerTaskFahaicc retof=fahaiccOTService.joinTask(otf);
    	   if(retof==null){
	    	    return returnPeopleCourtResult(type,content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "法海风控");
    	   
    	   log.info(reTaskAll);
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
	               result.setMessage("法海风控网创建新任务："+queryType+"--"+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("法海风控创建新任务："+queryType+"--"+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("法海风控新任务："+queryType+"--"+content+" 已存在");
	          }
		       
		     return result;
		   
	   }
    
}

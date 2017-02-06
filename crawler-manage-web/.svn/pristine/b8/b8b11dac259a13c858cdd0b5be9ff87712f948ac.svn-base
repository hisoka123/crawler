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
import com.crawlermanage.service.dailianmeng.DaiLianMengDBService;
import com.crawlermanage.service.ownerTask.CreditUnionOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.module.dao.entity.dailianmeng.CompanyOrIdentity;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskCreditUnion;

/**
 * 贷联盟
 */
@Controller
@RequestMapping("/ownerTask")
public class CreditUnionController {
    private static final Logger log = Logger.getLogger(CreditUnionController.class);

    @Autowired
    private CreditUnionOTService creditUnionOTService;

    @Autowired
    private DaiLianMengDBService dailianmengDBService;
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;
    
    /**
     * 页面跳转 (贷联盟)
     * @return
     */
    @RequestMapping("/creditUnion")
    public String toCreditUnion(){
        return "/ownerTask/creditUnion";
    }

    /**
     * 根据条件查询贷联盟任务
     *
     * @param loginName
     * @param searchType
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchCreditUnionTask", method= RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskCreditUnion> searchCreditUnionTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                   @RequestParam("loginName") String loginName,@RequestParam("searchType") String searchType,
                                                   @RequestParam("status") String status,@RequestParam("keyWord") String keyWord) {
        log.info("=========searchCreditUnionTask==========");

        if (searchType.equals("searchType_name")) {
            searchType = "公司名称";
        } else if (searchType.equals("searchType_IDCard")) {
            searchType = "身份证号";
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

        Page<OwnerTaskCreditUnion> ownerTaskCreditUnionPage = creditUnionOTService.searchCreditUnion(paramMap, currentPage, pageSize, null);
        List<OwnerTaskCreditUnion> resultList = ownerTaskCreditUnionPage.getContent();
//        System.out.println("====searchCreditUnion==" + resultList + "===total:" + ownerTaskCreditUnionPage.getTotalElements());

        return ownerTaskCreditUnionPage;
    }

    //创建新任务
    @RequestMapping(value="/dailianmengJoinTask",method=RequestMethod.POST)
    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("type") String type,
    		                                      @RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建贷联盟任务：type:"+type+"  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   String queryType=type;
    	   
    	   
    	   //重复新任务，返回
    	   for(OwnerTaskCreditUnion pcExist:creditUnionOTService.getAllCreditUnionTask(loginName)){
    		      if(pcExist.getSearchType().equals(type)&& pcExist.getKeyWord().equals(content)){
    		    	     return returnLoanUnionResult(type,content,2);
    		      }
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该企业,写入company表中
    		   
    		   CompanyOrIdentity coi = new CompanyOrIdentity();
		       coi.setType(queryType);
		       coi.setKeyword(content);
		       coi.setState(0);
		       coi.setNum(0);
		       coi.setTotalNum(0);
		       coi.setPriority(1);
		       
		       CompanyOrIdentity reCoi = dailianmengDBService.saveTimeTask(coi);
		       if(reCoi!=null){
		    	      timetask_id=reCoi.getId();
		       }else{
		    	      return returnLoanUnionResult(type,content,0);
		       }
	    	  
	      }else{
	    	  	CompanyOrIdentity  coi2 = dailianmengDBService.getTimeTask(timetaskID);
	    	  	coi2.setNum(0);
	    	  	coi2.setPriority(1);
				if(dailianmengDBService.saveTimeTask(coi2)==null){
					 return returnLoanUnionResult(type,content,0);
				}
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,ownertask_creditunion表
    	   OwnerTaskCreditUnion pc=new OwnerTaskCreditUnion();
    	   pc.setSearchType(type);
    	   pc.setKeyWord(content);
    	   pc.setLoginName(loginName);
    	   pc.setState(0);
    	   pc.setTimeTaskId(timetask_id);
    	   pc.setCreateTaskDate(date);
    	
    	   OwnerTaskCreditUnion rePc = creditUnionOTService.joinTask(pc);
    	   if(rePc==null){
	    	    return returnLoanUnionResult(type,content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll = ownerTaskAllService.save(loginName, "贷联盟");
    	   if(reTaskAll==null){
    		   return returnLoanUnionResult(type,content,0);
    	   }
    	   
    	   
    	   return returnLoanUnionResult(type,content,1);
    }
    
     //创建任务,返回结果
	   public OwnerTaskResult returnLoanUnionResult(String queryType,String content,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
	               result.setMessage("贷联盟创建新任务："+queryType+"--"+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("贷联盟创建新任务："+queryType+"--"+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("贷联盟新任务："+queryType+"--"+content+" 已存在");
	          }
		       
		     return result;
		   
	   }
}

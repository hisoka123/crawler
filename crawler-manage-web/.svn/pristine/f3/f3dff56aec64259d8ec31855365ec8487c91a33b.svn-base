package com.crawlermanage.controller.ownerTask;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.crawlermanage.service.ownerTask.PeopleCourtOTService;
import com.crawlermanage.service.renfawang.RenFaWangDBService;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
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


@Controller
@RequestMapping("/ownerTask")
public class PeopleCourtController {

    private static final Logger log = Logger.getLogger(PeopleCourtController.class);

    @Autowired
    private PeopleCourtOTService peopleCourtOTService;
    
    @Autowired
    private RenFaWangDBService renFaWangDBService;
    
    @Autowired
    private OwnerTaskAllService ownerTaskAllService;

    //页面跳转 (人法网)
    @RequestMapping("/peoplecourt")
    public String toPeopleCourt(){
        return "/ownerTask/peoplecourt";
    }

    /**
     * 查询人法网任务,全部
     *
     * @param loginName
     * @return
     */
    @RequestMapping(value="/getAllPeopleCourt",method= RequestMethod.POST)
    public @ResponseBody
    List<OwnerTaskPeopleCourt> getAllPeopleCourtTask(@RequestParam("loginName") String loginName){
        log.info("=========getAllPeopleCourtTask==========");
//        System.out.println("=====getPeopleCourtTask====" +peopleCourtOTService.getAllPeopleCourtTask(loginName));

        return peopleCourtOTService.getAllPeopleCourtTask(loginName);
    }

    /**
     * 根据条件查询人法网任务
     *
     * @param loginName
     * @param searchType
     * @param status
     * @param keyWord
     * @return
     */
    @RequestMapping(value="/searchPeopleCourtTask", method=RequestMethod.POST)
    public @ResponseBody
    Page<OwnerTaskPeopleCourt> searchPeopleCourtTask(@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "currentPage") int currentPage,
                                                     @RequestParam("loginName") String loginName,@RequestParam("searchType") String searchType,
                                                     @RequestParam("status") String status,@RequestParam("keyWord") String keyWord) {
        log.info("=========searchPeopleCourtTask==========");

        if (searchType.equals("searchType_name")) {
            searchType = "公司名称";
        } else if (searchType.equals("searchType_IDCard")) {
            searchType = "身份证号码";
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

        System.out.println(",pageSize: " + pageSize + ",currentPage: " + currentPage);
        System.out.println("====param== loginName: " + loginName + ",searchType: " + searchType + ",stateList: " + stateList + ", keyWord: " + keyWord);

        Page<OwnerTaskPeopleCourt> ownerTaskPeopleCourtPage = peopleCourtOTService.searchPeopleCourt(paramMap, currentPage, pageSize, null);
        List<OwnerTaskPeopleCourt> resultList = ownerTaskPeopleCourtPage.getContent();
        long total = ownerTaskPeopleCourtPage.getTotalElements();
//        System.out.println("====searchPeopleCourt==" + resultList + "===total: "+total);

        return ownerTaskPeopleCourtPage;
    }
    
  //创建新任务
    @RequestMapping(value="/renfawangJoinTask",method=RequestMethod.POST)
    public @ResponseBody OwnerTaskResult joinTask(@RequestParam("type") String type,
    		                                      @RequestParam("content") String content,
    		                                      @RequestParam("date") Date date,
    		                                      @RequestParam("loginName") String loginName,
    		                                      @RequestParam("existCode") Integer existCode,
    		                                      @RequestParam("timetaskID") Long timetaskID){
    	
    	   log.info("创建人法网任务：type:"+type+"  content:"+content+"  loginName:"+loginName+"  existCode:"+existCode+"  timetaskID:"+timetaskID);   
    	
    	   long timetask_id=0;
    	   //String queryType="";
    	   
    	   
    	   //重复新任务，返回
    	   for(OwnerTaskPeopleCourt pcExist:peopleCourtOTService.getAllPeopleCourtTask(loginName)){
    		      if(pcExist.getSearchType().equals(type)&& pcExist.getKeyWord().equals(content)){
    		    	     return returnPeopleCourtResult(type,content,2);
    		      }
    	   }
    	   
    	   //创建定时任务
    	   if(existCode==0){ //定时任务表中无该企业,
    		   
    		  /* if(type.equals("被执行人姓名/名称")){
       		         queryType="公司名称";
       	       }else if(type.equals("身份证号码/组织机构代码")){
       		         queryType="身份证号";
       	        }*/
    		   
    		   
    		   CompanyOrID coi=new CompanyOrID();
		       coi.setType(type);
		       coi.setKeyword(content);
		       coi.setState(0);
		       coi.setPriority(1);
		       coi.setNum(0);
		       
		       CompanyOrID reCoi=renFaWangDBService.saveTimeTask(coi);
		       if(reCoi!=null){
		    	      timetask_id=reCoi.getId();
		       }else{
		    	      return returnPeopleCourtResult(type,content,0);
		       }
	    	  
	      }else{
	    	     CompanyOrID  coi2=renFaWangDBService.getTimeTask(timetaskID);
	    	     coi2.setPriority(1);
	    	     coi2.setNum(0);
	    	    if(renFaWangDBService.saveTimeTask(coi2)==null){
	    	    	 return returnPeopleCourtResult(type,content,0);
	    	    }
	    	    
	    	    timetask_id=timetaskID;
	      }
    	
    	  //创建自身任务,ownertask_people_court表
    	   OwnerTaskPeopleCourt pc=new OwnerTaskPeopleCourt();
    	   pc.setSearchType(type);
    	   pc.setKeyWord(content);
    	   pc.setLoginName(loginName);
    	   pc.setState(0);
    	   pc.setTasktimeId(timetask_id);
    	   pc.setCreateTaskDate(date);
    	
    	   OwnerTaskPeopleCourt rePc=peopleCourtOTService.joinTask(pc);
    	   if(rePc==null){
	    	    return returnPeopleCourtResult(type,content,0);
	       }
    	
    	   
    	   OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "人法网");
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
	               result.setMessage("人法网创建新任务："+queryType+"--"+content+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("人法网创建新任务："+queryType+"--"+content+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("人法网新任务："+queryType+"--"+content+" 已存在");
	          }
		       
		     return result;
		   
	   }
}

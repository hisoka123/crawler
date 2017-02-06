package com.crawlermanage.controller.ownerTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.gsxt.GsxtDBService;
import com.crawlermanage.service.ownerTask.GsxtOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.module.dao.entity.gsxt.Company;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskGsxt;


@Controller
@RequestMapping("/ownerTask")
public class AjaxGsxtOTController {

	
	 private static final Logger log=LoggerFactory.getLogger(AjaxGsxtOTController.class);
	 
	 @Autowired
	 GsxtOTService gsxtOTService;
	 
	 @Autowired
	 OwnerTaskAllService ownerTaskAllService;
	 
	 @Autowired
	 GsxtDBService gsxtDBService;
	 
	//加入新任务---工商网
	   @RequestMapping(value="/gsxtJoinTask",method=RequestMethod.POST)
	   public @ResponseBody OwnerTaskResult gsxtJoinTask(@RequestParam("city") String city,
			                                             @RequestParam("name") String name,
			                                             @RequestParam("date") Date date,
			                                             @RequestParam("loginName") String loginName,
			                                             @RequestParam("existCode") int existCode,
			                                             @RequestParam("companyID") long companyID){
		   
		      log.info("工商网新任务：city="+city+" ,name="+name+" ,date="+date+" ,loginName="+loginName+",existCode="+existCode+" ,companyID="+companyID);
		      
		      long company_id=0;
		      
		      
		      for(OwnerTaskGsxt gsxtExist:gsxtOTService.getGsxtTask(loginName)){
		    	    if(gsxtExist.getCity().equals(city) && gsxtExist.getName().equals(name)){
		    	    	  return returnGsxtResult(city,name,2); 
		    	    }
		      }
		      
		      
		      if(existCode==0){ //定时任务表中无该企业,写入company表中
		    	   Company company=new Company();
			       company.setCity(city);
			       company.setName(name);
			       company.setState(0);
			       company.setPriority(1);
			       company.setNum(0);
			       
			       Company reCompany=gsxtDBService.saveCompany(company);
			       if(reCompany!=null){
			    	      company_id=reCompany.getId();
			       }else{
			    	      return returnGsxtResult(city,name,0);
			       }
		    	  
		      }else{
		    	    Company company2=gsxtDBService.findById(companyID);
		    	    company2.setPriority(1);
		    	    company2.setNum(0);
		    	    if(gsxtDBService.saveCompany(company2)==null){
		    	    	 return returnGsxtResult(city,name,0);
		    	    }
		    	    
		    	    company_id=companyID;
		      }
		     
		    
		      
		      //写入ownerTask_gsxt表中
		      OwnerTaskGsxt gsxt=new OwnerTaskGsxt();
		      
		      gsxt.setLoginName(loginName);
		      gsxt.setCity(city);
		      gsxt.setName(name);
		      gsxt.setState(0);
		      gsxt.setCompany_id(company_id);
		      gsxt.setCreateTaskDate(date);
		      
		      OwnerTaskGsxt reGsxt=gsxtOTService.gsxtJoinTask(gsxt);
		      
		      if(reGsxt==null){
		    	    return returnGsxtResult(city,name,0);
		      }
		      
		      
		    
		    //写入ownerTask_all表中
		      OwnerTaskAll reTaskAll=ownerTaskAllService.save(loginName, "工商网");
	    	   if(reTaskAll==null){
	    		   return returnGsxtResult(city,name,0);
	    	   }
		      
		       return returnGsxtResult(city,name,1);
	   }
	   
	   
	   
	   
	   //查询工商网任务,全部
	   @RequestMapping(value="/getGsxtTask",method=RequestMethod.POST)
	   public @ResponseBody List<OwnerTaskGsxt> getGsxtTask(@RequestParam("loginName") String loginName){
		   
		      return gsxtOTService.getGsxtTask(loginName);
		      
	   }
	   
	   //查询任务，条件筛选
	   @RequestMapping(value="/getGsxtTaskByCon",method=RequestMethod.POST)
	   public @ResponseBody List<OwnerTaskGsxt> getGsxtTaskByCon(@RequestParam("loginName") String loginName,
			                                                     @RequestParam("areas") String areas,
			                                                     @RequestParam("status") String status,
			                                                     @RequestParam("company") String company){
		   
		    	       
		    	        List<Integer> stateList=new ArrayList<Integer>();
		    	        
		    	        
		    	        if(status.equals("searchStatus_success")){
  	    	    	        stateList.add(1);
  	    	    	        stateList.add(7);
  	    	            }else if(status.equals("searchStatus_waiting")){
  	    	    	        stateList.add(0);
  	    	            }else if(status.equals("searchStatus_all")){
  	  	    	        	stateList.addAll(gsxtOTService.getStateAll(loginName));
  	  	    	        }else if(status.equals("searchStatus_handling")){
  	  	    	        	stateList.add(-3);
  	  	    	        }
		    	        
		    	       //全部地区 
		    	       if(areas.equals("searchArea_all")){
		    	    	       if(company.equals("")){
		    	    	    	     if(status.equals("searchStatus_feedback")){  //全部地区     反馈状态     全部公司
		    	    	    	    	  return gsxtOTService.getGsxtTaskFeedback(loginName);
		    	    	    	     }else{    //全部地区     非反馈状态     全部公司
		    	    	    	    	  return gsxtOTService.getGsxtTaskByTaskState(loginName, stateList);
		    	    	    	     }
		    	    	       }else{
		    	    	    	     if(status.equals("searchStatus_feedback")){   //全部地区   反馈状态   特定公司
		    	    	    	    	   return gsxtOTService.getGsxtTaskByCompanyFeedback(loginName, company);
		    	    	    	     }else{   //全部地区    非反馈状态    特定公司
		    	    	    	    	   return gsxtOTService.getGsxtTaskByTaskStateAndCompany(loginName, stateList, company);
		    	    	    	     }
		    	    	       }
		    	       }else {  //部分地区
		    	    	   
		    	    	       List<String> areaList=new ArrayList<String>();
		    	    	       String[] areaStr=areas.split(",");
		    	    	       
		    	    	       for(int i=0;i<areaStr.length;i++){
		    	    	    	       areaList.add(areaStr[i]);
		    	    	       }
		    	    	       
		    	    	       if(company.equals("")){
		    	    	    	      if(status.equals("searchStatus_feedback")){  //部分地区    反馈状态     全部公司
		    	    	    	    	     return gsxtOTService.getGsxtTaskByPartAreaFeedback(loginName, areaList);
		    	    	    	      }else{   //部分地区    非反馈状态     全部公司
		    	    	    	    	     return gsxtOTService.getGsxtTaskByPartArea(loginName, stateList, areaList);  
		    	    	    	      }
		    	    	       }else{
		    	    	    	      if(status.equals("searchStatus_feedback")){   //部分地区   反馈状态    特定公司
		    	    	    	    	     return gsxtOTService.getGsxtTaskByPartAreaAndCompanyFeedback(loginName, areaList, company);
		    	    	    	      }else{  //部分地区    非反馈状态    特定公司
		    	    	    	    	     return gsxtOTService.getGsxtTaskByPartAreaAndCompany(loginName, stateList, areaList, company);
		    	    	    	      }
		    	    	       }
		    	       }
		     
	   }
	   
	   //创建任务,返回结果
	   public OwnerTaskResult returnGsxtResult(String city,String name,long ownerTaskCode){
		   
		      OwnerTaskResult result=new OwnerTaskResult();
		   
		      if(ownerTaskCode==0){
		    	   result.setOwnerTaskCode(0);
  	               result.setMessage("工商网创建新任务："+city+"--"+name+" 失败");
		      }else if(ownerTaskCode==1){
	    	       result.setOwnerTaskCode(1);
	    	       result.setMessage("工商网创建新任务："+city+"--"+name+" 成功");
	          }else if(ownerTaskCode==2){
	        	   result.setOwnerTaskCode(2);
	    	       result.setMessage("工商网新任务："+city+"--"+name+" 已存在");
	          }
		       
		     return result;
		   
	   }
	   
	   
	   
}

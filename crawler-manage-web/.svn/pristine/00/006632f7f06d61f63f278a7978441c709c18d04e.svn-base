package com.crawlermanage.controller.ownerTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.dao.result.FeedbackResetResult;
import com.crawlermanage.service.cnca.CncaDBService;
import com.crawlermanage.service.creditchina.CreditchinaDBService;
import com.crawlermanage.service.customs.CustomsDBService;
import com.crawlermanage.service.dailianmeng.DaiLianMengDBService;
import com.crawlermanage.service.fahaicc.FahaiccDBService;
import com.crawlermanage.service.gsxt.GsxtDBService;
import com.crawlermanage.service.iautos.IautosDBService;
import com.crawlermanage.service.iecms.IecmsDBService;
import com.crawlermanage.service.ownerTask.AuthenticateOTService;
import com.crawlermanage.service.ownerTask.CreditUnionOTService;
import com.crawlermanage.service.ownerTask.CreditchinaOTService;
import com.crawlermanage.service.ownerTask.CrqpOTService;
import com.crawlermanage.service.ownerTask.CustomsOTService;
import com.crawlermanage.service.ownerTask.DishonestyOTService;
import com.crawlermanage.service.ownerTask.EnterpCreditOTService;
import com.crawlermanage.service.ownerTask.FahaiccOTService;
import com.crawlermanage.service.ownerTask.GsxtOTService;
import com.crawlermanage.service.ownerTask.IautosOTService;
import com.crawlermanage.service.ownerTask.IecmsOTService;
import com.crawlermanage.service.ownerTask.PeopleCourtOTService;
import com.crawlermanage.service.ownerTask.SipoOTService;
import com.crawlermanage.service.ownerTask.ZjCourtOTService;
import com.crawlermanage.service.qiyezhengxin.QiyezhengxinApiService;
import com.crawlermanage.service.renfawang.RenFaWangDBService;
import com.crawlermanage.service.shixin.ShixinDBService;
import com.crawlermanage.service.sipo.SipoDBService;
import com.crawlermanage.service.sxjlcxpt.SxjlcxptDBService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwExecuteCaseSearchService;
import com.module.dao.entity.cnca.CertificateCompany;
import com.module.dao.entity.creditchinatwo.CompanyRecord;
import com.module.dao.entity.creditchinatwo.CreditCompany;
import com.module.dao.entity.crqp.CreditrRecordQueryPlatformCompany;
import com.module.dao.entity.customs.CustomsCompany;
import com.module.dao.entity.dailianmeng.CompanyOrIdentity;
import com.module.dao.entity.fahaicc.FahaiccRoot;
import com.module.dao.entity.gsxt.Company;
import com.module.dao.entity.iautos.IautosKeyword;
import com.module.dao.entity.iecms.IecmsCompany;
import com.module.dao.entity.ownerTask.OwnerTaskAuthenticate;
import com.module.dao.entity.ownerTask.OwnerTaskCreditUnion;
import com.module.dao.entity.ownerTask.OwnerTaskCreditchina;
import com.module.dao.entity.ownerTask.OwnerTaskCrqp;
import com.module.dao.entity.ownerTask.OwnerTaskCustoms;
import com.module.dao.entity.ownerTask.OwnerTaskDishonesty;
import com.module.dao.entity.ownerTask.OwnerTaskEnterpCredit;
import com.module.dao.entity.ownerTask.OwnerTaskFahaicc;
import com.module.dao.entity.ownerTask.OwnerTaskFeedback;
import com.module.dao.entity.ownerTask.OwnerTaskGsxt;
import com.module.dao.entity.ownerTask.OwnerTaskIautos;
import com.module.dao.entity.ownerTask.OwnerTaskIecms;
import com.module.dao.entity.ownerTask.OwnerTaskPeopleCourt;
import com.module.dao.entity.ownerTask.OwnerTaskSipo;
import com.module.dao.entity.ownerTask.OwnerTaskZjCourt;
import com.module.dao.entity.qiyezhengxin.ZhengxinKeyword;
import com.module.dao.entity.renfawang.CompanyOrID;
import com.module.dao.entity.shixin.ShixinKeyword;
import com.module.dao.entity.sipo.SipoKeyword;
import com.module.dao.entity.zjsfgkw.ZjsfgkwKeyword;

@Controller
@RequestMapping("/ownerTask")
public class AjaxFeedBackController {

	   private static final Logger log=LoggerFactory.getLogger(AjaxFeedBackController.class);
	   
	   
	   @Autowired
	   GsxtOTService gsxtOTService;
	   
	   @Autowired
	   PeopleCourtOTService peopleCourtOTService;
	   
	   @Autowired
	   DishonestyOTService dishonestyOTService;
	   
	   @Autowired
	   FahaiccOTService fahaiccOTService;
	   
	   @Autowired
	   CreditUnionOTService creditUnionOTService;
	   
	   @Autowired
	   ZjCourtOTService zjCourtOTService;
	   
	   @Autowired
	   IecmsOTService iecmsOTService;
	   
	   @Autowired
	   CreditchinaOTService creditchinaOTService;
	   
	   @Autowired
	   IautosOTService iautosOTService;
	   
	   @Autowired
	   CustomsOTService customsOTService;
	   
	   @Autowired
	   SipoOTService sipoOTService;
	   
	   @Autowired
	   AuthenticateOTService authenticateOTService;
	   
	   @Autowired
	   CrqpOTService crqpOTService;
	   
	   @Autowired
	   EnterpCreditOTService enterpCreditOTService;
	   
	   //应用
	   @Autowired
	   GsxtDBService gsxtDBService;
	   
	   @Autowired
	   RenFaWangDBService renFaWangDBService;
	   
	   @Autowired
	   ShixinDBService shixinDBService;
	   
	   @Autowired
	   DaiLianMengDBService daiLianMengDBService;
	   
	   @Autowired
	   FahaiccDBService fahaiccDBService;
	   
	   @Autowired
	   ZjsfgkwExecuteCaseSearchService zjsfgkwExecuteCaseSearchService;
	   
	   @Autowired
	   IecmsDBService iecmsDBService;
	   
	   @Autowired
	   CreditchinaDBService creditchinaDBService;
	   
	   @Autowired
	   IautosDBService iautosDBService;
	   
	   @Autowired
	   CustomsDBService customsDBService;
	   
	   @Autowired
	   SipoDBService sipoDBService;
	   
	   @Autowired
	   CncaDBService cncaDBService;
	   
	   @Autowired
	   SxjlcxptDBService sxjlcxptDBService;
	   
	   @Autowired
	   QiyezhengxinApiService qiyezhengxinApiService;
	   
	   
	
	   @RequestMapping(value="/getFeedback",method=RequestMethod.POST)
	   public @ResponseBody List<OwnerTaskFeedback> getFeedback(@RequestParam("loginName") String loginName,
			                                              @RequestParam("ownerTaskType") String ownerTaskType ){
		
		      List<OwnerTaskFeedback> otfbList=new ArrayList<OwnerTaskFeedback>();
		      
		      log.info("获取反馈信息:loginName:{},ownerTaskType:{}",loginName,ownerTaskType);
		      
		      long index=0;
		      if(ownerTaskType.contains("工商网")){
		    	      List<OwnerTaskGsxt> gsxtList=gsxtOTService.getGsxtTaskFeedback(loginName);
		    	      log.info("工商网原反馈信息：{}",gsxtList);
		    	     for(Iterator<OwnerTaskGsxt> iter=gsxtList.iterator();iter.hasNext();){
		    	    	          
		    	    	          OwnerTaskFeedback otfb=new OwnerTaskFeedback();
		    	    	          OwnerTaskGsxt gsxt=iter.next();
		    	    	          
		    	    	          otfb.setId(index++);
		    	    	          otfb.setOwnerTaskType("工商网");
		    	    	          otfb.setOwnerTaskContent(gsxt.getCity()+"---"+gsxt.getName());
		    	    	          otfb.setTaskCreateDate(gsxt.getCreateTaskDate());
		    	    	          otfb.setOwnertTaskId(gsxt.getId());
		    	    	          otfb.setTimeTaskId(gsxt.getCompany_id());
		    	    	          
		    	    	          int state=gsxt.getState();
		    	    	          otfb.setState(state);
		    	    	          otfb.setFeedbackInfo(getFeedbackInfo(state));
		    	    	          otfbList.add(otfb);
		    	     }
		    	     
		      }else if(ownerTaskType.contains("人法网")){
		    	     List<OwnerTaskPeopleCourt> pcList=peopleCourtOTService.getFeedback(loginName);
		    	     log.info("人法网原反馈信息: {}",pcList);
		    	     for(Iterator<OwnerTaskPeopleCourt> iter=pcList.iterator();iter.hasNext();){
		    	    	          OwnerTaskFeedback otfb=new OwnerTaskFeedback();
		    	    	          OwnerTaskPeopleCourt pc=iter.next();
		    	    	          
		    	    	          otfb.setId(index++);
		    	    	          otfb.setOwnerTaskType("人法网");
		    	    	          otfb.setOwnerTaskContent(pc.getSearchType()+"---"+pc.getKeyWord());
		    	    	          otfb.setTaskCreateDate(pc.getCreateTaskDate());
		    	    	          otfb.setOwnertTaskId(pc.getId());
		    	    	          otfb.setTimeTaskId(pc.getTasktimeId());
		    	    	          
		    	    	          int state=pc.getState();
		    	    	          otfb.setState(state);
		    	    	          otfb.setFeedbackInfo(getFeedbackInfo(state));
		    	    	          otfbList.add(otfb);
		    	    	 
		    	     }
		      }else if(ownerTaskType.contains("失信网")){
		    	     List<OwnerTaskDishonesty> dishonestyList=dishonestyOTService.getFeedback(loginName);
		    	     log.info("失信网原反馈信息：{}",dishonestyList);
		    	     for(Iterator<OwnerTaskDishonesty> iter=dishonestyList.iterator();iter.hasNext();){
		    	    	          OwnerTaskFeedback otfb=new OwnerTaskFeedback();
		    	    	          OwnerTaskDishonesty otd=iter.next();
		    	    	          
		    	    	          otfb.setId(index++);
		    	    	          otfb.setOwnerTaskType("失信网");
		    	    	          otfb.setOwnerTaskContent(otd.getSearchType()+"---"+otd.getKeyWord());
		    	    	          otfb.setTaskCreateDate(otd.getCreateTaskDate());
		    	    	          otfb.setOwnertTaskId(otd.getId());
		    	    	          otfb.setTimeTaskId(otd.getTimeTaskId());
		    	    	          
		    	    	          int state=otd.getState();
		    	    	          otfb.setState(state);
		    	    	          otfb.setFeedbackInfo(getFeedbackInfo(state));
		    	    	          otfbList.add(otfb);
		    	     }
		      }else if(ownerTaskType.contains("法海")){
		    	     List<OwnerTaskFahaicc> fhList=fahaiccOTService.getFeedback(loginName);
		    	     log.info("法海风控原反馈信息：{}",fhList);
		    	     for(Iterator<OwnerTaskFahaicc> iter=fhList.iterator();iter.hasNext();){
		    	    	          OwnerTaskFeedback otfb=new OwnerTaskFeedback();
		    	    	          OwnerTaskFahaicc otf=iter.next();
		    	    	          
		    	    	          otfb.setId(index++);
		    	    	          otfb.setOwnerTaskType("法海风控");
		    	    	          otfb.setOwnerTaskContent(otf.getSearchType()+"---"+otf.getKeyWord());
		    	    	          otfb.setTaskCreateDate(otf.getCreateTaskDate());
		    	    	          otfb.setOwnertTaskId(otf.getId());
		    	    	          otfb.setTimeTaskId(otf.getTimetaskId());
		    	    	          
		    	    	          int state=otf.getState();
		    	    	          otfb.setState(state);
		    	    	          otfb.setFeedbackInfo(getFeedbackInfo(state));
		    	    	          otfbList.add(otfb);
		    	     }
		      }else if(ownerTaskType.contains("贷联盟")){
		    	     List<OwnerTaskCreditUnion> cuList=creditUnionOTService.getFeedback(loginName);
		    	     log.info("贷联盟原反馈信息:{}",cuList);
		    	     for(Iterator<OwnerTaskCreditUnion> iter=cuList.iterator();iter.hasNext();){
		    	    	          OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
		    	    	          OwnerTaskCreditUnion otc=iter.next();
		    	    	          
		    	    	          otfb.setId(index++);
		    	    	          otfb.setOwnerTaskType("贷联盟");
		    	    	          otfb.setOwnerTaskContent(otc.getSearchType()+"---"+otc.getKeyWord());
		    	    	          otfb.setTaskCreateDate(otc.getCreateTaskDate());
		    	    	          otfb.setOwnertTaskId(otc.getId());
		    	    	          otfb.setTimeTaskId(otc.getTimeTaskId());
		    	    	          
		    	    	          int state=otc.getState();
		    	    	          otfb.setState(state);
		    	    	          otfb.setFeedbackInfo(getFeedbackInfo(state));
		    	    	          otfbList.add(otfb);
		    	     }
		      }else if(ownerTaskType.contains("浙法网")){
		    	     List<OwnerTaskZjCourt> zjList=zjCourtOTService.getFeedback(loginName);
		    	     log.info("浙法网原反馈信息:{}",zjList);
		    	     for(Iterator<OwnerTaskZjCourt> iter=zjList.iterator();iter.hasNext();){
		    	    	          OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
		    	    	          OwnerTaskZjCourt otz=iter.next();
		    	    	          
		    	    	          otfb.setId(index++);
		    	    	          otfb.setOwnerTaskType("浙法网");
		    	    	          otfb.setOwnerTaskContent(otz.getSearchType()+"---"+otz.getKeyWord());
		    	    	          otfb.setTaskCreateDate(otz.getCreateTaskDate());
		    	    	          otfb.setOwnertTaskId(otz.getId());
		    	    	          otfb.setTimeTaskId(otz.getTimeTaskId());
		    	    	          
		    	    	          int state=otz.getState();
		    	    	          otfb.setState(state);
		    	    	          otfb.setFeedbackInfo(getFeedbackInfo(state));
		    	    	          otfbList.add(otfb);
		    	     }
		      }else if(ownerTaskType.contains("贸易备案")){
		    	    List<OwnerTaskIecms> iecmsList=iecmsOTService.getFeedback(loginName);
		    	    log.info("贸易备案原反馈信息:{}",iecmsList);
		    	    for(Iterator<OwnerTaskIecms> iter=iecmsList.iterator();iter.hasNext();){
		    	    	          OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
		    	    	          OwnerTaskIecms oti=iter.next();
		    	    	          
		    	    	          otfb.setId(index++);
		    	    	          otfb.setOwnerTaskType("贸易备案");
		    	    	          otfb.setOwnerTaskContent(oti.getSearchType()+"---"+oti.getKeyWord());
		    	    	          otfb.setTaskCreateDate(oti.getCreateTaskDate());
		    	    	          otfb.setOwnertTaskId(oti.getId());
		    	    	          otfb.setTimeTaskId(oti.getTimeTaskId());
		    	    	          
		    	    	          int state=oti.getState();
		    	    	          otfb.setState(state);
		    	    	          otfb.setFeedbackInfo(getFeedbackInfo(state));
		    	    	          otfbList.add(otfb);
		    	    	          
		    	    }
		      }else if(ownerTaskType.contains("信用中国")){
		    	     List<OwnerTaskCreditchina> ccList=creditchinaOTService.getFeedback(loginName);
		    	     log.info("信用中国原反馈信息:{}",ccList);
		    	     for(Iterator<OwnerTaskCreditchina> iter=ccList.iterator();iter.hasNext();){
	    	              OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
	    	              OwnerTaskCreditchina otc=iter.next();
	    	          
	    	             otfb.setId(index++);
	    	             otfb.setOwnerTaskType("信用中国");
	    	             otfb.setOwnerTaskContent(otc.getSearchType()+"---"+otc.getKeyWord());
	    	             otfb.setTaskCreateDate(otc.getCreateTaskDate());
	    	             otfb.setOwnertTaskId(otc.getId());
	    	             otfb.setTimeTaskId(otc.getTimeTaskId());
	    	          
	    	             int state=otc.getState();
	    	             otfb.setState(state);
	    	             otfb.setFeedbackInfo(getFeedbackInfo(state));
	    	             otfbList.add(otfb);
	    	          
	               }
		      }else if(ownerTaskType.contains("第一车网")){
		    	    List<OwnerTaskIautos> iautosList=iautosOTService.getFeedback(loginName);
		    	    log.info("第一车网原反馈信息:{}",iautosList);
		    	    for(Iterator<OwnerTaskIautos> iter=iautosList.iterator();iter.hasNext();){
    	                 OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
    	                 OwnerTaskIautos otc=iter.next();
    	          
    	                 otfb.setId(index++);
    	                 otfb.setOwnerTaskType("第一车网");
    	                 otfb.setOwnerTaskContent(otc.getKeyWord());
    	                 otfb.setTaskCreateDate(otc.getCreateTaskDate());
    	                 otfb.setOwnertTaskId(otc.getId());
    	                 otfb.setTimeTaskId(otc.getTasktimeid());
    	          
    	                 int state=otc.getState();
    	                 otfb.setState(state);
    	                 otfb.setFeedbackInfo(getFeedbackInfo(state));
    	                 otfbList.add(otfb);
    	          
                    }
		      }else if(ownerTaskType.contains("海关")){
		    	    List<OwnerTaskCustoms> customsList=customsOTService.getFeedback(loginName);
		    	    log.info("海关网原反馈信息:{}",customsList);
		    	    for(Iterator<OwnerTaskCustoms> iter=customsList.iterator();iter.hasNext();){
 	                     OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
 	                     OwnerTaskCustoms otc=iter.next();
 	          
 	                    otfb.setId(index++);
   	                    otfb.setOwnerTaskType("海关网");
   	                    otfb.setOwnerTaskContent(otc.getKeyWord());
   	                    otfb.setTaskCreateDate(otc.getCreateTaskDate());
   	                    otfb.setOwnertTaskId(otc.getId());
   	                    otfb.setTimeTaskId(otc.getTimeTaskId());
   	          
   	                    int state=otc.getState();
   	                    otfb.setState(state);
   	                    otfb.setFeedbackInfo(getFeedbackInfo(state));
   	                    otfbList.add(otfb);
                   }
		      }else if(ownerTaskType.contains("专利")){
		    	  List<OwnerTaskSipo> sipoList=sipoOTService.getFeedback(loginName);
		    	  log.info("专利网原反馈信息：{}",sipoList);
		    	  for(Iterator<OwnerTaskSipo> iter=sipoList.iterator();iter.hasNext();){
	                     OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
	                     OwnerTaskSipo otc=iter.next();
	          
	                    otfb.setId(index++);
	                    otfb.setOwnerTaskType("专利网");
	                    otfb.setOwnerTaskContent(otc.getSearchType()+"---"+otc.getKeyWord());
	                    otfb.setTaskCreateDate(otc.getCreateTaskDate());
	                    otfb.setOwnertTaskId(otc.getId());
	                    otfb.setTimeTaskId(otc.getTimeTaskId());
	          
	                    int state=otc.getState();
	                    otfb.setState(state);
	                    otfb.setFeedbackInfo(getFeedbackInfo(state));
	                    otfbList.add(otfb);
                 }
		      }else if(ownerTaskType.contains("认证")){
		    	  List<OwnerTaskAuthenticate> aList=authenticateOTService.getFeedback(loginName);
		    	  log.info("认证网原反馈信息:{}",aList);
		    	  for(Iterator<OwnerTaskAuthenticate> iter=aList.iterator();iter.hasNext();){
	                     OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
	                     OwnerTaskAuthenticate otc=iter.next();
	          
	                    otfb.setId(index++);
	                    otfb.setOwnerTaskType("认证网");
	                    otfb.setOwnerTaskContent(otc.getKeyWord());
	                    otfb.setTaskCreateDate(otc.getCreateTaskDate());
	                    otfb.setOwnertTaskId(otc.getId());
	                    otfb.setTimeTaskId(otc.getTimeTaskId());
	          
	                    int state=otc.getState();
	                    otfb.setState(state);
	                    otfb.setFeedbackInfo(getFeedbackInfo(state));
	                    otfbList.add(otfb);
                  }    
		      }else if(ownerTaskType.contains("失信记录")){
		    	  List<OwnerTaskCrqp> crqpList=crqpOTService.getFeedback(loginName);
		    	  log.info("失信记录平台原反馈信息：{}",crqpList);
		    	  for(Iterator<OwnerTaskCrqp> iter=crqpList.iterator();iter.hasNext();){
	                     OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
	                     OwnerTaskCrqp otc=iter.next();
	          
	                    otfb.setId(index++);
	                    otfb.setOwnerTaskType("失信记录");
	                    otfb.setOwnerTaskContent(otc.getKeyWord());
	                    otfb.setTaskCreateDate(otc.getCreateTaskDate());
	                    otfb.setOwnertTaskId(otc.getId());
	                    otfb.setTimeTaskId(otc.getTimeTaskId());
	          
	                    int state=otc.getState();
	                    otfb.setState(state);
	                    otfb.setFeedbackInfo(getFeedbackInfo(state));
	                    otfbList.add(otfb);
                  }  
		      }else if(ownerTaskType.contains("企业征信")){
		    	   List<OwnerTaskEnterpCredit> ecList=enterpCreditOTService.getFeedback(loginName);
		    	   log.info("企业征信原反馈信息:{}",ecList);
		    	   for(Iterator<OwnerTaskEnterpCredit> iter=ecList.iterator();iter.hasNext();){
	                     OwnerTaskFeedback otfb=new OwnerTaskFeedback(); 
	                     OwnerTaskEnterpCredit otc=iter.next();
	          
	                    otfb.setId(index++);
	                    otfb.setOwnerTaskType("11315企业征信");
	                    otfb.setOwnerTaskContent(otc.getKeyWord());
	                    otfb.setTaskCreateDate(otc.getCreateTaskDate());
	                    otfb.setOwnertTaskId(otc.getId());
	                    otfb.setTimeTaskId(otc.getTimeTaskId());
	          
	                    int state=otc.getState();
	                    otfb.setState(state);
	                    otfb.setFeedbackInfo(getFeedbackInfo(state));
	                    otfbList.add(otfb);
                  }  
		      }
		     
		      log.info("反馈信息:{}",otfbList);
		      
		      return otfbList;
	   }
	   
	   
	   //重置任务
	   @RequestMapping(value="/feedbackReset",method=RequestMethod.POST)
	   public @ResponseBody FeedbackResetResult resetTask(@RequestParam("loginName") String loginName,
			                                              @RequestParam("taskType") String taskType,
			                                              @RequestParam("ownerTaskId") Long ownerTaskId,
			                                              @RequestParam("timeTaskId") Long timeTaskId){
		   
		   
		     log.info("反馈信息重置：loginName:{},taskType:{},ownerTaskId:{},timeTaskId:{}",loginName,taskType,ownerTaskId,timeTaskId);
		   
		      if(taskType.contains("工商网")){
		    	      
		    	    //更新工商网定时任务表
		    	    Company company=gsxtDBService.findById(timeTaskId);
		    	    company.setState(0);
		    	    company.setNum(0);
		    	    
		    	  //更新工商网任务表
		    	    OwnerTaskGsxt gsxt=gsxtOTService.getGsxt(loginName, ownerTaskId);
		    	    gsxt.setState(0);
		    	    
		    	    if(gsxtDBService.saveCompany(company).getState()==0 && gsxtOTService.gsxtJoinTask(gsxt).getState()==0){
		    	    	   return reResetResult(1);
		    	    }else{
		    	    	   return reResetResult(0);
		    	    }
		      }else if(taskType.contains("人法网")){
		    	    
		    	    //更新定时任务
		    	    CompanyOrID coi=renFaWangDBService.getTimeTask(timeTaskId);
		    	    coi.setState(0);
		    	    coi.setNum(0);
		    	    
		    	    //更新人法网任务表
		    	    OwnerTaskPeopleCourt  pc=peopleCourtOTService.getPc(loginName,ownerTaskId);
		    	    pc.setState(0);
		    	    
		    	    if(renFaWangDBService.saveTimeTask(coi).getState()==0 && peopleCourtOTService.joinTask(pc).getState()==0){
		    	    	   return  reResetResult(1);
		    	    	    
		    	    }else{
		    	    	    return reResetResult(0);
		    	    }
		      }else if(taskType.contains("失信网")){
		    	    
		    	  //更新定时任务
		    	  ShixinKeyword sk=shixinDBService.getTimeTaskById(timeTaskId);
		    	  sk.setState(0);
		    	  sk.setNum(0);
		    	  
		    	//更新我的任务
		    	  OwnerTaskDishonesty otd=dishonestyOTService.getTask(ownerTaskId);
		    	  otd.setState(0);
		    	  
		    	  if(shixinDBService.saveTimeTask(sk).getState()==0 && dishonestyOTService.joinTask(otd).getState()==0){
		    		       return reResetResult(1);
		    	  }else{
		    		       return reResetResult(0);
		    	  }
		    	  
		      }else if(taskType.contains("贷联盟")){
		    	  
		    	  CompanyOrIdentity coi=daiLianMengDBService.getTimeTask(timeTaskId);
		    	  coi.setState(0);
		    	  coi.setNum(0);
		    	  
		    	  OwnerTaskCreditUnion otcu=creditUnionOTService.getTask(ownerTaskId);
		    	  otcu.setState(0);
		    	  
		    	  if(daiLianMengDBService.saveTimeTask(coi).getState()==0 && creditUnionOTService.joinTask(otcu).getState()==0){
		    		       return reResetResult(1);
		    	  }else{
		    		       return reResetResult(0);
		    	  }
		    	       
		      }else if(taskType.contains("法海")){
		    	  
		    	     FahaiccRoot fr=fahaiccDBService.getTimetask(timeTaskId);
		    	     fr.setState(0);
		    	     fr.setNum(0);
		    	     
		    	     
		    	     OwnerTaskFahaicc  otf=fahaiccOTService.getTask(ownerTaskId);
		    	     otf.setState(0);
		    	    
		    	     
		    	     if(fahaiccDBService.saveTimetask(fr).getState()==0 && fahaiccOTService.joinTask(otf).getState()==0){
		    	    	      return reResetResult(1);
		    	     }else{
		    	    	      return reResetResult(0);
		    	     }
		      }else if(taskType.contains("浙法网")){
		    	  
		    	    ZjsfgkwKeyword zk=zjsfgkwExecuteCaseSearchService.getTimeTask(timeTaskId);
		    	    zk.setState(0);
		    	    zk.setNum(0);
		    	    
		    	    OwnerTaskZjCourt otz=zjCourtOTService.getTask(ownerTaskId);
		    	    otz.setState(0);
		    	    
                    if(zjsfgkwExecuteCaseSearchService.saveTimeTask(zk).getState()==0 && zjCourtOTService.joinTask(otz).getState()==0){
                    	      return reResetResult(1);
                    }else{
                    	      return reResetResult(0);
                    }		    			  
		      }else if(taskType.contains("贸易备案")){
		    	  
		    	    IecmsCompany ic=iecmsDBService.getTimeTask(timeTaskId);
		    	    ic.setState(0);
		    	    ic.setNum(0);
		    	    
		    	    OwnerTaskIecms oti=iecmsOTService.getTask(ownerTaskId);
		    	    oti.setState(0);
		    	    
		    	    if(iecmsDBService.saveTimeTask(ic).getState()==0 && iecmsOTService.joinTask(oti).getState()==0){
		    	    	     return reResetResult(1);
		    	    }else{
		    	    	     return reResetResult(0);
		    	    }
		    	  
		      }else if(taskType.contains("信用中国")){
		    	   
		    	    CreditCompany  cc=creditchinaDBService.findById(timeTaskId);
		    	    cc.setState(0);
		    	    cc.setNum(0);
		    	    
		    	    OwnerTaskCreditchina otc=creditchinaOTService.getTask(ownerTaskId);
		    	    otc.setState(0);
		    	    
		    	    if(creditchinaDBService.saveCreditCompany(cc).getState()==0 && creditchinaOTService.creditchinaJoinTask(otc).getState()==0){
		    	    	     return reResetResult(1);
		    	    }else{
		    	    	     return reResetResult(0);
		    	    }
		      }else if(taskType.contains("第一车网")){
		    	    IautosKeyword ik=iautosDBService.getTimeTask(timeTaskId);
		    	    ik.setState(0);
		    	    ik.setNum(0);
		    	    
		    	    OwnerTaskIautos oti=iautosOTService.getTask(ownerTaskId);
		    	    oti.setState(0);
		    	    
		    	    if(iautosDBService.saveTimeTask(ik).getState()==0 && iautosOTService.joinTask(oti).getState()==0){
		    	    	      return reResetResult(1);
		    	    }else{
		    	    	      return reResetResult(0);
		    	    }
		      }else if(taskType.contains("海关网")){
		    	     CustomsCompany cc=customsDBService.getTimeTask(timeTaskId);
		    	     cc.setState(0);
		    	     cc.setNum(0);
		    	     
		    	     OwnerTaskCustoms otc=customsOTService.getTask(ownerTaskId);
		    	     otc.setState(0);
		    	     
		    	     if(customsDBService.saveTimeTask(cc).getState()==0 && customsOTService.joinTask(otc).getState()==0){
		    	    	     return reResetResult(1);
		    	     }else{
		    	    	     return reResetResult(0);
		    	     }
		      }else if(taskType.contains("专利网")){
		    	  
		    	     SipoKeyword sk=sipoDBService.getTimeTask(timeTaskId);
		    	     sk.setState(0);
		    	     sk.setNum(0);
		    	     
		    	     OwnerTaskSipo ots=sipoOTService.getTask(ownerTaskId);
		    	     ots.setState(0);
		    	     
		    	     if(sipoDBService.saveTimeTask(sk).getState()==0 && sipoOTService.joinTask(ots).getState()==0){
		    	    	      return reResetResult(1);
		    	     }else{
		    	    	      return reResetResult(0);
		    	     }
		    	    		 
		      }else if(taskType.contains("认证网")){
		    	     
		    	     CertificateCompany cc=cncaDBService.getTimeTask(timeTaskId);
		    	     cc.setState(0);
		    	     cc.setNum(0);
		    	     
		    	     OwnerTaskAuthenticate ota=authenticateOTService.getTask(ownerTaskId);
		    	     ota.setState(0);
		    	     
		    	     if(cncaDBService.saveTimeTask(cc).getState()==0 && authenticateOTService.joinTask(ota).getState()==0){
		    	    	     return reResetResult(1);
		    	     }else{
		    	    	     return reResetResult(0);
		    	     }
		      }else if(taskType.contains("失信记录")){
		    	    
		    	     CreditrRecordQueryPlatformCompany cr=sxjlcxptDBService.getTimeTask(timeTaskId);
		    	     cr.setState(0);
		    	     cr.setNum(0);
		    	     
		    	     OwnerTaskCrqp otc=crqpOTService.getTask(ownerTaskId);
		    	     otc.setState(0);
		    	     
		    	     if(sxjlcxptDBService.saveTimeTask(cr).getState()==0 && crqpOTService.joinTask(otc).getState()==0){
		    	    	      return reResetResult(1);
		    	     }else{
		    	    	      return reResetResult(0);
		    	     }
		    	     
		    	  
		      }else if(taskType.contains("企业征信")){
		    	     
		    	     ZhengxinKeyword zx=qiyezhengxinApiService.getTimeTask(timeTaskId);
		    	     zx.setState(0);
		    	     zx.setNum(0);
		    	     
		    	     OwnerTaskEnterpCredit otec=enterpCreditOTService.getTask(ownerTaskId);
		    	     otec.setState(0);
		    	     
		    	     if(qiyezhengxinApiService.saveTimeTask(zx).getState()==0 && enterpCreditOTService.joinTask(otec).getState()==0){
		    	    	      return reResetResult(1);
		    	     }else{
		    	    	      return reResetResult(0);
		    	     }
		      }else{
		    	     return null;
		      }
             
	   }
	   
	   
	   public String getFeedbackInfo(int state){
		    
		      String fbInfo="";
		   
		     if(state==2){
       	          fbInfo="验证码识别错误,建议重新获取验证码进行识别";
             }else if(state==3){
       	          fbInfo="Cookie失效,建议重新获取Cookie";
             }else if(state==4){
       	          fbInfo="手机验证码错误,建议重新获取手机验证码";
             }else if(state==5){
       	          fbInfo="数据采集的行为被目标网站屏蔽,建议先停止采集一段时间（2-12小时），然后调整采集频率重新开始采集";
             }else if(state==8){
       	          fbInfo="检索条件存在非法字符（比如：括号()，横杠- ），目标网站对非法字符采取了过滤策略以防止非法的参数导致系统出错（比如SQL注入）建议更换检索条件";
             }else if(state==9){
       	          fbInfo="数据采集所使用的后台账号登录目标网站失败，可能是由于账号被锁定或密码错误导致，建议使用后台账号登录目标网站，检查账号状态";
             }else if(state==10){
            	  fbInfo="符合输入关键字的检索结果数量过大、非法身份证号等";
             }else{
            	  fbInfo="请您联系客服帮助处理,并告之反馈代码.";
             }
		   
		     
		     return fbInfo;
	   }
	   
	  //返回重置结果
	   public FeedbackResetResult reResetResult(Integer result){
		      FeedbackResetResult fbrr=new FeedbackResetResult();
		      if(result==1){
		    	    fbrr.setId(1);
		    	    fbrr.setMes("成功重置");
		      }else if(result==0){
		    	    fbrr.setId(0);
		    	    fbrr.setMes("重置失败");
		      }
		      return fbrr;
	   }
	   
	   
}

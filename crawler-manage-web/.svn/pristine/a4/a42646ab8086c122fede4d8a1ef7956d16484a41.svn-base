package com.crawlermanage.controller.ownerTask;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.crawlermanage.service.ownerTask.PeopleCourtOTService;
import com.crawlermanage.service.ownerTask.SipoOTService;
import com.crawlermanage.service.ownerTask.ZjCourtOTService;
import com.module.dao.entity.ownerTask.OwnerTaskAll;


@Controller
@RequestMapping("/ownerTask")
public class AjaxOwnerTaskController {

	   private static final Logger log=LoggerFactory.getLogger(AjaxOwnerTaskController.class);
	   
	   @Autowired
	   OwnerTaskAllService ownerTaskAllService;
	   
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
	   
       //获取当前用户全部任务
	   @RequestMapping(value="/getAllTask",method=RequestMethod.POST)
	   public @ResponseBody List<OwnerTaskAll> getAllTask(@RequestParam("loginName") String loginName){
		   
		      List<OwnerTaskAll> ownerTaskAllList=ownerTaskAllService.getAllTask(loginName);
		      
		      for(OwnerTaskAll ownerTaskAll:ownerTaskAllList){
		    	    
		    	    if(ownerTaskAll.getTaskType().contains("工商网")){
		    	    	 ownerTaskAll.setTaskSuccessNum(gsxtOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("人法网")){
		    	    	 ownerTaskAll.setTaskSuccessNum(peopleCourtOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("失信网")){
		    	    	 ownerTaskAll.setTaskSuccessNum(dishonestyOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("法海")){
		    	    	 ownerTaskAll.setTaskSuccessNum(fahaiccOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("贷联盟")){
		    	    	 ownerTaskAll.setTaskSuccessNum(creditUnionOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("浙法网")){
		    	    	 ownerTaskAll.setTaskSuccessNum(zjCourtOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("贸易备案")){
		    	    	 ownerTaskAll.setTaskSuccessNum(iecmsOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("信用中国")){
		    	         ownerTaskAll.setTaskSuccessNum(creditchinaOTService.getTaskSuccessNum(loginName));	
		    	    }else if(ownerTaskAll.getTaskType().contains("第一车网")){
		    	    	 ownerTaskAll.setTaskSuccessNum(iautosOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("海关网")){
		    	    	 ownerTaskAll.setTaskSuccessNum(customsOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("专利网")){  
		    	    	 ownerTaskAll.setTaskSuccessNum(sipoOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("认证网")){
		    	    	 ownerTaskAll.setTaskSuccessNum(authenticateOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("失信记录")){
		    	    	 ownerTaskAll.setTaskSuccessNum(crqpOTService.getTaskSuccessNum(loginName));
		    	    }else if(ownerTaskAll.getTaskType().contains("企业征信")){
		    	    	 ownerTaskAll.setTaskSuccessNum(enterpCreditOTService.getTaskSuccessNum(loginName));
		    	    }
		      }
		   
		      return ownerTaskAllList;
		       
	   }
	   
	   
}

package com.crawlermanage.service.ownerTask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.repository.ownerTask.OwnerTaskRepository;

@Component("ownerTaskAllService")
public class OwnerTaskAllService {

	   @Autowired
	   OwnerTaskRepository ownerTaskRepository;
	
	   
	 //任务总表，保存新任务
	   public OwnerTaskAll save(String loginName,String specialTask){
		   
		      OwnerTaskAll allTask=getSpecialTask(loginName,specialTask);
		      
		      if(allTask!=null){
		    	   allTask.setTaskNum(allTask.getTaskNum()+1);
		    	   return ownerTaskRepository.save(allTask);
		      }else{
		    	   OwnerTaskAll createTask=new OwnerTaskAll();
		    	   createTask.setLoginName(loginName);
		    	   createTask.setTaskType(specialTask);
		    	   createTask.setTaskNum(1);
		    	   return ownerTaskRepository.save(createTask);
		      }
	   }
	   
	   
	   
	   //查找当前用户的全部任务
	   public List<OwnerTaskAll> getAllTask(String loginName){
		   
		      return ownerTaskRepository.findByLoginName(loginName);
		      
	   }
	   
	 //查询当前用户的指定任务
	   public OwnerTaskAll getSpecialTask(String loginName,String specialTask){
		      return ownerTaskRepository.findByLoginNameAndTaskType(loginName, specialTask);
	   }
}

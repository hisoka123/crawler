package com.crawlermanage.service.ownerTask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.auth.ActiveUserService;
import com.module.dao.entity.ownerTask.OwnerTaskGsxt;
import com.module.dao.repository.ownerTask.OwnerTaskGsxtRepository;

@Component("gsxtOTService")
public class GsxtOTService {
	
	   @Autowired
	   OwnerTaskGsxtRepository ownerTaskGsxtRepository;
	   
	   @Autowired
	   ActiveUserService activeUserService;
	   
	 //加入新任务---工商网
	   public OwnerTaskGsxt gsxtJoinTask(OwnerTaskGsxt ownerTaskGsxt){
		      
		      return ownerTaskGsxtRepository.save(ownerTaskGsxt);
		   
	   }
	   
	   //查询任务  全部
	   public List<OwnerTaskGsxt> getGsxtTask(String loginName){
		     
		      return ownerTaskGsxtRepository.findByLoginNameOrderByCreateTaskDateDesc(loginName);
	   }
	   
	   //查询任务    全部地区       特定任务状态(除反馈)    全部公司
	   public List<OwnerTaskGsxt> getGsxtTaskByTaskState(String loginName,List<Integer> states){
		      return ownerTaskGsxtRepository.findByTaskState(loginName, states);
	   }
	   
	   //查询任务  全部地区     特定任务状态(除反馈)，特定公司
	   public List<OwnerTaskGsxt> getGsxtTaskByTaskStateAndCompany(String loginName,List<Integer> states,String company){
		      return ownerTaskGsxtRepository.findByTaskStateAndCompany(loginName, states, company);
	   }
	   
	   //查询任务     部分地区、特定任务状态(除反馈)、全部公司
	   public List<OwnerTaskGsxt> getGsxtTaskByPartArea(String loginName,List<Integer> states,List<String> areas){
		      return ownerTaskGsxtRepository.findByPartArea(loginName, states, areas);
	   }
	   
	   //查询任务     部分地区、特定任务状态(除反馈)、特定公司
	   public List<OwnerTaskGsxt> getGsxtTaskByPartAreaAndCompany(String loginName,List<Integer> states,List<String> areas,String company){
		      return ownerTaskGsxtRepository.findByPartAreaAndCompany(loginName, states, areas, company);
	   }
	   
	   
	   //全部地区、反馈状态、全部公司
	   public List<OwnerTaskGsxt> getGsxtTaskFeedback(String loginName){
		      return ownerTaskGsxtRepository.findByFeedback(loginName);
	   }
	   
	   //查询任务  全部地区     反馈状态    特定公司
	   public List<OwnerTaskGsxt> getGsxtTaskByCompanyFeedback(String loginName,String company){
		      return ownerTaskGsxtRepository.findByCompanyFeedback(loginName, company);
	   }
	   
	   //查询任务     部分地区、反馈状态、全部公司
	   public List<OwnerTaskGsxt> getGsxtTaskByPartAreaFeedback(String loginName,List<String> areas){
		      return ownerTaskGsxtRepository.findByPartAreaFeedback(loginName, areas);
	   }
	   
	   //查询任务     部分地区、反馈状态、特定公司
	   public List<OwnerTaskGsxt> getGsxtTaskByPartAreaAndCompanyFeedback(String loginName,List<String> areas,String company){
		      return ownerTaskGsxtRepository.findByPartAreaAndCompanyFeedback(loginName, areas, company);
	   }
	   
	   
	   //id查询单个任务
	   public OwnerTaskGsxt getGsxt(String loginName,long id){
		      return ownerTaskGsxtRepository.findByLoginNameAndId(loginName, id);
	   }
	   
	   
	   //计算成功任务数---工商网
	   public Long getTaskSuccessNum(String loginName){
		      
		      return ownerTaskGsxtRepository.countBySuccess(loginName);
	   }

	   //查询工商网中全部任务状态
	   public List<Integer> getStateAll(String loginName){
		       return ownerTaskGsxtRepository.findState(loginName);
	   }
}

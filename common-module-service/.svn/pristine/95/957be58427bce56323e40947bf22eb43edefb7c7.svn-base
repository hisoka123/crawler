package com.crawlermanage.service.fahaicc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.fahaicc.FahaiccResult;
import com.module.dao.entity.fahaicc.FahaiccRoot;
import com.module.dao.entity.gsxt.Company;
import com.module.dao.repository.fahaicc.FahaiccResultRepository;
import com.module.dao.repository.fahaicc.FahaiccRootRepository;

@Component("fahaiccDBService")
public class FahaiccDBService {
	
	
	   @Autowired
	   FahaiccRootRepository fahaiccRootRepository;
	
	   @Autowired
	   FahaiccResultRepository fahaiccResultRepository;
	   
	
	 //检查是定时任务中是否存在该任务
	   public List<FahaiccRoot>  checkIsExistBYName(String type,String content){
		   
		     return fahaiccRootRepository.findByTypeAndKeyword(type, content);
	   }
	   
	     //检查企业
	     public FahaiccRoot getTimetask(Long id){
		      return fahaiccRootRepository.findById(id);
	     }
	   
	   
	   //查询详情
	   public List<FahaiccResult> getDetailByRootId(List<Long> idList){
		      
		      return fahaiccResultRepository.findByRootId(idList);
	   }
	   
	 //保存定时任务
	   public FahaiccRoot saveTimetask(FahaiccRoot fahaiccRoot){
		      return fahaiccRootRepository.save(fahaiccRoot);
	   }

}

package com.module.dao.repository.ownerTask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.module.dao.entity.ownerTask.OwnerTaskAll;

public interface OwnerTaskRepository extends JpaRepository<OwnerTaskAll,Long> {

	  
	   List<OwnerTaskAll> findByLoginName(String loginName);
	   
	   OwnerTaskAll findByLoginNameAndTaskType(String loginName,String taskType);
}

package com.module.dao.repository.ownerTask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.ownerTask.OwnerTaskIautos;

/**
 *
 */
@Repository
public interface OwnerTaskIautosRepository extends PagingAndSortingRepository<OwnerTaskIautos,Long>,JpaRepository<OwnerTaskIautos,Long>,JpaSpecificationExecutor<OwnerTaskIautos>{
       
	 List<OwnerTaskIautos> findByLoginNameOrderByCreateTaskDateDesc(String loginName);
	 
	//计算成功任务数
	   @Query("select count(*) from OwnerTaskIautos where state in (1,7) and loginName=?")
	   Long countBySuccess(String loginName);
	   
	 //获取反馈状态，非0,1,7,-3
	   @Query("select p from OwnerTaskIautos p where p.loginName=?1 and p.state not in (-3,0,1,7) order by p.createTaskDate desc")
	   List<OwnerTaskIautos> findFeedBack(String loginName);

}

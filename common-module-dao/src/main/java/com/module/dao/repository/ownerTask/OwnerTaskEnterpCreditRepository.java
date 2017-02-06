package com.module.dao.repository.ownerTask;

import java.util.List;

import com.module.dao.entity.ownerTask.OwnerTaskEnterpCredit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface OwnerTaskEnterpCreditRepository extends PagingAndSortingRepository<OwnerTaskEnterpCredit,Long>,JpaRepository<OwnerTaskEnterpCredit,Long>,JpaSpecificationExecutor<OwnerTaskEnterpCredit>{
	
	List<OwnerTaskEnterpCredit> findByLoginNameOrderByCreateTaskDateDesc(String loginName);
	
	 //计算成功任务数
	   @Query("select count(*) from OwnerTaskEnterpCredit where state in (1,7) and loginName=?")
	   Long countBySuccess(String loginName);
	   
	   //获取反馈状态，非0,1,7,-3
	   @Query("select p from OwnerTaskEnterpCredit p where p.loginName=?1 and p.state not in (-3,0,1,7) order by p.createTaskDate desc")
	   List<OwnerTaskEnterpCredit> findFeedBack(String loginName);

	   
}

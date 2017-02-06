package com.module.dao.repository.ownerTask;

import java.util.List;

import com.module.dao.entity.ownerTask.OwnerTaskCreditchina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface OwnerTaskCreditchinaRepository extends PagingAndSortingRepository<OwnerTaskCreditchina,Long>,JpaRepository<OwnerTaskCreditchina,Long>,JpaSpecificationExecutor<OwnerTaskCreditchina>{

	public List<OwnerTaskCreditchina> findByLoginNameOrderByCreateTaskDateDesc(String loginName);
	
	public List<OwnerTaskCreditchina> findByLoginNameAndSearchTypeOrderByCreateTaskDateDesc(String loginName,String objectType);
	
	 //计算成功任务数
	   @Query("select count(*) from OwnerTaskCreditchina where state in (1,7) and loginName=?")
	   Long countBySuccess(String loginName);
	   
	   //获取反馈状态，非0,1,7,-1
	   @Query("select p from OwnerTaskCreditchina p where p.loginName=?1 and p.state not in (-3,0,1,7) order by p.createTaskDate desc")
	   List<OwnerTaskCreditchina> findFeedBack(String loginName);
	
}

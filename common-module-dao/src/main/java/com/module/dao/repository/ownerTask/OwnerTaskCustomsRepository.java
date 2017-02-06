package com.module.dao.repository.ownerTask;

import java.util.List;

import com.module.dao.entity.ownerTask.OwnerTaskCustoms;
import com.module.dao.entity.ownerTask.OwnerTaskDishonesty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface OwnerTaskCustomsRepository extends PagingAndSortingRepository<OwnerTaskCustoms,Long>,JpaRepository<OwnerTaskCustoms,Long>,JpaSpecificationExecutor<OwnerTaskCustoms> {

	   //计算成功任务数
	   @Query("select count(*) from OwnerTaskCustoms where state in (1,7) and loginName=?")
	   Long countBySuccess(String loginName);
	   
	 //获取反馈状态，非0,1,7,-3
	   @Query("select p from OwnerTaskCustoms p where p.loginName=?1 and p.state not in (-3,0,1,7) order by p.createTaskDate desc")
	   List<OwnerTaskCustoms> findFeedBack(String loginName);
	   
	   List<OwnerTaskCustoms> findByLoginNameOrderByCreateTaskDateDesc(String LoginName);


}

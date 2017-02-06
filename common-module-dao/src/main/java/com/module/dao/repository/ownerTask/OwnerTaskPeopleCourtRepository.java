package com.module.dao.repository.ownerTask;

import com.module.dao.entity.ownerTask.OwnerTaskPeopleCourt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface OwnerTaskPeopleCourtRepository extends PagingAndSortingRepository<OwnerTaskPeopleCourt, Long>,JpaSpecificationExecutor<OwnerTaskPeopleCourt>,JpaRepository<OwnerTaskPeopleCourt,Long> {

    List<OwnerTaskPeopleCourt> findByLoginNameOrderByCreateTaskDateDesc(String loginName);
    
     //计算成功任务数
	   @Query("select count(*) from OwnerTaskPeopleCourt where  state in (1,7) and loginName=?1")
	   Long countBySuccess(String loginName);
	   
	   //获取反馈状态，非0,1,7,-3
	   @Query("select p from OwnerTaskPeopleCourt p where p.loginName=?1 and p.state not in (-3,0,1,7) order by p.createTaskDate desc")
	   List<OwnerTaskPeopleCourt> findFeedBack(String loginName);
	   
	   OwnerTaskPeopleCourt findByLoginNameAndId(String loginName,Long id);
}

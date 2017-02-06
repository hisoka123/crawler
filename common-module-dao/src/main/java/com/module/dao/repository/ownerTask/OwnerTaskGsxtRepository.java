package com.module.dao.repository.ownerTask;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.ownerTask.OwnerTaskGsxt;

@Repository
public interface OwnerTaskGsxtRepository extends JpaRepository<OwnerTaskGsxt,Long> {

	   //查询全部任务
	   List<OwnerTaskGsxt> findByLoginNameOrderByCreateTaskDateDesc(String loginName);
	   
	   //计算成功任务数
	   @Query("select count(*) from OwnerTaskGsxt where state in (1,7) and loginName=?")
	   Long countBySuccess(String loginName);
	   
	   //查询全部任务状态
	   @Query("select distinct g.state from OwnerTaskGsxt g where loginName=?1")
	   List<Integer> findState(String loginName);
	   
	   //全部地区、特定任务状态(除反馈)、全部公司
	   @Query("select g from OwnerTaskGsxt g where g.loginName=?1 and g.state in (?2) order by g.createTaskDate desc")
	   List<OwnerTaskGsxt> findByTaskState(String loginName,List<Integer> states);
	   
	   //全部地区、特定任务状态(除反馈)、特定公司
	   @Query("select g from OwnerTaskGsxt g where g.loginName=?1 and g.state in (?2) and g.name like %?3% order by g.createTaskDate desc")
	   List<OwnerTaskGsxt> findByTaskStateAndCompany(String loginName,List<Integer> states,String company);
	   
	   //部分地区、特定任务状态(除反馈)、全部公司
	   @Query("select g from OwnerTaskGsxt g where g.loginName=?1 and g.state in (?2) and g.city in (?3) order by g.createTaskDate desc")
	   List<OwnerTaskGsxt> findByPartArea(String loginName,List<Integer> states,List<String> areas);
	   
	   //部分地区、特定任务状态(除反馈)、特定公司
	   @Query("select g from OwnerTaskGsxt g where g.loginName=?1 and g.state in (?2) and g.city in (?3) and g.name like %?4% order by g.createTaskDate desc")
	   List<OwnerTaskGsxt> findByPartAreaAndCompany(String loginName,List<Integer> states,List<String> areas,String company);
	   
	   //id查询
	   OwnerTaskGsxt findByLoginNameAndId(String loginName,long id);
	   
	   //全部地区、反馈状态、全部公司
	   @Query("select g from OwnerTaskGsxt g where g.loginName=?1 and g.state not in (0,1,7,-3) order by g.createTaskDate desc")
	   List<OwnerTaskGsxt> findByFeedback(String loginName);
	   
	   //全部地区、反馈状态、特定公司
	   @Query("select g from OwnerTaskGsxt g where g.loginName=?1 and g.state not in (0,1,7,-3) and g.name like %?2% order by g.createTaskDate desc")
	   List<OwnerTaskGsxt> findByCompanyFeedback(String loginName,String company);
	   
	   //部分地区、反馈状态、全部公司
	   @Query("select g from OwnerTaskGsxt g where g.loginName=?1 and g.state not in (0,1,7,-3) and g.city in (?2) order by g.createTaskDate desc")
	   List<OwnerTaskGsxt> findByPartAreaFeedback(String loginName,List<String> areas);
	   
	   //部分地区、反馈状态、特定公司
	   @Query("select g from OwnerTaskGsxt g where g.loginName=?1 and g.state not in (0,1,7,-3) and g.city in (?2) and g.name like %?3% order by g.createTaskDate desc")
	   List<OwnerTaskGsxt> findByPartAreaAndCompanyFeedback(String loginName,List<String> areas,String company);
	   
}

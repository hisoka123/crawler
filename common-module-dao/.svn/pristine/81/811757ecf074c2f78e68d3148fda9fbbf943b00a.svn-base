package com.module.dao.repository.timeTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.module.dao.entity.timeTask.TaskTrace;

public interface TaskTrackDao extends PagingAndSortingRepository<TaskTrace, Long>,JpaSpecificationExecutor<TaskTrace>, JpaRepository<TaskTrace, Long>{

	Page<TaskTrace> findByTaskNameAndTaskGroupOrderByExecuteTimeDesc( String taskName,String taskGroup,Pageable pageable);
	
	Page<TaskTrace> findByTaskNameAndTaskGroupAndExceptionIsNotNullOrderByExecuteTimeDesc( String taskName,String taskGroup,Pageable pageable);
	
	Page<TaskTrace> findByTaskNameAndTaskGroupAndExceptionIsNullOrderByExecuteTimeDesc( String taskName,String taskGroup,Pageable pageable);
}

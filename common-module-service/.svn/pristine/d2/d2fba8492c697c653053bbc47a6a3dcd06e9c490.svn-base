package com.crawlermanage.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.module.dao.entity.timeTask.TaskTrace;
import com.module.dao.repository.timeTask.TaskTrackDao;

@Service
@Transactional
public class TaskTrackService {


	@Autowired
	TaskTrackDao taskTrackDao;
	
	public <S extends TaskTrace> S save(S entity) {
		return taskTrackDao.save(entity);
	}
	
	public Page<TaskTrace> findByTaskNameAndTaskGroupOrderByExecuteTimeDesc(String taskName,String taskGroup,int pageSize,int pageNumber,String pageOption
			) {
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<TaskTrace> page = taskTrackDao.findByTaskNameAndTaskGroupOrderByExecuteTimeDesc(taskName, taskGroup, pageRequest);
		//当pageOptiong为0时上一页
		if( "previous".equals(pageOption)){
			pageRequest = (PageRequest) page.previousPageable();
			//当pageOptiong为1时下一页
		}else if("next".equals(pageOption)){
			pageRequest = (PageRequest) page.nextPageable();
		}else if("first".equals(pageOption)){
			pageRequest = (PageRequest) pageRequest.first();
		}else if("last".equals(pageOption)){
			 pageNumber = page.getTotalPages()-1;
			 pageRequest = new PageRequest(pageNumber, pageSize);
		}
		return taskTrackDao.findByTaskNameAndTaskGroupOrderByExecuteTimeDesc(taskName, taskGroup, pageRequest);
	}
	
	public Page<TaskTrace> findByTaskNameAndTaskGroupAndExceptionIsNotNullOrderByExecuteTimeDesc(String taskName,String taskGroup,int pageSize,int pageNumber,String pageOption
			) {
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<TaskTrace> page = taskTrackDao.findByTaskNameAndTaskGroupAndExceptionIsNotNullOrderByExecuteTimeDesc(taskName, taskGroup, pageRequest);
		//当pageOptiong为0时上一页
		if( "previous".equals(pageOption)){
			pageRequest = (PageRequest) page.previousPageable();
			//当pageOptiong为1时下一页
		}else if("next".equals(pageOption)){
			pageRequest = (PageRequest) page.nextPageable();
		}else if("first".equals(pageOption)){
			pageRequest = (PageRequest) pageRequest.first();
		}else if("last".equals(pageOption)){
			 pageNumber = page.getTotalPages()-1;
			 pageRequest = new PageRequest(pageNumber, pageSize);
		}
		return taskTrackDao.findByTaskNameAndTaskGroupAndExceptionIsNotNullOrderByExecuteTimeDesc(taskName, taskGroup, pageRequest);
	}
	
	public Page<TaskTrace> findByTaskNameAndTaskGroupAndExceptionIsNullOrderByExecuteTimeDesc(String taskName,String taskGroup,int pageSize,int pageNumber,String pageOption
			) {
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<TaskTrace> page = taskTrackDao.findByTaskNameAndTaskGroupAndExceptionIsNullOrderByExecuteTimeDesc(taskName, taskGroup, pageRequest);
		//当pageOptiong为0时上一页
		if( "previous".equals(pageOption)){
			pageRequest = (PageRequest) page.previousPageable();
			//当pageOptiong为1时下一页
		}else if("next".equals(pageOption)){
			pageRequest = (PageRequest) page.nextPageable();
		}else if("first".equals(pageOption)){
			pageRequest = (PageRequest) pageRequest.first();
		}else if("last".equals(pageOption)){
			 pageNumber = page.getTotalPages()-1;
			 pageRequest = new PageRequest(pageNumber, pageSize);
		}
		return taskTrackDao.findByTaskNameAndTaskGroupAndExceptionIsNullOrderByExecuteTimeDesc(taskName, taskGroup, pageRequest);
	}
	
	
}

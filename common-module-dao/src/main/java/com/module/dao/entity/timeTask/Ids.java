package com.module.dao.entity.timeTask;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.module.dao.entity.data.IdEntity;
import com.module.dao.entity.timeTask.TaskTrace;


/**
 * @author lyg
 *报警设置实体
 */
@Entity
@Table(name = "Ids")
public class Ids extends IdEntity implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = -5181610290707654608L;
	
	
	private TaskTrace taskTrace;
	
	private Long tid;
	
	

	public void setTaskTrace(TaskTrace taskTrace) {
		this.taskTrace = taskTrace;
	}
	@ManyToOne
	public TaskTrace getTaskTrace() {
		return taskTrace;
	}
	
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}
	
}

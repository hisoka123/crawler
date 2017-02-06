package com.module.dao.entity.ownerTask;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="OwnerTask_all")
@SequenceGenerator(name="ownerTaskAllseq",sequenceName="ownerTaskAll_sequence",allocationSize=1)
public class OwnerTaskAll implements Serializable {

	   private static final long serialVersionUID = -2925465462601031917L;
	
	   @Id
	   @GeneratedValue(generator="ownerTaskAllseq",strategy=GenerationType.SEQUENCE)
	   private long id;
	 
	   private String loginName;
	   private String taskType;
	   private long taskNum;
	   
	   @Transient
	   private long taskSuccessNum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public long getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(long taskNum) {
		this.taskNum = taskNum;
	}

	public long getTaskSuccessNum() {
		return taskSuccessNum;
	}

	public void setTaskSuccessNum(long taskSuccessNum) {
		this.taskSuccessNum = taskSuccessNum;
	}

	@Override
	public String toString() {
		return "OwnerTaskAll [id=" + id + ", loginName=" + loginName
				+ ", taskType=" + taskType + ", taskNum=" + taskNum
				+ ", taskSuccessNum=" + taskSuccessNum + "]";
	}
	   
	   
}

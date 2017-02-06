package com.module.dao.entity.timeTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.module.dao.entity.data.IdEntity;


/**
 * @author lyg
 *定时任务执行记录实体an
 */
@Entity
@Table(name = "TaskTrace")
public class TaskTrace extends IdEntity implements Serializable{




	/**
	 * 
	 */
	private static final long serialVersionUID = -5181610290357616608L;
	
	private String taskName;
	
	private String taskGroup;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date executeTime;
	
	private String executeState;
	
	private long duration;
	
	private int storageNum;
	
	private String content;
	
	private List<Ids> tweetIds;
	
	private Exception exception;
	
	private String  arguments;
	
	private Long cId;
	
	private String cName;
	
	@Column(columnDefinition="TEXT")
	@Basic(fetch = FetchType.LAZY)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Transient
	public List<Long> getIds() {
		List<Ids> idList = getTweetIds();
		List<Long> tid = new ArrayList<Long>();
		for (Ids id : idList) {
			tid.add(id.getTid());
		}
		
		return tid;
	}
	
	public void setIds(List<Long> tid) {
	}
	public int getStorageNum() {
		return storageNum;
	}

	public void setStorageNum(int storageNum) {
		this.storageNum = storageNum;
	}
	
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getExecuteState() {
		return executeState;
	}

	public void setExecuteState(String executeState) {
		this.executeState = executeState;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="taskTrace")
	public List<Ids> getTweetIds() {
		return tweetIds;
	}
	
	public void setTweetIds(List<Ids> tweetIds) {
		this.tweetIds = tweetIds;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
	
}

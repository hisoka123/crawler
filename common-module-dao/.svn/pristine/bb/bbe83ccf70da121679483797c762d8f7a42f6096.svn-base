package com.module.dao.entity.ownerTask;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 人法网
 */
@Entity
@Table(name = "OwnerTask_people_court")
@SequenceGenerator(name="ownerTaskPeopleCourtSeq",sequenceName="ownerTaskPeopleCourt_sequence",allocationSize=1)
public class OwnerTaskPeopleCourt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "ownerTaskPeopleCourtSeq", strategy = GenerationType.SEQUENCE)
    private long id;

    private String loginName; //用户
    private String searchType; //查询类型　 名字、身份证号码
    private String keyWord; //关键字
    private int state;  //任务状态
    private Date createTaskDate;   //任务创建时间
    private Date completeTaskDate; //完成时间
    private long tasktimeId;       //对应定时任务中的id

    public OwnerTaskPeopleCourt() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTaskDate() {
        return createTaskDate;
    }

    public void setCreateTaskDate(Date createTaskDate) {
        this.createTaskDate = createTaskDate;
    }

    public Date getCompleteTaskDate() {
        return completeTaskDate;
    }

    public void setCompleteTaskDate(Date completeTaskDate) {
        this.completeTaskDate = completeTaskDate;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    
    public long getTasktimeId() {
		return tasktimeId;
	}

	public void setTasktimeId(long tasktimeId) {
		this.tasktimeId = tasktimeId;
	}

	@Override
	public String toString() {
		return "OwnerTaskPeopleCourt [id=" + id + ", loginName=" + loginName
				+ ", searchType=" + searchType + ", keyWord=" + keyWord
				+ ", state=" + state + ", createTaskDate=" + createTaskDate
				+ ", completeTaskDate=" + completeTaskDate + ", tasktimeId="
				+ tasktimeId + "]";
	}

	
}


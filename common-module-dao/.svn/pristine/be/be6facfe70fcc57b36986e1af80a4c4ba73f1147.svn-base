package com.module.dao.entity.ownerTask;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 法海网
 */

@Entity
@Table(name = "OwnerTask_fahaicc")
@SequenceGenerator(name="ownerTaskFahaiccSeq",sequenceName="ownerTaskFahaicc_sequence",allocationSize=1)
public class OwnerTaskFahaicc implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "ownerTaskFahaiccSeq", strategy = GenerationType.SEQUENCE)
    private long id;

    private String loginName; //用户
    private String searchType; //查询类型　 公司名称、身份证号码
    private String keyWord; //关键字
    private long timetaskId;
    private int state;  //任务状态
    private Date createTaskDate;   //任务创建时间
    private Date completeTaskDate; //完成时间

    public OwnerTaskFahaicc() {
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
    
    

    public long getTimetaskId() {
		return timetaskId;
	}

	public void setTimetaskId(long timetaskId) {
		this.timetaskId = timetaskId;
	}

	@Override
	public String toString() {
		return "OwnerTaskFahaicc [id=" + id + ", loginName=" + loginName
				+ ", searchType=" + searchType + ", keyWord=" + keyWord
				+ ", timetaskId=" + timetaskId + ", state=" + state
				+ ", createTaskDate=" + createTaskDate + ", completeTaskDate="
				+ completeTaskDate + "]";
	}

	
}

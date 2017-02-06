package com.module.dao.entity.ownerTask;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 浙江法院公开网
 *
 */

@Entity
@Table(name = "OwnerTask_zjCourt")
@SequenceGenerator(name="ownerTaskZjCourtSeq",sequenceName="ownerTaskZjCourt_sequence",allocationSize=1)
public class OwnerTaskZjCourt implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "ownerTaskZjCourtSeq", strategy = GenerationType.SEQUENCE)
    private long id;

    private String loginName; //用户
//    private String firstLevelModule;  //一级模块 （如：执行案件信息查询、曝光台、执行惩戒）
//    private String twoLevelModule;    //二级模块 (tab标签)
    private String searchType; //查询类型　 （姓名、证件号码、案号、执行法院、立案日期等）
    private String keyWord; //关键字
    private int state;  //任务状态
    private Date createTaskDate;   //任务创建时间
    private Date completeTaskDate; //完成时间
    private long timeTaskId;       //对应定时任务中的id
    private String type; //类型
    private String detailType; //具体详情类型
    public OwnerTaskZjCourt() {
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

    public long getTimeTaskId() {
        return timeTaskId;
    }

    public void setTimeTaskId(long timeTaskId) {
        this.timeTaskId = timeTaskId;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	@Override
    public String toString() {
        return "OwnerTaskZjCourt{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", searchType='" + searchType + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", state=" + state +
                ", createTaskDate=" + createTaskDate +
                ", completeTaskDate=" + completeTaskDate +
                ", timeTaskId=" + timeTaskId +
                ", type=" + type +
                 ", detailType=" + detailType +
                '}';
    }
}

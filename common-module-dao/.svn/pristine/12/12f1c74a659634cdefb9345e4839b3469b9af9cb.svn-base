package com.module.dao.entity.ownerTask;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 海关网
 */
@Entity
@Table(name = "OwnerTask_customs")
@SequenceGenerator(name = "ownerTaskCustomsSeq",sequenceName = "ownerTaskCustoms_sequence",allocationSize = 1)
public class OwnerTaskCustoms implements Serializable{
    private static final long serialVersionUID = 2433923035772150134L;

    @Id
    @GeneratedValue(generator = "ownerTaskCustomsSeq", strategy = GenerationType.SEQUENCE)
    private long id;

    private String loginName; //用户
    private String keyWord; //关键字 (企业名称或组织机构代码或社会信用代码)
    private int state;  //任务状态
    private Date createTaskDate;   //任务创建时间
    private Date completeTaskDate; //完成时间
    private long timeTaskId;       //对应定时任务中的id

    public OwnerTaskCustoms() {
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

    @Override
    public String toString() {
        return "OwnerTaskCustoms{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", state=" + state +
                ", createTaskDate=" + createTaskDate +
                ", completeTaskDate=" + completeTaskDate +
                ", timeTaskId=" + timeTaskId +
                '}';
    }
}

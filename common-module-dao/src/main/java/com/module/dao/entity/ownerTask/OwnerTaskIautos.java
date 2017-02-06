package com.module.dao.entity.ownerTask;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 第一车网
 */
@Entity
@Table(name = "OwnerTask_iautos")
@SequenceGenerator(name="OwnerTaskIautosSeq",sequenceName="OwnerTaskIautos_sequence",allocationSize=1)
public class OwnerTaskIautos implements Serializable{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "OwnerTaskIautosSeq", strategy = GenerationType.SEQUENCE)
    private long id;
    private String loginName; //用户
    private String city; //要查询的城市(全拼)
    private String keyWord; //关键字
    private int state;  //任务状态
    private Date createTaskDate;   //任务创建时间
    private Date completeTaskDate; //完成时间

    private long tasktimeid;       //对应定时任务中的id
    public OwnerTaskIautos() {
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

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getTasktimeid() {
		return tasktimeid;
	}

	public void setTasktimeid(long tasktimeid) {
		this.tasktimeid = tasktimeid;
	}

	@Override
	public String toString() {
		return "OwnerTaskIautos [id=" + id + ", loginName=" + loginName
				+ ", city=" + city + ", keyWord=" + keyWord + ", state="
				+ state + ", createTaskDate=" + createTaskDate
				+ ", completeTaskDate=" + completeTaskDate + ", tasktimeid="
				+ tasktimeid + "]";
	}
	
	
}

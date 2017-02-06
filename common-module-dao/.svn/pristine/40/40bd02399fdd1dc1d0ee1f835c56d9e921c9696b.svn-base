package com.module.dao.entity.ownerTask;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OwnerTask_gsxt")
@SequenceGenerator(name="ownerTaskGsxtSeq",sequenceName="ownerTaskGsxt_sequence",allocationSize=1)
public class OwnerTaskGsxt implements Serializable {

	   private static final long serialVersionUID = 359468156487813128L;
	
	   @Id
	   @GeneratedValue(generator="ownerTaskGsxtSeq",strategy=GenerationType.SEQUENCE)
	   private long id;
	   
	   private String loginName;
	   private String city;
	   private String name;
	   private int state;
	   private long company_id;
	   private Date createTaskDate;
	   private Date completeTaskDate;
	   
	   
	public OwnerTaskGsxt() {
		
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


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public long getCompany_id() {
		return company_id;
	}


	public void setCompany_id(long company_id) {
		this.company_id = company_id;
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


	@Override
	public String toString() {
		return "OwnerTaskGsxt [id=" + id + ", loginName=" + loginName
				+ ", city=" + city + ", name=" + name + ", state=" + state
				+ ", company_id=" + company_id + ", createTaskDate="
				+ createTaskDate + ", completeTaskDate=" + completeTaskDate
				+ "]";
	}
	   
	     
	   
	   
	

}

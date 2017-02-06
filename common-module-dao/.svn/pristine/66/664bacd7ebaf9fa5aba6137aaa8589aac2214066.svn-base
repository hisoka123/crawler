package com.module.dao.entity.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Email")
public class Email extends IdEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4141044068750308875L;
	
	private String address;
	
	private List<Alarm> alarms;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy ="emails")
	public List<Alarm> getAlarm() {
		return alarms;
	}

	public void setAlarm(List<Alarm> alarms) {
		this.alarms = alarms;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

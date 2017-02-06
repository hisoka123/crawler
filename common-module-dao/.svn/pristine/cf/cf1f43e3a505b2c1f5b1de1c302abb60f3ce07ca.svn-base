package com.module.dao.entity.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author lyg
 *报警设置实体
 */
@Entity
@Table(name = "Alarm")
public class Alarm extends IdEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5181610290307616608L;
	
	private String name;
	
	
	
	private Date updatime = new Date();
	
	private List<Region> regions;

	private List<Email> emails;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatime() {
		return updatime;
	}

	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	@ManyToMany()
	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

}

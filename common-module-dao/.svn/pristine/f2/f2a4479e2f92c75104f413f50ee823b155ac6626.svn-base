/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.module.dao.entity.auth;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
 


import com.module.dao.entity.data.IdEntity;


/**
 * 开发团队.
 * 
 */
@Entity
@Table(name = "Team") 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Group extends IdEntity {

	private String name;
	
	private ActiveUser master;
	
	private List<ActiveUser> userList = new ArrayList();
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@OneToOne
	@JoinColumn(name = "master_id")
	public ActiveUser getMaster() {
		return master;
	}

	public void setMaster(ActiveUser master) {
		this.master = master;
	}

	@OneToMany(mappedBy = "group")
	public List<ActiveUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ActiveUser> userList) {
		this.userList = userList;
	}
}

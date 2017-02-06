/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.module.dao.entity.auth;

import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.security.auth.AuthPermission;

import org.apache.commons.lang3.builder.ToStringBuilder; 
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.module.dao.entity.data.IdEntity;
import org.springframework.util.StringUtils;


/**
 * 角色.
 * 
 * @author calvin
 */
@Entity
@Table(name = "Role") 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role  extends IdEntity {

	private String name; 

	private String permissions;

	public Role() {
	}

    public Role(String name,String permissions) {
        this.name = name;
        this.permissions =  permissions;
    }

	public Role(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	@Transient
	public List<String> getPermissionList() {
		List<String> permissionList=  new ArrayList<>();
		String[] permissionArry = permissions.split(",");
		for(String permission:permissionArry){
			permissionList.add(permission);
		}
		return permissionList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

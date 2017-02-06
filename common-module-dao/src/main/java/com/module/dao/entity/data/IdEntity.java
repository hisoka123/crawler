package com.module.dao.entity.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdEntity {

	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//identity
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

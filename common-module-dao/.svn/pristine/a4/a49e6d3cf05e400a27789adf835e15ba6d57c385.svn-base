package com.module.dao.entity.timeTask;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.module.dao.entity.data.IdEntity;


/**
 * @author lyg
 *报警设置实体
 */
@Entity
@Table(name = "Exception")
public class Exception extends IdEntity implements Serializable{




	/**
	 * 
	 */
	private static final long serialVersionUID = -5181610290507654608L;
	
	private String message;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	
}

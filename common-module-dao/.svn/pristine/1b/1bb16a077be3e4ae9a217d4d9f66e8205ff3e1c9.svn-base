package com.module.dao.entity.gsxt;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 抵押人概况
 */

@Entity
@Table(name="t_dyrgk")
@NamedQuery(name="TDyrgk.findAll", query="SELECT t FROM TDyrgk t")
public class TDyrgk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String infoType;
	
	private String name;
	
	private String idType;
	
	private String idNum;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;
	
	@ManyToOne
	@JoinColumn(name="dcdydjxx_id")
	private TDcdydjxx TDcdydjxx;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public TDcdydjxx getTDcdydjxx() {
		return TDcdydjxx;
	}

	public void setTDcdydjxx(TDcdydjxx tDcdydjxx) {
		TDcdydjxx = tDcdydjxx;
	}
	
}

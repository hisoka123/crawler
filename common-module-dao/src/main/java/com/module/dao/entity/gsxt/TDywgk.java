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
 * 抵押物概况
 */

@Entity
@Table(name="t_dywgk")
@NamedQuery(name="TDywgk.findAll", query="SELECT t FROM TDywgk t")
public class TDywgk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String infoType;
	
	private String name;
	
	private String ownerShip;
	
	private String generalSituation;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String note;
	
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

	public String getOwnerShip() {
		return ownerShip;
	}

	public void setOwnerShip(String ownerShip) {
		this.ownerShip = ownerShip;
	}

	public String getGeneralSituation() {
		return generalSituation;
	}

	public void setGeneralSituation(String generalSituation) {
		this.generalSituation = generalSituation;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

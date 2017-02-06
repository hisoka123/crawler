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
 * 动产抵押登记信息变更
 */

@Entity
@Table(name="t_dcdydjxxbg")
@NamedQuery(name="TDcdydjxxbg.findAll", query="SELECT t FROM TDcdydjxxbg t")
public class TDcdydjxxbg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String infoType;
	
	private String changeDateTime;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String changeContent;
	
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

	public String getChangeDateTime() {
		return changeDateTime;
	}

	public void setChangeDateTime(String changeDateTime) {
		this.changeDateTime = changeDateTime;
	}

	public String getChangeContent() {
		return changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	public TDcdydjxx getTDcdydjxx() {
		return TDcdydjxx;
	}

	public void setTDcdydjxx(TDcdydjxx tDcdydjxx) {
		TDcdydjxx = tDcdydjxx;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}

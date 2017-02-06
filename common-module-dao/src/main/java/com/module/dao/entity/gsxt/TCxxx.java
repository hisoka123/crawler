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

@Entity
@Table(name="t_cxxx")
@NamedQuery(name="TCxxx.findAll", query="SELECT t FROM TCxxx t")
public class TCxxx implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String infoType;
	
	private String revokeItem;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String revokePreContent;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String revokePostContent;
	
	private String revokeDateTime;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;
	
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

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

	public String getRevokeItem() {
		return revokeItem;
	}

	public void setRevokeItem(String revokeItem) {
		this.revokeItem = revokeItem;
	}

	public String getRevokePreContent() {
		return revokePreContent;
	}

	public void setRevokePreContent(String revokePreContent) {
		this.revokePreContent = revokePreContent;
	}

	public String getRevokePostContent() {
		return revokePostContent;
	}

	public void setRevokePostContent(String revokePostContent) {
		this.revokePostContent = revokePostContent;
	}

	public String getRevokeDateTime() {
		return revokeDateTime;
	}

	public void setRevokeDateTime(String revokeDateTime) {
		this.revokeDateTime = revokeDateTime;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public TQyjbxx getTQyjbxx() {
		return TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx tQyjbxx) {
		TQyjbxx = tQyjbxx;
	}

	@Override
	public String toString() {
		return "TCxxx [id=" + id + ", infoType=" + infoType + ", revokeItem="
				+ revokeItem + ", revokePreContent=" + revokePreContent
				+ ", revokePostContent=" + revokePostContent
				+ ", revokeDateTime=" + revokeDateTime + ", html=" + html
				+ ", TQyjbxx=" + TQyjbxx + "]";
	}
	
}

package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * 主管部门（出资人）信息
 */

@Entity
@Table(name="t_zgbmxx")
@NamedQuery(name="TZgbmxx.findAll", query="SELECT t FROM TZgbmxx t")
public class TZgbmxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String infoType;
	
	private String type;
	
	private String name;
	
	private String idType;
	
	private String idNum;
	
	private String showDateTime;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getShowDateTime() {
		return showDateTime;
	}

	public void setShowDateTime(String showDateTime) {
		this.showDateTime = showDateTime;
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
	
}
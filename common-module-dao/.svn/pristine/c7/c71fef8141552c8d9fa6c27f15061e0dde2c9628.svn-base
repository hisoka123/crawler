package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_dwtzxx database table.
 * 
 */
@Entity
@Table(name="t_dwtzxx")
@NamedQuery(name="TDwtzxx.findAll", query="SELECT t FROM TDwtzxx t")
public class TDwtzxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String regnum;

	private String tzslqyhgmgqqyname;

	//bi-directional many-to-one association to TQynb
	@ManyToOne
	@JoinColumn(name="qynb_id")
	private TQynb TQynb;

	public TDwtzxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getInfotype() {
		return this.infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getRegnum() {
		return this.regnum;
	}

	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}

	public String getTzslqyhgmgqqyname() {
		return this.tzslqyhgmgqqyname;
	}

	public void setTzslqyhgmgqqyname(String tzslqyhgmgqqyname) {
		this.tzslqyhgmgqqyname = tzslqyhgmgqqyname;
	}

	public TQynb getTQynb() {
		return this.TQynb;
	}

	public void setTQynb(TQynb TQynb) {
		this.TQynb = TQynb;
	}

}
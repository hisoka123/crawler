package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_zyryxx database table.
 * 
 */
@Entity
@Table(name="t_zyryxx")
@NamedQuery(name="TZyryxx.findAll", query="SELECT t FROM TZyryxx t")
public class TZyryxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String name;

	private String position;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	public TZyryxx() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

}
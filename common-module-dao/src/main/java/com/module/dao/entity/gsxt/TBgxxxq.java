package com.module.dao.entity.gsxt;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_bgxxxq database table.
 * 
 */
@Entity
@Table(name="t_bgxxxq")
@NamedQuery(name="TBgxxxq.findAll", query="SELECT t FROM TBgxxxq t")
public class TBgxxxq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer status;

	private String typeorposition;

	//bi-directional many-to-one association to TBgxx
	@ManyToOne
	@JoinColumn(name="bgxx_id")
	private TBgxx TBgxx;

	public TBgxxxq() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTypeorposition() {
		return this.typeorposition;
	}

	public void setTypeorposition(String typeorposition) {
		this.typeorposition = typeorposition;
	}

	public TBgxx getTBgxx() {
		return this.TBgxx;
	}

	public void setTBgxx(TBgxx TBgxx) {
		this.TBgxx = TBgxx;
	}

}
package com.module.dao.entity.sipo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="l_sipo_keyword",uniqueConstraints={@UniqueConstraint(columnNames={"type", "name"})})
@NamedQuery(name="SipoKeyword.findAll", query="SELECT t FROM SipoKeyword t")
public class SipoKeyword implements Serializable{

	private static final long serialVersionUID = 6712934685287200429L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String type;
	
	@Column(columnDefinition="int default 0")
	private Integer state;
	
	@Column(columnDefinition="int default 0")
	private Integer num;

	//表示总共被爬取了多少次
	@Column(name="total_num", columnDefinition="int default 0")
	private Integer totalNum;
	
	// 优先级 数字越大，爬取优先级越高（默认为0）
	@Column(columnDefinition="int default 0")
	private int priority;
	
	@OneToMany(mappedBy="sipoKeyword",cascade=CascadeType.ALL)
	private Set<Sipo> sipos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Set<Sipo> getSipos() {
		return sipos;
	}
	public void setSipos(Set<Sipo> sipos) {
		this.sipos = sipos;
	}

}

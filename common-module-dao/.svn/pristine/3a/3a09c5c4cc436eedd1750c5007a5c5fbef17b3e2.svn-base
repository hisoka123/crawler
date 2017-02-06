package com.module.dao.entity.iautos;

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
@Table(name="c_iautos_keyword",uniqueConstraints={@UniqueConstraint(columnNames={"city", "name"})})
@NamedQuery(name="IautosKeyword.findAll", query="SELECT t FROM IautosKeyword t")
public class IautosKeyword  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3237373366477369032L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(columnDefinition="int default 0")
	private Integer state;

	private String city;
	
	@Column(columnDefinition="int default 0")
	private Integer num;
	
	//表示总共被爬取了多少次
	@Column(name="total_num", columnDefinition="int default 0")
	private Integer totalNum;
	
	// 优先级 数字越大，爬取优先级越高（默认为0）
	@Column(columnDefinition="int default 0")
	private int priority;

	@OneToMany(mappedBy="iautosKeyword",cascade=CascadeType.ALL)
	private Set<Iautos> iautos;

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public Set<Iautos> getIautos() {
		return iautos;
	}

	public void setIautos(Set<Iautos> iautos) {
		this.iautos = iautos;
	}

}

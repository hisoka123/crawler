package com.module.dao.entity.fahaicc;

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
@Table(name="f_root", uniqueConstraints={@UniqueConstraint(columnNames={"type", "keyword"})})
@NamedQuery(name="FahaiccRoot.findAll", query="SELECT t FROM FahaiccRoot t")
public class FahaiccRoot implements Serializable {
	private static final long serialVersionUID = -5965716009480019943L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String keyword;
	
	private String type;
	
	@Column(columnDefinition="int default 0")
	private Integer num;
	
	@Column(name="total_num", columnDefinition="int default 0")
	private Integer totalNum;
	
	@Column(columnDefinition="int default 0")
	private Integer priority;
	
	@Column(columnDefinition="int default 0")
	private Integer state;

	@OneToMany(mappedBy="fahaiccRoot", cascade=CascadeType.ALL)
	private Set<FahaiccResult> fahaiccResults;
	
	public FahaiccRoot(){}
	
	public FahaiccRoot(Long id,String keyword,String type,Integer num,Integer totalNum,Integer priority,Integer state){
		
		   this.id=id;
		   this.keyword=keyword;
		   this.type=type;
		   this.num=num;
		   this.totalNum=totalNum;
		   this.priority=priority;
		   this.state=state;
		
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<FahaiccResult> getFahaiccResults() {
		return fahaiccResults;
	}

	public void setFahaiccResults(Set<FahaiccResult> fahaiccResults) {
		this.fahaiccResults = fahaiccResults;
	}

	@Override
	public String toString() {
		return "FahaiccRoot [id=" + id + ", keyword=" + keyword + ", type="
				+ type + ", num=" + num + ", totalNum=" + totalNum
				+ ", priority=" + priority + ", state=" + state + "]";
	}
	
}

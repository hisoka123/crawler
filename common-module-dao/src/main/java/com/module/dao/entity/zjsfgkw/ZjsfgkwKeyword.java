package com.module.dao.entity.zjsfgkw;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name="g_zjsfgkw_keyword", uniqueConstraints={@UniqueConstraint(columnNames={"type", "value"})})
@SequenceGenerator(name="zjsfgkwkeywordSeq",sequenceName="zjsfgkwkeyword_sequence",allocationSize=1)
public class ZjsfgkwKeyword implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String value;//数据值
	private Integer state;//状态
	private Integer num;//访问次数
	@Column(name="total_num",columnDefinition="int default 0")
	private Integer totalNum;//访问总数
	private int priority;//优先级  数字越大，爬取优先级越高（默认为0）
	private String type;//类型
	private String propertyType;//属性类型
	@OneToMany(mappedBy="zjsfgkwKeyword",cascade=CascadeType.ALL)
	private Set<Zjsfgkw> zjsfgkws;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<Zjsfgkw> getZjsfgkws() {
		return zjsfgkws;
	}
	public void setZjsfgkws(Set<Zjsfgkw> zjsfgkws) {
		this.zjsfgkws = zjsfgkws;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	

}

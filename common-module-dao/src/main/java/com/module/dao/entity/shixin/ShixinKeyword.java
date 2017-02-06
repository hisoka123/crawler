package com.module.dao.entity.shixin;

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
@Table(name="s_dishonesty_keyword",uniqueConstraints={@UniqueConstraint(columnNames={"keyword", "cardNum"})})
@SequenceGenerator(name="shixinkeywordSeq",sequenceName="shixinkeyword_sequence",allocationSize=1)
public class ShixinKeyword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5258010405614390063L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String keyword;//关键词
	private String type;//类型
	private String name;//姓名
	private String cardNum;//身份证号码/组织机构代码
	private Integer state;//状态
	private Integer num;//访问次数
	@Column(name="total_num",columnDefinition="int default 0")
	private Integer totalNum;//访问总数
	//优先级  数字越大，爬取优先级越高（默认为0）
	private int priority;
	@OneToMany(mappedBy="shixinKeyword",cascade=CascadeType.ALL)
	private Set<Shixin> shixins;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Set<Shixin> getShixins() {
		return shixins;
	}
	public void setShixins(Set<Shixin> shixins) {
		this.shixins = shixins;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	
}

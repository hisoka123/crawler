package com.module.dao.entity.gsxt;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="company", uniqueConstraints={@UniqueConstraint(columnNames={"city", "name"})})
@NamedQuery(name="Company.findAll", query="SELECT t FROM Company t")
@SequenceGenerator(name="companySeq",sequenceName="company_sequence",allocationSize=1)
public class Company implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3363421356245470134L;
	
	private Long id;
	
	private String name;
	
	private String webAddress;
	
	private String eMail;
	
	private Integer state;
	
	private String city;
	
	//表示一个企业在一次定时任务中被爬取的次数（比如验证码错误之后，该企业会被再次爬取），每次添加我的任务，对应企业的num会被置为0
	private Integer num;
	
	//表示一个企业总共被爬取了多少次
	private Integer totalNum;
	
	//优先级  数字越大，爬取优先级越高（默认为0）
	private int priority;
	
	private Set<TQyjbxx> TQyjbxxs;
	
	private Set<TResultJson> TResultJsons;

	
	@Id
	@GeneratedValue(generator="companySeq",strategy=GenerationType.SEQUENCE)
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

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@Column(columnDefinition="int default 0")
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

	@Column(columnDefinition="int default 0")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name="total_num", columnDefinition="int default 0")
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	@Column(columnDefinition="int default 0")
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@OneToMany(mappedBy="Company",cascade=CascadeType.ALL)
	public Set<TQyjbxx> getTQyjbxxs() {
		return TQyjbxxs;
	}

	public void setTQyjbxxs(Set<TQyjbxx> tQyjbxxs) {
		TQyjbxxs = tQyjbxxs;
	}

	@OneToMany(mappedBy="Company",cascade=CascadeType.ALL)
	public Set<TResultJson> getTResultJsons() {
		return TResultJsons;
	}

	public void setTResultJsons(Set<TResultJson> tResultJsons) {
		TResultJsons = tResultJsons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", webAddress="
				+ webAddress + ", eMail=" + eMail + ", state=" + state
				+ ", city=" + city + ", num=" + num + ", totalNum=" + totalNum
				+ ", priority=" + priority + ", TQyjbxxs=" + TQyjbxxs
				+ ", TResultJsons=" + TResultJsons + "]";
	}
}

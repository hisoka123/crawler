/**
 * 
 */
package com.module.dao.entity.pbccrc;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="t_query_record")
@NamedQuery(name="PbcQueryRecord.findAll", query="SELECT e FROM PbcQueryRecord e")
public class PbcQueryRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "num",length = 50)
	private String num; 		//编号

	@Column(name = "query_type",length = 50)
	private String queryType; 	//查询类型  机构查询  个人查询

	@Column(name = "query_date",length = 25)
	private String queryDate; 	//查询日期

	@Column(name = "operator",length = 50)
	private String operator; 	//操作员

	@Column(name = "query_cause",length = 50)
	private String queryCause; 	//查询原因

	@ManyToOne
	@JoinColumn(name="json_id")
	private PlainPbccrcJson plainPbccrcJson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getQueryCause() {
		return queryCause;
	}

	public void setQueryCause(String queryCause) {
		this.queryCause = queryCause;
	}

	public PlainPbccrcJson getPlainPbccrcJson() {
		return plainPbccrcJson;
	}

	public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
		this.plainPbccrcJson = plainPbccrcJson;
	}
}

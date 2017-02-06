package com.crawler.zjsfgkw.domain.json;

import java.io.Serializable;
/**

* 浙法网执行案件信息查询bean

* @author dyx

*/
public class ExecuteCaseSearchJson implements Serializable {

	private static final long serialVersionUID = 1L;
	private String caseNo;//案号
	private String court;//法院
	private String caseState;//案件状态
	private String caseDate;//立案日期
	private String principal;//当事人
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getCaseState() {
		return caseState;
	}
	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}
	public String getCaseDate() {
		return caseDate;
	}
	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	@Override
	public String toString() {
		return "ExecuteCaseSearchJson [caseNo=" + caseNo + ", court=" + court
				+ ", caseState=" + caseState + ", caseDate=" + caseDate
				+ ", principal=" + principal + "]";
	}

}

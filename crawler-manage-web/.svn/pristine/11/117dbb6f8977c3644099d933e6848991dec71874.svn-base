package com.crawlermanage.dao.result;

import java.io.Serializable;
import java.util.List;

import com.crawler.creditchina.domain.jsontwo.CompanyRecord;

public class CreditResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1158558051514684664L;

	private int state; // 爬取企业状态码 0,排队等候；1，成功；7，无关键词
	private int existCode; // 定时任务中企业存在状态码，0，否；1，在
	private long company_id; // 定时任务中企业存在时的id;
	private List<CompanyRecord> companyRecordResult; // 企业详情

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getExistCode() {
		return existCode;
	}

	public void setExistCode(int existCode) {
		this.existCode = existCode;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}

	public List<CompanyRecord> getCompanyRecordResult() {
		return companyRecordResult;
	}

	public void setCompanyRecordResult(List<CompanyRecord> companyRecordResult) {
		this.companyRecordResult = companyRecordResult;
	}

	@Override
	public String toString() {
		return "CreditResult [state=" + state + ", existCode=" + existCode
				+ ", company_id=" + company_id + ", companyRecordResult="
				+ companyRecordResult + "]";
	}

}

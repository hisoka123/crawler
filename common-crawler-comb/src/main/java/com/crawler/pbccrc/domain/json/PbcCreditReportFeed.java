package com.crawler.pbccrc.domain.json;

import java.io.Serializable;
import java.util.List;

public class PbcCreditReportFeed implements Serializable {
	private static final long serialVersionUID = -8489599136031950805L;
	private ReportBase reportBase; 			//表头
	private CreditRecord creditRecord; 		//信贷记录
	private List<QueryRecord> queryRecords; //查询记录
	
	public ReportBase getReportBase() {
		return reportBase;
	}
	public void setReportBase(ReportBase reportBase) {
		this.reportBase = reportBase;
	}
	public CreditRecord getCreditRecord() {
		return creditRecord;
	}
	public void setCreditRecord(CreditRecord creditRecord) {
		this.creditRecord = creditRecord;
	}
	public List<QueryRecord> getQueryRecords() {
		return queryRecords;
	}
	public void setQueryRecords(List<QueryRecord> queryRecords) {
		this.queryRecords = queryRecords;
	}
	
	@Override
	public String toString() {
		return "PbcCreditReportFeed [reportBase=" + reportBase
				+ ", creditRecord=" + creditRecord + ", queryRecords="
				+ queryRecords + "]";
	}
}

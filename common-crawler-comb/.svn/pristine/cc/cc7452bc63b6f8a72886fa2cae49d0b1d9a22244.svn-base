package com.crawler.pbccrc.domain.json;

import java.io.Serializable;

public class ReportData implements Serializable {
	private static final long serialVersionUID = -3350030291792254007L;
	
	private String statusCode;
	private String message;
	private String loggedCookiesJson;
	private PbcCreditReportFeed report;
	private String reportFileURL;
	private String codeImageUrl;
	private String codeValue;

	public ReportData() {
		super();
	}
	
	public ReportData(String statusCode, String message, PbcCreditReportFeed report, String reportFileURL) {
		this.statusCode = statusCode;
		this.message = message;
		this.report = report;
		this.reportFileURL = reportFileURL;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLoggedCookiesJson() {
		return loggedCookiesJson;
	}
	public void setLoggedCookiesJson(String loggedCookiesJson) {
		this.loggedCookiesJson = loggedCookiesJson;
	}
	public PbcCreditReportFeed getReport() {
		return report;
	}
	public void setReport(PbcCreditReportFeed report) {
		this.report = report;
	}
	public String getReportFileURL() {
		return reportFileURL;
	}
	public void setReportFileURL(String reportFileURL) {
		this.reportFileURL = reportFileURL;
	}
	public String getCodeImageUrl() {
		return codeImageUrl;
	}
	public void setCodeImageUrl(String codeImageUrl) {
		this.codeImageUrl = codeImageUrl;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	@Override
	public String toString() {
		return "ReportData [statusCode=" + statusCode + ", message=" + message
				+ ", loggedCookiesJson=" + loggedCookiesJson + ", report="
				+ report + ", reportFileURL=" + reportFileURL
				+ ", codeImageUrl=" + codeImageUrl + ", codeValue=" + codeValue
				+ "]";
	}
}

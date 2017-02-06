package com.module.dao.entity.pbccrc;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_report_base")
@NamedQuery(name="PbcReportBase.findAll", query="SELECT e FROM PbcReportBase e")
public class PbcReportBase implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="report_id",length = 50)
	private String reportId;

	@Column(name="query_time",length = 25)
	private String queryTime;

	@Column(name="report_time",length = 25)
	private String reportTime;

	@Column(name="real_name",length = 50)
	private String realname;

	@Column(name="certificate_type",length = 50)
	private String certificateType;

	@Column(name="certificate_num",length = 50)
	private String certificateNum;

	@Column(name="marriage_status",length = 10)
	private String marriageStatus; //0:未婚   1:已婚  -1:未知


	@ManyToOne
	@JoinColumn(name="json_id")
	private PlainPbccrcJson plainPbccrcJson;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public PlainPbccrcJson getPlainPbccrcJson() {
		return plainPbccrcJson;
	}

	public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
		this.plainPbccrcJson = plainPbccrcJson;
	}
}

package com.crawler.creditchina.domain.jsontwo;

import java.io.Serializable;
import java.util.List;

public class CompanyRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1088503190788903251L;

	private String name;// 企业或个人名称
	private String objectType;// 主体类型 (法人或自然人)===2代表法人1代表自然人
	private Integer badCount;// 不良记录数
	private Integer dishonestyCount;// 失信记录数
	private List<BadRecord> badRecords;
	private List<DishonestyRecord> dishonestyRecords;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Integer getBadCount() {
		return badCount;
	}

	public void setBadCount(Integer badCount) {
		this.badCount = badCount;
	}

	public Integer getDishonestyCount() {
		return dishonestyCount;
	}

	public void setDishonestyCount(Integer dishonestyCount) {
		this.dishonestyCount = dishonestyCount;
	}

	public List<BadRecord> getBadRecords() {
		return badRecords;
	}

	public void setBadRecords(List<BadRecord> badRecords) {
		this.badRecords = badRecords;
	}

	public List<DishonestyRecord> getDishonestyRecords() {
		return dishonestyRecords;
	}

	public void setDishonestyRecords(List<DishonestyRecord> dishonestyRecords) {
		this.dishonestyRecords = dishonestyRecords;
	}

	@Override
	public String toString() {
		return "CompanyRecord [name=" + name + ", objectType=" + objectType
				+ ", badCount=" + badCount + ", dishonestyCount="
				+ dishonestyCount + ", badRecords=" + badRecords
				+ ", dishonestyRecords=" + dishonestyRecords + "]";
	}

}

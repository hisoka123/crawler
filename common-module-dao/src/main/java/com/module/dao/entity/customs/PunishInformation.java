package com.module.dao.entity.customs;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "h_punish_information")
@NamedQuery(name = "PunishInformation.findAll", query = "SELECT cu FROM PunishInformation cu")
public class PunishInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5616267759924125366L;

	public PunishInformation() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// 主键
	private String party;// 当事人
	private String caseQuality;// 案件性质
	private String punishDate;// 处罚日期
	private String punishNo;// 行政处罚决定书编号
	@ManyToOne
	@JoinColumn(name = "reginformation_id")
	private RegInformation regInformation;// 外键

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getCaseQuality() {
		return caseQuality;
	}

	public void setCaseQuality(String caseQuality) {
		this.caseQuality = caseQuality;
	}

	public String getPunishDate() {
		return punishDate;
	}

	public void setPunishDate(String punishDate) {
		this.punishDate = punishDate;
	}

	public String getPunishNo() {
		return punishNo;
	}

	public void setPunishNo(String punishNo) {
		this.punishNo = punishNo;
	}

	public RegInformation getRegInformation() {
		return regInformation;
	}

	public void setRegInformation(RegInformation regInformation) {
		this.regInformation = regInformation;
	}

	@Override
	public String toString() {
		return "PunishInformation [id=" + id + ", party=" + party
				+ ", caseQuality=" + caseQuality + ", punishDate=" + punishDate
				+ ", punishNo=" + punishNo + ", regInformation="
				+ regInformation + "]";
	}

}

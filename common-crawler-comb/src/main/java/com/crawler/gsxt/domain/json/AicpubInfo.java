package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息
 */
public class AicpubInfo implements Serializable { //GsgsInfo
	private static final long serialVersionUID = 2837475960959256834L;

	//登记信息
	private AicpubRegInfo regInfo; //gsgsDjInfo;
	
	//备案信息
	private AicpubArchiveInfo archiveInfo; //gsgsBaInfo;
	
	//动产抵押登记信息
	private AicpubChatMortgInfo chatMortgInfo; //gsgsDcdydjInfo;
	
	//股权出资登记信息
	private AicpubEqumortgregInfo equMortgRegInfo; //gsgsGqczdjInfo;
	
	//行政处罚信息
	private AicpubAdmpunishInfo admPunishInfo; //gsgsXzcfInfo;
	
	//经营异常信息
	private  AicpubOperanomaInfo operAnomaInfo; //gsgsJyycInfo;
	
	//严重违法信息
	private AicpubSerillegalInfo serIllegalInfo; //gsgsYzwfInfo;
	
	//抽查检查信息
	private AicpubCheckInfo checkInfo; //gsgsCcjcInfo;

	public AicpubRegInfo getRegInfo() {
		return regInfo;
	}

	public void setRegInfo(AicpubRegInfo regInfo) {
		this.regInfo = regInfo;
	}

	public AicpubArchiveInfo getArchiveInfo() {
		return archiveInfo;
	}

	public void setArchiveInfo(AicpubArchiveInfo archiveInfo) {
		this.archiveInfo = archiveInfo;
	}

	public AicpubChatMortgInfo getChatMortgInfo() {
		return chatMortgInfo;
	}

	public void setChatMortgInfo(AicpubChatMortgInfo chatMortgInfo) {
		this.chatMortgInfo = chatMortgInfo;
	}

	public AicpubEqumortgregInfo getEquMortgRegInfo() {
		return equMortgRegInfo;
	}

	public void setEquMortgRegInfo(AicpubEqumortgregInfo equMortgRegInfo) {
		this.equMortgRegInfo = equMortgRegInfo;
	}

	public AicpubAdmpunishInfo getAdmPunishInfo() {
		return admPunishInfo;
	}

	public void setAdmPunishInfo(AicpubAdmpunishInfo admPunishInfo) {
		this.admPunishInfo = admPunishInfo;
	}

	public AicpubOperanomaInfo getOperAnomaInfo() {
		return operAnomaInfo;
	}

	public void setOperAnomaInfo(AicpubOperanomaInfo operAnomaInfo) {
		this.operAnomaInfo = operAnomaInfo;
	}

	public AicpubSerillegalInfo getSerIllegalInfo() {
		return serIllegalInfo;
	}

	public void setSerIllegalInfo(AicpubSerillegalInfo serIllegalInfo) {
		this.serIllegalInfo = serIllegalInfo;
	}

	public AicpubCheckInfo getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(AicpubCheckInfo checkInfo) {
		this.checkInfo = checkInfo;
	}

	@Override
	public String toString() {
		return "AicpubInfo [regInfo=" + regInfo + ", archiveInfo="
				+ archiveInfo + ", chatMortgInfo=" + chatMortgInfo
				+ ", equMortgRegInfo=" + equMortgRegInfo + ", admPunishInfo="
				+ admPunishInfo + ", operAnomaInfo=" + operAnomaInfo
				+ ", serIllegalInfo=" + serIllegalInfo + ", checkInfo="
				+ checkInfo + "]";
	}
}

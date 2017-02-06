package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月29日
 * 其他部门公示信息
 */
public class OthrdeptpubInfo implements Serializable { //QtbmgsInfo
	private static final long serialVersionUID = 7643637002831370136L;
	
	//行政许可信息
	private OthrdeptpubAdmlicInfo admLicInfo; //qtbmgsXzxkInfo;
	//行政处罚信息
	private OthrdeptpubAdmpunishInfo admPunishInfo; //qtbmgsXzcfInfo;
	public OthrdeptpubAdmlicInfo getAdmLicInfo() {
		return admLicInfo;
	}
	public void setAdmLicInfo(OthrdeptpubAdmlicInfo admLicInfo) {
		this.admLicInfo = admLicInfo;
	}
	public OthrdeptpubAdmpunishInfo getAdmPunishInfo() {
		return admPunishInfo;
	}
	public void setAdmPunishInfo(OthrdeptpubAdmpunishInfo admPunishInfo) {
		this.admPunishInfo = admPunishInfo;
	}
	@Override
	public String toString() {
		return "OthrdeptpubInfo [admLicInfo=" + admLicInfo + ", admPunishInfo="
				+ admPunishInfo + "]";
	}
}

package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年4月20日
 * 工商公示信息->行政处罚信息
 */
public class AicpubAdmpunishInfo implements Serializable { //GsgsXzcfInfo
	private static final long serialVersionUID = 973381064544850906L;
	
	//行政处罚信息列表
	private List<AicpubAAdmpunishInfo> admPunishInfos; //gsgsXzcfXzcfInfos;
	//html内容
	private String html;
	public List<AicpubAAdmpunishInfo> getAdmPunishInfos() {
		return admPunishInfos;
	}
	public void setAdmPunishInfos(List<AicpubAAdmpunishInfo> admPunishInfos) {
		this.admPunishInfos = admPunishInfos;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "AicpubAdmpunishInfo [admPunishInfos=" + admPunishInfos
				+ ", html=" + html + "]";
	}
}

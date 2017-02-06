/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月29日
 * 其他部门公示信息->行政处罚信息
 */
public class OthrdeptpubAdmpunishInfo implements Serializable { //QtbmgsXzcfInfo
	private static final long serialVersionUID = 8127720319921597604L;
	
	//行政处罚信息列表
	private List<OthrdeptpubAAdmpunishInfo> admPunishInfos; //qtbmgsXzcfXzcfInfos;
	//html内容
	private String html;
	public List<OthrdeptpubAAdmpunishInfo> getAdmPunishInfos() {
		return admPunishInfos;
	}
	public void setAdmPunishInfos(List<OthrdeptpubAAdmpunishInfo> admPunishInfos) {
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
		return "OthrdeptpubAdmpunishInfo [admPunishInfos=" + admPunishInfos
				+ ", html=" + html + "]";
	}
}

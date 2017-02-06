/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * 专利
 * @author kingly
 *
 */
public class Patent implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title; //专利名称
	private String patentMark; //专利标识：专利局所在地（office）+专利/申请号（num）
	private String status; //专利状态   I:已颁发   P:审核中
	private List<UserFeedJson> inventors; //发明者
	private String awardDate; //颁发日期
	private String appliDate; //申请日期
	private String patentURL; //专利网址
	private String desc; //专利介绍
	private String desc_html;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPatentMark() {
		return patentMark;
	}
	public void setPatentMark(String patentMark) {
		this.patentMark = patentMark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<UserFeedJson> getInventors() {
		return inventors;
	}
	public void setInventors(List<UserFeedJson> inventors) {
		this.inventors = inventors;
	}
	public String getAwardDate() {
		return awardDate;
	}
	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}
	public String getAppliDate() {
		return appliDate;
	}
	public void setAppliDate(String appliDate) {
		this.appliDate = appliDate;
	}
	public String getPatentURL() {
		return patentURL;
	}
	public void setPatentURL(String patentURL) {
		this.patentURL = patentURL;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc_html() {
		return desc_html;
	}
	public void setDesc_html(String desc_html) {
		this.desc_html = desc_html;
	}
	
	@Override
	public String toString() {
		return "Patent [title=" + title + ", patentMark=" + patentMark
				+ ", status=" + status + ", inventors=" + inventors
				+ ", awardDate=" + awardDate + ", appliDate=" + appliDate
				+ ", patentURL=" + patentURL + ", desc=" + desc
				+ ", desc_html=" + desc_html + "]";
	}
	
}

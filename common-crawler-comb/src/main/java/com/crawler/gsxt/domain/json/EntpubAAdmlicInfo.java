/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示信息->行政许可信息->行政许可信息
 */
public class EntpubAAdmlicInfo implements Serializable { //QygsXzxkXzxkInfo
	private static final long serialVersionUID = 5325579446997336138L;
	
	//许可文件编号
	private String licenceNum; //xkwjNum;
	//许可文件名称
	private String licenceName; //xkwjName;
	//有效期自
	private String startDateTime; //startDate;
	//有效期至
	private String endDateTime; //endDate;
	//许可机关
	private String deciAuthority; //xkAuthority;
	//许可内容
	private String content; //xkContent;
	//状态
	private String status;
	//公示日期
	private String pubDate;
	//详情
	private String detail;
	
	private List<Detail> admlicDetail;
	
	public class Detail {
		//变更事项
		public String changeItem;
		
		//变更时间
		public String changeDateTime;
		
		//变更前内容
		public String changePreContent;
		
		//变更后内容
		public String changePostContent;

		public String getChangeItem() {
			return changeItem;
		}

		public void setChangeItem(String changeItem) {
			this.changeItem = changeItem;
		}

		public String getChangeDateTime() {
			return changeDateTime;
		}

		public void setChangeDateTime(String changeDateTime) {
			this.changeDateTime = changeDateTime;
		}

		public String getChangePreContent() {
			return changePreContent;
		}

		public void setChangePreContent(String changePreContent) {
			this.changePreContent = changePreContent;
		}

		public String getChangePostContent() {
			return changePostContent;
		}

		public void setChangePostContent(String changePostContent) {
			this.changePostContent = changePostContent;
		}
		
		
		
		
	}
	//html内容
	private String html;
	public String getLicenceNum() {
		return licenceNum;
	}
	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
	}
	public String getLicenceName() {
		return licenceName;
	}
	public void setLicenceName(String licenceName) {
		this.licenceName = licenceName;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getDeciAuthority() {
		return deciAuthority;
	}
	public void setDeciAuthority(String deciAuthority) {
		this.deciAuthority = deciAuthority;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	public List<Detail> getAdmlicDetail() {
		return admlicDetail;
	}
	public void setAdmlicDetail(List<Detail> admlicDetail) {
		this.admlicDetail = admlicDetail;
	}
	@Override
	public String toString() {
		return "EntpubAAdmlicInfo [licenceNum=" + licenceNum + ", licenceName="
				+ licenceName + ", startDateTime=" + startDateTime
				+ ", endDateTime=" + endDateTime + ", deciAuthority="
				+ deciAuthority + ", content=" + content + ", status=" + status
				+ ", detail=" + detail + ", admlicDetail=" + admlicDetail + ", html=" + html + "]";
	}
	
}

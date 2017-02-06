/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->经营异常信息->经营异常信息
 */
public class AicpubOOperanomaInfo implements Serializable { //GsgsJyycJyycInfo
	private static final long serialVersionUID = 6439501263004050622L;
	
	//列入经营异常名录原因
	private String includeCause; //lrjyycmlCause;
	
	//原因详情
	private String includeCauseDetail; //新加
	
	//原因编号
	private String serialNumber;//文书编号
	//列入日期
	private String includeDateTime; //lrDate;
	//作出决定机关(列入)
	private String includeAuthority; //lrzcjdAuthority;
	//移出经营异常名录原因
	private String removeCause; //ycjyycmlCause;
	//移出日期
	private String removeDateTime; //ycDate;
	//作出决定机关(移出)
	private String removeAuthority; //yczcjdAuthority;
	//作出决定机关(列入和移出 做出决定机关)
	private String authority; //zcjdAuthority;
	//html内容
	private String html;
	public String getIncludeCause() {
		return includeCause;
	}
	public void setIncludeCause(String includeCause) {
		this.includeCause = includeCause;
	}
	
	public String getIncludeCauseDetail() {
		return includeCauseDetail;
	}
	public void setIncludeCauseDetail(String includeCauseDetail) {
		this.includeCauseDetail = includeCauseDetail;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getIncludeDateTime() {
		return includeDateTime;
	}
	public void setIncludeDateTime(String includeDateTime) {
		this.includeDateTime = includeDateTime;
	}
	public String getIncludeAuthority() {
		return includeAuthority;
	}
	public void setIncludeAuthority(String includeAuthority) {
		this.includeAuthority = includeAuthority;
	}
	public String getRemoveCause() {
		return removeCause;
	}
	public void setRemoveCause(String removeCause) {
		this.removeCause = removeCause;
	}
	public String getRemoveDateTime() {
		return removeDateTime;
	}
	public void setRemoveDateTime(String removeDateTime) {
		this.removeDateTime = removeDateTime;
	}
	public String getRemoveAuthority() {
		return removeAuthority;
	}
	public void setRemoveAuthority(String removeAuthority) {
		this.removeAuthority = removeAuthority;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "AicpubOOperanomaInfo [includeCause=" + includeCause
				+ ", includeCauseDetail=" + includeCauseDetail
				+ ", serialNumber=" + serialNumber
				+ ", includeDateTime=" + includeDateTime
				+ ", includeAuthority=" + includeAuthority + ", removeCause="
				+ removeCause + ", removeDateTime=" + removeDateTime
				+ ", removeAuthority=" + removeAuthority + ", authority="
				+ authority + ", html=" + html + "]";
	}
	
}

package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年4月20日
 * 工商公示信息->动产抵押登记信息->动产抵押登记信息
 */
public class AicpubCChatMortgInfo implements Serializable { //GsgsDcdydjDcdydjInfo
	private static final long serialVersionUID = -3833696549665740379L;
	
	//登记编号
	private String regNum;
	//登记日期
	private String regDateTime; //regDate;
	//登记机关
	private String regAuthority;
	//被担保债权数额
	private String guaranteedDebtAmount; //bdbzqAmount;
	//状态
	private String status;
	//公示时间
	private String pubDateTime; //pubDate;
	//详情
	private String detail;
	//详情
	private AicpubCChatMortgDetail mortgDetail;
	
	//html内容
	private String html;
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(String regDateTime) {
		this.regDateTime = regDateTime;
	}
	public String getRegAuthority() {
		return regAuthority;
	}
	public void setRegAuthority(String regAuthority) {
		this.regAuthority = regAuthority;
	}
	public String getGuaranteedDebtAmount() {
		return guaranteedDebtAmount;
	}
	public void setGuaranteedDebtAmount(String guaranteedDebtAmount) {
		this.guaranteedDebtAmount = guaranteedDebtAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPubDateTime() {
		return pubDateTime;
	}
	public void setPubDateTime(String pubDateTime) {
		this.pubDateTime = pubDateTime;
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
	public AicpubCChatMortgDetail getMortgDetail() {
		return mortgDetail;
	}
	public void setMortgDetail(AicpubCChatMortgDetail mortgDetail) {
		this.mortgDetail = mortgDetail;
	}
	@Override
	public String toString() {
		return "AicpubCChatMortgInfo [regNum=" + regNum + ", regDateTime="
				+ regDateTime + ", regAuthority=" + regAuthority
				+ ", guaranteedDebtAmount=" + guaranteedDebtAmount
				+ ", status=" + status + ", pubDateTime=" + pubDateTime
				+ ", detail=" + detail + ", html=" + html + "]";
	}

}

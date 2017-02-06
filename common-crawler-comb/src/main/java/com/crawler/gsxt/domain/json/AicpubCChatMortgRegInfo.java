package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author fzy
 * @date 2016年5月24日
 * 工商公示->动产抵押登记信息->详情->动产抵押登记信息
 */
public class AicpubCChatMortgRegInfo implements Serializable{
	private static final long serialVersionUID = 4961747584857335572L;

	//登记编号
	private String regNum;
	//登记日期
	private String regDate;
	//登记机关
	private String regAuthority;
	//被担保债权种类
	private String guaranteedDebtType;
	//被担保债权数额
	private String guaranteedDebtAmount;
	//债务人履行债务的期限
	private String term;
	//担保范围
	private String guaranteedScope;
	//备注
	private String note;
	//html
	private String html;
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRegAuthority() {
		return regAuthority;
	}
	public void setRegAuthority(String regAuthority) {
		this.regAuthority = regAuthority;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getGuaranteedDebtType() {
		return guaranteedDebtType;
	}
	public void setGuaranteedDebtType(String guaranteedDebtType) {
		this.guaranteedDebtType = guaranteedDebtType;
	}
	public String getGuaranteedDebtAmount() {
		return guaranteedDebtAmount;
	}
	public void setGuaranteedDebtAmount(String guaranteedDebtAmount) {
		this.guaranteedDebtAmount = guaranteedDebtAmount;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getGuaranteedScope() {
		return guaranteedScope;
	}
	public void setGuaranteedScope(String guaranteedScope) {
		this.guaranteedScope = guaranteedScope;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "AicpubCChatMortgRegInfo [regNum=" + regNum + ", regDate="
				+ regDate + ", regAuthority=" + regAuthority
				+ ", guaranteedDebtType=" + guaranteedDebtType
				+ ", guaranteedDebtAmount=" + guaranteedDebtAmount + ", term="
				+ term + ", guaranteedScope=" + guaranteedScope + ", note="
				+ note + ", html=" + html + "]";
	}
}

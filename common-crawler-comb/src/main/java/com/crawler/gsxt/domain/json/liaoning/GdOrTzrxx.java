package com.crawler.gsxt.domain.json.liaoning;

/**
 * 工商公示信息->登记信息->股东信息（投资人信息）
 */
public class GdOrTzrxx {

	private String invtypeName;// 股东类型
	private String inv;// 股东
	private String blictypeName;// 证照/证件类型
	private String blicno;// 证照/证件号码
	private String sconformName;// 出资方式

	public String getSconformName() {
		return sconformName;
	}

	public void setSconformName(String sconformName) {
		this.sconformName = sconformName;
	}

	public String getInvtypeName() {
		return invtypeName;
	}

	public void setInvtypeName(String invtypeName) {
		this.invtypeName = invtypeName;
	}

	public String getInv() {
		return inv;
	}

	public void setInv(String inv) {
		this.inv = inv;
	}

	public String getBlictypeName() {
		return blictypeName;
	}

	public void setBlictypeName(String blictypeName) {
		this.blictypeName = blictypeName;
	}

	public String getBlicno() {
		return blicno;
	}

	public void setBlicno(String blicno) {
		this.blicno = blicno;
	}

	@Override
	public String toString() {
		return "GdOrTzrxx [invtypeName=" + invtypeName + ", inv=" + inv
				+ ", blictypeName=" + blictypeName + ", blicno=" + blicno
				+ ", sconformName=" + sconformName + "]";
	}

}

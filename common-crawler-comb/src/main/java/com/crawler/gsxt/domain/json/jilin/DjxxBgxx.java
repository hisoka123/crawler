package com.crawler.gsxt.domain.json.jilin;

/**
 * 工商公示信息->登记信息->变更信息
 */
public class DjxxBgxx {

	private String altitem;// 变更事项
	private String altbe;// 变更前内容
	private String altaf;// 变更后内容
	private JilinDateTime altdate;// 变更日期

	public String getAltitem() {
		return altitem;
	}

	public void setAltitem(String altitem) {
		this.altitem = altitem;
	}

	public String getAltbe() {
		return altbe;
	}

	public void setAltbe(String altbe) {
		this.altbe = altbe;
	}

	public String getAltaf() {
		return altaf;
	}

	public void setAltaf(String altaf) {
		this.altaf = altaf;
	}

	public JilinDateTime getAltdate() {
		return altdate;
	}

	public void setAltdate(JilinDateTime altdate) {
		this.altdate = altdate;
	}

	@Override
	public String toString() {
		return "DjxxBgxx [altitem=" + altitem + ", altbe=" + altbe + ", altaf="
				+ altaf + ", altdate=" + altdate + "]";
	}

}

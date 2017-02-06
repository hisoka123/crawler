package com.crawler.gsxt.domain.json.jilin;

/**
 * 企业公示信息->股东及出资信息->变更信息
 */
public class GdjczxxBgxx {

	private String altitem;// 变更事项
	private JilinDateTime altdate;// 变更时间
	private String altbe;// 变更前
	private String altaf;// 变更后

	public String getAltitem() {
		return altitem;
	}

	public void setAltitem(String altitem) {
		this.altitem = altitem;
	}

	public JilinDateTime getAltdate() {
		return altdate;
	}

	public void setAltdate(JilinDateTime altdate) {
		this.altdate = altdate;
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

	@Override
	public String toString() {
		return "GdjczxxBgxx [altitem=" + altitem + ", altdate=" + altdate
				+ ", altbe=" + altbe + ", altaf=" + altaf + "]";
	}

}

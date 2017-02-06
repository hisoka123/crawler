package com.crawler.gsxt.domain.json.jilin;

/**
 * 工商公示信息->抽查检查信息->抽查检查信息
 */
public class CcjcxxCcjcxx {

	private String insauth;// 检查实施机关
	private String instype;// 类型
	private JilinDateTime insdate;// 日期
	private String insres;// 结果

	public String getInsauth() {
		return insauth;
	}

	public void setInsauth(String insauth) {
		this.insauth = insauth;
	}

	public String getInstype() {
		return instype;
	}

	public void setInstype(String instype) {
		this.instype = instype;
	}

	public JilinDateTime getInsdate() {
		return insdate;
	}

	public void setInsdate(JilinDateTime insdate) {
		this.insdate = insdate;
	}

	public String getInsres() {
		return insres;
	}

	public void setInsres(String insres) {
		this.insres = insres;
	}

	@Override
	public String toString() {
		return "CcjcxxCcjcxx [insauth=" + insauth + ", instype=" + instype
				+ ", insdate=" + insdate + ", insres=" + insres + "]";
	}

}

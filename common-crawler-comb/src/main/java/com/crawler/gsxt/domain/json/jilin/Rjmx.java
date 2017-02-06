package com.crawler.gsxt.domain.json.jilin;

/**
 * 企业公示信息->股东及出资信息->股东及出资信息
 */
public class Rjmx {

	// case '1' : return '货币';case '2': return '实物';case '3' return '知识产权';case
	// '9':return '其他';
	private String conform;// 认缴出资方式
	private String subconam;// 认缴出资额（万元）
	private JilinDateTime condate;// 认缴出资日期

	public String getConform() {
		return conform;
	}

	public void setConform(String conform) {
		this.conform = conform;
	}

	public String getSubconam() {
		return subconam;
	}

	public void setSubconam(String subconam) {
		this.subconam = subconam;
	}

	public JilinDateTime getCondate() {
		return condate;
	}

	public void setCondate(JilinDateTime condate) {
		this.condate = condate;
	}

	@Override
	public String toString() {
		return "Rjmx [conform=" + conform + ", subconam=" + subconam
				+ ", condate=" + condate + "]";
	}

}

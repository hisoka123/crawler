package com.crawler.gsxt.domain.json.jilin;

/**
 * 工商公示信息->经营异常信息->经营异常信息
 */
public class JyycxxJyycxx {

	private String specause;// 列入经营异常名录原因
	private JilinDateTime abntime;// 列入日期
	private String remexcpres;// 移出经营异常名录原因
	private JilinDateTime remdate;// 移出日期
	private String decorg;// 作出决定机关

	public String getSpecause() {
		return specause;
	}

	public void setSpecause(String specause) {
		this.specause = specause;
	}

	public JilinDateTime getAbntime() {
		return abntime;
	}

	public void setAbntime(JilinDateTime abntime) {
		this.abntime = abntime;
	}

	public String getRemexcpres() {
		return remexcpres;
	}

	public void setRemexcpres(String remexcpres) {
		this.remexcpres = remexcpres;
	}

	public JilinDateTime getRemdate() {
		return remdate;
	}

	public void setRemdate(JilinDateTime remdate) {
		this.remdate = remdate;
	}

	public String getDecorg() {
		return decorg;
	}

	public void setDecorg(String decorg) {
		this.decorg = decorg;
	}

	@Override
	public String toString() {
		return "JyycxxJyycxx [specause=" + specause + ", abntime=" + abntime
				+ ", remexcpres=" + remexcpres + ", remdate=" + remdate
				+ ", decorg=" + decorg + "]";
	}

}

package com.crawler.gsxt.domain.json.jilin;

/**
 * 企业公示信息->行政许可信息->行政许可信息
 */
public class Xzxkxx {

	private String licno;// 许可文件编号
	private String licname;// 许可文件名称
	private JilinDateTime valfrom;// 有效期自
	private JilinDateTime valto;// 有效期至
	private String licanth;// 许可机关
	private String licitem;// 许可内容
	private String type;// 状态（1、有效）
	private String pid;

	public String getLicno() {
		return licno;
	}

	public void setLicno(String licno) {
		this.licno = licno;
	}

	public String getLicname() {
		return licname;
	}

	public void setLicname(String licname) {
		this.licname = licname;
	}

	public JilinDateTime getValfrom() {
		return valfrom;
	}

	public void setValfrom(JilinDateTime valfrom) {
		this.valfrom = valfrom;
	}

	public JilinDateTime getValto() {
		return valto;
	}

	public void setValto(JilinDateTime valto) {
		this.valto = valto;
	}

	public String getLicanth() {
		return licanth;
	}

	public void setLicanth(String licanth) {
		this.licanth = licanth;
	}

	public String getLicitem() {
		return licitem;
	}

	public void setLicitem(String licitem) {
		this.licitem = licitem;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Xzxkxx [licno=" + licno + ", licname=" + licname + ", valfrom="
				+ valfrom + ", valto=" + valto + ", licanth=" + licanth
				+ ", licitem=" + licitem + ", type=" + type + ", pid=" + pid
				+ "]";
	}

}

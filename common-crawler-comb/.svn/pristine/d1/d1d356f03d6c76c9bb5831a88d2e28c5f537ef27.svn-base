/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * 联系方式
 * @author kingly
 *
 */
public class ContactInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public class Twitter implements Serializable {
		private static final long serialVersionUID = 1L;
		public String account;
		public String profile;
	}
	
	public class WeChat implements Serializable {
		private static final long serialVersionUID = 1L;
		public String account;
		public String qr_code;
	}
	
	public class Skype implements Serializable {
		private static final long serialVersionUID = 1L;
		public String account;
		public String profile;
	}
	
	public class PerSite implements Serializable {
		private static final long serialVersionUID = 1L;
		public String name;
		public String link;
	}
	
	private List<String> emails;
	private String tel; //联系电话
	private String mobile; //手机
	private List<Twitter> twitters;
	private String qq;
	private WeChat wechat;
	private Skype skype;
	private List<PerSite> per_sites; //个人网站
	private String addr; //地址
	private String desc; //其他描述
	
	public List<String> getEmails() {
		return emails;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<Twitter> getTwitters() {
		return twitters;
	}
	public void setTwitters(List<Twitter> twitters) {
		this.twitters = twitters;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public WeChat getWechat() {
		return wechat;
	}
	public void setWechat(WeChat wechat) {
		this.wechat = wechat;
	}
	public Skype getSkype() {
		return skype;
	}
	public void setSkype(Skype skype) {
		this.skype = skype;
	}
	public List<PerSite> getPer_sites() {
		return per_sites;
	}
	public void setPer_sites(List<PerSite> per_sites) {
		this.per_sites = per_sites;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "ContactInfo [emails=" + emails + ", tel=" + tel + ", mobile="
				+ mobile + ", twitters=" + twitters + ", qq=" + qq
				+ ", wechat=" + wechat + ", skype=" + skype + ", per_sites="
				+ per_sites + ", addr=" + addr + ", desc=" + desc + "]";
	}

}

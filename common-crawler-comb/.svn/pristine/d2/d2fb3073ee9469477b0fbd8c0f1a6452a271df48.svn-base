package com.crawler.weixin.domain.json;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author kingly
 *
 */
public class UserFeedJson implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String openid; // 普通微信公众号的唯一标识
	private String profile_image;
	private String weixin_account;
	private String authen_info; // 认证信息
	private String func_info;
	private WeixinArticle article;
	private String[] qr_codes; // 二维码,优先取第一个，至少有一个有效

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getWeixin_account() {
		return weixin_account;
	}
	public void setWeixin_account(String weixin_account) {
		this.weixin_account = weixin_account;
	}
	public String getAuthen_info() {
		return authen_info;
	}
	public void setAuthen_info(String authen_info) {
		this.authen_info = authen_info;
	}
	public String getFunc_info() {
		return func_info;
	}
	public void setFunc_info(String func_info) {
		this.func_info = func_info;
	}
	public WeixinArticle getArticle() {
		return article;
	}
	public void setArticle(WeixinArticle article) {
		this.article = article;
	}
	public String[] getQr_codes() {
		return qr_codes;
	}
	public void setQr_codes(String[] qr_codes) {
		this.qr_codes = qr_codes;
	}

	@Override
	public String toString() {
		return "UserFeedJson [name=" + name + ", openid=" + openid
				+ ", profile_image=" + profile_image + ", weixin_account="
				+ weixin_account + ", func_info=" + func_info + ", article="
				+ article + ", qr_codes=" + Arrays.toString(qr_codes)
				+ ", authen_info=" + authen_info + "]";
	}

}

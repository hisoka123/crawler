package com.crawler.weibo.domain.json;

import java.io.Serializable;
import java.util.Arrays;

public class TweetJson implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2402816370816673048L;

	private String tid;
	
	private String content_text;
	
	private String content_html;
	
	private String reposts_count;
	
	private String comments_count;
	
	private String attitudes_count;
	
	private String created_at;
	
	private String source;// 来源   eg: 来自 iPhone 6
	
	private String[] pic_urls;
	
	private String uid;
	
	private String nickname;
	
	private String url;
	
	private String region;
	

	private boolean isTop;//是否是置顶
	
	private boolean isHot;//是否是热门贴
	
	private TweetJson retweet;//回帖

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
 
	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public String getContent_html() {
		return content_html;
	}

	public void setContent_html(String content_html) {
		this.content_html = content_html;
	}

	public String getReposts_count() {
		return reposts_count;
	}

	public void setReposts_count(String reposts_count) {
		this.reposts_count = reposts_count;
	}

	public String getComments_count() {
		return comments_count;
	}

	public void setComments_count(String comments_count) {
		this.comments_count = comments_count;
	}

	public String getAttitudes_count() {
		return attitudes_count;
	}

	public void setAttitudes_count(String attitudes_count) {
		this.attitudes_count = attitudes_count;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
   
	public String[] getPic_urls() {
		return pic_urls;
	}

	public void setPic_urls(String[] pic_urls) {
		this.pic_urls = pic_urls;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}
	 
	public TweetJson getRetweet() {
		return retweet;
	}

	public void setRetweet(TweetJson retweet) {
		this.retweet = retweet;
	}
	 
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	 
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}

	 

	 
	
	
	
	
	

}

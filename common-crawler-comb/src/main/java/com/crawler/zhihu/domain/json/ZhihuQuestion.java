package com.crawler.zhihu.domain.json;

import java.io.Serializable;

public class ZhihuQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String title;
	
	private String content;
	
	private String link;
	
	private String answers_num;
	
	private String followers_num;
	
	private String visits_num;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswers_num() {
		return answers_num;
	}

	public void setAnswers_num(String answers_num) {
		this.answers_num = answers_num;
	}

	public String getFollowers_num() {
		return followers_num;
	}

	public void setFollowers_num(String followers_num) {
		this.followers_num = followers_num;
	}

	public String getVisits_num() {
		return visits_num;
	}

	public void setVisits_num(String visits_num) {
		this.visits_num = visits_num;
	}

	
}

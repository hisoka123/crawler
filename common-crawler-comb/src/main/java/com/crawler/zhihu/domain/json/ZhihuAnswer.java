package com.crawler.zhihu.domain.json;

import java.io.Serializable;

public class ZhihuAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String agree_num;
	
	private String comments_num;
	
	private String content;
	
	private String created_time;
	
	private ZhihuQuestion question;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgree_num() {
		return agree_num;
	}

	public void setAgree_num(String agree_num) {
		this.agree_num = agree_num;
	}
	
	public String getComments_num() {
		return comments_num;
	}

	public void setComments_num(String comments_num) {
		this.comments_num = comments_num;
	}

	public ZhihuQuestion getQuestion() {
		return question;
	}

	public void setQuestion(ZhihuQuestion question) {
		this.question = question;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

}

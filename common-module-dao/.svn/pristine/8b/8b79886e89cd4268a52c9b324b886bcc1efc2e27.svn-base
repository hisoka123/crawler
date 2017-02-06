package com.module.dao.entity.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Picture")
public class Picture extends IdEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3492252300567657884L;

	
	private Tweet tweet;
	
	private String url;

	@ManyToOne 
	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
}

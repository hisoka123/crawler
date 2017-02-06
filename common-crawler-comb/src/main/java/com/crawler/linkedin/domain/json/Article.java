/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String pub_date;
	private String link;
	private String pre_img;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPre_img() {
		return pre_img;
	}
	public void setPre_img(String pre_img) {
		this.pre_img = pre_img;
	}
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", author=" + author + ", pub_date="
				+ pub_date + ", link=" + link + ", pre_img=" + pre_img + "]";
	}
	
}

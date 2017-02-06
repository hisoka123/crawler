/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;

/**
 * 推荐信
 * @author kingly
 *
 */
public class Endorsement implements Serializable {
	private static final long serialVersionUID = 1L;
	private String status = "received"; //默认是收到的推荐信。发出的推荐信标识为"given"
	private String endors_date; //推荐时间
	private UserFeedJson endorser; //推荐人/担保人
	private String content;
	private String content_html;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndors_date() {
		return endors_date;
	}
	public void setEndors_date(String endors_date) {
		this.endors_date = endors_date;
	}
	public UserFeedJson getEndorser() {
		return endorser;
	}
	public void setEndorser(UserFeedJson endorser) {
		this.endorser = endorser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_html() {
		return content_html;
	}
	public void setContent_html(String content_html) {
		this.content_html = content_html;
	}
	
	@Override
	public String toString() {
		return "Endorsement [status=" + status + ", endors_date=" + endors_date
				+ ", endorser=" + endorser + ", content=" + content
				+ ", content_html=" + content_html + "]";
	}
	
}

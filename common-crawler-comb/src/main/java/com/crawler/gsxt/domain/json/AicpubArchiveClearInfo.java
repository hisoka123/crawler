/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->备案信息->清算信息
 */
public class AicpubArchiveClearInfo implements Serializable { //GsgsBaQsInfo
	private static final long serialVersionUID = 6182418159687284649L;

	//清算组负责人
	private String leader;
	
	//清算组成员
	private List<String> members;
	
	//html内容
	private String html;

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "AicpubArchiveClearInfo [leader=" + leader + ", members="
				+ members + ", html=" + html + "]";
	}
}

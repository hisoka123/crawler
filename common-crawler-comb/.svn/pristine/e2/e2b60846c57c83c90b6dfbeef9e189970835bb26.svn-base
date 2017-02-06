package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->登记信息->变更信息
 */
public class AicpubRegChangeInfo implements Serializable { //GsgsDjBgInfo
	private static final long serialVersionUID = -3311282090529443131L;

	//变更事项
	private String item; //bgItem;
	
	//变更前内容
	private String preContent; //bgqContent;
	
	//变更后内容
	private String postContent; //bghContent;
	
	//变更日期
	private String dateTime; //bgDate;
	
	//变更信息详细
	public class ChangeInfo { //BgInfo
		//姓名
		public String name;
		//投资人类型/职位
		public String typeOrPosition;
		//证照号
		public String idNum;
				
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTypeOrPosition() {
			return typeOrPosition;
		}
		public void setTypeOrPosition(String typeOrPosition) {
			this.typeOrPosition = typeOrPosition;
		}
		public String getIdNum() {
			return idNum;
		}
		public void setIdNum(String idNum) {
			this.idNum = idNum;
		}
		
		
	}
	public class ChangeDetail { //BgDetail
		//变更前
		public List<AicpubRegChangeInfo.ChangeInfo> preInfos; //bgqInfos;
		//变更后
		public List<AicpubRegChangeInfo.ChangeInfo> postInfos; //bghInfos;
		public List<AicpubRegChangeInfo.ChangeInfo> getPreInfos() {
			return preInfos;
		}
		public void setPreInfos(List<AicpubRegChangeInfo.ChangeInfo> preInfos) {
			this.preInfos = preInfos;
		}
		public List<AicpubRegChangeInfo.ChangeInfo> getPostInfos() {
			return postInfos;
		}
		public void setPostInfos(List<AicpubRegChangeInfo.ChangeInfo> postInfos) {
			this.postInfos = postInfos;
		}
		
		
		
	}
	private ChangeDetail detail;
	
	//html内容
	private String html;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPreContent() {
		return preContent;
	}

	public void setPreContent(String preContent) {
		this.preContent = preContent;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public ChangeDetail getDetail() {
		return detail;
	}

	public void setDetail(ChangeDetail detail) {
		this.detail = detail;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "AicpubRegChangeInfo [item=" + item + ", preContent="
				+ preContent + ", postContent=" + postContent + ", dateTime="
				+ dateTime + ", detail=" + detail + ", html=" + html + "]";
	}
	
}

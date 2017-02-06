package com.crawler.gsxt.domain.json.liaoning;

/**
 * 工商公示信息->备案信息->主要人员信息
 */
public class Zyryxx {

	private String name;// 姓名
	private String positionName;// 职务

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Override
	public String toString() {
		return "Zyryxx [name=" + name + ", positionName=" + positionName + "]";
	}

}

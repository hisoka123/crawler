package com.crawler.gsxt.domain.json.jilin;

/**
 * 工商公示信息->备案信息->主要人员信息
 */
public class BaxxZyryxx {

	private String name;// 姓名
	private String position;// 职务

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "BaxxZyryxx [name=" + name + ", position=" + position + "]";
	}

}

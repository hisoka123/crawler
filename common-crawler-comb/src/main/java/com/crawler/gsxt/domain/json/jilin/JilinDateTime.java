package com.crawler.gsxt.domain.json.jilin;

/**
 * 吉林省时间共用类
 */
public class JilinDateTime {

	private String year;
	private String month;
	private String date;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "JinlinDateTime [year=" + year + ", month=" + month + ", date="
				+ date + "]";
	}

}

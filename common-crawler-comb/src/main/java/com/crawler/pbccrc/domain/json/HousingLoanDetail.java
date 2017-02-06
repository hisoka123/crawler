/**
 * 
 */
package com.crawler.pbccrc.domain.json;

import java.io.Serializable;
import java.util.Map;

/**
 * @author kingly
 * @date 2016年1月18日
 * 购房明细
 */
public class HousingLoanDetail implements Serializable {
	private static final long serialVersionUID = 4035318846601398127L;
	private String type; //明细类型 1、发生过的逾期的帐户明细如下   2、从未逾期过的帐户明细如下
	private Map<String, String> details; //明细列表   key:序号   value:内容
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, String> getDetails() {
		return details;
	}
	public void setDetails(Map<String, String> details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "HousingLoanDetail [type=" + type + ", details=" + details + "]";
	}
}

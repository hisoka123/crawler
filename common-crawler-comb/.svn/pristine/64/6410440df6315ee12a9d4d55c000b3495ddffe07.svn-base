/**
 * 
 */
package com.crawler.pbccrc.domain.json;

import java.io.Serializable;
import java.util.Map;

/**
 * @author kingly
 * @date 2016年1月18日
 * 其他贷款明细
 */
public class OtherLoanDetail implements Serializable {
	private static final long serialVersionUID = 9211392134359170490L;
	private String type; //明细类型 1、发生过逾期的账户明细如下   2、从未逾期过的账户明细如下
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
		return "OtherLoanDetail [type=" + type + ", details=" + details + "]";
	}
}

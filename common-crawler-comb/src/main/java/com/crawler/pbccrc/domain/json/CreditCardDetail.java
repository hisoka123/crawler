/**
 * 
 */
package com.crawler.pbccrc.domain.json;

import java.io.Serializable;
import java.util.Map;

/**
 * @author kingly
 * @date 2016年1月7日
 * 信用卡明细
 */
public class CreditCardDetail implements Serializable {
	private static final long serialVersionUID = -7897639215824748807L;
	private String type; //明细类型  1:发生过逾期的贷记卡账户明细如下  2:透支超过60天的准贷记卡帐户明细如下   3:从未逾期过的贷记卡及透支未超过60天的准贷记卡账户明细如下
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
		return "CreditCardDetail [type=" + type + ", details=" + details + "]";
	}
}

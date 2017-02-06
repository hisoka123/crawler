/**
 * 
 */
package com.crawler.pbccrc.domain.json;

import java.io.Serializable;
import java.util.Map;

/**
 * @author kingly
 * @date 2016年1月6日
 * 
 */
public class CreditSummary implements Serializable {
	private static final long serialVersionUID = -6315912621046190197L;
	private Map<String, String> creditCards;  //信用卡
	private Map<String, String> housingLoans; //住房贷款
	private Map<String, String> otherLoans;   //其他贷款
	
	public Map<String, String> getCreditCards() {
		return creditCards;
	}
	public void setCreditCards(Map<String, String> creditCards) {
		this.creditCards = creditCards;
	}
	public Map<String, String> getHousingLoans() {
		return housingLoans;
	}
	public void setHousingLoans(Map<String, String> housingLoans) {
		this.housingLoans = housingLoans;
	}
	public Map<String, String> getOtherLoans() {
		return otherLoans;
	}
	public void setOtherLoans(Map<String, String> otherLoans) {
		this.otherLoans = otherLoans;
	}

	@Override
	public String toString() {
		return "CreditSummary [creditCards=" + creditCards + ", housingLoans="
				+ housingLoans + ", otherLoans=" + otherLoans + "]";
	}
}

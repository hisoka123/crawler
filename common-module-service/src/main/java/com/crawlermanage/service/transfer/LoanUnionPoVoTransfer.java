package com.crawlermanage.service.transfer;

import java.util.Date;

import org.apache.log4j.Logger;

import com.crawler.dailianmeng.domain.json.LoanUnionFeedJson;
import com.module.dao.entity.dailianmeng.LoanUnion;

/**
 * @author kingly
 * @date 2016年6月13日
 * 对象转化工具
 */
public class LoanUnionPoVoTransfer {
	private static final Logger LOGGER = Logger.getLogger(LoanUnionPoVoTransfer.class);
	
	public static LoanUnion voToPo(LoanUnionFeedJson loanUnionFeedJson) {
		LoanUnion loanUnion = new LoanUnion();;
		
		if (loanUnionFeedJson != null) {
			loanUnion.setName(loanUnionFeedJson.getName());
			loanUnion.setCaseNum(loanUnionFeedJson.getCaseNum());
			loanUnion.setAge(loanUnionFeedJson.getAge());
			loanUnion.setSex(loanUnionFeedJson.getSex());
			loanUnion.setCardID(loanUnionFeedJson.getCardID());
			loanUnion.setLegalPerson(loanUnionFeedJson.getLegalPerson());
			loanUnion.setExecuteCourt(loanUnionFeedJson.getExecuteCourt());
			loanUnion.setProvince(loanUnionFeedJson.getProvince());
			loanUnion.setExecuteNum(loanUnionFeedJson.getExecuteNum());
			loanUnion.setCaseDate(loanUnionFeedJson.getCaseDate());
			loanUnion.setDependCourt(loanUnionFeedJson.getDependCourt());
			loanUnion.setEffectNum(loanUnionFeedJson.getEffectNum());
			loanUnion.setExecuteSituation(loanUnionFeedJson.getExecuteSituation());
			loanUnion.setAlreadyExecute(loanUnionFeedJson.getAlreadyExecute());
			loanUnion.setNoExecute(loanUnionFeedJson.getNoExecute());
			loanUnion.setBehaviorSituation(loanUnionFeedJson.getBehaviorSituation());
			loanUnion.setPubDate(loanUnionFeedJson.getPubDate());
			loanUnion.setUpdateDate(loanUnionFeedJson.getUpdateDate());
			loanUnion.setDebtMoney(loanUnionFeedJson.getDebtMoney());
			loanUnion.setLoanDate(loanUnionFeedJson.getLoanDate());
			loanUnion.setLoanTerm(loanUnionFeedJson.getLoanTerm());
			loanUnion.setListType(loanUnionFeedJson.getListType());
			loanUnion.setLoanState(loanUnionFeedJson.getLoanState());
			loanUnion.setDescribe(loanUnionFeedJson.getDescribe());
			loanUnion.setBirthday(loanUnionFeedJson.getBirthday());
			loanUnion.setIssuePlace(loanUnionFeedJson.getIssuePlace());
		}
		
		return loanUnion;
	}
	
}
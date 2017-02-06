package com.crawlermanage.service.transfer;

import java.util.Date;

import org.apache.log4j.Logger;

import com.crawler.renfawang.domain.json.PeopleCourtFeedJson;
import com.module.dao.entity.dailianmeng.LoanUnion;
import com.module.dao.entity.renfawang.PeopleCourt;

/**
 * @author kingly
 * @date 2016年6月13日
 * 对象转化工具
 */
public class PeopleCourtPoVoTransfer {
	private static final Logger LOGGER = Logger.getLogger(PeopleCourtPoVoTransfer.class);
	
	public static PeopleCourt voToPo(PeopleCourtFeedJson peopleCourtFeedJson) {
		PeopleCourt peopleCourt = new PeopleCourt();;
		
		if (peopleCourtFeedJson != null) {
			peopleCourt.setPname(peopleCourtFeedJson.getPname());
			peopleCourt.setPartyCardNum(peopleCourtFeedJson.getPartyCardNum());
			peopleCourt.setExecCourtName(peopleCourtFeedJson.getExecCourtName());
			peopleCourt.setCaseCode(peopleCourtFeedJson.getCaseCode());
			peopleCourt.setCaseCreateTime(peopleCourtFeedJson.getCaseCreateTime());
			peopleCourt.setExecMoney(peopleCourtFeedJson.getExecMoney());
		}
		
		return peopleCourt;
	}
	
}
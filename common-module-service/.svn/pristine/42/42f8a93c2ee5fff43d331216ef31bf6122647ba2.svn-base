package com.crawlermanage.service.transfer;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawler.zjsfgkw.domain.json.ExecuteCaseSearchJson;
import com.module.dao.entity.zjsfgkw.Zjsfgkw;

public class ZjsfgkwPoVoTransfer {
	
	private static final Logger LOGGER = Logger.getLogger(ZjsfgkwPoVoTransfer.class);
	
	public static Zjsfgkw voToExecuteCaseSearchPo(ExecuteCaseSearchJson executeCaseSearchJson) throws JSONException {
		Zjsfgkw zjsfgkw=new Zjsfgkw();
		if (executeCaseSearchJson != null) {
			zjsfgkw.setCaseNo(executeCaseSearchJson.getCaseNo());
			zjsfgkw.setCourt(executeCaseSearchJson.getCourt());
			zjsfgkw.setCaseState(executeCaseSearchJson.getCaseState());
			zjsfgkw.setCaseDate(executeCaseSearchJson.getCaseDate());
			zjsfgkw.setName(executeCaseSearchJson.getPrincipal());
			zjsfgkw.setExecutetime(new Date());
		}
		return zjsfgkw;
		
		
	}
	
	public static Zjsfgkw voToCreditSearchPo(CreditJson creditJson) throws JSONException {
		Zjsfgkw zjsfgkw=new Zjsfgkw();
		if (creditJson != null) {
			zjsfgkw.setName(creditJson.getName());
			zjsfgkw.setIdNo(creditJson.getIdNo());
			zjsfgkw.setAddress(creditJson.getAddress());
			zjsfgkw.setEnforceBasis(creditJson.getEnforceBasis());
			zjsfgkw.setCaseNo(creditJson.getCaseNo());
			zjsfgkw.setExecutReason(creditJson.getExecutReason());
			zjsfgkw.setCourt(creditJson.getCourt());
			zjsfgkw.setAmountNotExecuted(creditJson.getAmountNotExecuted());
			zjsfgkw.setCaseDate(creditJson.getCaseDate());
			zjsfgkw.setTargetAmount(creditJson.getTargetAmount());
			zjsfgkw.setCreditDate(creditJson.getCreditDate());
			zjsfgkw.setExecutetime(new Date());
		}
		return zjsfgkw;
		
		
	}


}

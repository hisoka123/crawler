package com.crawlermanage.service.transfer;

import java.util.Date;

import org.apache.log4j.Logger;

import com.crawler.sxjlcxpt.domain.json.CreditrRecordQueryPlatformJson;
import com.module.dao.entity.crqp.CreditrRecordQueryPlatform;

/**
 * @author yujh
 * @date 2016年6月15日
 * 对象转化工具
 */
public class CrqpPoVoTransfer {
	private static final Logger LOGGER = Logger.getLogger(CrqpPoVoTransfer.class);	
	public static CreditrRecordQueryPlatform voToPo(CreditrRecordQueryPlatformJson crqpJson) {
		CreditrRecordQueryPlatform crqp = new CreditrRecordQueryPlatform();		
		if (crqpJson != null) {
			crqp.setCompanyTitle(crqpJson.getCompanyTitle());//企业标题
			crqp.setKeywordDescription(crqpJson.getKeywordDescription());//关键字描述
			crqp.setContent(crqpJson.getContent());//内容				
			crqp.setExecutetime(new Date());
		}		
		return crqp;
	}
	
}
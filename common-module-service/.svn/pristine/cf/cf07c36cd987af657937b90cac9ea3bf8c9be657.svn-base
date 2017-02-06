package com.crawlermanage.service.transfer;

import org.apache.log4j.Logger;
import com.crawler.sipo.domain.json.TransactionDataSipo;
import com.module.dao.entity.sipo.TransactionData;

/**
 * @author kingly
 * @date 2016年6月13日
 * 对象转化工具
 */
public class TransactionDataPoVoTransfer {
	private static final Logger LOGGER = Logger.getLogger(TransactionDataPoVoTransfer.class);	
	public static TransactionData voToPo(TransactionDataSipo transactionDataSipo) {
		TransactionData transactionData = new TransactionData();
		if (transactionDataSipo != null) {
			transactionData.setNum(transactionDataSipo.getNum());
			transactionData.setDate(transactionDataSipo.getDate());
			transactionData.setType(transactionDataSipo.getType());
			transactionData.setContent(transactionDataSipo.getContent());
		}
		
		return transactionData;
	}
	
}
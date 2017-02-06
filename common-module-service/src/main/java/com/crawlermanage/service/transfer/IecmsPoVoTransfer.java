package com.crawlermanage.service.transfer;

import java.util.Date;

import org.apache.log4j.Logger;

import com.crawler.iecms.domain.json.IecmsJson;
import com.module.dao.entity.iecms.Iecms;

/**
 * @author yujh
 * @date 2016年6月15日
 * 对象转化工具
 */
public class IecmsPoVoTransfer {
	private static final Logger LOGGER = Logger.getLogger(IecmsPoVoTransfer.class);	
	public static Iecms voToPo(IecmsJson iecmsJson) {
		Iecms iecms = new Iecms();		
		if (iecmsJson != null) {
			iecms.setBusinessChineseName(iecmsJson.getBusinessChineseName());
			iecms.setBusinessEnglishName(iecmsJson.getBusinessEnglishName());
			iecms.setResidence(iecmsJson.getResidence());
			iecms.setZipcode(iecmsJson.getZipcode());
			iecms.setFax(iecmsJson.getFax());
			iecms.setImportExportEnterpriseCode(iecmsJson.getImportExportEnterpriseCode());
			iecms.setUnifiedSocialCreditCode(iecmsJson.getUnifiedSocialCreditCode());
			iecms.setExecutetime(new Date());
		}		
		return iecms;
	}
	
}
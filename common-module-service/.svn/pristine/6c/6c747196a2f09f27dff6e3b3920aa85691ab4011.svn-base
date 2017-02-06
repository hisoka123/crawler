package com.crawlermanage.service.transfer;

import org.apache.log4j.Logger;
import com.crawler.sipo.domain.json.SearchEngineSipo;
import com.module.dao.entity.sipo.Sipo;

/**
 * @author kingly
 * @date 2016年6月13日
 * 对象转化工具
 */
public class SipoPoVoTransfer {
	private static final Logger LOGGER = Logger.getLogger(SipoPoVoTransfer.class);
	
	public static Sipo voToPo(SearchEngineSipo searchEngineSipo) {
		Sipo sipo = new Sipo();
		if (searchEngineSipo != null) {
			sipo.setLicenseName(searchEngineSipo.getLicenseName());
			sipo.setLicenseNum(searchEngineSipo.getLicenseNum());
			sipo.setLicenseDate(searchEngineSipo.getLicenseDate());
			sipo.setApplicationNum(searchEngineSipo.getApplicationNum());	
			sipo.setApplicationDate(searchEngineSipo.getApplicationDate());
			sipo.setPatentHolder(searchEngineSipo.getPatentHolder());
			sipo.setInventor(searchEngineSipo.getInventor());
			sipo.setAddress(searchEngineSipo.getAddress());
			sipo.setClassNumber(searchEngineSipo.getClassNumber());
			sipo.setSummary(searchEngineSipo.getSummary());
			sipo.setNum(searchEngineSipo.getNum());
			sipo.setImg(searchEngineSipo.getImg());
			sipo.setQrcode(searchEngineSipo.getQrcode());
		}
		
		return sipo;
	}
	
}
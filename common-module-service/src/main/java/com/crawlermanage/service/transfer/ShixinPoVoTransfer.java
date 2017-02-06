package com.crawlermanage.service.transfer;

import java.util.Date;
import org.apache.log4j.Logger;
import org.json.JSONException;

import com.crawler.shixin.domain.json.ShiXinJson;
import com.module.dao.entity.shixin.Shixin;

public class ShixinPoVoTransfer {
	
	private static final Logger LOGGER = Logger.getLogger(ShixinPoVoTransfer.class);
	
	public static Shixin voToPo(ShiXinJson shiXinJson) throws JSONException {
		Shixin shixin=new Shixin();
		if (shiXinJson != null) {
			shixin.setName(shiXinJson.getIname());
			shixin.setSexy(shiXinJson.getSexy());
			shixin.setAge(shiXinJson.getAge());
			shixin.setCardNum(shiXinJson.getCardNum());
			shixin.setCourtName(shiXinJson.getCourtName());
			shixin.setAreaName(shiXinJson.getAreaName());
			shixin.setGistId(shiXinJson.getGistId());
			shixin.setRegDate(shiXinJson.getRegDate());
			shixin.setCaseCode(shiXinJson.getCaseCode());
			shixin.setGistUnit(shiXinJson.getGistUnit());
			shixin.setDuty(shiXinJson.getDuty());
			shixin.setPerformance(shiXinJson.getPerformance());
			shixin.setPublishDate(shiXinJson.getPublishDate());
			shixin.setDisruptTypeName(shiXinJson.getDisruptTypeName());
			shixin.setExecutetime(new Date());
		}
		return shixin;
		
		
	}


}

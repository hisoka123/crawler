package com.crawlermanage.service.transfer;

import org.apache.log4j.Logger;
import com.crawler.iautos.domain.json.UserFeedJson;
import com.module.dao.entity.iautos.Iautos;

/**
 * @author kingly
 * @date 2016年6月13日
 * 对象转化工具
 */
public class IautosPoVoTransfer {
	private static final Logger LOGGER = Logger.getLogger(IautosPoVoTransfer.class);
	
	public static Iautos voToPo(UserFeedJson userFeedJson) {
		Iautos iauto = new Iautos();
		if (userFeedJson != null) {
			iauto.setName(userFeedJson.getName());
			iauto.setTimekm(userFeedJson.getTimekm());
			iauto.setPrice(userFeedJson.getPrice());
			iauto.setProfile_image(userFeedJson.getProfile_image());
			iauto.setUrl(userFeedJson.getUrl());				
		}
		
		return iauto;
	}
	
}
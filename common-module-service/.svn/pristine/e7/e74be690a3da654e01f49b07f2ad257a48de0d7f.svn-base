package com.crawlermanage.service.transfer;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONException;
import com.crawler.zhengxin.domain.json.ZhengxinJson;
import com.module.dao.entity.qiyezhengxin.Zhengxin;

public class ZhengxinPoVoTransfer {
	
	private static final Logger LOGGER = Logger.getLogger(ZhengxinPoVoTransfer.class);
	
	public static Zhengxin voToPo(ZhengxinJson zhengxinJson) throws JSONException {
		Zhengxin zhengxin=new Zhengxin();
		if (zhengxinJson != null) {
			zhengxin.setCompany(zhengxinJson.getCompany());
			zhengxin.setDate(zhengxinJson.getDate());
			zhengxin.setExecutetime(new Date());
			zhengxin.setLink(zhengxinJson.getLink());
			zhengxin.setProperty(zhengxinJson.getProperty());
			zhengxin.setType(zhengxinJson.getType());
			zhengxin.setTitle(zhengxinJson.getTitle());
		}
		return zhengxin;
		
		
	}


}

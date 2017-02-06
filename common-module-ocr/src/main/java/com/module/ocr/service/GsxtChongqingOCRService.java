package com.module.ocr.service;

import java.io.File;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.ocr.utils.AbstractChaoJiYingHandler;

public class GsxtChongqingOCRService extends AbstractChaoJiYingHandler {
	private static final String CODETYPE = "6003";//"6003"; 复杂计算题 //验证码类型 参照 http://www.chaojiying.cn/price.html  似乎越贵越通用啊
	private static final String LEN_MIN = "0";
	private static final String TIME_ADD = "0";
	private static final String STR_DEBUG = "a";
	private static final Logger LOGGER = LoggerFactory.getLogger(GsxtChongqingOCRService.class);
	
	@Override
	public String getVerifycode(File file) throws Exception {
		Gson gson = new GsonBuilder().create();
		String chaoJiYingResult = super.getVerifycodeByChaoJiYing(CODETYPE, LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); //超级鹰返回结果 参照 http://www.chaojiying.com/api-10.html
		LOGGER.info("ChaoJiYingResult [CODETYPE=6003]: {}", chaoJiYingResult);
		String verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class).get("pic_str");
		
		if (StringUtils.isEmpty(verifycode)) {
			chaoJiYingResult = super.getVerifycodeByChaoJiYing("6003", LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); //6003 复杂计算题
			LOGGER.info("ChaoJiYingResult [CODETYPE=6003]: {}", chaoJiYingResult);
			verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class).get("pic_str");
		}
		
		return verifycode;
	}

}

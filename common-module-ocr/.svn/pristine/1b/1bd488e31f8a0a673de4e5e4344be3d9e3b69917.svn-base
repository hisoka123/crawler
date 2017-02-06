package com.module.ocr.service;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.ocr.utils.AbstractChaoJiYingHandler;
import com.module.ocr.utils.ChaoJiYingUtils;

public class GsxtGuangdongOCRService extends AbstractChaoJiYingHandler {
	private static final String CODETYPE = "2005";//"2004"; //验证码类型 参照 http://www.chaojiying.cn/price.html  似乎越贵越通用啊
	private static final String LEN_MIN = "0";
	private static final String TIME_ADD = "0";
	private static final String STR_DEBUG = "a";
	private static final Logger LOGGER = LoggerFactory.getLogger(GsxtGuangdongOCRService.class);
	
	@Override
	public String getVerifycode(File file) throws Exception {
		Gson gson = new GsonBuilder().create();
		String chaoJiYingResult = super.getVerifycodeByChaoJiYing(CODETYPE, LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); //超级鹰返回结果 参照 http://www.chaojiying.com/api-10.html
		
		LOGGER.info("ChaoJiYingResult [CODETYPE=2005]: {}", chaoJiYingResult);
		String verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class).get("pic_str");
		
		if (hasDengOrNull(verifycode)) {
			chaoJiYingResult = super.getVerifycodeByChaoJiYing("6003", LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); //6003 复杂计算题
			
			String errNo = ChaoJiYingUtils.getErrNo(chaoJiYingResult);
			if (!ChaoJiYingUtils.RESULT_SUCCESS.equals(errNo)) {
				return ChaoJiYingUtils.getErrMsg(errNo);
			}
			
			LOGGER.info("ChaoJiYingResult [CODETYPE=6003]: {}", chaoJiYingResult);
			verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class).get("pic_str");
		}
		
		return verifycode;
	}
	
	//如果识别结果含有“等”字 或 为空，很可能识别错误，则再调用6003进行识别
	private boolean hasDengOrNull(String result) {
		if (StringUtils.isEmpty(result) || result.contains("等")) {
			return true;
		}
		return false;
	}
	
}

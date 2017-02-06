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

public class GsxtQinghaiOCRService extends AbstractChaoJiYingHandler {
	private static final String CODETYPE = "5000";//"2004"; //验证码类型 参照 http://www.chaojiying.cn/price.html  似乎越贵越通用啊
	private static final String LEN_MIN = "0";
	private static final String TIME_ADD = "0";
	private static final String STR_DEBUG = "a";
	private static final Logger LOGGER = LoggerFactory.getLogger(GsxtQinghaiOCRService.class);
	
	@Override
	public String getVerifycode(File file) throws Exception {
		Gson gson = new GsonBuilder().create();
		String chaoJiYingResult = super.getVerifycodeByChaoJiYing(CODETYPE, LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); //超级鹰返回结果 参照 http://www.chaojiying.com/api-10.html
		
		LOGGER.info("ChaoJiYingResult [CODETYPE=5000]: {}", chaoJiYingResult);
		String verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class).get("pic_str");		
		
		if (StringUtils.isEmpty(verifycode) || verifycode.contains("拼音")) {
			chaoJiYingResult = super.getVerifycodeByChaoJiYing("6003", LEN_MIN,
					TIME_ADD, STR_DEBUG, file.getAbsolutePath()); // 6003 复杂计算题
			LOGGER.info("ChaoJiYingResult [CODETYPE=6003]: {}",
					chaoJiYingResult);
			
			String errNo = ChaoJiYingUtils.getErrNo(chaoJiYingResult);
			if (!ChaoJiYingUtils.RESULT_SUCCESS.equals(errNo)) {
				return ChaoJiYingUtils.getErrMsg(errNo);
			}
			
			verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class)
					.get("pic_str");
		}
		
		if (verifycode.contains("等于") || verifycode.contains("=") ||verifycode.contains("结果")) {
			chaoJiYingResult = super.getVerifycodeByChaoJiYing("6003", LEN_MIN,
					TIME_ADD, STR_DEBUG, file.getAbsolutePath()); // 6003 复杂计算题
			LOGGER.info("ChaoJiYingResult [CODETYPE=6003]: {}",
					chaoJiYingResult);
			verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class)
					.get("pic_str");
		}
		
		verifycode=verifycode.toLowerCase();
		return verifycode;
	}
	

	
	public static void main(String[] args) throws Exception {
		String verifycode = new GsxtQinghaiOCRService().getVerifycode(new File("F:/home/ubuntu/nfs-images/d029b60d-013e-4dfa-b565-56e0e4325150.jpg"));
		System.out.println(verifycode);
	}
}

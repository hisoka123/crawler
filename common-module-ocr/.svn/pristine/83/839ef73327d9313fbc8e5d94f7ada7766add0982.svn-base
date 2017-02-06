package com.module.ocr.service;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.ocr.utils.AbstractChaoJiYingHandler;
import com.module.ocr.utils.ChaoJiYingUtils;

/**
 * 可以用于1-6位的数字、英文字母图片识别
 */
public class GsxtGuizhouOCRService extends AbstractChaoJiYingHandler {

	private static final String CODETYPE = "6001";// "1106" 简单计算题; //验证码类型 参照
													// http://www.chaojiying.cn/price.html
													// 似乎越贵越通用啊
	private static final String LEN_MIN = "0";
	private static final String TIME_ADD = "0";
	private static final String STR_DEBUG = "a";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GsxtGuizhouOCRService.class);

	@Override
	public String getVerifycode(File file) throws Exception {

		Gson gson = new GsonBuilder().create();
		String chaoJiYingResult = super.getVerifycodeByChaoJiYing(CODETYPE,
				LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); // 超级鹰返回结果
																		// 参照
																		// http://www.chaojiying.com/api-10.html
		LOGGER.info("ChaoJiYingResult [CODETYPE=6001]: {}", chaoJiYingResult);
		String verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class)
				.get("pic_str");
		if (StringUtils.isEmpty(verifycode) || !isNum(verifycode)) {
			chaoJiYingResult = super.getVerifycodeByChaoJiYing("5000", LEN_MIN,
					TIME_ADD, STR_DEBUG, file.getAbsolutePath()); // 6003 复杂计算题
			LOGGER.info("ChaoJiYingResult [CODETYPE=5000]: {}",
					chaoJiYingResult);
			
			String errNo = ChaoJiYingUtils.getErrNo(chaoJiYingResult);
			if (!ChaoJiYingUtils.RESULT_SUCCESS.equals(errNo)) {
				return ChaoJiYingUtils.getErrMsg(errNo);
			}
			
			verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class)
					.get("pic_str");
		}
		
		if (verifycode.contains("等于") || verifycode.contains("=") || verifycode.contains("结果")) {
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
		verifycode=verifycode.toLowerCase();
		return verifycode;

	}

	// 校验字符串是否为数字
	private static boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static void main(String[] args) throws Exception {

		GsxtGuizhouOCRService gsxtJiangsuOCRService = new GsxtGuizhouOCRService();
		String verifycode = gsxtJiangsuOCRService.getVerifycode(new File(
				"F:\\home\\ubuntu\\nfs-images\\8854ff0a-cb76-4951-80a0-3522ad6cb2c1.jpg"));
		System.out.println("The verifycode is:" + verifycode);

	}

}

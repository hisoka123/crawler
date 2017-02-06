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

/**
 * 可以用于1-6位的数字、英文字母图片识别
 */
public class GsxtJiangsuOCRService extends AbstractChaoJiYingHandler {

	private static final String CODETYPE = "1006";// "1006" 1~6位英文数字;
													// //验证码类型 参照
													// http://www.chaojiying.cn/price.html
													// 似乎越贵越通用啊
	private static final String LEN_MIN = "0";
	private static final String TIME_ADD = "0";
	private static final String STR_DEBUG = "a";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GsxtJiangsuOCRService.class);

	@Override
	public String getVerifycode(File file) throws Exception {

		Gson gson = new GsonBuilder().create();
		String chaoJiYingResult = super.getVerifycodeByChaoJiYing(CODETYPE,
				LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); // 超级鹰返回结果
																		// 参照
																		// http://www.chaojiying.com/api-10.html
		LOGGER.info("ChaoJiYingResult [CODETYPE=1006]: {}", chaoJiYingResult);
		String verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class)
				.get("pic_str");
		if (StringUtils.isEmpty(verifycode) || !isContainsSixChar(verifycode)) {
			chaoJiYingResult = super.getVerifycodeByChaoJiYing("5000", LEN_MIN,
					TIME_ADD, STR_DEBUG, file.getAbsolutePath()); // 5000
																	// 不定长汉字英文数字
			LOGGER.info("ChaoJiYingResult [CODETYPE=5000]: {}",
					chaoJiYingResult);
			
			String errNo = ChaoJiYingUtils.getErrNo(chaoJiYingResult);
			if (!ChaoJiYingUtils.RESULT_SUCCESS.equals(errNo)) {
				return ChaoJiYingUtils.getErrMsg(errNo);
			}
			
			verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class)
					.get("pic_str");
		}
		return verifycode;

	}

	// 判断是否为6位
	private static boolean isContainsSixChar(String str) {
		return str.length() == 6;
	}

	// public static void main(String[] args) throws Exception {
	//
	// GsxtJiangsuOCRService gsxtJiangsuOCRService = new
	// GsxtJiangsuOCRService();
	// String verifycode = gsxtJiangsuOCRService.getVerifycode(new File(
	// "C:\\TCode\\gsxt\\03.jpg"));
	// System.out.println("The verifycode is:" + verifycode + "!");
	//
	// }

}

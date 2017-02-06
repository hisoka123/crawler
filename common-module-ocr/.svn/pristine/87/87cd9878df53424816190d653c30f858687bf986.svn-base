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
 * 用于解析不定长汉字英文数字以及复杂计算
 */
public class GsxtHebeiOCRService extends AbstractChaoJiYingHandler {

	private static final String CODETYPE = "5000";// "2004" 1~4位纯汉字; //验证码类型 参照
													// http://www.chaojiying.cn/price.html
													// 似乎越贵越通用啊
	private static final String LEN_MIN = "0";
	private static final String TIME_ADD = "0";
	private static final String STR_DEBUG = "a";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GsxtHebeiOCRService.class);

	@Override
	public String getVerifycode(File file) throws Exception {

		Gson gson = new GsonBuilder().create();
		String chaoJiYingResult = super.getVerifycodeByChaoJiYing(CODETYPE,
				LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); // 超级鹰返回结果
																		// 参照
																		// http://www.chaojiying.com/api-10.html
		LOGGER.info("ChaoJiYingResult [CODETYPE=5000]: {}", chaoJiYingResult);
		String verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class)
				.get("pic_str");
		if (StringUtils.isEmpty(verifycode)
				|| (!isNum(verifycode) && !isContainsFourChar(verifycode))) {
			chaoJiYingResult = super.getVerifycodeByChaoJiYing("6003", LEN_MIN,
					TIME_ADD, STR_DEBUG, file.getAbsolutePath()); // 6003
																	// 复杂计算题
			LOGGER.info("ChaoJiYingResult [CODETYPE=6003]: {}",
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

	// 校验字符串是否为数字
	private static boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	// 判断字符串是否包含两个或两个以上相邻的同一个字符
	// private static boolean isContainsMoreThanTwoSameAdjacentChar(String str)
	// {
	// if (StringUtils.isEmpty(str)) {
	// return false;
	// }
	// boolean flag = false;
	// char preChar = str.charAt(0);
	// char currentChar;
	// for (int i = 1; i < str.length(); i++) {
	// currentChar = str.charAt(i);
	// if (currentChar == preChar) {
	// return true;
	// }
	// preChar = currentChar;
	// }
	// return flag;
	// }

	// 判断是否为4位
	private static boolean isContainsFourChar(String str) {
		return str.length() == 4;
	}

	// public static void main(String[] args) throws Exception {
	//
	// GsxtHebeiOCRService gsxtHebeiOCRService = new GsxtHebeiOCRService();
	// String verifycode = gsxtHebeiOCRService.getVerifycode(new File(
	// "C:\\TCode\\gsxt\\25.jpg"));
	// System.out.println("The verifycode is:" + verifycode + "!");
	//
	// }

}

package com.crawler.domain.json;

import java.util.Arrays;
import java.util.List;

public interface StatusCodeDef {
	public static final String DOING_STATE = "-3"; 				// 正在进行中
	public static final String WEB_SERVICE_EXCEPTION = "-2"; 	// web服务器异常
	public static final String FAILURE = "-1"; 					// 程序执行失败
	public static final String SCCCESS = "0"; 					// 程序执行成功 或 数据初始化
	public static final String GET_DATA_SCCCESS = "1"; 			// 数据获取成功
	public static final String IMAGECODE_ERROR = "2"; 			// 验证码错误
	public static final String COOKIE_ERROR = "3"; 				// cookie错误
	public static final String MOBILE_VERICODE_ERROR = "4"; 	// 手机验证码错误
	public static final String FREQUENCY_LIMITED = "5";			// 频率限制
	public static final String DATA_CACHED = "6"; 				// 缓存中数据
	public static final String NO_DATA_FOUND = "7"; 			// 没有搜索到数据
	public static final String ILLEGAL_CHAR = "8"; 				// 非法字符
	public static final String USERNAME_OR_PASSWORD_ERROR = "9";// 用户名或密码错误
	public static final String ILLEGAL_PARAM = "10";            // 非法参数
	
	//需要爬取的状态
	public static final List<Integer> NEED_QUERY_STATES = Arrays.asList(
			new Integer[] { 0,
			Integer.parseInt(IMAGECODE_ERROR),
			Integer.parseInt(FREQUENCY_LIMITED), 100004,
			100013, 100014, 100015, 100017, 100020});	
	
	//最多爬取3次
	public static final int UPPER_LIMIT_NUM = 3;
	
	//文件后缀
	public static final String SERIALIZED_FILE_SUFFIX = ".ser";
}

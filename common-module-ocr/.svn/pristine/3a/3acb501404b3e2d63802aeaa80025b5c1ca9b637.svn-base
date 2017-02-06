package com.module.ocr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OCRConnectUtils {
	private static String serverHost;
	private static final Logger LOGGER = LoggerFactory.getLogger(OCRConnectUtils.class);
	
	static {
		InputStream is = OCRConnectUtils.class.getResourceAsStream("/ocr.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			serverHost = prop.getProperty("ocr.server");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getServerHost() {
		return serverHost;
	}
	
	
	/**
	 * 调用ocr-service的controller 进行 验证码识别
	 * @param urlPattern  ocr-service模块对应的controller方法路径
	 * @param imageUrl	     验证码图片的资源访问路径
	 * @return			     图片验证码识别结果 
	 * @throws IOException
	 */
	public static String getVerifycode(String urlPattern, String imageUrl) throws IOException {
		URL url = new URL("http://" + getServerHost() + "/ocr-service"+ urlPattern +"?imageUrl=" + URLEncoder.encode(imageUrl, "utf-8"));
		long start = System.currentTimeMillis();
		
		LOGGER.info("[request ocr server]===========================start===========================");
		LOGGER.info("[request ocr server]================= url====================:" + url.toString());
		InputStream is;
		try {
			LOGGER.info("[request ocr server]=================第一次尝试====================");
			is = url.openStream();
		} catch (IOException e) {
			LOGGER.info("[request ocr server]=================第二次尝试====================");
			is = url.openStream();
		}
		
		long end = System.currentTimeMillis();
		LOGGER.info("[request ocr server]===========================end===========================耗时：" + (end-start) + "ms");
		return IOUtils.toString(is, "utf-8");
	}
	
	
	public static String getVerifycode(String urlPattern, String imageUrl, String codeType) throws IOException {
		URL url = new URL("http://" + getServerHost() + "/ocr-service"+ urlPattern +"?imageUrl=" + URLEncoder.encode(imageUrl, "utf-8") + "&codeType=" + codeType);
		long start = System.currentTimeMillis();
		
		LOGGER.info("[request ocr server]===========================start===========================");
		LOGGER.info("[request ocr server]================= url====================:" + url.toString());
		InputStream is;
		try {
			LOGGER.info("[request ocr server]=================第一次尝试====================");
			is = url.openStream();
		} catch (IOException e) {
			LOGGER.info("[request ocr server]=================第二次尝试====================");
			is = url.openStream();
		}
		
		long end = System.currentTimeMillis();
		LOGGER.info("[request ocr server]===========================end===========================耗时：" + (end-start) + "ms");
		return IOUtils.toString(is, "utf-8");
	}
}

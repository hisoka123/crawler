package com.crawlermanage.controller.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.commons.io.IOUtils;

public class OCRUtils {
	
	//调用ocr-service的controller 进行 验证码识别
	public static String getVerifycode(String urlPattern, String imageUrl) throws IOException {
		URL url = new URL("http://" + Config.getServerHost() + ":8080/ocr-service"+ urlPattern +"?imageUrl=" + URLEncoder.encode(imageUrl, "utf-8"));
		InputStream is = url.openStream();
		return IOUtils.toString(is, "utf-8");
	}
}

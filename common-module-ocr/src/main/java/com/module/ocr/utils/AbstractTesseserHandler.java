/**
 * 
 */
package com.module.ocr.utils;

import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kingly
 * @date 2016年1月5日
 * 
 */
public abstract class AbstractTesseserHandler implements IVerifycodeHandler {
	private static final String TESSERACT_ROOT = "/usr/bin"; //"D:/Tesseract-OCR";
	public static final String TESSERACT_LANGUAGE = "eng";
	private long lastUpdateTime;
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTesseserHandler.class);
	
	/**
	 * @param imageFile  图片
	 * @param resultFile 结果result.txt文件
	 * @return 识别字符串结果
	 * @throws Exception
	 */
	@Override
	public String getVerifycode(File imageFile) throws Exception {
		File resultFile = new File(imageFile.getAbsolutePath().replace(imageFile.getName().substring(imageFile.getName().lastIndexOf(".")+1), "txt"));
		//文件存在则记录上一次文件更新时间
		/*if (resultFile.exists()) {
			lastUpdateTime = resultFile.lastModified();
		}*/
		
		//根据系统的不同，执行相应系统的命令调用tesseract-OCR
		final String system = System.getProperty("os.name").toLowerCase();
		String resultFileName = resultFile.getAbsolutePath().replace(".txt", "");
		if (system.contains("linux")) {
			String[] cmdA = {"/bin/sh", "-c", TESSERACT_ROOT + "/tesseract " + imageFile.getAbsolutePath()+ " " + resultFileName + " -l " + TESSERACT_LANGUAGE};  
			Process process = Runtime.getRuntime().exec(cmdA);  
			LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
			StringBuffer sb = new StringBuffer();  
			String line;
			while ((line = br.readLine()) != null) {  
				sb.append(line).append("\n");  
			} //System.out.println(sb.toString());
		} else if (system.contains("windows")) {
			Runtime r = Runtime.getRuntime();
			String command = TESSERACT_ROOT + "/tesseract.exe " + imageFile.getAbsolutePath() + " " + resultFileName + " -l " + TESSERACT_LANGUAGE;
			r.exec(command);
		}

		//只要result.txt文件不存在 或 result.txt文件没有更新 则等待，等待时间超过5s则break！
		long startTime = System.currentTimeMillis();
		while (!resultFile.exists() || lastUpdateTime==resultFile.lastModified()) {
			LOGGER.info("验证码识别中...");
			Thread.sleep(100);
			if (System.currentTimeMillis()-startTime > 5000) {
				LOGGER.info("验证码识别超时！");
				break;
			}
		}
		
		String result = FileUtils.readFileToString(resultFile);
		result = result.replace(" ", "").trim();
		return result;
	}
	
	
	@Override
	public String getVerifycode(File file, String codeType) throws Exception {
		return null;
	}
}

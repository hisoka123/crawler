package com.ocr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OCRFileHelper {
	private static String OCRFILE_BASE_PATH;
	static {
		InputStream is = OCRFileHelper.class.getResourceAsStream("/ocr.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			OCRFILE_BASE_PATH = prop.getProperty("nfs.filepath", "/home/ubuntu/nfs-images");
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
	
	public static String getOCRFILE_BASE_PATH() {
		return OCRFILE_BASE_PATH;
	}
	
	
	//写入
	/*public static String writeImageByUrl(String imageUrl) throws IOException {
		URL url = new URL(imageUrl);
		
		String destination = OCRFILE_BASE_PATH + "/" + UUID.randomUUID() + ".jpg";
		InputStream is = url.openStream();
		try {
			FileUtils.copyInputStreamToFile(is, new File(destination));
		} finally {
			if (is!=null) {
				is.close();
			}
		}
		return destination;
	}*/
}

package com.crawler.domain.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static String nfsNginxServer;//nfs.nginx.server;
	private static String nfsFilepath;//nfs.filepath;
	private static String ocrServer;//ocr.server;
	
	static {
		InputStream is = Config.class.getResourceAsStream("/crawler_conf.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			nfsNginxServer = prop.getProperty("nfs.nginx.server");
			nfsFilepath = prop.getProperty("nfs.filepath");
			ocrServer = prop.getProperty("ocr.server");
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

	public static String getNfsNginxServer() {
		return nfsNginxServer;
	}
	public static String getNfsFilepath() {
		return nfsFilepath;
	}
	public static String getOcrServer() {
		return ocrServer;
	}
}	

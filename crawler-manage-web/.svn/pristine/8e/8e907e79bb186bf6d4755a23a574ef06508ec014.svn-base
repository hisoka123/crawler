package com.crawlermanage.controller.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static String serverHost;
	private static String nginxServer;
	
	static {
		InputStream is = Config.class.getResourceAsStream("/application.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			serverHost = prop.getProperty("ocr.server");
			nginxServer = prop.getProperty("nfs.nginx.server");
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
	public static String getNginxServer() {
		return nginxServer;
	}
}	

/**
 * 
 */
package com.storm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kingly
 * @date 2016年5月26日
 * 命令行工具类
 */
public class CommandUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandUtil.class);
	
	public static String runCommand(String[] command) throws IOException {
		LOGGER.info("================================[command]: start!============================================");
		if (command!=null) {
			StringBuffer commandSb = new StringBuffer();
			for (int i = 0; i < command.length; i++) {
				commandSb.append(command[i] + " ");
			}
			LOGGER.info("[running command] " + commandSb.toString());
		}
		
		Process process = Runtime.getRuntime().exec(command);  
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuffer sb = new StringBuffer();  
		String line;
		String separator = System.getProperty("line.separator");
		while ((line = br.readLine()) != null) {  
			sb.append(line + separator);
		}
		
		LOGGER.info("================================[command]: end!============================================");
		return sb.toString();
	}
}

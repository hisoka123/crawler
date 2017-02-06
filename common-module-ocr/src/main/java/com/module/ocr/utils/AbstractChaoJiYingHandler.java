package com.module.ocr.utils;

import java.io.File;

public abstract class AbstractChaoJiYingHandler implements IVerifycodeHandler {
	
	private static final String USERNAME = "md19841002"; //"harmonycredit";		//"md19841002";
	private static final String PASSWORD = "12qwaszx";   //"HcPcPt20160919";	//"xhhcxy168";
	private static final String SOFTID = "891477";       //"892052";			//"891477"; 
	
	public String getVerifycodeByChaoJiYing(String codetype, String len_min, String time_add, String str_debug, String filePath) {
		return ChaoJiYingUtils.PostPic(USERNAME, PASSWORD, SOFTID, codetype, len_min, time_add, str_debug, filePath);
	}

	@Override
	public String getVerifycode(File file) throws Exception {
		return null;
	}

	@Override
	public String getVerifycode(File file, String codeType) throws Exception {
		return null;
	}
	
}

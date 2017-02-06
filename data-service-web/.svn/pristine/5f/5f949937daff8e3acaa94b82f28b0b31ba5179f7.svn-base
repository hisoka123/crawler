package com.dataservice.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLHelper {
	 
	public static String decode(String text,String charset) {
		String str = null;
		try {
			str = URLDecoder.decode(text, charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static String encode(String text,String charset) {
		String str = null;
		try {
			str = URLEncoder.encode(text, charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str; 
	}
	
}

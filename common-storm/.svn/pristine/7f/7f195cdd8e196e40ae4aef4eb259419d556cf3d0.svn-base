package com.storm.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.storm.domian.JsonParam;

public class JsonEngineFunction {

	private static final Logger log = LoggerFactory.getLogger(JsonEngineFunction.class);

	public String getJson(JsonParam param) throws Exception{ 
		String url = param.getUrl();
		log.info("url:{}",url);
		//String json = readUrl(url); 
		String json = readJsonUrl(url); 
		return json;
	}
	
	private String readJsonUrl(String urlString) throws Exception{ 
		return IOUtils.toString(new URL(urlString),"GBK"); 
	}
	
	private String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
}

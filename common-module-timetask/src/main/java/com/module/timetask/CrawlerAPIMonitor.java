package com.module.timetask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.google.gson.Gson;

@Component("crawlerAPIMonitor")
public class CrawlerAPIMonitor {
	private static final Logger Log = Logger.getLogger(CrawlerAPIMonitor.class);
	
	public Map<String, Object> monitor(String jobName, String jobGroup,String  urlString) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Log.info("==========================================================CrawlerAPIMonitor#monitor URL(urlString) 获得连接========================================================================");
		URL url = new URL(urlString);
		
		String str;
		URLConnection connection = null;
		InputStream is = null;
		try {
			Log.info("==========================================================url.openConnection() start==========connection is "+connection+"==============================================================");
			connection = url.openConnection();
			Log.info("==========================================================connection.getInputStream() start=====url is "+url+"===================================================================");
			is = connection.getInputStream();
			Log.info("==========================================================connection.getInputStream() start=====is is "+is+"===================================================================");			
			str = IOUtils.toString(is,Charsets.UTF_8);
			Log.info("==========================================================connection.getInputStream() end ==========str is "+str+"==============================================================");			
		} finally {
			
			try{
				
				if(null != connection){
					Log.info("==========================================================IOUtils.close(connection) start=====connection is "+connection+"===================================================================");
					IOUtils.close(connection);
					Log.info("==========================================================IOUtils.close(connection) end=====connection is "+connection+"===================================================================");
				}
				
			}finally{
				
				if(null != is){
					Log.info("==========================================================is.close() start=================is is "+is+"=======================================================");	
					is.close();
					Log.info("==========================================================is.close() end=================is is "+is+"=======================================================");	
				}
			}

		}
		Gson gson = new Gson();
		Result fromJson = gson.fromJson(str, Result.class);
		Log.info("==========================================================fromJson is "+fromJson.toString()+"=======================================================");	
		resultMap.put("APIresult", fromJson);
		Log.info("==========================================================resultMap is "+resultMap.toString()+"=======================================================");
		return resultMap;
	}
}

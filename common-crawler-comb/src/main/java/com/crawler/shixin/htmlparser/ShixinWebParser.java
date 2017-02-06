package com.crawler.shixin.htmlparser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.crawler.shixin.domain.json.ShiXinJson;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class ShixinWebParser {
	
	private static final Logger log = LoggerFactory.getLogger(ShixinWebParser.class);
	
	public List<Object> shiXinJsonParser(String html){
        Gson gson = new GsonBuilder().create();
		Type htmlListType = new TypeToken<List<Object>>(){}.getType();
		List<Object> resultHtmlList = gson.fromJson(html, htmlListType);
		return resultHtmlList;
	}
	
	public List<ShiXinJson> shiXinJsonParser2(String html){
        Gson gson = new GsonBuilder().create();
		Type htmlListType = new TypeToken<List<Object>>(){}.getType();
		List<Object> resultHtmlList = gson.fromJson(html, htmlListType);
		List<ShiXinJson> shiXinJsons=new ArrayList<ShiXinJson>();
		for(Object object:resultHtmlList){
			Type htmlType = new TypeToken<ShiXinJson>(){}.getType();
			ShiXinJson shiXinJson = gson.fromJson((String) object, htmlType);
			shiXinJsons.add(shiXinJson);
		}
		return shiXinJsons;
	}

}

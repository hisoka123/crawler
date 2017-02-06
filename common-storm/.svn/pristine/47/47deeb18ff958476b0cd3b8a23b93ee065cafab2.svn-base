package com.storm.function;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.crawler.storm.def.ShixinResultData;
import com.crawler.storm.def.WebParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.storm.def.StormTopologyConfig;

public class ShixinFunction {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShixinFunction.class);
	
	public static String searchPreHandle(WebParam webParam) throws Exception {
		LOGGER.info("=====================ShixinFunction.searchPreHandle start!========================");
		Gson gson = new GsonBuilder().create();
		ShixinResultData shixinResultData = new ShixinResultData(); 
		webParam.setSearchPage(webParam.getSearchPage());
		
		//获取搜索页隐藏域（hidden）参数
		if (StringUtils.isEmpty(webParam.getSearchPage())) {
			throw new RuntimeException("The searchPageUrl is not be defined!");
		}
		Response searchPage = Jsoup.connect(webParam.getSearchPage()).execute();
		Document document = searchPage.parse();
		System.out.println(document.html());
		
		
		return null;
	}
}

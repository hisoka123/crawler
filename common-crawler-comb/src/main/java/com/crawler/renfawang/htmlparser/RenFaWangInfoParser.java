package com.crawler.renfawang.htmlparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.htmlparser.AbstractParser;
import com.crawler.renfawang.domain.json.PeopleCourtFeedJson;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Component
public class RenFaWangInfoParser extends AbstractParser{

	private static final Logger logger = LoggerFactory.getLogger(RenFaWangInfoParser.class);
	
	public Result<List<Map<String,String>>> parseHtml(String html) {
		logger.info("===========RenFaWangInfoParser.parseHtml  start==========");
		
		Result<List<Map<String,String>>> result = new Result<List<Map<String,String>>>();
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		
    	Elements tbody = Jsoup.parseBodyFragment(html).select("tbody");
    	
    	if(tbody==null || tbody.isEmpty()) {
    		Map<String, String> map = new HashMap<String, String>();
    		map.put("tips", "没搜索到结果");
    		resultList.add(map);
    	} else {
    		Elements trElements = tbody.select("tr");
    		for (int i = 1; i < trElements.size(); i++) {
				Element element = trElements.get(i);
				Elements tdElements = element.select("td");
				Element element1 = tdElements.get(1);
				Element element2 = tdElements.get(2);
				Element element3 = tdElements.get(3);
				Element element4 = tdElements.get(4);
				Element aElement = element4.select("a").get(0);
				String idAttr = getElementAttr(aElement, "id");
				
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("pname", element1.text());
				resultMap.put("zxDate", element2.text());
				resultMap.put("zxNo", element3.text());
				resultMap.put("pNum", idAttr);
				resultList.add(resultMap);
			}
    	}
    	
    	result.setData(resultList);
    	
		return result;
	}
	
	public List<PeopleCourtFeedJson> parseDetailPage(String detailResults) {
		List<PeopleCourtFeedJson> peopleCourtFeedJsons = new ArrayList<PeopleCourtFeedJson>();
		
		Gson gson = new Gson();
		Map<String, Object> resultHtmlMap = gson.fromJson(detailResults, new TypeToken<Map<String, Object>>(){}.getType()); 
		List<String> detailDatas = (List<String>)resultHtmlMap.get("detailData");
		for (String peopleCourtFeedJson : detailDatas) {
			JsonObject obj = new JsonParser().parse(peopleCourtFeedJson).getAsJsonObject();
			String ccrs = obj.get("html").getAsString();
			PeopleCourtFeedJson peopleCourt = gson.fromJson(ccrs, PeopleCourtFeedJson.class);
			peopleCourtFeedJsons.add(peopleCourt);
		}
		return peopleCourtFeedJsons;
	}
}

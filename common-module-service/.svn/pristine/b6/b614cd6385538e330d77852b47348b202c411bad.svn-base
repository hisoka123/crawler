package com.crawlermanage.service.sipo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.sipo.domain.json.SearchEngineSipo;
import com.crawler.sipo.domain.json.TransactionDataSipo;
import com.crawler.sipo.htmlparse.SipoSearchParser;
import com.crawler.sipo.htmlparse.TransactionDataParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;
import com.module.htmlunit.unit.BaseUnit;

@Component
public class SipoSearchService {
	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private SipoSearchParser sipoSearchParser;
	
	@Autowired
	private TransactionDataParser transactionDataParser;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SipoSearchService.class);

	//@Cacheable(value = "dataCache", key = "'SipoSearchParser.search_' + #url + '&isDebug=' + #isDebug", unless = "#result==null || #isDebug==true")
	public  Result<List<SearchEngineSipo>>  search(String url, boolean isDebug,String logback) {


		LOGGER.info("url:{}", url);
		Result<List<SearchEngineSipo>> result = new Result<List<SearchEngineSipo>>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CRAWLERENGINE);
		WebParam webParam = new WebParam();
		webParam.setUrl(url);
		webParam.setUnit(UtilDefinition.JSOUP);
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		LOGGER.info("SipoSearchParser.search param:{}", param);

		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<SearchEngineSipo> users = sipoSearchParser.sipoListParser(wpr.getHtml());		
		for (SearchEngineSipo user:users) {
			webParam.setUrl("http://epub.sipo.gov.cn/fullTran.action?an="+user.getNum());
			param = fcm.toJson();
	        Result<List<TransactionDataSipo>>  originUrlPageResutl = new Result<List<TransactionDataSipo>>();;
	        originUrlPageResutl = crawlerEngine.execute(param, originUrlPageResutl);
	        
	        WebPageResponse wpr2 = gson.fromJson(originUrlPageResutl.getHtml(), WebPageResponse.class);	     
	        List<TransactionDataSipo> trans=transactionDataParser.transactionParser(wpr2.getHtml());
	        user.setTransactionDatas(trans);
		}
		
		result.setData(users);
		result.debugMode(isDebug);		
		return result;	
	}
	
	
	
	public  Result<List<SearchEngineSipo>>  getDataOnce(String type, String keyword,boolean isDebug,String logback) {
		keyword="申请（专利权）人='%"+keyword+"%'";
		keyword = BaseUnit.encode(keyword, "utf8");	
		String url = "http://epub.sipo.gov.cn/patentoutline.action?showType=1&strWord="+keyword+"&numSortMethod=0"
				+"&strLicenseCode=&selected="+type+"&numFMGB=0&numFMSQ=0&numSYXX=0&numWGSQ=0&pageSize=3&pageNow=1";
		Result<List<SearchEngineSipo>> result = new Result<List<SearchEngineSipo>>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CRAWLERENGINE);
		WebParam webParam = new WebParam();
		webParam.setUrl(url);
		webParam.setUnit(UtilDefinition.JSOUP);
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		LOGGER.info("SipoSearchParser.search param:{}", param);

		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<SearchEngineSipo> users = sipoSearchParser.sipoListParser(wpr.getHtml());		
		for (SearchEngineSipo user:users) {
			webParam.setUrl("http://epub.sipo.gov.cn/fullTran.action?an="+user.getNum());
			param = fcm.toJson();
	        Result<List<TransactionDataSipo>>  originUrlPageResutl = new Result<List<TransactionDataSipo>>();;
	        originUrlPageResutl = crawlerEngine.execute(param, originUrlPageResutl);
	        
	        WebPageResponse wpr2 = gson.fromJson(originUrlPageResutl.getHtml(), WebPageResponse.class);	     
	        List<TransactionDataSipo> trans=transactionDataParser.transactionParser(wpr2.getHtml());
	        user.setTransactionDatas(trans);
		}
		
		result.setData(users);
		result.debugMode(isDebug);		
		return result;	
	}
	
	
}

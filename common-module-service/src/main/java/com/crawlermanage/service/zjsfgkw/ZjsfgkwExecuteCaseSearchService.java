package com.crawlermanage.service.zjsfgkw;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawler.zjsfgkw.domain.json.ExecuteCaseSearchJson;
import com.crawler.zjsfgkw.htmlparser.ExecuteCaseSearchFeedListParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.zjsfgkw.Zjsfgkw;
import com.module.dao.entity.zjsfgkw.ZjsfgkwKeyword;
import com.module.dao.repository.zjsfgkw.ZjsfgkwKeywordRepository;
import com.module.dao.repository.zjsfgkw.ZjsfgkwRepository;

@Component
public class ZjsfgkwExecuteCaseSearchService{
	
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private ExecuteCaseSearchFeedListParser executeCaseSearchFeedListParser;
	
	@Autowired
	private ZjsfgkwKeywordRepository zjsfgkwKeywordRepository;
	
	@Autowired
	private ZjsfgkwRepository zjsfgkwRepository;
	  
	private static final Logger log = LoggerFactory.getLogger(ZjsfgkwExecuteCaseSearchService.class);
	
	
	//@Cacheable(value="dataCache", key="'ZjsfgkwExecuteCaseSearchService.searchExecuteCase_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<ExecuteCaseSearchJson>> searchExecuteCase(String url, boolean isDebug, String logback) {
		
		log.info("url:{}",url);
		
		Result<List<ExecuteCaseSearchJson>> result = new Result<List<ExecuteCaseSearchJson>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.ZJSFGKWEXECUTECASE_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUrl(url);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("ZjsfgkwExecuteCaseSearchService.searchExecuteCase param:{}", param);
		    
		result = crawlerEngine.execute(param, result);
		System.out.println(result.getHtml());
		
		String resultHtml = result.getHtml();
		Gson gson = new GsonBuilder().create();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		if (statusCodeDef!=null && StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setHtml((String) resultHtmlMap.get("searchPageHtml"));
			result.setError(errorMsg);
		}else{
			List<ExecuteCaseSearchJson> users;
			try {
				users = executeCaseSearchFeedListParser.executeCaseSearchParser((String) resultHtmlMap.get("searchPageHtml"));
				result.setData(users);
				result.debugMode(isDebug);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result; 
	}
	
	@Cacheable(value="dataCache", key="'ZjsfgkwExecuteCaseSearchService.searchExecuteCase2_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<CreditJson>> searchExecuteCase2(String url, boolean isDebug, String logback) {
		
		log.info("url:{}",url);
		
		Result<List<CreditJson>> result = new Result<List<CreditJson>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.ZJSFGKWEXECUTECASE_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUrl(url);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("ZjsfgkwExecuteCaseSearchService.searchExecuteCase param:{}", param);
		    
		result = crawlerEngine.execute(param, result);
		System.out.println(result.getHtml());
		List<CreditJson> users;
		try {
			users = executeCaseSearchFeedListParser.executeCaseSearchParser2(result.getHtml());
			result.setData(users);
			result.debugMode(isDebug);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result; 
	}
	   
	   
	   //检查是定时任务中是否存在该任务
	   public List<ZjsfgkwKeyword>  checkIsExistBYName(String queryType,String content){
		   
		     return zjsfgkwKeywordRepository.findByTypeAndValue(queryType, content);
	   }
	   
	 //检查是定时任务中是否存在该任务
	   public List<ZjsfgkwKeyword>  checkIsExistBYName2(String queryType,String content){
		   
		     return zjsfgkwKeywordRepository.findByTypeAndValue2(queryType, content);
	   }
	   
	   //查询详情
	   public List<Zjsfgkw> getDetailByCOI(List<Long> idList){
		      
		      return zjsfgkwRepository.findByCOI(idList);
	   }
	   
	   //将新任务保存到定时任务表中
	   public ZjsfgkwKeyword saveTimeTask(ZjsfgkwKeyword zjsfgkwKeyword){
		      return zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
	   }
	   
	   //通过id查询定时任务
	   public ZjsfgkwKeyword  getTimeTask(long id){
		      return zjsfgkwKeywordRepository.findOne(id);
	   }
	   
	   //检查是定时任务中是否存在该任务
	   public List<ZjsfgkwKeyword>  checkIsExistBYTypeAndName(String queryType,String content){
		   
		     return zjsfgkwKeywordRepository.findByTypeAndValue2(queryType, content);
	   }
}

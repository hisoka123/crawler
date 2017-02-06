package com.crawlermanage.service.weibo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;







import com.crawler.domain.json.Result;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.storm.def.WeiboHandleParam;
import com.crawler.weibo.domain.json.UserActionResponse;
import com.crawler.weibo.htmlparser.WeiboUserActionParser;
import com.crawlermanage.service.aspect.CrawlerEngine;

@Component
public class WeiboUserActionService {
	
	@Autowired
	private CrawlerEngine crawlerEngine;
	 
	@Autowired
	private WeiboUserActionParser weiboUserActionParser;
	
	private static final Logger log = LoggerFactory.getLogger(WeiboUserActionService.class);
	
	public Result<UserActionResponse> userAction(String action, String uid, boolean isDebug, String logback) {
		
		Result<UserActionResponse> result = new Result<UserActionResponse>();
		
		log.info("userAction :action"+action+"     uid:"+uid);
		
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.WEIBO_HANDLE_USERACTION); 
        WeiboHandleParam weiboHandleEngineParam = new WeiboHandleParam(); 
        weiboHandleEngineParam.setHandle(action);// 
        weiboHandleEngineParam.setUid(uid);//
        fcm.setWeiboHandleEngineParam(weiboHandleEngineParam);
        
	    WebParam webParam = new WebParam();
	    webParam.setLogback(logback);
	    fcm.setWebEngineParam(webParam);
		 
	    String param = fcm.toJson();
	    log.info("userAction param:{}",param);

	    result = crawlerEngine.execute(param, result);
		UserActionResponse response = weiboUserActionParser.userActionParser(result.getHtml());
		if (response!=null) log.info("response.getOk:{}", response.getOk());
		result.setData(response);
		result.debugMode(isDebug);
		
		return result;
		
	}
	

}

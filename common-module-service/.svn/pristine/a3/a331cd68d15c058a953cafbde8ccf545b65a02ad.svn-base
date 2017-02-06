package com.crawlermanage.service.qiyezhengxin;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.zhengxin.domain.json.ZhengxinJson;
import com.crawler.zhengxin.htmlparser.ZhengxinParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.module.dao.entity.qiyezhengxin.Zhengxin;
import com.module.dao.entity.qiyezhengxin.ZhengxinKeyword;
import com.module.dao.repository.qiyezhengxin.ZhengxinKeywordRepository;
import com.module.dao.repository.qiyezhengxin.ZhengxinRepository;
import com.module.htmlunit.definition.UtilDefinition;

@Component
public class QiyezhengxinApiService{
	
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private ZhengxinParser zhengxinParser;
	
	@Autowired
	private ZhengxinKeywordRepository zhengxinKeywordRepository;
	  
	@Autowired
	private ZhengxinRepository zhengxinRepository;
	private static final Logger log = LoggerFactory.getLogger(QiyezhengxinApiService.class);
	
	
	//@Cacheable(value="dataCache", key="'QiyezhengxinApiService.search_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<ZhengxinJson>> search(String url, boolean isDebug, String logback){
		
		log.info("url:{}",url);
		
		Result<List<ZhengxinJson>> result = new Result<List<ZhengxinJson>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.ENTERPRISE_CREDIT);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.addParam("jsEnable", "1");//1表示true
		webParam.addParam("keyword", url.split("@")[1]);//匹配搜索关键词
		webParam.setUrl(url.split("@")[0]);//获取url
		webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("QiyezhengxinApiService.search param:{}", param);
		    
		result = crawlerEngine.execute(param, result);
		System.out.println(result.getHtml());
		if(result!=null && result.getHtml().equals("frequently")){
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorName("频率限制");
			errorMsg.setErrorMsg("sorry,你可能访问的太快了");
			result.setError(errorMsg);
		}if(result!=null && result.getHtml().equals("0")){
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorName("没有搜索到数据");
			errorMsg.setErrorMsg("没有搜索到数据");
			result.setError(errorMsg);
		}else{
			List<ZhengxinJson> zhengxinJsons = zhengxinParser.qiyezhengxinParser(result.getHtml());
			result.setData(zhengxinJsons);
			result.debugMode(isDebug);	
		}
		
		return result; 
	}
	
	
	 //检查是定时任务中是否存在该任务
	   public List<ZhengxinKeyword>  checkIsExistBYName(String content){
		   
		     return zhengxinKeywordRepository.findByTypeAndValue(content);
	   }
	   
	   //查询详情
	   public List<Zhengxin> getDetailByCOI(List<Long> idList){
		      
		      return zhengxinRepository.findByCOI(idList);
	   }
	   
	   //将新任务保存到定时任务表中
	   public ZhengxinKeyword saveTimeTask(ZhengxinKeyword zhengxinKeyword){
		      return zhengxinKeywordRepository.save(zhengxinKeyword);
	   }
	   
	   //通过id查询定时任务
	   public ZhengxinKeyword  getTimeTask(long id){
		      return zhengxinKeywordRepository.findOne(id);
	   }
	
}

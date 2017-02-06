package com.crawlermanage.service.fahaicc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.fahaicc.domain.json.FahaiItemFeed;
import com.crawlermanage.service.renfawang.CompanyOrIDTaskService;
import com.crawlermanage.service.transfer.FahaiccPoVoTransfer;
import com.module.dao.entity.fahaicc.FahaiccResult;
import com.module.dao.entity.fahaicc.FahaiccRoot;
import com.module.dao.repository.fahaicc.FahaiccRootRepository;

@Service
public class FahaiccRootTaskService {
	
	private static final boolean IS_DEBUG = true;
	private static final Logger LOGGER = Logger.getLogger(CompanyOrIDTaskService.class);
	
	@Autowired
	private FahaiccRootRepository fahaiccRootRepository;
	
	@Autowired
	private FahaiccService fahaiccService;
	
	@Transactional
	public Map<String, Object> excOneceTask(String type) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		FahaiccRoot fahaiccRoot = null;
		try {
			fahaiccRoot = fahaiccRootRepository.findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(type, StatusCodeDef.NEED_QUERY_STATES, StatusCodeDef.UPPER_LIMIT_NUM);
			if (fahaiccRoot != null) {
				fahaiccRoot.setState(Integer.valueOf(StatusCodeDef.DOING_STATE));
				fahaiccRoot = fahaiccRootRepository.save(fahaiccRoot);
	
				//爬虫获取和解析数据
				String keyword = fahaiccRoot.getKeyword();
				LOGGER.info("=================查询关键字===================: "+ keyword);
				Result<List<FahaiItemFeed>> result = fahaiccService.getDataOnce(null, null, URLEncoder.encode(keyword, "utf8"), "1", IS_DEBUG, null);
				LOGGER.info("===========================result is " + result.toString() + "=========================");
				if (result.getError() != null) {
					String errorCode = result.getError().getErrorCode();
					int errorCodeInt = Integer.parseInt(errorCode);
					fahaiccRoot.setState(errorCodeInt);
				}
				
				//改变执行次数属性
				Integer num = fahaiccRoot.getNum()==null ? 1 : (fahaiccRoot.getNum() + 1);
				fahaiccRoot.setNum(num);
				Integer totalNum = fahaiccRoot.getTotalNum()==null ? 1 : (fahaiccRoot.getTotalNum() + 1);
				fahaiccRoot.setTotalNum(totalNum);
				
				List<FahaiItemFeed> fahaiItemFeeds = result.getData();
				if (fahaiItemFeeds != null && !fahaiItemFeeds.isEmpty()) {
					Date executeTime = new Date();
					Set<FahaiccResult> fahaiccResults = new HashSet<FahaiccResult>();
					for (FahaiItemFeed fahaiItemFeed : fahaiItemFeeds) {
						FahaiccResult fahaiccResult = FahaiccPoVoTransfer.voToPo(fahaiItemFeed);
						fahaiccResult.setFahaiccRoot(fahaiccRoot);
						fahaiccResult.setExecuteTime(executeTime);
						fahaiccResults.add(fahaiccResult);
					}
					//改变状态属性
					fahaiccRoot.setState(Integer.valueOf(StatusCodeDef.GET_DATA_SCCCESS));
					//一对多属性设置
					fahaiccRoot.setFahaiccResults(fahaiccResults);
				}
				fahaiccRootRepository.save(fahaiccRoot);
				
				//返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", fahaiccRoot.getKeyword());
				resultMap.put("arguments", arguments);
				resultMap.put("cName", fahaiccRoot.getKeyword());
				resultMap.put("cId", fahaiccRoot.getId());
				resultMap.put("APIresult", result);
			}
		} catch (Exception e) {
			if (fahaiccRoot != null){
				fahaiccRoot.setState(Integer.parseInt(StatusCodeDef.WEB_SERVICE_EXCEPTION));
				fahaiccRootRepository.save(fahaiccRoot);
				
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", fahaiccRoot.getKeyword());
				resultMap.put("arguments", arguments);
				resultMap.put("cName", fahaiccRoot.getKeyword());
				resultMap.put("cId", fahaiccRoot.getId());
			}
			
	        StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            sw.append("-------------------"); 
            if (fahaiccRoot != null){
            	sw.append(fahaiccRoot.getKeyword()); 
            }
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush(); 
			resultMap.put("Exception", sw.toString());
		}
		
		return resultMap;
	}

}

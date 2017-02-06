package com.crawlermanage.service.iautos;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.iautos.domain.json.UserFeedJson;
import com.crawlermanage.service.transfer.IautosPoVoTransfer;
import com.module.dao.entity.iautos.Iautos;
import com.module.dao.entity.iautos.IautosKeyword;
import com.module.dao.repository.iautos.IautosKeywordRepository;
import com.module.dao.repository.iautos.IautosRepository;

@Service
public class IautosTaskService {
	private static final boolean IS_DEBUG = false;
	private static final Logger LOGGER = Logger.getLogger(IautosTaskService.class);
	
	@Autowired
	private IautosRepository iautosRepository;	
	@Autowired
	private IautosKeywordRepository iautosKeywordRepository;
	@Autowired
	private IautosSearchService iautosSearchService;

	@Transactional
	public Map<String, Object> excOneceTask() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		IautosKeyword  iautoKeyword = iautosKeywordRepository.findTop1ByStateInAndNumLessThanOrderByPriorityDesc(StatusCodeDef.NEED_QUERY_STATES, StatusCodeDef.UPPER_LIMIT_NUM);		
		if (iautoKeyword != null) {
			iautoKeyword.setState(Integer.parseInt(StatusCodeDef.DOING_STATE));
			iautoKeyword = iautosKeywordRepository.save(iautoKeyword);			
		
			Method m1 = ReflectionUtils.findMethod(IautosSearchService.class, "getDataOnce", String.class,String.class, boolean.class, String.class); 
			Object o1 = ReflectionUtils.invokeMethod(m1, iautosSearchService, iautoKeyword.getCity(),iautoKeyword.getName(), IS_DEBUG, ""); 			
			@SuppressWarnings("unchecked")	
			Result<List<UserFeedJson>> result1 =(Result<List<UserFeedJson>>)o1;	
			resultMap.put("APIresult", result1);
			List<UserFeedJson> iautosList = result1.getData();		
			Date executetime = new Date();
			if (iautosList!=null && !iautosList.isEmpty()){
				for (UserFeedJson userFeedJson : iautosList){
					Iautos iauto = IautosPoVoTransfer.voToPo(userFeedJson);
					if (null != iauto) {								
						iauto.setExecutetime(executetime);			
						iauto.setIautosKeyword(iautoKeyword);
						iautosRepository.save(iauto);										
						//返回输入参数
						Map<String, Object> arguments = new HashMap<String, Object>();
						arguments.put("keyword", iautoKeyword.getName());
						arguments.put("city", iautoKeyword.getCity());
						resultMap.put("arguments", arguments);
						resultMap.put("keyword", iautoKeyword.getName());
						resultMap.put("id", iautoKeyword.getId());						
					}
				}
				
				//爬取次数设置
				Integer num = iautoKeyword.getNum() == null ? 1 : (iautoKeyword.getNum() + 1);
				iautoKeyword.setNum(num);
				
				Integer totalNum = iautoKeyword.getTotalNum() == null ? 1 : (iautoKeyword.getTotalNum() + 1);
				iautoKeyword.setTotalNum(totalNum);
				
				iautoKeyword.setState(Integer.parseInt(StatusCodeDef.GET_DATA_SCCCESS));		
			    iautosKeywordRepository.save(iautoKeyword);
				LOGGER.info("================将车品牌状态由未查询变为已经查询成功 即将state由   0变为1==="
						+ iautoKeyword.getName()
						+ "=================="
						+ iautoKeyword.getCity()+"============"+iautoKeyword.getState());
			}else{
				//爬取次数设置
				Integer num = iautoKeyword.getNum() == null ? 1 : (iautoKeyword.getNum() + 1);
				iautoKeyword.setNum(num);
				
				Integer totalNum = iautoKeyword.getTotalNum() == null ? 1 : (iautoKeyword.getTotalNum() + 1);
				iautoKeyword.setTotalNum(totalNum);
				
				iautoKeyword.setState(Integer.parseInt(StatusCodeDef.NO_DATA_FOUND));		
			    iautosKeywordRepository.save(iautoKeyword);			    
			    //返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", iautoKeyword.getName());
				arguments.put("city", iautoKeyword.getCity());
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", iautoKeyword.getName());
				resultMap.put("id", iautoKeyword.getId());			
				LOGGER.info("================将车品牌状态由未查询变为未查询到该关键字 即将state由   0变为7==="
						+ iautoKeyword.getName()
						+ "=================="
						+ iautoKeyword.getCity()+"============"+iautoKeyword.getState());
			}
		
		}
		
		return resultMap;
	}
}

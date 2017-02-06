package com.crawlermanage.service.sipo;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
import com.crawler.sipo.domain.json.SearchEngineSipo;
import com.crawler.sipo.domain.json.TransactionDataSipo;
import com.crawlermanage.service.transfer.SipoPoVoTransfer;
import com.crawlermanage.service.transfer.TransactionDataPoVoTransfer;
import com.module.dao.entity.sipo.Sipo;
import com.module.dao.entity.sipo.SipoKeyword;
import com.module.dao.entity.sipo.TransactionData;
import com.module.dao.repository.sipo.SipoKeywordRepository;
import com.module.dao.repository.sipo.SipoRepository;
import com.module.dao.repository.sipo.TransactionDataRepository;

@Service
public class SipoTaskService {
	private static final boolean IS_DEBUG = false;

	private static final Logger LOGGER = Logger.getLogger(SipoTaskService.class);
	
	@Autowired
	private SipoKeywordRepository  sipoKeywordRepository;

	@Autowired
	private SipoRepository  sipoRepository;	
	
	@Autowired
	private TransactionDataRepository  transactionDataRepository;	
	
	@Autowired
	private SipoSearchService  sipoSearchService;
	
	@Transactional
	public Map<String, Object> excOneceTask() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		SipoKeyword sipoKeyword = sipoKeywordRepository.findTopByStateInAndNumLessThanOrderByPriorityDesc(StatusCodeDef.NEED_QUERY_STATES, StatusCodeDef.UPPER_LIMIT_NUM);
		
		if (sipoKeyword != null) {
			sipoKeyword.setState(Integer.parseInt(StatusCodeDef.DOING_STATE));
			sipoKeyword = sipoKeywordRepository.save(sipoKeyword);

			Method m1 = ReflectionUtils.findMethod(SipoSearchService.class,
					"getDataOnce", String.class,String.class, boolean.class, String.class); 
			Object o1 = ReflectionUtils.invokeMethod(m1, sipoSearchService,sipoKeyword.getType(), sipoKeyword.getName(), IS_DEBUG, ""); 
			
			@SuppressWarnings("unchecked")
			Result<List<SearchEngineSipo>> result1 =(Result<List<SearchEngineSipo>>)o1;
			resultMap.put("APIresult", result1);	
			List<SearchEngineSipo> siposList = result1.getData();
			Date executetime = new Date();
			if (siposList!=null && !siposList.isEmpty()) {				
				for (SearchEngineSipo searchEngineSipo:siposList) {
					Sipo sipo=SipoPoVoTransfer.voToPo(searchEngineSipo);
					sipo.setExecutetime(executetime);
					sipo.setSipoKeyword(sipoKeyword);
		
					//要防止懒加载异常
					List<TransactionData> transactionDatas = new ArrayList<TransactionData>();				
					List<TransactionDataSipo> tranList=searchEngineSipo.getTransactionDatas();
					if (tranList!=null && !tranList.isEmpty()) {
						for (int i = 0; i < tranList.size(); i++) {
							TransactionData transactionData=TransactionDataPoVoTransfer.voToPo(tranList.get(i));
							transactionData.setExecutetime(executetime);
							transactionData.setSipo(sipo);
							transactionDatas.add(transactionData);
						}												
					}
					
					sipo.setTransactionDatas(transactionDatas);					
					sipoRepository.save(sipo);
					
				}		
				
				//改变执行次数属性
				Integer num = sipoKeyword.getNum() == null ? 1 : (sipoKeyword.getNum() + 1);
				sipoKeyword.setNum(num);
				
				Integer totalNum = sipoKeyword.getTotalNum() == null ? 1 : (sipoKeyword.getTotalNum() + 1);
				sipoKeyword.setTotalNum(totalNum);
				
				sipoKeyword.setState(Integer.parseInt(StatusCodeDef.GET_DATA_SCCCESS));	
				sipoKeywordRepository.save(sipoKeyword);
				LOGGER.info("================将公司关键字查询状态由未查询变为已经查询成功 即将state由   0变为1==="
						+ sipoKeyword.getName()
						+ "========="
						+ sipoKeyword.getType()+"========="+sipoKeyword.getState());
						
				//返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", sipoKeyword.getName());
				arguments.put("type", sipoKeyword.getType());
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", sipoKeyword.getName());
				resultMap.put("id", sipoKeyword.getId());
			}else{
				//没查到关键字				
				//改变执行次数属性
				Integer num = sipoKeyword.getNum() == null ? 1 : (sipoKeyword.getNum() + 1);
				sipoKeyword.setNum(num);
				
				Integer totalNum = sipoKeyword.getTotalNum() == null ? 1 : (sipoKeyword.getTotalNum() + 1);
				sipoKeyword.setTotalNum(totalNum);
				
				sipoKeyword.setState(Integer.parseInt(StatusCodeDef.NO_DATA_FOUND));	
				sipoKeywordRepository.save(sipoKeyword);
				LOGGER.info("================将公司关键字查询状态由未查询变为没有查询到关键字 即将state由   0变为7==="
						+ sipoKeyword.getName()
						+ "========="
						+ sipoKeyword.getType()+"========="+sipoKeyword.getState());
						
				//返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", sipoKeyword.getName());
				arguments.put("type", sipoKeyword.getType());
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", sipoKeyword.getName());
				resultMap.put("id", sipoKeyword.getId());			
			}
	
			
		}
		
		return resultMap;
	}
}

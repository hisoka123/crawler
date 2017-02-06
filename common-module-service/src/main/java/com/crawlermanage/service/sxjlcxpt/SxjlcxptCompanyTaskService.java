package com.crawlermanage.service.sxjlcxpt;

import java.lang.reflect.Method;
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
import org.springframework.util.ReflectionUtils;

import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawler.sxjlcxpt.domain.json.CreditrRecordQueryPlatformJson;
import com.crawlermanage.service.transfer.CrqpPoVoTransfer;
import com.crawlermanage.service.transfer.IecmsPoVoTransfer;
import com.google.gson.GsonBuilder;
import com.module.dao.entity.crqp.CreditrRecordQueryPlatform;
import com.module.dao.entity.crqp.CreditrRecordQueryPlatformCompany;
import com.module.dao.entity.iecms.Iecms;
import com.module.dao.repository.crqp.CreditrRecordQueryPlatformCompanyRepository;
import com.module.dao.repository.crqp.CreditrRecordQueryPlatformRepository;

@Service
public class SxjlcxptCompanyTaskService {
	
	private static final boolean IS_DEBUG = true;
	private static final Logger Log = Logger.getLogger(SxjlcxptCompanyTaskService.class);
	
	@Autowired
	private CreditrRecordQueryPlatformCompanyRepository creditrRecordQueryPlatformCompanyRepository;
	@Autowired
	private SxjlcxptUserInfoService sxjlcxptUserInfoService;
    
	@Autowired
	private CreditrRecordQueryPlatformRepository creditrRecordQueryPlatformRepository;
	
	@Transactional
	public Map<String, Object> excOneceTask() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		CreditrRecordQueryPlatformCompany creditrRecordQueryPlatformCompany = creditrRecordQueryPlatformCompanyRepository.findTopByStateInAndNumLessThanOrderByPriorityDesc( StatusCodeDef.NEED_QUERY_STATES,StatusCodeDef.UPPER_LIMIT_NUM);
//		CreditrRecordQueryPlatformCompany creditrRecordQueryPlatformCompany = creditrRecordQueryPlatformCompanyRepository.findTopByKeywordAndStateInAndNumLessThanOrderByPriorityDesc(keyword, StatusCodeDef.NEED_QUERY_STATES,StatusCodeDef.UPPER_LIMIT_NUM);
//		
		if (creditrRecordQueryPlatformCompany != null) {
			creditrRecordQueryPlatformCompany.setState(Integer.valueOf(StatusCodeDef.DOING_STATE));
			creditrRecordQueryPlatformCompany = creditrRecordQueryPlatformCompanyRepository.save(creditrRecordQueryPlatformCompany);

			Method m1 = ReflectionUtils.findMethod(SxjlcxptUserInfoService.class,"getDataOnce", String.class,boolean.class, String.class); 
			Object o1 = ReflectionUtils.invokeMethod(m1, sxjlcxptUserInfoService, creditrRecordQueryPlatformCompany.getKeyword(),IS_DEBUG, ""); 
			@SuppressWarnings("unchecked")
			Result<List<CreditrRecordQueryPlatformJson>> result1 =(Result<List<CreditrRecordQueryPlatformJson>>)o1;
			
			List<CreditrRecordQueryPlatformJson> CreditrRecordQueryPlatformJsons = result1.getData();
			
			//改变执行次数属性
			Integer num = creditrRecordQueryPlatformCompany.getNum() == null ? 1 : (creditrRecordQueryPlatformCompany.getNum() + 1);
			creditrRecordQueryPlatformCompany.setNum(num);	
			Integer totalNum = creditrRecordQueryPlatformCompany.getTotalNum() == null ? 1 : (creditrRecordQueryPlatformCompany.getTotalNum() + 1);
			creditrRecordQueryPlatformCompany.setTotalNum(totalNum);	
			if (CreditrRecordQueryPlatformJsons != null) {
				//改变状态属性
				creditrRecordQueryPlatformCompany.setState(Integer.valueOf(StatusCodeDef.GET_DATA_SCCCESS));
				
				Date executetime = new Date();
				Set<CreditrRecordQueryPlatform> crqps = new HashSet<CreditrRecordQueryPlatform>();
				for (CreditrRecordQueryPlatformJson creditrRecordQueryPlatformJson : CreditrRecordQueryPlatformJsons) {
					CreditrRecordQueryPlatform crqp = CrqpPoVoTransfer.voToPo(creditrRecordQueryPlatformJson);
					Log.info("iecrqpcms:" + new GsonBuilder().setPrettyPrinting().create().toJson(crqp));
					crqp.setCreditrrecordqueryplatformcompany(creditrRecordQueryPlatformCompany);
					crqp.setExecutetime(executetime);
					crqps.add(crqp);
				}
				
//				CreditrRecordQueryPlatformJson mycrqpjJson=CreditrRecordQueryPlatformJsons.get(0);
//				CreditrRecordQueryPlatform crqp = CrqpPoVoTransfer.voToPo(mycrqpjJson);
				
				//一对多属性设置
//				Set<CreditrRecordQueryPlatform> crqps = new HashSet<CreditrRecordQueryPlatform>();
//				crqps.add(crqp);
//				crqp.setCreditrrecordqueryplatformcompany(creditrRecordQueryPlatformCompany);
				creditrRecordQueryPlatformCompany.setCreditrrecordqueryplatforms(crqps);
				
			} else {
				String errorCode = result1.getError().getErrorCode();
				int errorCodeInt = Integer.parseInt(errorCode);
				creditrRecordQueryPlatformCompany.setState(errorCodeInt);
			}
			
			creditrRecordQueryPlatformCompanyRepository.save(creditrRecordQueryPlatformCompany);
			Log.info("==========================================================result1 is "
					+ result1.toString()
					+ "=======================================================");
			
			resultMap.put("APIresult", result1);
			
			//返回输入参数
			Map<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("keyword", creditrRecordQueryPlatformCompany.getKeyword());
			resultMap.put("arguments", arguments);
			resultMap.put("keyword", creditrRecordQueryPlatformCompany.getKeyword());
			resultMap.put("cId", creditrRecordQueryPlatformCompany.getId());
		}
		
		return resultMap;
	}
}

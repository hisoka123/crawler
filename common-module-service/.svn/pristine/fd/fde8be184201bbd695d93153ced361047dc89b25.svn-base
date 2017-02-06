package com.crawlermanage.service.iecms;

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

import com.crawler.dailianmeng.domain.json.LoanUnionFeedJson;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawlermanage.service.transfer.IecmsPoVoTransfer;
import com.crawlermanage.service.transfer.LoanUnionPoVoTransfer;
import com.google.gson.GsonBuilder;
import com.module.dao.entity.dailianmeng.LoanUnion;
import com.module.dao.entity.iecms.Iecms;
import com.module.dao.entity.iecms.IecmsCompany;
import com.module.dao.repository.iecms.IecmsCompanyRepository;
import com.module.dao.repository.iecms.IecmsRepository;

@Service
public class IecmsCompanyTaskService {
	
	private static final boolean IS_DEBUG = true;
	private static final Logger Log = Logger.getLogger(IecmsCompanyTaskService.class);
	
	@Autowired
	private IecmsCompanyRepository iecmsCompanyRepository;
	@Autowired
	private IecmsService iecmsService;
    
	@Autowired
	private IecmsRepository iecmsRepository;
	
	@Transactional
	public Map<String, Object> excOneceTask(String type) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		IecmsCompany iecmsCompany = iecmsCompanyRepository.findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(type, StatusCodeDef.NEED_QUERY_STATES,StatusCodeDef.UPPER_LIMIT_NUM);
		
		if (iecmsCompany != null) {
			iecmsCompany.setState(Integer.valueOf(StatusCodeDef.DOING_STATE));
			iecmsCompany = iecmsCompanyRepository.save(iecmsCompany);

			Method m1 = ReflectionUtils.findMethod(IecmsService.class,"getDataOnce", String.class,String.class,boolean.class, String.class); 
			Object o1 = ReflectionUtils.invokeMethod(m1, iecmsService, iecmsCompany.getKeyword(),iecmsCompany.getType(),IS_DEBUG, ""); 
			@SuppressWarnings("unchecked")
			Result<List<IecmsJson>> result1 =(Result<List<IecmsJson>>)o1;
			
			List<IecmsJson> iecmsJsons = result1.getData();
			
			//改变执行次数属性
			Integer num = iecmsCompany.getNum() == null ? 1 : (iecmsCompany.getNum() + 1);
			iecmsCompany.setNum(num);
			Integer totalNum = iecmsCompany.getTotalNum() == null ? 1 : (iecmsCompany.getTotalNum() + 1);
			iecmsCompany.setTotalNum(totalNum);	
			if (iecmsJsons != null) {
				//改变状态属性
				iecmsCompany.setState(Integer.valueOf(StatusCodeDef.GET_DATA_SCCCESS));
				Date executetime = new Date();
				Set<Iecms> iecmsJsonss = new HashSet<Iecms>();
				for (IecmsJson iecmsJson : iecmsJsons) {
					Iecms iecms = IecmsPoVoTransfer.voToPo(iecmsJson);
					Log.info("iecms:" + new GsonBuilder().setPrettyPrinting().create().toJson(iecms));
					iecms.setIecmscompany(iecmsCompany);
					iecms.setExecutetime(executetime);
					iecmsJsonss.add(iecms);
				}
				
//				IecmsJson myIecmsJson=iecmsJsons.get(0);
//				Iecms iecms = IecmsPoVoTransfer.voToPo(myIecmsJson);
				
				//一对多属性设置
//				Set<Iecms> iecmss = new HashSet<Iecms>();
//				iecmss.add(iecms);
//				iecms.setIecmscompany(iecmsCiompany);
				iecmsCompany.setIecms(iecmsJsonss);
				
			} else {
				String errorCode = result1.getError().getErrorCode();
				int errorCodeInt = Integer.parseInt(errorCode);
				iecmsCompany.setState(errorCodeInt);
			}
			
			iecmsCompanyRepository.save(iecmsCompany);
			Log.info("==========================================================result1 is "
					+ result1.toString()
					+ "=======================================================");
			
			resultMap.put("APIresult", result1);
			
			//返回输入参数
			Map<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("keyword", iecmsCompany.getKeyword());
			resultMap.put("arguments", arguments);
			resultMap.put("keyword", iecmsCompany.getKeyword());
			resultMap.put("cId", iecmsCompany.getId());
		}
		
		return resultMap;
	}
}

package com.crawlermanage.service.dailianmeng;

import java.io.PrintWriter;
import java.io.StringWriter;
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
import com.crawlermanage.service.transfer.LoanUnionPoVoTransfer;
import com.google.gson.GsonBuilder;
import com.module.dao.entity.dailianmeng.CompanyOrIdentity;
import com.module.dao.entity.dailianmeng.LoanUnion;
import com.module.dao.repository.dailianmeng.CompanyOrIdentityRepository;
import com.module.dao.repository.dailianmeng.LoanUnionRepository;

@Service
public class CompanyOrIdentityTaskService {
	
	private static final boolean IS_DEBUG = true;
	private static final Logger Log = Logger.getLogger(CompanyOrIdentityTaskService.class);
	
	@Autowired
	private CompanyOrIdentityRepository companyOrIdentityRepository;
	@Autowired
	private DaiLianMengUserInfoService dailianmengService;
	@Autowired
	private LoanUnionRepository loanUnionRepository;
	
	@Transactional
	public Map<String, Object> excOneceTask(String type) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CompanyOrIdentity companyOrIdentity = null;
		
		try {
			Log.info("-----------------------------------excute dailianmeng excOnceTask---------------------------------");
			companyOrIdentity = companyOrIdentityRepository.findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(type, StatusCodeDef.NEED_QUERY_STATES, StatusCodeDef.UPPER_LIMIT_NUM);
			
			if (companyOrIdentity != null) {
				companyOrIdentity.setState(Integer.valueOf(StatusCodeDef.DOING_STATE));
				companyOrIdentity = companyOrIdentityRepository.save(companyOrIdentity);
				
				String keyword = companyOrIdentity.getKeyword();
				Log.info("=================查询关键字===================: "+ keyword);
				
				Method m1 = ReflectionUtils.findMethod(DaiLianMengUserInfoService.class,
						"getDataOnce", String.class, boolean.class, String.class); 
				Object o1 = ReflectionUtils.invokeMethod(m1, dailianmengService, companyOrIdentity.getKeyword(), IS_DEBUG, ""); 
				@SuppressWarnings("unchecked")
				Result<List<LoanUnionFeedJson>> result1 =(Result<List<LoanUnionFeedJson>>)o1;
				Log.info("===========================result is " + result1.toString() + "=========================");
				
				List<LoanUnionFeedJson> loanUnionFeedJsons = result1.getData();
				
				//改变执行次数属性
				Integer num = companyOrIdentity.getNum() == null ? 1 : (companyOrIdentity.getNum() + 1);
				companyOrIdentity.setNum(num);
				Integer totalNum = companyOrIdentity.getTotalNum() == null ? 1 : (companyOrIdentity.getTotalNum() + 1);
				companyOrIdentity.setTotalNum(totalNum);
				
				if (result1.getError() != null) {
					String errorCode = result1.getError().getErrorCode();
					int errorCodeInt = Integer.parseInt(errorCode);
					companyOrIdentity.setState(errorCodeInt);
				}
				
				if (loanUnionFeedJsons != null) {
					Date executetime = new Date();
					Set<LoanUnion> loanUnions = new HashSet<LoanUnion>();
					for (LoanUnionFeedJson loanUnionFeedJson : loanUnionFeedJsons) {
						LoanUnion loanUnion = LoanUnionPoVoTransfer.voToPo(loanUnionFeedJson);
						loanUnion.setCompanyOrIdentity(companyOrIdentity);
						loanUnion.setExecutetime(executetime);
						loanUnions.add(loanUnion);
					}
					//改变状态属性
					companyOrIdentity.setState(Integer.valueOf(StatusCodeDef.GET_DATA_SCCCESS));
					//一对多属性设置
					companyOrIdentity.setLoanUnions(loanUnions);
				}
				companyOrIdentityRepository.save(companyOrIdentity);
				
				//返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", companyOrIdentity.getKeyword());
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", companyOrIdentity.getKeyword());
				resultMap.put("cId", companyOrIdentity.getId());
				resultMap.put("APIresult", result1);
			}
		} catch (Exception e) {
			if (companyOrIdentity != null){
				companyOrIdentity.setState(Integer.parseInt(StatusCodeDef.WEB_SERVICE_EXCEPTION));
				companyOrIdentityRepository.save(companyOrIdentity);
				
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", companyOrIdentity.getKeyword());
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", companyOrIdentity.getKeyword());
				resultMap.put("cId", companyOrIdentity.getId());
			}
			
			StringWriter sw = new StringWriter();   
			PrintWriter pw = new PrintWriter(sw, true);   
			//Gson gson = new Gson();
			sw.append("-------------------"); 
			sw.append(companyOrIdentity.getKeyword()); 
			
			e.printStackTrace(pw);   
			pw.flush();   
			sw.flush(); 
			
			resultMap.put("Exception", sw.toString());
			
		}
		
		return resultMap;
	}
}

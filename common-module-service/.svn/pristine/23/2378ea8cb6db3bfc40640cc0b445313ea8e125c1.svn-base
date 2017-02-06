package com.crawlermanage.service.renfawang;

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

import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.renfawang.domain.json.PeopleCourtFeedJson;
import com.crawlermanage.service.transfer.PeopleCourtPoVoTransfer;
import com.module.dao.entity.renfawang.CompanyOrID;
import com.module.dao.entity.renfawang.PeopleCourt;
import com.module.dao.repository.renfawang.CompanyOrIDRepository;

@Service
public class CompanyOrIDTaskService {
	
	private static final boolean IS_DEBUG = true;
	private static final Logger Log = Logger.getLogger(CompanyOrIDTaskService.class);
	
	@Autowired
	private CompanyOrIDRepository companyOrIDRepository;
	@Autowired
	private RenFaWangService rfwService;
	
	@Transactional
	public Map<String, Object> excOneceTask(String type) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CompanyOrID companyOrID = null;
		
		try {
			companyOrID = companyOrIDRepository.findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(type, StatusCodeDef.NEED_QUERY_STATES, StatusCodeDef.UPPER_LIMIT_NUM);
			
			if (companyOrID != null) {
				companyOrID.setState(Integer.valueOf(StatusCodeDef.DOING_STATE));
				companyOrID = companyOrIDRepository.save(companyOrID);
				
				String keyword = companyOrID.getKeyword();
				Log.info("=================查询关键字===================: "+ keyword);
				Method m1 = ReflectionUtils.findMethod(RenFaWangService.class,
						"getDataOnce", String.class, String.class, boolean.class, String.class); 
				Object o1 = ReflectionUtils.invokeMethod(m1, rfwService, companyOrID.getKeyword(), type, IS_DEBUG, ""); 
				@SuppressWarnings("unchecked")
				Result<List<PeopleCourtFeedJson>> result1 =(Result<List<PeopleCourtFeedJson>>)o1;
				Log.info("===========================result is " + result1.toString() + "=========================");
				List<PeopleCourtFeedJson> peopleCourtFeedJsons = result1.getData();
				
				//改变执行次数属性
				Integer num = companyOrID.getNum() == null ? 1 : (companyOrID.getNum() + 1);
				companyOrID.setNum(num);
				Integer totalNum = companyOrID.getTotalNum() == null ? 1 : (companyOrID.getTotalNum() + 1);
				companyOrID.setTotalNum(totalNum);
				
				if (result1.getError() != null) {
					String errorCode = result1.getError().getErrorCode();
					int errorCodeInt = Integer.parseInt(errorCode);
					companyOrID.setState(errorCodeInt);
				}
				
				if (peopleCourtFeedJsons != null) {
					Date executetime = new Date();
					Set<PeopleCourt> peopleCourts = new HashSet<PeopleCourt>();
					for (PeopleCourtFeedJson peopleCourtFeedJson : peopleCourtFeedJsons) {
						PeopleCourt peopleCourt = PeopleCourtPoVoTransfer.voToPo(peopleCourtFeedJson);
						peopleCourt.setCompanyOrIdentity(companyOrID);
						peopleCourt.setExecutetime(executetime);
						peopleCourts.add(peopleCourt);
					}
					//改变状态属性
					companyOrID.setState(Integer.valueOf(StatusCodeDef.GET_DATA_SCCCESS));
					//一对多属性设置
					companyOrID.setPeopleCourts(peopleCourts);
				}
				companyOrIDRepository.save(companyOrID);
				
				//返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", companyOrID.getKeyword());
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", companyOrID.getKeyword());
				resultMap.put("cId", companyOrID.getId());
				resultMap.put("APIresult", result1);
			}
			
		} catch (Exception e) {
			if (companyOrID != null){
				companyOrID.setState(Integer.parseInt(StatusCodeDef.WEB_SERVICE_EXCEPTION));
				companyOrIDRepository.save(companyOrID);
				
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", companyOrID.getKeyword());
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", companyOrID.getKeyword());
				resultMap.put("cId", companyOrID.getId());
			}
			
			StringWriter sw = new StringWriter();   
			PrintWriter pw = new PrintWriter(sw, true);   
			//Gson gson = new Gson();
			sw.append("-------------------"); 
			sw.append(companyOrID.getKeyword()); 
			
			e.printStackTrace(pw);   
			pw.flush();   
			sw.flush(); 
			
			resultMap.put("Exception", sw.toString());
		} 
		
		return resultMap;
	}
}

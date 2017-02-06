package com.crawlermanage.service.gsxt;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.gsxt.domain.json.AicFeedJson;
import com.crawler.htmlparser.GsonFactory;
import com.crawlermanage.service.transfer.GsxtPoVoTransfer;
import com.module.dao.entity.gsxt.Company;
import com.module.dao.entity.gsxt.TQyjbxx;
import com.module.dao.entity.gsxt.TResultJson;
import com.module.dao.repository.gsxt.CompanyRepository;
import com.module.dao.repository.gsxt.ResultJsonRepository;

@Service
public class CompanyTaskService {
	@Autowired
    private CompanyRepository companyRepository;
	
	@Autowired
    private ResultJsonRepository resultJsonRepository;
	
	@Autowired
	private GsxtService gsxtService;	
	
	private static final boolean IS_DEBUG = false;
	private static final Logger Log = Logger.getLogger(CompanyTaskService.class);
	
	
	@Transactional
	public Map<String, Object> excOneceTask(String city) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Company company = null;
		try {
			company = companyRepository.findTopByCityAndStateInAndNumLessThanOrderByIdAscPriorityDesc(city,StatusCodeDef.NEED_QUERY_STATES, StatusCodeDef.UPPER_LIMIT_NUM);
			if (company != null) {
				company.setState(Integer.parseInt(StatusCodeDef.DOING_STATE));
				company = companyRepository.save(company);

				Date executetime = new Date();
				Method m1 = ReflectionUtils.findMethod(GsxtService.class, "getDataOf" + city + "Once", String.class, boolean.class, String.class); ///////
				Object o1 = ReflectionUtils.invokeMethod(m1, gsxtService, company.getName(), IS_DEBUG, ""); ///////
				@SuppressWarnings("unchecked")
				Result<AicFeedJson> result1 =(Result<AicFeedJson>)o1;
				
				
				AicFeedJson gsxtFeedJson = result1.getData();
				String resultJsonStr = GsonFactory.getGson().toJson(gsxtFeedJson);
				
				TQyjbxx qyjbxx = GsxtPoVoTransfer.voToPo(gsxtFeedJson);
				TResultJson resultJson = new TResultJson(company.getName(), resultJsonStr, executetime);
				if (qyjbxx!=null) {
					qyjbxx.setExecutetime(executetime);
					
					qyjbxx.setCompany(company);
					resultJson.setCompany(company);
					
					Set<TQyjbxx> tQyjbxxs = company.getTQyjbxxs();
					if (tQyjbxxs==null) {
						tQyjbxxs = new HashSet<TQyjbxx>();
					}
					tQyjbxxs.add(qyjbxx);
					
					//要防止懒加载异常
					Set<TResultJson> tResultJsons = resultJsonRepository.findByCompanyId(company.getId());
					if (tResultJsons==null) {
						tResultJsons = new HashSet<TResultJson>();
					}
					tResultJsons.add(resultJson);
					
					company.setTQyjbxxs(tQyjbxxs);
					company.setTResultJsons(tResultJsons);
				}
				
				//返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("company", company.getName());
				resultMap.put("arguments", arguments);
				resultMap.put("cName", company.getName());
				resultMap.put("cId", company.getId());
				Log.info("================将公司状态由为入库变为入库 即将state由   0变为1==="
						+ company.getName()
						+ "========================================="
						+ company.getState());

				if (result1.getData() != null) {
					company.setState(Integer.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
				} else {
					String errorCode = result1.getError().getErrorCode();
					int errorCodeInt = Integer.parseInt(errorCode);
					company.setState(errorCodeInt);
				}
				
				//num totalNum
				Integer num = company.getNum() == null ? 1 : (company.getNum() + 1);
				company.setNum(num);
				Integer totalNum = company.getTotalNum() == null ? 1 : (company.getTotalNum() + 1);
				company.setTotalNum(totalNum);
				
				//保存
				companyRepository.save(company);
				
				Log.info("==========================================================result1 is "
						+ result1.toString()
						+ "=======================================================");
				resultMap.put("APIresult", result1);
			}
		} catch (Exception e) {
			
			if (company != null){
				company.setState(Integer.parseInt(StatusCodeDef.WEB_SERVICE_EXCEPTION));
				companyRepository.save(company);
			}
			
			Map<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("company", company.getName());
			resultMap.put("arguments", arguments);
			resultMap.put("cName", company.getName());
			resultMap.put("cId", company.getId());
			
	        StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            //Gson gson = new Gson();
            sw.append("-------------------"); 
            sw.append(company.getName()); 
            
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush(); 
	            
			resultMap.put("Exception", sw.toString());
			//return resultMap;
		}
		
		Log.info("===================resultMap is "+resultMap.toString()+"====================");
		return resultMap;
	}
}

package com.module.timetask;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.dailianmeng.CompanyOrIdentityTaskService;

@Component("excLoanUnion")
public class ExcLoanUnion {

	@Autowired
	private CompanyOrIdentityTaskService companyOrIdentityTaskService;
	
	public Map<String, Object> excOnece(String jobName, String jobGroup,String type) throws Exception {
		
		Map<String, Object> resultMap = companyOrIdentityTaskService.excOneceTask(type);
		
		return resultMap;
	}
}

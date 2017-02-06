package com.module.timetask;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.renfawang.CompanyOrIDTaskService;

@Component("excPeopleCourt")
public class ExcPeopleCourt {

	@Autowired
	private CompanyOrIDTaskService companyOrIDTaskService;
	
	public Map<String, Object> excOnece(String jobName, String jobGroup,String type) throws Exception {
		
		Map<String, Object> resultMap = companyOrIDTaskService.excOneceTask(type);
		
		return resultMap;
	}
}

package com.module.timetask;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.iecms.IecmsCompanyTaskService;

@Component("excIecms")
public class ExcIecms {	
	@Autowired
	private IecmsCompanyTaskService iecmsCompanyTaskService;
	
	public Map<String, Object> excOnece(String jobName, String jobGroup,String  type) throws Exception {
		return iecmsCompanyTaskService.excOneceTask(type);
	}
}

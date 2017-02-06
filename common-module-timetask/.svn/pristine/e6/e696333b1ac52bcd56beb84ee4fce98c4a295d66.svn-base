package com.module.timetask;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawlermanage.service.gsxt.CompanyTaskService;
import com.crawlermanage.service.gsxt.GsxtService;
import com.module.dao.repository.gsxt.CompanyRepository;
import com.module.dao.repository.gsxt.TQyjbxxRepository;

@Component("excOneceCor")
public class ExcOneceCor {
	
	@Autowired
	CompanyRepository companyRepository ;
	@Autowired
	private TQyjbxxRepository qyjbxxRepository;	
	@Autowired
	private GsxtService gsxtService;
	
	@Autowired
	private CompanyTaskService companyTaskService;
	
	public Map<String, Object> excOnece(String jobName, String jobGroup,String  city) throws Exception {
		return companyTaskService.excOneceTask(city);
	}
}

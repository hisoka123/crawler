package com.module.timetask;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawlermanage.service.sxjlcxpt.SxjlcxptCompanyTaskService;

@Component("excSxjlcxpt")
public class ExcSxjlcxpt {	
	@Autowired
	private SxjlcxptCompanyTaskService sxjlcxptCompanyTaskService;
	
	public Map<String, Object> excOnece(String jobName, String jobGroup,String  keyword) throws Exception {
		return sxjlcxptCompanyTaskService.excOneceTask();
	}
}

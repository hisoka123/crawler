package com.module.timetask;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawlermanage.service.fahaicc.FahaiccRootTaskService;

@Component("excFahaicc")
public class ExcFahaicc {

	@Autowired
	private FahaiccRootTaskService fahaiccRootTaskService;
	
	public Map<String, Object> excOnece(String jobName, String jobGroup, String type) throws Exception {
		
		Map<String, Object> resultMap = fahaiccRootTaskService.excOneceTask(type);
		
		return resultMap;
	}
}

package com.module.timetask;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.shixin.ShixinTaskService;

@Component("excShixin")
public class ExcShixin {
	@Autowired
	private ShixinTaskService shixinTaskService;
	
	public Map<String, Object> excOnece(String jobName, String jobGroup,String  type) throws Exception {
		return shixinTaskService.excOneceTask();
	}
}

package com.module.timetask;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.zjsfgkw.ZjsfgkwTaskService;

@Component("excZjsfgkw")
public class ExcZjsfgkw {
	@Autowired
	private ZjsfgkwTaskService zjsfgkwTaskService;
	
	public Map<String, Object> excOnece(String value, String jobGroup,String  type) throws Exception {
		return zjsfgkwTaskService.excOneceTask();
	}
}

package com.module.timetask;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.qiyezhengxin.ZhengxinTaskService;

@Component("excZhengxin")
public class ExcZhengxin {
	@Autowired
	private ZhengxinTaskService zhengxinTaskService;
	
	public Map<String, Object> excOnece(String jobCompany, String jobGroup,String  type) throws Exception {
		return zhengxinTaskService.excOneceTask();
	}
}

package com.module.timetask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.cnca.CncaTaskService;

@Component("cncaCor")
public class CncaCor {

	private static final Logger LOGGER = LoggerFactory.getLogger(CncaCor.class);

	@Autowired
	private CncaTaskService cncaTaskService;

	public Map<String, Object> excOnece(String jobName, String jobGroup)
			throws Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		LOGGER.info("the CncaTask is start!====="
				+ simpleDateFormat.format(new Date()));
		Map<String, Object> resultMap = cncaTaskService.cncaTask();
		LOGGER.info("the CncaTask is end!====="
				+ simpleDateFormat.format(new Date()));

		return resultMap;

	}

}

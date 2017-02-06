package com.module.timetask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.customs.CustomsTaskService;

@Component("customsCor")
public class CustomsCor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomsCor.class);

	@Autowired
	private CustomsTaskService customsTaskService;

	public Map<String, Object> excOnece(String jobName, String jobGroup)
			throws Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		LOGGER.info("the CustomsTask is start!====="
				+ simpleDateFormat.format(new Date()));
		Map<String, Object> resultMap = customsTaskService.customsTask();
		LOGGER.info("the CustomsTask is end!====="
				+ simpleDateFormat.format(new Date()));

		return resultMap;

	}

}

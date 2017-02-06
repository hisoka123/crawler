package com.module.timetask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.creditchina.CreditchinaTaskService;

@Component("creditchinaCor")
public class CreditchinaCor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CreditchinaCor.class);

	@Autowired
	private CreditchinaTaskService creditchinaTaskService;

	public Map<String, Object> excOnece(String jobName, String jobGroup)
			throws Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		LOGGER.info("the CreditchinaTask is start!====="
				+ simpleDateFormat.format(new Date()));
		Map<String, Object> resultMap = creditchinaTaskService
				.creditchinaTask();
		LOGGER.info("the CreditchinaTask is end!====="
				+ simpleDateFormat.format(new Date()));

		return resultMap;

	}

}

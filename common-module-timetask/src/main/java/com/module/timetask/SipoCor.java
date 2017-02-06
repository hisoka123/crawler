package com.module.timetask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawlermanage.service.sipo.SipoTaskService;

@Component("SipoCor")
public class SipoCor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SipoCor.class);

	@Autowired
	private SipoTaskService sipoTaskService;

	public Map<String, Object> excOnece(String jobName, String jobGroup)
			throws Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		LOGGER.info("the SipoTask is start!====="
				+ simpleDateFormat.format(new Date()));
		Map<String, Object> resultMap = sipoTaskService.excOneceTask();
		LOGGER.info("the SipoTask is end!====="
				+ simpleDateFormat.format(new Date()));

		return resultMap;

	}

}

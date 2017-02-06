package com.module.timetask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawlermanage.service.iautos.IautosTaskService;

@Component("IautosCor")
public class IautosCor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IautosCor.class);

	@Autowired
	private IautosTaskService iautosTaskService;

	public Map<String, Object> excOnece(String jobName, String jobGroup)
			throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LOGGER.info("the IautosTask is start!====="+ simpleDateFormat.format(new Date()));		
		Map<String, Object> resultMap = iautosTaskService.excOneceTask();		
		LOGGER.info("the IautosTask is end!====="+ simpleDateFormat.format(new Date()));
		return resultMap;
	}

}

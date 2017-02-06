package com.module.timetask.quartz.jobbean;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.module.timetask.CustomsCor;

public class CustomsCorJob extends QuartzJobBean {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomsCorJob.class);

	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		CustomsCor customsCor = getApplicationContext(jobexecutioncontext)
				.getBean("customsCor", CustomsCor.class);
		String jobGroup = jobexecutioncontext.getJobDetail().getKey()
				.getGroup();
		String jobName = jobexecutioncontext.getJobDetail().getKey().getName();
		try {
			customsCor.excOnece(jobName, jobGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ApplicationContext getApplicationContext(
			final JobExecutionContext jobexecutioncontext) {
		try {
			return (ApplicationContext) jobexecutioncontext.getScheduler()
					.getContext().get("applicationContextKey");
		} catch (SchedulerException e) {
			LOGGER.error(
					"jobexecutioncontext.getScheduler().getContext() error!", e);
			throw new RuntimeException(e);
		}
	}

}

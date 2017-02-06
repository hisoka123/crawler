package com.module.timetask.quartz.jobbean;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.module.timetask.ExcZjsfgkw;

public class ExcZjsfgkwJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(ExcZjsfgkwJob.class);
	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		ExcZjsfgkw excZjsfgkw = getApplicationContext(jobexecutioncontext).getBean("excZjsfgkw", ExcZjsfgkw.class);
		String jobGroup = jobexecutioncontext.getJobDetail().getKey().getGroup();
		String value = jobexecutioncontext.getJobDetail().getKey().getName();
		String type = jobexecutioncontext.getJobDetail().getDescription().trim();
		try {
			logger.debug("ExcZjsfgkwJob参数value="+value+";jobGroup="+jobGroup+";type="+type);
			excZjsfgkw.excOnece(value, jobGroup, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ApplicationContext getApplicationContext(final JobExecutionContext jobexecutioncontext) {
		try {
			return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContextKey");
		} catch (SchedulerException e) {
			logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
			throw new RuntimeException(e);
		}
	}

}

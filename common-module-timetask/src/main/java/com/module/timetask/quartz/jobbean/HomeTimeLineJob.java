package com.module.timetask.quartz.jobbean;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.module.timetask.HomeTimeLineCrawler;


public class HomeTimeLineJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(HomeTimeLineJob.class);
	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		HomeTimeLineCrawler homeTimeLineCrawler = getApplicationContext(jobexecutioncontext).getBean("homeTimeLineCrawler", HomeTimeLineCrawler.class);
		homeTimeLineCrawler.saveHomeTimeLine();
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

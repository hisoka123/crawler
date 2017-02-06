package com.module.timetask.quartz.jobbean;

import java.io.IOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.module.timetask.CrawlerAPIMonitor;

public class CrawlerAPIMonitorJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(CrawlerAPIMonitorJob.class);
	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		CrawlerAPIMonitor crawlerAPIMonitor = getApplicationContext(jobexecutioncontext).getBean("crawlerAPIMonitor", CrawlerAPIMonitor.class);
		String jobGroup = jobexecutioncontext.getJobDetail().getKey().getGroup();
		String jobName = jobexecutioncontext.getJobDetail().getKey().getName();
		String url = jobexecutioncontext.getJobDetail().getDescription().trim();
		try {
			crawlerAPIMonitor.monitor( jobName,  jobGroup, url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

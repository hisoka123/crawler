package com.module.timetask.quartz.jobbean;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.module.timetask.ExcSxjlcxpt;

public class SxjlcxptJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(SxjlcxptJob.class);
	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		ExcSxjlcxpt excSxjlcxpt = getApplicationContext(jobexecutioncontext).getBean("excSxjlcxpt", ExcSxjlcxpt.class);
		String jobGroup = jobexecutioncontext.getJobDetail().getKey().getGroup();
		String jobName = jobexecutioncontext.getJobDetail().getKey().getName();
		String keyword = jobexecutioncontext.getJobDetail().getDescription().trim();
		try {
			excSxjlcxpt.excOnece(jobName, jobGroup, keyword);
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

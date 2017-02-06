package com.module.timetask.quartz.jobbean;

import org.apache.thrift7.TException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import backtype.storm.generated.DRPCExecutionException;

import com.module.timetask.SinaEmergencyStore;

public class SinaEmergencyJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(SinaEmergencyJob.class);
	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		SinaEmergencyStore sinaBurstoutStore = getApplicationContext(jobexecutioncontext).getBean("sinaEmergencyStore", SinaEmergencyStore.class);
		String jobGroup = jobexecutioncontext.getJobDetail().getKey().getGroup();
		String jobName = jobexecutioncontext.getJobDetail().getKey().getName();
		try {
			sinaBurstoutStore.saveSinaBurstout(jobName,jobGroup);
		} catch (TException e) {
			e.printStackTrace();
		} catch (DRPCExecutionException e) {
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

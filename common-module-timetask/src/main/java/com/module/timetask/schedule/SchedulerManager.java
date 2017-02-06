package com.module.timetask.schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.module.timetask.bean.JobBean;

/**
 * @author Administrator 日程管理类
 */
@Component
public class SchedulerManager {
	/**
	 * 加载Schedule类
	 */
	private StdScheduler scheduler;

	@Value("${task.startSchedulerEnable}")
	public boolean startSchedulerEnable;
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerManager.class);
	
	
	public SchedulerManager() {
		super();
	}
	public SchedulerManager(StdScheduler scheduler) {
		super();
		this.scheduler = scheduler;
	}

	/**
	 * @Description: 列表展示
	 * @Title: SchedulerManager.java
	 */
	public List<JobDetail> listAllJobs() {

		try {

			if (scheduler.isShutdown()) {
				return null;
			}
			List<String> groupList = scheduler.getJobGroupNames();
			List<JobDetail> jobList = new ArrayList<JobDetail>();
			for (String group : groupList) {
				Set<JobKey> jobKes = scheduler.getJobKeys(GroupMatcher
						.jobGroupEquals(group));

				for (JobKey jobKey : jobKes) {
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					jobList.add(jobDetail);
				}
			}
			return jobList;
		} catch (SchedulerException e) {
			throw new RuntimeException("列表展示定时任务异常", e);
		}
	}

	/**
	 * @Description: 添加一个定时任务并部署若该任务已存在更新任务；否则添加新任务
	 * 
	 * @param jobBean
	 *            任务
	 * @Title: SchedulerManager.java
	 */
	public void addJob(JobBean jobBean) {
		try {
			if (scheduler.isShutdown()) {
				return;
			}
			Class clazz = Class.forName(jobBean.getJobClazz());
			JobKey jobKey = JobKey.jobKey(jobBean.getJobName(),
					jobBean.getJobGroup());
			if (!scheduler.checkExists(jobKey)) {
				JobDetail detail = JobBuilder.newJob(clazz)
						.withDescription(jobBean.getDesc())
						.withIdentity(jobKey).build();// 任务名，任务组，任务执行类
				// 触发器1.构造时间表达式
				CronScheduleBuilder builder = CronScheduleBuilder
						.cronSchedule(jobBean.getCronExpression());// 触发器时间设定
				// 构造触发器
				CronTrigger trigger = (CronTrigger) TriggerBuilder
						.newTrigger()
						.withIdentity(jobBean.getJobName(),
								jobBean.getJobGroup()).withSchedule(builder)
						.build();

				scheduler.scheduleJob(detail, trigger);
				scheduler.pauseJob(jobKey);
				// 启动
				startScheduler();
			} else {
				modifyJobTime(jobBean);
			}
		} catch (Exception e) {
			throw new RuntimeException("添加定时任务异常", e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobBean
	 * @Title: QuartzManager.java
	 * 
	 * @version V2.0
	 */
	public void modifyJobTime(JobBean jobBean) {
		try {
			// 取得该触发器
			TriggerKey triggerKey = new TriggerKey(jobBean.getJobName(),
					jobBean.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler
					.getTrigger(triggerKey);

			if (trigger == null||scheduler.isShutdown()) {
				return;
			}

			String oldCronExpression = trigger.getCronExpression();
			// 如果时间表达式与触发器原时间表达式不同则改变当前触发器出发时间
			if (!oldCronExpression
					.equalsIgnoreCase(jobBean.getCronExpression())) {
				CronScheduleBuilder builder = CronScheduleBuilder
						.cronSchedule(jobBean.getCronExpression());// 触发器时间设定
				// 立刻重新启动触发器
				CronTrigger newTrigger = trigger.getTriggerBuilder()
						.withIdentity(triggerKey).withSchedule(builder).build();
				scheduler.rescheduleJob(triggerKey, newTrigger);
			}
		} catch (Exception e) {
			throw new RuntimeException("修改任务时间表达式异常", e);
		}
	}

	/**
	 * 暂停任务
	 * 
	 * @throws SchedulerException
	 */
	public void pauseJob(JobBean jobBean) {
		JobKey jobKey = JobKey.jobKey(jobBean.getJobName(),
				jobBean.getJobGroup());
		try {
			if (!scheduler.isShutdown()) {
				scheduler.pauseJob(jobKey);
			}
		} catch (SchedulerException e) {
			throw new RuntimeException("暂停定时任务", e);
		}
	}

	/**
	 * 恢复任务
	 * 
	 * @throws SchedulerException
	 */
	public void resumeJob(JobBean jobBean) {
		JobKey jobKey = JobKey.jobKey(jobBean.getJobName(),
				jobBean.getJobGroup());
		try {
			if (!scheduler.isShutdown()) {
				scheduler.resumeJob(jobKey);
			}
		} catch (SchedulerException e) {
			throw new RuntimeException("恢复定时任务异常", e);
		}
	}

	/**
	 * 删除任务
	 * 
	 * @throws SchedulerException
	 */
	public void removeJob(JobBean jobBean) {
		JobKey jobKey = JobKey.jobKey(jobBean.getJobName(),
				jobBean.getJobGroup());
		try {
			if (!scheduler.isShutdown()) {
				scheduler.deleteJob(jobKey);
			}
		} catch (SchedulerException e) {
			throw new RuntimeException("删除定时任务异常", e);

		}
	}

	/**
	 * 立即运行任务（一次）
	 * 
	 * @throws SchedulerException
	 */
	public void runOnce(JobBean jobBean) {
		JobKey jobKey = JobKey.jobKey(jobBean.getJobName(),
				jobBean.getJobGroup());
		try {
			if (!scheduler.isShutdown()) {
				scheduler.triggerJob(jobKey);
			}
		} catch (SchedulerException e) {
			throw new RuntimeException("立即运行一次定时任务异常", e);
		}
	}

	/**
	 * 获取时间表达式
	 * 
	 * @throws SchedulerException
	 */
	public String getCroByJob(JobBean jobBean) {
		JobKey jobKey = JobKey.jobKey(jobBean.getJobName(),
				jobBean.getJobGroup());
		List<CronTrigger> triggers;
		try {
			triggers = (List<CronTrigger>) scheduler.getTriggersOfJob(jobKey);
			String cro = triggers.get(0).getCronExpression();
			return cro;
		} catch (SchedulerException e) {
			throw new RuntimeException("获取CronExpressionByJob异常");
		}
	}

	/**
	 * 获得trriger
	 * 
	 * @return
	 */
	public CronTrigger getTrriger(TriggerKey triggerKey) {
		try {
			return (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			throw new RuntimeException("获取TrrigerByTriggerKey异常");
		}
	}
	/**
	 * 获得任务状态
	 * 
	 * @return
	 */
	public String getTriggerState(TriggerKey triggerKey) {
		try {
			TriggerState triggerState ;//NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED
			triggerState = scheduler.getTriggerState(triggerKey);
			String jobState = "";
			switch (triggerState) {
			case NONE:
				jobState = "无";
				break;
			case NORMAL:
				jobState = "正常";
				break;
			case PAUSED:
				jobState = "暂停";
				break;
			case COMPLETE:
				jobState = "触发完成";
				break;
			case ERROR:
				jobState = "错误";
				break;
			case BLOCKED:
				jobState = "阻塞";
				break;
			default:
				break;
			}
			return  jobState;
		} catch (SchedulerException e) {
			throw new RuntimeException("获取TriggerState异常");
		}
	}

	/**
	 * 获得任务上次触发时间
	 */
	public String getPreviousFireTime(TriggerKey triggerKey) {
		CronTrigger cronTrigger = getTrriger(triggerKey);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date PreviousFireTime = cronTrigger.getPreviousFireTime();
		if (null == PreviousFireTime) {
			return "任务尚未执行";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(PreviousFireTime);
		return formatter.format(calendar.getTime());
	}

	/**
	 * 获得任务下次触发时间
	 */
	public String getNextFireTime(TriggerKey triggerKey) {
		CronTrigger cronTrigger = getTrriger(triggerKey);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nextFireTime = cronTrigger.getNextFireTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nextFireTime);
		return formatter.format(calendar.getTime());
	}
	
	/**
	 * 暂停所有任务
	 */
	public void pauseAll() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.pauseAll();
			}
		} catch (Exception e) {
			throw new RuntimeException("暂停所有任务异常", e);
		}
	}

	/**
	 * 暂停所有任务
	 */
	public void resumeAll() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.resumeAll();
			}
		} catch (Exception e) {
			throw new RuntimeException("恢复所有任务异常", e);
		}
	}

	/**
	 * 删除所有任务
	 */
	public void removeAll() {
		try {
			if (!scheduler.isShutdown()) {
				List<String> groupList = scheduler.getJobGroupNames();
				Set<JobKey> jobKeys = new HashSet<JobKey>();

				for (String group : groupList) {
					Set<JobKey> jobKeySet = scheduler.getJobKeys(GroupMatcher
							.jobGroupEquals(group));
					jobKeys.addAll(jobKeySet);
					scheduler.deleteJobs(new ArrayList<JobKey>(jobKeys));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("删除所有任务异常", e);
		}
	}

	/**
	 * 启动日程
	 */
	public void startScheduler() {
		LOGGER.info("===============startSchedulerEnable======="+startSchedulerEnable);
		if (!startSchedulerEnable) {
			LOGGER.info("=========================startSchedulerEnable is false, startScheduler failed!================================");
			return;
		}
		try {
			if (scheduler.isShutdown()) {
				return ;
			}
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException("启动日程异常", e);
		}
	}

	/**
	 * 关闭日程
	 */
	public void shutdownScheduler() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException("关闭日程异常", e);
		}
	}

	/*
	 * public static void main(String[] args) throws ClassNotFoundException,
	 * SchedulerException { String clazz =
	 * "frameworkx.springframework.scheduling.quartz.MyQuartzJobBean"; JobBean
	 * jobBean = new JobBean(); jobBean.setJobClazz(clazz);
	 * jobBean.setJobName("sadfddd"); jobBean.setJobGroup("asfdfdf");
	 * jobBean.setDesc("试试看吧哈哈"); jobBean.setCronExpression("0 0/7 * * * ?"); //
	 * addJob(jobBean);
	 * 
	 * // TrrigerBean trrigerBean = new TrrigerBean(); //
	 * trrigerBean.setTrrigerName("abcTrri"); //
	 * trrigerBean.setTrrigerGroup("trriGrou"); //
	 * trrigerBean.setCronExpression("0/10 0 * * * ?"); //
	 * modifyJobTime(jobBean, trrigerBean); // TrrigerBean trrigerBean2 = new
	 * TrrigerBean(); // trrigerBean2.setTrrigerName("abcTrri"); //
	 * trrigerBean2.setTrrigerGroup("trriGrou"); //
	 * trrigerBean2.setCronExpression("0/5 0 * * * ?"); //
	 * modifyJobTime(trrigerBean2); // 3 // String clazz = //
	 * "frameworkx.springframework.scheduling.quartz.MyQuartzJobBean"; //
	 * jobBean.setJobClazz(clazz); // jobBean.setJobName("abc"); //
	 * jobBean.setJobGroup("jobGroup"); // jobBean.setDesc("试试看吧哈哈"); //
	 * TrrigerBean trrigerBean = new TrrigerBean(); //
	 * trrigerBean.setTrrigerName("abcTrri"); //
	 * trrigerBean.setTrrigerGroup("trriGrou"); //
	 * trrigerBean.setCronExpression("0 0/1 * * * ?"); // addJob(jobBean,
	 * trrigerBean); // TrrigerBean trrigerBean2 = new TrrigerBean(); //
	 * trrigerBean2.setTrrigerName("abcTrri"); //
	 * trrigerBean2.setTrrigerGroup("trriGrou"); //
	 * trrigerBean2.setCronExpression("0/10 * * * * ?"); //
	 * modifyJobTime(trrigerBean2); // 4 // JobBean jobBean = new JobBean(); //
	 * jobBean.setJobName("abc"); // jobBean.setJobGroup("jobGroup"); //
	 * runOnce(jobBean); // scheduler.start(); // pauseJob(jobBean); //
	 * resumeJob(jobBean); // List<JobDetail> jobList = listAllJobs(); //for
	 * (JobDetail jobDetail : jobList) { //System.err.println(jobDetail);
	 * ApplicationContext springContext = new
	 * ClassPathXmlApplicationContext("applicationContext-quartz.xml");
	 * StdScheduler scheduler = (StdScheduler)
	 * springContext.getBean("quartzScheduler"); scheduler.deleteJob(new
	 * JobKey("abc","jobGroup")); }
	 */
}

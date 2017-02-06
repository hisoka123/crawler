package com.crawlermanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobDetail;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.module.timetask.bean.JobBean;
import com.module.timetask.schedule.SchedulerManager;

@Controller
@RequestMapping("/schedule")
public class ScheduleControl {

	private static final Logger log = LoggerFactory
			.getLogger(ScheduleControl.class);

	/**
	 * 
	 * 列表展示定时任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/scheduleList")
	public ModelAndView scheduleList(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			// 获得任务调度管理
			SchedulerManager schedulerManager = getSchedul(request);
			if(schedulerManager != null){
			List<JobBean> jobList = new ArrayList<JobBean>();
			List<JobDetail> jobDetails = schedulerManager.listAllJobs();
			if (null != jobDetails ) {
				for (JobDetail jobDetail : jobDetails) {
					JobBean jobBean = new JobBean();
					jobBean.setJobName(jobDetail.getKey().getName());
					jobBean.setJobGroup(jobDetail.getKey().getGroup());
					jobBean.setCronExpression(schedulerManager.getCroByJob(jobBean));
					TriggerKey triggerKey = TriggerKey.triggerKey(jobDetail
							.getKey().getName(), jobDetail.getKey().getGroup());
					jobBean.setJobStatus(schedulerManager.getTriggerState(triggerKey));
					jobBean.setPreviousFireTime(schedulerManager
							.getPreviousFireTime(triggerKey));
					jobBean.setNextFireTime(schedulerManager
							.getNextFireTime(triggerKey));
					jobBean.setJobClazz(jobDetail.getJobClass().toString().substring(5).trim());
					jobBean.setDesc(jobDetail.getDescription());
					jobList.add(jobBean);
				}
			}
			model.put("jobList", jobList);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return new ModelAndView("sinaWeibo/taskList", model);
	}

	/**
	 * 添加定时任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addJob")
	public String addJob(JobBean jobBean, HttpServletRequest request) {
		// 加载定时任务配置文件
		try {
			SchedulerManager schedulerManager = getSchedul(request);
			if (StringUtils.isNotBlank(jobBean.getJobName())
					&& StringUtils.isNotBlank(jobBean.getJobGroup())
					&& StringUtils.isNotBlank(jobBean.getJobClazz())) {
				schedulerManager.addJob(jobBean);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 修改定时任务触发时间
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/modifyJobTime")
	public String modifyJobTime(JobBean jobBean,
			HttpServletRequest request) {
		try {
			// 加载定时任务配置文件
			SchedulerManager schedulerManager = getSchedul(request);
			if (StringUtils.isNotBlank(jobBean.getJobName())
					&& StringUtils.isNotBlank(jobBean.getJobGroup())) {
				schedulerManager.modifyJobTime(jobBean);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 定时任务暂停
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/pauseJob")
	public String pauseJob(JobBean jobBean, HttpServletRequest request) {
		try {
			// 加载定时任务配置文件
			jobBean.setJobName(jobBean.getJobName());
			jobBean.setJobGroup(jobBean.getJobGroup());
			SchedulerManager schedulerManager = getSchedul(request);
			if (StringUtils.isNotBlank(jobBean.getJobName())
					&& StringUtils.isNotBlank(jobBean.getJobGroup())) {
				schedulerManager.pauseJob(jobBean);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 定时任务恢复
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/resumeJob")
	public String resumeJob(JobBean jobBean, HttpServletRequest request) {
		// 加载定时任务配置文件
		try {
			jobBean.setJobName(jobBean.getJobName());
			jobBean.setJobGroup(jobBean.getJobGroup());
			SchedulerManager schedulerManager = getSchedul(request);
			if (StringUtils.isNotBlank(jobBean.getJobName())
					&& StringUtils.isNotBlank(jobBean.getJobGroup())) {
				schedulerManager.resumeJob(jobBean);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 立即执行一次定时任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/runOnce")
	public String runOnce(JobBean jobBean, HttpServletRequest request) {
		// 加载定时任务配置文件
		try {
			SchedulerManager schedulerManager = getSchedul(request);
			if (StringUtils.isNotBlank(jobBean.getJobName())
					&& StringUtils.isNotBlank(jobBean.getJobGroup())) {
				schedulerManager.runOnce(jobBean);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 删除定时任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/removeJob")
	public String removeJob(JobBean jobBean, HttpServletRequest request) {
		// 加载定时任务配置文件
		try {
			jobBean.setJobName(jobBean.getJobName());
			jobBean.setJobGroup(jobBean.getJobGroup());
			SchedulerManager schedulerManager = getSchedul(request);
			if (StringUtils.isNotBlank(jobBean.getJobName())
					&& StringUtils.isNotBlank(jobBean.getJobGroup())) {
				schedulerManager.removeJob(jobBean);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 暂停所有任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/pauseAll")
	public String pauseAll(JobBean jobBean, HttpServletRequest request) {
		// 加载定时任务配置文件
		try {
			SchedulerManager schedulerManager = getSchedul(request);
			schedulerManager.pauseAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 恢复所有任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/resumeAll")
	public String resumeAll(JobBean jobBean, HttpServletRequest request) {
		try {
			// 加载定时任务配置文件
			SchedulerManager schedulerManager = getSchedul(request);
			schedulerManager.resumeAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 删除所有
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/removeAll")
	public String removeAll(JobBean jobBean, HttpServletRequest request) {
		// 加载定时任务配置文件
		try {
			SchedulerManager schedulerManager = getSchedul(request);
			schedulerManager.removeAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 启动 日程
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/startScheduler")
	public String startScheduler(JobBean jobBean,
			HttpServletRequest request) {
		// 加载定时任务配置文件
		try {
			SchedulerManager schedulerManager = getSchedul(request);
			schedulerManager.startScheduler();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 关闭 日程
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/shutdownScheduler")
	public String shutdownScheduler(JobBean jobBean,
			HttpServletRequest request) {
		// 加载定时任务配置文件
		try {
			SchedulerManager schedulerManager = getSchedul(request);
			schedulerManager.shutdownScheduler();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/schedule/scheduleList";
	}

	/**
	 * 
	 * 得到任务调度器管理
	 * 
	 * @param request
	 * @return
	 */
	private SchedulerManager getSchedul(HttpServletRequest request) {
		// 实例化调度工具
		SchedulerManager schedulerManager = null;
		try {
			// 获得SringCongtext上下文
			ApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(request.getServletContext());
			// 得到调度器
			StdScheduler scheduler = (StdScheduler) applicationContext
					.getBean("quartzScheduler");
			schedulerManager = new SchedulerManager(scheduler);
		} catch (BeansException e) {
			log.error(e.getMessage(), e);
		}
		return schedulerManager;
	}
}

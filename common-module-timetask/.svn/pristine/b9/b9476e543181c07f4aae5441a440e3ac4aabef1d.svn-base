package com.module.timetask.aspect;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import jline.internal.Log;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawlermanage.service.TaskTrackService;
import com.google.gson.GsonBuilder;
import com.module.dao.entity.timeTask.Ids;
import com.module.dao.entity.timeTask.TaskTrace;
@Component
@Aspect
public class TaskTraceAspect {
	private static final Logger Log = Logger.getLogger(TaskTraceAspect.class);
	@Autowired
	private TaskTrackService taskTrackService;


	/**
	 * Pointcut
	 * 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数
	 * 该方法就是一个标识，不进行调用
	 */
	@Pointcut("execution(* com.module.timetask.*.*(..))")
	private void aspectjMethod(){};

	/** 
	 * Around 
	 * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
	 * 
	 * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice
	 * 执行完AfterAdvice，再转到ThrowingAdvice
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */ 
	@Around(value = "aspectjMethod()")  
	public Map<String , Object> aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {  
		Log.info("+++++++++++++++++++++++++++++++++++++++++++ TaskTraceAspect  aroundAdvice start +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		TaskTrace taskTrace = new TaskTrace();
		long startTime = System.currentTimeMillis();
		//调用核心逻辑
		
		Map<String , Object> retVal = null;
		try {
			retVal = (Map<String, Object>) pjp.proceed();
		} catch (Exception e) {
			taskTrace.setExecuteState("exception");
			com.module.dao.entity.timeTask.Exception exception = new com.module.dao.entity.timeTask.Exception();
			
		       StringWriter sw = new StringWriter();   
	            PrintWriter pw = new PrintWriter(sw, true);   
	            e.printStackTrace(pw);   
	            pw.flush();   
	            sw.flush();   
			exception.setMessage(sw.toString());
			taskTrace.setException(exception);
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		
		if(null != retVal && retVal.get("Exception") != null){
			taskTrace.setExecuteState("exception");
			com.module.dao.entity.timeTask.Exception exception = new com.module.dao.entity.timeTask.Exception();
			
			exception.setMessage((String) retVal.get("Exception"));
			taskTrace.setException(exception);
			Log.error(retVal.get("Exception"));
		}
		
		//新浪微博突发事件微博定时特别处理
		if(null != retVal && retVal.get("ids") != null){
		List<Long> ids = (List<Long>) retVal.get("ids");
		Log.info(ids.size());
		List<Ids>  idss = new ArrayList<Ids>();
		for (Long ids2 : ids) {
			Ids ids3 = new Ids();
			ids3.setTid(ids2);
			ids3.setTaskTrace(taskTrace);
			idss.add(ids3);
		}
		taskTrace.setTweetIds(idss);
		taskTrace.setStorageNum(ids.size());
		
		}
		//爬虫API测试定时任务特别处理
		if(null != retVal && retVal.get("APIresult") != null){
			
			Result result = (Result) retVal.get("APIresult");
			taskTrace.setContent(new GsonBuilder().setPrettyPrinting().create().toJson(result));
			if(result.getError() != null){
				taskTrace.setExecuteState("exception");
				com.module.dao.entity.timeTask.Exception exception = new com.module.dao.entity.timeTask.Exception();
				exception.setMessage(result.getError().getErrorMsg());
				taskTrace.setException(exception);
			}
		}
		//输入参数特别处理
		if(null != retVal && retVal.get("arguments") != null){
			taskTrace.setArguments(retVal.get("arguments").toString());
		}
		if(null != retVal && retVal.get("cName") != null){
			taskTrace.setcName(retVal.get("cName").toString());
		}
		if(null != retVal && retVal.get("cId") != null){
			taskTrace.setcId((Long)retVal.get("cId"));
		}
		
		
		Object[]  o= pjp.getArgs();
		String taskName = (String) o[0];
		String taskGroup = (String) o[1];
		long duration = endTime -startTime;
		
		taskTrace.setTaskName(taskName);
		taskTrace.setTaskGroup(taskGroup);
		taskTrace.setDuration(duration);
		if(null == taskTrace.getExecuteState()||"".equals(taskTrace.getExecuteState())){
		Log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++okokokok+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		taskTrace.setExecuteState("ok");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(startTime);
		taskTrace.setExecuteTime(calendar.getTime());
		taskTrackService.save(taskTrace);
		
		Log.info("+++++++++++++++++++++++++++++++++++++++++++ TaskTraceAspect  aroundAdvice end ++++"+taskTrace.getExecuteState()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		return retVal;
	} 
	
}

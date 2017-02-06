/**
 * 
 */
package com.crawlermanage.controller.api.linkedin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.service.linkedin.LinkedinLoginService;
import com.crawlermanage.service.linkedin.task.CrawlerTaskEvent;
import com.crawlermanage.service.linkedin.task.Event;
import com.crawlermanage.service.linkedin.task.LinkedinCrawlerTask;
import com.crawlermanage.service.linkedin.task.Listener;
import com.crawlermanage.service.linkedin.task.XmlListenerContext;

/**
 * @author kingly
 * @date 2015年9月21日
 * 
 */

@Controller
@RequestMapping("/api/linkedin")
public class LinkinCrawlerTaskController {
	@Autowired
	private LinkedinLoginService linkedinLoginService;
	@Autowired
	private XmlListenerContext xmlListenerContext;
	private Event event = new CrawlerTaskEvent();
	
	
	@RequestMapping("/task")
	public String home() {
		return "linkedIn/crawler-task";
	}
	
	
	@RequestMapping("/task/start")
	@ResponseBody
	public String start(boolean signal) {
		String result = null;
		
		if (signal) {
			LinkedinCrawlerTask task = new LinkedinCrawlerTask();
			List<Listener> listeners = xmlListenerContext.getCrawlerTaskListeners();
			task.setEvent(event);
			task.setListeners(listeners);
			task.startTask();
			result = "start!";
		} else {
			CrawlerTaskEvent cevent = (CrawlerTaskEvent)event;
			cevent.setAllTaskStop(true);
			result = "stop!";
		}
		
		try {Thread.sleep(1500);} catch (Exception e) {} ////
		return result;
	}
	
	@RequestMapping("/task/on")
	@ResponseBody
	public String on(boolean signal) {
		String result = null;
		CrawlerTaskEvent cevent = (CrawlerTaskEvent)event;
		
		if (signal) {
			cevent.setAllTaskPause(false);
			result = "on!";
		} else {
			cevent.setAllTaskPause(true);
			result = "pause!";
		}
		
		return result;
	}
	
	@RequestMapping("/task/addprofileOn")
	@ResponseBody
	public String addprofileOn(boolean signal) {
		String result = null;
		CrawlerTaskEvent cevent = (CrawlerTaskEvent)event;
		
		if (signal) {
			cevent.setAllTaskSaddProfilesPause(false);
			result = "addprofileOn!";
		} else {
			cevent.setAllTaskSaddProfilesPause(true);
			result = "addprofilePause!";
		}
		
		return result;
	}
	
}

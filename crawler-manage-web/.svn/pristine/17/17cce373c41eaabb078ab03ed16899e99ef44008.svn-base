package com.crawlermanage.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.weibo.domain.json.TweetJson;
import com.crawlermanage.service.TaskTrackService;
import com.crawlermanage.service.TweetService;
import com.google.gson.Gson;
import com.module.dao.entity.data.Picture;
import com.module.dao.entity.data.Tweet;
import com.module.dao.entity.timeTask.TaskTrace;

@Controller
@RequestMapping("/taskTrack")
public class TaskTrackController {

private static final Logger log = LoggerFactory.getLogger(TaskTrackController.class);
	
	@Autowired
	private TaskTrackService taskTrackService;
	@Autowired
	private TweetService tweetService;

	/**
	 * 跳转至列表展示页
	 * @return
	 */
	@RequestMapping("/listException")
	public String  listException(@RequestParam("taskName") String taskName ,@RequestParam("taskGroup") String taskGroup ,@RequestParam("pageSize") int pageSize ,@RequestParam("pageNumber") int pageNumber,@RequestParam(value ="pageOption",required = false) String pageOption,Model model){
		
		try {
			log.info("================================================TaskTrackController.list()start=====================================================");
			List<TaskTrace> tList;
			Page<TaskTrace> pTaskTrace = taskTrackService.findByTaskNameAndTaskGroupAndExceptionIsNotNullOrderByExecuteTimeDesc(taskName, taskGroup, pageSize, pageNumber, pageOption);
			//当option 为0 上一页 
			tList = pTaskTrace.getContent();
			model.addAttribute("tList", tList);
			model.addAttribute("page", pTaskTrace);
			model.addAttribute("taskName", taskName);
			model.addAttribute("taskGroup", taskGroup);
			log.info("================================================TaskTrackController.list()end=====================================================");
		} catch (Exception e) {
			log.info("列表展示任务追踪记录异常", e);
		}
		
		return "sinaWeibo/taskTrackList";
	}
	
	/**
	 * 跳转至列表展示页
	 * @return
	 */
	@RequestMapping("/listSuccess")
	public String  listSuccess(@RequestParam("taskName") String taskName ,@RequestParam("taskGroup") String taskGroup ,@RequestParam("pageSize") int pageSize ,@RequestParam("pageNumber") int pageNumber,@RequestParam(value ="pageOption",required = false) String pageOption,Model model){
		
		try {
			log.info("================================================TaskTrackController.list()start=====================================================");
			List<TaskTrace> tList;
			Page<TaskTrace> pTaskTrace = taskTrackService.findByTaskNameAndTaskGroupAndExceptionIsNullOrderByExecuteTimeDesc(taskName, taskGroup, pageSize, pageNumber, pageOption);
			//当option 为0 上一页 
			tList = pTaskTrace.getContent();
			model.addAttribute("tList", tList);
			model.addAttribute("page", pTaskTrace);
			model.addAttribute("taskName", taskName);
			model.addAttribute("taskGroup", taskGroup);
			log.info("================================================TaskTrackController.list()end=====================================================");
		} catch (Exception e) {
			log.info("列表展示任务追踪记录异常", e);
		}
		
		return "sinaWeibo/taskTrackList";
	}
	
	/**
	 * 跳转至列表展示页
	 * @return
	 */
	@RequestMapping("/list")
	public String  list(@RequestParam("taskName") String taskName ,@RequestParam("taskGroup") String taskGroup ,@RequestParam("pageSize") int pageSize ,@RequestParam("pageNumber") int pageNumber,@RequestParam(value ="pageOption",required = false) String pageOption,Model model){
		
		try {
			log.info("================================================TaskTrackController.list()start=====================================================");
			List<TaskTrace> tList;
			Page<TaskTrace> pTaskTrace = taskTrackService.findByTaskNameAndTaskGroupOrderByExecuteTimeDesc(taskName, taskGroup, pageSize, pageNumber, pageOption);
			//当option 为0 上一页 
			tList = pTaskTrace.getContent();
			model.addAttribute("tList", tList);
			model.addAttribute("page", pTaskTrace);
			model.addAttribute("taskName", taskName);
			model.addAttribute("taskGroup", taskGroup);
			log.info("================================================TaskTrackController.list()end=====================================================");
		} catch (Exception e) {
			log.info("列表展示任务追踪记录异常", e);
		}
		
		return "sinaWeibo/taskTrackList";
	}
	
	//ajax获得alarm对象
		@RequestMapping("/getTweet")
		public @ResponseBody String getTweet(String tids){
			log.info("+++++++++++++++++++++++++++ids.toString()++++++++++++++++++++++"+tids+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			String[] tidString = tids.split(",");
			List<Long> ids=new ArrayList<Long>();
			for (String string : tidString) {
				Long tid = Long.valueOf(string.trim());
				ids.add(tid);
			}
			log.info("+++++++++++++++++++++++++++ids.toString()++++++++++++++++++++++"+ids.toString()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			List<TweetJson>  tweetJsons	 = new ArrayList<TweetJson>();
			for (Long long1 : ids) {
				Tweet tweet = tweetService.getTweet(long1);
				log.info("+++++++++++++++++++++++++++tweet.toString()++++++++++++++++++++++"+tweet.toString()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				TweetJson json = new TweetJson();
				json.setTid(tweet.getTid().toString());
				json.setContent_text(tweet.getText());
				if(null != tweet.getUserFeed()){
				json.setNickname(tweet.getUserFeed().getScreen_name());
				}
				if(null != tweet.getCreated_at()){
					json.setCreated_at(tweet.getCreated_at().toString());
					}
				json.setRegion(tweet.getRegion().getRegionChiName());
				List<Picture> picList = tweet.getPic();
				String[] pic_urls = new String[picList.size()];
				for (int i = 0; i < picList.size(); i++) {
					pic_urls[i] = picList.get(i).getUrl();
				}
				json.setPic_urls(pic_urls);
				log.info("+++++++++++++++++++++++++json.toString()++++++++++++++++++++++++"+json.toString()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				tweetJsons.add(json);
				log.info("++++++++++++++++++++++++++tweetJsons.toString()+++++++++++++++++++++++"+tweetJsons.toString()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			}
			Gson gson = new Gson();
			String tweets = gson.toJson(tweetJsons);
			return tweets;		
		}
}

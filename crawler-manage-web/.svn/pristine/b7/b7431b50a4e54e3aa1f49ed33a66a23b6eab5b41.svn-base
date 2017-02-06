package com.crawlermanage.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.weibo.domain.json.TweetJson;
import com.crawlermanage.service.AlertService;
import com.crawlermanage.service.RegionService;
import com.crawlermanage.service.TweetService;
import com.google.gson.Gson;
import com.module.dao.entity.data.Alarm;
import com.module.dao.entity.data.Email;
import com.module.dao.entity.data.Picture;
import com.module.dao.entity.data.Region;
import com.module.dao.entity.data.Tweet;
import com.module.timetask.bean.AlarmJson;
/**
 * 本类为sprig mvc的controller类，主要功能为以下模块：
 * 
 * 
 * @author lyg
 *
 */

@Controller
@RequestMapping("/sinaWeibo")
public class AlarmController {
    
	private static final Logger log = LoggerFactory.getLogger(AlarmController.class);
	
	@Autowired
	private RegionService regionService;
	@Autowired
	private AlertService alertService;
	@Autowired
	private TweetService tweetService;
	
	
	
	
	
	/**
	 * 跳转至报警任务列表页
	 * @return
	 */
	@RequestMapping("/alarmList")
	public String  alarmList(Model model){
		Set<Map.Entry<Region, List<Region>>> regionSet = regionService.getAllSubRegionByParentId(-2L).entrySet();
		List<Alarm> alarms = alertService.findAll();
		Map<Long, String> alarmIdMap = new HashMap<Long, String>();
		for (Alarm alarm : alarms) {
			List<Region> regions = alarm.getRegions();
			List<Long> id = new ArrayList<Long>();
			StringBuilder sb = new  StringBuilder();
			for (Region region : regions) {
				id.add(region.getId());
			}
			for (Region region : regions) {
				if(!(id.contains(region.getParentId()))){
					sb.append(region.getRegionChiName() + "  ");
				}
			}
			alarmIdMap.put(alarm.getId(), sb.toString());
		}
		
		model.addAttribute("regionSet", regionSet);
		model.addAttribute("alarms", alarms);
		model.addAttribute("alarmIdMap", alarmIdMap);
		return "sinaWeibo/alarm";
	}
	
	/**
	 * 增加报警任务
	 * @return
	 */
	@RequestMapping(value = "/alarmAdd",method = RequestMethod.POST)
	public String  alarmAdd(@Param("alarmName") String alarmName ,@Param("email") String email ,@Param("region") String region ,Model model){
		Alarm alarm = new Alarm();
		log.info("=============================region===={"+region+"}========================================");
		if(StringUtils.isNotBlank(alarmName)){
			alarm.setName(alarmName.trim());
		}
		if(StringUtils.isNotBlank(region)){
			String[] regionIds = region.split(",");
			List<Long> regions = new ArrayList<Long>();
			for (String regionId : regionIds) {
				Long id = Long.valueOf(regionId);
				regions.add(id);
			}
			
			List<Region> regionList = regionService.getRegionsByIds(regions);
			alarm.setRegions(regionList);
		}
		List<Alarm> alarms = new ArrayList<Alarm>();
		alarms.add(alarm);
		List<Email> emails = new ArrayList<Email>();
		if(StringUtils.isNotBlank(email)){
			String[] Emails = email.split(",");
			for (String eml : Emails) {
				Email email1 = new  Email();
				email1.setAlarm(alarms);
				email1.setAddress(eml.trim());
				emails.add(email1);
			}
		}
		alarm.setEmails(emails);
		alertService.saveAndFlush(alarm);
		return "redirect:/sinaWeibo/alarmList";
	}
	
	/**
	 * 删除报警任务
	 * @return
	 */
	@RequestMapping(value = "/alarmRemove",method = RequestMethod.GET)
	public String  alarmRemove(@Param("id") Long id){
		alertService.delete(id);
		return "redirect:/sinaWeibo/alarmList";
	}
	
	/**
	 * 修改报警任务
	 * @return
	 */
	@RequestMapping(value = "/alarmModify",method = RequestMethod.POST)
	public String  alarmModify(@Param("id") Long id,@Param("alarmName") String alarmName ,@Param("email") String email ,@Param("region") String region ,Model model){
		Alarm alarm = alertService.findOne(id);
		if(StringUtils.isNotBlank(alarmName)){
			alarm.setName(alarmName.trim());
		}
		if(StringUtils.isNotBlank(region)){
			String[] regionIds = region.split(",");
			List<Long> regions = new ArrayList<Long>();
			for (String regionId : regionIds) {
				Long reId = Long.valueOf(regionId);
				regions.add(reId);
			}
			
			List<Region> regionList = regionService.getRegionsByIds(regions);
			alarm.setRegions(regionList);
		}
		List<Alarm> alarms = new ArrayList<Alarm>();
		alarms.add(alarm);
		List<Email> emails = alarm.getEmails();
		emails.clear();
		if(StringUtils.isNotBlank(email)){
			String[] Emails = email.split(",");
			for (String eml : Emails) {
				Email email1 = new  Email();
				email1.setAlarm(alarms);
				email1.setAddress(eml.trim());
				emails.add(email1);
			}
		}
		alarm.setEmails(emails);
		alertService.saveAndFlush(alarm);
		return "redirect:/sinaWeibo/alarmList";
	}
	
	/**
	 * 查看所关注报警微博
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/alarmBurstoutList")
	public ModelAndView alarmBurstoutList(HttpServletRequest request,@RequestParam("pageSize") int pageSize ,@RequestParam("pageNumber") int pageNumber,@RequestParam(value ="pageOption",required = false) String pageOption,@Param("id") Long id) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Tweet> tList;
		try {
			Alarm alarm = alertService.findOne(id);
			List<Region> regions = alarm.getRegions();
			log.info("++++++++++++++++++++++++++++++"+regions+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Page<Tweet> tpage= tweetService.findByRegionIdOrderByCreateTimeDesc(regions, pageSize, pageNumber, pageOption);
			long maxId = tweetService.findMaxIdByAlarm(regions);
			//当option 为0 上一页 
			tList = tpage.getContent();
			model.put("tList", tList);
			model.put("page", tpage);
			model.put("maxId", maxId);
			model.put("alarm", alarm);
		} catch (Exception e) {
			log.info("列表展示报警突发事件", e);
		}
		return new ModelAndView("sinaWeibo/alarmBurstout", model);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getCountTidByAlarm/{alarmId}")
	public @ResponseBody long getCountTidByAlarm(@PathVariable("alarmId") Long alarmId) {
		Alarm alarm = alertService.findOne(alarmId);
		long tidCount = tweetService.countTidByAlarm(alarm.getRegions());
		return tidCount;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getNewTweet/{id}/{alarmId}")
	public @ResponseBody String getNewTweet(@PathVariable("id") long id,@PathVariable("alarmId") long alarmId) {
		Alarm alarm = alertService.findOne(alarmId);
		List<Tweet> tweetList = tweetService.findNewByAlarmOrderByCreateTimeDesc(id, alarm.getRegions());
		List<TweetJson> tweetJsons = new ArrayList<TweetJson>();
		for (Tweet tweet : tweetList) {
			TweetJson json = new TweetJson();
			json.setTid(tweet.getTid().toString());
			json.setContent_text(tweet.getText());
			json.setNickname(tweet.getUserFeed().getScreen_name());
			json.setCreated_at(tweet.getCreated_at().toString());
			json.setRegion(tweet.getRegion().getRegionChiName());
			List<Picture> picList = tweet.getPic();
			String[] pic_urls = new String[picList.size()];
			for (int i = 0; i < picList.size(); i++) {
				pic_urls[i] = picList.get(i).getUrl();
			}
			json.setPic_urls(pic_urls);
			tweetJsons.add(json);
		}
		System.out.println("tweetList----------"+tweetList.size());
		Gson gson = new Gson();
		String tweets = gson.toJson(tweetJsons);
		return tweets;
	}
	//ajax获得alarm对象
	@RequestMapping("/getAlarm")
	public @ResponseBody String getAlarm(@RequestParam("id") Long id){
		log.info(String.valueOf(id));
		Alarm alarm = alertService.findOne(id);
		AlarmJson alarmJson = new AlarmJson();
		List<String> emails = new ArrayList<String>();
		List<Email> emails2 = alarm.getEmails();
		log.info("+++++++++++++++++++++++++++++"+emails2.size()+"++++++++++++++++++++++++++++++++++++++++");
		for (Email email : emails2) {
			String eml = email.getAddress();
			emails.add(eml);
		}
		List<String> regionids = new ArrayList<String>();
		List<String> regionNames = new ArrayList<String>();
		List<Region> regions2 = alarm.getRegions();
		for (Region region : regions2) {
			String idregion = String.valueOf(region.getId());
			regionids.add(idregion);
			String NameRegion = region.getRegionChiName();
			regionNames.add(NameRegion);
		}
		alarmJson.setEmails(emails);
		alarmJson.setRegionChiNams(regionNames);
		alarmJson.setRegionIds(regionids);
		alarmJson.setName(alarm.getName());
		log.info("----------------------", alarm);
		Gson gson = new Gson();
		String Json = gson.toJson(alarmJson);
		return Json;		
	}
	
}

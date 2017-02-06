package com.crawlermanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.weibo.domain.json.TweetJson;
import com.crawlermanage.service.TweetService;
import com.google.gson.Gson;
import com.module.dao.entity.data.Picture;
import com.module.dao.entity.data.Tweet;

@Controller
@RequestMapping("/sinaWeibo")
public class SinaBurstoutControl {
	private static final Logger log = LoggerFactory
			.getLogger(SinaBurstoutControl.class);
	@Autowired
	private TweetService tweetService;

	/**
	 * 
	 * 列表展示突发事件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/burstoutList")
	public ModelAndView burstoutList(HttpServletRequest request,@RequestParam("pageSize") int pageSize ,@RequestParam("pageNumber") int pageNumber,@RequestParam(value ="pageOption",required = false) String pageOption) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Tweet> tList;
		try {
			Page<Tweet> tpage= tweetService.getTweetByRegionIsNotNull(pageSize, pageNumber, pageOption);
			long maxId = tweetService.findMaxId();
			//当option 为0 上一页 
			tList = tpage.getContent();
			model.put("tList", tList);
			model.put("page", tpage);
			model.put("maxId", maxId);
		} catch (Exception e) {
			log.info("列表展示突发事件", e);
		}
		return new ModelAndView("sinaWeibo/burstout", model);
	}
	
	/**
	 * 
	 * 定时检查是否有新突发事件入库
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getCountTidAll")
	public @ResponseBody long countTidAllRegionIsNotNull() {
		long tidCount = tweetService.countTidAllRegionIsNotNull();
		return tidCount;
	}
	
	/**
	 * 
	 * 返回新入库微博
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getNewTweet/{id}")
	public @ResponseBody String getNewTweet(@PathVariable("id") long id) {
		List<Tweet> tweetList = tweetService.findNewByRegionIsNotNullOrderByCreateTimeDesc(id);
		List<TweetJson> tweetJsons = new ArrayList<TweetJson>();
		for (Tweet tweet : tweetList) {
			TweetJson json = new TweetJson();
			json.setTid(tweet.getTid().toString());
			json.setContent_text(tweet.getText());
			if(null !=tweet.getUserFeed()&&null !=tweet.getUserFeed().getScreen_name()){
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
			tweetJsons.add(json);
		}
		System.out.println("tweetList----------"+tweetList.size());
		Gson gson = new Gson();
		String tweets = gson.toJson(tweetJsons);
		return tweets;
	}
}

package com.sns.weibo.service;
 

import weibo4j.Timeline;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

public class HomeTimelineService {
 
	public static StatusWapper getHomeTimeline(String access_token){
		Timeline tm = new Timeline(access_token);
		StatusWapper status = null;
		try {
			status = tm.getHomeTimeline(); 
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return status;
	}
  

}

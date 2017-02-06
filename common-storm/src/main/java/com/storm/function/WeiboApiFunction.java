package com.storm.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.model.StatusWapper;

import com.google.gson.Gson;
import com.sns.weibo.service.HomeTimelineService;
import com.storm.domian.WeiboApiParam;

public class WeiboApiFunction {

	private static final Logger log = LoggerFactory.getLogger(WeiboApiFunction.class);

	public String getHomeTimelineJson(WeiboApiParam aep) {
		StatusWapper status = HomeTimelineService.getHomeTimeline(aep.getAccessToken());
		Gson gson = new Gson();
		String statusJson = gson.toJson(status);
		return statusJson;
	}
}

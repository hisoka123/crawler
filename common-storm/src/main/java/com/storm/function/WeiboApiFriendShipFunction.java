package com.storm.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

import com.sns.weibo.service.FriendShipService;
import com.storm.domian.WeiboFriendShipApiParam;

public class WeiboApiFriendShipFunction {
	
	private static final Logger log = LoggerFactory.getLogger(WeiboApiFriendShipFunction.class);

	public String getFriendShipJson(WeiboFriendShipApiParam param) throws WeiboException {
		String json = null;
		Long source = Long.parseLong(param.getSourceId());
		Long target = Long.parseLong(param.getTargetId()); 
		JSONObject jsonObject = FriendShipService.getFriendships(source, target, param.getAccessToken()); 
		json = jsonObject==null?null:jsonObject.toString();
		return json;
	}

}

package com.sns.weibo.service;

import weibo4j.Friendships;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

public class FriendShipService {
	
	public static JSONObject getFriendships(Long source,Long target,String access_token) throws WeiboException{
		Friendships fm = new Friendships(access_token);
		JSONObject json = null;
	 
		json = fm.getFriendshipsById(source, target);
	 
		return json;
	}
	
	/*public static void main(String[] args) throws WeiboException {
		String access_token = "2.00oTwkUFpHMPpDe31b060d84HBVE1E";
		Long source = 1642351362L;//angelababy
		Long target = 1763582395L;//韩庚
		Friendships fm = new Friendships(access_token);
		JSONObject json = fm.getFriendshipsById(source, target);
		System.out.println(json.toString());

	}*/

}

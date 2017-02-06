package test;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

public class HomeTimelineService {
	/**
	 * account:13520800817/12qwaszx
	 * app:vcsparkSNSdata
	 * App Key:3505613165 
	 * App Secret:872304208be3d3041e4ce74f7b896023
	 * */
	private static final String access_token = "2.00oTwkUFpHMPpDe31b060d84HBVE1E";
	
	public  StatusWapper getHomeTimeline(){
		Timeline tm = new Timeline(access_token);
		StatusWapper status = null;
		try {
			status = tm.getHomeTimeline();
			System.out.println(status.toString()); 
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return status;
	} 
	
	public static void main(String[] args) { 
		Timeline tm = new Timeline(access_token);
		try {
			StatusWapper status = tm.getHomeTimeline();
			System.out.println(status.toString()); 
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}

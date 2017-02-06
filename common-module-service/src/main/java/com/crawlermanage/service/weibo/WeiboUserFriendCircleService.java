package com.crawlermanage.service.weibo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.weibo.domain.compare.UserFansComparator;
import com.crawler.weibo.domain.json.FriendCircle;
import com.crawler.weibo.domain.json.UserFeedJson;
import com.sns.weibo.definition.AccessToken;

@Component
public class WeiboUserFriendCircleService {
	
	/*
	 * 去掉机构微博
	 * Accesstoken
	 * */
	private static final Logger log = LoggerFactory.getLogger(WeiboUserFriendCircleService.class);
	
	@Autowired
	private WeiboUserFollowerService weiboFollowerService;
	
	//@Cacheable(value="dataCache", key="'WeiboUserFriendCircleService.getFriendCircle_uid_'+#uid+'_pagetotal_'+#pagetotal+'_limit_'+#limit", unless="#result == null")
	@Cacheable(value="dataCache", key="'WeiboUserFriendCircleService.getFriendCircle_uid=' + #uid + '&pagetotal=' + #pagetotal + '&limit=' + #limit +'&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")
	public Result<FriendCircle> getFriendCircle(String uid, int pagetotal, int limit, boolean isDebug, String logback) {
		FriendCircle fc = new FriendCircle();
		Result<FriendCircle> result = null;
		List<UserFeedJson> friend = new ArrayList<UserFeedJson>(); 
		int count = limit;
		
		List<UserFeedJson> users = getTotalFollowersOrderByFans(uid,pagetotal,logback); 
		for(UserFeedJson user:users){
			UserFeedJson ufj = getFriendShip(uid,user);
			if(ufj!=null){
				//如果相关关注，加入到friend集合，同时最大限制数减一，API调用次数有限（一个APP大概一小时200次调用），这里给一个现实，
				count--;
				friend.add(ufj);
			}
			if(count<=0){
				fc.setFriend(friend); 
				break;
			}
		} 
		fc.setUid(uid);
		
		StringBuffer sb = new StringBuffer();
		String first_line_msg = pagetotal==1 ? "There is " + pagetotal + " html page in total, the URL is:  " : "There are " + pagetotal + " html pages in total, the URLs are as follows:  ";
		sb.append(first_line_msg);
		if(pagetotal > 0) sb.append("http://weibo.com/" + uid + "/follow?page=1");
		for (int i = 2; i <= pagetotal; i++) {
			sb.append("  <br/>  http://weibo.com/" + uid + "/follow?page=" + i);
		}
		String html = sb.toString();
		result = new Result<FriendCircle>(fc, html, isDebug); 
		
		return result;
	}
	
	public UserFeedJson getFriendShip(String uid,UserFeedJson user){
		UserFeedJson ufj = null;
		String accesstoken = getAccessToken();
		boolean bool = weiboFollowerService.isFollowEachother(uid,user.getUid(),accesstoken);
		if(bool){//判断是否uid这个用户关注的人，同时也关注uid，也就是相互关注
			ufj = user; 
		}
		
		return ufj;
	}
	
	public String getAccessToken(){ 
		return AccessToken.getRandomUserAgent().toString();
	}
	
	
	 
	//得到关注的任务列表，并按粉丝数目排序
	public List<UserFeedJson> getTotalFollowersOrderByFans(String uid,int pagetotal,String logback){
		List<UserFeedJson> totalFollowers = new ArrayList<UserFeedJson>();
		for(int i = 1;i<=pagetotal;i++){
			List<UserFeedJson> followers = getFollowers(uid,i,logback);
			if(followers!=null&&followers.size()>0){
				totalFollowers.addAll(followers);
			} 
		}
		Collections.sort(totalFollowers, new UserFansComparator());
		log.info("getTotalFollowersOrderByFans.size:"+totalFollowers.size());
		return totalFollowers;
	}
	
	//得到关注的任务列表
	public List<UserFeedJson> getFollowers(String uid,int page,String logback){
		String url = "http://weibo.com/"+uid+"/follow?page="+page;
		log.info("getFollowers:"+url);
		List<UserFeedJson> users = weiboFollowerService.getFollowers(url, false, logback).getData();
		users = weiboFollowerService.filterPersonAndV(users);
		return users;
	}
	

}

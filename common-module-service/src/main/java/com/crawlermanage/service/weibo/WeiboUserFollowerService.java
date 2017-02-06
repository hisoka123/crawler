package com.crawlermanage.service.weibo;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.storm.def.WeiboFriendShipApiParam;
import com.crawler.storm.def.WeiboLogonParam;
import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawler.weibo.domain.json.UserTarget;
import com.crawler.weibo.htmlparser.WeiboUserFollowerListParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;

@Component
public class WeiboUserFollowerService {

	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private WeiboUserFollowerListParser weiboFollowerListParser;

	private static final Logger log = LoggerFactory.getLogger(WeiboUserFollowerService.class);

	@Cacheable(value="dataCache", key="'WeiboUserFollowerService.getFollowers_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<UserFeedJson>> getFollowers(String url, boolean isDebug, String logback) {

		log.info("getFollowers url:{}", url);

		Result<List<UserFeedJson>> result = new Result<List<UserFeedJson>>();
		FunctionCallParam fcm = new FunctionCallParam(); 
		fcm.setFunction(FunctionDefine.WEIBO_HANDLE_GETPAGE); 
	    WeiboLogonParam weiboLogonParam = new WeiboLogonParam();
	    weiboLogonParam.setUrl(url); 
	    weiboLogonParam.setJsEnable(true);
	    fcm.setWeiboLogonParam(weiboLogonParam);
	    
	    WebParam webParam = new WebParam();
	    webParam.setLogback(logback);
	    fcm.setWebEngineParam(webParam);
	    
	    String param = fcm.toJson();
	    log.info("getFollowers param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		List<UserFeedJson> users = weiboFollowerListParser.followerScriptParser(result.getHtml());
		result.setData(users);
		result.debugMode(isDebug);

		return result;
	}
	
	@Cacheable(value="dataCache", key="'WeiboUserFollowerService.isFollowEachother_sourceId_'+#sourceId+'_targetId_'+#targetId")  
	public boolean isFollowEachother(String sourceId,String targetId,String accesstoken){ 
		 boolean bool = false;
		 FunctionCallParam fcm = new FunctionCallParam();
		 fcm.setFunction(FunctionDefine.WEIBO_API_FRIENDSHIPS); 
	     WeiboFriendShipApiParam weiboFriendShipApiParam = new WeiboFriendShipApiParam();
	     weiboFriendShipApiParam.setAccessToken(accesstoken); 
	     weiboFriendShipApiParam.setSourceId(sourceId); 
	     weiboFriendShipApiParam.setTargetId(targetId); 
	     fcm.setWeiboFriendShipApiParam(weiboFriendShipApiParam);
	     String param = fcm.toJson();
		 log.info("isFollowEachother param:{}",param);
		 String json = null;
		 
		 json = crawlerEngine.execute(param);  
		 log.info("isFollowEachother html:{}", json);
		 
		 Gson gson = new Gson();
		 UserTarget ut = gson.fromJson(json, UserTarget.class); 
		 Boolean b1 = ut.getSource().getFollowing(); 
		 Boolean b2 = ut.getSource().getFollowed_by(); 
		 if(b1&&b2){
			 log.info("相互是好友!!   User:"+ut.getSource().getScreen_name()+" UID:"+ut.getSource().getId()+" <--->  User:"+ut.getTarget().getScreen_name()+" UID:"+ut.getTarget().getId());
			 bool = true;
		 }
		    
	     return bool;
	}
	
	/**
	 * 过滤微博用户，只要“微博个人认证” 用户
	 * @param users
	 * @return
	 */
	public List<UserFeedJson> filterPersonAndV(List<UserFeedJson> users) {
		if(users!=null){ 
			for (Iterator<UserFeedJson> it = users.iterator(); it.hasNext();) {
				UserFeedJson ufj = (UserFeedJson) it.next();
				String vStr = ufj.getV(); 
				if(vStr!=null&&!"微博个人认证".equals(vStr.trim())){ 
					it.remove();
				}
			}   
		}  
		return users;
	}

}

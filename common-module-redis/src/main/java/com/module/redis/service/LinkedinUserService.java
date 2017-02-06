/**
 * 
 */
package com.module.redis.service;

import java.util.List;

import com.crawler.linkedin.domain.json.UserFeedJson;

/**
 * @author kingly
 * @date 2015年9月6日
 * 
 */
public interface LinkedinUserService {
	public long saddUserProfiles(List<String> profiles);
	public long saddUserProfile(String profile);
	public String spopUserProfile();
	public long scard();
	
	public String hmsetUser(UserFeedJson user);
	public String hgetUserJsonByUid(String uid);	
}

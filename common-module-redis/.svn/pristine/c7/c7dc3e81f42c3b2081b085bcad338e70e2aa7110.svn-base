/**
 * 
 */
package com.module.redis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawler.linkedin.util.GsonUtil;
import com.module.redis.dao.LinkedinUserDao;

/**
 * @author kingly
 * @date 2015年9月6日
 * 
 * [Notice] The function param or its eles: value sent to redis cannot be null
 * 
 */
@Service("linkedinUserService")
public class LinkedinUserServiceImpl implements LinkedinUserService {
	@Autowired
	LinkedinUserDao linkedinUserDao;
	
	@Override
	public long saddUserProfiles(List<String> profiles) {
		return linkedinUserDao.sadd(profiles.toArray(new String[profiles.size()]));
	}
	
	@Override
	public long saddUserProfile(String profile) {
		return linkedinUserDao.sadd(profile);
	}

	@Override
	public String spopUserProfile() {
		return linkedinUserDao.spop();
	}
	
	@Override
	public long scard() {
		return linkedinUserDao.scard();
	}
	
	@Override
	public String hmsetUser(UserFeedJson user) {
		Map<String, String> hash = new HashMap<String, String>();
		
		if (user.getName()==null)  user.setName("");
		if (user.getHeadline()==null) user.setHeadline("");
		if (user.getLocation()==null) user.setLocation("");
		if (user.getIndustry()==null) user.setIndustry("");
		
		hash.put("name", user.getName());
		hash.put("headLine", user.getHeadline());
		hash.put("location", user.getLocation()); 
		hash.put("industry", user.getIndustry());
		hash.put("userJson", GsonUtil.toJson(user));
		
		return linkedinUserDao.hmset(user.getUid(), hash);
	}

	@Override
	public String hgetUserJsonByUid(String uid) {
		return linkedinUserDao.hget(uid, "userJson");
	}

}

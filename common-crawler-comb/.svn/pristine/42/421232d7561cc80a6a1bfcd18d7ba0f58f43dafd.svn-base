/**
 * 
 */
package com.crawler.linkedin.util;

import java.util.UUID;

import com.crawler.linkedin.domain.json.UserFeedJson;

/**
 * @author kingly
 * @date 2015年9月6日
 * 
 */
public class IDGenerator {
	private static final String LINKEDIN_USERID_PREFIX = "linkedin_userid_";
	
	public static String generateLinkedinUserId(UserFeedJson user) {
		String name = user.getName();
		String homepage_profile = user.getProfile();
		UUID uuid = UUID.nameUUIDFromBytes((name + homepage_profile).getBytes());
		return LINKEDIN_USERID_PREFIX + uuid.toString();
	}
}

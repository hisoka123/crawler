/**
 * 
 */
package com.module.redis.dao;

import java.util.Map;

/**
 * @author kingly
 * @date 2015年9月6日
 * 
 */
public interface LinkedinUserDao {
	public static final int DATABASE_INDEX01 = 1; //0-15 默认0
	public static final String LINKEDINUSER_PROFILES = "linkedinUser_profiles";
	
	public long sadd(String... members);
	public String spop();
	public long scard();
	
	public String hmset(String key, Map<String, String> hash);
	public String hget(String key, String field);
}

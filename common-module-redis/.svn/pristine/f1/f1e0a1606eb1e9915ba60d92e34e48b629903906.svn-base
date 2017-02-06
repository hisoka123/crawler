/**
 * 
 */
package com.module.redis.dao;

import java.util.Map;
import java.util.Set;

import com.module.redis.domain.CookieJson;

/**
 * @author kingly
 *
 */
public interface CookieDao {
	public static final String LOGIN_COOKIES = "login_cookies_";
	
	//增
	public void save(CookieJson cookie);
	
	//查
	public Map<Object, Object> queryByKey(String key);
	
	//查键符合正则表达式的键
	public Set<String> keys(String pattern);
	
	//删
	public void delete(String key);
}

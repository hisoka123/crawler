/**
 * 
 */
package com.module.redis.service;

import java.util.Collection;
import java.util.List;

import com.module.redis.domain.CookieJson;

/**
 * @author kingly
 *
 */
public interface CookieService {
	//增
	public void save(Collection<CookieJson> cookies);
	public void save(String cookies);
	
	//查
	public List<CookieJson> queryByKey(String key);
	public List<CookieJson> queryAllCookies();
	
	//删
	public void deleteAllCookies();
}

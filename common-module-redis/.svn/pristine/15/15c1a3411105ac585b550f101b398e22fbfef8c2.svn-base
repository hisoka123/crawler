/**
 * 
 */
package com.module.redis.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.redis.dao.CookieDao;
import com.module.redis.domain.CookieJson;
import com.module.redis.util.GsonUtil;

/**
 * @author kingly
 *
 */
@Service("cookieService")
public class CookieServiceImpl implements CookieService {
	@Autowired
	private CookieDao cookieDao;
	
	@Override
	public void save(Collection<CookieJson> cookies) {
		if (cookies==null || cookies.isEmpty()) {
			return;
		}
		for (CookieJson cookie : cookies) {
			if (cookie==null) continue; 
			cookieDao.save(cookie);
		}
	}

	@Override
	public void save(String cookies) {
		CookieJson[] cookieArayy = (CookieJson[])GsonUtil.fromJson(cookies, CookieJson[].class);
		if (cookieArayy==null) {
			return;
		}
		for (CookieJson cookie : cookieArayy) {
			cookieDao.save(cookie);
		}
	}

	@Override
	public List<CookieJson> queryByKey(String key) {
		return getCookiesByKey(key);
	}

	@Override
	public List<CookieJson> queryAllCookies() { //同层方法尽量减少相互调用
		Set<String> keysTemp = cookieDao.keys("*" + CookieDao.LOGIN_COOKIES + "*");
		if (keysTemp==null || keysTemp.isEmpty()) {
			return null;
		}
		
		Set<String> keys = new HashSet<String>();
		for (String key : keysTemp) {
			key = CookieDao.LOGIN_COOKIES + key.split(CookieDao.LOGIN_COOKIES)[1];
			keys.add(key);
		}
		
		List<CookieJson> cookies = new ArrayList<CookieJson>();
		for (String key : keys) {
			cookies.addAll(getCookiesByKey(key));
		}
		
		return cookies;
	}
	
	
	
	private List<CookieJson> getCookiesByKey(String key) {
		Map<Object, Object> map = cookieDao.queryByKey(key); //H,HK,HV  Map<HK,HV>  [key-H]
		if (map==null || map.isEmpty()) {
			return null;
		}
		List<CookieJson> cookies = new ArrayList<CookieJson>();
		for (Entry<Object, Object> entry : map.entrySet()) {
			String cookieJson = (String)entry.getValue();
			CookieJson cookie = (CookieJson)GsonUtil.fromJson(cookieJson, CookieJson.class);
			cookies.add(cookie);
		}
		return cookies;
	}

	@Override
	public void deleteAllCookies() {
		Set<String> keysTemp = cookieDao.keys("*" + CookieDao.LOGIN_COOKIES + "*");
		if (keysTemp==null || keysTemp.isEmpty()) return;
		for (String key : keysTemp) {
			key = CookieDao.LOGIN_COOKIES + key.split(CookieDao.LOGIN_COOKIES)[1];
			cookieDao.delete(key);
		}
	}
	
}

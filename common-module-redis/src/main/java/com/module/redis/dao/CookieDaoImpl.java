/**
 * 
 */
package com.module.redis.dao;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.module.redis.domain.CookieJson;
import com.module.redis.util.GsonUtil;

/**
 * @author Administrator
 *
 */

@Repository("cookieDao")
public class CookieDaoImpl implements CookieDao {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void save(CookieJson cookie) {
		redisTemplate.opsForHash().put(LOGIN_COOKIES + cookie.getDomain(), cookie.getName(), GsonUtil.toJson(cookie));
	}
	
	@Override
	public Map<Object, Object> queryByKey(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	@Override
	public Set<String> keys(String pattern) {
		return ((Jedis)redisTemplate.getConnectionFactory().getConnection().getNativeConnection()).keys(pattern); //返回的字符段含有编码前缀
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	
	
}

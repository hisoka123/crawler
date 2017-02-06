/**
 * 
 */
package com.module.redis.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

/**
 * @author kingly
 * @date 2015年9月6日
 * 
 */
@Repository("linkedinUserDao")
public class LinkedinUserDaoImpl implements LinkedinUserDao {
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	/*private static Jedis jedis;
	static {
		ApplicationContext context = App.getInstance().getContext();
		JedisConnectionFactory jf = (JedisConnectionFactory) context.getBean("jedisConnectionFactory");
		jedis = jf.getShardInfo().createResource();
		jedis.select(DATABASE_INDEX);
	}*/

	@Override
	public long sadd(String... members) {
		Jedis jedis = jedisConnectionFactory.getShardInfo().createResource();
		jedis.select(DATABASE_INDEX01);
		long sadd_num = jedis.sadd(LINKEDINUSER_PROFILES, members); //返回元素增加的个数
		jedis.close();
		return sadd_num;
	}

	@Override
	public String spop() {
		Jedis jedis = jedisConnectionFactory.getShardInfo().createResource();
		jedis.select(DATABASE_INDEX01);
		String spop = jedis.spop(LINKEDINUSER_PROFILES);
		jedis.close();
		return spop;
	}
	
	@Override
	public long scard() {
		Jedis jedis = jedisConnectionFactory.getShardInfo().createResource();
		jedis.select(DATABASE_INDEX01);
		long scard = jedis.scard(LINKEDINUSER_PROFILES);
		jedis.close();
		return scard;
	}
	
	@Override
	public String hmset(String key, Map<String, String> hash) {
		Jedis jedis = jedisConnectionFactory.getShardInfo().createResource();
		jedis.select(DATABASE_INDEX01);
		String hmset = jedis.hmset(key, hash);
		jedis.close();
		return hmset;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisConnectionFactory.getShardInfo().createResource();
		jedis.select(DATABASE_INDEX01);
		String hget = jedis.hget(key, field);
		jedis.close();
		return hget;
	}

}

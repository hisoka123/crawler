/**
 * 
 */
package com.module.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author kingly
 * @date 2016年4月26日
 * 
 */
public class JedisPoolUtil {
	private static JedisPool jedisPool;
	private static Logger LOGGER = LoggerFactory.getLogger(JedisPoolUtil.class);
	
	static {
		initJedisPool();
	}
	/**
	 * 初始化连接池
	 */
	public static void initJedisPool() {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
		jedisPool = (JedisPool) ac.getBean("jedisPool");
	}
	
	public static JedisPool getJedisPool() {
		return jedisPool;
	}
	public static void setJedisPool(JedisPool jedisPool) {
		JedisPoolUtil.jedisPool = jedisPool;
	}
	
	
	/**
	 * 获取数据库连接
	 */
	public static Jedis getConnection() {
		Jedis jedis = null;
		try {
			LOGGER.info("====================getResource start!======================");
			jedis = jedisPool.getResource();
			LOGGER.info("====================getResource end!======================");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedis;
	}

	/**
	 * 关闭数据库连接
	 */
	public static void closeConnection(Jedis jedis) {
		if (null != jedis) {
			try {
				LOGGER.info("====================returnResource start!======================");
				jedisPool.returnResource(jedis);
				LOGGER.info("====================returnResource end!======================");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

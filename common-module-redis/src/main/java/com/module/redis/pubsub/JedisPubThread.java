package com.module.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.module.redis.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;

/**
 * @author kingly
 * @date 2016年4月26日
 *
 */
public class JedisPubThread implements Runnable {
	private JedisPubSubForLogListener jedisPubSubForLogListener;
	private String[] channelPatterns;
	private int dbIndex = 0; //默认为0库
	private static final Logger LOGGER = LoggerFactory.getLogger(JedisPubThread.class);
	
	public JedisPubThread(JedisPubSubForLogListener jedisPubSubForLogListener, String[] channelPatterns) {
		this.jedisPubSubForLogListener = jedisPubSubForLogListener;
		this.channelPatterns = channelPatterns;
	}
	public JedisPubThread(JedisPubSubForLogListener jedisPubSubForLogListener, String[] channelPatterns, int dbIndex) {
		this.jedisPubSubForLogListener = jedisPubSubForLogListener;
		this.channelPatterns = channelPatterns;
		this.dbIndex = dbIndex;
	}

	//开始一个或多个channel的发布监听
	@Override
	public void run() {
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getConnection();
			jedis.select(dbIndex);
			//jedis = new Jedis(HOST, Integer.parseInt(PORT), Integer.parseInt(TIMEOUT));
			//jedisPubSubTest.proceed(jr.getClient(), "news.share", "news.blog");
			//jedisPubSubForLogListener.proceedWithPatterns(jedis.getClient(), proceedPattern);
			//jedis.psubscribe(jedisPubSubForLogListener, "news.*");  
			jedis.psubscribe(jedisPubSubForLogListener, channelPatterns);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis!=null) {
				//jedis.disconnect();
				JedisPoolUtil.closeConnection(jedis);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < channelPatterns.length; i++) {
			sb.append(channelPatterns[i] + ",");
		}
		String cps = sb.toString().substring(0, sb.toString().lastIndexOf(","));
		LOGGER.info("==============redis channel[{}]: psubscribe 线程结束==============", cps);
	}
	
	//结束一个或多个channel的发布监听
	public void punsubscribe(String[] unsubscribePatterns) {
		jedisPubSubForLogListener.punsubscribe(unsubscribePatterns); 
	}

}

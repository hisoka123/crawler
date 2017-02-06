package com.module.redis.pubsub;

import com.module.redis.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author kingly
 * @date 2016年4月26日
 *
 */
public class JedisPubSubForLogListener extends JedisPubSub {
	
	   Jedis jedis=null;
	   
	   public JedisPubSubForLogListener(){
		    try {
				jedis = JedisPoolUtil.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	
	
	
	//取得订阅消息后的处理
	@Override
	public void onMessage(String channel, String message) {
		System.out.println("onMessage: channel["+channel+"], message["+message+"]");
	}

	//取得按表达式的方式订阅的消息后的处理
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		   System.err.println("onPMessage: channel["+channel+"], message["+message+"]");

		   try {
		    	  jedis.select(JedisPubSubDef.TEMP_LOG_DB_INDEX);
				  long filed=jedis.hlen(channel)+1l;
				  jedis.hset(channel,String.valueOf(filed),message);
				  jedis.expire(channel,3600);
				  Thread.sleep(100);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	//初始化订阅时候的处理
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		
		System.out.println("onSubscribe: channel["+channel+"], subscribedChannels["+subscribedChannels+"]");
	}

	//取消订阅时候的处理
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("onUnsubscribe: channel["+channel+"], subscribedChannels["+subscribedChannels+"]");
	}

	//取消按表达式的方式订阅时候的处理
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		System.out.println("onPUnsubscribe: pattern["+pattern+"], subscribedChannels["+subscribedChannels+"]");
		
		jedis.select(2);
		jedis.expire(pattern,3600);
        long filed=jedis.hlen(pattern)+1l;
		jedis.hset(pattern,String.valueOf(filed),"@logOver");
		if (jedis!=null) {
			JedisPoolUtil.closeConnection(jedis);
		}
	}

	//初始化按表达式的方式订阅时候的处理
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		 System.out.println("onPSubscribe: pattern["+pattern+"], subscribedChannels["+subscribedChannels+"]");
	}
}

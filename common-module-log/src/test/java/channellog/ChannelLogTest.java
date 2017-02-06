package channellog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.module.log.redis.ChannelDef;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.redis.pubsub.JedisPubSubForLogListener;
import com.module.redis.pubsub.JedisPubThread;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-redis.xml")
public class ChannelLogTest {
	
	
	@Test
	public void test() throws Exception {
		String logback = "123456789";
		String channel = ChannelDef.LOG_CHANNEL_PREFIX+logback;
		
		JedisPubThread jedisPubThread = new JedisPubThread(new JedisPubSubForLogListener(), new String[]{channel});
		new Thread(jedisPubThread).start();
		
		
		ChannelLogger logger = ChannelLoggerFactory.getLogger(ChannelLogTest.class, logback);
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			logger.debug("=======================日志，第"+i+"条=======================");
		}
		logger.returnRedisResource();
		
		
		jedisPubThread.punsubscribe(new String[]{channel});
	}
	
}


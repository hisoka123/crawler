package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.module.redis.pubsub.JedisPubSubForLogListener;
import com.module.redis.pubsub.JedisPubThread;

/**
 * @author kingly
 * @date 2016年4月26日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-redis.xml")
public class JedisPubTest {
	
	@Test
	public void test() throws Exception {
		JedisPubThread jedisPubThread = new JedisPubThread(new JedisPubSubForLogListener(), new String[]{"log.*", "news.*"});
		new Thread(jedisPubThread).start();
		
		Thread.sleep(20000);
		
		jedisPubThread.punsubscribe(new String[]{"log.*", "news.*"});
	}
}

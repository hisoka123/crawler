package test;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.module.redis.app.App;
import com.module.redis.dao.CookieDao;
import com.module.redis.domain.CookieJson;
import com.module.redis.service.CookieService;

public class RedisTest {
	 

	public static void main(String[] args) {
		
		/*ApplicationContext app = App.getInstance().getContext();
		
		//CookieDao cookieService = (CookieDao) app.getBean("cookieDao"); 
		//cookieService.queryByKey("aaaa");
		CookieService cookieService = (CookieService) app.getBean("cookieService"); 
		List<CookieJson> cookies = cookieService.queryAllCookies();
		System.out.println(cookies); */
		
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("a");
		hashSet.add("a");
		for (String string : hashSet) {
			System.out.println(string);
		}
	}

}

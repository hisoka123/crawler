/**
 * 
 */
package com.crawlermanage.service.linkedin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.linkedin.login.LinkedinLogin;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.domain.WebPageResponse;
import com.module.redis.domain.CookieJson;
import com.module.redis.service.CookieService;

/**
 * @author kingly
 * @date 2015年9月9日
 * 
 */
@Component
public class LinkedinLoginService {
	@Autowired
	private CookieService cookieService;
	
	public boolean login() {
		String url = "https://www.linkedin.com/uas/login";
		
		WebPageResponse response = null;
		try {
			response = LinkedinLogin.getInstance().getPageInlogin(url, true);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Collection<Cookie> cookies = response.getCookies();
		Set<CookieJson> cookieJsons = new HashSet<CookieJson>();
		for (Cookie cookie : cookies) {
			if (".www.linkedin.com".equals(cookie.getDomain())) {
				CookieJson cookieJson = new CookieJson();
				cookieJson.setDomain(cookie.getDomain());
				if (cookie.getExpires()!=null) cookieJson.setExpirationDate("" + cookie.getExpires().getTime());
				//cookieJson.setExpirationDate("1449998342.55868");
				cookieJson.setName(cookie.getName());
				cookieJson.setPath(cookie.getPath());
				cookieJson.setValue(cookie.getValue());
				cookieJson.setHostOnly("false");
				if ("JSESSIONID".equals(cookie.getName())) cookieJson.setHttpOnly("false"); else cookieJson.setHttpOnly("true");
				if ("bscookie".equals(cookie.getName())) cookieJson.setSecure("true"); else cookieJson.setSecure("false");
				cookieJson.setStoreId("0");
				cookieJson.setSession("false");
				cookieJsons.add(cookieJson);
			}
		}
		cookieService.deleteAllCookies();
		cookieService.save(cookieJsons);
		return true;
	}
	
}

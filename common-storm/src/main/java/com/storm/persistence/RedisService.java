package com.storm.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.module.redis.app.App;
import com.module.redis.dao.CookieDao;
import com.module.redis.domain.CookieJson;
import com.module.redis.service.CookieService;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class RedisService {
	
	private static final Logger log = LoggerFactory.getLogger(RedisService.class);
	
	private static RedisService instance;
	
	private ApplicationContext app ; 
	
	public RedisService(){
		if(app==null){
			app = App.getInstance().getContext();
		}
	} 
	
	public static RedisService getInstance() {
		if (instance == null) {
			synchronized (RedisService.class) {
				if (instance == null) {
					instance = new RedisService();
				}
			}
		}
		return instance;
	}
	
	public List<CookieJson> getCookiesByDomain(String domain){
		CookieService cookieService = (CookieService) app.getBean("cookieService"); 
		List<CookieJson> cookies = cookieService.queryByKey(CookieDao.LOGIN_COOKIES + domain);
		log.info(domain+"-----------getCookiesByDomain------"+cookies); 
		return cookies;
	}
	
	public List<CookieJson> getCookiesByDomainS(Collection<String> domains){
		CookieService cookieService = (CookieService) app.getBean("cookieService"); 
		List<CookieJson> cookieset = null;
		if(domains!=null)
			cookieset = new ArrayList<CookieJson>();
			for(String domain:domains){
				List<CookieJson> cookies = cookieService.queryByKey(CookieDao.LOGIN_COOKIES + domain);
				if(cookies!=null&&cookies.size()>0){
					cookieset.addAll(cookies);
					log.info(domain+"-----------getCookiesByDomainS ------"+cookies); 
				}  
			} 
		return cookieset;
	}
	
	public List<Cookie> gethtmlunitCookiesByDomainS(Collection<String> domains){ 
		List<CookieJson> cjs = getCookiesByDomainS(domains);
		return convert2HtmlunitCookie(cjs);
	}
	
	public List<Cookie> getAllCookies(){
		CookieService cookieService = (CookieService) app.getBean("cookieService"); 
		List<CookieJson> cookies = cookieService.queryAllCookies();
		log.info("getAllCookies------"+cookies); 
		return convert2HtmlunitCookie(cookies);
	}
	
	public void saveCookies(Collection<Cookie> cookies){
		CookieService cookieService = (CookieService) app.getBean("cookieService"); 
		if(cookies!=null){
			List<CookieJson> cjs = new ArrayList<CookieJson>();
			for(Cookie cookie:cookies){
				CookieJson cj = new CookieJson();
				if(cookie.getDomain()!=null){
					cj.setDomain(cookie.getDomain()); 
				}
				if(cookie.getExpires()!=null){
					cj.setExpirationDate(cookie.getExpires().getTime()+""); 
				}
				if(cookie.getName()!=null){
					cj.setName(cookie.getName());
				}
				if(cookie.getPath()!=null){
					cj.setPath(cookie.getPath()); 
				}
				if(cookie.getValue()!=null){
					cj.setValue(cookie.getValue()); 
				} 
				cjs.add(cj);
				log.info("cj.toString()---"+cj.toString());
			}
			cookieService.save(cjs);
		} 
	}
	
	public Map<String,String> convert2MapCookie(List<CookieJson> cookies){
		Map<String,String> cookiemap = null;
		if(cookies!=null&&cookies.size()>0){
			cookiemap = new HashMap<String,String>();
			for(CookieJson cj:cookies){
				cookiemap.put(cj.getName(), cj.getValue()); 
			}
		}
		return cookiemap;
	}
	
	public List<Cookie> convert2HtmlunitCookie(List<CookieJson> cookies){
		List<Cookie> htmlunitcookies = null;
		if(cookies!=null&&cookies.size()>0){
			htmlunitcookies = new ArrayList<Cookie>();
			for(CookieJson cj:cookies){
				Date expirationDate = null;
				boolean secure = "false".equals(cj.getSecure())?false:true;
				boolean httpOnly = "false".equals(cj.getHttpOnly())?false:true;
				Cookie cookie = new Cookie(cj.getDomain(), cj.getName(), cj.getValue(), cj.getPath(), expirationDate, secure, httpOnly);
				htmlunitcookies.add(cookie);
			}
		}
		return htmlunitcookies;
	}
	

}

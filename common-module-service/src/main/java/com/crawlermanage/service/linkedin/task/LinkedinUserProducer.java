/**
 * 
 */
package com.crawlermanage.service.linkedin.task;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.thrift7.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backtype.storm.generated.DRPCExecutionException;

import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawler.linkedin.htmlparser.LinkedinUserInfoParser;
import com.crawler.linkedin.login.LinkedinLogin;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.LogonParam;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.domain.WebPageResponse;
import com.module.drpc.service.CrawlerEngineService;
import com.module.htmlunit.definition.UtilDefinition;
import com.module.redis.domain.CookieJson;
import com.module.redis.service.CookieService;
import com.module.redis.service.LinkedinUserService;

/**
 * @author kingly
 * @date 2015年9月6日
 * 
 */
@Component
public class LinkedinUserProducer implements Runnable {
	@Autowired
	private CrawlerEngineService crawlerEngineService;
	@Autowired
	private LinkedinUserInfoParser linkedinUserInfoParser;
	@Autowired
	private LinkedinUserService linkedinUserService;
	@Autowired
	private CookieService cookieService;
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkedinUserProducer.class);
	
	private CrawlerTaskEvent crawlerTaskEvent;
	public CrawlerTaskEvent getCrawlerTaskEvent() {
		return crawlerTaskEvent;
	}
	public void setCrawlerTaskEvent(CrawlerTaskEvent crawlerTaskEvent) {
		this.crawlerTaskEvent = crawlerTaskEvent;
	}
	

	@Override
	public void run() {
		int i = 1;
		long start = 0;
		long end = 0;
		boolean needPrintPausing = true;
		
		ProducerOnceResult por = null;
		long profileContainerSize = 0;
		
		String threadName = Thread.currentThread().getName();
		LOGGER.info("The linkedinCrawlerTask of {} is start!", threadName);
		
		while (true) {
			if (crawlerTaskEvent==null) {
				LOGGER.info("The crawlerTaskEvent is null!");
				break;
			}
			if (crawlerTaskEvent.isAllTaskStop()) {
				LOGGER.info("{}: The linkedinCrawlerTask is stopped!", threadName);
				break;
			}
			if (crawlerTaskEvent.isAllTaskPause()) {
				if (needPrintPausing) {
					LOGGER.info("{}: The linkedinCrawlerTask is pausing!", threadName);
					needPrintPausing = false;
				}
				continue;
			}
			
			needPrintPausing = true;
			start = System.currentTimeMillis();
			
			synchronized (this) {
				if (!crawlerTaskEvent.isUserLoginedin()) {
					for (int j = 0; j < crawlerTaskEvent.getLoginAttemptTimes(); j++) { //尝试m次登录
						if (login()) {
							crawlerTaskEvent.setUserLoginedin(true);
							break;
						}
					}
				}
			}
			
			if (crawlerTaskEvent.isUserLoginedin()) {
				String profile = null;
				synchronized (this) {
					profile = linkedinUserService.spopUserProfile();
				}
				profileContainerSize = linkedinUserService.scard();
				if (profile==null) continue;
				System.err.println(threadName + " :Searching user " + i + " ...");
				LOGGER.info("{}: The current profile in LinkedinUserProducer thread is: {}", threadName, profile);
				LOGGER.info("{}: The current size of redis set linkedinUser_profiles is: {}", threadName, profileContainerSize);
				por = getUserHomepageInfo(profile);
				if (por==null) continue;
				
				List<String> profiles = por.getSimilarUserProfiles();
				if (profiles!=null && !crawlerTaskEvent.isAllTaskSaddProfilesPause()) {
					linkedinUserService.saddUserProfiles(profiles);
				} else if (profiles==null) {
					LOGGER.info("{}: {} 页面的相似会员为空！", threadName, profile);
				} else if (crawlerTaskEvent.isAllTaskSaddProfilesPause()) {
					//LOGGER.info("{}: AddProfile is pausing", threadName, profile);
				}
				
				UserFeedJson user = por.getUser();
				if (user==null) continue;
				String store_result = linkedinUserService.hmsetUser(user);
				if (!"OK".equals(store_result)) {
					LOGGER.error("{}: linkedinUserService.hmsetUser方法缓存LinkedUser时错误！", threadName);
				}
				i++;
			}
			
			try {Thread.sleep(crawlerTaskEvent.getRandomTaskInterval());} catch (InterruptedException e) {}///////////////////////////
			end = System.currentTimeMillis();
			LOGGER.info("{}:The consume time is: {} min", threadName, (end-start)/60000.0);
		}
	}
	
	
	public ProducerOnceResult getUserHomepageInfo(String url) {
		ProducerOnceResult por = null;
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.COOKIE_HANDLE_GETPAGE); 
		
		LogonParam logonParam = new LogonParam();
		logonParam.setUrl(url);
		logonParam.setUnit(UtilDefinition.HTMLUNIT);
		logonParam.setDomains(LinkedinUserInfoParser.COOKIE_DOMAINS);
		
		fcm.setLogonParam(logonParam);
		String param = fcm.toJson();
		
		try {
			String html = crawlerEngineService.execute(param);
			
			synchronized (this) {
				if (!html.contains(LinkedinLogin.getLoginUser().getNickname())) {
					crawlerTaskEvent.setUserLoginedin(false); //////////////////////////////////
				}
			}
			
			UserFeedJson user = linkedinUserInfoParser.userParser(html);
			List<String> similarUserProfiles = linkedinUserInfoParser.getSimilarUserProfiles(html);
			por = new ProducerOnceResult(user, similarUserProfiles);
		} catch (TException e) {
			e.printStackTrace();
		} catch (DRPCExecutionException e) {
			e.printStackTrace();
		}
		return por;
	}
	
	public void setInitUserProfile(String... profile) {
		linkedinUserService.saddUserProfiles(Arrays.asList(profile));
	}
	
	public boolean startNewProducerThread(String producerName) {
		Thread producer = new Thread(this);
		producer.setName(producerName);
		producer.start();
		return producer.isAlive();
	}
	
	
	public boolean login() {
		String url = "https://www.linkedin.com/uas/login";
		
		WebPageResponse response = null;
		try {
			LinkedinLogin linkedinLogin = LinkedinLogin.getInstance();
			LinkedinLogin.nextUser(); /////////////////////////////////////////////////////////////
			response = linkedinLogin.getPageInlogin(url, true);
			LOGGER.info("The user's nickname is: " + LinkedinLogin.getLoginUser().getNickname());
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

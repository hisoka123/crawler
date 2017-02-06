package com.crawlermanage.service.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.RssParam;
import com.crawler.storm.def.WebParam;
import com.module.drpc.service.CrawlerEngineService;
import com.module.htmlunit.unit.BaseUnit;
import com.module.rss.domain.FeedItem;
import com.module.rss.fetch.RssFetchService;


@Component
public class NewsFetchService {
	
	private static final Logger log = LoggerFactory.getLogger(NewsFetchService.class);
	
	private static final String CHARSET = "UTF-8";

    private static final String CHARSET_GB2312 = "gb2312";
	
	@Autowired
	private RssFetchService rssFetchService;
	
	@Autowired
	private CrawlerEngineService crawlerEngineService;
	 
	@Cacheable(value="dataCache", key="'NewsFetchService.searchNews_keyword=' + #keyword + '&domain=' + #domain + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<FeedItem>> searchNews(String keyword, String domain, boolean isDebug, String logback) {
		Result<List<FeedItem>> result =  null; 
        domain = domain.replaceFirst("http://","");
        domain = BaseUnit.encode(domain,CHARSET_GB2312);
//        keyword = BaseUnit.encode(keyword, CHARSET);
		String url = rssFetchService.getBaiduRssFeedUrl(keyword, domain);
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.RSS_FETCH); 
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		
		RssParam rssParam = new RssParam();
		rssParam.setUrl(url);  
	    fcm.setRssParam(rssParam);
	    String param = fcm.toJson();
	    log.info("searchNews param:{}",param);
	   
		try {
			String xml = crawlerEngineService.execute(param); 
			log.info("================================================================================");
			log.info(xml);
			log.info("================================================================================");
			List<FeedItem> feeds = rssFetchService.getBaiduRssFeed(xml); 
			result = new Result<List<FeedItem>>(feeds, xml, isDebug);
			log.info("feeds.size:{}",feeds.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
	}

}

package com.crawler.weibo.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.domain.json.HtmlJson;
import com.crawler.weibo.domain.json.TweetJson;

@Component
public class WeiboTopicParser extends WeiboStatusListParser{ 

	private static final Logger log = LoggerFactory.getLogger(WeiboTopicParser.class);
	
	public List<TweetJson> tweetScriptParser(String html){ 
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		HtmlJson feedList = scriptParser(html,"pl_weibo_direct",STK);
		
		log.info(feedList.getHtml());

		List<TweetJson> tweets = feedList==null?null:tweetFeedParser(feedList.getHtml());
		   
		return tweets;
		
	}
	

	public List<TweetJson> tweetFeedParser(String html){
		  
		List<TweetJson> tweets = new ArrayList<TweetJson>(); 
		Elements eles = Jsoup.parse(html).select("div[action-type=feed_list_item]");
		//log.info("TweetFeed.size------"+eles.size());
		if(eles!=null&&eles.size()>0){
			for(Element e:eles){
				TweetJson tweet = new TweetJson(); 
				Element e1 = getfirstChildElement(e,"p.comment_txt"); 
				//log.info("帖子正文 e1--->"+getHtml(e1));
				tweet.setContent_text(getElementText(e1));
				tweet.setContent_html(getElementHtml(e1));
				log.info("微博正文--->"+getElementText(e1)); 
				
				String tid = getElementAttr(e, "mid");
				log.info("微博ID --->"+tid);
				tweet.setTid(tid);//微博ID
				
				Element e3 = getfirstChildElement(e,"a:contains(转发) em"); 
				log.info("转发数 e3--->"+getHtml(e3));
				tweet.setReposts_count(getElementText(e3)); 
				log.info("转发数 --->"+getElementText(e3));
				
				Element e4 = getfirstChildElement(e,"a:contains(评论) em"); 
				log.info("评论数 e4--->"+getHtml(e4));
				tweet.setComments_count(getElementText(e4)); 
				log.info("评论数 --->"+getElementText(e4));
				
				Element e5 = getfirstChildElement(e,"a[title=赞]"); 
				log.info("点赞数 e5--->"+getHtml(e5));
				tweet.setAttitudes_count(getElementText(e5)); 
				log.info("点赞数 --->"+getElementText(e5));
				
				Element e6 = getfirstChildElement(e,"a[node-type=feed_list_item_date][suda-data]"); 
				//log.info("时间 e6--->"+getHtml(e6));
				tweet.setCreated_at(getElementAttr(e6, "title"));
				log.info("发帖时间 --->"+getElementAttr(e6, "title"));
				
				Element e7 = getfirstChildElement(e,"a[rel=nofollow][target=_blank]"); 
				log.info("来源 e7--->"+getHtml(e7));
				tweet.setSource(getElementText(e7));
				log.info(getElementText(e7));
				  
				//这里selector 避免了回帖的图片
				Element tweetimge = getfirstChildElement(e,"div[node-type=fl_pic_list]");  
				Elements e8 = getElements(tweetimge,"li.WB_pic img"); 
				log.info("微博图片--->"+e8);
				tweet.setPic_urls(getElementAttr(e8,"src")); 
				
				Element e9 = getfirstChildElement(e,"a:contains(转发)"); 
				log.info("发帖用户ID e9--->"+getHtml(e9));
				tweet.setUid(getUid(getElementAttr(e9, "action-data")));    
				tweet.setNickname(getNickname(getElementAttr(e9, "action-data")));
				log.info("=========================================================================="); 
				tweets.add(tweet);
				  
			}
		}
		
		return tweets;
	}
	
	public String getNickname(String text){
		String str = null;
		log.info("getNickname---"+text);
		if(text!=null&&text.indexOf("name=")!=-1){
			int beginIndex = text.indexOf("name=")+5; 
			int endIndex = text.indexOf("&", beginIndex); 
			str = text.substring(beginIndex,endIndex); 
		} 
		return str;
	}
	
	
	public String getUid(String text){
		String str = null;
		log.info("getUid---"+text);
		if(text!=null&&text.indexOf("uid=")!=-1){
			int beginIndex = text.indexOf("uid=")+4; 
			int endIndex = text.indexOf("&", beginIndex); 
			str = text.substring(beginIndex,endIndex); 
		} 
		return str;
	}
	

	
	
}

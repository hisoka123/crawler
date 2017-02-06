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
public class WeiboStatusListParser  extends AbstractWeiboParser {
	
	private static final Logger log = LoggerFactory.getLogger(WeiboStatusListParser.class);
	   
	public List<TweetJson> tweetScriptParser(String html){ 
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		HtmlJson userFeedList = scriptParser(html,"Pl_Official_MyProfileFeed",FM);
		
		//log.info(userFeedList.getHtml());

		List<TweetJson> tweets = userFeedList==null?new ArrayList<TweetJson>():tweetFeedParser(userFeedList.getHtml());
		   
		return tweets;
		
	}
	
	public List<TweetJson> tweetFeedParser(String html){
		  
		List<TweetJson> tweets = new ArrayList<TweetJson>(); 
		Elements eles = Jsoup.parse(html).select("div[action-type=feed_list_item]");
		//log.info("TweetFeed.size------"+eles.size());
		if(eles!=null&&eles.size()>0){
			for(Element e:eles){
				TweetJson tweet = new TweetJson(); 
				Element e1 = getfirstChildElement(e,"div.WB_text"); 
				//log.info("帖子正文 e1--->"+getHtml(e1));
				tweet.setContent_text(getElementText(e1));
				tweet.setContent_html(getElementHtml(e1));
				log.info("微博正文--->"+getElementText(e1));
				
				Element e2 = getfirstChildElement(e,"i[title=热门]"); 
				log.info("是否是热门贴--->"+getHtml(e2)); 
				if(e2==null){
					tweet.setHot(false);
				}else{
					tweet.setHot(true);
				}
				
				String isTop = getElementAttr(e, "feedtype");
				log.info("是否是置顶贴 --->"+isTop);
				if("top".equals(isTop)){
					tweet.setTop(true);
				}else{
					tweet.setTop(false);
				}
				
				String tid = getElementAttr(e, "mid");
				log.info("微博ID --->"+tid);
				tweet.setTid(tid);//微博ID
				
				Element e3 = getfirstChildElement(e,"span[node-type=forward_btn_text]"); 
				//log.info("转发数 e3--->"+getHtml(e3));
				tweet.setReposts_count(getRepostsCount(getElementText(e3))); 
				log.info("转发数 --->"+getRepostsCount(getElementText(e3)));
				
				Element e4 = getfirstChildElement(e,"span[node-type=comment_btn_text]"); 
				//log.info("评论数 e4--->"+getHtml(e4));
				tweet.setComments_count(getCommentsCount(getElementText(e4))); 
				log.info("评论数 --->"+getCommentsCount(getElementText(e4)));
				
				Element e5 = getfirstChildElement(e,"span[node-type=like_status] em"); 
				//log.info("点赞数 e5--->"+getHtml(e5));
				tweet.setAttitudes_count(getElementText(e5)); 
				log.info("点赞数 --->"+getElementText(e5));
				
				Element e6 = getfirstChildElement(e,"a[node-type=feed_list_item_date][suda-data]"); 
				//log.info("时间 e6--->"+getHtml(e6));
				tweet.setCreated_at(getElementAttr(e6, "title"));
				log.info("发帖时间 --->"+getElementAttr(e6, "title"));
				
				Element e7 = getfirstChildElement(e,"a[action-type=app_source][suda-uatrack]"); 
				log.info("来源 e7--->"+getHtml(e7));
				tweet.setSource(getElementText(e7));
				log.info(getElementText(e7));
				  
				//这里selector 避免了回帖的图片
				Element tweetimge = getfirstChildElement(e,"div[node-type=feed_list_content] ~ div[node-type=feed_list_media_prev]");  
				Elements e8 = getElements(tweetimge,"li.WB_pic img"); 
				log.info("微博图片--->"+e8);
				tweet.setPic_urls(getElementAttr(e8,"src")); 
				
				Element e9 = getfirstChildElement(e,"a[action-type=fl_favorite]"); 
				log.info("发帖用户ID e3--->"+getHtml(e3));
				tweet.setUid(getUserIdByUsercard(getElementAttr(e9, "diss-data"))); 
				log.info(getUserIdByUsercard(getElementAttr(e9, "diss-data")));
				
				
				Element retweete = getfirstChildElement(e,"div[node-type=feed_list_forwardContent]"); 
				Element nonetweet = getfirstChildElement(retweete,"div.empty_con");//回帖被删除了 
				if(nonetweet==null&&retweete!=null){
					log.info("---------- 回帖   ----------"); 
					log.info(retweete.html()); 
					TweetJson retweet = new TweetJson(); 
					Element re1 = getfirstChildElement(retweete,"div.WB_text");  
					retweet.setContent_text(getElementText(re1));
					retweet.setContent_html(getElementHtml(re1));
					log.info("转发微博正文--->"+getElementText(re1));
					
					Element re2 = getfirstChildElement(retweete,"div[mid]");  
					retweet.setTid(getElementAttr(re2,"mid")); 
					log.info("转发微博ID--->"+getElementAttr(re2,"mid"));
					
					Element re3 = getfirstChildElement(retweete,"div[mid] span:contains(转发)");  
					retweet.setReposts_count(getRepostsCount(getElementText(re3))); 
					log.info("转发微博转发数--->"+getRepostsCount(getElementText(re3)));
					
					Element re4 = getfirstChildElement(retweete,"div[mid] span:contains(评论)");  
					retweet.setComments_count(getCommentsCount(getElementText(re4))); 
					log.info("转发微博评论数--->"+getCommentsCount(getElementText(re4)));
					
					Element re4_1 = getfirstChildElement(re4,"a[href]");  
					retweet.setUrl(getElementAttr(re4_1, "href")); 
					log.info("转发微博地址--->"+getElementAttr(re4_1, "href"));
					
					Element re5 = getfirstChildElement(retweete,"div[mid] span[node-type=like_status] em");  
					retweet.setAttitudes_count(getElementText(re5)); 
					log.info("转发微博点赞数--->"+getElementText(re5));
					
					Element re6 = getfirstChildElement(retweete,"a[node-type=feed_list_item_date]");  
					retweet.setCreated_at(getElementAttr(re6, "title"));
					log.info("转发微博时间--->"+getElementAttr(re6, "title"));
					
					Element re7 = getfirstChildElement(retweete,"a[action-type=app_source]");  
					retweet.setSource(getElementText(re7));
					log.info("转发微博来源--->"+getElementText(re7));
					
					//这里selector 避免了回帖的图片
					Element retweetimge = getfirstChildElement(retweete,"div.media_box");  
					Elements re8 = getElements(retweetimge,"li.WB_pic img"); 
					log.info("转发微博图片--->"+re8);
					retweet.setPic_urls(getElementAttr(re8,"src")); 
					
					Element re9 = getfirstChildElement(retweete,"a[node-type=feed_list_originNick]");  
					retweet.setUid(getUserIdByUsercard(getElementAttr(re9, "usercard"))); 
					log.info("转发微博用户ID--->"+getUserIdByUsercard(getElementAttr(re9, "usercard")));
					retweet.setNickname(getElementAttr(re9,"nick-name"));
					log.info("转发微博用户昵称--->"+getElementAttr(re9,"nick-name"));
					
					tweet.setRetweet(retweet);
				}
				
				log.info("=========================================================================="); 
				tweets.add(tweet);
				  
			}
		}
		
		return tweets;
	}
	
	public String getRepostsCount(String text){ 
		String str = null;
		if(text!=null&&text.indexOf("转发")!=-1){
			int beginIndex = text.indexOf("转发")+2; 
			str = text.substring(beginIndex).trim(); 
		} 
		return str;
	}
	
	public String getCommentsCount(String text){ 
		String str = null;
		if(text!=null&&text.indexOf("评论")!=-1){
			int beginIndex = text.indexOf("评论")+2; 
			str = text.substring(beginIndex).trim(); 
		} 
		return str;
	}
	

}

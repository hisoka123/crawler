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
import com.crawler.weibo.domain.json.UserFeedJson;

@Component
public class WeiboUserFollowerListParser extends AbstractWeiboParser {

	private static final Logger log = LoggerFactory.getLogger(WeiboUserFollowerListParser.class);

	public List<UserFeedJson> followerScriptParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		HtmlJson userFeedList = scriptParser(html,"\"ns\":\"pl.content.followTab.index\"",FM);

		List<UserFeedJson> users = userFeedList==null?new ArrayList<UserFeedJson>():followerFeedParser(userFeedList.getHtml());
 
		return users;

	}
	
	
	
	public List<UserFeedJson> followerFeedParser(String html){
		  
		List<UserFeedJson> users = new ArrayList<UserFeedJson>(); 
		Elements eles = Jsoup.parse(html).select("li.follow_item");
		log.info("UserFeed.size------"+eles.size());
		//log.info("html---"+html);
		if(eles!=null&&eles.size()>0){
			for(Element e:eles){
				UserFeedJson uf = new UserFeedJson();
				Element e1 = getfirstChildElement(e,"dt.mod_pic > a"); 
				log.info("用户昵称 e1--->"+getHtml(e1));
				uf.setNickname(getElementAttr(e1,"title"));//用户昵称
				uf.setProfile(getWeiboFullUrl(getElementAttr(e1,"href")));//用户主页链接
				
				Element e2 = getfirstChildElement(e,"dt.mod_pic img"); 
				log.info("用户头像 e2--->"+getHtml(e2));
				uf.setUid(getUserIdByUsercard(getElementAttr(e2,"usercard")));//用户ID 
				uf.setProfile_image(getElementAttr(e2,"src"));//用户头像
				 
				Element e3 = getfirstChildElement(e,"span:contains(关注) a[target=_blank]"); 
				log.info("关注数 e3--->"+getHtml(e3));
				uf.setFollow(getElementText(e3));
				uf.setFollow_url(getWeiboFullUrl(getElementAttr(e3,"href")));
				
				Element e4 = getfirstChildElement(e,"span:contains(粉丝) a[target=_blank]");  
				log.info("粉丝数 e4--->"+getHtml(e4));
				uf.setFans(getElementText(e4));
				uf.setFans_url(getWeiboFullUrl(getElementAttr(e4,"href")));
				
				Element e5 = getfirstChildElement(e,"span:contains(微博) a[target=_blank]");  
				log.info("微博数 e5--->"+getHtml(e5));
				uf.setStatuses(getElementText(e5));
				uf.setStatuses_url(getWeiboFullUrl(getElementAttr(e5,"href")));
				
				Element e6 = getfirstChildElement(e,"i[title]");  
				log.info("用户认证 e6--->"+getHtml(e6));
				uf.setV(getElementAttr(e6,"title"));//用户认证
				
				Element e7 = getfirstChildElement(e,"i:not([title])");  
				log.info("性别 e7--->"+getHtml(e7));
				uf.setGender(getGender(getElementAttr(e7,"class")));//性别
				
				Element e8 = getfirstChildElement(e,"div.info_add span");    
				log.info("地理位置 e8--->"+getHtml(e8));
				uf.setLocation(getElementText(e8));//地理位置
				
				Element e9 = getfirstChildElement(e,"div.info_intro span");    
				log.info("人物介绍 e9--->"+getHtml(e9));
				uf.setPerson_info(getElementText(e9));//人物介绍
				
 
				
				log.info("==========================================================================");
				users.add(uf);
			}
		}
		return users;
	}
	
	

}

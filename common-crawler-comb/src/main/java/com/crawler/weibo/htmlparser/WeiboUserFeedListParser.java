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
public class WeiboUserFeedListParser extends AbstractWeiboParser {
	
	private static final Logger log = LoggerFactory.getLogger(WeiboUserFeedListParser.class);
	   
	public List<UserFeedJson> userScriptParser(String html){ 
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		HtmlJson userFeedList = scriptParser(html,"\"pid\":\"pl_user_feedList\"",STK);

		List<UserFeedJson> users = userFeedList==null?null:userFeedParser(userFeedList.getHtml()); 
		   
		return users;
		
	}

	public List<UserFeedJson> userFeedParser(String html){
		//log.info(html);
		List<UserFeedJson> users = new ArrayList<UserFeedJson>(); 
		Elements eles = Jsoup.parse(html).select("div.list_person");
		log.info("UserFeed.size------"+eles.size());
		if(eles!=null&&eles.size()>0){
			for(Element e:eles){
				log.info("e--->"+getHtml(e));
				UserFeedJson uf = new UserFeedJson();
				Element e1 = getfirstChildElement(e,"div.person_pic a[suda-data]"); 
				log.info("e1--->"+getHtml(e1));
				uf.setNickname(getElementAttr(e1,"title"));//用户昵称
				uf.setProfile(getElementAttr(e1,"href"));//用户主页链接    
				Element e2 = getfirstChildElement(e,"span:contains(关注) > a"); 
				log.info("e2--->"+getHtml(e2));
				uf.setFollow(getElementText(e2));
				uf.setFollow_url(getElementAttr(e2,"href"));
				uf.setUid(getUserIdByFollow(uf.getFollow_url()));//用户ID
				
				Element e3 = getfirstChildElement(e,"span:contains(粉丝) > a");  
				log.info("e3--->"+getHtml(e3));
				uf.setFans(getElementText(e3));
				uf.setFans_url(getElementAttr(e3,"href"));
				
				Element e4 = getfirstChildElement(e,"span:contains(微博) > a");  
				log.info("e4--->"+getHtml(e4));
				uf.setStatuses(getElementText(e4));
				uf.setStatuses_url(getElementAttr(e4,"href"));
				
				Element e5 = getfirstChildElement(e,"div.person_info p");  
				log.info("e5--->"+getHtml(e5));
				uf.setPerson_info(getElementText(e5));//用户简介
				
				Elements e6 = getElements(e,"p:contains(教育信息) > a");   
				uf.setPerson_edu(getElementText(e6));
				
				Elements e7 = getElements(e,"p:contains(职业信息) > a");   
				uf.setPerson_job(getElementText(e7));
				
				Elements e8 = getElements(e,"p:contains(标签) > a");  
				uf.setPerson_label(getElementText(e8));
				
				Element e9 = getfirstChildElement(e,"img.W_face_radius");  
				log.info("e9--->"+getHtml(e9));
				uf.setProfile_image(getElementAttr(e9,"src"));//用户头像
				
				Element e10 = getfirstChildElement(e,"a.approve_co,a.approve");  
				log.info("e10--->"+getHtml(e10));
				uf.setV(getElementAttr(e10,"title"));//用户认证
				
				Element e11 = getfirstChildElement(e,"span.m_icon");  
				log.info("e11--->"+getHtml(e11));
				uf.setGender(getElementAttr(e11,"title"));//性别
				
				Element e12 = getElementByIndex(e,"span",1);  
				log.info("e12--->"+getHtml(e12));
				uf.setLocation(getElementText(e12));//地理位置
				
				Element e13 = getfirstChildElement(e,"p.person_card");  
				log.info("e13--->"+getHtml(e13));
				uf.setPerson_card(getElementText(e13));//Person_card 
				
				log.info("==========================================================================");
				users.add(uf);
			}
		}
		return users;
	}
	
	
	

}

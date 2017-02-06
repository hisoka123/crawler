package com.crawler.weibo.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.domain.json.HtmlJson;
import com.crawler.htmlparser.AbstractParser;

public abstract class AbstractWeiboParser extends AbstractParser{
	
	public static final String STK = "STK";
	
	public static final String FM = "FM";
	
	public HtmlJson scriptParser(String html,String indexStr,String type){
		HtmlJson htmlJson = null;
		if(html!=null){
			Elements eles = Jsoup.parseBodyFragment(html).select("script"); 
			for(Element e:eles){
				String text = e.html(); 
				if(text.indexOf(indexStr)!=-1){ 
					if(type.equals(STK)){
						text = STKParser(text); 
						htmlJson = parser(text); 
					}
					if(type.equals(FM)){
						text = FMParser(text); 
						htmlJson = parser(text);
					} 
				} 
			}
		}
		return htmlJson;
	}
	
	public String STKParser(String text){ 
		String json = null;
		if(text!=null){ 
			text = text.replace("STK && STK.pageletM && STK.pageletM.view({", "{");
			text = text.replace("})", "}");
			json = text;
		} 
		return json; 
	}
	
	public String FMParser(String text){ 
		String json = null;
		if(text!=null){ 
			text = text.replace("FM.view({", "{");
			text = text.replace("})", "}");
			json = text;
		} 
		return json; 
	}

	public String getWeiboFullUrl(String href){
		if(href!=null){
			href = "http://weibo.com" + href ;
		}
		return href;
	}
	
	public String getUserIdByUsercard(String usercard){
		String uid = null;
		if(usercard!=null){ 
			int beginIndex = usercard.indexOf("=")+1;
			uid = usercard.substring(beginIndex);
		} 
		return uid;
	}
	
	public String getUserIdByFollow(String follow_url){
		String uid = null;
		if(follow_url!=null){ 
			int beginIndex = follow_url.indexOf("http://weibo.com/")+17;
			int endIndex =  follow_url.lastIndexOf("/follow");
			uid = follow_url.substring(beginIndex, endIndex);
		} 
		return uid;
	}
	
	public String getGender(String text){
		String gender = null;
		if(text!=null&&text.indexOf("female")!=-1){
			gender = "女";
		}
		if(text!=null&&text.indexOf("icon_male")!=-1){
			gender = "男";
		}
		return gender;
	}

}

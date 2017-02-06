package com.crawler.iautos.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.crawler.htmlparser.AbstractParser;
import com.crawler.iautos.domain.json.UserFeedJson;



@Component
public class IautosSearchListParser extends AbstractParser {

//解析html文件，返回对象
	public List<UserFeedJson>  userParser(String html){				
		List<UserFeedJson> users = new ArrayList<UserFeedJson>(); 
		Document doc = null;
		try {
			doc = Jsoup.parse(html,"utf-8");
			Element div = doc.getElementsByAttributeValue("class","principal-graphs").first();		
			
			if(div==null){		
				UserFeedJson user = new UserFeedJson();					
				Element uls=  doc.getElementsByAttributeValue("class","no-results-txt").first();
				user.setTipmessage(uls.text());
				users.add(user);
			}else{					
				 Elements uls=  div.select("ul");
				    Elements lis=uls.select("li");
						for (Element li : lis) {
							UserFeedJson user = new UserFeedJson();				
							String url=li.getElementsByTag("a").attr("abs:href");					
			                user.setUrl(url);
			                
			                String jpgurl=li.getElementsByTag("img").attr("abs:data-original");      
			             	 
			             	user.setProfile_image(jpgurl);
			            	String name=li.getElementsByTag("h3").text();	           
			            	user.setName(name);
			            	
			            	Element price= li.getElementsByAttributeValue("class","fl").first();
		       	
			            	user.setPrice(price.text());
			            	Element timekm=  li.getElementsByAttributeValue("class","fr").first();	           
			            	user.setTimekm(timekm.text());	            	
			            	Element  certificate=  li.getElementsByAttributeValue("class","brand-confirm").first();
			            	if (certificate==null) {
			            		user.setCertificate("");
							}else{
								user.setCertificate(certificate.text());
							}
			            	users.add(user);	            	
						} 
			}
	       
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
		
	}
	
}

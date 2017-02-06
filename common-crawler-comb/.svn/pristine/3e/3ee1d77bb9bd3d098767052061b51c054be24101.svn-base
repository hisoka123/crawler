package com.crawler.gsxt.htmlparser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.crawler.gsxt.domain.json.UserFeedJson;
import com.crawler.htmlparser.AbstractParser;



@Component
public class GsxtSearchListParser extends AbstractGsxtParser {


	public List<UserFeedJson>  userParser(String html){
				
		List<UserFeedJson> users = new ArrayList<UserFeedJson>(); 
		Document doc = null;
		String  url="http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!openEntInfo.dhtml?";
		try {
			doc = Jsoup.parse(html,"utf-8");
			org.jsoup.nodes.Element div = doc.getElementsByAttributeValue("class","list").first();

			Elements uls=  div.select("ul");
			
			for (org.jsoup.nodes.Element ul : uls) {		
			    UserFeedJson uf = new UserFeedJson();
			    Element title=  (Element) ul.getElementsByAttributeValue("class","font16").first();
			    Element detail=  (Element) ul.getElementsByAttributeValue("class","font14").first();	
			    Calendar c = Calendar.getInstance();
				long timeStamp = c.getTimeInMillis();
				
			    Element select = ul.select("a").first();
				String attr = select.attr("onclick");
				List<String> list = new AbstractParser() {}.getSubStringByRegex(attr, "\\w+");					
				String  pramString="entId="+list.get(1)+"&credit_ticket="+list.get(2)+"&entNo="+list.get(3)+"&timeStamp="+timeStamp;
				uf.setTitle(title.text());
				uf.setUrl(url+pramString);
				uf.setDetail(detail.text().replace("?", "")+" ");
				users.add(uf);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
		
	}
	
}

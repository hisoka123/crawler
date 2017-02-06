package com.module.rss.fetch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.module.rss.common.SourceEnum;
import com.module.rss.domain.FeedItem;

@Component
public class RssFetchService {
	
	private static final Logger log = LoggerFactory.getLogger(RssFetchService.class);
 
	public List<FeedItem> getBaiduRssFeed(String xml) throws Exception{
		if (StringUtils.isEmpty(xml)) {
			return null;
		}
		
		List<FeedItem> feeds = new ArrayList<FeedItem>();
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss",Locale.US); 
		Document doc = DocumentHelper.parseText(xml);
		List nodes = doc.selectNodes("//rss/channel/item");
		if(nodes!=null){
			for (Iterator iter = nodes.iterator(); iter.hasNext(); ) {
				Element itemEle = (Element) iter.next();
				FeedItem fi = new FeedItem();
				Node titleNode = itemEle.selectSingleNode("title");
				log.info("titleNode.getText()-----"+titleNode.getText());
				fi.setTitle(titleNode.getText());
				
				Node linkNode = itemEle.selectSingleNode("link");
				log.info("linkNode.getText()-----"+linkNode.getText());
				fi.setLink(linkNode.getText());
				
				Node descriptionNode = itemEle.selectSingleNode("description");
				log.info("descriptionNode.getText()-----"+descriptionNode.getText());
				fi.setSummary(descriptionNode.getText());
				
				Node pubDateNode = itemEle.selectSingleNode("pubDate");
				log.info("pubDateNode.getText()-----"+pubDateNode.getText());
				fi.setPubDate(formatter.parse(pubDateNode.getText()));
				
				Node sourceNode = itemEle.selectSingleNode("source");
				log.info("sourceNode.getText()-----"+sourceNode.getText());
				fi.setSource(sourceNode.getText());
			 
				feeds.add(fi);
				 
		    }
		}
		
		return feeds;
	}
	
	/*public String getGoogleRssFeedUrl(String keyword,String domain){
		StringBuffer url = new StringBuffer();
		url.append(SourceEnum.GOOGLE.getRssfeed()).append(keyword).append("+site:").append(domain);
		return url.toString();
	}*/
	
	public String getBaiduRssFeedUrl(String keyword,String domain){
		StringBuffer url = new StringBuffer(); 
		url.append(SourceEnum.BAIDU.getRssfeed()).append(keyword);
		if(domain!=null&&!"".equals(domain.trim())){
			url.append("+site:").append(domain);
		} 
		return url.toString();
	}
}

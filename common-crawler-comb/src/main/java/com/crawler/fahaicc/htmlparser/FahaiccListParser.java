/**
 * 
 */
package com.crawler.fahaicc.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.fahaicc.domain.json.FahaiItemFeed;


/**
 * @author kingly
 * @date 2016年2月26日
 * 
 */
@Component
public class FahaiccListParser extends AbstractFahaiccParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(FahaiccListParser.class);
	
	public List<FahaiItemFeed> searchListParser(String html){
		LOGGER.info("==============FahaiccListParser.searchListParser start！===============");
		if (StringUtils.isEmpty(html)) {
	        return null;
	    }
		
		List<FahaiItemFeed> fahaiItemFeeds = new ArrayList<FahaiItemFeed>();
		Document document = Jsoup.parseBodyFragment(html);
		
		Elements result1_divs = document.select("#result1");
		if (result1_divs!=null && !result1_divs.isEmpty()) { //第一页
			Element result1_div = result1_divs.get(0);
			Elements ele_dls = getElements(result1_div, "dl");
			if (ele_dls==null || ele_dls.isEmpty()) {
				return fahaiItemFeeds;
			}
			for (Element ele_dl : ele_dls) {
				FahaiItemFeed fahaiItemFeed = new FahaiItemFeed();
				
				Element ele_dt_a = getfirstChildElement(ele_dl, "dt a");
				String title = getElementText(ele_dt_a);
				String linkUrl = FAHAICC_HOME + getElementAttr(ele_dt_a, "href");
				Element ele_dd = getfirstChildElement(ele_dl, "dd");
				
				String content = null;
				String contentHtml = null;
				if (getElements(ele_dl, "dd").size()>1) {
					Element ele_dd2 = getElements(ele_dl, "dd").get(1);
					content = getElementText(ele_dd) + getElementText(ele_dd2);
					contentHtml = getElementHtml(ele_dd) + getElementHtml(ele_dd2);
				} else {
					content = getElementText(ele_dd);
					contentHtml = getElementHtml(ele_dd);
				}
				
				List<String> xxxDates = getSubStringByRegex(content, "\\d+/\\d+/\\d+");
				String xxxDate = null;
				if (xxxDates.size()>0) {
					xxxDate = xxxDates.get(0).replaceAll("&nbsp;", "").trim();
				}
				if (content.contains("开庭日期")) {
					fahaiItemFeed.setCourtDate(xxxDate);
				} else if (content.contains("审结日期")) {
					fahaiItemFeed.setConclusionDate(xxxDate);
				} else if (content.contains("立案时间")) {
					fahaiItemFeed.setFilingDate(xxxDate);
				} else if (content.contains("发布日期") || content.contains("发布时间")) {
					fahaiItemFeed.setPubDate(xxxDate);
				}
				
				String authority = getSubStringByRegex(content, "\u673a\u6784\u003a.*\u7c7b\u522b\u003a").get(0).replaceAll("&nbsp;|\u673a\u6784\u003a|\u7c7b\u522b\u003a", "").trim();
				fahaiItemFeed.setAuthority(authority);
				
				String type = getSubStringByRegex(getElementText(ele_dd), "\u7c7b\u522b\u003a.*$").get(0).replace("\u7c7b\u522b\u003a", "").trim();
				fahaiItemFeed.setType(type);
				
				fahaiItemFeed.setTitle(title);
				fahaiItemFeed.setLinkUrl(linkUrl);
				fahaiItemFeed.setContent(content);
				fahaiItemFeed.setContentHtml(contentHtml);
				fahaiItemFeeds.add(fahaiItemFeed);
			}
		} else { //其他页
			Elements ele_dls = document.select("body dl");
			if (ele_dls==null || ele_dls.isEmpty()) {
				return fahaiItemFeeds;
			}
			for (Element ele_dl : ele_dls) {
				FahaiItemFeed fahaiItemFeed = new FahaiItemFeed();
				
				Element ele_dt_a = getfirstChildElement(ele_dl, "dt a");
				String title = getElementText(ele_dt_a);
				String linkUrl = FAHAICC_HOME + getElementAttr(ele_dt_a, "href");
				Element ele_dd = getfirstChildElement(ele_dl, "dd");
				
				String content = null;
				String contentHtml = null;
				if (getElements(ele_dl, "dd").size()>1) {
					Element ele_dd2 = getElements(ele_dl, "dd").get(1);
					content = getElementText(ele_dd) + getElementText(ele_dd2);
					contentHtml = getElementHtml(ele_dd) + getElementHtml(ele_dd2);
				} else {
					content = getElementText(ele_dd);
					contentHtml = getElementHtml(ele_dd);
				}
				
				String xxxDate = getSubStringByRegex(content, "\\d+/\\d+/\\d+").get(0).replaceAll("&nbsp;", "").trim();
				if (content.contains("开庭日期")) {
					fahaiItemFeed.setCourtDate(xxxDate);
				} else if (content.contains("审结日期")) {
					fahaiItemFeed.setConclusionDate(xxxDate);
				} else if (content.contains("立案时间")) {
					fahaiItemFeed.setFilingDate(xxxDate);
				} else if (content.contains("发布日期") || content.contains("发布时间")) {
					fahaiItemFeed.setPubDate(xxxDate);
				}
				
				String authority = getSubStringByRegex(content, "\u673a\u6784\u003a.*\u7c7b\u522b\u003a").get(0).replaceAll("&nbsp;|\u673a\u6784\u003a|\u7c7b\u522b\u003a", "").trim();
				fahaiItemFeed.setAuthority(authority);
				
				String type = getSubStringByRegex(getElementText(ele_dd), "\u7c7b\u522b\u003a.*$").get(0).replace("\u7c7b\u522b\u003a", "").trim();
				fahaiItemFeed.setType(type);
				
				fahaiItemFeed.setTitle(title);
				fahaiItemFeed.setLinkUrl(linkUrl);
				fahaiItemFeed.setContent(content);
				fahaiItemFeed.setContentHtml(contentHtml);
				fahaiItemFeeds.add(fahaiItemFeed);
			}
		}
		return fahaiItemFeeds;
	}
	
}

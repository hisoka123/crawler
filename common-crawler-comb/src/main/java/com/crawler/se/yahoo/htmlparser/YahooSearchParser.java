package com.crawler.se.yahoo.htmlparser;

import com.crawler.se.yahoo.domin.json.SearchEngineYahoo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxz on 2016/1/11.
 */
@Component("yahooSearchParser")
public class YahooSearchParser extends AbstractSearchEngineParser{

    private static final Logger logger = LoggerFactory.getLogger(YahooSearchParser.class);

    public List<SearchEngineYahoo> searchListParser(String html){
        if (StringUtils.isEmpty(html)) {
            return null;
        }

        List<SearchEngineYahoo> SearchEngineYahooList = new ArrayList<SearchEngineYahoo>();

        logger.info("=================WikiParser.wikiListParser begin=================");
        Elements eles = Jsoup.parseBodyFragment(html).select("div#web");

        if(eles==null || eles.isEmpty()) {
            return SearchEngineYahooList;
        }

        Element ele = eles.get(0);
        Elements ele_results = getElements(ele, "ol>li");
        if (ele_results==null || ele_results.isEmpty()) {
            return SearchEngineYahooList;
        }

        for(Element ele_result:ele_results){
            String title = "";
            String brief = "";
            String linkUrl = "";
            Timestamp publishDate = null;
            String publishDateStr = "";
            boolean isPopularized = false;
            String quickPicUrl = "";
            String koubeiLink = "";

            SearchEngineYahoo SearchEngineYahoo =  new SearchEngineYahoo();

            Element titleEle = getfirstChildElement(ele_result, "h3.title a");
            Element briefEle = getfirstChildElement(ele_result,"div.compText");

            title = getElementText(titleEle);
            linkUrl =  getElementAttr(titleEle,"href");
            brief = getElementText(briefEle);


            SearchEngineYahoo.setTitle(title);
            SearchEngineYahoo.setContent(brief);
            SearchEngineYahoo.setLinkUrl(linkUrl);

            SearchEngineYahooList.add(SearchEngineYahoo);
        }
        logger.info("articleParser articles:{}", SearchEngineYahooList);

        return SearchEngineYahooList;
    }



}

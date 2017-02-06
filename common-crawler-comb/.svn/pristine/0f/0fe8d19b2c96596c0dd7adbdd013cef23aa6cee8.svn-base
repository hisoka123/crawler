package com.crawler.se.baidu.htmlparser;

import com.crawler.se.baidu.domin.json.SearchEngineBaidu;
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
@Component("baiduSearchParser")
public class BaiduSearchParser extends AbstractSearchEngineParser{

    private static final Logger logger = LoggerFactory.getLogger(BaiduSearchParser.class);

    public List<SearchEngineBaidu> searchListParser(String html){
        if (StringUtils.isEmpty(html)) {
            return null;
        }

        List<SearchEngineBaidu> searchEngineBaiduList = new ArrayList<SearchEngineBaidu>();

        logger.info("=================WikiParser.wikiListParser begin=================");
        Elements eles = Jsoup.parseBodyFragment(html).select("div#content_left");

        if(eles==null || eles.isEmpty()||eles.contains("<dt>")) {
            return searchEngineBaiduList;
        }

        Element ele = eles.get(0);
        Elements ele_results = getElements(ele, "div.c-container");
        if (ele_results==null || ele_results.isEmpty()) {
            return searchEngineBaiduList;
        }

        for(Element ele_result:ele_results){
            String title = "";
            String content = "";
            String linkUrl = "";
            Timestamp publishDate = null;
            String publishDateStr = "";
            boolean isPopularized = false;
            String quickPicUrl = "";
            String koubeiLink = "";

            SearchEngineBaidu searchEngineBaidu =  new SearchEngineBaidu();

            Element titleEle = getfirstChildElement(ele_result, "h3 a");
            Element briefEle = getfirstChildElement(ele_result,"div.c-row,div.c-abstract,table.op-tieba-general-maintable");

            title = getElementText(titleEle);
            linkUrl =  getElementAttr(titleEle,"href");
            content = getElementText(briefEle);
            /*if(null != content){
                content = content.replaceAll(title,"");
            }*/


            searchEngineBaidu.setTitle(title);
            searchEngineBaidu.setContent(content);
            searchEngineBaidu.setLinkUrl(linkUrl);

            searchEngineBaiduList.add(searchEngineBaidu);
        }
        logger.info("articleParser articles:{}", searchEngineBaiduList);

        return searchEngineBaiduList;
    }



}

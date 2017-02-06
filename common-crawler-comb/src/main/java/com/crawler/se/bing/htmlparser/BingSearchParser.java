package com.crawler.se.bing.htmlparser;

import com.crawler.se.bing.domin.json.SearchEngineBing;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxz on 2016/1/11.
 */
@Component("bingSearchParser")
public class BingSearchParser extends AbstractSearchEngineParser{

    private static final Logger logger = LoggerFactory.getLogger(BingSearchParser.class);

    public List<SearchEngineBing> searchListParser(String html){
        if (StringUtils.isEmpty(html)) {
            return null;
        }

        List<SearchEngineBing> SearchEngineBingList = new ArrayList<SearchEngineBing>();

        logger.info("=================WikiParser.wikiListParser begin=================");
        Elements eles = Jsoup.parseBodyFragment(html).select("ol#b_results");

        if(eles==null || eles.isEmpty()||eles.contains("<dt>")) {
            return SearchEngineBingList;
        }

        Element ele = eles.get(0);
        Elements ele_results = ele.select("ol#b_results>li");
        if (ele_results==null || ele_results.isEmpty()) {
            return SearchEngineBingList;
        }

        for (int i=0; i<ele_results.size(); i++) {
        	Element ele_result = ele_results.get(i);
        	Element ele_sprequery = getfirstChildElement(ele_result, "div#sp_requery"); //页码
        	Element ele_sb_count = getfirstChildElement(ele_result, "span.sb_count"); 	//搜索结果条数
        	Element ele_vlist2col = getfirstChildElement(ele_result, "div.b_vlist2col"); //相关搜索
        	if (i==ele_results.size()-1 
        			|| (ele_sprequery!=null && getElementText(ele_sprequery).contains("包含") && getElementText(ele_sprequery).contains("的结果。"))
        			|| (ele_sb_count!=null && getElementText(ele_sb_count).contains("条结果"))
        			|| (ele_vlist2col!=null && getElementText(getfirstChildElement(ele_result, "h2")).contains("的相关搜索"))) {
        		continue;
        	}
            String title = "";
            String content = "";
            String linkUrl = "";

            SearchEngineBing SearchEngineBing =  new SearchEngineBing();

            Element titleEle = getfirstChildElement(ele_result, "h2");
            Element linkUrlEle = getfirstChildElement(ele_result, "h2 a");

            Element briefEle = getfirstChildElement(ele_result,"div.b_caption,div.nodewrapper");

            title = getElementText(titleEle);
            linkUrl =  getElementAttr(linkUrlEle,"href");
            content = getElementText(briefEle);


            SearchEngineBing.setTitle(title);
            SearchEngineBing.setContent(content);
            SearchEngineBing.setLinkUrl(linkUrl);

            SearchEngineBingList.add(SearchEngineBing);
        }
        logger.info("articleParser articles:{}", SearchEngineBingList);

        return SearchEngineBingList;
    }



}

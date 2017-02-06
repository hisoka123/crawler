package com.crawler.se.haosou.htmlparser;

import com.crawler.se.haosou.domin.json.SearchEngineHaoSou;
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
@Component("haoSouSearchParser")
public class SearchParser extends AbstractSearchEngineParser {

    private static final Logger logger = LoggerFactory.getLogger(SearchParser.class);

    public List<SearchEngineHaoSou> searchListParser(String html){
        if (StringUtils.isEmpty(html)) {
            return null;
        }

        List<SearchEngineHaoSou> SearchEngineHaoSouList = new ArrayList<SearchEngineHaoSou>();

        logger.info("=================WikiParser.wikiListParser begin=================");
        Elements eles = Jsoup.parseBodyFragment(html).select("#m-result");

        if(eles==null || eles.isEmpty()||eles.contains("<dt>")) {
            return SearchEngineHaoSouList;
        }

        Element ele = eles.get(0);
        Elements ele_results = getElements(ele, "li.res-list");
        if (ele_results==null || ele_results.isEmpty()) {
            return SearchEngineHaoSouList;
        }

        for(Element ele_result:ele_results){
            String title = "";
            String content = "";
            String linkUrl = "";


            SearchEngineHaoSou SearchEngineHaoSou =  new SearchEngineHaoSou();

            Element titleEle = getfirstChildElement(ele_result, "h3");
            Element linkEle = getfirstChildElement(ele_result,"h3 a");
            Element briefEle = getfirstChildElement(ele_result,"p.res-desc,div.res-rich,div.res-desc");

            if(briefEle == null){
                briefEle = getfirstChildElement(ele_result,"div.res-comm-con");
            }

            title = getElementText(titleEle);
            linkUrl =  getElementAttr(linkEle,"href");
            content = getElementText(briefEle);


            SearchEngineHaoSou.setTitle(title);
            SearchEngineHaoSou.setContent(content);
            SearchEngineHaoSou.setUrlLink(linkUrl);

            SearchEngineHaoSouList.add(SearchEngineHaoSou);
        }
        logger.info("articleParser articles:{}", SearchEngineHaoSouList);

        return SearchEngineHaoSouList;
    }



}

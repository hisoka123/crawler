package com.crawler.se.sougou.htmlparser;

import com.crawler.se.sougou.domin.json.SearchEngineSouGou;
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
@Component("SouGouSearchParser")
public class SouGouSearchParser extends AbstractSearchEngineParser {

    private static final Logger logger = LoggerFactory.getLogger(SouGouSearchParser.class);

    public List<SearchEngineSouGou> searchListParser(String html){
        if (StringUtils.isEmpty(html)) {
            return null;
        }

        List<SearchEngineSouGou> SearchEngineSouGouList = new ArrayList<SearchEngineSouGou>();

        logger.info("=================SouGouSearchParser.searchListParser begin=================");
        Elements eles = Jsoup.parseBodyFragment(html).select("div.results");

        if(eles==null || eles.isEmpty()||eles.contains("<dt>")) {
            return SearchEngineSouGouList;
        }

        Element ele = eles.get(0);
        Elements ele_results = getElements(ele, "div.vrwrap,div.rb");
        if (ele_results==null || ele_results.isEmpty()) {
            return SearchEngineSouGouList;
        }

        for(Element ele_result:ele_results){
            String title = "";
            String content = "";
            String linkUrl = "";


            SearchEngineSouGou SearchEngineSouGou =  new SearchEngineSouGou();

            Element titleEle = getfirstChildElement(ele_result, "h3");
            Element linkEle = getfirstChildElement(ele_result,"h3 a");
            Element briefEle = getfirstChildElement(ele_result,"div.strBox,div.ft,div.tab-way,div.str-pd-box,div.str-box-v4");

            if(briefEle == null){
                briefEle = getfirstChildElement(ele_result,"div.res-comm-con");
            }

            title = getElementText(titleEle);
            linkUrl =  getElementAttr(linkEle,"href");
            content = getElementText(briefEle);
            if (StringUtils.isEmpty(title)) {
            	titleEle = getfirstChildElement(ele_result, "div.vrTitle");
            	title = getElementText(titleEle);
            	
            	linkEle = getfirstChildElement(titleEle, "a");
            	linkUrl =  getElementAttr(linkEle,"href");
            	
            	briefEle = getfirstChildElement(ele_result, "div.wx-table,div.wx-box-new");
            	content = getElementText(briefEle);
            }
            


            SearchEngineSouGou.setTitle(title);
            SearchEngineSouGou.setContent(content);
            SearchEngineSouGou.setUrlLink(linkUrl);

            SearchEngineSouGouList.add(SearchEngineSouGou);
        }
        logger.info("articleParser articles:{}", SearchEngineSouGouList);

        return SearchEngineSouGouList;
    }



}

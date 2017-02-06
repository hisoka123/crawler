package com.crawler.baidu.htmlparser;

import com.crawler.baidu.domain.json.BaiduWiki;
import com.crawler.baidu.domain.json.BaseInfo;
import com.crawler.baidu.domain.json.CatalogInfo;

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
 * Created by zxz on 2015/8/11.
 */
@Component
public class WikiParser extends AbsractWikiParser{
    private static final Logger logger = LoggerFactory.getLogger(AbsractWikiParser.class);


    public List<BaiduWiki> wikiListParser(String html){
    	if (StringUtils.isEmpty(html)) {
    		return null;
    	}

        List<BaiduWiki> baiduWikes = new ArrayList<BaiduWiki>();

        logger.info("=================WikiParser.wikiListParser begin=================");
        Elements eles = Jsoup.parseBodyFragment(html).select("dl.search-list");

        if(eles==null || eles.isEmpty()||eles.contains("<dt>")) {
            return baiduWikes;
        }

        Element ele = eles.get(0);
        Elements ele_results = getElements(ele, "dd");
        if (ele_results==null || ele_results.isEmpty()) {
            return baiduWikes;
        }

        for(Element ele_result:ele_results){
            String title;
            String link;
            String introduction;
            String lastUpdateDate;

            BaiduWiki baiduWiki =  new BaiduWiki();
            Element titleEle = getfirstChildElement(ele_result, "a.result-title");
            Element intrdEle = getfirstChildElement(ele_result,"p.result-summary");
            Element dateEle = getfirstChildElement(ele_result,"span.result-date");
            title = getElementText(titleEle);
            link =  getElementAttr(titleEle,"href");
            introduction = getElementText(intrdEle);
            lastUpdateDate = getElementText(dateEle);

            baiduWiki.setTitle(title);
            baiduWiki.setLink(link);
            baiduWiki.setIntroduction(introduction);
            baiduWiki.setLastUpdateDate(lastUpdateDate);

            baiduWikes.add(baiduWiki);
        }
        logger.info("articleParser articles:{}", baiduWikes);

        return baiduWikes;
    }




    /**
     * 分析词条详情页
     *
     * @param html
     * @return BaiduWiki
     */
    public BaiduWiki parseDetail(String url,String html) {
    	if (StringUtils.isEmpty(html)) {
    		return null;
    	}
    	
        BaiduWiki baiduWikes = new BaiduWiki();
        boolean status = true;

        logger.info("=================DetailParser.parseDetail begin=================");

        // -- get main-content


        Elements eles = Jsoup.parseBodyFragment(html).select("div.main-content");

        if (eles == null || eles.isEmpty() || eles.contains("<dt>")) {
            return baiduWikes;
        }

        baiduWikes = new BaiduWiki();
        BaseInfo[] baseInfoArrys = null;
        CatalogInfo[] catalogInfoArry = null;

        // title&summary&linkPic
        String title;
        String subTitle;
        String summary = "";
        String linkPic;


        Element ele = eles.get(0);
        Element titleEle = getfirstChildElement(ele, "dl dd h1");
        Element subTitleEle = getfirstChildElement(ele, "dl dd h2");
        Elements summariesDiv = getElements(ele, "div.lemma-summary div.para");
        Element linkPicEle = getfirstChildElement(ele, "div.para a.image-link img");

        for (Element summaryEle : summariesDiv) {
            summary = summary + getElementText(summaryEle) + "<br>";
        }

        title = getElementText(titleEle);
        subTitle = null == getElementText(subTitleEle) ?"":getElementText(subTitleEle);
        linkPic = getElementAttr(linkPicEle,"data-src");

        //information

        Element catalogInfoEle = getfirstChildElement(ele, "div.basic-info");
        Elements catalogInfoEles = getElements(catalogInfoEle, "dt.basicInfo-item");

        if(catalogInfoEles == null){
            status = false;
        }
        if(status) {
            baseInfoArrys = new BaseInfo[catalogInfoEles.size()];

            for (int i = 0; i < catalogInfoEles.size(); i++) {
                BaseInfo baseInfo = new BaseInfo();
                Element catalogInfoNameEle;
                Element catalogInfoValueEle;
                String name = "";
                String value = "";

                catalogInfoNameEle = catalogInfoEles.get(i);
                catalogInfoValueEle = catalogInfoEles.get(i).nextElementSibling();

                name = getElementText(catalogInfoNameEle);
                value = getElementText(catalogInfoValueEle);

                baseInfo.setTitle(name);
                baseInfo.setBiContent(value);
                baseInfo.setListOrder(i + 1);
                baseInfoArrys[i] = baseInfo;

            }
        }
        status = true;


        //content detail

        Element catalogParentEle = getfirstChildElement(ele, "div.lemma-catalog");
        Elements catalogEles = getElements(catalogParentEle, "li[class^=level]");

        if(status) {
            catalogInfoArry = new CatalogInfo[catalogEles.size()];
            int catalogInfoIndex = 0;
            for (Element catalogEle : catalogEles) {
                CatalogInfo catalogInfo = new CatalogInfo();

                String inPageLink;
                String parentInPageLink;
                int level;
                String titleofCatalogInfo;
                String content;


                Element inPageLinkEle = getfirstChildElement(catalogEle, "a");
                inPageLink = getElementAttr(inPageLinkEle, "href");
                parentInPageLink = inPageLink.substring(0, (inPageLink.length()) - 2);
                level = calculateLevel(inPageLink);
                titleofCatalogInfo = getElementText(inPageLinkEle);
                content = findContentOfCatalog(ele, inPageLink);

                catalogInfo.setInPageLink(inPageLink);
                catalogInfo.setParentInPageLink(parentInPageLink);
                catalogInfo.setCatalogLevel(level);
                catalogInfo.setTitle(titleofCatalogInfo);
                catalogInfo.setContent(content);

                catalogInfoArry[catalogInfoIndex] = catalogInfo;
                catalogInfoIndex++;
            }
        }

        //--get side-content
        Elements subEles = Jsoup.parseBodyFragment(html).select("div.side-content");
        Element subEle = subEles.get(0);

        // link pic

        if(linkPic == null) {
            Element picLink = getfirstChildElement(subEle, "div.summary-pic a img");
            linkPic = getElementAttr(picLink, "src");
        }


        //--load baiduWikes

        baiduWikes.setLink(url);
        baiduWikes.setTitle(title);
        baiduWikes.setSubTitle(subTitle);
        baiduWikes.setPicLink(linkPic);
        baiduWikes.setIntroduction(summary);
        baiduWikes.setBaseInfos(baseInfoArrys);
        baiduWikes.setCatalogInfos(catalogInfoArry);

        logger.info("articleParser articles:{}", baiduWikes);

        return baiduWikes;

    }


    /**
     * public mathod
     */

    /**
     * 计算目录级别
     *
     * @param inPageLnke
     * @return int
     */
    private int calculateLevel(String inPageLnke) {
        String[] inPageLinkArry = inPageLnke.split("#");
        return inPageLinkArry.length;
    }


    /**
     * 查找目录对应内容
     *
     * @param element
     * @param inPageLink
     * @return String
     */
    private String findContentOfCatalog(Element element, String inPageLink) {
        String content;

        inPageLink = inPageLink.replaceAll("#", "_");
        if ('_' == inPageLink.charAt(0)) {
            inPageLink = inPageLink.substring(1, inPageLink.length());
        }

        Element adivH2LEle = getfirstChildElement(element, "div.anchor-list a[name=" + inPageLink + "]");
        Element divH2Lele = adivH2LEle.parent();

        Element hLEle = divH2Lele.nextElementSibling();


        content = loopElementOfParaDiv(hLEle);

        return content;
    }


    private String loopElementOfParaDiv(Element element) {
        String content = "";
        if(element != null) {
            Element nextsubEle = element.nextElementSibling();
            if (nextsubEle != null) {
                String nodeName = nextsubEle.nodeName();
                if (!(nodeName.charAt(0) == 'h')) {
                    String nodeCssName = nextsubEle.attr("class");
                    if (!"anchor-list".equals(nodeCssName) && nodeCssName.indexOf("lemma-reference") == -1) {

                        //删除内容中照片元素
                        for( Element picEle : nextsubEle.select("div.lemma-picture,div.album-wrap") )
                        {
                            picEle.remove();
                        }

                        content = getElementHtml(nextsubEle);
                        System.out.println(content);
                        content = content + loopElementOfParaDiv(nextsubEle);
                        System.out.println("last content:"+content);
                    }
                }
            }
        }
        content = replaceLinkToBaiduLink(content);
        return content;
    }



    /**
     * solve baidu's problem of pic that prevent outer-link.
     * @param albumAddress
     * @return
     */





    /**
     * replace relative path to baike's absolute path
     */
    private String replaceLinkToBaiduLink(String content){
        content = content.replaceAll("href=\"/","href=\"http://baike.baidu.com/");
        return content;
    }



    /**
     *
     * @param albumAddress
     * @return
     */
    private String findInitPicLink(String albumAddress){
        String initPicAddress = "";
        if(albumAddress ==  null||"".equals(albumAddress)){
            return albumAddress;
        }

        return initPicAddress;
    }


//    public static void main (String argsp[]){
//        int a =  calculateLevel("1#1");
//        System.out.println("等级为:"+a);
//    }

}

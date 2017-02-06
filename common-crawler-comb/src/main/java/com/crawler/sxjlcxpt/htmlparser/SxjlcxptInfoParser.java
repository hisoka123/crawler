package com.crawler.sxjlcxpt.htmlparser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crawler.htmlparser.AbstractParser;
import com.crawler.sxjlcxpt.domain.json.CreditrRecordQueryPlatformJson;
import com.crawler.sxjlcxpt.domain.json.UserFeedJson;

@Component
public class SxjlcxptInfoParser extends AbstractParser{

	private static final Logger logger = LoggerFactory.getLogger(SxjlcxptInfoParser.class);

	
	public UserFeedJson parseResultHtml(String html) {
		UserFeedJson userFeedJson = new UserFeedJson();
		
		List<String> theadList = new ArrayList<String>();
		
		List<Map<String, String>> tbodyList = new ArrayList<Map<String, String>>();
		// 	不诚信信息搜索［搜索关键字： 武汉］
		//Element content = Jsoup.parseBodyFragment(html).getElementById("jjK1Title");
		//Element content1 = Jsoup.parseBodyFragment(html).getElementById("lblkeyword");
		//焦作市工商局：3批次服装抽检不合格
		//a href="../news/10036209-1-%ce%e4%ba%ba.shtml"
		//getElementsByAttributeValue("href","../news/10036209-1-%ce%e4%ba%ba.shtml");
		//Elements hre =Jsoup.parseBodyFragment(html).getElementsByAttributeValueContaining("href", "news");
	    //<table align="center" class="listTableClass" cellspacing="1" width="100%">
		//Elements eles = Jsoup.parseBodyFragment(html).select("span");
		//Jsoup.parseBodyFragment(html).getElementsByAttributeStarting
		//根据属性前缀获取元素 <li data-name="Peter Liu" data-city="ShangHai" data-lang="CSharp" data-food="apple">
        //doc.getElementsByAttributeStarting("data-");
		//Elements e=Jsoup.parseBodyFragment(html).getElementsByAttributeStarting("");
		//根据属性获取元素<a href="#"></a>
        //doc.getElementsByAttribute("href");
		//Elements e1=Jsoup.parseBodyFragment(html).getElementsByAttribute("href");
		//Element element=eles.get(2); 
		
		
		/*Elements hre =Jsoup.parseBodyFragment(html).getElementsByClass("lid");
		Element zongtabel=Jsoup.parseBodyFragment(html).getElementById("showstore");
		
		if(hre==null ) {
        	userFeedJson.setTipMessage("暂时没有数据！");
        } else {
        	//解析html中的数据
      
        	for(int i=0;i<hre.size();i++){
        		//<a href="../news/10036209-1.shtml" target="_blank" title="焦作市工商局：3批次服装抽检不合格">焦作市工商局：3批次服装抽检不合格</a><span>[2016年03月04日]</span>
        		Element element1=hre.get(i);
        		Element aElement = element1.select("a").get(0);
        		String hrefElement = getElementAttr(aElement, "href");
        		hrefElement=hrefElement.substring(2, hrefElement.length());
        		String hreftext =aElement.text();
        		Map<String, String> map = new TreeMap<String, String>(); 
        		//http://www.315cx.org.cn/news/10034618-1.shtml
        		map.put("herfsxjlcxpt", "http://www.315cx.org.cn"+hrefElement);
        		map.put("textsxjlcxpt", hreftext);
        		tbodyList.add(map);
        	}
        		
        }*/
		
		
		Element ele_showstore = Jsoup.parseBodyFragment(html).getElementById("showstore");
		Elements ele_tables = getElements(ele_showstore, "table");
		if (ele_tables==null || ele_tables.isEmpty()) {
			return userFeedJson;
		}
		for (Element ele_table : ele_tables) {			
			Elements hre =ele_table.getElementsByClass("lid");
			Element element1=hre.get(0);
			Element aElement = element1.select("a").get(0);
    		String hrefElement = getElementAttr(aElement, "href");
    		hrefElement=hrefElement.substring(2, hrefElement.length());
    		String hreftext =aElement.text();
    		
    		Elements gjz =ele_table.getElementsByClass("zi_12hei");
    		Element gjztext=gjz.get(0);
    		String gjztextt =gjztext.text();
    		Map<String, String> map = new TreeMap<String, String>(); 
    		//http://www.315cx.org.cn/news/10034618-1.shtml
    		map.put("herfsxjlcxpt", "http://www.315cx.org.cn"+hrefElement);
    		map.put("textsxjlcxpt", hreftext);
    		map.put("gjztext", gjztextt);
    		tbodyList.add(map);
			
		}
		
		userFeedJson.setTbody(tbodyList);
		
		return userFeedJson;
	}
	
//	public static void main(String[] args) {
//
//		Document doc;
//
//		try {
//
//		//获取文档
//
//		doc=Jsoup.connect("http://www.315cx.org.cn/search/?searchkey=%ce%e4%ba%ba&searchtype=1").get();
//
//		/*****获取单一元素******/
//
//		//与JS类似的根据ID选择的选择器<div id="content"></div>
//
//		Element content = doc.getElementById("content");
//
//		/*****一下方法的返回值都是Elements集合******/
//
//		//获取所有的a标签<a href="#"></a>
//
//		content.getElementsByTag("a");
//
//		//类选择器<div></div>
//
//		doc.getElementsByClass("divClass");
//
//		//获取Document的所有元素
//
//		doc.getAllElements();
//
//		//根据属性获取元素<a href="#"></a>
//
//		doc.getElementsByAttribute("href");
//
//		//根据属性前缀获取元素 <li data-name="Peter Liu" data-city="ShangHai" data-lang="CSharp" data-food="apple">
//
//		doc.getElementsByAttributeStarting("data-");
//
//		//根据key-value选择如<a href="http://xdemo.org"></a>
//
//		doc.getElementsByAttributeValue("href","http://xdemo.org");
//
//		//和上面的正好相反
//
//		doc.getElementsByAttributeValueNot("href","http://xdemo.org");
//
//		//根据key-value,其中value可能是key对应属性的一个子字符串，选择如<a href="http://xdemo.org"></a>
//
//		doc.getElementsByAttributeValueContaining("href", "xdemo");
//
//		//根据key-value,其中key对应值的结尾是value，选择如<a href="http://xdemo.org"></a>
//
//		doc.getElementsByAttributeValueEnding("href", "org");
//
//		//和上面的正好相反
//
//		doc.getElementsByAttributeValueStarting("href","http://xdemo");
//
//		//正则匹配，value需要满足正则表达式，<a href="http://xdemo.org"></a>,如href的值含有汉字
//
//		//doc.getElementsByAttributeValueMatching("href",Pattern.compile("[\u4e00-\u9fa5]"));
//
//		//同上
//
//		doc.getElementsByAttributeValueMatching("href", "[\u4e00-\u9fa5]");
//
//		//根据元素所在的z-index获取元素
//
//		doc.getElementsByIndexEquals(0);
//
//		//获取z-index大于x的元素
//
//		doc.getElementsByIndexGreaterThan(0);
//
//		//和上面的正好相反
//
//		doc.getElementsByIndexLessThan(10);
//
//		//遍历标签
//
//		for (Element link : content.getElementsByTag("a")) {
//
//		 String linkHref = link.attr("href");
//
//		 String linkText = link.text();
//
//		}
//
//		/**************一些其他常用的方法**************/
//
//		//获取网页标题
//
//		doc.title();
//
//		//获取页面的所有文本
//
//		doc.text();
//
//		//为元素添加一个css class
//
//		content.addClass("newClass");
//
//		//根据属性获取值
//
//		content.attr("id");
//
//		//获取所有子元素
//
//		content.children();
//
//		//获取元素内的所有文本
//
//		content.text();
//
//		//获取同级元素
//
//		content.siblingElements();
//
//		} catch (Exception e) {
//
//		e.printStackTrace();
//
//		}
//
//		}
	
//	s.replace(".", "#"));
	//<a href="../news/10036209-1.shtml" target="_blank" title="焦作市工商局：3批次服装抽检不合格">焦作市工商局：3批次服装抽检不合格</a><span>[2016年03月04日]</span>
	//../news/10036057-1.shtml
//	public static void main(String[] args) {
//		String s="../news/10036057-1.shtml";
//		String ss=s.substring(2, s.length());
//		System.out.println("www.baidu.com"+ss);
//		
//		
//	}
//	
	public List<CreditrRecordQueryPlatformJson> parseResultHtmlSxjlcxpt(
			String html) {
		List<CreditrRecordQueryPlatformJson> creditrRecordQueryPlatformJsons = new ArrayList<CreditrRecordQueryPlatformJson>();
		Element ele_showstore = Jsoup.parseBodyFragment(html).getElementById("showstore");
		if (ele_showstore != null) {
			Elements ele_tables = getElements(ele_showstore, "table");
			if (ele_tables != null && ele_tables.size() > 0) {
				int i = 0;
				for (Element ele_table : ele_tables) {
					i++;
					if (i > 1) {
						Elements hre = ele_table.getElementsByClass("lid");
						Element element1 = hre.get(0);
						Element aElement = element1.select("a").get(0);
						String hrefElement = getElementAttr(aElement, "href");
						hrefElement = hrefElement.substring(2,
								hrefElement.length());
						String hreftext = aElement.text();
						Elements gjz = ele_table.getElementsByClass("zi_12hei");
						Element gjztext = gjz.get(0);
						String gjztextt = gjztext.text();
						CreditrRecordQueryPlatformJson crqpj = new CreditrRecordQueryPlatformJson();
						crqpj.setCompanyTitle(gjztextt);// 企业标题
						crqpj.setKeywordDescription(hreftext);// 关键字描述
						crqpj.setContent("http://www.315cx.org.cn"
								+ hrefElement);// 内容
						creditrRecordQueryPlatformJsons.add(crqpj);
					}
					if (i == 6) {
						break;
					}
				}
			}
		}
		return creditrRecordQueryPlatformJsons;
	}

}

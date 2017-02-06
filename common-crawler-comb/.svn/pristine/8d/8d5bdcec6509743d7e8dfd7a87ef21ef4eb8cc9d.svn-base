/**
 * 
 */
package com.crawler.weixin.htmlparser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.weixin.domain.json.UserFeedJson;
import com.crawler.weixin.domain.json.WeixinArticle;

/**
 * @author kingly
 *
 */
@Component
public class WeixinArticleParser extends AbstractWeixinParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinArticleParser.class);
	
	public List<WeixinArticle> articleParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		LOGGER.info("=================WeixinArticleParser.articleParser begin=================");
		List<WeixinArticle> articles = new ArrayList<WeixinArticle>();
		
		Elements eles = Jsoup.parseBodyFragment(html).select("div.results");
		if(eles==null || eles.isEmpty()) {
			return articles;
		}
		
		Element ele = eles.get(0);
		Elements ele_results = getElements(ele, "div.wx-rb.wx-rb3");
		if (ele_results==null || ele_results.isEmpty()) {
			return articles;
		}
		
		for (Element ele_result : ele_results) {
			WeixinArticle article = new WeixinArticle();
			
			Element ele_txtbox_h4_a = getfirstChildElement(ele_result, "div.txt-box h4 a");
			String title = getElementText(ele_txtbox_h4_a);
			String link = getElementAttr(ele_txtbox_h4_a, "href");
			article.setTitle(title);
			article.setLink(link);
			
			Element ele_txtbox_p = getfirstChildElement(ele_result, "div.txt-box p");
			String content = getElementText(ele_txtbox_p);
			article.setContent(content);
			
			Element ele_sp = getfirstChildElement(ele_result, "div.s-p");
			String created_time = getElementAttr(ele_sp, "t");
			Date created_date = new Date(Long.parseLong(created_time)*1000);
			created_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(created_date);
			article.setCreated_time(created_time);
			
			UserFeedJson from = new UserFeedJson();
			Element ele_weixin_account = getfirstChildElement(ele_sp, "a#weixin_account");
			String name = getElementAttr(ele_weixin_account, "title");
			String openid = getElementAttr(ele_weixin_account, "i");
			String profile_image = WEIXIN_IMAGE_HOME + "/" + openid;
			from.setName(name);
			from.setOpenid(openid);
			from.setProfile_image(profile_image);
			
			List<String> bizStrs = getSubStringByRegex(link, "(?<=(__biz=)).*?(?=(&|$))"); //匹配以__biz开头，以&或结束符号结尾的字符串
			String biz = bizStrs.isEmpty() ? "" : bizStrs.get(0);
			String qr_code1 = WEIXIN_QR_CODE_HOME + "&__biz=" + biz + "&scene=10000001";
			String qr_code2 = WEIXIN_QR_CODE_HOME + "&__biz=" + biz + "&scene=10000004";
			
			if (!"".equals(biz)) {
				from.setQr_codes(new String[]{qr_code1, qr_code2});
			}
			article.setFrom(from);
			
			Element ele_ablank_img = getfirstChildElement(ele_result, "div.img_box2 a img");
			String pre_image = getElementAttr(ele_ablank_img, "src");
			article.setPre_image(pre_image);
			
			articles.add(article);
		}
		
		LOGGER.info("articleParser articles:{}", articles);
		
		return articles;
	}
	
}

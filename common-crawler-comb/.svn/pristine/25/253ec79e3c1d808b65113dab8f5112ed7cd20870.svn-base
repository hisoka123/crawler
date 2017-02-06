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

@Component
public class WeixinUserFeedListParser extends AbstractWeixinParser{

	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUserFeedListParser.class);
	
	public List<UserFeedJson> userParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		LOGGER.info("=================WeixinUserFeedListParser.userParser begin=================");
		List<UserFeedJson> users = new ArrayList<UserFeedJson>();
		
		Elements eles = Jsoup.parseBodyFragment(html).select("div.results");
		if(eles==null || eles.isEmpty()) {
			return users;
		}
		
		Element ele = eles.get(0);
		Elements ele_results = getElements(ele, "div.wx-rb.bg-blue.wx-rb_v1._item");
		if (ele_results==null || ele_results.isEmpty()) {
			return users;
		}
		
		for (Element ele_result : ele_results) {
			UserFeedJson user = new UserFeedJson();
			
			String openid = getElementAttr(ele_result, "href");
			openid = openid.substring(openid.indexOf("=")+1);
			openid = openid.substring(0, openid.indexOf('&'));
			user.setOpenid(openid);
			
			Element ele_img = getfirstChildElement(ele_result, "div.img-box img");
			String profile_image = getElementAttr(ele_img, "src");
			user.setProfile_image(profile_image);
			
			Element ele_txtbox_h3 = getfirstChildElement(ele_result, "div.txt-box h3");
			String name = getElementText(ele_txtbox_h3);
			user.setName(name);
			
			Element ele_txtbox_h4_span = getfirstChildElement(ele_result, "div.txt-box h4 span");
			String weixin_account = getElementText(ele_txtbox_h4_span);
			weixin_account = weixin_account.substring(weixin_account.indexOf("微信号：")+4);
			user.setWeixin_account(weixin_account);
			
			Element ele_psp3_funcinfo = getfirstChildElement(ele_result, "div.txt-box p.s-p3:contains(功能介绍：)");
			Element ele_psp3_sptxt = getfirstChildElement(ele_psp3_funcinfo, "span.sp-txt");
			String func_info = getElementText(ele_psp3_sptxt);
			user.setFunc_info(func_info);
			
			Element ele_psp3_authenInfo = getfirstChildElement(ele_result, "div.txt-box p.s-p3:contains(认证：)");
			Element ele_authenInfo = getfirstChildElement(ele_psp3_authenInfo, "span.sp-txt");
			String authen_info = getElementText(ele_authenInfo);
			user.setAuthen_info(authen_info);
			
			Element ele_psp3_lastarticle = getfirstChildElement(ele_result, "div.txt-box p.s-p3:contains(最近文章：)");
			if (ele_psp3_lastarticle!=null) {
				WeixinArticle article = new WeixinArticle();
				Element ele_psp3_sptxt_blue = getfirstChildElement(ele_psp3_lastarticle, "span.sp-txt a.blue");
				String ele_psp3_sptxt_blue_href = getElementAttr(ele_psp3_sptxt_blue, "href");
				String ele_psp3_sptxt_blue_title = getElementText(ele_psp3_sptxt_blue);
				article.setTitle(ele_psp3_sptxt_blue_title);
				article.setLink(ele_psp3_sptxt_blue_href);
				
				Element ele_hui_script = getfirstChildElement(ele_psp3_lastarticle, "span.hui script");
				String ele_hui_script_text = ele_hui_script==null ? null: ele_hui_script.html();
				String created_time = ele_hui_script_text==null ? null : getSubStringByRegex(ele_hui_script_text, "[0-9]{4,}").get(0);
				if (created_time!=null) {
					Date created_date = new Date(Long.parseLong(created_time)*1000);
					created_time = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(created_date);
					article.setCreated_time(created_time);
				}
				user.setArticle(article);
			}
			
			Element ele_posbox_img = getfirstChildElement(ele_result, "div.pos-ico div.pos-box img:eq(1)");
			String qr_code = getElementAttr(ele_posbox_img, "src");
			user.setQr_codes(new String[]{qr_code});
			users.add(user);
		}
		
		LOGGER.info("userParser users:{}", users);
		
		return users;
	}
	
}

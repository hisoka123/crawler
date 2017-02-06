package com.crawler.linkedin.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.linkedin.domain.json.UserFeedJson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

@Component
public class LinkedinUserFeedListParser extends AbstractLinkedinParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(LinkedinUserFeedListParser.class);
	
	public List<UserFeedJson> userSearchParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		LOGGER.info("=================LinkedinUserFeedListParser.userParser begin=================");
		List<UserFeedJson> users = new ArrayList<UserFeedJson>();
		
		List<String> list = getSubStringByRegex(html, "<code id=\"voltron_srp_main-content\" .*><!--.*--></code>");
		String resultJson = list.isEmpty() ? null : list.get(0);
		if (resultJson==null) return users;
		resultJson = resultJson.replaceAll("<code id=\"voltron_srp_main-content\" style=\"display:none;\"><!--.*\"results\":\\[\\{\"person\"", "\\{\"person\"").replaceAll("\\],\"i18n_looking_for_someone\".*--></code>", ""); 
		resultJson = resultJson.replaceAll("\\\\u", UTF8_MARK); //将gson不能解析的"\\\\u"字符先替换成能够解析的标识符,待解析完成后再替换回来    //gson会将特殊符号<>+=-等处理成utf-8编码
		resultJson = "[" + resultJson + "]";
		
		JsonArray jarray = null;
		try {
			jarray = new JsonParser().parse(resultJson).getAsJsonArray();
			if (jarray==null || jarray.isJsonNull() || jarray.size()==0) {
				return users;
			}
		} catch (JsonSyntaxException e) { //JsonSyntaxException <==> users's size is 0
			return users;
		}
		
		for (JsonElement jele : jarray) {
			String jeleStr = jele.toString();
			if (StringUtils.isEmpty(jeleStr) || !jeleStr.contains("\"person\":{") || !jeleStr.contains("\"firstName\":") || !jeleStr.contains("\"lastName\":")) continue;
			
			JsonElement jele_person = jele.getAsJsonObject().get("person");
			if (jele_person==null) continue; //与关键词相关的公司... 
			JsonObject root_person = jele_person.getAsJsonObject();
			UserFeedJson user = new UserFeedJson();
			
			//JsonElement jele_uid = root_person.get("id");
			//if (jele_uid!=null) user.setUid(jele_uid.getAsString());
			
			JsonElement jele_authToken = root_person.get("authToken");
			if (jele_authToken!=null) user.setAuthToken(jele_authToken.getAsString());
			
			JsonElement jele_firstName = root_person.get("firstName");
			String firstName = jele_firstName==null ? null : jele_firstName.getAsString();
			JsonElement jele_lastName = root_person.get("lastName");
			String lastName = jele_lastName==null ? null : jele_lastName.getAsString();
			String name = firstName + " " + lastName;
			if (" ".equals(name)) continue; //linkedin会员
			user.setName(name);
			
			JsonElement jele_profile = root_person.get("link_nprofile_view_3");
			if (jele_profile!=null) user.setProfile(jele_profile.getAsString());
			
			JsonElement jele_logo_result_base = root_person.get("logo_result_base");
			JsonElement jele_profile_img = jele_logo_result_base==null ? null : jele_logo_result_base.getAsJsonObject().get("media_picture_link");
			if (jele_profile_img != null) {
				String profile_img = jele_profile_img.getAsString();
				user.setProfile_img(profile_img);
			}
			
			JsonElement jele_headline = root_person.get("fmt_headline");
			if (jele_headline!=null) {
				String headline = jele_headline.getAsString();
				headline = parseHtmlCodeStrToString(headline);
				user.setHeadline(headline);
			}
			
			JsonElement jele_location = root_person.get("fmt_location");
			if (jele_location!=null) user.setLocation(jele_location.getAsString());
			
			JsonElement jele_industry = root_person.get("fmt_industry");
			if (jele_industry!=null) user.setIndustry(jele_industry.getAsString());
			
			JsonElement jele_position_root = root_person.get("snippets");
			if (jele_position_root!=null) {
				JsonArray jarr_position = jele_position_root.getAsJsonArray();
				if (jarr_position.size() != 0) {
					List<String> cur_positions = new ArrayList<String>();
					List<String> pre_positions = new ArrayList<String>();
					for (JsonElement jele_position : jarr_position) {
						JsonObject root_position = jele_position.getAsJsonObject();
						JsonElement jele_fieldName = root_position.get("fieldName");
						String fieldName = jele_fieldName==null ? null : jele_fieldName.getAsString();
						JsonElement jele_bodyList = root_position.get("bodyList");
						
						if (jele_bodyList==null) {
							if ("目前就职".equals(fieldName)) {
								JsonElement jele_body = root_position.get("body");
								JsonElement jele_heading = root_position.get("heading");
								String cur_position = jele_body==null ? null : jele_body.getAsString();
								cur_position = cur_position==null ? jele_heading.getAsString() : cur_position;
								cur_position = parseHtmlCodeStrToString(cur_position);
								cur_positions.add(cur_position);
							} else if ("曾经就职".equals(fieldName)) {
								JsonElement jele_body = root_position.get("body");
								JsonElement jele_heading = root_position.get("heading");
								String pre_position = jele_body==null ? null : jele_body.getAsString();
								pre_position = pre_position==null ? jele_heading.getAsString() : pre_position;
								pre_position = parseHtmlCodeStrToString(pre_position);
								pre_positions.add(pre_position);
							}
							continue;//
						}
						
						JsonArray jarr_position_body = jele_bodyList.getAsJsonArray();
						if ("目前就职".equals(fieldName)) {
							for (JsonElement jele_position_body : jarr_position_body) {
								String cur_position = jele_position_body.getAsString();
								cur_position = parseHtmlCodeStrToString(cur_position);
								cur_positions.add(cur_position);
							}
						} else if ("曾经就职".equals(fieldName)) {
							for (JsonElement jele_position_body : jarr_position_body) {
								String pre_position = jele_position_body.getAsString();
								pre_position = parseHtmlCodeStrToString(pre_position);
								pre_positions.add(pre_position);
							}
						}
					}
					user.setCur_positions(cur_positions);
					user.setPre_positions(pre_positions);
				}
			}
			//
			users.add(user);
		}
		
		for (int i=0; i<users.size(); i++) {
			LOGGER.info("user_"+(i+1)+":{}", users.get(i));
			//System.out.println("user_" + i + ":" + users.get(i));
		}
		return users;
	}
	
	
	public String parseHtmlCodeStrToString(String htmlCodeStr) {
		if (StringUtils.isEmpty(htmlCodeStr)) return htmlCodeStr;
		List<String> htmlCodes = getSubStringByRegex(htmlCodeStr, "\\&\\#[x]?[0-9a-fA-F]{3,5};");
		if (htmlCodes==null || htmlCodes.isEmpty()) return htmlCodeStr;
		String str = null; 
		for (int i = 0; i < htmlCodes.size(); i++) {
			String htmlCode = htmlCodes.get(i);
			if (htmlCode.contains("&#x")) {
				htmlCode = htmlCode.replace("&#x", "").replace(";", "");
				int codeValue = Integer.valueOf(htmlCode, 16);
				str = "" + (char)codeValue;
				htmlCodeStr = htmlCodeStr.replaceFirst("\\&\\#x[0-9a-fA-F]{3,5};", str);
			} else if (htmlCode.contains("&#")) {
				htmlCode = htmlCode.replace("&#", "").replace(";", "");
				int codeValue = Integer.parseInt(htmlCode);
				str = "" + (char)codeValue;
				htmlCodeStr = htmlCodeStr.replaceFirst("\\&\\#[0-9a-fA-F]{3,5};", str);
			}
		}
		return htmlCodeStr;
	}
	
}






package com.crawler.zhihu.htmlparser;

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

import com.crawler.zhihu.domain.json.UserFeedJson;
import com.crawler.zhihu.domain.json.ZhihuAction;
import com.crawler.zhihu.domain.json.ZhihuAnswer;
import com.crawler.zhihu.domain.json.ZhihuArticle;
import com.crawler.zhihu.domain.json.ZhihuQuestion;
import com.crawler.zhihu.domain.json.ZhihuTopic;

@Component
public class ZhihuUserFeedListParser extends AbstractZhihuParser{

	private static final Logger log = LoggerFactory.getLogger(ZhihuUserFeedListParser.class);
	  
	public UserFeedJson profileParser(String html){
		if (StringUtils.isEmpty(html)) { //html is null <==> Exception in storm
			return null;
		}
		
		UserFeedJson ufj = new UserFeedJson();
		Elements eles = Jsoup.parseBodyFragment(html).select("div.zu-main");
		if(eles!=null&&eles.size()>0){
			//ufj = new UserFeedJson();
			Element ele = eles.get(0); 
			Element ele_img = getfirstChildElement(ele, "img.zm-profile-header-img");
			String profileImage = getElementAttr(ele_img,"src");
			ufj.setProfile_image(profileImage);
			log.info("profileImage-------"+profileImage);
			
			Element ele_location = getfirstChildElement(ele, "div.zm-profile-header-user-describe span.location");
			if (ele_location!=null) {
				String location = getElementText(ele_location);
				ufj.setLocation(location);
				log.info("location-------"+location);
			}
			
			Element ele_name = getfirstChildElement(ele, "div.title-section span.name");
			String name = getElementText(ele_name);
			ufj.setName(name);
			log.info("name-------"+name);
			
			Element ele_bio = getfirstChildElement(ele, "div.title-section span.bio");
			String bio = getElementText(ele_bio);
			ufj.setBio(bio);
			log.info("bio-------"+bio);
			
			Element ele_profileinfo = getfirstChildElement(ele, "span.fold-item span.content");
			String profileinfo = getElementHtml(ele_profileinfo);
			ufj.setPerson_info(profileinfo);
			log.info("profileinfo-------"+profileinfo);
			
			Element ele_gender = getfirstChildElement(ele, "span.gender i.icon");
			if (ele_gender!=null) {
				String gender = getElementAttr(ele_gender, "class");
				ufj.setGender(getGender(gender));
				log.info("gender-------"+gender);
			}
			
			Element ele_profile = getfirstChildElement(ele, "a.zm-profile-header-user-detail");
			String profile = getElementAttr(ele_profile, "href");
			profile = "/people/" + profile.split("/")[2];
			ufj.setProfile(ZHIHU+profile);
			log.info("profile-------"+ZHIHU+profile);
			
			Element ele_agree_num = getfirstChildElement(ele, "span.zm-profile-header-user-agree strong");
			String agree_num = getElementText(ele_agree_num);
			ufj.setAgree_num(agree_num);
			log.info("agree_num-------"+agree_num);
			
			Element ele_thank_num = getfirstChildElement(ele, "span.zm-profile-header-user-thanks strong");
			String thank_num = getElementText(ele_thank_num);
			ufj.setThank_num(thank_num);
			log.info("thank_num-------"+thank_num);
			
			Element ele_ask_num = getfirstChildElement(ele, "a:contains(提问) span");
			String ask_num = getElementText(ele_ask_num);
			ufj.setAsk_num(ask_num);
			log.info("ask_num-------"+ask_num);
			
			Element ele_answers_num = getfirstChildElement(ele, "a:contains(回答) span");
			String answers_num = getElementText(ele_answers_num);
			ufj.setAnswers_num(answers_num);
			log.info("answers_num-------"+ask_num);
			
			Element ele_posts_num = getfirstChildElement(ele, "a:contains(专栏文章) span");
			String posts_num = getElementText(ele_posts_num);
			ufj.setPosts_num(posts_num);
			log.info("posts_num-------"+posts_num);
			
			Element ele_attention_num = getfirstChildElement(ele, "div.zm-profile-side-following a:contains(关注了) strong");
			log.info("ele_attention_num.html-------"+ele_attention_num.html());
			String attention_num = getElementText(ele_attention_num);
			ufj.setAttention_num(attention_num);
			log.info("attention_num-------"+attention_num);
			
			Element ele_followers_num = getfirstChildElement(ele, "div.zm-profile-side-following a:contains(关注者) strong");
			String followers_num = getElementText(ele_followers_num);
			ufj.setFollowers_num(followers_num);
			log.info("followers_num-------"+followers_num);
			
			Elements ele_skill_topics = getElements(ele, "div.skilled-topics div.item[data-url-token]");
			if(ele_skill_topics!=null&&ele_skill_topics.size()>0){
				List<ZhihuTopic> topics = new ArrayList<ZhihuTopic>();
				for(Element eTopic:ele_skill_topics){
					ZhihuTopic zt = new ZhihuTopic();
					Element eAvatar = getfirstChildElement(eTopic,"span.avatar img");
					String avatar = getElementAttr(eAvatar, "src");
					zt.setAvatar(avatar);
					
					Element et = getfirstChildElement(eTopic,"a[title]");
					String topic_name = getElementAttr(et, "title");
					zt.setName(topic_name); 
					String topic_link = getElementAttr(et, "href");
					zt.setLink(ZHIHU+topic_link);
					
					topics.add(zt);
				}
				ufj.setSkill_topics(topics);
			}
			
			//解析“回答”
			Elements ele_answers = getElements(ele, "#zh-profile-answers-inner-list .zm-profile-section-item.zg-clear");
			if (ele_answers!=null && !ele_answers.isEmpty()) {
				List<ZhihuAnswer> answers = new ArrayList<ZhihuAnswer>();
				for (Element ele_answer : ele_answers) {
					ZhihuAnswer answer = new ZhihuAnswer();
					ZhihuQuestion question = new ZhihuQuestion();
					
					Element ele_question_link = getfirstChildElement(ele_answer, "h2.zm-profile-question a.question_link");
					String ele_question_link_href = getElementAttr(ele_question_link, "href");
					String ele_question_link_text = getElementText(ele_question_link);
					String[] hrefEles = ele_question_link_href.split("/");
					answer.setId(hrefEles[4]);
					question.setId(hrefEles[2]);
					question.setTitle(ele_question_link_text);
					question.setLink(ZHIHU + "/question/" + question.getId());
					
					Element ele_vote_num = getfirstChildElement(ele_answer, ".zm-profile-vote-num");
					String ans_agree_num = getElementText(ele_vote_num);
					answer.setAgree_num(ans_agree_num);
					
					Element ele_ans_content = getfirstChildElement(ele_answer, "div.zm-profile-section-main>div");
					String ans_content = getElementText(ele_ans_content);
					answer.setContent(ans_content);
					
					answer.setQuestion(question);
					
					answers.add(answer);
				}
				ufj.setAnswers(answers);
			}
			
			//解析“提问”
			Elements ele_questions = getElements(ele, "#zh-profile-ask-inner-list .zm-profile-section-item.zg-clear");
			if (ele_questions!=null && !ele_questions.isEmpty()) {
				List<ZhihuQuestion> questions = new ArrayList<ZhihuQuestion>();
				for (Element ele_question : ele_questions) {
					ZhihuQuestion question = new ZhihuQuestion();
					Element ele_question_link = getfirstChildElement(ele_question, "h2.zm-profile-question a.question_link");
					String ele_question_link_href = getElementAttr(ele_question_link, "href");
					String ele_question_link_text = getElementText(ele_question_link);
					String[] hrefEles = ele_question_link_href.split("/");
					question.setId(hrefEles[2]);
					question.setLink(ZHIHU + ele_question_link_href);
					question.setTitle(ele_question_link_text);
					
					questions.add(question);
				}
				ufj.setQuestions(questions);
			}
			
			//按时间倒序解析“最新动态”
			Elements ele_actions = getElements(ele, "#zh-profile-activity-page-list .zm-profile-section-item");
			if (ele_actions!=null && !ele_actions.isEmpty()) {
				List<ZhihuAction> actions = new ArrayList<ZhihuAction>();
				for (Element ele_action : ele_actions) {
					ZhihuAction action = new ZhihuAction();
					Element ele_question_link = getfirstChildElement(ele_action, ".zm-profile-activity-page-item-main .question_link");
					Element ele_post_link = getfirstChildElement(ele_action, ".zm-profile-activity-page-item-main .post-link");
					if (ele_question_link!=null) { //赞同、关注、回答
						ZhihuAnswer answer = new ZhihuAnswer();
						ZhihuQuestion question = new ZhihuQuestion();
						
						Element ele_operation = getfirstChildElement(ele_action, "div.zm-profile-activity-page-item-main");
						String operationExplain = getElementText(ele_operation);
						if (operationExplain.contains("赞同了回答")) {
							action.setOperation(AGREE);
						} else if (operationExplain.contains("关注了问题")) {
							action.setOperation(FOLLOW);
						} else if (operationExplain.contains("回答了问题")) {
							action.setOperation(ANSWER);
						} else if (operationExplain.contains("提了一个问题")) {
							action.setOperation(ASK);
						}
						
						String ele_question_link_href = getElementAttr(ele_question_link, "href");
						String ele_question_link_text = getElementText(ele_question_link);
						String[] hrefEles = ele_question_link_href.split("/");
						question.setId(hrefEles[2]);
						question.setTitle(ele_question_link_text);
						question.setLink(ZHIHU + "/question/" + question.getId());
						
						Element ele_vote_num = getfirstChildElement(ele_action, "div.zm-item-vote a.zm-item-vote-count");
						String vote_num = getElementText(ele_vote_num);
						answer.setAgree_num(vote_num);
						
						Element ele_ans_content = getfirstChildElement(ele_action, "div.zm-item-rich-text[data-resourceid] textarea.content");
						String ans_content = getElementText(ele_ans_content);
						answer.setContent(ans_content);
						answer.setQuestion(question);
						action.setAnswer(answer);
						
						String ele_data_timestamp = getElementAttr(ele_action, "data-time");
						Date ele_data_date = new Date(Long.parseLong(ele_data_timestamp)*1000);
						String operate_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ele_data_date);
						action.setOperate_time(operate_time);
						
						actions.add(action);
					} else if (ele_post_link!=null) { //发表文章
						ZhihuArticle article = new ZhihuArticle();
						
						action.setOperation(POST);
						
						String ele_post_link_href = getElementAttr(ele_post_link, "href");
						String ele_post_link_text = getElementText(ele_post_link);
						String article_id = ele_post_link_href.substring(ele_post_link_href.lastIndexOf("/")+1);
						article.setId(article_id);
						article.setTitile(ele_post_link_text);
						article.setLink(ele_post_link_href);
						
						Element ele_vote_num = getfirstChildElement(ele_action, "div.zm-item-vote a.zm-item-vote-count");
						String vote_num = getElementText(ele_vote_num);
						article.setAgree_num(vote_num);
						
						Element ele_article_content = getfirstChildElement(ele_action, "div.zm-item-rich-text[data-resourceid] textarea.content");
						String article_content = getElementText(ele_article_content);
						article.setContent(article_content);
						
						String ele_data_timestamp = getElementAttr(ele_action, "data-time");
						Date ele_data_date = new Date(Long.parseLong(ele_data_timestamp)*1000);
						String operate_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ele_data_date);
						action.setOperate_time(operate_time);
						
						action.setArticle(article);
						actions.add(action);
					}
				}
				ufj.setActions(actions);
			}
			
		}
		
		return ufj;
	}
	
	
	/**
	 * 解析asks页面
	 * @param html asks页面
	 * @return 用户提问过的问题的list集合
	 */
	public List<ZhihuQuestion> asksParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		List<ZhihuQuestion> questions = new ArrayList<ZhihuQuestion>();
		
		Elements eles = Jsoup.parseBodyFragment(html).select("div#zh-profile-ask-list");
		if(eles==null || eles.isEmpty()){
			return questions;
		}
		
		Element ele = eles.get(0);
		Elements ele_questions = getElements(ele, "div.zm-profile-section-item");
		if (ele_questions==null || ele_questions.isEmpty()) {
			return questions;
		}
		
		for (Element ele_question : ele_questions) {
			ZhihuQuestion question = new ZhihuQuestion();
			
			Element ele_visits_num = getfirstChildElement(ele_question, "div.zm-profile-vote-num");
			String visits_num = getElementText(ele_visits_num);
			question.setVisits_num(visits_num);
			
			Element ele_metazggray = getfirstChildElement(ele_question, "div.meta.zg-gray");
			String ele_metazggray_text = getElementText(ele_metazggray);
			List<String> num_list = getSubStringByRegex(ele_metazggray_text, "[0-9]+");
			question.setAnswers_num(num_list.get(0));
			question.setFollowers_num(num_list.get(1));
			
			Element ele_question_title = getfirstChildElement(ele_question, "h2.zm-profile-question a.question_link");
			String ele_question_title_href = getElementAttr(ele_question_title, "href");
			String ele_question_title_text = getElementText(ele_question_title);
			question.setId(ele_question_title_href.split("/")[2]);
			question.setTitle(ele_question_title_text);
			question.setLink(ZHIHU + ele_question_title_href);
			
			questions.add(question);
		}
		return questions;
	}
	
	
	/**
	 * 解析answers页面
	 * @param html answers页面
	 * @return 用户回答的list集合
	 */
	public List<ZhihuAnswer> answersParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		List<ZhihuAnswer> answers = new ArrayList<ZhihuAnswer>();
		
		Elements eles = Jsoup.parseBodyFragment(html).select("div#zh-profile-answer-list");
		if (eles==null || eles.isEmpty()) {
			return answers;
		}
		
		Element ele = eles.get(0);
		Elements ele_answers = getElements(ele, "div.zm-item");
		if (ele_answers==null || ele_answers.isEmpty()) {
			return answers;
		}
		
		answers = new ArrayList<ZhihuAnswer>();
		for (Element ele_answer : ele_answers) {
			ZhihuAnswer answer = new ZhihuAnswer();
			ZhihuQuestion question = new ZhihuQuestion();
			
			Element ele_question_link = getfirstChildElement(ele_answer, "h2 a.question_link");
			String ele_question_link_href = getElementAttr(ele_question_link, "href");
			String ele_question_link_text = getElementText(ele_question_link);
			String[] textStrs = ele_question_link_href.split("/");
			String question_id = textStrs[2];
			String answer_id = textStrs[4];
			answer.setId(answer_id);
			question.setId(question_id);
			question.setLink(ZHIHU + "/question/" + question.getId());
			question.setTitle(ele_question_link_text);
			
			Element ele_agree_num = getfirstChildElement(ele_answer, "div.zm-item-vote a.zm-item-vote-count");
			String agree_num = getElementText(ele_agree_num);
			answer.setAgree_num(agree_num);
			
			Element ele_answer_content = getfirstChildElement(ele_answer, "div.zm-item-rich-text[data-resourceid] textarea.content");
			String answer_content = getElementText(ele_answer_content);
			answer.setContent(answer_content);
			
			Element ele_comments_num = getfirstChildElement(ele_answer, "div.zm-meta-panel a.meta-item.toggle-comment");
			String ele_comments_num_text = getElementText(ele_comments_num);
			String comments_num = "0";
			if (!"添加评论".equals(ele_comments_num_text)) {
				List<String> num_textstrs = getSubStringByRegex(ele_comments_num_text, "[0-9]+");
				comments_num = num_textstrs.isEmpty() ? "0" : getSubStringByRegex(ele_comments_num_text, "[0-9]+").get(0);
			}
			answer.setComments_num(comments_num);
			
			Element ele_created_time = getfirstChildElement(ele_answer, "div.zm-item-answer[data-created]");
			String created_timestamp = getElementAttr(ele_created_time, "data-created");
			Date created_date = new Date(Long.parseLong(created_timestamp)*1000);
			String created_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(created_date);
			answer.setCreated_time(created_time);
			
			answer.setQuestion(question);
			answers.add(answer);
		}
		return answers;
	}
	
	/**
	 * 解析followees页面
	 * @param html followees页面
	 * @return 用户主动关注的人物的集合
	 */
	public List<UserFeedJson> followeesParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		List<UserFeedJson> followees = new ArrayList<UserFeedJson>();
		
		Elements eles = Jsoup.parseBodyFragment(html).select("div.zh-general-list");
		if (eles==null || eles.isEmpty()) {
			return followees;
		}
		
		Element ele = eles.get(0);
		Elements ele_followees = getElements(ele, "div.zm-profile-section-item");
		if (ele_followees==null || ele_followees.isEmpty()) {
			return followees;
		}
		
		followees = new ArrayList<UserFeedJson>();
		for (Element ele_followee : ele_followees) {
			UserFeedJson followee = new UserFeedJson();
			
			Element ele_divh2_zglink = getfirstChildElement(ele_followee, "div.zm-list-content-medium h2.zm-list-content-title a.zg-link");
			String name = getElementText(ele_divh2_zglink);
			String profile = getElementAttr(ele_divh2_zglink, "href");
			followee.setName(name);
			followee.setProfile(profile);
			
			Element ele_zgbiggray = getfirstChildElement(ele_followee, "div.zg-big-gray");
			String bio = getElementText(ele_zgbiggray);
			followee.setBio(bio);
			
			Element ele_avatar_img = getfirstChildElement(ele_followee, "a.zm-item-link-avatar img.zm-item-img-avatar");
			String profile_image = getElementAttr(ele_avatar_img, "src");
			followee.setProfile_image(profile_image);
			
			Element ele_followers_num = getfirstChildElement(ele_followee, "div.details a.zg-link-gray-normal:contains(关注者)");
			String followers_num = getElementText(ele_followers_num);
			followers_num = StringUtils.isEmpty(followers_num) ? followers_num : getSubStringByRegex(followers_num, "[0-9]+").get(0);
			followee.setFollowers_num(followers_num);
			
			Element ele_ask_num = getfirstChildElement(ele_followee, "div.details a.zg-link-gray-normal:contains(提问)");
			String ask_num = getElementText(ele_ask_num);
			ask_num = StringUtils.isEmpty(ask_num) ? ask_num : getSubStringByRegex(ask_num, "[0-9]+").get(0);
			followee.setAsk_num(ask_num);
			
			Element ele_answers_num = getfirstChildElement(ele_followee, "div.details a.zg-link-gray-normal:contains(回答)");
			String answers_num = getElementText(ele_answers_num);
			answers_num = StringUtils.isEmpty(answers_num) ? answers_num : getSubStringByRegex(answers_num, "[0-9]+").get(0);
			followee.setAnswers_num(answers_num);
			
			Element ele_agree_num = getfirstChildElement(ele_followee, "div.details a.zg-link-gray-normal:contains(赞同)");
			String agree_num = getElementText(ele_agree_num);
			agree_num = StringUtils.isEmpty(agree_num) ? agree_num : getSubStringByRegex(agree_num, "[0-9]+").get(0);
			followee.setAgree_num(agree_num);
			
			followees.add(followee);
		}
		
		return followees;
	}
	
	public String getGender(String classname){
		
		String gender = null;
		
		if(classname.indexOf("icon-profile-male")!=-1){
			gender = "男";
		}
		
		if(classname.indexOf("icon-profile-female")!=-1){
			gender = "女";
		}
		
		return gender;
	}
	

	public List<UserFeedJson> userParser(String html){   
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		List<UserFeedJson> users = getUserFeedJsons(html);  
	  
		return users; 
	}
	
	
	public UserFeedJson getUserFeedJson(Element ele){
		UserFeedJson ufj = new UserFeedJson();  
		Element ele_img = getfirstChildElement(ele, "img.avatar");
		String profileImage = getElementAttr(ele_img,"src");
		ufj.setProfile_image(profileImage);
		
		Element ele_name = getfirstChildElement(ele, "a.name-link");
		String username = getElementText(ele_name);
		ufj.setName(username);
		String profileUrl = getElementAttr(ele_name,"href");
		ufj.setProfile(ZHIHU+profileUrl);
		
		Element ele_gender = getfirstChildElement(ele, "i");
		String gender = getElementAttr(ele_gender,"title");
		ufj.setGender(gender);
		
		Element ele_bio = getfirstChildElement(ele, "div.ellipsis");
		String bio = getElementText(ele_bio);
		ufj.setBio(bio);
		
		Element ele_answers_num = getfirstChildElement(ele, "a:contains(回答) strong");
		String answers_num = getElementText(ele_answers_num);
		ufj.setAnswers_num(answers_num);
		
		Element ele_posts_num = getfirstChildElement(ele, "a:contains(文章) strong");
		String posts_num = getElementText(ele_posts_num);
		ufj.setPosts_num(posts_num);
		
		Element ele_followers_num = getfirstChildElement(ele, "a:contains(关注者) strong");
		String followers_num = getElementText(ele_followers_num);
		ufj.setFollowers_num(followers_num);
	 
		
		return ufj;
	}
	
	public List<UserFeedJson> getUserFeedJsons(String html){  
		List<UserFeedJson> ufjs = new ArrayList<UserFeedJson>();
		Elements eles = Jsoup.parseBodyFragment(html).select("ul.users[data-paging] li.item");
		if(eles!=null&&eles.size()>0){
			for(Element ele:eles){
				UserFeedJson ufj = getUserFeedJson(ele);
				ufjs.add(ufj);
			}
		}
		return ufjs;
	}
	
}

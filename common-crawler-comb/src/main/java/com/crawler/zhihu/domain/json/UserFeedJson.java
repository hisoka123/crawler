package com.crawler.zhihu.domain.json;

import java.io.Serializable;
import java.util.List;

public class UserFeedJson implements Serializable{

	private static final long serialVersionUID = 4430479381582522500L;
	
	private String name;
	
	private String bio;
	
	private String person_info;
	
	private String profile_image;
	
	private String location;
	
	private String gender;
	
	private String profile;
	
	private String ask_num;
	
	private String answers_num;
	
	private String posts_num;
	
	private String attention_num;
	
	private String followers_num;
	
	private String agree_num;
	
	private String thank_num;
	
	private List<ZhihuTopic> skill_topics;
	
	private List<ZhihuAnswer> answers;//
	
	private List<ZhihuQuestion> questions;//
	
	private List<ZhihuAction> actions;//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerson_info() {
		return person_info;
	}

	public void setPerson_info(String person_info) {
		this.person_info = person_info;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAnswers_num() {
		return answers_num;
	}

	public void setAnswers_num(String answers_num) {
		this.answers_num = answers_num;
	}

	public String getPosts_num() {
		return posts_num;
	}

	public void setPosts_num(String posts_num) {
		this.posts_num = posts_num;
	}

	public String getFollowers_num() {
		return followers_num;
	}

	public void setFollowers_num(String followers_num) {
		this.followers_num = followers_num;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAsk_num() {
		return ask_num;
	}

	public void setAsk_num(String ask_num) {
		this.ask_num = ask_num;
	}

	public String getAttention_num() {
		return attention_num;
	}

	public void setAttention_num(String attention_num) {
		this.attention_num = attention_num;
	}

	public String getAgree_num() {
		return agree_num;
	}

	public void setAgree_num(String agree_num) {
		this.agree_num = agree_num;
	}

	public String getThank_num() {
		return thank_num;
	}

	public void setThank_num(String thank_num) {
		this.thank_num = thank_num;
	}

	public List<ZhihuTopic> getSkill_topics() {
		return skill_topics;
	}

	public void setSkill_topics(List<ZhihuTopic> skill_topics) {
		this.skill_topics = skill_topics;
	}

	public List<ZhihuAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<ZhihuAnswer> answers) {
		this.answers = answers;
	}

	public List<ZhihuQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ZhihuQuestion> questions) {
		this.questions = questions;
	}

	public List<ZhihuAction> getActions() {
		return actions;
	}

	public void setActions(List<ZhihuAction> actions) {
		this.actions = actions;
	}
	

}

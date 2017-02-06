/**
 * 
 */
package com.crawler.linkedin.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.linkedin.domain.json.AdditionalInfo;
import com.crawler.linkedin.domain.json.Article;
import com.crawler.linkedin.domain.json.Certification;
import com.crawler.linkedin.domain.json.ContactInfo;
import com.crawler.linkedin.domain.json.ContactInfo.WeChat;
import com.crawler.linkedin.domain.json.EducationInfo;
import com.crawler.linkedin.domain.json.Endorsement;
import com.crawler.linkedin.domain.json.Honoraward;
import com.crawler.linkedin.domain.json.JobInfo;
import com.crawler.linkedin.domain.json.Language;
import com.crawler.linkedin.domain.json.Organization;
import com.crawler.linkedin.domain.json.Patent;
import com.crawler.linkedin.domain.json.Project;
import com.crawler.linkedin.domain.json.Publication;
import com.crawler.linkedin.domain.json.School;
import com.crawler.linkedin.domain.json.Skill;
import com.crawler.linkedin.domain.json.TestScore;
import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawler.linkedin.domain.json.Volunteer;
import com.crawler.linkedin.util.IDGenerator;

/**
 * @author kingly
 *
 */

@Component
public class LinkedinUserInfoParser extends AbstractLinkedinParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkedinUserInfoParser.class);
	
	public UserFeedJson userParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		
		LOGGER.info("=================LinkedinUserInfoParser.userParser begin=================");
		UserFeedJson user = new UserFeedJson();
		
		Elements eles = Jsoup.parseBodyFragment(html).select("body");
		if (eles==null || eles.isEmpty()) {
			return user;
		}
		Element ele = eles.get(0);
		
		//基本资料
		Element ele_topcard = getfirstChildElement(ele, "div#top-card");
		if (ele_topcard!=null) {
			ContactInfo cont_info = new ContactInfo();
			
			//ele_procard
			Element ele_procard = getfirstChildElement(ele_topcard, "div.profile-card");
			
			Element ele_profile_img = getfirstChildElement(ele_procard, "div.profile-picture img");
			String profile_img = getElementAttr(ele_profile_img, "src");
			user.setProfile_img(profile_img);
			
			Element ele_proviewcont = getfirstChildElement(ele_topcard, "div.profile-overview-content");
			Element ele_name = getfirstChildElement(ele_proviewcont, "div#name span.full-name");
			String name = getElementText(ele_name);
			user.setName(name);
			
			Element ele_headline = getfirstChildElement(ele_proviewcont, "div#headline p");
			String headline = getElementText(ele_headline);
			user.setHeadline(headline);
			
			Element ele_location = getfirstChildElement(ele_proviewcont, "div#location a[name=location]");
			String location = getElementText(ele_location);
			user.setLocation(location);
			
			Element ele_industry = getfirstChildElement(ele_proviewcont, "div#location a[name=industry]");
			String industry = getElementText(ele_industry);
			user.setIndustry(industry);
			
			Elements ele_curpos = getElements(ele_proviewcont, "tr#overview-summary-current ol li");
			if (ele_curpos!=null && !ele_curpos.isEmpty()) {
				List<String> cur_positions = new ArrayList<String>();
				for (Element ele_curpo : ele_curpos) {
					Element ele_cur_position = getfirstChildElement(ele_curpo, "a");
					String cur_position = getElementText(ele_cur_position);
					cur_positions.add(cur_position);
				}
				user.setCur_positions(cur_positions);
			}
			
			Elements ele_prepos = getElements(ele_proviewcont, "tr#overview-summary-past ol li");
			if (ele_prepos!=null && !ele_prepos.isEmpty()) {
				List<String> pre_positions = new ArrayList<String>();
				for (Element ele_prepo : ele_prepos) {
					Element ele_pre_position = getfirstChildElement(ele_prepo, "a");
					String pre_position = getElementText(ele_pre_position);
					pre_positions.add(pre_position);
				}
				user.setPre_positions(pre_positions);
			}
			
			Elements ele_edus = getElements(ele_proviewcont, "tr#overview-summary-education ol li");
			if (ele_edus!=null && !ele_edus.isEmpty()) {
				List<String> bg_educations = new ArrayList<String>();
				for (Element ele_edu : ele_edus) {
					Element ele_bg_education = getfirstChildElement(ele_edu, "a");
					String bg_education = getElementText(ele_bg_education);
					bg_educations.add(bg_education);
				}
				user.setBg_educations(bg_educations);
			}
			
			//ele_procard_ext
			Element ele_procard_ext = getfirstChildElement(ele_topcard, "div.profile-card-extras");
			boolean hasEffectiveContInfo = false;
			//email
			Elements ele_emails = getElements(ele_procard_ext, "div#email-view ul li");
			if (ele_emails!=null && !ele_emails.isEmpty()) {
				List<String> emails = new ArrayList<String>();
				for (Element ele_email : ele_emails) {
					Element ele_email_a = getfirstChildElement(ele_email, "a");
					String email = getElementText(ele_email_a);
					emails.add(email);
				}
				cont_info.setEmails(emails);
				hasEffectiveContInfo = true;
			}
			//twitter
			Elements ele_twitters = getElements(ele_procard_ext, "div#twitter-view ul li");
			if (ele_twitters!=null && !ele_twitters.isEmpty()) {
				List<ContactInfo.Twitter> twitters = new ArrayList<ContactInfo.Twitter>();
				for (Element ele_twitter : ele_twitters) {
					ContactInfo.Twitter twitter = cont_info.new Twitter();
					Element ele_twitter_a = getfirstChildElement(ele_twitter, "a");
					twitter.account = getElementText(ele_twitter_a);
					twitter.profile = LINKEDIN + getElementAttr(ele_twitter_a, "href");
					twitters.add(twitter);
				}
				cont_info.setTwitters(twitters);
				hasEffectiveContInfo = true;
			}
			//wechat
			Element ele_divchat = getfirstChildElement(ele_procard_ext, "div#wechat");
			String qr_code = getElementAttr(ele_divchat, "data-qr2-url");
			if (!StringUtils.isEmpty(qr_code)) {
				qr_code = LINKEDIN + StringUtils.replace(qr_code, "Du4nMccn4R7KpuUgY0_5b", "SJW_aSgshkwzwomDKPZEw"); //
			}
			Element ele_divchat_a = getfirstChildElement(ele_divchat, "div#wechat-view a");
			String account = getElementText(ele_divchat_a);
			if (!StringUtils.isEmpty(account)) {
				WeChat weChat = cont_info.new WeChat();
				weChat.account = account;
				weChat.qr_code = qr_code;
				cont_info.setWechat(weChat);
				hasEffectiveContInfo = true;
			}
			//persite
			Elements ele_persites = getElements(ele_procard_ext, "div#website-view ul li");
			if (ele_persites!=null && !ele_persites.isEmpty()) {
				List<ContactInfo.PerSite> per_sites = new ArrayList<ContactInfo.PerSite>();
				for (Element ele_persite : ele_persites) {
					ContactInfo.PerSite perSite = cont_info.new PerSite();
					Element ele_persite_a = getfirstChildElement(ele_persite, "a");
					perSite.name = getElementText(ele_persite_a);
					perSite.link = LINKEDIN + getElementAttr(ele_persite_a, "href");
					per_sites.add(perSite);
				}
				cont_info.setPer_sites(per_sites);
				hasEffectiveContInfo = true;
			}
			
			if (!hasEffectiveContInfo) {
				cont_info = null;
			}
			user.setCont_info(cont_info);
			
			//profile
			Element ele_divproaction = getfirstChildElement(ele_topcard , "div.profile-actions[role=tablist]");
			Element ele_profile = getfirstChildElement(ele_divproaction, "a.view-public-profile");
			String profile = getElementAttr(ele_profile, "href");
			user.setProfile(profile);
		}
		
		
		//activity
		Element ele_activity = getfirstChildElement(ele, "div#activity");
		//posts-list
		Elements ele_articles = getElements(ele_activity, "div#influencer-posts ul li");
		if (ele_articles!=null && !ele_articles.isEmpty()) {
			List<Article> articles = new ArrayList<Article>();
			for (Element ele_article : ele_articles) {
				Article article = new Article();
				
				Element ele_link = getfirstChildElement(ele_article, "a.influencer-post-thumb");
				String link = getElementAttr(ele_link, "href");
				article.setLink(link);
				
				Element ele_preimg = getfirstChildElement(ele_link, "img");
				String pre_img = getElementAttr(ele_preimg, "src");
				article.setPre_img(pre_img);
				
				Element ele_title = getfirstChildElement(ele_article, "div.influencer-info-wrapper h5.influencer-post-title a");
				String title = getElementText(ele_title);
				article.setTitle(title);
				
				Element ele_pubdate = getfirstChildElement(ele_article, "div.influencer-info-wrapper span.influencer-post-published");
				String pub_date = getElementText(ele_pubdate);
				article.setPub_date(pub_date);
				
				articles.add(article);
			}
			user.setArticles(articles);
		}
		
		
		//background
		//background-summary-container/bg_summary
		Element ele_background = getfirstChildElement(ele, "div#background");
		Element ele_bgsummary = getfirstChildElement(ele_background, "div#background-summary-container div#summary-item-view p.description");
		String bg_summary = getElementText(ele_bgsummary);
		user.setBg_summary(bg_summary);
		if (ele_bgsummary!=null) {
			user.setBg_summary_html(ele_bgsummary.toString()); //有HTML格式
		}
		//background-experience-container/jobInfos
		Elements ele_jobInfos = getElements(ele_background, "div#background-experience-container div#background-experience div.editable-item.section-item");
		if (ele_jobInfos!=null && !ele_jobInfos.isEmpty()) {
			List<JobInfo> jobInfos = new ArrayList<JobInfo>();
			for (Element ele_jobInfo : ele_jobInfos) {
				JobInfo jobInfo = new JobInfo();
				
				//header
				Element ele_header = getfirstChildElement(ele_jobInfo, "header");
				Element ele_companyimg = getfirstChildElement(ele_header, "h5.experience-logo img");
				String company_img = getElementAttr(ele_companyimg, "data-li-src");
				jobInfo.setCompany_img(company_img);
				
				Element ele_position = getfirstChildElement(ele_header, "h4 a[name=title]");
				String position = getElementText(ele_position);
				jobInfo.setPosition(position);
				
				Element ele_company = getfirstChildElement(ele_header, "h5 a[dir=auto]");//
				String company = getElementText(ele_company);
				String company_link = LINKEDIN + getElementAttr(ele_company, "href");
				jobInfo.setCompany(company);
				jobInfo.setCompany_link(company_link);
				
				//experience-date-locale
				Element ele_datelocale = getfirstChildElement(ele_jobInfo, "span.experience-date-locale");
				String date_locale = getElementText(ele_datelocale);
				Element ele_locale = getfirstChildElement(ele_datelocale, "span.locality");
				String location = getElementText(ele_locale);
				String exp_date = date_locale;//
				if (!StringUtils.isEmpty(ele_locale)) {
					exp_date = StringUtils.replace(date_locale, location, "");
				}
				jobInfo.setLocation(location);
				if (!StringUtils.isEmpty(exp_date)) {
					jobInfo.setExp_date(exp_date);
				}
				
				//description
				Element ele_desc = getfirstChildElement(ele_jobInfo, "p.description");
				String jobInfo_desc = getElementText(ele_desc);
				jobInfo.setDesc(jobInfo_desc);
				if (ele_desc!=null) {
					jobInfo.setDesc_html(ele_desc.toString());
				}
				
				//education-associated
				Elements ele_associated_dts = getElements(ele_jobInfo, "dl.education-associated dt");
				if (ele_associated_dts!=null && !ele_associated_dts.isEmpty()) {
					for (int i=0; i<ele_associated_dts.size(); i++) {
						Element ele_associated_dt = ele_associated_dts.get(i);
						String associated_dt_flag = getElementText(ele_associated_dt);
						associated_dt_flag = associated_dt_flag==null ? "" : associated_dt_flag;
						
						//honorawards
						if (associated_dt_flag.contains("荣誉") || associated_dt_flag.contains("奖项")) {
							//Elements ele_honorawards = getElements(ele_jobInfo, "dl.education-associated dd.closed ul li"); //如果class=""，则匹配为.closed
							Elements ele_dd_honorawards = ele_jobInfo.select("dl.education-associated dd:eq(" + (2*i+1) + ")");
							Element ele_dd_honoraward = null;
							if (ele_dd_honorawards!=null && !ele_dd_honorawards.isEmpty()) {
								ele_dd_honoraward = ele_dd_honorawards.get(0);
							}
							Elements ele_honorawards = getElements(ele_dd_honoraward, "ul li");
							
							if (ele_honorawards!=null && !ele_honorawards.isEmpty()) {
								List<Honoraward> honorawards = new ArrayList<Honoraward>();
								for (Element ele_honoraward : ele_honorawards) {
									Honoraward honoraward = new Honoraward();
									
									Element ele_summary = getfirstChildElement(ele_honoraward, "h6 span");
									String summary = getElementText(ele_summary);
									honoraward.setSummary(summary);
									
									Element ele_hw_desc = getfirstChildElement(ele_honoraward, "p span");
									String desc = getElementText(ele_hw_desc);
									honoraward.setDesc(desc);
									
									honorawards.add(honoraward);
								}
								jobInfo.setHonorawards(honorawards);
							}
						}
						
						//endorsements
						if (associated_dt_flag.contains("推荐信")) {
							//Elements ele_endorsements = getElements(ele_jobInfo, "dl.education-associated dd.associated-endorsements ul li");
							Elements ele_dd_endorsements = ele_jobInfo.select("dl.education-associated dd:eq(" + (2*i+1) + ")");
							Element ele_dd_endorsement = null;
							if (ele_dd_endorsements!=null && !ele_dd_endorsements.isEmpty()) {
								ele_dd_endorsement = ele_dd_endorsements.get(0);
							}
							Elements ele_endorsements = getElements(ele_dd_endorsement, "ul li");
							
							
							if (ele_endorsements!=null && !ele_endorsements.isEmpty()) {
								List<Endorsement> endorsements = new ArrayList<Endorsement>();
								for (Element ele_endorsement : ele_endorsements) {
									Endorsement endorsement = new Endorsement();
									
									UserFeedJson endorser = new UserFeedJson();
									Element ele_endorser_prefile_img = getfirstChildElement(ele_endorsement, "a img");
									String endorser_prefile_img = getElementAttr(ele_endorser_prefile_img, "data-li-src");
									endorser.setProfile_img(endorser_prefile_img);
									
									Element ele_endorser = getfirstChildElement(ele_endorsement, "hgroup h5 a");
									String endorser_name = getElementText(ele_endorser);
									String endorser_profile = getElementAttr(ele_endorser, "href");
									endorser.setName(endorser_name);
									endorser.setProfile(endorser_profile);
									
									Element ele_endorser_headline = getfirstChildElement(ele_endorsement, "hgroup h6");
									String endorser_headline = getElementText(ele_endorser_headline);
									endorser.setHeadline(endorser_headline);
									endorsement.setEndorser(endorser);
									
									Element ele_endorser_content = getfirstChildElement(ele_endorsement, "p span");
									String endorser_content = getElementText(ele_endorser_content);
									endorsement.setContent(endorser_content);
									
									endorsements.add(endorsement);
								}
								jobInfo.setEndorsements(endorsements);
							}
						}
						
						//projects
						if (associated_dt_flag.contains("项目")) {
							Elements ele_dd_pros = ele_jobInfo.select("dl.education-associated dd:eq(" + (2*i+1) + ")");
							Element ele_dd_pro = null;
							if (ele_dd_pros!=null && !ele_dd_pros.isEmpty()) {
								ele_dd_pro = ele_dd_pros.get(0);
							}
							Elements ele_projects = getElements(ele_dd_pro, "ul li");
							if (ele_projects!=null && !ele_projects.isEmpty()) {
								List<Project> projects = new ArrayList<Project>();
								for (Element ele_project : ele_projects) {
									Project project = new Project();
									
									Element ele_project_name = getfirstChildElement(ele_project, "h6 span[dir=auto]");
									String project_name = getElementText(ele_project_name);
									project.setName(project_name);
									
									Element ele_project_desc = getfirstChildElement(ele_project, "p span");
									String project_desc = getElementText(ele_project_desc);
									project.setDesc(project_desc);
									
									projects.add(project);
								}
								jobInfo.setProjects(projects);
							}
						}
						//orgs
						if (associated_dt_flag.contains("组织")) {
							Elements ele_dd_orgs = ele_jobInfo.select("dl.education-associated dd:eq(" + (2*i+1) + ")");
							Element ele_dd_org = null;
							if (ele_dd_orgs!=null && !ele_dd_orgs.isEmpty()) {
								ele_dd_org = ele_dd_orgs.get(0);
							}
							Elements ele_orgs = getElements(ele_dd_org, "ul li");
							if (ele_orgs!=null && !ele_orgs.isEmpty()) {
								List<Organization> orgs = new ArrayList<Organization>();
								for (Element ele_org : ele_orgs) {
									Organization org = new Organization();
									
									Element ele_org_name = getfirstChildElement(ele_org, "h6 span");
									String org_name = getElementText(ele_org_name);
									org.setName(org_name);
									
									Element ele_org_desc = getfirstChildElement(ele_org, "p span");
									String org_desc = getElementText(ele_org_desc);
									org.setDesc(org_desc);
									
									orgs.add(org);
								}
								jobInfo.setOrgs(orgs);
							}
						}
						//testScores
						if (associated_dt_flag.contains("成绩")) {
							Elements ele_dd_testScores = ele_jobInfo.select("dl.education-associated dd:eq(" + (2*i+1) + ")");
							Element ele_dd_testScore = null;
							if (ele_dd_testScores!=null && !ele_dd_testScores.isEmpty()) {
								ele_dd_testScore = ele_dd_testScores.get(0);
							}
							Elements ele_testScores = getElements(ele_dd_testScore, "ul li");
							if (ele_testScores!=null && !ele_testScores.isEmpty()) {
								List<TestScore> testScores = new ArrayList<TestScore>();
								for (Element ele_testScore : ele_testScores) {
									TestScore testScore = new TestScore();
									
									Element ele_testScore_name = getfirstChildElement(ele_testScore, "h6 span");
									String testScore_name = getElementText(ele_testScore_name);
									testScore.setName(testScore_name);
									
									Element ele_testScore_score = getfirstChildElement(ele_testScore, "p span");
									String testScore_score = getElementText(ele_testScore_score);
									testScore.setScore(testScore_score);
									testScores.add(testScore);
								}
								jobInfo.setTestScores(testScores);
							}
						}
						//cources
						if (associated_dt_flag.contains("课程")) {
							Elements ele_dd_courses = ele_jobInfo.select("dl.education-associated dd:eq(" + (2*i+1) + ")");
							Element ele_dd_course = null;
							if (ele_dd_courses!=null && !ele_dd_courses.isEmpty()) {
								ele_dd_course = ele_dd_courses.get(0);
							}
							Elements ele_courses = getElements(ele_dd_course, "ul li");
							if (ele_courses!=null && !ele_courses.isEmpty()) {
								List<String> courses = new ArrayList<String>();
								for (Element ele_course : ele_courses) {
									String course = getElementText(ele_course);
									courses.add(course);
								}
								jobInfo.setCourses(courses);
							}
						}
						//
					}
				}
				jobInfos.add(jobInfo);
			}
			user.setJobInfos(jobInfos);
		}
		//background-honors-container/honorawards
		Elements ele_honorawards = getElements(ele_background, "div#background-honors-container div.editable-item");
		if (ele_honorawards!=null && !ele_honorawards.isEmpty()) {
			List<Honoraward> honorawards = new ArrayList<Honoraward>();
			for (Element ele_hw : ele_honorawards) {
				Honoraward honoraward = new Honoraward();
				
				Element ele_honoraward = getfirstChildElement(ele_hw, "div.honoraward");
				Element ele_honoraward_summary = getfirstChildElement(ele_honoraward, "h4.summary span");
				if (ele_honoraward_summary==null) {
					ele_honoraward_summary = getfirstChildElement(ele_honoraward, "p");
				}
				String honoraward_summary = getElementText(ele_honoraward_summary);
				honoraward.setSummary(honoraward_summary);
				
				Element ele_honoraward_org = getfirstChildElement(ele_honoraward, "h5 span");
				String honoraward_org = getElementText(ele_honoraward_org);
				honoraward.setOrg(honoraward_org);
				
				Element ele_honoraward_honors_date = getfirstChildElement(ele_honoraward, "span.honors-date");
				String honoraward_honors_date = getElementText(ele_honoraward_honors_date);
				honoraward.setHonors_date(honoraward_honors_date);
				
				Element ele_honoraward_desc = getfirstChildElement(ele_honoraward, "p.description");
				String honoraward_desc = getElementText(ele_honoraward_desc);
				honoraward.setDesc(honoraward_desc);
				if (ele_honoraward_desc!=null) {
					honoraward.setDesc_html(ele_honoraward_desc.toString());
				}
				
				honorawards.add(honoraward);
			}
			user.setHonorawards(honorawards);
		}
		//background-projects-container/projects
		Elements ele_projects = getElements(ele_background, "div#background-projects-container div.editable-item.section-item");
		if (ele_projects!=null && !ele_projects.isEmpty()) {
			List<Project> projects = new ArrayList<Project>();
			for (Element ele_project : ele_projects) {
				Project project = new Project();
				
				Element ele_project_a = getfirstChildElement(ele_project, "hgroup a");
				String project_link = getElementAttr(ele_project_a, "href");
				project_link = StringUtils.isEmpty(project_link) ? project_link : LINKEDIN + project_link;
				project.setLink(project_link);
				
				Element ele_project_name = getfirstChildElement(ele_project_a, "span[dir=auto]");
				ele_project_name = ele_project_name==null ? getfirstChildElement(ele_project, "hgroup h4 span[dir=auto]") : ele_project_name;
				String project_name = getElementText(ele_project_name);
				project.setName(project_name);
				
				Element ele_project_prodate = getfirstChildElement(ele_project, "span.projects-date time");
				String project_prodate = getElementText(ele_project_prodate);
				project_prodate = StringUtils.isEmpty(project_prodate) ? null : project_prodate;
				project.setPro_date(project_prodate);
				
				Element ele_project_desc = getfirstChildElement(ele_project, "p.description");
				String project_desc = getElementText(ele_project_desc);
				project.setDesc(project_desc);
				if (ele_project_desc!=null) {
					project.setDesc_html(ele_project_desc.toString());
				}
				
				Elements ele_members = getElements(ele_project, "dl.associated-list dd.associated-endorsements ul li");
				if (ele_members!=null && !ele_members.isEmpty()) {
					ArrayList<UserFeedJson> members = new ArrayList<UserFeedJson>();
					for (Element ele_member : ele_members) {
						UserFeedJson member = new UserFeedJson();
						
						Element ele_member_profile_img = getfirstChildElement(ele_member, "img");
						String member_profile_img = getElementAttr(ele_member_profile_img, "data-li-src");
						if (StringUtils.isEmpty(member_profile_img)) {
							member_profile_img = getElementAttr(ele_member_profile_img, "src");
						}
						member.setProfile_img(member_profile_img);
						
						Element ele_member_name_a = getfirstChildElement(ele_member, "hgroup h5 span a");
						String member_profile = getElementAttr(ele_member_name_a, "href");
						String member_name = getElementText(ele_member_name_a);
						member.setName(member_name);
						member.setProfile(member_profile);
						
						Element ele_member_headline = getfirstChildElement(ele_member, "h6");
						String member_headline = getElementText(ele_member_headline);
						if (StringUtils.isEmpty(member.getName())) {
							member.setName(member_headline);
						} else {
							member.setHeadline(member_headline);
						}
						
						members.add(member);
					}
					project.setMembers(members);
				}
				projects.add(project);
			}
			user.setProjects(projects);
		}
		//background-patents-container/patents
		Elements ele_patents = getElements(ele_background, "div#background-patents-container div#background-patents div.editable-item.section-item");
		if (ele_patents!=null && !ele_patents.isEmpty()) {
			List<Patent> patents = new ArrayList<Patent>();
			for (Element ele_patent : ele_patents) {
				Patent patent = new Patent();
				
				Element ele_patent_a = getfirstChildElement(ele_patent, "hgroup h4 a");
				Element ele_patent_h4 = getfirstChildElement(ele_patent, "hgroup h4");
				if (ele_patent_a!=null) {
					String patent_title = getElementText(ele_patent_a);
					patent_title = StringUtils.replace(patent_title, "(链接)", "");
					String patentURL = getElementAttr(ele_patent_a, "href");
					if (!StringUtils.isEmpty(patentURL)) {
						patentURL = LINKEDIN + patentURL;
					}
					patent.setTitle(patent_title);
					patent.setPatentURL(patentURL);
				} else {
					String patent_title = getElementText(ele_patent_h4);
					patent.setTitle(patent_title);
				}
				
				Element ele_patent_mark = getfirstChildElement(ele_patent, "hgroup h5 span");
				String patent_mark = getElementText(ele_patent_mark);
				patent.setPatentMark(patent_mark);
				
				Element ele_patent_date = getfirstChildElement(ele_patent, "span.patents-date");
				String patent_date = getElementText(ele_patent_date);
				if (patent_date!=null && patent_date.contains("颁发时间")) {
					String award_date = StringUtils.replace(patent_date, "颁发时间: ", "");
					patent.setAwardDate(award_date);
				}
				if (patent_date!=null && patent_date.contains("申请时间")) {
					String appli_date = StringUtils.replace(patent_date, "申请时间: ", "");
					patent.setAppliDate(appli_date);
				}
				
				Element ele_patent_desc = getfirstChildElement(ele_patent, "p.description");
				String patent_desc = getElementText(ele_patent_desc);
				patent.setDesc(patent_desc);
				if (ele_patent_desc!=null) {
					patent.setDesc_html(ele_patent_desc.toString());
				}
				
				Elements ele_patent_inventors = getElements(ele_patent, "dl.associated-list dd.associated-endorsements ul li");
				if (ele_patent_inventors!=null && !ele_patent_inventors.isEmpty()) {
					List<UserFeedJson> inventors = new ArrayList<UserFeedJson>();
					for (Element ele_patent_inventor : ele_patent_inventors) {
						UserFeedJson inventor = new UserFeedJson();
						
						Element ele_patent_inventor_profileimg = getfirstChildElement(ele_patent_inventor, "img");
						String patent_inventor_profileimg = getElementAttr(ele_patent_inventor_profileimg, "data-li-src");
						if (patent_inventor_profileimg==null) {
							patent_inventor_profileimg = getElementAttr(ele_patent_inventor_profileimg, "src");
						}
						inventor.setProfile_img(patent_inventor_profileimg);
						
						Element ele_patent_inventor_a = getfirstChildElement(ele_patent_inventor, "hgroup h5 span a");
						String patent_inventor_name = getElementText(ele_patent_inventor_a);
						if (patent_inventor_name==null) {
							Element ele_patent_inventor_name = getfirstChildElement(ele_patent_inventor, "h6");
							patent_inventor_name = getElementText(ele_patent_inventor_name);
						}
						String patent_inventor_profile = getElementAttr(ele_patent_inventor_a, "href");
						inventor.setName(patent_inventor_name);
						inventor.setProfile(patent_inventor_profile);
						
						Element ele_patent_inventor_headline = getfirstChildElement(ele_patent_inventor, "hgroup h6");
						String patent_inventor_headline = getElementText(ele_patent_inventor_headline);
						inventor.setHeadline(patent_inventor_headline);
						inventors.add(inventor);
					}
					patent.setInventors(inventors);
				}
				
				patents.add(patent);
			}
			user.setPatents(patents);
		}
		//background-organizations-container/parti_orgs
		Elements ele_parti_orgs = getElements(ele_background, "div#background-organizations-container div.editable-item:not(div#organization-additional-item)");
		if (ele_parti_orgs!=null && !ele_parti_orgs.isEmpty()) {
			List<Organization> parti_orgs = new ArrayList<Organization>();
			for (Element ele_parti_org : ele_parti_orgs) {
				Organization parti_org = new Organization();
				
				Element ele_parti_org_name = getfirstChildElement(ele_parti_org, "hgroup h4.summary a");
				String parti_org_name = getElementText(ele_parti_org_name);
				parti_org.setName(parti_org_name);
				
				Element ele_parti_org_role = getfirstChildElement(ele_parti_org, "hgroup h5 span");
				String parti_org_role = getElementText(ele_parti_org_role);
				parti_org.setRole(parti_org_role);
				
				Element ele_org_date = getfirstChildElement(ele_parti_org, "span.organizations-date time");
				String org_date = getElementText(ele_org_date);
				parti_org.setOrg_date(org_date);
				
				Element ele_parti_org_desc = getfirstChildElement(ele_parti_org, "p.description");
				String parti_org_desc = getElementText(ele_parti_org_desc);
				parti_org.setDesc(parti_org_desc);
				if (ele_parti_org_desc!=null) {
					parti_org.setDesc_html(ele_parti_org_desc.toString());
				}
				
				parti_orgs.add(parti_org);
			}
			
			Elements ele_parti_addorgs = getElements(ele_background, "div#background-organizations-container div#organization-additional-item div.organization p a");
			if (ele_parti_addorgs!=null && !ele_parti_addorgs.isEmpty()) {
				for (Element ele_parti_addorg : ele_parti_addorgs) {
					Organization parti_addorg = new Organization();
					
					String parti_addorg_name = getElementText(ele_parti_addorg);
					if (StringUtils.isEmpty(parti_addorg_name)) continue; 
					parti_addorg.setName(parti_addorg_name);
					parti_orgs.add(parti_addorg);
				}
			}
			
			user.setParti_orgs(parti_orgs);
		}
		//background-volunteering-container/volunteer
		Element ele_volunteer = getfirstChildElement(ele_background, "div#background-volunteering-container");
		Elements ele_partiorgs = getElements(ele_volunteer, "div.editable-item.section-item:has(div.experience)"); //匹配包含子孙元素的元素
		if (ele_volunteer!=null && ele_volunteer.hasText()) {
			Volunteer volunteer = new Volunteer();
			
			if (ele_partiorgs!=null && !ele_partiorgs.isEmpty()) {
				ArrayList<Organization> parti_orgs = new ArrayList<Organization>();
				for (Element ele_partiorg : ele_partiorgs) {
					if (ele_partiorg==null) continue;
					Organization parti_org = new Organization();
					
					Element ele_partiorg_role = getfirstChildElement(ele_partiorg, "hgroup h4 span");
					String partiorg_role = getElementText(ele_partiorg_role);
					parti_org.setRole(partiorg_role);
					
					Element ele_partiorg_name = getfirstChildElement(ele_partiorg, "hgroup h5 a");
					String partiorg_name = getElementText(ele_partiorg_name);
					parti_org.setName(partiorg_name);
					
					Element ele_partiorg_orgdate = getfirstChildElement(ele_partiorg, "span.volunteering-date-cause");
					String partiorg_orgdate = getElementText(ele_partiorg_orgdate);
					
					Element ele_partiorg_cate = getfirstChildElement(ele_partiorg, "span.volunteering-date-cause span.locality");
					String partiorg_cate = getElementText(ele_partiorg_cate);
					partiorg_orgdate = StringUtils.replace(partiorg_orgdate, partiorg_cate, "");
					parti_org.setCate(partiorg_cate);
					parti_org.setOrg_date(partiorg_orgdate);
					
					Element ele_partiorg_desc = getfirstChildElement(ele_partiorg, "p.description");
					String partiorg_desc = getElementText(ele_partiorg_desc);
					parti_org.setDesc(partiorg_desc);
					if (ele_partiorg_desc!=null) {
						parti_org.setDesc_html(ele_partiorg_desc.toString());
					}
					
					parti_orgs.add(parti_org);
				}
				volunteer.setParti_orgs(parti_orgs);
			}
			
			
			Elements ele_intens = getElements(ele_volunteer, "div#volunteering-opportunities ul.volunteering-opportunities li");
			if (ele_intens!=null && !ele_intens.isEmpty()) {
				List<String> intens = new ArrayList<String>();
				for (Element ele_inten : ele_intens) {
					String inten = getElementText(ele_inten);
					intens.add(inten);
				}
				volunteer.setIntens(intens);
			}
			
			Elements ele_interests = getElements(ele_volunteer, "div#volunteering-interests div#volunteering-causes-view ul.volunteering-listing li");
			if (ele_interests!=null && !ele_interests.isEmpty()) {
				List<String> interests = new ArrayList<String>();
				for (Element ele_interest : ele_interests) {
					String interest = getElementText(ele_interest);
					interests.add(interest);
				}
				volunteer.setInterests(interests);
			}
			
			Elements ele_suptorgs = getElements(ele_volunteer, "div#volunteering-interests div#volunteering-organizations-view ul.volunteering-listing li");
			if (ele_suptorgs!=null && !ele_suptorgs.isEmpty()) {
				List<Organization> supt_orgs = new ArrayList<Organization>();
				for (Element ele_suptorg : ele_suptorgs) {
					Organization supt_org = new Organization();
					
					Element ele_suptorg_a = getfirstChildElement(ele_suptorg, "a");
					String suptorg_name = getElementText(ele_suptorg_a);
					supt_org.setName(suptorg_name);
					supt_orgs.add(supt_org);
				}
				volunteer.setSupt_orgs(supt_orgs);
			}
			user.setVolunteer(volunteer);
		}
		//background-languages-container/languages
		Elements ele_languages = getElements(ele_background, "div#background-languages-container div#languages-view ol li.section-item");
		if (ele_languages!=null && !ele_languages.isEmpty()) {
			List<Language> languages = new ArrayList<Language>();
			for (Element ele_language : ele_languages) {
				Language language = new Language();
				
				Element ele_language_name = getfirstChildElement(ele_language, "h4 span");
				String language_name = getElementText(ele_language_name);
				language.setName(language_name);
				
				Element ele_language_profic = getfirstChildElement(ele_language, "div.languages-proficiency");
				String language_profic = getElementText(ele_language_profic);
				language.setProfic(language_profic);
				languages.add(language);
			}
			user.setLanguages(languages);
		}
		//background-skills-container
		Element ele_skills_all = getfirstChildElement(ele_background, "div#background-skills-container div#profile-skills");
		//skills
		Elements ele_skills = getElements(ele_skills_all, "ul.skills-section:not(.compact-view) li.endorse-item"); //“不包含”匹配
		if (ele_skills!=null && !ele_skills.isEmpty()) {
			List<Skill> skills = new ArrayList<Skill>();
			for (Element ele_skill : ele_skills) {
				Skill skill = new Skill();
				
				Element ele_skill_endorse_num = getfirstChildElement(ele_skill, "span.skill-pill a.endorse-count span.num-endorsements");
				String skill_endorse_num = getElementText(ele_skill_endorse_num);
				skill.setEndorse_num(skill_endorse_num);
				
				Element ele_skill_name = getfirstChildElement(ele_skill, "span.endorse-item-name span.endorse-item-name-text");
				String skill_name = getElementText(ele_skill_name);
				skill.setName(skill_name);
				skills.add(skill);
			}
			user.setSkills(skills);
		}
		//skills_others
		Elements ele_skills_others = getElements(ele_skills_all, "ul.skills-section.compact-view li.endorse-item:not(#see-more-less-skill)");
		if (ele_skills_others!=null && !ele_skills_others.isEmpty()) {
			List<Skill> skills_others = new ArrayList<Skill>();
			for (Element ele_skills_other : ele_skills_others) {
				Skill skills_other = new Skill();
				
				Element ele_skills_other_endorse_num = getfirstChildElement(ele_skills_other, "span.skill-pill a.endorse-count span.num-endorsements");
				String skills_other_endorse_num = getElementText(ele_skills_other_endorse_num);
				skills_other.setEndorse_num(skills_other_endorse_num);
				
				Element ele_skills_other_name = getfirstChildElement(ele_skills_other, "span.endorse-item-name span.endorse-item-name-text");
				String skills_other_name = getElementText(ele_skills_other_name);
				skills_other.setName(skills_other_name);
				skills_others.add(skills_other);
			}
			user.setSkills_others(skills_others);
		}
		//background-publications-container/pubs
		Elements ele_pubs = getElements(ele_background, "div#background-publications-container div.editable-item.section-item");
		if (ele_pubs!=null && !ele_pubs.isEmpty()) {
			List<Publication> pubs = new ArrayList<Publication>();
			for (Element ele_pub : ele_pubs) {
				Publication pub = new Publication();
				
				Element ele_pub_link = getfirstChildElement(ele_pub, "hgroup h4 a");
				if (ele_pub_link!=null) {
					String pub_link = LINKEDIN + getElementAttr(ele_pub_link, "href");
					pub.setLink(pub_link);
				}
				
				Element ele_pub_name = getfirstChildElement(ele_pub, "hgroup h4 span[dir=auto]");
				String pub_name = getElementText(ele_pub_name);
				pub.setName(pub_name);
				
				Element ele_pub_press = getfirstChildElement(ele_pub, "hgroup h5 span[dir=auto]");
				String pub_press = getElementText(ele_pub_press);
				pub.setPress(pub_press);
				
				Element ele_pub_date = getfirstChildElement(ele_pub, "span.publication-date");
				String pub_date = getElementText(ele_pub_date);
				pub.setPub_date(pub_date);
				
				Element ele_desc = getfirstChildElement(ele_pub, "p.description");
				String pub_desc = getElementText(ele_desc);
				pub.setDesc(pub_desc);
				if (ele_desc!=null) {
					pub.setDesc_html(ele_desc.toString());
				}
				
				Elements ele_authors = getElements(ele_pub, "dl.associated-list dd.associated-endorsements ul li");
				if (ele_authors!=null && !ele_authors.isEmpty()) {
					List<UserFeedJson> authors = new ArrayList<UserFeedJson>();
					for (Element ele_author : ele_authors) {
						UserFeedJson author = new UserFeedJson();
						
						Element ele_author_hgroup = getfirstChildElement(ele_author, "hgroup");
						if (ele_author_hgroup==null) {
							Element ele_author_profile_img = getfirstChildElement(ele_author, "span.associated-photo img");
							String author_profile_img = getElementAttr(ele_author_profile_img, "src");
							author.setProfile_img(author_profile_img);
							
							Element ele_author_name = getfirstChildElement(ele_author, "h6");
							String author_name = getElementText(ele_author_name);
							author.setName(author_name);
							authors.add(author);
							continue;
						}
						
						Element ele_author_profile_img = getfirstChildElement(ele_author, "a.associated-photo img");
						String author_profile_img = getElementAttr(ele_author_profile_img, "data-li-src");
						author.setProfile_img(author_profile_img);
						
						Element ele_author_name_a = getfirstChildElement(ele_author, "hgroup h5 span a");
						String author_name = getElementText(ele_author_name_a);
						String author_profile = getElementAttr(ele_author_name_a, "href");
						author.setName(author_name);
						author.setProfile(author_profile);
						
						Element ele_author_headline = getfirstChildElement(ele_author, "hgroup h6");
						String author_headline = getElementText(ele_author_headline);
						author.setHeadline(author_headline);
						authors.add(author);
					}
					pub.setAuthors(authors);
				}
				pubs.add(pub);
			}
			user.setPubs(pubs);
		}
		//background-education-container/edus
		Elements ele_edus = getElements(ele_background, "div#background-education-container div.editable-item.section-item");
		if (ele_edus!=null && !ele_edus.isEmpty()) {
			List<EducationInfo> edus = new ArrayList<EducationInfo>();
			for (Element ele_edu : ele_edus) {
				EducationInfo edu = new EducationInfo();
				
				School school = new School();
				Element ele_school_logo = getfirstChildElement(ele_edu, "a[title=学校详细信息] h5.education-logo img");
				String school_logo = getElementAttr(ele_school_logo, "data-li-src");
				school.setLogo(school_logo);
				
				Element ele_school_a = getfirstChildElement(ele_edu, "header h4.summary a");
				String school_name = getElementText(ele_school_a);
				String school_link = LINKEDIN + getElementAttr(ele_school_a, "href");
				school.setName(school_name);
				school.setLink(school_link);
				
				Elements ele_associated_dts = getElements(ele_edu, "dl.education-associated dt");
				if (ele_associated_dts!=null && !ele_associated_dts.isEmpty()) {
					for (int i=0; i<ele_associated_dts.size(); i++) {
						Element ele_associated_dt = ele_associated_dts.get(i);
						String associated_dt_falg = getElementText(ele_associated_dt);
						associated_dt_falg = associated_dt_falg==null ? "" : associated_dt_falg;
						
						if (associated_dt_falg.contains("项目")) {
							Elements ele_pros = ele_edu.select("dl.education-associated dd:eq(" + (2*i+1) + ")");//
							Element ele_pro = null;
							if (ele_pros!=null && !ele_pros.isEmpty()) {
								ele_pro = ele_pros.get(0);
							}
							Elements ele_peojects = getElements(ele_pro, "ul li");
							if (ele_peojects!=null && !ele_peojects.isEmpty()) {
								List<Project> projects = new ArrayList<Project>();
								for (Element ele_peoject : ele_peojects) {
									Project project = new Project();
									
									Element ele_peoject_name = getfirstChildElement(ele_peoject, "h6 span[dir=auto]");
									String peoject_name = getElementText(ele_peoject_name);
									project.setName(peoject_name);
									projects.add(project);
								}
								school.setProjects(projects);
							}
						}
						
						if (associated_dt_falg.contains("荣誉和奖项")) {
							Elements ele_hws = ele_edu.select("dl.education-associated dd:eq(" + (2*i+1) + ")");//
							Element ele_hw = null;
							if (ele_hws!=null && !ele_hws.isEmpty()) {
								ele_hw = ele_hws.get(0);
							}
							Elements ele_honowards = getElements(ele_hw, "ul li");
							if (ele_honowards!=null && !ele_honowards.isEmpty()) {
								List<Honoraward> honowards = new ArrayList<Honoraward>();
								for (Element ele_honoward : ele_honowards) {
									Honoraward honoward = new Honoraward();
									
									Element ele_honoward_summary = getfirstChildElement(ele_honoward, "h6 span[dir=auto]");
									String honoward_summary = getElementText(ele_honoward_summary);
									honoward.setSummary(honoward_summary);
									honowards.add(honoward);
								}
								school.setHonorawards(honowards);
							}
						}
						
						if (associated_dt_falg.contains("课程")) {
							Elements ele_school_cources = getElements(ele_edu, "dl.education-associated dd.associated-courses ul li");
							if (ele_school_cources!=null && !ele_school_cources.isEmpty()) {
								List<String> school_cources = new ArrayList<String>();
								for (Element ele_school_cource : ele_school_cources) {
									String school_cource = getElementText(ele_school_cource);
									school_cources.add(school_cource);
								}
								school.setCourses(school_cources);
							}
						}
					}
				}
				edu.setSchool(school);
				
				Element ele_edu_degree = getfirstChildElement(ele_edu, "header h5 span.degree");
				String edu_degree = getElementText(ele_edu_degree);
				edu_degree = edu_degree==null ? edu_degree : edu_degree.replace(",", "");
				edu.setDegree(edu_degree);
				
				Element ele_edu_major = getfirstChildElement(ele_edu, "header h5 span.major a");
				String edu_major = getElementText(ele_edu_major);
				edu.setMajor(edu_major);
				
				Element ele_edu_date = getfirstChildElement(ele_edu, "span.education-date");
				String edu_date = getElementText(ele_edu_date);
				if (!"".equals(edu_date)) {
					edu.setEdu_date(edu_date);
				}
				
				Element ele_edu_desc = getfirstChildElement(ele_edu, "p.notes");
				String edu_desc = getElementText(ele_edu_desc);
				edu.setDesc(edu_desc);
				if (ele_edu_desc!=null) {
					edu.setDesc_html(ele_edu_desc.toString());
				}
				
				Elements ele_edu_activities = getElements(ele_edu, "p.activities a");
				if (ele_edu_activities!=null && !ele_edu_activities.isEmpty()) {
					List<String> edu_activities = new ArrayList<String>();
					for (Element ele_edu_activitie : ele_edu_activities) {
						String edu_activitie = getElementText(ele_edu_activitie);
						edu_activities.add(edu_activitie);
					}
					edu.setActivities(edu_activities);
				}
				edus.add(edu);
			}
			user.setEdus(edus);
		}
		//background-certifications-container/certis 资格认证
		Elements ele_certis = getElements(ele_background, "div#background-certifications div.editable-item.section-item");
		if (ele_certis!=null && !ele_certis.isEmpty()) {
			List<Certification> certis = new ArrayList<Certification>();
			for (Element ele_certi : ele_certis) {
				Certification certi = new Certification();
				
				Element ele_certi_name = getfirstChildElement(ele_certi, "hgroup h4 a");
				String certi_name = getElementText(ele_certi_name);
				certi_name = certi_name==null ? certi_name : certi_name.replace("(链接)", "");
				certi.setCerti_name(certi_name);
				
				Element ele_org_name = getfirstChildElement(ele_certi, "hgroup h5:not(.certification-logo) a");
				String org_name = getElementText(ele_org_name);
				certi.setOrg_name(org_name);
				
				Element ele_certi_date = getfirstChildElement(ele_certi, "span.certification-date time");
				String certi_date = getElementText(ele_certi_date);
				if (!"".equals(certi_date)) {
					certi.setCerti_date(certi_date);
				}
				certis.add(certi);
			}
			user.setCertis(certis);
		}
		//background-courses-container/courses 学校及课程
		Elements ele_schools = getElements(ele_background, "div#background-courses-container div#courses-view div.section-item");
		if (ele_schools!=null && !ele_schools.isEmpty()) {
			List<School> schools = new ArrayList<School>();
			for (Element ele_school : ele_schools) {
				School school = new School();
				
				Element ele_school_a = getfirstChildElement(ele_school, "h4 a");
				Element ele_school_h4 = getfirstChildElement(ele_school, "h4");
				if (ele_school_a!=null) {
					String school_name = getElementText(ele_school_a);
					String school_link = LINKEDIN + getElementAttr(ele_school_a, "href");
					school.setName(school_name);
					school.setLink(school_link);
				} else {
					String school_name = getElementText(ele_school_h4);
					school.setName(school_name);
				}
				
				Elements ele_school_coulist = getElements(ele_school, "ul.courses-listing li");
				if (ele_school_coulist!=null && !ele_school_coulist.isEmpty()) {
					List<String> school_coulist = new ArrayList<String>();
					for (Element ele_school_course : ele_school_coulist) {
						String school_course = getElementText(ele_school_course);
						school_coulist.add(school_course);
					}
					school.setCourses(school_coulist);
				}
				schools.add(school);
			}
			user.setSchools(schools);
		}
		//background-test-scores-container/testScores
		Elements ele_testscores = getElements(ele_background, "div#background-test-scores-container div.editable-item.section-item");
		if (ele_testscores!=null && !ele_testscores.isEmpty()) {
			List<TestScore> testScores = new ArrayList<TestScore>();
			for (Element ele_testscore : ele_testscores) {
				TestScore testScore = new TestScore();
				
				Element ele_testscore_name = getfirstChildElement(ele_testscore, "div.scores h4 span");
				String testscore_name = getElementText(ele_testscore_name);
				testScore.setName(testscore_name);
				
				Element ele_testscore_score = getfirstChildElement(ele_testscore, "div.scores h5 span");
				String testscore_score = getElementText(ele_testscore_score);
				testscore_score = StringUtils.isEmpty(testscore_score) ? null : testscore_score.replace("成绩: ", "");
				testScore.setScore(testscore_score);
				
				Element ele_testscore_testdate = getfirstChildElement(ele_testscore, "span.test-scores-date time");
				String testscore_testdate = getElementText(ele_testscore_testdate);
				testScore.setTest_date(testscore_testdate);
				
				Element ele_testscore_desc = getfirstChildElement(ele_testscore, "p.description");
				String testscore_desc = getElementText(ele_testscore_desc);
				testScore.setDesc(testscore_desc);
				if (ele_testscore_desc!=null) {
					testScore.setDesc_html(ele_testscore_desc.toString());
				}
				
				testScores.add(testScore);
			}
			user.setTestScores(testScores);
		}
		//background-additional-info-container/add_info
		Element ele_addinfo = getfirstChildElement(ele_background, "div#background-additional-info-container");
		if (ele_addinfo!=null) {
			AdditionalInfo add_info = new AdditionalInfo();
			//interests//
			Elements ele_interests = getElements(ele_addinfo, "li#interests ul.interests-listing li");
			if (ele_interests!=null && !ele_interests.isEmpty()) {
				List<String> interests = new ArrayList<String>();
				for (Element ele_interest : ele_interests) {
					Element ele_interest_a = getfirstChildElement(ele_interest, "a");
					String interest = getElementText(ele_interest_a);
					interests.add(interest);
				}
				add_info.setInterests(interests);
			}
			//personal-info//
			Element ele_perinfo = getfirstChildElement(ele_addinfo, "li#personal-info table#personal-info-view");
			Element ele_birth = getfirstChildElement(ele_perinfo, "tr:contains(生日)");
			ele_birth = getfirstChildElement(ele_birth, "td");
			String birth = getElementText(ele_birth);
			add_info.setBirth(birth);
			Element ele_maristatus = getfirstChildElement(ele_perinfo, "tr:contains(婚姻状况)");
			ele_maristatus = getfirstChildElement(ele_maristatus, "td");
			String mari_status = getElementText(ele_maristatus);
			add_info.setMari_status(mari_status);
			//contact//
			Element ele_contact = getfirstChildElement(ele_addinfo, "li#contact-comments div#contact-comments-view p.description");
			String contactStr = getElementText(ele_contact);
			if (contactStr!=null) {
				ContactInfo contact = new ContactInfo();
				boolean hasEffectiveContact = false;
				
				List<String> mobiles = getSubStringByRegex(contactStr, "手机：[0-9]{11}");
				String mobile = mobiles.isEmpty() ? null : mobiles.get(0).replace("手机：", "");
				if (!StringUtils.isEmpty(mobile)) {
					contact.setMobile(mobile);
					hasEffectiveContact = true;
				}
				
				List<String> emails = getSubStringByRegex(contactStr, "[a-zA-Z0-9\\.-_]+@[a-zA-Z0-9\\.-_]+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?");
				if (!emails.isEmpty()) {
					contact.setEmails(emails);
					hasEffectiveContact = true;
				}
				
				if (!hasEffectiveContact) {
					contact.setDesc(contactStr);
				}
				
				add_info.setContact(contact);
			}
			user.setAdd_info(add_info);
		}
		
		//endorsements 工作经历及推荐信 //当endorsements.status==recived时，相对于user，endorser为推荐人；当status==given时，相对于user，endorser为被推荐人
		Elements ele_jobwendors = getElements(ele, "div#endorsements div.endorsements-received ol li");
		boolean isRecived = true;
		if (ele_jobwendors.isEmpty()) {
			ele_jobwendors = getElements(ele, "div#endorsements div.endorsements-given ol li");
			isRecived = false;
		}
		if (ele_jobwendors!=null && !ele_jobwendors.isEmpty()) {
			List<JobInfo> jobwendors = new ArrayList<JobInfo>();
			for (Element ele_jobwendor : ele_jobwendors) {
				JobInfo job = new JobInfo();
				
				Element ele_job_position = getfirstChildElement(ele_jobwendor, "hgroup h3 span");
				String job_position = getElementText(ele_job_position);
				job.setPosition(job_position);
				
				Element ele_job_company = getfirstChildElement(ele_jobwendor, "hgroup h4 span");
				String job_company = getElementText(ele_job_company);
				job.setCompany(job_company);
				
				Elements ele_endors = getElements(ele_jobwendor, "div.endorsement-full");
				if (ele_endors!=null && !ele_endors.isEmpty()) {
					List<Endorsement> endors = new ArrayList<Endorsement>();
					for (Element ele_endor : ele_endors) {
						Endorsement endor = new Endorsement();
						
						if (!isRecived) {
							endor.setStatus("given");
						}
						
						UserFeedJson endorser = new UserFeedJson();
						Element ele_endorser_profile_img = getfirstChildElement(ele_endor, "div.endorsement-picture a img");
						String endorser_profile_img = getElementAttr(ele_endorser_profile_img, "src");
						endorser.setProfile_img(endorser_profile_img);
						
						Element ele_endor_info = getfirstChildElement(ele_endor, "div.endorsement-info");
						Element ele_endorser_name_a = getfirstChildElement(ele_endor_info, "hgroup h5 span a");
						String endorser_name = getElementText(ele_endorser_name_a);
						String endorser_profile = getElementAttr(ele_endorser_name_a, "href");
						endorser.setName(endorser_name);
						endorser.setProfile(endorser_profile);
						
						Element ele_endorser_headline = getfirstChildElement(ele_endor_info, "h6");
						String endorser_headline = getElementText(ele_endorser_headline); //
						endorser.setHeadline(endorser_headline);
						
						Element ele_relation_date = getfirstChildElement(ele_endor_info, "span.endorsement-date");
						String relation_date = getElementText(ele_relation_date); //
						String relation = relation_date.replaceAll("推荐人:.*\\(", "").replaceAll("\\).*", "");
						endorser.setAdd_desc(relation);
						endor.setEndorser(endorser);
						
						String endors_date = relation_date.replaceAll(".*推荐时间: ", "");
						endor.setEndors_date(endors_date);
						
						Element ele_endor_content = getfirstChildElement(ele_endor_info, "blockquote.endorsement-quote p.description");
						String endor_content = getElementText(ele_endor_content);	
						endor.setContent(endor_content);
						
						endors.add(endor);
					}
					job.setEndorsements(endors);
				}
				jobwendors.add(job);
			}
			user.setJobwendors(jobwendors);
		}
		
		//uid
		user.setUid(IDGenerator.generateLinkedinUserId(user));
		
		return user;
	}
	
	
	/**
	 * 得到类似用户主页链接列表
	 * @param html HTML页面
	 * @return 类似用户linkedin主页链接的list集合
	 */
	public List<String> getSimilarUserProfiles(String html) {
		LOGGER.info("=================LinkedinUserInfoParser.getSimilarUserProfiles begin=================");
		
		List<String> userProfiles = null;
		Elements eles = Jsoup.parseBodyFragment(html).select("body");
		if (eles==null || eles.isEmpty()) return null;
		Element ele = eles.get(0);
		
		Elements ele_userProfiles = getElements(ele, "div#aux div.discovery-module div.discovery-panel ol.discovery-results li");
		Elements ele_userProfiles_altern = getElements(ele, "div#aux div.insights-browse-map ul li");
		if (ele_userProfiles!=null && !ele_userProfiles.isEmpty()) {
			userProfiles = new ArrayList<String>();
			for (Element ele_userProfile : ele_userProfiles) {
				Element ele_userProfile_a = getfirstChildElement(ele_userProfile, "a");
				String userProfile = getElementAttr(ele_userProfile_a, "href");
				if (StringUtils.isEmpty(userProfile)) continue;
				userProfiles.add(userProfile);
			}
		} else if (ele_userProfiles_altern!=null && !ele_userProfiles_altern.isEmpty()){
			userProfiles = new ArrayList<String>();
			for (Element ele_userProfile_altern : ele_userProfiles_altern) {
				Element ele_userProfile_altern_a = getfirstChildElement(ele_userProfile_altern, "a");
				String userProfile_altern = getElementAttr(ele_userProfile_altern_a, "href");
				if (StringUtils.isEmpty(userProfile_altern)) continue;
				userProfiles.add(userProfile_altern);
			}
		}
		
		/*if (userProfiles!=null) {
			for (int i=0; i<userProfiles.size(); i++) {
				LOGGER.info("similarUserProfile_"+ (i+1) +": {}", userProfiles.get(i));
			}
		}*/
		
		return userProfiles;
	}
	
}








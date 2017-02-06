package com.crawler.weibo.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.domain.json.HtmlJson;
import com.crawler.weibo.domain.json.EducationInfo;
import com.crawler.weibo.domain.json.JobInfo;
import com.crawler.weibo.domain.json.School;
import com.crawler.weibo.domain.json.UserFeedJson;

@Component
public class WeiboUserInfoParser extends AbstractWeiboParser{

	private static final Logger log = LoggerFactory.getLogger(WeiboUserInfoParser.class);
	
	public UserFeedJson userInfoScriptParser(String html) {
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		UserFeedJson user = new UserFeedJson();

		HtmlJson htmlJson = scriptParser(html,"\"domid\":\"Pl_Official_PersonalInfo",FM);
		
		if(htmlJson!=null&&htmlJson.getHtml()!=null){
			user = userInfoFeedParser(user,htmlJson.getHtml());
		} 
		
		log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		
		HtmlJson htmlJson2 = scriptParser(html,"\"domid\":\"Pl_Core_T8CustomTriColumn",FM);
		    
		if(htmlJson2!=null&&htmlJson2.getHtml()!=null){
			user = userInfoCountParser(user,htmlJson2.getHtml()); 
		} 
		
		return user;

	}
	
	public UserFeedJson userInfoCountParser(UserFeedJson user,String html){
		Elements base_eles = Jsoup.parse(html).select("table.tb_counter");
		log.info("base_eles.size------"+base_eles.size()); 
		if(base_eles!=null&&base_eles.size()>0){
			Element e = base_eles.get(0); 
			Element e1 = getfirstChildElement(e,"a:contains(关注) strong");  
			log.info("关注数  e1--->"+getHtml(e1));
			user.setFollow(getElementText(e1)); 
			
			Element e2 = getfirstChildElement(e,"a:contains(粉丝) strong");  
			log.info("粉丝数  e2--->"+getHtml(e2));
			user.setFans(getElementText(e2)); 
			
			Element e3 = getfirstChildElement(e,"a:contains(微博) strong");  
			log.info("微博数  e3--->"+getHtml(e3));
			user.setStatuses(getElementText(e3)); 
			
		}
		return user;
	}
	 
	
	public UserFeedJson userInfoFeedParser(UserFeedJson user,String html){
		//log.info(html);
		Elements base_eles = Jsoup.parse(html).select("ul.clearfix");
		log.info("base_eles.size------"+base_eles.size()); 
		if(base_eles!=null&&base_eles.size()>0){
			Element e = base_eles.get(0); 
			Element e1 = getfirstChildElement(e,"li:contains(昵称) span.pt_detail");  
			log.info("用户昵称  e1--->"+getHtml(e1));
			user.setNickname(getElementText(e1));//用户昵称 
			
			Element e2 = getfirstChildElement(e,"li:contains(个性域名) span.pt_detail");  
			log.info("用户主页 e2--->"+getHtml(e2));
			user.setProfile(getElementText(e2));//用户主页链接
			
			Element e3 = getfirstChildElement(e,"li:contains(所在地) span.pt_detail");  
			log.info("所在地 e3--->"+getHtml(e3));
			user.setLocation(getElementText(e3));//用户所在地
			
			Element e4 = getfirstChildElement(e,"li:contains(性别) span.pt_detail");  
			log.info("性别 e4--->"+getHtml(e4));
			user.setGender(getElementText(e4));//性别
			
			Element e5 = getfirstChildElement(e,"li:contains(博客) a");  
			log.info("博客 e5--->"+getHtml(e5));
			user.setBlog_url(getElementText(e5));//博客
			
			Element e6 = getfirstChildElement(e,"li:contains(生日) span.pt_detail");  
			log.info("生日 e6--->"+getHtml(e6));
			user.setBirthday(getElementText(e6));//生日
			
			Element e7 = getfirstChildElement(e,"li:contains(注册时间) span.pt_detail");  
			log.info("注册时间 e7--->"+getHtml(e7));
			user.setCreated_at(getElementText(e7));//注册时间
			
			Element e8 = getfirstChildElement(e,"li:contains(简介) span.pt_detail");  
			log.info("简介 e8--->"+getHtml(e8));
			user.setPerson_info(getElementText(e8));//用户主页链接
			 
		}
		
		Elements contract_eles = Jsoup.parse(html).select("div:contains(联系信息).PCD_text_b");
		log.info("contract_eles.size------"+contract_eles.size());  
		if(contract_eles!=null&&contract_eles.size()>0){ 
			Element e = contract_eles.get(0); 
			Element e1 = getfirstChildElement(e,"span.pt_detail");  
			log.info("邮箱  e1--->"+getHtml(e1));
			user.setEmail(getElementText(e1));//邮箱
		}
		
		Elements job_eles = Jsoup.parse(html).select("div:contains(工作信息).PCD_text_b");
		log.info("job_eles.size------"+job_eles.size());  
		if(job_eles!=null&&job_eles.size()>0){  
			Element jobe = job_eles.get(0); 
			Elements eles = getElements(jobe,"span.pt_detail");  
			if(eles!=null&&eles.size()>0){
				JobInfo[] jobinfos = new JobInfo[eles.size()];
				for(int i=0;i<eles.size();i++){
					Element e = eles.get(i);
					String jobhtml = getHtml(e); 
					log.info("工作  e--->"+jobhtml); 
					if(e!=null){
						JobInfo jobInfo = new JobInfo();
						Element e1 = getfirstChildElement(e,"a");  
						jobInfo.setOrganization(getElementText(e1));
						jobInfo.setOrganization_url(getElementAttr(e1, "href"));
						//  e.ownText() --> (2012 - 2014) 地区：福建 ，厦门 职位：派出所教导员 
						jobInfo.setPosition(getPosition(jobhtml)); 
						jobInfo.setDuration(getDuration(jobhtml));
						jobInfo.setLocation(getLocation(jobhtml));
						jobinfos[i] = jobInfo; 
					}
				}
				user.setJobinfo(jobinfos);
			}
		}
		
		Elements edu_eles = Jsoup.parse(html).select("div:contains(教育信息).PCD_text_b");
		log.info("edu_eles.size------"+edu_eles.size());  
		if(edu_eles!=null&&edu_eles.size()>0){
			Element edue = edu_eles.get(0); 
			List<EducationInfo> educationinfos = new ArrayList<EducationInfo>();
			EducationInfo university_educationInfo= getEducationInfo(edue,"大学");
			if(university_educationInfo!=null){
				educationinfos.add(university_educationInfo);
			}
			EducationInfo senior_educationInfo= getEducationInfo(edue,"高中");
			if(senior_educationInfo!=null){
				educationinfos.add(senior_educationInfo);
			}
			EducationInfo junior_educationInfo= getEducationInfo(edue,"初中");
			if(junior_educationInfo!=null){
				educationinfos.add(junior_educationInfo);
			}
			EducationInfo primary_educationInfo= getEducationInfo(edue,"小学");
			if(primary_educationInfo!=null){
				educationinfos.add(primary_educationInfo);
			}
			user.setEducationinfos(educationinfos);
		}
		
		return user;
	}
	
	public EducationInfo getEducationInfo(Element edue,String flag){
		EducationInfo educationInfo = null;
		if(edue!=null){
			Elements school_eles = getElements(edue,"li:contains("+flag+").clearfix > span.pt_detail > a");  
			log.info("school_eles.size------"+school_eles.size());
			if(school_eles!=null&&school_eles.size()>0){
				educationInfo = new EducationInfo();
				educationInfo.setFlag(flag);
				if(school_eles!=null){
					List<School> schools = new ArrayList<School>();
					for(Element e:school_eles){
						School school = new School();
						log.info("学校------"+getHtml(e));
						school.setName(getElementText(e));
						school.setUrl(getElementAttr(e, "href"));
						schools.add(school);
					}
					educationInfo.setSchool(schools);
				}
			}
		}
		return educationInfo;
	}
	
	
	public String getDuration(String text){
		//log.info("getDuration   text:"+text);
		String str = null;
		if(text.indexOf("(")!=-1){
			//int beginIndex = text.indexOf("(")+1;
			//int endIndex = text.indexOf(")");
			int beginIndex = text.lastIndexOf("(")+1;//有限公司名称自带括号，这里是去最后面的一个括号内的内柔，也就是年限
			int endIndex = text.lastIndexOf(")");
			str = text.substring(beginIndex, endIndex).trim();
			//log.info("getDuration:"+str);
		} 
		return str;
	}
	
	public String getPosition(String text){
		//log.info("getPosition   text:"+text);
		String str = null;
		if(text.indexOf("职位：")!=-1){
			int beginIndex = text.indexOf("职位：")+3; 
			int endIndex = text.indexOf("</span>",beginIndex);
			str = text.substring(beginIndex, endIndex).trim();
			//log.info("getPosition:"+str);
		} 
		return str;
	}
	
	public String getLocation(String text){
		//log.info("getLocation   text:"+text);
		String str = null;
		if(text.indexOf("地区：")!=-1){
			//log.info("text.length():"+text.length());
			int beginIndex = text.indexOf("地区：")+3;
			//log.info("beginIndex:"+beginIndex);
			int endIndex = text.indexOf("<br>",beginIndex);
			//log.info("endIndex:"+endIndex);
			str = text.substring(beginIndex, endIndex).trim();
			//log.info("getLocation:"+str);
		} 
		return str;
	}
	
	
	
}

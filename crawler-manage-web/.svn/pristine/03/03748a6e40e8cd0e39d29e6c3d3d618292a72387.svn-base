package com.crawlermanage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;











import com.crawler.domain.json.Result;
import com.crawler.zhihu.domain.json.UserFeedJson;
import com.crawler.zhihu.domain.json.ZhihuAnswer;
import com.crawler.zhihu.domain.json.ZhihuQuestion;
import com.crawlermanage.controller.api.zhihu.ZhihuUserSearchController;
import com.crawlermanage.service.zhihu.ZhihuUserProfileService;
import com.crawlermanage.service.zhihu.ZhihuUserSearchService;
import com.google.gson.Gson;


@Controller
@RequestMapping("/zhihu")
public class ZhihuController {

	
	private static final Logger log = LoggerFactory.getLogger(ZhihuController.class);
	
	@Autowired
	private ZhihuUserSearchService zhihuUserSearchService;
	
	@Autowired
	private ZhihuUserProfileService zhihuUserProfileService;

	
/*
 * (1)爬取人物的基本信息			
 */

//(1-1)转向”爬取“页，填写爬取人物。
@RequestMapping("/crawler")
public String crawler(){
	return "zhihu/crawler";
}

//(1-2)转向“爬取结果”页
@RequestMapping(value="/crawler_search",method=RequestMethod.POST)
public String crawler_search(@RequestParam("crawlerPerson") String crawlerPerson,Model model){		
	model.addAttribute("crawlerPerson", crawlerPerson);
	return "zhihu/crawler_search";		
}

//(1-3)将爬取结果以对象的形式传向前端
	@RequestMapping("/crawler_searchResult")
	public @ResponseBody List<UserFeedJson> toCrawler22(HttpServletRequest request,HttpServletResponse response){
		
		 List<UserFeedJson> userFeedJsonList=null;
		 Result<List<UserFeedJson>> result=null;
		 String url;
		 try {
		      String crawlerPerson = URLDecoder.decode(request.getParameter("crawlerPerson"),"utf-8");
			  log.info("知乎crawlerPerson:{}",crawlerPerson);
			  
			  url="http://www.zhihu.com/search?type=people&q="+request.getParameter("crawlerPerson");
			  log.info("知乎url:{}",url);

	          result =zhihuUserSearchService.searchUser(url,false,"");          
			  userFeedJsonList=result.getData();
	          
	          Gson gson = new Gson();  		
		      log.info("知乎爬取结果:{}",gson.toJson(userFeedJsonList));	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userFeedJsonList;		
	}

/*
 * (3)人物发表问答帖子的详情页,
 */

//(3-1)转向问答帖子
@RequestMapping(value="/detail",method=RequestMethod.GET)
public String detail(@RequestParam("url") String url,@RequestParam("crawlerPerson") String crawlerPerson,Model model){
	String person="";
	   try {
		 person=URLDecoder.decode(crawlerPerson,"utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   model.addAttribute("url_person",url+"?"+person);	
	   return "zhihu/detail";
}
//(3-2)获取最新动态帖子内容
@RequestMapping(value="/detailContent",method=RequestMethod.GET)
public @ResponseBody UserFeedJson detailContent(@RequestParam("url") String url){
	log.info("知乎url:{}",url);	
	UserFeedJson userfeed = zhihuUserProfileService.userProfile(url,false).getData();	
	Gson gson = new Gson();  	
	String usersJson = gson.toJson(userfeed);
	log.info("知乎帖子usersJson:{}"+usersJson);
	
	return userfeed;
}
//(3-3)获取提问帖子内容
@RequestMapping(value="/asksContent",method=RequestMethod.GET)
public @ResponseBody List<ZhihuQuestion> asksContent(@RequestParam("url") String url){
	log.info("知乎url:{}",url);
	Result<List<ZhihuQuestion>> result=zhihuUserProfileService.userAsks(url,false,"");
	List<ZhihuQuestion> questions=result.getData();
	Gson gson=new Gson();
	log.info("知乎提问:{}"+gson.toJson(questions));
	return questions;	
}

//(3-4)获取回答帖子内容
@RequestMapping(value="/getAnswerContent",method=RequestMethod.GET)
public @ResponseBody List<ZhihuAnswer> getAnswerContent(@RequestParam("url") String url){
	log.info("知乎url:{}",url);
	Result<List<ZhihuAnswer>> result = zhihuUserProfileService.userAnswers(url,false,"");
	 List<ZhihuAnswer> answers=result.getData();
	Gson gson=new Gson();
	log.info("知乎提问:{}"+gson.toJson(answers));
	return answers;
}






}

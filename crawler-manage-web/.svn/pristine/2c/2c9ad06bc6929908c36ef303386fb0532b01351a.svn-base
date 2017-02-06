package com.crawlermanage.controller.api.weibo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.weibo.domain.json.FriendCircle;
import com.crawlermanage.service.weibo.WeiboUserFriendCircleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;

@Controller
@RequestMapping("/api/weibo")
public class WeiboUserFriendCircleController {

	private static final Logger log = LoggerFactory.getLogger(WeiboUserFriendCircleController.class);
	
	private static final int default_total = 1;//好友分页，最多5页
	
	private static final int default_total_max = 5;//好友分页，最多5页
	
	private static final int default_limit = 1;//返回人脉个数(同事也是相互关注接口调用次数)
	
	private static final int default_limit_max = 8;//返回人脉个数(同事也是相互关注接口调用次数)
	
	@Autowired
	private WeiboUserFriendCircleService weiboUserFriendCircleService;
	  
	@RequestMapping(value = "/friendcircle")
	@ResponseBody
    public String home(@RequestParam("uid") String uid,String depth,String limit, boolean isDebug, String logback) {  
		log.info("uid:{}",uid); 
		if(uid==null||"".equals(uid.trim())){
			return null;
		}
		int iTotal = getInt(depth,default_total);
		iTotal = iTotal>default_total_max?default_total_max:iTotal;//限制最多5页
		int iLimit = getInt(limit,default_limit);
		iLimit = iLimit>default_limit_max?default_limit_max:iLimit;//限制最多8个人脉关系
		Result<FriendCircle> result = weiboUserFriendCircleService.getFriendCircle(uid,iTotal,iLimit, isDebug, logback); 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String resultJson = gson.toJson(result); 
		return resultJson;
	}
	
	public int getInt(String str,int defau){
		int i ;
		if(str==null){
			i = defau;
		}else{
			try{ 
				i = Integer.parseInt(str); 
				i = i<=0?defau:i;
			}catch(Exception e){
				log.info(e.getMessage());
				i = defau;
			}
		} 
		return i;
	}
	
	@RequestMapping("/echartTest")
	public String echartTest(HttpServletRequest request) {
		String url = request.getParameter("url");
		if (url!=null) {
			url = BaseUnit.encode(url, "utf8");
			request.setAttribute("url", "http://localhost:8080/data/api/linkedin/userinfo?url=" + url);
		}
		return "sinaWeibo/echart_test";
	}
}

package com.crawlermanage.controller.api.fahaicc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.crawler.fahaicc.domain.json.FahaiccLogin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.fahaicc.domain.json.FahaiItemFeed;
import com.crawler.storm.def.StormResultData;
import com.crawlermanage.service.fahaicc.FahaiccService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/fahaicc")
public class FahaiccApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FahaiccApiController.class);
	
	@Autowired
	private FahaiccService fahaiccService;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, boolean isDebug, String logback) {
		LOGGER.info("===================FahaiccApiController.login start!=========================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		//随机获取一个登录账户
		FahaiccLogin fahaiccLogin = FahaiccService.getRandomLoginInfo();
		username = fahaiccLogin.getName();
		password = fahaiccLogin.getPassword();
		LOGGER.info("username:{}, password:{}", username, password);

 		Result<StormResultData> result = fahaiccService.login(username, password, isDebug, logback);
		return gson.toJson(result);
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public String search(@RequestParam("cookies") String cookies, @RequestParam("q")String q, @RequestParam("pg") String pg, boolean isDebug
			, String logback) throws UnsupportedEncodingException {
		LOGGER.info("===================FahaiccApiController.search start!=========================");
		LOGGER.info("q:{}, pg:{}", q, pg);
		q = URLEncoder.encode(q, "utf8");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Result<List<FahaiItemFeed>> result = fahaiccService.search(cookies, q, pg, isDebug, logback);
		return gson.toJson(result);
	}
	
	
	@RequestMapping("/getDataOnce")
	@ResponseBody
	public String getDataOnce(String username, String password, 
			@RequestParam("q")String q, @RequestParam("pg") String pg, 
			boolean isDebug, String logback) throws UnsupportedEncodingException {
		LOGGER.info("===================FahaiccApiController.getDataOnce start!=========================");
		LOGGER.info("q:{}, pg:{}", q, pg);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		q = URLEncoder.encode(q, "utf8");
		
		//登录返回结果
		Result<List<FahaiItemFeed>> result = fahaiccService.getDataOnce(username, password, q, pg, isDebug, logback);
		return gson.toJson(result);
	}
}

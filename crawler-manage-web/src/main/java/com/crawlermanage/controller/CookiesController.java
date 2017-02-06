package com.crawlermanage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.module.redis.domain.CookieJson;
import com.module.redis.service.CookieService;


@Controller
@RequestMapping("/cookies")
public class CookiesController {
	@Autowired
	private CookieService cookieService;
	
	private static final Logger log = LoggerFactory.getLogger(CookiesController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String home(@RequestParam("cookies") String cookies) {    
		log.info(cookies);
		
		cookieService.save(cookies);
		
		//showCookies ();
		
		return cookies; 
	} 
	
	public void showCookies(){
		List<CookieJson> list = cookieService.queryAllCookies();
		for (int i=0; i<list.size(); i++) {
			log.info("queryAllCookies_" + (i+1) + ":" + list.get(i));
		}
	}

} 

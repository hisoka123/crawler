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

import com.crawlermanage.service.auth.ActiveUserService;
import com.module.dao.entity.auth.ActiveUser;


@Controller
@RequestMapping("/activeuser")
public class ActiveUserController {

	@Autowired
	private ActiveUserService activeUserService;
	
	private static final Logger log = LoggerFactory.getLogger(CookiesController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String home(@RequestParam("aaa") String aaa) {    
		log.info(aaa);
		
		//List<ActiveUser> aus = activeUserService.aaa();
		
		//log.info("aus.size----------"+aus.size());
		 
		return null; 
	} 
	
}

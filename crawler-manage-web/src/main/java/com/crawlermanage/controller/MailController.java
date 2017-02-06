package com.crawlermanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.service.sender.EmailSenderService;
import com.module.mail.MimeMailService;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	private static final Logger log = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private EmailSenderService mailService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String home(@RequestParam("text") String text,@RequestParam("sendto") String sendto) {    
		String subject = "突发事件信息推送";
		log.info("sendTo:{},subject:{}",sendto,subject);
		mailService.sendMail(text, sendto, subject);
		return text; 
	}

}

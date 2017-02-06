package com.crawlermanage.service.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.mail.MimeMailService;

@Component
public class EmailSenderService {
	
	@Autowired
	private MimeMailService mimeMailService;
	
	public void sendMail(String text,String sendTo,String subject) {
		 mimeMailService.sendMail(text, sendTo, subject);
	}

}

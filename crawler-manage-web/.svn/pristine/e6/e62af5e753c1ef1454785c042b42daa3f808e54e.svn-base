package com.crawlermanage.controller.modules;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.websocket.Decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/modules/sinaweibo")
public class SinaweiboController {

	private static final Logger log = LoggerFactory.getLogger(SinaweiboController.class);
	
	
	@RequestMapping("/sinaSearch")
	public String toSinaSearch(){
		return "/modules/sinaweibo/sinaSearch";
	}
	@RequestMapping("sinaDetail")
	public String toSinaDetail(){
		 return "/modules/sinaweibo/sinaDetail";
	}
	
	
	
	
   
	@RequestMapping("/")
	public String redirectToSearch1(){
		  return "/modules/sinaweibo/sinaSearch";
	}
	@RequestMapping("")
	public String redirectToSearch2(){
		return "/modules/sinaweibo/sinaSearch";
	}
	
	
	
	
	
	
	
	
	
}

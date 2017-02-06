package com.crawlermanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crawlermanage.service.TweetService;
import com.module.dao.entity.data.Tweet;

@Controller
@RequestMapping("/test")
public class TweetController {

     private static final Logger log = LoggerFactory.getLogger(TweetController.class);
	
	
	 
	@Autowired
	private TweetService tweetService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
    public String home(Model model,Long id) {    
		log.info("id----------"+id);
		
		Tweet tweet = tweetService.getTweet(id);
		
		log.info("tweet----------"+tweet); 
		
		
		model.addAttribute("tweet", tweet);
		 
		return "index";
		}
	
 

}

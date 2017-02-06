package com.crawlermanage.controller.ownerTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ownerTask")
public class FeedBackController {

	   
	   @RequestMapping("/feedback")
	   public String toFeedBack2(){
		     return "/ownerTask/feedback";
	   }
}

package com.crawlermanage.controller.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/modules/youku")
public class YoukuController {

	
	@RequestMapping("/youkuSearch")
	  public String toYoukuSearch(){
		     return "/modules/youku/youkuSearch";
	  }
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  @RequestMapping("/")
	  public String redirectToYoukuSearch1(){
		    return "/modules/youku/youkuSearch";
	  }
	  
	  @RequestMapping("")
	  public String redirectToYoukuSearch2(){
		    return "/modules/youku/youkuSearch";
	  }
}

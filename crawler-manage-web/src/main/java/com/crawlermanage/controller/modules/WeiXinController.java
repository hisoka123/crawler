package com.crawlermanage.controller.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modules/weixin")
public class WeiXinController {

	
	@RequestMapping("/weixinSearch")
	  public String toWeixinSearch(){
		     return "/modules/weixin/weixinSearch";
	  }
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  @RequestMapping("/")
	  public String redirectToWeixinSearch1(){
		    return "redirect:/modules/weixin/weixinSearch";
	  }
	  
	  @RequestMapping("")
	  public String redirectToWeixinSearch2(){
		    return "redirect:/modules/weixin/weixinSearch";
	  }
}

package com.crawlermanage.controller.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modules")
public class ModulesController {
      
  @RequestMapping("/")
  public String redirectToIndex1(){
	     return "redirect:index";
  }
  @RequestMapping("")
  public String redirectToIndex2(){
	     return "redirect:/modules/index";
  }
  
  @RequestMapping("/index")
  public String toIndex(){
	  return "/modules/index";
  }
  
  
 
  
}

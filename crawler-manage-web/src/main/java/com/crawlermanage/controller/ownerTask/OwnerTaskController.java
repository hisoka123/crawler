package com.crawlermanage.controller.ownerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ownerTask")
public class OwnerTaskController {

	   private static final Logger log=LoggerFactory.getLogger(OwnerTaskController.class);
	   
	   
	   @RequestMapping("")
	   public String toOwnerTaskHome1(){
		      return "/ownerTask/homeTask";
	   }
	 
	   
	   //全部任务列表页
	   @RequestMapping("/homeTask")
	   public String toOwnerTaskHome2(){
		      return "/ownerTask/homeTask";
		   
	   }
	   
	   
	
	
}

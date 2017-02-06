package com.crawlermanage.controller.ownerTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ownerTask")
public class GsxtOTController {

	  //工商网任务列表页
	   @RequestMapping("/gsxtTask")
	   public String toGsxt(){
		      return "/ownerTask/gsxtTask";
	   }
	
}

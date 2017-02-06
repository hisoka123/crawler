package com.crawlermanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.module.drpc.StormDrpc;

@Controller 
public class HomeController {
	
	@Autowired
	StormDrpc stormDrpc;

	@RequestMapping("/home")
    public String home(){ 
   	 	return "home"; 
    }
	
	@RequestMapping("/index")
    public String index(){ 
   	 	return "index"; 
    }
	
	@RequestMapping("/cluster")
    public String cluster(Model model){ 
		model.addAttribute("stormHost", stormDrpc.getHost());
		model.addAttribute("stormPort", stormDrpc.getPort());
   	 	return "storm-ui/cluster"; 
    } 
}

package com.crawlermanage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketDemoController {

	static Logger logger = LoggerFactory.getLogger(WebSocketDemoController.class);
 
	@Bean
    public WebsocketEndPoint websocketEndPoint() {
        return new WebsocketEndPoint();
    }
	
	@RequestMapping("/websocketdemo") 
    public String wiki(){ 
   	 	return "demo/websocket"; 
    }
	
	
	
	

}

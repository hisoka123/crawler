/**
 * 
 */
package com.dataservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dataservice.helper.NetWorkHelper;

/**
 * @author kingly
 * @date 2015年8月26日
 * 
 */
@Controller
public class HomeController {
	
	//@Autowired
	//private UserFeedDao userFeedDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	
	/**
	 * 在本controller所有方法执行前执行的方法
	 * @param request
	 */
	@ModelAttribute
	public void accessLog(HttpServletRequest request) {
		LOGGER.info("The current User's IP: {}", NetWorkHelper.getIpAddr(request));
	}
	
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
}

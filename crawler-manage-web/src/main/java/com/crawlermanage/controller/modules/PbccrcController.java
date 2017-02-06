package com.crawlermanage.controller.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modules/pbccrc")
public class PbccrcController {

	@RequestMapping(value={"", "pbccrcAuth"})
	public String redirectToSearch1() {
		return "/modules/pbccrc/pbccrcAuth";
	}
	
	@RequestMapping(value="/chptest")
	public String redirectToChpTest() {
		return "/modules/pbccrc/pbccrc-chp-test";
	}
}

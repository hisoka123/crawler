/**
 * 
 */
package com.crawlermanage.controller.api.linkedin;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawlermanage.service.linkedin.LinkedinUserSearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;

/**
 * @author kingly
 *
 */

@Controller
@RequestMapping("/api/linkedin")
public class LinkedinUserSearchController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkedinUserSearchController.class);

	@Autowired
	private LinkedinUserSearchService linkedinUserSearchService;

	@RequestMapping(value = "/user")
	@ResponseBody
	public String home(@RequestParam("q") String q, boolean isDebug) {
		LOGGER.info("=====================================LinkedinUserSearchController.home started!===============================================");
		LOGGER.info("q:{}", q);
        q = BaseUnit.encode(q, "utf8");
        LOGGER.info("q2:{}", q);
		String url = "http://www.linkedin.com/vsearch/f?type=all&keywords=" + q;
		LOGGER.info("url:{}", url);

		Result<List<UserFeedJson>> result = linkedinUserSearchService.searchUser(url, isDebug);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String resultJson = gson.toJson(result);
		resultJson = resultJson.replaceAll(LinkedinUserSearchService.getUTF8_MARK(), "\\\\u");
		
		return resultJson;
	}

}

package com.crawlermanage.controller.api.creditchina;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.creditchina.domain.jsontwo.CompanyRecord;
import com.crawler.domain.json.Result;
import com.crawlermanage.service.creditchina.CreditchinaSearchService;
import com.crawlermanage.service.creditchina.CreditchinaTaskService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/creditchina")
public class CreditchinaController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CreditchinaController.class);

	@Autowired
	private CreditchinaSearchService creditchinaSearchService;
	@Autowired
	private CreditchinaTaskService creditchinaTaskService;

	@RequestMapping(value = "/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword,
			@RequestParam("objectType") String objectType, boolean isDebug,
			String logback) {
		LOGGER.info("=============================CreditchinaController.getDataOnce======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Result<List<CompanyRecord>> result = creditchinaSearchService
				.searchWithOCR(keyword, objectType, isDebug, logback);
		return gson.toJson(result);
	}

	@RequestMapping(value = "/searchWithTest")
	@ResponseBody
	public String searchWithTest() {
		LOGGER.info("=============================CreditchinaController.searchWithTest======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, Object> result = creditchinaTaskService.creditchinaTask();
		return gson.toJson(result);
	}

}

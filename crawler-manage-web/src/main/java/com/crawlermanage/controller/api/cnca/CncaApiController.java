package com.crawlermanage.controller.api.cnca;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.cnca.domain.json.Certificate;
import com.crawler.cnca.domain.json.CertificateCompanyData;
import com.crawler.cnca.domain.json.CncaResult;
import com.crawler.cnca.domain.json.CncaResultData;
import com.crawler.domain.json.Result;
import com.crawlermanage.service.cnca.CncaService;
import com.crawlermanage.service.cnca.CncaTaskService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/cncaApiController")
public class CncaApiController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CncaApiController.class);

	@Autowired
	private CncaService cncaService;
	@Autowired
	private CncaTaskService cncaTaskService;

	private String changeResultToJson(Object obj) {

		return new GsonBuilder().setPrettyPrinting().create().toJson(obj);

	}

	@RequestMapping("/getSearchPage")
	@ResponseBody
	public String getSearchPage(String logback, boolean isDebug) {

		LOGGER.info("--------------cncaApiController=====getSearchPage--------------------");

		Result<CncaResultData> searchPage = cncaService.getSearchPage(isDebug,
				logback);
		return changeResultToJson(searchPage);

	}

	@RequestMapping(value = "/searchCompanyList")
	@ResponseBody
	public String searchCompanyList(String serializedFileName, String keyword,
			String randomCode, boolean isDebug, String logback)
			throws UnsupportedEncodingException {

		LOGGER.info("=============================CncaApiController.searchCompanyList======================================");
		Result<CertificateCompanyData> result = cncaService.searchCompanyList(
				serializedFileName, keyword, randomCode, isDebug, logback);

		return changeResultToJson(result);

	}

	@RequestMapping(value = "/searchCncaList")
	@ResponseBody
	public String searchCncaList(String serializedFileName, String keyword,
			String orgCode, String checkC, String randomCode, boolean isDebug,
			String logback) throws UnsupportedEncodingException {

		LOGGER.info("=============================CncaApiController.searchCncaList======================================");
		Result<CncaResult> result = cncaService.searchCncaList(
				serializedFileName, keyword, orgCode, checkC, randomCode,
				isDebug, logback);

		return changeResultToJson(result);

	}

	@RequestMapping(value = "/searchCncaDetail")
	@ResponseBody
	public String searchCncaDetail(String serializedFileName, String rzjgId,
			String certNo, String checkC, String showtemp, boolean isDebug,
			String logback) throws UnsupportedEncodingException {

		LOGGER.info("=============================CncaApiController.searchCncaDetail======================================");
		Result<Certificate> result = cncaService.searchCncaDetail(
				serializedFileName, rzjgId, certNo, checkC, showtemp, isDebug,
				logback);

		return changeResultToJson(result);

	}

	@RequestMapping(value = "/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword,
			boolean isDebug, String logback) {

		LOGGER.info("=============================CncaApiController.searchWithOCR======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Result<List<Certificate>> result = cncaService.searchWithOCR(keyword,
				isDebug, logback);
		return gson.toJson(result);

	}

	@RequestMapping(value = "/searchWithTest")
	@ResponseBody
	public String searchWithTest() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, Object> result = cncaTaskService.cncaTask();
		return gson.toJson(result);

	}

}

package com.crawlermanage.controller.api.customs;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.customs.domain.json.Customs;
import com.crawler.customs.domain.json.CustomsResultData;
import com.crawler.domain.json.Result;
import com.crawlermanage.service.customs.CustomsService;
import com.crawlermanage.service.customs.CustomsTaskService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/customs")
public class CustomsGetController {

	@Autowired
	private CustomsService customsService;
	@Autowired
	private CustomsTaskService customsTaskService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomsGetController.class);

	/**
	 * 获取查询页cookie及OCR验证码识别
	 * 
	 * @param isDebug
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getSearchPage")
	@ResponseBody
	public String getSearchPage(boolean isDebug, String logback)
			throws IOException {

		LOGGER.info("========================CustomsGetController.getSearchPage============================");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Result<CustomsResultData> result = customsService.getSearchPage(
				isDebug, logback);

		if (result.getData() == null) {
			return gson.toJson(result);
		}

		// String imageUrl = result.getData().getImageUrl();
		// String imageCode = getImageCodeByOCR(imageUrl);
		// result.getData().setCodeImageValue(imageCode);

		String resultJson = gson.toJson(result);

		LOGGER.info("CustomsService.getSearchPage service result data:{}",
				resultJson);

		return resultJson;

	}

	/**
	 * 查询海关信用信息
	 * 
	 * @param cookies
	 *            登录页的cookies
	 * @param copName
	 *            查询关键字
	 * @param randomCode
	 *            登录页图片验证码
	 * @param isDebug
	 * @return
	 */
	@RequestMapping(value = "/searchResultList")
	@ResponseBody
	public String searchResultList(String copName, String randomCode,
			String cookies, boolean isDebug, String logback) {

		LOGGER.info("=============================CustomsGetController.searchResultList======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// 查询列表
		Result<CustomsResultData> searchResultList = customsService
				.excuteSearch(cookies, copName, randomCode, isDebug, logback);

		return gson.toJson(searchResultList);

	}

	/**
	 * 查询海关信用信息
	 * 
	 * @param cookies
	 *            登录页的cookies
	 * @param seqNo
	 *            参数一
	 * @param saicSysNo
	 *            参数二
	 * @param isDebug
	 * @return
	 */
	@RequestMapping(value = "/searchResultDetail")
	@ResponseBody
	public String searchResultDetail(String seqNo, String saicSysNo,
			String cookies, boolean isDebug, String logback) {

		LOGGER.info("=============================CustomsGetController.searchResultDetail======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// 查询
		Result<Customs> result = customsService.searchDetail(seqNo, saicSysNo,
				cookies, isDebug, logback);

		return gson.toJson(result);

	}

	@RequestMapping(value = "/getDataOnce")
	@ResponseBody
	public String getDataOnce(@RequestParam("keyword") String keyword,
			boolean isDebug, String logback) {

		LOGGER.info("=============================CustomsGetController.searchWithOCR======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Result<List<Customs>> result = customsService.searchWithOCR(keyword,
				isDebug, logback);
		return gson.toJson(result);

	}

	@RequestMapping(value = "/searchWithTest")
	@ResponseBody
	public String searchWithTest() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, Object> result = customsTaskService.customsTask();
		return gson.toJson(result);

	}

}

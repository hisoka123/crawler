package com.crawlermanage.service.customs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.customs.domain.json.Customs;
import com.crawler.customs.domain.json.CustomsResultData;
import com.crawler.customs.htmlparser.CustomsParser;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.CustomsParam;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.SearchDetail;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class CustomsService {

	@Autowired
	private CrawlerEngine crawlerEngine;
	@Autowired
	private CustomsParser customsParser;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomsService.class);

	public Result<CustomsResultData> getSearchPage(boolean isDebug,
			String logback) {

		Result<CustomsResultData> result = new Result<CustomsResultData>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CUSTOMS_TOSEARCHPAGE);

		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);

		String param = fcm.toJson();
		LOGGER.info("CustomsService.getSearchPage param:{}", param);
		result = crawlerEngine.execute(param, result);

		if (!StringUtils.isEmpty(result.getHtml())) {
			Gson gson = new GsonBuilder().create();
			Type prdType = new TypeToken<CustomsResultData>() {
			}.getType();
			CustomsResultData prd = gson.fromJson(result.getHtml(), prdType);
			LOGGER.info("CustomsService.getSearchPage storm result data:{}",
					prd.toString());
			result.setData(new CustomsResultData(prd.getStatusCode(), prd
					.getMessage(), prd.getCookies(), prd.getImageUrl()));
		}

		result.debugMode(isDebug);
		return result;

	}

	/**
	 * 
	 * @param cookies
	 *            查询页的cookies
	 * @param copName
	 *            关键字
	 * @param randomCode
	 *            查询图片验证码
	 * @param isDebug
	 * @return Result<CustomsResultData>
	 */
	public Result<CustomsResultData> excuteSearch(String cookies,
			String copName, String randomCode, boolean isDebug, String logback) {

		Result<CustomsResultData> result = new Result<CustomsResultData>();

		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<HashSet<Cookie>>() {
		}.getType();
		Set<Cookie> cookieSet = gson.fromJson(cookies, type);

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CUSTOMS_SEARCH);

		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);

		CustomsParam customsParam = new CustomsParam(copName, randomCode,
				cookieSet);
		fcm.setCustomsParam(customsParam);
		String param = fcm.toJson();

		LOGGER.info("customsService.excuteSearch param:{}", param);
		result = crawlerEngine.execute(param, result);

		Type type2 = new TypeToken<CustomsResultData>() {
		}.getType();
		CustomsResultData customsResultData = gson.fromJson(result.getHtml(),
				type2);

		if (!StringUtils.isEmpty(customsResultData.getHtml())) {
			LOGGER.info("customsService.excuteSearch storm result data:{}",
					result.getHtml());
			List<com.crawler.customs.domain.json.SearchDetail> searchArgsList = customsParser
					.getSearchArgs(customsResultData.getHtml());
			customsResultData.setSearchArgsList(searchArgsList);
			customsResultData.debugMode(isDebug);
			result.setData(customsResultData);
		}

		result.getData().setHtml(null);
		result.debugMode(isDebug);
		return result;

	}

	public Result<Customs> searchDetail(String seqNo, String saicSysNo,
			String cookies, boolean isDebug, String logback) {

		Result<Customs> result = new Result<Customs>();

		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<HashSet<Cookie>>() {
		}.getType();
		Set<Cookie> cookieSet = gson.fromJson(cookies, type);
		SearchDetail searchDetail = new SearchDetail();
		searchDetail.setSeqNo(seqNo);
		searchDetail.setSaicSysNo(saicSysNo);
		searchDetail.setCookies(cookieSet);

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CUSTOMS_DETAIL);
		fcm.setSearchDetail(searchDetail);

		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);

		String param = fcm.toJson();

		result = crawlerEngine.execute(param, result);

		LOGGER.info("The return CustomsService.searchDetail is {}",
				gson.toJson(result));

		Type type2 = new TypeToken<CustomsResultData>() {
		}.getType();
		CustomsResultData customsResultData = gson.fromJson(result.getHtml(),
				type2);

		String customsDetail = customsResultData.getHtml();
		if (!StringUtils.isEmpty(customsDetail)) {
			LOGGER.info("customsService.searchDetail storm result data:{}",
					customsDetail);
			Customs customs = customsParser.customsListParser(customsResultData
					.getHtml());
			// customsResultData.setSearchArgsList(searchArgsList);
			result.setData(customs);
			customsResultData.debugMode(isDebug);
		}

		result.debugMode(isDebug);

		return result;

	}

	public Result<List<Customs>> searchWithOCR(String keyword, boolean isDebug,
			String logback) {

		LOGGER.info("=============CustomsService.searchWithOCR start!=================");
		Gson gson = new GsonBuilder().create();
		Result<List<Customs>> result = new Result<List<Customs>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CUSTOMS_WITH_OCR);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		// webParam.setSearchPage("");
		// webParam.setCodeImageId("");
		webParam.addParam("keyword", keyword);
		// webParam.addParam("keywordXpath", "");
		// webParam.addParam("imagecodeXpath", "");
		// webParam.addParam("loginBtnXpath", "");
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info(
				"The CustomsService.searchWithOCR functionCallParam is: {}",
				param);
		result = crawlerEngine.execute(param, result);
		if (result.getError() != null) {
			return result;
		}
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		// String ip = (String) resultHtmlMap.get("ip");
		// String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			List<Customs> customsList = new ArrayList<Customs>();
			@SuppressWarnings("unchecked")
			List<String> wds = (List<String>) resultHtmlMap
					.get("creditCustoms");
			if (null != wds && !wds.isEmpty() && wds.size() > 0) {
				for (String wd : wds) {
					Customs customs = customsParser.customsListParser(wd);
					customsList.add(customs);
				}
			}
			result.setData(customsList);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap
					.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			// errorMsg.setIp(ip);
			// errorMsg.setHostName(hostName);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl
						+ "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			// errorMsg.setIp(ip);
			// errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl
					+ "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			// errorMsg.setIp(ip);
			// errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl
					+ "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			// errorMsg.setIp(ip);
			// errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl
					+ "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		return result;

	}

}

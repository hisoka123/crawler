package com.storm.function;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.crawler.creditchina.domain.json.Creditchina;
import com.crawler.creditchina.domain.json.CreditchinaList;
import com.crawler.creditchina.domain.json.CreditchinaResult;
import com.crawler.domain.json.StatusCodeDef;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.storm.domian.CreditchinaParam;
import com.storm.domian.WebParam;

public class CreditchinaFunction {

	private static final String SEARCH_PAGE = "http://www.creditchina.gov.cn/credit_info_search?t="
			+ new Date().getTime();

	public class ResultData {
		public String statusCode;
		public String message;
		public String html;
	}

	private String parseResultDataToJson(ResultData resultData) {
		return new GsonBuilder().create().toJson(resultData);
	}

	public String searchWithOCR(CreditchinaParam creditchinaParam,
			WebParam webParam) throws FailingHttpStatusCodeException,
			IOException {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CreditchinaFunction.class, webParam.getLogback());
		ResultData resultData = new ResultData();
		String keyword = creditchinaParam.getKeyword();
		String objectType = creditchinaParam.getObjectType();
		String page = creditchinaParam.getPage();
		if (StringUtils.isEmpty(keyword)) {
			resultData.statusCode = StatusCodeDef.FAILURE;
			resultData.message = "输入的查询关键字为空，请重新输入！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of CreditchinaFunction.search is:"
					+ resultDataToJson);
			LOGGER.returnRedisResource();
			return resultDataToJson;
		}
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.addRequestHeader("Origin", "http://www.creditchina.gov.cn");
		webClient.addRequestHeader("Referer",
				"http://www.creditchina.gov.cn/search_all");
		WebRequest requestLogin = new WebRequest(new URL(
				CreditchinaFunction.SEARCH_PAGE + "&keyword="
						+ URLEncoder.encode(keyword, "utf-8")), HttpMethod.POST);
		ArrayList<NameValuePair> nameValuePairsSearch = new ArrayList<NameValuePair>();
		nameValuePairsSearch.add(new NameValuePair("dataType", "1"));
		nameValuePairsSearch.add(new NameValuePair("exact", "0"));
		nameValuePairsSearch.add(new NameValuePair("objectType", objectType));
		nameValuePairsSearch.add(new NameValuePair("page", page));
		nameValuePairsSearch.add(new NameValuePair("searchtype", "0"));
		requestLogin.setRequestParameters(nameValuePairsSearch);
		Page searchPage = webClient.getPage(requestLogin);
		WebResponse searchResponse = searchPage.getWebResponse();
		String resContent = searchResponse.getContentAsString();
		if (null == resContent || StringUtils.isEmpty(resContent)) {
			resultData.statusCode = StatusCodeDef.FAILURE;
			resultData.message = "查询失败！";
		} else {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			CreditchinaResult creditchinaResult = gson.fromJson(resContent,
					CreditchinaResult.class);
			if (creditchinaResult.getTotalCount() > 0) {
				CreditchinaList creditchinaList = creditchinaResult.getResult();
				List<Creditchina> results = creditchinaList.getResults();
				if (null != results && results.size() > 0) {
					resultData.statusCode = StatusCodeDef.SCCCESS;
					resultData.message = "查询成功，并且找到了相应的返回结果！";
					List<Map<String, Object>> wds = new ArrayList<Map<String, Object>>();
					for (Creditchina creditchina : results) {
						Integer a = creditchina.getObjectType();
						String b = creditchina.getEncryStr();
						WebRequest requestSearch = new WebRequest(new URL(
								"http://www.creditchina.gov.cn/credit_info_detail?objectType="
										+ a + "&encryStr=" + b), HttpMethod.GET);
						Page wt = webClient.getPage(requestSearch);
						if (null != wt && wt.isHtmlPage()) {
							Map<String, Object> wd = new HashMap<String, Object>();
							HtmlPage fy = (HtmlPage) wt;
							wd.put("companyName", creditchina.getName());
							wd.put("objectType",
									String.valueOf(creditchina.getObjectType()));
							wd.put("xyzgxqym", fy.asXml());
							wds.add(wd);
						}
					}
					resultData.html = gson.toJson(wds);
				} else {
					resultData.statusCode = StatusCodeDef.FAILURE;
					resultData.message = "出现了未知错误！";
					resultData.html = new GsonBuilder().create().toJson(
							creditchinaResult);
				}
			} else {
				resultData.statusCode = StatusCodeDef.NO_DATA_FOUND;
				resultData.message = "查询成功，该公司没有相应的返回结果！";
				resultData.html = new GsonBuilder().create().toJson(
						creditchinaResult);
			}
		}
		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of CreditchinaFunction.search is:"
				+ resultDataToJson);
		webClient.removeRequestHeader("Origin");
		webClient.removeRequestHeader("Referer");
		LOGGER.returnRedisResource();
		return resultDataToJson;
	}

	public String search(CreditchinaParam creditchinaParam, WebParam webParam)
			throws FailingHttpStatusCodeException, IOException {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CreditchinaFunction.class, webParam.getLogback());

		ResultData resultData = new ResultData();

		String keyword = creditchinaParam.getKeyword();
		String page = creditchinaParam.getPage();
		if (StringUtils.isEmpty(keyword)) {
			resultData.statusCode = StatusCodeDef.USERNAME_OR_PASSWORD_ERROR;
			resultData.message = "输入的查询关键字为空！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of CreditchinaFunction.search is:"
					+ resultDataToJson);
			return resultDataToJson;
		}

		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.addRequestHeader("Origin", "http://www.creditchina.gov.cn");
		webClient.addRequestHeader("Referer",
				"http://www.creditchina.gov.cn/search_all");

		WebRequest requestLogin = new WebRequest(new URL(
				CreditchinaFunction.SEARCH_PAGE + "&keyword="
						+ URLEncoder.encode(keyword, "utf-8")), HttpMethod.POST);
		ArrayList<NameValuePair> nameValuePairsSearch = new ArrayList<NameValuePair>();
		nameValuePairsSearch.add(new NameValuePair("areas", ""));
		// nameValuePairsSearch.add(new NameValuePair("keyword", keyword));
		nameValuePairsSearch.add(new NameValuePair("page", page));
		nameValuePairsSearch.add(new NameValuePair("sources", ""));
		requestLogin.setRequestParameters(nameValuePairsSearch);

		Page searchPage = webClient.getPage(requestLogin);
		WebResponse searchResponse = searchPage.getWebResponse();
		String resContent = searchResponse.getContentAsString();

		if (null == resContent || StringUtils.isEmpty(resContent)) {
			resultData.statusCode = StatusCodeDef.FAILURE;
			resultData.message = "查询失败！";
		} else {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			CreditchinaResult creditchinaResult = gson.fromJson(resContent,
					CreditchinaResult.class);
			if (creditchinaResult.getTotalCount() > 0) {
				resultData.html = resContent;
				resultData.statusCode = StatusCodeDef.SCCCESS;
				resultData.message = "查询成功，并且找到了相应的返回结果！";
			} else {
				resultData.html = resContent;
				resultData.statusCode = StatusCodeDef.NO_DATA_FOUND;
				resultData.message = "查询成功，没有找到该公司的相应的返回结果！";
			}
		}

		webClient.removeRequestHeader("Origin");
		webClient.removeRequestHeader("Referer");

		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of CreditchinaFunction.search is:"
				+ resultDataToJson);

		LOGGER.returnRedisResource();

		return resultDataToJson;
	}

}

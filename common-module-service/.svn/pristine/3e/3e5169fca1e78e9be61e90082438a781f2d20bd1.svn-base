package com.crawlermanage.service.creditchina;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.creditchina.domain.jsontwo.CompanyRecord;
import com.crawler.creditchina.htmlparser.CreditchinaParser;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.CreditchinaParam;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class CreditchinaSearchService {

	@Autowired
	private CrawlerEngine crawlerEngine;
	@Autowired
	private CreditchinaParser creditchinaParser;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CreditchinaSearchService.class);

	/**
	 * 信用中国（定时任务OCR调用）
	 * 
	 * @param keyword
	 * @param objectType
	 * @param isDebug
	 * @param logback
	 * @return
	 */
	public Result<List<CompanyRecord>> searchWithOCR(String keyword,
			String objectType, boolean isDebug, String logback) {
		String page = "1";
		Result<List<CompanyRecord>> result = new Result<List<CompanyRecord>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CREDITCHINA_OCR);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		CreditchinaParam creditchinaParam = new CreditchinaParam();
		creditchinaParam.setKeyword(keyword);
		creditchinaParam.setObjectType(objectType);
		creditchinaParam.setPage(page);
		fcm.setCreditchinaParam(creditchinaParam);
		String param = fcm.toJson();
		result = crawlerEngine.execute(param, result);
		String respHtml = result.getHtml();
		LOGGER.info("CreditchinaSearchService.searchWithOCR方法返回的结果是：{}",
				respHtml);
		JsonObject obj = new JsonParser().parse(respHtml).getAsJsonObject();
		String statusCode = obj.get("statusCode").getAsString();
		String ccrs = obj.get("html").getAsString();
		if (StatusCodeDef.SCCCESS.equals(statusCode)) {
			List<CompanyRecord> wds = creditchinaParser.getListFromHtml(ccrs);
			result.setData(wds);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCode)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCode)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		return result;
	}

}

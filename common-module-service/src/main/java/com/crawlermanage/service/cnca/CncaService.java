package com.crawlermanage.service.cnca;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.cnca.domain.json.Certificate;
import com.crawler.cnca.domain.json.CertificateCompanyData;
import com.crawler.cnca.domain.json.CncaResult;
import com.crawler.cnca.domain.json.CncaResultData;
import com.crawler.cnca.htmlparser.CncaParser;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Component
public class CncaService {

	@Autowired
	private CrawlerEngine crawlerEngine;
	@Autowired
	private CncaParser cncaParser;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CncaService.class);

	public Result<CncaResultData> getSearchPage(boolean isDebug, String logback) {

		Result<CncaResultData> result = new Result<CncaResultData>();

		FunctionCallParam fcm = new FunctionCallParam();

		fcm.setFunction(FunctionDefine.COMMON_TOSEARCHPAGE);

		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage(CncaParser.CNCA_SEARCHPAGE);
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Origin", "http://cx.cnca.cn");
		requestHeaders.put("Referer",
				"http://cx.cnca.cn/rjwcx/web/cert/publicCert.do?progId=10");
		webParam.setRequestHeaders(requestHeaders);
		webParam.setCodeImageId("//img[@id='checkCodeImg']");
		fcm.setWebEngineParam(webParam);

		String param = fcm.toJson();
		LOGGER.info("CommonService.getSearchPage param:{}", param);

		result = crawlerEngine.execute(param, result);

		if (!StringUtils.isEmpty(result.getHtml())) {
			Gson gson = new GsonBuilder().create();
			Type prdType = new TypeToken<CncaResultData>() {
			}.getType();
			CncaResultData prd = gson.fromJson(result.getHtml(), prdType);
			LOGGER.info("CommonService.getSearchPage storm result data:{}",
					prd.toString());
			result.setData(new CncaResultData(prd.getStatusCode(), prd
					.getMessage(), prd.getSerializedFileName(), prd
					.getImageUrl()));
		}

		result.debugMode(isDebug);

		return result;

	}

	public Result<CertificateCompanyData> searchCompanyList(
			String serializedFileName, String keyword, String randomCode,
			boolean isDebug, String logback)
			throws UnsupportedEncodingException {

		Result<CertificateCompanyData> result = new Result<CertificateCompanyData>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.COMMON_SEARCH);
		WebParam webParam = new WebParam();
		Map<String, String> params = new HashMap<String, String>();
		params.put("imagecode", randomCode);
		params.put("webSite", "cncaCompanyList");
		webParam.setParams(params);
		webParam.setLogback(logback);
		String orgName = URLEncoder.encode(keyword, "utf-8");
		webParam.setUrl("http://cx.cnca.cn/rjwcx/web/cert/queryOrg.do?certNumber=&orgName="
				+ orgName + "&queryType=public&checkCode=" + randomCode);
		webParam.setSerializedFileName(serializedFileName);
		webParam.setSearchPage(CncaParser.CNCA_SEARCHPAGE);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();

		LOGGER.info("commonService.searchCompanyList param:{}", param);
		result = crawlerEngine.execute(param, result);

		if (null != result.getHtml()) {
			Gson gson = new GsonBuilder().create();
			Type prdType = new TypeToken<CertificateCompanyData>() {
			}.getType();
			JsonObject obj = new JsonParser().parse(result.getHtml())
					.getAsJsonObject();
			String ccrs = obj.get("html").getAsString();
			CertificateCompanyData prd = gson.fromJson(ccrs, prdType);
			LOGGER.info("CommonService.searchCompanyList storm result data:{}",
					prd.toString());
			result.setData(prd);
		}

		result.debugMode(isDebug);

		return result;

	}

	public Result<CncaResult> searchCncaList(String serializedFileName,
			String keyword, String orgCode, String checkC, String randomCode,
			boolean isDebug, String logback)
			throws UnsupportedEncodingException {

		Result<CncaResult> result = new Result<CncaResult>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.COMMON_SEARCH);
		WebParam webParam = new WebParam();
		Map<String, String> params = new HashMap<String, String>();
		params.put("webSite", "companyCncaList");
		webParam.setParams(params);
		webParam.setLogback(logback);
		String orgName = URLEncoder.encode(keyword, "utf-8");
		webParam.setUrl("http://cx.cnca.cn/rjwcx/web/cert/list.do?progId=10&orgName="
				+ orgName
				+ "&orgCode="
				+ orgCode
				+ "&method=queryCertByOrg&needCheck=false&checkC="
				+ checkC
				+ "&randomCheckCode="
				+ randomCode
				+ "&queryType=public&page=1&rows=10&checkCode=");
		webParam.setSerializedFileName(serializedFileName);
		webParam.setSearchPage(CncaParser.CNCA_SEARCHPAGE);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();

		LOGGER.info("commonService.searchCompanyList param:{}", param);
		result = crawlerEngine.execute(param, result);

		if (null != result.getHtml()) {
			Gson gson = new GsonBuilder().create();
			Type prdType = new TypeToken<CncaResult>() {
			}.getType();
			JsonObject obj = new JsonParser().parse(result.getHtml())
					.getAsJsonObject();
			String ccrs = obj.get("html").getAsString();
			CncaResult prd = gson.fromJson(ccrs, prdType);
			LOGGER.info("CommonService.searchCncaList storm result data:{}",
					prd.toString());
			result.setData(prd);
		}

		result.debugMode(isDebug);

		return result;

	}

	public Result<Certificate> searchCncaDetail(String serializedFileName,
			String rzjgId, String certNo, String checkC, String showtemp,
			boolean isDebug, String logback)
			throws UnsupportedEncodingException {

		Result<Certificate> result = new Result<Certificate>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.COMMON_SEARCH);
		WebParam webParam = new WebParam();
		Map<String, String> params = new HashMap<String, String>();
		params.put("webSite", "companyCncaDetail");
		// params.put("realUrl","http://cx.cnca.cn/rjwcx/web/cert/show3C.do?rzjgId="
		// + rzjgId + "&certNo=" + certNo + "&checkC=" + checkC);
		webParam.setParams(params);
		webParam.setLogback(logback);
		// String wd = "http://cx.cnca.cn/rjwcx/web/cert/index.do?url=";
		// String sm = "web/cert/show3C.do?rzjgId=" + rzjgId + "&certNo=" +
		// certNo + "&checkC=" + checkC;
		// String ms = wd + URLEncoder.encode(URLEncoder.encode(sm, "utf-8"),
		// "utf-8");
		// webParam.setUrl("http://cx.cnca.cn/rjwcx/web/cert/show3C.do?rzjgId="
		// + rzjgId + "&certNo=" + certNo + "&checkC=" + checkC);
		String url = "";
		if ("1".equals(showtemp.trim())) {
			url = "http://cx.cnca.cn/rjwcx/web/cert/show.do?rzjgId=" + rzjgId
					+ "&certNo=" + certNo + "&checkC=" + checkC;
		} else if ("2".equals(showtemp.trim())) {
			url = "http://cx.cnca.cn/rjwcx/web/cert/show3C.do?rzjgId=" + rzjgId
					+ "&certNo=" + certNo + "&checkC=" + checkC;
		} else if ("3".equals(showtemp.trim())) {
			url = "http://cx.cnca.cn/rjwcx/web/cert/showNs.do?rzjgId=" + rzjgId
					+ "&certNo=" + certNo + "&checkC=" + checkC;
		} else if ("4".equals(showtemp.trim())) {
			url = "http://cx.cnca.cn/rjwcx/web/cert/show.do.do?rzjgId="
					+ rzjgId + "&certNo=" + certNo + "&checkC=" + checkC;
		} else {
			url = "http://cx.cnca.cn/rjwcx/web/cert/showZyxGy.do?rzjgId="
					+ rzjgId + "&certNo=" + certNo + "&checkC=" + checkC;
		}
		webParam.setUrl(url);
		webParam.setSerializedFileName(serializedFileName);
		webParam.setSearchPage(CncaParser.CNCA_SEARCHPAGE);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();

		LOGGER.info("commonService.searchCompanyList param:{}", param);
		result = crawlerEngine.execute(param, result);

		if (null != result.getHtml()) {
			JsonObject obj = new JsonParser().parse(result.getHtml())
					.getAsJsonObject();
			String ccrs = obj.get("html").getAsString();
			Certificate certificate = cncaParser.cncaDetailParser(ccrs);
			LOGGER.info("CommonService.searchCncaList storm result data:{}",
					certificate.toString());
			result.setData(certificate);
		}

		result.debugMode(isDebug);

		return result;

	}

	/**
	 * searchWithOCR（认证网OCR查询）
	 * 
	 * @param keyword
	 * @param isDebug
	 * @param logback
	 * @return
	 */
	public Result<List<Certificate>> searchWithOCR(String keyword,
			boolean isDebug, String logback) {

		LOGGER.info("=============CncaService.searchWithOCR start!=================");
		Gson gson = new GsonBuilder().create();
		Result<List<Certificate>> result = new Result<List<Certificate>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.COMMON_OCR);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage(CncaParser.CNCA_SEARCHPAGE);
		webParam.setCodeImageId("//img[@id='checkCodeImg']");
		webParam.addParam("webSite", "cnca");
		webParam.addParam("keyword", keyword);
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The CncaService.searchWithOCR functionCallParam is: {}",
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
			List<Certificate> cncaList = cncaParser.cncaListParser(resultHtml);
			result.setData(cncaList);
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
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			// errorMsg.setIp(ip);
			// errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		return result;

	}

}

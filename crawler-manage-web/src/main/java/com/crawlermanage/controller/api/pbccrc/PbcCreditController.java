package com.crawlermanage.controller.api.pbccrc;

import java.io.IOException;
import java.util.List;

import com.crawler.pbccrc.utils.Constants;
import com.crawler.pbccrc.utils.EncryptUtil;
import com.crawlermanage.service.pbccrc.PbccrcAccountSessionTask;
import com.crawlermanage.service.pbccrc.PbccrcDBService;
import com.module.dao.entity.pbccrc.PbccrcAccount;
import com.module.dao.entity.pbccrc.PlainPbccrcJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.pbccrc.domain.json.LoginData;
import com.crawler.pbccrc.domain.json.ReportData;
import com.crawler.pbccrc.domain.json.ResultData;
import com.crawlermanage.service.pbccrc.PbccrcService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.ocr.utils.OCRConnectUtils;

@Controller
@RequestMapping("/api/pbccrc")
public class PbcCreditController {

	@Autowired
	private PbccrcService pbccrcService;

	@Autowired
	private PbccrcDBService pbccrcDBService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PbcCreditController.class);

	
	/**
	 * 获取登录页cookie及OCR验证码识别
	 * @param isDebug
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getloginpage")
	@ResponseBody
	public String getLoginPage(boolean isDebug, String logback) throws IOException {
		LOGGER.info("=============================PbcCreditController.getLoginPage======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Result<ResultData> result = pbccrcService.getLoginPage(isDebug, logback);

		if (result.getData()==null) {
			return gson.toJson(result);
		}
		
		//String imageUrl = result.getData().getCodeImageUrl();
		//String imageCode = OCRConnectUtils.getVerifycode("/pbccrc/getVerifycode", imageUrl); //OCRUtils.getVerifycode("/pbccrc/getVerifycode",  imageUrl);
		//result.getData().setCodeValue(imageCode);
		
		String resultJson = gson.toJson(result);
		LOGGER.info("=======resultJson========"+resultJson);
		
		return resultJson;
	}
	
	/**
	 * 登录人行征信系统
	 * @param cookies 登录页的cookies
	 * @param username 用户名
	 * @param password 密码
	 * @param verifycode 登录页图片验证码
	 * @param isDebug
	 * @return 
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public String login(@RequestParam("cookies") String cookies,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("verifycode") String verifycode, boolean isDebug, String logback) {
		LOGGER.info("=============================PbcCreditController.login======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		LOGGER.info("==============cookies==========="+cookies);
		// 登录
		Result<LoginData> loginResult = pbccrcService.excuteLogin(cookies, username, password, verifycode, isDebug, logback);

		try {
			if(loginResult.getData()!=null && "0".equals(loginResult.getData().getStatusCode())){
				new PbccrcAccountSessionTask(username,password,cookies,pbccrcService).saveAccountInfo();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("========================saveAccountInfo error！==============================" + e.getMessage());
		}

		String json = gson.toJson(loginResult);
		LOGGER.info("json===PbcCreditController.login====="+json);
		return json;
	}
	
	
	/*====================================================================================*/
	/**
	 * @param cookies 登录前登陆页的cookies
	 * @param username 用户名
	 * @param password 密码
	 * @param verifycode 图片验证码
	 * @param tradecode 手机授权码
	 * @param isDebug
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getcredit")
	@ResponseBody
	public String getCredit(@RequestParam("cookies") String cookies,
			@RequestParam("username") final String username,
			@RequestParam("password") final String password,
			@RequestParam("verifycode") String verifycode,
			@RequestParam("tradecode") final String tradecode,final boolean isDebug, String logback) throws IOException {

		LOGGER.info("=============================PbcCreditController.getcredit======================================");


		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// 登录
		Result<LoginData> loginResult = pbccrcService.excuteLogin(cookies, username, password, verifycode, isDebug, logback);
		if (!StatusCodeDef.SCCCESS.equals(loginResult.getData().getStatusCode())) {
			return gson.toJson(loginResult);
		}

		// 获取报告
		final Result<ReportData> reportResult = pbccrcService.getCeditReport(gson.toJson(loginResult.getData().getCookies()), tradecode, isDebug, logback);



		return gson.toJson(reportResult);
	}
	
	@RequestMapping(value = "/logged/getcredit")
	@ResponseBody
	public String getCredit(@RequestParam("cookies") String cookies,
			@RequestParam("tradecode") String tradecode, boolean isDebug, String logback) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// 获取报告
		Result<ReportData> reportResult = pbccrcService.getCeditReport(cookies, tradecode, isDebug, logback);
		return gson.toJson(reportResult);
	}

	/**
	 * 获取人行征信报告测试（JSON）
	 * @param username
	 * @param password
	 * @param tradecode
	 * @param isDebug
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/test/getcredit")
	@ResponseBody
	public String getCreditTest(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("tradecode") String tradecode, boolean isDebug, String logback) throws IOException {
		LOGGER.info("=============================PbcCreditController.getCreditTest======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// 获取登录页
		Result<ResultData> result1 = pbccrcService.getLoginPage(isDebug, logback);
		if (result1==null) {
			return gson.toJson(result1);
		}
		String imageUrl = result1.getData().getCodeImageUrl();
		
		//OCR图片验证码
		String imageCode = OCRConnectUtils.getVerifycode("/pbccrc/getVerifycode", imageUrl); //OCRUtils.getVerifycode("/pbccrc/getVerifycode",  imageUrl);
		result1.getData().setCodeValue(imageCode);

		// 登录
		Result<LoginData> loginResult = pbccrcService.excuteLogin(gson.toJson(result1.getData().getCookies()), username, password, imageCode, isDebug, logback);
		if (StatusCodeDef.IMAGECODE_ERROR.equals(loginResult.getData().getStatusCode())) {
			loginResult.getData().setCodeImageUrl(imageUrl);
			loginResult.getData().setCodeValue(imageCode);
			return gson.toJson(loginResult);
		}
		if (!StatusCodeDef.SCCCESS.equals(loginResult.getData().getStatusCode())) {
			return gson.toJson(loginResult);
		}

		// 获取报告
		Result<ReportData> reportResult = pbccrcService.getCeditReport(gson.toJson(loginResult.getData().getCookies()), tradecode, isDebug, logback);
		return gson.toJson(reportResult);
	}
	
	
	
	/*====================================================================================*/
	/**
	 * 获取人行征信报告前的验证码页面
	 * @param username 征信用户名
	 * @param cookies 登陆成功的cookies
	 * @param isDebug 是否测试模式
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/logged/getpdfreportpage")
	@ResponseBody
	public String getPDFReportPage(@RequestParam("username") String username,
			@RequestParam("cookies") String cookies,
			boolean isDebug, String logback) throws IOException {
		LOGGER.info("=============================PbcCreditController.getPDFReportPage======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Result<ResultData> result = pbccrcService.getPDFReportImageCodePage(username, cookies, isDebug, logback);

		if (result.getData()==null) {
			return gson.toJson(result);
		} else if (StatusCodeDef.DATA_CACHED.equals(result.getData().getStatusCode())) {
			return gson.toJson(result);
		}
		
		String imageUrl = result.getData().getCodeImageUrl();
		String imageCode = OCRConnectUtils.getVerifycode("/pbccrc/getVerifycode", imageUrl); //OCRUtils.getVerifycode("/pbccrc/getVerifycode",  imageUrl);
		result.getData().setCodeValue(imageCode);

		String resultJson = gson.toJson(result);
		return resultJson;
	}
	
	/**
	 * 获取人行征信报告（PDF）
	 * @param username 征信用户名
	 * @param cookies 登录成功后的cookies
	 * @param verifycode 人行PDF征信报告图片验证码
	 * @param isDebug
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/logged/getpdfcredit")
	@ResponseBody
	public String getPDFCeditReport(@RequestParam("username") String username,
			@RequestParam("cookies") String cookies,
			@RequestParam("verifycode") String verifycode, boolean isDebug, String logback) throws IOException {
		LOGGER.info("=============================PbcCreditController.getPDFCeditReport======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// 获取报告
		Result<ReportData> result = pbccrcService.getPDFCeditReport(username, cookies, verifycode, isDebug, logback);
		return gson.toJson(result);
	}
	
	/**
	 * 获取人行征信报告测试（PDF）
	 * @param username 用户名
	 * @param password 密码
	 * @param tradecode 手机授权码
	 * @param isDebug
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/test/getpdfcredit")
	@ResponseBody
	public String getPDFCrediTest(@RequestParam("username") String username,
			@RequestParam("password") String password, boolean isDebug, String logback) throws IOException {
		LOGGER.info("=============================PbcCreditController.getPDFCrediTest======================================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// 获取登录页
		Result<ResultData> result1 = pbccrcService.getLoginPage(isDebug, logback);
		if (result1==null) {
			return gson.toJson(result1);
		}
		String loginImageUrl = result1.getData().getCodeImageUrl();
		String loginImageCode = OCRConnectUtils.getVerifycode("/pbccrc/getVerifycode", loginImageUrl); //OCRUtils.getVerifycode("/pbccrc/getVerifycode", loginImageUrl);
		result1.getData().setCodeValue(loginImageCode);

		// 登录
		Result<LoginData> loginResult = pbccrcService.excuteLogin(gson.toJson(result1.getData().getCookies()), username, password, loginImageCode, isDebug,logback);
		if (StatusCodeDef.IMAGECODE_ERROR.equals(loginResult.getData().getStatusCode())) {
			loginResult.getData().setCodeImageUrl(loginImageUrl);
			loginResult.getData().setCodeValue(loginImageCode);
			return gson.toJson(loginResult);
		}
		if (!StatusCodeDef.SCCCESS.equals(loginResult.getData().getStatusCode())) {
			return gson.toJson(loginResult);
		}

		// 获取PDF征信报告的图片验证码
		Result<ResultData> pdfReportImageCodeResult = pbccrcService.getPDFReportImageCodePage(username, gson.toJson(loginResult.getData().getCookies()), isDebug,logback);
		if (StatusCodeDef.DATA_CACHED.equals(pdfReportImageCodeResult.getData().getStatusCode())) {
			return gson.toJson(pdfReportImageCodeResult);
		}
		if (!StatusCodeDef.SCCCESS.equals(pdfReportImageCodeResult.getData().getStatusCode())) {
			return gson.toJson(pdfReportImageCodeResult);
		}
		
		//OCR图片验证码识别
		String pdfReportImageCode = OCRConnectUtils.getVerifycode("/pbccrc/getVerifycode", pdfReportImageCodeResult.getData().getCodeImageUrl()); //OCRUtils.getVerifycode("/pbccrc/getVerifycode", pdfReportImageCodeResult.getData().getCodeImageUrl());
		
		// 获取PDF征信报告
		Result<ReportData> pdfReportResult = pbccrcService.getPDFCeditReport(username, gson.toJson(loginResult.getData().getCookies()), pdfReportImageCode, isDebug,logback);
		if (StatusCodeDef.IMAGECODE_ERROR.equals(pdfReportResult.getData().getStatusCode())) {
			pdfReportResult.getData().setCodeImageUrl(pdfReportImageCodeResult.getData().getCodeImageUrl());
			pdfReportResult.getData().setCodeValue(pdfReportImageCode);
		}
		return gson.toJson(pdfReportResult);
	}
}

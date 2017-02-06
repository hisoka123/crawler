package com.crawlermanage.service.pbccrc;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

import com.crawler.pbccrc.domain.json.*;
import com.module.dao.entity.pbccrc.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.htmlparser.EncryptAndDecryptUtil;
import com.crawler.pbccrc.htmlparser.PbcCreditFeedParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.PbccrcParam;
import com.crawler.storm.def.PbccrcResultData;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.redis.service.PbccrcPDFCreditReportService;

@Component
public class PbccrcService {
	@Autowired
	private CrawlerEngine crawlerEngine;
	@Autowired
	private PbcCreditFeedParser pbcCreditFeedParser;
	@Autowired
	private PbccrcPDFCreditReportService pbccrcPDFCreditReportService;

	@Autowired
	private PbccrcDBService pbccrcDBService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PbccrcService.class);
	
	
	public Result<ResultData> getLoginPage(boolean isDebug, String logback){
		Gson gson = new GsonBuilder().create();
		Result<ResultData> result = new Result<ResultData>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.PBCCRC_TOLOGINPAGE);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		
		String param = fcm.toJson();
		LOGGER.info("PbccrcService.getLoginPage param:{}", param);
		result = crawlerEngine.execute(param, result);
		
		if (!StringUtils.isEmpty(result.getHtml())) {
			Type prdType = new TypeToken<PbccrcResultData>(){}.getType();
			PbccrcResultData prd = gson.fromJson(result.getHtml(), prdType);
			LOGGER.info("PbccrcService.getLoginPage storm result data:{}", prd.getHtml());
			
			if (StatusCodeDef.IMAGECODE_ERROR.equals(prd.getStatusCode())) {
				result = crawlerEngine.execute(param, result);
				prd = gson.fromJson(result.getHtml(), prdType);
				LOGGER.info("PbccrcService.getLoginPage storm result data:{}", prd.getHtml());
			}
			
			if (StatusCodeDef.IMAGECODE_ERROR.equals(prd.getStatusCode())) {
				ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setIp(prd.getIp());
				errorMsg.setHostName(prd.getHostName());
				errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
				errorMsg.setErrorMsg(prd.getMessage());
				errorMsg.setErrorName("验证码错误");
				result.setError(errorMsg);
				result.debugMode(isDebug);
				return result;
			}
			result.setData(new ResultData(prd.getStatusCode(), prd.getMessage(), prd.getCookies(), prd.getImageUrl()));
		}
		result.debugMode(isDebug);
		return result;
	}
	
	
	/**
	 * 
	 * @param cookies 登录页的cookies
	 * @param username 用户名
	 * @param password 密码
	 * @param verifycode 登录图片验证码
	 * @param isDebug
	 * @return Result<LoginData>
	 */
	public Result<LoginData> excuteLogin(String cookies, String username, String password, 
			String verifycode, boolean isDebug, String logback) {
		Result<LoginData> result = new Result<LoginData>();
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<HashSet<Cookie>>(){}.getType();
		Set<Cookie> cookieSet = gson.fromJson(cookies, type);
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.PBCCRC_LOGIN);
		PbccrcParam pbccrcParam = new PbccrcParam(username, password, verifycode, null, cookieSet);
		fcm.setPbccrcParam(pbccrcParam);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		
		String param = fcm.toJson();
		LOGGER.info("PbccrcService.excuteLogin param:{}", param);
		result = crawlerEngine.execute(param, result);
		LOGGER.info("------excuteLogin---result--"+result);
		if (!StringUtils.isEmpty(result.getHtml())) {
			Type prdType = new TypeToken<PbccrcResultData>(){}.getType();
			PbccrcResultData prd = gson.fromJson(result.getHtml(), prdType);
			LOGGER.info("PbccrcService.excuteLogin storm result data:{}", prd.getHtml());
			
			/*Set<Cookie> cookies2 = prd.getCookies();
			Cookie scookie = null;
			if(cookies2!=null) {
				for (Cookie cookie : cookies2) {
					if ("Secure".equals(cookie.getName())) {
						scookie = cookie;
					}
				}
			}
			cookies2.remove(scookie);
			String nameAndPass = null;
			try {
				nameAndPass = username+EncryptAndDecryptUtil.SEARATOR+EncryptAndDecryptUtil.encrypt(password, EncryptAndDecryptUtil.KEY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cookies2.add(new Cookie("ipcrs.pbccrc.org.cn", "Secure", nameAndPass, "/", null, true, true));*/
			
			result.setData(new LoginData(prd.getStatusCode(), prd.getMessage(), prd.getCookies()));
		}
		result.debugMode(isDebug);
		return result;
	}
	
	
	/**
	 * 获取人行征信报告（JSON）
	 * @param cookies 登录成功的cookies
	 * @param tradeCode 手机授权码
	 * @param isDebug 
	 * @return Result<ReportData>
	 * @throws IOException
	 */
	public Result<ReportData> getCeditReport(String cookies,String tradeCode,boolean isDebug, String logback) throws IOException{
		Result<ReportData> result = new Result<ReportData>();
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<HashSet<Cookie>>(){}.getType();
		Set<Cookie> cookieSet = gson.fromJson(cookies, type);
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.PBCCRC_GETREPORT);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		
		PbccrcParam pbccrcParam = new PbccrcParam(null, null, null, tradeCode, cookieSet);
		fcm.setPbccrcParam(pbccrcParam);
		String param = fcm.toJson();
		LOGGER.info("PbccrcService.getCeditReport param:{}", param);
		result = crawlerEngine.execute(param, result);
		
		if (!StringUtils.isEmpty(result.getHtml())) {
			Type prdType = new TypeToken<PbccrcResultData>(){}.getType();
			PbccrcResultData prd = gson.fromJson(result.getHtml(), prdType); //username
			LOGGER.info("PbccrcService.getCeditReport storm result data:{}", prd.getHtml());
			PbcCreditReportFeed pbcCreditReportFeed = pbcCreditFeedParser.getPbcCreditFeed(prd.getHtml());
			ReportData reportData = new ReportData(prd.getStatusCode(), prd.getMessage(), pbcCreditReportFeed, null);
			reportData.setLoggedCookiesJson(cookies); //username
			result.setData(reportData);
		}
		result.debugMode(isDebug);
		return result;
	}
	
	
	
	/**
	 * @param username 征信用户名
	 * @param cookies 登录成功的cookies
	 * @param isDebug
	 * @return Result<ResultData>
	 */
	public Result<ResultData> getPDFReportImageCodePage(String username, String cookies, boolean isDebug, String logback) {
		Result<ResultData> result = new Result<ResultData>();
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<HashSet<Cookie>>(){}.getType();
		Set<Cookie> cookieSet = gson.fromJson(cookies, type);
		
		String reportFileURL = pbccrcPDFCreditReportService.getPDFReportURLByUserName(username);
		if (reportFileURL!=null) {
			ResultData resultData = new ResultData(StatusCodeDef.DATA_CACHED, "redis服务器已有资源[reportFileURL]", cookieSet, null);
			resultData.setReportFileURL(reportFileURL);
			result.setData(resultData);
			result.debugMode(isDebug);
			return result;
		}
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.PBCCRC_GETPDFREPORT_IMAGECODE);
		PbccrcParam pbccrcParam = new PbccrcParam(null, null, null, null, cookieSet);
		fcm.setPbccrcParam(pbccrcParam);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		
		String param = fcm.toJson();
		LOGGER.info("PbccrcService.getPDFReportImageCodePage param:{}", param);
		result = crawlerEngine.execute(param, result);
		
		if (!StringUtils.isEmpty(result.getHtml())) {
			Type prdType = new TypeToken<PbccrcResultData>(){}.getType();
			PbccrcResultData prd = gson.fromJson(result.getHtml(), prdType);
			LOGGER.info("PbccrcService.getPDFReportImageCodePage storm result data----imageUrl:{}", prd.getImageUrl());
			result.setData(new ResultData(prd.getStatusCode(), prd.getMessage(), prd.getCookies(), prd.getImageUrl()));
		}
		result.debugMode(isDebug);
		return result;
	}
	
	/**
	 * @param username 征信用户名
	 * @param cookies 登录成功的cookies
	 * @param imageCode PDF征信报告的图片验证码
	 * @param isDebug
	 * @return Result<ReportData>
	 * @throws IOException
	 * 【注】：由于要对每个用户的pdf文档做24h缓存 以解决人行征信系统24h内只能扒取10次的限制，故还需要传递用户名作为redis缓存数据的key
	 */
	public Result<ReportData> getPDFCeditReport(String username, String cookies, 
			String imageCode, boolean isDebug, String logback) throws IOException {
		Result<ReportData> result = new Result<ReportData>();
		
		String reportFileURL = pbccrcPDFCreditReportService.getPDFReportURLByUserName(username);
		if (reportFileURL!=null) { //redis缓存中有数据则从缓存中获取数据
			ReportData reportDataInCache = new ReportData(StatusCodeDef.SCCCESS, "成功获取PDF征信报告", null, reportFileURL);
			reportDataInCache.setLoggedCookiesJson(cookies);
			result.setData(reportDataInCache);
			result.setHtml("redis服务器资源[reportFileURL]");
			result.debugMode(isDebug);
			return result;
		}
		
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<HashSet<Cookie>>(){}.getType();
		Set<Cookie> cookieSet = gson.fromJson(cookies, type);
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.PBCCRC_GETPDFREPORT);
		PbccrcParam pbccrcParam = new PbccrcParam(null, null, imageCode, null, cookieSet);
		fcm.setPbccrcParam(pbccrcParam);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		
		String param = fcm.toJson();
		LOGGER.info("PbccrcService.getPDFCeditReport param:{}", param);
		result = crawlerEngine.execute(param, result);
		
		if (!StringUtils.isEmpty(result.getHtml())) {
			Type prdType = new TypeToken<PbccrcResultData>(){}.getType();
			PbccrcResultData prd = gson.fromJson(result.getHtml(), prdType);
			
			if (prd.getReportFileURL()!=null) {
				pbccrcPDFCreditReportService.cachePDFReportURL(username, prd.getReportFileURL()); //缓存数据到redis服务器24h
			}
			
			LOGGER.info("PbccrcService.getPDFCeditReport storm result data----reportFileURL:{}", prd.getReportFileURL());
			ReportData reportData = new ReportData(prd.getStatusCode(), prd.getMessage(), null, prd.getReportFileURL());
			reportData.setLoggedCookiesJson(cookies);
			result.setData(reportData);
		}
		result.debugMode(isDebug);
		return result;
	}

    private void transferAndSaveQueryRecords(PlainPbccrcJson plainPbccrcJson , List<QueryRecord> queryRecords) {
        if(null != queryRecords && queryRecords.size() > 0){
            List<PbcQueryRecord> pbcQueryRecords = new ArrayList<>();
            for(QueryRecord queryRecord : queryRecords){
                PbcQueryRecord pbcQueryRecord = new PbcQueryRecord();
                pbcQueryRecord.setNum(queryRecord.getNum());
                pbcQueryRecord.setOperator(queryRecord.getOperator());
                pbcQueryRecord.setQueryCause(queryRecord.getQueryCause());
                pbcQueryRecord.setQueryDate(queryRecord.getQueryDate());
                pbcQueryRecord.setQueryType(queryRecord.getQueryType());

                pbcQueryRecord.setPlainPbccrcJson(plainPbccrcJson);
                pbcQueryRecords.add(pbcQueryRecord);
            }
            pbccrcDBService.saveAllQueryRecords(pbcQueryRecords);
        }
    }

    private void transferAndSaveCreditCardInfo(PlainPbccrcJson plainPbccrcJson, CreditRecord creditRecord) {
		if(null != creditRecord){
			//信贷概要
			CreditSummary summary = creditRecord.getCreditSummary();
			if(null != summary){
				transferAndSaveCreditCardSummary(plainPbccrcJson, summary);
			}
			//信贷明细
			List<Map<String, Object>> creditCardDetails = creditRecord.getParsedCreditCardDetails();
			if(null != creditCardDetails){
				transferAndSaveCreditCardDetails(plainPbccrcJson,creditCardDetails);
			}

			//购房贷款明细
			//其他贷款明细
			List<Map<String, Object>> loanDetails = creditRecord.getParsedLoanDetails();
			if(null != loanDetails){
				transferAndSaveLoanDetails(plainPbccrcJson,loanDetails);
			}
			//为他人担保信息明细
            List<Map<String, Object>> guaranteeInfoDetails = creditRecord.getParsedGuaranteeInfoDetails();
			if(null != guaranteeInfoDetails){
				transferAndSaveParsedGuaranteeInfoDetails(plainPbccrcJson,guaranteeInfoDetails);
			}
		}
	}

	private void transferAndSaveParsedGuaranteeInfoDetails(PlainPbccrcJson plainPbccrcJson,List<Map<String, Object>> guaranteeInfoDetailsMap) {

		List<GuarantyForOtherDetail> guarantyForOtherDetails = new ArrayList<>();
		for(Map<String,Object> guaranteeMap : guaranteeInfoDetailsMap){
			GuarantyForOtherDetail guarantyForOtherDetail = new GuarantyForOtherDetail();
			for(Map.Entry<String,Object> entry : guaranteeMap.entrySet()){
				String key = entry.getKey();
				if("guaranteedPerson".equals(key)){
					guarantyForOtherDetail.setGuaranteedPerson((String) entry.getValue());
					continue;
				}

				if("guaranteedPersonIdNum".equals(key)){
					guarantyForOtherDetail.setGuaranteedPersonIdNum((String) entry.getValue());
					continue;
				}

				if("otherGuaranteeAmount".equals(key)){
					guarantyForOtherDetail.setOtherGuaranteeAmount((String) entry.getValue());
					continue;
				}

				if("realPrincipal".equals(key)){
					guarantyForOtherDetail.setRealPrincipal((String) entry.getValue());
					continue;
				}

				if("actualDay".equals(key)){
					guarantyForOtherDetail.setActualDay((String) entry.getValue());
					continue;
				}
			}
			guarantyForOtherDetail.setPlainPbccrcJson(plainPbccrcJson);
			guarantyForOtherDetails.add(guarantyForOtherDetail);
		}

		pbccrcDBService.saveAllGuaranty(guarantyForOtherDetails);
	}

	private void transferAndSaveLoanDetails(PlainPbccrcJson plainPbccrcJson , List<Map<String, Object>> otherLoanDetailsMap) {
		List<PbcLoanDetail> loanDetails = new ArrayList<>();
		for(Map<String,Object> otherLoanMap : otherLoanDetailsMap){
			PbcLoanDetail loanDetail = new PbcLoanDetail();
			for(Map.Entry<String,Object> entry : otherLoanMap.entrySet()){
				String key = entry.getKey();
				if("currency".equals(key)){
					loanDetail.setCurrency((String) entry.getValue());
					continue;
				}

				if("accountStatus".equals(key)){
					loanDetail.setAccountStatus((String) entry.getValue());
					continue;
				}

				if("overdueNo".equals(key)){
					loanDetail.setOverdueNo((String) entry.getValue());
					continue;
				}

				if("isOverdue".equals(key)){
					loanDetail.setIsOverdue((String) entry.getValue());
					continue;
				}

				if("issueDay".equals(key)){
					loanDetail.setIssueDay((String) entry.getValue());
					continue;
				}

				if("abortDay".equals(key)){
					loanDetail.setAbortDay((String) entry.getValue());
					continue;
				}

				if("actualDay".equals(key)){
					loanDetail.setActualDay((String) entry.getValue());
					continue;
				}

				if("conteactAmount".equals(key)){
					loanDetail.setConteactAmount((String) entry.getValue());
					continue;
				}

				if("loanBalance".equals(key)){
					loanDetail.setLoanBalance((String) entry.getValue());
					continue;
				}

				if("overdueAmount".equals(key)){
					loanDetail.setOverdueAmount((String) entry.getValue());
					continue;
				}

				if("overdueForNo".equals(key)){
					loanDetail.setOverdueForNo((String) entry.getValue());
					continue;
				}

				if("settleDay".equals(key)){
					loanDetail.setSettleDay((String) entry.getValue());
					continue;
				}
			}
			loanDetail.setPlainPbccrcJson(plainPbccrcJson);
			loanDetails.add(loanDetail);
		}

		pbccrcDBService.saveAllLoanDetails(loanDetails);
	}


	private void transferAndSaveCreditCardDetails(PlainPbccrcJson plainPbccrcJson,List<Map<String, Object>> creditCardDetailsMap) {
		List<PbcCreditCardDetail> creditCardDetails = new ArrayList<>();
		for(Map<String,Object> creditCardMap : creditCardDetailsMap){
			PbcCreditCardDetail creditCardDetail = new PbcCreditCardDetail();
			for(Map.Entry<String,Object> entry : creditCardMap.entrySet()){
				String key = entry.getKey();
				if("isQuasiCreditCard".equals(key)){
					creditCardDetail.setIsQuasiCreditCard((String)entry.getValue());
					continue;
				}

				if("accountStatus".equals(key)){
					creditCardDetail.setAccountStatus((String) entry.getValue());
					continue;
				}

				if("currency".equals(key)){
					creditCardDetail.setCurrency((String) entry.getValue());
					continue;
				}

				if("overdueNo".equals(key)){
					creditCardDetail.setOverdueNo((String) entry.getValue());
					continue;
				}

				if("isOverdue".equals(key)){
					creditCardDetail.setIsOverdue((String) entry.getValue());
					continue;
				}

				if("issueDay".equals(key)){
					creditCardDetail.setIssueDay((String) entry.getValue());
					continue;
				}

				if("abortDay".equals(key)){
					creditCardDetail.setAbortDay((String) entry.getValue());
					continue;
				}

				if("limit".equals(key)){
					creditCardDetail.setLimit((String) entry.getValue());
					continue;
				}

				if("usedLimit".equals(key)){
					creditCardDetail.setUsedLimit((String) entry.getValue());
					continue;
				}

				if("overdueAmount".equals(key)){
					creditCardDetail.setOverdueAmount((String) entry.getValue());
					continue;
				}

				if("overdueForNo".equals(key)){
					creditCardDetail.setOverdueForNo((String) entry.getValue());
					continue;
				}

				if("cancellationDay".equals(key)){
					creditCardDetail.setCancellationDay((String) entry.getValue());
					continue;
				}
			}
			creditCardDetail.setPlainPbccrcJson(plainPbccrcJson);
			creditCardDetails.add(creditCardDetail);
		}

		pbccrcDBService.saveAllPbcCreditCardDetail(creditCardDetails);
	}

	private void transferAndSaveCreditCardSummary(PlainPbccrcJson plainPbccrcJson, CreditSummary summary) {
		if(null != summary){
			Map<String,String> creditCards = summary.getCreditCards();
			Map<String,String> housingLoans = summary.getHousingLoans();
			Map<String,String> otherLoans = summary.getOtherLoans();

			List<CreditRecordProfile> profiles = new ArrayList<>();
			try{
				//信用卡
				CreditRecordProfile creditCardProfile = new CreditRecordProfile();
				creditCardProfile.setType("信用卡");
				creditCardProfile.setAccountNum(trimToInteger(creditCards.get("accountNum")));
				creditCardProfile.setActiveNum(trimToInteger(creditCards.get("activeNum")));
				creditCardProfile.setOverdueNum(trimToInteger(creditCards.get("overdueNum")));
				creditCardProfile.setOverdue90Num(trimToInteger(creditCards.get("overdue90Num")));
				creditCardProfile.setGuaranteeNum(trimToInteger(creditCards.get("guaranteeNum")));

				creditCardProfile.setPlainPbccrcJson(plainPbccrcJson);
				profiles.add(creditCardProfile);
				//房贷
				CreditRecordProfile housingLoanProfile = new CreditRecordProfile();
				housingLoanProfile.setType("房贷");
				housingLoanProfile.setAccountNum(trimToInteger(housingLoans.get("accountNum")));
				housingLoanProfile.setActiveNum(trimToInteger(housingLoans.get("activeNum")));
				housingLoanProfile.setOverdueNum(trimToInteger(housingLoans.get("overdueNum")));
				housingLoanProfile.setOverdue90Num(trimToInteger(housingLoans.get("overdue90Num")));
				housingLoanProfile.setGuaranteeNum(trimToInteger(housingLoans.get("guaranteeNum")));

				housingLoanProfile.setPlainPbccrcJson(plainPbccrcJson);
				profiles.add(housingLoanProfile);

				//其他贷款
				CreditRecordProfile otherLoanProfile = new CreditRecordProfile();
				otherLoanProfile.setType("其他贷款");
				otherLoanProfile.setAccountNum(trimToInteger(otherLoans.get("accountNum")));
				otherLoanProfile.setActiveNum(trimToInteger(otherLoans.get("activeNum")));
				otherLoanProfile.setOverdueNum(trimToInteger(otherLoans.get("overdueNum")));
				otherLoanProfile.setOverdue90Num(trimToInteger(otherLoans.get("overdue90Num")));
				otherLoanProfile.setGuaranteeNum(trimToInteger(otherLoans.get("guaranteeNum")));

				otherLoanProfile.setPlainPbccrcJson(plainPbccrcJson);
				profiles.add(otherLoanProfile);

				pbccrcDBService.saveAllCreditCardProfile(profiles);
			}catch (NumberFormatException e){
				LOGGER.error("信贷概要---数据类型转换异常",e);
			}
		}
	}

	private void transferAndSaveReportBase(PlainPbccrcJson plainPbccrcJson, ReportBase reportBase) {
		if(null != reportBase){
			PbcReportBase pbcReportBase = new PbcReportBase();
			pbcReportBase.setReportId(reportBase.getReportId());
			pbcReportBase.setQueryTime(reportBase.getQueryTime());
			pbcReportBase.setReportTime(reportBase.getReportTime());
			pbcReportBase.setRealname(reportBase.getRealname());
			pbcReportBase.setCertificateType(reportBase.getCertificateType());
			pbcReportBase.setCertificateNum(reportBase.getCertificateNum());
			pbcReportBase.setMarriageStatus(reportBase.getMarriageStatus());

			pbcReportBase.setPlainPbccrcJson(plainPbccrcJson);
			pbccrcDBService.save(pbcReportBase);
		}
	}

	private Integer trimToInteger(Object obj)
			throws  NumberFormatException{
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

//	public void saveReportData()

	/*public void saveReport(String username , String passwd, String tradecode, Result<ReportData> reportResult, PbcCreditReportFeed pbcCreditReportFeed) {

		LOGGER.info("saveReport----------------username--"+username+"---------tradecode--"+tradecode);
		PbccrcAccount pbccrcAccount = null;

		List<PbccrcAccount> pbccrcAccounts = pbccrcDBService.findByUsername(username);
		if (null == pbccrcAccounts || pbccrcAccounts.size() == 0){
			pbccrcAccount = new PbccrcAccount();
			pbccrcAccount.setUsername(username);
			pbccrcAccount.setPassword(passwd);
			pbccrcAccount.setTradecode(tradecode);
		}else{
			pbccrcAccount = pbccrcAccounts.get(0);
			pbccrcAccount.setPassword(passwd);
			pbccrcAccount.setTradecode(tradecode);
		}
		pbccrcDBService.save(pbccrcAccount);

		//保存爬取的Json字符串
		PlainPbccrcJson plainPbccrcJson = new PlainPbccrcJson();
		plainPbccrcJson.setPbccrcAccount(pbccrcAccount);
		plainPbccrcJson.setPlainPbccrcJson(new Gson().toJson(reportResult));
		plainPbccrcJson.setCreateTime(new Date());
		pbccrcDBService.save(plainPbccrcJson);

		saveParsedReportJson(plainPbccrcJson,pbcCreditReportFeed);
	}*/

	public void saveReport(String tsf75e5b, String tradecode, Result<ReportData> reportResult, PbcCreditReportFeed pbcCreditReportFeed) {

		LOGGER.info("saveReport----------------tsf75e5b--"+ tsf75e5b +"  ---------tradecode--"+tradecode);
		//获取json，根据json获取用户
		PlainPbccrcJson plainPbccrcJson = pbccrcDBService.findByTsf75e5b(tsf75e5b);
		if(null != plainPbccrcJson){
			plainPbccrcJson.setPlainPbccrcJson(new Gson().toJson(reportResult));
			PbccrcAccount pbccrcAccount = plainPbccrcJson.getPbccrcAccount();
			if(null != pbccrcAccount){
				pbccrcAccount.setTradecode(tradecode);
				pbccrcDBService.save(plainPbccrcJson);
				pbccrcDBService.save(pbccrcAccount);
				saveParsedReportJson(plainPbccrcJson, pbcCreditReportFeed);
			}
		}
	}

	private void saveParsedReportJson(PlainPbccrcJson plainPbccrcJson, PbcCreditReportFeed pbcCreditReportFeed) {
		ReportBase reportBase = pbcCreditReportFeed.getReportBase();
		CreditRecord creditRecord = pbcCreditReportFeed.getCreditRecord();
		List<QueryRecord> queryRecords = pbcCreditReportFeed.getQueryRecords();

		if(null != reportBase){
			transferAndSaveReportBase(plainPbccrcJson, reportBase);
		}

		if(null  != creditRecord){
			transferAndSaveCreditCardInfo(plainPbccrcJson, creditRecord);
		}

		if(null != queryRecords){
			transferAndSaveQueryRecords(plainPbccrcJson, queryRecords);
		}
	}

	public void saveSessionInfo(PbccrcAccount pbccrcAccount, PlainPbccrcJson plainPbccrcJson) {
		pbccrcDBService.save(pbccrcAccount);
		pbccrcDBService.save(plainPbccrcJson);
	}

	public PbccrcAccount findByUsername(String username) {
		List<PbccrcAccount> pbccrcAccounts = pbccrcDBService.findByUsername(username);
		if(null != pbccrcAccounts && pbccrcAccounts.size() > 0){
			return pbccrcAccounts.get(0);
		}
		return null;
	}
}


package com.crawlermanage.controller.api.pbccrc;

import java.io.IOException;

import com.crawlermanage.service.pbccrc.PbccrcPersistTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.Result;
import com.crawler.pbccrc.domain.json.PbcCreditReportFeed;
import com.crawler.pbccrc.domain.json.ReportData;
import com.crawler.pbccrc.htmlparser.PbcCreditFeedParser;
import com.crawlermanage.service.pbccrc.PbccrcService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/pbccrc/chp3")
public class Chp3PbcCreditController {
	@Autowired
	private PbccrcService pbccrcService;
	@Autowired
	private PbcCreditFeedParser pbcCreditFeedParser;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PbcCreditController.class);
	
	@RequestMapping(value = "/logged/getcredit")
	@ResponseBody
	public String getCredit(@RequestParam("cookies") String cookies,
			@RequestParam("tradecode") String tradecode, boolean isDebug, String logback) throws IOException {
		LOGGER.info("=======================Chp3PbcCreditController.getCredit start!==============================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		LOGGER.info("cookies---------"+cookies);
		LOGGER.info("tradecode---------"+tradecode);
		// 获取报告
		Result<ReportData> reportResult = pbccrcService.getCeditReport(cookies, tradecode, isDebug, logback);
		
		if (reportResult.getData()==null || !"0".equals(reportResult.getData().getStatusCode())) {
			return gson.toJson(reportResult);
		}
		
		//解析
		PbcCreditReportFeed reportFeed = reportResult.getData().getReport();
		//解析信用卡
		reportFeed = pbcCreditFeedParser.parseCreditCardDetails(reportFeed);
		//解析贷款明细（住房贷款 和 其他贷款）
		reportFeed = pbcCreditFeedParser.parseLoanDetails(reportFeed);
		//解析担保信息明细
		reportFeed = pbcCreditFeedParser.parseGuaranteeInfoDetails(reportFeed);

		new Thread(new PbccrcPersistTask(pbccrcService,reportResult.getData().getLoggedCookiesJson(),tradecode,reportResult,reportFeed)).start();

		return gson.toJson(reportResult);
	}
}




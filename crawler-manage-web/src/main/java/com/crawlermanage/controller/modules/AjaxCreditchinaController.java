package com.crawlermanage.controller.modules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.creditchina.domain.jsontwo.CompanyRecord;
import com.crawlermanage.dao.result.CreditResult;
import com.crawlermanage.service.creditchina.CreditchinaDBService;
import com.crawlermanage.service.creditchina.CreditchinaSearchService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.creditchinatwo.BDResultJson;
import com.module.dao.entity.creditchinatwo.CreditCompany;

@Controller
@RequestMapping("/modules/creditchina")
public class AjaxCreditchinaController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AjaxCreditchinaController.class);

	@Autowired
	private CreditchinaSearchService creditchinaSearchService;
	@Autowired
	private CreditchinaDBService creditchinaDBService;

	// 核查企业
	@RequestMapping(value = "/checkchinaCompany", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody CreditResult checkchinaCompany(
			@RequestParam("name") String name,
			@RequestParam("objectType") String objectType) {
		LOGGER.info("=====AjaxCreditchinaController.checkchinaCompany is start!=====");
		CreditResult creditResult = new CreditResult();
		CreditCompany creditCompany = creditchinaDBService.companyIsExist(name,
				objectType);
		Gson gson = new Gson();
		if (creditCompany != null) {
			creditResult.setExistCode(1);
			creditResult.setCompany_id(creditCompany.getId());
			BDResultJson bDResultJson = creditchinaDBService
					.getBDResultJson(creditCompany.getId());
			if (null != bDResultJson && bDResultJson.getId() >= 0) {
				creditResult.setState(1);
				List<CompanyRecord> creditchinaResult = gson.fromJson(
						bDResultJson.getResult(),
						new TypeToken<List<CompanyRecord>>() {
						}.getType());
				creditResult.setCompanyRecordResult(creditchinaResult);
			} else {
				if (creditCompany.getState() == 7) {
					creditResult.setState(creditCompany.getState());
				} else {
					creditResult.setState(0);
				}
			}
		} else {
			creditResult.setExistCode(0);
			creditResult.setState(0);
		}
		LOGGER.info("=====AjaxCreditchinaController.checkchinaCompany is end!=====");
		return creditResult;
	}

}

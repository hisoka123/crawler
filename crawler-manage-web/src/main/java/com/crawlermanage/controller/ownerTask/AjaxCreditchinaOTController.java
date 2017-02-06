package com.crawlermanage.controller.ownerTask;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlermanage.dao.result.OwnerTaskResult;
import com.crawlermanage.service.creditchina.CreditchinaDBService;
import com.crawlermanage.service.ownerTask.CreditchinaOTService;
import com.crawlermanage.service.ownerTask.OwnerTaskAllService;
import com.module.dao.entity.creditchinatwo.CreditCompany;
import com.module.dao.entity.ownerTask.OwnerTaskAll;
import com.module.dao.entity.ownerTask.OwnerTaskCreditchina;

@Controller
@RequestMapping("/ownerTask")
public class AjaxCreditchinaOTController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AjaxCreditchinaOTController.class);

	@Autowired
	CreditchinaOTService creditchinaOTService;
	@Autowired
	OwnerTaskAllService ownerTaskAllService;
	@Autowired
	CreditchinaDBService creditchinaDBService;

	// 加入新任务---信用中国
	@RequestMapping(value = "/creditchinaJoinTask", method = RequestMethod.POST)
	public @ResponseBody OwnerTaskResult creditchinaJoinTask(
			@RequestParam("name") String name,
			@RequestParam("objectType") String objectType,
			@RequestParam("date") Date date,
			@RequestParam("loginName") String loginName,
			@RequestParam("existCode") int existCode,
			@RequestParam("companyID") long companyID) {
		LOGGER.info("信用中国新任务：name=" + name + " ,objectType=" + objectType
				+ " ,date=" + date + " ,loginName=" + loginName + ",existCode="
				+ existCode + " ,companyID=" + companyID);
		long company_id = 0;
		for (OwnerTaskCreditchina creditchinaExist : creditchinaOTService
				.getCreditchinaTask(loginName, objectType)) {
			if (creditchinaExist.getKeyWord().equals(name)) {
				return returnCreditchinaResult(name, 2);
			}
		}
		if (existCode == 0) { // 定时任务表中无该企业,写入credit_company表中
			CreditCompany creditCompany = new CreditCompany();
			creditCompany.setName(name);
			creditCompany.setObjectType(objectType);
			creditCompany.setState(0);
			creditCompany.setPriority(1);
			creditCompany.setNum(0);
			creditCompany.setTotalNum(0);
			CreditCompany reCompany = creditchinaDBService
					.saveCreditCompany(creditCompany);
			if (reCompany != null) {
				company_id = reCompany.getId();
			} else {
				return returnCreditchinaResult(name, 0);
			}
		} else {
			CreditCompany company2 = creditchinaDBService.findById(companyID);
			company2.setPriority(1);
			company2.setNum(0);
			if (creditchinaDBService.saveCreditCompany(company2) == null) {
				return returnCreditchinaResult(name, 0);
			}
			company_id = companyID;
		}
		// 写入ownerTask_creditchina表中
		OwnerTaskCreditchina creditchina = new OwnerTaskCreditchina();
		creditchina.setLoginName(loginName);
		creditchina.setKeyWord(name);
		creditchina.setSearchType(objectType);
		creditchina.setState(0);
		creditchina.setTimeTaskId(company_id);
		creditchina.setCreateTaskDate(date);
		OwnerTaskCreditchina reCreditchina = creditchinaOTService
				.creditchinaJoinTask(creditchina);
		if (reCreditchina == null) {
			return returnCreditchinaResult(name, 0);
		}
		// 写入ownerTask_all表中
		OwnerTaskAll reTaskAll = ownerTaskAllService.save(loginName, "信用中国");
		if (reTaskAll == null) {
			return returnCreditchinaResult(name, 0);
		}
		return returnCreditchinaResult(name, 1);
	}

	// 创建任务,返回结果
	private OwnerTaskResult returnCreditchinaResult(String name,
			long ownerTaskCode) {
		OwnerTaskResult result = new OwnerTaskResult();
		if (ownerTaskCode == 0) {
			result.setOwnerTaskCode(0);
			result.setMessage("信用中国创建新任务：" + name + " 失败");
		} else if (ownerTaskCode == 1) {
			result.setOwnerTaskCode(1);
			result.setMessage("信用中国创建新任务：" + name + " 成功");
		} else if (ownerTaskCode == 2) {
			result.setOwnerTaskCode(2);
			result.setMessage("信用中国新任务：" + name + " 已存在");
		}
		return result;
	}

}

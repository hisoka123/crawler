package com.crawlermanage.service.transfer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawler.customs.domain.json.AdmPuInformation;
import com.crawler.customs.domain.json.CreditRate;
import com.crawler.customs.domain.json.Customs;
import com.module.dao.entity.customs.CreditLevel;
import com.module.dao.entity.customs.PunishInformation;
import com.module.dao.entity.customs.RegInformation;

/**
 * 海关网对象转化工具
 */
public class CustomsPoVoTransfer {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomsPoVoTransfer.class);

	public static Customs poToVo(RegInformation regInformation) {
		LOGGER.info("RegInformation transfer to Customs is begin!");
		Customs customs = null;
		if (null != regInformation) {
			customs = new Customs();
			customs.setcRCode(regInformation.getCustomsRegNo());// 海关注册编码
			customs.setSocialCreditCode(regInformation.getSocialCreditNo());// 社会信用代码
			customs.setRecordDate(regInformation.getRegDate());// 注册日期
			customs.setBarCode(regInformation.getBarCode());// 组织机构代码
			customs.setbCName(regInformation.getChineseCompanyName());// 企业中文名称
			customs.setCustomsName(regInformation.getRegCustoms());// 注册海关
			customs.setRegAddress(regInformation.getRegAddress());// 工商注册地址
			customs.setAdmDivision(regInformation.getAdmDivision());// 行政区划
			customs.setEcoReg(regInformation.getEnconomicDivision());// 经济区划
			customs.setEconomicCategory(regInformation.getEnconomicType());// 经济类型
			customs.setBusType(regInformation.getBusinessType());// 经营类别
			customs.setBusinessLine(regInformation.getBusinessLine());// 行业种类
			customs.setCustomsValidity(regInformation.getCustomsValidity());// 报关有效期
			customs.setCustomsMarks(regInformation.getCustomsCancelMarks());// 海关注销标志
			customs.setAnnualReport(regInformation.getAnnualReport());// 年报情况
			Set<CreditLevel> creditLevels = regInformation.getCreditLevels();
			if (null != creditLevels && !creditLevels.isEmpty()) {
				List<CreditRate> creditRateList = customs.getCreditRate();
				for (CreditLevel creditLevel : creditLevels) {
					CreditRate creditRate = new CreditRate();
					creditRate.setIdentifyTime(creditLevel.getIdentifyDate());// 认定时间
					creditRate.setLegalNumber(creditLevel.getLegalNo());// 法律文书编号
					creditRate.setQuatityRate(creditLevel.getCreditLevel());// 信用等级
					creditRateList.add(creditRate);
				}
				customs.setCreditRate(creditRateList);
			}
			Set<PunishInformation> punishInformations = regInformation
					.getPunishInformations();
			if (null != punishInformations && !punishInformations.isEmpty()) {
				List<AdmPuInformation> admPuInformationList = customs
						.getAdmPuInformation();
				for (PunishInformation punishInformation : punishInformations) {
					AdmPuInformation admPuInformation = new AdmPuInformation();
					admPuInformation.setParty(punishInformation.getParty());// 当事人
					admPuInformation.setCaseNature(punishInformation
							.getCaseQuality());// 案件性质
					admPuInformation.setPunishmentDate(punishInformation
							.getPunishDate());// 处罚日期
					admPuInformation.setPenaltyNumber(punishInformation
							.getPunishNo());// 行政处罚决定书编号
					admPuInformationList.add(admPuInformation);
				}
				customs.setAdmPuInformation(admPuInformationList);
			}
		}
		LOGGER.info("RegInformation transfer to Customs is end!");
		return customs;
	}

	public static RegInformation voToPo(Customs customs) {
		LOGGER.info("Customs transfer to RegInformation is begin!");
		RegInformation regInformation = null;
		if (customs != null) {
			regInformation = new RegInformation();
			regInformation.setCustomsRegNo(customs.getcRCode());// 海关注册编码
			regInformation.setSocialCreditNo(customs.getSocialCreditCode());// 社会信用代码
			regInformation.setRegDate(customs.getRecordDate());// 注册日期
			regInformation.setBarCode(customs.getBarCode());// 组织机构代码
			regInformation.setChineseCompanyName(customs.getbCName());// 企业中文名称
			regInformation.setRegCustoms(customs.getCustomsName());// 注册海关
			regInformation.setRegAddress(customs.getRegAddress());// 工商注册地址
			regInformation.setAdmDivision(customs.getAdmDivision());// 行政区划
			regInformation.setEnconomicDivision(customs.getEcoReg());// 经济区划
			regInformation.setEnconomicType(customs.getEconomicCategory());// 经济类型
			regInformation.setBusinessType(customs.getBusType());// 经营类别
			regInformation.setBusinessLine(customs.getBusinessLine());// 行业种类
			regInformation.setCustomsValidity(customs.getCustomsValidity());// 报关有效期
			regInformation.setCustomsCancelMarks(customs.getCustomsMarks());// 海关注销标志
			regInformation.setAnnualReport(customs.getAnnualReport());// 年报情况
			List<CreditRate> creditRateList = customs.getCreditRate();
			if (null != creditRateList && !creditRateList.isEmpty()
					&& creditRateList.size() > 0) {
				Set<CreditLevel> creditLevels = new HashSet<CreditLevel>();
				for (CreditRate creditRate : creditRateList) {
					CreditLevel creditLevel = new CreditLevel();
					creditLevel.setRegInformation(regInformation);
					creditLevel.setIdentifyDate(creditRate.getIdentifyTime());// 认定时间
					creditLevel.setLegalNo(creditRate.getLegalNumber());// 法律文书编号
					creditLevel.setCreditLevel(creditRate.getQuatityRate());// 信用等级
					// creditLevel.setRegInformation(regInformation);
					creditLevels.add(creditLevel);
				}
				regInformation.setCreditLevels(creditLevels);
			}
			List<AdmPuInformation> admPuInformationList = customs
					.getAdmPuInformation();
			if (null != admPuInformationList && !admPuInformationList.isEmpty()
					&& admPuInformationList.size() > 0) {
				Set<PunishInformation> punishInformations = new HashSet<PunishInformation>();
				for (AdmPuInformation admPuInformation : admPuInformationList) {
					PunishInformation punishInformation = new PunishInformation();
					punishInformation.setRegInformation(regInformation);
					punishInformation.setParty(admPuInformation.getParty());// 当事人
					punishInformation.setCaseQuality(admPuInformation
							.getCaseNature());// 案件性质
					punishInformation.setPunishDate(admPuInformation
							.getPunishmentDate());// 处罚日期
					punishInformation.setPunishNo(admPuInformation
							.getPenaltyNumber());// 行政处罚决定书编号
					// punishInformation.setRegInformation(regInformation);
					punishInformations.add(punishInformation);
				}
				regInformation.setPunishInformations(punishInformations);
			}
		}
		LOGGER.info("Customs transfer to RegInformation is end!");
		return regInformation;
	}

}
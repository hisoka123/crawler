package com.crawlermanage.service.transfer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.module.dao.entity.creditchinatwo.BadRecord;
import com.module.dao.entity.creditchinatwo.CompanyRecord;
import com.module.dao.entity.creditchinatwo.DishonestyRecord;

/**
 * 信用中国对象转化工具
 */
public class CreditchinaPoVoTransfer {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CreditchinaPoVoTransfer.class);

	public static com.crawler.creditchina.domain.jsontwo.CompanyRecord poToVo(
			CompanyRecord companyRecord) {
		LOGGER.info("CompanyRecord transfer to com.crawler.creditchina.domain.jsontwo.CompanyRecord is begin!");
		com.crawler.creditchina.domain.jsontwo.CompanyRecord companyRecord1 = null;
		if (null != companyRecord) {
			companyRecord1 = new com.crawler.creditchina.domain.jsontwo.CompanyRecord();
			companyRecord1.setName(companyRecord.getName());
			companyRecord1.setObjectType(companyRecord.getObjectType());
			companyRecord1.setBadCount(companyRecord.getBadCount());
			Set<BadRecord> badRecords = companyRecord.getBadRecords();
			if (null != badRecords && badRecords.size() > 0) {
				List<com.crawler.creditchina.domain.jsontwo.BadRecord> wds = new ArrayList<com.crawler.creditchina.domain.jsontwo.BadRecord>();
				for (BadRecord badRecord : badRecords) {
					com.crawler.creditchina.domain.jsontwo.BadRecord wd = new com.crawler.creditchina.domain.jsontwo.BadRecord();
					wd.setInforSource(badRecord.getInforSource());// 信息来源（现网站已无该字段）
					wd.setCaseName(badRecord.getCaseName());// 案件名称（现网站已无该字段）
					wd.setAccepted(badRecord.getAccepted());// 受理号（现网站已无该字段）
					wd.setBusinessType(badRecord.getBusinessType());// 业务类型（现网站已无该字段）
					wd.setCaseNature(badRecord.getCaseNature());// 案件性质（现网站已无该字段）
					wd.setQualitBasis(badRecord.getQualitBasis());// 定性依据（现网站已无该字段）
					wd.setPunishBasis(badRecord.getPunishBasis());// 处罚依据（现网站已无该字段）
					wd.setCaseDate(badRecord.getCaseDate());// 立案日期（现网站已无该字段）
					wd.setDeliveryDate(badRecord.getDeliveryDate());// 处罚决定书送达日期（现网站已无该字段）
					wd.setRegNumber(badRecord.getRegNumber());// 企业注册号
					wd.setCompanyName(badRecord.getCompanyName());// 企业名称
					wd.setLegalPerson(badRecord.getLegalPerson());// 法定代表人
					wd.setPunishNumber(badRecord.getPunishNumber());// 处罚决定书文号
					wd.setPunishContent(badRecord.getPunishContent());// 处罚决定内容
					wd.setRegCapital(badRecord.getRegCapital());// 注册资本
					wd.setRegAuthority(badRecord.getRegAuthority());// 登记机关
					wd.setAbode(badRecord.getAbode());// 住所
					wd.setIndustryCode(badRecord.getIndustryCode());// 行业代码
					wd.setCreationDate(badRecord.getCreationDate());// 设立日期
					wd.setIncludedReason(badRecord.getIncludedReason());// 列入原因
					wd.setIncludedDate(badRecord.getIncludedDate());// 列入时间
					wd.setRemoveDate(badRecord.getRemoveDate());// 移出日期
					wd.setStatus(badRecord.getStatus());// 状态
					wd.setProvince(badRecord.getProvince());// 省份
					wd.setTaskDate(badRecord.getTaskDate());// 入库时间
					wd.setPunishPerName(badRecord.getPunishPerName());// 处罚对象名称
					wd.setCardNumber(badRecord.getCardNumber());// 证件号码
					wd.setPunishDate(badRecord.getPunishDate());// 处罚处理日期
					wd.setPunishName(badRecord.getPunishName());// 处罚处理名称
					wd.setPunishType(badRecord.getPunishType());// 处罚处理种类
					wd.setPunishAuthrity(badRecord.getPunishAuthrity());// 处罚机关
					wd.setEndDate(badRecord.getEndDate());// 有效截止日期
					wds.add(wd);
				}
				companyRecord1.setBadRecords(wds);
			}
			companyRecord1.setDishonestyCount(companyRecord
					.getDishonestyCount());
			Set<DishonestyRecord> dishonestyRecords = companyRecord
					.getDishonestyRecords();
			if (null != dishonestyRecords && dishonestyRecords.size() > 0) {
				List<com.crawler.creditchina.domain.jsontwo.DishonestyRecord> wds = new ArrayList<com.crawler.creditchina.domain.jsontwo.DishonestyRecord>();
				for (DishonestyRecord dishonestyRecord : dishonestyRecords) {
					com.crawler.creditchina.domain.jsontwo.DishonestyRecord wd = new com.crawler.creditchina.domain.jsontwo.DishonestyRecord();
					wd.setInforSource(dishonestyRecord.getInforSource());// 信息来源
					wd.setReleaseTime(dishonestyRecord.getReleaseTime());// 发布时间
					wd.setCompanyName(dishonestyRecord.getCompanyName());// 企业/自然人名称
					wd.setCodeNumber(dishonestyRecord.getCodeNumber());// 组织机构代码/身份证号
					wd.setCaseNumber(dishonestyRecord.getCaseNumber());// 案号
					wd.setFilingTime(dishonestyRecord.getFilingTime());// 立案时间
					wd.setSex(dishonestyRecord.getSex());// 性别
					wd.setAge(dishonestyRecord.getAge());// 年龄
					wd.setExeCourt(dishonestyRecord.getExeCourt());// 执行法院
					wd.setProvince(dishonestyRecord.getProvince());// 省份
					wd.setPerformNum(dishonestyRecord.getPerformNum());// 执行依据文号
					wd.setPerformUnit(dishonestyRecord.getPerformUnit());// 作出执行依据单位
					wd.setLawEffect(dishonestyRecord.getLawEffect());// 法律生效文书确定的义务
					wd.setExecution(dishonestyRecord.getExecution());// 被执行人的履行情况
					wd.setSituation(dishonestyRecord.getSituation());// 失信被执行人具体情形
					wd.setLegalPerson(dishonestyRecord.getLegalPerson());// 法定代表人或者负责人姓名
					wd.setExecuted(dishonestyRecord.getExecuted());// 已履行
					wd.setNonperforming(dishonestyRecord.getNonperforming());// 未履行
					wds.add(wd);
				}
				companyRecord1.setDishonestyRecords(wds);
			}
		}
		LOGGER.info("CompanyRecord transfer to com.crawler.creditchina.domain.jsontwo.CompanyRecord is end!");
		return companyRecord1;
	}

	public static CompanyRecord voToPo(
			com.crawler.creditchina.domain.jsontwo.CompanyRecord companyRecord1) {
		LOGGER.info("com.crawler.creditchina.domain.jsontwo.CompanyRecord transfer to CompanyRecord is begin!");
		CompanyRecord companyRecord = null;
		if (null != companyRecord1) {
			companyRecord = new CompanyRecord();
			companyRecord.setName(companyRecord1.getName());
			companyRecord.setObjectType(companyRecord1.getObjectType());
			companyRecord.setBadCount(companyRecord1.getBadCount());
			List<com.crawler.creditchina.domain.jsontwo.BadRecord> badRecords = companyRecord1
					.getBadRecords();
			if (null != badRecords && badRecords.size() > 0) {
				Set<BadRecord> wds = new LinkedHashSet<BadRecord>();
				for (com.crawler.creditchina.domain.jsontwo.BadRecord badRecord : badRecords) {
					BadRecord wd = new BadRecord();
					wd.setCompanyRecord(companyRecord);
					wd.setInforSource(badRecord.getInforSource());// 信息来源（现网站已无该字段）
					wd.setCaseName(badRecord.getCaseName());// 案件名称（现网站已无该字段）
					wd.setAccepted(badRecord.getAccepted());// 受理号（现网站已无该字段）
					wd.setBusinessType(badRecord.getBusinessType());// 业务类型（现网站已无该字段）
					wd.setCaseNature(badRecord.getCaseNature());// 案件性质（现网站已无该字段）
					wd.setQualitBasis(badRecord.getQualitBasis());// 定性依据（现网站已无该字段）
					wd.setPunishBasis(badRecord.getPunishBasis());// 处罚依据（现网站已无该字段）
					wd.setCaseDate(badRecord.getCaseDate());// 立案日期（现网站已无该字段）
					wd.setDeliveryDate(badRecord.getDeliveryDate());// 处罚决定书送达日期（现网站已无该字段）
					wd.setRegNumber(badRecord.getRegNumber());// 企业注册号
					wd.setCompanyName(badRecord.getCompanyName());// 企业名称
					wd.setLegalPerson(badRecord.getLegalPerson());// 法定代表人
					wd.setPunishNumber(badRecord.getPunishNumber());// 处罚决定书文号
					wd.setPunishContent(badRecord.getPunishContent());// 处罚决定内容
					wd.setRegCapital(badRecord.getRegCapital());// 注册资本
					wd.setRegAuthority(badRecord.getRegAuthority());// 登记机关
					wd.setAbode(badRecord.getAbode());// 住所
					wd.setIndustryCode(badRecord.getIndustryCode());// 行业代码
					wd.setCreationDate(badRecord.getCreationDate());// 设立日期
					wd.setIncludedReason(badRecord.getIncludedReason());// 列入原因
					wd.setIncludedDate(badRecord.getIncludedDate());// 列入时间
					wd.setRemoveDate(badRecord.getRemoveDate());// 移出日期
					wd.setStatus(badRecord.getStatus());// 状态
					wd.setProvince(badRecord.getProvince());// 省份
					wd.setTaskDate(badRecord.getTaskDate());// 入库时间
					wd.setPunishPerName(badRecord.getPunishPerName());// 处罚对象名称
					wd.setCardNumber(badRecord.getCardNumber());// 证件号码
					wd.setPunishDate(badRecord.getPunishDate());// 处罚处理日期
					wd.setPunishName(badRecord.getPunishName());// 处罚处理名称
					wd.setPunishType(badRecord.getPunishType());// 处罚处理种类
					wd.setPunishAuthrity(badRecord.getPunishAuthrity());// 处罚机关
					wd.setEndDate(badRecord.getEndDate());// 有效截止日期
					wds.add(wd);
				}
				companyRecord.setBadRecords(wds);
			}
			companyRecord.setDishonestyCount(companyRecord1
					.getDishonestyCount());
			List<com.crawler.creditchina.domain.jsontwo.DishonestyRecord> dishonestyRecords = companyRecord1
					.getDishonestyRecords();
			if (null != dishonestyRecords && dishonestyRecords.size() > 0) {
				Set<DishonestyRecord> wds = new LinkedHashSet<DishonestyRecord>();
				for (com.crawler.creditchina.domain.jsontwo.DishonestyRecord dishonestyRecord : dishonestyRecords) {
					DishonestyRecord wd = new DishonestyRecord();
					wd.setCompanyRecord(companyRecord);
					wd.setInforSource(dishonestyRecord.getInforSource());// 信息来源
					wd.setReleaseTime(dishonestyRecord.getReleaseTime());// 发布时间
					wd.setCompanyName(dishonestyRecord.getCompanyName());// 企业/自然人名称
					wd.setCodeNumber(dishonestyRecord.getCodeNumber());// 组织机构代码/身份证号
					wd.setCaseNumber(dishonestyRecord.getCaseNumber());// 案号
					wd.setFilingTime(dishonestyRecord.getFilingTime());// 立案时间
					wd.setSex(dishonestyRecord.getSex());// 性别
					wd.setAge(dishonestyRecord.getAge());// 年龄
					wd.setExeCourt(dishonestyRecord.getExeCourt());// 执行法院
					wd.setProvince(dishonestyRecord.getProvince());// 省份
					wd.setPerformNum(dishonestyRecord.getPerformNum());// 执行依据文号
					wd.setPerformUnit(dishonestyRecord.getPerformUnit());// 作出执行依据单位
					wd.setLawEffect(dishonestyRecord.getLawEffect());// 法律生效文书确定的义务
					wd.setExecution(dishonestyRecord.getExecution());// 被执行人的履行情况
					wd.setSituation(dishonestyRecord.getSituation());// 失信被执行人具体情形
					wd.setLegalPerson(dishonestyRecord.getLegalPerson());// 法定代表人或者负责人姓名
					wd.setExecuted(dishonestyRecord.getExecuted());// 已履行
					wd.setNonperforming(dishonestyRecord.getNonperforming());// 未履行
					wds.add(wd);
				}
				companyRecord.setDishonestyRecords(wds);
			}
		}
		LOGGER.info("com.crawler.creditchina.domain.jsontwo.CompanyRecord transfer to CompanyRecord is end!");
		return companyRecord;
	}

}
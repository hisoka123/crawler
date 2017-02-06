package com.crawler.creditchina.htmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crawler.creditchina.domain.jsontwo.BadRecord;
import com.crawler.creditchina.domain.jsontwo.CompanyRecord;
import com.crawler.creditchina.domain.jsontwo.DishonestyRecord;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class CreditchinaParser {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CreditchinaParser.class);

	public CompanyRecord getEntityFromHtml(String html) {
		LOGGER.info("======creditchina parser is start!=====");
		if (StringUtils.isEmpty(html)) {
			return null;
		}
		CompanyRecord companyRecord = new CompanyRecord();
		Document creditchinaDoc = Jsoup.parseBodyFragment(html);
		Element creditchina_et = creditchinaDoc.getElementsByClass(
				"creditsearch-tagsinfos").first();
		Element bad_et = null;
		if (creditchina_et != null) {
			bad_et = creditchina_et.select("div.creditsearch-tagsinfo").get(2);
		}
		if (bad_et != null) {
			Elements bad_uls = bad_et.select("ul");
			int i = 0;
			if (bad_uls != null && bad_uls.size() > 0) {
				List<BadRecord> badRecords = new ArrayList<BadRecord>();
				for (Element bad_ul : bad_uls) {
					Elements bad_lis = bad_ul.select("li");
					if (bad_lis != null && bad_lis.size() > 0) {
						i++;
						BadRecord badRecord = new BadRecord();
						for (Element bad_li : bad_lis) {
							if (bad_li.text().contains("企业名称：")) {
								badRecord.setCompanyName(bad_li.ownText());// 企业名称
							}
							if (bad_li.text().contains("注册号：")) {
								badRecord.setRegNumber(bad_li.ownText());// 企业注册号
							}
							if (bad_li.text().contains("注册资本：")) {
								badRecord.setRegCapital(bad_li.ownText());// 注册资本
							}
							if (bad_li.text().contains("法定代表人：")) {
								badRecord.setLegalPerson(bad_li.ownText());// 法定代表人
							}
							if (bad_li.text().contains("登记机关：")) {
								badRecord.setRegAuthority(bad_li.ownText());// 登记机关
							}
							if (bad_li.text().contains("住所：")) {
								badRecord.setAbode(bad_li.ownText());// 住所
							}
							if (bad_li.text().contains("行业代码：")) {
								badRecord.setIndustryCode(bad_li.ownText());// 行业代码
							}
							if (bad_li.text().contains("设立日期：")) {
								badRecord.setCreationDate(bad_li.ownText());// 设立日期
							}
							if (bad_li.text().contains("列入原因：")) {
								badRecord.setIncludedReason(bad_li.ownText());// 列入原因
							}
							if (bad_li.text().contains("移出日期：")) {
								badRecord.setRemoveDate(bad_li.ownText());// 移出日期
							}
							if (bad_li.text().contains("状态：")) {
								badRecord.setStatus(bad_li.ownText());// 状态
							}
							if (bad_li.text().contains("省份：")) {
								badRecord.setProvince(bad_li.ownText());// 省份
							}
							if (bad_li.text().contains("入库时间：")) {
								badRecord.setTaskDate(bad_li.ownText());// 入库时间
							}
							if (bad_li.text().contains("处罚决定内容：")
									|| bad_li.text().contains("处罚处理内容：")) {
								badRecord.setPunishContent(bad_li.ownText());// 处罚决定内容（处罚处理内容）
							}
							if (bad_li.text().contains("处罚决定书文号：")
									|| bad_li.text().contains("处罚决定书ID：")) {
								badRecord.setPunishNumber(bad_li.ownText());// 处罚决定书文号（处罚决定书ID）
							}
							if (bad_li.text().contains("处罚对象名称：")) {
								badRecord.setPunishPerName(bad_li.ownText());// 处罚对象名称
							}
							if (bad_li.text().contains("证件号码：")) {
								badRecord.setCardNumber(bad_li.ownText());// 证件号码
							}
							if (bad_li.text().contains("处罚处理日期：")) {
								badRecord.setPunishDate(bad_li.ownText());// 处罚处理日期
							}
							if (bad_li.text().contains("处罚处理名称：")) {
								badRecord.setPunishName(bad_li.ownText());// 处罚处理名称
							}
							if (bad_li.text().contains("处罚处理种类：")) {
								badRecord.setPunishType(bad_li.ownText());// 处罚处理种类
							}
							if (bad_li.text().contains("处罚机关：")) {
								badRecord.setPunishAuthrity(bad_li.ownText());// 处罚机关
							}
							if (bad_li.text().contains("有效截止日期：")) {
								badRecord.setEndDate(bad_li.ownText());// 列入时间
							}
							if (bad_li.text().contains("信息来源：")) {
								badRecord.setInforSource(bad_li.ownText());// 信息来源（现网站已无该字段）
							}
							if (bad_li.text().contains("案件名称：")) {
								badRecord.setCaseName(bad_li.ownText());// 案件名称（现网站已无该字段）
							}
							if (bad_li.text().contains("受理号：")) {
								badRecord.setAccepted(bad_li.ownText());// 受理号（现网站已无该字段）
							}
							if (bad_li.text().contains("业务类型：")) {
								badRecord.setBusinessType(bad_li.ownText());// 业务类型（现网站已无该字段）
							}
							if (bad_li.text().contains("案件性质：")) {
								badRecord.setCaseNature(bad_li.ownText());// 案件性质（现网站已无该字段）
							}
							if (bad_li.text().contains("定性依据：")) {
								badRecord.setQualitBasis(bad_li.ownText());// 定性依据（现网站已无该字段）
							}
							if (bad_li.text().contains("处罚依据：")) {
								badRecord.setPunishBasis(bad_li.ownText());// 处罚依据（现网站已无该字段）
							}
							if (bad_li.text().contains("立案日期：")) {
								badRecord.setCaseDate(bad_li.ownText());// 立案日期（现网站已无该字段）
							}
							if (bad_li.text().contains("处罚决定书送达日期：")) {
								badRecord.setDeliveryDate(bad_li.ownText());// 处罚决定书送达日期（现网站已无该字段）
							}
						}
						badRecords.add(badRecord);
					}
				}
				companyRecord.setBadCount(i);
				companyRecord.setBadRecords(badRecords);
			} else {
				companyRecord.setBadCount(i);
			}
		} else {
			companyRecord.setBadCount(0);
		}
		Element dishonesty_et = creditchinaDoc.getElementById("dishonestyImg");
		if (dishonesty_et != null) {
			Elements dishonesty_uls = dishonesty_et.select("ul");
			int j = 0;
			if (dishonesty_uls != null && dishonesty_uls.size() > 0) {
				List<DishonestyRecord> dishonestyRecords = new ArrayList<DishonestyRecord>();
				for (Element dishonesty_ul : dishonesty_uls) {
					Elements dishonesty_lis = dishonesty_ul.select("li");
					if (dishonesty_lis != null && dishonesty_lis.size() > 0) {
						j++;
						DishonestyRecord dishonestyRecord = new DishonestyRecord();
						for (Element dishonesty_li : dishonesty_lis) {
							if (dishonesty_li.text().contains("信息来源：")) {
								dishonestyRecord.setInforSource(dishonesty_li
										.ownText());// 信息来源
							}
							if (dishonesty_li.text().contains("发布时间：")) {
								dishonestyRecord.setReleaseTime(dishonesty_li
										.ownText());// 发布时间
							}
							if (dishonesty_li.text().contains("企业名称：")
									|| dishonesty_li.text().contains("名称：")) {
								dishonestyRecord.setCompanyName(dishonesty_li
										.ownText());// 企业/自然人名称
							}
							if (dishonesty_li.text().contains("组织机构代码：")
									|| dishonesty_li.text().contains("身份证号码：")) {
								dishonestyRecord.setCodeNumber(dishonesty_li
										.ownText());// 组织机构代码/身份证号
							}
							if (dishonesty_li.text().contains("案号：")) {
								dishonestyRecord.setCaseNumber(dishonesty_li
										.ownText());// 案号
							}
							if (dishonesty_li.text().contains("立案时间：")) {
								dishonestyRecord.setFilingTime(dishonesty_li
										.ownText());// 立案时间
							}
							if (dishonesty_li.text().contains("性别：")) {
								dishonestyRecord
										.setSex(dishonesty_li.ownText());// 性别
							}
							if (dishonesty_li.text().contains("年龄：")) {
								dishonestyRecord
										.setAge(dishonesty_li.ownText());// 年龄
							}
							if (dishonesty_li.text().contains("执行法院：")) {
								dishonestyRecord.setExeCourt(dishonesty_li
										.ownText());// 执行法院
							}
							if (dishonesty_li.text().contains("省份：")) {
								dishonestyRecord.setProvince(dishonesty_li
										.ownText());// 省份
							}
							if (dishonesty_li.text().contains("执行依据文号：")) {
								dishonestyRecord.setPerformNum(dishonesty_li
										.ownText());// 执行依据文号
							}
							if (dishonesty_li.text().contains("做出执行依据单位：")) {
								dishonestyRecord.setPerformUnit(dishonesty_li
										.ownText());// 作出执行依据单位
							}
							if (dishonesty_li.text().contains("生效法律文书确定的义务：")) {
								dishonestyRecord.setLawEffect(dishonesty_li
										.ownText());// 法律生效文书确定的义务
							}
							if (dishonesty_li.text().contains("被执行人的履行情况：")) {
								dishonestyRecord.setExecution(dishonesty_li
										.ownText());// 被执行人的履行情况
							}
							if (dishonesty_li.text().contains("失信被执行人行为具体情形：")) {
								dishonestyRecord.setSituation(dishonesty_li
										.ownText());// 失信被执行人具体情形
							}
							if (dishonesty_li.text().contains("法定代表人或者负责人姓名：")) {
								dishonestyRecord.setLegalPerson(dishonesty_li
										.ownText());// 法定代表人或者负责人姓名
							}
							if (dishonesty_li.text().contains("已履行：")) {
								dishonestyRecord.setExecuted(dishonesty_li
										.ownText());// 已履行
							}
							if (dishonesty_li.text().contains("未履行：")) {
								dishonestyRecord.setNonperforming(dishonesty_li
										.ownText());// 未履行
							}
						}
						dishonestyRecords.add(dishonestyRecord);
					}
				}
				companyRecord.setDishonestyCount(j);
				companyRecord.setDishonestyRecords(dishonestyRecords);
			} else {
				companyRecord.setDishonestyCount(j);
			}
		} else {
			companyRecord.setDishonestyCount(0);
		}
		LOGGER.info("======creditchina parser is end!=====");
		return companyRecord;
	}

	public List<CompanyRecord> getListFromHtml(String html) {
		LOGGER.info("======creditchina parser is start!=====");
		List<CompanyRecord> wds = null;
		List<Map<String, Object>> htmlList = new Gson().fromJson(html,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());
		if (null != htmlList && htmlList.size() > 0) {
			wds = new ArrayList<CompanyRecord>();
			for (Map<String, Object> htmlMap : htmlList) {
				CompanyRecord companyRecord = getEntityFromHtml(htmlMap.get(
						"xyzgxqym").toString());
				if (null != htmlMap.get("companyName")) {
					companyRecord
							.setName(htmlMap.get("companyName").toString());
				}
				if (null != htmlMap.get("objectType")) {
					companyRecord.setObjectType(htmlMap.get("objectType")
							.toString());
				}
				wds.add(companyRecord);
			}
		}
		LOGGER.info("======creditchina parser is end!=====");
		return wds;
	}

}
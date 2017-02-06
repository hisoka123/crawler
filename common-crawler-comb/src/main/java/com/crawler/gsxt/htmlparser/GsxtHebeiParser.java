package com.crawler.gsxt.htmlparser;

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

import com.crawler.gsxt.domain.json.*;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtHebeiParser extends AbstractGsxtParser {

	private static final Logger LOOGER = LoggerFactory
			.getLogger(GsxtHebeiParser.class);

	public AicFeedJson getHebeiResultObj(String html, boolean isDebug) {

		LOOGER.info("The method of GsxtHebeiParser.getHebeiResultObj is begin !");

		Gson gson = new GsonBuilder().create();
		Map<String, Object> htmlMap = gson.fromJson(html,
				new TypeToken<Map<String, Object>>() {
				}.getType());

		if (null == htmlMap.get("gsgsxx")
				|| null == String.valueOf(htmlMap.get("gsgsxx"))
				|| StringUtils.isEmpty(String.valueOf(htmlMap.get("gsgsxx")))) {
			return null;
		}

		// 工商系统bean
		AicFeedJson gsxtFeedJson = new AicFeedJson();

		// 工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();

		Document gsgsxx = Jsoup.parseBodyFragment(htmlMap.get("gsgsxx")
				.toString());

		// 工商公示信息->登记信息
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();

		// 工商公示信息->登记信息->基本信息
		Elements baseinfo = gsgsxx.getElementsByAttributeValue("rel",
				"layout-01_01").select("tbody");
		Elements ths = baseinfo.select("tr").select("th");
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
		for (int i = 1; i < ths.size(); i++) {
			if (ths.get(i).text().trim().contains("统一社会信用代码")
					|| ths.get(i).text().trim().contains("注册号")) {
				gsgsDjJbInfo.setNum(ths.get(i).nextElementSibling().text());// 注册号或信用代码
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("名称")) {
				gsgsDjJbInfo.setName(ths.get(i).nextElementSibling().text());// 名称
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("类型")) {
				gsgsDjJbInfo.setType(ths.get(i).nextElementSibling().text());// 类型
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("负责人")
					|| ths.get(i).text().trim().equalsIgnoreCase("法定代表人")
					|| ths.get(i).text().trim().contains("经营者")) {
				gsgsDjJbInfo.setLegalRepr(ths.get(i).nextElementSibling()
						.text());// 法定代表人/经营者
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("注册资本")) {
				gsgsDjJbInfo.setRegCapital(ths.get(i).nextElementSibling()
						.text());// 注册资本
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("成立日期")) {
				gsgsDjJbInfo.setRegDateTime(ths.get(i).nextElementSibling()
						.text());// 成立日期
			}
			if (ths.get(i).text().trim().contains("营业场所")
					|| ths.get(i).text().trim().contains("住所")) {
				gsgsDjJbInfo.setAddress(ths.get(i).nextElementSibling().text());// 经营场所/住所
			}
			if (ths.get(i).text().trim().contains("营业期限自")
					|| ths.get(i).text().trim().contains("经营期限自")) {
				gsgsDjJbInfo.setStartDateTime(ths.get(i).nextElementSibling()
						.text());// 营业期限自（即营业开始日期）
			}
			if (ths.get(i).text().trim().contains("营业期限至")
					|| ths.get(i).text().trim().contains("经营期限至")) {
				gsgsDjJbInfo.setEndDateTime(ths.get(i).nextElementSibling()
						.text());// 营业期限至（即营业结束日期）
			}
			if (ths.get(i).text().trim().contains("经营范围")) {
				gsgsDjJbInfo.setBusinessScope(ths.get(i).nextElementSibling()
						.text());// 经营范围
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("登记机关")) {
				gsgsDjJbInfo.setRegAuthority(ths.get(i).nextElementSibling()
						.text());// 登记机关
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("核准日期")) {
				gsgsDjJbInfo.setApprovalDateTime(ths.get(i)
						.nextElementSibling().text());// 核准日期
			}
			if (ths.get(i).text().trim().contains("登记状态")) {
				gsgsDjJbInfo.setRegStatus(ths.get(i).nextElementSibling()
						.text());// 登记状态
			}
			if (ths.get(i).text().trim().contains("吊销日期")) {
				gsgsDjJbInfo.setRevokeDate(ths.get(i).nextElementSibling()
						.text());// 吊销日期
			}
		}
		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);// 基本信息
		// 工商公示信息->登记信息->股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
		Element touziren = gsgsxx.getElementById("investorTable");
		if (null != touziren) {
			Elements touziren_trs = touziren.select("tbody").get(0)
					.select("tr");
			if (null != touziren_trs && touziren_trs.size() > 3) {
				for (int i = 2; i < touziren_trs.size() - 1; i++) {
					// 股东信息
					AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();
					Elements touziren_tds = touziren_trs.get(i).select("td");
					int touziren_tds_size = touziren_tds.size();
					if (touziren_tds_size > 3) {
						gsgsDjGdInfo.setType(touziren_tds.get(0).text());// 股东类型
						gsgsDjGdInfo.setName(touziren_tds.get(1).text());// 股东名称
						gsgsDjGdInfo.setIdType(touziren_tds.get(2).text());// 证照/证件类型
						gsgsDjGdInfo.setIdNum(touziren_tds.get(3).text());// 证照/证件号码
					}
					gsgsDjGdInfos.add(gsgsDjGdInfo);
				}
			}
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = (List<Map<String, Object>>) htmlMap
				.get("gsgsxx_djxx_tzrxx_xqs");
		int gsgsxx_djxx_tzrxx_xqs_size = 0;
		if (null != gsgsxx_djxx_tzrxx_xqs) {
			gsgsxx_djxx_tzrxx_xqs_size = gsgsxx_djxx_tzrxx_xqs.size();
		}
		List<AicpubRegStohrStohrinvestInfo> gsgsDjGdGdjczInfos = new ArrayList<AicpubRegStohrStohrinvestInfo>();
		if (gsgsxx_djxx_tzrxx_xqs_size > 0) {
			for (Map<String, Object> gsgsxx_djxx_tzrxx_xq : gsgsxx_djxx_tzrxx_xqs) {
				Document gsgsxx_djxx_tzrxx_xq_page = Jsoup
						.parseBodyFragment(gsgsxx_djxx_tzrxx_xq.get(
								"gsgsxx_djxx_tzrxx_xq").toString());
				Elements gsgsxx_djxx_tzrxx_xq_trs = gsgsxx_djxx_tzrxx_xq_page
						.getElementById("investor").select("tbody")
						.select("tr");
				int gsgsxx_djxx_tzrxx_xq_trs_size = gsgsxx_djxx_tzrxx_xq_trs
						.size();
				if (gsgsxx_djxx_tzrxx_xq_trs_size > 3) {
					AicpubRegStohrStohrinvestInfo gsgsDjGdGdjczInfo = new AicpubRegStohrStohrinvestInfo();
					gsgsDjGdGdjczInfo.setStockholder(gsgsxx_djxx_tzrxx_xq_trs
							.get(3).select("td").get(0).text());// 股东
					gsgsDjGdGdjczInfo.setSubAmount(gsgsxx_djxx_tzrxx_xq_trs
							.get(3).select("td").get(1).text());// 认缴额（万元）
					gsgsDjGdGdjczInfo.setPaidAmount(gsgsxx_djxx_tzrxx_xq_trs
							.get(3).select("td").get(2).text());// 实缴额（万元）
					List<AmountDetail> subAmountDetails = new ArrayList<AmountDetail>();
					List<AmountDetail> paidAmountDetails = new ArrayList<AmountDetail>();
					for (int i = 3; i < gsgsxx_djxx_tzrxx_xq_trs_size; i++) {
						AicpubRegStohrStohrinvestInfo.AmountDetail subamountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
						AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
						if (i > 3) {
							Elements wc_tds = gsgsxx_djxx_tzrxx_xq_trs.get(i)
									.select("td");
							subamountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(0).text();// 认缴出资方式
							subamountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(1).text();// 认缴出资额（万元）
							subamountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(2).text();// 认缴出资日期
							if (wc_tds.size() > 5) {
								paidAmountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(3).text();// 实缴出资方式
								paidAmountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(4).text();// 实缴出资额（万元）
								paidAmountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(5).text();// 实缴出资日期
							}
						} else {
							Elements wc_tds = gsgsxx_djxx_tzrxx_xq_trs.get(i)
									.select("td");
							subamountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(3).text();// 认缴出资方式
							subamountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(4).text();// 认缴出资额（万元）
							subamountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(5).text();// 认缴出资日期
							if (wc_tds.size() > 8) {
								paidAmountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(6).text();// 实缴出资方式
								paidAmountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(7).text();// 实缴出资额（万元）
								paidAmountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(8).text();// 实缴出资日期
							}
						}
						subAmountDetails.add(subamountDetail);
						paidAmountDetails.add(paidAmountDetail);
					}
					gsgsDjGdGdjczInfo.setSubAmountDetails(subAmountDetails);
					gsgsDjGdGdjczInfo.setPaidAmountDetails(paidAmountDetails);
					gsgsDjGdGdjczInfos.add(gsgsDjGdGdjczInfo);
				}
			}
		}
		// 股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos2 = new ArrayList<AicpubRegStohrInfo>();
		for (AicpubRegStohrInfo gsgsDjGdInfo : gsgsDjGdInfos) {
			for (AicpubRegStohrStohrinvestInfo gsgsDjGdGdjczInfo : gsgsDjGdGdjczInfos) {
				if (gsgsDjGdInfo
						.getName()
						.trim()
						.equalsIgnoreCase(
								gsgsDjGdGdjczInfo.getStockholder().trim())) {
					gsgsDjGdInfo.setStohrInvestInfo(gsgsDjGdGdjczInfo);
				}
			}
			gsgsDjGdInfos2.add(gsgsDjGdInfo);
		}
		gsgsDjInfo.setStohrInfos(gsgsDjGdInfos2);// 股东信息
		// 工商公示信息->登记信息->变更信息
		List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<AicpubRegChangeInfo>();
		Element biangeng = gsgsxx.getElementById("alterTable");
		if (null != biangeng) {
			Elements biangeng_trs = biangeng.select("tbody").get(0)
					.select("tr");
			if (null != biangeng_trs && biangeng_trs.size() > 3) {
				for (int i = 2; i < biangeng_trs.size() - 1; i++) {
					AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
					gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td")
							.get(0).text());// 变更事项
					Elements biangeng_one = biangeng_trs.get(i).select("td")
							.get(1).getElementsByTag("span");
					if (null != biangeng_one && biangeng_one.size() > 0) {
						gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
								.select("td").get(1).getElementById("allWords")
								.text());// 变更前内容
					} else {
						gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
								.select("td").get(1).text());// 变更前内容
					}
					Elements biangeng_two = biangeng_trs.get(i).select("td")
							.get(2).getElementsByTag("span");
					if (null != biangeng_two && biangeng_two.size() > 0) {
						gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
								.select("td").get(2).getElementById("allWords")
								.text());// 变更后内容
					} else {
						gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
								.select("td").get(2).text());// 变更后内容
					}
					gsgsDjBgInfo.setDateTime(biangeng_trs.get(i).select("td")
							.get(3).text());// 变更日期
					gsgsDjBgInfos.add(gsgsDjBgInfo);
				}
			}
			gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);// 变更信息
		}
		gsgsInfo.setRegInfo(gsgsDjInfo);// 工商公示信息->登记信息

		// 工商公示信息->备案信息
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
		Element beian_zyryxx = gsgsxx.getElementById("memberTable");
		// 工商公示信息->备案信息->主要人员信息
		List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
		if (null != beian_zyryxx) {
			Elements beian_zyryxx_trs = beian_zyryxx.select("tr");
			if (null != beian_zyryxx_trs && beian_zyryxx_trs.size() > 3) {
				for (int i = 2; i < beian_zyryxx_trs.size() - 1; i++) {
					int td_size = beian_zyryxx_trs.get(i).select("td").size();
					String name1 = beian_zyryxx_trs.get(i).select("td").get(1)
							.text();
					String position1 = beian_zyryxx_trs.get(i).select("td")
							.get(2).text();
					String name2 = "";
					String position2 = "";
					if (td_size > 5) {
						name2 = beian_zyryxx_trs.get(i).select("td").get(4)
								.text();
						position2 = beian_zyryxx_trs.get(i).select("td").get(5)
								.text();
					}
					// 主要人员信息
					AicpubArchivePrimemberInfo gsgsBaZyryInfo1 = new AicpubArchivePrimemberInfo();
					// 主要人员信息
					AicpubArchivePrimemberInfo gsgsBaZyryInfo2 = new AicpubArchivePrimemberInfo();
					if (!"".equals(name1) && null != name1
							&& !StringUtils.isEmpty(name1.trim())) {
						gsgsBaZyryInfo1.setName(name1);// 姓名
						gsgsBaZyryInfo1.setPosition(position1);// 职务
						gsgsBaZyryInfos.add(gsgsBaZyryInfo1);
					}
					if (!"".equals(name2) && null != name2
							&& !StringUtils.isEmpty(name2.trim())) {
						gsgsBaZyryInfo2.setName(name2);// 姓名
						gsgsBaZyryInfo2.setPosition(position2);// 职务
						gsgsBaZyryInfos.add(gsgsBaZyryInfo2);
					}
				}
			}
		}
		gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
		// 工商公示信息->备案信息->分支机构信息
		List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
		Element beian_fzjgxx = gsgsxx.getElementById("branchTable");
		if (null != beian_fzjgxx) {
			Elements beian_fzjgxx_trs = beian_fzjgxx.select("tr");
			if (null != beian_fzjgxx_trs && beian_fzjgxx_trs.size() > 3) {
				for (int i = 2; i < beian_fzjgxx_trs.size() - 1; i++) {
					// 分支机构信息
					AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
					gsgsBaFzjgInfo.setNum(beian_fzjgxx_trs.get(i).select("td")
							.get(1).text());// 统一社会信用代码/注册号
					gsgsBaFzjgInfo.setName(beian_fzjgxx_trs.get(i).select("td")
							.get(2).text());// 名称
					gsgsBaFzjgInfo.setRegAuthority(beian_fzjgxx_trs.get(i)
							.select("td").get(3).text());// 登记机关
					gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
				}
			}
		}
		gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);// 分支机构信息
		// 工商公示信息->备案信息->清算信息
		AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
		Elements beian_qingsuanrenyuan = gsgsxx.getElementsByAttributeValue(
				"rel", "layout-01_02").select("table");
		if (null != beian_qingsuanrenyuan) {
			Elements beian_qingsuanrenyuan_ths = beian_qingsuanrenyuan
					.select("th");
			for (Element beian_qingsuanrenyuan_th : beian_qingsuanrenyuan_ths) {
				if (beian_qingsuanrenyuan_th.text().contains("清算负责人")) {
					gsgsBaQsInfo.setLeader(beian_qingsuanrenyuan_th
							.nextElementSibling().text());// 清算组负责人
				}
				if (beian_qingsuanrenyuan_th.text().contains("清算组成员")) {
					String wq = beian_qingsuanrenyuan_th.nextElementSibling()
							.text();
					List<String> wd = new ArrayList<String>();
					if (wq.contains("、")) {
						for (String wc : wq.split("、")) {
							wd.add(wc);
						}
						gsgsBaQsInfo.setMembers(wd);// 清算组成员
					} else {
						wd.add(wq);
						gsgsBaQsInfo.setMembers(wd);// 清算组成员
					}
				}
			}
			if (isDebug) {
				gsgsBaQsInfo.setHtml(beian_qingsuanrenyuan.html());
			}
		}
		gsgsBaInfo.setClearInfo(gsgsBaQsInfo);// 清算信息
		gsgsInfo.setArchiveInfo(gsgsBaInfo);// 工商公示信息->备案信息

		// 工商公示信息->动产抵押登记信息
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		Element dongchandiya_et = gsgsxx.getElementById("mortageTable");
		// 工商公示信息->动产抵押登记信息列表
		List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
		if (null != dongchandiya_et) {
			Elements dongchandiya_trs = dongchandiya_et.select("tbody").get(0)
					.select("tr");
			if (null != dongchandiya_trs && dongchandiya_trs.size() > 3) {
				for (int j = 2; j < dongchandiya_trs.size() - 1; j++) {
					AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
					gsgsDcdydjDcdydjInfo.setRegNum(dongchandiya_trs.get(j)
							.select("td").get(1).text());// 登记编号
					gsgsDcdydjDcdydjInfo.setRegDateTime(dongchandiya_trs.get(j)
							.select("td").get(2).text());// 登记日期
					gsgsDcdydjDcdydjInfo.setRegAuthority(dongchandiya_trs
							.get(j).select("td").get(3).text());// 登记机关
					gsgsDcdydjDcdydjInfo
							.setGuaranteedDebtAmount(dongchandiya_trs.get(j)
									.select("td").get(4).text());// 被担保债权数额
					gsgsDcdydjDcdydjInfo.setStatus(dongchandiya_trs.get(j)
							.select("td").get(5).text());// 状态
					gsgsDcdydjDcdydjInfo.setPubDateTime("");// 公示时间（该字段比较特殊，河北工商网暂时没有该字段。）
					if (!dongchandiya_trs.get(j).select("td").get(6).text()
							.contains("详情")) {
						gsgsDcdydjDcdydjInfo.setDetail(dongchandiya_trs.get(j)
								.select("td").get(6).text());// 详情
					}
					gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
				}
			}
			if (isDebug) {
				gsgsDcdydjInfo.setHtml(dongchandiya_et.html());
			}
		}
		// gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);// 动产抵押登记信息
		// gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息
		// 工商公示信息->动产抵押登记信息->详情
		if (null != htmlMap.get("gsgsxx_dcdydjxx_dcdydjxx_xqs")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_dcdydjxx_dcdydjxx_xqs")))) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> gsgsxx_dcdydjxx_xqs = (List<Map<String, Object>>) htmlMap
					.get("gsgsxx_dcdydjxx_dcdydjxx_xqs");
			List<AicpubCChatMortgDetail> aicpubCChatMortgDetails = new ArrayList<AicpubCChatMortgDetail>();
			if (null != gsgsxx_dcdydjxx_xqs && gsgsxx_dcdydjxx_xqs.size() > 0) {
				for (Map<String, Object> gsgsxx_dcdydjxx_xq : gsgsxx_dcdydjxx_xqs) {
					// 工商公示信息->动产抵押登记信息->详情
					AicpubCChatMortgDetail aicpubCChatMortgDetail = new AicpubCChatMortgDetail();
					Document wd = Jsoup.parseBodyFragment(String
							.valueOf(gsgsxx_dcdydjxx_xq
									.get("gsgsxx_dcdydjxx_dcdydjxx_xq")));
					Elements dcDetails = wd.select(".info.m-bottom.m-top");
					if (null != dcDetails && !dcDetails.isEmpty()) {
						// 工商公示信息->动产抵押登记信息->详情->动产抵押登记信息
						AicpubCChatMortgRegInfo aicpubCChatMortgRegInfo = new AicpubCChatMortgRegInfo();
						Elements dcDetails_ths = dcDetails.get(0).select("th");
						for (Element dcDetails_th : dcDetails_ths) {
							if (dcDetails_th.text().contains("登记编号")) {
								aicpubCChatMortgRegInfo.setRegNum(dcDetails_th
										.nextElementSibling().text());
								continue;
							}
							if (dcDetails_th.text().contains("登记日期")) {
								aicpubCChatMortgRegInfo.setRegDate(dcDetails_th
										.nextElementSibling().text());
								continue;
							}
							if (dcDetails_th.text().contains("登记机关")) {
								aicpubCChatMortgRegInfo
										.setRegAuthority(dcDetails_th
												.nextElementSibling().text());
								continue;
							}
							if (dcDetails_th.text().contains("被担保债权种类")) {
								aicpubCChatMortgRegInfo
										.setGuaranteedDebtType(dcDetails_th
												.nextElementSibling().text());
								continue;
							}
							if (dcDetails_th.text().contains("被担保债权数额")) {
								aicpubCChatMortgRegInfo
										.setGuaranteedDebtAmount(dcDetails_th
												.nextElementSibling().text());
								continue;
							}
							if (dcDetails_th.text().contains("债务人履行债务的期限")) {
								aicpubCChatMortgRegInfo.setTerm(dcDetails_th
										.nextElementSibling().text());
								continue;
							}
							if (dcDetails_th.text().contains("担保范围")) {
								aicpubCChatMortgRegInfo
										.setGuaranteedScope(dcDetails_th
												.nextElementSibling().text());
								continue;
							}
							if (dcDetails_th.text().contains("备注")) {
								aicpubCChatMortgRegInfo.setNote(dcDetails_th
										.nextElementSibling().text());
								continue;
							}
						}
						if (isDebug) {
							aicpubCChatMortgRegInfo.setHtml(dcDetails.get(0)
									.html());
						}
						aicpubCChatMortgDetail
								.setMortgRegInfo(aicpubCChatMortgRegInfo);
					}
					Element dyqrgk = wd.getElementById("mortgageMorTable");
					if (null != dyqrgk) {
						Elements dyqrgk_tbs_trs = dyqrgk.select("tbody").get(0)
								.select("tr");
						if (null != dyqrgk_tbs_trs && dyqrgk_tbs_trs.size() > 3) {
							List<AicpubCChatMortgPersonInfo> aicpubCChatMortgPersonInfos = new ArrayList<AicpubCChatMortgPersonInfo>();
							for (int i = 2; i < dyqrgk_tbs_trs.size() - 1; i++) {
								// 工商公示信息->动产抵押登记信息->详情->抵押权人概况
								AicpubCChatMortgPersonInfo aicpubCChatMortgPersonInfo = new AicpubCChatMortgPersonInfo();
								aicpubCChatMortgPersonInfo
										.setName(dyqrgk_tbs_trs.get(i)
												.select("td").get(1).text());// 抵押权人名称
								aicpubCChatMortgPersonInfo
										.setType(dyqrgk_tbs_trs.get(i)
												.select("td").get(2).text());// 抵押权人证照/证件类型
								aicpubCChatMortgPersonInfo
										.setNum(dyqrgk_tbs_trs.get(i)
												.select("td").get(3).text());// 证照/证件号码
								aicpubCChatMortgPersonInfos
										.add(aicpubCChatMortgPersonInfo);
							}
							aicpubCChatMortgDetail
									.setMortgPersonInfos(aicpubCChatMortgPersonInfos);
						}
					}
					Elements parent_id3_tbs = wd.select(".info.m-bottom.m-top");
					if (null != parent_id3_tbs && parent_id3_tbs.size() > 2) {
						Elements parent_id3_ths = parent_id3_tbs.get(2)
								.select("tbody").get(0).select("th");
						if (null != parent_id3_ths && !parent_id3_ths.isEmpty()) {
							// 工商公示信息->动产抵押登记信息->详情->被担保债权概况
							AicpubCChatMortgGuaranteedInfo aicpubCChatMortgGuaranteedInfo = new AicpubCChatMortgGuaranteedInfo();
							for (Element parent_id3_th : parent_id3_ths) {
								if (parent_id3_th.text().contains("种类")) {
									aicpubCChatMortgGuaranteedInfo
											.setCategory(parent_id3_th
													.nextElementSibling()
													.text());//
									continue;
								}
								if (parent_id3_th.text().contains("数额")) {
									aicpubCChatMortgGuaranteedInfo
											.setAmount(parent_id3_th
													.nextElementSibling()
													.text());//
									continue;
								}
								if (parent_id3_th.text().contains("担保的范围")) {
									aicpubCChatMortgGuaranteedInfo
											.setGuarantyScope(parent_id3_th
													.nextElementSibling()
													.text());//
									continue;
								}
								if (parent_id3_th.text().contains("债务人履行债务的期限")) {
									aicpubCChatMortgGuaranteedInfo
											.setTerm(parent_id3_th
													.nextElementSibling()
													.text());//
									continue;
								}
								if (parent_id3_th.text().contains("备注")) {
									aicpubCChatMortgGuaranteedInfo
											.setNote(parent_id3_th
													.nextElementSibling()
													.text());//
									continue;
								}
							}
							if (isDebug) {
								aicpubCChatMortgGuaranteedInfo
										.setHtml(parent_id3_tbs.get(2).html());
							}
							aicpubCChatMortgDetail
									.setMortgGuaranteedInfo(aicpubCChatMortgGuaranteedInfo);
						}
					}
					Element dywgk = wd.getElementById("mortgageGuaTable");
					if (null != dywgk) {
						Elements dywgk_trs = dywgk.select("tbody").get(0)
								.select("tr");
						if (null != dywgk_trs && dywgk_trs.size() > 3) {
							List<AicpubCChatMortgGoodsInfo> aicpubCChatMortgGoodsInfos = new ArrayList<AicpubCChatMortgGoodsInfo>();
							for (int i = 2; i < dywgk_trs.size() - 1; i++) {
								// 工商公示信息->动产抵押登记信息->详情->抵押物概况
								AicpubCChatMortgGoodsInfo aicpubCChatMortgGoodsInfo = new AicpubCChatMortgGoodsInfo();
								aicpubCChatMortgGoodsInfo.setName(dywgk_trs
										.get(i).select("td").get(1).text());// 名称
								aicpubCChatMortgGoodsInfo
										.setOwnerShip(dywgk_trs.get(i)
												.select("td").get(2).text());// 所有权归属
								aicpubCChatMortgGoodsInfo
										.setGeneralSituation(dywgk_trs.get(i)
												.select("td").get(3).text());// 数量、质量、状况、所在地等情况
								aicpubCChatMortgGoodsInfo.setNote(dywgk_trs
										.get(i).select("td").get(4).text());// 备注
								aicpubCChatMortgGoodsInfos
										.add(aicpubCChatMortgGoodsInfo);
							}
							aicpubCChatMortgDetail
									.setMortgGoodsInfos(aicpubCChatMortgGoodsInfos);
						}
					}
					Element statusReason = wd.getElementById("statusReason");
					if (null != statusReason) {
						Elements statusReason_tbs = statusReason
								.select("table");
						if (null != statusReason_tbs
								&& !statusReason_tbs.isEmpty()) {
							// 工商公示信息->动产抵押登记信息->详情->注销信息
							AicpubCChatMortgRevokeInfo aicpubCChatMortgRevokeInfo = new AicpubCChatMortgRevokeInfo();
							aicpubCChatMortgRevokeInfo.setRevokeDate(wd
									.getElementById("WRITEOFF_DATE").text());// 注销日期
							aicpubCChatMortgRevokeInfo.setRevokeReason(wd
									.getElementById("WRITEOFF_REASON").text());// 注销原因
							aicpubCChatMortgDetail
									.setMortgRevokeInfo(aicpubCChatMortgRevokeInfo);
						}
					}
					aicpubCChatMortgDetails.add(aicpubCChatMortgDetail);
				}
			}
			List<AicpubCChatMortgInfo> aicpubCChatMortgInfos = new ArrayList<AicpubCChatMortgInfo>();
			if (null != gsgsDcdydjDcdydjInfos
					&& gsgsDcdydjDcdydjInfos.size() > 0) {
				for (AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo2 : gsgsDcdydjDcdydjInfos) {
					if (null != aicpubCChatMortgDetails
							&& aicpubCChatMortgDetails.size() > 0) {
						for (AicpubCChatMortgDetail aicpubCChatMortgDetail2 : aicpubCChatMortgDetails) {
							if (gsgsDcdydjDcdydjInfo2.getRegNum().contains(
									aicpubCChatMortgDetail2.getMortgRegInfo()
											.getRegNum())) {
								gsgsDcdydjDcdydjInfo2
										.setMortgDetail(aicpubCChatMortgDetail2);
								break;
							}
						}
					}
					aicpubCChatMortgInfos.add(gsgsDcdydjDcdydjInfo2);
				}
				gsgsDcdydjInfo.setChatMortgInfos(aicpubCChatMortgInfos);// 动产抵押登记信息
				gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息
			}
		}

		// 工商公示信息->股权出资登记信息
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		Element guquanchuzhi_et = gsgsxx.getElementById("pledgeTable");
		// 工商公示信息->股权出资登记信息列表
		List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
		if (null != guquanchuzhi_et) {
			Elements guquanchuzhi_trs = guquanchuzhi_et.select("tbody").get(0)
					.select("tr");
			if (null != guquanchuzhi_trs && guquanchuzhi_trs.size() > 3) {
				for (int i = 2; i < guquanchuzhi_trs.size() - 1; i++) {
					// 工商公示信息->股权出资登记信息
					AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
					gsgsGqczdjGqczdjInfo.setRegNum(guquanchuzhi_trs.get(i)
							.select("td").get(1).text());// 登记编号
					gsgsGqczdjGqczdjInfo.setMortgagorName(guquanchuzhi_trs
							.get(i).select("td").get(2).text());// 出质人
					gsgsGqczdjGqczdjInfo.setMortgagorIdNum(guquanchuzhi_trs
							.get(i).select("td").get(3).text());// 证照/证件号码（出质人）
					gsgsGqczdjGqczdjInfo.setMortgAmount(guquanchuzhi_trs.get(i)
							.select("td").get(4).text());// 出质股权数额
					gsgsGqczdjGqczdjInfo.setMortgageeName(guquanchuzhi_trs
							.get(i).select("td").get(5).text());// 质权人
					gsgsGqczdjGqczdjInfo.setMortgageeIdNum(guquanchuzhi_trs
							.get(i).select("td").get(6).text());// 证照/证件号码
					gsgsGqczdjGqczdjInfo.setRegDateTime(guquanchuzhi_trs.get(i)
							.select("td").get(7).text());// 股权出质设立登记日期
					gsgsGqczdjGqczdjInfo.setStatus(guquanchuzhi_trs.get(i)
							.select("td").get(8).text());// 状态
					gsgsGqczdjGqczdjInfo.setPubDate("");// 公示时间（该字段比较特殊，河北工商网暂时没有该字段。）
					gsgsGqczdjGqczdjInfo.setChangeSitu(guquanchuzhi_trs.get(i)
							.select("td").get(9).text());// 变化情况
					gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
				}
			}
			if (isDebug) {
				gsgsGqczdjInfo.setHtml(guquanchuzhi_et.html());
			}
		}
		gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);// 股权出资登记信息
		gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);// 工商公示信息->股权出资登记信息

		// 工商公示信息->行政处罚信息
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
		// 工商公示信息->行政处罚信息列表
		List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
		Element xingzhengchufa = gsgsxx.getElementById("punishTable");
		if (null != xingzhengchufa) {
			Elements xingzhengchufa_trs = xingzhengchufa.select("tbody").get(0)
					.select("tr");
			if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 3) {
				for (int i = 2; i < xingzhengchufa_trs.size() - 1; i++) {
					AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();
					gsgsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs.get(i)
							.select("td").get(1).text());// 行政处罚决定书文号
					gsgsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs
							.get(i).select("td").get(2).text());// 违法行为类型
					gsgsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs.get(i)
							.select("td").get(3).text());// 行政处罚内容
					gsgsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs.get(i)
							.select("td").get(4).text());// 作出行政处罚决定机关名称
					gsgsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs.get(i)
							.select("td").get(5).text());// 作出行政处罚决定日期
					gsgsXzcfXzcfInfos.add(gsgsXzcfXzcfInfo);
				}
			}
			if (isDebug) {
				gsgsXzcfInfo.setHtml(xingzhengchufa.html());
			}
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs = (List<Map<String, Object>>) htmlMap
				.get("gsgsxx_xzcfxx_xzcfxx_xqs");
		// TODO
		// int gsgsxx_xzcfxx_xzcfxx_xqs_size = gsgsxx_xzcfxx_xzcfxx_xqs.size();
		int gsgsxx_xzcfxx_xzcfxx_xqs_size = 0;
		List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos2 = new ArrayList<AicpubAAdmpunishInfo>();
		if (null != gsgsxx_xzcfxx_xzcfxx_xqs
				&& gsgsxx_xzcfxx_xzcfxx_xqs_size > 0) {
			for (Map<String, Object> gsgsxx_xzcfxx_xzcfxx_xq : gsgsxx_xzcfxx_xzcfxx_xqs) {
				Document gsgsxx_xzcfxx_xzcfxx_xq_page = Jsoup
						.parseBodyFragment(gsgsxx_xzcfxx_xzcfxx_xq.get(
								"gsgsxx_xzcfxx_xzcfxx_xq").toString());
				AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();
				AicpubAAdmpunishInfo.PunishDetail xzcfDetail = gsgsXzcfXzcfInfo.new PunishDetail();
				xzcfDetail.punishRepNum = gsgsxx_xzcfxx_xzcfxx_xq_page
						.getElementById("xinweichufaxinxi")
						.getElementById("CR_WRIT_SN").text();// 行政处罚决定书文号
				xzcfDetail.name = gsgsxx_xzcfxx_xzcfxx_xq_page
						.getElementById("xinweichufaxinxi")
						.getElementById("NAME").text();// 名称
				xzcfDetail.regNum = gsgsxx_xzcfxx_xzcfxx_xq_page
						.getElementById("xinweichufaxinxi")
						.getElementById("REG_NO").text();// 注册号
				xzcfDetail.legalReprName = gsgsxx_xzcfxx_xzcfxx_xq_page
						.getElementById("xinweichufaxinxi")
						.getElementById("OPER_MAN_NAME").text();// 法定代表人（负责人）姓名
				xzcfDetail.illegalActType = gsgsxx_xzcfxx_xzcfxx_xq_page
						.getElementById("xinweichufaxinxi")
						.getElementById("BREAK_ACTION_TYPE").text();// 违法行为类型
				xzcfDetail.punishContent = gsgsxx_xzcfxx_xzcfxx_xq_page
						.getElementById("xinweichufaxinxi")
						.getElementById("DEAL_KIND").text();// 行政处罚内容
				xzcfDetail.deciAuthority = gsgsxx_xzcfxx_xzcfxx_xq_page
						.getElementById("xinweichufaxinxi")
						.getElementById("CR_ORG").text();// 作出行政处罚决定机关名称
				xzcfDetail.deciDateTime = gsgsxx_xzcfxx_xzcfxx_xq_page
						.getElementById("xinweichufaxinxi")
						.getElementById("CR_WRIT_DATE").text();// 作出行政处罚决定日期
				if (gsgsxx_xzcfxx_xzcfxx_xq_page.getElementById("CR_CONTENT")
						.hasAttr("div")) {
					xzcfDetail.punishRep = gsgsxx_xzcfxx_xzcfxx_xq_page
							.getElementById("CR_CONTENT")
							.select("div.Section1").first().text();// 行政处罚决定书
				}
				gsgsXzcfXzcfInfo.setPunishDetail(xzcfDetail);
				gsgsXzcfXzcfInfos2.add(gsgsXzcfXzcfInfo);
			}
		}
		if (null != gsgsXzcfXzcfInfos && gsgsXzcfXzcfInfos.size() > 0
				&& null != gsgsXzcfXzcfInfos2 && gsgsXzcfXzcfInfos2.size() > 0) {
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos3 = new ArrayList<AicpubAAdmpunishInfo>();
			for (AicpubAAdmpunishInfo gsgsXzcfXzcfInfo : gsgsXzcfXzcfInfos) {
				for (AicpubAAdmpunishInfo gsgsXzcfXzcfInfo2 : gsgsXzcfXzcfInfos2) {
					if (gsgsXzcfXzcfInfo
							.getPunishRepNum()
							.trim()
							.equalsIgnoreCase(
									gsgsXzcfXzcfInfo2.getPunishDetail().punishRepNum
											.trim())) {
						gsgsXzcfXzcfInfo.setPunishDetail(gsgsXzcfXzcfInfo2
								.getPunishDetail());
					}
				}
				gsgsXzcfXzcfInfos3.add(gsgsXzcfXzcfInfo);
			}
			gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos3);// 行政处罚信息
		} else {
			gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos);// 行政处罚信息
		}
		gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);// 工商公示信息->行政处罚信息

		// 工商公示信息->经营异常信息
		AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
		List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();// 经营异常信息列表
		Element yichangminglu = gsgsxx.getElementById("exceptTable");
		if (null != yichangminglu) {
			Elements yichangminglu_trs = yichangminglu.select("tbody").get(0)
					.select("tr");
			if (null != yichangminglu_trs && yichangminglu_trs.size() > 3) {
				for (int i = 2; i < yichangminglu_trs.size() - 1; i++) {
					yichangminglu_trs.get(i).select("td");
					AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();// 经营异常信息
					Elements span_one = yichangminglu_trs.get(i).select("td")
							.get(1).getElementsByTag("span");
					if (null != span_one && span_one.size() > 0) {
						gsgsJyycJyycInfo.setIncludeCause(yichangminglu_trs
								.get(i).select("td").get(1).select("span")
								.attr("completedata"));// 列入经营异常名录原因
					} else {
						gsgsJyycJyycInfo.setIncludeCause(yichangminglu_trs
								.get(i).select("td").get(1).text());// 列入经营异常名录原因
					}
					gsgsJyycJyycInfo.setIncludeDateTime(yichangminglu_trs
							.get(i).select("td").get(2).text());// 列入日期
					Elements span_three = yichangminglu_trs.get(i).select("td")
							.get(3).getElementsByTag("span");
					if (null != span_three && span_three.size() > 0) {
						gsgsJyycJyycInfo.setRemoveCause(yichangminglu_trs
								.get(i).select("td").get(3).select("span")
								.attr("completedata"));// 移出经营异常名录原因
					} else {
						gsgsJyycJyycInfo.setRemoveCause(yichangminglu_trs
								.get(i).select("td").get(3).text());// 移出经营异常名录原因
					}
					gsgsJyycJyycInfo.setRemoveDateTime(yichangminglu_trs.get(i)
							.select("td").get(4).text());// 移出日期
					gsgsJyycJyycInfo.setAuthority(yichangminglu_trs.get(i)
							.select("td").get(5).text());// 作出决定机关
					gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
				}
			}
			if (isDebug) {
				gsgsJyycInfo.setHtml(yichangminglu.html());
			}
		}
		gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);// 经营异常信息列表
		gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);// 工商公示信息->经营异常信息

		// 工商公示信息->严重违法信息
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		Element heimingdan_et = gsgsxx.getElementById("blackTable");
		// 工商公示信息->严重违法信息列表
		List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
		if (null != heimingdan_et) {
			Elements heimingdan_trs = heimingdan_et.select("tbody").get(0)
					.select("tr");
			if (null != heimingdan_trs && heimingdan_trs.size() > 3) {
				for (int i = 2; i < heimingdan_trs.size() - 1; i++) {
					// 严重违法信息
					AicpubSSerillegalInfo gsgsYzwfYzwfInfo = new AicpubSSerillegalInfo();
					gsgsYzwfYzwfInfo.setIncludeCause(heimingdan_trs.get(i)
							.select("td").get(1).text());// 列入严重违法企业名单原因
					gsgsYzwfYzwfInfo.setIncludeDateTime(heimingdan_trs.get(i)
							.select("td").get(2).text());// 列入日期
					gsgsYzwfYzwfInfo.setRemoveCause(heimingdan_trs.get(i)
							.select("td").get(3).text());// 移出严重违法企业名单原因
					gsgsYzwfYzwfInfo.setRemoveDateTime(heimingdan_trs.get(i)
							.select("td").get(4).text());// 移出日期
					gsgsYzwfYzwfInfo.setDeciAuthority(heimingdan_trs.get(i)
							.select("td").get(5).text());// 作出决定机关
					gsgsYzwfYzwfInfos.add(gsgsYzwfYzwfInfo);
				}
			}
			if (isDebug) {
				gsgsYzwfInfo.setHtml(heimingdan_et.html());
			}
		}
		gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);// 严重违法信息
		gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);// 工商公示信息->严重违法信息

		// 工商公示信息->抽查检查信息
		AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		Element chouchaxinxi_et = gsgsxx.getElementById("spotcheckTable");
		// 工商公示信息->抽查检查信息列表
		List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
		if (null != chouchaxinxi_et) {
			Elements chouchaxinxi_trs = chouchaxinxi_et.select("tbody").get(0)
					.select("tr");
			if (null != chouchaxinxi_trs && chouchaxinxi_trs.size() > 3) {
				for (int i = 2; i < chouchaxinxi_trs.size() - 1; i++) {
					AicpubCCheckInfo gsgsCcjcCcjcInfo = new AicpubCCheckInfo();
					gsgsCcjcCcjcInfo.setCheckImplAuthority(chouchaxinxi_trs
							.get(i).select("td").get(1).text());// 检查实施机关
					gsgsCcjcCcjcInfo.setType(chouchaxinxi_trs.get(i)
							.select("td").get(2).text());// 类型
					gsgsCcjcCcjcInfo.setDateTime(chouchaxinxi_trs.get(i)
							.select("td").get(3).text());// 日期
					gsgsCcjcCcjcInfo.setResult(chouchaxinxi_trs.get(i)
							.select("td").get(4).text());// 结果
					gsgsCcjcCcjcInfos.add(gsgsCcjcCcjcInfo);
				}
			}
			if (isDebug) {
				gsgsCcjcInfo.setHtml(chouchaxinxi_et.html());
			}
		}
		gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);// 抽查检查信息
		gsgsInfo.setCheckInfo(gsgsCcjcInfo);// 工商公示信息->抽查检查信息

		gsxtFeedJson.setAicPubInfo(gsgsInfo);// 工商公示信息

		// 企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();

		Document qygsxx_list = Jsoup.parseBodyFragment(htmlMap.get(
				"qygsxx_list").toString());

		// 企业公示信息->企业年报
		List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> qygsxx_qynb_infos = (List<Map<String, Object>>) htmlMap
				.get("qygsxx_qynb_infos");
		if (null != qygsxx_qynb_infos && qygsxx_qynb_infos.size() > 0) {
			for (Map<String, Object> qygsxx_qynb_info : qygsxx_qynb_infos) {
				Document qygsxx_qynb_info_page = Jsoup
						.parseBodyFragment(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_page").toString());
				EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();// 企业年报
				String qqq = qygsxx_qynb_info.get("qygsxx_qynb_list_a_text")
						.toString();
				qygsQynbInfo.setSubmitYear(qqq);// 报送年度
				Elements wd_as = qygsxx_list.getElementsByTag("a");
				for (Element wd_a : wd_as) {
					if (wd_a.text().contains(qqq)) {
						qygsQynbInfo.setPubDateTime(wd_a.parent()
								.nextElementSibling().text());// 发布日期
						break;
					}
				}
				// qygsQynbInfo.setPubDateTime(qygsxx_qynb_info.get(
				// "qygsxx_qynb_list_pubdate").toString());// 发布日期
				// 企业公示信息->企业年报->企业基本信息
				EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
				// 企业公示信息->企业年报->股东及出资信息
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();// 股东及出资信息
				List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
				// 企业资产状况信息
				EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
				Elements qygsxx_qynb_info_page_ths = qygsxx_qynb_info_page
						.select("th");
				for (Element qygsxx_qynb_info_page_th : qygsxx_qynb_info_page_ths) {
					if ((null != qygsxx_qynb_info_page_th.nextElementSibling())
							&& (qygsxx_qynb_info_page_th.text().trim()
									.equals("注册号/统一社会信用代码")
									|| qygsxx_qynb_info_page_th.text().trim()
											.equals("注册号") || qygsxx_qynb_info_page_th
									.text().trim().equals("统一社会信用代码"))) {
						qygsQynbJbInfo.setNum(qygsxx_qynb_info_page_th
								.nextElementSibling().text());// 注册号/统一社会信用代码
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("企业名称")) {
						qygsQynbJbInfo.setName(qygsxx_qynb_info_page_th
								.nextElementSibling().text());// 企业名称
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("企业联系电话")) {
						qygsQynbJbInfo.setTel(qygsxx_qynb_info_page_th
								.nextElementSibling().text());// 企业联系电话
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("邮政编码")) {
						qygsQynbJbInfo.setZipCode(qygsxx_qynb_info_page_th
								.nextElementSibling().text());// 邮政编码
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("企业通信地址")) {
						qygsQynbJbInfo.setAddress(qygsxx_qynb_info_page_th
								.nextElementSibling().text());// 企业通信地址
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("电子邮箱")) {
						qygsQynbJbInfo.setEmail(qygsxx_qynb_info_page_th
								.nextElementSibling().text());// 电子邮箱
					}
					if ((null != qygsxx_qynb_info_page_th.nextElementSibling())
							&& (qygsxx_qynb_info_page_th.text().trim()
									.equals("企业是否有投资信息或购买其他公司股权")
									|| qygsxx_qynb_info_page_th.text().trim()
											.equals("企业是否有投资信息") || qygsxx_qynb_info_page_th
									.text().trim().equals("企业是否购买其他公司股权"))) {
						qygsQynbJbInfo
								.setHasInvestInfoOrPurchOtherCorpEqu(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 企业是否有投资信息或购买其他公司股权
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("企业经营状态")) {
						qygsQynbJbInfo
								.setOperatingStatus(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 企业经营状态
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("是否有网站或网点")) {
						qygsQynbJbInfo
								.setHasWebsiteOrStore(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 是否有网站或网点
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("从业人数")) {
						qygsQynbJbInfo.setEmpNum(qygsxx_qynb_info_page_th
								.nextElementSibling().text());// 从业人数
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("有限责任公司本年度是否发生股东股权转让")) {
						qygsQynbJbInfo
								.setIsStohrEquTransferred(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 有限责任公司本年度是否发生股东股权转让
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("资产总额")) {
						qygsQynbQyzczkInfo
								.setAssetAmount(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 资产总额
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("所有者权益合计")) {
						qygsQynbQyzczkInfo
								.setTotalEquity(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 所有者权益合计
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("营业总收入")) {
						qygsQynbQyzczkInfo
								.setSalesAmount(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 营业总收入
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("利润总额")) {
						qygsQynbQyzczkInfo
								.setProfitAmount(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 利润总额
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("营业总收入中主营业务收入")) {
						qygsQynbQyzczkInfo
								.setPriBusiIncomeInSalesAmount(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 营业总收入中主营业务收入
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("净利润")) {
						qygsQynbQyzczkInfo
								.setNetProfit(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 净利润
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("纳税总额")) {
						qygsQynbQyzczkInfo
								.setTaxesAmount(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 纳税总额
					}
					if (null != qygsxx_qynb_info_page_th.nextElementSibling()
							&& qygsxx_qynb_info_page_th.text().trim()
									.equals("负债总额")) {
						qygsQynbQyzczkInfo
								.setLiabilityAmount(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 负债总额
					}
					if (qygsxx_qynb_info_page_th.text().trim()
							.contains("股东及出资信息")) {
						Element parent = qygsxx_qynb_info_page_th.parent()
								.parent();
						Elements parent_trs = parent.select("tr");
						if (null != parent_trs && parent_trs.size() > 2) {
							for (int i = 2; i < parent_trs.size(); i++) {
								EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo = new EntpubAnnreportStohrinvestInfo();// 股东及出资信息
								qygsQynbGdjczInfo.setStockholder(parent_trs
										.get(i).select("td").get(0).text());// 股东（发起人）
								qygsQynbGdjczInfo.setSubAmount(parent_trs
										.get(i).select("td").get(1).text());// 认缴出资额（万元）
								qygsQynbGdjczInfo.setSubDateTime(parent_trs
										.get(i).select("td").get(2).text());// 认缴出资时间
								qygsQynbGdjczInfo.setSubMethod(parent_trs
										.get(i).select("td").get(3).text());// 认缴出资方式
								qygsQynbGdjczInfo.setPaidAmount(parent_trs
										.get(i).select("td").get(4).text());// 实缴出资额（万元）
								qygsQynbGdjczInfo.setPaidDateTime(parent_trs
										.get(i).select("td").get(5).text());// 实缴出资时间
								qygsQynbGdjczInfo.setPaidMethod(parent_trs
										.get(i).select("td").get(6).text());// 实缴出资方式
								qygsQynbGdjczInfos.add(qygsQynbGdjczInfo);
							}
						}
					}
					if (qygsxx_qynb_info_page_th.text().trim().contains("修改记录")) {
						Element parent = qygsxx_qynb_info_page_th.parent()
								.parent();
						Elements parent_trs = parent.select("tr");
						int parent_trs_size = parent_trs.size();
						if (parent_trs_size > 2) {
							for (int i = 2; i < parent_trs_size; i++) {
								Elements parent_trs_tds = parent_trs.get(i)
										.select("td");
								int parent_trs_tds_size = parent_trs_tds.size();
								EntpubAnnreportModifyInfo qygsQynbXgjlInfo = new EntpubAnnreportModifyInfo();// 修改记录
								if (parent_trs_tds_size > 3) {
									qygsQynbXgjlInfo.setItem(parent_trs_tds
											.get(1).text());
									qygsQynbXgjlInfo
											.setPreContent(parent_trs_tds
													.get(2).text());
									qygsQynbXgjlInfo
											.setPostContent(parent_trs_tds.get(
													3).text());
									qygsQynbXgjlInfo.setDateTime(parent_trs_tds
											.get(4).text());
								}
								qygsQynbXgjlInfos.add(qygsQynbXgjlInfo);
							}
						}
					}
				}
				qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);// 基本信息
				qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);// 企业公示信息->股东及出资信息
				qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);// 企业资产状况信息
				qygsQynbInfo.setChangeInfos(qygsQynbXgjlInfos);// 修改记录
				// 对外提供保证担保信息
				List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();
				Element wq = null;
				for (Element wd : qygsxx_qynb_info_page_ths) {
					if (wd.text().contains("对外提供保证担保信息")) {
						wq = wd.parent().parent();
						break;
					}
				}
				if (null != wq) {
					Elements wq_trs = wq.select("tr");
					if (null != wq_trs && wq_trs.size() > 2) {
						for (int i = 2; i < wq_trs.size(); i++) {
							// 对外提供保证担保信息
							EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
							qygsQynbDwtgbzdbInfo.setCreditor(wq_trs.get(i)
									.select("td").get(0).text());// 债权人
							qygsQynbDwtgbzdbInfo.setDebtor(wq_trs.get(i)
									.select("td").get(1).text());// 债务人
							qygsQynbDwtgbzdbInfo.setPriCredRightType(wq_trs
									.get(i).select("td").get(2).text());// 主债权种类
							qygsQynbDwtgbzdbInfo.setPriCredRightAmount(wq_trs
									.get(i).select("td").get(3).text());// 主债权数额
							qygsQynbDwtgbzdbInfo.setExeDebtDeadline(wq_trs
									.get(i).select("td").get(4).text());// 履行债务的期限
							qygsQynbDwtgbzdbInfo.setGuaranteePeriod(wq_trs
									.get(i).select("td").get(5).text());// 保证的期间
							qygsQynbDwtgbzdbInfo.setGuaranteeMethod(wq_trs
									.get(i).select("td").get(6).text());// 保证的方式
							qygsQynbDwtgbzdbInfo.setGuaranteeScope(wq_trs
									.get(i).select("td").get(7).text());// 保证担保的范围
							qygsQynbDwtgbzdbInfos.add(qygsQynbDwtgbzdbInfo);
						}
					}
				}
				qygsQynbInfo.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);// 对外提供保证担保信息
				// 网站或网店信息
				List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdxxInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
				Element wqq = null;
				for (Element wdd : qygsxx_qynb_info_page_ths) {
					if (wdd.text().contains("网站或网店信息")) {
						wqq = wdd.parent().parent();
						break;
					}
				}
				if (null != wqq) {
					Elements wqq_trs = wqq.select("tr");
					if (null != wqq_trs && wqq_trs.size() > 2) {
						for (int i = 2; i < wqq_trs.size(); i++) {
							// 网站或网店信息
							EntpubAnnreportWebsiteInfo qygsQynbWzhwdxxbInfo = new EntpubAnnreportWebsiteInfo();
							qygsQynbWzhwdxxbInfo.setType(wqq_trs.get(i)
									.select("td").get(0).text());// 类型
							qygsQynbWzhwdxxbInfo.setName(wqq_trs.get(i)
									.select("td").get(1).text());// 名称
							qygsQynbWzhwdxxbInfo.setWebsite(wqq_trs.get(i)
									.select("td").get(2).text());// 网址
							qygsQynbWzhwdxxInfos.add(qygsQynbWzhwdxxbInfo);
						}
					}
				}
				qygsQynbInfo.setWebsiteInfos(qygsQynbWzhwdxxInfos);// 网站或网店信息
				// 股权变更信息
				List<EntpubAnnreportEquchangeInfo> qygsQynbGqbgxxInfos = new ArrayList<EntpubAnnreportEquchangeInfo>();
				Element wp = null;
				for (Element wdd : qygsxx_qynb_info_page_ths) {
					if (wdd.text().contains("股权变更信息")) {
						wp = wdd.parent().parent();
						break;
					}
				}
				if (null != wp) {
					Elements wp_trs = wp.select("tr");
					if (null != wp_trs && wp_trs.size() > 2) {
						for (int i = 2; i < wp_trs.size(); i++) {
							// 股权变更信息
							EntpubAnnreportEquchangeInfo qygsQynbGqbgxxbInfo = new EntpubAnnreportEquchangeInfo();
							qygsQynbGqbgxxbInfo.setStockholder(wp_trs.get(i)
									.select("td").get(0).text());// 股东
							qygsQynbGqbgxxbInfo.setPreOwnershipRatio(wp_trs
									.get(i).select("td").get(1).text());// 变更前股权比例
							qygsQynbGqbgxxbInfo.setPostOwnershipRatio(wp_trs
									.get(i).select("td").get(2).text());// 变更后股权比例
							qygsQynbGqbgxxbInfo.setDateTime(wp_trs.get(i)
									.select("td").get(3).text());// 股权变更日期
							qygsQynbGqbgxxInfos.add(qygsQynbGqbgxxbInfo);
						}
					}
				}
				qygsQynbInfo.setEquChangeInfos(qygsQynbGqbgxxInfos);// 股权变更信息
				// 对外投资信息
				List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzxxInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
				Element wpp = null;
				for (Element wdd : qygsxx_qynb_info_page_ths) {
					if (wdd.text().contains("对外投资信息")) {
						wpp = wdd.parent().parent();
						break;
					}
				}
				if (null != wpp) {
					Elements wpp_trs = wpp.select("tr");
					if (null != wpp_trs && wpp_trs.size() > 2) {
						for (int i = 2; i < wpp_trs.size(); i++) {
							// 对外投资信息
							EntpubAnnreportExtinvestInfo qygsQynbDwtzxxbInfo = new EntpubAnnreportExtinvestInfo();
							qygsQynbDwtzxxbInfo.setEnterpriseName(wpp_trs
									.get(i).select("td").get(0).text());//
							qygsQynbDwtzxxbInfo.setRegNum(wpp_trs.get(i)
									.select("td").get(1).text());//
							qygsQynbDwtzxxInfos.add(qygsQynbDwtzxxbInfo);
						}
					}
				}
				qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzxxInfos);// 对外投资信息
				if (isDebug) {
					qygsQynbInfo.setHtml(qygsxx_qynb_info_page.select("div")
							.html());
				}
				qygsQynbInfos.add(qygsQynbInfo);// 企业公示信息->企业年报
			}
		}
		qygsInfo.setAnnReports(qygsQynbInfos);// 企业公示信息->企业年报

		// 企业公示信息->股东及出资信息
		EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
		// 企业公示信息->股东及出资信息
		List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
		if (null != qygsxx_list.getElementById("investor")) {
			Elements gdjczxx_trs = qygsxx_list.getElementById("investor")
					.select("tbody").get(0).select("tr");
			int gdjczxx_trs_size = gdjczxx_trs.size();
			if (gdjczxx_trs_size > 3) {
				for (int j = 3; j < gdjczxx_trs_size; j++) {
					int wd = gdjczxx_trs.get(j).select("td").size();
					// 企业公示信息->股东及出资信息
					EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new EntpubSStohrinvestInfo();
					qygsGdjczGdjczInfo.setStockholder(gdjczxx_trs.get(j)
							.select("td").get(0).text());// 股东
					qygsGdjczGdjczInfo.setSubAmount(gdjczxx_trs.get(j)
							.select("td").get(1).text());// 认缴额（万元）
					qygsGdjczGdjczInfo.setPaidAmount(gdjczxx_trs.get(j)
							.select("td").get(2).text());// 实缴额（万元）
					List<EntpubSStohrinvestInfo.Detail> subDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
					EntpubSStohrinvestInfo.Detail rjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
					rjDetail.amount = gdjczxx_trs.get(j).select("td").get(3)
							.text();// 出资额（万元）
					rjDetail.method = gdjczxx_trs.get(j).select("td").get(4)
							.text();// 出资方式
					rjDetail.dateTime = gdjczxx_trs.get(j).select("td").get(5)
							.text();// 出资日期
					subDetails.add(rjDetail);
					qygsGdjczGdjczInfo.setSubDetails(subDetails);
					if (wd > 8) {
						List<EntpubSStohrinvestInfo.Detail> paidDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
						EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
						sjDetail.amount = gdjczxx_trs.get(j).select("td")
								.get(6).text();// 出资额（万元）
						sjDetail.method = gdjczxx_trs.get(j).select("td")
								.get(7).text();// 出资方式
						sjDetail.amount = gdjczxx_trs.get(j).select("td")
								.get(8).text();// 出资日期
						paidDetails.add(sjDetail);
						qygsGdjczGdjczInfo.setPaidDetails(paidDetails);
					}
					qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
				}
			}
			qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息
		}

		Elements investor = qygsxx_list.getElementsByAttributeValue("rel",
				"layout-02_04");
		// 变更信息
		List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
		if (null != investor && investor.size() > 1) {
			Element wc = investor.get(0).select("table").get(1);
			if (null != wc) {
				Elements wc_trs = wc.select("tbody").get(0).select("tr");
				if (null != wc_trs && wc_trs.size() > 2) {
					for (int i = 2; i < wc_trs.size(); i++) {
						EntpubStohrinvestChangeInfo gygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
						gygsGdjczBgInfo.setItem(wc_trs.get(i).select("td")
								.get(1).text());// 变更事项
						gygsGdjczBgInfo.setDateTime(wc_trs.get(i).select("td")
								.get(2).text());// 变更时间
						gygsGdjczBgInfo.setPreContent(wc_trs.get(i)
								.select("td").get(3).text());// 变更前
						gygsGdjczBgInfo.setPostContent(wc_trs.get(i)
								.select("td").get(4).text());// 变更后
						qygsGdjczBgInfos.add(gygsGdjczBgInfo);
					}
				}
			}
		}
		qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);// 变更信息
		qygsInfo.setStohrInvestInfo(qygsGdjczInfo);// 企业公示信息->股东及出资信息

		// 企业公示信息->股权变更信息
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		Element gudongguquan_et = qygsxx_list
				.getElementById("othStocktransTable");
		// 企业公示信息->股权变更信息列表
		List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
		if (null != gudongguquan_et) {
			Elements gudongguquan_trs = gudongguquan_et.select("tbody").get(0)
					.select("tr");
			if (null != gudongguquan_trs && gudongguquan_trs.size() > 3) {
				for (int i = 2; i < gudongguquan_trs.size() - 1; i++) {
					// 企业公示信息->股权变更信息
					EntpubEEquchangeInfo gygsGqbgGqbgInfo = new EntpubEEquchangeInfo();
					gygsGqbgGqbgInfo.setStockholder(gudongguquan_trs.get(i)
							.select("td").get(1).text());// 股东
					gygsGqbgGqbgInfo.setPreOwnershipRatio(gudongguquan_trs
							.get(i).select("td").get(2).text());// 变更前股权比例
					gygsGqbgGqbgInfo.setPostOwnershipRatio(gudongguquan_trs
							.get(i).select("td").get(3).text());// 变更后股权比例
					gygsGqbgGqbgInfo.setDateTime(gudongguquan_trs.get(i)
							.select("td").get(4).text());// 股权变更日期
					qygsGqbgGqbgInfos.add(gygsGqbgGqbgInfo);
				}
			}
			if (isDebug) {
				qygsGqbgInfo.setHtml(gudongguquan_et.html());
			}
		}
		qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);// 企业公示信息->股权变更信息
		qygsInfo.setEquChangeInfo(qygsGqbgInfo);// 企业公示信息->股权变更信息

		// 企业公示信息->行政许可信息
		EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
		Elements xingzhengxuke_ets = qygsxx_list.getElementsByAttributeValue(
				"rel", "layout-02_02");
		// 企业公示信息->行政许可信息列表
		List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
		if (null != xingzhengxuke_ets) {
			Elements xingzhengxuke_trs = xingzhengxuke_ets.get(0)
					.select("tbody").get(0).select("tr");
			if (null != xingzhengxuke_trs && xingzhengxuke_trs.size() > 2) {
				for (int i = 2; i < xingzhengxuke_trs.size(); i++) {
					// 企业公示信息->行政许可信息列表
					EntpubAAdmlicInfo qygsXzxkXzxkInfo = new EntpubAAdmlicInfo();
					qygsXzxkXzxkInfo.setLicenceNum(xingzhengxuke_trs.get(i)
							.select("td").get(1).text());// 许可文件编号
					qygsXzxkXzxkInfo.setLicenceName(xingzhengxuke_trs.get(i)
							.select("td").get(2).text());// 许可文件名称
					qygsXzxkXzxkInfo.setStartDateTime(xingzhengxuke_trs.get(i)
							.select("td").get(3).text());// 有效期自
					qygsXzxkXzxkInfo.setEndDateTime(xingzhengxuke_trs.get(i)
							.select("td").get(4).text());// 有效期至
					qygsXzxkXzxkInfo.setDeciAuthority(xingzhengxuke_trs.get(i)
							.select("td").get(5).text());// 许可机关
					qygsXzxkXzxkInfo.setContent(xingzhengxuke_trs.get(i)
							.select("td").get(6).text());// 许可内容
					qygsXzxkXzxkInfo.setStatus(xingzhengxuke_trs.get(i)
							.select("td").get(7).text());// 状态
					qygsXzxkXzxkInfo.setDetail(xingzhengxuke_trs.get(i)
							.select("td").get(8).text());// 详情
					qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
				}
			}
			if (isDebug) {
				qygsXzxkInfo.setHtml(xingzhengxuke_ets.html());
			}
		}
		qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
		qygsInfo.setAdmLicInfo(qygsXzxkInfo);// 企业公示信息->行政许可信息

		// 企业公示信息->知识产权出质登记信息
		EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
		Elements zhishichanquan_ets = qygsxx_list.getElementsByAttributeValue(
				"rel", "layout-02_03");
		// 企业公示信息->知识产权出质登记信息列表
		List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
		if (null != zhishichanquan_ets) {
			Elements zhishichanquan_trs = zhishichanquan_ets.get(0)
					.select("tbody").get(0).select("tr");
			if (null != zhishichanquan_trs && zhishichanquan_trs.size() > 2) {
				for (int i = 2; i < zhishichanquan_trs.size(); i++) {
					// 企业公示信息->知识产权出质登记信息
					EntpubIIntellectualproregInfo qygsZscqczdjZscqczdjInfo = new EntpubIIntellectualproregInfo();
					qygsZscqczdjZscqczdjInfo.setRegNum(zhishichanquan_trs
							.get(i).select("td").get(1).text());// 注册号
					qygsZscqczdjZscqczdjInfo.setName(zhishichanquan_trs.get(i)
							.select("td").get(2).text());// 名称
					qygsZscqczdjZscqczdjInfo.setType(zhishichanquan_trs.get(i)
							.select("td").get(3).text());// 种类
					qygsZscqczdjZscqczdjInfo
							.setMortgagorName(zhishichanquan_trs.get(i)
									.select("td").get(4).text());// 出质人名称
					qygsZscqczdjZscqczdjInfo
							.setMortgageeName(zhishichanquan_trs.get(i)
									.select("td").get(5).text());// 质权人名称
					qygsZscqczdjZscqczdjInfo
							.setPledgeRegDeadline(zhishichanquan_trs.get(i)
									.select("td").get(6).text());// 质权登记期限
					qygsZscqczdjZscqczdjInfo.setStatus(zhishichanquan_trs
							.get(i).select("td").get(7).text());// 状态
					qygsZscqczdjZscqczdjInfo.setChangeSitu(zhishichanquan_trs
							.get(i).select("td").get(8).text());// 变化情况
					qygsZscqczdjZscqczdjInfos.add(qygsZscqczdjZscqczdjInfo);
				}
				if (isDebug) {
					qygsZscqczdjInfo.setHtml(zhishichanquan_ets.html());
				}
			}
		}
		qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);// 企业公示信息->知识产权出质登记信息
		qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);// 企业公示信息->知识产权出质登记信息

		// 企业公示信息->行政处罚信息
		EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
		Element xingzhengchufa_et1 = qygsxx_list.getElementById("ePunishTable");
		// 企业公示信息->行政处罚信息列表
		List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
		if (null != xingzhengchufa_et1) {
			Elements xingzhengchufa_trs = xingzhengchufa_et1.select("tbody")
					.get(0).select("tr");
			if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 3) {
				for (int j = 2; j < xingzhengchufa_trs.size() - 1; j++) {
					// 企业公示信息->行政处罚信息
					EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();
					qygsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs.get(j)
							.select("td").get(1).text());// 行政处罚决定书文号
					qygsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs
							.get(j).select("td").get(2).text());// 违法行为类型
					qygsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs.get(j)
							.select("td").get(3).text());// 行政处罚内容
					qygsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs.get(j)
							.select("td").get(4).text());// 作出行政处罚决定机关名称
					qygsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs.get(j)
							.select("td").get(5).text());// 作出行政处罚决定日期
					qygsXzcfXzcfInfo.setNote(xingzhengchufa_trs.get(j)
							.select("td").get(6).text());// 备注
					qygsXzcfXzcfInfos.add(qygsXzcfXzcfInfo);
				}
			}
			if (isDebug) {
				qygsXzcfInfo.setHtml(xingzhengchufa_et1.html());
			}
		}
		qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);// 企业公示信息->行政处罚信息
		qygsInfo.setAdmPunishInfo(qygsXzcfInfo);// 企业公示信息->行政处罚信息

		// 企业公示信息
		gsxtFeedJson.setEntPubInfo(qygsInfo);

		// 其他部门公示信息
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();
		if (null != htmlMap.get("qtbmgsxx")
				&& !StringUtils
						.isEmpty(String.valueOf(htmlMap.get("qtbmgsxx")))) {
			Document qtbmgsxx_page = Jsoup.parseBodyFragment(htmlMap.get(
					"qtbmgsxx").toString());

			// 其他部门公示信息->行政许可信息
			OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
			Elements xingzhengxuke_et = qtbmgsxx_page
					.getElementsByAttributeValue("rel", "layout-03_01");
			// 其他部门公示信息->行政许可信息列表
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			if (null != xingzhengxuke_et) {
				Elements xingzhengxuke1_trs = xingzhengxuke_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengxuke1_trs && xingzhengxuke1_trs.size() > 2) {
					for (int i = 2; i < xingzhengxuke1_trs.size(); i++) {
						// 其他部门公示信息->行政许可信息列表
						OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo = new OthrdeptpubAAdmlicInfo();
						qtbmgsXzxkXzxkInfo.setLicenceNum(xingzhengxuke1_trs
								.get(i).select("td").get(1).text());// 许可文件编号
						qtbmgsXzxkXzxkInfo.setLicenceName(xingzhengxuke1_trs
								.get(i).select("td").get(2).text());// 许可文件名称
						qtbmgsXzxkXzxkInfo.setStartDateTime(xingzhengxuke1_trs
								.get(i).select("td").get(3).text());// 有效期自
						qtbmgsXzxkXzxkInfo.setEndDateTime(xingzhengxuke1_trs
								.get(i).select("td").get(4).text());// 有效期至
						qtbmgsXzxkXzxkInfo.setContent(xingzhengxuke1_trs.get(i)
								.select("td").get(5).text());// 许可内容
						qtbmgsXzxkXzxkInfo.setDeciAuthority(xingzhengxuke1_trs
								.get(i).select("td").get(6).text());// 许可机关
						qtbmgsXzxkXzxkInfo.setStatus(xingzhengxuke1_trs.get(i)
								.select("td").get(7).text());// 状态
						qtbmgsXzxkXzxkInfo.setDetail(xingzhengxuke1_trs.get(i)
								.select("td").get(8).text());// 详情
						qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
					}
				}
				if (isDebug && null != xingzhengxuke_et) {
					qtbmgsXzxkInfo.setHtml(xingzhengxuke_et.html());
				}
			}
			qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);// 其他部门公示信息->行政许可信息列表
			qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);// 其他部门公示信息->行政许可信息

			// 其他部门公示信息->行政处罚信息
			OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
			Element xingzhengchufa_et = qtbmgsxx_page
					.getElementById("oPunishTable");
			// 其他部门公示信息->行政处罚信息列表
			List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();
			if (null != xingzhengchufa_et) {
				Elements xingzhengchufa_trs = xingzhengchufa_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 2) {
					for (int i = 2; i < xingzhengchufa_trs.size(); i++) {
						OthrdeptpubAAdmpunishInfo qtbmgsXzcfXzcfInfo = new OthrdeptpubAAdmpunishInfo();
						qtbmgsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs
								.get(i).select("td").get(1).text());// 行政处罚决定书文号
						qtbmgsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs
								.get(i).select("td").get(2).text());// 违法行为类型
						qtbmgsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs
								.get(i).select("td").get(3).text());// 行政处罚内容
						qtbmgsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs
								.get(i).select("td").get(4).text());// 作出行政处罚决定机关名称
						qtbmgsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs
								.get(i).select("td").get(5).text());// 作出行政处罚决定日期
						qtbmgsXzcfXzcfInfo.setDetail(xingzhengchufa_trs.get(i)
								.select("td").get(6).text());// 详情
						qtbmgsXzcfXzcfInfo.setNote("");// 备注（暂无）
						qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
					}
				}
				if (isDebug) {
					qtbmgsXzcfInfo.setHtml(xingzhengchufa_et.html());
				}
			}
			qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);// 其他部门公示信息->行政处罚信息列表
			qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);// 其他部门公示信息->行政处罚信息
		}

		// 其他部门公示信息
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);

		if (null != htmlMap.get("sfxzgsxx_list")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("sfxzgsxx_list")))) {

			// 司法协助公示信息
			JudasspubInfo sfxzgsInfo = new JudasspubInfo();

			Document sfxzgsxx_list_page = Jsoup.parseBodyFragment(htmlMap.get(
					"sfxzgsxx_list").toString());

			Elements guquandongjie_ets = sfxzgsxx_list_page
					.getElementsByAttributeValue("rel", "layout-06_01");
			if (null != guquandongjie_ets && !guquandongjie_ets.isEmpty()
					&& guquandongjie_ets.size() > 0) {
				// 司法协助公示信息->股权冻结信息
				JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
				// 司法协助公示信息->股权冻结信息列表
				List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
				Elements guquandongjie_trs = guquandongjie_ets.select("tbody")
						.get(0).select("tr");
				if (null != guquandongjie_trs && guquandongjie_trs.size() > 2) {
					for (int i = 2; i < guquandongjie_trs.size(); i++) {
						// 司法协助公示信息->股权冻结信息
						JudasspubEEqufreezeInfo sfxzgsGqdjGqdjInfo = new JudasspubEEqufreezeInfo();
						sfxzgsGqdjGqdjInfo.setExecutedPerson(guquandongjie_trs
								.get(i).select("td").get(1).text());// 被执行人
						sfxzgsGqdjGqdjInfo.setEquAmount(guquandongjie_trs
								.get(i).select("td").get(2).text());// 股权数额
						sfxzgsGqdjGqdjInfo.setExeCourt(guquandongjie_trs.get(i)
								.select("td").get(3).text());// 执行法院
						sfxzgsGqdjGqdjInfo
								.setAssistPubNoticeNum(guquandongjie_trs.get(i)
										.select("td").get(4).text());// 协助公示通知书文号
						sfxzgsGqdjGqdjInfo.setStatus(guquandongjie_trs.get(i)
								.select("td").get(5).text());// 状态
						sfxzgsGqdjGqdjInfo.setDetail(guquandongjie_trs.get(i)
								.select("td").get(6).text());// 详情
						sfxzgsGqdjGqdjInfos.add(sfxzgsGqdjGqdjInfo);
					}
				}
				if (isDebug) {
					sfxzgsGqdjInfo.setHtml(guquandongjie_ets.html());
				}
				sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);// 司法协助公示信息->股权冻结信息列表
				sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);// 司法协助公示信息->股权冻结信息
			}

			Elements guquanbiangeng_ets = sfxzgsxx_list_page
					.getElementsByAttributeValue("rel", "layout-06_02");
			if (null != guquanbiangeng_ets && !guquanbiangeng_ets.isEmpty()
					&& guquanbiangeng_ets.size() > 0) {
				// 司法协助公示信息->股东变更信息
				JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
				// 司法协助公示信息->股东变更信息列表
				List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
				Elements guquanbiangeng_trs = guquanbiangeng_ets
						.select("tbody").get(0).select("tr");
				if (null != guquanbiangeng_trs && guquanbiangeng_trs.size() > 2) {
					for (int i = 2; i < guquanbiangeng_trs.size(); i++) {
						// 司法协助公示信息->股东变更信息
						JudasspubSStohrchangeInfo sfxzgsGdbgGdbgInfo = new JudasspubSStohrchangeInfo();
						sfxzgsGdbgGdbgInfo.setExecutedPerson(guquanbiangeng_trs
								.get(i).select("td").get(1).text());// 被执行人
						sfxzgsGdbgGdbgInfo.setEquAmount(guquanbiangeng_trs
								.get(i).select("td").get(2).text());// 股权数额
						sfxzgsGdbgGdbgInfo.setAssignee(guquanbiangeng_trs
								.get(i).select("td").get(3).text());// 受让人
						sfxzgsGdbgGdbgInfo.setExeCourt(guquanbiangeng_trs
								.get(i).select("td").get(4).text());// 执行法院
						sfxzgsGdbgGdbgInfo.setDetail(guquanbiangeng_trs.get(i)
								.select("td").get(5).text());// 详情
						sfxzgsGdbgGdbgInfos.add(sfxzgsGdbgGdbgInfo);
					}
				}
				if (isDebug) {
					sfxzgsGdbgInfo.setHtml(guquanbiangeng_ets.html());
				}
				sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);// 司法协助公示信息->股东变更信息列表
				sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);// 司法协助公示信息->股东变更信息列表
			}

			// 司法协助公示信息
			gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);

		}

		return gsxtFeedJson;

	}

}

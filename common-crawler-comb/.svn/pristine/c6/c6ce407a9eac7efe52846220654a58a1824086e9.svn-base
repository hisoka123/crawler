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
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo.Detail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtGansuParser extends AbstractGsxtParser {

	private static final Logger LOOGER = LoggerFactory
			.getLogger(GsxtGansuParser.class);

	public AicFeedJson getGansuResultObj(String html, boolean isDebug) {

		LOOGER.info("The method of GsxtGansuParser.getGansuResultObj is begin !");

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
		Elements baseinfo = gsgsxx.getElementById("jibenxinxi").select("table")
				.get(0).select("tbody");
		Elements ths = baseinfo.select("tr").select("th");
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
		for (int i = 1; i < ths.size(); i++) {
			if (ths.get(i).text().trim().contains("统一社会信用代码")
					|| ths.get(i).text().trim().contains("注册号")) {
				gsgsDjJbInfo.setNum(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 注册号或信用代码
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("名称")) {
				gsgsDjJbInfo.setName(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 名称
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("类型")) {
				gsgsDjJbInfo.setType(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 类型
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("负责人")
					|| ths.get(i).text().trim().equalsIgnoreCase("法定代表人")
					|| ths.get(i).text().trim().contains("经营者")) {
				gsgsDjJbInfo.setLegalRepr(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 法定代表人/经营者
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("注册资本")) {
				gsgsDjJbInfo.setRegCapital(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 注册资本
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("成立日期")) {
				gsgsDjJbInfo.setRegDateTime(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 成立日期
			}
			if (ths.get(i).text().trim().contains("营业场所")
					|| ths.get(i).text().trim().contains("住所")) {
				gsgsDjJbInfo.setAddress(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 经营场所/住所
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("营业期限自")) {
				gsgsDjJbInfo.setStartDateTime(baseinfo.select("tr")
						.select("td").get(i - 1).text());// 营业期限自（即营业开始日期）
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("营业期限至")) {
				gsgsDjJbInfo.setEndDateTime(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 营业期限至（即营业结束日期）
			}
			if (ths.get(i).text().trim().contains("经营范围")) {
				gsgsDjJbInfo.setBusinessScope(baseinfo.select("tr")
						.select("td").get(i - 1).text());// 经营范围
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("登记机关")) {
				gsgsDjJbInfo.setRegAuthority(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 登记机关
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("核准日期")) {
				gsgsDjJbInfo.setApprovalDateTime(baseinfo.select("tr")
						.select("td").get(i - 1).text());// 核准日期
			}
			if (ths.get(i).text().trim().contains("登记状态")) {
				gsgsDjJbInfo.setRegStatus(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 登记状态
			}
			if (ths.get(i).text().trim().contains("吊销日期")) {
				gsgsDjJbInfo.setRevokeDate(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 吊销日期
			}
		}
		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);// 基本信息
		// 工商公示信息->登记信息->股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
		Element touziren = gsgsxx.getElementById("invTab");
		if (null != touziren) {
			Elements touziren_tbodys = touziren.select("tbody");
			if (null != touziren_tbodys && touziren_tbodys.size() > 1) {
				Element touziren_tbody = touziren_tbodys.get(1);
				Elements touziren_trs = touziren_tbody.select("tr");
				int touziren_nums = touziren_trs.size();
				if (touziren_nums > 1) {
					for (int i = 1; i < touziren_nums; i++) {
						// 股东信息
						AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();
						Elements touziren_tds = touziren_trs.get(i)
								.select("td");
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
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = (List<Map<String, Object>>) htmlMap
				.get("gsgsxx_djxx_tzrxx_xqs");
		List<AicpubRegStohrStohrinvestInfo> gsgsDjGdGdjczInfos = new ArrayList<AicpubRegStohrStohrinvestInfo>();
		if (null != gsgsxx_djxx_tzrxx_xqs) {
			int gsgsxx_djxx_tzrxx_xqs_size = gsgsxx_djxx_tzrxx_xqs.size();
			if (gsgsxx_djxx_tzrxx_xqs_size > 0) {
				for (Map<String, Object> gsgsxx_djxx_tzrxx_xq : gsgsxx_djxx_tzrxx_xqs) {
					Document gsgsxx_djxx_tzrxx_xq_page = Jsoup
							.parseBodyFragment(gsgsxx_djxx_tzrxx_xq.get(
									"gsgsxx_djxx_tzrxx_xq").toString());
					Elements gsgsxx_djxx_tzrxx_xq_trs = gsgsxx_djxx_tzrxx_xq_page
							.getElementById("sifapanding").select("table")
							.get(0).select("tbody").select("tr");
					int gsgsxx_djxx_tzrxx_xq_trs_size = gsgsxx_djxx_tzrxx_xq_trs
							.size();
					if (gsgsxx_djxx_tzrxx_xq_trs_size > 3) {
						AicpubRegStohrStohrinvestInfo gsgsDjGdGdjczInfo = new AicpubRegStohrStohrinvestInfo();
						List<AmountDetail> subAmountDetails = new ArrayList<AmountDetail>();
						List<AmountDetail> paidAmountDetails = new ArrayList<AmountDetail>();
						gsgsDjGdGdjczInfo
								.setStockholder(gsgsxx_djxx_tzrxx_xq_trs.get(3)
										.select("td").get(0).text());// 股东
						gsgsDjGdGdjczInfo.setSubAmount(gsgsxx_djxx_tzrxx_xq_trs
								.get(3).select("td").get(1).text());// 认缴额（万元）
						gsgsDjGdGdjczInfo
								.setPaidAmount(gsgsxx_djxx_tzrxx_xq_trs.get(3)
										.select("td").get(2).text());// 实缴额（万元）
						for (int i = 3; i < gsgsxx_djxx_tzrxx_xq_trs_size; i++) {
							AicpubRegStohrStohrinvestInfo.AmountDetail subamountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
							AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
							subamountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(3).text();// 出资方式
							subamountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(4).text();// 出资额（万元）
							subamountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(5).text();// 出资日期
							paidAmountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(6).text();// 出资方式
							paidAmountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(7).text();// 出资额（万元）
							paidAmountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(8).text();// 出资日期
							subAmountDetails.add(subamountDetail);
							paidAmountDetails.add(paidAmountDetail);
						}
						gsgsDjGdGdjczInfo.setSubAmountDetails(subAmountDetails);
						gsgsDjGdGdjczInfo
								.setPaidAmountDetails(paidAmountDetails);
						gsgsDjGdGdjczInfos.add(gsgsDjGdGdjczInfo);
					}
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
		Element biangeng = gsgsxx.getElementById("changTab").select("tbody")
				.get(1);
		if (null != biangeng) {
			Elements biangeng_trs = biangeng.select("tr");
			int biangeng_trs_size = biangeng_trs.size();
			if (biangeng_trs_size > 1) {
				for (int i = 1; i < biangeng_trs_size; i++) {
					AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
					gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td")
							.get(0).text());// 变更事项
					Elements biangeng_one = biangeng_trs.get(i).select("td")
							.get(1).getElementsByTag("span");
					if (null != biangeng_one && biangeng_one.size() > 1) {
						gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
								.select("td").get(1).select("span").get(1)
								.text());// 变更前内容
					} else {
						gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
								.select("td").get(1).text());// 变更前内容
					}
					Elements biangeng_two = biangeng_trs.get(i).select("td")
							.get(2).getElementsByTag("span");
					if (null != biangeng_two && biangeng_two.size() > 1) {
						gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
								.select("td").get(2).select("span").get(1)
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
		Element beian_zyryxx = gsgsxx.getElementById("perTab");
		// 工商公示信息->备案信息->主要人员信息
		List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
		if (null != beian_zyryxx) {
			Elements beian_zyryxx_trs = beian_zyryxx.select("tr");
			int beian_zyryxx_size = beian_zyryxx_trs.size();
			if (beian_zyryxx_size > 2) {
				for (int i = 2; i < beian_zyryxx_size; i++) {
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
		Element beian_fzjgxx = gsgsxx.getElementById("branTab");
		if (null != beian_fzjgxx) {
			Elements beian_fzjgxx_trs = beian_fzjgxx.select("tr");
			int beian_fzjgxx_size = beian_fzjgxx_trs.size();
			if (beian_fzjgxx_size > 2) {
				for (int i = 2; i < beian_fzjgxx_size; i++) {
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
		Element beian_qingsuanrenyuan = gsgsxx.getElementById("t32");
		if (null != beian_qingsuanrenyuan) {
			gsgsBaQsInfo.setLeader(beian_qingsuanrenyuan.select("tbody").get(0)
					.select("tr").get(1).select("td").get(0).text());// 清算组负责人
			// TODO
			gsgsBaQsInfo.setMembers(null);// 清算组成员
			if (isDebug) {
				gsgsBaQsInfo.setHtml(beian_qingsuanrenyuan.html());
			}
		}
		gsgsBaInfo.setClearInfo(gsgsBaQsInfo);// 清算信息
		gsgsInfo.setArchiveInfo(gsgsBaInfo);// 工商公示信息->备案信息

		Element dongchandiya_et = gsgsxx.getElementById("moveTab");
		// 工商公示信息->动产抵押登记信息
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		// 工商公示信息->动产抵押登记信息列表
		List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
		if (null != dongchandiya_et) {
			Elements dongchandiya_trs = dongchandiya_et.select("tbody").get(0)
					.select("tr");
			if (null != dongchandiya_trs && dongchandiya_trs.size() > 2) {
				for (int i = 2; i < dongchandiya_trs.size(); i++) {
					AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
					gsgsDcdydjDcdydjInfo.setRegNum(dongchandiya_trs.get(i)
							.select("td").get(1).text());// 登记编号
					gsgsDcdydjDcdydjInfo.setRegDateTime(dongchandiya_trs.get(i)
							.select("td").get(2).text());// 登记日期
					gsgsDcdydjDcdydjInfo.setRegAuthority(dongchandiya_trs
							.get(i).select("td").get(3).text());// 登记机关
					gsgsDcdydjDcdydjInfo
							.setGuaranteedDebtAmount(dongchandiya_trs.get(i)
									.select("td").get(4).text());// 被担保债权数额
					gsgsDcdydjDcdydjInfo.setStatus(dongchandiya_trs.get(i)
							.select("td").get(5).text());// 状态
					gsgsDcdydjDcdydjInfo.setPubDateTime("");// 公示时间（该字段比较特殊，甘肅工商网暂时没有该字段。）
					gsgsDcdydjDcdydjInfo.setDetail(dongchandiya_trs.get(i)
							.select("td").get(6).text());// 详情
					gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
				}
			}
			if (isDebug) {
				gsgsDcdydjInfo.setHtml(dongchandiya_et.html());
			}
		}
		gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);// 动产抵押登记信息
		gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息

		Element guquanchuzhi_et = gsgsxx.getElementById("stockTab");
		// 工商公示信息->股权出资登记信息
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		// 工商公示信息->股权出资登记信息列表
		List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
		if (null != guquanchuzhi_et) {
			Elements guquanchuzhi_trs = guquanchuzhi_et.select("tbody").get(0)
					.select("tr");
			if (null != guquanchuzhi_trs && guquanchuzhi_trs.size() > 2) {
				for (int i = 2; i < guquanchuzhi_trs.size(); i++) {
					// 工商公示信息->股权出资登记信息
					AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
					gsgsGqczdjGqczdjInfo.setRegNum(guquanchuzhi_trs.get(i)
							.select("td").get(1).text());// 登记编号
					gsgsGqczdjGqczdjInfo.setMortgagorName(guquanchuzhi_trs
							.get(i).select("td").get(1).text());// 出质人
					gsgsGqczdjGqczdjInfo.setMortgagorIdNum(guquanchuzhi_trs
							.get(i).select("td").get(1).text());// 证照/证件号码（出质人）
					gsgsGqczdjGqczdjInfo.setMortgAmount(guquanchuzhi_trs.get(i)
							.select("td").get(1).text());// 出质股权数额
					gsgsGqczdjGqczdjInfo.setMortgageeName(guquanchuzhi_trs
							.get(i).select("td").get(1).text());// 质权人
					gsgsGqczdjGqczdjInfo.setMortgageeIdNum(guquanchuzhi_trs
							.get(i).select("td").get(1).text());// 证照/证件号码
					gsgsGqczdjGqczdjInfo.setRegDateTime(guquanchuzhi_trs.get(i)
							.select("td").get(1).text());// 股权出质设立登记日期
					gsgsGqczdjGqczdjInfo.setStatus(guquanchuzhi_trs.get(i)
							.select("td").get(1).text());// 状态
					gsgsGqczdjGqczdjInfo.setPubDate("");// 公示时间（该字段比较特殊，甘肃工商网暂时没有该字段。）
					gsgsGqczdjGqczdjInfo.setChangeSitu(guquanchuzhi_trs.get(i)
							.select("td").get(1).text());// 变化情况
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
		Element xingzhengchufa = gsgsxx.getElementById("penTab");
		if (null != xingzhengchufa) {
			Elements xingzhengchufa_trs = xingzhengchufa.select("tbody").get(0)
					.select("tr");
			int xingzhengchufa_trs_size = xingzhengchufa_trs.size();
			if (xingzhengchufa_trs_size > 2) {
				for (int i = 2; i < xingzhengchufa_trs_size; i++) {
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
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs = (List<Map<String, Object>>) htmlMap
				.get("gsgsxx_xzcfxx_xzcfxx_xqs");
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
		Element yichangminglu = gsgsxx.getElementById("excpTab");
		if (null != yichangminglu) {
			Elements yichangminglu_trs = yichangminglu.select("tbody").get(0)
					.select("tr");
			int yichangminglu_trs_size = yichangminglu_trs.size();
			if (yichangminglu_trs_size > 2) {
				for (int i = 2; i < yichangminglu_trs_size; i++) {
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
			gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);// 经营异常信息列表
			if (isDebug) {
				gsgsJyycInfo.setHtml(yichangminglu.html());
			}
		}
		gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);// 工商公示信息->经营异常信息

		Element heimingdan_et = gsgsxx.getElementById("illegalTab");
		// 工商公示信息->严重违法信息
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		// 工商公示信息->严重违法信息列表
		List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
		if (null != heimingdan_et) {
			Elements heimingdan_trs = heimingdan_et.select("tbody").get(0)
					.select("tr");
			if (null != heimingdan_trs && heimingdan_trs.size() > 2) {
				for (int i = 2; i < heimingdan_trs.size(); i++) {
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
		// 工商公示信息->抽查检查信息列表
		List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
		Element chouchaxinxi = gsgsxx.getElementById("tableChoucha");
		if (null != chouchaxinxi) {
			Elements chouchaxinxi_trs = chouchaxinxi.select("tr");
			int chouchaxinxi_trs_size = chouchaxinxi_trs.size();
			if (chouchaxinxi_trs_size > 1) {
				for (int i = 1; i < chouchaxinxi_trs_size; i++) {
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
			gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);// 抽查检查信息
			if (isDebug) {
				gsgsCcjcInfo.setHtml(chouchaxinxi.html());
			}
		}
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
				String wcc = qygsxx_qynb_info.get("qygsxx_qynb_list_a_text")
						.toString();
				qygsQynbInfo.setSubmitYear(wcc);// 报送年度
				Elements qqq_as = qygsxx_list.getElementsByTag("a");
				for (Element qqq_a : qqq_as) {
					if (qqq_a.text().contains(wcc)) {
						qygsQynbInfo.setPubDateTime(qqq_a.parent()
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
									.contains("注册号") || qygsxx_qynb_info_page_th
									.text().trim().contains("统一社会信用代码"))) {
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
					if ((null != qygsxx_qynb_info_page_th.nextElementSibling())
							&& (qygsxx_qynb_info_page_th.text().trim()
									.equals("是否有网站或网点") || qygsxx_qynb_info_page_th
									.text().trim().equals("是否有网站或网店"))) {
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
							&& qygsxx_qynb_info_page_th.text().contains("隶属关系")) {
						qygsQynbJbInfo.setAffiliation(qygsxx_qynb_info_page_th
								.nextElementSibling().text());// 隶属关系
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
							&& (qygsxx_qynb_info_page_th.text().trim()
									.equals("营业总收入中主营业务收入") || qygsxx_qynb_info_page_th
									.text().trim().equals("主营业务收入"))) {
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
						int parent_trs_size = parent_trs.size();
						if (parent_trs_size > 2) {
							for (int i = 2; i < parent_trs_size; i++) {
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
				Element dwtgdb_et = qygsxx_qynb_info_page
						.getElementById("guaTab");
				// 对外提供保证担保信息
				List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();
				if (null != dwtgdb_et) {
					Elements dwtgdb_trs = dwtgdb_et.select("tbody").get(0)
							.select("tr");
					if (null != dwtgdb_trs && dwtgdb_trs.size() > 2) {
						for (int i = 2; i < dwtgdb_trs.size(); i++) {
							// 对外提供保证担保信息
							EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
							qygsQynbDwtgbzdbInfo.setCreditor(dwtgdb_trs.get(i)
									.select("td").get(0).text());// 债权人
							qygsQynbDwtgbzdbInfo.setDebtor(dwtgdb_trs.get(i)
									.select("td").get(1).text());// 债务人
							qygsQynbDwtgbzdbInfo.setPriCredRightType(dwtgdb_trs
									.get(i).select("td").get(2).text());// 主债权种类
							qygsQynbDwtgbzdbInfo
									.setPriCredRightAmount(dwtgdb_trs.get(i)
											.select("td").get(3).text());// 主债权数额
							qygsQynbDwtgbzdbInfo.setExeDebtDeadline(dwtgdb_trs
									.get(i).select("td").get(4).text());// 履行债务的期限
							qygsQynbDwtgbzdbInfo.setGuaranteePeriod(dwtgdb_trs
									.get(i).select("td").get(5).text());// 保证的期间
							qygsQynbDwtgbzdbInfo.setGuaranteeMethod(dwtgdb_trs
									.get(i).select("td").get(6).text());// 保证的方式
							qygsQynbDwtgbzdbInfo.setGuaranteeScope("");// 保证担保的范围（甘肃工商网暂时没有该字段）
							qygsQynbDwtgbzdbInfos.add(qygsQynbDwtgbzdbInfo);
						}
					}
				}
				qygsQynbInfo.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);// 对外提供保证担保信息
				Element dwtzxx_et = qygsxx_qynb_info_page
						.getElementById("invoutTab");
				if (null != dwtzxx_et) {
					Elements dwtzxx_trs = dwtzxx_et.select("tbody").get(0)
							.select("tr");
					if (null != dwtzxx_trs && dwtzxx_trs.size() > 2) {
						// 对外投资信息
						List<EntpubAnnreportExtinvestInfo> extInvestInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
						for (int i = 2, dwtzxx_trs_size = dwtzxx_trs.size(); i < dwtzxx_trs_size; i++) {
							// 对外投资信息
							EntpubAnnreportExtinvestInfo entpubAnnreportExtinvestInfo = new EntpubAnnreportExtinvestInfo();
							entpubAnnreportExtinvestInfo
									.setEnterpriseName(dwtzxx_trs.get(i)
											.select("td").get(0).text());// 投资设立企业或购买股权企业名称
							entpubAnnreportExtinvestInfo.setRegNum(dwtzxx_trs
									.get(i).select("td").get(1).text());// 注册号/统一社会信用代码
							extInvestInfos.add(entpubAnnreportExtinvestInfo);
						}
						qygsQynbInfo.setExtInvestInfos(extInvestInfos);// 对外投资信息
					}
				}
				if (isDebug) {
					qygsQynbInfo.setHtml(qygsxx_qynb_info_page.select("div")
							.html());
				}
				qygsQynbInfos.add(qygsQynbInfo);// 企业公示信息->企业年报
			}
		}
		qygsInfo.setAnnReports(qygsQynbInfos);// 企业公示信息->企业年报

		Element touziren_et = qygsxx_list.getElementById("touziren");
		// 企业公示信息->股东及出资信息
		EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
		// 企业公示信息->股东及出资信息
		List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
		if (null != touziren_et) {
			Elements gdjczxx_tbodys = touziren_et.select("tbody");
			int gdjczxx_trs_size = 0;
			if (null != gdjczxx_tbodys && gdjczxx_tbodys.size() > 0) {
				gdjczxx_trs_size = gdjczxx_tbodys.get(0).select("tr").size();
			}
			// TODO
			// 需要优化
			if (gdjczxx_trs_size > 3) {
				for (int j = 3; j < gdjczxx_trs_size; j++) {
					// 企业公示信息->股东及出资信息
					EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new EntpubSStohrinvestInfo();
					qygsGdjczGdjczInfo.setStockholder(gdjczxx_tbodys.get(0)
							.select("tr").get(j).select("td").get(0).text());// 股东
					qygsGdjczGdjczInfo.setSubAmount(gdjczxx_tbodys.get(0)
							.select("tr").get(j).select("td").get(1).text());// 认缴额（万元）
					qygsGdjczGdjczInfo.setPaidAmount(gdjczxx_tbodys.get(0)
							.select("tr").get(j).select("td").get(2).text());// 实缴额（万元）
					List<Detail> subDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
					EntpubSStohrinvestInfo.Detail rjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
					rjDetail.method = gdjczxx_tbodys.get(0).select("tr").get(j)
							.select("td").get(3).text();// 出资方式
					rjDetail.amount = gdjczxx_tbodys.get(0).select("tr").get(j)
							.select("td").get(4).text();// 出资额（万元）
					rjDetail.dateTime = gdjczxx_tbodys.get(0).select("tr")
							.get(j).select("td").get(5).text();// 出资日期
					subDetails.add(rjDetail);
					// qygsGdjczGdjczInfo.setSubDetails(subDetails);
					List<Detail> paidDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
					EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
					sjDetail.method = gdjczxx_tbodys.get(0).select("tr").get(j)
							.select("td").get(6).text();// 出资方式
					sjDetail.amount = gdjczxx_tbodys.get(0).select("tr").get(j)
							.select("td").get(7).text();// 出资额（万元）
					sjDetail.dateTime = gdjczxx_tbodys.get(0).select("tr")
							.get(j).select("td").get(8).text();// 出资日期
					paidDetails.add(sjDetail);
					// qygsGdjczGdjczInfo.setPaidDetails(paidDetails);
					qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
				}
			}
			if (isDebug) {
				qygsGdjczInfo.setHtml(touziren_et.select("table").html());
			}
			qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息

			// 变更信息
			List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
			if (null != gdjczxx_tbodys && gdjczxx_tbodys.size() > 1) {
				Elements bgxx_trs = gdjczxx_tbodys.get(1).select("tr");
				if (null != bgxx_trs && bgxx_trs.size() > 2) {
					for (int i = 2; i < bgxx_trs.size(); i++) {
						EntpubStohrinvestChangeInfo gygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
						gygsGdjczBgInfo.setItem(bgxx_trs.get(i).select("td")
								.get(1).text());// 变更事项
						gygsGdjczBgInfo.setDateTime(bgxx_trs.get(i)
								.select("td").get(2).text());// 变更时间
						gygsGdjczBgInfo.setPreContent(bgxx_trs.get(i)
								.select("td").get(3).text());// 变更前
						gygsGdjczBgInfo.setPostContent(bgxx_trs.get(i)
								.select("td").get(4).text());// 变更后
						qygsGdjczBgInfos.add(gygsGdjczBgInfo);
					}
				}
			}
			qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);// 变更信息
		}
		qygsInfo.setStohrInvestInfo(qygsGdjczInfo);// 企业公示信息->股东及出资信息

		Element gudongguquan_et = qygsxx_list.getElementById("transTab");
		// 企业公示信息->股权变更信息
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		// 企业公示信息->股权变更信息列表
		List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
		if (null != gudongguquan_et) {
			Elements gudongguquan_trs = gudongguquan_et.select("tbody").get(0)
					.select("tr");
			if (null != gudongguquan_trs && gudongguquan_trs.size() > 2) {
				for (int i = 2; i < gudongguquan_trs.size(); i++) {
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

		Element xingzhengxuke = qygsxx_list.getElementById("xingzhengxuke");
		// 企业公示信息->行政许可信息
		EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
		// 企业公示信息->行政许可信息列表
		List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
		if (null != xingzhengxuke) {
			Elements xingzhengxuke_trs = xingzhengxuke.select("tbody").get(0)
					.select("tr");
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
							.select("td").get(7).text());// 状态（暂无）
					qygsXzxkXzxkInfo.setDetail(xingzhengxuke_trs.get(i)
							.select("td").get(8).text());// 详情（暂无）
					qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
				}
			}
			if (isDebug) {
				qygsXzxkInfo.setHtml(xingzhengxuke.select("table").html());
			}
		}
		qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
		qygsInfo.setAdmLicInfo(qygsXzxkInfo);// 企业公示信息->行政许可信息

		Element zhishichanquan_et = qygsxx_list
				.getElementById("zhishichanquan");
		// 企业公示信息->知识产权出质登记信息
		EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
		// 企业公示信息->知识产权出质登记信息列表
		List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
		if (null != zhishichanquan_et) {
			Elements zhishichanquan_trs = zhishichanquan_et.select("tbody")
					.get(0).select("tr");
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
			}
			if (isDebug) {
				qygsZscqczdjInfo.setHtml(zhishichanquan_et.html());
			}
		}
		qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);// 企业公示信息->知识产权出质登记信息
		qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);// 企业公示信息->知识产权出质登记信息

		Element xingzhengchufa_et1 = qygsxx_list.getElementById("penTab");
		// 企业公示信息->行政处罚信息
		EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
		// 企业公示信息->行政处罚信息列表
		List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
		if (null != xingzhengchufa_et1) {
			Elements xingzhengchufa_trs = xingzhengchufa_et1.select("tr");
			if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 2) {
				for (int i = 0; i < xingzhengchufa_trs.size(); i++) {
					// 企业公示信息->行政处罚信息
					EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();
					qygsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs.get(i)
							.select("td").get(1).text());// 行政处罚决定书文号
					qygsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs.get(i)
							.select("td").get(2).text());// 行政处罚内容
					qygsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs.get(i)
							.select("td").get(3).text());// 作出行政处罚决定机关名称
					qygsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs.get(i)
							.select("td").get(4).text());// 作出行政处罚决定日期
					qygsXzcfXzcfInfo.setNote(xingzhengchufa_trs.get(i)
							.select("td").get(5).text());// 备注
					qygsXzcfXzcfInfo.setIllegalActType("");// 违法行为类型（暂无）
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

		// 其他部门公示信息（甘肃的比较特殊，与其它工商系统不同）
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();
		if (null != htmlMap.get("qtbmgsxx")
				&& !StringUtils
						.isEmpty(String.valueOf(htmlMap.get("qtbmgsxx")))) {
			Document qtbmgsxx_page = Jsoup.parseBodyFragment(htmlMap.get(
					"qtbmgsxx").toString());
			// TODO
			// 其他部门公示信息->行政许可信息
			OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
			// 其他部门公示信息->行政许可信息列表
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			// 其他部门公示信息->行政许可信息列表
			OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo = new OthrdeptpubAAdmlicInfo();
			qtbmgsXzxkXzxkInfo.setLicenceNum("");// 许可文件编号
			qtbmgsXzxkXzxkInfo.setLicenceName("");// 许可文件名称
			qtbmgsXzxkXzxkInfo.setStartDateTime("");// 有效期自
			qtbmgsXzxkXzxkInfo.setEndDateTime("");// 有效期至
			qtbmgsXzxkXzxkInfo.setDeciAuthority("");// 许可机关
			qtbmgsXzxkXzxkInfo.setContent("");// 许可内容
			qtbmgsXzxkXzxkInfo.setStatus("");// 状态
			qtbmgsXzxkXzxkInfo.setDetail("");// 详情
			qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
			qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);// 其他部门公示信息->行政许可信息列表
			// Element xingzhengxuke_et = qtbmgsxx_page.getElementById("");
			// Element xingzhengxuke_et = null;
			/*
			 * if (isDebug && null != xingzhengxuke_et) {
			 * qtbmgsXzxkInfo.setHtml(xingzhengxuke_et.html()); }
			 */
			qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);// 其他部门公示信息->行政许可信息

			// TODO
			// 其他部门公示信息->行政处罚信息
			OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
			// 其他部门公示信息->行政处罚信息列表
			List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();
			OthrdeptpubAAdmpunishInfo qtbmgsXzcfXzcfInfo = new OthrdeptpubAAdmpunishInfo();
			qtbmgsXzcfXzcfInfo.setPunishRepNum("");// 行政处罚决定书文号
			qtbmgsXzcfXzcfInfo.setIllegalActType("");// 违法行为类型
			qtbmgsXzcfXzcfInfo.setPunishContent("");// 行政处罚内容
			qtbmgsXzcfXzcfInfo.setDeciAuthority("");// 作出行政处罚决定机关名称
			qtbmgsXzcfXzcfInfo.setDeciDateTime("");// 作出行政处罚决定日期
			qtbmgsXzcfXzcfInfo.setDetail("");// 详情（暂无）
			qtbmgsXzcfXzcfInfo.setNote("");// 备注（暂无）
			qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
			qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);// 其他部门公示信息->行政处罚信息列表
			Element xingzhengchufa_et = qtbmgsxx_page
					.getElementById("oPunishTable");
			if (isDebug && null != xingzhengchufa_et) {
				qtbmgsXzcfInfo.setHtml(xingzhengchufa_et.html());
			}
			qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);// 其他部门公示信息->行政处罚信息
		}

		// 其他部门公示信息
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);

		// 司法协助公示信息
		JudasspubInfo sfxzgsInfo = new JudasspubInfo();
		if (null != htmlMap.get("sfxzgsxx_list")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("sfxzgsxx_list")))) {
			Document sfxzgsxx_list_page = Jsoup.parseBodyFragment(htmlMap.get(
					"sfxzgsxx_list").toString());
			Element guquandongjie_et = sfxzgsxx_list_page
					.getElementById("frzeTab");
			// 司法协助公示信息->股权冻结信息
			JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
			// 司法协助公示信息->股权冻结信息列表
			List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
			if (null != guquandongjie_et) {
				Elements guquandongjie_trs = guquandongjie_et.select("tr");
				if (null != guquandongjie_trs && guquandongjie_trs.size() > 2) {
					for (int i = 0; i < guquandongjie_trs.size(); i++) {
						// 司法协助公示信息->股权冻结信息
						JudasspubEEqufreezeInfo sfxzgsGqdjGqdjInfo = new JudasspubEEqufreezeInfo();
						sfxzgsGqdjGqdjInfo.setExecutedPerson(guquandongjie_trs
								.get(i).select("td").get(1).text());// 被执行人
						sfxzgsGqdjGqdjInfo.setEquAmount("");// 股权数额（甘肃工商网暂无）
						sfxzgsGqdjGqdjInfo.setExeCourt(guquandongjie_trs.get(i)
								.select("td").get(5).text());// 执行法院
						sfxzgsGqdjGqdjInfo.setAssistPubNoticeNum("");// 协助公示通知书文号（甘肃工商网暂无）
						sfxzgsGqdjGqdjInfo.setStatus(guquandongjie_trs.get(i)
								.select("td").get(7).text());// 状态
						sfxzgsGqdjGqdjInfo.setDetail(guquandongjie_trs.get(i)
								.select("td").get(8).text());// 详情
						sfxzgsGqdjGqdjInfos.add(sfxzgsGqdjGqdjInfo);
					}
				}
				if (isDebug) {
					sfxzgsGqdjInfo.setHtml(guquandongjie_et.html());
				}
			}
			sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);// 司法协助公示信息->股权冻结信息列表
			sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);// 司法协助公示信息->股权冻结信息

			Element guquanbiangeng_et = sfxzgsxx_list_page
					.getElementById("equAltTab");
			// 司法协助公示信息->股东变更信息
			JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
			// 司法协助公示信息->股东变更信息列表
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			if (null != guquanbiangeng_et) {
				Elements guquanbiangeng_trs = guquanbiangeng_et.select("tr");
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
				if (isDebug && null != guquanbiangeng_et) {
					sfxzgsGdbgInfo.setHtml(guquanbiangeng_et.html());
				}
			}
			sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);// 司法协助公示信息->股东变更信息列表
			sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);// 司法协助公示信息->股东变更信息列表
		}

		// 司法协助公示信息
		gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);

		return gsxtFeedJson;

	}

}

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
public class GsxtNingxiaParser extends AbstractGsxtParser {

	private static final Logger LOOGER = LoggerFactory
			.getLogger(GsxtNingxiaParser.class);

	public AicFeedJson getNingxiaResultObj(String html, boolean isDebug) {

		LOOGER.info("The method of GsxtNingxiaParser.getNingxiaResultObj is begin !");

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

		// 工商公示信息->登记信息->基本信息
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
		if (null != htmlMap.get("gsgsxx_djxx_jbxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_djxx_jbxx")
						.toString())) {
			Document gsgsxx_djxx_jbxx = Jsoup.parseBodyFragment(htmlMap.get(
					"gsgsxx_djxx_jbxx").toString());
			Elements gsgsxx_djxx_jbxx_tb = gsgsxx_djxx_jbxx.select("table")
					.select("tbody");
			if (null != gsgsxx_djxx_jbxx_tb) {
				Elements ths = gsgsxx_djxx_jbxx_tb.select("tr").select("th");
				for (int i = 1; i < ths.size(); i++) {
					if (ths.get(i).text().trim().contains("统一社会信用代码")
							|| ths.get(i).text().trim().contains("注册号")) {
						gsgsDjJbInfo.setNum(gsgsxx_djxx_jbxx.select("table")
								.select("tbody").select("tr").select("td")
								.get(i - 1).text());// 注册号或信用代码
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("名称")) {
						gsgsDjJbInfo.setName(gsgsxx_djxx_jbxx.select("table")
								.select("tbody").select("tr").select("td")
								.get(i - 1).text());// 名称
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("类型")) {
						gsgsDjJbInfo.setType(gsgsxx_djxx_jbxx.select("table")
								.select("tbody").select("tr").select("td")
								.get(i - 1).text());// 类型
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("负责人")
							|| ths.get(i).text().trim()
									.equalsIgnoreCase("法定代表人")
							|| ths.get(i).text().trim().contains("经营者")) {
						gsgsDjJbInfo.setLegalRepr(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 法定代表人/经营者
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("注册资本")) {
						gsgsDjJbInfo.setRegCapital(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 注册资本
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("成立日期")) {
						gsgsDjJbInfo.setRegDateTime(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 成立日期
					}
					if (ths.get(i).text().trim().contains("营业场所")
							|| ths.get(i).text().trim().contains("住所")) {
						gsgsDjJbInfo.setAddress(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 经营场所/住所
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("营业期限自")
							|| ths.get(i).text().contains("经营期限自")) {
						gsgsDjJbInfo.setStartDateTime(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 营业期限自（即营业开始日期）
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("营业期限至")
							|| ths.get(i).text().contains("经营期限至")) {
						gsgsDjJbInfo.setEndDateTime(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 营业期限至（即营业结束日期）
					}
					if (ths.get(i).text().trim().contains("经营范围")) {
						gsgsDjJbInfo.setBusinessScope(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 经营范围
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("登记机关")) {
						gsgsDjJbInfo.setRegAuthority(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 登记机关
					}
					if (ths.get(i).text().trim().equalsIgnoreCase("核准日期")) {
						gsgsDjJbInfo.setApprovalDateTime(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 核准日期
					}
					if (ths.get(i).text().trim().contains("登记状态")) {
						gsgsDjJbInfo.setRegStatus(gsgsxx_djxx_jbxx
								.select("table").select("tbody").select("tr")
								.select("td").get(i - 1).text());// 登记状态
					}
					if (ths.get(i).text().trim().contains("吊销日期")) {
						gsgsDjJbInfo.setRevokeDate(gsgsxx_djxx_jbxx
								.select("tbody").select("tr").select("td")
								.get(i - 1).text());// 吊销日期
					}
				}
				if (isDebug) {
					gsgsDjJbInfo.setHtml(gsgsxx_djxx_jbxx_tb.html());
				}
			}
		}
		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);// 基本信息

		// 工商公示信息->登记信息->股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
		if (null != htmlMap.get("gsgsxx_djxx_tzrxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_djxx_tzrxx")
						.toString())) {
			Document gsgsxx_djxx_tzrxx = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("gsgsxx_djxx_tzrxx")));
			Element touziren_tbody = gsgsxx_djxx_tzrxx.select("table").get(0)
					.select("tbody").get(0);
			if (null != touziren_tbody) {
				Elements touziren_trs = touziren_tbody.select("tr");
				int touziren_nums = touziren_trs.size();
				if (touziren_nums > 2) {
					for (int i = 2; i < touziren_nums; i++) {
						// 股东信息
						AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();
						gsgsDjGdInfo.setType(touziren_trs.get(i).select("td")
								.get(0).text());// 股东类型
						gsgsDjGdInfo.setName(touziren_trs.get(i).select("td")
								.get(1).text());// 股东名称
						gsgsDjGdInfo.setIdType(touziren_trs.get(i).select("td")
								.get(2).text());// 证照/证件类型
						gsgsDjGdInfo.setIdNum(touziren_trs.get(i).select("td")
								.get(3).text());// 证照/证件号码
						gsgsDjGdInfos.add(gsgsDjGdInfo);
					}
				}
			}
		}
		gsgsDjInfo.setStohrInfos(gsgsDjGdInfos);// 股东信息
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = (List<Map<String, Object>>) htmlMap
				.get("gsgsxx_djxx_tzrxx_xqs");
		if (null != gsgsxx_djxx_tzrxx_xqs && !gsgsxx_djxx_tzrxx_xqs.isEmpty()) {
			List<AicpubRegStohrStohrinvestInfo> gsgsDjGdGdjczInfos = new ArrayList<AicpubRegStohrStohrinvestInfo>();
			if (gsgsxx_djxx_tzrxx_xqs.size() > 0) {
				for (Map<String, Object> gsgsxx_djxx_tzrxx_xq : gsgsxx_djxx_tzrxx_xqs) {
					Document gsgsxx_djxx_tzrxx_xq_page = Jsoup
							.parseBodyFragment(gsgsxx_djxx_tzrxx_xq.get(
									"gsgsxx_djxx_tzrxx_xq").toString());
					Elements gsgsxx_djxx_tzrxx_xq_trs = gsgsxx_djxx_tzrxx_xq_page
							.getElementById("jibenxinxi").select("tbody")
							.select("tr");
					int gsgsxx_djxx_tzrxx_xq_trs_size = gsgsxx_djxx_tzrxx_xq_trs
							.size();
					if (gsgsxx_djxx_tzrxx_xq_trs_size > 4) {
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
						for (int i = 3; i < gsgsxx_djxx_tzrxx_xq_trs_size - 1; i++) {
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
						;
						gsgsDjGdGdjczInfo
								.setPaidAmountDetails(paidAmountDetails);
						gsgsDjGdGdjczInfos.add(gsgsDjGdGdjczInfo);
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
										gsgsDjGdGdjczInfo.getStockholder()
												.trim())) {
							gsgsDjGdInfo.setStohrInvestInfo(gsgsDjGdGdjczInfo);
						}
					}
					gsgsDjGdInfos2.add(gsgsDjGdInfo);
				}
				gsgsDjInfo.setStohrInfos(gsgsDjGdInfos2);
			}
		}
		// 工商公示信息->登记信息->变更信息
		List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<AicpubRegChangeInfo>();
		if (null != htmlMap.get("gsgsxx_djxx_bgxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_djxx_bgxx")
						.toString())) {
			Document gsgsxx_djxx_bgxx = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("gsgsxx_djxx_bgxx")));
			Element biangeng = gsgsxx_djxx_bgxx.select("table").get(0)
					.select("tbody").get(0);
			if (null != biangeng) {
				Elements biangeng_trs = biangeng.select("tr");
				int biangeng_trs_size = biangeng_trs.size();
				if (biangeng_trs_size > 2) {
					for (int i = 2; i < biangeng_trs_size; i++) {
						AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
						gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td")
								.get(0).text());// 变更事项
						Elements biangeng_one = biangeng_trs.get(i)
								.select("td").get(1).getElementsByTag("div");
						if (null != biangeng_one && biangeng_one.size() > 1) {
							gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
									.select("td").get(1).select("div").get(1)
									.text());// 变更前内容
						} else {
							gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
									.select("td").get(1).text());// 变更前内容
						}
						Elements biangeng_two = biangeng_trs.get(i)
								.select("td").get(2).getElementsByTag("div");
						if (null != biangeng_two && biangeng_two.size() > 1) {
							gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
									.select("td").get(2).select("div").get(1)
									.text());// 变更后内容
						} else {
							gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
									.select("td").get(2).text());// 变更后内容
						}
						gsgsDjBgInfo.setDateTime(biangeng_trs.get(i)
								.select("td").get(3).text());// 变更日期
						gsgsDjBgInfos.add(gsgsDjBgInfo);
					}
				}
				gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);// 变更信息
			}
		}

		gsgsInfo.setRegInfo(gsgsDjInfo);// 工商公示信息->登记信息

		// 工商公示信息->备案信息
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();

		// 工商公示信息->备案信息->主要人员信息
		if (null != htmlMap.get("gsgsxx_baxx_zyryxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_baxx_zyryxx")
						.toString())) {
			Document gsgsxx_baxx_zyryxx = Jsoup.parseBodyFragment(htmlMap.get(
					"gsgsxx_baxx_zyryxx").toString());
			Elements beian_zyryxx = gsgsxx_baxx_zyryxx.select("table").get(0)
					.select("tbody").get(0).select("tr");
			List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
			int beian_zyryxx_size = beian_zyryxx.size();
			if (beian_zyryxx_size > 2) {
				for (int i = 2; i < beian_zyryxx_size; i++) {
					int beian_zyryxx_td_size = beian_zyryxx.get(i).select("td")
							.size();
					String name1 = beian_zyryxx.get(i).select("td").get(1)
							.text();
					String position1 = beian_zyryxx.get(i).select("td").get(2)
							.text();
					String name2 = "";
					String position2 = "";
					if (beian_zyryxx_td_size > 5) {
						name2 = beian_zyryxx.get(i).select("td").get(4).text();
						position2 = beian_zyryxx.get(i).select("td").get(5)
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
			gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
		}
		// 工商公示信息->备案信息->分支机构信息
		List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
		if (null != htmlMap.get("gsgsxx_baxx_fzjgxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_baxx_fzjgxx")
						.toString())) {
			Document gsgsxx_baxx_fzjgxx = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("gsgsxx_baxx_fzjgxx")));
			Elements beian_fzjgxx = gsgsxx_baxx_fzjgxx.select("table").get(0)
					.select("tbody").get(0).select("tr");
			int beian_fzjgxx_size = beian_fzjgxx.size();
			if (beian_fzjgxx_size > 2) {
				for (int i = 2; i < beian_fzjgxx_size; i++) {
					// 分支机构信息
					AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
					gsgsBaFzjgInfo.setNum(beian_fzjgxx.get(i).select("td")
							.get(1).text());// 统一社会信用代码/注册号
					gsgsBaFzjgInfo.setName(beian_fzjgxx.get(i).select("td")
							.get(2).text());// 名称
					gsgsBaFzjgInfo.setRegAuthority(beian_fzjgxx.get(i)
							.select("td").get(3).text());// 登记机关
					gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
				}
			}
			gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);// 分支机构信息
		}
		// 工商公示信息->备案信息->清算信息
		AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
		if (null != htmlMap.get("gsgsxx_baxx_qsxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_baxx_qsxx")
						.toString())) {
			Document gsgsxx_baxx_qsxx = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("gsgsxx_baxx_qsxx")));
			Elements beian_qingsuanrenyuan = gsgsxx_baxx_qsxx.select("table")
					.get(0).select("tr");
			if (null != beian_qingsuanrenyuan) {
				gsgsBaQsInfo.setLeader(beian_qingsuanrenyuan.get(1)
						.select("td").get(0).text());// 清算组负责人
				String wd = beian_qingsuanrenyuan.get(2).select("td").get(0)
						.text();
				List<String> wq = new ArrayList<String>();
				if (wd.contains("、")) {
					for (String wc : wd.split("、")) {
						wq.add(wc);
					}
					gsgsBaQsInfo.setMembers(wq);// 清算组成员
				} else {
					wq.add(wd);
					gsgsBaQsInfo.setMembers(wq);// 清算组成员
				}
				if (isDebug) {
					gsgsBaQsInfo.setHtml(beian_qingsuanrenyuan.html());
				}
				gsgsBaInfo.setClearInfo(gsgsBaQsInfo);// 清算信息
			}
		}
		gsgsInfo.setArchiveInfo(gsgsBaInfo);// 工商公示信息->备案信息

		// 工商公示信息->动产抵押登记信息
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		// 工商公示信息->动产抵押登记信息列表
		List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
		if (null != htmlMap.get("gsgsxx_dcdydjxx_dcdydjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_dcdydjxx_dcdydjxx")))) {
			Document gsgsxx_dcdydjxx_dcdydjxx_page = Jsoup
					.parseBodyFragment(String.valueOf(htmlMap
							.get("gsgsxx_dcdydjxx_dcdydjxx")));
			Elements dongchandiya_et = gsgsxx_dcdydjxx_dcdydjxx_page
					.select("table");
			if (null != dongchandiya_et) {
				Elements ta_tb_trs = dongchandiya_et.get(0).select("tbody")
						.get(0).select("tr");
				int ta_tb_trs_size = ta_tb_trs.size();
				if (ta_tb_trs_size > 2) {
					for (int i = 2; i < ta_tb_trs_size; i++) {
						AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
						gsgsDcdydjDcdydjInfo.setRegNum(ta_tb_trs.get(i)
								.select("td").get(1).text());// 登记编号
						gsgsDcdydjDcdydjInfo.setPubDateTime(ta_tb_trs.get(i)
								.select("td").get(2).text());// 公示时间（该字段比较特殊，江苏工商网暂时没有该字段。）
						gsgsDcdydjDcdydjInfo.setRegDateTime(ta_tb_trs.get(i)
								.select("td").get(3).text());// 登记日期
						gsgsDcdydjDcdydjInfo.setRegAuthority(ta_tb_trs.get(i)
								.select("td").get(4).text());// 登记机关
						gsgsDcdydjDcdydjInfo.setGuaranteedDebtAmount(ta_tb_trs
								.get(i).select("td").get(5).text());// 被担保债权数额
						gsgsDcdydjDcdydjInfo.setStatus(ta_tb_trs.get(i)
								.select("td").get(6).text());// 状态
						gsgsDcdydjDcdydjInfo.setDetail("");// 详情
						gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
					}
				}
				// gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);//
				// 动产抵押登记信息
				if (isDebug) {
					gsgsDcdydjInfo.setHtml(dongchandiya_et.get(0).html());
				}
			}
			// gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息
		}
		// 工商公示信息->动产抵押登记信息->详情
		if (null != htmlMap.get("gsgsxx_dcdydjxx_xqs")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_dcdydjxx_xqs")))) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> gsgsxx_dcdydjxx_xqs = (List<Map<String, Object>>) htmlMap
					.get("gsgsxx_dcdydjxx_xqs");
			List<AicpubCChatMortgDetail> aicpubCChatMortgDetails = new ArrayList<AicpubCChatMortgDetail>();
			if (null != gsgsxx_dcdydjxx_xqs && gsgsxx_dcdydjxx_xqs.size() > 0) {
				for (Map<String, Object> gsgsxx_dcdydjxx_xq : gsgsxx_dcdydjxx_xqs) {
					// 工商公示信息->动产抵押登记信息->详情
					AicpubCChatMortgDetail aicpubCChatMortgDetail = new AicpubCChatMortgDetail();
					if (null != gsgsxx_dcdydjxx_xq.get("dcdydjxx")
							&& !StringUtils
									.isEmpty(String.valueOf(gsgsxx_dcdydjxx_xq
											.get("dcdydjxx")))) {
						Document dcdydjxx = Jsoup
								.parseBodyFragment(gsgsxx_dcdydjxx_xq.get(
										"dcdydjxx").toString());
						Elements dcDetails = dcdydjxx.select("table");
						if (null != dcDetails && !dcDetails.isEmpty()) {
							// 工商公示信息->动产抵押登记信息->详情->动产抵押登记信息
							AicpubCChatMortgRegInfo aicpubCChatMortgRegInfo = new AicpubCChatMortgRegInfo();
							Elements dcDetails_ths = dcDetails.select("th");
							for (Element dcDetails_th : dcDetails_ths) {
								if (dcDetails_th.text().contains("登记编号")) {
									aicpubCChatMortgRegInfo
											.setRegNum(dcDetails_th
													.nextElementSibling()
													.text());
									continue;
								}
								if (dcDetails_th.text().contains("登记日期")) {
									aicpubCChatMortgRegInfo
											.setRegDate(dcDetails_th
													.nextElementSibling()
													.text());
									continue;
								}
								if (dcDetails_th.text().contains("登记机关")) {
									aicpubCChatMortgRegInfo
											.setRegAuthority(dcDetails_th
													.nextElementSibling()
													.text());
									continue;
								}
								if (dcDetails_th.text().contains("被担保债权种类")) {
									aicpubCChatMortgRegInfo
											.setGuaranteedDebtType(dcDetails_th
													.nextElementSibling()
													.text());
									continue;
								}
								if (dcDetails_th.text().contains("被担保债权数额")) {
									aicpubCChatMortgRegInfo
											.setGuaranteedDebtAmount(dcDetails_th
													.nextElementSibling()
													.text());
									continue;
								}
								if (dcDetails_th.text().contains("债务人履行债务的期限")) {
									aicpubCChatMortgRegInfo
											.setTerm(dcDetails_th
													.nextElementSibling()
													.text());
									continue;
								}
								if (dcDetails_th.text().contains("担保范围")) {
									aicpubCChatMortgRegInfo
											.setGuaranteedScope(dcDetails_th
													.nextElementSibling()
													.text());
									continue;
								}
								if (dcDetails_th.text().contains("备注")) {
									aicpubCChatMortgRegInfo
											.setNote(dcDetails_th
													.nextElementSibling()
													.text());
									continue;
								}
							}
							if (isDebug) {
								aicpubCChatMortgRegInfo.setHtml(dcDetails
										.html());
							}
							aicpubCChatMortgDetail
									.setMortgRegInfo(aicpubCChatMortgRegInfo);
						}
					}
					if (null != gsgsxx_dcdydjxx_xq.get("dyqrgk")
							&& !StringUtils.isEmpty(String
									.valueOf(gsgsxx_dcdydjxx_xq.get("dyqrgk")))) {
						Document dyqrgk = Jsoup
								.parseBodyFragment(gsgsxx_dcdydjxx_xq.get(
										"dyqrgk").toString());
						Elements dyqrgk_tbs = dyqrgk.select("table");
						if (null != dyqrgk_tbs && !dyqrgk_tbs.isEmpty()) {
							if (null != dyqrgk_tbs && dyqrgk_tbs.size() > 0) {
								Elements dyqrgk_tbs_trs = dyqrgk_tbs.get(0)
										.select("tbody").get(0).select("tr");
								if (null != dyqrgk_tbs_trs
										&& dyqrgk_tbs_trs.size() > 2) {
									List<AicpubCChatMortgPersonInfo> aicpubCChatMortgPersonInfos = new ArrayList<AicpubCChatMortgPersonInfo>();
									for (int i = 2; i < dyqrgk_tbs_trs.size(); i++) {
										// 工商公示信息->动产抵押登记信息->详情->抵押权人概况
										AicpubCChatMortgPersonInfo aicpubCChatMortgPersonInfo = new AicpubCChatMortgPersonInfo();
										aicpubCChatMortgPersonInfo
												.setName(dyqrgk_tbs_trs.get(i)
														.select("td").get(1)
														.text());// 抵押权人名称
										aicpubCChatMortgPersonInfo
												.setType(dyqrgk_tbs_trs.get(i)
														.select("td").get(2)
														.text());// 抵押权人证照/证件类型
										aicpubCChatMortgPersonInfo
												.setNum(dyqrgk_tbs_trs.get(i)
														.select("td").get(3)
														.text());// 证照/证件号码
										aicpubCChatMortgPersonInfos
												.add(aicpubCChatMortgPersonInfo);
									}
									aicpubCChatMortgDetail
											.setMortgPersonInfos(aicpubCChatMortgPersonInfos);
								}
							}
						}
					}
					if (null != gsgsxx_dcdydjxx_xq.get("bdbzqgk")
							&& !StringUtils
									.isEmpty(String.valueOf(gsgsxx_dcdydjxx_xq
											.get("bdbzqgk")))) {
						Document bdbzqgk = Jsoup
								.parseBodyFragment(gsgsxx_dcdydjxx_xq.get(
										"bdbzqgk").toString());
						Elements bdbzqgk_tbs = bdbzqgk.select("table");
						if (null != bdbzqgk_tbs && !bdbzqgk_tbs.isEmpty()) {
							Elements parent_id3_ths = bdbzqgk_tbs.select("th");
							if (null != parent_id3_ths
									&& !parent_id3_ths.isEmpty()) {
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
									if (parent_id3_th.text().contains(
											"债务人履行债务的期限")) {
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
											.setHtml(bdbzqgk_tbs.html());
								}
								aicpubCChatMortgDetail
										.setMortgGuaranteedInfo(aicpubCChatMortgGuaranteedInfo);
							}
						}
					}
					if (null != gsgsxx_dcdydjxx_xq.get("dywgk")
							&& !StringUtils.isEmpty(String
									.valueOf(gsgsxx_dcdydjxx_xq.get("dywgk")))) {
						Document dywgk = Jsoup
								.parseBodyFragment(gsgsxx_dcdydjxx_xq.get(
										"dywgk").toString());
						Elements dywgk_tbs = dywgk.select("table");
						if (null != dywgk_tbs && !dywgk_tbs.isEmpty()) {
							Elements dywgk_trs = dywgk_tbs.get(0)
									.select("tbody").get(0).select("tr");
							if (null != dywgk_trs && dywgk_trs.size() > 2) {
								List<AicpubCChatMortgGoodsInfo> aicpubCChatMortgGoodsInfos = new ArrayList<AicpubCChatMortgGoodsInfo>();
								for (int i = 2; i < dywgk_trs.size(); i++) {
									// 工商公示信息->动产抵押登记信息->详情->抵押物概况
									AicpubCChatMortgGoodsInfo aicpubCChatMortgGoodsInfo = new AicpubCChatMortgGoodsInfo();
									aicpubCChatMortgGoodsInfo.setName(dywgk_trs
											.get(i).select("td").get(1).text());// 名称
									aicpubCChatMortgGoodsInfo
											.setOwnerShip(dywgk_trs.get(i)
													.select("td").get(2).text());// 所有权归属
									aicpubCChatMortgGoodsInfo
											.setGeneralSituation(dywgk_trs
													.get(i).select("td").get(3)
													.text());// 数量、质量、状况、所在地等情况
									aicpubCChatMortgGoodsInfo.setNote(dywgk_trs
											.get(i).select("td").get(4).text());// 备注
									aicpubCChatMortgGoodsInfos
											.add(aicpubCChatMortgGoodsInfo);
								}
								aicpubCChatMortgDetail
										.setMortgGoodsInfos(aicpubCChatMortgGoodsInfos);
							}
						}
					}
					if (null != gsgsxx_dcdydjxx_xq.get("bg")
							&& !StringUtils.isEmpty(String
									.valueOf(gsgsxx_dcdydjxx_xq.get("bg")))) {
						Document bg = Jsoup
								.parseBodyFragment(gsgsxx_dcdydjxx_xq.get("bg")
										.toString());
						Elements bg_tbs = bg.select("table");
						if (null != bg_tbs && !bg_tbs.isEmpty()) {
							Elements dywgk_trs = bg_tbs.get(0).select("tbody")
									.get(0).select("tr");
							if (null != dywgk_trs && dywgk_trs.size() > 2) {
								List<AicpubCChatMortgChangeInfo> mortgChangeInfos = new ArrayList<AicpubCChatMortgChangeInfo>();
								for (int i = 2; i < dywgk_trs.size(); i++) {
									// 工商公示信息->动产抵押登记信息->详情->变更
									AicpubCChatMortgChangeInfo mortgChangeInfo = new AicpubCChatMortgChangeInfo();
									mortgChangeInfo.setChangeDate(dywgk_trs
											.get(i).select("td").get(1).text());// 变更日期
									mortgChangeInfo.setChangeContent(dywgk_trs
											.get(i).select("td").get(1).text());// 变更内容
									mortgChangeInfos.add(mortgChangeInfo);
								}
								aicpubCChatMortgDetail
										.setMortgChangeInfos(mortgChangeInfos);
							}
						}
					}
					if (null != gsgsxx_dcdydjxx_xq.get("zx")
							&& !StringUtils.isEmpty(String
									.valueOf(gsgsxx_dcdydjxx_xq.get("zx")))) {
						Document zx = Jsoup
								.parseBodyFragment(gsgsxx_dcdydjxx_xq.get("zx")
										.toString());
						Elements zx_tbs = zx.select("table");
						if (null != zx_tbs && !zx_tbs.isEmpty()) {
							Elements zx_ths = zx_tbs.select("th");
							// 工商公示信息->动产抵押登记信息->详情->注销信息
							AicpubCChatMortgRevokeInfo aicpubCChatMortgRevokeInfo = new AicpubCChatMortgRevokeInfo();
							for (Element zx_th : zx_ths) {
								if (zx_th.text().contains("注销日期")) {
									aicpubCChatMortgRevokeInfo
											.setRevokeDate(zx_th
													.nextElementSibling()
													.text());// 注销日期
								}
								if (zx_th.text().contains("注销原因")) {
									aicpubCChatMortgRevokeInfo
											.setRevokeReason(zx_th
													.nextElementSibling()
													.text());// 注销原因
								}
							}
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
		if (null != htmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_gqczdjxx_gqczdjxx")))) {
			Document gsgsxx_gqczdjxx_gqczdjxx_page = Jsoup
					.parseBodyFragment(htmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")
							.toString());
			Elements guquanchuzhi_et = gsgsxx_gqczdjxx_gqczdjxx_page
					.select("table");
			// 工商公示信息->股权出资登记信息列表
			List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
			if (null != guquanchuzhi_et) {
				Elements guquanchuzhi_trs = guquanchuzhi_et.get(0)
						.select("tbody").get(0).select("tr");
				if (null != guquanchuzhi_trs && guquanchuzhi_trs.size() > 2) {
					for (int i = 2; i < guquanchuzhi_trs.size(); i++) {
						// 工商公示信息->股权出资登记信息
						AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
						gsgsGqczdjGqczdjInfo.setRegNum(guquanchuzhi_trs.get(i)
								.select("td").get(1).text());// 登记编号
						gsgsGqczdjGqczdjInfo.setMortgagorName(guquanchuzhi_trs
								.get(i).select("td").get(2).text());// 出质人
						gsgsGqczdjGqczdjInfo.setMortgagorIdNum(guquanchuzhi_trs
								.get(i).select("td").get(3).text());// 证照/证件号码（出质人）
						gsgsGqczdjGqczdjInfo.setMortgAmount(guquanchuzhi_trs
								.get(i).select("td").get(4).text());// 出质股权数额
						gsgsGqczdjGqczdjInfo.setMortgageeName(guquanchuzhi_trs
								.get(i).select("td").get(5).text());// 质权人
						gsgsGqczdjGqczdjInfo.setMortgageeIdNum(guquanchuzhi_trs
								.get(i).select("td").get(6).text());// 证照/证件号码
						gsgsGqczdjGqczdjInfo.setRegDateTime(guquanchuzhi_trs
								.get(i).select("td").get(7).text());// 股权出质设立登记日期
						gsgsGqczdjGqczdjInfo.setPubDate(guquanchuzhi_trs.get(i)
								.select("td").get(8).text());// 公示时间（该字段比较特殊，江苏工商网暂时没有该字段。）
						gsgsGqczdjGqczdjInfo.setStatus(guquanchuzhi_trs.get(i)
								.select("td").get(9).text());// 状态
						if (!StringUtils.isEmpty(guquanchuzhi_trs.get(i)
								.select("td").get(10).text())
								&& !guquanchuzhi_trs.get(i).select("td")
										.get(10).text().trim().equals("详情")) {
							gsgsGqczdjGqczdjInfo.setChangeSitu(guquanchuzhi_trs
									.get(i).select("td").get(10).text());// 变化情况
						}

						gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
					}
				}
				if (isDebug) {
					gsgsGqczdjInfo.setHtml(guquanchuzhi_et.html());
				}
				gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);// 股权出资登记信息
			}
			gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);// 工商公示信息->股权出资登记信息
		}

		// 工商公示信息->行政处罚信息
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
		if (null != htmlMap.get("gsgsxx_xzcfxx_xzcfxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_xzcfxx_xzcfxx")
						.toString())) {
			Document gsgsxx_xzcfxx_xzcfxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_xzcfxx_xzcfxx").toString());
			// 工商公示信息->行政处罚信息列表
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
			Element xingzhengchufa = gsgsxx_xzcfxx_xzcfxx.select("table")
					.get(0);
			if (null != xingzhengchufa) {
				Elements xingzhengchufa_trs = xingzhengchufa.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 2) {
					int xingzhengchufa_trs_size = xingzhengchufa_trs.size();
					for (int i = 2; i < xingzhengchufa_trs_size; i++) {
						AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();
						gsgsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs
								.get(i).select("td").get(1).text());// 行政处罚决定书文号
						gsgsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs
								.get(i).select("td").get(2).text());// 违法行为类型
						gsgsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs
								.get(i).select("td").get(3).text());// 行政处罚内容
						gsgsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs
								.get(i).select("td").get(4).text());// 作出行政处罚决定机关名称
						gsgsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs
								.get(i).select("td").get(5).text());// 作出行政处罚决定日期
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
			// int gsgsxx_xzcfxx_xzcfxx_xqs_size =
			// gsgsxx_xzcfxx_xzcfxx_xqs.size();
			// TODO
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
					if (gsgsxx_xzcfxx_xzcfxx_xq_page.getElementById(
							"CR_CONTENT").hasAttr("div")) {
						xzcfDetail.punishRep = gsgsxx_xzcfxx_xzcfxx_xq_page
								.getElementById("CR_CONTENT")
								.select("div.Section1").first().text();// 行政处罚决定书
					}
					gsgsXzcfXzcfInfo.setPunishDetail(xzcfDetail);
					gsgsXzcfXzcfInfos2.add(gsgsXzcfXzcfInfo);
				}
			}
			if (null != gsgsXzcfXzcfInfos && gsgsXzcfXzcfInfos.size() > 0
					&& null != gsgsXzcfXzcfInfos2
					&& gsgsXzcfXzcfInfos2.size() > 0) {
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
		}

		// 工商公示信息->经营异常信息
		AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
		if (null != htmlMap.get("gsgsxx_jyycxx_jyycxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_jyycxx_jyycxx")
						.toString())) {
			Document gsgsxx_jyycxx_jyycxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_jyycxx_jyycxx").toString());
			List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();// 经营异常信息列表
			Element yichangminglu = gsgsxx_jyycxx_jyycxx.select("table").get(0);
			if (null != yichangminglu) {
				Elements yichangminglu_trs = yichangminglu.select("tbody")
						.get(0).select("tr");
				int yichangminglu_trs_size = yichangminglu_trs.size();
				if (yichangminglu_trs_size > 2) {
					for (int i = 2; i < yichangminglu_trs_size; i++) {
						yichangminglu_trs.get(i).select("td");
						AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();// 经营异常信息
						Elements span_one = yichangminglu_trs.get(i)
								.select("td").get(1).getElementsByTag("span");
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
						Elements span_three = yichangminglu_trs.get(i)
								.select("td").get(3).getElementsByTag("span");
						if (null != span_three && span_three.size() > 0) {
							gsgsJyycJyycInfo.setRemoveCause(yichangminglu_trs
									.get(i).select("td").get(3).select("span")
									.attr("completedata"));// 移出经营异常名录原因
						} else {
							gsgsJyycJyycInfo.setRemoveCause(yichangminglu_trs
									.get(i).select("td").get(3).text());// 移出经营异常名录原因
						}
						gsgsJyycJyycInfo.setRemoveDateTime(yichangminglu_trs
								.get(i).select("td").get(4).text());// 移出日期
						gsgsJyycJyycInfo.setAuthority(yichangminglu_trs.get(i)
								.select("td").get(5).text());// 作出决定机关
						gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
					}
				}
				if (isDebug) {
					gsgsJyycInfo.setHtml(yichangminglu.html());
				}
				gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);// 经营异常信息列表
			}
			gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);// 工商公示信息->经营异常信息
		}

		// 工商公示信息->严重违法信息
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		if (null != htmlMap.get("gsgsxx_yzwfxx_yzwfxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_yzwfxx_yzwfxx")
						.toString())) {
			Document gsgsxx_yzwfxx_yzwfxx_page = Jsoup
					.parseBodyFragment(htmlMap.get("gsgsxx_yzwfxx_yzwfxx")
							.toString());
			// 工商公示信息->严重违法信息列表
			List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
			Element heimingdan_et = gsgsxx_yzwfxx_yzwfxx_page.select("table")
					.get(0);
			if (null != heimingdan_et) {
				Elements heimingdan_trs = heimingdan_et.select("tbody").get(0)
						.select("tr");
				if (null != heimingdan_trs && heimingdan_trs.size() > 2) {
					for (int j = 2; j < heimingdan_trs.size(); j++) {
						int heimingdan_tds_size = heimingdan_trs.get(j)
								.select("td").size();
						// 严重违法信息
						AicpubSSerillegalInfo gsgsYzwfYzwfInfo = new AicpubSSerillegalInfo();
						gsgsYzwfYzwfInfo.setIncludeCause(heimingdan_trs.get(j)
								.select("td").get(1).text());// 列入严重违法企业名单原因
						gsgsYzwfYzwfInfo.setIncludeDateTime(heimingdan_trs
								.get(j).select("td").get(2).text());// 列入日期
						if (heimingdan_tds_size > 5) {
							gsgsYzwfYzwfInfo.setRemoveCause(heimingdan_trs
									.get(j).select("td").get(3).text());// 移出严重违法企业名单原因
							gsgsYzwfYzwfInfo.setRemoveDateTime(heimingdan_trs
									.get(j).select("td").get(4).text());// 移出日期
							gsgsYzwfYzwfInfo.setDeciAuthority(heimingdan_trs
									.get(j).select("td").get(5).text());// 作出决定机关
						}
						gsgsYzwfYzwfInfos.add(gsgsYzwfYzwfInfo);
						gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);// 严重违法信息
					}
				}
				if (isDebug) {
					gsgsYzwfInfo.setHtml(heimingdan_et.html());
				}
			}
			gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);// 工商公示信息->严重违法信息
		}

		// 工商公示信息->抽查检查信息
		AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		if (null != htmlMap.get("gsgsxx_ccjcxx_ccjcxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_ccjcxx_ccjcxx")
						.toString())) {
			Document gsgsxx_ccjcxx_ccjcxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_ccjcxx_ccjcxx").toString());
			// 工商公示信息->抽查检查信息列表
			List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
			Elements chouchaxinxi_trs = gsgsxx_ccjcxx_ccjcxx.select("table")
					.get(0).getElementById("tableChoucha").select("tr");
			if (null != chouchaxinxi_trs) {
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
					gsgsCcjcInfo.setHtml(chouchaxinxi_trs.html());
				}
			}
			gsgsInfo.setCheckInfo(gsgsCcjcInfo);// 工商公示信息->抽查检查信息
		}

		gsxtFeedJson.setAicPubInfo(gsgsInfo);// 工商公示信息

		// 企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();

		// 企业公示信息->企业年报
		List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> qygsxx_qynb_infos = (List<Map<String, Object>>) htmlMap
				.get("qygsxx_qynb_infos");
		if (null != qygsxx_qynb_infos && qygsxx_qynb_infos.size() > 0) {
			for (Map<String, Object> qygsxx_qynb_info : qygsxx_qynb_infos) {
				EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();// 企业年报
				String wcc = qygsxx_qynb_info.get("qygsxx_qynb_list_a_text")
						.toString();
				qygsQynbInfo.setSubmitYear(wcc);// 报送年度
				Document qqq = Jsoup.parseBodyFragment(htmlMap.get(
						"qygsxx_qynb_list_page").toString());
				Elements qqq_as = qqq.getElementsByTag("a");
				for (Element qqq_a : qqq_as) {
					if (qqq_a.text().contains(wcc)) {
						qygsQynbInfo.setPubDateTime(qqq_a.parent()
								.nextElementSibling().text());// 发布日期
						break;
					}
				}
				// qygsQynbInfo.setPubDateTime(qygsxx_qynb_info.get(
				// "qygsxx_qynb_list_pubdate").toString());// 发布日期
				Document qygsxx_qynb_info_qyjbqk_page = Jsoup
						.parseBodyFragment(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_qyjbqk").toString());
				// 企业公示信息->企业年报->企业基本信息
				EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
				Element qyjbxx_tbody = qygsxx_qynb_info_qyjbqk_page
						.select("table").get(0).select("tbody").get(0);
				Elements qygsxx_qynb_info_qyjbqk_ths = qyjbxx_tbody
						.select("th");
				int qygsxx_qynb_info_qyjbqk_ths_size = qygsxx_qynb_info_qyjbqk_ths
						.size();
				for (int i = 1; i < qygsxx_qynb_info_qyjbqk_ths_size; i++) {
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("注册号")
							|| qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
									.contains("统一社会信用代码")) {
						qygsQynbJbInfo.setNum(qyjbxx_tbody.select("td")
								.get(i - 1).text());// 注册号/统一社会信用代码
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("企业名称")) {
						qygsQynbJbInfo.setName(qyjbxx_tbody.select("td")
								.get(i - 1).text());// 企业名称
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("企业联系电话")) {
						qygsQynbJbInfo.setTel(qyjbxx_tbody.select("td")
								.get(i - 1).text());// 企业联系电话
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("邮政编码")) {
						qygsQynbJbInfo.setZipCode(qyjbxx_tbody.select("td")
								.get(i - 1).text());// 邮政编码
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("企业通信地址")) {
						qygsQynbJbInfo.setAddress(qyjbxx_tbody.select("td")
								.get(i - 1).text());// 企业通信地址
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("电子邮箱")) {
						qygsQynbJbInfo.setEmail(qyjbxx_tbody.select("td")
								.get(i - 1).text());// 电子邮箱
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("有限责任公司本年度是否发生股东股权转让")) {
						qygsQynbJbInfo.setIsStohrEquTransferred(qyjbxx_tbody
								.select("td").get(i - 1).text());// 有限责任公司本年度是否发生股东股权转让
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text()
							.contains("企业经营状态")
							|| qygsxx_qynb_info_qyjbqk_ths.get(i).text()
									.contains("企业登记状态")) {
						qygsQynbJbInfo.setOperatingStatus(qyjbxx_tbody
								.select("td").get(i - 1).text());// 企业经营状态
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("是否有网站或网店")) {
						qygsQynbJbInfo.setHasWebsiteOrStore(qyjbxx_tbody
								.select("td").get(i - 1).text());// 是否有网站或网店
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("企业是否有投资信息或购买其他公司股权")) {
						qygsQynbJbInfo
								.setHasInvestInfoOrPurchOtherCorpEqu(qyjbxx_tbody
										.select("td").get(i - 1).text());// 企业是否有投资信息或购买其他公司股权
					}
					if (qygsxx_qynb_info_qyjbqk_ths.get(i).text().trim()
							.contains("从业人数")) {
						qygsQynbJbInfo.setEmpNum(qyjbxx_tbody.select("td")
								.get(i - 1).text());// 从业人数
					}
				}
				qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);// 企业公示信息->企业年报->企业基本信息
				if (null != qygsxx_qynb_info
						.get("qygsxx_qynb_info_gdjczxx_page")
						&& !StringUtils.isEmpty(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_gdjczxx_page").toString())) {
					Document qygsxx_qynb_info_gdjczxx_page = Jsoup
							.parseBodyFragment(qygsxx_qynb_info.get(
									"qygsxx_qynb_info_gdjczxx_page").toString());
					Element chuziren = qygsxx_qynb_info_gdjczxx_page
							.select("table").get(0).select("tbody").get(0);
					List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();// 股东及出资信息
					if (null != chuziren) {
						Elements qyjbxx_gd_trs = chuziren.select("tr");
						int qyjbxx_gd_trs_size = qyjbxx_gd_trs.size();
						if (qyjbxx_gd_trs_size > 2) {
							for (int i = 2; i < qyjbxx_gd_trs_size; i++) {
								EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo = new EntpubAnnreportStohrinvestInfo();// 股东及出资信息
								qygsQynbGdjczInfo.setStockholder(qyjbxx_gd_trs
										.get(i).select("td").get(0).text());// 股东（发起人）
								qygsQynbGdjczInfo.setSubAmount(qyjbxx_gd_trs
										.get(i).select("td").get(1).text());// 认缴出资额（万元）
								qygsQynbGdjczInfo.setSubDateTime(qyjbxx_gd_trs
										.get(i).select("td").get(2).text());// 认缴出资时间
								qygsQynbGdjczInfo.setSubMethod(qyjbxx_gd_trs
										.get(i).select("td").get(3).text());// 认缴出资方式
								qygsQynbGdjczInfo.setPaidAmount(qyjbxx_gd_trs
										.get(i).select("td").get(4).text());// 实缴出资额（万元）
								qygsQynbGdjczInfo.setPaidDateTime(qyjbxx_gd_trs
										.get(i).select("td").get(5).text());// 实缴出资时间
								qygsQynbGdjczInfo.setPaidDateTime(qyjbxx_gd_trs
										.get(i).select("td").get(6).text());// 实缴出资方式
								qygsQynbGdjczInfos.add(qygsQynbGdjczInfo);
							}
						}
					}
					qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);// 企业公示信息->股东及出资信息
				}
				if (null != qygsxx_qynb_info.get("qygsxx_qynb_info_qyzcxx")
						&& !StringUtils.isEmpty(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_qyzcxx").toString())) {
					Document qygsxx_qynb_info_qyzcxx_page = Jsoup
							.parseBodyFragment(qygsxx_qynb_info.get(
									"qygsxx_qynb_info_qyzcxx").toString());
					// 企业资产状况信息（生产经营情况）
					EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
					Element qyjbxx_qyzczkxx = qygsxx_qynb_info_qyzcxx_page
							.select("table").get(0).select("tbody").get(0);
					Elements qyjbxx_qyzczkxx_ths = qyjbxx_qyzczkxx.select("th");
					int qyjbxx_qyzczkxx_ths_size = qyjbxx_qyzczkxx_ths.size();
					for (int i = 1; i < qyjbxx_qyzczkxx_ths_size; i++) {
						if (qyjbxx_qyzczkxx_ths.get(i).text().contains("资产总额")) {
							qygsQynbQyzczkInfo.setAssetAmount(qyjbxx_qyzczkxx
									.select("td").get(i - 1).text());// 资产总额
						}
						if (qyjbxx_qyzczkxx_ths.get(i).text()
								.contains("所有者权益合计")) {
							qygsQynbQyzczkInfo.setTotalEquity(qyjbxx_qyzczkxx
									.select("td").get(i - 1).text());// 所有者权益合计
						}
						if (qyjbxx_qyzczkxx_ths.get(i).text().contains("销售总额")) {
							qygsQynbQyzczkInfo.setSalesAmount(qyjbxx_qyzczkxx
									.select("td").get(i - 1).text());// 销售总额
						}
						if (qyjbxx_qyzczkxx_ths.get(i).text().contains("利润总额")) {
							qygsQynbQyzczkInfo.setProfitAmount(qyjbxx_qyzczkxx
									.select("td").get(i - 1).text());// 利润总额
						}
						if (qyjbxx_qyzczkxx_ths.get(i).text()
								.contains("销售总额中主营业务收入")
								|| qyjbxx_qyzczkxx_ths.get(i).text()
										.contains("主营业务收入")) {
							qygsQynbQyzczkInfo
									.setPriBusiIncomeInSalesAmount(qyjbxx_qyzczkxx
											.select("td").get(i - 1).text());// 销售总额中主营业务收入
						}
						if (qyjbxx_qyzczkxx_ths.get(i).text().contains("净利润")) {
							qygsQynbQyzczkInfo.setNetProfit(qyjbxx_qyzczkxx
									.select("td").get(i - 1).text());// 净利润
						}
						if (qyjbxx_qyzczkxx_ths.get(i).text().contains("纳税总额")) {
							qygsQynbQyzczkInfo.setTaxesAmount(qyjbxx_qyzczkxx
									.select("td").get(i - 1).text());// 纳税总额
						}
						if (qyjbxx_qyzczkxx_ths.get(i).text().contains("负债总额")) {
							qygsQynbQyzczkInfo
									.setLiabilityAmount(qyjbxx_qyzczkxx
											.select("td").get(i - 1).text());// 负债总额
						}
					}
					if (isDebug && null != qyjbxx_qyzczkxx) {
						qygsQynbQyzczkInfo.setHtml(qyjbxx_qyzczkxx.html());
					}
					qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);// 企业资产状况信息
				}
				if (null != qygsxx_qynb_info
						.get("qygsxx_qynb_info_dwtzxx_page")
						&& !StringUtils.isEmpty(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_dwtzxx_page").toString())) {
					Document qygsxx_qynb_info_dwtzxx_page = Jsoup
							.parseBodyFragment(qygsxx_qynb_info.get(
									"qygsxx_qynb_info_dwtzxx_page").toString());
					Elements dwtzxx_ets = qygsxx_qynb_info_dwtzxx_page
							.select("table");
					if (null != dwtzxx_ets && !dwtzxx_ets.isEmpty()
							&& dwtzxx_ets.size() > 0) {
						// 对外投资信息
						List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzxxInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
						Elements dwtzxx_trs = null;
						if (null != dwtzxx_ets.get(0).select("tbody")
								&& !dwtzxx_ets.get(0).select("tbody").isEmpty()) {
							dwtzxx_trs = dwtzxx_ets.get(0).select("tbody")
									.get(0).select("tr");
						}
						if (null != dwtzxx_trs && dwtzxx_trs.size() > 2) {
							for (int i = 2; i < dwtzxx_trs.size(); i++) {
								EntpubAnnreportExtinvestInfo qygsQynbDwtzxxInfo = new EntpubAnnreportExtinvestInfo();
								qygsQynbDwtzxxInfo.setEnterpriseName(dwtzxx_trs
										.get(i).select("td").get(0).text());// 投资设立企业或购买股权企业名称
								qygsQynbDwtzxxInfo.setRegNum(dwtzxx_trs.get(i)
										.select("td").get(1).text());// 注册号
								qygsQynbDwtzxxInfos.add(qygsQynbDwtzxxInfo);
							}
						}
						qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzxxInfos);// 对外投资信息
					}
				}
				if (null != qygsxx_qynb_info
						.get("qygsxx_qynb_info_dwtgbzdbxx_page")
						&& !StringUtils.isEmpty(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_dwtgbzdbxx_page").toString())) {
					Document qygsxx_qynb_info_dwtgbzdbxx_page = Jsoup
							.parseBodyFragment(qygsxx_qynb_info.get(
									"qygsxx_qynb_info_dwtgbzdbxx_page")
									.toString());
					Elements dwtgdb_ets = qygsxx_qynb_info_dwtgbzdbxx_page
							.select("table");
					if (null != dwtgdb_ets && !dwtgdb_ets.isEmpty()
							&& dwtgdb_ets.size() > 0) {
						// 对外提供保证担保信息
						List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();
						Elements dwtgdb_trs = dwtgdb_ets.get(0).select("tbody")
								.get(0).select("tr");
						if (null != dwtgdb_trs && dwtgdb_trs.size() > 2) {
							for (int i = 2; i < dwtgdb_trs.size(); i++) {
								EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
								qygsQynbDwtgbzdbInfo.setCreditor(dwtgdb_trs
										.get(i).select("td").get(0).text());// 债权人
								qygsQynbDwtgbzdbInfo.setDebtor(dwtgdb_trs
										.get(i).select("td").get(1).text());// 债务人
								qygsQynbDwtgbzdbInfo
										.setPriCredRightType(dwtgdb_trs.get(i)
												.select("td").get(2).text());// 主债权种类
								qygsQynbDwtgbzdbInfo
										.setPriCredRightAmount(dwtgdb_trs
												.get(i).select("td").get(3)
												.text());// 主债权数额
								qygsQynbDwtgbzdbInfo
										.setExeDebtDeadline(dwtgdb_trs.get(i)
												.select("td").get(4).text());// 履行债务的期限
								qygsQynbDwtgbzdbInfo
										.setGuaranteePeriod(dwtgdb_trs.get(i)
												.select("td").get(5).text());// 保证的期间
								qygsQynbDwtgbzdbInfo
										.setGuaranteeMethod(dwtgdb_trs.get(i)
												.select("td").get(6).text());// 保证的方式
								qygsQynbDwtgbzdbInfo
										.setGuaranteeScope(dwtgdb_trs.get(i)
												.select("td").get(7).text());// 保证担保的范围
								qygsQynbDwtgbzdbInfos.add(qygsQynbDwtgbzdbInfo);
							}
						}
						qygsQynbInfo
								.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);// 对外提供保证担保信息
					}
				}
				if (null != qygsxx_qynb_info
						.get("qygsxx_qynb_info_wzhwdxx_page")
						&& !StringUtils.isEmpty(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_wzhwdxx_page").toString())) {
					Document qygsxx_qynb_info_wzhwdxx_page = Jsoup
							.parseBodyFragment(qygsxx_qynb_info.get(
									"qygsxx_qynb_info_wzhwdxx_page").toString());
					// 网站或网店信息
					List<EntpubAnnreportWebsiteInfo> qygsQynbwzhwdInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
					if (null != qygsxx_qynb_info_wzhwdxx_page.select("table")) {
						Element ta_tb = qygsxx_qynb_info_wzhwdxx_page
								.select("table").get(0).select("tbody").get(0);
						Elements ta_tb_trs = ta_tb.select("tr");
						int ta_tb_trs_size = ta_tb_trs.size();
						if (ta_tb_trs_size > 2) {
							for (int j = 2; j < ta_tb_trs_size; j++) {
								EntpubAnnreportWebsiteInfo qygsQynbwzhwdInfo = new EntpubAnnreportWebsiteInfo();
								qygsQynbwzhwdInfo.setType(ta_tb_trs.get(j)
										.select("td").get(0).text());// 类型
								qygsQynbwzhwdInfo.setName(ta_tb_trs.get(j)
										.select("td").get(1).text());// 名称
								qygsQynbwzhwdInfo.setWebsite(ta_tb_trs.get(j)
										.select("td").get(2).text());// 网址
								qygsQynbwzhwdInfos.add(qygsQynbwzhwdInfo);
							}
						}
					}
					qygsQynbInfo.setWebsiteInfos(qygsQynbwzhwdInfos);// 网站或网店信息
				}
				if (null != qygsxx_qynb_info.get("qygsxx_qynb_info_xgjl_page")
						&& !StringUtils.isEmpty(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_xgjl_page").toString())) {
					Document qygsxx_qynb_info_xgjl_page = Jsoup
							.parseBodyFragment(qygsxx_qynb_info.get(
									"qygsxx_qynb_info_xgjl_page").toString());
					// 修改记录
					List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
					if (null != qygsxx_qynb_info_xgjl_page.select("table")) {
						Elements xgjl_trs = qygsxx_qynb_info_xgjl_page
								.select("table").get(0).select("tbody").get(0)
								.select("tr");
						int xgjl_trs_size = xgjl_trs.size();
						if (xgjl_trs_size > 2) {
							for (int j = 2; j < xgjl_trs_size; j++) {
								EntpubAnnreportModifyInfo qygsQynbXgjlInfo = new EntpubAnnreportModifyInfo();
								qygsQynbXgjlInfo.setItem(xgjl_trs.get(j)
										.select("td").get(1).text());// 修改事项
								qygsQynbXgjlInfo.setPreContent(xgjl_trs.get(j)
										.select("td").get(2).text());// 修改前
								qygsQynbXgjlInfo.setPostContent(xgjl_trs.get(j)
										.select("td").get(3).text());// 修改后
								qygsQynbXgjlInfo.setDateTime(xgjl_trs.get(j)
										.select("td").get(4).text());// 修改日期
								qygsQynbXgjlInfos.add(qygsQynbXgjlInfo);
							}
						}
					}
					qygsQynbInfo.setChangeInfos(qygsQynbXgjlInfos);// 修改记录
				}
				qygsQynbInfos.add(qygsQynbInfo);// 企业公示信息->企业年报
			}
		}
		qygsInfo.setAnnReports(qygsQynbInfos);// 企业公示信息->企业年报

		// 企业公示信息->股东及出资信息
		EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
		if (null != htmlMap.get("qygsxx_gdjczxx")
				&& !StringUtils.isEmpty(htmlMap.get("qygsxx_gdjczxx")
						.toString())) {
			Document qygsxx_gdjczxx = Jsoup.parseBodyFragment(htmlMap.get(
					"qygsxx_gdjczxx").toString());
			// 企业公示信息->股东及出资信息
			List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
			Element qygsxx_gdjczxx_tb = qygsxx_gdjczxx.select("table").get(0);
			if (null != qygsxx_gdjczxx_tb) {
				Elements gdjczxx_trs = qygsxx_gdjczxx_tb.select("tbody").get(0)
						.select("tr");
				if (null != gdjczxx_trs && gdjczxx_trs.size() > 3) {
					for (int j = 3; j < gdjczxx_trs.size(); j++) {
						Elements wdqs = gdjczxx_trs.get(j).select("td");
						if (null != wdqs && wdqs.size() > 8) {
							// 企业公示信息->股东及出资信息
							EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new EntpubSStohrinvestInfo();
							Element gdjczxx_td = gdjczxx_trs.get(j)
									.select("td").get(0);
							qygsGdjczGdjczInfo.setStockholder(gdjczxx_trs
									.get(j).select("td").get(0).text());// 股东
							qygsGdjczGdjczInfo.setSubAmount(gdjczxx_trs.get(j)
									.select("td").get(1).text());// 认缴额（万元）
							qygsGdjczGdjczInfo.setPaidAmount(gdjczxx_trs.get(j)
									.select("td").get(2).text());// 实缴额（万元）
							int wd = 1;
							if (gdjczxx_td.hasAttr("rowspan")) {
								String rowspan = gdjczxx_td.attr("rowspan");
								if (null != rowspan
										&& !StringUtils.isEmpty(rowspan)) {
									wd = Integer.parseInt(rowspan.trim());
								}
							}
							List<Detail> subDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
							List<Detail> paidDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
							for (int i = 0; i < wd; i++) {
								Elements gdjczxx_tds = gdjczxx_trs.get(j + i)
										.select("td");
								if (null != gdjczxx_tds
										&& gdjczxx_tds.size() > 8) {
									// 认缴明细
									EntpubSStohrinvestInfo.Detail rjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
									rjDetail.method = gdjczxx_trs.get(j + i)
											.select("td").get(3).text();// 认缴出资方式
									rjDetail.amount = gdjczxx_trs.get(j + i)
											.select("td").get(4).text();// 认缴出资额（万元）
									rjDetail.dateTime = gdjczxx_trs.get(j + i)
											.select("td").get(5).text();// 认缴出资日期

									if (null != gdjczxx_tds
											&& gdjczxx_tds.size() > 9) {
										rjDetail.showDate = gdjczxx_trs
												.get(j + i).select("td").get(9)
												.text();// 公示日期
									}
									subDetails.add(rjDetail);
									// 实缴明细
									EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
									sjDetail.method = gdjczxx_trs.get(j + i)
											.select("td").get(6).text();// 实缴出资方式
									sjDetail.amount = gdjczxx_trs.get(j + i)
											.select("td").get(7).text();// 实缴出资额（万元）
									sjDetail.dateTime = gdjczxx_trs.get(j + i)
											.select("td").get(8).text();// 实缴出资日期
									if (null != gdjczxx_tds
											&& gdjczxx_tds.size() > 9) {
										sjDetail.showDate = gdjczxx_trs
												.get(j + i).select("td").get(9)
												.text();// 公示日期
									}
									paidDetails.add(sjDetail);
								} else {
									// 认缴明细
									EntpubSStohrinvestInfo.Detail rjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
									rjDetail.method = gdjczxx_trs.get(j + i)
											.select("td").get(0).text();// 认缴出资方式
									rjDetail.amount = gdjczxx_trs.get(j + i)
											.select("td").get(1).text();// 认缴出资额（万元）
									rjDetail.dateTime = gdjczxx_trs.get(j + i)
											.select("td").get(2).text();// 认缴出资日期
									if (null != gdjczxx_tds
											&& gdjczxx_tds.size() > 9) {
										rjDetail.showDate = gdjczxx_trs
												.get(j + i).select("td").get(9)
												.text();// 公示日期
									}
									subDetails.add(rjDetail);
									// 实缴明细
									EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
									sjDetail.method = gdjczxx_trs.get(j + i)
											.select("td").get(3).text();// 实缴出资方式
									sjDetail.amount = gdjczxx_trs.get(j + i)
											.select("td").get(4).text();// 实缴出资额（万元）
									sjDetail.dateTime = gdjczxx_trs.get(j + i)
											.select("td").get(5).text();// 实缴出资日期
									if (null != gdjczxx_tds
											&& gdjczxx_tds.size() > 9) {
										sjDetail.showDate = gdjczxx_trs
												.get(j + i).select("td").get(9)
												.text();// 公示日期
									}
									paidDetails.add(sjDetail);
								}
								qygsGdjczGdjczInfo.setSubDetails(subDetails);
								qygsGdjczGdjczInfo.setPaidDetails(paidDetails);
							}
							qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
						}
					}
				}
				qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息

				// 变更信息
				List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
				Element bgxx_tb = qygsxx_gdjczxx.select("table").get(1);
				if (null != bgxx_tb) {
					Elements bgxx_trs = bgxx_tb.select("tbody").get(0)
							.select("tr");
					int bgxx_trs_size = bgxx_trs.size();
					if (bgxx_trs_size > 2) {
						for (int i = 2; i < bgxx_trs_size; i++) {
							EntpubStohrinvestChangeInfo gygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
							gygsGdjczBgInfo.setItem(bgxx_trs.get(i)
									.select("td").get(1).text());// 变更事项
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
		}
		qygsInfo.setStohrInvestInfo(qygsGdjczInfo);// 企业公示信息->股东及出资信息

		// 企业公示信息->股权变更信息
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		if (null != htmlMap.get("qygsxx_gqbgxx")
				&& !StringUtils
						.isEmpty(htmlMap.get("qygsxx_gqbgxx").toString())) {
			Document qygsxx_gqbgxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_gqbgxx")));
			// 企业公示信息->股权变更信息列表
			List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
			Element qygsxx_gqbgxx_td = qygsxx_gqbgxx_page.select("table")
					.get(0).select("tbody").get(0);
			// 企业公示信息->股权变更信息
			if (null != qygsxx_gqbgxx_td) {
				Elements qygsxx_gqbgxx_trs = qygsxx_gqbgxx_td.select("tr");
				int qygsxx_gqbgxx_trs_size = qygsxx_gqbgxx_trs.size();
				if (qygsxx_gqbgxx_trs_size > 2) {
					for (int i = 2; i < qygsxx_gqbgxx_trs_size; i++) {
						EntpubEEquchangeInfo gygsGqbgGqbgInfo = new EntpubEEquchangeInfo();
						gygsGqbgGqbgInfo.setStockholder(qygsxx_gqbgxx_trs
								.get(i).select("td").get(1).text());// 股东
						gygsGqbgGqbgInfo.setPreOwnershipRatio(qygsxx_gqbgxx_trs
								.get(i).select("td").get(2).text());// 变更前股权比例
						gygsGqbgGqbgInfo
								.setPostOwnershipRatio(qygsxx_gqbgxx_trs.get(i)
										.select("td").get(3).text());// 变更后股权比例
						gygsGqbgGqbgInfo.setDateTime(qygsxx_gqbgxx_trs.get(i)
								.select("td").get(4).text());// 股权变更日期
						qygsGqbgGqbgInfos.add(gygsGqbgGqbgInfo);
					}
				}
				qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);// 企业公示信息->股权变更信息
				if (isDebug) {
					qygsGqbgInfo.setHtml(qygsxx_gqbgxx_td.html());
				}
			}
			qygsInfo.setEquChangeInfo(qygsGqbgInfo);// 企业公示信息->股权变更信息
		}

		// 企业公示信息->行政许可信息
		EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
		if (null != htmlMap.get("qygsxx_xzxkxx")
				&& !StringUtils
						.isEmpty(htmlMap.get("qygsxx_xzxkxx").toString())) {
			Document qygsxx_xzxkxx_page = Jsoup.parseBodyFragment(htmlMap.get(
					"qygsxx_xzxkxx").toString());
			// 企业公示信息->行政许可信息列表
			List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
			Elements xingzhengxuke = qygsxx_xzxkxx_page.select("table");
			if (null != xingzhengxuke) {
				Elements xingzhengxuke_trs = xingzhengxuke.get(0)
						.select("tbody").get(0).select("tr");
				if (null != xingzhengxuke_trs && xingzhengxuke_trs.size() > 2) {
					for (int i = 2; i < xingzhengxuke_trs.size(); i++) {
						// 企业公示信息->行政许可信息列表
						EntpubAAdmlicInfo qygsXzxkXzxkInfo = new EntpubAAdmlicInfo();
						qygsXzxkXzxkInfo.setLicenceNum(xingzhengxuke_trs.get(i)
								.select("td").get(1).text());// 许可文件编号
						qygsXzxkXzxkInfo.setLicenceName(xingzhengxuke_trs
								.get(i).select("td").get(2).text());// 许可文件名称
						qygsXzxkXzxkInfo.setStartDateTime(xingzhengxuke_trs
								.get(i).select("td").get(3).text());// 有效期自
						qygsXzxkXzxkInfo.setEndDateTime(xingzhengxuke_trs
								.get(i).select("td").get(4).text());// 有效期至
						qygsXzxkXzxkInfo.setDeciAuthority(xingzhengxuke_trs
								.get(i).select("td").get(5).text());// 许可机关
						qygsXzxkXzxkInfo.setContent(xingzhengxuke_trs.get(i)
								.select("td").get(6).text());// 许可内容
						qygsXzxkXzxkInfo.setStatus(xingzhengxuke_trs.get(i)
								.select("td").get(7).text());// 状态
						// TODO
						if (!StringUtils.isEmpty(xingzhengxuke_trs.get(i)
								.select("td").get(9).text())
								&& xingzhengxuke_trs.get(i).select("td").get(9)
										.text().trim().equals("详情")) {
							// TODO
						} else {
							qygsXzxkXzxkInfo.setDetail(xingzhengxuke_trs.get(i)
									.select("td").get(9).text());// 详情（有问题，暂缓处理）
						}
						qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
					}
				}
				qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
				if (isDebug) {
					qygsXzxkInfo.setHtml(xingzhengxuke.get(0).html());
				}
			}
		}
		qygsInfo.setAdmLicInfo(qygsXzxkInfo);
		// 企业公示信息->行政许可信息

		// 企业公示信息->知识产权出质登记信息
		EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
		if (null != htmlMap.get("qygsxx_zscqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("qygsxx_zscqczdjxx")))) {
			Document qygsxx_zscqczdjxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_zscqczdjxx")));
			Elements zhishichanquan_et = qygsxx_zscqczdjxx_page.select("table");
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
						qygsZscqczdjZscqczdjInfo.setName(zhishichanquan_trs
								.get(i).select("td").get(2).text());// 名称
						qygsZscqczdjZscqczdjInfo.setType(zhishichanquan_trs
								.get(i).select("td").get(3).text());// 种类
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
						qygsZscqczdjZscqczdjInfo
								.setChangeSitu(zhishichanquan_trs.get(i)
										.select("td").get(9).text());// 变化情况
						qygsZscqczdjZscqczdjInfos.add(qygsZscqczdjZscqczdjInfo);
					}
				}
				qygsZscqczdjInfo
						.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);// 企业公示信息->知识产权出质登记信息
				if (isDebug) {
					qygsZscqczdjInfo.setHtml(zhishichanquan_et.html());
				}
			}
		}
		qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);// 企业公示信息->知识产权出质登记信息

		// 企业公示信息->行政处罚信息
		EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
		if (null != htmlMap.get("qygsxx_xzcfxx")
				&& !StringUtils
						.isEmpty(htmlMap.get("qygsxx_xzcfxx").toString())) {
			Document qygsxx_xzcfxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_xzcfxx")));
			Elements xingzhengchufa_et = qygsxx_xzcfxx_page.select("table");
			// 企业公示信息->行政处罚信息列表
			List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
			if (null != xingzhengchufa_et) {
				Elements xingzhengchufa_trs = xingzhengchufa_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 2) {
					for (int i = 2; i < xingzhengchufa_trs.size(); i++) {
						Elements xingzhengchufa_tds = xingzhengchufa_trs.get(i)
								.select("td");
						// 企业公示信息->行政处罚信息
						EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();
						qygsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_tds
								.get(1).text());// 行政处罚决定书文号
						qygsXzcfXzcfInfo.setPunishContent(xingzhengchufa_tds
								.get(2).text());// 行政处罚内容
						qygsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_tds
								.get(3).text());// 作出行政处罚决定机关名称
						qygsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_tds
								.get(4).text());// 作出行政处罚决定日期
						if (null != xingzhengchufa_tds
								&& xingzhengchufa_tds.size() > 7) {
							qygsXzcfXzcfInfo
									.setIllegalActType(xingzhengchufa_tds
											.get(5).text());// 违法行为类型
							qygsXzcfXzcfInfo.setShowDate(xingzhengchufa_tds
									.get(6).text());// 公示日期
							qygsXzcfXzcfInfo.setNote(xingzhengchufa_tds.get(7)
									.text());// 备注
						} else {
							qygsXzcfXzcfInfo.setShowDate(xingzhengchufa_tds
									.get(5).text());// 公示日期
							qygsXzcfXzcfInfo.setNote(xingzhengchufa_tds.get(6)
									.text());// 备注
						}
						qygsXzcfXzcfInfos.add(qygsXzcfXzcfInfo);
					}
				}
				if (isDebug) {
					qygsXzcfInfo.setHtml(xingzhengchufa_et.html());
				}
			}
			qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);// 企业公示信息->行政处罚信息
		}
		qygsInfo.setAdmPunishInfo(qygsXzcfInfo);// 企业公示信息->行政处罚信息

		// 企业公示信息
		gsxtFeedJson.setEntPubInfo(qygsInfo);

		// 其他部门公示信息
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();

		// 其他部门公示信息->行政许可信息
		OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
		if (null != htmlMap.get("qtbmgsxx_xzxkxx")
				&& !StringUtils.isEmpty(htmlMap.get("qtbmgsxx_xzxkxx")
						.toString())) {
			Document qtbmgsxx_xzxkxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qtbmgsxx_xzxkxx")));
			Elements xingzhengxuke_et = qtbmgsxx_xzxkxx_page.select("table");
			// 其他部门公示信息->行政许可信息列表
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			if (null != xingzhengxuke_et) {
				Elements xingzhengxuke_trs = xingzhengxuke_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengxuke_trs && xingzhengxuke_trs.size() > 2) {
					for (int i = 2; i < xingzhengxuke_trs.size(); i++) {
						// 其他部门公示信息->行政许可信息列表
						OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo = new OthrdeptpubAAdmlicInfo();
						qtbmgsXzxkXzxkInfo.setLicenceNum(xingzhengxuke_trs
								.get(i).select("td").get(1).text());// 许可文件编号
						qtbmgsXzxkXzxkInfo.setLicenceName(xingzhengxuke_trs
								.get(i).select("td").get(2).text());// 许可文件名称
						qtbmgsXzxkXzxkInfo.setStartDateTime(xingzhengxuke_trs
								.get(i).select("td").get(3).text());// 有效期自
						qtbmgsXzxkXzxkInfo.setEndDateTime(xingzhengxuke_trs
								.get(i).select("td").get(4).text());// 有效期至
						qtbmgsXzxkXzxkInfo.setDeciAuthority(xingzhengxuke_trs
								.get(i).select("td").get(5).text());// 许可机关
						qtbmgsXzxkXzxkInfo.setContent(xingzhengxuke_trs.get(i)
								.select("td").get(6).text());// 许可内容
						qtbmgsXzxkXzxkInfo.setStatus(xingzhengxuke_trs.get(i)
								.select("td").get(7).text());// 状态
						qtbmgsXzxkXzxkInfo.setDetail(xingzhengxuke_trs.get(i)
								.select("td").get(8).text());// 详情
						qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
					}
				}
				if (isDebug) {
					qtbmgsXzxkInfo.setHtml(xingzhengxuke_et.html());
				}
			}
			qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);// 其他部门公示信息->行政许可信息列表
		}
		qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);// 其他部门公示信息->行政许可信息

		// 其他部门公示信息->行政处罚信息
		OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
		if (null != htmlMap.get("qtbmgsxx_xzcfxx")
				&& !StringUtils.isEmpty(htmlMap.get("qtbmgsxx_xzcfxx")
						.toString())) {
			Document qtbmgsxx_xzcfxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qtbmgsxx_xzcfxx")));
			Elements xingzhengchufa_et = qtbmgsxx_xzcfxx_page.select("table");
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
						qtbmgsXzcfXzcfInfo.setDetail("");// 详情（暂无）
						qtbmgsXzcfXzcfInfo.setNote("");// 备注（暂无）
						qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
					}
				}
				if (isDebug) {
					qtbmgsXzcfInfo.setHtml(xingzhengchufa_et.html());
				}
			}
			qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);// 其他部门公示信息->行政处罚信息列表
		}
		qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);// 其他部门公示信息->行政处罚信息

		// 其他部门公示信息
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);

		// 司法协助公示信息
		JudasspubInfo sfxzgsInfo = new JudasspubInfo();

		// 司法协助公示信息->股权冻结信息
		JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
		if (null != htmlMap.get("sfxzgsxx")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx").toString())) {
			Document sfxzgsxx_gqdjxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx")));
			Element guquandongjie_et = sfxzgsxx_gqdjxx_page
					.getElementById("sifaxiezhu");
			// 司法协助公示信息->股权冻结信息列表
			List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
			if (null != guquandongjie_et) {
				Elements guquandongjie_trs = guquandongjie_et.select("table")
						.select("tbody").get(0).select("tr");
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
					sfxzgsGqdjInfo.setHtml(guquandongjie_et.html());
				}
			}
			sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);// 司法协助公示信息->股权冻结信息列表
		}
		sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);// 司法协助公示信息->股权冻结信息

		// 司法协助公示信息->股东变更信息
		JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
		if (null != htmlMap.get("sfxzgsxx")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx").toString())) {
			Document sfxzgsxx_gqbgxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx")));
			Element guquanbiangeng_et = sfxzgsxx_gqbgxx_page
					.getElementById("sifagudong");
			// 司法协助公示信息->股东变更信息列表
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			if (null != guquanbiangeng_et) {
				Elements guquanbiangeng_trs = guquanbiangeng_et.select("table")
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
					sfxzgsGdbgInfo.setHtml(guquanbiangeng_et.html());
				}
			}
			sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);// 司法协助公示信息->股东变更信息列表
		}
		sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);// 司法协助公示信息->股东变更信息列表

		// 司法协助公示信息
		gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);

		return gsxtFeedJson;

	}

}

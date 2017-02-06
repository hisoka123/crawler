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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtJiangsuParser extends AbstractGsxtParser {

	private static final Logger LOOGER = LoggerFactory
			.getLogger(GsxtJiangsuParser.class);

	public AicFeedJson getJiangsuResultObj(String html, boolean isDebug) {

		LOOGER.info("The method of GsxtJiangsuParser.getJiangsuResultObj is begin !");

		Gson gson = new GsonBuilder().create();
		Map<String, Object> htmlMap = gson.fromJson(html,
				new TypeToken<Map<String, Object>>() {
				}.getType());

		if (null == htmlMap.get("gsgsxx_djxx")
				|| null == String.valueOf(htmlMap.get("gsgsxx_djxx"))
				|| StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_djxx")))) {
			return null;
		}

		// 工商系统bean
		AicFeedJson gsxtFeedJson = new AicFeedJson();

		// 工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();

		// 工商公示信息->登记信息
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();
		Document gsgsxx_djxx = Jsoup.parseBodyFragment(htmlMap.get(
				"gsgsxx_djxx").toString());
		// 工商公示信息->登记信息->基本信息
		Element baseinfo = gsgsxx_djxx.getElementById("jibenxinxi")
				.getElementById("baseinfo");
		Elements ths = baseinfo.select("tbody").select("tr").select("th");
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
		for (int i = 1, qq = ths.size(); i < qq; i++) {
			Element wd_th = ths.get(i);
			if (ths.get(i).text().trim().contains("统一社会信用代码")
					|| ths.get(i).text().trim().equalsIgnoreCase("注册号")) {
				gsgsDjJbInfo.setNum(baseinfo.select("tbody").select("tr")
						.select("td").get(i - 1).text());// 注册号或信用代码
				continue;
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("名称")) {
				gsgsDjJbInfo.setName(baseinfo.select("tbody").select("tr")
						.select("td").get(i - 1).text());// 名称
				continue;
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("类型")) {
				gsgsDjJbInfo.setType(baseinfo.select("tbody").select("tr")
						.select("td").get(i - 1).text());// 类型
				continue;
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("负责人")
					|| ths.get(i).text().trim().equalsIgnoreCase("法定代表人")
					|| ths.get(i).text().trim().contains("经营者")
					|| ths.get(i).text().trim().contains("执行事务合伙人")
					|| ths.get(i).text().trim().contains("投资人")) {
				gsgsDjJbInfo.setLegalRepr(baseinfo.select("tbody").select("tr")
						.select("td").get(i - 1).text());// 法定代表人/经营者
				continue;
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("注册资本")) {
				gsgsDjJbInfo.setRegCapital(baseinfo.select("tbody")
						.select("tr").select("td").get(i - 1).text());// 注册资本
				continue;
			}
			if (wd_th.text().equalsIgnoreCase("成立日期")
					|| wd_th.text().trim().equalsIgnoreCase("注册日期")) {
				gsgsDjJbInfo.setRegDateTime(wd_th.nextElementSibling().text());// 成立日期
				continue;
			}
			if (wd_th.text().contains("营业场所") || wd_th.text().contains("住所")
					|| wd_th.text().contains("主要经营场所")
					|| wd_th.text().contains("经营场所")) {
				gsgsDjJbInfo.setAddress(wd_th.nextElementSibling().text());// 经营场所/住所
				continue;
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("营业期限自")
					|| ths.get(i).text().trim().equalsIgnoreCase("合伙期限自")) {
				gsgsDjJbInfo.setStartDateTime(baseinfo.select("tbody")
						.select("tr").select("td").get(i - 1).text());// 营业期限自（即营业开始日期）
				continue;
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("营业期限至")
					|| ths.get(i).text().trim().equalsIgnoreCase("合伙期限至")) {
				gsgsDjJbInfo.setEndDateTime(baseinfo.select("tbody")
						.select("tr").select("td").get(i - 1).text());// 营业期限至（即营业结束日期）
				continue;
			}
			if (ths.get(i).text().trim().contains("经营范围")) {
				gsgsDjJbInfo.setBusinessScope(baseinfo.select("tbody")
						.select("tr").select("td").get(i - 1).text());// 经营范围
				continue;
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("登记机关")) {
				gsgsDjJbInfo.setRegAuthority(baseinfo.select("tbody")
						.select("tr").select("td").get(i - 1).text());// 登记机关
				continue;
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("核准日期")) {
				gsgsDjJbInfo.setApprovalDateTime(baseinfo.select("tbody")
						.select("tr").select("td").get(i - 1).text());// 核准日期
				continue;
			}
			if (wd_th.text().contains("登记状态") || wd_th.text().contains("经营状态")) {
				gsgsDjJbInfo.setRegStatus(wd_th.nextElementSibling().text());// 登记状态
				continue;
			}
			if (wd_th.text().contains("组成形式")) {
				gsgsDjJbInfo.setFormType(wd_th.nextElementSibling().text());// 组成形式
				continue;
			}
			if (ths.get(i).text().trim().contains("吊销日期")) {
				gsgsDjJbInfo.setRevokeDate(baseinfo.select("tr").select("td")
						.get(i - 1).text());// 吊销日期
				continue;
			}
		}
		if (isDebug && null != baseinfo) {
			gsgsDjJbInfo.setHtml(baseinfo.html());// 基本信息
		}
		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);// 基本信息
		// 工商公示信息->登记信息->股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
		Element touziren_tbody = gsgsxx_djxx.getElementById("jibenxinxi")
				.getElementById("touziren").select("tbody").get(0);
		if (null != touziren_tbody) {
			Elements touziren_trs = touziren_tbody.select("tr");
			int touziren_nums = touziren_trs.size();
			if (touziren_nums > 2) {
				if (!touziren_trs.get(2).select("td").get(0).text()
						.contains("{NO_}")) {
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
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = (List<Map<String, Object>>) htmlMap
				.get("gsgsxx_djxx_tzrxx_xqs");
		List<AicpubRegStohrStohrinvestInfo> gsgsDjGdGdjczInfos = new ArrayList<AicpubRegStohrStohrinvestInfo>();
		if (null != gsgsxx_djxx_tzrxx_xqs && !gsgsxx_djxx_tzrxx_xqs.isEmpty()) {
			int gsgsxx_djxx_tzrxx_xqs_size = gsgsxx_djxx_tzrxx_xqs.size();
			if (gsgsxx_djxx_tzrxx_xqs_size > 0) {
				for (Map<String, Object> gsgsxx_djxx_tzrxx_xq : gsgsxx_djxx_tzrxx_xqs) {
					Document gsgsxx_djxx_tzrxx_xq_page = Jsoup
							.parseBodyFragment(gsgsxx_djxx_tzrxx_xq.get(
									"gsgsxx_djxx_tzrxx_xq").toString());
					Elements gsgsxx_djxx_tzrxx_xq_trs = gsgsxx_djxx_tzrxx_xq_page
							.getElementById("touziren").select("tbody")
							.select("tr");
					if (null != gsgsxx_djxx_tzrxx_xq_trs
							&& gsgsxx_djxx_tzrxx_xq_trs.size() > 2) {
						AicpubRegStohrStohrinvestInfo gsgsDjGdGdjczInfo = new AicpubRegStohrStohrinvestInfo();
						List<AicpubRegStohrStohrinvestInfo.AmountDetail> subAmountDetails = new ArrayList<AicpubRegStohrStohrinvestInfo.AmountDetail>();
						List<AicpubRegStohrStohrinvestInfo.AmountDetail> paidAmountDetails = new ArrayList<AicpubRegStohrStohrinvestInfo.AmountDetail>();
						gsgsDjGdGdjczInfo
								.setStockholder(gsgsxx_djxx_tzrxx_xq_trs.get(2)
										.select("td").get(0).text());// 股东
						gsgsDjGdGdjczInfo.setStockType(gsgsxx_djxx_tzrxx_xq_trs
								.get(2).select("td").get(1).text());// 投资人类型
						// gsgsDjGdGdjczInfo.setSubAmount(gsgsxx_djxx_tzrxx_xq_trs
						// .get(2).select("td").get(3).text());// 认缴额（万元）
						// gsgsDjGdGdjczInfo
						// .setPaidAmount(gsgsxx_djxx_tzrxx_xq_trs.get(2)
						// .select("td").get(6).text());// 实缴额（万元）
						for (int i = 2, gsgsxx_djxx_tzrxx_xq_trs_size = gsgsxx_djxx_tzrxx_xq_trs
								.size(); i < gsgsxx_djxx_tzrxx_xq_trs_size; i++) {
							Elements gsgsxx_djxx_tzrxx_xq_tds = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td");
							if (null != gsgsxx_djxx_tzrxx_xq_tds) {
								if (gsgsxx_djxx_tzrxx_xq_tds.size() > 7) {
									AicpubRegStohrStohrinvestInfo.AmountDetail subamountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
									AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
									subamountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
											.get(2).select("td").get(2).text();// 出资方式
									subamountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
											.get(i).select("td").get(3).text();// 认缴出资额
									subamountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
											.get(i).select("td").get(4).text();// 出资日期
									paidAmountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
											.get(i).select("td").get(5).text();// 出资方式
									paidAmountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
											.get(i).select("td").get(6).text();// 实缴出资额
									paidAmountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
											.get(i).select("td").get(7).text();// 实缴时间
									subAmountDetails.add(subamountDetail);
									paidAmountDetails.add(paidAmountDetail);
								} else {
									if (gsgsxx_djxx_tzrxx_xq_tds.size() > 4) {
										AicpubRegStohrStohrinvestInfo.AmountDetail subamountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
										AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
										subamountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
												.get(2).select("td").get(2)
												.text();// 认缴出资方式
										subamountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
												.get(i).select("td").get(0)
												.text();// 认缴出资额
										subamountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
												.get(i).select("td").get(1)
												.text();// 认缴出资日期
										paidAmountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
												.get(i).select("td").get(2)
												.text();// 实缴出资方式
										paidAmountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
												.get(i).select("td").get(3)
												.text();// 实缴出资额
										paidAmountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
												.get(i).select("td").get(4)
												.text();// 实缴实缴时间
										subAmountDetails.add(subamountDetail);
										paidAmountDetails.add(paidAmountDetail);
									}
								}
							}
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
		Element biangeng = gsgsxx_djxx.getElementById("biangeng")
				.select("tbody").get(0);
		if (null != biangeng) {
			Elements biangeng_trs = biangeng.select("tr");
			int biangeng_trs_size = biangeng_trs.size();
			if (biangeng_trs_size > 2) {
				for (int i = 2; i < biangeng_trs_size; i++) {
					AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
					gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td")
							.get(0).text());// 变更事项
					Elements biangeng_one = biangeng_trs.get(i).select("td")
							.get(1).getElementsByTag("span");
					if (null != biangeng_one && biangeng_one.size() > 0) {
						gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
								.select("td").get(1).select("span").get(0)
								.attr("completedata"));// 变更前内容
					} else {
						gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
								.select("td").get(1).text());// 变更前内容
					}
					Elements biangeng_two = biangeng_trs.get(i).select("td")
							.get(2).getElementsByTag("span");
					if (null != biangeng_two && biangeng_two.size() > 0) {
						gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
								.select("td").get(2).select("span").get(0)
								.attr("completedata"));// 变更后内容
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
		if (null != htmlMap.get("gsgsxx_baxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_baxx").toString())) {
			Document gsgsxx_baxx = Jsoup.parseBodyFragment(htmlMap.get(
					"gsgsxx_baxx").toString());
			Elements beian_zyryxx = gsgsxx_baxx.getElementById("beian")
					.getElementsByAttributeValue("name",
							"zhuyaorenyuanxinxiTAB");
			// 工商公示信息->备案信息->主要人员信息
			List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
			Elements beian_zyryxx_trs = null;
			int beian_zyryxx_size = 0;
			if (null != beian_zyryxx && !beian_zyryxx.isEmpty()) {
				beian_zyryxx_trs = beian_zyryxx.get(0).select("tr");
				if (null != beian_zyryxx_trs) {
					beian_zyryxx_size = beian_zyryxx_trs.size();
				}
			}
			if (beian_zyryxx_size > 2) {
				if (!beian_zyryxx_trs.get(2).select("td").get(1).text()
						.contains("{PERSON_NAME}")) {
					for (int i = 2; i < beian_zyryxx_size; i++) {
						int beian_tds_size = beian_zyryxx_trs.get(i)
								.select("td").size();
						String name1 = beian_zyryxx_trs.get(i).select("td")
								.get(1).text();
						String position1 = beian_zyryxx_trs.get(i).select("td")
								.get(2).text();
						String name2 = "";
						String position2 = "";
						if (beian_tds_size > 5) {
							name2 = beian_zyryxx_trs.get(i).select("td").get(4)
									.text();
							position2 = beian_zyryxx_trs.get(i).select("td")
									.get(5).text();
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
			Elements beian_fzjgxx_ets = gsgsxx_baxx.getElementById("beian")
					.getElementsByAttributeValue("name", "fengongsixinxiTAB");
			Elements beian_fzjgxx = null;
			if (null != beian_fzjgxx_ets && !beian_fzjgxx_ets.isEmpty()) {
				beian_fzjgxx = beian_fzjgxx_ets.get(0).select("tr");
			}
			if (null != beian_fzjgxx && beian_fzjgxx.size() > 2) {
				String biaoti = beian_fzjgxx.get(0).select("th").get(0).text()
						.trim();
				if (biaoti.contains("分支机构信息")
						&& !beian_fzjgxx.get(2).select("td").get(1).text()
								.contains("{DIST_REG_NO}")) {
					for (int i = 2; i < beian_fzjgxx.size(); i++) {
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
				} else if (biaoti.contains("分公司信息")
						&& !beian_fzjgxx.get(2).select("td").get(2).text()
								.contains("{DIST_REG_NO}")) {
					for (int i = 2; i < beian_fzjgxx.size(); i++) {
						// 分公司信息
						AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
						gsgsBaFzjgInfo.setName(beian_fzjgxx.get(i).select("td")
								.get(1).text());// 名称
						gsgsBaFzjgInfo.setNum(beian_fzjgxx.get(i).select("td")
								.get(2).text());// 统一社会信用代码/注册号
						// gsgsBaFzjgInfo.setRegAuthority(beian_fzjgxx.get(i).select("td").get(3).text());//
						// 登记机关
						gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
					}
				}
			}
			gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);// 分支机构信息
			// 工商公示信息->备案信息->清算信息
			AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
			Element beian_qingsuanrenyuan = gsgsxx_baxx.getElementById("beian")
					.getElementById("qingsuanfuzeren");
			if (null != beian_qingsuanrenyuan) {
				Elements beian_qingsuanrenyuan_tbs = beian_qingsuanrenyuan
						.select("table");
				Elements beian_qingsuanrenyuan_trs = null;
				if (null != beian_qingsuanrenyuan_tbs
						&& !beian_qingsuanrenyuan_tbs.isEmpty()) {
					beian_qingsuanrenyuan_trs = beian_qingsuanrenyuan_tbs
							.get(0).select("tbody").get(0).select("tr");
				}
				if (null != beian_qingsuanrenyuan_trs) {
					if (!beian_qingsuanrenyuan_trs.get(1)
							.getElementById("qszfzr").text()
							.contains("{DIST_REG_NO}")) {
						gsgsBaQsInfo.setLeader(beian_qingsuanrenyuan_trs.get(1)
								.getElementById("qszfzr").text());// 清算组负责人
						String wq = beian_qingsuanrenyuan_trs.get(2)
								.select("td").get(0).text().trim();
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
		}

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
			Element dongchandiya_et = gsgsxx_dcdydjxx_dcdydjxx_page
					.getElementById("dongchandiya");
			if (null != dongchandiya_et) {
				Elements dongchandiya_trs = dongchandiya_et.select("table")
						.get(0).select("tbody").get(0).select("tr");
				if (null != dongchandiya_trs && dongchandiya_trs.size() > 2) {
					for (int i = 2; i < dongchandiya_trs.size(); i++) {
						AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
						gsgsDcdydjDcdydjInfo.setRegNum(dongchandiya_trs.get(i)
								.select("td").get(1).text());// 登记编号
						gsgsDcdydjDcdydjInfo.setRegDateTime(dongchandiya_trs
								.get(i).select("td").get(2).text());// 登记日期
						gsgsDcdydjDcdydjInfo.setRegAuthority(dongchandiya_trs
								.get(i).select("td").get(3).text());// 登记机关
						gsgsDcdydjDcdydjInfo
								.setGuaranteedDebtAmount(dongchandiya_trs
										.get(i).select("td").get(4).text());// 被担保债权数额
						gsgsDcdydjDcdydjInfo.setStatus(dongchandiya_trs.get(i)
								.select("td").get(5).text());// 状态
						// gsgsDcdydjDcdydjInfo.setPubDateTime(dongchandiya_trs.get(i).select("td").get(6).text());//
						// 公示时间（该字段比较特殊，江苏工商网暂时没有该字段。）
						gsgsDcdydjDcdydjInfo.setDetail(dongchandiya_trs.get(i)
								.select("td").get(6).text());// 详情
						gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
					}
				}
				if (isDebug) {
					gsgsDcdydjInfo.setHtml(dongchandiya_et.html());
				}
			}
			// 动产抵押登记信息
			// gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);
			// 工商公示信息->动产抵押登记信息
			// gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);
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
					Document wd = Jsoup.parseBodyFragment(String
							.valueOf(gsgsxx_dcdydjxx_xq
									.get("gsgsxx_dcdydjxx_xq")));
					Element dcDetails = wd.getElementById("dcDetails");
					if (null != dcDetails) {
						// 工商公示信息->动产抵押登记信息->详情->动产抵押登记信息
						AicpubCChatMortgRegInfo aicpubCChatMortgRegInfo = new AicpubCChatMortgRegInfo();
						Elements dcDetails_ths = dcDetails.select("th");
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
							aicpubCChatMortgRegInfo.setHtml(dcDetails.html());
						}
						aicpubCChatMortgDetail
								.setMortgRegInfo(aicpubCChatMortgRegInfo);
					}
					Element dyqrgk = wd.getElementById("dyqrgk");
					if (null != dyqrgk) {
						Elements dyqrgk_tbs = dyqrgk.select("table");
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
					}
					Element parent_id3 = wd.getElementById("parent_id3");
					if (null != parent_id3) {
						Elements parent_id3_tbs = parent_id3.select("table");
						if (null != parent_id3_tbs && !parent_id3_tbs.isEmpty()) {
							Elements parent_id3_ths = parent_id3_tbs.get(0)
									.select("tbody").get(0).select("th");
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
											.setHtml(parent_id3_tbs.html());
								}
								aicpubCChatMortgDetail
										.setMortgGuaranteedInfo(aicpubCChatMortgGuaranteedInfo);
							}
						}
					}
					Element dywgk = wd.getElementById("dywgk");
					if (null != dywgk) {
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
		if (null != htmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_gqczdjxx_gqczdjxx")))) {
			Document gsgsxx_gqczdjxx_gqczdjxx_page = Jsoup
					.parseBodyFragment(htmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")
							.toString());
			Element guquanchuzhi_et = gsgsxx_gqczdjxx_gqczdjxx_page
					.getElementById("guquanchuzhi");
			// 工商公示信息->股权出资登记信息列表
			List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
			if (null != guquanchuzhi_et) {
				Elements guquanchuzhi_trs = guquanchuzhi_et.select("table")
						.get(0).select("tbody").get(0).select("tr");
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
						gsgsGqczdjGqczdjInfo.setStatus(guquanchuzhi_trs.get(i)
								.select("td").get(8).text());// 状态
						// gsgsGqczdjGqczdjInfo.setPubDate(guquanchuzhi_trs.get(i).select("td").get(9).text());//
						// 公示时间（该字段比较特殊，江苏工商网暂时没有该字段。）
						gsgsGqczdjGqczdjInfo.setChangeSitu(guquanchuzhi_trs
								.get(i).select("td").get(9).text());// 变化情况
						gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
					}
				}
				if (isDebug) {
					gsgsGqczdjInfo.setHtml(guquanchuzhi_et.html());
				}
			}
			gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);// 股权出资登记信息
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
			Element xingzhengchufa = gsgsxx_xzcfxx_xzcfxx
					.getElementById("xingzhengchufa");
			if (null != xingzhengchufa) {
				Elements xingzhengchufa_trs = xingzhengchufa.select("tbody")
						.get(0).select("tr");
				int xingzhengchufa_trs_size = xingzhengchufa_trs.size();
				if (xingzhengchufa_trs_size > 2) {
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
			}
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs = (List<Map<String, Object>>) htmlMap
					.get("gsgsxx_xzcfxx_xzcfxx_xqs");
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos2 = new ArrayList<AicpubAAdmpunishInfo>();
			if (null != gsgsxx_xzcfxx_xzcfxx_xqs
					&& gsgsxx_xzcfxx_xzcfxx_xqs.size() > 0) {
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
			Element yichangminglu = gsgsxx_jyycxx_jyycxx
					.getElementById("yichangminglu");
			Element jingyingyichangminglu = gsgsxx_jyycxx_jyycxx
					.getElementById("jingyingyichangminglu");
			Element wdc = null;
			if (null != yichangminglu) {
				wdc = yichangminglu;
			}
			if (null != jingyingyichangminglu) {
				wdc = jingyingyichangminglu;
			}
			if (null != wdc) {
				Elements yichangminglu_trs = wdc.select("tbody").get(0)
						.select("tr");
				if (null != yichangminglu_trs && yichangminglu_trs.size() > 2) {
					for (int i = 2; i < yichangminglu_trs.size(); i++) {
						AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();// 经营异常信息
						Elements span_one = yichangminglu_trs.get(i)
								.select("td").get(1).getElementsByTag("span");
						if (null != span_one && span_one.size() > 0) {
							gsgsJyycJyycInfo.setIncludeCause(yichangminglu_trs
									.get(i).select("td").get(1).select("span")
									.get(0).attr("completedata"));// 列入经营异常名录原因
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
									.get(0).attr("completedata"));// 移出经营异常名录原因
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
					gsgsJyycInfo.setHtml(wdc.html());
				}
			}
			gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);// 经营异常信息列表
		}
		gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);// 工商公示信息->经营异常信息

		// 工商公示信息->严重违法信息
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		if (null != htmlMap.get("gsgsxx_yzwfxx_yzwfxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_yzwfxx_yzwfxx")
						.toString())) {
			Document gsgsxx_yzwfxx_yzwfxx_page = Jsoup
					.parseBodyFragment(htmlMap.get("gsgsxx_yzwfxx_yzwfxx")
							.toString());
			Element heimingdan_et = gsgsxx_yzwfxx_yzwfxx_page
					.getElementById("heimingdan");
			Element yanzhongweifaqiye_et = gsgsxx_yzwfxx_yzwfxx_page
					.getElementById("yanzhongweifaqiye");
			Element wqc = null;
			if (null != heimingdan_et) {
				wqc = heimingdan_et;
			}
			if (null != yanzhongweifaqiye_et) {
				wqc = yanzhongweifaqiye_et;
			}
			// 工商公示信息->严重违法信息列表
			List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
			if (null != wqc) {
				Elements heimingdan_trs = wqc.select("table").get(0)
						.select("tbody").get(0).select("tr");
				if (null != heimingdan_trs && heimingdan_trs.size() > 2) {
					for (int j = 2; j < heimingdan_trs.size(); j++) {
						// 严重违法信息
						AicpubSSerillegalInfo gsgsYzwfYzwfInfo = new AicpubSSerillegalInfo();
						gsgsYzwfYzwfInfo.setIncludeCause(heimingdan_trs.get(j)
								.select("td").get(1).text());// 列入严重违法企业名单原因
						gsgsYzwfYzwfInfo.setIncludeDateTime(heimingdan_trs
								.get(j).select("td").get(2).text());// 列入日期
						gsgsYzwfYzwfInfo.setRemoveCause(heimingdan_trs.get(j)
								.select("td").get(3).text());// 移出严重违法企业名单原因
						gsgsYzwfYzwfInfo.setRemoveDateTime(heimingdan_trs
								.get(j).select("td").get(4).text());// 移出日期
						gsgsYzwfYzwfInfo.setDeciAuthority(heimingdan_trs.get(j)
								.select("td").get(5).text());// 作出决定机关
						gsgsYzwfYzwfInfos.add(gsgsYzwfYzwfInfo);
					}
				}
				if (isDebug) {
					gsgsYzwfInfo.setHtml(wqc.html());
				}
			}
			gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);// 严重违法信息
		}
		gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);// 工商公示信息->严重违法信息

		// 工商公示信息->抽查检查信息
		AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		if (null != htmlMap.get("gsgsxx_ccjcxx_ccjcxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_ccjcxx_ccjcxx")
						.toString())) {
			Document gsgsxx_ccjcxx_ccjcxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_ccjcxx_ccjcxx").toString());
			// 工商公示信息->抽查检查信息列表
			List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
			Element chouchaxinxi = gsgsxx_ccjcxx_ccjcxx
					.getElementById("chouchaxinxi");
			if (null != chouchaxinxi) {
				Elements chouchaxinxi_trs = chouchaxinxi.select("table").get(0)
						.select("tr");
				int chouchaxinxi_trs_size = chouchaxinxi_trs.size();
				if (chouchaxinxi_trs_size > 2) {
					for (int i = 2; i < chouchaxinxi_trs_size; i++) {
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
					gsgsCcjcInfo.setHtml(chouchaxinxi.html());
				}
			}
			gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);// 抽查检查信息
		}
		gsgsInfo.setCheckInfo(gsgsCcjcInfo);// 工商公示信息->抽查检查信息

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
				Document qygsxx_qynb_info_page = Jsoup
						.parseBodyFragment(qygsxx_qynb_info.get(
								"qygsxx_qynb_info_page").toString());
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
				// 企业公示信息->企业年报->企业基本信息
				EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("REG_NO")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("REG_NO").hasText()) {
					qygsQynbJbInfo.setNum(qygsxx_qynb_info_page
							.getElementById("qyjbxx").getElementById("REG_NO")
							.text());// 注册号/统一社会信用代码
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("CORP_NAME")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("CORP_NAME").hasText()) {
					qygsQynbJbInfo.setName(qygsxx_qynb_info_page
							.getElementById("qyjbxx")
							.getElementById("CORP_NAME").text());// 企业名称
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("TEL")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("TEL").hasText()) {
					qygsQynbJbInfo.setTel(qygsxx_qynb_info_page
							.getElementById("qyjbxx").getElementById("TEL")
							.text());// 企业联系电话
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("ZIP")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("ZIP").hasText()) {
					qygsQynbJbInfo.setZipCode(qygsxx_qynb_info_page
							.getElementById("qyjbxx").getElementById("ZIP")
							.text());// 邮政编码
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("ADDR")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("ADDR").hasText()) {
					qygsQynbJbInfo.setAddress(qygsxx_qynb_info_page
							.getElementById("qyjbxx").getElementById("ADDR")
							.text());// 企业通信地址
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("E_MAIL")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("E_MAIL").hasText()) {
					qygsQynbJbInfo.setEmail(qygsxx_qynb_info_page
							.getElementById("qyjbxx").getElementById("E_MAIL")
							.text());// 电子邮箱
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("IF_EQUITY")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("IF_EQUITY").hasText()) {
					qygsQynbJbInfo
							.setIsStohrEquTransferred(qygsxx_qynb_info_page
									.getElementById("qyjbxx")
									.getElementById("IF_EQUITY").text());// 有限责任公司本年度是否发生股东股权转让
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("PRODUCE_STATUS")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("PRODUCE_STATUS").hasText()) {
					qygsQynbJbInfo.setOperatingStatus(qygsxx_qynb_info_page
							.getElementById("qyjbxx")
							.getElementById("PRODUCE_STATUS").text());// 企业经营状态
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("IF_WEBSITE")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("IF_WEBSITE").hasText()) {
					qygsQynbJbInfo.setHasWebsiteOrStore(qygsxx_qynb_info_page
							.getElementById("qyjbxx")
							.getElementById("IF_WEBSITE").text());// 是否有网站或网店
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("IF_INVEST")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("IF_INVEST").hasText()) {
					qygsQynbJbInfo
							.setHasInvestInfoOrPurchOtherCorpEqu(qygsxx_qynb_info_page
									.getElementById("qyjbxx")
									.getElementById("IF_INVEST").text());// 企业是否有投资信息或购买其他公司股权
				}
				if (null != qygsxx_qynb_info_page.getElementById("qyjbxx")
						.getElementById("PRAC_PERSON_NUM")
						&& qygsxx_qynb_info_page.getElementById("qyjbxx")
								.getElementById("PRAC_PERSON_NUM").hasText()) {
					qygsQynbJbInfo.setEmpNum(qygsxx_qynb_info_page
							.getElementById("qyjbxx")
							.getElementById("PRAC_PERSON_NUM").text());// 从业人数
				}
				qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);// 企业公示信息->企业年报->企业基本信息
				Element chuziren = qygsxx_qynb_info_page.getElementById(
						"qyjbxx").getElementById("chuziren");
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();// 股东及出资信息
				if (null != chuziren) {
					Elements qyjbxx_gd_trs = chuziren.select("tr");
					if (null != qyjbxx_gd_trs && qyjbxx_gd_trs.size() > 2) {
						for (int i = 2; i < qyjbxx_gd_trs.size(); i++) {
							EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo = new EntpubAnnreportStohrinvestInfo();// 股东及出资信息
							qygsQynbGdjczInfo.setStockholder(qyjbxx_gd_trs
									.get(i).select("td").get(0).text());// 股东（发起人）
							qygsQynbGdjczInfo.setSubAmount(qyjbxx_gd_trs.get(i)
									.select("td").get(1).text());// 认缴出资额（万元）
							qygsQynbGdjczInfo.setSubDateTime(qyjbxx_gd_trs
									.get(i).select("td").get(2).text());// 认缴出资时间
							qygsQynbGdjczInfo.setSubMethod(qyjbxx_gd_trs.get(i)
									.select("td").get(3).text());// 认缴出资方式
							qygsQynbGdjczInfo.setPaidAmount(qyjbxx_gd_trs
									.get(i).select("td").get(4).text());// 实缴出资额（万元）
							qygsQynbGdjczInfo.setPaidDateTime(qyjbxx_gd_trs
									.get(i).select("td").get(5).text());// 实缴出资时间
							qygsQynbGdjczInfo.setPaidMethod(qyjbxx_gd_trs
									.get(i).select("td").get(6).text());// 实缴出资方式
							qygsQynbGdjczInfos.add(qygsQynbGdjczInfo);
						}
					}
				}
				qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);// 企业公示信息->企业年报->股东及出资信息
				// 企业公示信息->企业年报->企业资产状况信息
				EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
				Element qyjbxx_qyzczkxx = qygsxx_qynb_info_page
						.getElementById("qyjbxx");
				if (null != qyjbxx_qyzczkxx.getElementById("NET_AMOUNT")
						&& qyjbxx_qyzczkxx.getElementById("NET_AMOUNT")
								.hasText()) {
					qygsQynbQyzczkInfo.setAssetAmount(qyjbxx_qyzczkxx
							.getElementById("NET_AMOUNT").text());// 资产总额
				}
				if (null != qyjbxx_qyzczkxx.getElementById("TOTAL_EQUITY")
						&& qyjbxx_qyzczkxx.getElementById("TOTAL_EQUITY")
								.hasText()) {
					qygsQynbQyzczkInfo.setTotalEquity(qyjbxx_qyzczkxx
							.getElementById("TOTAL_EQUITY").text());// 所有者权益合计
				}
				if (null != qyjbxx_qyzczkxx.getElementById("SALE_INCOME")
						&& qyjbxx_qyzczkxx.getElementById("SALE_INCOME")
								.hasText()) {
					qygsQynbQyzczkInfo.setSalesAmount(qyjbxx_qyzczkxx
							.getElementById("SALE_INCOME").text());// 销售总额
				}
				if (null != qyjbxx_qyzczkxx.getElementById("PROFIT_TOTAL")
						&& qyjbxx_qyzczkxx.getElementById("PROFIT_TOTAL")
								.hasText()) {
					qygsQynbQyzczkInfo.setProfitAmount(qyjbxx_qyzczkxx
							.getElementById("PROFIT_TOTAL").text());// 利润总额
				}
				if (null != qyjbxx_qyzczkxx.getElementById("SERV_FARE_INCOME")
						&& qyjbxx_qyzczkxx.getElementById("SERV_FARE_INCOME")
								.hasText()) {
					qygsQynbQyzczkInfo
							.setPriBusiIncomeInSalesAmount(qyjbxx_qyzczkxx
									.getElementById("SERV_FARE_INCOME").text());// 销售总额中主营业务收入
				}
				if (null != qyjbxx_qyzczkxx.getElementById("PROFIT_RETA")
						&& qyjbxx_qyzczkxx.getElementById("PROFIT_RETA")
								.hasText()) {
					qygsQynbQyzczkInfo.setNetProfit(qyjbxx_qyzczkxx
							.getElementById("PROFIT_RETA").text());// 净利润
				}
				if (null != qyjbxx_qyzczkxx.getElementById("TAX_TOTAL")
						&& qyjbxx_qyzczkxx.getElementById("TAX_TOTAL")
								.hasText()) {
					qygsQynbQyzczkInfo.setTaxesAmount(qyjbxx_qyzczkxx
							.getElementById("TAX_TOTAL").text());// 纳税总额
				}
				if (null != qyjbxx_qyzczkxx.getElementById("DEBT_AMOUNT")
						&& qyjbxx_qyzczkxx.getElementById("DEBT_AMOUNT")
								.hasText()) {
					qygsQynbQyzczkInfo.setLiabilityAmount(qyjbxx_qyzczkxx
							.getElementById("DEBT_AMOUNT").text());// 负债总额
				}
				qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);// 企业资产状况信息
				// 对外提供保证担保信息
				List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();
				Element duiwaidanbao = qygsxx_qynb_info_page
						.getElementById("duiwaidanbao");
				if (null != duiwaidanbao) {
					Elements duiwaidanbao_trs = duiwaidanbao.select("tr");
					if (null != duiwaidanbao_trs && duiwaidanbao_trs.size() > 2) {
						for (int i = 2; i < duiwaidanbao_trs.size(); i++) {
							EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
							qygsQynbDwtgbzdbInfo.setCreditor(duiwaidanbao_trs
									.get(i).select("td").get(0).text());// 债权人
							qygsQynbDwtgbzdbInfo.setDebtor(duiwaidanbao_trs
									.get(i).select("td").get(1).text());// 债务人
							qygsQynbDwtgbzdbInfo
									.setPriCredRightType(duiwaidanbao_trs
											.get(i).select("td").get(2).text());// 主债权种类
							qygsQynbDwtgbzdbInfo
									.setPriCredRightAmount(duiwaidanbao_trs
											.get(i).select("td").get(3).text());// 主债权数额
							qygsQynbDwtgbzdbInfo
									.setExeDebtDeadline(duiwaidanbao_trs.get(i)
											.select("td").get(4).text());// 履行债务的期限
							qygsQynbDwtgbzdbInfo
									.setGuaranteePeriod(duiwaidanbao_trs.get(i)
											.select("td").get(5).text());// 保证的期间
							qygsQynbDwtgbzdbInfo
									.setGuaranteeMethod(duiwaidanbao_trs.get(i)
											.select("td").get(6).text());// 保证的方式
							// qygsQynbDwtgbzdbInfo.setGuaranteeScope(duiwaidanbao_trs.get(i).select("td").get(6).text());//
							// 保证担保的范围（江苏工商网暂时没有该字段）
							qygsQynbDwtgbzdbInfos.add(qygsQynbDwtgbzdbInfo);
						}
					}
				}
				qygsQynbInfo.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);// 对外提供保证担保信息
				// 修改记录
				List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
				Element modifyRecord = qyjbxx_qyzczkxx
						.getElementById("modifyRecord");
				if (null != modifyRecord) {
					Elements modifyRecord_trs = modifyRecord.select("tr");
					if (null != modifyRecord_trs && modifyRecord_trs.size() > 2) {
						for (int i = 2; i < modifyRecord_trs.size(); i++) {
							EntpubAnnreportModifyInfo qygsQynbXgjlInfo = new EntpubAnnreportModifyInfo();
							qygsQynbXgjlInfo.setItem(modifyRecord_trs.get(i)
									.select("td").get(1).text());// 修改事项
							qygsQynbXgjlInfo.setPreContent(modifyRecord_trs
									.get(i).select("td").get(2).text());// 修改前
							qygsQynbXgjlInfo.setPostContent(modifyRecord_trs
									.get(i).select("td").get(3).text());// 修改后
							qygsQynbXgjlInfo.setDateTime(modifyRecord_trs
									.get(i).select("td").get(4).text());// 修改日期
							qygsQynbXgjlInfos.add(qygsQynbXgjlInfo);
						}
					}
				}
				qygsQynbInfo.setChangeInfos(qygsQynbXgjlInfos);// 修改记录
				Element web = qygsxx_qynb_info_page.getElementById("web");
				if (null != web) {
					// 网站或网店信息
					List<EntpubAnnreportWebsiteInfo> entpubAnnreportWebsiteInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
					Elements web_trs = web.select("tr");
					if (null != web_trs && web_trs.size() > 2) {
						for (int i = 2; i < web_trs.size(); i++) {
							EntpubAnnreportWebsiteInfo entpubAnnreportWebsiteInfo = new EntpubAnnreportWebsiteInfo();
							entpubAnnreportWebsiteInfo.setType(web_trs.get(i)
									.select("td").get(0).text());// 类型
							entpubAnnreportWebsiteInfo.setName(web_trs.get(i)
									.select("td").get(1).text());// 名称
							entpubAnnreportWebsiteInfo.setWebsite(web_trs
									.get(i).select("td").get(2).text());// 网址
							entpubAnnreportWebsiteInfos
									.add(entpubAnnreportWebsiteInfo);
						}
					}
					qygsQynbInfo.setWebsiteInfos(entpubAnnreportWebsiteInfos);// 网站或网店信息
				}
				Element duiwaitouzi = qygsxx_qynb_info_page
						.getElementById("duiwaitouzi");
				if (null != duiwaitouzi) {
					// 对外投资信息
					List<EntpubAnnreportExtinvestInfo> extInvestInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
					Elements duiwaitouzi_trs = duiwaitouzi.select("tr");
					if (null != duiwaitouzi_trs && duiwaitouzi_trs.size() > 2) {
						for (int i = 2, duiwaitouzi_trs_size = duiwaitouzi_trs
								.size(); i < duiwaitouzi_trs_size; i++) {
							EntpubAnnreportExtinvestInfo entpubAnnreportExtinvestInfo = new EntpubAnnreportExtinvestInfo();
							entpubAnnreportExtinvestInfo
									.setEnterpriseName(duiwaitouzi_trs.get(i)
											.select("td").get(0).text());// 投资设立企业或购买股权企业名称
							entpubAnnreportExtinvestInfo
									.setRegNum(duiwaitouzi_trs.get(i)
											.select("td").get(1).text());// 注册号/统一社会信用代码
							extInvestInfos.add(entpubAnnreportExtinvestInfo);
						}
					}
					qygsQynbInfo.setExtInvestInfos(extInvestInfos);// 对外投资信息
				}
				qygsQynbInfos.add(qygsQynbInfo);// 企业公示信息->企业年报
			}
		} else {
			if (null != htmlMap.get("qygsxx_qynb_list_page")
					&& StringUtils.isNotEmpty(htmlMap.get(
							"qygsxx_qynb_list_page").toString())) {
				Document qygsxx_qynb_list_page = Jsoup
						.parseBodyFragment(htmlMap.get("qygsxx_qynb_list_page")
								.toString());
				Elements qygsxx_qynb_list_ths = qygsxx_qynb_list_page
						.select("th");
				if (null != qygsxx_qynb_list_ths
						&& !qygsxx_qynb_list_ths.isEmpty()) {
					for (Element qygsxx_qynb_list_th : qygsxx_qynb_list_ths) {
						if (qygsxx_qynb_list_th.text().contains("企业年报")) {
							Element qygsxx_qynb_list_tbody = qygsxx_qynb_list_th
									.parent().parent();
							Elements qygsxx_qynb_list_trs = qygsxx_qynb_list_tbody
									.select("tr");
							if (null != qygsxx_qynb_list_trs
									&& qygsxx_qynb_list_trs.size() > 2) {
								for (int i = 2, qq = qygsxx_qynb_list_trs
										.size(); i < qq; i++) {
									EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();// 企业年报
									qygsQynbInfo
											.setSubmitYear(qygsxx_qynb_list_trs
													.get(i).select("td").get(1)
													.text());// 报送年度
									qygsQynbInfo
											.setPubDateTime(qygsxx_qynb_list_trs
													.get(i).select("td").get(2)
													.text());// 发布日期
									qygsQynbInfos.add(qygsQynbInfo);
								}
							}
							break;
						}
					}
				}
			}
		}
		qygsInfo.setAnnReports(qygsQynbInfos);// 企业公示信息->企业年报

		// 企业公示信息->股东及出资信息
		EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
		// 企业公示信息->投资人及出资信息
		if (null != htmlMap.get("qygsxx_gdjczxx")
				&& !StringUtils.isEmpty(htmlMap.get("qygsxx_gdjczxx")
						.toString())) {
			Document qygsxx_gdjczxx = Jsoup.parseBodyFragment(htmlMap.get(
					"qygsxx_gdjczxx").toString());
			if (null != qygsxx_gdjczxx.getElementById("touziren")) {
				Elements gdjczxx_trs = qygsxx_gdjczxx
						.getElementById("touziren").select("tbody").get(0)
						.select("tr");
				if (null != gdjczxx_trs && gdjczxx_trs.size() > 3) {
					// 企业公示信息->股东及出资信息
					List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
					for (int j = 3, gdjczxx_trs_size = gdjczxx_trs.size(); j < gdjczxx_trs_size; j++) {
						Elements gdjczxx_tds = gdjczxx_trs.get(j).select("td");
						if (null != gdjczxx_tds && gdjczxx_tds.size() > 9) {
							// 企业公示信息->股东及出资信息->投资人及出资信息
							EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new EntpubSStohrinvestInfo();
							qygsGdjczGdjczInfo.setStockholder(gdjczxx_tds
									.get(0).text());// 股东（投资人名称）
							qygsGdjczGdjczInfo.setInvestorsType(gdjczxx_tds
									.get(1).text());// 投资人类型
							qygsGdjczGdjczInfo.setSubAmount(gdjczxx_tds.get(2)
									.text());// 累计认缴额（万元）
							qygsGdjczGdjczInfo.setPaidAmount(gdjczxx_tds.get(3)
									.text());// 累计实缴额（万元）
							List<EntpubSStohrinvestInfo.Detail> subDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
							List<EntpubSStohrinvestInfo.Detail> paidDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
							EntpubSStohrinvestInfo.Detail rjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
							rjDetail.amount = gdjczxx_tds.get(4).text();// 认缴出资额（万元）
							rjDetail.method = gdjczxx_tds.get(5).text();// 出资方式
							rjDetail.dateTime = gdjczxx_tds.get(6).text();// 出资日期
							subDetails.add(rjDetail);
							EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
							sjDetail.amount = gdjczxx_tds.get(7).text();// 实缴出资额（万元）
							sjDetail.method = gdjczxx_tds.get(8).text();// 出资方式
							sjDetail.dateTime = gdjczxx_tds.get(9).text();// 出资日期
							paidDetails.add(sjDetail);
							for (int i = j + 1; i < gdjczxx_trs_size; i++) {
								Elements gdjczxx_tds_mx = gdjczxx_trs.get(i)
										.select("td");
								if (null != gdjczxx_tds_mx
										&& gdjczxx_tds_mx.size() > 7) {
									break;
								} else if (null == gdjczxx_tds_mx
										|| gdjczxx_tds_mx.isEmpty()) {
									break;
								} else {
									EntpubSStohrinvestInfo.Detail rj2Detail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
									rj2Detail.amount = gdjczxx_tds_mx.get(0)
											.text();// 认缴出资额（万元）
									rj2Detail.method = gdjczxx_tds_mx.get(1)
											.text();// 出资方式
									rj2Detail.dateTime = gdjczxx_tds_mx.get(2)
											.text();// 出资日期
									subDetails.add(rj2Detail);
									EntpubSStohrinvestInfo.Detail sj2Detail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
									sj2Detail.amount = gdjczxx_tds_mx.get(3)
											.text();// 实缴出资额（万元）
									sj2Detail.method = gdjczxx_tds_mx.get(4)
											.text();// 出资方式
									sj2Detail.dateTime = gdjczxx_tds_mx.get(5)
											.text();// 出资日期
									paidDetails.add(sj2Detail);
								}
							}
							qygsGdjczGdjczInfo.setSubDetails(subDetails);
							qygsGdjczGdjczInfo.setPaidDetails(paidDetails);
							qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
						}
					}
					qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息
				}
			}
		}

		// TODO
		// 企业公示信息->股东及出资信息->变更信息
		// List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new
		// ArrayList<EntpubStohrinvestChangeInfo>();
		// EntpubStohrinvestChangeInfo gygsGdjczBgInfo = new
		// EntpubStohrinvestChangeInfo();
		// gygsGdjczBgInfo.setItem("");// 变更事项
		// gygsGdjczBgInfo.setDateTime("");// 变更时间
		// gygsGdjczBgInfo.setPreContent("");// 变更前
		// gygsGdjczBgInfo.setPostContent("");// 变更后
		// gygsGdjczBgInfo.setHtml("江苏工商网中暂未发现该项信息！");
		// qygsGdjczBgInfos.add(gygsGdjczBgInfo);
		// qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);// 变更信息
		qygsInfo.setStohrInvestInfo(qygsGdjczInfo);// 企业公示信息->股东及出资信息

		// 企业公示信息->股权变更信息
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		if (null != htmlMap.get("qygsxx_gqbgxx")
				&& !StringUtils
						.isEmpty(htmlMap.get("qygsxx_gqbgxx").toString())) {
			Document qygsxx_gqbgxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_gqbgxx")));
			Element gudongguquan_et = qygsxx_gqbgxx_page
					.getElementById("gudongguquan");
			// 企业公示信息->股权变更信息列表
			List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
			if (null != gudongguquan_et) {
				Elements gudongguquan_trs = gudongguquan_et.select("table")
						.get(0).select("tr");
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
					qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);// 企业公示信息->股权变更信息
				}
				if (isDebug) {
					qygsGqbgInfo.setHtml(gudongguquan_et.html());
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
			Element xingzhengxuke = qygsxx_xzxkxx_page
					.getElementById("xingzhengxuke");
			// 企业公示信息->行政许可信息列表
			List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
			if (null != xingzhengxuke) {
				Elements xingzhengxuke_trs = xingzhengxuke.select("table")
						.get(0).select("tbody").get(0).select("tr");
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
						Elements xingzhengxuke_spans = xingzhengxuke_trs.get(i)
								.select("td").get(6).select("span");
						if (null != xingzhengxuke_spans
								&& xingzhengxuke_spans.size() > 0) {
							qygsXzxkXzxkInfo.setContent(xingzhengxuke_spans
									.get(0).attr("completedata"));// 许可内容
						} else {
							qygsXzxkXzxkInfo.setContent(xingzhengxuke_trs
									.get(i).select("td").get(6).text());// 许可内容
						}

						qygsXzxkXzxkInfo.setStatus("");// 状态（暂无）
						qygsXzxkXzxkInfo.setDetail("");// 详情（暂无）
						qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
					}
				}
				if (isDebug) {
					qygsXzxkInfo.setHtml(xingzhengxuke.html());
				}
			}
			qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
		}
		qygsInfo.setAdmLicInfo(qygsXzxkInfo);// 企业公示信息->行政许可信息

		// 企业公示信息->知识产权出质登记信息
		EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
		if (null != htmlMap.get("qygsxx_zscqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("qygsxx_zscqczdjxx")))) {
			Document qygsxx_zscqczdjxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_zscqczdjxx")));
			Element zhishichanquan_et = qygsxx_zscqczdjxx_page
					.getElementById("zhishichanquan");
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
						qygsZscqczdjZscqczdjInfo
								.setChangeSitu(zhishichanquan_trs.get(i)
										.select("td").get(7).text());// 变化情况
						// qygsZscqczdjZscqczdjInfo.setStatus(zhishichanquan_trs.get(i).select("td").get(7).text());//
						// 状态（江苏工商网暂无）
						qygsZscqczdjZscqczdjInfos.add(qygsZscqczdjZscqczdjInfo);
					}
				}
				if (isDebug) {
					qygsZscqczdjInfo.setHtml(zhishichanquan_et.html());
				}
			}
			qygsZscqczdjInfo
					.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);// 企业公示信息->知识产权出质登记信息
		}
		qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);// 企业公示信息->知识产权出质登记信息

		// 企业公示信息->行政处罚信息
		EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
		if (null != htmlMap.get("qygsxx_xzcfxx")
				&& !StringUtils
						.isEmpty(htmlMap.get("qygsxx_xzcfxx").toString())) {
			Document qygsxx_xzcfxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_xzcfxx")));
			Element xingzhengchufa_et = qygsxx_xzcfxx_page
					.getElementById("xingzhengchufa");
			// 企业公示信息->行政处罚信息列表
			List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
			if (null != xingzhengchufa_et) {
				Elements xingzhengchufa_trs = xingzhengchufa_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 2) {
					for (int i = 2; i < xingzhengchufa_trs.size(); i++) {
						// 企业公示信息->行政处罚信息
						EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();
						qygsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs
								.get(i).select("td").get(1).text());// 行政处罚决定书文号
						qygsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs
								.get(i).select("td").get(2).text());// 行政处罚内容
						qygsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs
								.get(i).select("td").get(3).text());// 作出行政处罚决定机关名称
						qygsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs
								.get(i).select("td").get(4).text());// 作出行政处罚决定日期
						// qygsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs.get(i).select("td").get(4).text());//
						// 违法行为类型（江苏工商网暂无）
						// qygsXzcfXzcfInfo.setNote(xingzhengchufa_trs.get(i).select("td").get(4).text());//
						// 备注（江苏工商网暂无）
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
			Element xingzhengxuke_et = qtbmgsxx_xzxkxx_page
					.getElementById("xingzhengxuke");
			// 其他部门公示信息->行政许可信息列表
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			if (null != xingzhengxuke_et) {
				Elements xingzhengxuke_trs = xingzhengxuke_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengxuke_trs && xingzhengxuke_trs.size() > 2) {
					for (int i = 2; i < xingzhengxuke_trs.size(); i++) {
						Elements xingzhengxuke_tds = xingzhengxuke_trs.get(i)
								.select("td");
						// 其他部门公示信息->行政许可信息列表->行政许可信息实体
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
						if (xingzhengxuke_tds.size() > 9) {
							qtbmgsXzxkXzxkInfo.setStatus(xingzhengxuke_tds.get(
									7).text());// 登记状态
							qtbmgsXzxkXzxkInfo.setDetail(xingzhengxuke_tds.get(
									9).text());// 详情
						}
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
			Element xingzhengchufa_et = qtbmgsxx_xzcfxx_page
					.getElementById("xingzhengchufa");
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
								.get(i).select("td").get(4).text());// 行政处罚内容
						qtbmgsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs
								.get(i).select("td").get(5).text());// 作出行政处罚决定机关名称
						qtbmgsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs
								.get(i).select("td").get(6).text());// 作出行政处罚决定日期
						// qtbmgsXzcfXzcfInfo.setDetail(xingzhengchufa_trs.get(i).select("td").get(6).text());//
						// 详情（暂无）
						// qtbmgsXzcfXzcfInfo.setNote(xingzhengchufa_trs.get(i).select("td").get(6).text());//
						// 备注（暂无）
						qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
					}
				}
				if (isDebug && null != xingzhengchufa_et) {
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
		// TODO
		// 司法协助公示信息->股权冻结信息
		JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
		if (null != htmlMap.get("sfxzgsxx_gqdjxx_list")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx_gqdjxx_list")
						.toString())) {
			Document sfxzgsxx_gqdjxx_list_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx_gqdjxx_list")));
			Element guquandongjie_et = sfxzgsxx_gqdjxx_list_page
					.getElementById("guquandongjie");
			// 司法协助公示信息->股权冻结信息列表
			List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
			if (null != guquandongjie_et) {
				Elements guquandongjie_trs = guquandongjie_et.select("tbody")
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
					sfxzgsGqdjInfo.setHtml(guquandongjie_et.html());
				}
			}
			sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);// 司法协助公示信息->股权冻结信息列表
		}
		sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);// 司法协助公示信息->股权冻结信息

		// 司法协助公示信息->股东变更信息
		JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
		if (null != htmlMap.get("sfxzgsxx_gqbgxx_list")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx_gqbgxx_list")
						.toString())) {
			Document sfxzgsxx_gqbgxx_list_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx_gqbgxx_list")));
			Element guquanbiangeng_et = sfxzgsxx_gqbgxx_list_page
					.getElementById("guquanbiangeng");
			// 司法协助公示信息->股东变更信息列表
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			if (null != guquanbiangeng_et) {
				Elements guquanbiangeng_trs = guquanbiangeng_et.select("tbody")
						.get(0).select("tr");
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

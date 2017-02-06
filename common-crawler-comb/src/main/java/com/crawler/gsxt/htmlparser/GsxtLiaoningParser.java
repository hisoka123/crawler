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
import com.crawler.gsxt.domain.json.liaoning.Bgxx;
import com.crawler.gsxt.domain.json.liaoning.Fzjgxx;
import com.crawler.gsxt.domain.json.liaoning.GdOrTzrxx;
import com.crawler.gsxt.domain.json.liaoning.GsgsxxBaxxCymc;
import com.crawler.gsxt.domain.json.liaoning.Zgbmxx;
import com.crawler.gsxt.domain.json.liaoning.Zyryxx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtLiaoningParser extends AbstractGsxtParser {

	private static final Logger LOOGER = LoggerFactory
			.getLogger(GsxtLiaoningParser.class);

	public AicFeedJson getLiaoningResultObj(String html, boolean isDebug) {

		LOOGER.info("The method of GsxtLiaoningParser.getLiaoningResultObj is begin !");

		Gson gson = new GsonBuilder().create();
		Map<String, Object> htmlMap = gson.fromJson(html,
				new TypeToken<Map<String, Object>>() {
				}.getType());

		if (null == htmlMap.get("gsgsxx_djxx_jbxx")
				|| null == String.valueOf(htmlMap.get("gsgsxx_djxx_jbxx"))
				|| StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_djxx_jbxx")))) {
			return null;
		}

		// 工商系统bean
		AicFeedJson gsxtFeedJson = new AicFeedJson();

		// 工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();

		// 工商公示信息->登记信息
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();
		// 工商公示信息->登记信息->基本信息
		Document gsgsxx_djxx_jbxx = Jsoup.parseBodyFragment(htmlMap.get(
				"gsgsxx_djxx_jbxx").toString());
		Element jibenxinxi = gsgsxx_djxx_jbxx.getElementById("jibenxinxi");
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
		if (null != jibenxinxi) {
			Elements ths = jibenxinxi.select("tbody").select("tr").select("th");
			for (int i = 1; i < ths.size(); i++) {
				if (ths.get(i).text().contains("统一社会信用代码")
						|| ths.get(i).text().contains("注册号")) {
					gsgsDjJbInfo.setNum(jibenxinxi.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 注册号或信用代码
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("名称")) {
					gsgsDjJbInfo.setName(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 名称
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("类型")) {
					gsgsDjJbInfo.setType(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 类型
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("负责人")
						|| ths.get(i).text().contains("投资人")
						|| ths.get(i).text().trim().equalsIgnoreCase("法定代表人")
						|| ths.get(i).text().contains("经营者")) {
					gsgsDjJbInfo.setLegalRepr(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 法定代表人/经营者
				}
				if (ths.get(i).text().contains("注册资本")
						|| ths.get(i).text().contains("成员出资总额")) {
					gsgsDjJbInfo.setRegCapital(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 注册资本
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("成立日期")) {
					gsgsDjJbInfo.setRegDateTime(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 成立日期
				}
				if (ths.get(i).text().contains("营业场所")
						|| ths.get(i).text().contains("住所")) {
					gsgsDjJbInfo.setAddress(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 经营场所/住所
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("营业期限自")
						|| ths.get(i).text().contains("经营期限自")) {
					gsgsDjJbInfo.setStartDateTime(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 营业期限自（即营业开始日期）
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("营业期限至")
						|| ths.get(i).text().contains("经营期限至")) {
					gsgsDjJbInfo.setEndDateTime(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 营业期限至（即营业结束日期）
				}
				if (ths.get(i).text().contains("经营范围")
						|| ths.get(i).text().contains("业务范围")) {
					gsgsDjJbInfo.setBusinessScope(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 经营范围
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("登记机关")) {
					gsgsDjJbInfo.setRegAuthority(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 登记机关
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("核准日期")) {
					gsgsDjJbInfo.setApprovalDateTime(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 核准日期
				}
				if (ths.get(i).text().contains("登记状态")) {
					gsgsDjJbInfo.setRegStatus(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 登记状态
				}
				if (ths.get(i).text().contains("吊销日期")) {
					gsgsDjJbInfo.setRevokeDate(jibenxinxi.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 吊销日期
				}
			}
			if (isDebug) {
				gsgsDjJbInfo.setHtml(jibenxinxi.html());
			}
		}
		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);// 基本信息
		// 工商公示信息->登记信息->股东信息（投资人信息）
		if (null != htmlMap.get("gsgsxx_djxx_tzrxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_djxx_tzrxx")))) {
			List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
			Document gsgsxx_djxx_tzrxx_page = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_djxx_tzrxx").toString());
			Element gudong_tbody = gsgsxx_djxx_tzrxx_page
					.getElementById("tzr_itemContainer");
			Element touziren_tbody = gsgsxx_djxx_tzrxx_page
					.getElementById("tzr_grdz_itemContainer");
			if (null != gudong_tbody) {
				List<GdOrTzrxx> gdOrTzrxxList = null;
				String bgxxHtml = htmlMap.get("gsgsxx_djxx_tzrxx").toString();
				String[] wcd = bgxxHtml.split("tzr_paging\\(\\[");
				if (null != wcd && wcd.length > 1) {
					String htmlJson = "[" + wcd[1].split("\\]")[0] + "]";
					gdOrTzrxxList = gson.fromJson(htmlJson,
							new TypeToken<List<GdOrTzrxx>>() {
							}.getType());
				}
				if (null != gdOrTzrxxList && !gdOrTzrxxList.isEmpty()
						&& gdOrTzrxxList.size() > 0) {
					for (GdOrTzrxx gdOrTzrxx : gdOrTzrxxList) {
						// 股东信息
						AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();
						gsgsDjGdInfo.setType(gdOrTzrxx.getInvtypeName());// 股东类型
						gsgsDjGdInfo.setName(gdOrTzrxx.getInv());// 股东名称
						gsgsDjGdInfo.setIdType(gdOrTzrxx.getBlictypeName());// 证照/证件类型
						gsgsDjGdInfo.setIdNum(gdOrTzrxx.getBlicno());// 证照/证件号码
						gsgsDjGdInfos.add(gsgsDjGdInfo);
					}
				}
			}
			if (null != touziren_tbody) {
				List<GdOrTzrxx> gdOrTzrxxList = null;
				String bgxxHtml = htmlMap.get("gsgsxx_djxx_tzrxx").toString();
				String[] wcd = bgxxHtml.split("tzr_grdz_paging\\(\\[");
				if (null != wcd && wcd.length > 1) {
					String htmlJson = "[" + wcd[1].split("\\]")[0] + "]";
					gdOrTzrxxList = gson.fromJson(htmlJson,
							new TypeToken<List<GdOrTzrxx>>() {
							}.getType());
				}
				if (null != gdOrTzrxxList && !gdOrTzrxxList.isEmpty()
						&& gdOrTzrxxList.size() > 0) {
					for (GdOrTzrxx gdOrTzrxx : gdOrTzrxxList) {
						// 股东信息
						AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();
						gsgsDjGdInfo.setName(gdOrTzrxx.getInv());// 股东名称
						gsgsDjGdInfo.setSconform(gdOrTzrxx.getSconformName());// 出资方式
						gsgsDjGdInfos.add(gsgsDjGdInfo);
					}
				}
			}
			// gsgsDjInfo.setStohrInfos(gsgsDjGdInfos);// 股东信息
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = (List<Map<String, Object>>) htmlMap
					.get("gsgsxx_djxx_tzrxx_xqs");
			List<AicpubRegStohrStohrinvestInfo> gsgsDjGdGdjczInfos = new ArrayList<AicpubRegStohrStohrinvestInfo>();
			if (null != gsgsxx_djxx_tzrxx_xqs
					&& !gsgsxx_djxx_tzrxx_xqs.isEmpty()
					&& gsgsxx_djxx_tzrxx_xqs.size() > 0) {
				for (Map<String, Object> gsgsxx_djxx_tzrxx_xq : gsgsxx_djxx_tzrxx_xqs) {
					Document gsgsxx_djxx_tzrxx_xq_page = Jsoup
							.parseBodyFragment(gsgsxx_djxx_tzrxx_xq.get(
									"gsgsxx_djxx_tzrxx_xq").toString());
					Elements gsgsxx_djxx_tzrxx_xq_trs = gsgsxx_djxx_tzrxx_xq_page
							.getElementById("gdjczTab").select("tbody").get(0)
							.select("tr");
					if (null != gsgsxx_djxx_tzrxx_xq_trs
							&& gsgsxx_djxx_tzrxx_xq_trs.size() > 3) {
						AicpubRegStohrStohrinvestInfo gsgsDjGdGdjczInfo = new AicpubRegStohrStohrinvestInfo();
						gsgsDjGdGdjczInfo
								.setStockholder(gsgsxx_djxx_tzrxx_xq_trs.get(3)
										.select("td").get(0).text());// 股东
						gsgsDjGdGdjczInfo.setSubAmount(gsgsxx_djxx_tzrxx_xq_trs
								.get(3).select("td").get(2).text());// 认缴额（万元）
						gsgsDjGdGdjczInfo
								.setPaidAmount(gsgsxx_djxx_tzrxx_xq_trs.get(3)
										.select("td").get(3).text());// 实缴额（万元）
						List<AicpubRegStohrStohrinvestInfo.AmountDetail> subAmountDetails = new ArrayList<AicpubRegStohrStohrinvestInfo.AmountDetail>();
						List<AicpubRegStohrStohrinvestInfo.AmountDetail> paidAmountDetails = new ArrayList<AicpubRegStohrStohrinvestInfo.AmountDetail>();
						for (int i = 3; i < gsgsxx_djxx_tzrxx_xq_trs.size(); i++) {
							AicpubRegStohrStohrinvestInfo.AmountDetail subamountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
							AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
							Elements gsgsxx_djxx_tzrxx_xq_tds = gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td");
							if (null != gsgsxx_djxx_tzrxx_xq_tds
									&& gsgsxx_djxx_tzrxx_xq_tds.size() > 12) {
								subamountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(4).text();// 出资方式
								subamountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(6).text();// 出资额（万元）
								subamountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(7).text();// 出资日期
								paidAmountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(9).text();// 出资方式
								paidAmountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(11).text();// 出资额（万元）
								paidAmountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(12).text();// 出资日期
								subAmountDetails.add(subamountDetail);
								paidAmountDetails.add(paidAmountDetail);
							} else {
								subamountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(0).text();// 出资方式
								subamountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(2).text();// 出资额（万元）
								subamountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(3).text();// 出资日期
								paidAmountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(5).text();// 出资方式
								paidAmountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(7).text();// 出资额（万元）
								paidAmountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(8).text();// 出资日期
								subAmountDetails.add(subamountDetail);
								paidAmountDetails.add(paidAmountDetail);
							}
						}
						gsgsDjGdGdjczInfo.setSubAmountDetails(subAmountDetails);
						gsgsDjGdGdjczInfo
								.setPaidAmountDetails(paidAmountDetails);
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
		}
		if (null != htmlMap.get("gsgsxx_djxx_bgxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_djxx_bgxx")))) {
			List<Bgxx> bgxxList = null;
			String bgxxHtml = htmlMap.get("gsgsxx_djxx_bgxx").toString();
			String[] wcd = bgxxHtml.split("paging\\(\\[");
			if (null != wcd && wcd.length > 1) {
				String htmlJson = "[" + wcd[1].split("\\]")[0] + "]";
				bgxxList = gson.fromJson(htmlJson, new TypeToken<List<Bgxx>>() {
				}.getType());
			}
			if (null != bgxxList && !bgxxList.isEmpty() && bgxxList.size() > 0) {
				// 工商公示信息->登记信息->变更信息
				List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<AicpubRegChangeInfo>();
				for (Bgxx bgxx : bgxxList) {
					AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
					gsgsDjBgInfo.setItem(bgxx.getAltitemName());// 变更事项
					gsgsDjBgInfo.setPreContent(bgxx.getAltbe());// 变更前内容
					gsgsDjBgInfo.setPostContent(bgxx.getAltaf());// 变更后内容
					gsgsDjBgInfo.setDateTime(bgxx.getAltdate());// 变更日期
					gsgsDjBgInfos.add(gsgsDjBgInfo);
				}
				gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);// 变更信息
			}
		}
		// // 工商公示信息->登记信息->变更信息
		// List<AicpubRegChangeInfo> gsgsDjBgInfos = new
		// ArrayList<AicpubRegChangeInfo>();
		// if (null != htmlMap.get("gsgsxx_djxx_bgxx")
		// && !StringUtils.isEmpty(String.valueOf(htmlMap
		// .get("gsgsxx_djxx_bgxx")))) {
		// Document gsgsxx_djxx_bgxx_page = Jsoup.parseBodyFragment(htmlMap
		// .get("gsgsxx_djxx_bgxx").toString());
		// Element biangeng = gsgsxx_djxx_bgxx_page
		// .getElementById("bgItemContainer");
		// if (null != biangeng) {
		// Elements biangeng_trs = biangeng.select("tr");
		// if (null != biangeng_trs && biangeng_trs.size() > 0) {
		// for (int i = 0; i < biangeng_trs.size(); i++) {
		// AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
		// gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td")
		// .get(0).text());// 变更事项
		// Elements biangeng_one = biangeng_trs.get(i)
		// .select("td").get(1).getElementsByTag("span");
		// if (null != biangeng_one && biangeng_one.size() > 1) {
		// gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
		// .select("td").get(1).select("span").get(1)
		// .ownText());// 变更前内容
		// } else {
		// gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
		// .select("td").get(1).text());// 变更前内容
		// }
		// // if (null != biangeng_one && biangeng_one.size() > 0)
		// // {
		// // gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
		// // .select("td").get(1).select("span").get(0)
		// // .attr("completedata"));// 变更前内容
		// // } else {
		// // gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
		// // .select("td").get(1).text());// 变更前内容
		// // }
		// Elements biangeng_two = biangeng_trs.get(i)
		// .select("td").get(2).getElementsByTag("span");
		// if (null != biangeng_two && biangeng_two.size() > 1) {
		// gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
		// .select("td").get(2).select("span").get(1)
		// .ownText());// 变更后内容
		// } else {
		// gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
		// .select("td").get(2).text());// 变更后内容
		// }
		// // if (null != biangeng_two && biangeng_two.size() > 0)
		// // {
		// // gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
		// // .select("td").get(2).select("span").get(0)
		// // .attr("completedata"));// 变更后内容
		// // } else {
		// // gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
		// // .select("td").get(2).text());// 变更后内容
		// // }
		// gsgsDjBgInfo.setDateTime(biangeng_trs.get(i)
		// .select("td").get(3).text());// 变更日期
		// gsgsDjBgInfos.add(gsgsDjBgInfo);
		// }
		// }
		// gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);// 变更信息
		// }
		// }
		gsgsInfo.setRegInfo(gsgsDjInfo);// 工商公示信息->登记信息

		// 工商公示信息->备案信息
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
		// 工商公示信息->备案信息->主要人员信息
		if (null != htmlMap.get("gsgsxx_baxx_zyryxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_baxx_zyryxx")))) {
			String gsgsxx_baxx_zyryxx_page = htmlMap.get("gsgsxx_baxx_zyryxx")
					.toString();
			String[] aaa = gsgsxx_baxx_zyryxx_page.split("zyry_nz_paging\\(");
			if (null != aaa && aaa.length > 1) {
				String htmlJson = aaa[1].split("\\]")[0] + "]";
				List<Zyryxx> zyryxxList = gson.fromJson(htmlJson,
						new TypeToken<List<Zyryxx>>() {
						}.getType());
				if (null != zyryxxList && !zyryxxList.isEmpty()) {
					// 工商公示信息->备案信息->主要人员信息
					List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
					for (Zyryxx zyryxx : zyryxxList) {
						// 主要人员信息
						AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
						gsgsBaZyryInfo.setName(zyryxx.getName());// 姓名
						gsgsBaZyryInfo.setPosition(zyryxx.getPositionName());// 职务
						gsgsBaZyryInfos.add(gsgsBaZyryInfo);
					}
					gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
				}
			}
			String[] bbb = gsgsxx_baxx_zyryxx_page.split("zyry_nm_paging\\(");
			if (null != bbb && bbb.length > 1) {
				String htmlJson = bbb[1].split("\\]")[0] + "]";
				List<GsgsxxBaxxCymc> gsgsxxBaxxCymcList = gson.fromJson(
						htmlJson, new TypeToken<List<GsgsxxBaxxCymc>>() {
						}.getType());
				if (null != gsgsxxBaxxCymcList && !gsgsxxBaxxCymcList.isEmpty()) {
					// 工商公示信息->备案信息->成员名册
					List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
					for (GsgsxxBaxxCymc gsgsxxBaxxCymc : gsgsxxBaxxCymcList) {
						// 主要人员信息
						AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
						gsgsBaZyryInfo.setName(gsgsxxBaxxCymc.getInv());// 姓名
						gsgsBaZyryInfos.add(gsgsBaZyryInfo);
					}
					gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
				}
			}
		}
		// // 工商公示信息->备案信息->主要人员信息
		// if (null != htmlMap.get("gsgsxx_baxx_zyryxx")
		// && !StringUtils.isEmpty(String.valueOf(htmlMap
		// .get("gsgsxx_baxx_zyryxx")))) {
		// Document gsgsxx_baxx_zyryxx_page = Jsoup.parseBodyFragment(htmlMap
		// .get("gsgsxx_baxx_zyryxx").toString());
		// Element beian_zyryxx = gsgsxx_baxx_zyryxx_page
		// .getElementById("zyryNzIc");
		// Elements beian_zyryxx_trs = null;
		// int beian_zyryxx_size = 0;
		// // 工商公示信息->备案信息->主要人员信息
		// List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new
		// ArrayList<AicpubArchivePrimemberInfo>();
		// if (null != beian_zyryxx) {
		// beian_zyryxx_trs = beian_zyryxx.select("tr");
		// if (null != beian_zyryxx_trs) {
		// beian_zyryxx_size = beian_zyryxx_trs.size();
		// }
		// }
		// if (beian_zyryxx_size > 0) {
		// for (int i = 0; i < beian_zyryxx_size; i++) {
		// int zyryxx_size = beian_zyryxx_trs.get(i).select("td")
		// .size();
		// String name1 = beian_zyryxx_trs.get(i).select("td").get(1)
		// .text();
		// String position1 = beian_zyryxx_trs.get(i).select("td")
		// .get(2).text();
		// String name2 = "";
		// String position2 = "";
		// if (zyryxx_size > 5) {
		// name2 = beian_zyryxx_trs.get(i).select("td").get(4)
		// .text();
		// position2 = beian_zyryxx_trs.get(i).select("td").get(5)
		// .text();
		// }
		// // 主要人员信息
		// AicpubArchivePrimemberInfo gsgsBaZyryInfo1 = new
		// AicpubArchivePrimemberInfo();
		// // 主要人员信息
		// AicpubArchivePrimemberInfo gsgsBaZyryInfo2 = new
		// AicpubArchivePrimemberInfo();
		// if (!"".equals(name1) && null != name1
		// && !StringUtils.isEmpty(name1.trim())) {
		// gsgsBaZyryInfo1.setName(name1);// 姓名
		// gsgsBaZyryInfo1.setPosition(position1);// 职务
		// gsgsBaZyryInfos.add(gsgsBaZyryInfo1);
		// }
		// if (!"".equals(name2) && null != name2
		// && !StringUtils.isEmpty(name2.trim())) {
		// gsgsBaZyryInfo2.setName(name2);// 姓名
		// gsgsBaZyryInfo2.setPosition(position2);// 职务
		// gsgsBaZyryInfos.add(gsgsBaZyryInfo2);
		// }
		// }
		// }
		// gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
		// }
		if (null != htmlMap.get("gsgsxx_baxx_zgbmxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_baxx_zgbmxx")))) {
			String gsgsxx_baxx_zgbmxx_page = htmlMap.get("gsgsxx_baxx_zgbmxx")
					.toString();
			String[] wdc = gsgsxx_baxx_zgbmxx_page.split("tzr_fgsfr_paging\\(");
			String htmlJson = "";
			if (null != wdc && wdc.length > 1) {
				htmlJson = wdc[1].split("\\]")[0] + "]";
			}
			if (StringUtils.isNotEmpty(htmlJson)) {
				List<Zgbmxx> zgbmxxList = gson.fromJson(htmlJson,
						new TypeToken<List<Zgbmxx>>() {
						}.getType());
				if (null != zgbmxxList && zgbmxxList.size() > 0) {
					// 工商公示信息->备案信息->主管部门（出资人）信息
					List<AicpubArchiveMainDeptInfo> mainDeptInfo = new ArrayList<AicpubArchiveMainDeptInfo>();
					for (Zgbmxx zgbmxx : zgbmxxList) {
						// 主管部门（出资人）信息
						AicpubArchiveMainDeptInfo aicpubArchiveMainDeptInfo = new AicpubArchiveMainDeptInfo();
						aicpubArchiveMainDeptInfo.setType(zgbmxx
								.getInvtypeName());// 出资人类型
						aicpubArchiveMainDeptInfo.setName(zgbmxx.getInv());// 出资人
						aicpubArchiveMainDeptInfo.setIdType(zgbmxx
								.getBlictypeName());// 证照/证件类型
						aicpubArchiveMainDeptInfo.setIdNum(zgbmxx.getBlicno());// 证照/证件号码
						mainDeptInfo.add(aicpubArchiveMainDeptInfo);
					}
					gsgsBaInfo.setMainDeptInfo(mainDeptInfo);// 主管部门（出资人）信息
				}
			}
		}
		// if (null != htmlMap.get("gsgsxx_baxx_zgbmxx")
		// && !StringUtils.isEmpty(String.valueOf(htmlMap
		// .get("gsgsxx_baxx_zgbmxx")))) {
		// Document gsgsxx_baxx_zgbmxx_page = Jsoup.parseBodyFragment(htmlMap
		// .get("gsgsxx_baxx_zgbmxx").toString());
		// Element beian_zgbmxx = gsgsxx_baxx_zgbmxx_page
		// .getElementById("tzr_fgsfr_itemContainer");
		// if (null != beian_zgbmxx) {
		// Elements beian_zgbmxx_trs = beian_zgbmxx.select("tr");
		// if (null != beian_zgbmxx_trs && beian_zgbmxx_trs.size() > 0) {
		// // 工商公示信息->备案信息->主管部门（出资人）信息
		// List<AicpubArchiveMainDeptInfo> mainDeptInfo = new
		// ArrayList<AicpubArchiveMainDeptInfo>();
		// for (int i = 0; i < beian_zgbmxx_trs.size(); i++) {
		// // 主管部门（出资人）信息
		// AicpubArchiveMainDeptInfo aicpubArchiveMainDeptInfo = new
		// AicpubArchiveMainDeptInfo();
		// aicpubArchiveMainDeptInfo.setType(beian_zgbmxx_trs
		// .get(i).select("td").get(1).text());// 出资人类型
		// aicpubArchiveMainDeptInfo.setName(beian_zgbmxx_trs
		// .get(i).select("td").get(2).text());// 出资人
		// aicpubArchiveMainDeptInfo.setIdType(beian_zgbmxx_trs
		// .get(i).select("td").get(3).text());// 证照/证件类型
		// aicpubArchiveMainDeptInfo.setIdNum(beian_zgbmxx_trs
		// .get(i).select("td").get(4).text());// 证照/证件号码
		// mainDeptInfo.add(aicpubArchiveMainDeptInfo);
		// }
		// gsgsBaInfo.setMainDeptInfo(mainDeptInfo);// 主管部门（出资人）信息
		// }
		// }
		// }
		if (null != htmlMap.get("gsgsxx_baxx_fzjgxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_baxx_fzjgxx")))) {
			String gsgsxx_baxx_fzjgxx_page = htmlMap.get("gsgsxx_baxx_fzjgxx")
					.toString();
			String htmlJson = gsgsxx_baxx_fzjgxx_page.split("fzjgPaging\\(")[1]
					.split("\\]")[0] + "]";
			List<Fzjgxx> fzjgxxList = gson.fromJson(htmlJson,
					new TypeToken<List<Fzjgxx>>() {
					}.getType());
			if (null != fzjgxxList && !fzjgxxList.isEmpty()) {
				// 工商公示信息->备案信息->分支机构信息
				List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
				for (Fzjgxx fzjgxx : fzjgxxList) {
					// 分支机构信息
					AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
					gsgsBaFzjgInfo.setNum(fzjgxx.getRegno());// 统一社会信用代码/注册号
					gsgsBaFzjgInfo.setName(fzjgxx.getBrname());// 名称
					gsgsBaFzjgInfo.setRegAuthority(fzjgxx.getRegorgName());// 登记机关
					gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
				}
				gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);// 分支机构信息
			}
		}
		// if (null != htmlMap.get("gsgsxx_baxx_fzjgxx")
		// && !StringUtils.isEmpty(String.valueOf(htmlMap
		// .get("gsgsxx_baxx_fzjgxx")))) {
		// Document gsgsxx_baxx_fzjgxx_page = Jsoup.parseBodyFragment(htmlMap
		// .get("gsgsxx_baxx_fzjgxx").toString());
		// // 工商公示信息->备案信息->分支机构信息
		// List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new
		// ArrayList<AicpubArchiveBranchInfo>();
		// Elements beian_fzjgxx = gsgsxx_baxx_fzjgxx_page.getElementById(
		// "fzjgItemContainer").select("tr");
		// if (null != beian_fzjgxx && beian_fzjgxx.size() > 0) {
		// int beian_fzjgxx_size = beian_fzjgxx.size();
		// if (beian_fzjgxx_size > 0) {
		// for (int i = 0; i < beian_fzjgxx_size; i++) {
		// // 分支机构信息
		// AicpubArchiveBranchInfo gsgsBaFzjgInfo = new
		// AicpubArchiveBranchInfo();
		// gsgsBaFzjgInfo.setNum(beian_fzjgxx.get(i).select("td")
		// .get(1).text());// 统一社会信用代码/注册号
		// gsgsBaFzjgInfo.setName(beian_fzjgxx.get(i).select("td")
		// .get(2).text());// 名称
		// gsgsBaFzjgInfo.setRegAuthority(beian_fzjgxx.get(i)
		// .select("td").get(3).text());// 登记机关
		// gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
		// }
		// }
		// }
		// gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);// 分支机构信息
		// }
		if (null != htmlMap.get("gsgsxx_baxx_qsxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_baxx_qsxx")))) {
			Document gsgsxx_baxx_qsxx_page = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_baxx_qsxx").toString());
			// 工商公示信息->备案信息->清算信息
			AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
			Elements beian_qingsuanrenyuan = gsgsxx_baxx_qsxx_page
					.select("table").get(0).select("tr");
			if (null != beian_qingsuanrenyuan
					&& beian_qingsuanrenyuan.size() > 3) {
				gsgsBaQsInfo.setLeader(beian_qingsuanrenyuan.get(2)
						.select("td").get(0).text());// 清算组负责人
				List<String> wd = new ArrayList<String>();
				wd.add(beian_qingsuanrenyuan.get(2).select("td").get(1).text());
				gsgsBaQsInfo.setMembers(wd);// 清算组成员
				if (isDebug) {
					gsgsBaQsInfo.setHtml(beian_qingsuanrenyuan.html());
				}
			}

			gsgsBaInfo.setClearInfo(gsgsBaQsInfo);// 清算信息
		}
		gsgsInfo.setArchiveInfo(gsgsBaInfo);// 工商公示信息->备案信息

		// 工商公示信息->动产抵押登记信息
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		if (null != htmlMap.get("gsgsxx_dcdydjxx_dcdydjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_dcdydjxx_dcdydjxx")))) {
			Document gsgsxx_dcdydjxx_dcdydjxx_page = Jsoup
					.parseBodyFragment(String.valueOf(htmlMap
							.get("gsgsxx_dcdydjxx_dcdydjxx")));
			Element dongchandiya_et = gsgsxx_dcdydjxx_dcdydjxx_page
					.getElementById("dcdydjItemContainer");
			// 工商公示信息->动产抵押登记信息列表
			List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
			if (null != dongchandiya_et) {
				Elements dongchandiya_trs = dongchandiya_et.select("tr");
				if (null != dongchandiya_trs && dongchandiya_trs.size() > 0) {
					for (int j = 0; j < dongchandiya_trs.size(); j++) {
						AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
						gsgsDcdydjDcdydjInfo.setRegNum(dongchandiya_trs.get(j)
								.select("td").get(1).text());// 登记编号
						gsgsDcdydjDcdydjInfo.setRegDateTime(dongchandiya_trs
								.get(j).select("td").get(2).text());// 登记日期
						gsgsDcdydjDcdydjInfo.setRegAuthority(dongchandiya_trs
								.get(j).select("td").get(3).text());// 登记机关
						gsgsDcdydjDcdydjInfo
								.setGuaranteedDebtAmount(dongchandiya_trs
										.get(j).select("td").get(4).text());// 被担保债权数额
						gsgsDcdydjDcdydjInfo.setStatus(dongchandiya_trs.get(j)
								.select("td").get(5).text());// 状态
						gsgsDcdydjDcdydjInfo.setPubDateTime(dongchandiya_trs
								.get(j).select("td").get(6).text());// 公示时间
						gsgsDcdydjDcdydjInfo.setDetail(dongchandiya_trs.get(j)
								.select("td").get(7).text());// 详情
						gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
					}
				}
				if (isDebug) {
					gsgsDcdydjInfo.setHtml(dongchandiya_et.html());
				}
			}
			gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);// 动产抵押登记信息
		}
		gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息

		// 工商公示信息->股权出资登记信息
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		if (null != htmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_gqczdjxx_gqczdjxx")))) {
			Document gsgsxx_gqczdjxx_gqczdjxx_page = Jsoup
					.parseBodyFragment(htmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")
							.toString());
			Element guquanchuzhi_et = gsgsxx_gqczdjxx_gqczdjxx_page
					.getElementById("gqczgjItemContainer");
			// 工商公示信息->股权出资登记信息列表
			List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
			if (null != guquanchuzhi_et) {
				Elements guquanchuzhi_trs = guquanchuzhi_et.select("tr");
				if (null != guquanchuzhi_trs && guquanchuzhi_trs.size() > 0) {
					for (int i = 0; i < guquanchuzhi_trs.size(); i++) {
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
						gsgsGqczdjGqczdjInfo.setPubDate(guquanchuzhi_trs.get(i)
								.select("td").get(9).text());// 公示时间
						gsgsGqczdjGqczdjInfo.setChangeSitu(guquanchuzhi_trs
								.get(i).select("td").get(10).text());// 变化情况
						gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
					}
				}
				if (isDebug) {
					gsgsGqczdjInfo.setHtml(guquanchuzhi_et.html());
				}
			}
			gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);// 股权出资登记信息
		}
		gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);// 工商公示信息->股权出资登记信息

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
					.getElementById("xzcfItemContainer");
			if (null != xingzhengchufa) {
				Elements xingzhengchufa_trs = xingzhengchufa.select("tr");
				if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 0) {
					for (int i = 0; i < xingzhengchufa_trs.size(); i++) {
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
		}
		gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);// 工商公示信息->行政处罚信息

		// 工商公示信息->经营异常信息
		AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
		if (null != htmlMap.get("gsgsxx_jyycxx_jyycxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_jyycxx_jyycxx")
						.toString())) {
			Document gsgsxx_jyycxx_jyycxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_jyycxx_jyycxx").toString());
			List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();// 经营异常信息列表
			Elements yichangminglu_trs = gsgsxx_jyycxx_jyycxx.getElementById(
					"jyyc_itemContainer").select("tr");
			int yichangminglu_trs_size = yichangminglu_trs.size();
			if (yichangminglu_trs_size > 0) {
				for (int i = 0; i < yichangminglu_trs_size; i++) {
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
			if (isDebug && null != yichangminglu_trs) {
				gsgsJyycInfo.setHtml(yichangminglu_trs.html());
			}
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
					.getElementById("yzwf_itemContainer");
			// 工商公示信息->严重违法信息列表
			List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
			if (null != heimingdan_et) {
				Elements heimingdan_trs = heimingdan_et.select("tr");
				if (null != heimingdan_trs && heimingdan_trs.size() > 0) {
					for (int j = 0; j < heimingdan_trs.size(); j++) {
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
					gsgsYzwfInfo.setHtml(heimingdan_et.html());
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
			Elements chouchaxinxi_trs = gsgsxx_ccjcxx_ccjcxx.getElementById(
					"ccjc_itemContainer").select("tr");
			int chouchaxinxi_trs_size = chouchaxinxi_trs.size();
			if (chouchaxinxi_trs_size > 0) {
				for (int i = 0; i < chouchaxinxi_trs_size; i++) {
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
				Element qygsxx_qynb_jbxx = qygsxx_qynb_info_page
						.getElementById("qufenkuang").select("table").get(0)
						.select("tbody").get(0);
				Elements qygsxx_qynb_jbxx_ths = qygsxx_qynb_jbxx.select("th");
				for (int i = 2; i < qygsxx_qynb_jbxx_ths.size(); i++) {
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("注册号")
							|| qygsxx_qynb_jbxx_ths.get(i).text()
									.contains("统一社会信用代码")) {
						qygsQynbJbInfo.setNum(qygsxx_qynb_jbxx.select("td")
								.get(i - 2).text());// 注册号/统一社会信用代码
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("企业名称")) {
						qygsQynbJbInfo.setName(qygsxx_qynb_jbxx.select("td")
								.get(i - 2).text());// 企业名称
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("企业联系电话")) {
						qygsQynbJbInfo.setTel(qygsxx_qynb_jbxx.select("td")
								.get(i - 2).text());// 企业联系电话
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("邮政编码")) {
						qygsQynbJbInfo.setZipCode(qygsxx_qynb_jbxx.select("td")
								.get(i - 2).text());// 邮政编码
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("企业通信地址")) {
						qygsQynbJbInfo.setAddress(qygsxx_qynb_jbxx.select("td")
								.get(i - 2).text());// 企业通信地址
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("电子邮箱")) {
						qygsQynbJbInfo.setEmail(qygsxx_qynb_jbxx.select("td")
								.get(i - 2).text());// 电子邮箱
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text()
							.contains("有限责任公司本年度是否发生股东股权转让")) {
						qygsQynbJbInfo
								.setIsStohrEquTransferred(qygsxx_qynb_jbxx
										.select("td").get(i - 2).text());// 有限责任公司本年度是否发生股东股权转让
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("企业经营状态")) {
						qygsQynbJbInfo.setOperatingStatus(qygsxx_qynb_jbxx
								.select("td").get(i - 2).text());// 企业经营状态
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("是否有网站或网店")) {
						qygsQynbJbInfo.setHasWebsiteOrStore(qygsxx_qynb_jbxx
								.select("td").get(i - 2).text());// 是否有网站或网店
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text()
							.contains("企业是否有投资信息或购买其他公司股权")) {
						qygsQynbJbInfo
								.setHasInvestInfoOrPurchOtherCorpEqu(qygsxx_qynb_jbxx
										.select("td").get(i - 2).text());// 企业是否有投资信息或购买其他公司股权
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text().contains("从业人数")) {
						qygsQynbJbInfo.setEmpNum(qygsxx_qynb_jbxx.select("td")
								.get(i - 2).text());// 从业人数
					}
					if (qygsxx_qynb_jbxx_ths.get(i).text()
							.contains("是否有对外担保信息")) {
						qygsQynbJbInfo
								.setHasExternalSecurity(qygsxx_qynb_jbxx_ths
										.get(i).nextElementSibling().text());// 是否有对外担保信息
					}
				}
				qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);// 企业公示信息->企业年报->基本信息
				Element chuziren = qygsxx_qynb_info_page
						.getElementById("czItemContainer");
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();// 股东及出资信息
				if (null != chuziren) {
					Elements qyjbxx_gd_trs = chuziren.select("tr");
					int qyjbxx_gd_trs_size = qyjbxx_gd_trs.size();
					if (qyjbxx_gd_trs_size > 0) {
						for (int i = 0; i < qyjbxx_gd_trs_size; i++) {
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
				qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);// 企业公示信息->股东及出资信息
				// 企业资产状况信息
				EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
				if (null != qygsxx_qynb_info_page.getElementById("assgro")) {
					qygsQynbQyzczkInfo.setAssetAmount(qygsxx_qynb_info_page
							.getElementById("assgro").nextElementSibling()
							.text());// 资产总额
				}
				if (null != qygsxx_qynb_info_page.getElementById("totequ")) {
					qygsQynbQyzczkInfo.setTotalEquity(qygsxx_qynb_info_page
							.getElementById("totequ").nextElementSibling()
							.text());// 所有者权益合计
				}
				if (null != qygsxx_qynb_info_page.getElementById("vendinc")) {
					qygsQynbQyzczkInfo.setSalesAmount(qygsxx_qynb_info_page
							.getElementById("vendinc").nextElementSibling()
							.text());// 销售总额
				}
				if (null != qygsxx_qynb_info_page.getElementById("progro")) {
					qygsQynbQyzczkInfo.setProfitAmount(qygsxx_qynb_info_page
							.getElementById("progro").nextElementSibling()
							.text());// 利润总额
				}
				if (null != qygsxx_qynb_info_page.getElementById("maibusinc")) {
					qygsQynbQyzczkInfo
							.setPriBusiIncomeInSalesAmount(qygsxx_qynb_info_page
									.getElementById("maibusinc")
									.nextElementSibling().text());// 销售总额中主营业务收入
				}
				if (null != qygsxx_qynb_info_page.getElementById("netinc")) {
					qygsQynbQyzczkInfo.setNetProfit(qygsxx_qynb_info_page
							.getElementById("netinc").nextElementSibling()
							.text());// 净利润
				}
				if (null != qygsxx_qynb_info_page.getElementById("ratgro")) {
					qygsQynbQyzczkInfo.setTaxesAmount(qygsxx_qynb_info_page
							.getElementById("ratgro").nextElementSibling()
							.text());// 纳税总额
				}
				if (null != qygsxx_qynb_info_page.getElementById("liagro")) {
					qygsQynbQyzczkInfo.setLiabilityAmount(qygsxx_qynb_info_page
							.getElementById("liagro").nextElementSibling()
							.text());// 负债总额
				}
				qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);// 企业资产状况信息
				Element dwtgdb_et = qygsxx_qynb_info_page
						.getElementById("dbItemContainer");
				// 对外提供保证担保信息
				List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();
				if (null != dwtgdb_et) {
					Elements dwtgdb_trs = dwtgdb_et.select("tr");
					if (null != dwtgdb_trs && dwtgdb_trs.size() > 0) {
						for (int i = 0; i < dwtgdb_trs.size(); i++) {
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
							qygsQynbDwtgbzdbInfo.setGuaranteeScope(dwtgdb_trs
									.get(i).select("td").get(7).text());// 保证担保的范围
							qygsQynbDwtgbzdbInfos.add(qygsQynbDwtgbzdbInfo);
						}
					}
				}
				qygsQynbInfo.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);// 对外提供保证担保信息
				Element xgjl_et = qygsxx_qynb_info_page
						.getElementById("xgItemContainer");
				// 修改记录
				List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
				if (null != xgjl_et) {
					Elements xgjl_trs = xgjl_et.select("tr");
					if (null != xgjl_trs && xgjl_trs.size() > 0) {
						for (int i = 0; i < xgjl_trs.size(); i++) {
							EntpubAnnreportModifyInfo qygsQynbXgjlInfo = new EntpubAnnreportModifyInfo();
							qygsQynbXgjlInfo.setItem(xgjl_trs.get(i)
									.select("td").get(1).text());
							qygsQynbXgjlInfo.setPreContent(xgjl_trs.get(i)
									.select("td").get(2).text());
							qygsQynbXgjlInfo.setPostContent(xgjl_trs.get(i)
									.select("td").get(3).text());
							qygsQynbXgjlInfo.setDateTime(xgjl_trs.get(i)
									.select("td").get(4).text());
							qygsQynbXgjlInfos.add(qygsQynbXgjlInfo);
						}
					}
				}
				qygsQynbInfo.setChangeInfos(qygsQynbXgjlInfos);// 修改记录
				Element wzhwdxx = qygsxx_qynb_info_page
						.getElementById("swItemContainer");
				if (null != wzhwdxx) {
					Elements wzhwdxx_trs = wzhwdxx.select("tr");
					if (null != wzhwdxx_trs && wzhwdxx_trs.size() > 0) {
						// 网站或网店信息
						List<EntpubAnnreportWebsiteInfo> websiteInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
						for (int i = 0; i < wzhwdxx_trs.size(); i++) {
							EntpubAnnreportWebsiteInfo entpubAnnreportWebsiteInfo = new EntpubAnnreportWebsiteInfo();
							entpubAnnreportWebsiteInfo.setType(wzhwdxx_trs
									.get(i).select("td").get(0).text());// 类型
							entpubAnnreportWebsiteInfo.setName(wzhwdxx_trs
									.get(i).select("td").get(1).text());// 名称
							entpubAnnreportWebsiteInfo.setWebsite(wzhwdxx_trs
									.get(i).select("td").get(2).text());// 网址
							websiteInfos.add(entpubAnnreportWebsiteInfo);
						}
						qygsQynbInfo.setWebsiteInfos(websiteInfos);// 网站或网店信息
					}
				}
				Element dwtzxx_et = qygsxx_qynb_info_page
						.getElementById("tzItemContainer");
				if (null != dwtzxx_et) {
					Elements dwtzxx_trs = dwtzxx_et.select("tr");
					if (null != dwtzxx_trs && dwtzxx_trs.size() > 0) {
						// 对外投资信息
						List<EntpubAnnreportExtinvestInfo> extInvestInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
						for (int i = 0; i < dwtzxx_trs.size(); i++) {
							EntpubAnnreportExtinvestInfo entpubAnnreportExtinvestInfo = new EntpubAnnreportExtinvestInfo();
							entpubAnnreportExtinvestInfo
									.setEnterpriseName(dwtzxx_trs.get(i)
											.select("td").get(0).text());// 投资设立企业或购买股权企业名称
							entpubAnnreportExtinvestInfo.setRegNum(dwtzxx_trs
									.get(i).select("td").get(1).text());// 统一社会信用代码/注册号
							extInvestInfos.add(entpubAnnreportExtinvestInfo);
						}
						qygsQynbInfo.setExtInvestInfos(extInvestInfos);// 对外投资信息
					}
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
			if (null != qygsxx_gdjczxx.getElementById("gdjczTab")) {
				Elements gdjczxx_trs = qygsxx_gdjczxx
						.getElementById("gdjczTab").select("tbody").get(0)
						.select("tr");
				int gdjczxx_trs_size = gdjczxx_trs.size();
				if (gdjczxx_trs_size > 4) {
					for (int j = 3; j < gdjczxx_trs_size - 1; j++) {
						// 企业公示信息->股东及出资信息
						EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new EntpubSStohrinvestInfo();
						qygsGdjczGdjczInfo.setStockholder(gdjczxx_trs.get(j)
								.select("td").get(0).text());// 股东
						qygsGdjczGdjczInfo.setSubAmount(gdjczxx_trs.get(j)
								.select("td").get(2).text());// 认缴额（万元）
						qygsGdjczGdjczInfo.setPaidAmount(gdjczxx_trs.get(j)
								.select("td").get(3).text());// 实缴额（万元）
						EntpubSStohrinvestInfo.Detail rjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
						rjDetail.amount = gdjczxx_trs.get(j).select("td")
								.get(4).text();// 出资额（万元）
						rjDetail.method = gdjczxx_trs.get(j).select("td")
								.get(5).text();// 出资方式
						rjDetail.dateTime = gdjczxx_trs.get(j).select("td")
								.get(6).text();// 出资日期
						EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
						sjDetail.amount = gdjczxx_trs.get(j).select("td")
								.get(7).text();// 出资额（万元）
						sjDetail.method = gdjczxx_trs.get(j).select("td")
								.get(8).text();// 出资方式
						sjDetail.dateTime = gdjczxx_trs.get(j).select("td")
								.get(9).text();// 出资日期
						qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
					}
				}
				qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息
			}
		}
		// 变更信息
		List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
		if (null != htmlMap.get("qygsxx_gdjczxx_bgxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("qygsxx_gdjczxx_bgxx")))) {
			Document qygsxx_gdjczxx_bgxx_page = Jsoup.parseBodyFragment(htmlMap
					.get("qygsxx_gdjczxx_bgxx").toString());
			Element qygsxx_gdjczxx_bgxx = qygsxx_gdjczxx_bgxx_page
					.getElementById("tzrbgItemContainer");
			if (null != qygsxx_gdjczxx_bgxx) {
				Elements qygsxx_gdjczxx_bgxx_trs = qygsxx_gdjczxx_bgxx
						.select("tr");
				if (null != qygsxx_gdjczxx_bgxx_trs
						&& qygsxx_gdjczxx_bgxx_trs.size() > 0) {
					for (int i = 0; i < qygsxx_gdjczxx_bgxx_trs.size(); i++) {
						EntpubStohrinvestChangeInfo gygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
						gygsGdjczBgInfo.setItem(qygsxx_gdjczxx_bgxx_trs.get(i)
								.select("td").get(1).text());// 变更事项
						gygsGdjczBgInfo.setDateTime(qygsxx_gdjczxx_bgxx_trs
								.get(i).select("td").get(2).text());// 变更时间
						gygsGdjczBgInfo.setPreContent(qygsxx_gdjczxx_bgxx_trs
								.get(i).select("td").get(3).text());// 变更前
						gygsGdjczBgInfo.setPostContent(qygsxx_gdjczxx_bgxx_trs
								.get(i).select("td").get(4).text());// 变更后
						qygsGdjczBgInfos.add(gygsGdjczBgInfo);
					}
				}
			}
			qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);// 变更信息
		}
		qygsInfo.setStohrInvestInfo(qygsGdjczInfo);// 企业公示信息->股东及出资信息

		// 企业公示信息->股权变更信息
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		if (null != htmlMap.get("qygsxx_gqbgxx")
				&& !StringUtils
						.isEmpty(htmlMap.get("qygsxx_gqbgxx").toString())) {
			Document qygsxx_gqbgxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_gqbgxx")));
			Element gudongguquan_et = qygsxx_gqbgxx_page
					.getElementById("gqbgItemContainer");
			// 企业公示信息->股权变更信息列表
			List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
			if (null != gudongguquan_et) {
				Elements gudongguquan_trs = gudongguquan_et.select("tr");
				if (null != gudongguquan_trs && gudongguquan_trs.size() > 0) {
					for (int i = 0; i < gudongguquan_trs.size(); i++) {
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
		}

		// 企业公示信息->行政许可信息
		EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
		if (null != htmlMap.get("qygsxx_xzxkxx")
				&& !StringUtils
						.isEmpty(htmlMap.get("qygsxx_xzxkxx").toString())) {
			Document qygsxx_xzxkxx_page = Jsoup.parseBodyFragment(htmlMap.get(
					"qygsxx_xzxkxx").toString());
			Element xzxkxx_et = qygsxx_xzxkxx_page
					.getElementById("xzxkItemContainer");
			// 企业公示信息->行政许可信息列表
			List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
			if (null != xzxkxx_et) {
				Elements xingzhengxuke_trs = xzxkxx_et.select("tr");
				if (null != xingzhengxuke_trs && xingzhengxuke_trs.size() > 0) {
					for (int i = 0; i < xingzhengxuke_trs.size(); i++) {
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
						qygsXzxkXzxkInfo.setDetail(xingzhengxuke_trs.get(i)
								.select("td").get(9).text());// 详情
						qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
					}
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
					.getElementById("czdjItemContainer");
			// 企业公示信息->知识产权出质登记信息列表
			List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
			if (null != zhishichanquan_et) {
				Elements zhishichanquan_trs = zhishichanquan_et.select("tr");
				if (null != zhishichanquan_trs && zhishichanquan_trs.size() > 0) {
					for (int i = 0; i < zhishichanquan_trs.size(); i++) {
						// 企业公示信息->知识产权出质登记信息
						EntpubIIntellectualproregInfo qygsZscqczdjZscqczdjInfo = new EntpubIIntellectualproregInfo();
						qygsZscqczdjZscqczdjInfo.setRegNum("");// 注册号
						qygsZscqczdjZscqczdjInfo.setName("");// 名称
						qygsZscqczdjZscqczdjInfo.setType("");// 种类
						qygsZscqczdjZscqczdjInfo.setMortgagorName("");// 出质人名称
						qygsZscqczdjZscqczdjInfo.setMortgageeName("");// 质权人名称
						qygsZscqczdjZscqczdjInfo.setPledgeRegDeadline("");// 质权登记期限
						qygsZscqczdjZscqczdjInfo.setChangeSitu("");// 变化情况
						qygsZscqczdjZscqczdjInfo.setStatus("");// 状态（暂无）
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
					.getElementById("qycfItemContainer");
			// 企业公示信息->行政处罚信息列表
			List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
			if (null != xingzhengchufa_et) {
				Elements xingzhengchufa_trs = xingzhengchufa_et.select("tr");
				if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 0) {
					for (int i = 0; i < xingzhengchufa_trs.size(); i++) {
						// 企业公示信息->行政处罚信息
						EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();
						qygsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs
								.get(i).select("td").get(1).text());// 行政处罚决定书文号
						qygsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs
								.get(i).select("td").get(2).text());// 违法行为类型
						qygsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs
								.get(i).select("td").get(3).text());// 行政处罚内容
						qygsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs
								.get(i).select("td").get(4).text());// 作出行政处罚决定机关名称
						qygsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs
								.get(i).select("td").get(5).text());// 作出行政处罚决定日期
						qygsXzcfXzcfInfo.setNote(xingzhengchufa_trs.get(i)
								.select("td").get(6).text());// 备注
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
					.getElementById("s_qt_xzxkxx");
			// 其他部门公示信息->行政许可信息列表
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			if (null != xingzhengxuke_et) {
				Elements xingzhengxuke_trs = xingzhengxuke_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengxuke_trs && xingzhengxuke_trs.size() > 3) {
					for (int i = 2; i < xingzhengxuke_trs.size() - 1; i++) {
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
						qtbmgsXzxkXzxkInfo.setDetail("");// 详情（辽宁工商网暂无）
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
					.getElementById("s_qt_xzcfxx");
			// 其他部门公示信息->行政处罚信息列表
			List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();
			if (null != xingzhengchufa_et) {
				Elements xingzhengchufa_trs = xingzhengchufa_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 3) {
					for (int i = 2; i < xingzhengchufa_trs.size() - 1; i++) {
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
		if (null != htmlMap.get("sfxzgsxx_gqdjxx")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx_gqdjxx")
						.toString())) {
			Document sfxzgsxx_gqdjxx_list_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx_gqdjxx")));
			Element guquandongjie_et = sfxzgsxx_gqdjxx_list_page
					.getElementById("gqdj_itemContainer");
			// 司法协助公示信息->股权冻结信息列表
			List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
			if (null != guquandongjie_et) {
				Elements guquandongjie_trs = guquandongjie_et.select("tr");
				if (null != guquandongjie_trs && guquandongjie_trs.size() > 0) {
					for (int i = 0; i < guquandongjie_trs.size(); i++) {
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
		if (null != htmlMap.get("sfxzgsxx_gdbgxx")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx_gdbgxx")
						.toString())) {
			Document sfxzgsxx_gqbgxx_list_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx_gdbgxx")));
			Element guquanbiangeng_et = sfxzgsxx_gqbgxx_list_page
					.getElementById("gdbg_itemContainer");
			// 司法协助公示信息->股东变更信息列表
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			if (null != guquanbiangeng_et) {
				Elements guquanbiangeng_trs = guquanbiangeng_et.select("tr");
				if (null != guquanbiangeng_trs && guquanbiangeng_trs.size() > 0) {
					for (int i = 0; i < guquanbiangeng_trs.size(); i++) {
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

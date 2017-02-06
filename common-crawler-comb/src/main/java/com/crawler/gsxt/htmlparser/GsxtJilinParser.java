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

import com.crawler.gsxt.domain.json.AicFeedJson;
import com.crawler.gsxt.domain.json.AicpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.AicpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveBranchInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveClearInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveInfo;
import com.crawler.gsxt.domain.json.AicpubArchivePrimemberInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgInfo;
import com.crawler.gsxt.domain.json.AicpubCCheckInfo;
import com.crawler.gsxt.domain.json.AicpubChatMortgInfo;
import com.crawler.gsxt.domain.json.AicpubCheckInfo;
import com.crawler.gsxt.domain.json.AicpubEEqumortgregInfo;
import com.crawler.gsxt.domain.json.AicpubEqumortgregInfo;
import com.crawler.gsxt.domain.json.AicpubInfo;
import com.crawler.gsxt.domain.json.AicpubOOperanomaInfo;
import com.crawler.gsxt.domain.json.AicpubOperanomaInfo;
import com.crawler.gsxt.domain.json.AicpubRegBaseInfo;
import com.crawler.gsxt.domain.json.AicpubRegChangeInfo;
import com.crawler.gsxt.domain.json.AicpubRegInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.crawler.gsxt.domain.json.AicpubSSerillegalInfo;
import com.crawler.gsxt.domain.json.AicpubSerillegalInfo;
import com.crawler.gsxt.domain.json.EntpubAAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAssetInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportBaseInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportExtguaranteeInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportModifyInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubEEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubIIntellectualproregInfo;
import com.crawler.gsxt.domain.json.EntpubInfo;
import com.crawler.gsxt.domain.json.EntpubIntellectualproregInfo;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubStohrinvestChangeInfo;
import com.crawler.gsxt.domain.json.EntpubStohrinvestInfo;
import com.crawler.gsxt.domain.json.JudasspubEEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubInfo;
import com.crawler.gsxt.domain.json.JudasspubSStohrchangeInfo;
import com.crawler.gsxt.domain.json.JudasspubStohrchangeInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubInfo;
import com.crawler.gsxt.domain.json.jilin.BaxxZyryxx;
import com.crawler.gsxt.domain.json.jilin.CcjcxxCcjcxx;
import com.crawler.gsxt.domain.json.jilin.DjxxBgxx;
import com.crawler.gsxt.domain.json.jilin.GdjczGdjcz;
import com.crawler.gsxt.domain.json.jilin.GdjczxxBgxx;
import com.crawler.gsxt.domain.json.jilin.GdjczxxBgxxList;
import com.crawler.gsxt.domain.json.jilin.JilinDateTime;
import com.crawler.gsxt.domain.json.jilin.JyycxxJyycxx;
import com.crawler.gsxt.domain.json.jilin.Rjmx;
import com.crawler.gsxt.domain.json.jilin.Sjmx;
import com.crawler.gsxt.domain.json.jilin.Xzxkxx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtJilinParser extends AbstractGsxtParser {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GsxtJilinParser.class);

	public AicFeedJson getJilinResultObj(String html, boolean isDebug) {

		LOGGER.info("The method of GsxtJilinParser.getJilinResultObj is begin !");

		Gson gson = new GsonBuilder().create();
		Map<String, Object> htmlMap = gson.fromJson(html,
				new TypeToken<Map<String, Object>>() {
				}.getType());

		if (null == htmlMap.get("gsgsxx")
				|| null == String.valueOf(htmlMap.get("gsgsxx"))
				|| StringUtils.isEmpty(String.valueOf(htmlMap.get("gsgsxx")))) {
			LOGGER.info("The html is none!");
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
		Element baseinfo = gsgsxx.getElementById("jibenxinxi");
		Elements ths = baseinfo.select("tr").select("th");
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
		for (int i = 0; i < ths.size(); i++) {
			if (ths.get(i).text().contains("统一社会信用代码")
					|| ths.get(i).text().contains("注册号")) {
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
			if (ths.get(i).text().trim().equalsIgnoreCase("营业期限自")) {
				gsgsDjJbInfo.setStartDateTime(ths.get(i).nextElementSibling()
						.text());// 营业期限自（即营业开始日期）
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("营业期限至")) {
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
		Element touziren = gsgsxx.getElementById("touziren");
		if (null != touziren) {
			Elements touziren_trs = touziren.getElementById("czxxtable")
					.select("tr");
			if (null != touziren_trs && !touziren_trs.isEmpty()) {
				for (int i = 0; i < touziren_trs.size(); i++) {
					// 股东信息
					AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();
					Elements touziren_tds = touziren_trs.get(i).select("td");
					if (null != touziren_tds && touziren_tds.size() > 3) {
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
					List<AmountDetail> subAmountDetails = new ArrayList<AmountDetail>();
					List<AmountDetail> paidAmountDetails = new ArrayList<AmountDetail>();
					gsgsDjGdGdjczInfo.setStockholder(gsgsxx_djxx_tzrxx_xq_trs
							.get(3).select("td").get(0).text());// 股东
					gsgsDjGdGdjczInfo.setSubAmount(gsgsxx_djxx_tzrxx_xq_trs
							.get(3).select("td").get(1).text());// 认缴额（万元）
					gsgsDjGdGdjczInfo.setPaidAmount(gsgsxx_djxx_tzrxx_xq_trs
							.get(3).select("td").get(2).text());// 实缴额（万元）
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
		String wyc = "";
		String wcq = htmlMap.get("gsgsxx").toString();
		String[] wcqs = wcq.split("var bgsxliststr =\'\\[");
		if (null != wcqs && wcqs.length > 1) {
			wyc = "[" + wcqs[1].split("\\]")[0] + "]";
		}
		if (!StringUtils.isEmpty(wyc)) {
			List<DjxxBgxx> djxxBgxxList = gson.fromJson(wyc,
					new TypeToken<List<DjxxBgxx>>() {
					}.getType());
			if (null != djxxBgxxList && !djxxBgxxList.isEmpty()
					&& djxxBgxxList.size() > 0) {
				List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<AicpubRegChangeInfo>();
				for (DjxxBgxx djxxBgxx : djxxBgxxList) {
					AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
					gsgsDjBgInfo.setItem(djxxBgxx.getAltitem());// 变更事项
					gsgsDjBgInfo.setPreContent(djxxBgxx.getAltbe());// 变更前内容
					gsgsDjBgInfo.setPostContent(djxxBgxx.getAltaf());// 变更后内容
					String bgDateTime = "";
					JilinDateTime condate = djxxBgxx.getAltdate();
					if (null != condate) {
						int a = Integer.parseInt(condate.getYear()) + 1900;
						int b = Integer.parseInt(condate.getMonth()) + 1;
						bgDateTime = String.valueOf(a) + "年"
								+ String.valueOf(b) + "月" + condate.getDate()
								+ "日";
					}
					gsgsDjBgInfo.setDateTime(bgDateTime);// 变更日期
					gsgsDjBgInfos.add(gsgsDjBgInfo);
				}
				gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);// 变更信息
			}
		}
		// // 工商公示信息->登记信息->变更信息
		// Element biangeng = gsgsxx.getElementById("bgsxtable");
		// if (null != biangeng) {
		// List<AicpubRegChangeInfo> gsgsDjBgInfos = new
		// ArrayList<AicpubRegChangeInfo>();
		// Elements biangeng_trs = biangeng.select("tr");
		// if (null != biangeng_trs && !biangeng_trs.isEmpty()) {
		// for (int i = 0; i < biangeng_trs.size(); i++) {
		// AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
		// gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td")
		// .get(0).text());// 变更事项
		// Elements biangeng_one = biangeng_trs.get(i).select("td")
		// .get(1).getElementsByTag("span");
		// if (null != biangeng_one && biangeng_one.size() > 1) {
		// gsgsDjBgInfo.setPreContent(biangeng_one.get(1)
		// .ownText());// 变更前内容
		// } else {
		// gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
		// .select("td").get(1).text());// 变更前内容
		// }
		// Elements biangeng_two = biangeng_trs.get(i).select("td")
		// .get(2).getElementsByTag("span");
		// if (null != biangeng_two && biangeng_two.size() > 1) {
		// gsgsDjBgInfo.setPostContent(biangeng_two.get(1)
		// .ownText());// 变更后内容
		// } else {
		// gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
		// .select("td").get(2).text());// 变更后内容
		// }
		// gsgsDjBgInfo.setDateTime(biangeng_trs.get(i).select("td")
		// .get(3).text());// 变更日期
		// gsgsDjBgInfos.add(gsgsDjBgInfo);
		// }
		// }
		// gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);// 变更信息
		// }
		gsgsInfo.setRegInfo(gsgsDjInfo);// 工商公示信息->登记信息

		// 工商公示信息->备案信息
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
		if (null != htmlMap.get("gsgsxx_baxx_zyryxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_baxx_zyryxx")))) {
			// 工商公示信息->备案信息->主要人员信息
			Document gsgsxx_baxx_zyryxx = Jsoup.parseBodyFragment(htmlMap.get(
					"gsgsxx_baxx_zyryxx").toString());
			Elements gsgsxx_baxx_zyryxx_pres = gsgsxx_baxx_zyryxx.select("pre");
			if (null != gsgsxx_baxx_zyryxx_pres
					&& gsgsxx_baxx_zyryxx_pres.size() > 0) {
				String wdd = gsgsxx_baxx_zyryxx_pres.get(0).text();
				List<BaxxZyryxx> baxxZyryxxList = gson.fromJson(wdd,
						new TypeToken<List<BaxxZyryxx>>() {
						}.getType());
				if (null != baxxZyryxxList && baxxZyryxxList.size() > 0) {
					// 工商公示信息->备案信息->主要人员信息
					List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
					for (BaxxZyryxx baxxZyryxx : baxxZyryxxList) {
						AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
						gsgsBaZyryInfo.setName(baxxZyryxx.getName());
						gsgsBaZyryInfo.setPosition(baxxZyryxx.getPosition());
						gsgsBaZyryInfos.add(gsgsBaZyryInfo);
					}
					gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);
				}
			}
		}
		// Element beian = gsgsxx.getElementById("beian");
		// if (null != beian) {
		// Element beian_zyryxx = beian.getElementById("gsgsRyxxList");
		// if (null != beian_zyryxx) {
		// Elements beian_zyryxx_trs = beian_zyryxx.select("tr");
		// if (null != beian_zyryxx_trs && beian_zyryxx_trs.size() > 1) {
		// // 工商公示信息->备案信息->主要人员信息
		// List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new
		// ArrayList<AicpubArchivePrimemberInfo>();
		// for (int i = 0; i < beian_zyryxx_trs.size() - 1; i++) {
		// int td_size = beian_zyryxx_trs.get(i).select("td")
		// .size();
		// String name1 = beian_zyryxx_trs.get(i).select("td")
		// .get(1).text();
		// String position1 = beian_zyryxx_trs.get(i).select("td")
		// .get(2).text();
		// String name2 = "";
		// String position2 = "";
		// if (td_size > 5) {
		// name2 = beian_zyryxx_trs.get(i).select("td").get(4)
		// .text();
		// position2 = beian_zyryxx_trs.get(i).select("td")
		// .get(5).text();
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
		// gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
		// }
		// }
		// }

		Element beian_fzjgxx = gsgsxx.getElementById("gsgsFzjgList");
		if (null != beian_fzjgxx) {
			Elements beian_fzjgxx_trs = beian_fzjgxx.select("tr");
			if (null != beian_fzjgxx_trs && beian_fzjgxx_trs.size() > 0) {
				// 工商公示信息->备案信息->分支机构信息
				List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
				for (int i = 0; i < beian_fzjgxx_trs.size(); i++) {
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
				gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);// 分支机构信息
			}
		}

		Element beian_qingsuanrenyuan = gsgsxx.getElementById("gsgsQsxx");
		if (null != beian_qingsuanrenyuan) {
			// 工商公示信息->备案信息->清算信息
			AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
			Elements beian_qingsuanrenyuan_ths = beian_qingsuanrenyuan
					.select("th");
			for (Element beian_qingsuanrenyuan_th : beian_qingsuanrenyuan_ths) {
				if (beian_qingsuanrenyuan_th.text().contains("清算组负责人 ")) {
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
			gsgsBaInfo.setClearInfo(gsgsBaQsInfo);// 清算信息
		}
		gsgsInfo.setArchiveInfo(gsgsBaInfo);// 工商公示信息->备案信息

		Element dongchandiya_et = gsgsxx.getElementById("gsgsDcdylist");
		if (null != dongchandiya_et) {
			// 工商公示信息->动产抵押登记信息
			AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
			Elements dongchandiya_trs = dongchandiya_et.select("tr");
			if (null != dongchandiya_trs && dongchandiya_trs.size() > 0) {
				// 工商公示信息->动产抵押登记信息列表
				List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
				for (int j = 0; j < dongchandiya_trs.size(); j++) {
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
					gsgsDcdydjDcdydjInfo.setPubDateTime("");// 公示时间（该字段比较特殊，吉林工商网暂时没有该字段。）
					gsgsDcdydjDcdydjInfo.setDetail(dongchandiya_trs.get(j)
							.select("td").get(6).text());// 详情
					gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
				}
				gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);// 动产抵押登记信息
			}
			if (isDebug) {
				gsgsDcdydjInfo.setHtml(dongchandiya_et.html());
			}
			gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息
		}

		Element guquanchuzhi_et = gsgsxx.getElementById("gsgsGqczlist");
		if (null != guquanchuzhi_et) {
			// 工商公示信息->股权出资登记信息
			AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
			Elements guquanchuzhi_trs = guquanchuzhi_et.select("tr");
			if (null != guquanchuzhi_trs && guquanchuzhi_trs.size() > 0) {
				// 工商公示信息->股权出资登记信息列表
				List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
				for (int i = 0; i < guquanchuzhi_trs.size(); i++) {
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
					gsgsGqczdjGqczdjInfo.setPubDate("");// 公示时间（该字段比较特殊，吉林工商网暂时没有该字段。）
					gsgsGqczdjGqczdjInfo.setChangeSitu(guquanchuzhi_trs.get(i)
							.select("td").get(9).text());// 变化情况
					gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
				}
				gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);// 股权出资登记信息
			}
			if (isDebug) {
				gsgsGqczdjInfo.setHtml(guquanchuzhi_et.html());
			}
			gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);// 工商公示信息->股权出资登记信息
		}

		// 工商公示信息->行政处罚信息
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
		// 工商公示信息->行政处罚信息列表
		List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
		Element xingzhengchufa = gsgsxx.getElementById("gsgsxzcflist");
		if (null != xingzhengchufa) {
			Elements xingzhengchufa_trs = xingzhengchufa.select("tr");
			if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 0) {
				for (int i = 0; i < xingzhengchufa_trs.size(); i++) {
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

		if (null != htmlMap.get("gsgsxx_jyycxx_jyycxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_jyycxx_jyycxx")
						.toString())) {
			String gsgsxx_jyycxx_jyycxx = htmlMap.get("gsgsxx_jyycxx_jyycxx")
					.toString();
			Document jyycxxHtml = Jsoup.parseBodyFragment(gsgsxx_jyycxx_jyycxx);
			Elements pres = jyycxxHtml.getElementsByTag("pre");
			if (null != pres && !pres.isEmpty() && pres.size() > 0) {
				String pre = pres.get(0).text();
				if (!StringUtils.isEmpty(pre)) {
					List<JyycxxJyycxx> JyycxxJyycxxList = gson.fromJson(pre,
							new TypeToken<List<JyycxxJyycxx>>() {
							}.getType());
					if (null != JyycxxJyycxxList && !JyycxxJyycxxList.isEmpty()
							&& JyycxxJyycxxList.size() > 0) {
						// 工商公示信息->经营异常信息
						AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
						List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();// 经营异常信息列表
						for (JyycxxJyycxx jyycxxJyycxx : JyycxxJyycxxList) {
							AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();// 经营异常信息
							gsgsJyycJyycInfo.setIncludeCause(jyycxxJyycxx
									.getSpecause());// 列入经营异常名录原因
							String lrycDateTime = "";
							JilinDateTime condate = jyycxxJyycxx.getAbntime();
							if (null != condate) {
								int a = Integer.parseInt(condate.getYear()) + 1900;
								int b = Integer.parseInt(condate.getMonth()) + 1;
								lrycDateTime = String.valueOf(a) + "年"
										+ String.valueOf(b) + "月"
										+ condate.getDate() + "日";
							}
							gsgsJyycJyycInfo.setIncludeDateTime(lrycDateTime);// 列入日期
							gsgsJyycJyycInfo.setRemoveCause(jyycxxJyycxx
									.getRemexcpres());// 移出经营异常名录原因
							String ycycDateTime = "";
							JilinDateTime remdate = jyycxxJyycxx.getRemdate();
							if (null != remdate) {
								int a = Integer.parseInt(remdate.getYear()) + 1900;
								int b = Integer.parseInt(remdate.getMonth()) + 1;
								ycycDateTime = String.valueOf(a) + "年"
										+ String.valueOf(b) + "月"
										+ remdate.getDate() + "日";
							}
							gsgsJyycJyycInfo.setRemoveDateTime(ycycDateTime);// 移出日期
							gsgsJyycJyycInfo.setAuthority(jyycxxJyycxx
									.getDecorg());// 作出决定机关
							gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
						}
						if (isDebug) {
							gsgsJyycInfo.setHtml(pre);
						}
						gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);// 经营异常信息列表
						gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);// 工商公示信息->经营异常信息
					}
				}
			}
		}

		// // 工商公示信息->经营异常信息
		// AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
		// List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new
		// ArrayList<AicpubOOperanomaInfo>();// 经营异常信息列表
		// Element yichangminglu = gsgsxx.getElementById("gsgsjyyclist");
		// if (null != yichangminglu) {
		// Elements yichangminglu_trs = yichangminglu.select("tr");
		// if (null != yichangminglu_trs && yichangminglu_trs.size() > 0) {
		// for (int i = 0; i < yichangminglu_trs.size(); i++) {
		// yichangminglu_trs.get(i).select("td");
		// AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();//
		// 经营异常信息
		// Elements span_one = yichangminglu_trs.get(i).select("td")
		// .get(1).getElementsByTag("span");
		// if (null != span_one && span_one.size() > 0) {
		// gsgsJyycJyycInfo.setIncludeCause(yichangminglu_trs
		// .get(i).select("td").get(1).select("span")
		// .attr("completedata"));// 列入经营异常名录原因
		// } else {
		// gsgsJyycJyycInfo.setIncludeCause(yichangminglu_trs
		// .get(i).select("td").get(1).text());// 列入经营异常名录原因
		// }
		// gsgsJyycJyycInfo.setIncludeDateTime(yichangminglu_trs
		// .get(i).select("td").get(2).text());// 列入日期
		// Elements span_three = yichangminglu_trs.get(i).select("td")
		// .get(3).getElementsByTag("span");
		// if (null != span_three && span_three.size() > 0) {
		// gsgsJyycJyycInfo.setRemoveCause(yichangminglu_trs
		// .get(i).select("td").get(3).select("span")
		// .attr("completedata"));// 移出经营异常名录原因
		// } else {
		// gsgsJyycJyycInfo.setRemoveCause(yichangminglu_trs
		// .get(i).select("td").get(3).text());// 移出经营异常名录原因
		// }
		// gsgsJyycJyycInfo.setRemoveDateTime(yichangminglu_trs.get(i)
		// .select("td").get(4).text());// 移出日期
		// gsgsJyycJyycInfo.setAuthority(yichangminglu_trs.get(i)
		// .select("td").get(5).text());// 作出决定机关
		// gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
		// }
		// }
		// if (isDebug) {
		// gsgsJyycInfo.setHtml(yichangminglu.html());
		// }
		// }
		// gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);// 经营异常信息列表
		// gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);// 工商公示信息->经营异常信息

		Element heimingdan_et = gsgsxx.getElementById("gsgsyzwflsit");
		if (null != heimingdan_et) {
			// 工商公示信息->严重违法信息
			AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
			Elements heimingdan_trs = heimingdan_et.select("tr");
			if (null != heimingdan_trs && heimingdan_trs.size() > 0) {
				// 工商公示信息->严重违法信息列表
				List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
				for (int i = 0; i < heimingdan_trs.size(); i++) {
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
				gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);// 严重违法信息
			}
			if (isDebug) {
				gsgsYzwfInfo.setHtml(heimingdan_et.html());
			}
			gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);// 工商公示信息->严重违法信息
		}

		if (null != htmlMap.get("gsgsxx_ccjcxx_ccjcxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_ccjcxx_ccjcxx")))) {
			// 工商公示信息->抽查检查信息
			AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
			Document gsgsxx_ccjcxx_ccjcxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_ccjcxx_ccjcxx").toString());
			Elements gsgsxx_ccjcxx_pres = gsgsxx_ccjcxx_ccjcxx.select("pre");
			if (null != gsgsxx_ccjcxx_pres && gsgsxx_ccjcxx_pres.size() > 0) {
				String wdd = gsgsxx_ccjcxx_pres.get(0).text();
				List<CcjcxxCcjcxx> ccjcxxList = gson.fromJson(wdd,
						new TypeToken<List<CcjcxxCcjcxx>>() {
						}.getType());
				if (null != ccjcxxList && ccjcxxList.size() > 0) {
					// 工商公示信息->抽查检查信息列表
					List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
					for (CcjcxxCcjcxx ccjcxx : ccjcxxList) {
						AicpubCCheckInfo gsgsCcjcCcjcInfo = new AicpubCCheckInfo();
						gsgsCcjcCcjcInfo.setCheckImplAuthority(ccjcxx
								.getInsauth());// 检查实施机关
						gsgsCcjcCcjcInfo.setType(ccjcxx.getInstype());// 类型
						String ccjcDateTime = "";
						JilinDateTime condate = ccjcxx.getInsdate();
						if (null != condate) {
							int a = Integer.parseInt(condate.getYear()) + 1900;
							int b = Integer.parseInt(condate.getMonth()) + 1;
							ccjcDateTime = String.valueOf(a) + "年"
									+ String.valueOf(b) + "月"
									+ condate.getDate() + "日";
						}
						gsgsCcjcCcjcInfo.setDateTime(ccjcDateTime);// 日期
						gsgsCcjcCcjcInfo.setResult(ccjcxx.getInsres());// 结果
						gsgsCcjcCcjcInfos.add(gsgsCcjcCcjcInfo);
					}
					gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);// 抽查检查信息
				}
				if (isDebug) {
					gsgsCcjcInfo.setHtml(gsgsxx_ccjcxx_pres.html());
				}
			}
			gsgsInfo.setCheckInfo(gsgsCcjcInfo);// 工商公示信息->抽查检查信息
		}
		// Element chouchaxinxi_et = gsgsxx.getElementById("gsgsccjclist");
		// if (null != chouchaxinxi_et) {
		// // 工商公示信息->抽查检查信息
		// AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		// Elements chouchaxinxi_trs = chouchaxinxi_et.select("tr");
		// if (null != chouchaxinxi_trs && chouchaxinxi_trs.size() > 1) {
		// // 工商公示信息->抽查检查信息列表
		// List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new
		// ArrayList<AicpubCCheckInfo>();
		// for (int i = 0; i < chouchaxinxi_trs.size() - 1; i++) {
		// AicpubCCheckInfo gsgsCcjcCcjcInfo = new AicpubCCheckInfo();
		// gsgsCcjcCcjcInfo.setCheckImplAuthority(chouchaxinxi_trs
		// .get(i).select("td").get(1).text());// 检查实施机关
		// gsgsCcjcCcjcInfo.setType(chouchaxinxi_trs.get(i)
		// .select("td").get(2).text());// 类型
		// gsgsCcjcCcjcInfo.setDateTime(chouchaxinxi_trs.get(i)
		// .select("td").get(3).text());// 日期
		// gsgsCcjcCcjcInfo.setResult(chouchaxinxi_trs.get(i)
		// .select("td").get(4).text());// 结果
		// gsgsCcjcCcjcInfos.add(gsgsCcjcCcjcInfo);
		// }
		// gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);// 抽查检查信息
		// }
		// if (isDebug) {
		// gsgsCcjcInfo.setHtml(chouchaxinxi_et.html());
		// }
		// gsgsInfo.setCheckInfo(gsgsCcjcInfo);// 工商公示信息->抽查检查信息
		// }
		gsxtFeedJson.setAicPubInfo(gsgsInfo);// 工商公示信息

		// 企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();

		Document qygsxx_list = Jsoup.parseBodyFragment(htmlMap.get(
				"qygsxx_list").toString());

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> qygsxx_qynb_infos = (List<Map<String, Object>>) htmlMap
				.get("qygsxx_qynb_infos");
		if (null != qygsxx_qynb_infos && qygsxx_qynb_infos.size() > 0) {
			// 企业公示信息->企业年报
			List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
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
				// 企业公示信息->企业年报->企业基本信息
				EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
				// 企业公示信息->企业年报->股东及出资信息
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();// 股东及出资信息
				// 企业公示信息->企业年报->修改记录
				List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
				// 企业公示信息->企业年报->企业资产状况信息
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
											.equals("企业是否有投资信息")
									|| qygsxx_qynb_info_page_th.text().trim()
											.equals("企业是否购买其他公司股权") || qygsxx_qynb_info_page_th
									.text().trim().contains("企业是否有对外投资设立企业信息"))) {
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
					if ((null != qygsxx_qynb_info_page_th.nextElementSibling())
							&& (qygsxx_qynb_info_page_th.text().trim()
									.equals("有限责任公司本年度是否发生股东股权转让") || qygsxx_qynb_info_page_th
									.text().trim().contains("有限责任公司本年度是否发"))) {
						qygsQynbJbInfo
								.setIsStohrEquTransferred(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 有限责任公司本年度是否发生股东股权转让
					}
					if ((null != qygsxx_qynb_info_page_th.nextElementSibling())
							&& (qygsxx_qynb_info_page_th.text().trim()
									.contains("隶属关系"))) {
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
					if ((null != qygsxx_qynb_info_page_th.nextElementSibling())
							&& (qygsxx_qynb_info_page_th.text().trim()
									.equals("营业总收入") || qygsxx_qynb_info_page_th
									.text().contains("销售总额"))) {
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
					if ((null != qygsxx_qynb_info_page_th.nextElementSibling())
							&& (qygsxx_qynb_info_page_th.text().trim()
									.equals("营业总收入中主营业务收入")
									|| qygsxx_qynb_info_page_th.text()
											.contains("销售总额中主营业务收入") || qygsxx_qynb_info_page_th
									.text().contains("主营业务收入"))) {
						qygsQynbQyzczkInfo
								.setPriBusiIncomeInSalesAmount(qygsxx_qynb_info_page_th
										.nextElementSibling().text());// 营业总收入中主营业务收入
					}
					if ((null != qygsxx_qynb_info_page_th.nextElementSibling())
							&& (qygsxx_qynb_info_page_th.text().contains("净利润"))) {
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
				Element czxxtable = qygsxx_qynb_info_page
						.getElementById("czxxtable");
				if (null != czxxtable) {
					Elements czxxtable_trs = czxxtable.select("tr");
					if (null != czxxtable_trs && czxxtable_trs.size() > 0) {
						for (int i = 0; i < czxxtable_trs.size(); i++) {
							// 企业公示信息->企业年报->股东及出资信息
							EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo = new EntpubAnnreportStohrinvestInfo();// 股东及出资信息
							qygsQynbGdjczInfo.setStockholder(czxxtable_trs
									.get(i).select("td").get(0).text());// 股东（发起人）
							qygsQynbGdjczInfo.setSubAmount(czxxtable_trs.get(i)
									.select("td").get(1).text());// 认缴出资额（万元）
							qygsQynbGdjczInfo.setSubDateTime(czxxtable_trs
									.get(i).select("td").get(2).text());// 认缴出资时间
							qygsQynbGdjczInfo.setSubMethod(czxxtable_trs.get(i)
									.select("td").get(3).text());// 认缴出资方式
							qygsQynbGdjczInfo.setPaidAmount(czxxtable_trs
									.get(i).select("td").get(4).text());// 实缴出资额（万元）
							qygsQynbGdjczInfo.setPaidDateTime(czxxtable_trs
									.get(i).select("td").get(5).text());// 实缴出资时间
							qygsQynbGdjczInfo.setPaidMethod(czxxtable_trs
									.get(i).select("td").get(6).text());// 实缴出资方式
							qygsQynbGdjczInfos.add(qygsQynbGdjczInfo);
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
				if (isDebug) {
					qygsQynbInfo.setHtml(qygsxx_qynb_info_page.select("div")
							.html());
				}
				qygsQynbInfos.add(qygsQynbInfo);// 企业公示信息->企业年报
			}
			qygsInfo.setAnnReports(qygsQynbInfos);// 企业公示信息->企业年报
		}

		// 企业公示信息->股东及出资信息
		EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
		if (null != htmlMap.get("qygsxx_gdjczxx_gdjczxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("qygsxx_gdjczxx_gdjczxx")))) {
			Document qygsxx_gdjczxx_gdjczxx = Jsoup.parseBodyFragment(htmlMap
					.get("qygsxx_gdjczxx_gdjczxx").toString());
			Elements qygsxx_gdjczxx_pres = qygsxx_gdjczxx_gdjczxx
					.getElementsByTag("pre");
			// TODO
			// 由于所取的数据表本不全，还需要更多验证
			if (null != qygsxx_gdjczxx_pres && !qygsxx_gdjczxx_pres.isEmpty()
					&& qygsxx_gdjczxx_pres.size() > 0) {
				String qygsxx_gdjczxx_str = qygsxx_gdjczxx_pres.get(0).text();
				List<GdjczGdjcz> GdjczGdjczList = null;
				if (null != qygsxx_gdjczxx_str
						&& !StringUtils.isEmpty(qygsxx_gdjczxx_str)) {
					GdjczGdjczList = gson.fromJson(qygsxx_gdjczxx_str,
							new TypeToken<List<GdjczGdjcz>>() {
							}.getType());
				}
				if (null != GdjczGdjczList && GdjczGdjczList.size() > 0) {
					// 企业公示信息->股东及出资信息
					List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
					for (GdjczGdjcz gdjczGdjcz : GdjczGdjczList) {
						// 企业公示信息->股东及出资信息
						EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new EntpubSStohrinvestInfo();
						qygsGdjczGdjczInfo.setStockholder(gdjczGdjcz.getCzxx()
								.getInv());// 股东
						qygsGdjczGdjczInfo.setSubAmount(gdjczGdjcz.getCzxx()
								.getLisubconam());// 认缴额（万元）
						qygsGdjczGdjczInfo.setPaidAmount(gdjczGdjcz.getCzxx()
								.getLiacconam());// 实缴额（万元）
						List<Rjmx> rjxxList = gdjczGdjcz.getRjxx();
						if (null != rjxxList && rjxxList.size() > 0) {
							List<EntpubSStohrinvestInfo.Detail> rjDetailList = new ArrayList<EntpubSStohrinvestInfo.Detail>();
							for (Rjmx rjmx : rjxxList) {
								EntpubSStohrinvestInfo.Detail rjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
								rjDetail.amount = rjmx.getSubconam();// 出资额（万元）
								String conform = rjmx.getConform();
								if (null != conform) {
									if (conform.contains("1")) {
										rjDetail.method = "货币";// 出资方式
									} else if (conform.contains("2")) {
										rjDetail.method = "实物";// 出资方式
									} else if (conform.contains("3")) {
										rjDetail.method = "知识产权";// 出资方式
									} else if (conform.contains("9")) {
										rjDetail.method = "其他";// 出资方式
									}
								}
								String rjDateTime = "";
								JilinDateTime condate = rjmx.getCondate();
								if (null != condate) {
									int a = Integer.parseInt(condate.getYear()) + 1900;
									int b = Integer
											.parseInt(condate.getMonth()) + 1;
									rjDateTime = String.valueOf(a) + "年"
											+ String.valueOf(b) + "月"
											+ condate.getDate() + "日";
								}
								rjDetail.dateTime = rjDateTime;// 出资日期
								rjDetailList.add(rjDetail);
							}
							qygsGdjczGdjczInfo.setSubDetails(rjDetailList);
						}
						List<Sjmx> sjxxList = gdjczGdjcz.getSjxx();
						if (null != sjxxList && sjxxList.size() > 0) {
							List<EntpubSStohrinvestInfo.Detail> sjDetailList = new ArrayList<EntpubSStohrinvestInfo.Detail>();
							for (Sjmx sjxx : sjxxList) {
								EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
								sjDetail.amount = sjxx.getAcconam();// 出资额（万元）
								String conform = sjxx.getConform();
								if (null != conform) {
									if (conform.contains("1")) {
										sjDetail.method = "货币";// 出资方式
									} else if (conform.contains("2")) {
										sjDetail.method = "实物";// 出资方式
									} else if (conform.contains("3")) {
										sjDetail.method = "知识产权";// 出资方式
									} else if (conform.contains("9")) {
										sjDetail.method = "其他";// 出资方式
									}
								}
								String sjDateTime = "";
								JilinDateTime condate = sjxx.getCondate();
								if (null != condate) {
									int a = Integer.parseInt(condate.getYear()) + 1900;
									int b = Integer
											.parseInt(condate.getMonth()) + 1;
									sjDateTime = String.valueOf(a) + "年"
											+ String.valueOf(b) + "月"
											+ condate.getDate() + "日";
								}
								sjDetail.dateTime = sjDateTime;// 出资日期
								sjDetailList.add(sjDetail);
							}
							qygsGdjczGdjczInfo.setPaidDetails(sjDetailList);
						}
						qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
					}
					qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息
				}
			}
		}

		// // 企业公示信息->股东及出资信息
		// EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
		// if (null != qygsxx_list.getElementById("jsxxczxxlist")) {
		// Elements gdjczxx_trs = qygsxx_list.getElementById("jsxxczxxlist")
		// .select("tr");
		// if (null != gdjczxx_trs && gdjczxx_trs.size() > 0) {
		// // 企业公示信息->股东及出资信息
		// List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new
		// ArrayList<EntpubSStohrinvestInfo>();
		// for (int j = 0; j < gdjczxx_trs.size(); j++) {
		// int wd = gdjczxx_trs.get(j).select("td").size();
		// // 企业公示信息->股东及出资信息
		// EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new
		// EntpubSStohrinvestInfo();
		// qygsGdjczGdjczInfo.setStockholder(gdjczxx_trs.get(j)
		// .select("td").get(0).text());// 股东
		// qygsGdjczGdjczInfo.setSubAmount(gdjczxx_trs.get(j)
		// .select("td").get(1).text());// 认缴额（万元）
		// qygsGdjczGdjczInfo.setPaidAmount(gdjczxx_trs.get(j)
		// .select("td").get(2).text());// 实缴额（万元）
		// EntpubSStohrinvestInfo.Detail rjDetail = qygsGdjczGdjczInfo.new
		// Detail();// 认缴明细
		// rjDetail.amount = gdjczxx_trs.get(j).select("td").get(3)
		// .text();// 出资额（万元）
		// rjDetail.method = gdjczxx_trs.get(j).select("td").get(4)
		// .text();// 出资方式
		// rjDetail.dateTime = gdjczxx_trs.get(j).select("td").get(5)
		// .text();// 出资日期
		// if (wd > 8) {
		// EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new
		// Detail();// 实缴明细
		// sjDetail.amount = gdjczxx_trs.get(j).select("td")
		// .get(6).text();// 出资额（万元）
		// sjDetail.method = gdjczxx_trs.get(j).select("td")
		// .get(7).text();// 出资方式
		// sjDetail.amount = gdjczxx_trs.get(j).select("td")
		// .get(8).text();// 出资日期
		// }
		// qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
		// }
		// qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);//
		// 企业公示信息->股东及出资信息
		// }
		// }

		if (null != htmlMap.get("qygsxx_gdjczxx_bgxx")
				&& !StringUtils.isEmpty(htmlMap.get("qygsxx_gdjczxx_bgxx")
						.toString())) {
			Document qygsxx_gdjczxx_bgxx = Jsoup.parseBodyFragment(htmlMap.get(
					"qygsxx_gdjczxx_bgxx").toString());
			Elements pres = qygsxx_gdjczxx_bgxx.getElementsByTag("pre");
			if (null != pres && !pres.isEmpty() && pres.size() > 0) {
				String preHtml = pres.get(0).text();
				if (null != preHtml) {
					List<GdjczxxBgxxList> gdjczxxBgxxList = gson.fromJson(
							preHtml, new TypeToken<List<GdjczxxBgxxList>>() {
							}.getType());
					if (null != gdjczxxBgxxList && gdjczxxBgxxList.size() > 0) {
						GdjczxxBgxxList gdjczxxBgxxList2 = gdjczxxBgxxList
								.get(0);
						// 企业公示信息->股东及出资信息->变更信息
						List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
						for (GdjczxxBgxx gdjczxxBgxx : gdjczxxBgxxList2
								.getBgxx()) {
							EntpubStohrinvestChangeInfo gygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
							gygsGdjczBgInfo.setItem(gdjczxxBgxx.getAltitem());// 变更事项
							String bgTime = "";
							JilinDateTime condate = gdjczxxBgxx.getAltdate();
							if (null != condate) {
								int a = Integer.parseInt(condate.getYear()) + 1900;
								int b = Integer.parseInt(condate.getMonth()) + 1;
								bgTime = String.valueOf(a) + "年"
										+ String.valueOf(b) + "月"
										+ condate.getDate() + "日";
							}
							gygsGdjczBgInfo.setDateTime(bgTime);// 变更时间
							gygsGdjczBgInfo.setPreContent(gdjczxxBgxx
									.getAltbe());// 变更前
							gygsGdjczBgInfo.setPostContent(gdjczxxBgxx
									.getAltaf());// 变更后
							qygsGdjczBgInfos.add(gygsGdjczBgInfo);
						}
						qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);// 变更信息
					}
				}
			}
		}
		// Element investor = qygsxx_list.getElementById("jsxxczxxbglist");
		// if (null != investor) {
		// Elements wc_trs = investor.select("tr");
		// if (null != wc_trs && wc_trs.size() > 1) {
		// // 企业公示信息->股东及出资信息->变更信息
		// List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new
		// ArrayList<EntpubStohrinvestChangeInfo>();
		// for (int i = 0; i < wc_trs.size() - 1; i++) {
		// EntpubStohrinvestChangeInfo gygsGdjczBgInfo = new
		// EntpubStohrinvestChangeInfo();
		// gygsGdjczBgInfo.setItem(wc_trs.get(i).select("td").get(1)
		// .text());// 变更事项
		// gygsGdjczBgInfo.setDateTime(wc_trs.get(i).select("td")
		// .get(2).text());// 变更时间
		// gygsGdjczBgInfo.setPreContent(wc_trs.get(i).select("td")
		// .get(3).text());// 变更前
		// gygsGdjczBgInfo.setPostContent(wc_trs.get(i).select("td")
		// .get(4).text());// 变更后
		// qygsGdjczBgInfos.add(gygsGdjczBgInfo);
		// }
		// qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);// 变更信息
		// }
		// }
		qygsInfo.setStohrInvestInfo(qygsGdjczInfo);// 企业公示信息->股东及出资信息

		// 企业公示信息->股权变更信息
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		Element gudongguquan_et = qygsxx_list.getElementById("jsxxGqbgList");
		if (null != gudongguquan_et) {
			Elements gudongguquan_trs = gudongguquan_et.select("tr");
			if (null != gudongguquan_trs && gudongguquan_trs.size() > 0) {
				// 企业公示信息->股权变更信息列表
				List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
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
				qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);// 企业公示信息->股权变更信息
			}
			if (isDebug) {
				qygsGqbgInfo.setHtml(gudongguquan_et.html());
			}
		}
		qygsInfo.setEquChangeInfo(qygsGqbgInfo);// 企业公示信息->股权变更信息

		if (null != htmlMap.get("qygsxx_xzxkxx_xzxkxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("qygsxx_xzxkxx_xzxkxx")))) {
			Document qygsxx_xzxkxx_xzxkxx = Jsoup.parseBodyFragment(htmlMap
					.get("qygsxx_xzxkxx_xzxkxx").toString());
			Elements qygsxx_xzxkxx_pres = qygsxx_xzxkxx_xzxkxx
					.getElementsByTag("pre");
			if (null != qygsxx_xzxkxx_pres && qygsxx_xzxkxx_pres.size() > 0) {
				String qygsxx_xzxkxx_str = qygsxx_xzxkxx_pres.get(0).text()
						.trim();
				List<Xzxkxx> xzxkxxList = gson.fromJson(qygsxx_xzxkxx_str,
						new TypeToken<List<Xzxkxx>>() {
						}.getType());
				if (null != xzxkxxList && xzxkxxList.size() > 0) {
					// 企业公示信息->行政许可信息
					EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
					// 企业公示信息->行政许可信息列表
					List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
					for (Xzxkxx xzxkxx : xzxkxxList) {
						// 企业公示信息->行政许可信息列表
						EntpubAAdmlicInfo qygsXzxkXzxkInfo = new EntpubAAdmlicInfo();
						qygsXzxkXzxkInfo.setLicenceNum(xzxkxx.getLicno());// 许可文件编号
						qygsXzxkXzxkInfo.setLicenceName(xzxkxx.getLicname());// 许可文件名称
						String startDateTime = "";
						JilinDateTime condate = xzxkxx.getValfrom();
						if (null != condate) {
							int a = Integer.parseInt(condate.getYear()) + 1900;
							int b = Integer.parseInt(condate.getMonth()) + 1;
							startDateTime = String.valueOf(a) + "年"
									+ String.valueOf(b) + "月"
									+ condate.getDate() + "日";
						}
						qygsXzxkXzxkInfo.setStartDateTime(startDateTime);// 有效期自
						String endDateTime = "";
						JilinDateTime condate1 = xzxkxx.getValto();
						if (null != condate1) {
							int a = Integer.parseInt(condate1.getYear()) + 1900;
							int b = Integer.parseInt(condate1.getMonth()) + 1;
							endDateTime = String.valueOf(a) + "年"
									+ String.valueOf(b) + "月"
									+ condate1.getDate() + "日";
						}
						qygsXzxkXzxkInfo.setEndDateTime(endDateTime);// 有效期至
						qygsXzxkXzxkInfo.setDeciAuthority(xzxkxx.getLicanth());// 许可机关
						qygsXzxkXzxkInfo.setContent(xzxkxx.getLicitem());// 许可内容
						if (null != xzxkxx.getType()
								&& xzxkxx.getType().trim().equals("1")) {
							qygsXzxkXzxkInfo.setStatus("有效");// 状态
						} else {
							qygsXzxkXzxkInfo.setStatus("无效");// 状态
						}
						// qygsXzxkXzxkInfo.setDetail("");// 详情
						qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
					}
					qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
					qygsInfo.setAdmLicInfo(qygsXzxkInfo);// 企业公示信息->行政许可信息
				}
			}
		}

		// // 企业公示信息->行政许可信息
		// EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
		// Element xingzhengxuke_et =
		// qygsxx_list.getElementById("jsxxxkxxlist");
		// if (null != xingzhengxuke_et) {
		// Elements xingzhengxuke_trs = xingzhengxuke_et.select("tr");
		// if (null != xingzhengxuke_trs && xingzhengxuke_trs.size() > 1) {
		// // 企业公示信息->行政许可信息列表
		// List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new
		// ArrayList<EntpubAAdmlicInfo>();
		// for (int i = 0; i < xingzhengxuke_trs.size() - 1; i++) {
		// // 企业公示信息->行政许可信息列表
		// EntpubAAdmlicInfo qygsXzxkXzxkInfo = new EntpubAAdmlicInfo();
		// qygsXzxkXzxkInfo.setLicenceNum(xingzhengxuke_trs.get(i)
		// .select("td").get(1).text());// 许可文件编号
		// qygsXzxkXzxkInfo.setLicenceName(xingzhengxuke_trs.get(i)
		// .select("td").get(2).text());// 许可文件名称
		// qygsXzxkXzxkInfo.setStartDateTime(xingzhengxuke_trs.get(i)
		// .select("td").get(3).text());// 有效期自
		// qygsXzxkXzxkInfo.setEndDateTime(xingzhengxuke_trs.get(i)
		// .select("td").get(4).text());// 有效期至
		// qygsXzxkXzxkInfo.setDeciAuthority(xingzhengxuke_trs.get(i)
		// .select("td").get(5).text());// 许可机关
		// qygsXzxkXzxkInfo.setContent(xingzhengxuke_trs.get(i)
		// .select("td").get(6).text());// 许可内容
		// qygsXzxkXzxkInfo.setStatus(xingzhengxuke_trs.get(i)
		// .select("td").get(7).text());// 状态
		// qygsXzxkXzxkInfo.setDetail(xingzhengxuke_trs.get(i)
		// .select("td").get(8).text());// 详情
		// qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
		// }
		// qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
		// }
		// if (isDebug) {
		// qygsXzxkInfo.setHtml(xingzhengxuke_et.html());
		// }
		// }
		// qygsInfo.setAdmLicInfo(qygsXzxkInfo);// 企业公示信息->行政许可信息

		Element zhishichanquan_et = qygsxx_list
				.getElementById("jsxxzscqczlist");
		if (null != zhishichanquan_et) {
			// 企业公示信息->知识产权出质登记信息
			EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
			Elements zhishichanquan_trs = zhishichanquan_et.select("tr");
			if (null != zhishichanquan_trs && zhishichanquan_trs.size() > 0) {
				// 企业公示信息->知识产权出质登记信息列表
				List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
				for (int i = 0; i < zhishichanquan_trs.size(); i++) {
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
					qygsZscqczdjInfo.setHtml(zhishichanquan_et.html());
				}
				qygsZscqczdjInfo
						.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);// 企业公示信息->知识产权出质登记信息
			}
			qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);// 企业公示信息->知识产权出质登记信息
		}

		Element xingzhengchufa_et1 = qygsxx_list.getElementById("jsxxxzcflist");
		if (null != xingzhengchufa_et1) {
			// 企业公示信息->行政处罚信息
			EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
			Elements xingzhengchufa_trs = xingzhengchufa_et1.select("tr");
			if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 0) {
				// 企业公示信息->行政处罚信息列表
				List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
				for (int j = 0; j < xingzhengchufa_trs.size(); j++) {
					// 企业公示信息->行政处罚信息
					EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();
					qygsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs.get(j)
							.select("td").get(1).text());// 行政处罚决定书文号
					// qygsXzcfXzcfInfo.setIllegalActType("");// 违法行为类型
					qygsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs.get(j)
							.select("td").get(2).text());// 行政处罚内容
					qygsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs.get(j)
							.select("td").get(3).text());// 作出行政处罚决定机关名称
					qygsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs.get(j)
							.select("td").get(4).text());// 作出行政处罚决定日期
					qygsXzcfXzcfInfo.setNote(xingzhengchufa_trs.get(j)
							.select("td").get(5).text());// 备注
					qygsXzcfXzcfInfos.add(qygsXzcfXzcfInfo);
				}
				qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);// 企业公示信息->行政处罚信息
			}
			if (isDebug) {
				qygsXzcfInfo.setHtml(xingzhengchufa_et1.html());
			}
			qygsInfo.setAdmPunishInfo(qygsXzcfInfo);// 企业公示信息->行政处罚信息
		}

		// 企业公示信息
		gsxtFeedJson.setEntPubInfo(qygsInfo);

		// 其他部门公示信息
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();
		if (null != htmlMap.get("qtbmgsxx")
				&& !StringUtils
						.isEmpty(String.valueOf(htmlMap.get("qtbmgsxx")))) {
			Document qtbmgsxx_page = Jsoup.parseBodyFragment(htmlMap.get(
					"qtbmgsxx").toString());
			Element xingzhengxuke_et1 = qtbmgsxx_page
					.getElementById("xingzhengxuke");
			if (null != xingzhengxuke_et1) {
				// 其他部门公示信息->行政许可信息
				OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
				Elements xingzhengxuke1_trs = xingzhengxuke_et1.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengxuke1_trs && xingzhengxuke1_trs.size() > 2) {
					// 其他部门公示信息->行政许可信息列表
					List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
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
						qtbmgsXzxkXzxkInfo.setDeciAuthority(xingzhengxuke1_trs
								.get(i).select("td").get(5).text());// 许可机关
						qtbmgsXzxkXzxkInfo.setContent(xingzhengxuke1_trs.get(i)
								.select("td").get(6).text());// 许可内容
						qtbmgsXzxkXzxkInfo.setStatus(xingzhengxuke1_trs.get(i)
								.select("td").get(7).text());// 状态
						qtbmgsXzxkXzxkInfo.setDetail(xingzhengxuke1_trs.get(i)
								.select("td").get(8).text());// 详情
						qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
					}
					qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);// 其他部门公示信息->行政许可信息列表
				}
				if (isDebug && null != xingzhengxuke_et1) {
					qtbmgsXzxkInfo.setHtml(xingzhengxuke_et1.html());
				}
				qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);// 其他部门公示信息->行政许可信息
			}

			Element xingzhengchufa_et = qtbmgsxx_page
					.getElementById("xingzhengchufa");
			if (null != xingzhengchufa_et) {
				// 其他部门公示信息->行政处罚信息
				OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
				Elements xingzhengchufa_trs = xingzhengchufa_et.select("tbody")
						.get(0).select("tr");
				if (null != xingzhengchufa_trs && xingzhengchufa_trs.size() > 2) {
					// 其他部门公示信息->行政处罚信息列表
					List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();
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
					qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);// 其他部门公示信息->行政处罚信息列表
				}
				if (isDebug) {
					qtbmgsXzcfInfo.setHtml(xingzhengchufa_et.html());
				}
				qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);// 其他部门公示信息->行政处罚信息
			}
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

			Element guquandongjie_et = sfxzgsxx_list_page
					.getElementById("sfxxtable");
			if (null != guquandongjie_et) {
				// 司法协助公示信息->股权冻结信息
				JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
				Elements guquandongjie_trs = guquandongjie_et.select("tbody")
						.get(0).select("tr");
				if (null != guquandongjie_trs && guquandongjie_trs.size() > 0) {
					// 司法协助公示信息->股权冻结信息列表
					List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
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
					sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);// 司法协助公示信息->股权冻结信息列表
				}
				if (isDebug) {
					sfxzgsGqdjInfo.setHtml(guquandongjie_et.html());
				}
				sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);// 司法协助公示信息->股权冻结信息
			}

			Element guquanbiangeng_et = sfxzgsxx_list_page
					.getElementById("sifagudong");
			if (null != guquanbiangeng_et) {
				// 司法协助公示信息->股东变更信息
				JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
				Elements guquanbiangeng_trs = guquanbiangeng_et.select("tbody")
						.get(1).select("tr");
				if (null != guquanbiangeng_trs && guquanbiangeng_trs.size() > 0) {
					// 司法协助公示信息->股东变更信息列表
					List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
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
					sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);// 司法协助公示信息->股东变更信息列表
				}
				if (isDebug) {
					sfxzgsGdbgInfo.setHtml(guquanbiangeng_et.html());
				}
				sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);// 司法协助公示信息->股东变更信息列表
			}

			// 司法协助公示信息
			gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);

		}
		return gsxtFeedJson;

	}
}

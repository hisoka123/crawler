package com.crawler.gsxt.htmlparser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.crawler.gsxt.domain.json.AicpubAAdmpunishInfo.PunishDetail;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.crawler.gsxt.domain.json.AicpubSSerillegalInfo;
import com.crawler.gsxt.domain.json.AicpubSerillegalInfo;
import com.crawler.gsxt.domain.json.EntpubAAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAssetInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportBaseInfo;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtFujianParser extends AbstractGsxtParser {

	private static final Log LOOGER = LogFactory.getLog(GsxtHebeiParser.class);

	public AicFeedJson getFujianResultObj(String html, boolean isDebug) {

		LOOGER.info("The method of GsxtFujianParser.getFujianResultObj is begin !");

		Gson gson = new GsonBuilder().create();
		Type htmlMapType = new TypeToken<Map<String, Object>>() {}.getType();
		Map<String, Object> htmlMap = gson.fromJson(html, htmlMapType);

		if (null == htmlMap.get("gsgsxx")|| null == String.valueOf(htmlMap.get("gsgsxx"))|| StringUtils.isEmpty(String.valueOf(htmlMap.get("gsgsxx")))) {
			return null;
		}
		// 工商系统bean
		AicFeedJson gsxtFeedJson = new AicFeedJson();
		// 工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();
		Document gsgsxx = Jsoup.parseBodyFragment(htmlMap.get("gsgsxx").toString());

		// 工商公示信息->登记信息
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();

		// 工商公示信息->登记信息->基本信息
		Elements baseinfo = gsgsxx.getElementsByAttributeValue("rel", "layout-01_01").get(0).select("tbody");
		Elements ths = baseinfo.select("tr").select("th");
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
		for (int i = 1; i < ths.size(); i++) {
			if (ths.get(i).text().trim().contains("统一社会信用代码")|| ths.get(i).text().trim().contains("注册号")) {
				gsgsDjJbInfo.setNum(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 注册号或信用代码
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("名称")) {
				gsgsDjJbInfo.setName(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 名称
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("类型")) {
				gsgsDjJbInfo.setType(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 类型
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("负责人")|| ths.get(i).text().trim().equalsIgnoreCase("法定代表人")|| ths.get(i).text().trim().contains("经营者")) {
				gsgsDjJbInfo.setLegalRepr(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 法定代表人/经营者
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("注册资本")) {
				gsgsDjJbInfo.setRegCapital(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 注册资本
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("成立日期")) {
				gsgsDjJbInfo.setRegDateTime(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 成立日期
			}
			if (ths.get(i).text().trim().contains("营业场所")|| ths.get(i).text().trim().contains("住所")) {
				gsgsDjJbInfo.setAddress(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 经营场所/住所
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("营业期限自")) {
				gsgsDjJbInfo.setStartDateTime(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 营业期限自（即营业开始日期）
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("营业期限至")) {
				gsgsDjJbInfo.setEndDateTime(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 营业期限至（即营业结束日期）
			}
			if (ths.get(i).text().trim().contains("经营范围")) {
				gsgsDjJbInfo.setBusinessScope(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 经营范围
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("登记机关")) {
				gsgsDjJbInfo.setRegAuthority(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 登记机关
			}
			if (ths.get(i).text().trim().equalsIgnoreCase("核准日期")) {
				gsgsDjJbInfo.setApprovalDateTime(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 核准日期
			}
			if (ths.get(i).text().trim().contains("登记状态")) {
				gsgsDjJbInfo.setRegStatus(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 登记状态
			}
			if (ths.get(i).text().trim().contains("吊销日期")) {
				gsgsDjJbInfo.setRevokeDate(baseinfo.select("tbody").select("tr").select("td").get(i - 1).text());// 吊销日期
			}
		}
		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);// 基本信息
		// 工商公示信息->登记信息->股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
		Element elementById = gsgsxx.getElementById("investorTable");	
		if (null != elementById) {
			Element touziren_tbody = gsgsxx.getElementById("investorTable").select("tbody").get(0);
			Elements touziren_trs = touziren_tbody.select("tr");
			int touziren_nums = touziren_trs.size();
			if (touziren_nums > 2) {
				for (int i = 2; i < touziren_nums - 1; i++) {
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
		List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<AicpubRegChangeInfo>();
		Element biangeng = gsgsxx.getElementById("alterTable").select("tbody")
				.get(0);
		if (null != biangeng) {
			Elements biangeng_trs = biangeng.select("tr");
			int biangeng_trs_size = biangeng_trs.size();
			if (biangeng_trs_size > 2) {
				for (int i = 2; i < biangeng_trs_size - 1; i++) {
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
			int beian_zyryxx_size = beian_zyryxx_trs.size();
			if (beian_zyryxx_size > 2) {
				for (int i = 2; i < beian_zyryxx_size - 1; i++) {
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
			int beian_fzjgxx_size = beian_fzjgxx_trs.size();
			if (beian_fzjgxx_size > 2) {
				for (int i = 2; i < beian_fzjgxx_size - 1; i++) {
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
		// TODO
		// 工商公示信息->备案信息->清算信息
		AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
		Elements elementsByAttributeValue = gsgsxx.getElementsByAttributeValue("rel", "layout-01_02");
		if(elementsByAttributeValue.size()!=0){					
		Elements beian_qingsuanrenyuan = gsgsxx.getElementsByAttributeValue("rel", "layout-01_02").get(0).select("table");
		gsgsBaQsInfo.setLeader(null);// 清算组负责人
		gsgsBaQsInfo.setMembers(null);// 清算组成员
		if (isDebug && null != beian_qingsuanrenyuan) {
			gsgsBaQsInfo.setHtml(beian_qingsuanrenyuan.html());
		}
		}
		gsgsBaInfo.setClearInfo(gsgsBaQsInfo);// 清算信息
		gsgsInfo.setArchiveInfo(gsgsBaInfo);// 工商公示信息->备案信息

		// TODO
		// 工商公示信息->动产抵押登记信息
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		// 工商公示信息->动产抵押登记信息列表
		List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
		AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
		gsgsDcdydjDcdydjInfo.setRegNum("");// 登记编号
		gsgsDcdydjDcdydjInfo.setRegDateTime("");// 登记日期
		gsgsDcdydjDcdydjInfo.setRegAuthority("");// 登记机关
		gsgsDcdydjDcdydjInfo.setGuaranteedDebtAmount("");// 被担保债权数额
		gsgsDcdydjDcdydjInfo.setStatus("");// 状态
		gsgsDcdydjDcdydjInfo.setPubDateTime("");// 公示时间（该字段比较特殊，江苏工商网暂时没有该字段。）
		gsgsDcdydjDcdydjInfo.setDetail("");// 详情
		gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
		gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);// 动产抵押登记信息
		Element dongchandiya_et = gsgsxx.getElementById("mortageTable");
		if (isDebug && null != dongchandiya_et) {
			gsgsDcdydjInfo.setHtml(dongchandiya_et.html());
		}
		gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息

		// TODO
		// 工商公示信息->股权出资登记信息
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		// 工商公示信息->股权出资登记信息列表
		List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
		// 工商公示信息->股权出资登记信息
		AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
		gsgsGqczdjGqczdjInfo.setRegNum("");// 登记编号
		gsgsGqczdjGqczdjInfo.setMortgagorName("");// 出质人
		gsgsGqczdjGqczdjInfo.setMortgagorIdNum("");// 证照/证件号码（出质人）
		gsgsGqczdjGqczdjInfo.setMortgAmount("");// 出质股权数额
		gsgsGqczdjGqczdjInfo.setMortgageeName("");// 质权人
		gsgsGqczdjGqczdjInfo.setMortgageeIdNum("");// 证照/证件号码
		gsgsGqczdjGqczdjInfo.setRegDateTime("");// 股权出质设立登记日期
		gsgsGqczdjGqczdjInfo.setStatus("");// 状态
		gsgsGqczdjGqczdjInfo.setPubDate("");// 公示时间（该字段比较特殊，江苏工商网暂时没有该字段。）
		gsgsGqczdjGqczdjInfo.setChangeSitu("");// 变化情况
		gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
		gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);// 股权出资登记信息
		Element guquanchuzhi_et = gsgsxx.getElementById("pledgeTable");
		if (isDebug && null != guquanchuzhi_et) {
			gsgsGqczdjInfo.setHtml(guquanchuzhi_et.html());
		}
		gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);// 工商公示信息->股权出资登记信息

		// 工商公示信息->行政处罚信息
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
		// 工商公示信息->行政处罚信息列表
		List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
		Element xingzhengchufa = gsgsxx.getElementById("punishTable");
		if (null != xingzhengchufa) {
			Elements xingzhengchufa_trs = xingzhengchufa.select("tbody").get(0)
					.select("tr");
			int xingzhengchufa_trs_size = xingzhengchufa_trs.size();
			if (xingzhengchufa_trs_size > 3) {
				for (int i = 2; i < xingzhengchufa_trs_size - 1; i++) {
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
		Element yichangminglu = gsgsxx.getElementById("exceptTable");
		Elements yichangminglu_trs = yichangminglu.select("tbody").get(0)
				.select("tr");
		int yichangminglu_trs_size = yichangminglu_trs.size();
		if (yichangminglu_trs_size > 3) {
			for (int i = 2; i < yichangminglu_trs_size - 1; i++) {
				yichangminglu_trs.get(i).select("td");
				AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();// 经营异常信息
				Elements span_one = yichangminglu_trs.get(i).select("td")
						.get(1).getElementsByTag("span");
				if (null != span_one && span_one.size() > 0) {
					gsgsJyycJyycInfo.setIncludeCause(yichangminglu_trs.get(i)
							.select("td").get(1).select("span")
							.attr("completedata"));// 列入经营异常名录原因
				} else {
					gsgsJyycJyycInfo.setIncludeCause(yichangminglu_trs.get(i)
							.select("td").get(1).text());// 列入经营异常名录原因
				}
				gsgsJyycJyycInfo.setIncludeDateTime(yichangminglu_trs.get(i)
						.select("td").get(2).text());// 列入日期
				Elements span_three = yichangminglu_trs.get(i).select("td")
						.get(3).getElementsByTag("span");
				if (null != span_three && span_three.size() > 0) {
					gsgsJyycJyycInfo.setRemoveCause(yichangminglu_trs.get(i)
							.select("td").get(3).select("span")
							.attr("completedata"));// 移出经营异常名录原因
				} else {
					gsgsJyycJyycInfo.setRemoveCause(yichangminglu_trs.get(i)
							.select("td").get(3).text());// 移出经营异常名录原因
				}
				gsgsJyycJyycInfo.setRemoveDateTime(yichangminglu_trs.get(i)
						.select("td").get(4).text());// 移出日期
				gsgsJyycJyycInfo.setAuthority(yichangminglu_trs.get(i)
						.select("td").get(5).text());// 作出决定机关
				gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
			}
		}
		gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);// 经营异常信息列表
		if (isDebug && null != yichangminglu) {
			gsgsJyycInfo.setHtml(yichangminglu.html());
		}
		gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);// 工商公示信息->经营异常信息

		// TODO
		// 工商公示信息->严重违法信息
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		// 工商公示信息->严重违法信息列表
		List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
		// 严重违法信息
		AicpubSSerillegalInfo gsgsYzwfYzwfInfo = new AicpubSSerillegalInfo();
		gsgsYzwfYzwfInfo.setIncludeCause("");// 列入严重违法企业名单原因
		gsgsYzwfYzwfInfo.setIncludeDateTime("");// 列入日期
		gsgsYzwfYzwfInfo.setRemoveCause("");// 移出严重违法企业名单原因
		gsgsYzwfYzwfInfo.setRemoveDateTime("");// 移出日期
		gsgsYzwfYzwfInfo.setDeciAuthority("");// 作出决定机关
		gsgsYzwfYzwfInfos.add(gsgsYzwfYzwfInfo);
		gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);// 严重违法信息
		Element heimingdan_et = gsgsxx.getElementById("blackTable");
		if (isDebug && null != heimingdan_et) {
			gsgsYzwfInfo.setHtml(heimingdan_et.html());
		}
		gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);// 工商公示信息->严重违法信息

		// 工商公示信息->抽查检查信息
		AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		// 工商公示信息->抽查检查信息列表
		List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
		Elements chouchaxinxi_trs = gsgsxx.getElementById("spotcheckTable")
				.select("tbody").get(0).select("tr");
		int chouchaxinxi_trs_size = chouchaxinxi_trs.size();
		if (chouchaxinxi_trs_size > 3) {
			for (int i = 2; i < chouchaxinxi_trs_size - 1; i++) {
				AicpubCCheckInfo gsgsCcjcCcjcInfo = new AicpubCCheckInfo();
				gsgsCcjcCcjcInfo.setCheckImplAuthority(chouchaxinxi_trs.get(i)
						.select("td").get(1).text());// 检查实施机关
				gsgsCcjcCcjcInfo.setType(chouchaxinxi_trs.get(i).select("td")
						.get(2).text());// 类型
				gsgsCcjcCcjcInfo.setDateTime(chouchaxinxi_trs.get(i)
						.select("td").get(3).text());// 日期
				gsgsCcjcCcjcInfo.setResult(chouchaxinxi_trs.get(i).select("td")
						.get(4).text());// 结果
				gsgsCcjcCcjcInfos.add(gsgsCcjcCcjcInfo);
			}
		}
		gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);// 抽查检查信息
		if (isDebug && null != chouchaxinxi_trs) {
			gsgsCcjcInfo.setHtml(chouchaxinxi_trs.html());
		}
		gsgsInfo.setCheckInfo(gsgsCcjcInfo);// 工商公示信息->抽查检查信息

		gsxtFeedJson.setAicPubInfo(gsgsInfo);// 工商公示信息

		// 企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();

		Document qygsxx_list = Jsoup.parseBodyFragment(htmlMap.get("qygsxx_list").toString());

		// 企业公示信息->企业年报
		List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> qygsxx_qynb_infos = (List<Map<String, Object>>) htmlMap.get("qygsxx_qynb_infos");
		if (null != qygsxx_qynb_infos && qygsxx_qynb_infos.size() > 0) {
			for (Map<String, Object> qygsxx_qynb_info : qygsxx_qynb_infos) {
				Document qygsxx_qynb_info_page = Jsoup.parseBodyFragment(qygsxx_qynb_info.get("qygsxx_qynb_info_page").toString());
				EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();// 企业年报
				qygsQynbInfo.setSubmitYear(qygsxx_qynb_info.get("qygsxx_qynb_list_a_text").toString());// 报送年度
				qygsQynbInfo.setPubDateTime(qygsxx_qynb_info.get("qygsxx_qynb_list_pubdate").toString());// 发布日期
				// 企业公示信息->企业年报->企业基本信息
				EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
				// 企业公示信息->企业年报->股东及出资信息
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();// 股东及出资信息
				List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
				// 企业资产状况信息
				EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
				Elements qygsxx_qynb_info_page_ths = qygsxx_qynb_info_page.select("th");
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
				// TODO
				// // 对外提供保证担保信息
				// List<QygsQynbDwtgbzdbInfo> qygsQynbDwtgbzdbInfos = new
				// ArrayList<QygsQynbDwtgbzdbInfo>();
				// QygsQynbDwtgbzdbInfo qygsQynbDwtgbzdbInfo = new
				// QygsQynbDwtgbzdbInfo();
				// String qygsQynbDwtgbzdbInfoHtml = "页面中没有该项信息！";
				// if (null != qyjbxx_qyzczkxx.getElementById("duiwaidanbao")
				// && qyjbxx_qyzczkxx.getElementById("duiwaidanbao")
				// .hasText()) {
				// qygsQynbDwtgbzdbInfoHtml = qyjbxx_qyzczkxx.getElementById(
				// "duiwaidanbao").html();
				// }
				// qygsQynbDwtgbzdbInfo.setHtml(qygsQynbDwtgbzdbInfoHtml);
				// qygsQynbInfo.setQygsQynbDwtgbzdbInfos(qygsQynbDwtgbzdbInfos);//
				// 对外提供保证担保信息
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
				for (int j = 3; j < gdjczxx_trs_size; j += 2) {
					// 企业公示信息->股东及出资信息
					EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new EntpubSStohrinvestInfo();
					qygsGdjczGdjczInfo.setStockholder(gdjczxx_trs.get(j)
							.select("td").get(0).text());// 股东
					qygsGdjczGdjczInfo.setSubAmount(gdjczxx_trs.get(j)
							.select("td").get(2).text());// 认缴额（万元）
					qygsGdjczGdjczInfo.setPaidAmount(gdjczxx_trs.get(j)
							.select("td").get(3).text());// 实缴额（万元）
					// TODO
					// Detail rjDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细
					// rjDetail.amount = gdjczxx_trs.get(j).select("td").get(4)
					// .text();// 出资额（万元）
					// rjDetail.method = gdjczxx_trs.get(j).select("td").get(5)
					// .text();// 出资方式
					// rjDetail.date = gdjczxx_trs.get(j).select("td").get(6)
					// .text();// 出资日期
					// Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
					// sjDetail.amount = gdjczxx_trs.get(j).select("td").get(7)
					// .text();// 出资额（万元）
					// sjDetail.method = gdjczxx_trs.get(j).select("td").get(8)
					// .text();// 出资方式
					// sjDetail.date = gdjczxx_trs.get(j).select("td").get(9)
					// .text();// 出资日期
					qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
				}
			}
			qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息
		}

		// TODO
		// 变更信息
		List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
		EntpubStohrinvestChangeInfo gygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
		gygsGdjczBgInfo.setItem("");// 变更事项
		gygsGdjczBgInfo.setDateTime("");// 变更时间
		gygsGdjczBgInfo.setPreContent("");// 变更前
		gygsGdjczBgInfo.setPostContent("");// 变更后
		Elements investor = qygsxx_list.getElementsByAttributeValue("rel","layout-02_04");
		if (isDebug && investor.size()!=0) {
			gygsGdjczBgInfo.setHtml(investor.get(0).select("table").get(1).html());
		}
		qygsGdjczBgInfos.add(gygsGdjczBgInfo);
		qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);// 变更信息
		qygsInfo.setStohrInvestInfo(qygsGdjczInfo);// 企业公示信息->股东及出资信息

		// TODO
		// 企业公示信息->股权变更信息
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		// 企业公示信息->股权变更信息列表
		List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
		// 企业公示信息->股权变更信息
		EntpubEEquchangeInfo gygsGqbgGqbgInfo = new EntpubEEquchangeInfo();
		gygsGqbgGqbgInfo.setStockholder("");// 股东
		gygsGqbgGqbgInfo.setPreOwnershipRatio("");// 变更前股权比例
		gygsGqbgGqbgInfo.setPostOwnershipRatio("");// 变更后股权比例
		gygsGqbgGqbgInfo.setDateTime("");// 股权变更日期
		qygsGqbgGqbgInfos.add(gygsGqbgGqbgInfo);
		qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);// 企业公示信息->股权变更信息
		Element gudongguquan_et = qygsxx_list
				.getElementById("othStocktransTable");
		if (isDebug && null != gudongguquan_et) {
			qygsGqbgInfo.setHtml(gudongguquan_et.html());
		}
		qygsInfo.setEquChangeInfo(qygsGqbgInfo);// 企业公示信息->股权变更信息

		// TODO
		// 企业公示信息->行政许可信息
		EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
		// 企业公示信息->行政许可信息列表
		List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
		Elements xingzhengxuke_trs = qygsxx_list
				.getElementsByAttributeValue("rel", "layout-02_02").get(0)
				.select("tbody").get(0).select("tr");
		int xingzhengxuke_trs_size = xingzhengxuke_trs.size();
		if (xingzhengxuke_trs_size > 2) {
			for (int i = 2; i < xingzhengxuke_trs_size; i++) {
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
		qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
		qygsInfo.setAdmLicInfo(qygsXzxkInfo);// 企业公示信息->行政许可信息

		// TODO
		// 企业公示信息->知识产权出质登记信息
		EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
		// 企业公示信息->知识产权出质登记信息列表
		List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
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
		qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);// 企业公示信息->知识产权出质登记信息
		Elements zhishichanquan_et = qygsxx_list.getElementsByAttributeValue(
				"rel", "layout-02_03");
		if (isDebug && null != zhishichanquan_et) {
			qygsZscqczdjInfo.setHtml(zhishichanquan_et.html());
		}
		qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);// 企业公示信息->知识产权出质登记信息

		// TODO
		// 企业公示信息->行政处罚信息
		EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
		// 企业公示信息->行政处罚信息列表
		List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
		// 企业公示信息->行政处罚信息
		EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();
		qygsXzcfXzcfInfo.setPunishRepNum("");// 行政处罚决定书文号
		qygsXzcfXzcfInfo.setPunishContent("");// 行政处罚内容
		qygsXzcfXzcfInfo.setDeciAuthority("");// 作出行政处罚决定机关名称
		qygsXzcfXzcfInfo.setDeciDateTime("");// 作出行政处罚决定日期
		qygsXzcfXzcfInfo.setIllegalActType("");// 违法行为类型（暂无）
		qygsXzcfXzcfInfo.setNote("");// 备注（暂无）
		qygsXzcfXzcfInfos.add(qygsXzcfXzcfInfo);
		qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);// 企业公示信息->行政处罚信息
		Element xingzhengchufa_et1 = qygsxx_list.getElementById("ePunishTable");
		if (isDebug && null != xingzhengchufa_et1) {
			qygsXzcfInfo.setHtml(xingzhengchufa_et1.html());
		}
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
			Elements xingzhengxuke_et = qtbmgsxx_page
					.getElementsByAttributeValue("rel", "layout-03_01");
			if (isDebug && null != xingzhengxuke_et) {
				qtbmgsXzxkInfo.setHtml(xingzhengxuke_et.html());
			}
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
			// TODO
			// 司法协助公示信息->股权冻结信息
			JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
			// 司法协助公示信息->股权冻结信息列表
			List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
			// 司法协助公示信息->股权冻结信息
			JudasspubEEqufreezeInfo sfxzgsGqdjGqdjInfo = new JudasspubEEqufreezeInfo();
			sfxzgsGqdjGqdjInfo.setExecutedPerson("");// 被执行人
			sfxzgsGqdjGqdjInfo.setEquAmount("");// 股权数额
			sfxzgsGqdjGqdjInfo.setExeCourt("");// 执行法院
			sfxzgsGqdjGqdjInfo.setAssistPubNoticeNum("");// 协助公示通知书文号
			sfxzgsGqdjGqdjInfo.setStatus("");// 状态
			sfxzgsGqdjGqdjInfo.setDetail("");// 详情
			sfxzgsGqdjGqdjInfos.add(sfxzgsGqdjGqdjInfo);
			sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);// 司法协助公示信息->股权冻结信息列表
			Elements guquandongjie_et = sfxzgsxx_list_page
					.getElementsByAttributeValue("rel", "layout-06_01");
			if (isDebug && null != guquandongjie_et) {
				sfxzgsGqdjInfo.setHtml(guquandongjie_et.html());
			}
			sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);// 司法协助公示信息->股权冻结信息

			// TODO
			// 司法协助公示信息->股东变更信息
			JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
			// 司法协助公示信息->股东变更信息列表
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			// 司法协助公示信息->股东变更信息
			JudasspubSStohrchangeInfo sfxzgsGdbgGdbgInfo = new JudasspubSStohrchangeInfo();
			sfxzgsGdbgGdbgInfo.setExecutedPerson("");// 被执行人
			sfxzgsGdbgGdbgInfo.setEquAmount("");// 股权数额
			sfxzgsGdbgGdbgInfo.setAssignee("");// 受让人
			sfxzgsGdbgGdbgInfo.setExeCourt("");// 执行法院
			sfxzgsGdbgGdbgInfo.setDetail("");// 详情
			sfxzgsGdbgGdbgInfos.add(sfxzgsGdbgGdbgInfo);
			sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);// 司法协助公示信息->股东变更信息列表
			Elements guquanbiangeng_et = sfxzgsxx_list_page
					.getElementsByAttributeValue("rel", "layout-06_02");
			if (isDebug && null != guquanbiangeng_et) {
				sfxzgsGdbgInfo.setHtml(guquanbiangeng_et.html());
			}
			sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);// 司法协助公示信息->股东变更信息列表
		}

		// 司法协助公示信息
		gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);

		return gsxtFeedJson;

	}
}

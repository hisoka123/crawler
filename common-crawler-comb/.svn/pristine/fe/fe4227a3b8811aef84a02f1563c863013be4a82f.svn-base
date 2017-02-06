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
import com.crawler.gsxt.domain.json.AicpubArchiveMainDeptInfo;
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
import com.crawler.gsxt.domain.json.EntpubAnnreportEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportExtguaranteeInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportExtinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportManageInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportModifyInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportWebsiteInfo;
import com.crawler.gsxt.domain.json.EntpubEEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubIIntellectualproregInfo;
import com.crawler.gsxt.domain.json.EntpubInfo;
import com.crawler.gsxt.domain.json.EntpubIntellectualproregInfo;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo.Detail;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtGuizhouParser extends AbstractGsxtParser {

	private static final Log LOOGER = LogFactory
			.getLog(GsxtGuizhouParser.class);

	public AicFeedJson getGuizhouResultObj(String html, boolean isDebug) {

		LOOGER.info("The method of GsxtGuizhouParser.getGuizhouResultObj is begin !");

		Gson gson = new GsonBuilder().create();
		Type htmlMapType = new TypeToken<Map<String, Object>>() {}.getType();
		Map<String, Object> htmlMap = gson.fromJson(html, htmlMapType);

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
		Element table_baseinfo = baseinfo.select("table").get(0);		
		Elements jbxx_tds = table_baseinfo.select("tbody").select("tr").select("td");
		Elements jbxx_ths = table_baseinfo.select("tbody").select("tr").select("th");		
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
		for (int i = 1; i < jbxx_ths.size(); i++) {		
			if (jbxx_ths.get(i).text().trim().contains("统一社会信用代码")
					|| jbxx_ths.get(i).text().trim().contains("注册号")) {				
				gsgsDjJbInfo.setNum(jbxx_tds.get(i-1).text());// 注册号或信用代码
			} 	
			if (jbxx_ths.get(i).text().trim().contains("名称")) {	
				gsgsDjJbInfo.setName(jbxx_tds.get(i-1).text());// 名称
			} 
			if (jbxx_ths.get(i).text().trim().contains("类型")) {
				gsgsDjJbInfo.setType(jbxx_tds.get(i-1).text());// 类型
			}
			if (jbxx_ths.get(i).text().trim().contains("负责人")
					|| jbxx_ths.get(i).text().trim().contains("法定代表人")
					|| jbxx_ths.get(i).text().trim().contains("经营者")) {			
				gsgsDjJbInfo.setLegalRepr(jbxx_tds.get(i-1).text());// 法定代表人/经营者
			}
			if (jbxx_ths.get(i).text().trim().contains("注册资本")) {
				gsgsDjJbInfo.setRegCapital(jbxx_tds.get(i-1).text());// 注册资本
			}
			if (jbxx_ths.get(i).text().trim().contains("成立日期")
					|| jbxx_ths.get(i).text().trim().contains("注册日期")) {
				gsgsDjJbInfo.setRegDateTime(jbxx_tds.get(i - 1).text());// 成立日期
			}
			
			if (jbxx_ths.get(i).text().trim().contains("组成形式")) {
				gsgsDjJbInfo.setFormType(jbxx_tds.get(i-1).text());// 组成形式
			} 
			
			if (jbxx_ths.get(i).text().trim().contains("场所")
					|| jbxx_ths.get(i).text().trim().contains("住所")) {
				gsgsDjJbInfo.setAddress(jbxx_tds.get(i - 1).text());// 经营场所/住所
			}
			if (jbxx_ths.get(i).text().trim().contains("营业期限自") || jbxx_ths.get(i).text().trim().contains("经营期限自")) {
				gsgsDjJbInfo.setStartDateTime(jbxx_tds.get(i-1).text());// 营业期限自（即营业开始日期）
			} 
			if (jbxx_ths.get(i).text().trim().contains("营业期限至") || jbxx_ths.get(i).text().trim().contains("经营期限至")) {
				gsgsDjJbInfo.setEndDateTime(jbxx_tds.get(i-1).text());// 营业期限至（即营业结束日期）
			} 
			if (jbxx_ths.get(i).text().trim().contains("经营范围")) {
				gsgsDjJbInfo.setBusinessScope(jbxx_tds.get(i-1).text());// 经营范围/
			} 
			if (jbxx_ths.get(i).text().trim().contains("登记机关")) {
				gsgsDjJbInfo.setRegAuthority(jbxx_tds.get(i-1).text());// 登记机关
			} 
			if (jbxx_ths.get(i).text().trim().contains("核准日期")) {
				gsgsDjJbInfo.setApprovalDateTime(jbxx_tds.get(i-1).text());// 核准日期
			} 
			if (jbxx_ths.get(i).text().trim().contains("登记状态")) {
				gsgsDjJbInfo.setRegStatus(jbxx_tds.get(i-1).text());// 登记状态
			}
			
		}	

		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);// 基本信息
		// 工商公示信息->登记信息->股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
		Element touziren_table = gsgsxx_djxx.getElementById("tzxxTable");
		if (null != touziren_table) {
			Elements touziren_trs = touziren_table.select("tbody").select("tr");
			int touziren_nums = touziren_trs.size();
			
			if (touziren_nums > 2) {				
				Element touziren_trs_style = touziren_table.getElementsByAttributeValue("style", "text-align: right; padding-right: 20px; font-size: 16px;").first();					
				if (null != touziren_trs_style) {
					for (int i = 2; i < touziren_nums-1; i++) {
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
				}else{
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
		if (null != gsgsxx_djxx_tzrxx_xqs) {
			int gsgsxx_djxx_tzrxx_xqs_size = gsgsxx_djxx_tzrxx_xqs.size();
			if (gsgsxx_djxx_tzrxx_xqs_size > 0) {
				for (Map<String, Object> gsgsxx_djxx_tzrxx_xq : gsgsxx_djxx_tzrxx_xqs) {
					Document gsgsxx_djxx_tzrxx_xq_page = Jsoup
							.parseBodyFragment(gsgsxx_djxx_tzrxx_xq.get(
									"gsgsxx_djxx_tzrxx_xq").toString());
					Element gsgsxx_djxx_tzrxx_table = gsgsxx_djxx_tzrxx_xq_page
							.getElementById("touziren");
					if (null != gsgsxx_djxx_tzrxx_table) {
						Elements gsgsxx_djxx_tzrxx_xq_trs=gsgsxx_djxx_tzrxx_table.select("tbody").select("tr");						
						int gsgsxx_djxx_tzrxx_xq_trs_size = gsgsxx_djxx_tzrxx_xq_trs.size();
						if (gsgsxx_djxx_tzrxx_xq_trs_size > 2) {
							AicpubRegStohrStohrinvestInfo gsgsDjGdGdjczInfo = new AicpubRegStohrStohrinvestInfo();
							List<AmountDetail> subAmountDetails = new ArrayList<AmountDetail>();
							List<AmountDetail> paidAmountDetails = new ArrayList<AmountDetail>();
							gsgsDjGdGdjczInfo.setStockholder(gsgsxx_djxx_tzrxx_xq_trs.get(2).select("td").get(0).text());// 股东
							gsgsDjGdGdjczInfo.setStockType(gsgsxx_djxx_tzrxx_xq_trs.get(2).select("td").get(1).text());// 认缴额（万元）
							for (int i = 2; i < gsgsxx_djxx_tzrxx_xq_trs_size; i++) {
								AicpubRegStohrStohrinvestInfo.AmountDetail subamountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
								AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
								subamountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs.get(i).select("td").get(2).text();// 出资方式
								subamountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(3).text();// 出资额（万元）
								subamountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(4).text();// 出资日期
								paidAmountDetail.investMethod = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(5).text();// 出资方式
								paidAmountDetail.investAmount = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(6).text();// 出资额（万元）
								paidAmountDetail.investDateTime = gsgsxx_djxx_tzrxx_xq_trs
										.get(i).select("td").get(7).text();// 出资日期
								subAmountDetails.add(subamountDetail);
								paidAmountDetails.add(paidAmountDetail);
							}
							gsgsDjGdGdjczInfo.setSubAmountDetails(subAmountDetails);
							gsgsDjGdGdjczInfo.setPaidAmountDetails(paidAmountDetails);
							gsgsDjGdGdjczInfos.add(gsgsDjGdGdjczInfo);
						}
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
		Element biangeng = gsgsxx_djxx.getElementById("bgxxTable");
		if (null != biangeng) {
			Elements biangeng_trs = biangeng.select("tr");
			int biangeng_trs_size = biangeng_trs.size();
			Elements biangeng_trs_style = biangeng.getElementsByAttributeValue("style", "text-align: right; padding-right: 20px; font-size: 16px;");	
			if (null != biangeng_trs_style) {
				if (biangeng_trs_size > 2) {
					for (int i = 2; i < biangeng_trs_size-1; i++) {
						AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
						gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td")
								.get(0).text());// 变更事项					
							gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
									.select("td").get(1).text());// 变更前内容						
					
							gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
									.select("td").get(2).text());// 变更后内容			
						gsgsDjBgInfo.setDateTime(biangeng_trs.get(i).select("td")
								.get(3).text());// 变更日期
						gsgsDjBgInfos.add(gsgsDjBgInfo);
					}
				}
			}else{
				if (biangeng_trs_size > 2) {
					for (int i = 2; i < biangeng_trs_size; i++) {
						AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
						gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td")
								.get(0).text());// 变更事项
					
							gsgsDjBgInfo.setPreContent(biangeng_trs.get(i)
									.select("td").get(1).text());// 变更前内容
							gsgsDjBgInfo.setPostContent(biangeng_trs.get(i)
									.select("td").get(2).text());// 变更后内容
						
						gsgsDjBgInfo.setDateTime(biangeng_trs.get(i).select("td")
								.get(3).text());// 变更日期
						gsgsDjBgInfos.add(gsgsDjBgInfo);
					}
				}
			}		
			gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);// 变更信息
		}
		gsgsInfo.setRegInfo(gsgsDjInfo);// 工商公示信息->登记信息

		// 工商公示信息->备案信息
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
		if (null != htmlMap.get("gsgsxx_baxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_baxx").toString())) {
			Document gsgsxx_baxx = Jsoup.parseBodyFragment(htmlMap.get("gsgsxx_baxx").toString());								
			Element beian_zyryxx_tb = gsgsxx_baxx.getElementById("beian").getElementById("zyryTable");	
			if (null !=beian_zyryxx_tb) {
				Elements beian_zyryxx_trs = beian_zyryxx_tb.select("tr");
				// 工商公示信息->备案信息->主要人员信息
				List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
				int beian_zyryxx_size = beian_zyryxx_trs.size();			
				Element gsgsxx_baxx_style = beian_zyryxx_tb.getElementsByAttributeValue("style", "text-align: right; padding-right: 20px; font-size: 16px;").first();		
				if (null !=gsgsxx_baxx_style) {				
					if (beian_zyryxx_size > 2) {
						for (int i = 2; i < beian_zyryxx_size-1; i++) {
							String name1 = beian_zyryxx_trs.get(i).select("td").get(1).text();
							String position1 = beian_zyryxx_trs.get(i).select("td").get(2).text();
							String name2 = beian_zyryxx_trs.get(i).select("td").get(4).text();
							String position2 = beian_zyryxx_trs.get(i).select("td").get(5).text();
							// 主要人员信息
							AicpubArchivePrimemberInfo gsgsBaZyryInfo1 = new AicpubArchivePrimemberInfo();
							// 主要人员信息
							AicpubArchivePrimemberInfo gsgsBaZyryInfo2 = new AicpubArchivePrimemberInfo();
							if (null != name1 && !StringUtils.isEmpty(name1.trim())) {
								gsgsBaZyryInfo1.setName(name1);// 姓名
								gsgsBaZyryInfo1.setPosition(position1);// 职务
								gsgsBaZyryInfos.add(gsgsBaZyryInfo1);
							}
							if (null != name2 && !StringUtils.isEmpty(name2.trim())) {
								gsgsBaZyryInfo2.setName(name2);// 姓名
								gsgsBaZyryInfo2.setPosition(position2);// 职务
								gsgsBaZyryInfos.add(gsgsBaZyryInfo2);
							}
						}
					}
					gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
				}else{
					if (beian_zyryxx_size > 2) {
						for (int i = 2; i < beian_zyryxx_size; i++) {
							String name1 = beian_zyryxx_trs.get(i).select("td").get(1).text();
							String position1 = beian_zyryxx_trs.get(i).select("td").get(2).text();
							String name2 = beian_zyryxx_trs.get(i).select("td").get(4).text();
							String position2 = beian_zyryxx_trs.get(i).select("td").get(5).text();
							// 主要人员信息
							AicpubArchivePrimemberInfo gsgsBaZyryInfo1 = new AicpubArchivePrimemberInfo();
							// 主要人员信息
							AicpubArchivePrimemberInfo gsgsBaZyryInfo2 = new AicpubArchivePrimemberInfo();
							if (null != name1 && !StringUtils.isEmpty(name1.trim())) {
								gsgsBaZyryInfo1.setName(name1);// 姓名
								gsgsBaZyryInfo1.setPosition(position1);// 职务
								gsgsBaZyryInfos.add(gsgsBaZyryInfo1);
							}
							if (null != name2 && !StringUtils.isEmpty(name2.trim())) {
								gsgsBaZyryInfo2.setName(name2);// 姓名
								gsgsBaZyryInfo2.setPosition(position2);// 职务
								gsgsBaZyryInfos.add(gsgsBaZyryInfo2);
							}
						}
					}
				}
				
				gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
			}
			
			//主管部门（出资人）信息
			Element beian_tzxx_tb = gsgsxx_baxx.getElementById("beian").getElementById("tzxxTable");	
			if (null !=beian_tzxx_tb) {
				Elements beian_zyryxx_trs = beian_tzxx_tb.select("tr");
				// 工商公示信息->备案信息->主管部门（出资人）信息
				List<AicpubArchiveMainDeptInfo>	aicpubArchiveMainDeptInfos= new ArrayList<AicpubArchiveMainDeptInfo>();		
				int beian_zyryxx_size = beian_zyryxx_trs.size();
					if (beian_zyryxx_size > 2) {
						for (int i = 2; i < beian_zyryxx_size; i++) {
							String type = beian_zyryxx_trs.get(i).select("td").get(0).text();
							String name = beian_zyryxx_trs.get(i).select("td").get(1).text();
							String idType = beian_zyryxx_trs.get(i).select("td").get(2).text();
							String idNum = beian_zyryxx_trs.get(i).select("td").get(3).text();
							// 主管部门（出资人）信息
							AicpubArchiveMainDeptInfo aicpubArchiveMainDeptInfo = new AicpubArchiveMainDeptInfo();
							aicpubArchiveMainDeptInfo.setType(type);
							aicpubArchiveMainDeptInfo.setName(name);
							aicpubArchiveMainDeptInfo.setIdType(idType);
							aicpubArchiveMainDeptInfo.setIdNum(idNum);
							aicpubArchiveMainDeptInfos.add(aicpubArchiveMainDeptInfo);							
						}
					}
				gsgsBaInfo.setMainDeptInfo(aicpubArchiveMainDeptInfos);// 主管部门（出资人）信息
				
			}
			
			Element beian_zyryxx_tb2 = gsgsxx_baxx.getElementById("beian").getElementById("t30");
			if (null !=beian_zyryxx_tb2) {
				Elements gsgsxx_baxx_style2 = gsgsxx_baxx.getElementsByAttributeValue("style", "word-wrap: break-word; word-break: break-all;");	
				List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
				if (null !=gsgsxx_baxx_style2) {		
				if (gsgsxx_baxx_style2.size()>=2) {
					for (int i = 1; i <=gsgsxx_baxx_style2.size()/2; i+=2) {
						String name = gsgsxx_baxx_style2.get(i).text();						
						if (null != name && !StringUtils.isEmpty(name.trim())) {		
							AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
							gsgsBaZyryInfo.setName(name);// 姓名
							gsgsBaZyryInfos.add(gsgsBaZyryInfo);
						}
					}
				}						
			}		
			gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息	
		}
				
			// 工商公示信息->备案信息->分支机构信息
			List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
			Element beian_fzjgxx_table = gsgsxx_baxx.getElementById("beian")
					.getElementById("fzjgTable");			
			if (null !=beian_fzjgxx_table) {
				Elements beian_fzjgxx_tr=beian_fzjgxx_table.select("tr");
				int beian_fzjgxx_size = beian_fzjgxx_tr.size();
				if (beian_fzjgxx_size > 2) {
					for (int i = 2; i < beian_fzjgxx_size; i++) {
						// 分支机构信息
						AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
						gsgsBaFzjgInfo.setNum(beian_fzjgxx_tr.get(i).select("td")
								.get(1).text());// 统一社会信用代码/注册号
						gsgsBaFzjgInfo.setName(beian_fzjgxx_tr.get(i).select("td")
								.get(2).text());// 名称
						gsgsBaFzjgInfo.setRegAuthority(beian_fzjgxx_tr.get(i)
								.select("td").get(3).text());// 登记机关
						gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
					}
				}
				gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);// 分支机构信息
			}
		
			// 工商公示信息->备案信息->清算信息
			AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
			Elements beian_qingsuanrenyuan = gsgsxx_baxx.getElementById("beian").select("table");
			if (beian_qingsuanrenyuan.size()==3) {
				Element beian_qingsuan_table=beian_qingsuanrenyuan.get(2);
				String leader=beian_qingsuan_table.getElementById("2_qsfzr").text();
				gsgsBaQsInfo.setLeader(leader);// 清算组负责人				
				String member=beian_qingsuan_table.getElementById("2_qscy").text();			
				List<String> members=new ArrayList<String>();
				members.add(member);
				gsgsBaQsInfo.setMembers(members);// 清算组成员
				gsgsBaInfo.setClearInfo(gsgsBaQsInfo);// 清算信息				
			}
			gsgsInfo.setArchiveInfo(gsgsBaInfo);// 工商公示信息->备案信息
		}

		
		// 工商公示信息->动产抵押登记信息
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		if (null != htmlMap.get("gsgsxx_dcdydjxx_dcdydjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_dcdydjxx_dcdydjxx")))) {
			Document gsgsxx_dcdydjxx_dcdydjxx_page = Jsoup
					.parseBodyFragment(String.valueOf(htmlMap
							.get("gsgsxx_dcdydjxx_dcdydjxx")));
			// 工商公示信息->动产抵押登记信息列表
			List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
			Element dongchandiya_et = gsgsxx_dcdydjxx_dcdydjxx_page.getElementById("dongchandiya");
			if (null !=dongchandiya_et) {
				Element dongchandiya_table=dongchandiya_et.getElementById("dcdyTable");
				Elements dongchandiya_trs = dongchandiya_table.select("tbody").select("tr");
				int dongchandiya_trs_size=dongchandiya_trs.size();
				if (dongchandiya_trs_size>2) {
					for (int i = 2; i < dongchandiya_trs_size; i++) {
						AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
						gsgsDcdydjDcdydjInfo.setRegNum(dongchandiya_trs.get(i).select("td").get(1).text());// 登记编号
						gsgsDcdydjDcdydjInfo.setRegDateTime(dongchandiya_trs.get(i).select("td").get(2).text());// 登记日期
						gsgsDcdydjDcdydjInfo.setRegAuthority(dongchandiya_trs.get(i).select("td").get(3).text());// 登记机关
						gsgsDcdydjDcdydjInfo.setGuaranteedDebtAmount(dongchandiya_trs.get(i).select("td").get(4).text());// 被担保债权数额
						gsgsDcdydjDcdydjInfo.setStatus(dongchandiya_trs.get(i).select("td").get(5).text());// 状态
						gsgsDcdydjDcdydjInfo.setPubDateTime(dongchandiya_trs.get(i).select("td").get(6).text());// 公示时间（该字段比较特殊，江苏工商网暂时没有该字段。）
						gsgsDcdydjDcdydjInfo.setDetail(dongchandiya_trs.get(i).select("td").get(7).text());// 详情
						gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
					}
				}else{
					if (isDebug) {
						gsgsDcdydjInfo.setHtml(dongchandiya_et.toString());
					}					
				}
				gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);// 动产抵押登记信息
				gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息
			}			
	
		}

		
		// 工商公示信息->股权出质信息
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		if (null != htmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_gqczdjxx_gqczdjxx")))) {
			Document gsgsxx_gqczdjxx_gqczdjxx_page = Jsoup
					.parseBodyFragment(htmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")
							.toString());
			// 工商公示信息->股权出质登记信息列表
			List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
			
			// 工商公示信息->股权出质登记信息
			Element guquanchuzhi_table = gsgsxx_gqczdjxx_gqczdjxx_page.getElementById("gqczTable");
			if (null !=guquanchuzhi_table ) {				
				Elements guquanchuzhi_trs = guquanchuzhi_table.select("tbody").select("tr");
				int guquanchuzhi_trs_size=guquanchuzhi_trs.size();
				if (guquanchuzhi_trs_size>2) {
					for (int i = 2; i < guquanchuzhi_trs_size; i++) {					
						AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
						gsgsGqczdjGqczdjInfo.setRegNum(guquanchuzhi_trs.get(i).select("td").get(1).text());// 登记编号
						gsgsGqczdjGqczdjInfo.setMortgagorName(guquanchuzhi_trs.get(i).select("td").get(2).text());// 出质人
						gsgsGqczdjGqczdjInfo.setMortgagorIdNum(guquanchuzhi_trs.get(i).select("td").get(3).text());// 证照/证件号码（出质人）
						gsgsGqczdjGqczdjInfo.setMortgAmount(guquanchuzhi_trs.get(i).select("td").get(4).text());// 出质股权数额
						gsgsGqczdjGqczdjInfo.setMortgageeName(guquanchuzhi_trs.get(i).select("td").get(5).text());// 质权人
						gsgsGqczdjGqczdjInfo.setMortgageeIdNum(guquanchuzhi_trs.get(i).select("td").get(6).text());// 证照/证件号码
						gsgsGqczdjGqczdjInfo.setRegDateTime(guquanchuzhi_trs.get(i).select("td").get(7).text());// 股权出质设立登记日期						
						gsgsGqczdjGqczdjInfo.setStatus(guquanchuzhi_trs.get(i).select("td").get(8).text());// 状态
						gsgsGqczdjGqczdjInfo.setPubDate(guquanchuzhi_trs.get(i).select("td").get(9).text());// 公示时间（该字段比较特殊，江苏工商网暂时没有该字段。）
						gsgsGqczdjGqczdjInfo.setChangeSitu(guquanchuzhi_trs.get(i).select("td").get(10).text());// 变化情况
						gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);						
					}
				}
				gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);
			}	
			gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);// 工商公示信息->股权出资登记信息
		}

		// 工商公示信息->行政处罚信息
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
		if (null != htmlMap.get("gsgsxx_xzcfxx_xzcfxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_xzcfxx_xzcfxx")
						.toString())) {
			Document gsgsxx_jyycxx_jyycxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_xzcfxx_xzcfxx").toString());
			// 工商公示信息->行政处罚信息列表
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
			Element xingzhengchufa = gsgsxx_jyycxx_jyycxx.getElementById("xzcfTable");
			if (null != xingzhengchufa) {
				Elements xingzhengchufa_trs = xingzhengchufa.select("tbody").get(0).select("tr");
				int xingzhengchufa_trs_size = xingzhengchufa_trs.size();
				if (xingzhengchufa_trs_size > 3) {
					for (int i = 2; i < xingzhengchufa_trs_size-2; i++) {
						AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();
						gsgsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs
								.get(i).select("td").get(1).text());// 行政处罚决定书文号
						gsgsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs.get(i)
								.select("td").get(2).text());// 违法行为类型
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
			List<Map<String, Object>> gsgsxx_jyycxx_jyycxx_xqs = (List<Map<String, Object>>) htmlMap.get("gsgsxx_xzcfxx_xzcfxx_xqs");
			int gsgsxx_jyycxx_jyycxx_xqs_size = gsgsxx_jyycxx_jyycxx_xqs.size();
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos2 = new ArrayList<AicpubAAdmpunishInfo>();
			if (null != gsgsxx_jyycxx_jyycxx_xqs
					&& gsgsxx_jyycxx_jyycxx_xqs_size > 0) {
				for (Map<String, Object> gsgsxx_jyycxx_jyycxx_xq : gsgsxx_jyycxx_jyycxx_xqs) {
					Document gsgsxx_jyycxx_jyycxx_xq_page = Jsoup
							.parseBodyFragment(gsgsxx_jyycxx_jyycxx_xq.get(
									"gsgsxx_xzcfxx_xzcfxx_xq").toString());
					AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();					
					Elements gsgsxx_jyycxx_jyycxx_xq_trs = gsgsxx_jyycxx_jyycxx_xq_page.getElementById("sifapanding").select("table").get(1).select("tr");				
					AicpubAAdmpunishInfo.PunishDetail xzcfDetail = gsgsXzcfXzcfInfo.new PunishDetail();							
					xzcfDetail.punishRepNum = gsgsxx_jyycxx_jyycxx_xq_trs.select("td").get(0).text();// 行政处罚决定书文号
					xzcfDetail.name = gsgsxx_jyycxx_jyycxx_xq_trs.select("td").get(1).text();;// 名称
					xzcfDetail.regNum = gsgsxx_jyycxx_jyycxx_xq_trs.select("td").get(2).text();;// 注册号
					xzcfDetail.legalReprName = gsgsxx_jyycxx_jyycxx_xq_trs.select("td").get(3).text();;// 法定代表人（负责人）姓名
					xzcfDetail.illegalActType = gsgsxx_jyycxx_jyycxx_xq_trs.select("td").get(4).text();;// 违法行为类型
					xzcfDetail.punishContent = gsgsxx_jyycxx_jyycxx_xq_trs.select("td").get(5).text();;// 行政处罚内容
					xzcfDetail.deciAuthority = gsgsxx_jyycxx_jyycxx_xq_trs.select("td").get(6).text();;// 作出行政处罚决定机关名称
					xzcfDetail.deciDateTime = gsgsxx_jyycxx_jyycxx_xq_trs.select("td").get(7).text();;// 作出行政处罚决定日期
					
					Element ele_div_gscf1 = getfirstChildElement(gsgsxx_jyycxx_jyycxx_xq_page, "#sifapanding div.gscf1");
					if (ele_div_gscf1!=null) {
				     Elements select = gsgsxx_jyycxx_jyycxx_xq_page.getElementById("sifapanding").select("div.gscf1");
						xzcfDetail.punishRep = select.outerHtml();// 行政处罚决定书
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
			Element yichangminglu = gsgsxx_jyycxx_jyycxx.getElementById("jingyingyichang");
			Element yichangminglu_table=yichangminglu.getElementById("jyycTable");
			Elements yichangminglu_trs = yichangminglu_table.select("tbody").select("tr");
			int yichangminglu_trs_size = yichangminglu_trs.size();
			if (yichangminglu_trs_size > 2) {
				for (int i = 2; i < yichangminglu_trs_size; i++) {
					Elements yichangminglu_tds= yichangminglu_trs.get(i).select("td");				
					AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();// 经营异常信息
					gsgsJyycJyycInfo.setIncludeCause(yichangminglu_tds.get(1).text());// 列入经营异常名录原因				
					gsgsJyycJyycInfo.setIncludeDateTime(yichangminglu_tds.get(2).text());// 列入日期			
					gsgsJyycJyycInfo.setRemoveCause(yichangminglu_tds.get(3).text());// 移出经营异常名录原因					
					gsgsJyycJyycInfo.setRemoveDateTime(yichangminglu_tds.get(4).text());// 移出日期
					gsgsJyycJyycInfo.setAuthority(yichangminglu_tds.get(5).text());// 作出决定机关
					Elements  yichangminglu_as=yichangminglu_tds.select("a");
					if (null != yichangminglu_as) {			
						gsgsJyycJyycInfo.setSerialNumber(yichangminglu_as.get(0).text());
					}
					
					gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
				}
			}
				
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> gsgsxx_jyycxx_jyycxx_xqs = (List<Map<String, Object>>) htmlMap.get("gsgsxx_jyycxx_jyycxx_xqs");
			int gsgsxx_jyycxx_jyycxx_xqs_size = gsgsxx_jyycxx_jyycxx_xqs.size();
			List<AicpubOOperanomaInfo> gsgsJyycJyycInfos2 = new ArrayList<AicpubOOperanomaInfo>();
			if (null != gsgsxx_jyycxx_jyycxx_xqs
					&& gsgsxx_jyycxx_jyycxx_xqs_size > 0) {
				for (Map<String, Object> gsgsxx_jyycxx_jyycxx_xq : gsgsxx_jyycxx_jyycxx_xqs) {
					Document gsgsxx_jyycxx_jyycxx_xq_page = Jsoup.parseBodyFragment(gsgsxx_jyycxx_jyycxx_xq.get(
									"gsgsxx_jyycxx_jyycxx_xq").toString());
				
				    Elements table_el= gsgsxx_jyycxx_jyycxx_xq_page.getElementById("qufenkuang").select("table");
				    if (null != table_el) {
				    	AicpubOOperanomaInfo gsgsJyycJyycInfo2 = new AicpubOOperanomaInfo();	
				    	  Element tableHtml=table_el.get(0);
				    	  String serialNumber=gsgsxx_jyycxx_jyycxx_xq_page.getElementById("1_jdswh").text();
				    	  gsgsJyycJyycInfo2.setIncludeCauseDetail(tableHtml.toString());
				    	  gsgsJyycJyycInfo2.setSerialNumber(serialNumber);
				    	  gsgsJyycJyycInfos2.add(gsgsJyycJyycInfo2);
					}			
					
		    	}
			}
						
			if (null != gsgsJyycJyycInfos && gsgsJyycJyycInfos.size() > 0
					&& null != gsgsJyycJyycInfos2
					&& gsgsJyycJyycInfos2.size() > 0) {
				List<AicpubOOperanomaInfo> gsgsJyycJyycInfos3 = new ArrayList<AicpubOOperanomaInfo>();
				for (AicpubOOperanomaInfo gsgsJyycJyycInfo : gsgsJyycJyycInfos) {
					for (AicpubOOperanomaInfo gsgsJyycJyycInfo2 : gsgsJyycJyycInfos2) {								
						if (gsgsJyycJyycInfo.getSerialNumber().trim().contains(gsgsJyycJyycInfo2.getSerialNumber().trim())) {
							gsgsJyycJyycInfo.setIncludeCauseDetail(gsgsJyycJyycInfo2.getIncludeCauseDetail());
						}
												
					}
					gsgsJyycJyycInfos3.add(gsgsJyycJyycInfo);
				}
				gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos3);// 经营异常信息列表
			} else {
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
			Element yanzhongweifa_div = gsgsxx_yzwfxx_yzwfxx_page.getElementById("yanzhongweifa");			
			if (null !=yanzhongweifa_div) {
				Element yanzhongweifa_table=yanzhongweifa_div.getElementById("yzwfTable");
				Elements yanzhongweifa_trs=yanzhongweifa_table.select("tbody").select("tr");
				int yanzhongweifa_trs_size=yanzhongweifa_trs.size();
				if (yanzhongweifa_trs_size>2) {
					for (int i = 2; i < yanzhongweifa_trs_size; i++) {
						Elements yanzhongweifa_tds=yanzhongweifa_trs.select("td");
						String lryzwfqymdCause = yanzhongweifa_tds.get(1).text();
						String lrDate = yanzhongweifa_tds.get(2).text();
						String ycyzwfqymdCause = yanzhongweifa_tds.get(3).text();
						String ycDate = yanzhongweifa_tds.get(4).text();
						String zcjdAuthority = yanzhongweifa_tds.get(5).text();
						AicpubSSerillegalInfo gsgsYzwfYzwfInfo = new AicpubSSerillegalInfo();
						gsgsYzwfYzwfInfo.setIncludeCause(lryzwfqymdCause);
						gsgsYzwfYzwfInfo.setIncludeDateTime(lrDate);
						gsgsYzwfYzwfInfo.setRemoveCause(ycyzwfqymdCause);
						gsgsYzwfYzwfInfo.setRemoveDateTime(ycDate);
						gsgsYzwfYzwfInfo.setDeciAuthority(zcjdAuthority);
						gsgsYzwfYzwfInfos.add(gsgsYzwfYzwfInfo);
					}
				}else{
					if(isDebug) {
						gsgsYzwfInfo.setHtml(yanzhongweifa_div.toString());
					}
				}		
				gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);// 严重违法信息
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
			Elements chouchaxinxi_trs = gsgsxx_ccjcxx_ccjcxx
					.getElementById("chouchajiancha").select("ccjcTable").select("tbody").select("tr");
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
			gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);// 抽查检查信息
			gsgsInfo.setCheckInfo(gsgsCcjcInfo);// 工商公示信息->抽查检查信息
		}

		gsxtFeedJson.setAicPubInfo(gsgsInfo);// 工商公示信息

		// 企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();

		Document qygsxx_qynb = Jsoup.parseBodyFragment(htmlMap.get(
				"qygsxx_qynb").toString());
		
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
				String submitYear=qygsxx_qynb_info.get("qygsxx_qynb_list_a_text").toString();
				qygsQynbInfo.setSubmitYear(submitYear);// 报送年度							
				Elements qygsxx_qynb_as = qygsxx_qynb.getElementsByTag("a");
				for (Element qygsxx_qynb_a : qygsxx_qynb_as) {
					if (qygsxx_qynb_a.text().contains(submitYear)) {
						qygsQynbInfo.setPubDateTime(qygsxx_qynb_a.parent().nextElementSibling().text());// 发布日期
						break;
					}
				}
//				
				// 企业公示信息->企业年报->企业基本信息
				EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
				if (null != qygsxx_qynb_info_page.getElementById("1_zch")
						&& qygsxx_qynb_info_page.getElementById("1_zch").hasText()) {
					qygsQynbJbInfo.setNum(qygsxx_qynb_info_page
							.getElementById("1_zch").text());// 注册号/统一社会信用代码
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_qymc")					
						&& qygsxx_qynb_info_page.getElementById("1_qymc").hasText()) {
					qygsQynbJbInfo.setName(qygsxx_qynb_info_page
							.getElementById("1_qymc").text());// 企业名称
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_lxdh")
						&& qygsxx_qynb_info_page.getElementById("1_lxdh").hasText()) {
					qygsQynbJbInfo.setTel(qygsxx_qynb_info_page.getElementById("1_lxdh")
							.text());// 企业联系电话
				}
				if (null != qygsxx_qynb_info_page
						.getElementById("1_yzbm")
						&& qygsxx_qynb_info_page
								.getElementById("1_yzbm").hasText()) {
					qygsQynbJbInfo.setZipCode(qygsxx_qynb_info_page
							.getElementById("1_yzbm")
							.text());// 邮政编码
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_dz")
						&& qygsxx_qynb_info_page.getElementById("1_dz").hasText()) {
					qygsQynbJbInfo.setAddress(qygsxx_qynb_info_page.getElementById("1_dz")
							.text());// 企业通信地址
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_dzyx")
						&& qygsxx_qynb_info_page.getElementById("1_dzyx").hasText()) {
					qygsQynbJbInfo.setEmail(qygsxx_qynb_info_page.getElementById("1_dzyx").text());// 电子邮箱
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_sfzr")
						&& qygsxx_qynb_info_page.getElementById("1_sfzr").hasText()) {
					qygsQynbJbInfo.setIsStohrEquTransferred(qygsxx_qynb_info_page.getElementById("1_sfzr").text());// 有限责任公司本年度是否发生股东股权转让
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_jyzt")
						&& qygsxx_qynb_info_page.getElementById("1_jyzt").hasText()) {
					qygsQynbJbInfo.setOperatingStatus(qygsxx_qynb_info_page.getElementById("1_jyzt").text());// 企业经营状态
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_sfww")
						&& qygsxx_qynb_info_page.getElementById("1_sfww").hasText()) {
					qygsQynbJbInfo.setHasWebsiteOrStore(qygsxx_qynb_info_page.getElementById("1_sfww").text());// 是否有网站或网店
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_sfdw")
						&& qygsxx_qynb_info_page.getElementById("1_sfdw").hasText()) {
					qygsQynbJbInfo.setHasInvestInfoOrPurchOtherCorpEqu(qygsxx_qynb_info_page.getElementById("1_sfdw").text());// 企业是否有投资信息或购买其他公司股权
				}
				if (null != qygsxx_qynb_info_page.getElementById("1_cyrs")
						&& qygsxx_qynb_info_page.getElementById("1_cyrs").hasText()) {
					qygsQynbJbInfo.setEmpNum(qygsxx_qynb_info_page.getElementById("1_cyrs").text());// 从业人数
				}
				qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);// 企业公示信息->企业年报
							
				//企业年报信息--> 网站或网店信息
				List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
				Element wzwdxxTable = qygsxx_qynb_info_page.getElementById("wzwdTable");				
				if (null != wzwdxxTable) {
					Elements wzwdxxTrs = wzwdxxTable.select("tr");
					int wzwdxxTrs_size=wzwdxxTrs.size();					
					Element wzwdxxTable_style = wzwdxxTable.getElementsByAttributeValue("style", "text-align: right; padding-right: 20px; font-size: 16px;").first();				
					if (null != wzwdxxTable_style) {
						if (wzwdxxTrs_size>2) {
							for (int i = 2; i < wzwdxxTrs_size-1; i++) {					
								EntpubAnnreportWebsiteInfo qygsQynbWzhwdInfo=new EntpubAnnreportWebsiteInfo();
								qygsQynbWzhwdInfo.setType(wzwdxxTrs.get(i).select("td").get(0).text());
								qygsQynbWzhwdInfo.setName(wzwdxxTrs.get(i).select("td").get(1).text());
								qygsQynbWzhwdInfo.setWebsite(wzwdxxTrs.get(i).select("td").get(2).text());
								qygsQynbWzhwdInfos.add(qygsQynbWzhwdInfo);						
							}										
						}
					}else{
						if (wzwdxxTrs_size>2) {
							for (int i = 2; i < wzwdxxTrs_size; i++) {					
								EntpubAnnreportWebsiteInfo qygsQynbWzhwdInfo=new EntpubAnnreportWebsiteInfo();
								qygsQynbWzhwdInfo.setType(wzwdxxTrs.get(i).select("td").get(0).text());
								qygsQynbWzhwdInfo.setName(wzwdxxTrs.get(i).select("td").get(1).text());
								qygsQynbWzhwdInfo.setWebsite(wzwdxxTrs.get(i).select("td").get(2).text());
								qygsQynbWzhwdInfos.add(qygsQynbWzhwdInfo);						
							}										
						}
					}			
										
					qygsQynbInfo.setWebsiteInfos(qygsQynbWzhwdInfos);
				}
				
					
				//企业年报信息--> 股东及出资信息
				Element chuziren = qygsxx_qynb_info_page.getElementById("tzxxTable");
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();// 股东及出资信息
				if (null != chuziren) {
					Elements qyjbxx_gd_trs = chuziren.select("tr");
					int qyjbxx_gd_trs_size = qyjbxx_gd_trs.size();				
					Element qyjbxx_gd_style = chuziren.getElementsByAttributeValue("style", "text-align: right; padding-right: 20px; font-size: 16px;").first();
					if (null !=qyjbxx_gd_style) {
						if (qyjbxx_gd_trs_size > 2) {
							for (int i = 2; i < qyjbxx_gd_trs_size-1; i++) {
								EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo = new EntpubAnnreportStohrinvestInfo();// 股东及出资信息
								qygsQynbGdjczInfo.setStockholder(qyjbxx_gd_trs
										.get(i).select("td").get(0).text());// 股东（发起人）
								qygsQynbGdjczInfo.setSubAmount(qyjbxx_gd_trs
										.get(i).select("td").get(1).text());// 认缴出资额（万元）
								qygsQynbGdjczInfo.setSubDateTime(qyjbxx_gd_trs.get(i)
										.select("td").get(2).text());// 认缴出资时间
								qygsQynbGdjczInfo.setSubMethod(qyjbxx_gd_trs
										.get(i).select("td").get(3).text());// 认缴出资方式
								qygsQynbGdjczInfo.setPaidAmount(qyjbxx_gd_trs
										.get(i).select("td").get(4).text());// 实缴出资额（万元）
								qygsQynbGdjczInfo.setPaidDateTime(qyjbxx_gd_trs.get(i)
										.select("td").get(5).text());// 实缴出资时间
								qygsQynbGdjczInfo.setPaidMethod(qyjbxx_gd_trs
										.get(i).select("td").get(6).text());// 实缴出资方式
								qygsQynbGdjczInfos.add(qygsQynbGdjczInfo);
							}
						}
					}else{
						if (qyjbxx_gd_trs_size > 2) {
							for (int i = 2; i < qyjbxx_gd_trs_size; i++) {
								EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo = new EntpubAnnreportStohrinvestInfo();// 股东及出资信息
								qygsQynbGdjczInfo.setStockholder(qyjbxx_gd_trs
										.get(i).select("td").get(0).text());// 股东（发起人）
								qygsQynbGdjczInfo.setSubAmount(qyjbxx_gd_trs
										.get(i).select("td").get(1).text());// 认缴出资额（万元）
								qygsQynbGdjczInfo.setSubDateTime(qyjbxx_gd_trs.get(i)
										.select("td").get(2).text());// 认缴出资时间
								qygsQynbGdjczInfo.setSubMethod(qyjbxx_gd_trs
										.get(i).select("td").get(3).text());// 认缴出资方式
								qygsQynbGdjczInfo.setPaidAmount(qyjbxx_gd_trs
										.get(i).select("td").get(4).text());// 实缴出资额（万元）
								qygsQynbGdjczInfo.setPaidDateTime(qyjbxx_gd_trs.get(i)
										.select("td").get(5).text());// 实缴出资时间
								qygsQynbGdjczInfo.setPaidMethod(qyjbxx_gd_trs
										.get(i).select("td").get(6).text());// 实缴出资方式
								qygsQynbGdjczInfos.add(qygsQynbGdjczInfo);
							}
						}
					}
															
					qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);// 企业公示信息->股东及出资信息
				}
			
				//企业年报信息--> 对外投资信息
				List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();// 对外投资信息
				Element qynbDwtzInfos = qygsxx_qynb_info_page.getElementById("dwtzTable");
				if (null != qynbDwtzInfos) {
					Elements dwtzxx_gd_trs = qynbDwtzInfos.select("tr");
					int dwtzxx_gd_trs_size = dwtzxx_gd_trs.size();								
					Element dwtzxx_gd_style = qynbDwtzInfos.getElementsByAttributeValue("style", "text-align: right; padding-right: 20px; font-size: 16px;").first();					
					if (null !=dwtzxx_gd_style) {
						if (dwtzxx_gd_trs_size > 2) {
							for (int i = 2; i < dwtzxx_gd_trs_size-1; i++) {
								EntpubAnnreportExtinvestInfo qygsQynbDwtzInfo = new EntpubAnnreportExtinvestInfo();// 股东及出资信息							
								qygsQynbDwtzInfo.setEnterpriseName(dwtzxx_gd_trs.get(i).select("td").get(0).text());//投资设立企业或购买股权企业名称
								qygsQynbDwtzInfo.setRegNum(dwtzxx_gd_trs.get(i).select("td").get(1).text());// 股东（发起人）								
								qygsQynbDwtzInfos.add(qygsQynbDwtzInfo);
							}
						}
					}else{					
						if (dwtzxx_gd_trs_size > 2) {
							for (int i = 2; i < dwtzxx_gd_trs_size; i++) {
								EntpubAnnreportExtinvestInfo qygsQynbDwtzInfo = new EntpubAnnreportExtinvestInfo();// 股东及出资信息							
								qygsQynbDwtzInfo.setEnterpriseName(dwtzxx_gd_trs
										.get(i).select("td").get(0).text());//投资设立企业或购买股权企业名称
								qygsQynbDwtzInfo.setRegNum(dwtzxx_gd_trs
										.get(i).select("td").get(1).text());// 股东（发起人）								
								qygsQynbDwtzInfos.add(qygsQynbDwtzInfo);
							}
						}
					}
					
					qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzInfos);
				}
			
				Elements qyjbxx_qyzczkxx = qygsxx_qynb_info_page.getElementById("sifapanding").select("table");	
				int table_size=qyjbxx_qyzczkxx.size();
				for (int i = 0; i < table_size; i++) {
					Element qyjbxx_qyzczkxx_table=qyjbxx_qyzczkxx.get(i);				
					  Elements qygsxx_qynb_info_ths = getElements(qyjbxx_qyzczkxx_table, "th");	
					  Elements qygsxx_qynb_info_tds = getElements(qyjbxx_qyzczkxx_table, "td");	
					 String table_name=qygsxx_qynb_info_ths.get(0).text();						
					 if (table_name.contains("企业资产状况信息")) {
						// 企业资产状况信息
						EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
					    String assetAmount = qygsxx_qynb_info_tds.get(0).text();
						String syzqyhj = qygsxx_qynb_info_tds.get(1).text();
						String liabilityAmount = qygsxx_qynb_info_tds.get(2).text();
						String salesAmount = qygsxx_qynb_info_tds.get(3).text();
						String profitAmount = qygsxx_qynb_info_tds.get(4).text();
						String xszezzyywsr = qygsxx_qynb_info_tds.get(5).text();
						String netProfit = qygsxx_qynb_info_tds.get(6).text();
						String taxesAmount = qygsxx_qynb_info_tds.get(7).text();
									
						qygsQynbQyzczkInfo.setAssetAmount(assetAmount);
						qygsQynbQyzczkInfo.setTotalEquity(syzqyhj);
						qygsQynbQyzczkInfo.setLiabilityAmount(liabilityAmount);
						qygsQynbQyzczkInfo.setSalesAmount(salesAmount);
						qygsQynbQyzczkInfo.setProfitAmount(profitAmount);
						qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(xszezzyywsr);
						qygsQynbQyzczkInfo.setNetProfit(netProfit);
						qygsQynbQyzczkInfo.setTaxesAmount(taxesAmount);
						qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);// 企业资产状况信息		
					}else if (table_name.contains("生产经营情况")) {
						//生产经营情况
						List<EntpubAnnreportManageInfo> manageInfos = new ArrayList<EntpubAnnreportManageInfo>();
						EntpubAnnreportManageInfo manageInfo=new EntpubAnnreportManageInfo();
						Elements qynbscjyqkTds = qygsxx_qynb_info_tds;
						String saleSum = qynbscjyqkTds.get(0).text();
						String salarySum = qynbscjyqkTds.get(1).text();
						String netProfit = qynbscjyqkTds.get(2).text();
						manageInfo.setSaleSum(saleSum);
						manageInfo.setSalarySum(salarySum);
						manageInfo.setNetProfit(netProfit);
						manageInfos.add(manageInfo);
						qygsQynbInfo.setManageInfos(manageInfos);
					}
				}

				// 对外提供保证担保信息
				List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();				
				Element  dwtgdbxx_tb=qygsxx_qynb_info_page.getElementById("dwtgdbTable");
				if (null != dwtgdbxx_tb) {
					Elements dwtgdbxx_trs=dwtgdbxx_tb.select("tr");
					if (dwtgdbxx_trs.size()>2) {
						for (int j = 2; j < dwtgdbxx_trs.size(); j++) {
							EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
							qygsQynbDwtgbzdbInfo.setCreditor(dwtgdbxx_trs.get(j).select("td").get(0).text());
							qygsQynbDwtgbzdbInfo.setDebtor(dwtgdbxx_trs.get(j).select("td").get(1).text());
							
							qygsQynbDwtgbzdbInfo.setPriCredRightType(dwtgdbxx_trs.get(j).select("td").get(2).text());
							qygsQynbDwtgbzdbInfo.setPriCredRightAmount(dwtgdbxx_trs.get(j).select("td").get(3).text());
							qygsQynbDwtgbzdbInfo.setExeDebtDeadline(dwtgdbxx_trs.get(j).select("td").get(4).text());
							qygsQynbDwtgbzdbInfo.setGuaranteePeriod(dwtgdbxx_trs.get(j).select("td").get(5).text());
							qygsQynbDwtgbzdbInfo.setGuaranteeMethod(dwtgdbxx_trs.get(j).select("td").get(6).text());
							//qygsQynbDwtgbzdbInfo.setGuaranteeScope(dwtgdbxx_trs.get(j).select("td").get(7).text());
							qygsQynbDwtgbzdbInfos.add(qygsQynbDwtgbzdbInfo);
						}
					}

					qygsQynbInfo.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);// 对外提供保证担保信息
				}
			
				
				// 股权变更记录   
				List<EntpubAnnreportEquchangeInfo> qygsQynbGqbgInfos = new ArrayList<EntpubAnnreportEquchangeInfo>();
				
				Element  gqbgxx_tb=qygsxx_qynb_info_page.getElementById("tzbgxxTable");
				if (null !=gqbgxx_tb) {
					Elements gqbgxx_trs=gqbgxx_tb.select("tr");
	    			if (gqbgxx_trs.size()>2) {					
						for (int i = 2; i < gqbgxx_trs.size(); i++) {
							EntpubAnnreportEquchangeInfo qygsQynbGqbgInfo = new EntpubAnnreportEquchangeInfo();
							
							qygsQynbGqbgInfo.setStockholder(gqbgxx_trs.get(i).select("td").get(1).text()); //股东（发起人）							
							qygsQynbGqbgInfo.setPreOwnershipRatio(gqbgxx_trs.get(i).select("td").get(2).text());//变更前股权比例
							qygsQynbGqbgInfo.setPostOwnershipRatio(gqbgxx_trs.get(i).select("td").get(3).text());	//变更后股权比例
							qygsQynbGqbgInfo.setDateTime(gqbgxx_trs.get(i).select("td").get(4).text());//股权变更日期							
							qygsQynbGqbgInfos.add(qygsQynbGqbgInfo);
						}
						
					}
	    			qygsQynbInfo.setEquChangeInfos(qygsQynbGqbgInfos);// 股权变更记录   
				}		
			
				// 修改记录
				List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();				
				Element  xgjlxx_tb=qygsxx_qynb_info_page.getElementById("table_bgxx");
			
				if (null !=xgjlxx_tb) {
					Elements xgjlxx_trs=xgjlxx_tb.select("tr");
					if (xgjlxx_trs.size()>2) {					
						for (int i = 2; i < xgjlxx_trs.size(); i++) {
							EntpubAnnreportModifyInfo qygsQynbXgjlInfo = new EntpubAnnreportModifyInfo();													
							qygsQynbXgjlInfo.setItem(xgjlxx_trs.get(i).select("td").get(0).text());
							qygsQynbXgjlInfo.setPreContent(xgjlxx_trs.get(i).select("td").get(1).text());
							qygsQynbXgjlInfo.setPostContent(xgjlxx_trs.get(i).select("td").get(2).text());
							qygsQynbXgjlInfo.setDateTime(xgjlxx_trs.get(i).select("td").get(3).text());							
							qygsQynbXgjlInfos.add(qygsQynbXgjlInfo);
						}
						
					}
					qygsQynbInfo.setChangeInfos(qygsQynbXgjlInfos);
				}	
				qygsQynbInfos.add(qygsQynbInfo);
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
			Element touzixinxi = qygsxx_gdjczxx.getElementById("touzixinxi");
			if (null!= touzixinxi) {
				Element gdjczxx_table = qygsxx_gdjczxx.getElementById("tzxxTable");				
				if (null != gdjczxx_table) {
					Elements gdjczxx_trs=gdjczxx_table.select("tbody").select("tr");
					int gdjczxx_trs_size = gdjczxx_trs.size();
					if (gdjczxx_trs_size > 2) {
						for (int j = 2; j < gdjczxx_trs_size; j++) {
							// 企业公示信息->股东及出资信息
							EntpubSStohrinvestInfo qygsGdjczGdjczInfo = new EntpubSStohrinvestInfo();
							Elements gdjczxx_tds=gdjczxx_trs.select("td");
							qygsGdjczGdjczInfo.setStockholder(gdjczxx_tds.get(0).text());// 股东
							qygsGdjczGdjczInfo.setSubAmount(gdjczxx_tds.get(1).text());// 认缴额（万元）
							qygsGdjczGdjczInfo.setPaidAmount(gdjczxx_tds.get(2).text());// 实缴额（万元）
							
													
							List<Detail> subDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
							List<Detail> paidDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();	
							EntpubSStohrinvestInfo.Detail subDetail = qygsGdjczGdjczInfo.new Detail();// 认缴明细					
							subDetail.amount = gdjczxx_tds.get(3).text();// 出资额（万元）
							subDetail.method = gdjczxx_tds.get(4).text();// 出资方式
							subDetail.dateTime = gdjczxx_tds.get(5).text();// 出资日期
							subDetail.showDate =gdjczxx_tds.get(6).text();// 公示日期
							subDetails.add(subDetail);
							EntpubSStohrinvestInfo.Detail paidDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
							paidDetail.amount = gdjczxx_tds.get(7).text();// 出资额（万元）
							paidDetail.method = gdjczxx_tds.get(8).text();// 出资方式
							paidDetail.dateTime = gdjczxx_tds.get(9).text();// 出资日期
							paidDetail.showDate = gdjczxx_tds.get(10).text();// 出资日期
							paidDetails.add(paidDetail);
							qygsGdjczGdjczInfo.setSubDetails(subDetails);
							qygsGdjczGdjczInfo.setPaidDetails(paidDetails);						
							qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
						}
					}
					qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息		
				}
				
				//变更信息
				Element bgxx_tb = qygsxx_gdjczxx.getElementById("tzbgxxTable");//变更信息				
				if (null !=bgxx_tb) {
					Elements bgxx_trs=bgxx_tb.select("tbody").select("tr");
					List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();				
					int bgxx_trs_size = bgxx_trs.size();
					if (bgxx_trs_size > 2) {
						for (int j = 2; j < bgxx_trs.size(); j ++) {						
							EntpubStohrinvestChangeInfo qygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
							qygsGdjczBgInfo.setItem(bgxx_trs.get(j).select("td").get(0).text());
							qygsGdjczBgInfo.setDateTime(bgxx_trs.get(j).select("td").get(1).text());
							qygsGdjczBgInfo.setPreContent(bgxx_trs.get(j).select("td").get(2).text());
							qygsGdjczBgInfo.setPostContent(bgxx_trs.get(j).select("td").get(3).text());												
							qygsGdjczBgInfos.add(qygsGdjczBgInfo);
						}
					}				
					qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);
				}					
			}
		}

		// 企业公示信息->股权变更信息
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		if (null != htmlMap.get("qygsxx_gqbgxx")
				&& !StringUtils
						.isEmpty(htmlMap.get("qygsxx_gqbgxx").toString())) {
			Document qygsxx_gqbgxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_gqbgxx")));
			// 企业公示信息->股权变更信息列表
			List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();			
			Element guquanbiangeng_tb = qygsxx_gqbgxx_page.getElementById("gqzrTable");			
			if (null !=guquanbiangeng_tb) {
				Elements guquanbiangeng_trs=guquanbiangeng_tb.select("tbody").get(0).select("tr");
				int guquanbiangeng_trs_size = guquanbiangeng_trs.size();
				Element guquanbiangeng_trs_style = guquanbiangeng_tb.getElementsByAttributeValue("style", "text-align: right; padding-right: 20px; font-size: 16px;").first();   
				if (null !=guquanbiangeng_trs_style) {
					if (guquanbiangeng_trs_size > 2) {
						for (int i = 2; i < guquanbiangeng_trs_size-1; i++) {
							//  企业公示信息->股权变更信息
							EntpubEEquchangeInfo gygsGqbgGqbgInfo = new EntpubEEquchangeInfo();
							gygsGqbgGqbgInfo.setStockholder(guquanbiangeng_trs.get(i)
									.select("td").get(1).text());// 许可文件编号
							gygsGqbgGqbgInfo.setPreOwnershipRatio(guquanbiangeng_trs.get(i)
									.select("td").get(2).text());// 许可文件名称
							gygsGqbgGqbgInfo.setPostOwnershipRatio(guquanbiangeng_trs.get(i)
									.select("td").get(3).text());// 有效期自
							gygsGqbgGqbgInfo.setDateTime(guquanbiangeng_trs.get(i)
									.select("td").get(4).text());// 有效期至
							
							qygsGqbgGqbgInfos.add(gygsGqbgGqbgInfo);
						}
					}
				}else{
					if (guquanbiangeng_trs_size > 2) {
						for (int i = 2; i < guquanbiangeng_trs_size; i++) {
							//  企业公示信息->股权变更信息
							EntpubEEquchangeInfo gygsGqbgGqbgInfo = new EntpubEEquchangeInfo();
							gygsGqbgGqbgInfo.setStockholder(guquanbiangeng_trs.get(i)
									.select("td").get(1).text());// 许可文件编号
							gygsGqbgGqbgInfo.setPreOwnershipRatio(guquanbiangeng_trs.get(i)
									.select("td").get(2).text());// 许可文件名称
							gygsGqbgGqbgInfo.setPostOwnershipRatio(guquanbiangeng_trs.get(i)
									.select("td").get(3).text());// 有效期自
							gygsGqbgGqbgInfo.setDateTime(guquanbiangeng_trs.get(i)
									.select("td").get(4).text());// 有效期至
							
							qygsGqbgGqbgInfos.add(gygsGqbgGqbgInfo);
						}
					}
				}
				
				qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);
			}
			
		}

		// 企业公示信息->行政许可信息
		EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
		if(null != htmlMap.get("qygsxx_xzxkxx") && !StringUtils.isEmpty(htmlMap.get(
				"qygsxx_xzxkxx").toString())){
			Document qygsxx_xzxkxx_page = Jsoup.parseBodyFragment(htmlMap.get(
					"qygsxx_xzxkxx").toString());
			// 企业公示信息->行政许可信息列表
			List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
			Element xingzhengxuke_tb = qygsxx_xzxkxx_page.getElementById("xzxkTable");
			if (null !=xingzhengxuke_tb) {
				Elements xingzhengxuke_trs=xingzhengxuke_tb.select("tbody").get(0)
						.select("tr");				
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
			}
			
		}
		qygsInfo.setAdmLicInfo(qygsXzxkInfo);// 企业公示信息->行政许可信息

		// 企业公示信息->知识产权出质登记信息
		EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
		if (null != htmlMap.get("qygsxx_zscqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("qygsxx_zscqczdjxx")))) {
			Document qygsxx_zscqczdjxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_zscqczdjxx")));
			// 企业公示信息->知识产权出质登记信息列表
			List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();					
		
			Element zhishichanquan_tbs = qygsxx_zscqczdjxx_page.getElementById("zscqTable");			
			if (null !=zhishichanquan_tbs) {
				Elements zhishichanquan_trs=zhishichanquan_tbs.select("tbody").get(0).select("tr");
				int zhishichanquan_tr_size = zhishichanquan_trs.size();			
				if (zhishichanquan_tr_size>2) {				
					for (int i = 2; i < zhishichanquan_tr_size; i++) {
						// 企业公示信息->知识产权出质登记信息
						EntpubIIntellectualproregInfo qygsZscqczdjZscqczdjInfo = new EntpubIIntellectualproregInfo();
						qygsZscqczdjZscqczdjInfo.setRegNum(zhishichanquan_trs.get(i).select("td").get(0).text());// 注册号
						qygsZscqczdjZscqczdjInfo.setName(zhishichanquan_trs.get(i).select("td").get(1).text());// 名称
						qygsZscqczdjZscqczdjInfo.setType(zhishichanquan_trs.get(i).select("td").get(2).text());// 种类
						qygsZscqczdjZscqczdjInfo.setMortgagorName(zhishichanquan_trs.get(i).select("td").get(3).text());// 出质人名称
						qygsZscqczdjZscqczdjInfo.setMortgageeName(zhishichanquan_trs.get(i).select("td").get(4).text());// 质权人名称
						qygsZscqczdjZscqczdjInfo.setPledgeRegDeadline(zhishichanquan_trs.get(i).select("td").get(5).text());// 质权登记期限
						qygsZscqczdjZscqczdjInfo.setChangeSitu(zhishichanquan_trs.get(i).select("td").get(6).text());// 变化情况
						qygsZscqczdjZscqczdjInfo.setStatus(zhishichanquan_trs.get(i).select("td").get(7).text());// 状态（暂无）
						qygsZscqczdjZscqczdjInfos.add(qygsZscqczdjZscqczdjInfo);
					}			
					qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);// 企业公示信息->知识产权出质登记信息
				}else{					
					if (isDebug) {
						qygsZscqczdjInfo.setHtml(zhishichanquan_trs.html());
					}			
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
			// 企业公示信息->行政处罚信息列表
			List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();		
			Element xingzhengchufa_et = qygsxx_xzcfxx_page.getElementById("xzcfTable");
			Elements xingzhengchufa_trs=xingzhengchufa_et.select("tbody").get(0).select("tr");
			if (xingzhengchufa_trs.size()>2) {
				for (int i = 2; i < xingzhengchufa_trs.size(); i++) {
					// 企业公示信息->行政处罚信息
					EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();	
					qygsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs.get(i).select("td").get(0).text());// 行政处罚决定书文号
					qygsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs.get(i).select("td").get(2).text());// 行政处罚内容
					qygsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs.get(i).select("td").get(2).text());// 作出行政处罚决定机关名称
					qygsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs.get(i).select("td").get(3).text());// 作出行政处罚决定日期
					qygsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs.get(i).select("td").get(4).text());// 违法行为类型（暂无）
					qygsXzcfXzcfInfo.setNote(xingzhengchufa_trs.get(i).select("td").get(5).text());// 备注（暂无）
					qygsXzcfXzcfInfos.add(qygsXzcfXzcfInfo);
				}
				qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);
			}else{
				if (isDebug) {
					qygsXzcfInfo.setHtml(xingzhengchufa_et.html());
				}				
			}
			
		}
		qygsInfo.setAdmPunishInfo(qygsXzcfInfo);// 企业公示信息->行政处罚信息

		// 企业公示信息
		gsxtFeedJson.setEntPubInfo(qygsInfo);

		// 其他部门公示信息
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();

		// 其他部门公示信息->行政许可信息
		OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
		if (null != htmlMap.get("qtbmgsxx_xzxk")
				&& !StringUtils.isEmpty(htmlMap.get("qtbmgsxx_xzxk")
						.toString())) {
			Document qtbmgsxx_xzxkxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qtbmgsxx_xzxk")));
			// 其他部门公示信息->行政许可信息列表
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			// 其他部门公示信息->行政许可信息列表
			String  xingzhengxuke_str = qtbmgsxx_xzxkxx_page.select("body").text();		
			String successed=new JsonParser().parse(xingzhengxuke_str).getAsJsonObject().get("successed").getAsString();
			if ("true".equals(successed)) {
				JsonArray jsonArray=new JsonParser().parse(xingzhengxuke_str).getAsJsonObject().get("data").getAsJsonArray();
				for (int i = 0; i < jsonArray.size(); i++) {					
					JsonObject dataObject = jsonArray.get(i).getAsJsonObject(); 
					
					OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo = new OthrdeptpubAAdmlicInfo();
					if (null != dataObject.get("xkwjbh") && !dataObject.get("xkwjbh").isJsonNull()) {
						String licenceNum = dataObject.get("xkwjbh").getAsString();
						qtbmgsXzxkXzxkInfo.setLicenceNum(licenceNum);// 许可文件编号
					}else{
						qtbmgsXzxkXzxkInfo.setLicenceNum("");// 许可文件编号
					}
					
					if (dataObject.has("xkwjmc") && !dataObject.get("xkwjmc").isJsonNull()) {
						String licenceName = dataObject.get("xkwjmc").getAsString(); 
						qtbmgsXzxkXzxkInfo.setLicenceName(licenceName);// 许可文件名称
					}else{
						qtbmgsXzxkXzxkInfo.setLicenceName("");// 许可文件名称
					}
					
					if (dataObject.has("yxq1") && !dataObject.get("yxq1").isJsonNull()) {
						String startDateTime = dataObject.get("yxq1").getAsString();
						qtbmgsXzxkXzxkInfo.setStartDateTime(startDateTime);// 有效期自
					}else{
						qtbmgsXzxkXzxkInfo.setStartDateTime("");// 有效期自
					}
		
	
				   if (dataObject.has("yxq2") && !dataObject.get("yxq2").isJsonNull()) {
						String endDateTime = dataObject.get("yxq2").getAsString();
						qtbmgsXzxkXzxkInfo.setEndDateTime(endDateTime);// 有效期至
				   }else{
						qtbmgsXzxkXzxkInfo.setEndDateTime("");// 有效期至
				   }
					
				   if (dataObject.has("yxq") && !dataObject.get("yxq").isJsonNull()) {
						String expiryDate = dataObject.get("yxq").getAsString();
						qtbmgsXzxkXzxkInfo.setExpiryDate(expiryDate);// 有效期       
			     	}else{
			     		qtbmgsXzxkXzxkInfo.setExpiryDate("");// 有效期       
			     	}
				   
				   if (dataObject.has("xkjg") && !dataObject.get("xkjg").isJsonNull()) {
					   String deciAuthority = dataObject.get("xkjg").getAsString();
					   qtbmgsXzxkXzxkInfo.setDeciAuthority(deciAuthority);// 许可机关
			     	}else{
			     		 qtbmgsXzxkXzxkInfo.setDeciAuthority("");// 许可机关
			     	}
				
				   if (dataObject.has("xknr") && !dataObject.get("xknr").isJsonNull()) {
					   String content = dataObject.get("xknr").getAsString(); 
					   qtbmgsXzxkXzxkInfo.setContent(content);// 许可内容
				    }else{
				       qtbmgsXzxkXzxkInfo.setContent("");// 许可内容
				    }
					
				   if (dataObject.has("zt") && !dataObject.get("zt").isJsonNull()) {
						String status = dataObject.get("zt").getAsString(); 
						qtbmgsXzxkXzxkInfo.setStatus(status);// 状态									
				     }else{
				    	qtbmgsXzxkXzxkInfo.setStatus("");// 状态		
				     }
					qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
				}
				qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);
			}
		
			qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);// 其他部门公示信息->行政许可信息
		}
		

		// 其他部门公示信息->行政处罚信息
		OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
		if (null != htmlMap.get("qtbmgsxx_xzcf")
				&& !StringUtils.isEmpty(htmlMap.get("qtbmgsxx_xzcf")
						.toString())) {
			Document qtbmgsxx_xzcfxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qtbmgsxx_xzcf")));
			// 其他部门公示信息->行政处罚信息列表
	
			List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();				
			String  xingzhengchufa_str = qtbmgsxx_xzcfxx_page.select("body").text();		
			String successed=new JsonParser().parse(xingzhengchufa_str).getAsJsonObject().get("successed").getAsString();
			if ("true".equals(successed)) {
				JsonArray jsonArray=new JsonParser().parse(xingzhengchufa_str).getAsJsonObject().get("data").getAsJsonArray();
				for (int i = 0; i < jsonArray.size(); i++) {
					JsonObject dataObject = jsonArray.get(i).getAsJsonObject(); 					
					OthrdeptpubAAdmpunishInfo qtbmgsXzcfXzcfInfo = new OthrdeptpubAAdmpunishInfo();					
					   if (dataObject.has("cfjdsh") && !dataObject.get("cfjdsh").isJsonNull()) {
							String punishRepNum = dataObject.get("cfjdsh").getAsString();
							qtbmgsXzcfXzcfInfo.setPunishRepNum(punishRepNum);// 行政处罚决定书文号
				     	}else{
				     		qtbmgsXzcfXzcfInfo.setPunishRepNum("");// 行政处罚决定书文号
				     	}
				
					   if (dataObject.has("wfxwlx") && !dataObject.get("wfxwlx").isJsonNull()) {
							String illegalActType=dataObject.get("wfxwlx").getAsString();
							qtbmgsXzcfXzcfInfo.setIllegalActType(illegalActType);// 违法行为类型
				     	}else{
				     		qtbmgsXzcfXzcfInfo.setIllegalActType("");// 违法行为类型
				     	}
				   
					   if (dataObject.has("xzcfnr") && !dataObject.get("xzcfnr").isJsonNull()) {
							String punishContent=dataObject.get("xzcfnr").getAsString();
							qtbmgsXzcfXzcfInfo.setPunishContent(punishContent);// 行政处罚内容
				     	}else{
				     		qtbmgsXzcfXzcfInfo.setPunishContent("");// 行政处罚内容
				     	}
				   
					   if (dataObject.has("cfjg") && !dataObject.get("cfjg").isJsonNull()) {
							String deciAuthority=dataObject.get("cfjg").getAsString();
							qtbmgsXzcfXzcfInfo.setDeciAuthority(deciAuthority);// 作出行政处罚决定机关名称
				     	}else{
				     		qtbmgsXzcfXzcfInfo.setDeciAuthority("");// 作出行政处罚决定机关名称
				     	}
			
					    if (dataObject.has("cfrq") && !dataObject.get("cfrq").isJsonNull()) {
							String deciDateTime=dataObject.get("cfrq").getAsString();
							qtbmgsXzcfXzcfInfo.setDeciDateTime(deciDateTime);// 作出行政处罚决定日期
				     	}else{
				     		qtbmgsXzcfXzcfInfo.setDeciDateTime("");// 作出行政处罚决定日期
				     	}
				
					    
					qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
				}
				qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);// 其他部门公示信息->行政处罚信息列表
			}
			
			qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);// 其他部门公示信息->行政处罚信息
		
		}

		// 其他部门公示信息
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);

		// 司法协助公示信息
		JudasspubInfo sfxzgsInfo = new JudasspubInfo();
		// 司法协助公示信息->股权冻结信息
		JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
		if (null != htmlMap.get("sfxzgsxx_gqdjxx_gqbgxx")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx_gqdjxx_gqbgxx")
						.toString())) {
			Document sfxzgsxx_gqdjxx_list_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx_gqdjxx_gqbgxx")));
			// 司法协助公示信息->股权冻结信息列表
			List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
			// 司法协助公示信息->股权冻结信息					
			Element guquandongjie_et = sfxzgsxx_gqdjxx_list_page.getElementById("guquandongjie");
			if (null != guquandongjie_et) {				
				Element guquandongjie_table=guquandongjie_et.getElementById("gqdjTable");				
				if (null != guquandongjie_table) {
					Elements guquandongjie_trs =guquandongjie_table.select("tbody").select("tr");
					if (guquandongjie_trs.size()>2) {
						for (int i = 2; i < guquandongjie_trs.size(); i++) {					
							JudasspubEEqufreezeInfo sfxzgsGqdjGqdjInfo = new JudasspubEEqufreezeInfo();
							sfxzgsGqdjGqdjInfo.setExecutedPerson(guquandongjie_trs.get(i).select("td").get(0).text());// 被执行人
							sfxzgsGqdjGqdjInfo.setEquAmount(guquandongjie_trs.get(i).select("td").get(0).text());// 股权数额
							sfxzgsGqdjGqdjInfo.setExeCourt(guquandongjie_trs.get(i).select("td").get(0).text());// 执行法院
							sfxzgsGqdjGqdjInfo.setAssistPubNoticeNum(guquandongjie_trs.get(i).select("td").get(0).text());// 协助公示通知书文号
							sfxzgsGqdjGqdjInfo.setStatus(guquandongjie_trs.get(i).select("td").get(0).text());// 状态
							sfxzgsGqdjGqdjInfo.setDetail(guquandongjie_trs.get(i).select("td").get(0).text());// 详情
							sfxzgsGqdjGqdjInfos.add(sfxzgsGqdjGqdjInfo);
						}
						sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);// 司法协助公示信息->股权冻结信息列表
					}else{
						if (isDebug) {
							sfxzgsGqdjInfo.setHtml(guquandongjie_et.html());	
						}					
					}
				}
				sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);// 司法协助公示信息->股权冻结信息
			}
	
		}		
	

		// 司法协助公示信息->股东变更信息
		JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
		if (null != htmlMap.get("sfxzgsxx_gqdjxx_gqbgxx")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx_gqdjxx_gqbgxx")
						.toString())) {
			Document sfxzgsxx_gqbgxx_list_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx_gqdjxx_gqbgxx")));
			// 司法协助公示信息->股东变更信息列表
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			// 司法协助公示信息->股东变更信息
			Element guquanbiangeng_et = sfxzgsxx_gqbgxx_list_page.getElementById("gudongbiangeng");
			if (null != guquanbiangeng_et) {
				Element guquanbiangeng_table = guquanbiangeng_et
						.getElementById("gdbgTable");
				if (null != guquanbiangeng_table) {
					Elements guquanbiangeng_trs = guquanbiangeng_table.select(
							"tbody").select("tr");

					if (guquanbiangeng_trs.size() > 2) {
						for (int i = 2; i < guquanbiangeng_trs.size(); i++) {
							JudasspubSStohrchangeInfo sfxzgsGdbgGdbgInfo = new JudasspubSStohrchangeInfo();
							sfxzgsGdbgGdbgInfo
									.setExecutedPerson(guquanbiangeng_trs
											.get(i).select("td").get(0).text());// 被执行人
							sfxzgsGdbgGdbgInfo.setEquAmount(guquanbiangeng_trs
									.get(i).select("td").get(1).text());// 股权数额
							sfxzgsGdbgGdbgInfo.setAssignee(guquanbiangeng_trs
									.get(i).select("td").get(2).text());// 受让人
							sfxzgsGdbgGdbgInfo.setExeCourt(guquanbiangeng_trs
									.get(i).select("td").get(3).text());// 执行法院
							sfxzgsGdbgGdbgInfo.setDetail(guquanbiangeng_trs
									.get(i).select("td").get(4).text());// 详情
							sfxzgsGdbgGdbgInfos.add(sfxzgsGdbgGdbgInfo);
						}
						
					} else {
						if (isDebug) {
							sfxzgsGdbgInfo.setHtml(guquanbiangeng_et.html());
						}
					}
					sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);
				}
			}
			
		}
		sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);// 司法协助公示信息->股东变更信息列表
			
		// 司法协助公示信息
		gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);
		return gsxtFeedJson;

	}

}

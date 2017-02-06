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
import com.crawler.gsxt.domain.json.EntpubAAdmlicInfo.Detail;
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
public class GsxtXinjiangParser extends AbstractGsxtParser {

private static final Log LOOGER = LogFactory.getLog(GsxtXinjiangParser.class);

	public AicFeedJson xinjiangResultParser(String html,Boolean isDebug)  {
		LOOGER.info("The method of GsxtSichuanParser.sichuanResultParser is begin !");
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
		Element baseinfo_jibenxinxi = gsgsxx_djxx.getElementById("jibenxinxi");
		if (baseinfo_jibenxinxi !=null ) {
			Element baseinfo=baseinfo_jibenxinxi.select("table").get(0);
			Elements ths = baseinfo.select("tr").select("th");
			AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();// 工商公示信息->登记信息->基本信息
			for (int i = 1; i < ths.size(); i++) {
				if (ths.get(i).text().trim().contains("统一社会信用代码")
						|| ths.get(i).text().trim().contains("注册号")) {
					gsgsDjJbInfo.setNum(baseinfo.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 注册号或信用代码
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("名称")) {
					gsgsDjJbInfo.setName(baseinfo.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 名称
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("类型")) {
					gsgsDjJbInfo.setType(baseinfo.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 类型
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("负责人")
						|| ths.get(i).text().trim().equalsIgnoreCase("法定代表人")
						|| ths.get(i).text().trim().contains("经营者") || ths.get(i).text().trim().contains("投资人")) {
					gsgsDjJbInfo.setLegalRepr(baseinfo.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 法定代表人/经营者
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("注册资本") || ths.get(i).text().trim().equalsIgnoreCase("注册资金") ) {
					gsgsDjJbInfo.setRegCapital(baseinfo.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 注册资本
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("成立日期") || ths.get(i).text().trim().equalsIgnoreCase("注册日期")) {
					gsgsDjJbInfo.setRegDateTime(baseinfo.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 成立日期
				}
				if (ths.get(i).text().trim().contains("营业场所")
						|| ths.get(i).text().trim().contains("住所") || ths.get(i).text().trim().contains("经营场所")) {
					gsgsDjJbInfo.setAddress(baseinfo.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 经营场所/住所
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("营业期限自") || ths.get(i).text().trim().equalsIgnoreCase("经营期限自")) {
					gsgsDjJbInfo.setStartDateTime(baseinfo.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 营业期限自（即营业开始日期）
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("营业期限至") || ths.get(i).text().trim().equalsIgnoreCase("经营期限至")) {
					gsgsDjJbInfo.setEndDateTime(baseinfo.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 营业期限至（即营业结束日期）
				}
				if (ths.get(i).text().trim().contains("经营范围")) {
					gsgsDjJbInfo.setBusinessScope(baseinfo.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 经营范围
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("登记机关")) {
					gsgsDjJbInfo.setRegAuthority(baseinfo.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 登记机关
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("核准日期")) {
					gsgsDjJbInfo.setApprovalDateTime(baseinfo.select("tbody")
							.select("tr").select("td").get(i - 1).text());// 核准日期
				}
				if (ths.get(i).text().trim().equalsIgnoreCase("组成形式")) {
					gsgsDjJbInfo.setFormType(baseinfo.select("tbody")
							.select("tr").select("td").get(i - 1).text());//组成形式
				}
				if (ths.get(i).text().trim().contains("登记状态")) {
					gsgsDjJbInfo.setRegStatus(baseinfo.select("tbody").select("tr")
							.select("td").get(i - 1).text());// 登记状态
				}
			}
			
			gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);// 基本信息
		}	
	
		// 工商公示信息->登记信息->股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
		Element touziren_table = gsgsxx_djxx.getElementById("table_fr");			
		if (null != touziren_table) {
			Elements touziren_trs = touziren_table.select("tr");
			int touziren_nums = touziren_trs.size();
			Element touziren_page=touziren_table.getElementById("scroll_div");
			String table_name=touziren_table.select("th").get(0).text().trim();
			if (table_name.contains("股东信息")) {
				if (null != touziren_page) {
					if (touziren_nums > 3) {
						for (int i = 2; i < touziren_nums-1; i++) {
							// 股东信息
							AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();
							gsgsDjGdInfo.setType(touziren_trs.get(i).select("td")
									.get(3).text());// 股东类型
							gsgsDjGdInfo.setName(touziren_trs.get(i).select("td")
									.get(0).text());// 股东名称
							gsgsDjGdInfo.setIdType(touziren_trs.get(i).select("td")
									.get(1).text());// 证照/证件类型
							gsgsDjGdInfo.setIdNum(touziren_trs.get(i).select("td")
									.get(2).text());// 证照/证件号码
							gsgsDjGdInfos.add(gsgsDjGdInfo);
						}
					}
				}else{
					if (touziren_nums > 3) {
						for (int i = 2; i < touziren_nums; i++) {
							// 股东信息
							AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();
							gsgsDjGdInfo.setType(touziren_trs.get(i).select("td")
									.get(3).text());// 股东类型
							gsgsDjGdInfo.setName(touziren_trs.get(i).select("td")
									.get(0).text());// 股东名称
							gsgsDjGdInfo.setIdType(touziren_trs.get(i).select("td")
									.get(1).text());// 证照/证件类型
							gsgsDjGdInfo.setIdNum(touziren_trs.get(i).select("td")
									.get(2).text());// 证照/证件号码
							gsgsDjGdInfos.add(gsgsDjGdInfo);
						}
					}
				}
			} else if (table_name.contains("股东信息") || table_name.contains("投资人信息")) {
				if (null != touziren_page) {
					if (touziren_nums > 3) {
						for (int i = 2; i < touziren_nums - 1; i++) {
							// 股东信息
							AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();

							gsgsDjGdInfo.setName(touziren_trs.get(i)
									.select("td").get(0).text());// 姓名
							gsgsDjGdInfo.setSconform(touziren_trs.get(i)
									.select("td").get(1).text());// 出资方式
							gsgsDjGdInfos.add(gsgsDjGdInfo);
						}
					}
				} else {
					if (touziren_nums > 3) {
						for (int i = 2; i < touziren_nums; i++) {
							// 股东信息
							AicpubRegStohrInfo gsgsDjGdInfo = new AicpubRegStohrInfo();

							gsgsDjGdInfo.setName(touziren_trs.get(i)
									.select("td").get(0).text());// 姓名
							gsgsDjGdInfo.setSconform(touziren_trs.get(i)
									.select("td").get(1).text());// 出资方式
							gsgsDjGdInfos.add(gsgsDjGdInfo);
						}
					}
				}

			}		
			
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> gsgsxx_djxx_tzrxx_xqs = (List<Map<String, Object>>) htmlMap
				.get("gsgsxx_djxx_tzrxx_xqs");
		int gsgsxx_djxx_tzrxx_xqs_size = gsgsxx_djxx_tzrxx_xqs.size();
		List<AicpubRegStohrStohrinvestInfo> gsgsDjGdGdjczInfos = new ArrayList<AicpubRegStohrStohrinvestInfo>();
		if (gsgsxx_djxx_tzrxx_xqs_size > 0) {
			for (Map<String, Object> gsgsxx_djxx_tzrxx_xq : gsgsxx_djxx_tzrxx_xqs) {
				Document gsgsxx_djxx_tzrxx_xq_page = Jsoup
						.parseBodyFragment(gsgsxx_djxx_tzrxx_xq.get(
								"gsgsxx_djxx_tzrxx_xq").toString());
				Element gsgsxx_djxx_tzrxx_tb = gsgsxx_djxx_tzrxx_xq_page.getElementById("sifapanding");						
				if (null != gsgsxx_djxx_tzrxx_tb) {
					Elements gsgsxx_djxx_tzrxx_xq_trs=gsgsxx_djxx_tzrxx_tb.select("tbody").select("tr");					
					int gsgsxx_djxx_tzrxx_xq_trs_size = gsgsxx_djxx_tzrxx_xq_trs.size();
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
							Elements lis_3=gsgsxx_djxx_tzrxx_xq_trs
									.get(i).select("td").get(3).select("li");										
								for (int j = 0; j < lis_3.size(); j++) {				
									AicpubRegStohrStohrinvestInfo.AmountDetail subAmountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
									subAmountDetail.setInvestMethod(gsgsxx_djxx_tzrxx_xq_trs.get(i).select("td").get(3).select("li").get(j).text());
									subAmountDetail.setInvestAmount(gsgsxx_djxx_tzrxx_xq_trs.get(i).select("td").get(4).select("li").get(j).text());
									subAmountDetail.setInvestDateTime(gsgsxx_djxx_tzrxx_xq_trs.get(i).select("td").get(5).select("li").get(j).text());
									subAmountDetails.add(subAmountDetail);
								}
								
								Elements lis_6=gsgsxx_djxx_tzrxx_xq_trs.get(i).select("td").get(6).select("li");	
									for (int j = 0; j < lis_6.size(); j++) {
										AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdGdjczInfo.new AmountDetail();
										paidAmountDetail.investMethod=gsgsxx_djxx_tzrxx_xq_trs.get(i).select("td").get(6).select("li").get(j).text();	
										paidAmountDetail.investAmount=gsgsxx_djxx_tzrxx_xq_trs.get(i).select("td").get(7).select("li").get(j).text();
										paidAmountDetail.investDateTime=gsgsxx_djxx_tzrxx_xq_trs.get(i).select("td").get(8).select("li").get(j).text();	
										paidAmountDetails.add(paidAmountDetail);
									}
															
						}
				
						gsgsDjGdGdjczInfo.setSubAmountDetails(subAmountDetails);
						gsgsDjGdGdjczInfo.setPaidAmountDetails(paidAmountDetails);		
						gsgsDjGdGdjczInfos.add(gsgsDjGdGdjczInfo);
					}
				}
				
			}
		}
		// 股东信息
		List<AicpubRegStohrInfo> gsgsDjGdInfos2 = new ArrayList<AicpubRegStohrInfo>();
		for (AicpubRegStohrInfo gsgsDjGdInfo : gsgsDjGdInfos) {
			for (AicpubRegStohrStohrinvestInfo gsgsDjGdGdjczInfo : gsgsDjGdGdjczInfos) {
				if (gsgsDjGdInfo.getName().trim()
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
		Element biangeng_table = gsgsxx_djxx.getElementById("table_bg");			
		if (null != biangeng_table) {			
			Elements biangeng_trs = biangeng_table.select("tbody").select("tr");
			int biangeng_trs_size = biangeng_trs.size();
			if (biangeng_trs_size > 2) {
				for (int i = 2; i < biangeng_trs_size-1; i++) {
					AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();// 变更信息
					gsgsDjBgInfo.setItem(biangeng_trs.get(i).select("td").get(0).text());// 变更事项
					   String bgqnr=biangeng_trs.get(i).select("td").get(1).text();
					   if(bgqnr!=null&&!"".equals(bgqnr)){
						   bgqnr= bgqnr.replaceAll("收起更多", "");
					   }
						gsgsDjBgInfo.setPreContent(bgqnr);// 变更前内容					
						String bghnr=biangeng_trs.get(i).select("td").get(2).text();
						 if(bghnr!=null&&!"".equals(bghnr)){
							 bghnr= bghnr.replaceAll("收起更多", "");
						   }
						gsgsDjBgInfo.setPostContent(bghnr);// 变更后内容
				
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
		List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos=null;
		List<AicpubArchiveMainDeptInfo> aicpubArchiveMainDeptInfos=null;     
		if (null != htmlMap.get("gsgsxx_baxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_baxx").toString())) {
			Document gsgsxx_baxx = Jsoup.parseBodyFragment(htmlMap.get(
					"gsgsxx_baxx").toString());					
			Element beian_zyryxx_fr = gsgsxx_baxx.getElementById("table_fr");					
			if (null != beian_zyryxx_fr) {
				Elements beian_zyryxx_trs=beian_zyryxx_fr.select("tr");
				int beian_zyryxx_size=beian_zyryxx_trs.size();
				String table_name=beian_zyryxx_fr.select("tbody").select("th").get(0).text();				
				if (table_name.contains("主管部门（出资人）信息")) {
					aicpubArchiveMainDeptInfos= new ArrayList<AicpubArchiveMainDeptInfo>();
					if (beian_zyryxx_size > 2) {
						for (int i = 2; i < beian_zyryxx_size-1; i++) {
							Elements beian_zyryxx_tds=beian_zyryxx_trs.get(i).select("td");
							String type = beian_zyryxx_tds.get(1).text();
							String name = beian_zyryxx_tds.get(2).text();
							String idType = beian_zyryxx_tds.get(3).text();
							String idNum = beian_zyryxx_tds.get(4).text();							
							
							AicpubArchiveMainDeptInfo aicpubArchiveMainDeptInfo = new AicpubArchiveMainDeptInfo();
							aicpubArchiveMainDeptInfo.setType(type);
							aicpubArchiveMainDeptInfo.setName(name);
							aicpubArchiveMainDeptInfo.setIdType(idType);
							aicpubArchiveMainDeptInfo.setIdNum(idNum);
							aicpubArchiveMainDeptInfos.add(aicpubArchiveMainDeptInfo);			
					
						}
					}
					
				} 
				gsgsBaInfo.setMainDeptInfo(aicpubArchiveMainDeptInfos);
			}
				
			
			Element beian_zyryxx = gsgsxx_baxx.getElementById("table_ry1");		
			if (null !=beian_zyryxx) {				
				// 工商公示信息->备案信息->主要人员信息
				Elements beian_zyryxx_trs=beian_zyryxx.select("tr");	           
				int beian_zyryxx_size = beian_zyryxx_trs.size();				
				String table_name=beian_zyryxx.select("tbody").select("th").get(0).text();				
			    if (table_name.contains("主要人员信息")) {
					gsgsBaZyryInfos= new ArrayList<AicpubArchivePrimemberInfo>();
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
				}								
				gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);// 主要人员信息
			}
			
			
		
			// 工商公示信息->备案信息->分支机构信息
			List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
			Element beian_fzjgxx = gsgsxx_baxx.getElementById("table_fr2");			
			if (null !=beian_fzjgxx) {
				Elements beian_fzjgxx_trs=beian_fzjgxx.select("tr");
				int beian_fzjgxx_size = beian_fzjgxx_trs.size();
				if (beian_fzjgxx_size > 3) {
					for (int i = 2; i < beian_fzjgxx_size-1; i++) {
						// 分支机构信息
						AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
						gsgsBaFzjgInfo.setNum(beian_fzjgxx_trs.get(i).select("td").get(1).text());// 统一社会信用代码/注册号
						gsgsBaFzjgInfo.setName(beian_fzjgxx_trs.get(i).select("td").get(2).text());// 名称
						gsgsBaFzjgInfo.setRegAuthority(beian_fzjgxx_trs.get(i).select("td").get(3).text());// 登记机关
						gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
					}
				}
				gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);// 分支机构信息
			}
			
			
			// 工商公示信息->备案信息->清算信息
			AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
			Element beian_qingsuanrenyuan = gsgsxx_baxx.getElementById("t32");
			if (null != beian_qingsuanrenyuan) {
				Elements elementsByAttributeValue = beian_qingsuanrenyuan.getElementsByAttributeValue("colspan","4");
				gsgsBaQsInfo.setLeader(elementsByAttributeValue.get(0).text());// 清算组负责人
				List<String> a=new ArrayList<String>();
				a.add(elementsByAttributeValue.get(1).text());
				gsgsBaQsInfo.setMembers(a);// 清算组成员
				gsgsBaInfo.setClearInfo(gsgsBaQsInfo);// 清算信息
				if (isDebug) {
					gsgsBaQsInfo.setHtml(beian_qingsuanrenyuan.toString());
				}			
			}
		
			gsgsBaInfo.setClearInfo(gsgsBaQsInfo);
			gsgsInfo.setArchiveInfo(gsgsBaInfo);// 工商公示信息->备案信息
		}

		
		// 工商公示信息->动产抵押登记信息
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		if (null != htmlMap.get("gsgsxx_dcdydjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_dcdydjxx")))) {
			Document gsgsxx_dcdydjxx_dcdydjxx_page = Jsoup
					.parseBodyFragment(String.valueOf(htmlMap
							.get("gsgsxx_dcdydjxx")));
			// 工商公示信息->动产抵押登记信息列表
			List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
			Element dongchandiya_et = gsgsxx_dcdydjxx_dcdydjxx_page.getElementById("dongchandiya");			
			if (null !=dongchandiya_et) {   				
				Element scroll_begin=dongchandiya_et.getElementById("scroll_begin");
				Elements dongchandiya_trs = dongchandiya_et.select("tbody").select("tr");
				int dongchandiya_trs_size=dongchandiya_trs.size();				
				if (null != scroll_begin) {					
					if (dongchandiya_trs_size>3) {
						for (int i = 2; i < dongchandiya_trs_size-1; i++) {
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
					}					
				}else{					
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
					}
				}

			}		
			gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);// 动产抵押登记信息
			gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);// 工商公示信息->动产抵押登记信息
		}

		
		// 工商公示信息->股权出资登记信息
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		if (null != htmlMap.get("gsgsxx_gqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("gsgsxx_gqczdjxx")))) {
			Document gsgsxx_gqczdjxx_gqczdjxx_page = Jsoup
					.parseBodyFragment(htmlMap.get("gsgsxx_gqczdjxx")
							.toString());
			// 工商公示信息->股权出资登记信息列表
			List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();			
			// 工商公示信息->股权出资登记信息
			Element guquanchuzhi_et = gsgsxx_gqczdjxx_gqczdjxx_page.getElementById("guquanchuzhi");
			
			if (null != guquanchuzhi_et) {
				Elements guquanchuzhi_trs = guquanchuzhi_et.select("tbody").select("tr");
				int guquanchuzhi_trs_size=guquanchuzhi_trs.size();
				if (guquanchuzhi_trs_size>3) {
					for (int i = 2; i < guquanchuzhi_trs_size-1; i++) {					
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
				}else{
					if (isDebug) {
						gsgsGqczdjInfo.setHtml(guquanchuzhi_et.toString());
					}	
				}
			
			}
			gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);
			gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);// 工商公示信息->股权出资登记信息			
		}

		// 工商公示信息->行政处罚信息
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
		if (null != htmlMap.get("gsgsxx_xzcfxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_xzcfxx")
						.toString())) {
			Document gsgsxx_xzcfxx_xzcfxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_xzcfxx").toString());
			// 工商公示信息->行政处罚信息列表
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
			Element xingzhengchufa = gsgsxx_xzcfxx_xzcfxx.getElementById("gsgsxx_xzcf");
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
			List<Map<String, Object>> gsgsxx_xzcfxx_xzcfxx_xqs = (List<Map<String, Object>>) htmlMap.get("gsgsxx_xzcfxx_xqs");
			int gsgsxx_xzcfxx_xzcfxx_xqs_size = gsgsxx_xzcfxx_xzcfxx_xqs.size();
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos2 = new ArrayList<AicpubAAdmpunishInfo>();
			if (null != gsgsxx_xzcfxx_xzcfxx_xqs
					&& gsgsxx_xzcfxx_xzcfxx_xqs_size > 0) {
				for (Map<String, Object> gsgsxx_xzcfxx_xzcfxx_xq : gsgsxx_xzcfxx_xzcfxx_xqs) {
					Document gsgsxx_xzcfxx_xzcfxx_xq_page = Jsoup
							.parseBodyFragment(gsgsxx_xzcfxx_xzcfxx_xq.get(
									"gsgsxx_xzcfxx_xq").toString());
					AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();					
					Elements gsgsxx_xzcfxx_xzcfxx_xq_trs = gsgsxx_xzcfxx_xzcfxx_xq_page.getElementById("sifapanding").select("table").get(1).select("tr");
				
					AicpubAAdmpunishInfo.PunishDetail xzcfDetail = gsgsXzcfXzcfInfo.new PunishDetail();							
					xzcfDetail.punishRepNum = gsgsxx_xzcfxx_xzcfxx_xq_trs.select("td").get(0).text();// 行政处罚决定书文号
					xzcfDetail.name = gsgsxx_xzcfxx_xzcfxx_xq_trs.select("td").get(1).text();;// 名称
					xzcfDetail.regNum = gsgsxx_xzcfxx_xzcfxx_xq_trs.select("td").get(2).text();;// 注册号
					xzcfDetail.legalReprName = gsgsxx_xzcfxx_xzcfxx_xq_trs.select("td").get(3).text();;// 法定代表人（负责人）姓名
					xzcfDetail.illegalActType = gsgsxx_xzcfxx_xzcfxx_xq_trs.select("td").get(4).text();;// 违法行为类型
					xzcfDetail.punishContent = gsgsxx_xzcfxx_xzcfxx_xq_trs.select("td").get(5).text();;// 行政处罚内容
					xzcfDetail.deciAuthority = gsgsxx_xzcfxx_xzcfxx_xq_trs.select("td").get(6).text();;// 作出行政处罚决定机关名称
					xzcfDetail.deciDateTime = gsgsxx_xzcfxx_xzcfxx_xq_trs.select("td").get(7).text();;// 作出行政处罚决定日期
					
					Element ele_div_gscf1 = getfirstChildElement(gsgsxx_xzcfxx_xzcfxx_xq_page, "#sifapanding div.gscf1");
					if (ele_div_gscf1!=null) {
				     Elements select = gsgsxx_xzcfxx_xzcfxx_xq_page.getElementById("sifapanding").select("div.gscf1");
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
		if (null != htmlMap.get("gsgsxx_jyycxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_jyycxx")
						.toString())) {
			Document gsgsxx_jyycxx_jyycxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_jyycxx").toString());
			List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();// 经营异常信息列表
			Element yichangminglu = gsgsxx_jyycxx_jyycxx
					.getElementById("yichangminglu");
			Elements yichangminglu_trs = yichangminglu.select("tbody").get(0).select("tr");
			int yichangminglu_trs_size = yichangminglu_trs.size();
			if (yichangminglu_trs_size > 3) {
				for (int i = 2; i < yichangminglu_trs_size-1; i++) {
					Elements yichangminglu_tds= yichangminglu_trs.get(i).select("td");
					AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();// 经营异常信息
					gsgsJyycJyycInfo.setIncludeCause(yichangminglu_tds.get(1).text());// 列入经营异常名录原因				
					gsgsJyycJyycInfo.setIncludeDateTime(yichangminglu_tds.get(2).text());// 列入日期			
					gsgsJyycJyycInfo.setRemoveCause(yichangminglu_tds.get(3).text());// 移出经营异常名录原因					
					gsgsJyycJyycInfo.setRemoveDateTime(yichangminglu_tds.get(4).text());// 移出日期
					gsgsJyycJyycInfo.setAuthority(yichangminglu_tds.get(5).text());// 作出决定机关
					gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
				}
			}
			gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);// 经营异常信息列表
			gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);// 工商公示信息->经营异常信息
		}

		
		// 工商公示信息->严重违法信息
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		if (null != htmlMap.get("gsgsxx_yzwfxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_yzwfxx")
						.toString())) {
			Document gsgsxx_yzwfxx_yzwfxx_page = Jsoup
					.parseBodyFragment(htmlMap.get("gsgsxx_yzwfxx")
							.toString());
			// 工商公示信息->严重违法信息列表
			List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();			
			Element heimingdan_et = gsgsxx_yzwfxx_yzwfxx_page.getElementById("yanzhongweifa");			
			if (heimingdan_et!=null) {
				 gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
				Elements gsgsYzwfTrs = heimingdan_et.getElementById("table_wfxx").select("tr");
				int gsgsYzwfTrs_size=gsgsYzwfTrs.size();
				Elements gsgsYzwfTds = heimingdan_et.getElementById("table_wfxx").select("td");
				if (gsgsYzwfTrs_size>3) {
						for (int j = 2; j < gsgsYzwfTrs_size-1; j++) {							
							String lryzwfqymdCause = gsgsYzwfTds.get(1).text();
							String lrDate = gsgsYzwfTds.get(2).text();
							String ycyzwfqymdCause = gsgsYzwfTds.get(3).text();
							String ycDate = gsgsYzwfTds.get(4).text();
							String zcjdAuthority = gsgsYzwfTds.get(5).text();
							AicpubSSerillegalInfo gsgsYzwfYzwfInfo = new AicpubSSerillegalInfo();
							gsgsYzwfYzwfInfo.setIncludeCause(lryzwfqymdCause);
							gsgsYzwfYzwfInfo.setIncludeDateTime(lrDate);
							gsgsYzwfYzwfInfo.setRemoveCause(ycyzwfqymdCause);
							gsgsYzwfYzwfInfo.setRemoveDateTime(ycDate);
							gsgsYzwfYzwfInfo.setDeciAuthority(zcjdAuthority);
							gsgsYzwfYzwfInfos.add(gsgsYzwfYzwfInfo);							
						}												
					}else{
						if (isDebug) {
							gsgsYzwfInfo.setHtml(heimingdan_et.toString());
						}
					}

			}	
			
			gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);			
			gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);// 工商公示信息->严重违法信息
		}

		// 工商公示信息->抽查检查信息
		AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		if (null != htmlMap.get("gsgsxx_ccjcxx")
				&& !StringUtils.isEmpty(htmlMap.get("gsgsxx_ccjcxx")
						.toString())) {
			Document gsgsxx_ccjcxx_ccjcxx = Jsoup.parseBodyFragment(htmlMap
					.get("gsgsxx_ccjcxx").toString());
			// 工商公示信息->抽查检查信息列表
			List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
			Element chouchaxinxi_table = gsgsxx_ccjcxx_ccjcxx
					.getElementById("chouchaxinxi");		
			if (null != chouchaxinxi_table) {
				Elements chouchaxinxi_trs=chouchaxinxi_table.select("table").select("tr");
				int chouchaxinxi_trs_size = chouchaxinxi_trs.size();
				if (chouchaxinxi_trs_size > 3) {
					for (int i = 2; i < chouchaxinxi_trs_size-1; i++) {
						AicpubCCheckInfo gsgsCcjcCcjcInfo = new AicpubCCheckInfo();
						gsgsCcjcCcjcInfo.setCheckImplAuthority(chouchaxinxi_trs.get(i)
								.select("td").get(1).text());// 检查实施机关
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
			}
		
			gsgsInfo.setCheckInfo(gsgsCcjcInfo);// 工商公示信息->抽查检查信息
		}

		gsxtFeedJson.setAicPubInfo(gsgsInfo);// 工商公示信息

		// 企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();


		Document qygsxx_qynb = Jsoup.parseBodyFragment(htmlMap.get("qygsxx_qynb").toString());
//		System.out.println("#################################");
//		System.out.println(htmlMap.get("qygsxx_qynb").toString());
//		System.out.println("#################################");
//		Elements select = qygsxx_qynb.select("table");
//		Element table=select.get(0);
	
		
		// 企业公示信息->企业年报
		List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> qygsxx_qynb_infos = (List<Map<String, Object>>) htmlMap.get("qygsxx_qynb_infos");
		if (null != qygsxx_qynb_infos && qygsxx_qynb_infos.size() > 0) {
			for (Map<String, Object> qygsxx_qynb_info : qygsxx_qynb_infos) {
				Document qygsxx_qynb_info_page = Jsoup.parseBodyFragment(qygsxx_qynb_info.get("qygsxx_qynb_info_page").toString());
				EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();// 企业年报
				String submitYear= qygsxx_qynb_info.get("qygsxx_qynb_list_a_text").toString();
				qygsQynbInfo.setSubmitYear(submitYear);// 报送年度				
				Elements qygsxx_qynb_as = qygsxx_qynb.getElementsByTag("a");
				for (Element qygsxx_qynb_a : qygsxx_qynb_as) {
					if (qygsxx_qynb_a.text().contains(submitYear)) {
						qygsQynbInfo.setPubDateTime(qygsxx_qynb_a.parent().nextElementSibling().text());// 发布日期
						break;
					}
				}
				// 企业公示信息->企业年报->企业基本信息					
				Element qygsxx_qynb_info_tables_div=qygsxx_qynb_info_page.getElementById("sifapanding");	
				if (null != qygsxx_qynb_info_tables_div) {
					Elements qygsxx_qynb_info_tables=qygsxx_qynb_info_tables_div.select("table");				
					int  table_size=qygsxx_qynb_info_tables.size();
					for (int k = 0; k < table_size; k++) {
						  Element qygsxx_qynb_info_table=qygsxx_qynb_info_tables.get(k);
						  Elements qygsxx_qynb_info_ths = getElements(qygsxx_qynb_info_table, "th");	
						  Elements qygsxx_qynb_info_trs = getElements(qygsxx_qynb_info_table, "tr");	
						  Elements qygsxx_qynb_info_tds = getElements(qygsxx_qynb_info_table, "td");					  
						  if (k==0) {
								//企业年报信息--> 企业基本信息
								 EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
						         for (int i = 2; i <qygsxx_qynb_info_ths.size(); i++) {
									String th_name=qygsxx_qynb_info_ths.get(i).text().trim();	
									if (th_name.contains("注册号") || th_name.contains("统一社会信用代码")) {
										String num = qygsxx_qynb_info_tds.get(i-2).text().trim();									
										qygsQynbJbInfo.setNum(num);
									}
						        						        	 
									if (th_name.contains("企业名称")) {
										String name = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setName(name);
									}
									
									if (th_name.contains("企业联系电话")) {
										String tel = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setTel(tel);
									}
									if (th_name.contains("邮政编码")) {
										String zipCode = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setZipCode(zipCode);
									}
									
									if (th_name.contains("企业通信地址")) {
										String address = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setAddress(address);
									}
									if (th_name.contains("电子邮箱")) {
										String email = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setEmail(email);
									}
									
									if (th_name.contains("有限责任公司本年度是否发生股东股权转让")) {
										String isStohrEquTransferred = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setIsStohrEquTransferred(isStohrEquTransferred);
									}
									if (th_name.contains("企业经营状态")) {
										String operatingStatus = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setOperatingStatus(operatingStatus);
									}
									if (th_name.contains("是否有网站或网店")) {
										String hasWebsiteOrStore = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setHasWebsiteOrStore(hasWebsiteOrStore);
									}
									
									if (th_name.contains("企业是否有投资信息或购买其他公司股权")) {
										String hasInvestInfoOrPurchOtherCorpEqu = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setHasInvestInfoOrPurchOtherCorpEqu(hasInvestInfoOrPurchOtherCorpEqu);
									}
									
									if (th_name.contains("从业人数")) {
										String empNum = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setEmpNum(empNum);
									}		
									if (th_name.contains("隶属关系")) {
										String affiliation = qygsxx_qynb_info_tds.get(i-2).text().trim();
										qygsQynbJbInfo.setAffiliation(affiliation);
									}		
									  qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);
								}
						  }else{
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
									if(qynbscjyqkTds.size()>2){
										String netProfit = qynbscjyqkTds.get(2).text();
										manageInfo.setNetProfit(netProfit);
									}									
									manageInfo.setSaleSum(saleSum);
									manageInfo.setSalarySum(salarySum);									
									manageInfos.add(manageInfo);
									qygsQynbInfo.setManageInfos(manageInfos);
							}

						}
					}					
				}
			
				//企业年报信息--> 网站或网店信息
				List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
				Element wzwdxxTable = qygsxx_qynb_info_page.getElementById("table_wzxx");
				if (null !=wzwdxxTable) {
					Elements wzwdxxTrs = wzwdxxTable.select("tr");
					int wzwdxxTrs_size=wzwdxxTrs.size();										
						if (wzwdxxTrs_size>2) {
							for (int i = 2; i < wzwdxxTrs_size-1; i++) {					
								EntpubAnnreportWebsiteInfo qygsQynbWzhwdInfo=new EntpubAnnreportWebsiteInfo();
								qygsQynbWzhwdInfo.setType(wzwdxxTrs.get(i).select("td").get(0).text());
								qygsQynbWzhwdInfo.setName(wzwdxxTrs.get(i).select("td").get(1).text());
								qygsQynbWzhwdInfo.setWebsite(wzwdxxTrs.get(i).select("td").get(2).text());
								qygsQynbWzhwdInfos.add(qygsQynbWzhwdInfo);		
							}										
						}            
					qygsQynbInfo.setWebsiteInfos(qygsQynbWzhwdInfos);
				}			
				
				
				//企业年报信息--> 股东及出资信息
				Element chuziren = qygsxx_qynb_info_page.getElementById("table_tzrxx");
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();// 股东及出资信息
				if (null != chuziren) {
					Elements qyjbxx_gd_trs = chuziren.select("tbody").get(0).select("tr");
					int qyjbxx_gd_trs_size = qyjbxx_gd_trs.size();
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
									
					qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);// 企业公示信息->股东及出资信息
				}
				
				//企业年报信息--> 对外投资信息
				List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();// 对外投资信息
				Element qynbDwtzInfos = qygsxx_qynb_info_page.getElementById("table_tzxx");
				if (null != qynbDwtzInfos) {
					Elements dwtzxx_gd_trs = qynbDwtzInfos.select("tr");
					int dwtzxx_gd_trs_size = dwtzxx_gd_trs.size();								
						if (dwtzxx_gd_trs_size > 3) {
							for (int i = 2; i < dwtzxx_gd_trs_size-1; i++) {
								EntpubAnnreportExtinvestInfo qygsQynbDwtzInfo = new EntpubAnnreportExtinvestInfo();// 股东及出资信息							
								qygsQynbDwtzInfo.setEnterpriseName(dwtzxx_gd_trs
										.get(i).select("td").get(0).text());//投资设立企业或购买股权企业名称
								qygsQynbDwtzInfo.setRegNum(dwtzxx_gd_trs
										.get(i).select("td").get(1).text());// 股东（发起人）
								
								qygsQynbDwtzInfos.add(qygsQynbDwtzInfo);
							}
						}		
					qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzInfos);
				}	
				
				
				// 对外提供保证担保信息
				List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();				
				Element  dwtgdbxx_tb=qygsxx_qynb_info_page.getElementById("table_dbxx");				
				if (null != dwtgdbxx_tb ) {
					Elements dwtgdbxx_trs=dwtgdbxx_tb.select("tbody").select("tr");
					if (dwtgdbxx_trs.size()>3) {
						for (int j = 2; j < dwtgdbxx_trs.size()-1; j++) {
							EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
							qygsQynbDwtgbzdbInfo.setCreditor(dwtgdbxx_trs.get(j).select("td").get(0).text());
							qygsQynbDwtgbzdbInfo.setDebtor(dwtgdbxx_trs.get(j).select("td").get(1).text());
							
							qygsQynbDwtgbzdbInfo.setPriCredRightType(dwtgdbxx_trs.get(j).select("td").get(2).text());
							qygsQynbDwtgbzdbInfo.setPriCredRightAmount(dwtgdbxx_trs.get(j).select("td").get(3).text());
							qygsQynbDwtgbzdbInfo.setExeDebtDeadline(dwtgdbxx_trs.get(j).select("td").get(4).text());
							qygsQynbDwtgbzdbInfo.setGuaranteePeriod(dwtgdbxx_trs.get(j).select("td").get(5).text());
							qygsQynbDwtgbzdbInfo.setGuaranteeMethod(dwtgdbxx_trs.get(j).select("td").get(6).text());
							qygsQynbDwtgbzdbInfo.setGuaranteeScope(dwtgdbxx_trs.get(j).select("td").get(7).text());
							qygsQynbDwtgbzdbInfos.add(qygsQynbDwtgbzdbInfo);
						}
					}					
					qygsQynbInfo.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);// 对外提供保证担保信息
				}
				

				// 股权变更记录   
				List<EntpubAnnreportEquchangeInfo> qygsQynbGqbgInfos = new ArrayList<EntpubAnnreportEquchangeInfo>();				
				Element  gqbgxx_tb=qygsxx_qynb_info_page.getElementById("table_gqbg");
				if (null !=gqbgxx_tb) {
					Elements gqbgxx_trs=gqbgxx_tb.select("tbody").get(0).select("tr");
				
						if (gqbgxx_trs.size()>2) {					
							for (int i = 2; i < gqbgxx_trs.size()-1; i++) {
								EntpubAnnreportEquchangeInfo qygsQynbGqbgInfo = new EntpubAnnreportEquchangeInfo();
								
								qygsQynbGqbgInfo.setStockholder(gqbgxx_trs.get(i).select("td").get(0).text()); //股东（发起人）							
								qygsQynbGqbgInfo.setPreOwnershipRatio(gqbgxx_trs.get(i).select("td").get(1).text());//变更前股权比例
								qygsQynbGqbgInfo.setPostOwnershipRatio(gqbgxx_trs.get(i).select("td").get(2).text());	//变更后股权比例
								qygsQynbGqbgInfo.setDateTime(gqbgxx_trs.get(i).select("td").get(3).text());//股权变更日期							
								qygsQynbGqbgInfos.add(qygsQynbGqbgInfo);
							}
							
						}    		
	    			qygsQynbInfo.setEquChangeInfos(qygsQynbGqbgInfos);// 股权变更记录   
				}		

				// 修改记录
				List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();				
				Element  xgjlxx_tb=qygsxx_qynb_info_page.getElementById("table_bgxx");			
				if (null !=xgjlxx_tb) {
					Elements xgjlxx_trs=xgjlxx_tb.select("tbody").select("tr");
						if (xgjlxx_trs.size()>3) {					
							for (int i = 2; i < xgjlxx_trs.size()-1; i++) {
								EntpubAnnreportModifyInfo qygsQynbXgjlInfo = new EntpubAnnreportModifyInfo();														
								qygsQynbXgjlInfo.setItem(xgjlxx_trs.get(i).select("td").get(1).text());
								qygsQynbXgjlInfo.setPreContent(xgjlxx_trs.get(i).select("td").get(2).text());
								qygsQynbXgjlInfo.setPostContent(xgjlxx_trs.get(i).select("td").get(3).text());
								qygsQynbXgjlInfo.setDateTime(xgjlxx_trs.get(i).select("td").get(4).text());							
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
			if (null != qygsxx_gdjczxx) {			
				Element gdjczxx_tb = qygsxx_gdjczxx.getElementById("table_qytzr");					
				if (null != gdjczxx_tb) {					
					Elements gdjczxx_trs=gdjczxx_tb.select("tbody").select("tr");
					int gdjczxx_trs_size = gdjczxx_trs.size();
					Element gdjczxx_page=gdjczxx_tb.getElementById("scroll_div");				
					if (null != gdjczxx_page) {
						if (gdjczxx_trs_size > 2) {
							for (int j = 3; j < gdjczxx_trs_size-1; j += 2) {
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
								rjDetail.dateTime = gdjczxx_trs.get(j).select("td").get(6)
										.text();// 出资日期
								EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
								sjDetail.amount = gdjczxx_trs.get(j).select("td")
										.get(7).text();// 出资额（万元）
								sjDetail.method = gdjczxx_trs.get(j).select("td")
										.get(8).text();// 出资方式
								sjDetail.dateTime = gdjczxx_trs.get(j).select("td").get(9)
										.text();// 出资日期
								qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
							}
						}
						
					}else{
						if (gdjczxx_trs_size > 2) {
							for (int j = 3; j < gdjczxx_trs_size; j += 2) {
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
								rjDetail.dateTime = gdjczxx_trs.get(j).select("td").get(6)
										.text();// 出资日期
								EntpubSStohrinvestInfo.Detail sjDetail = qygsGdjczGdjczInfo.new Detail();// 实缴明细
								sjDetail.amount = gdjczxx_trs.get(j).select("td")
										.get(7).text();// 出资额（万元）
								sjDetail.method = gdjczxx_trs.get(j).select("td")
										.get(8).text();// 出资方式
								sjDetail.dateTime = gdjczxx_trs.get(j).select("td").get(9)
										.text();// 出资日期
								qygsGdjczGdjczInfos.add(qygsGdjczGdjczInfo);
							}
						}

						
					}
				
					qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);// 企业公示信息->股东及出资信息
				}
				
				
				Element bgxx_tb = qygsxx_gdjczxx.getElementById("table_tzrxxbg");//变更信息				
				if (null !=bgxx_tb) {
					Elements bgxx_trs=bgxx_tb.select("tbody").select("tr");
					Element bgxx_page=bgxx_tb.getElementById("scroll_div");
					List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();				
					int bgxx_trs_size = bgxx_trs.size();
					if (null != bgxx_page) {
						if (bgxx_trs_size > 2) {
							for (int j = 2; j < bgxx_trs.size()-1; j ++) {						
								EntpubStohrinvestChangeInfo qygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
								qygsGdjczBgInfo.setItem(bgxx_trs.get(j).select("td").get(0).text());
								qygsGdjczBgInfo.setDateTime(bgxx_trs.get(j).select("td").get(1).text());
								qygsGdjczBgInfo.setPreContent(bgxx_trs.get(j).select("td").get(2).text());
								qygsGdjczBgInfo.setPostContent(bgxx_trs.get(j).select("td").get(3).text());					
								
								qygsGdjczBgInfos.add(qygsGdjczBgInfo);
							}
						}	
					}else{
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
			
			Element guquanbiangeng_tb = qygsxx_gqbgxx_page
					.getElementById("table_tzrbgxx");			
			if (null !=guquanbiangeng_tb) {
				Elements guquanbiangeng_trs=guquanbiangeng_tb.select("tbody").get(0)
						.select("tr");
			
				int guquanbiangeng_trs_size = guquanbiangeng_trs.size();
				
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
			Element xingzhengxuke_tb = qygsxx_xzxkxx_page
					.getElementById("table_xzxk");
			if (null !=xingzhengxuke_tb) {
				Elements xingzhengxuke_trs=xingzhengxuke_tb.select("tbody").get(0)
						.select("tr");			
				int xingzhengxuke_trs_size = xingzhengxuke_trs.size();
					if (xingzhengxuke_trs_size > 2) {
						for (int i = 2; i < xingzhengxuke_trs_size-1; i++) {
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
		
			//行政许可信息详情
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> gsgsxx_xzxkxx_xzxkxx_xqs = (List<Map<String, Object>>) htmlMap.get("qygsxx_xzxkxx_xqs");						
			List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos2 = new ArrayList<EntpubAAdmlicInfo>();
		
			if (null != gsgsxx_xzxkxx_xzxkxx_xqs
					&& gsgsxx_xzxkxx_xzxkxx_xqs.size()> 0) {
				for (Map<String, Object> gsgsxx_xzxkxx_xzxkxx_xq : gsgsxx_xzxkxx_xzxkxx_xqs) {
					Document gsgsxx_xzxkxx_xzxkxx_xq_page = Jsoup
							.parseBodyFragment(gsgsxx_xzxkxx_xzxkxx_xq.get(
									"qygsxx_xzxkxx_xq").toString());
					EntpubAAdmlicInfo gsgsXzxkXzxkInfo = new EntpubAAdmlicInfo();					
					Element gsgsxx_xzxkxx_xzxkxx_table = gsgsxx_xzxkxx_xzxkxx_xq_page.getElementById("table_xzxkbg");	
					if (null != gsgsxx_xzxkxx_xzxkxx_table) {
						List<Detail> admlicDetails=new ArrayList<Detail>();
						Elements gsgsxx_xzxkxx_xzxkxx_xq_trs=gsgsxx_xzxkxx_xzxkxx_table.select("table").select("tr");
						int bgxq_trs_size=gsgsxx_xzxkxx_xzxkxx_xq_trs.size();
						if (bgxq_trs_size>3) {
							for (int i = 2; i < bgxq_trs_size-1; i++) {
								EntpubAAdmlicInfo.Detail admlicDetail = gsgsXzxkXzxkInfo.new Detail();															
								admlicDetail.changeItem = gsgsxx_xzxkxx_xzxkxx_xq_trs.select("td").get(1).text();;// 名称
								admlicDetail.changeDateTime = gsgsxx_xzxkxx_xzxkxx_xq_trs.select("td").get(2).text();;// 注册号
								admlicDetail.changePreContent = gsgsxx_xzxkxx_xzxkxx_xq_trs.select("td").get(3).text();;// 法定代表人（负责人）姓名
								admlicDetail.changePostContent = gsgsxx_xzxkxx_xzxkxx_xq_trs.select("td").get(4).text();;// 违法行为类型
								admlicDetails.add(admlicDetail);
							}
							
						}						
						gsgsXzxkXzxkInfo.setAdmlicDetail(admlicDetails);
						qygsXzxkXzxkInfos2.add(gsgsXzxkXzxkInfo);
					}
				
				}
			}
			
			if (null != qygsXzxkXzxkInfos && qygsXzxkXzxkInfos.size() > 0
					&& null != qygsXzxkXzxkInfos2
					&& qygsXzxkXzxkInfos2.size() > 0) {
				if (qygsXzxkXzxkInfos.size() == qygsXzxkXzxkInfos2.size()) {
					for (int i = 0; i < qygsXzxkXzxkInfos.size(); i++) {
						for (int j = 0; j < qygsXzxkXzxkInfos2.size(); j++) {
							if (i == j) {
								qygsXzxkXzxkInfos.get(i).setAdmlicDetail(
										qygsXzxkXzxkInfos2.get(j)
												.getAdmlicDetail());
							}
						}

					}

				}
				qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);// 行政处罚信息
			} else {
				qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
			}
			
	    	qygsInfo.setAdmLicInfo(qygsXzxkInfo);// 企业公示信息->行政许可信息
		}

		
		// 企业公示信息->知识产权出质登记信息
		EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
		if (null != htmlMap.get("qygsxx_zscqczdjxx")
				&& !StringUtils.isEmpty(String.valueOf(htmlMap
						.get("qygsxx_zscqczdjxx")))) {
			Document qygsxx_zscqczdjxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qygsxx_zscqczdjxx")));
			// 企业公示信息->知识产权出质登记信息列表
			List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();							
			Element zhishichanquan_tbs = qygsxx_zscqczdjxx_page.getElementById("table_zscq");			
			if (null !=zhishichanquan_tbs) {
				Elements zhishichanquan_trs=zhishichanquan_tbs.select("tbody").get(0).select("tr");
				int zhishichanquan_tr_size = zhishichanquan_trs.size();			
				if (zhishichanquan_tr_size>2) {				
					for (int i = 2; i < zhishichanquan_tr_size-1; i++) {
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
			Element xingzhengchufa_et = qygsxx_xzcfxx_page
					.getElementById("table_qyxzcf");			
			if (null != xingzhengchufa_et) {
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
			// 其他部门公示信息->行政许可信息列表
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			// 其他部门公示信息->行政许可信息列表
			Element xingzhengxuke_div = qtbmgsxx_xzxkxx_page.getElementById("xingzhengxuke");			
			if (null !=xingzhengxuke_div) {
				Element xingzhengxuke_tb=xingzhengxuke_div.getElementById("table_xzxkother");
				Elements xingzhengxuke_tb_trs=xingzhengxuke_tb.select("tbody").select("tr");	
				int xingzhengxuke_tb_tr_size=xingzhengxuke_tb_trs.size();
				if (xingzhengxuke_tb_tr_size>2) {
					for (int i = 2; i < xingzhengxuke_tb_tr_size-1; i++) {
						OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo = new OthrdeptpubAAdmlicInfo();
						qtbmgsXzxkXzxkInfo.setLicenceNum(xingzhengxuke_tb_trs.get(i).select("td").get(0).text());// 许可文件编号
						qtbmgsXzxkXzxkInfo.setLicenceName(xingzhengxuke_tb_trs.get(i).select("td").get(1).text());// 许可文件名称
						qtbmgsXzxkXzxkInfo.setStartDateTime(xingzhengxuke_tb_trs.get(i).select("td").get(2).text());// 有效期自
						qtbmgsXzxkXzxkInfo.setEndDateTime(xingzhengxuke_tb_trs.get(i).select("td").get(3).text());// 有效期至
						qtbmgsXzxkXzxkInfo.setDeciAuthority(xingzhengxuke_tb_trs.get(i).select("td").get(4).text());// 许可机关
						qtbmgsXzxkXzxkInfo.setContent(xingzhengxuke_tb_trs.get(i).select("td").get(5).text());// 许可内容
						qtbmgsXzxkXzxkInfo.setStatus(xingzhengxuke_tb_trs.get(i).select("td").get(6).text());// 状态
						qtbmgsXzxkXzxkInfo.setDetail(xingzhengxuke_tb_trs.get(i).select("td").get(7).text());// 详情
						qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
					}
					qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);
				}else{
					if (isDebug) {
						qtbmgsXzxkInfo.setHtml(xingzhengxuke_tb.toString());
					}
					
				}
			}
			
		}
		qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);// 其他部门公示信息->行政许可信息

		
		// 其他部门公示信息->行政处罚信息
		OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
		if (null != htmlMap.get("qtbmgsxx_xzcfxx")
				&& !StringUtils.isEmpty(htmlMap.get("qtbmgsxx_xzcfxx")
						.toString())) {
			Document qtbmgsxx_xzcfxx_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("qtbmgsxx_xzcfxx")));
			// 其他部门公示信息->行政处罚信息列表
			List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();			
			Element  xingzhengchufa_tb=qtbmgsxx_xzcfxx_page.getElementById("table_qtxzcf");			
			if (null !=xingzhengchufa_tb) {
				Elements xingzhengchufa_trs=xingzhengchufa_tb.select("tbody").get(0).select("tr");			
				if (xingzhengchufa_trs.size()>2) {				
					for (int i = 2; i < xingzhengchufa_trs.size()-1; i++) {
						OthrdeptpubAAdmpunishInfo qtbmgsXzcfXzcfInfo = new OthrdeptpubAAdmpunishInfo();
						qtbmgsXzcfXzcfInfo.setPunishRepNum(xingzhengchufa_trs.get(i).select("td").get(0).text());// 行政处罚决定书文号
						qtbmgsXzcfXzcfInfo.setIllegalActType(xingzhengchufa_trs.get(i).select("td").get(1).text());// 违法行为类型
						qtbmgsXzcfXzcfInfo.setPunishContent(xingzhengchufa_trs.get(i).select("td").get(2).text());// 行政处罚内容
						qtbmgsXzcfXzcfInfo.setDeciAuthority(xingzhengchufa_trs.get(i).select("td").get(3).text());// 作出行政处罚决定机关名称
						qtbmgsXzcfXzcfInfo.setDeciDateTime(xingzhengchufa_trs.get(i).select("td").get(4).text());// 作出行政处罚决定日期
						qtbmgsXzcfXzcfInfo.setDetail(xingzhengchufa_trs.get(i).select("td").get(5).text());// 详情（暂无）
						qtbmgsXzcfXzcfInfo.setNote(xingzhengchufa_trs.get(i).select("td").get(6).text());// 备注（暂无）
						qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
					}
				}else{
					if (isDebug) {
						qtbmgsXzcfInfo.setHtml(xingzhengchufa_tb.toString());
					}				
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
			// 司法协助公示信息->股权冻结信息列表
			List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
			// 司法协助公示信息->股权冻结信息
					
			Element guquandongjie_et = sfxzgsxx_gqdjxx_list_page.getElementById("guquandongjie");
			if (null != guquandongjie_et) {				
				Elements guquandongjie_trs=guquandongjie_et.getElementById("table_gqdj").select("tbody").select("tr");
				if (guquandongjie_trs.size()>2) {
					for (int i = 2; i < guquandongjie_trs.size()-1; i++) {					
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
			
			
		}		
		sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);// 司法协助公示信息->股权冻结信息

		// 司法协助公示信息->股东变更信息
		JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
		if (null != htmlMap.get("sfxzgsxx_gqbgxx")
				&& !StringUtils.isEmpty(htmlMap.get("sfxzgsxx_gqbgxx")
						.toString())) {
			Document sfxzgsxx_gqbgxx_list_page = Jsoup.parseBodyFragment(String
					.valueOf(htmlMap.get("sfxzgsxx_gqbgxx")));
			// 司法协助公示信息->股东变更信息列表
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			// 司法协助公示信息->股东变更信息
			Element guquanbiangeng_et = sfxzgsxx_gqbgxx_list_page
					.getElementById("gudongbiangeng");
			if (null != guquanbiangeng_et) {		
				Elements  guquanbiangeng_trs=guquanbiangeng_et.getElementById("table_gdbg").select("tbody").select("tr");
				if (guquanbiangeng_trs.size()>2) {				
					for (int i = 2; i < guquanbiangeng_trs.size()-1; i++) {
						JudasspubSStohrchangeInfo sfxzgsGdbgGdbgInfo = new JudasspubSStohrchangeInfo();
						sfxzgsGdbgGdbgInfo.setExecutedPerson(guquanbiangeng_trs.get(i).select("td").get(0).text());// 被执行人
						sfxzgsGdbgGdbgInfo.setEquAmount(guquanbiangeng_trs.get(i).select("td").get(1).text());// 股权数额
						sfxzgsGdbgGdbgInfo.setAssignee(guquanbiangeng_trs.get(i).select("td").get(2).text());// 受让人
						sfxzgsGdbgGdbgInfo.setExeCourt(guquanbiangeng_trs.get(i).select("td").get(3).text());// 执行法院
						sfxzgsGdbgGdbgInfo.setDetail(guquanbiangeng_trs.get(i).select("td").get(4).text());// 详情	
						sfxzgsGdbgGdbgInfos.add(sfxzgsGdbgGdbgInfo);
					}				
				}else{
					if (isDebug) {
						sfxzgsGdbgInfo.setHtml(guquanbiangeng_et.html());
					}				
				}
			}
			sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);
		}
		sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);// 司法协助公示信息->股东变更信息列表
		
		
		// 司法协助公示信息
		gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);
		return gsxtFeedJson;

	}

}

	
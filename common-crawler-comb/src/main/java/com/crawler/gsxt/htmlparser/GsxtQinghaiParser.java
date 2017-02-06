package com.crawler.gsxt.htmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtQinghaiParser extends AbstractGsxtParser {

	public  AicFeedJson qinghaiResultParser(String html, Boolean isDebug) {  
	
    	Gson gson = new Gson();		
		Map<String, Object> resultHtmlMap = gson.fromJson(html, new TypeToken<Map<String, Object>>(){}.getType()); 
		//解析result 
		AicFeedJson gsxtFeedJson = new AicFeedJson();
		//工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();
	
	
		String gsgsxxHtml = (String)resultHtmlMap.get("gsgsxx");
		Document gsgsxxDoc = Jsoup.parse(gsgsxxHtml);
		
		//一 工商公示信息
		//-----------------工商公示信息-->登记信息 start-----------------------
		
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();
		
		Element djxxDiv = gsgsxxDoc.getElementById("jibenxinxi");
		Elements djxx_tables = djxxDiv.getElementsByTag("table");
		Element jbxx_table = djxx_tables.get(0);
	
		
		//登记信息 -->基本信息		
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();
		Elements jbxx_tds = jbxx_table.select("tr").select("td");
		Elements jbxx_ths = jbxx_table.select("tr").select("th");
		
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
			if (jbxx_ths.get(i).text().trim().contains("营业期限自") || jbxx_ths.get(i).text().trim().contains("经营期限自") ) {
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
		//登记信息 -->股东及出资信息
		List<AicpubRegStohrStohrinvestInfo> gsgsDjGdjczList = new ArrayList<AicpubRegStohrStohrinvestInfo>();
		ArrayList<String> gdDetailList = (ArrayList<String>)resultHtmlMap.get("gsgsxx_gdxx_xqs");
		for (String gdxxDetailHtml : gdDetailList) {
			Document gdxxDetailDoc = Jsoup.parse(gdxxDetailHtml);
			Element gdxxDetailDiv = gdxxDetailDoc.getElementById("details");
			Elements gdxxDetailTables = gdxxDetailDiv.getElementsByTag("table");
			if(gdxxDetailTables != null && !gdxxDetailTables.isEmpty()) {
				Element gdxxDetailTable = gdxxDetailTables.get(0);
				Elements gdxxDetail_tds = gdxxDetailTable.select("td");
				String stockholder = gdxxDetail_tds.get(0).text();
				String subAmount = gdxxDetail_tds.get(1).text();
				String paidAmount = gdxxDetail_tds.get(2).text();
				String sub_method = gdxxDetail_tds.get(3).text();
				String sub_amount = gdxxDetail_tds.get(4).text();
				String sub_czDate = gdxxDetail_tds.get(5).text();
				String paid_method = gdxxDetail_tds.get(6).text();
				String paid_amount = gdxxDetail_tds.get(7).text();
				String paid_czDate = gdxxDetail_tds.get(8).text();
				
				AicpubRegStohrStohrinvestInfo gsgsDjGdjczInfo = new AicpubRegStohrStohrinvestInfo();
				AicpubRegStohrStohrinvestInfo.AmountDetail subAmountDetail = gsgsDjGdjczInfo.new AmountDetail();
				AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdjczInfo.new AmountDetail();
				List<AmountDetail> subAmountDetailList = new ArrayList<AicpubRegStohrStohrinvestInfo.AmountDetail>();
				List<AmountDetail> paidAmountDetailList = new ArrayList<AicpubRegStohrStohrinvestInfo.AmountDetail>();
				
				subAmountDetail.investMethod = sub_method;
				subAmountDetail.investAmount = sub_amount;
				subAmountDetail.investDateTime = sub_czDate;
				paidAmountDetail.investMethod = paid_method;
				paidAmountDetail.investAmount = paid_amount;
				paidAmountDetail.investDateTime = paid_czDate;
				gsgsDjGdjczInfo.setStockholder(stockholder);
				gsgsDjGdjczInfo.setSubAmount(subAmount);
				gsgsDjGdjczInfo.setPaidAmount(paidAmount);
				subAmountDetailList.add(subAmountDetail);
				paidAmountDetailList.add(paidAmountDetail);
				gsgsDjGdjczInfo.setSubAmountDetails(subAmountDetailList);
				gsgsDjGdjczInfo.setPaidAmountDetails(paidAmountDetailList);
				gsgsDjGdjczList.add(gsgsDjGdjczInfo);
			}
		}
	
		//登记信息 -->股东信息		
		Element gdxx_table=null;
		List<AicpubRegStohrInfo> gsgsDjGdList=null;
		if (djxxDiv.getElementById("invDiv")!=null) {
			 gdxx_table = djxxDiv.getElementById("invDiv").getElementsByTag("table").get(0);			 
			    gsgsDjGdList = new ArrayList<AicpubRegStohrInfo>();
				Elements gdxx_trs = gdxx_table.getElementsByTag("tr");
				int i = 0;
				for (Element gdxx_tr : gdxx_trs) {
					Elements gdxx_tds = gdxx_tr.getElementsByTag("td");				
					if (gdxx_tds.size()==4) {
						String gdType = gdxx_tds.get(0).text();
						String gdName = gdxx_tds.get(1).text();
						String idType = gdxx_tds.get(2).text();
						String idNum = gdxx_tds.get(3).text();
						AicpubRegStohrInfo gsgsdjgdInfo1 = new AicpubRegStohrInfo();
						gsgsdjgdInfo1.setName(gdName);
						gsgsdjgdInfo1.setIdType(idType);
						gsgsdjgdInfo1.setIdNum(idNum);
						gsgsdjgdInfo1.setType(gdType);
						gsgsDjGdList.add(gsgsdjgdInfo1);
					}else if (gdxx_tds.size()==5) {
						String gdType = gdxx_tds.get(0).text();
						String gdName = gdxx_tds.get(1).text();
						String idType = gdxx_tds.get(2).text();
						String idNum = gdxx_tds.get(3).text();
						AicpubRegStohrInfo gsgsdjgdInfo2 = new AicpubRegStohrInfo();
						gsgsdjgdInfo2.setName(gdName);
						gsgsdjgdInfo2.setIdType(idType);
						gsgsdjgdInfo2.setIdNum(idNum);
						gsgsdjgdInfo2.setType(gdType);
						gsgsdjgdInfo2.setStohrInvestInfo(gsgsDjGdjczList.get(i));
						gsgsDjGdList.add(gsgsdjgdInfo2);
						i++;
					}						
				}
		}
		
		//登记信息 -->变更信息
		Element bgxx_table=null;
		List<AicpubRegChangeInfo> gsgsDjBgList=null;
		String gsgsxx_djxx_bgxxHtml = (String)resultHtmlMap.get("gsgsxx_djxx_bgxx");
		Document gsgsxx_djxx_bgxxDoc = Jsoup.parse(gsgsxx_djxx_bgxxHtml);		
		if (null !=gsgsxx_djxx_bgxxDoc) {
			gsgsxx_djxx_bgxxHtml=gsgsxx_djxx_bgxxHtml.substring(1,gsgsxx_djxx_bgxxHtml.length()-1); 
	        Document gsgsxx_djxx_bgxxHtml_doc = Jsoup.parse(gsgsxx_djxx_bgxxHtml);			
			bgxx_table=gsgsxx_djxx_bgxxHtml_doc.getElementById("altTab");
			gsgsDjBgList = new ArrayList<AicpubRegChangeInfo>();
			Elements bgxx_trs = bgxx_table.getElementsByTag("tr");
			for (Element bgxx_tr : bgxx_trs) {
				Elements bgxx_tds = bgxx_tr.getElementsByTag("td");
				String bgItem = bgxx_tds.get(0).text();
				String bgqContent = bgxx_tds.get(1).text();
				String bghContent = bgxx_tds.get(2).text();
				String bgDate = bgxx_tds.get(3).text();
				
				AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();
				gsgsDjBgInfo.setItem(bgItem);
				gsgsDjBgInfo.setPreContent(bgqContent);
				gsgsDjBgInfo.setPostContent(bghContent);
				gsgsDjBgInfo.setDateTime(bgDate);
				gsgsDjBgList.add(gsgsDjBgInfo);
			}
		}		
		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);
		gsgsDjInfo.setStohrInfos(gsgsDjGdList);
		gsgsDjInfo.setChangeInfos(gsgsDjBgList);		
		gsgsInfo.setRegInfo(gsgsDjInfo);
		//-----------------工商公示信息-->登记信息 end-----------------------
		
		
		//-----------------工商公示信息-->备案信息 start-----------------------
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
       
		//备案信息-->主要人员信息
		List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos=null;
		List<AicpubArchiveMainDeptInfo> aicpubArchiveMainDeptInfos=null;         
		Element memDivElement=gsgsxxDoc.getElementById("beian");
		if (null !=memDivElement) {			
			Element memDivElement_table_name=memDivElement.getElementById("t30");
			if (null != memDivElement_table_name) {
				String table_name=memDivElement_table_name.select("tbody").select("th").get(0).text();
				if (table_name.contains("主管部门（出资人）信息")) {
					aicpubArchiveMainDeptInfos= new ArrayList<AicpubArchiveMainDeptInfo>();
					Element memDivElement_table = gsgsxxDoc.getElementById("invDiv");
					if (null !=memDivElement_table) {				
						Elements zyryTrElements = memDivElement_table.select("tbody").select("tr");
						for (Element zyryTr : zyryTrElements) {
							Elements zyryTdElements = zyryTr.select("td");
							String type = zyryTdElements.get(1).text();
							String name = zyryTdElements.get(2).text();
							String idType = zyryTdElements.get(3).text();
							String idNum = zyryTdElements.get(4).text();
							String showDate=zyryTdElements.get(5).text();
							
							AicpubArchiveMainDeptInfo aicpubArchiveMainDeptInfo = new AicpubArchiveMainDeptInfo();
							aicpubArchiveMainDeptInfo.setType(type);
							aicpubArchiveMainDeptInfo.setName(name);
							aicpubArchiveMainDeptInfo.setIdType(idType);
							aicpubArchiveMainDeptInfo.setIdNum(idNum);
							aicpubArchiveMainDeptInfo.setShowDate(showDate);
							aicpubArchiveMainDeptInfos.add(aicpubArchiveMainDeptInfo);														
						}
						gsgsBaInfo.setMainDeptInfo(aicpubArchiveMainDeptInfos);
					}
				}
				
				if (table_name.contains("主要人员信息")) {
					gsgsBaZyryInfos= new ArrayList<AicpubArchivePrimemberInfo>();
					Element memDivElement_table = gsgsxxDoc.getElementById("memDiv");
					if (null !=memDivElement_table) {				
						Elements zyryTrElements = memDivElement_table.select("tbody").select("tr");
						for (Element zyryTr : zyryTrElements) {
							Elements zyryTdElements = zyryTr.select("td");
							String zyry_name = zyryTdElements.get(1).text();
							String zyry_position = zyryTdElements.get(2).text();
							String zyry_name2 = zyryTdElements.get(4).text();
							String zyry_position2 = zyryTdElements.get(5).text();
							if(!"".equals(zyry_name)) {
								AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
								gsgsBaZyryInfo.setName(zyry_name);
								gsgsBaZyryInfo.setPosition(zyry_position);
								gsgsBaZyryInfos.add(gsgsBaZyryInfo);				
							}
							if(!"".equals(zyry_name2)) {
								AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
								gsgsBaZyryInfo.setName(zyry_name2);
								gsgsBaZyryInfo.setPosition(zyry_position2);
								gsgsBaZyryInfos.add(gsgsBaZyryInfo);				
							}
						}
						gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);
					}
				}	
			}
					
		}
	
		//备案信息-->分支机构信息		
		List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos=null;
		Element gsgsBaFzjgDiv=null;
		if (gsgsxxDoc.getElementById("childDiv")!=null) {
			gsgsBaFzjgInfos= new ArrayList<AicpubArchiveBranchInfo>();
			gsgsBaFzjgDiv = gsgsxxDoc.getElementById("childDiv");
			Elements gsgsBaFzjgTrs = gsgsBaFzjgDiv.select("tr");
			for (Element gsgsBaFzjgTr : gsgsBaFzjgTrs) {
				Elements gsgsBaFzjgTds = gsgsBaFzjgTr.select("td");
				String fzjg_num = gsgsBaFzjgTds.get(1).text();
				String fzjg_name = gsgsBaFzjgTds.get(2).text();
				String fzjg_regAuthority = gsgsBaFzjgTds.get(3).text();
				AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
				gsgsBaFzjgInfo.setNum(fzjg_num);
				gsgsBaFzjgInfo.setName(fzjg_name);
				gsgsBaFzjgInfo.setRegAuthority(fzjg_regAuthority);
				gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
			}
		}
		gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);
		
		//备案信息-->清算信息
		AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
		Element beianElement = gsgsxxDoc.getElementById("beian");		
		if (null !=beianElement) {
			Elements tables = beianElement.select("table");		
			if(tables.size()==6) {
				Element gsgsBaQsTable = tables.get(tables.size()-1);
				Elements gsgsBaQsTds = gsgsBaQsTable.select("td");
				String leader = gsgsBaQsTds.get(0).text();
				String members = gsgsBaQsTds.get(1).text();
				List<String> memList = new ArrayList<String>();
				memList.add(members);
				gsgsBaQsInfo.setLeader(leader);
				gsgsBaQsInfo.setMembers(memList);
			}
		}
		gsgsBaInfo.setClearInfo(gsgsBaQsInfo);
		gsgsInfo.setArchiveInfo(gsgsBaInfo);
		//-----------------工商公示信息-->备案信息 end-----------------------
				
		//-----------------工商公示信息-->动产抵押登记信息 start-----------------------
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		
		List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos =new ArrayList<AicpubCChatMortgInfo>();
		Element gsgsDcdydjDiv=null;
		if (gsgsxxDoc.getElementById("dongchandiya")!=null) {
			gsgsDcdydjDiv = gsgsxxDoc.getElementById("dongchandiya");
			Elements gsgsDcdydjTrs = gsgsDcdydjDiv.select("#mortDiv").select("table").select("tr");
			for (Element gsgsDcdydjTr : gsgsDcdydjTrs) {
				Elements gsgsDcdydjTds = gsgsDcdydjTr.select("td");
				String regNum = gsgsDcdydjTds.get(1).text();
				String regDate = gsgsDcdydjTds.get(2).text();
				String reg_Authority = gsgsDcdydjTds.get(3).text();
				String bdbzqAmount = gsgsDcdydjTds.get(4).text();
				String status = gsgsDcdydjTds.get(5).text();
				String pubDate = gsgsDcdydjTds.get(6).text();
				String detail = gsgsDcdydjTds.get(7).text();
				
				AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
				gsgsDcdydjDcdydjInfo.setRegNum(regNum);
				gsgsDcdydjDcdydjInfo.setRegDateTime(regDate);
				gsgsDcdydjDcdydjInfo.setRegAuthority(reg_Authority);
				gsgsDcdydjDcdydjInfo.setGuaranteedDebtAmount(bdbzqAmount);
				gsgsDcdydjDcdydjInfo.setStatus(status);
				gsgsDcdydjDcdydjInfo.setPubDateTime(pubDate);
				gsgsDcdydjDcdydjInfo.setDetail(detail);
				gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
			}
		}		
		if(isDebug) {
			gsgsDcdydjInfo.setHtml(gsgsDcdydjDiv.toString());
		}
		gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);		
		gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);
	//-----------------工商公示信息-->动产抵押登记信息 end-----------------------
								
	//-----------------工商公示信息-->股权出质登记信息 start-----------------------
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		Element gsgsGqczdjDiv=null;	
		List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos= new ArrayList<AicpubEEqumortgregInfo>();;
		
		if (gsgsxxDoc.getElementById("guquanchuzhi")!=null) {
		     gsgsGqczdjDiv = gsgsxxDoc.getElementById("guquanchuzhi");
			 Elements gsgsGqczdjTrs = gsgsGqczdjDiv.select("#pledgeDiv").select("table").select("tr");			
			for (Element gsgsGqczdjTr : gsgsGqczdjTrs) {
				Elements gsgsGqczdjTds = gsgsGqczdjTr.select("tr").select("td");
				String regNum = gsgsGqczdjTds.get(1).text();
				String czr = gsgsGqczdjTds.get(2).text();
				String czrIdNum = gsgsGqczdjTds.get(3).text();
				String czgqAmount = gsgsGqczdjTds.get(4).text();
				String zqr = gsgsGqczdjTds.get(5).text();
				String zqrIdNum = gsgsGqczdjTds.get(6).text();
				String gqczsldjDate = gsgsGqczdjTds.get(7).text();
				String status = gsgsGqczdjTds.get(8).text();
				String pubDate = gsgsGqczdjTds.get(9).text();
				String changeSitu = gsgsGqczdjTds.get(10).text();
				
				AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
				gsgsGqczdjGqczdjInfo.setRegNum(regNum);
				gsgsGqczdjGqczdjInfo.setMortgagorName(czr);
				gsgsGqczdjGqczdjInfo.setMortgagorIdNum(czrIdNum);
				gsgsGqczdjGqczdjInfo.setMortgAmount(czgqAmount);
				gsgsGqczdjGqczdjInfo.setMortgageeName(zqr);
				gsgsGqczdjGqczdjInfo.setMortgageeIdNum(zqrIdNum);
				gsgsGqczdjGqczdjInfo.setRegDateTime(gqczsldjDate);
				gsgsGqczdjGqczdjInfo.setStatus(status);
				gsgsGqczdjGqczdjInfo.setPubDate(pubDate);
				gsgsGqczdjGqczdjInfo.setChangeSitu(changeSitu);
				gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
			}
			if(isDebug) {
				gsgsGqczdjInfo.setHtml(gsgsGqczdjDiv.toString());
			}
		}
		gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);
		gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);
			//-----------------工商公示信息-->股权出质登记信息 end-----------------------
			
			//-----------------工商公示信息-->行政处罚信息 start-----------------------
			/*
			 * 还差详情信息
			 */
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
			Element gsgsXzcfXzcfDiv=null;
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos=null;
			if (gsgsxxDoc.getElementById("xingzhengchufa")!=null) {
				gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
				gsgsXzcfXzcfDiv = gsgsxxDoc.getElementById("xingzhengchufa");
				Elements gsgsXzcfXzcfTrs = gsgsXzcfXzcfDiv.select("#punTab").select("table").select("tr");
				for (Element gsgsXzcfXzcfTr : gsgsXzcfXzcfTrs) {
					Elements gsgsGqczdjTds = gsgsXzcfXzcfTr.select("td");
					String xzcfjdsNum = gsgsGqczdjTds.get(1).text();
					String wfxwType = gsgsGqczdjTds.get(2).text();
					String xzcfContent = gsgsGqczdjTds.get(3).text();
					String zcxzcfjdjgName = gsgsGqczdjTds.get(4).text();
					String zcxzcfjdDate = gsgsGqczdjTds.get(5).text();
					
					AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();
					gsgsXzcfXzcfInfo.setPunishRepNum(xzcfjdsNum);
					gsgsXzcfXzcfInfo.setIllegalActType(wfxwType);					
					gsgsXzcfXzcfInfo.setPunishContent(zcxzcfjdjgName);
					gsgsXzcfXzcfInfo.setDeciAuthority(xzcfContent);
					gsgsXzcfXzcfInfo.setDeciDateTime(zcxzcfjdDate);
					gsgsXzcfXzcfInfos.add(gsgsXzcfXzcfInfo);
				}
			}	
			if(isDebug) {
				gsgsXzcfInfo.setHtml(gsgsXzcfXzcfDiv.toString());
			}
			
			gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos);
			gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);
			//-----------------工商公示信息-->行政处罚信息  end-----------------------
			
			
			//-----------------工商公示信息-->经营异常信息 start-----------------------
			AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
			
			List<AicpubOOperanomaInfo> gsgsJyycJyycInfos =new ArrayList<AicpubOOperanomaInfo>();
			Element gsgsJyycDiv=null;
			if (gsgsxxDoc.getElementById("jingyingyichangminglu")!=null) {
				 gsgsJyycDiv = gsgsxxDoc.getElementById("jingyingyichangminglu");
				Elements gsgsJyycTrs = gsgsJyycDiv.getElementById("excDiv").select("tr");
				for (Element gsgsJyycTr : gsgsJyycTrs) {
					Elements gsgsJyycTds = gsgsJyycTr.select("td");
					String lrjyycmlCause = gsgsJyycTds.get(1).text();
					String lrDate = gsgsJyycTds.get(2).text();
					String ycjyycmlCause = gsgsJyycTds.get(3).text();
					String ycDate = gsgsJyycTds.get(4).text();
					String zcjdAuthority = gsgsJyycTds.get(5).text();
					
					AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();
					gsgsJyycJyycInfo.setIncludeCause(lrjyycmlCause);
					gsgsJyycJyycInfo.setIncludeDateTime(lrDate);
					gsgsJyycJyycInfo.setRemoveCause(ycjyycmlCause);
					gsgsJyycJyycInfo.setRemoveDateTime(ycDate);
					gsgsJyycJyycInfo.setAuthority(zcjdAuthority);						
					gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);								
				}
			}
					
			if(isDebug) {
				gsgsJyycInfo.setHtml(gsgsJyycDiv.toString());
			}
			gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);
			gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);
			//-----------------工商公示信息-->经营异常信息 end-----------------------
			
			
			//-----------------工商公示信息-->严重违法信息 start-----------------------

			AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
			List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos=null;
			Element gsgsYzwfDiv=null;
			if (gsgsxxDoc.getElementById("yanzhongweifaqiye")!=null) {
				 gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
				 gsgsYzwfDiv = gsgsxxDoc.getElementById("yanzhongweifaqiye");
				Elements gsgsYzwfTrs = gsgsYzwfDiv.getElementById("serillDiv").select("tr");
				for (Element gsgsYzwfTr : gsgsYzwfTrs) {
					Elements gsgsYzwfTds = gsgsYzwfTr.select("td");
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
				if(isDebug) {
					gsgsYzwfInfo.setHtml(gsgsYzwfDiv.toString());
				}
			}	
			
			gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);
			gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);
			//-----------------工商公示信息-->严重违法信息 end-----------------------
			
			
			//-----------------工商公示信息-->抽查检查信息 start-----------------------
			AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
			List<AicpubCCheckInfo> gsgsCcjcCcjcInfos=null;
			Element gsgsCcjcDiv=null;
			if (gsgsxxDoc.getElementById("chouchaxinxi")!=null) {
				 gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
				 gsgsCcjcDiv = gsgsxxDoc.getElementById("chouchaxinxi");
				Elements gsgsCcjcTrs = gsgsCcjcDiv.getElementById("spotCheckDiv").select("tr");
				for (Element gsgsCcjcTr : gsgsCcjcTrs) {
					Elements gsgsCcjcTds = gsgsCcjcTr.select("td");
					String jcssAuthority = gsgsCcjcTds.get(1).text();
					String gsgsCcjc_type = gsgsCcjcTds.get(2).text();
					String gsgsCcjc_date = gsgsCcjcTds.get(3).text();
					String gsgsCcjc_result = gsgsCcjcTds.get(4).text();
					
					AicpubCCheckInfo gsgsCcjcCcjcInfo = new AicpubCCheckInfo();
					gsgsCcjcCcjcInfo.setCheckImplAuthority(jcssAuthority);
					gsgsCcjcCcjcInfo.setType(gsgsCcjc_type);
					gsgsCcjcCcjcInfo.setDateTime(gsgsCcjc_date);
					gsgsCcjcCcjcInfo.setResult(gsgsCcjc_result);
					gsgsCcjcCcjcInfos.add(gsgsCcjcCcjcInfo);
				}
			}			
			if (isDebug) {
				gsgsCcjcInfo.setHtml(gsgsCcjcDiv.toString());
			}
			gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);
			gsgsInfo.setCheckInfo(gsgsCcjcInfo);
			gsxtFeedJson.setAicPubInfo(gsgsInfo);
			//-----------------工商公示信息-->抽查检查信 end-----------------------			
		
			
			
			EntpubInfo qygsInfo = new EntpubInfo();
			String qygsxxHtml = (String)resultHtmlMap.get("qygsxx");
			Document qygsxxDoc = Jsoup.parse(qygsxxHtml);
			//-----------------企业公示信息-->企业年报 start-----------------------
			//企业年报列表信息--报送年度和发布日期
        	List<String> qynbDetailList = (ArrayList<String>)resultHtmlMap.get("qygsxx_qynb_detail");
			List<EntpubAnnreportInfo> qygsQynbInfos=null;
			if(qynbDetailList!=null && !qynbDetailList.isEmpty()){
				qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
				Element qygsnbDiv = qygsxxDoc.getElementById("qiyenianbao");
				Elements qygsnbTrs = qygsnbDiv.select("tr");
				int k = 0;
				int qygsnbTrs_size=qygsnbTrs.size();	
				for (int j = 2; j < qygsnbTrs_size; j++) {
					EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();					
					Elements qygsnbTds = qygsnbTrs.get(j).select("td");					
					if (qygsnbTds.size() == 3) {
						String submitYear = qygsnbTds.get(1).text();
						String pubDate = qygsnbTds.get(2).text();
						qygsQynbInfo.setSubmitYear(submitYear);
						qygsQynbInfo.setPubDateTime(pubDate);
					}				
					String qynbDetailHtml = qynbDetailList.get(k++);
					Document qygsxxnbDetailDoc = Jsoup.parse(qynbDetailHtml);					
					Elements qygsnbxxTables = qygsxxnbDetailDoc.select("#sifapanding").select("table");		
					int table_size=qygsnbxxTables.size();
					for (int t = 0; t < table_size; t++) {						
						  Element qygsxx_qynb_info_table=qygsnbxxTables.get(t);
						  Elements qygsxx_qynb_info_ths = getElements(qygsxx_qynb_info_table, "th");	
						  Elements qygsxx_qynb_info_trs = getElements(qygsxx_qynb_info_table, "tr");	
						  Elements qygsxx_qynb_info_tds = getElements(qygsxx_qynb_info_table, "td");
						  if (t==0) {
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
								
								if (th_name.contains("企业是否有投资信息或购买其他公司股权") || th_name.contains("是否有投资信息或购买其他公司股权")) {
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
							  String table_name=qygsxx_qynb_info_ths.get(0).text().trim();		
							  
							  if (table_name.contains("网站或网店信息")) {
								  //	企业年报信息--> 网站或网店信息
								  List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
									Elements wzwdxxTrs = qygsxx_qynb_info_trs;
									for (Element wzwdxxTr : wzwdxxTrs) {
										if(!"".equals(wzwdxxTr.attr("id")) && !wzwdxxTr.hasAttr("style")) {
											Elements wzwdxxTds = wzwdxxTr.select("td");
											String wzwd_type = wzwdxxTds.get(0).text();
											String wzwd_name = wzwdxxTds.get(1).text();
											String website = wzwdxxTds.get(2).text();
											
											EntpubAnnreportWebsiteInfo qygsQynbWzhwdInfo = new EntpubAnnreportWebsiteInfo();
											qygsQynbWzhwdInfo.setType(wzwd_type);
											qygsQynbWzhwdInfo.setName(wzwd_name);
											qygsQynbWzhwdInfo.setWebsite(website);
											qygsQynbWzhwdInfos.add(qygsQynbWzhwdInfo);
										}
									}
									qygsQynbInfo.setWebsiteInfos(qygsQynbWzhwdInfos);	  
								} else if (table_name.contains("股东及出资信息")) {
									//企业年报信息--> 股东及出资信息
									List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();
									Elements gdczxxTrs = qygsxx_qynb_info_trs;
									for (Element gdczxxTr : gdczxxTrs) {
										if(!"".equals(gdczxxTr.attr("id")) && !gdczxxTr.hasAttr("style")) {
											Elements gdczxxTds = gdczxxTr.select("td");
											String stockholder = gdczxxTds.get(0).text();
											String rjczAmount = gdczxxTds.get(1).text();
											String rjczDate = gdczxxTds.get(2).text();
											String rjczMethod = gdczxxTds.get(3).text();
											String sjczAmount = gdczxxTds.get(4).text();
											String sjczDate = gdczxxTds.get(5).text();
											String sjczMethod = gdczxxTds.get(6).text();
											
											EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo = new EntpubAnnreportStohrinvestInfo();
											qygsQynbGdjczInfo.setStockholder(stockholder);
											qygsQynbGdjczInfo.setSubAmount(rjczAmount);						
											qygsQynbGdjczInfo.setSubDateTime(rjczDate);
											qygsQynbGdjczInfo.setSubMethod(rjczMethod);
											qygsQynbGdjczInfo.setPaidAmount(sjczAmount);
											qygsQynbGdjczInfo.setPaidDateTime(sjczDate);
											qygsQynbGdjczInfo.setPaidMethod(sjczMethod);
											qygsQynbGdjczInfos.add(qygsQynbGdjczInfo);
										}
									}
									qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);
																	
								}else if (table_name.contains("对外投资信息")) {
								//企业年报信息--> 对外投资信息							
								List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
								Elements dwtzxxTrs = qygsxx_qynb_info_trs;
								for (Element dwtzxxTr : dwtzxxTrs) {
									if(!"".equals(dwtzxxTr.attr("id")) && !dwtzxxTr.hasAttr("style")) {
										Elements dwtzxxTds = dwtzxxTr.select("td");
										String tzslqyhgmgqqyName = dwtzxxTds.get(0).text();
										String regNum = dwtzxxTds.get(1).text();
										
										EntpubAnnreportExtinvestInfo qygsQynbDwtzInfo = new EntpubAnnreportExtinvestInfo();
										qygsQynbDwtzInfo.setEnterpriseName(tzslqyhgmgqqyName);
										qygsQynbDwtzInfo.setRegNum(regNum);
										qygsQynbDwtzInfos.add(qygsQynbDwtzInfo);
									}
								}
								qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzInfos);
								
							}else if (table_name.contains("企业资产状况信息")) {
								//企业年报信息--> 企业资产状况信息						
								Elements qyzczkxxTds = qygsxx_qynb_info_tds;
								String assetAmount = qyzczkxxTds.get(0).text();
								String syzqyhj = qyzczkxxTds.get(1).text();
								String liabilityAmount = qyzczkxxTds.get(2).text();
								String salesAmount = qyzczkxxTds.get(3).text();
								String profitAmount = qyzczkxxTds.get(4).text();
								String xszezzyywsr = qyzczkxxTds.get(5).text();
								String netProfit = qyzczkxxTds.get(6).text();
								String taxesAmount = qyzczkxxTds.get(7).text();
								
								EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
								qygsQynbQyzczkInfo.setAssetAmount(assetAmount);
								qygsQynbQyzczkInfo.setTotalEquity(syzqyhj);
								qygsQynbQyzczkInfo.setLiabilityAmount(liabilityAmount);
								qygsQynbQyzczkInfo.setSalesAmount(salesAmount);
								qygsQynbQyzczkInfo.setProfitAmount(profitAmount);
								qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(xszezzyywsr);
								qygsQynbQyzczkInfo.setNetProfit(netProfit);
								qygsQynbQyzczkInfo.setTaxesAmount(taxesAmount);					
								qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);
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
							}else if (table_name.contains("对外提供保证担保信息")) {
							//企业年报信息--> 对外提供保证担保信息			
							List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();
							Elements dwdbxxTrs = qygsxx_qynb_info_trs;
							for (Element dwdbxxTr : dwdbxxTrs) {
								if(!"".equals(dwdbxxTr.attr("id")) && !dwdbxxTr.hasAttr("style")) {
									EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
									Elements dwdbxxTds = dwdbxxTr.select("td");
									String creditor = dwdbxxTds.get(0).text();
									String debtor = dwdbxxTds.get(1).text();
									String priCredRightType = dwdbxxTds.get(2).text();
									String priCredRightAmount = dwdbxxTds.get(3).text();
									String exeDebtDeadline = dwdbxxTds.get(4).text();
									String guaranteePeriod = dwdbxxTds.get(5).text();
									String guaranteeMethod = dwdbxxTds.get(6).text();
									if(dwdbxxTds.size()>7) {
										String guaranteeScope = dwdbxxTds.get(7).text();
										qygsQynbDwtgbzdbInfo.setGuaranteeScope(guaranteeScope);
									}
									qygsQynbDwtgbzdbInfo.setCreditor(creditor);
									qygsQynbDwtgbzdbInfo.setDebtor(debtor);
									qygsQynbDwtgbzdbInfo.setPriCredRightType(priCredRightType);
									qygsQynbDwtgbzdbInfo.setPriCredRightAmount(priCredRightAmount);
									qygsQynbDwtgbzdbInfo.setExeDebtDeadline(exeDebtDeadline);
									qygsQynbDwtgbzdbInfo.setGuaranteePeriod(guaranteePeriod);
									qygsQynbDwtgbzdbInfo.setGuaranteeMethod(guaranteeMethod);
									qygsQynbDwtgbzdbInfos.add(qygsQynbDwtgbzdbInfo);
								}
							}
							qygsQynbInfo.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);														
						}else if (table_name.contains("股权变更信息")) {
							//企业年报信息--> 股权变更信息
							List<EntpubAnnreportEquchangeInfo> qygsQynbGqbgInfos = new ArrayList<EntpubAnnreportEquchangeInfo>();
							Elements gqbgxxTrs = qygsxx_qynb_info_trs;
							for (Element gqbgxxTr : gqbgxxTrs) {
								if(!"".equals(gqbgxxTr.attr("id")) && !gqbgxxTr.hasAttr("style")) {
									Elements gqbgxxTds = gqbgxxTr.select("td");
									String stockholder = gqbgxxTds.get(0).text();
									String bgqOwnershipRatio = gqbgxxTds.get(1).text();
									String bghOwnershipRatio = gqbgxxTds.get(2).text();
									String bgDate = gqbgxxTds.get(3).text();
									
									EntpubAnnreportEquchangeInfo qygsQynbGqbgInfo = new EntpubAnnreportEquchangeInfo();
									qygsQynbGqbgInfo.setStockholder(stockholder);
									qygsQynbGqbgInfo.setPreOwnershipRatio(bgqOwnershipRatio);
									qygsQynbGqbgInfo.setPostOwnershipRatio(bghOwnershipRatio);
									qygsQynbGqbgInfo.setDateTime(bgDate);
									qygsQynbGqbgInfos.add(qygsQynbGqbgInfo);
								}
							}
							qygsQynbInfo.setEquChangeInfos(qygsQynbGqbgInfos);
						}else if (table_name.contains("修改记录")) {							
							//企业年报信息--> 修改记录
							List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
							Elements xgjlxxTrs = qygsxx_qynb_info_trs;
							for (Element xgjlxxTr : xgjlxxTrs) {
								if(!"".equals(xgjlxxTr.attr("id")) && !xgjlxxTr.hasAttr("style")) {
									Elements xgjlxxTds = xgjlxxTr.select("td");
									String xgItem = xgjlxxTds.get(1).text();
									String xgqContent = xgjlxxTds.get(2).text();
									String xghContent = xgjlxxTds.get(3).text();
									String xgDate = xgjlxxTds.get(4).text();
									
									EntpubAnnreportModifyInfo qygsQynbXgjlInfo = new EntpubAnnreportModifyInfo();
									qygsQynbXgjlInfo.setItem(xgItem);
									qygsQynbXgjlInfo.setPreContent(xgqContent);
									qygsQynbXgjlInfo.setPostContent(xghContent);
									qygsQynbXgjlInfo.setDateTime(xgDate);
									qygsQynbXgjlInfos.add(qygsQynbXgjlInfo);
								}
							}
							qygsQynbInfo.setChangeInfos(qygsQynbXgjlInfos);												
						}
						
					}

				}
			 qygsQynbInfos.add(qygsQynbInfo);
			}				
			qygsInfo.setAnnReports(qygsQynbInfos);		
		}

			//-----------------企业公示信息-->企业年报信息 end-----------------------	
	
			//-----------------企业公示信息-->股东及出资信息 start-----------------------	
			
			EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
			Element qygsgdczDiv = qygsxxDoc.getElementById("touziren");
			
			//股东及出资信息-->股东及出资信息	
			List<EntpubSStohrinvestInfo> qygsGdjczGdjczList=null;
			if (null !=qygsgdczDiv) {
				qygsGdjczGdjczList=new ArrayList<EntpubSStohrinvestInfo>();
				Element qygsgdczxxDiv = qygsgdczDiv.getElementById("gdDiv");
				Elements qygsgdczxxTrs = qygsgdczxxDiv.select("tr");	
				
				for (int j = 3; j < qygsgdczxxTrs.size(); j++) {			
					int rowspan = 0;
					Elements qygsgdczxxTds=qygsgdczxxTrs.get(j).select("td");
				    if(qygsgdczxxTds.size()>8){
				    	EntpubSStohrinvestInfo qygsGdjczGdjczInfo=new EntpubSStohrinvestInfo();
				    	String attr = qygsgdczxxTds.get(0).attr("rowspan"); 
				    	rowspan = Integer.parseInt(attr);
				    	
				    	String stockholder=qygsgdczxxTds.get(0).text();
						String rjAmount=qygsgdczxxTds.get(1).text();
						String sjAmount=qygsgdczxxTds.get(2).text();
						
						String rj_method=qygsgdczxxTds.get(3).text();
						String rj_amount=qygsgdczxxTds.get(4).text();				
						String rj_date=qygsgdczxxTds.get(5).text();						
						String rj_showdate=qygsgdczxxTds.get(6).text();
						
						String sj_method=qygsgdczxxTds.get(7).text();
						String sj_amount=qygsgdczxxTds.get(8).text();
						String sj_date=qygsgdczxxTds.get(9).text();
						String sj_showdate=qygsgdczxxTds.get(10).text();
						EntpubSStohrinvestInfo.Detail  rjDetail=qygsGdjczGdjczInfo.new Detail();
						EntpubSStohrinvestInfo.Detail  sjDetail=qygsGdjczGdjczInfo.new Detail();
						List<Detail> rjDetailList = new ArrayList<EntpubSStohrinvestInfo.Detail>();
						List<Detail> sjDetailList = new ArrayList<EntpubSStohrinvestInfo.Detail>();
						rjDetail.method=rj_method;
						rjDetail.amount=rj_amount;
						rjDetail.dateTime=rj_date;
						rjDetail.showDate=rj_showdate;
						sjDetail.method=sj_method;
						sjDetail.amount=sj_amount;
						sjDetail.dateTime=sj_date;
						sjDetail.showDate=sj_showdate;
						qygsGdjczGdjczInfo.setStockholder(stockholder);
						qygsGdjczGdjczInfo.setSubAmount(rjAmount);
						qygsGdjczGdjczInfo.setPaidAmount(sjAmount);
						rjDetailList.add(rjDetail);
						sjDetailList.add(sjDetail);
						qygsGdjczGdjczInfo.setSubDetails(rjDetailList);
						qygsGdjczGdjczInfo.setPaidDetails(sjDetailList);				    	
				    	
				    	for (int i = 1; i < rowspan; i++) {
				    		EntpubSStohrinvestInfo.Detail  rjDetail2=qygsGdjczGdjczInfo.new Detail();
							EntpubSStohrinvestInfo.Detail  sjDetail2=qygsGdjczGdjczInfo.new Detail();
				    		Elements qygsgdczxxTds2 = qygsgdczxxTrs.get(j+i).select("td");
				    		String rj_method2=qygsgdczxxTds2.get(0).text();
				    		String rj_amount2=qygsgdczxxTds2.get(1).text();				
				    		String rj_date2=qygsgdczxxTds2.get(2).text();						
				    		String rj_showdate2=qygsgdczxxTds2.get(3).text();
							
				    		String sj_method2=qygsgdczxxTds2.get(4).text();
				    		String sj_amount2=qygsgdczxxTds2.get(5).text();
				    		String sj_date2=qygsgdczxxTds2.get(6).text();
				    		String sj_showdate2=qygsgdczxxTds2.get(7).text();
				    		rjDetail2.method=rj_method2;
				    		rjDetail2.amount=rj_amount2;
				    		rjDetail2.dateTime=rj_date2;
				    		rjDetail2.showDate=rj_showdate2;
				    		sjDetail2.method=sj_method2;
				    		sjDetail2.amount=sj_amount2;
				    		sjDetail2.dateTime=sj_date2;
				    		sjDetail2.showDate=sj_showdate2;
				    		rjDetailList.add(rjDetail2);
				    		sjDetailList.add(sjDetail2);
						}
				    	qygsGdjczGdjczList.add(qygsGdjczGdjczInfo);
				   }
					    							
				}				
				qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczList);	
				
				//股东及出资信息-->变更信息
				List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
				Element qygsbgxxDiv = qygsgdczDiv.getElementById("altInv");
				Elements qygsbgxxTrs = qygsbgxxDiv.select("tr");
				for (int j = 2; j < qygsbgxxTrs.size(); j++) {
					Elements qygsbgxxTds = qygsbgxxTrs.get(j).select("td");
					String bgItem = qygsbgxxTds.get(1).text();
					String bgDate = qygsbgxxTds.get(2).text();
					String bgqContent = qygsbgxxTds.get(3).text();
					String bghContent = qygsbgxxTds.get(4).text();
					
					EntpubStohrinvestChangeInfo qygsGdjczBgInfo = new EntpubStohrinvestChangeInfo();
					qygsGdjczBgInfo.setItem(bgItem);
					qygsGdjczBgInfo.setDateTime(bgDate);
					qygsGdjczBgInfo.setPreContent(bgqContent);
					qygsGdjczBgInfo.setPostContent(bghContent);
					qygsGdjczBgInfos.add(qygsGdjczBgInfo);
				}
				if (isDebug) {
					qygsGdjczInfo.setHtml(qygsbgxxDiv.toString());
				}
				qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);
				qygsInfo.setStohrInvestInfo(qygsGdjczInfo);
				
			}
			//-----------------企业公示信息-->股东及出资信息 end-----------------------		 
			//-----------------企业公示信息-->股权变更信息 start-----------------------		
			 //企业公示信息-->股权变更信息
			EntpubEquchangeInfo qygsGqbgInfo =new EntpubEquchangeInfo();
			 List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos=null;
			 if (qygsxxDoc.getElementById("gqbg")!=null) {
				 qygsGqbgGqbgInfos=new ArrayList<EntpubEEquchangeInfo>();
				 Element qygsgqbgxxDiv = qygsxxDoc.getElementById("gqbg");
				 Elements qygsgqbgxxTrs = qygsgqbgxxDiv.select("tr");		
				 for (int j = 2; j < qygsgqbgxxTrs.size(); j++) {
					 Elements qygsgqbgxxTds=qygsgqbgxxTrs.get(j).select("td");
	    			 String stockholder=qygsgqbgxxTds.get(1).text();
					 String bgqOwnershipRatio=qygsgqbgxxTds.get(2).text();
					 String bghOwnershipRatio=qygsgqbgxxTds.get(3).text();
					 String bgDate=qygsgqbgxxTds.get(4).text();
					 String gsrq = qygsgqbgxxTds.get(5).text();
					 EntpubEEquchangeInfo qygsGqbgGqbgInfo=new EntpubEEquchangeInfo();
					 qygsGqbgGqbgInfo.setStockholder(stockholder);
					 qygsGqbgGqbgInfo.setPreOwnershipRatio(bgqOwnershipRatio);
					 qygsGqbgGqbgInfo.setPostOwnershipRatio(bghOwnershipRatio);
					 qygsGqbgGqbgInfo.setDateTime(bgDate);
					 qygsGqbgGqbgInfos.add(qygsGqbgGqbgInfo);
				}
				 				
				if (isDebug) {
						qygsGqbgInfo.setHtml(qygsgqbgxxDiv.toString());
					}
				 qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);
				 qygsInfo.setEquChangeInfo(qygsGqbgInfo); 
			}

			//-----------------企业公示信息-->股权变更信息 end-----------------------		
			//-----------------企业公示信息-->行政许可信息 start-----------------------
			//企业公示信息-->行政许可信息		 
			 EntpubAdmlicInfo qygsXzxkInfo =new EntpubAdmlicInfo();
			 List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos=null;			 
			 String qygsxx_xzxkxx_moreHtml = (String)resultHtmlMap.get("qygsxx_xzxkxx_more");		 
			 if (null !=qygsxx_xzxkxx_moreHtml) {
				 qygsxx_xzxkxx_moreHtml=qygsxx_xzxkxx_moreHtml.substring(1,qygsxx_xzxkxx_moreHtml.length()-1); 
			     Document qygsxx_xzxkxx_moreHtml_doc = Jsoup.parse(qygsxx_xzxkxx_moreHtml);
				 qygsXzxkXzxkInfos=new ArrayList<EntpubAAdmlicInfo>();
				 Element qygsxzxkTable = qygsxx_xzxkxx_moreHtml_doc.getElementById("licenseRegTab");
				 if (null != qygsxzxkTable) {
					 Elements qygsxzxkTrs = qygsxzxkTable.select("tr");	
					 for (int j = 0; j < qygsxzxkTrs.size(); j++) {
						 Elements qygsxzxkTds=qygsxzxkTrs.get(j).select("td");
						 String xkwjNum=qygsxzxkTds.get(1).text();
						 String xkwjName=qygsxzxkTds.get(2).text();
						 String xzxk_startDate=qygsxzxkTds.get(3).text();
						 String xzxk_endDate=qygsxzxkTds.get(4).text();
						 
						 String xkAuthority=qygsxzxkTds.get(5).text();
						 String xkContent=qygsxzxkTds.get(6).text();
						 String status=qygsxzxkTds.get(7).text();
						 String pubDate= qygsxzxkTds.get(8).text();
						 String detail = qygsxzxkTds.get(9).text();							
						 
						 EntpubAAdmlicInfo qygsXzxkXzxkInfo=new EntpubAAdmlicInfo();
						 qygsXzxkXzxkInfo.setLicenceNum(xkwjNum);
						 qygsXzxkXzxkInfo.setLicenceName(xkwjName);
						 qygsXzxkXzxkInfo.setStartDateTime(xzxk_startDate);
						 qygsXzxkXzxkInfo.setEndDateTime(xzxk_endDate);
						 qygsXzxkXzxkInfo.setDeciAuthority(xkAuthority);
						 qygsXzxkXzxkInfo.setContent(xkContent);
						 qygsXzxkXzxkInfo.setStatus(status);
						 qygsXzxkXzxkInfo.setPubDate(pubDate);
						 qygsXzxkXzxkInfo.setDetail(detail);	
						
						 qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
					}
				}
	    		qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
			}			
		   qygsInfo.setAdmLicInfo(qygsXzxkInfo); 
		//-----------------企业公示信息-->行政许可信息 end-----------------------
			 
		//-----------------企业公示信息-->知识产权出质登记信息 start-----------------------	
				 
		EntpubIntellectualproregInfo qygsZscqczdjInfo =new EntpubIntellectualproregInfo();
			 List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos=null;
			 if (qygsxxDoc.getElementById("zscqDiv")!=null) {
				 qygsZscqczdjZscqczdjInfos=new ArrayList<EntpubIIntellectualproregInfo>();
				 Element qygszscqdjxxDiv = qygsxxDoc.getElementById("zscqDiv");
				 Elements qygszscqdjxxTrs = qygszscqdjxxDiv.select("tr");	
				 for (int j = 2; j < qygszscqdjxxTrs.size(); j++) {
					 Elements qygszscqdjxxTds=qygszscqdjxxTrs.get(j).select("td");
					 String regNum=qygszscqdjxxTds.get(1).text();
					 String zscq_name=qygszscqdjxxTds.get(2).text();
					 String zscq_type=qygszscqdjxxTds.get(3).text();
					 String czrName=qygszscqdjxxTds.get(4).text();
					 
					 String zqrName=qygszscqdjxxTds.get(5).text();
					 String zqdjDeadline=qygszscqdjxxTds.get(6).text();
					 String status=qygszscqdjxxTds.get(7).text();
					 String changeSitu=qygszscqdjxxTds.get(8).text();
					 
					 EntpubIIntellectualproregInfo qygsZscqczdjZscqczdjInfo=new EntpubIIntellectualproregInfo();
					 qygsZscqczdjZscqczdjInfo.setRegNum(regNum);
					 qygsZscqczdjZscqczdjInfo.setName(zscq_name);
					 qygsZscqczdjZscqczdjInfo.setType(zscq_type);
					 qygsZscqczdjZscqczdjInfo.setMortgagorName(czrName);
					 qygsZscqczdjZscqczdjInfo.setMortgageeName(zqrName);
					 qygsZscqczdjZscqczdjInfo.setPledgeRegDeadline(zqdjDeadline);
					 qygsZscqczdjZscqczdjInfo.setStatus(status);
					 qygsZscqczdjZscqczdjInfo.setChangeSitu(changeSitu);
					 qygsZscqczdjZscqczdjInfos.add(qygsZscqczdjZscqczdjInfo);
				}				 
				if (isDebug) {
						qygsZscqczdjInfo.setHtml(qygszscqdjxxDiv.toString());
					}
				 qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);
			}
			 qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo); 		 
	    //-----------------企业公示信息-->知识产权出质登记信息 end-----------------------
			 

	     //-----------------企业公示信息-->行政处罚信息 start-----------------------			 
			 EntpubAdmpunishInfo qygsXzcfInfo =new EntpubAdmpunishInfo();
			 List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos=new ArrayList<EntpubAAdmpunishInfo>();
			 Element qygsxzcfxxDiv = qygsxxDoc.getElementById("xzcfDiv");
			 if (qygsxxDoc.getElementById("xzcfDiv")!=null) {
				 Elements qygsxzcfxxTrs = qygsxzcfxxDiv.select("tr");	
				 for (int j = 2; j < qygsxzcfxxTrs.size(); j++) {
					 Elements qygsxzcfxxTds=qygsxzcfxxTrs.get(j).select("td");
					 String xzcfjdsNum=qygsxzcfxxTds.get(1).text();
					 String xzcfContent=qygsxzcfxxTds.get(2).text();
					 String zcxzcfjdjgName=qygsxzcfxxTds.get(3).text();
					 String zcxzcfjdDate=qygsxzcfxxTds.get(4).text();
					 
					 String wfxwType=qygsxzcfxxTds.get(5).text();
					 String note=qygsxzcfxxTds.get(6).text();
				 
					 EntpubAAdmpunishInfo qygsXzcfXzcfInfo=new EntpubAAdmpunishInfo();
					 qygsXzcfXzcfInfo.setPunishRepNum(xzcfjdsNum);
					 qygsXzcfXzcfInfo.setPunishContent(xzcfContent);
					 qygsXzcfXzcfInfo.setDeciAuthority(zcxzcfjdjgName);
					 qygsXzcfXzcfInfo.setDeciDateTime(zcxzcfjdDate);
					 qygsXzcfXzcfInfo.setIllegalActType(wfxwType);
					 qygsXzcfXzcfInfo.setNote(note);
					 qygsXzcfXzcfInfos.add(qygsXzcfXzcfInfo);
				}
				if (isDebug) {
					qygsXzcfInfo.setHtml(qygsxzcfxxDiv.toString());
				}
				 
				 qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);
			}
			 
			 qygsInfo.setAdmPunishInfo(qygsXzcfInfo); 			 
			 gsxtFeedJson.setEntPubInfo(qygsInfo);
		//-----------------企业公示信息-->行政处罚信息 end-----------------------	
			 
		   //三 其他部门公示信息
		 	 
			 OthrdeptpubInfo  qtbmgsInfo=new OthrdeptpubInfo();
			 String qtbmgsxxHtml = (String)resultHtmlMap.get("qtbmgsxx");
			 Document qtbmgsxxHtmlDoc = Jsoup.parse(qtbmgsxxHtml);
				
			//-----------------其他部门公示信息-->行政许可信息 start-----------------------
			
			 OthrdeptpubAdmlicInfo qtbmgsXzxkInfo=new OthrdeptpubAdmlicInfo();
			 List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos =new 	ArrayList<OthrdeptpubAAdmlicInfo>();
			 Element qtbmxzxkxxDiv = qtbmgsxxHtmlDoc.getElementById("licenseRegDiv");
			 Elements qtbmxzxkxxTrs=qtbmxzxkxxDiv.select("tr");
				for (Element qtbmxzxkxxTr: qtbmxzxkxxTrs) {
					 Elements qtbmxzxkxxTds=qtbmxzxkxxTr.select("td");
					 String xkwjNum=qtbmxzxkxxTds.get(1).text();
					 String xkwjName=qtbmxzxkxxTds.get(2).text();
					 String xzxk_startDate=qtbmxzxkxxTds.get(3).text();
					 String xzxk_endDate=qtbmxzxkxxTds.get(4).text();
					 
					 String xkAuthority=qtbmxzxkxxTds.get(5).text();
					 String xkContent=qtbmxzxkxxTds.get(6).text();
					 String status=qtbmxzxkxxTds.get(7).text();
					 String detail=qtbmxzxkxxTds.get(8).text();
					 
					 OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo=new OthrdeptpubAAdmlicInfo();
					 
					 qtbmgsXzxkXzxkInfo.setLicenceNum(xkwjNum);
					 qtbmgsXzxkXzxkInfo.setLicenceName(xkwjName);
					 qtbmgsXzxkXzxkInfo.setStartDateTime(xzxk_startDate);
					 qtbmgsXzxkXzxkInfo.setEndDateTime(xzxk_endDate);
					 qtbmgsXzxkXzxkInfo.setDeciAuthority(xkAuthority);
					 qtbmgsXzxkXzxkInfo.setContent(xkContent);
					 qtbmgsXzxkXzxkInfo.setStatus(status);
					 qtbmgsXzxkXzxkInfo.setDetail(detail);
					 qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
				}
				if (isDebug) {
					qtbmgsXzxkInfo.setHtml(qtbmxzxkxxDiv.toString());
				}
				qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);
				qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);
			   //-----------------其他部门公示信息-->行政许可信息 end----------------------- 
				 //-----------------其他部门公示信息-->行政处罚信息 start-----------------------
			
				OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo=new OthrdeptpubAdmpunishInfo();
				List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos =new ArrayList<OthrdeptpubAAdmpunishInfo>();
				 Element qtbmxzcfxxDiv = qtbmgsxxHtmlDoc.getElementById("xzcfDiv");
				 Elements qtbmxzcfxxTrs=qtbmxzcfxxDiv.select("tr");
				 for (int j = 2; j < qtbmxzcfxxTrs.size(); j++) {
					 Elements qtbmxzcfxxTds=qtbmxzcfxxTrs.get(j).select("td");
					 String xzcfjdsNum=qtbmxzcfxxTds.get(1).text();
					 String wfxwType=qtbmxzcfxxTds.get(2).text();
					 String xzcfContent=qtbmxzcfxxTds.get(3).text();
					 String zcxzcfjdjgName=qtbmxzcfxxTds.get(4).text();
					 String zcxzcfjdDate=qtbmxzcfxxTds.get(5).text();
					 String detail=qtbmxzcfxxTds.get(6).text();
					 String note=qtbmxzcfxxTds.get(7).text();
					 
					 OthrdeptpubAAdmpunishInfo qtbmgsXzcfXzcfInfo=new OthrdeptpubAAdmpunishInfo();
					 qtbmgsXzcfXzcfInfo.setPunishRepNum(xzcfjdsNum);
					 qtbmgsXzcfXzcfInfo.setIllegalActType(wfxwType);
					 qtbmgsXzcfXzcfInfo.setPunishContent(xzcfContent);
					 qtbmgsXzcfXzcfInfo.setDeciAuthority(zcxzcfjdjgName);
					 qtbmgsXzcfXzcfInfo.setDeciDateTime(zcxzcfjdDate);
					 qtbmgsXzcfXzcfInfo.setDetail(detail);
					 qtbmgsXzcfXzcfInfo.setNote(note);
					 qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
				}			 
				if (isDebug) {
						qtbmgsXzcfInfo.setHtml(qtbmxzcfxxDiv.toString());
					}
				 qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);
				 qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);				
				 gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);
			   //-----------------其他部门公示信息-->行政处罚信息 end----------------------- 
				
				  //四  司法协助公示信息
				//-----------------司法协助公示信息-->司法股权冻结信息start----------------------- 
				 
				 JudasspubInfo sfxzgsInfo =new JudasspubInfo();
				 JudasspubEqufreezeInfo sfxzgsGqdjInfo =new JudasspubEqufreezeInfo();
				 String sfxzgqdjxxHtml = (String)resultHtmlMap.get("sfxzgsxx");
				if (sfxzgqdjxxHtml!=null) {
					 Document sfxzgqdjxxDoc = Jsoup.parse(sfxzgqdjxxHtml);
					 List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos=new ArrayList<JudasspubEEqufreezeInfo>();
					 Element  sfxzgqdjxxDiv=sfxzgqdjxxDoc.getElementById("EquityFreezeDiv");
					 Elements sfxzgqdjxxTrs=sfxzgqdjxxDiv.select("tr");
					 for (int j = 2; j < sfxzgqdjxxTrs.size();j++) {
						 Elements sfxzgqdjxxTds=sfxzgqdjxxTrs.get(j).select("td");
						 String bzxPerson=sfxzgqdjxxTds.get(1).text();
						 String gqAmount=sfxzgqdjxxTds.get(2).text();
						 String exeCourt=sfxzgqdjxxTds.get(3).text();
						 String xzgstzsNum=sfxzgqdjxxTds.get(4).text();
						 String status=sfxzgqdjxxTds.get(5).text();
						 String detail=sfxzgqdjxxTds.get(6).text();
						 
						 JudasspubEEqufreezeInfo sfxzgsGqdjGqdjInfo=new JudasspubEEqufreezeInfo();
						 sfxzgsGqdjGqdjInfo.setExecutedPerson(bzxPerson);
						 sfxzgsGqdjGqdjInfo.setEquAmount(gqAmount);
						 sfxzgsGqdjGqdjInfo.setExeCourt(exeCourt);
						 sfxzgsGqdjGqdjInfo.setAssistPubNoticeNum(xzgstzsNum);
						 sfxzgsGqdjGqdjInfo.setStatus(status);
						 sfxzgsGqdjGqdjInfo.setDetail(detail);
						 sfxzgsGqdjGqdjInfos.add(sfxzgsGqdjGqdjInfo);
					}				 
					 
					if (isDebug) {
							sfxzgsGqdjInfo.setHtml(sfxzgqdjxxDiv.toString());
						} 
					 sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);
					 sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);
					//-----------------司法协助公示信息-->司法股权冻结信息end----------------------- 
					 
					//-----------------司法协助公示信息-->股东变更信息start----------------------- 			
					 JudasspubStohrchangeInfo sfxzgsGdbgInfo =new JudasspubStohrchangeInfo();
					 List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos=new ArrayList<JudasspubSStohrchangeInfo>();
					 Element  sfxzgdbgxxDiv=sfxzgqdjxxDoc.getElementById("xzcfDiv");
					 Elements  sfxzgdbgxxTrs=sfxzgdbgxxDiv.select("tr");
					 for (int j = 2; j < sfxzgdbgxxTrs.size(); j++) {
						 Elements sfxzgdbgxxTds=sfxzgdbgxxTrs.get(j).select("td");
						 String bzxPerson=sfxzgdbgxxTds.get(1).text();
						 String gqAmount=sfxzgdbgxxTds.get(2).text();
						 String srPerson=sfxzgdbgxxTds.get(3).text();
						 String exeCourt=sfxzgdbgxxTds.get(4).text();
						 String detail=sfxzgdbgxxTds.get(5).text();
						 
						 JudasspubSStohrchangeInfo sfxzgsGdbgGdbgInfo=new JudasspubSStohrchangeInfo();
						 sfxzgsGdbgGdbgInfo.setExecutedPerson(bzxPerson);
						 sfxzgsGdbgGdbgInfo.setEquAmount(gqAmount);
						 sfxzgsGdbgGdbgInfo.setAssignee(srPerson);
						 sfxzgsGdbgGdbgInfo.setExeCourt(exeCourt);
						 sfxzgsGdbgGdbgInfo.setDetail(detail);
						 sfxzgsGdbgGdbgInfos.add(sfxzgsGdbgGdbgInfo);
					}
						if (isDebug) {
							sfxzgsGdbgInfo.setHtml(sfxzgdbgxxDiv.toString());
						}
					 sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);
					 sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);
					 gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);
				}
			//-----------------司法协助公示信息-->股东变更信息end----------------------- 	
		return gsxtFeedJson;
	}

}

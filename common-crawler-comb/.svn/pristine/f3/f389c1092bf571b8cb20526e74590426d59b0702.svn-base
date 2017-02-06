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
import com.crawler.gsxt.domain.json.AicpubCChatMortgChangeInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgDetail;
import com.crawler.gsxt.domain.json.AicpubCChatMortgGoodsInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgGuaranteedInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgPersonInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgRegInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgRevokeInfo;
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
import com.crawler.gsxt.domain.json.EntpubAnnreportExtinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportInfo;
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
import com.crawler.gsxt.domain.json.OthrdeptpubAAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtBeijingParser {

	public AicFeedJson beijingResultParser(String resultHtmls, Boolean isDebug) {
		//解析result
		Gson gson = new Gson();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		
		AicFeedJson gsxtFeedJson = new AicFeedJson();	
		
		//一、工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();
		
		//----------工商公示信息-->登记信息   start-------------
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();
		
		//1.基本信息
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();
		
		Object gsgsxx_djxx_jbxx_object = resultHtmlMap.get("gsgsxx_djxx_jbxx");
		if (gsgsxx_djxx_jbxx_object != null) {
			String gsgsxxDjjbHtml = (String)gsgsxx_djxx_jbxx_object;
			Document gsgsxxDjjbDoc = Jsoup.parse(gsgsxxDjjbHtml);
			Element gsgsxxDjjbDiv = gsgsxxDjjbDoc.getElementById("jbxx");
			Elements gsgsxxDjjbTable = gsgsxxDjjbDiv.select("table");
			Elements gsgsxxDjjbThs = gsgsxxDjjbTable.select("th");
			Elements gsgsxxDjjbTds = gsgsxxDjjbTable.select("td");
			gsgsxxDjjbThs.remove(0);
			for (int i = 0; i < gsgsxxDjjbThs.size(); i++) {
				String gsgsxxDjjbTh = gsgsxxDjjbThs.get(i).text();
				String gsgsxxDjjbTd = gsgsxxDjjbTds.get(i).text();
				
				switch (gsgsxxDjjbTh) {
				case "注册号":
					gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
					break;
				case "统一社会信用代码":
					gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
					break;
				case "注册号/统一社会信用代码":
					gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
					break;
				case "名称":
					gsgsDjJbInfo.setName(gsgsxxDjjbTd);
					break;
				case "类型":
					gsgsDjJbInfo.setType(gsgsxxDjjbTd);
					break;
				case "法定代表人":
					gsgsDjJbInfo.setLegalRepr(gsgsxxDjjbTd);
					break;
				case "负责人":
					gsgsDjJbInfo.setLegalRepr(gsgsxxDjjbTd);
					break;
				case "注册资本":
					gsgsDjJbInfo.setRegCapital(gsgsxxDjjbTd);
					break;
				case "成立日期":
					gsgsDjJbInfo.setRegDateTime(gsgsxxDjjbTd);
					break;
				case "住所":
					gsgsDjJbInfo.setAddress(gsgsxxDjjbTd);
					break;
				case "经营场所":
					gsgsDjJbInfo.setAddress(gsgsxxDjjbTd);
					break;
				case "营业场所":
					gsgsDjJbInfo.setAddress(gsgsxxDjjbTd);
					break;
				case "营业期限自":
					gsgsDjJbInfo.setStartDateTime(gsgsxxDjjbTd);
					break;
				case "经营期限自":
					gsgsDjJbInfo.setStartDateTime(gsgsxxDjjbTd);
					break;
				case "营业期限至":
					gsgsDjJbInfo.setEndDateTime(gsgsxxDjjbTd);
					break;
				case "经营期限至":
					gsgsDjJbInfo.setEndDateTime(gsgsxxDjjbTd);
					break;
				case "经营范围":
					gsgsDjJbInfo.setBusinessScope(gsgsxxDjjbTd);
					break;
				case "登记机关":
					gsgsDjJbInfo.setRegAuthority(gsgsxxDjjbTd);
					break;
				case "核准日期":
					gsgsDjJbInfo.setApprovalDateTime(gsgsxxDjjbTd);
					break;
				case "登记状态":
					gsgsDjJbInfo.setRegStatus(gsgsxxDjjbTd);
					break;
				case "吊销日期":
					gsgsDjJbInfo.setRevokeDate(gsgsxxDjjbTd);
					break;
				default:
					break;
				}
			}
		}
		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);
		
		//2.股东信息    详情
		List<AicpubRegStohrStohrinvestInfo> gsgsDjGdGdjczInfos = new ArrayList<AicpubRegStohrStohrinvestInfo>();
		Object gsgsxx_djxx_tzrxx_xq_object = resultHtmlMap.get("gsgsxx_djxx_tzrxx_xq");
		if (gsgsxx_djxx_tzrxx_xq_object != null) {
			ArrayList<String> gdDetailList = (ArrayList<String>)gsgsxx_djxx_tzrxx_xq_object;
			for (String gdxxDetailHtml : gdDetailList) {
				Document gsgsxxDjgdxqDoc = Jsoup.parse(gdxxDetailHtml);
				Elements gdxxDetailTables = gsgsxxDjgdxqDoc.select("table");
				if (gdxxDetailTables != null && !gdxxDetailTables.isEmpty()) {
					Element gdxxDetailTable = gdxxDetailTables.get(0);
					if (gdxxDetailTable != null) {
						Elements gdxxDetailTrs = gdxxDetailTable.select("tr");
						
						AicpubRegStohrStohrinvestInfo gsgsDjGdjczInfo = new AicpubRegStohrStohrinvestInfo();
						for (Element gdxxDetailTr : gdxxDetailTrs) {
							Elements gdxxDetail_tds = gdxxDetailTr.select("td");
							int tdSize = gdxxDetail_tds.size();
							if (tdSize == 3) {
								String stockholder = gdxxDetail_tds.get(0).text();
								String subAmount = gdxxDetail_tds.get(1).text();
								String paidAmount = gdxxDetail_tds.get(2).text();
								
								gsgsDjGdjczInfo.setStockholder(stockholder);
								gsgsDjGdjczInfo.setSubAmount(subAmount);
								gsgsDjGdjczInfo.setPaidAmount(paidAmount);
							} else if (tdSize == 6) {
								String sub_method = gdxxDetail_tds.get(0).text();
								String sub_amount = gdxxDetail_tds.get(1).text();
								String sub_czDate = gdxxDetail_tds.get(2).text();
								String paid_method = gdxxDetail_tds.get(3).text();
								String paid_amount = gdxxDetail_tds.get(4).text();
								String paid_czDate = gdxxDetail_tds.get(5).text();
								
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
								subAmountDetailList.add(subAmountDetail);
								paidAmountDetailList.add(paidAmountDetail);
								gsgsDjGdjczInfo.setSubAmountDetails(subAmountDetailList);
								gsgsDjGdjczInfo.setPaidAmountDetails(paidAmountDetailList);
							} else if (tdSize == 9) {
								String stockholder = gdxxDetail_tds.get(0).text();
								String subAmount = gdxxDetail_tds.get(1).text();
								String paidAmount = gdxxDetail_tds.get(2).text();
								String sub_method = gdxxDetail_tds.get(3).text();
								String sub_amount = gdxxDetail_tds.get(4).text();
								String sub_czDate = gdxxDetail_tds.get(5).text();
								String paid_method = gdxxDetail_tds.get(6).text();
								String paid_amount = gdxxDetail_tds.get(7).text();
								String paid_czDate = gdxxDetail_tds.get(8).text();
								
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
							}
						}
						
						gsgsDjGdGdjczInfos.add(gsgsDjGdjczInfo);
					}
				}
			}
		}
		
		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
		Object gsgsxx_djxx_tzrxx_object = resultHtmlMap.get("gsgsxx_djxx_tzrxx");
		if (gsgsxx_djxx_tzrxx_object != null) {
			String gsgsxxDjgdHtml = (String)gsgsxx_djxx_tzrxx_object;
			Document gsgsxxDjgdDoc = Jsoup.parse(gsgsxxDjgdHtml);
			Element gsgsxxDjgdTable = gsgsxxDjgdDoc.getElementById("touziren");
			Elements gdxx_trs = gsgsxxDjgdTable.select("tr");
			int i = 0;
			for (Element gdxx_tr : gdxx_trs) {
				if(!"".equals(gdxx_tr.attr("id"))){
					Elements gdxx_tds = gdxx_tr.select("td");
					
					AicpubRegStohrInfo gsgsdjgdInfo = new AicpubRegStohrInfo();
					int trSize = gdxx_tds.size();
					if(trSize > 0) {
						String gdType = gdxx_tds.get(0).text();
						gsgsdjgdInfo.setType(gdType);
					}
					if (trSize > 1) {
						String gdName = gdxx_tds.get(1).text();
						gsgsdjgdInfo.setName(gdName);
					}
					if (trSize > 2) {
						String idType = gdxx_tds.get(2).text();
						gsgsdjgdInfo.setIdType(idType);
					}
					if (trSize > 3) {
						String idNum = gdxx_tds.get(3).text();
						gsgsdjgdInfo.setIdNum(idNum);
					}
					if (trSize > 4) {
						String gdxq = gdxx_tds.get(4).text();
						if (!"".equals(gdxq)) {
							if (gsgsDjGdGdjczInfos.size() > i) {
								gsgsdjgdInfo.setStohrInvestInfo(gsgsDjGdGdjczInfos.get(i++));
							}
						}
					} 
					
					gsgsDjGdInfos.add(gsgsdjgdInfo);
				}
			}
		}
		
		gsgsDjInfo.setStohrInfos(gsgsDjGdInfos);
		
		//3.变更信息（包括变更信息详细）
		/*变更信息详细*/
		List<AicpubRegChangeInfo.ChangeDetail> bgDetailList = new ArrayList<AicpubRegChangeInfo.ChangeDetail>();
		
		Object gsgsxx_djxx_bgxx_xq_object = resultHtmlMap.get("gsgsxx_djxx_bgxx_xq");
		if (gsgsxx_djxx_bgxx_xq_object != null) {
			List<String> gsgsxxDjbgxqHtmlList = (List<String>)gsgsxx_djxx_bgxx_xq_object;
			for (String gsgsxxDjbgxqHtml : gsgsxxDjbgxqHtmlList) {
				Document gsgsxxDjbgxqDoc = Jsoup.parse(gsgsxxDjbgxqHtml);
				Elements gsgsxxDjbgxqTables = gsgsxxDjbgxqDoc.select("table");
				if (gsgsxxDjbgxqTables != null && gsgsxxDjbgxqTables.size() > 2) {
					Element bgQxxTable = gsgsxxDjbgxqTables.get(1);
					Elements bgQxxTrs = bgQxxTable.select("tr");
					bgQxxTrs.remove(0);
					bgQxxTrs.remove(0);
					Element bgHxxTable = gsgsxxDjbgxqTables.get(2);
					Elements bgHxxTrs = bgHxxTable.select("tr");
					bgHxxTrs.remove(0);
					bgHxxTrs.remove(0);
					
					AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();
					AicpubRegChangeInfo.ChangeDetail bgDetail = gsgsDjBgInfo.new ChangeDetail();
					
					List<AicpubRegChangeInfo.ChangeInfo> bgqInfos = new ArrayList<AicpubRegChangeInfo.ChangeInfo>();
					for (Element bgQxxTr : bgQxxTrs) {
						Elements bgQxxTds = bgQxxTr.select("td");
						if (bgQxxTds.size() == 3) {
							String name = bgQxxTds.get(1).text();
							String typeOrPosition = bgQxxTds.get(2).text();
							
							AicpubRegChangeInfo.ChangeInfo bgQInfo = gsgsDjBgInfo.new ChangeInfo();
							bgQInfo.name = name;
							bgQInfo.typeOrPosition = typeOrPosition;
							
							bgqInfos.add(bgQInfo);
						}
					}
					
					List<AicpubRegChangeInfo.ChangeInfo> bghInfos = new ArrayList<AicpubRegChangeInfo.ChangeInfo>();
					for (Element bgHxxTr : bgHxxTrs) {
						Elements bgHxxTds = bgHxxTr.select("td");
						if (bgHxxTds.size() == 3) {
							String name = bgHxxTds.get(1).text();
							String typeOrPosition = bgHxxTds.get(2).text();
							
							AicpubRegChangeInfo.ChangeInfo bgQInfo = gsgsDjBgInfo.new ChangeInfo();
							bgQInfo.name = name;
							bgQInfo.typeOrPosition = typeOrPosition;
							
							bghInfos.add(bgQInfo);
						}
					}
					bgDetail.preInfos = bgqInfos;
					bgDetail.postInfos = bghInfos;
					
					bgDetailList.add(bgDetail);
				}
			}
		}
		
		/*变更信息*/
		List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<AicpubRegChangeInfo>();
		Object gsgsxx_djxx_bgxx_object = resultHtmlMap.get("gsgsxx_djxx_bgxx");
		if (gsgsxx_djxx_bgxx_object != null) {
			String gsgsxxDjbgHtml = (String)gsgsxx_djxx_bgxx_object;
			Document gsgsxxDjbgDoc = Jsoup.parse(gsgsxxDjbgHtml);
			Element gsgsxxDjbgTable = gsgsxxDjbgDoc.getElementById("touziren");
			Elements bgxx_trs = gsgsxxDjbgTable.select("tr");
			
			int j = 0;
			for (Element bgxx_tr : bgxx_trs) {
				Elements bgxx_tds = bgxx_tr.getElementsByTag("td");
				AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();
				if (bgxx_tds.size() == 4) {
					String bgItem = bgxx_tds.get(0).text();
					String bgqContent = bgxx_tds.get(1).text();
					String bghContent = bgxx_tds.get(2).text();
					String bgDate = bgxx_tds.get(3).text();
					
					gsgsDjBgInfo.setItem(bgItem);
					gsgsDjBgInfo.setPreContent(bgqContent);
					gsgsDjBgInfo.setPostContent(bghContent);
					gsgsDjBgInfo.setDateTime(bgDate);
					gsgsDjBgInfos.add(gsgsDjBgInfo);
				} else if(bgxx_tds.size() == 3) {
					String bgItem = bgxx_tds.get(0).text();
					String bgDate = bgxx_tds.get(2).text();
					
					gsgsDjBgInfo.setItem(bgItem);
					gsgsDjBgInfo.setDetail(bgDetailList.get(j++));
					gsgsDjBgInfo.setDateTime(bgDate);
					gsgsDjBgInfos.add(gsgsDjBgInfo);
				}
				
			}
		}
		gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);
		
		gsgsInfo.setRegInfo(gsgsDjInfo);
		//----------工商公示信息-->登记信息  end-------------
		
		
		//----------工商公示信息-->备案信息  start-------------
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
		
		//备案信息-->主要人员信息
		List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
		Object gsgsxx_baxx_zyryxx_object = resultHtmlMap.get("gsgsxx_baxx_zyryxx");
		if (gsgsxx_baxx_zyryxx_object != null) {
			
			String gsgsBaZyryHtml = (String)gsgsxx_baxx_zyryxx_object;
			Document gsgsBaZyryDoc = Jsoup.parse(gsgsBaZyryHtml);
			Element gsgsBaZyryTable = gsgsBaZyryDoc.getElementById("touziren");
			Elements gsgsBaZyryTrs = gsgsBaZyryTable.select("tr");
			gsgsBaZyryTrs.remove(0);
			gsgsBaZyryTrs.remove(0);
			for (Element zyryTr : gsgsBaZyryTrs) {
				Elements zyryTdElements = zyryTr.select("td");
				if(zyryTdElements.size() == 3) {
					String zyry_name = zyryTdElements.get(1).text();
					String zyry_position = zyryTdElements.get(2).text();
					if(!"".equals(zyry_name)) {
						AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
						gsgsBaZyryInfo.setName(zyry_name);
						gsgsBaZyryInfo.setPosition(zyry_position);
						gsgsBaZyryInfos.add(gsgsBaZyryInfo);
					}
				}
				
				if(zyryTdElements.size() == 6) {
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
			}
		}
		gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);
		
		//备案信息-->分支机构信息
		List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
		Object gsgsxx_baxx_fzjgxx_object = resultHtmlMap.get("gsgsxx_baxx_fzjgxx");
		if (gsgsxx_baxx_fzjgxx_object != null) {
			String gsgsBaFzjgHtml = (String)gsgsxx_baxx_fzjgxx_object;
			Document gsgsBaFzjgDoc = Jsoup.parse(gsgsBaFzjgHtml);
			Element gsgsBaFzjgTable = gsgsBaFzjgDoc.getElementById("touziren");
			Elements gsgsBaFzjgTrs = gsgsBaFzjgTable.select("tr");
			gsgsBaFzjgTrs.remove(0);
			gsgsBaFzjgTrs.remove(0);
			for (Element gsgsBaFzjgTr : gsgsBaFzjgTrs) {
				Elements gsgsBaFzjgTds = gsgsBaFzjgTr.select("td");
				if (gsgsBaFzjgTds.size() == 4) {
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
		}
		gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);
		
		//备案信息-->清算信息
		AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
		Object gsgsxx_baxx_qsxx_object = resultHtmlMap.get("gsgsxx_baxx_qsxx");
		if (gsgsxx_baxx_qsxx_object != null) {
			String gsgsBaQsHtml = (String)gsgsxx_baxx_qsxx_object;
			Document gsgsBaQsDoc = Jsoup.parse(gsgsBaQsHtml);
			Elements gsgsBaQsTables = gsgsBaQsDoc.select("table");
			if (gsgsBaQsTables != null && !gsgsBaQsTables.isEmpty()) {
				Element gsgsBaQsTable = gsgsBaQsTables.get(0);
				Elements gsgsBaQsTrs = gsgsBaQsTable.select("tr");
				
				Elements gsgsBaQsTds = gsgsBaQsTrs.select("td");
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
		
		//备案信息-->主管部门（出资人）信息
		List<AicpubArchiveMainDeptInfo> aicpubArchiveMainDeptInfos = new ArrayList<AicpubArchiveMainDeptInfo>();
		Object gsgsxx_baxx_zgbmxx_object = resultHtmlMap.get("gsgsxx_baxx_zgbmxx");
		if (gsgsxx_baxx_zgbmxx_object != null) {
			String gsgsBaZgbmHtml = (String)gsgsxx_baxx_zgbmxx_object;
			Document gsgsBaZgbmDoc = Jsoup.parse(gsgsBaZgbmHtml);
			Element gsgsBaZgbmTable = gsgsBaZgbmDoc.getElementById("touziren");
			Elements gsgsBaZgbmTrs = gsgsBaZgbmTable.select("tr");
			for (Element gsgsBaZgbmTr : gsgsBaZgbmTrs) {
				Elements gsgsBaZgbmTds = gsgsBaZgbmTr.select("td");
				if (gsgsBaZgbmTds.size() == 5) {
					String type = gsgsBaZgbmTds.get(1).text();
					String name = gsgsBaZgbmTds.get(2).text();
					String idType = gsgsBaZgbmTds.get(3).text();
					String idNum = gsgsBaZgbmTds.get(4).text();
					AicpubArchiveMainDeptInfo deptInfo = new AicpubArchiveMainDeptInfo();
					deptInfo.setType(type);
					deptInfo.setName(name);
					deptInfo.setIdType(idType);
					deptInfo.setIdNum(idNum);
					aicpubArchiveMainDeptInfos.add(deptInfo);
				}
			}
		}
		gsgsBaInfo.setMainDeptInfo(aicpubArchiveMainDeptInfos);
		
		//----------工商公示信息-->备案信息  end-------------
		
		//-----------------工商公示信息-->动产抵押登记信息 start-----------------------
		//1.详情
		List<AicpubCChatMortgDetail> mortgDetails = new ArrayList<AicpubCChatMortgDetail>();
		Object gsgsxx_dcdydjxx_details_object = resultHtmlMap.get("gsgsxx_dcdydjxx_details");
		if (gsgsxx_dcdydjxx_details_object != null) {
			List<Map<String, Object>> gsgsDcdydjDetailHtmls = (List<Map<String, Object>>)gsgsxx_dcdydjxx_details_object;
			for (Map<String, Object> dcdyDetailMap : gsgsDcdydjDetailHtmls) {
				AicpubCChatMortgDetail mortgDetail = new AicpubCChatMortgDetail();
				
				//获取动产抵押登记信息详情 (动产抵押登记信息|被担保债权概况|注销)
				Object gsgsxx_dcdydjxx_detail_page_object = dcdyDetailMap.get("gsgsxx_dcdydjxx_detail_page");
				if (gsgsxx_dcdydjxx_detail_page_object != null) {
					String gsgsxx_dcdydjxx_detail_page = (String)gsgsxx_dcdydjxx_detail_page_object;
					Document gsgsDcdydjDetailDoc = Jsoup.parse(gsgsxx_dcdydjxx_detail_page);
					Elements gsgsDcdydjDetailTables = gsgsDcdydjDetailDoc.select("table");
					for (Element gsgsDcdydjDetailTable : gsgsDcdydjDetailTables) {
						String gsgsDcdydjDetailTitle = gsgsDcdydjDetailTable.select("th").get(0).text();
						
						Elements gsgsDcdydjDetailThs = gsgsDcdydjDetailTable.select("th");
						Elements gsgsDcdydjDetailTds = gsgsDcdydjDetailTable.select("td");
						gsgsDcdydjDetailThs.remove(0);
						if ("动产抵押登记信息".equals(gsgsDcdydjDetailTitle)) {
							AicpubCChatMortgRegInfo mortgRegInfo = new AicpubCChatMortgRegInfo();
							
							for (int i = 0; i < gsgsDcdydjDetailThs.size(); i++) {
								String gsgsDcdydjDetailTh = gsgsDcdydjDetailThs.get(i).text().replace(" ", "");
								String gsgsDcdydjDetailTd = gsgsDcdydjDetailTds.get(i).text().replace(" ", "");
								
								switch (gsgsDcdydjDetailTh) {
									case "登记编号":
										mortgRegInfo.setRegNum(gsgsDcdydjDetailTd);
										break;
									case "登记日期":
										mortgRegInfo.setRegDate(gsgsDcdydjDetailTd);
										break;
									case "登记机关":
										mortgRegInfo.setRegAuthority(gsgsDcdydjDetailTd);
										break;
									default:
										break;
								}
							}
							
							mortgDetail.setMortgRegInfo(mortgRegInfo);
							
						} else if ("被担保债权概况".equals(gsgsDcdydjDetailTitle)) {
							AicpubCChatMortgGuaranteedInfo mortgGuaranteedInfo = new AicpubCChatMortgGuaranteedInfo();
							
							for (int i = 0; i < gsgsDcdydjDetailThs.size(); i++) {
								String gsgsDcdydjDetailTh = gsgsDcdydjDetailThs.get(i).text().replace(" ", "");
								String gsgsDcdydjDetailTd = gsgsDcdydjDetailTds.get(i).text().replace(" ", "");
								
								switch (gsgsDcdydjDetailTh) {
									case "种类":
										mortgGuaranteedInfo.setCategory(gsgsDcdydjDetailTd);
										break;
									case "数额":
										mortgGuaranteedInfo.setAmount(gsgsDcdydjDetailTd);
										break;
									case "担保的范围":
										mortgGuaranteedInfo.setGuarantyScope(gsgsDcdydjDetailTd);
										break;
									case "债务人履行债务的期限":
										mortgGuaranteedInfo.setTerm(gsgsDcdydjDetailTd);
										break;
									case "备注":
										mortgGuaranteedInfo.setNote(gsgsDcdydjDetailTd);
										break;
									default:
										break;
								}
								
							}
							
							mortgDetail.setMortgGuaranteedInfo(mortgGuaranteedInfo);
							
						} else if ("注销".equals(gsgsDcdydjDetailTitle)) {
							AicpubCChatMortgRevokeInfo mortgRevokeInfo = new AicpubCChatMortgRevokeInfo();
							
							for (int i = 0; i < gsgsDcdydjDetailThs.size(); i++) {
								String gsgsDcdydjDetailTh = gsgsDcdydjDetailThs.get(i).text().replace(" ", "");
								String gsgsDcdydjDetailTd = gsgsDcdydjDetailTds.get(i).text().replace(" ", "");
								
								switch (gsgsDcdydjDetailTh) {
									case "注销日期":
										mortgRevokeInfo.setRevokeDate(gsgsDcdydjDetailTd);
										break;
									case "注销原因":
										mortgRevokeInfo.setRevokeReason(gsgsDcdydjDetailTd);
										break;
									default:
										break;
								}
								
							}
							
							mortgDetail.setMortgRevokeInfo(mortgRevokeInfo);
						}
						
					}
				}
			
				//获取动产抵押登记信息详情  抵押权人概况
				List<AicpubCChatMortgPersonInfo> mortgPersonInfos = new ArrayList<AicpubCChatMortgPersonInfo>();
				Object gsgsxx_dcdydjxx_detail_dyqrgk_page_object = dcdyDetailMap.get("gsgsxx_dcdydjxx_detail_dyqrgk_page");
				if (gsgsxx_dcdydjxx_detail_dyqrgk_page_object != null) {
					String gsgsxx_dcdydjxx_detail_dyqrgk_page = (String)gsgsxx_dcdydjxx_detail_dyqrgk_page_object;
					Document gsgsDcdydjDetailDyqrgkDoc = Jsoup.parse(gsgsxx_dcdydjxx_detail_dyqrgk_page);
					Elements gsgsDcdydjDetailDyqrgkTables = gsgsDcdydjDetailDyqrgkDoc.select("table");
					if (gsgsDcdydjDetailDyqrgkTables != null && !gsgsDcdydjDetailDyqrgkTables.isEmpty()) {
						Element gsgsDcdydjDetailDyqrgkTable = gsgsDcdydjDetailDyqrgkTables.get(0);
						if (gsgsDcdydjDetailDyqrgkTable != null) {
							Elements gsgsDcdydjDetailDyqrgkTrs = gsgsDcdydjDetailDyqrgkTable.select("tr");
							for (Element gsgsDcdydjDetailDyqrgkTr : gsgsDcdydjDetailDyqrgkTrs) {
								Elements gsgsDcdydjDetailDyqrgkTds = gsgsDcdydjDetailDyqrgkTr.select("td");
								if (gsgsDcdydjDetailDyqrgkTds.size() == 4) {
									String name = gsgsDcdydjDetailDyqrgkTds.get(1).text();
									String type = gsgsDcdydjDetailDyqrgkTds.get(2).text();
									String num = gsgsDcdydjDetailDyqrgkTds.get(3).text();
									
									AicpubCChatMortgPersonInfo mortgPersonInfo = new AicpubCChatMortgPersonInfo();
									mortgPersonInfo.setName(name);
									mortgPersonInfo.setType(type);
									mortgPersonInfo.setNum(num);
									
									mortgPersonInfos.add(mortgPersonInfo);
								}
							}
						}
					}
				}
				mortgDetail.setMortgPersonInfos(mortgPersonInfos);
				
				//获取动产抵押登记信息详情   抵押物概况
				List<AicpubCChatMortgGoodsInfo> mortgGoodsInfos = new ArrayList<AicpubCChatMortgGoodsInfo>();
				Object gsgsxx_dcdydjxx_detail_dywgk_page_object = dcdyDetailMap.get("gsgsxx_dcdydjxx_detail_dywgk_page");
				if (gsgsxx_dcdydjxx_detail_dywgk_page_object != null) {
					String gsgsxx_dcdydjxx_detail_dywgk_page = (String)gsgsxx_dcdydjxx_detail_dywgk_page_object;
					Document gsgsDcdydjDetailDywgkkDoc = Jsoup.parse(gsgsxx_dcdydjxx_detail_dywgk_page);
					Elements gsgsDcdydjDetailDywgkTables = gsgsDcdydjDetailDywgkkDoc.select("table");
					if (gsgsDcdydjDetailDywgkTables != null && !gsgsDcdydjDetailDywgkTables.isEmpty()) {
						Element gsgsDcdydjDetailDywgkTable = gsgsDcdydjDetailDywgkTables.get(0);
						if (gsgsDcdydjDetailDywgkTable != null) {
							Elements gsgsDcdydjDetailDywgkTrs = gsgsDcdydjDetailDywgkTable.select("tr");
							for (Element gsgsDcdydjDetailDywgkTr : gsgsDcdydjDetailDywgkTrs) {
								Elements gsgsDcdydjDetailDywgkTds = gsgsDcdydjDetailDywgkTr.select("td");
								if (gsgsDcdydjDetailDywgkTds.size() == 5) {
									String name = gsgsDcdydjDetailDywgkTds.get(1).text();
									String ownerShip = gsgsDcdydjDetailDywgkTds.get(2).text();
									String generalSituation = gsgsDcdydjDetailDywgkTds.get(3).text();
									String note = gsgsDcdydjDetailDywgkTds.get(4).text();
									
									AicpubCChatMortgGoodsInfo mortgGoodsInfo = new AicpubCChatMortgGoodsInfo();
									mortgGoodsInfo.setName(name);
									mortgGoodsInfo.setOwnerShip(ownerShip);
									mortgGoodsInfo.setGeneralSituation(generalSituation);
									mortgGoodsInfo.setNote(note);
									
									mortgGoodsInfos.add(mortgGoodsInfo);
								}
							}
						}
					}
				}
				mortgDetail.setMortgGoodsInfos(mortgGoodsInfos);
				
				//获取动产抵押登记信息详情  变更
				List<AicpubCChatMortgChangeInfo> mortgChangeInfos = new ArrayList<AicpubCChatMortgChangeInfo>();
				Object gsgsxx_dcdydjxx_detail_dcdybg_page_object = dcdyDetailMap.get("gsgsxx_dcdydjxx_detail_dcdybg_page");
				if (gsgsxx_dcdydjxx_detail_dcdybg_page_object != null) {
					String gsgsxx_dcdydjxx_detail_dcdybg_page = (String)gsgsxx_dcdydjxx_detail_dcdybg_page_object;
					Document gsgsDcdydjDetailbgDoc = Jsoup.parse(gsgsxx_dcdydjxx_detail_dcdybg_page);
					Elements gsgsDcdydjDetailbgTables = gsgsDcdydjDetailbgDoc.select("table");
					if (gsgsDcdydjDetailbgTables != null && !gsgsDcdydjDetailbgTables.isEmpty()) {
						Element gsgsDcdydjDetailbgTable = gsgsDcdydjDetailbgTables.get(0);
						if (gsgsDcdydjDetailbgTable != null) {
							Elements gsgsDcdydjDetailbgTrs = gsgsDcdydjDetailbgTable.select("tr");
							for (Element gsgsDcdydjDetailbgTr : gsgsDcdydjDetailbgTrs) {
								Elements gsgsDcdydjDetailbgTds = gsgsDcdydjDetailbgTr.select("td");
								if (gsgsDcdydjDetailbgTds.size() == 3) {
									String changeDate = gsgsDcdydjDetailbgTds.get(1).text();
									String changeContent = gsgsDcdydjDetailbgTds.get(2).text();
									
									AicpubCChatMortgChangeInfo mortgChangeInfo = new AicpubCChatMortgChangeInfo();
									mortgChangeInfo.setChangeDate(changeDate);
									mortgChangeInfo.setChangeContent(changeContent);
									
									mortgChangeInfos.add(mortgChangeInfo);
								}
							}
						}
					}
				}
				mortgDetail.setMortgChangeInfos(mortgChangeInfos);			
				
				mortgDetails.add(mortgDetail);
			}
		}
		
		//2.
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
		Object gsgsxx_dcdydjxx_dcdydjxx_object = resultHtmlMap.get("gsgsxx_dcdydjxx_dcdydjxx");
		if (gsgsxx_dcdydjxx_dcdydjxx_object != null) {
			String gsgsDcdydjHtml = (String)gsgsxx_dcdydjxx_dcdydjxx_object;
			Document gsgsDcdydjDoc = Jsoup.parse(gsgsDcdydjHtml);
			Elements gsgsDcdydjTables = gsgsDcdydjDoc.select("table");
			if (gsgsDcdydjTables != null && !gsgsDcdydjTables.isEmpty()) {
				Element gsgsDcdydjTable = gsgsDcdydjTables.get(0);
				Elements gsgsDcdydjTrs = gsgsDcdydjTable.select("tr");
				gsgsDcdydjTrs.remove(0);
				gsgsDcdydjTrs.remove(0);
				int i = 0;
				for (Element gsgsDcdydjTr : gsgsDcdydjTrs) {
					Elements gsgsDcdydjTds = gsgsDcdydjTr.select("td");
					
					if (gsgsDcdydjTds.size() == 7) {
						AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
						String regNum = gsgsDcdydjTds.get(1).text();
						String regDate = gsgsDcdydjTds.get(2).text();
						String reg_Authority = gsgsDcdydjTds.get(3).text();
						String bdbzqAmount = gsgsDcdydjTds.get(4).text();
						String status = gsgsDcdydjTds.get(5).text();
						gsgsDcdydjDcdydjInfo.setRegNum(regNum);
						gsgsDcdydjDcdydjInfo.setRegDateTime(regDate);
						gsgsDcdydjDcdydjInfo.setRegAuthority(reg_Authority);
						gsgsDcdydjDcdydjInfo.setGuaranteedDebtAmount(bdbzqAmount);
						gsgsDcdydjDcdydjInfo.setStatus(status);
						gsgsDcdydjDcdydjInfo.setMortgDetail(mortgDetails.get(i++));
						gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
						
					} else if (gsgsDcdydjTds.size() == 8) {
						AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
						String regNum = gsgsDcdydjTds.get(1).text();
						String regDate = gsgsDcdydjTds.get(2).text();
						String reg_Authority = gsgsDcdydjTds.get(3).text();
						String bdbzqAmount = gsgsDcdydjTds.get(4).text();
						String status = gsgsDcdydjTds.get(5).text();
						String pubDate = gsgsDcdydjTds.get(6).text();
						gsgsDcdydjDcdydjInfo.setRegNum(regNum);
						gsgsDcdydjDcdydjInfo.setRegDateTime(regDate);
						gsgsDcdydjDcdydjInfo.setRegAuthority(reg_Authority);
						gsgsDcdydjDcdydjInfo.setGuaranteedDebtAmount(bdbzqAmount);
						gsgsDcdydjDcdydjInfo.setStatus(status);
						gsgsDcdydjDcdydjInfo.setPubDateTime(pubDate);
						gsgsDcdydjDcdydjInfo.setMortgDetail(mortgDetails.get(i++));
						gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
					}
					
				}
				
				if(isDebug) {
					gsgsDcdydjInfo.setHtml(gsgsDcdydjTable.toString());
				}
			}
		}
		
		gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);
		gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);
		
		//-----------------工商公示信息-->动产抵押登记信息 end-----------------------
		
		//-----------------工商公示信息-->股权出质登记信息 start-----------------------
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
		Object gsgsxx_gqczdjxx_gqczdjxx_object = resultHtmlMap.get("gsgsxx_gqczdjxx_gqczdjxx");
		if (gsgsxx_gqczdjxx_gqczdjxx_object != null) {
			String gsgsGqczdjHtml = (String)gsgsxx_gqczdjxx_gqczdjxx_object;
			Document gsgsGqczdjDoc = Jsoup.parse(gsgsGqczdjHtml);
			Elements gsgsGqczdjTables = gsgsGqczdjDoc.select("table");
			if (gsgsGqczdjTables != null && !gsgsGqczdjTables.isEmpty()) {
				Element gsgsGqczdjTable = gsgsGqczdjTables.get(0);
				Elements gsgsGqczdjTrs = gsgsGqczdjTable.select("tr");
				gsgsGqczdjTrs.remove(0);
				gsgsGqczdjTrs.remove(0);
				for (Element gsgsGqczdjTr : gsgsGqczdjTrs) {
					Elements gsgsGqczdjTds = gsgsGqczdjTr.select("td");
					
					AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
					
					if (gsgsGqczdjTds.size() == 10) {
						String regNum = gsgsGqczdjTds.get(1).text();
						String czr = gsgsGqczdjTds.get(2).text();
						String czrIdNum = gsgsGqczdjTds.get(3).text();
						String czgqAmount = gsgsGqczdjTds.get(4).text();
						String zqr = gsgsGqczdjTds.get(5).text();
						String zqrIdNum = gsgsGqczdjTds.get(6).text();
						String gqczsldjDate = gsgsGqczdjTds.get(7).text();
						String status = gsgsGqczdjTds.get(8).text();
						String changeSitu = gsgsGqczdjTds.get(9).text();
						gsgsGqczdjGqczdjInfo.setRegNum(regNum);
						gsgsGqczdjGqczdjInfo.setMortgagorName(czr);
						gsgsGqczdjGqczdjInfo.setMortgagorIdNum(czrIdNum);
						gsgsGqczdjGqczdjInfo.setMortgAmount(czgqAmount);
						gsgsGqczdjGqczdjInfo.setMortgageeName(zqr);
						gsgsGqczdjGqczdjInfo.setMortgageeIdNum(zqrIdNum);
						gsgsGqczdjGqczdjInfo.setRegDateTime(gqczsldjDate);
						gsgsGqczdjGqczdjInfo.setStatus(status);
						gsgsGqczdjGqczdjInfo.setChangeSitu(changeSitu);
						
					} else if (gsgsGqczdjTds.size() == 11) {
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
					}
					
					gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
				}
				
				if(isDebug) {
					gsgsGqczdjInfo.setHtml(gsgsGqczdjTable.toString());
				}
			}
		}
		
		gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);
		gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);
		//-----------------工商公示信息-->股权出质登记信息 end-----------------------
		
		//-----------------工商公示信息-->行政处罚信息 start-----------------------
		List<AicpubAAdmpunishInfo.PunishDetail> punishDetialList = new ArrayList<AicpubAAdmpunishInfo.PunishDetail>();
		Object gsgsxx_djxx_xxcfxx_xq_object = resultHtmlMap.get("gsgsxx_djxx_xxcfxx_xq");
		if (gsgsxx_djxx_xxcfxx_xq_object != null) {
			List<String> gsgsXzcfxqList = (List<String>)gsgsxx_djxx_xxcfxx_xq_object;
			for (String gsgsXzcfxqHtml : gsgsXzcfxqList) {
				AicpubAAdmpunishInfo aicpubAAdmpunishInfo = new AicpubAAdmpunishInfo();
				AicpubAAdmpunishInfo.PunishDetail punishDetail = aicpubAAdmpunishInfo.new PunishDetail();
				
				Document gsgsXzcfxqDoc = Jsoup.parse(gsgsXzcfxqHtml);
				Elements gsgsXzcfxqTables = gsgsXzcfxqDoc.select("table");
				if (gsgsXzcfxqTables != null && !gsgsXzcfxqTables.isEmpty()) {
					Element gsgsXzcfxqTable = gsgsXzcfxqTables.get(1);
					Elements gsgsXzcfxqTrs = gsgsXzcfxqTable.select("tr");
					
					gsgsXzcfxqTrs.remove(0);
					for (Element gsgsXzcfxqTr : gsgsXzcfxqTrs) {
						String xqThContent = gsgsXzcfxqTr.select("th").get(0).text().trim();
						String xqTdContent = gsgsXzcfxqTr.select("td").get(0).text().trim();
						
						switch (xqThContent) {
							case "行政处罚决定书文号":
								punishDetail.punishRepNum = xqTdContent;
								break;
							case "名称":
								punishDetail.name = xqTdContent;
								break;
							case "注册号":
								punishDetail.regNum = xqTdContent;
								break;
							case "法定代表人（负责人）姓名":
								punishDetail.legalReprName = xqTdContent;
								break;
							case "违法行为类型":
								punishDetail.illegalActType = xqTdContent;
								break;
							case "行政处罚内容":
								punishDetail.punishContent = xqTdContent;
								break;
							case "作出行政处罚决定机关名称":
								punishDetail.deciAuthority = xqTdContent;
								break;
							case "作出行政处罚决定日期":
								punishDetail.deciDateTime = xqTdContent;
								break;
							default:
								break;
						}
						
						punishDetialList.add(punishDetail);
					}
					
				}
			}
		}
		
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
		List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
		Object gsgsxx_xzcfxx_xzcfxx_object = resultHtmlMap.get("gsgsxx_xzcfxx_xzcfxx");
		if (gsgsxx_xzcfxx_xzcfxx_object != null) {
			String gsgsXzcfHtml = (String)gsgsxx_xzcfxx_xzcfxx_object;
			Document gsgsXzcfDoc = Jsoup.parse(gsgsXzcfHtml);
			Elements gsgsXzcfTables = gsgsXzcfDoc.select("table");
			if (gsgsXzcfTables != null && !gsgsXzcfTables.isEmpty()) {
				Element gsgsXzcfTable = gsgsXzcfTables.get(0);
				Elements gsgsXzcfTrs = gsgsXzcfTable.select("tr");
				gsgsXzcfTrs.remove(0);
				gsgsXzcfTrs.remove(0);
				
				int i = 0;
				for (Element gsgsXzcfXzcfTr : gsgsXzcfTrs) {
					Elements gsgsGqczdjTds = gsgsXzcfXzcfTr.select("td");
					
					if (gsgsGqczdjTds.size() == 7) {
						String xzcfjdsNum = gsgsGqczdjTds.get(1).text();
						String wfxwType = gsgsGqczdjTds.get(2).text();
						String xzcfContent = gsgsGqczdjTds.get(3).text();
						String zcxzcfjdjgName = gsgsGqczdjTds.get(4).text();
						String zcxzcfjdDate = gsgsGqczdjTds.get(5).text();
						
						AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();
						gsgsXzcfXzcfInfo.setPunishRepNum(xzcfjdsNum);
						gsgsXzcfXzcfInfo.setIllegalActType(wfxwType);
						gsgsXzcfXzcfInfo.setPunishContent(xzcfContent);
						gsgsXzcfXzcfInfo.setDeciAuthority(zcxzcfjdjgName);
						gsgsXzcfXzcfInfo.setDeciDateTime(zcxzcfjdDate);
						gsgsXzcfXzcfInfo.setPunishDetail(punishDetialList.get(i++));
						gsgsXzcfXzcfInfos.add(gsgsXzcfXzcfInfo);
					}
				}
				
				if(isDebug) {
					gsgsXzcfInfo.setHtml(gsgsXzcfTable.toString());
				}
			}
		}
		
		gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos);
		gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);
		
		//-----------------工商公示信息-->行政处罚信息 end-----------------------
		
		//-----------------工商公示信息-->经营异常信息 start-----------------------
		AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
		List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();
		Object gsgsxx_jyycxx_jyycxx_object = resultHtmlMap.get("gsgsxx_jyycxx_jyycxx");
		if (gsgsxx_jyycxx_jyycxx_object != null) {
			String gsgsJyycHtml = (String)gsgsxx_jyycxx_jyycxx_object;
			Document gsgsJyycDoc = Jsoup.parse(gsgsJyycHtml);
			Elements gsgsJyycTables = gsgsJyycDoc.select("table");
			if (gsgsJyycTables != null && !gsgsJyycTables.isEmpty()) {
				Element gsgsJyycTable = gsgsJyycTables.get(0);
				Elements gsgsJyycTrs = gsgsJyycTable.select("tr");
				gsgsJyycTrs.remove(0);
				gsgsJyycTrs.remove(0);
				for (Element gsgsJyycTr : gsgsJyycTrs) {
					Elements gsgsJyycTds = gsgsJyycTr.select("td");
					
					AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();
					if (gsgsJyycTds.size() == 6) {
						String lrjyycmlCause = gsgsJyycTds.get(1).text();
						String lrDate = gsgsJyycTds.get(2).text();
						String ycjyycmlCause = gsgsJyycTds.get(3).text();
						String ycDate = gsgsJyycTds.get(4).text();
						String zcjdAuthority = gsgsJyycTds.get(5).text();
						
						gsgsJyycJyycInfo.setIncludeCause(lrjyycmlCause);
						gsgsJyycJyycInfo.setIncludeDateTime(lrDate);
						gsgsJyycJyycInfo.setRemoveCause(ycjyycmlCause);
						gsgsJyycJyycInfo.setRemoveDateTime(ycDate);
						gsgsJyycJyycInfo.setRemoveAuthority(zcjdAuthority);
					} else if (gsgsJyycTds.size() == 7) {
						String lrjyycmlCause = gsgsJyycTds.get(1).text();
						String lrDate = gsgsJyycTds.get(2).text();
						String lrzcjdAuthority = gsgsJyycTds.get(3).text();
						String ycjyycmlCause = gsgsJyycTds.get(4).text();
						String ycDate = gsgsJyycTds.get(5).text();
						String yczcjdAuthority = gsgsJyycTds.get(6).text();
						gsgsJyycJyycInfo.setIncludeCause(lrjyycmlCause);
						gsgsJyycJyycInfo.setIncludeDateTime(lrDate);
						gsgsJyycJyycInfo.setIncludeAuthority(lrzcjdAuthority);
						gsgsJyycJyycInfo.setRemoveCause(ycjyycmlCause);
						gsgsJyycJyycInfo.setRemoveDateTime(ycDate);
						gsgsJyycJyycInfo.setRemoveAuthority(yczcjdAuthority);
					}
					gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
				}
				
				if(isDebug) {
					gsgsJyycInfo.setHtml(gsgsJyycTable.toString());
				}
			}
		}
		
		gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);
		gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);
		//-----------------工商公示信息-->经营异常信息 end-----------------------
		
		//-----------------工商公示信息-->严重违法信息 start-----------------------
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
		Object gsgsxx_yzwfxx_yzwfxx_object = resultHtmlMap.get("gsgsxx_yzwfxx_yzwfxx");
		if (gsgsxx_yzwfxx_yzwfxx_object != null) {
			String gsgsYzwfHtml = (String)gsgsxx_yzwfxx_yzwfxx_object;
			Document gsgsYzwfDoc = Jsoup.parse(gsgsYzwfHtml);
			Elements gsgsYzwfTables = gsgsYzwfDoc.select("table");
			if (gsgsYzwfTables != null && !gsgsYzwfTables.isEmpty()) {
				Element gsgsYzwfTable = gsgsYzwfTables.get(0);
				Elements gsgsYzwfTrs = gsgsYzwfTable.select("tr");
				gsgsYzwfTrs.remove(0);
				gsgsYzwfTrs.remove(0);
				for (Element gsgsYzwfTr : gsgsYzwfTrs) {
					Elements gsgsYzwfTds = gsgsYzwfTr.select("td");
					AicpubSSerillegalInfo gsgsYzwfYzwfInfo = new AicpubSSerillegalInfo();
					
					if (gsgsYzwfTds.size() == 6) {
						String lryzwfqymdCause = gsgsYzwfTds.get(1).text();
						String lrDate = gsgsYzwfTds.get(2).text();
						String ycyzwfqymdCause = gsgsYzwfTds.get(3).text();
						String ycDate = gsgsYzwfTds.get(4).text();
						String zcjdAuthority = gsgsYzwfTds.get(5).text();
						
						gsgsYzwfYzwfInfo.setIncludeCause(lryzwfqymdCause);
						gsgsYzwfYzwfInfo.setIncludeDateTime(lrDate);
						gsgsYzwfYzwfInfo.setRemoveCause(ycyzwfqymdCause);
						gsgsYzwfYzwfInfo.setRemoveDateTime(ycDate);
						gsgsYzwfYzwfInfo.setDeciAuthority(zcjdAuthority);
					}
					gsgsYzwfYzwfInfos.add(gsgsYzwfYzwfInfo);
				}
				
				if(isDebug) {
					gsgsYzwfInfo.setHtml(gsgsYzwfTable.toString());
				}
			}
			
		}
		gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);
		gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);
		//-----------------工商公示信息-->严重违法信息 end-----------------------
		
		//-----------------工商公示信息-->抽查检查信息 start-----------------------
		AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
		Object gsgsxx_ccjcxx_ccjcxx_object = resultHtmlMap.get("gsgsxx_ccjcxx_ccjcxx");
		if (gsgsxx_ccjcxx_ccjcxx_object != null) {
			String gsgsCcjcHtml = (String)gsgsxx_ccjcxx_ccjcxx_object;
			Document gsgsCcjcDoc = Jsoup.parse(gsgsCcjcHtml);
			Elements gsgsCcjcTables = gsgsCcjcDoc.select("table");
			if (gsgsCcjcTables != null && !gsgsCcjcTables.isEmpty()) {
				Element gsgsCcjcTable = gsgsCcjcTables.get(0);
				Elements gsgsCcjcTrs = gsgsCcjcTable.select("tr");
				gsgsCcjcTrs.remove(0);
				gsgsCcjcTrs.remove(0);
				for (Element gsgsCcjcTr : gsgsCcjcTrs) {
					Elements gsgsCcjcTds = gsgsCcjcTr.select("td");
					AicpubCCheckInfo gsgsCcjcCcjcInfo = new AicpubCCheckInfo();
					if (gsgsCcjcTds.size() == 5) {
						String jcssAuthority = gsgsCcjcTds.get(1).text();
						String gsgsCcjc_type = gsgsCcjcTds.get(2).text();
						String gsgsCcjc_date = gsgsCcjcTds.get(3).text();
						String gsgsCcjc_result = gsgsCcjcTds.get(4).text();
						
						gsgsCcjcCcjcInfo.setCheckImplAuthority(jcssAuthority);
						gsgsCcjcCcjcInfo.setType(gsgsCcjc_type);
						gsgsCcjcCcjcInfo.setDateTime(gsgsCcjc_date);
						gsgsCcjcCcjcInfo.setResult(gsgsCcjc_result);
					}
					
					gsgsCcjcCcjcInfos.add(gsgsCcjcCcjcInfo);
				}
				
				if (isDebug) {
					gsgsCcjcInfo.setHtml(gsgsCcjcTable.toString());
				}
			}
		}
		
		gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);
		gsgsInfo.setCheckInfo(gsgsCcjcInfo);
		//-----------------工商公示信息-->抽查检查信息 end-----------------------
		
		
		//二、企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();
		
		//-----------------企业公示信息-->企业年报 start-----------------------
		//企业年报列表信息--报送年度和发布日期
		List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
		
		Object qygsxx_qynb_infos_object = resultHtmlMap.get("qygsxx_qynb_infos");
		if (qygsxx_qynb_infos_object != null) {
			List<Map<String, Object>> qynbDetailList = (List<Map<String, Object>>)qygsxx_qynb_infos_object;
			for (Map<String, Object> qynbDetailMap : qynbDetailList) {
				EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();
				
				Object qygsxx_qynb_list_a_text_object = qynbDetailMap.get("qygsxx_qynb_list_a_text");
				if (qygsxx_qynb_list_a_text_object != null) {
					String submitYear = (String)qygsxx_qynb_list_a_text_object;
					qygsQynbInfo.setSubmitYear(submitYear);
				}
				
				Object qygsxx_qynb_list_pubdate_object = qynbDetailMap.get("qygsxx_qynb_list_pubdate");
				if (qygsxx_qynb_list_pubdate_object != null) {
					String pubDate = (String)qygsxx_qynb_list_pubdate_object;
					qygsQynbInfo.setPubDateTime(pubDate);
				}
				
				//企业基本信息 & (企业资产状况信息|生产经营情况)  qygsxx_qynb_info_1_3_page
				Object qygsxx_qynb_info_1_3_page_object = qynbDetailMap.get("qygsxx_qynb_info_1_3_page");
				if (qygsxx_qynb_info_1_3_page_object != null) {
					String qygsxx_qynb_info_1_3_page = (String)qygsxx_qynb_info_1_3_page_object;
					Document qygsxxQynb13Doc = Jsoup.parse(qygsxx_qynb_info_1_3_page);
					Elements qygsxxQynb13Tables = qygsxxQynb13Doc.select("table");
					for (Element qygsxxQynb13Table : qygsxxQynb13Tables) {
						String qygsxxQynbTitle = qygsxxQynb13Table.select("th").get(0).text();
						
						if (qygsxxQynbTitle.indexOf("年度报告") > 0) {
							qygsxxQynbTitle = qygsxxQynb13Table.select("th").get(1).text();
						}
						
						Elements qygsxxQynb13Ths = qygsxxQynb13Table.select("th");
						Elements qygsxxQynb13Tds = qygsxxQynb13Table.select("td");
						qygsxxQynb13Ths.remove(0);
						if ("企业基本信息".equals(qygsxxQynbTitle)) {
							EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
							qygsxxQynb13Ths.remove(0);
							
							for (int i = 0; i < qygsxxQynb13Ths.size(); i++) {
								String qygsxxQynb13Th = qygsxxQynb13Ths.get(i).text().replace(" ", "");
								String qygsxxQynb13Td = qygsxxQynb13Tds.get(i).text();
								
								switch (qygsxxQynb13Th) {
									case "注册号/统一社会信用代码":
										qygsQynbJbInfo.setNum(qygsxxQynb13Td);
										break;
									case "企业名称":
										qygsQynbJbInfo.setName(qygsxxQynb13Td);
										break;
									case "企业联系电话":
										qygsQynbJbInfo.setTel(qygsxxQynb13Td);
										break;
									case "邮政编码":
										qygsQynbJbInfo.setZipCode(qygsxxQynb13Td);
										break;
									case "企业通信地址":
										qygsQynbJbInfo.setAddress(qygsxxQynb13Td);
										break;
									case "电子邮箱":
										qygsQynbJbInfo.setEmail(qygsxxQynb13Td);
										break;
									case "有限责任公司本年度是否发生股东股权转让":
										qygsQynbJbInfo.setIsStohrEquTransferred(qygsxxQynb13Td);
										break;
									case "企业经营状态":
										qygsQynbJbInfo.setOperatingStatus(qygsxxQynb13Td);
										break;
									case "是否有网站或网店":
										qygsQynbJbInfo.setHasWebsiteOrStore(qygsxxQynb13Td);
										break;
									case "企业是否有投资信息或购买其他公司股权":
										qygsQynbJbInfo.setHasInvestInfoOrPurchOtherCorpEqu(qygsxxQynb13Td);
										break;
									case "从业人数":
										qygsQynbJbInfo.setEmpNum(qygsxxQynb13Td);
										break;
									default:
										break;
								}
							}
							
							qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);
							
						} else if ("企业资产状况信息".equals(qygsxxQynbTitle) || "生产经营情况".equals(qygsxxQynbTitle)) {
							EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
							
							for (int i = 0; i < qygsxxQynb13Ths.size(); i++) {
								String qygsxxQynb13Th = qygsxxQynb13Ths.get(i).text();
								String qygsxxQynb13Td = qygsxxQynb13Tds.get(i).text();
								
								switch (qygsxxQynb13Th) {
									case "资产总额":
										qygsQynbQyzczkInfo.setAssetAmount(qygsxxQynb13Td);
										break;
									case "所有者权益合计":
										qygsQynbQyzczkInfo.setTotalEquity(qygsxxQynb13Td);
										break;
									case "销售总额":
										qygsQynbQyzczkInfo.setSalesAmount(qygsxxQynb13Td);
										break;
									case "利润总额":
										qygsQynbQyzczkInfo.setProfitAmount(qygsxxQynb13Td);
										break;
									case "营业总收入中主营业务收入":
										qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(qygsxxQynb13Td);
										break;
									case "主营业务收入":
										qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(qygsxxQynb13Td);
										break;
									case "净利润":
										qygsQynbQyzczkInfo.setNetProfit(qygsxxQynb13Td);
										break;
									case "纳税总额":
										qygsQynbQyzczkInfo.setTaxesAmount(qygsxxQynb13Td);
										break;
									case "负债总额":
										qygsQynbQyzczkInfo.setLiabilityAmount(qygsxxQynb13Td);
										break;
									default:
										break;
								}
								
							}
							
							qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);
							
						} else if ("生产经营情况".equals(qygsxxQynbTitle)) {
							
						}
						
					}
				}
				
				//股东及出资信息    qygsxx_qynb_info_gdjczxx_page
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();
				Object qygsxx_qynb_info_gdjczxx_page_object = qynbDetailMap.get("qygsxx_qynb_info_gdjczxx_page");
				if (qygsxx_qynb_info_gdjczxx_page_object != null) {
					String qygsxx_qynb_info_gdjczxx_page = (String)qygsxx_qynb_info_gdjczxx_page_object;
					Document qygsxxQynbGdjczxxDoc = Jsoup.parse(qygsxx_qynb_info_gdjczxx_page);
					Element qygsxxQynbGdjczxxTable = qygsxxQynbGdjczxxDoc.getElementById("touziren");
					if (qygsxxQynbGdjczxxTable != null) {
						Elements gdczxxTrs = qygsxxQynbGdjczxxTable.select("tr");
						gdczxxTrs.remove(0);
						gdczxxTrs.remove(0);
						for (Element gdczxxTr : gdczxxTrs) {
							Elements gdczxxTds = gdczxxTr.select("td");
							if(gdczxxTds.size() == 7) {
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
					}
				}
				qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);
				
				//对外投资信息   qygsxx_qynb_info_dwtgbzdbxx_page
				List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
				Object qygsxx_qynb_info_dwtzxx_page_object = qynbDetailMap.get("qygsxx_qynb_info_dwtzxx_page");
				if (qygsxx_qynb_info_dwtzxx_page_object != null) {
					String qygsxx_qynb_info_dwtzxx_page = (String) qygsxx_qynb_info_dwtzxx_page_object;
					Document qygsxxQynbDwtzxxDoc = Jsoup.parse(qygsxx_qynb_info_dwtzxx_page);
					Element qygsxxQynbDwtzxxTable = qygsxxQynbDwtzxxDoc.getElementById("touziren");
					if (qygsxxQynbDwtzxxTable != null) {
						Elements dwtzxxTrs = qygsxxQynbDwtzxxTable.select("tr");
						for (Element dwtzxxTr : dwtzxxTrs) {
							Elements dwtzxxTds = dwtzxxTr.select("td");
							if(dwtzxxTds.size() == 2) {
								String tzslqyhgmgqqyName = dwtzxxTds.get(0).text();
								String regNum = dwtzxxTds.get(1).text();
								
								EntpubAnnreportExtinvestInfo qygsQynbDwtzInfo = new EntpubAnnreportExtinvestInfo();
								qygsQynbDwtzInfo.setEnterpriseName(tzslqyhgmgqqyName);
								qygsQynbDwtzInfo.setRegNum(regNum);
								qygsQynbDwtzInfos.add(qygsQynbDwtzInfo);
							}
						}
					}
				}
				qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzInfos);
				
				//网站或网店信息     qygsxx_qynb_info_wzhwdxx_page
				List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
				Object qygsxx_qynb_info_wzhwdxx_page_object = qynbDetailMap.get("qygsxx_qynb_info_wzhwdxx_page");
				if (qygsxx_qynb_info_wzhwdxx_page_object != null) {
					String qygsxx_qynb_info_wzhwdxx_page = (String) qygsxx_qynb_info_wzhwdxx_page_object;
					Document qygsxxQynbWzhwdxxDoc = Jsoup.parse(qygsxx_qynb_info_wzhwdxx_page);
					Element qygsxxQynbWzhwdxxTable = qygsxxQynbWzhwdxxDoc.getElementById("touziren");
					if (qygsxxQynbWzhwdxxTable != null) {
						Elements wzwdxxTrs = qygsxxQynbWzhwdxxTable.select("tr");
						wzwdxxTrs.remove(0);
						wzwdxxTrs.remove(0);
						for (Element wzwdxxTr : wzwdxxTrs) {
							Elements wzwdxxTds = wzwdxxTr.select("td");
							if(wzwdxxTds.size() == 3) {
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
					}
				}
				
				qygsQynbInfo.setWebsiteInfos(qygsQynbWzhwdInfos);
				
				//修改记录     qygsxx_qynb_info_xgjl_page
				List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
				Object qygsxx_qynb_info_xgjl_page_object = qynbDetailMap.get("qygsxx_qynb_info_xgjl_page");
				if (qygsxx_qynb_info_xgjl_page_object != null) {
					String qygsxx_qynb_info_xgjl_page = (String) qygsxx_qynb_info_xgjl_page_object;
					Document qygsxxQynbXgjlDoc = Jsoup.parse(qygsxx_qynb_info_xgjl_page);
					Element qygsxxQynbXgjlTable = qygsxxQynbXgjlDoc.getElementById("touziren");
					if (qygsxxQynbXgjlTable != null) {
						Elements xgjlxxTrs = qygsxxQynbXgjlTable.select("tr");
						xgjlxxTrs.remove(0);
						xgjlxxTrs.remove(0);
						for (Element xgjlxxTr : xgjlxxTrs) {
							Elements xgjlxxTds = xgjlxxTr.select("td");
							if(xgjlxxTds.size() == 5) {
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
					}
				}
				
				qygsQynbInfo.setChangeInfos(qygsQynbXgjlInfos);
				
				qygsQynbInfos.add(qygsQynbInfo);
			}
			
		}
		
		qygsInfo.setAnnReports(qygsQynbInfos);
		//-----------------企业公示信息-->企业年报 end-----------------------
		
		//-----------------企业公示信息-->股东及出资信息 start-----------------------
		EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
		Object qygsxx_gdjczxx_object = resultHtmlMap.get("qygsxx_gdjczxx");
		if (qygsxx_gdjczxx_object != null) {
			String qygsxx_gdjczxx_page = (String) qygsxx_gdjczxx_object;
			Document qygsxxGdjczxxlDoc = Jsoup.parse(qygsxx_gdjczxx_page);
			//股东及出资信息-->股东及出资信息
			List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
			
			Element qygsxxGdjczDiv = qygsxxGdjczxxlDoc.getElementById("xingzhengxuke");
			if (qygsxxGdjczDiv != null) {
				Elements qygsxxGdjczTables = qygsxxGdjczDiv.select("table");
				if (qygsxxGdjczTables != null && !qygsxxGdjczTables.isEmpty()) {
					Element qygsxxGdjczTable = qygsxxGdjczTables.get(0);
					if (qygsxxGdjczTable != null) {
						Elements qygsxxGdjczTrs = qygsxxGdjczTable.select("tr");
						for (Element qygsxxGdjczTr : qygsxxGdjczTrs) {
							EntpubSStohrinvestInfo qygsQynbGdjczInfo = new EntpubSStohrinvestInfo();
							
							Elements qygsxxGdjczTds = qygsxxGdjczTr.select(">td");
							if (qygsxxGdjczTds.size() >= 5) {
								String stockholder = qygsxxGdjczTds.get(0).text();
								String rjAmount = qygsxxGdjczTds.get(1).text();
								String sjAmount = qygsxxGdjczTds.get(2).text();
								
								qygsQynbGdjczInfo.setStockholder(stockholder);
								qygsQynbGdjczInfo.setSubAmount(rjAmount);
								qygsQynbGdjczInfo.setPaidAmount(sjAmount);
								
								
								Element qygsxxGdjczRjTd = qygsxxGdjczTds.get(3);
								Elements qygsxxGdjczRjTdTables = qygsxxGdjczRjTd.select("table");
								if (qygsxxGdjczRjTdTables != null && !qygsxxGdjczRjTdTables.isEmpty()) {
									List<Detail> rjDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
									Element qygsxxGdjczRjTdTable = qygsxxGdjczRjTdTables.get(0);
									Elements qygsxxGdjczRjTdTrs = qygsxxGdjczRjTdTable.select("tr");
									for (Element qygsxxGdjczRjTdTr : qygsxxGdjczRjTdTrs) {
										Elements qygsxxGdjczRjTdTds = qygsxxGdjczRjTdTr.select("td");
										String rjczMethod = qygsxxGdjczRjTdTds.get(0).text();
										String rjczAmount = qygsxxGdjczRjTdTds.get(1).text();
										String rjczDate = qygsxxGdjczRjTdTds.get(2).text();
										
										Detail rjDetail = qygsQynbGdjczInfo.new Detail();
										rjDetail.method = rjczMethod;
										rjDetail.amount = rjczAmount;
										rjDetail.dateTime = rjczDate;
										
										rjDetails.add(rjDetail);
									}
									qygsQynbGdjczInfo.setSubDetails(rjDetails);
								}
								
								
								Element qygsxxGdjczSjTd = qygsxxGdjczTds.get(4);
								Elements qygsxxGdjczSjTables = qygsxxGdjczSjTd.select("table");
								if (qygsxxGdjczSjTables != null && !qygsxxGdjczSjTables.isEmpty()) {
									List<Detail> sjDetails = new ArrayList<EntpubSStohrinvestInfo.Detail>();
									Element qygsxxGdjczSjTdTable = qygsxxGdjczSjTables.get(0);
									Elements qygsxxGdjczSjTdTrs = qygsxxGdjczSjTdTable.select("tr");
									for (Element qygsxxGdjczSjTdTr : qygsxxGdjczSjTdTrs) {
										Elements qygsxxGdjczSjTdTds = qygsxxGdjczSjTdTr.select("td");
										String sjczMethod = qygsxxGdjczSjTdTds.get(0).text();
										String sjczAmount = qygsxxGdjczSjTdTds.get(1).text();
										String sjczDate = qygsxxGdjczSjTdTds.get(2).text();
										
										Detail sjDetail = qygsQynbGdjczInfo.new Detail();
										sjDetail.method = sjczMethod;
										sjDetail.amount = sjczAmount;
										sjDetail.dateTime = sjczDate;
										
										sjDetails.add(sjDetail);
									}
									
									qygsQynbGdjczInfo.setPaidDetails(sjDetails);
								}
								
								qygsGdjczGdjczInfos.add(qygsQynbGdjczInfo);
							}
						}
					}
				}
			}
			qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);
			
			//股东及出资信息-->变更信息
			List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
			/*
			 *	解析代码
			 */
			qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);
			
			if (isDebug) {
				qygsGdjczInfo.setHtml(qygsxxGdjczxxlDoc.toString());
			}
		}
		
		qygsInfo.setStohrInvestInfo(qygsGdjczInfo);
		//-----------------企业公示信息-->股东及出资信息 end-----------------------
		
		//-----------------企业公示信息-->股权变更信息 start-----------------------
		EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
		List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
		Object qygsxx_gqbgxx_object = resultHtmlMap.get("qygsxx_gqbgxx");
		if (qygsxx_gqbgxx_object != null) {
			String qygsxx_gqbgxx_page = (String) qygsxx_gqbgxx_object;
			Document qygsxxGqbgxxDoc = Jsoup.parse(qygsxx_gqbgxx_page);
			Elements qygsxxGqbgxxTables = qygsxxGqbgxxDoc.select("table");
			if (qygsxxGqbgxxTables != null && !qygsxxGqbgxxTables.isEmpty()) {
				Element qygsxxGqbgxxTable = qygsxxGqbgxxTables.get(0);
				Elements qygsxxGqbgxxTrs = qygsxxGqbgxxTable.select("tr");
				qygsxxGqbgxxTrs.remove(0);
				qygsxxGqbgxxTrs.remove(0);
				for (Element qygsxxGqbgxxTr : qygsxxGqbgxxTrs) {
					Elements qygsxxGqbgxxTds = qygsxxGqbgxxTr.select("td");
					if (qygsxxGqbgxxTds.size() == 5) {
						String stockholder = qygsxxGqbgxxTds.get(1).text();
						String bgqOwnershipRatio = qygsxxGqbgxxTds.get(2).text();
						String bghOwnershipRatio = qygsxxGqbgxxTds.get(3).text();
						String bgDate = qygsxxGqbgxxTds.get(4).text();
						
						EntpubEEquchangeInfo qygsGqbgGqbgInfo = new EntpubEEquchangeInfo();
						qygsGqbgGqbgInfo.setStockholder(stockholder);
						qygsGqbgGqbgInfo.setPreOwnershipRatio(bgqOwnershipRatio);
						qygsGqbgGqbgInfo.setPostOwnershipRatio(bghOwnershipRatio);
						qygsGqbgGqbgInfo.setDateTime(bgDate);
						qygsGqbgGqbgInfos.add(qygsGqbgGqbgInfo);
					}
				}
				
				if (isDebug) {
					qygsGqbgInfo.setHtml(qygsxxGqbgxxTable.toString());
				}
			}
		}
		qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);
		
		qygsInfo.setEquChangeInfo(qygsGqbgInfo);
		//-----------------企业公示信息-->股权变更信息 end-----------------------
		
		//-----------------企业公示信息-->行政许可信息 start-----------------------
		//1.详情
		List<List<EntpubAAdmlicInfo.Detail>> admlicDetails = new ArrayList<List<EntpubAAdmlicInfo.Detail>>();
		Object yqgsxx_xzxkxx_details_object = resultHtmlMap.get("yqgsxx_xzxkxx_details");
		if (yqgsxx_xzxkxx_details_object != null) {
			List<Map<String, Object>> qygsXzxkDetailHtmls = (List<Map<String, Object>>)yqgsxx_xzxkxx_details_object;
			for (Map<String, Object> xzxkDetailMap : qygsXzxkDetailHtmls) {
				List<EntpubAAdmlicInfo.Detail> admlicDetailList = new ArrayList<EntpubAAdmlicInfo.Detail>();
				Object yqgsxx_xzxkxx_detail_page_object = xzxkDetailMap.get("yqgsxx_xzxkxx_detail_page");
				if (yqgsxx_xzxkxx_detail_page_object != null) {
					String qygsxx_xzxk_detail_page = (String)yqgsxx_xzxkxx_detail_page_object;
					Document qygsXzxkDetailDoc = Jsoup.parse(qygsxx_xzxk_detail_page);
					Elements qygsXzxkDetailTables = qygsXzxkDetailDoc.select("table");
					if (qygsXzxkDetailTables != null && !qygsXzxkDetailTables.isEmpty()) {
						Element qygsXzxkDetailTable = qygsXzxkDetailTables.get(0);
						if (qygsXzxkDetailTable != null) {
							Elements qygsXzxkDetailTrs = qygsXzxkDetailTable.select("tr");
							for (Element qygsXzxkDetailTr : qygsXzxkDetailTrs) {
								Elements qygsXzxkDetailTds = qygsXzxkDetailTr.select("td");
								if (qygsXzxkDetailTds.size() == 5) {
									String changeItem = qygsXzxkDetailTds.get(1).text();
									String changeDateTime = qygsXzxkDetailTds.get(2).text();
									String changePreContent = qygsXzxkDetailTds.get(3).text();
									String changePostContent = qygsXzxkDetailTds.get(4).text();
									
									EntpubAAdmlicInfo admlicInfo = new EntpubAAdmlicInfo();
									EntpubAAdmlicInfo.Detail detail = admlicInfo.new Detail();
									detail.changeItem = changeItem;
									detail.changeDateTime = changeDateTime;
									detail.changePreContent = changePreContent;
									detail.changePostContent = changePostContent;
									
									admlicDetailList.add(detail);
								}
							}
						}
					}
				}
				admlicDetails.add(admlicDetailList);
			}
		}
		//2.
		EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
		List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
		Object qygsxx_xzxkxx_object = resultHtmlMap.get("qygsxx_xzxkxx");
		if (qygsxx_xzxkxx_object != null) {
			String qygsxx_xzxkxx_page = (String) qygsxx_xzxkxx_object;
			Document qygsxxXzxkxxDoc = Jsoup.parse(qygsxx_xzxkxx_page);
			Elements qygsxxXzxkxxTables = qygsxxXzxkxxDoc.select("table");
			if (qygsxxXzxkxxTables != null && !qygsxxXzxkxxTables.isEmpty()) {
				Element qygsxxXzxkxxTable = qygsxxXzxkxxTables.get(0);
				Elements qygsxxXzxkxxTrs = qygsxxXzxkxxTable.select("tr");
				qygsxxXzxkxxTrs.remove(0);
				qygsxxXzxkxxTrs.remove(0);
				int i = 0;
				for (Element qygsxxXzxkxxTr : qygsxxXzxkxxTrs) {
					Elements qygsxxXzxkxxTds = qygsxxXzxkxxTr.select("td");
					if (qygsxxXzxkxxTds.size() == 9) {
						String xkwjNum = qygsxxXzxkxxTds.get(1).text();
						String xkwjName = qygsxxXzxkxxTds.get(2).text();
						String xzxkstartDate = qygsxxXzxkxxTds.get(3).text();
						String xzxkendDate = qygsxxXzxkxxTds.get(4).text();
						String xkAuthority = qygsxxXzxkxxTds.get(5).text();
						String xkContent = qygsxxXzxkxxTds.get(6).text();
						String status = qygsxxXzxkxxTds.get(7).text();
						
						EntpubAAdmlicInfo qygsXzxkXzxkInfo = new EntpubAAdmlicInfo();
						qygsXzxkXzxkInfo.setLicenceNum(xkwjNum);
						qygsXzxkXzxkInfo.setLicenceName(xkwjName);
						qygsXzxkXzxkInfo.setStartDateTime(xzxkstartDate);
						qygsXzxkXzxkInfo.setEndDateTime(xzxkendDate);
						qygsXzxkXzxkInfo.setDeciAuthority(xkAuthority);
						qygsXzxkXzxkInfo.setContent(xkContent);
						qygsXzxkXzxkInfo.setStatus(status);
						qygsXzxkXzxkInfo.setAdmlicDetail(admlicDetails.get(i++));
						qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
					}
				}
				
				if (isDebug) {
					qygsXzxkInfo.setHtml(qygsxxXzxkxxTable.toString());
				}
			}
		}
		
		qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
		
		qygsInfo.setAdmLicInfo(qygsXzxkInfo);
		//-----------------企业公示信息-->行政许可信息 end-----------------------
		
		//-----------------企业公示信息-->知识产权出质登记信息 start-----------------------
		EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
		List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
		Object qygsxx_zscqczdjxx_object = resultHtmlMap.get("qygsxx_zscqczdjxx");
		if (qygsxx_zscqczdjxx_object != null) {
			String qygsxx_zscqczdjxx_page = (String) qygsxx_zscqczdjxx_object;
			Document qygsxxZscqczdjxxDoc = Jsoup.parse(qygsxx_zscqczdjxx_page);
			Elements qygsxxZscqczdjxxTables = qygsxxZscqczdjxxDoc.select("table");
			if (qygsxxZscqczdjxxTables != null && !qygsxxZscqczdjxxTables.isEmpty()) {
				Element qygsxxZscqczdjxxTable = qygsxxZscqczdjxxTables.get(0);
				Elements qygsxxZscqczdjxxTrs = qygsxxZscqczdjxxTable.select("tr");
				qygsxxZscqczdjxxTrs.remove(0);
				qygsxxZscqczdjxxTrs.remove(0);
				for (Element qygsxxZscqczdjxxTr : qygsxxZscqczdjxxTrs) {
					Elements qygsxxZscqczdjxxTds = qygsxxZscqczdjxxTr.select("td");
					if (qygsxxZscqczdjxxTds.size() == 9) {
						String regNum = qygsxxZscqczdjxxTds.get(1).text();
						String zscqname = qygsxxZscqczdjxxTds.get(2).text();
						String zscqtype = qygsxxZscqczdjxxTds.get(3).text();
						String czrName = qygsxxZscqczdjxxTds.get(4).text();
						String zqrName = qygsxxZscqczdjxxTds.get(5).text();
						String zqdjDeadline = qygsxxZscqczdjxxTds.get(6).text();
						String status = qygsxxZscqczdjxxTds.get(7).text();
						String changeSitu = qygsxxZscqczdjxxTds.get(8).text();
						
						EntpubIIntellectualproregInfo qygsZscqczdjZscqczdjInfo = new EntpubIIntellectualproregInfo();
						qygsZscqczdjZscqczdjInfo.setRegNum(regNum);
						qygsZscqczdjZscqczdjInfo.setName(zscqname);
						qygsZscqczdjZscqczdjInfo.setType(zscqtype);
						qygsZscqczdjZscqczdjInfo.setMortgagorName(czrName);
						qygsZscqczdjZscqczdjInfo.setMortgageeName(zqrName);
						qygsZscqczdjZscqczdjInfo.setPledgeRegDeadline(zqdjDeadline);
						qygsZscqczdjZscqczdjInfo.setStatus(status);
						qygsZscqczdjZscqczdjInfo.setChangeSitu(changeSitu);
						qygsZscqczdjZscqczdjInfos.add(qygsZscqczdjZscqczdjInfo);
					}
				}
				
				if (isDebug) {
					qygsZscqczdjInfo.setHtml(qygsxxZscqczdjxxTable.toString());
				}
			}
		}
		
		qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);
		
		qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);
		//-----------------企业公示信息-->知识产权出质登记信息 end-----------------------
		
		//-----------------企业公示信息-->行政处罚信息 start-----------------------
		EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
		List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
		Object qygsxx_xzcfxx_object = resultHtmlMap.get("qygsxx_xzcfxx");
		if (qygsxx_xzcfxx_object != null) {
			String qygsxx_xzcfxx_page = (String) qygsxx_xzcfxx_object;
			Document qygsxxXzcfxxDoc = Jsoup.parse(qygsxx_xzcfxx_page);
			Elements qygsxxXzcfxxTables = qygsxxXzcfxxDoc.select("table");
			if (qygsxxXzcfxxTables != null && !qygsxxXzcfxxTables.isEmpty()) {
				Element qygsxxXzcfxxTable = qygsxxXzcfxxTables.get(0);
				Elements qygsxxXzcfxxTrs = qygsxxXzcfxxTable.select("tr");
				qygsxxXzcfxxTrs.remove(0);
				qygsxxXzcfxxTrs.remove(0);
				for (Element qygsxxXzcfxxTr : qygsxxXzcfxxTrs) {
					Elements qygsxxXzcfxxTds = qygsxxXzcfxxTr.select("td");
					if (qygsxxXzcfxxTds.size() >= 7) {
						String xzcfjdsNum = qygsxxXzcfxxTds.get(1).text();
						String xzcfContent = qygsxxXzcfxxTds.get(2).text();
						String zcxzcfjdjgName = qygsxxXzcfxxTds.get(3).text();
						String zcxzcfjdDate = qygsxxXzcfxxTds.get(4).text();
						String wfxwType = qygsxxXzcfxxTds.get(5).text();
						String note = qygsxxXzcfxxTds.get(6).text();
						
						EntpubAAdmpunishInfo qygsXzcfXzcfInfo = new EntpubAAdmpunishInfo();
						qygsXzcfXzcfInfo.setPunishRepNum(xzcfjdsNum);
						qygsXzcfXzcfInfo.setPunishContent(xzcfContent);
						qygsXzcfXzcfInfo.setDeciAuthority(zcxzcfjdjgName);
						qygsXzcfXzcfInfo.setDeciDateTime(zcxzcfjdDate);
						qygsXzcfXzcfInfo.setIllegalActType(wfxwType);
						qygsXzcfXzcfInfo.setNote(note);
						qygsXzcfXzcfInfos.add(qygsXzcfXzcfInfo);
					}
				}
				
				if (isDebug) {
					qygsXzcfInfo.setHtml(qygsxxXzcfxxTable.toString());
				}
			}
		}
		
		qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);
		
		qygsInfo.setAdmPunishInfo(qygsXzcfInfo);
		//-----------------企业公示信息-->行政处罚信息 end-----------------------
		
		//三、其他部门公示信息
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();
		
		//-----------------其他部门公示信息-->行政许可信息 start-----------------------
		OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
		List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
		Object qtbmgsxx_xzxkxx_object = resultHtmlMap.get("qtbmgsxx_xzxkxx");
		if (qtbmgsxx_xzxkxx_object != null) {
			String qtbmgsxx_xzxkxx_page = (String) qtbmgsxx_xzxkxx_object;
			Document qtbmgsxxXzxkxxDoc = Jsoup.parse(qtbmgsxx_xzxkxx_page);
			Elements qtbmgsxxXzxkxxTables = qtbmgsxxXzxkxxDoc.select("table");
			if (qtbmgsxxXzxkxxTables != null && !qtbmgsxxXzxkxxTables.isEmpty()) {
				Element qtbmgsxxXzxkxxTable = qtbmgsxxXzxkxxTables.get(0);
				Elements qtbmgsxxXzxkxxTrs = qtbmgsxxXzxkxxTable.select("tr");
				qtbmgsxxXzxkxxTrs.remove(0);
				qtbmgsxxXzxkxxTrs.remove(0);
				for (Element qtbmgsxxXzxkxxTr : qtbmgsxxXzxkxxTrs) {
					Elements qtbmgsxxXzxkxxTds = qtbmgsxxXzxkxxTr.select("td");
					if (qtbmgsxxXzxkxxTds.size() == 9) {
						String xkwjNum = qtbmgsxxXzxkxxTds.get(1).text();
						String xkwjName = qtbmgsxxXzxkxxTds.get(2).text();
						String xzxkstartDate = qtbmgsxxXzxkxxTds.get(3).text();
						String xzxkendDate = qtbmgsxxXzxkxxTds.get(4).text();
						String xkAuthority = qtbmgsxxXzxkxxTds.get(5).text();
						String xkContent = qtbmgsxxXzxkxxTds.get(6).text();
						String status = qtbmgsxxXzxkxxTds.get(7).text();
						String detail = qtbmgsxxXzxkxxTds.get(8).text();
						
						OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo = new OthrdeptpubAAdmlicInfo();
						qtbmgsXzxkXzxkInfo.setLicenceNum(xkwjNum);
						qtbmgsXzxkXzxkInfo.setLicenceName(xkwjName);
						qtbmgsXzxkXzxkInfo.setStartDateTime(xzxkstartDate);
						qtbmgsXzxkXzxkInfo.setEndDateTime(xzxkendDate);
						qtbmgsXzxkXzxkInfo.setDeciAuthority(xkAuthority);
						qtbmgsXzxkXzxkInfo.setContent(xkContent);
						qtbmgsXzxkXzxkInfo.setStatus(status);
						qtbmgsXzxkXzxkInfo.setDetail(detail);
						qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
					}
				}
				
				if (isDebug) {
					qtbmgsXzxkInfo.setHtml(qtbmgsxxXzxkxxTable.toString());
				}
			}
		}
		qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);
		
		qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);
		//-----------------其他部门公示信息-->行政许可信息 end-----------------------
		
		//-----------------其他部门公示信息-->行政处罚信息 start-----------------------
		OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
		List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();
		Object qtbmgsxx_xzcfxx_object = resultHtmlMap.get("qtbmgsxx_xzcfxx");
		if (qtbmgsxx_xzcfxx_object != null) {
			String qtbmgsxx_xzcfxx_page = (String) qtbmgsxx_xzcfxx_object;
			Document qtbmgsxxXzcfxxDoc = Jsoup.parse(qtbmgsxx_xzcfxx_page);
			Elements qtbmgsxxXzcfxxTables = qtbmgsxxXzcfxxDoc.select("table");
			if (qtbmgsxxXzcfxxTables != null && !qtbmgsxxXzcfxxTables.isEmpty()) {
				Element qtbmgsxxXzcfxxTable = qtbmgsxxXzcfxxTables.get(0);
				Elements qtbmgsxxXzcfxxTrs = qtbmgsxxXzcfxxTable.select("tr");
				qtbmgsxxXzcfxxTrs.remove(0);
				qtbmgsxxXzcfxxTrs.remove(0);
				for (Element qtbmgsxxXzcfxxTr : qtbmgsxxXzcfxxTrs) {
					Elements qtbmgsxxXzcfxxTds = qtbmgsxxXzcfxxTr.select("td");
					if (qtbmgsxxXzcfxxTds.size() == 6) {
						String xzcfjdsNum = qtbmgsxxXzcfxxTds.get(1).text();
						String wfxwType = qtbmgsxxXzcfxxTds.get(2).text();
						String xzcfContent = qtbmgsxxXzcfxxTds.get(3).text();
						String zcxzcfjdjgName = qtbmgsxxXzcfxxTds.get(4).text();
						String zcxzcfjdDate = qtbmgsxxXzcfxxTds.get(5).text();
						
						OthrdeptpubAAdmpunishInfo qtbmgsXzcfXzcfInfo = new OthrdeptpubAAdmpunishInfo();
						qtbmgsXzcfXzcfInfo.setPunishRepNum(xzcfjdsNum);
						qtbmgsXzcfXzcfInfo.setIllegalActType(wfxwType);
						qtbmgsXzcfXzcfInfo.setPunishContent(xzcfContent);
						qtbmgsXzcfXzcfInfo.setDeciAuthority(zcxzcfjdjgName);
						qtbmgsXzcfXzcfInfo.setDeciDateTime(zcxzcfjdDate);
						qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
					}
				}
				
				if (isDebug) {
					qtbmgsXzcfInfo.setHtml(qtbmgsxxXzcfxxTable.toString());
				}
			}
		}
		qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);
		
		qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);
		//-----------------其他部门公示信息-->行政处罚信息 end-----------------------
		
		gsxtFeedJson.setAicPubInfo(gsgsInfo);
		gsxtFeedJson.setEntPubInfo(qygsInfo);
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);
		
		return gsxtFeedJson;
	}
}

package com.crawler.gsxt.htmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.StringUtil;
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
import com.crawler.gsxt.domain.json.AicpubRegRevokeInfo;
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
public class GsxtShanghaiParser {
	
	public AicFeedJson shanghaiResultParser(String resultHtmls, Boolean isDebug) {
		
		//解析result
		AicFeedJson gsxtFeedJson = new AicFeedJson();
		//一、工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();
		Gson gson = new Gson();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		
		Object gsgsxx_object = resultHtmlMap.get("gsgsxx");
		if (gsgsxx_object != null) {
			
			String gsgsxxHtml = (String)gsgsxx_object;
			Document gsgsxxDoc = Jsoup.parse(gsgsxxHtml);
			
			//-----------------工商公示信息-->登记信息 start-----------------------
			AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();
			Elements gsgsDjxxDivs = gsgsxxDoc.getElementsByAttributeValue("rel", "layout-01_01");
			for (Element gsgsDjxxDiv : gsgsDjxxDivs) {
				Elements gsgsDjxxTables = gsgsDjxxDiv.select("table");
				if (gsgsDjxxTables != null && !gsgsDjxxTables.isEmpty()) {
					Element gsgsDjxxTable = gsgsDjxxTables.get(0);
					if (StringUtil.isBlank(gsgsDjxxTable.attr("id"))) {
						//基本信息
						AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();
						
						Elements gsgsxxDjjbThs = gsgsDjxxTable.select("th");
						Elements gsgsxxDjjbTds = gsgsDjxxTable.select("td");
						gsgsxxDjjbThs.remove(0);
								
						for (int i = 0; i < gsgsxxDjjbThs.size(); i++) {
							String gsgsxxDjjbTh = gsgsxxDjjbThs.get(i).text().replace(" ", "");
							String gsgsxxDjjbTd = gsgsxxDjjbTds.get(i).text();
							
							switch (gsgsxxDjjbTh) {
							case "注册号/统一社会信用代码":
								gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
								break;
							case "注册号":
								gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
								break;
							case "统一社会信用代码":
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
						
						if(isDebug) {
							gsgsDjJbInfo.setHtml(gsgsDjxxTable.toString());
						}
						
						gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);
						
					} else if ("investorTable".equals(gsgsDjxxTable.attr("id"))) {
						//股东信息
						//登记信息 -->股东及出资信息
						List<AicpubRegStohrStohrinvestInfo> gsgsDjGdjczList = new ArrayList<AicpubRegStohrStohrinvestInfo>();
						Object gsgsxx_gdxx_detail_object = resultHtmlMap.get("gsgsxx_gdxx_detail");
						if (gsgsxx_gdxx_detail_object != null) {
							ArrayList<String> gdDetailList = (ArrayList<String>)gsgsxx_gdxx_detail_object;
							for (String gdxxDetailHtml : gdDetailList) {
								Document gdxxDetailDoc = Jsoup.parse(gdxxDetailHtml);
								Element gdxxDetailTable = gdxxDetailDoc.getElementById("investor");
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
										} else if (tdSize == 7) {
											String stockholder = gdxxDetail_tds.get(0).text();
											String subAmount = gdxxDetail_tds.get(1).text();
											String paidAmount = gdxxDetail_tds.get(2).text();
											String sub_method = gdxxDetail_tds.get(3).text();
											String sub_amount = gdxxDetail_tds.get(4).text();
											String sub_czDate = gdxxDetail_tds.get(5).text();
											
											AicpubRegStohrStohrinvestInfo.AmountDetail subAmountDetail = gsgsDjGdjczInfo.new AmountDetail();
											AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = gsgsDjGdjczInfo.new AmountDetail();
											List<AmountDetail> subAmountDetailList = new ArrayList<AicpubRegStohrStohrinvestInfo.AmountDetail>();
											List<AmountDetail> paidAmountDetailList = new ArrayList<AicpubRegStohrStohrinvestInfo.AmountDetail>();
											
											subAmountDetail.investMethod = sub_method;
											subAmountDetail.investAmount = sub_amount;
											subAmountDetail.investDateTime = sub_czDate;
											paidAmountDetail.investMethod = "";
											paidAmountDetail.investAmount = "";
											paidAmountDetail.investDateTime = "";
											gsgsDjGdjczInfo.setStockholder(stockholder);
											gsgsDjGdjczInfo.setSubAmount(subAmount);
											gsgsDjGdjczInfo.setPaidAmount(paidAmount);
											subAmountDetailList.add(subAmountDetail);
											paidAmountDetailList.add(paidAmountDetail);
											gsgsDjGdjczInfo.setSubAmountDetails(subAmountDetailList);
											gsgsDjGdjczInfo.setPaidAmountDetails(paidAmountDetailList);
										}
									}
									
									gsgsDjGdjczList.add(gsgsDjGdjczInfo);
								}
							}
						}
						
						//登记信息 -->股东信息
						List<AicpubRegStohrInfo> gsgsDjGdList = new ArrayList<AicpubRegStohrInfo>();
						Elements gdxx_trs = gsgsDjxxTable.select("tr");
						gdxx_trs.remove(0);
						gdxx_trs.remove(0);
						int i = 0;
						for (Element gdxx_tr : gdxx_trs) {
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
									if (gsgsDjGdjczList.size() > i) {
										gsgsdjgdInfo.setStohrInvestInfo(gsgsDjGdjczList.get(i++));
									}
								}
							} 
							
							gsgsDjGdList.add(gsgsdjgdInfo);
						}
						gsgsDjInfo.setStohrInfos(gsgsDjGdList);
					} else if ("alterTable".equals(gsgsDjxxTable.attr("id"))) {
						//变更信息or撤销信息
						List<AicpubRegChangeInfo> gsgsDjBgList = new ArrayList<AicpubRegChangeInfo>();
						List<AicpubRegRevokeInfo> gsgsDjCxList = new ArrayList<AicpubRegRevokeInfo>();
						
						Elements gsgsDjxxTrs = gsgsDjxxTable.select("tr");
						if (gsgsDjxxTrs != null && !gsgsDjxxTrs.isEmpty()) {
							String textContent = gsgsDjxxTrs.get(0).text();
							
							if ("变更信息".equals(textContent)) {
								gsgsDjxxTrs.remove(0);
								gsgsDjxxTrs.remove(0);
								for (Element bgxx_tr : gsgsDjxxTrs) {
									Elements bgxx_tds = bgxx_tr.getElementsByTag("td");
									if (bgxx_tds.size() == 4) {
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
								
								gsgsDjInfo.setChangeInfos(gsgsDjBgList);
							} else if ("撤销信息".equals(textContent)) {
								gsgsDjxxTrs.remove(0);
								gsgsDjxxTrs.remove(0);
								for (Element bgxx_tr : gsgsDjxxTrs) {
									Elements bgxx_tds = bgxx_tr.getElementsByTag("td");
									if (bgxx_tds.size() == 4) {
										String revokeItem = bgxx_tds.get(0).text();
										String revokePreContent = bgxx_tds.get(1).text();
										String revokePostContent = bgxx_tds.get(2).text();
										String revokeDate = bgxx_tds.get(3).text();
										
										AicpubRegRevokeInfo gsgsDjcxInfo = new AicpubRegRevokeInfo();
										gsgsDjcxInfo.setRevokeItem(revokeItem);
										gsgsDjcxInfo.setRevokePreContent(revokePreContent);
										gsgsDjcxInfo.setRevokePostContent(revokePostContent);
										gsgsDjcxInfo.setRevokeDate(revokeDate);
										gsgsDjCxList.add(gsgsDjcxInfo);
									}
								}
								
								gsgsDjInfo.setRevokeInfos(gsgsDjCxList);
							}
						}
					}
				}
			}
			gsgsInfo.setRegInfo(gsgsDjInfo);
			//-----------------工商公示信息-->登记信息 end-----------------------
			
			//-----------------工商公示信息-->备案信息 start-----------------------
			AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
			
			Elements gsgsBaxxDivs = gsgsxxDoc.getElementsByAttributeValue("rel", "layout-01_02");
			for (Element gsgsBaxxDiv : gsgsBaxxDivs) {
				Elements gsgsBaxxTables = gsgsBaxxDiv.select("table");
				if (gsgsBaxxTables != null && !gsgsBaxxTables.isEmpty()) {
					Element gsgsBaxxTable = gsgsBaxxTables.get(0);
					if ("memberTable".equals(gsgsBaxxTable.attr("id"))) {
						//主要人员信息
						List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
						Elements zyryTrElements = gsgsBaxxTable.select("tr");
						for (Element zyryTr : zyryTrElements) {
							Elements zyryTdElements = zyryTr.select("td");
							if (zyryTdElements.size() == 6) {
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
						gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);
						
					} else if ("branchTable".equals(gsgsBaxxTable.attr("id"))) {
						//分支机构信息
						List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
						Elements gsgsBaFzjgTrs = gsgsBaxxTable.select("tr");
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
						gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);
						
					} else if (StringUtil.isBlank(gsgsBaxxTable.attr("id"))) {
						//清算信息
						AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
						Elements gsgsBaQsTds = gsgsBaxxTable.select("td");
						if (gsgsBaQsTds.size() == 2) {
							String leader = gsgsBaQsTds.get(0).text();
							String members = gsgsBaQsTds.get(1).text();
							List<String> memList = new ArrayList<String>();
							memList.add(members);
							gsgsBaQsInfo.setLeader(leader);
							gsgsBaQsInfo.setMembers(memList);
						}
						gsgsBaInfo.setClearInfo(gsgsBaQsInfo);
					}
				}
				
				//备案信息-->主管部门（出资人）信息
				List<AicpubArchiveMainDeptInfo> aicpubArchiveMainDeptInfos = new ArrayList<AicpubArchiveMainDeptInfo>();
				Element gsgsBaZgbmTable = gsgsBaxxDiv.getElementById("investorTable");
				if (gsgsBaZgbmTable != null) {
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
					gsgsBaInfo.setMainDeptInfo(aicpubArchiveMainDeptInfos);
				}
			}
			
			gsgsInfo.setArchiveInfo(gsgsBaInfo);
			//-----------------工商公示信息-->备案信息 end-----------------------
			
			//-----------------工商公示信息-->动产抵押登记信息 start-----------------------
			AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
			List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
			Element gsgsDcdydjTable = gsgsxxDoc.getElementById("mortageTable");
			if (gsgsDcdydjTable != null) {
				Elements gsgsDcdydjTrs = gsgsDcdydjTable.select("tr");
				for (Element gsgsDcdydjTr : gsgsDcdydjTrs) {
					Elements gsgsDcdydjTds = gsgsDcdydjTr.select("td");
					if (gsgsDcdydjTds.size() == 8) {
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
					gsgsDcdydjInfo.setHtml(gsgsDcdydjTable.toString());
				}
			}
			
			gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);
			gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);
			//-----------------工商公示信息-->动产抵押登记信息 end-----------------------
			
			//-----------------工商公示信息-->股权出质登记信息 start-----------------------
			AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
			List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
			Element gsgsGqczdjTable = gsgsxxDoc.getElementById("pledgeTable");
			if (gsgsGqczdjTable != null) {
				Elements gsgsGqczdjTrs = gsgsGqczdjTable.select("tr");
				for (Element gsgsGqczdjTr : gsgsGqczdjTrs) {
					Elements gsgsGqczdjTds = gsgsGqczdjTr.select("td");
					if (gsgsGqczdjTds.size() == 11) {
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
				}
				
				if(isDebug) {
					gsgsGqczdjInfo.setHtml(gsgsGqczdjTable.toString());
				}
			}
			
			gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);
			gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);
			//-----------------工商公示信息-->股权出质登记信息 end-----------------------
			
			//-----------------工商公示信息-->行政处罚信息 start-----------------------
			AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
			Element gsgsXzcfXzcfTable = gsgsxxDoc.getElementById("punishTable");
			if (gsgsXzcfXzcfTable != null) {
				Elements gsgsXzcfXzcfTrs = gsgsXzcfXzcfTable.select("tr");
				for (Element gsgsXzcfXzcfTr : gsgsXzcfXzcfTrs) {
					Elements gsgsGqczdjTds = gsgsXzcfXzcfTr.select("td");
					if (gsgsGqczdjTds.size() >= 6) {
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
						gsgsXzcfXzcfInfos.add(gsgsXzcfXzcfInfo);
					}
				}
				
				if(isDebug) {
					gsgsXzcfInfo.setHtml(gsgsXzcfXzcfTable.toString());
				}
			}
			
			gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos);
			gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);
			//-----------------工商公示信息-->行政处罚信息 end-----------------------
			
			//-----------------工商公示信息-->经营异常信息 start-----------------------
			AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
			List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();
			Element gsgsJyycTable = gsgsxxDoc.getElementById("exceptTable");
			if (gsgsJyycTable != null) {
				Elements gsgsJyycTrs = gsgsJyycTable.select("tr");
				for (Element gsgsJyycTr : gsgsJyycTrs) {
					Elements gsgsJyycTds = gsgsJyycTr.select("td");
					if (gsgsJyycTds.size() == 6) {
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
					gsgsJyycInfo.setHtml(gsgsJyycTable.toString());
				}
			}
			
			gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);
			gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);
			//-----------------工商公示信息-->经营异常信息 end-----------------------
			
			//-----------------工商公示信息-->严重违法信息 start-----------------------
			AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
			List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
			Element gsgsYzwfTable = gsgsxxDoc.getElementById("blackTable");
			if (gsgsYzwfTable != null) {
				Elements gsgsYzwfTrs = gsgsYzwfTable.select("tr");
				for (Element gsgsYzwfTr : gsgsYzwfTrs) {
					Elements gsgsYzwfTds = gsgsYzwfTr.select("td");
					if (gsgsYzwfTds.size() == 6) {
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
				}
				
				if(isDebug) {
					gsgsYzwfInfo.setHtml(gsgsYzwfTable.toString());
				}
			}
			
			gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);
			gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);
			//-----------------工商公示信息-->严重违法信息 end-----------------------
			
			//-----------------工商公示信息-->抽查检查信息 start-----------------------
			AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
			List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
			Element gsgsCcjcTable = gsgsxxDoc.getElementById("spotcheckTable");
			if (gsgsCcjcTable != null) {
				Elements gsgsCcjcTrs = gsgsCcjcTable.select("tr");
				for (Element gsgsCcjcTr : gsgsCcjcTrs) {
					Elements gsgsCcjcTds = gsgsCcjcTr.select("td");
					if (gsgsCcjcTds.size() == 5) {
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
					gsgsCcjcInfo.setHtml(gsgsCcjcTable.toString());
				}
			}
			
			gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);
			gsgsInfo.setCheckInfo(gsgsCcjcInfo);
			//-----------------工商公示信息-->抽查检查信息 end-----------------------
		}
		
		//二、企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();
		
		Object qygsxx_object = resultHtmlMap.get("qygsxx");
		if (qygsxx_object != null) {
			String qygsxxHtml = (String)qygsxx_object;
			Document qygsxxDoc = Jsoup.parse(qygsxxHtml);
			
			//-----------------企业公示信息-->企业年报 start-----------------------
			//企业年报列表信息--报送年度和发布日期
			Object qygsxx_qynb_detail_object = resultHtmlMap.get("qygsxx_qynb_detail");
			if (qygsxx_qynb_detail_object != null) {
				List<String> qynbDetailList = (ArrayList<String>)qygsxx_qynb_detail_object;
				List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
				Elements qygsnbDivs = qygsxxDoc.getElementsByAttributeValue("rel", "layout-02_01");
				if (qygsnbDivs != null && !qygsnbDivs.isEmpty()) {
					Elements qygsnbTrs = qygsnbDivs.get(0).select("tr");
					int k = 0;
					for (int j = 2; j < qygsnbTrs.size(); j++) {
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
						
						Elements qygsnbxxTables = qygsxxnbDetailDoc.select(".detail-info").select("table");
						for (Element qygsnbxxTable : qygsnbxxTables) {
							Elements qyjbxxThs = qygsnbxxTable.select("th");
							Elements qyjbxxTds = qygsnbxxTable.select("td");
							String itemTitle = qyjbxxThs.get(0).text();
							if (itemTitle != null && itemTitle.contains("年度报告")) {
								itemTitle = qyjbxxThs.get(1).text();
							}
							
							if ("企业基本信息".equals(itemTitle)) {
								EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
								qyjbxxThs.remove(0);
								qyjbxxThs.remove(0);
								for (int i = 0; i < qyjbxxThs.size(); i++) {
									String qyjbxxTh = qyjbxxThs.get(i).text();
									String qyjbxxTd = qyjbxxTds.get(i).text();
									
									switch (qyjbxxTh) {
										case "注册号/统一社会信用代码":
											qygsQynbJbInfo.setNum(qyjbxxTd);
											break;
										case "企业名称":
											qygsQynbJbInfo.setName(qyjbxxTd);
											break;
										case "企业联系电话":
											qygsQynbJbInfo.setTel(qyjbxxTd);
											break;
										case "邮政编码":
											qygsQynbJbInfo.setZipCode(qyjbxxTd);
											break;
										case "企业通信地址":
											qygsQynbJbInfo.setAddress(qyjbxxTd);
											break;
										case "企业电子邮箱":
											qygsQynbJbInfo.setEmail(qyjbxxTd);
											break;
										case "电子邮箱":
											qygsQynbJbInfo.setEmail(qyjbxxTd);
											break;
										case "有限责任公司本年度是否发生股东股权转让":
											qygsQynbJbInfo.setIsStohrEquTransferred(qyjbxxTd);
											break;
										case "企业经营状态":
											qygsQynbJbInfo.setOperatingStatus(qyjbxxTd);
											break;
										case "是否有网站或网店":
											qygsQynbJbInfo.setHasWebsiteOrStore(qyjbxxTd);
											break;
										case "是否有网站或网点":
											qygsQynbJbInfo.setHasWebsiteOrStore(qyjbxxTd);
											break;
										case "企业是否有投资信息或购买其他公司股权":
											qygsQynbJbInfo.setHasInvestInfoOrPurchOtherCorpEqu(qyjbxxTd);
											break;
										case "从业人数":
											qygsQynbJbInfo.setEmpNum(qyjbxxTd);
											break;
										default:
											break;
									}
								}
								
								qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);
								
							} else if ("企业资产状况信息".equals(itemTitle)) {
								EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
								qyjbxxThs.remove(0);
								for (int i = 0; i < qyjbxxThs.size(); i++) {
									String qyjbxxTh = qyjbxxThs.get(i).text();
									String qyjbxxTd = qyjbxxTds.get(i).text();
									
									switch (qyjbxxTh) {
										case "资产总额":
											qygsQynbQyzczkInfo.setAssetAmount(qyjbxxTd);
											break;
										case "所有者权益合计":
											qygsQynbQyzczkInfo.setTotalEquity(qyjbxxTd);
											break;
										case "营业总收入":
											qygsQynbQyzczkInfo.setSalesAmount(qyjbxxTd);
											break;
										case "利润总额":
											qygsQynbQyzczkInfo.setProfitAmount(qyjbxxTd);
											break;
										case "营业总收入中主营业务收入":
											qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(qyjbxxTd);
											break;
										case "净利润":
											qygsQynbQyzczkInfo.setNetProfit(qyjbxxTd);
											break;
										case "纳税总额":
											qygsQynbQyzczkInfo.setTaxesAmount(qyjbxxTd);
											break;
										case "负债总额":
											qygsQynbQyzczkInfo.setLiabilityAmount(qyjbxxTd);
											break;
										default:
											break;
									}
									
								}
								
								qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);
								
							} else if ("网站或网店信息".equals(itemTitle)) {
								//企业年报信息--> 网站或网店信息
								List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
								Elements wzwdxxTrs = qygsnbxxTable.select("tr");
								for (Element wzwdxxTr : wzwdxxTrs) {
									Elements wzwdxxTds = wzwdxxTr.select("td");
									if (wzwdxxTds.size() == 3) {
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
							} else if (itemTitle.indexOf("股东及出资信息") > -1) {
								//企业年报信息--> 股东及出资信息
								List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();
								Elements gdczxxTrs = qygsnbxxTable.select("tr");
								for (Element gdczxxTr : gdczxxTrs) {
									Elements gdczxxTds = gdczxxTr.select("td");
									if (gdczxxTds.size() == 7) {
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
								
							} else if ("对外投资信息".equals(itemTitle)) {
								//企业年报信息--> 对外投资信息
								List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
								Elements dwtzxxTrs = qygsnbxxTable.select("tr");
								for (Element dwtzxxTr : dwtzxxTrs) {
									Elements dwtzxxTds = dwtzxxTr.select("td");
									if (dwtzxxTds.size() == 2) {
										String tzslqyhgmgqqyName = dwtzxxTds.get(0).text();
										String regNum = dwtzxxTds.get(1).text();
										
										EntpubAnnreportExtinvestInfo qygsQynbDwtzInfo = new EntpubAnnreportExtinvestInfo();
										qygsQynbDwtzInfo.setEnterpriseName(tzslqyhgmgqqyName);
										qygsQynbDwtzInfo.setRegNum(regNum);
										qygsQynbDwtzInfos.add(qygsQynbDwtzInfo);
									}
								}
								qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzInfos);
							} else if ("对外提供保证担保信息".equals(itemTitle)) {
								//企业年报信息--> 对外提供保证担保信息
								List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();
								Elements dwdbxxTrs = qygsnbxxTable.select("tr");
								for (Element dwdbxxTr : dwdbxxTrs) {
									Elements dwdbxxTds = dwdbxxTr.select("td");
									if (dwdbxxTds.size() >= 7) {
										EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
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
							} else if ("股权变更信息".equals(itemTitle)) {
								//企业年报信息--> 股权变更信息
								List<EntpubAnnreportEquchangeInfo> qygsQynbGqbgInfos = new ArrayList<EntpubAnnreportEquchangeInfo>();
								Elements gqbgxxTrs = qygsnbxxTable.select("tr");
								for (Element gqbgxxTr : gqbgxxTrs) {
									if(!"".equals(gqbgxxTr.attr("id")) && !gqbgxxTr.hasAttr("style")) {
										Elements gqbgxxTds = gqbgxxTr.select("td");
										if (gqbgxxTds.size() == 4) {
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
								}
								qygsQynbInfo.setEquChangeInfos(qygsQynbGqbgInfos);
								
							} else if ("修改记录".equals(itemTitle)) {
								//企业年报信息--> 修改记录
								List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
								Elements xgjlxxTrs = qygsnbxxTable.select("tr");
								for (Element xgjlxxTr : xgjlxxTrs) {
									Elements xgjlxxTds = xgjlxxTr.select("td");
									if (xgjlxxTds.size() == 5) {
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
						
						qygsQynbInfos.add(qygsQynbInfo);
					}
				}
				qygsInfo.setAnnReports(qygsQynbInfos);
			}
			
			//-----------------企业公示信息-->企业年报 end-----------------------
			
			//-----------------企业公示信息-->股东及出资信息 start-----------------------
			EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
			Elements qygsgdczDivs = qygsxxDoc.getElementsByAttributeValue("rel", "layout-02_04");
			
			if (qygsgdczDivs != null && !qygsgdczDivs.isEmpty()) {
				//股东及出资信息-->股东及出资信息
				List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
				Elements qygsgdczTables = qygsgdczDivs.select("table");
				for (Element qygsgdczTable : qygsgdczTables) {
					Elements qygsgdczTrs = qygsgdczTable.select("tr");
					if (qygsgdczTrs != null && !qygsgdczTrs.isEmpty()) {
						String textContent = qygsgdczTrs.get(0).text();
						if (textContent.indexOf("股东及出资信息") > 0) {
							qygsgdczTrs.remove(0);
							qygsgdczTrs.remove(0);
							
							EntpubSStohrinvestInfo qygsQynbGdjczInfo = new EntpubSStohrinvestInfo();
							for (Element gdczxxTr : qygsgdczTrs) {
								Elements gdczxxTds = gdczxxTr.select(">td");
								if (gdczxxTds.size() >= 5) {
									String stockholder = gdczxxTds.get(0).text();
									String rjAmount = gdczxxTds.get(1).text();
									String sjAmount = gdczxxTds.get(2).text();
									
									qygsQynbGdjczInfo.setStockholder(stockholder);
									qygsQynbGdjczInfo.setSubAmount(rjAmount);
									qygsQynbGdjczInfo.setPaidAmount(sjAmount);
									
									
									Element qygsxxGdjczRjTd = gdczxxTds.get(3);
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
									
									
									Element qygsxxGdjczSjTd = gdczxxTds.get(4);
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
							qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);
						} else if ("变更信息".equals(textContent)) {
							//股东及出资信息-->变更信息
							List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<EntpubStohrinvestChangeInfo>();
							for (int j = 2; j < qygsgdczTrs.size(); j++) {
								Elements qygsbgxxTds = qygsgdczTrs.get(j).select("td");
								if (qygsbgxxTds.size() == 5) {
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
							}
								
							if (isDebug) {
								qygsGdjczInfo.setHtml(qygsgdczTable.toString());
							}
							
							qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);
							
							qygsInfo.setStohrInvestInfo(qygsGdjczInfo);
						}
					}
				}
				
			}
			//-----------------企业公示信息-->股东及出资信息 end-----------------------
			
			//-----------------企业公示信息-->股权变更信息 start-----------------------
			EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
			List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
			Element qygsGqbgTable = qygsxxDoc.getElementById("othStocktransTable");
			if (qygsGqbgTable != null) {
				Elements qygsGqbgxxTrs = qygsGqbgTable.select("tr");
				for (int j = 2; j < qygsGqbgxxTrs.size(); j++) {
					Elements qygsGqbgxxTds = qygsGqbgxxTrs.get(j).select("td");
					if (qygsGqbgxxTds.size() >= 5) {
						String stockholder = qygsGqbgxxTds.get(1).text();
						String bgqOwnershipRatio = qygsGqbgxxTds.get(2).text();
						String bghOwnershipRatio = qygsGqbgxxTds.get(3).text();
						String bgDate = qygsGqbgxxTds.get(4).text();
						
						EntpubEEquchangeInfo qygsGqbgGqbgInfo = new EntpubEEquchangeInfo();
						qygsGqbgGqbgInfo.setStockholder(stockholder);
						qygsGqbgGqbgInfo.setPreOwnershipRatio(bgqOwnershipRatio);
						qygsGqbgGqbgInfo.setPostOwnershipRatio(bghOwnershipRatio);
						qygsGqbgGqbgInfo.setDateTime(bgDate);
						qygsGqbgGqbgInfos.add(qygsGqbgGqbgInfo);
					}
				}
				
				if (isDebug) {
					qygsGqbgInfo.setHtml(qygsGqbgTable.toString());
				}
			}
			
			qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);
			
			qygsInfo.setEquChangeInfo(qygsGqbgInfo);
			//-----------------企业公示信息-->股权变更信息 end-----------------------
			
			//-----------------企业公示信息-->行政许可信息 start-----------------------
			//1.详情
			Object qygsxx_xzxkDetail_list = resultHtmlMap.get("qygsxx_xzxkDetail_list");
			List<EntpubAAdmlicInfo> xzxkDetails = new ArrayList<EntpubAAdmlicInfo>();
			if (qygsxx_xzxkDetail_list != null) {
				ArrayList<String> xzxkDetailList = (ArrayList<String>)qygsxx_xzxkDetail_list;
				for (String xzxkDetailHtml : xzxkDetailList) {
					Document qygsxxXzxkDetailDoc = Jsoup.parse(xzxkDetailHtml);
					Element xzxkDetailTable = qygsxxXzxkDetailDoc.getElementById("permitAlt");
					if (xzxkDetailTable != null) {
						Elements xzxkDetailTrs = xzxkDetailTable.select("tr");
						EntpubAAdmlicInfo entpubAAdmlicInfo = new EntpubAAdmlicInfo();
						for (Element xzxkDetailTr : xzxkDetailTrs) {
							Elements xzxkDetailTds = xzxkDetailTr.select("td");
							if (xzxkDetailTds.size() == 5) {
								String changeItem = xzxkDetailTds.get(1).text();
								String changeDateTime = xzxkDetailTds.get(2).text();
								String changePreContent = xzxkDetailTds.get(3).text();
								String changePostContent = xzxkDetailTds.get(4).text();
								
								EntpubAAdmlicInfo.Detail detail = entpubAAdmlicInfo.new Detail();
								detail.changeItem = changeItem;
								detail.changeDateTime = changeDateTime;
								detail.changePreContent = changePreContent;
								detail.changePostContent = changePostContent;
								
							}
						}
						
						xzxkDetails.add(entpubAAdmlicInfo);
					}
				}
			}
			
			//2.
			List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
			EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
			Elements qygsXzxkDivs = qygsxxDoc.getElementsByAttributeValue("rel", "layout-02_02");
			if (qygsXzxkDivs != null && !qygsXzxkDivs.isEmpty()) {
				Elements qygsXzxkTables = qygsXzxkDivs.select("table");
				if (qygsXzxkTables != null && !qygsXzxkTables.isEmpty()) {
					Element qygsXzxkTable = qygsXzxkTables.get(0);
					if (qygsXzxkTable != null) {
						Elements qygsXzxkxxTrs = qygsXzxkTable.select("tr");
						
						int i = 0;
						for (Element qygsXzxkxxTr : qygsXzxkxxTrs) {
							Elements qygsXzxkxxTds = qygsXzxkxxTr.select("td");
							if (qygsXzxkxxTds.size() >= 9) {
								String xkwjNum = qygsXzxkxxTds.get(1).text();
								String xkwjName = qygsXzxkxxTds.get(2).text();
								String xzxkstartDate = qygsXzxkxxTds.get(3).text();
								String xzxkendDate = qygsXzxkxxTds.get(4).text();
								String xkAuthority = qygsXzxkxxTds.get(5).text();
								String xkContent = qygsXzxkxxTds.get(6).text();
								String status = qygsXzxkxxTds.get(7).text();
								String detail = qygsXzxkxxTds.get(8).text();
								
								EntpubAAdmlicInfo qygsXzxkXzxkInfo = new EntpubAAdmlicInfo();
								qygsXzxkXzxkInfo.setLicenceNum(xkwjNum);
								qygsXzxkXzxkInfo.setLicenceName(xkwjName);
								qygsXzxkXzxkInfo.setStartDateTime(xzxkstartDate);
								qygsXzxkXzxkInfo.setEndDateTime(xzxkendDate);
								qygsXzxkXzxkInfo.setDeciAuthority(xkAuthority);
								qygsXzxkXzxkInfo.setContent(xkContent);
								qygsXzxkXzxkInfo.setStatus(status);
								qygsXzxkXzxkInfo.setAdmlicDetail(xzxkDetails.get(i++).getAdmlicDetail());
								qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
							}
						}
						if (isDebug) {
							qygsXzxkInfo.setHtml(qygsXzxkTable.toString());
						}
					}
				}
				
			}
			
			qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
			
			qygsInfo.setAdmLicInfo(qygsXzxkInfo);
			//-----------------企业公示信息-->行政许可信息 end-----------------------
			
			//-----------------企业公示信息-->知识产权出质登记信息 start-----------------------
			EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
			List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
			Elements qygsZscqczdjDivs = qygsxxDoc.getElementsByAttributeValue("rel", "layout-02_03");
			if (qygsZscqczdjDivs != null && !qygsZscqczdjDivs.isEmpty()) {
				Element qygsZscqczdjxxDiv = qygsZscqczdjDivs.get(0);
				if (qygsZscqczdjxxDiv != null) {
					Elements qygsZscqczdjxxTables = qygsZscqczdjxxDiv.select("table");
					if (qygsZscqczdjxxTables != null && !qygsZscqczdjxxTables.isEmpty()) {
						Elements qygsZscqczdjxxTrs = qygsZscqczdjxxTables.get(0).select("tr");
						for (int j = 2; j < qygsZscqczdjxxTrs.size(); j++) {
							Elements qygsZscqczdjxxTds = qygsZscqczdjxxTrs.get(j).select("td");
							if (qygsZscqczdjxxTds.size() >= 9) {
								String regNum = qygsZscqczdjxxTds.get(1).text();
								String zscqname = qygsZscqczdjxxTds.get(2).text();
								String zscqtype = qygsZscqczdjxxTds.get(3).text();
								String czrName = qygsZscqczdjxxTds.get(4).text();
								String zqrName = qygsZscqczdjxxTds.get(5).text();
								String zqdjDeadline = qygsZscqczdjxxTds.get(6).text();
								String status = qygsZscqczdjxxTds.get(7).text();
								String changeSitu = qygsZscqczdjxxTds.get(8).text();
								
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
					}
					
					if (isDebug) {
						qygsZscqczdjInfo.setHtml(qygsZscqczdjxxDiv.toString());
					}
				}
				
			}
			
			qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);
			
			qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);
			//-----------------企业公示信息-->知识产权出质登记信息 end-----------------------
			
			//-----------------企业公示信息-->行政处罚信息 start-----------------------
			EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
			List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
			Element qygsXzcfXzcfTable = qygsxxDoc.getElementById("ePunishTable");
			if (qygsXzcfXzcfTable != null) {
				Elements qygsXzcfXzcfxxTrs = qygsXzcfXzcfTable.select("tr");
				for (int j = 2; j < qygsXzcfXzcfxxTrs.size(); j++) {
					Elements qygsXzcfXzcfxxTds = qygsXzcfXzcfxxTrs.get(j).select("td");
					if (qygsXzcfXzcfxxTds.size() >= 7) {
						String xzcfjdsNum = qygsXzcfXzcfxxTds.get(1).text();
						String xzcfContent = qygsXzcfXzcfxxTds.get(2).text();
						String zcxzcfjdjgName = qygsXzcfXzcfxxTds.get(3).text();
						String zcxzcfjdDate = qygsXzcfXzcfxxTds.get(4).text();
						String wfxwType = qygsXzcfXzcfxxTds.get(5).text();
						String note = qygsXzcfXzcfxxTds.get(6).text();
						
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
					qygsXzcfInfo.setHtml(qygsXzcfXzcfTable.toString());
				}
			}
			
			qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);
			
			qygsInfo.setAdmPunishInfo(qygsXzcfInfo);
			//-----------------企业公示信息-->行政处罚信息 end-----------------------
		}
		
		//三、其他部门公示信息
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();
		Object qtbmgsxx_object = resultHtmlMap.get("qtbmgsxx");
		if (qtbmgsxx_object != null) {
			String qtbmgsxxHtml = (String)qtbmgsxx_object;
			Document qtbmgsxxDoc = Jsoup.parse(qtbmgsxxHtml);
			
			//-----------------其他部门公示信息-->行政许可信息 start-----------------------
			OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			Elements qtbmgsXzxkxxDivs = qtbmgsxxDoc.getElementsByAttributeValue("rel", "layout-03_01");
			if (qtbmgsXzxkxxDivs != null && !qtbmgsXzxkxxDivs.isEmpty()) {
				Elements qtbmgsXzxkxxTables = qtbmgsXzxkxxDivs.get(0).select("table");
				if (qtbmgsXzxkxxTables != null && !qtbmgsXzxkxxTables.isEmpty()) {
					Elements qtbmgsXzxkxxTrs = qtbmgsXzxkxxTables.get(0).select("tr");
					for (Element qtbmgsXzxkxxTr : qtbmgsXzxkxxTrs) {
						Elements qtbmgsXzxkxxTds = qtbmgsXzxkxxTr.select("td");
						if (qtbmgsXzxkxxTds.size() == 9) {
							String xkwjNum = qtbmgsXzxkxxTds.get(1).text();
							String xkwjName = qtbmgsXzxkxxTds.get(2).text();
							String xzxkstartDate = qtbmgsXzxkxxTds.get(3).text();
							String xzxkendDate = qtbmgsXzxkxxTds.get(4).text();
							String xkAuthority = qtbmgsXzxkxxTds.get(5).text();
							//许可内容是一个文件下载链接
							String xkContent = "";
							Element xkContentTd = qtbmgsXzxkxxTds.get(6);
							Elements xkContentAs = xkContentTd.select("a");
							if (xkContentAs != null && !xkContentAs.isEmpty()) {
								Element xkContentA = xkContentAs.get(0);
								if (xkContentA != null) {
									String attrVal = xkContentA.attr("onclick");
									int beginIndex = attrVal.indexOf("'");
									int endIndex = attrVal.lastIndexOf("'");
									String fileName = attrVal.substring(beginIndex+1, endIndex);
									xkContent = "https://www.sgs.gov.cn/notice/download/permit_content_download?name=" + fileName;
								}
							}
							
							String status = qtbmgsXzxkxxTds.get(7).text();
							String detail = qtbmgsXzxkxxTds.get(8).text();
							
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
				}
				
				if (isDebug) {
					qtbmgsXzxkInfo.setHtml(qtbmgsXzxkxxDivs.toString());
				}
			}
			qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);
			
			qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);
			//-----------------其他部门公示信息-->行政许可信息 end-----------------------
			
			//-----------------其他部门公示信息-->行政处罚信息 start-----------------------
			OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
			List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();
			Element qtbmgsXzcfTable = qtbmgsxxDoc.getElementById("oPunishTable");
			if (qtbmgsXzcfTable != null) {
				Elements qtbmgsXzcfxxTrs = qtbmgsXzcfTable.select("tr");
				for (int j = 2; j < qtbmgsXzcfxxTrs.size(); j++) {
					Elements qtbmgsXzcfxxTds = qtbmgsXzcfxxTrs.get(j).select("td");
					if (qtbmgsXzcfxxTds.size() == 6) {
						String xzcfjdsNum = qtbmgsXzcfxxTds.get(1).text();
						String wfxwType = qtbmgsXzcfxxTds.get(2).text();
						String xzcfContent = qtbmgsXzcfxxTds.get(3).text();
						String zcxzcfjdjgName = qtbmgsXzcfxxTds.get(4).text();
						String zcxzcfjdDate = qtbmgsXzcfxxTds.get(5).text();
						
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
					qtbmgsXzcfInfo.setHtml(qtbmgsXzcfTable.toString());
				}
			}
			
			qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);
			
			qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);
		}
		//-----------------其他部门公示信息-->行政处罚信息 end-----------------------
		
		//四、司法协助公示信息
		JudasspubInfo sfxzgsInfo = new JudasspubInfo();
		Object sfxzgsxx_object = resultHtmlMap.get("sfxzgsxx");
		if (sfxzgsxx_object != null) {
			String sfxzgsxxHtml = (String)sfxzgsxx_object;
			Document sfxzgsxxDoc = Jsoup.parse(sfxzgsxxHtml);
			
			//-----------------司法协助公示信息-->股权冻结信息 start-----------------------
			JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
			List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
			Elements sfxzgsDivs = sfxzgsxxDoc.getElementsByAttributeValue("rel", "layout-06_01");
			if (sfxzgsDivs != null && !sfxzgsDivs.isEmpty()) {
				Elements sfxzgsxxTables = sfxzgsDivs.get(0).select("table");
				if (sfxzgsxxTables != null && !sfxzgsxxTables.isEmpty()) {
					Elements sfxzgsxxTrs = sfxzgsxxTables.get(0).select("tr");
					for (int j = 2; j < sfxzgsxxTrs.size(); j++) {
						Elements sfxzgsxxTds = sfxzgsxxTrs.get(j).select("td");
						if (sfxzgsxxTds.size() == 7) {
							String bzxPerson = sfxzgsxxTds.get(1).text();
							String gqAmount = sfxzgsxxTds.get(2).text();
							String exeCourt = sfxzgsxxTds.get(3).text();
							String xzgstzsNum = sfxzgsxxTds.get(4).text();
							String status = sfxzgsxxTds.get(5).text();
							String detail = sfxzgsxxTds.get(6).text();
							
							JudasspubEEqufreezeInfo sfxzgsGqdjGqdjInfo = new JudasspubEEqufreezeInfo();
							sfxzgsGqdjGqdjInfo.setExecutedPerson(bzxPerson);
							sfxzgsGqdjGqdjInfo.setEquAmount(gqAmount);
							sfxzgsGqdjGqdjInfo.setExeCourt(exeCourt);
							sfxzgsGqdjGqdjInfo.setAssistPubNoticeNum(xzgstzsNum);
							sfxzgsGqdjGqdjInfo.setStatus(status);
							sfxzgsGqdjGqdjInfo.setDetail(detail);
							sfxzgsGqdjGqdjInfos.add(sfxzgsGqdjGqdjInfo);
						}
					}
					
					if (isDebug) {
						sfxzgsGqdjInfo.setHtml(sfxzgsxxTables.toString());
					}
				}
			}
			
			sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);
			
			sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);
			//-----------------司法协助公示信息-->股权冻结信息 end-----------------------
			
			//-----------------司法协助公示信息-->股权变更信息 start-----------------------
			JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			Elements sfxzgsGdbgxxDivs = sfxzgsxxDoc.getElementsByAttributeValue("rel", "layout-06_02");
			if (sfxzgsGdbgxxDivs != null && !sfxzgsGdbgxxDivs.isEmpty()) {
				Elements sfxzgsGdbgxxTables = sfxzgsGdbgxxDivs.get(0).select("table");
				if (sfxzgsGdbgxxTables != null && !sfxzgsGdbgxxTables.isEmpty()) {
					Elements sfxzgsGdbgxxTrs = sfxzgsGdbgxxTables.get(0).select("tr");
					for (int j = 2; j < sfxzgsGdbgxxTrs.size(); j++) {
						Elements sfxzgsxxTds = sfxzgsGdbgxxTrs.get(j).select("td");
						if (sfxzgsxxTds.size() == 6) {
							String bzxPerson = sfxzgsxxTds.get(1).text();
							String gqAmount = sfxzgsxxTds.get(2).text();
							String srPerson = sfxzgsxxTds.get(3).text();
							String exeCourt = sfxzgsxxTds.get(4).text();
							String detail = sfxzgsxxTds.get(5).text();
							
							JudasspubSStohrchangeInfo sfxzgsGdbgGdbgInfo = new JudasspubSStohrchangeInfo();
							sfxzgsGdbgGdbgInfo.setExecutedPerson(bzxPerson);
							sfxzgsGdbgGdbgInfo.setEquAmount(gqAmount);
							sfxzgsGdbgGdbgInfo.setAssignee(srPerson);
							sfxzgsGdbgGdbgInfo.setExeCourt(exeCourt);
							sfxzgsGdbgGdbgInfo.setDetail(detail);
							sfxzgsGdbgGdbgInfos.add(sfxzgsGdbgGdbgInfo);
						}
					}
					
					if (isDebug) {
						sfxzgsGdbgInfo.setHtml(sfxzgsGdbgxxTables.toString());
					}
				}
				sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);
			}
			
			
			sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);
			//-----------------司法协助公示信息-->股权变更信息 end-----------------------
		}
		
		gsxtFeedJson.setAicPubInfo(gsgsInfo);
		gsxtFeedJson.setEntPubInfo(qygsInfo);
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);
		gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);
		
		return gsxtFeedJson;
	}
}

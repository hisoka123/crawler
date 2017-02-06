package com.crawler.gsxt.htmlparser;

/**
 * @author kingly
 * @date 2016年5月13日
 *
 */
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
import com.crawler.gsxt.domain.json.EntpubAnnreportEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportExtguaranteeInfo;
import com.crawler.gsxt.domain.json.JudasspubEEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubInfo;
import com.crawler.gsxt.domain.json.JudasspubSStohrchangeInfo;
import com.crawler.gsxt.domain.json.JudasspubStohrchangeInfo;
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
public class GsxtGuangdongParser extends AbstractGsxtParser {
	
	public AicFeedJson guangdongResultParser(String resultHtmls, Boolean isDebug) {
		//解析result
		Gson gson = new Gson();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		Object gsgsxx_djxx_page_object = resultHtmlMap.get("gsgsxx_djxx_page");
		if (gsgsxx_djxx_page_object != null) {
			return guangdongResultParserOne(resultHtmlMap, isDebug);
		} else {
			return guangdongResultParserTwo(resultHtmlMap, isDebug);
		}
	}
	
	public AicFeedJson guangdongResultParserTwo(Map<String, Object> resultHtmlMap, Boolean isDebug) {
		AicFeedJson gsxtFeedJson = new AicFeedJson();
		//一、工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();
		Gson gson = new Gson();
		
		Object gsgsxx_object = resultHtmlMap.get("gsgsxx_page");
		//-----------------工商公示信息-->登记信息 start-----------------------
		if (gsgsxx_object != null) {
			
			String gsgsxxHtml = (String)gsgsxx_object;
			Document gsgsxxDoc = Jsoup.parse(gsgsxxHtml);
			
			Element djxxDiv = gsgsxxDoc.getElementById("jibenxinxi");
			AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();
			AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();
			if (djxxDiv != null) {
				Elements djxx_tables = djxxDiv.select("table");
				if (djxx_tables != null && !djxx_tables.isEmpty()) {
					Element jbxx_table = djxx_tables.get(0);
					
					//登记信息 -->基本信息
					Elements gsgsxxDjjbThs = jbxx_table.select("th");
					Elements gsgsxxDjjbTds = jbxx_table.select("td");
					gsgsxxDjjbThs.remove(0);
					
					for (int i = 0; i < gsgsxxDjjbThs.size(); i++) {
						String gsgsxxDjjbTh = gsgsxxDjjbThs.get(i).text().replace(" ", "");
						String gsgsxxDjjbTd = gsgsxxDjjbTds.get(i).text();
						
						switch (gsgsxxDjjbTh) {
						case "注册号/统一社会信用代码":
							gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
							break;
						case "注册号":
							String regNum = gsgsDjJbInfo.getNum();
							gsgsxxDjjbTd = (regNum != null && !"".equals(regNum))? gsgsxxDjjbTd + "/" + regNum : gsgsxxDjjbTd;
							gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
							break;
						case "统一社会信用代码":
							String num = gsgsDjJbInfo.getNum();
							gsgsxxDjjbTd = (num != null && !"".equals(num))? num + "/" + gsgsxxDjjbTd : gsgsxxDjjbTd;
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
						case "注册资本":
							gsgsDjJbInfo.setRegCapital(gsgsxxDjjbTd);
							break;
						case "注册资金":
							gsgsDjJbInfo.setRegCapital(gsgsxxDjjbTd);
							break;
						case "成立日期":
							gsgsDjJbInfo.setRegDateTime(gsgsxxDjjbTd);
							break;
						case "住所":
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
						gsgsDjJbInfo.setHtml(jbxx_table.toString());
					}
				}
				gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);
				
				//登记信息 -->股东及出资信息
				List<AicpubRegStohrStohrinvestInfo> gsgsDjGdjczList = new ArrayList<AicpubRegStohrStohrinvestInfo>();
				Object gsgsxx_gdxx_detail_object = resultHtmlMap.get("gsgsxx_gdxx_detail");
				if (gsgsxx_gdxx_detail_object != null) {
					ArrayList<String> gdDetailList = (ArrayList<String>)gsgsxx_gdxx_detail_object;
					for (String gdxxDetailHtml : gdDetailList) {
						Document gdxxDetailDoc = Jsoup.parse(gdxxDetailHtml);
						Element gdxxDetailDiv = gdxxDetailDoc.getElementById("details");
						Elements gdxxDetailTables = gdxxDetailDiv.select("table");
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
								
								gsgsDjGdjczList.add(gsgsDjGdjczInfo);
							}
						}
					}
				}
				
				//登记信息 -->股东信息
				List<AicpubRegStohrInfo> gsgsDjGdList = new ArrayList<AicpubRegStohrInfo>();
				Element invDivElement = djxxDiv.getElementById("invDiv");
				if (invDivElement != null) {
					Elements guxxTables = invDivElement.select("table");
					if (guxxTables != null && !guxxTables.isEmpty()) {
						Element gdxx_table = guxxTables.get(0);
						Elements gdxx_trs = gdxx_table.select("tr");
						int i = 0;
						for (Element gdxx_tr : gdxx_trs) {
							Elements gdxx_tds = gdxx_tr.select("td");
							
							AicpubRegStohrInfo gsgsdjgdInfo = new AicpubRegStohrInfo();
							int trSize = gdxx_tds.size();
							if(trSize > 0) {
								String gdName = gdxx_tds.get(0).text();
								gsgsdjgdInfo.setName(gdName);
							}
							if (trSize > 1) {
								String idType = gdxx_tds.get(1).text();
								gsgsdjgdInfo.setIdType(idType);
							}
							if (trSize > 2) {
								String idNum = gdxx_tds.get(2).text();
								gsgsdjgdInfo.setIdNum(idNum);
							}
							if (trSize > 3) {
								String gdType = gdxx_tds.get(3).text();
								gsgsdjgdInfo.setType(gdType);
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
					}
				}
				gsgsDjInfo.setStohrInfos(gsgsDjGdList);
				
				//登记信息 -->变更信息
				List<AicpubRegChangeInfo> gsgsDjBgList = new ArrayList<AicpubRegChangeInfo>();
				Element bgxx_table = djxxDiv.getElementById("altTab");
				if (bgxx_table != null) {
					Elements bgxx_trs = bgxx_table.select("tr");
					
					for (Element bgxx_tr : bgxx_trs) {
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
				}
				gsgsDjInfo.setChangeInfos(gsgsDjBgList);
				
			}
			gsgsInfo.setRegInfo(gsgsDjInfo);
			//-----------------工商公示信息-->登记信息 end-----------------------
			
			//-----------------工商公示信息-->备案信息 start-----------------------
			AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
			
			//备案信息-->主要人员信息
			List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
			Element memDivElement = gsgsxxDoc.getElementById("memDiv");
			if (memDivElement != null) {
				Elements zyryTables = memDivElement.select("table");
				if (zyryTables != null && !zyryTables.isEmpty()) {
					Element zyryTableElement = zyryTables.get(0);
					if (zyryTableElement != null) {
						Elements zyryTrElements = zyryTableElement.select("tr");
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
					}
				}
			}
			gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);
			
			//备案信息-->分支机构信息
			List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
			Element gsgsBaFzjgDiv = gsgsxxDoc.getElementById("childDiv");
			if (gsgsBaFzjgDiv != null) {
				Elements gsgsBaFzjgTrs = gsgsBaFzjgDiv.select("tr");
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
			Element beianElement = gsgsxxDoc.getElementById("beian");
			if (beianElement != null) {
				Elements tables = beianElement.select("table");
				if(tables != null && !tables.isEmpty()) {
					Element gsgsBaQsTable = tables.get(tables.size()-1);
					Elements gsgsBaQsTds = gsgsBaQsTable.select("td");
					if (gsgsBaQsTds.size() == 2) {
						String leader = gsgsBaQsTds.get(0).text();
						String members = gsgsBaQsTds.get(1).text();
						List<String> memList = new ArrayList<String>();
						memList.add(members);
						gsgsBaQsInfo.setLeader(leader);
						gsgsBaQsInfo.setMembers(memList);
					}
				}
				
				if(isDebug) {
					gsgsBaInfo.setHtml(beianElement.toString());
				}
			}
			gsgsBaInfo.setClearInfo(gsgsBaQsInfo);
			
			gsgsInfo.setArchiveInfo(gsgsBaInfo);
			
			//-----------------工商公示信息-->备案信息 end-----------------------
			
			
			//-----------------工商公示信息-->动产抵押登记信息 start-----------------------
			AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
			List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
			Element gsgsDcdydjDiv = gsgsxxDoc.getElementById("dongchandiya");
			if (gsgsDcdydjDiv != null) {
				Element mortDivElement = gsgsDcdydjDiv.getElementById("#mortDiv");
				if (mortDivElement != null) {
					Elements gsgsDcdydjTables = mortDivElement.select("table");
					if (gsgsDcdydjTables != null && !gsgsDcdydjTables.isEmpty()) {
						Elements gsgsDcdydjTrs = gsgsDcdydjTables.get(0).select("tr");
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
					}
				}
				
				if(isDebug) {
					gsgsDcdydjInfo.setHtml(gsgsDcdydjDiv.toString());
				}
			}
			
			gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);
			gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);
			
			//-----------------工商公示信息-->动产抵押登记信息 end-----------------------
			
			
			//-----------------工商公示信息-->股权出质登记信息 start-----------------------
			AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
			List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
			Element gsgsGqczdjDiv = gsgsxxDoc.getElementById("guquanchuzhi");
			if (gsgsGqczdjDiv != null) {
				Element gdczdjElement = gsgsGqczdjDiv.getElementById("#pledgeDiv");
				if (gdczdjElement != null) {
					Elements gdczdjTables = gdczdjElement.select("table");
					if (gdczdjTables != null && !gdczdjTables.isEmpty()) {
						Elements gsgsGqczdjTrs = gdczdjTables.get(0).select("tr");
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
					}
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
			List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
			Element gsgsXzcfXzcfDiv = gsgsxxDoc.getElementById("xingzhengchufa");
			if (gsgsXzcfXzcfDiv != null) {
				Element xzcfTable = gsgsXzcfXzcfDiv.getElementById("#punTab");
				if (xzcfTable != null) {
					Elements gsgsXzcfXzcfTrs = xzcfTable.select("tr");
					for (Element gsgsXzcfXzcfTr : gsgsXzcfXzcfTrs) {
						Elements gsgsGqczdjTds = gsgsXzcfXzcfTr.select("td");
						if (gsgsGqczdjTds.size() == 6) {
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
				}
				
				if(isDebug) {
					gsgsXzcfInfo.setHtml(gsgsXzcfXzcfDiv.toString());
				}
			}
			
			gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos);
			gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);
			
			//-----------------工商公示信息-->行政处罚信息 end-----------------------
			
			//-----------------工商公示信息-->经营异常信息 start-----------------------
			AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
			List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();
			Element gsgsJyycDiv = gsgsxxDoc.getElementById("jingyingyichangminglu");
			if (gsgsJyycDiv != null) {
				Element jyycDiv = gsgsJyycDiv.getElementById("excDiv");
				if (jyycDiv != null) {
					Elements jyycTables = jyycDiv.select("table");
					if (jyycTables != null && !jyycTables.isEmpty()) {
						Elements gsgsJyycTrs = jyycTables.get(0).select("tr");
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
								gsgsJyycJyycInfo.setRemoveAuthority(zcjdAuthority);
								gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
							}
						}
					}
				}
				
				if(isDebug) {
					gsgsJyycInfo.setHtml(gsgsJyycDiv.toString());
				}
			}
			
			gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);
			gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);
			//-----------------工商公示信息-->经营异常信息 end-----------------------
			
			
			//-----------------工商公示信息-->严重违法信息 start-----------------------
			AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
			List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
			Element gsgsYzwfDiv = gsgsxxDoc.getElementById("yanzhongweifaqiye");
			if (gsgsYzwfDiv != null) {
				Element yzwfDiv = gsgsYzwfDiv.getElementById("serillDiv");
				if (yzwfDiv != null) {
					Elements yzwfTables = yzwfDiv.select("table");
					if (yzwfTables != null && !yzwfTables.isEmpty()) {
						Elements gsgsYzwfTrs = yzwfTables.select("tr");
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
					}
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
			List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
			Element gsgsCcjcDiv = gsgsxxDoc.getElementById("chouchaxinxi");
			if (gsgsCcjcDiv != null) {
				Element ccjcDiv = gsgsCcjcDiv.getElementById("spotCheckDiv");
				if (ccjcDiv != null) {
					Elements ccjcTables = ccjcDiv.select("table");
					Elements gsgsCcjcTrs = ccjcTables.get(0).select("tr");
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
				}
				
				if (isDebug) {
					gsgsCcjcInfo.setHtml(gsgsCcjcDiv.toString());
				}
			}
			
			gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);
			gsgsInfo.setCheckInfo(gsgsCcjcInfo);
			//-----------------工商公示信息-->抽查检查信息 end-----------------------
			
		}
		
		
		//二、企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();
		
		Object qygsxx_object = resultHtmlMap.get("qygsxx_page");
		if (qygsxx_object != null) {
			String qygsxxHtml = (String)qygsxx_object;
			Document qygsxxDoc = Jsoup.parse(qygsxxHtml);
			
			//-----------------企业公示信息-->企业年报 start-----------------------
			//企业年报列表信息--报送年度和发布日期
			Object qygsxx_qynb_detail_object = resultHtmlMap.get("qygsxx_qynb_detail");
			if (qygsxx_qynb_detail_object != null) {
				List<String> qynbDetailList = (ArrayList<String>)qygsxx_qynb_detail_object;
				List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();
				Element qygsnbDiv = qygsxxDoc.getElementById("qiyenianbao");
				if (qygsnbDiv != null) {
					Elements qygsnbTrs = qygsnbDiv.select("tr");
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
						
						Elements qygsnbxxTables = qygsxxnbDetailDoc.select("#sifapanding").select("table");
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
										case "是否有投资信息或购买其他公司股权":
											qygsQynbJbInfo.setHasInvestInfoOrPurchOtherCorpEqu(qyjbxxTd);
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
										case "其中：主营业务收入":
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
									if(!"".equals(wzwdxxTr.attr("id")) && !wzwdxxTr.hasAttr("style")) {
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
								}
								qygsQynbInfo.setWebsiteInfos(qygsQynbWzhwdInfos);
							} else if ("股东及出资信息".equals(itemTitle)) {
								//企业年报信息--> 股东及出资信息
								List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();
								Elements gdczxxTrs = qygsnbxxTable.select("tr");
								for (Element gdczxxTr : gdczxxTrs) {
									if(!"".equals(gdczxxTr.attr("id")) && !gdczxxTr.hasAttr("style")) {
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
								}
								qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);
								
							} else if ("对外投资信息".equals(itemTitle)) {
								//企业年报信息--> 对外投资信息
								List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
								Elements dwtzxxTrs = qygsnbxxTable.select("tr");
								for (Element dwtzxxTr : dwtzxxTrs) {
									if(!"".equals(dwtzxxTr.attr("id")) && !dwtzxxTr.hasAttr("style")) {
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
								}
								qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzInfos);
							} else if ("对外提供保证担保信息".equals(itemTitle)) {
								//企业年报信息--> 对外提供保证担保信息
								List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = new ArrayList<EntpubAnnreportExtguaranteeInfo>();
								Elements dwdbxxTrs = qygsnbxxTable.select("tr");
								for (Element dwdbxxTr : dwdbxxTrs) {
									if(!"".equals(dwdbxxTr.attr("id")) && !dwdbxxTr.hasAttr("style")) {
										EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo = new EntpubAnnreportExtguaranteeInfo();
										Elements dwdbxxTds = dwdbxxTr.select("td");
										if (dwdbxxTds.size() >= 7) {
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
									if(!"".equals(xgjlxxTr.attr("id")) && !xgjlxxTr.hasAttr("style")) {
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
			Element qygsgdczDiv = qygsxxDoc.getElementById("touziren");
			
			if (qygsgdczDiv != null) {
				//股东及出资信息-->股东及出资信息
				List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
				Element qygsgdczxxDiv = qygsgdczDiv.getElementById("gdDiv");
				if (qygsgdczxxDiv != null) {
					Elements gdczxxTables = qygsgdczxxDiv.select("table");
					if (gdczxxTables != null && !gdczxxTables.isEmpty()) {
						Element qygsxxGdjczTable = gdczxxTables.get(0);
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
				Element qygsbgxxDiv = qygsgdczDiv.getElementById("altInv");
				if (qygsbgxxDiv != null) {
					Elements qygsbgxxTables = qygsbgxxDiv.select("table");
					if (qygsbgxxTables != null && !qygsbgxxTables.isEmpty()) {
						Elements qygsbgxxTrs = qygsbgxxTables.get(0).select("tr");
						for (int j = 2; j < qygsbgxxTrs.size(); j++) {
							Elements qygsbgxxTds = qygsbgxxTrs.get(j).select("td");
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
					}
					
					if (isDebug) {
						qygsGdjczInfo.setHtml(qygsbgxxDiv.toString());
					}
				}
				
				qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);
				
				qygsInfo.setStohrInvestInfo(qygsGdjczInfo);
				
			}
			//-----------------企业公示信息-->股东及出资信息 end-----------------------
			
			
			//-----------------企业公示信息-->股权变更信息 start-----------------------
			EntpubEquchangeInfo qygsGqbgInfo = new EntpubEquchangeInfo();
			List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = new ArrayList<EntpubEEquchangeInfo>();
			Element qygsGqbgDiv = qygsxxDoc.getElementById("gudongguquan");
			if (qygsGqbgDiv != null) {
				Element qygsGqbgxxDiv = qygsGqbgDiv.getElementById("gqbg");
				if (qygsGqbgxxDiv != null) {
					Elements qygsGqbgxxTables = qygsGqbgxxDiv.select("table");
					if (qygsGqbgxxTables != null && !qygsGqbgxxTables.isEmpty()) {
						Elements qygsGqbgxxTrs = qygsGqbgxxTables.get(0).select("tr");
						for (int j = 2; j < qygsGqbgxxTrs.size(); j++) {
							Elements qygsGqbgxxTds = qygsGqbgxxTrs.get(j).select("td");
							if (qygsGqbgxxTds.size() == 6) {
								String stockholder = qygsGqbgxxTds.get(1).text();
								String bgqOwnershipRatio = qygsGqbgxxTds.get(2).text();
								String bghOwnershipRatio = qygsGqbgxxTds.get(3).text();
								String bgDate = qygsGqbgxxTds.get(4).text();
								String gsrq = qygsGqbgxxTds.get(5).text();
								
								EntpubEEquchangeInfo qygsGqbgGqbgInfo = new EntpubEEquchangeInfo();
								qygsGqbgGqbgInfo.setStockholder(stockholder);
								qygsGqbgGqbgInfo.setPreOwnershipRatio(bgqOwnershipRatio);
								qygsGqbgGqbgInfo.setPostOwnershipRatio(bghOwnershipRatio);
								qygsGqbgGqbgInfo.setDateTime(bgDate);
								qygsGqbgGqbgInfos.add(qygsGqbgGqbgInfo);
							}
						}
					}
				}
				
				if (isDebug) {
					qygsGqbgInfo.setHtml(qygsGqbgDiv.toString());
				}
			}
			
			qygsGqbgInfo.setEquChangeInfos(qygsGqbgGqbgInfos);
			
			qygsInfo.setEquChangeInfo(qygsGqbgInfo);
			//-----------------企业公示信息-->股权变更信息 end-----------------------
			
			//-----------------企业公示信息-->行政许可信息 start-----------------------
			EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
			List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
			Element qygsXzxkDiv = qygsxxDoc.getElementById("xingzhengxuke");
			if (qygsXzxkDiv != null) {
				Element qygsXzxkxxDiv = qygsXzxkDiv.getElementById("licenseRegDiv");
				if (qygsXzxkxxDiv != null) {
					Elements qygsXzxkxxTables = qygsXzxkxxDiv.select("table");
					if (qygsXzxkxxTables != null && !qygsXzxkxxTables.isEmpty()) {
						Elements qygsXzxkxxTrs = qygsXzxkxxTables.get(0).select("tr");
						
						for (Element qygsXzxkxxTr : qygsXzxkxxTrs) {
							Elements qygsXzxkxxTds = qygsXzxkxxTr.select("td");
							if (qygsXzxkxxTds.size() == 10) {
								String xkwjNum = qygsXzxkxxTds.get(1).text();
								String xkwjName = qygsXzxkxxTds.get(2).text();
								String xzxkstartDate = qygsXzxkxxTds.get(3).text();
								String xzxkendDate = qygsXzxkxxTds.get(4).text();
								String xkAuthority = qygsXzxkxxTds.get(5).text();
								String xkContent = qygsXzxkxxTds.get(6).text();
								String status = qygsXzxkxxTds.get(7).text();
								String gsrq = qygsXzxkxxTds.get(8).text();
								String detail = qygsXzxkxxTds.get(9).text();
								
								EntpubAAdmlicInfo qygsXzxkXzxkInfo = new EntpubAAdmlicInfo();
								qygsXzxkXzxkInfo.setLicenceNum(xkwjNum);
								qygsXzxkXzxkInfo.setLicenceName(xkwjName);
								qygsXzxkXzxkInfo.setStartDateTime(xzxkstartDate);
								qygsXzxkXzxkInfo.setEndDateTime(xzxkendDate);
								qygsXzxkXzxkInfo.setDeciAuthority(xkAuthority);
								qygsXzxkXzxkInfo.setContent(xkContent);
								qygsXzxkXzxkInfo.setStatus(status);
								qygsXzxkXzxkInfo.setDetail(detail);
								qygsXzxkXzxkInfos.add(qygsXzxkXzxkInfo);
							}
						}
					}
				}
				
				if (isDebug) {
					qygsXzxkInfo.setHtml(qygsXzxkDiv.toString());
				}
			}
			
			qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);
			
			qygsInfo.setAdmLicInfo(qygsXzxkInfo);
			//-----------------企业公示信息-->行政许可信息 end-----------------------
			
			//-----------------企业公示信息-->知识产权出质登记信息 start-----------------------
			EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
			List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
			Element qygsZscqczdjDiv = qygsxxDoc.getElementById("zhishichanquan");
			if (qygsZscqczdjDiv != null) {
				Element qygsZscqczdjxxDiv = qygsZscqczdjDiv.getElementById("zscqDiv");
				if (qygsZscqczdjxxDiv != null) {
					Elements qygsZscqczdjxxTables = qygsZscqczdjxxDiv.select("table");
					if (qygsZscqczdjxxTables != null && !qygsZscqczdjxxTables.isEmpty()) {
						Elements qygsZscqczdjxxTrs = qygsZscqczdjxxTables.get(0).select("tr");
						for (int j = 2; j < qygsZscqczdjxxTrs.size(); j++) {
							Elements qygsZscqczdjxxTds = qygsZscqczdjxxTrs.get(j).select("td");
							if (qygsZscqczdjxxTds.size() == 10) {
								String regNum = qygsZscqczdjxxTds.get(1).text();
								String zscqname = qygsZscqczdjxxTds.get(2).text();
								String zscqtype = qygsZscqczdjxxTds.get(3).text();
								String czrName = qygsZscqczdjxxTds.get(4).text();
								String zqrName = qygsZscqczdjxxTds.get(5).text();
								String zqdjDeadline = qygsZscqczdjxxTds.get(6).text();
								String status = qygsZscqczdjxxTds.get(7).text();
								String gsrq = qygsZscqczdjxxTds.get(8).text();
								String changeSitu = qygsZscqczdjxxTds.get(9).text();
								
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
				}
				
				if (isDebug) {
					qygsZscqczdjInfo.setHtml(qygsZscqczdjDiv.toString());
				}
			}
			
			qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);
			
			qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);
			//-----------------企业公示信息-->知识产权出质登记信息 end-----------------------
			
			//-----------------企业公示信息-->行政处罚信息 start-----------------------
			EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
			List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
			Element qygsXzcfXzcfDiv = qygsxxDoc.getElementById("xingzhengchufa");
			if (qygsXzcfXzcfDiv != null) {
				Element qygsXzcfXzcfxxDiv = qygsXzcfXzcfDiv.getElementById("xzcfDiv");
				if (qygsXzcfXzcfxxDiv != null) {
					Elements qygsXzcfXzcfxxTables = qygsXzcfXzcfxxDiv.select("table");
					if (qygsXzcfXzcfxxTables != null && !qygsXzcfXzcfxxTables.isEmpty()) {
						Elements qygsXzcfXzcfxxTrs = qygsXzcfXzcfxxTables.get(0).select("tr");
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
					}
				}
				
				if (isDebug) {
					qygsXzcfInfo.setHtml(qygsXzcfXzcfDiv.toString());
				}
			}
			
			qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);
			
			qygsInfo.setAdmPunishInfo(qygsXzcfInfo);
			//-----------------企业公示信息-->行政处罚信息 end-----------------------
		}
		
		//三、其他部门公示信息
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();
		Object qtbmgsxx_object = resultHtmlMap.get("qtgsxx_page");
		if (qtbmgsxx_object != null) {
			String qtbmgsxxHtml = (String)qtbmgsxx_object;
			Document qtbmgsxxDoc = Jsoup.parse(qtbmgsxxHtml);
			
			//-----------------其他部门公示信息-->行政许可信息 start-----------------------
			OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
			List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
			Element qtbmgsXzxkxxDiv = qtbmgsxxDoc.getElementById("licenseRegDiv");
			if (qtbmgsXzxkxxDiv != null) {
				Elements qtbmgsXzxkxxTables = qtbmgsXzxkxxDiv.select("table");
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
							String xkContent = qtbmgsXzxkxxTds.get(6).text();
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
					qtbmgsXzxkInfo.setHtml(qtbmgsXzxkxxDiv.toString());
				}
			}
			qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);
			
			qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);
			//-----------------其他部门公示信息-->行政许可信息 end-----------------------
			
			//-----------------其他部门公示信息-->行政处罚信息 start-----------------------
			OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
			List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();
			Element qtbmgsXzcfDiv = qtbmgsxxDoc.getElementById("xingzhengchufa");
			if (qtbmgsXzcfDiv != null) {
				Element qtbmgsXzcfxxDiv = qtbmgsXzcfDiv.getElementById("xzcfDiv");
				if (qtbmgsXzcfxxDiv != null) {
					Elements qtbmgsXzcfxxTables = qtbmgsXzcfxxDiv.select("table");
					Elements qtbmgsXzcfxxTrs = qtbmgsXzcfxxTables.get(0).select("tr");
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
				}
				if (isDebug) {
					qtbmgsXzcfInfo.setHtml(qtbmgsXzcfDiv.toString());
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
			Element sfxzgsDiv = sfxzgsxxDoc.getElementById("EquityFreeze");
			if (sfxzgsDiv != null) {
				Element sfxzgsxxDiv = sfxzgsDiv.getElementById("EquityFreezeDiv");
				if (sfxzgsxxDiv != null) {
					Elements sfxzgsxxTables = sfxzgsxxDiv.select("table");
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
					}
				}
				
				if (isDebug) {
					sfxzgsGqdjInfo.setHtml(sfxzgsDiv.toString());
				}
			}
			
			sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);
			
			sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);
			//-----------------司法协助公示信息-->股权冻结信息 end-----------------------
			
			//-----------------司法协助公示信息-->股权变更信息 start-----------------------
			JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
			List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
			Element sfxzgsGdbgxxDiv = sfxzgsxxDoc.getElementById("xzcfDiv");
			if (sfxzgsGdbgxxDiv != null) {
				Elements sfxzgsGdbgxxTables = sfxzgsGdbgxxDiv.select("table");
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
				}
				
				if (isDebug) {
					sfxzgsGdbgInfo.setHtml(sfxzgsGdbgxxDiv.toString());
				}
			}
			
			sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);
			
			sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);
			//-----------------司法协助公示信息-->股权变更信息 end-----------------------
		}
		
		gsxtFeedJson.setAicPubInfo(gsgsInfo);
		gsxtFeedJson.setEntPubInfo(qygsInfo);
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);
		gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);
		
		return gsxtFeedJson;
	
	}

	public AicFeedJson guangdongResultParserOne(Map<String, Object> resultHtmlMap, Boolean isDebug) {
		AicFeedJson gsxtFeedJson = new AicFeedJson();	
		
		//一、工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();
		
		//----------工商公示信息-->登记信息   start-------------
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();
		
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();
		
		Object gsgsxx_djxx_page_object = resultHtmlMap.get("gsgsxx_djxx_page");
		if (gsgsxx_djxx_page_object != null) {
			String gsgsxxDjxxHtml = (String)gsgsxx_djxx_page_object;
			Document gsgsxxDjxxDoc = Jsoup.parse(gsgsxxDjxxHtml);
			
			//1.基本信息
			Element gsgsxxDjjbDiv = gsgsxxDjxxDoc.getElementById("jibenxinxi");
			if (gsgsxxDjjbDiv != null) {
				Element gsgsxxDjjbTable = gsgsxxDjjbDiv.getElementById("baseinfo");
				if (gsgsxxDjjbTable == null) {
					Elements gsgsxxDjjbTables = gsgsxxDjjbDiv.select("table");
					if (gsgsxxDjjbTables != null && !gsgsxxDjjbTables.isEmpty()) {
						gsgsxxDjjbTable = gsgsxxDjjbTables.get(0);
					}
				}
				Elements gsgsxxDjjbThs = gsgsxxDjjbTable.select("th");
				Elements gsgsxxDjjbTds = gsgsxxDjjbTable.select("td");
				gsgsxxDjjbThs.remove(0);
				for (int i = 0; i < gsgsxxDjjbThs.size(); i++) {
					String gsgsxxDjjbTh = gsgsxxDjjbThs.get(i).text();
					String gsgsxxDjjbTd = gsgsxxDjjbTds.get(i).text();
					
					switch (gsgsxxDjjbTh) {
					case "注册号":
						String regNum = gsgsDjJbInfo.getNum();
						gsgsxxDjjbTd = (regNum != null && !"".equals(regNum))? gsgsxxDjjbTd + "/" + regNum : gsgsxxDjjbTd;
						gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
						break;
					case "统一社会信用代码":
						String num = gsgsDjJbInfo.getNum();
						gsgsxxDjjbTd = (num != null && !"".equals(num))? num + "/" + gsgsxxDjjbTd : gsgsxxDjjbTd;
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
				gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);
			}
			
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
							
							for (Element gdxxDetailTr : gdxxDetailTrs) {
								Elements gdxxDetail_tds = gdxxDetailTr.select("td");
								int tdSize = gdxxDetail_tds.size();
								if (tdSize == 3) {
									String stockholder = gdxxDetail_tds.get(0).text();
									String subAmount = gdxxDetail_tds.get(1).text();
									String paidAmount = gdxxDetail_tds.get(2).text();
									
									AicpubRegStohrStohrinvestInfo gsgsDjGdjczInfo = new AicpubRegStohrStohrinvestInfo();
									gsgsDjGdjczInfo.setStockholder(stockholder);
									gsgsDjGdjczInfo.setSubAmount(subAmount);
									gsgsDjGdjczInfo.setPaidAmount(paidAmount);
									gsgsDjGdGdjczInfos.add(gsgsDjGdjczInfo);
								} else if (tdSize == 6) {
									String sub_method = gdxxDetail_tds.get(0).text();
									String sub_amount = gdxxDetail_tds.get(1).text();
									String sub_czDate = gdxxDetail_tds.get(2).text();
									String paid_method = gdxxDetail_tds.get(3).text();
									String paid_amount = gdxxDetail_tds.get(4).text();
									String paid_czDate = gdxxDetail_tds.get(5).text();
									
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
									subAmountDetailList.add(subAmountDetail);
									paidAmountDetailList.add(paidAmountDetail);
									gsgsDjGdjczInfo.setSubAmountDetails(subAmountDetailList);
									gsgsDjGdjczInfo.setPaidAmountDetails(paidAmountDetailList);
									gsgsDjGdGdjczInfos.add(gsgsDjGdjczInfo);
								} else if (tdSize == 8) {
									String stockholder = gdxxDetail_tds.get(0).text();
									String sub_amount = gdxxDetail_tds.get(2).text();
									String sub_method = gdxxDetail_tds.get(3).text();
									String sub_czDate = gdxxDetail_tds.get(4).text();
									String paid_amount = gdxxDetail_tds.get(5).text();
									String paid_method = gdxxDetail_tds.get(6).text();
									String paid_czDate = gdxxDetail_tds.get(7).text();
									
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
									subAmountDetailList.add(subAmountDetail);
									paidAmountDetailList.add(paidAmountDetail);
									gsgsDjGdjczInfo.setSubAmountDetails(subAmountDetailList);
									gsgsDjGdjczInfo.setPaidAmountDetails(paidAmountDetailList);
									gsgsDjGdGdjczInfos.add(gsgsDjGdjczInfo);
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
									gsgsDjGdGdjczInfos.add(gsgsDjGdjczInfo);
								}
							}
							
						}
					}
				}
			}
			
			List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();
			Element gsgsxxDjgdTable = gsgsxxDjxxDoc.getElementById("touziren");
			if (gsgsxxDjgdTable == null) {
				gsgsxxDjgdTable = gsgsxxDjxxDoc.getElementById("Table1");
			}
			if (gsgsxxDjgdTable != null) {
				Elements gdxx_trs = gsgsxxDjgdTable.select("tr");
				int i = 0;
				for (Element gdxx_tr : gdxx_trs) {
					Elements gdxx_tds = gdxx_tr.select("td");
					
					int trSize = gdxx_tds.size();
					if (trSize == 4) {
						AicpubRegStohrInfo gsgsdjgdInfo = new AicpubRegStohrInfo();
						String gdType = gdxx_tds.get(0).text();
						String gdName = gdxx_tds.get(1).text();
						String idType = gdxx_tds.get(2).text();
						String idNum = gdxx_tds.get(3).text();
						
						gsgsdjgdInfo.setType(gdType);
						gsgsdjgdInfo.setName(gdName);
						gsgsdjgdInfo.setIdType(idType);
						gsgsdjgdInfo.setIdNum(idNum);
						gsgsDjGdInfos.add(gsgsdjgdInfo);
					}
					
					if (trSize == 5) {
						AicpubRegStohrInfo gsgsdjgdInfo = new AicpubRegStohrInfo();
						String gdType = gdxx_tds.get(0).text();
						String gdName = gdxx_tds.get(1).text();
						String idType = gdxx_tds.get(2).text();
						String idNum = gdxx_tds.get(3).text();
						String gdxq = gdxx_tds.get(4).text();
						
						gsgsdjgdInfo.setType(gdType);
						gsgsdjgdInfo.setName(gdName);
						gsgsdjgdInfo.setIdType(idType);
						gsgsdjgdInfo.setIdNum(idNum);
						if (!"".equals(gdxq)) {
							if (gsgsDjGdGdjczInfos.size() > i) {
								gsgsdjgdInfo.setStohrInvestInfo(gsgsDjGdGdjczInfos.get(i++));
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
					Elements gsgsxxDjbgxqTable = gsgsxxDjbgxqDoc.select("table");
					Element bgQxxTable = gsgsxxDjbgxqTable.get(1);
					Elements bgQxxTrs = bgQxxTable.select("tr");
					bgQxxTrs.remove(0);
					bgQxxTrs.remove(0);
					Element bgHxxTable = gsgsxxDjbgxqTable.get(2);
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
			
			/*变更信息*/
			List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<AicpubRegChangeInfo>();
			Element gsgsxxDjbgDiv = gsgsxxDjxxDoc.getElementById("biangeng");
			if (gsgsxxDjbgDiv == null) {
				gsgsxxDjbgDiv = gsgsxxDjxxDoc.getElementById("biangengxinxi");
			}
			if (gsgsxxDjbgDiv != null) {
				Elements gsgsxxDjbgTables = gsgsxxDjbgDiv.select("table");
				if (gsgsxxDjbgTables != null && !gsgsxxDjbgTables.isEmpty()) {
					Element gsgsxxDjbgTable = gsgsxxDjbgTables.get(0);
					Elements bgxx_trs = gsgsxxDjbgTable.select("tr");
					
					int j = 0;
					for (Element bgxx_tr : bgxx_trs) {
						Elements bgxx_tds = bgxx_tr.getElementsByTag("td");
						if (bgxx_tds.size() == 5) {
							String bgItem = bgxx_tds.get(1).text();
							String bgqContent = bgxx_tds.get(2).text();
							String bghContent = bgxx_tds.get(3).text();
							String bgDate = bgxx_tds.get(4).text();
							
							AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();
							gsgsDjBgInfo.setItem(bgItem);
							gsgsDjBgInfo.setPreContent(bgqContent);
							gsgsDjBgInfo.setPostContent(bghContent);
							gsgsDjBgInfo.setDateTime(bgDate);
							gsgsDjBgInfos.add(gsgsDjBgInfo);
						} else if (bgxx_tds.size() == 4) {
							String bgItem = bgxx_tds.get(0).text();
							String bgqContent = bgxx_tds.get(1).text();
							String bghContent = bgxx_tds.get(2).text();
							String bgDate = bgxx_tds.get(3).text();
							
							AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();
							gsgsDjBgInfo.setItem(bgItem);
							gsgsDjBgInfo.setPreContent(bgqContent);
							gsgsDjBgInfo.setPostContent(bghContent);
							gsgsDjBgInfo.setDateTime(bgDate);
							gsgsDjBgInfos.add(gsgsDjBgInfo);
						} else if(bgxx_tds.size() == 3) {
							String bgItem = bgxx_tds.get(0).text();
							String bgDate = bgxx_tds.get(2).text();
							
							AicpubRegChangeInfo gsgsDjBgInfo = new AicpubRegChangeInfo();
							gsgsDjBgInfo.setItem(bgItem);
							gsgsDjBgInfo.setDetail(bgDetailList.get(j++));
							gsgsDjBgInfo.setDateTime(bgDate);
							gsgsDjBgInfos.add(gsgsDjBgInfo);
						}
						
					}
					gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);
				}
			}
			
			gsgsInfo.setRegInfo(gsgsDjInfo);
			
		}
		
		//----------工商公示信息-->登记信息  end-------------
		
		//----------工商公示信息-->备案信息  start-------------
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
		
		List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
		Object gsgsxx_baxx_page_object = resultHtmlMap.get("gsgsxx_baxx_page");
		if (gsgsxx_baxx_page_object != null) {
			
			String gsgsBaxxHtml = (String)gsgsxx_baxx_page_object;
			Document gsgsBaxxDoc = Jsoup.parse(gsgsBaxxHtml);
			Element gsgsBaxxDiv = gsgsBaxxDoc.getElementById("beian");
			
			//备案信息-->主要人员信息
			if (gsgsBaxxDiv != null) {
				Element gsgsBaZyryDiv = gsgsBaxxDiv.getElementById("zyry");
				if (gsgsBaZyryDiv != null) {
					Elements gsgsBaZyryTables = gsgsBaZyryDiv.select("table");
					if (gsgsBaZyryTables != null && !gsgsBaZyryTables.isEmpty()) {
						Element gsgsBaZyryTable = gsgsBaZyryTables.get(0);
						if (gsgsBaZyryTable != null) {
							Elements gsgsBaZyryTrs = gsgsBaZyryTable.select("tr");
							for (Element zyryTr : gsgsBaZyryTrs) {
								Elements zyryTdElements = zyryTr.select("td");
								int zyryTdSize = zyryTdElements.size();
								if(zyryTdSize == 3 || zyryTdSize == 4) {
									String zyry_name = zyryTdElements.get(1).text();
									String zyry_position = zyryTdElements.get(2).text();
									if(!"".equals(zyry_name)) {
										AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
										gsgsBaZyryInfo.setName(zyry_name);
										gsgsBaZyryInfo.setPosition(zyry_position);
										gsgsBaZyryInfos.add(gsgsBaZyryInfo);
									}
								}
								
								if(zyryTdSize == 6) {
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
					}
				}
			}
			
			//备案信息-->分支机构信息
			if (gsgsBaxxDiv != null) {
				List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
				Element gsgsBaFzjgDiv = gsgsBaxxDiv.getElementById("branch");
				if (gsgsBaFzjgDiv != null) {
					Elements gsgsBaFzjgTables = gsgsBaFzjgDiv.select("table");
					if (gsgsBaFzjgTables != null && !gsgsBaFzjgTables.isEmpty()) {
						Element gsgsBaFzjgTable = gsgsBaFzjgTables.get(0);
						if (gsgsBaFzjgTable != null) {
							Elements gsgsBaFzjgTrs = gsgsBaFzjgTable.select("tr");
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
					}
				}
				gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);
			}
			
			//备案信息-->清算信息
/*			AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
			Elements gsgsBaQsTables = gsgsBaxxDiv.select("table");
			if (gsgsBaQsTables != null && !gsgsBaQsTables.isEmpty()) {
				Element gsgsBaQsTable = gsgsBaQsTables.get(2);
				Elements gsgsBaQsTrs = gsgsBaQsTable.select("tr");
				
				Elements gsgsBaQsTds = gsgsBaQsTrs.select("td");
				String leader = gsgsBaQsTds.get(0).text();
				String members = gsgsBaQsTds.get(1).text();
				List<String> memList = new ArrayList<String>();
				memList.add(members);
				gsgsBaQsInfo.setLeader(leader);
				gsgsBaQsInfo.setMembers(memList);
			}
			gsgsBaInfo.setClearInfo(gsgsBaQsInfo);
			*/
			
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
			
		}
		
		gsgsInfo.setArchiveInfo(gsgsBaInfo);
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
		Object gsgsxx_dcdydjxx_dcdydjxx_object = resultHtmlMap.get("gsgsxx_dcdyxx_page");
		if (gsgsxx_dcdydjxx_dcdydjxx_object != null) {
			String gsgsDcdydjHtml = (String)gsgsxx_dcdydjxx_dcdydjxx_object;
			Document gsgsDcdydjDoc = Jsoup.parse(gsgsDcdydjHtml);
			Element gsgsDcdydjDiv = gsgsDcdydjDoc.getElementById("dongchandiya");
			if (gsgsDcdydjDiv != null) {
				Elements gsgsDcdydjTables = gsgsDcdydjDiv.select("table");
				if (gsgsDcdydjTables != null && !gsgsDcdydjTables.isEmpty()) {
					Element gsgsDcdydjTable = gsgsDcdydjTables.get(0);
					Elements gsgsDcdydjTrs = gsgsDcdydjTable.select("tr");
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
							if (mortgDetails.size() > i) {
								gsgsDcdydjDcdydjInfo.setMortgDetail(mortgDetails.get(i++));
							}
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
							if (mortgDetails.size() > i) {
								gsgsDcdydjDcdydjInfo.setMortgDetail(mortgDetails.get(i++));
							}
							gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
						}
						
					}
					
					if(isDebug) {
						gsgsDcdydjInfo.setHtml(gsgsDcdydjTable.toString());
					}
				}
			}
		}
		
		gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);
		gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);
		
		//-----------------工商公示信息-->动产抵押登记信息 end-----------------------
		
		//-----------------工商公示信息-->股权出质登记信息 start-----------------------
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
		Object gsgsxx_gqczdjxx_gqczdjxx_object = resultHtmlMap.get("gsgsxx_gqczdjxx_page");
		if (gsgsxx_gqczdjxx_gqczdjxx_object != null) {
			String gsgsGqczdjHtml = (String)gsgsxx_gqczdjxx_gqczdjxx_object;
			Document gsgsGqczdjDoc = Jsoup.parse(gsgsGqczdjHtml);
			Element gsgsGqczdjDiv = gsgsGqczdjDoc.getElementById("guquanchuzhi");
			if (gsgsGqczdjDiv != null) {
				Elements gsgsGqczdjTables = gsgsGqczdjDiv.select("table");
				if (gsgsGqczdjTables != null && !gsgsGqczdjTables.isEmpty()) {
					Element gsgsGqczdjTable = gsgsGqczdjTables.get(0);
					Elements gsgsGqczdjTrs = gsgsGqczdjTable.select("tr");
					for (Element gsgsGqczdjTr : gsgsGqczdjTrs) {
						Elements gsgsGqczdjTds = gsgsGqczdjTr.select("td");
						
						
						if (gsgsGqczdjTds.size() == 10) {
							AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
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
							gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
							
						} else if (gsgsGqczdjTds.size() == 11) {
							AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();
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
							gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
						}
						
					}
					
					if(isDebug) {
						gsgsGqczdjInfo.setHtml(gsgsGqczdjTable.toString());
					}
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
		Object gsgsxx_xzcfxx_xzcfxx_object = resultHtmlMap.get("gsgsxx_xzcfxx_page");
		if (gsgsxx_xzcfxx_xzcfxx_object != null) {
			String gsgsXzcfHtml = (String)gsgsxx_xzcfxx_xzcfxx_object;
			Document gsgsXzcfDoc = Jsoup.parse(gsgsXzcfHtml);
			Element gsgsXzcfDiv = gsgsXzcfDoc.getElementById("xingzhengchufa");
			if (gsgsXzcfDiv != null) {
				Elements gsgsXzcfTables = gsgsXzcfDiv.select("table");
				if (gsgsXzcfTables != null && !gsgsXzcfTables.isEmpty()) {
					Element gsgsXzcfTable = gsgsXzcfTables.get(0);
					Elements gsgsXzcfTrs = gsgsXzcfTable.select("tr");
					
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
		}
		
		gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos);
		gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);
		
		//-----------------工商公示信息-->行政处罚信息 end-----------------------
		
		//-----------------工商公示信息-->经营异常信息 start-----------------------
		AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
		List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();
		Object gsgsxx_jyycxx_jyycxx_object = resultHtmlMap.get("gsgsxx_jyycxx_page");
		if (gsgsxx_jyycxx_jyycxx_object != null) {
			String gsgsJyycHtml = (String)gsgsxx_jyycxx_jyycxx_object;
			Document gsgsJyycDoc = Jsoup.parse(gsgsJyycHtml);
			Element gsgsJyycDiv = gsgsJyycDoc.getElementById("yichangminglu");
			if (gsgsJyycDiv != null) {
				Elements gsgsJyycTables = gsgsJyycDiv.select("table");
				if (gsgsJyycTables != null && !gsgsJyycTables.isEmpty()) {
					Element gsgsJyycTable = gsgsJyycTables.get(0);
					Elements gsgsJyycTrs = gsgsJyycTable.select("tr");
					for (Element gsgsJyycTr : gsgsJyycTrs) {
						Elements gsgsJyycTds = gsgsJyycTr.select("td");
						
						if (gsgsJyycTds.size() == 6) {
							AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();
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
							gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
						} else if (gsgsJyycTds.size() == 7) {
							AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();
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
							gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
						}
					}
					
					if(isDebug) {
						gsgsJyycInfo.setHtml(gsgsJyycTable.toString());
					}
				}
			}
		}
		
		gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);
		gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);
		//-----------------工商公示信息-->经营异常信息 end-----------------------
		
		//-----------------工商公示信息-->严重违法信息 start-----------------------
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
		Object gsgsxx_yzwfxx_yzwfxx_object = resultHtmlMap.get("gsgsxx_yzwfsxxx_page");
		if (gsgsxx_yzwfxx_yzwfxx_object != null) {
			String gsgsYzwfHtml = (String)gsgsxx_yzwfxx_yzwfxx_object;
			Document gsgsYzwfDoc = Jsoup.parse(gsgsYzwfHtml);
			Element gsgsYzwfDiv = gsgsYzwfDoc.getElementById("heimingdan");
			if (gsgsYzwfDiv != null) {
				Elements gsgsYzwfTables = gsgsYzwfDoc.select("table");
				if (gsgsYzwfTables != null && !gsgsYzwfTables.isEmpty()) {
					Element gsgsYzwfTable = gsgsYzwfTables.get(0);
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
			}
			
		}
		gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);
		gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);
		//-----------------工商公示信息-->严重违法信息 end-----------------------
		
		//-----------------工商公示信息-->抽查检查信息 start-----------------------
		AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
		Object gsgsxx_ccjcxx_ccjcxx_object = resultHtmlMap.get("gsgsxx_ccjcxx_page");
		if (gsgsxx_ccjcxx_ccjcxx_object != null) {
			String gsgsCcjcHtml = (String)gsgsxx_ccjcxx_ccjcxx_object;
			Document gsgsCcjcDoc = Jsoup.parse(gsgsCcjcHtml);
			Element gsgsCcjcDiv = gsgsCcjcDoc.getElementById("chouchajiancha");
			if (gsgsCcjcDiv != null) {
				Elements gsgsCcjcTables = gsgsCcjcDiv.select("table");
				if (gsgsCcjcTables != null && !gsgsCcjcTables.isEmpty()) {
					Element gsgsCcjcTable = gsgsCcjcTables.get(0);
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
				
				Object qygsxx_qynb_info = qynbDetailMap.get("qygsxx_qynb_info");
				if (qygsxx_qynb_info != null) {
					String qygsxx_qynb_info_page = (String)qygsxx_qynb_info;
					Document qygsxxQynbDoc = Jsoup.parse(qygsxx_qynb_info_page);
					Elements qygsxxQynbTables = qygsxxQynbDoc.select("table");
					
					//企业基本信息
					if (qygsxxQynbTables != null && qygsxxQynbTables.size() > 1) {
						Element qygsxxQynbJbxxTable = qygsxxQynbTables.get(1);
						if (qygsxxQynbJbxxTable != null) {
							EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
							Elements qygsxxQynbJbxxTrs = qygsxxQynbJbxxTable.select("tr");
							Elements qygsxxQynbJbxxThs = qygsxxQynbJbxxTrs.select("th");
							Elements qygsxxQynbJbxxTds = qygsxxQynbJbxxTrs.select("td");
							if (qygsxxQynbJbxxThs != null && qygsxxQynbJbxxTds != null) {
								int thSize = qygsxxQynbJbxxThs.size();
								int tdSize = qygsxxQynbJbxxTds.size();
								if (thSize == tdSize) {
									for (int i = 0; i < thSize; i++) {
										String thContent = qygsxxQynbJbxxThs.get(i).text().replace(" ", "");
										String tdContent = qygsxxQynbJbxxTds.get(i).text().replace(" ", "");
										
										switch (thContent) {
										case "注册号/统一社会信用代码":
											qygsQynbJbInfo.setNum(tdContent);
											break;
										case "统一社会信用代码":
											qygsQynbJbInfo.setNum(tdContent);
											break;
										case "注册号":
											qygsQynbJbInfo.setNum(tdContent);
											break;
										case "企业名称":
											qygsQynbJbInfo.setName(tdContent);
											break;
										case "企业联系电话":
											qygsQynbJbInfo.setTel(tdContent);
											break;
										case "邮政编码":
											qygsQynbJbInfo.setZipCode(tdContent);
											break;
										case "企业通信地址":
											qygsQynbJbInfo.setAddress(tdContent);
											break;
										case "电子邮箱":
											qygsQynbJbInfo.setEmail(tdContent);
											break;
										case "有限责任公司本年度是否发生股东股权转让":
											qygsQynbJbInfo.setIsStohrEquTransferred(tdContent);
											break;
										case "企业经营状态":
											qygsQynbJbInfo.setOperatingStatus(tdContent);
											break;
										case "是否有网站或网店":
											qygsQynbJbInfo.setHasWebsiteOrStore(tdContent);
											break;
										case "企业是否有投资信息或购买其他公司股权":
											qygsQynbJbInfo.setHasInvestInfoOrPurchOtherCorpEqu(tdContent);
											break;
										case "从业人数":
											qygsQynbJbInfo.setEmpNum(tdContent);
											break;
										default:
											break;
										}
									}
										
									qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);
								}
							}
						}
					}
					
					//网站或网店信息
					List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
					Element qygsxxQynbWzhwdxxTable = qygsxxQynbDoc.getElementById("t02");
					if (qygsxxQynbWzhwdxxTable != null) {
						Elements wzwdxxTrs = qygsxxQynbWzhwdxxTable.select("tr");
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
					qygsQynbInfo.setWebsiteInfos(qygsQynbWzhwdInfos);
					
					//股东及出资信息
					List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();
					Element qygsxxQynbGdjczxxTable = qygsxxQynbDoc.getElementById("t03");
					if (qygsxxQynbGdjczxxTable != null) {
						Elements gdczxxTrs = qygsxxQynbGdjczxxTable.select("tr");
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
					qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);
					
					//对外投资信息   qygsxx_qynb_info_dwtgbzdbxx_page
					List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
					Element qygsxxQynbDwtzxxTable = qygsxxQynbDoc.getElementById("t04");
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
					qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzInfos);
					
					//企业资产状况信息
					Element qygsxxQynbQyzczkTable = qygsxxQynbDoc.getElementById("t05");
					if (qygsxxQynbQyzczkTable != null) {
						Elements qygsxxQynbQyzczkTrs = qygsxxQynbQyzczkTable.select("tr");
						EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
						for (Element qygsxxQynbQyzczkTr : qygsxxQynbQyzczkTrs) {
							Elements qygsxxQynbQyzczkThs = qygsxxQynbQyzczkTr.select("th");
							Elements qygsxxQynbQyzczkTds = qygsxxQynbQyzczkTr.select("td");
							if (qygsxxQynbQyzczkThs != null && qygsxxQynbQyzczkTds != null) {
								int thSize = qygsxxQynbQyzczkThs.size();
								int tdSize = qygsxxQynbQyzczkTds.size();
								if (thSize == tdSize) {
									for (int i = 0; i < thSize; i++) {
										String thContent = qygsxxQynbQyzczkThs.get(i).text().replace(" ", "");
										String tdContent = qygsxxQynbQyzczkTds.get(i).text().replace(" ", "");
										
										switch (thContent) {
										case "资产总额":
											qygsQynbQyzczkInfo.setAssetAmount(tdContent);
											break;
										case "所有者权益合计":
											qygsQynbQyzczkInfo.setTotalEquity(tdContent);
											break;
										case "销售总额":
											qygsQynbQyzczkInfo.setSalesAmount(tdContent);
											break;
										case "利润总额":
											qygsQynbQyzczkInfo.setProfitAmount(tdContent);
											break;
										case "营业总收入中主营业务收入":
											qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(tdContent);
											break;
										case "其中：主营业务收入":
											qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(tdContent);
											break;
										case "主营业务收入":
											qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(tdContent);
											break;
										case "净利润":
											qygsQynbQyzczkInfo.setNetProfit(tdContent);
											break;
										case "纳税总额":
											qygsQynbQyzczkInfo.setTaxesAmount(tdContent);
											break;
										case "负债总额":
											qygsQynbQyzczkInfo.setLiabilityAmount(tdContent);
											break;
										default:
											break;
										}
									}
								}
							}
						}
						
						qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);
					}
					
					//修改记录
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
				}
					
				qygsQynbInfos.add(qygsQynbInfo);
			}
			
		}
		
		qygsInfo.setAnnReports(qygsQynbInfos);
		//-----------------企业公示信息-->企业年报 end-----------------------
		
		//-----------------企业公示信息-->股东及出资信息 start-----------------------
		EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
		Object qygsxx_gdjczxx_object = resultHtmlMap.get("qygsxx_gdjczxx_page");
		if (qygsxx_gdjczxx_object != null) {
			String qygsxx_gdjczxx_page = (String) qygsxx_gdjczxx_object;
			Document qygsxxGdjczxxlDoc = Jsoup.parse(qygsxx_gdjczxx_page);
			//股东及出资信息-->股东及出资信息
			List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
			
			Element qygsxxGdjczDiv = qygsxxGdjczxxlDoc.getElementById("sifapanding");
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
		Object qygsxx_gqbgxx_object = resultHtmlMap.get("qygsxx_gqbgxx_page");
		if (qygsxx_gqbgxx_object != null) {
			String qygsxx_gqbgxx_page = (String) qygsxx_gqbgxx_object;
			Document qygsxxGqbgxxDoc = Jsoup.parse(qygsxx_gqbgxx_page);
			Elements qygsxxGqbgxxTables = qygsxxGqbgxxDoc.select("table");
			if (qygsxxGqbgxxTables != null && !qygsxxGqbgxxTables.isEmpty()) {
				Element qygsxxGqbgxxTable = qygsxxGqbgxxTables.get(0);
				Elements qygsxxGqbgxxTrs = qygsxxGqbgxxTable.select("tr");
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
		Object qygsxx_xzxkxx_object = resultHtmlMap.get("qygsxx_xzxkxx_page");
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
		Object qygsxx_zscqczdjxx_object = resultHtmlMap.get("qygsxx_zscqczdjxx_page");
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
		Object qygsxx_xzcfxx_object = resultHtmlMap.get("qygsxx_xzcfxx_page");
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
		Object qtbmgsxx_xzxkxx_object = resultHtmlMap.get("qtbmgsxx_page");
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

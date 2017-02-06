package com.crawler.gsxt.htmlparser;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.crawler.gsxt.domain.json.AicFeedJson;
import com.crawler.gsxt.domain.json.AicpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.AicpubAAdmpunishInfo.PunishDetail;
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
import com.crawler.htmlparser.AbstractParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@Component
public class GsxtShandongParser {

	public AicFeedJson shandongResultParser(String resultHtmls, Boolean isDebug) {
		//解析result
		AicFeedJson gsxtFeedJson = new AicFeedJson();
		//一、工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();
		Gson gson = new Gson();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		
		Object gsgsxx_object = resultHtmlMap.get("qyxx_gsgsxx_djxx");
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
				Element invDivElement = djxxDiv.getElementById("touziren");
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
								//gsgsdjgdInfo.setName(gdName);
								gsgsdjgdInfo.setType(gdName);
							}
							if (trSize > 1) {
								String idType = gdxx_tds.get(1).text();
								//gsgsdjgdInfo.setIdType(idType);
								gsgsdjgdInfo.setName(idType);
							}
							if (trSize > 2) {
								String idNum = gdxx_tds.get(2).text();
								//gsgsdjgdInfo.setIdNum(idNum);
								gsgsdjgdInfo.setIdType(idNum);
							}
							if (trSize > 3) {
								String gdType = gdxx_tds.get(3).text();
								//gsgsdjgdInfo.setType(gdType);
								gsgsdjgdInfo.setIdNum(gdType);
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
				//Element bgxx_table = djxxDiv.getElementById("altTab");//.select("table")
				Elements select = djxxDiv.select("table");				
				Element bgxx_table =djxxDiv.select("table").get(select.size()-2);
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
			Object gsgsxx_baxx_zyryxx = resultHtmlMap.get("qyxx_gsgsxx_baxx_zyryxx");
			if(gsgsxx_baxx_zyryxx!=null){						
			List<Map<String, String>> listmy = gson.fromJson((String) gsgsxx_baxx_zyryxx, new TypeToken<List<Map<String, String>>>(){}.getType());
			for (Map<String, String> map : listmy) {
				AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
				gsgsBaZyryInfo.setName(map.get("name"));
				gsgsBaZyryInfo.setPosition(map.get("position"));
				gsgsBaZyryInfos.add(gsgsBaZyryInfo);
			}			
			gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);
			}
			//备案信息-->分支机构信息
			List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();
			Object qyxx_gsgsxx_baxx_fzjgxx = resultHtmlMap.get("qyxx_gsgsxx_baxx_fzjgxx");
			if(qyxx_gsgsxx_baxx_fzjgxx!=null){						
				List<Map<String, String>> listfzjgxx = gson.fromJson((String) qyxx_gsgsxx_baxx_fzjgxx, new TypeToken<List<Map<String, String>>>(){}.getType());
				for (Map<String, String> map : listfzjgxx) {
					AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
					gsgsBaFzjgInfo.setNum(map.get("regno"));
					gsgsBaFzjgInfo.setName(map.get("brname"));
					gsgsBaFzjgInfo.setRegAuthority(map.get("regorg"));

					gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
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
		    Object qyxx_gsgsxx_xzcfxx = resultHtmlMap.get("qyxx_gsgsxx_xzcfxx");			
			if(qyxx_gsgsxx_xzcfxx!=null){						
			List<Map<String, Object>> listmy = gson.fromJson((String) qyxx_gsgsxx_xzcfxx, new TypeToken<List<Map<String, Object>>>(){}.getType());
			for (Map<String, Object> object : listmy) {				
				AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();
				AicpubAAdmpunishInfo.PunishDetail punishDetail = gsgsXzcfXzcfInfo.new PunishDetail();
			
				gsgsXzcfXzcfInfo.setPunishRepNum((String)object.get("pendecno"));//行政处罚决定书文号
				gsgsXzcfXzcfInfo.setIllegalActType((String)object.get("illegacttype"));//违法行为类型
				
				gsgsXzcfXzcfInfo.setPunishContent((String)object.get("pentype")+"罚款金额:"+(Double)object.get("penam")+"万元");//行政处罚内容				
				gsgsXzcfXzcfInfo.setDeciAuthority((String)object.get("penauth"));//作出行政处罚决定机关名称
				Map<String, Double> DeciDateTime=(Map<String, Double>) object.get("pendecissdate");
				String dateStr="";
				if(null==DeciDateTime){
					gsgsXzcfXzcfInfo.setDeciDateTime("");//作出行政处罚决定日期
				}else{
					Double time =DeciDateTime.get("time");
					Date date = new Date(BigDecimal.valueOf(time).longValue());
					 dateStr = new SimpleDateFormat("yyyy年MM月dd日").format(date);
					gsgsXzcfXzcfInfo.setDeciDateTime(dateStr);//作出行政处罚决定日期	
				}					
				
				String remark=(String)object.get("remark");
				Document xzcfjdsDoc = Jsoup.parse(remark);
				String name="";
				String legalReprName="";
				Element xzcfjdsDiv = xzcfjdsDoc.getElementsByClass("WordSection1").get(0);
				if(xzcfjdsDiv!=null){
					Elements djxx_tables = xzcfjdsDiv.select("table");
					if (djxx_tables != null && !djxx_tables.isEmpty()) {
						Element jbxx_table = djxx_tables.get(0);
						Elements gsgsxxDjjbtrs = jbxx_table.select("tr");										
						for (int i = 0; i < gsgsxxDjjbtrs.size(); i++) {						
							Elements gsgsxxDjjbTds = gsgsxxDjjbtrs.get(i).select("td");
							  for(int j=0;j<gsgsxxDjjbTds.size();j++){
								  String gsgsxxDjjbTd = gsgsxxDjjbTds.get(j).select("span").get(0).text();
								  switch (gsgsxxDjjbTd) {
									case "名称":
										name=gsgsxxDjjbTds.get(j+1).select("span").get(0).text();
										break;
									case "法定代表人":
										legalReprName=gsgsxxDjjbTds.get(j+1).select("span").get(0).text();
										break;
									default:
										break;
								  }
							  }
														
						}
					}
				}

				punishDetail.punishRepNum=(String)object.get("pendecno");//行政处罚决定书文号
				punishDetail.name=name;//名称
				punishDetail.regNum=(String)object.get("regno");//注册号regno
				punishDetail.legalReprName=legalReprName;//法定代表人（负责人）姓名
				punishDetail.illegalActType=(String)object.get("illegacttype");//违法行为类型
				punishDetail.punishContent=(String)object.get("pentype")+"罚款金额:"+(Double)object.get("penam")+"万元";//行政处罚内容
				punishDetail.deciAuthority=(String)object.get("penauth");//作出行政处罚决定机关名称
				punishDetail.deciDateTime=dateStr;//作出行政处罚决定日期
				punishDetail.punishRep=(String)object.get("remark");//行政处罚决定书
				gsgsXzcfXzcfInfo.setPunishDetail(punishDetail);
				gsgsXzcfXzcfInfos.add(gsgsXzcfXzcfInfo);
			}

			}
			
			gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos);
			gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);
			
			//-----------------工商公示信息-->行政处罚信息 end-----------------------
			
			//-----------------工商公示信息-->经营异常信息 start-----------------------
			AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
			List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();
			Object gsgsxx_baxx_jyycxx = resultHtmlMap.get("qyxx_gsgsxx_jyjcxx");
			
			if(gsgsxx_baxx_jyycxx!=null){						
			List<Map<String, Object>> listmy = gson.fromJson((String) gsgsxx_baxx_jyycxx, new TypeToken<List<Map<String, Object>>>(){}.getType());

			for (Map<String, Object> object : listmy) {				
				AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();
				gsgsJyycJyycInfo.setIncludeCause((String)object.get("specause"));//列入经营异常名录原因
				
				@SuppressWarnings("unchecked")
				Map<String, Double> object1 = (Map<String, Double>) object.get("abntime");
				String IncludeDateTime="";
				if(null==object1){
					IncludeDateTime="";
				}else{
					Double time =object1.get("time");
					Date date = new Date(BigDecimal.valueOf(time).longValue());
					String dateStr = new SimpleDateFormat("yyyy年MM月dd日").format(date);
					gsgsJyycJyycInfo.setIncludeDateTime(dateStr);//列入日期	
				}
				
				gsgsJyycJyycInfo.setIncludeAuthority((String)object.get("decorg"));//作出决定机关(列入)
				gsgsJyycJyycInfo.setRemoveCause((String)object.get("remexcpres"));	//移出经营异常名录原因		
				
				Map<String, Double> RemoveDateTime=(Map<String, Double>) object.get("remdate");
				if(null==RemoveDateTime){
					gsgsJyycJyycInfo.setRemoveDateTime("");	//移出日期	
				}else{
					Double time =RemoveDateTime.get("time");
					Date date = new Date(BigDecimal.valueOf(time).longValue());
					String dateStr = new SimpleDateFormat("yyyy年MM月dd日").format(date);
					gsgsJyycJyycInfo.setRemoveDateTime(dateStr);//列入日期	
				}
					
				
				gsgsJyycJyycInfo.setRemoveAuthority((String)object.get("remexcpres"));	//作出决定机关(列入和移出 做出决定机关)						
				gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
			}
			
				if(isDebug) {
					gsgsJyycInfo.setHtml(gsgsxx_baxx_jyycxx.toString());
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
										case "有限责任公司本年度是否发生股东（发起人）股权转让":
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
										case "企业是否有对外投资设立企业信息":
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
										case "销售总额":
											qygsQynbQyzczkInfo.setSalesAmount(qyjbxxTd);
											break;
										case "利润总额":
											qygsQynbQyzczkInfo.setProfitAmount(qyjbxxTd);
											break;
										case "销售总额中主营业务收入":
											qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(qyjbxxTd);
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
							} else if ("股东及出资信息".equals(itemTitle)) {
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
								qygsQynbInfo.setExtGuaranteeInfos(qygsQynbDwtgbzdbInfos);
							} else if ("股权变更信息".equals(itemTitle)) {
								//企业年报信息--> 股权变更信息
								List<EntpubAnnreportEquchangeInfo> qygsQynbGqbgInfos = new ArrayList<EntpubAnnreportEquchangeInfo>();
								Elements gqbgxxTrs = qygsnbxxTable.select("tr");
								for (Element gqbgxxTr : gqbgxxTrs) {
								
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
								qygsQynbInfo.setEquChangeInfos(qygsQynbGqbgInfos);
								
							} else if ("修改记录".equals(itemTitle)) {
								//企业年报信息--> 修改记录
								List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = new ArrayList<EntpubAnnreportModifyInfo>();
								Elements xgjlxxTrs = qygsnbxxTable.select("tr");
								for (Element xgjlxxTr : xgjlxxTrs) {
								
										Elements xgjlxxTds = xgjlxxTr.select("td");
										if (xgjlxxTds.size() ==5) {
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
		Object qtbmgsxx_object = resultHtmlMap.get("qtbmgsxx");
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
		// -----------------司法协助公示信息-->股权冻结信息 start-----------------------
				JudasspubInfo sfxzgsInfo = new JudasspubInfo();
				JudasspubEqufreezeInfo sfxzgsGqdjInfo = new JudasspubEqufreezeInfo();
				String sfxzgsxxHtml = (String) resultHtmlMap.get("sfxzgsxx_page");
				if(!"".equals(sfxzgsxxHtml)&&sfxzgsxxHtml!=null){				
				List<String> encrpripidlist = new AbstractParser() {}.getSubStringByRegex(sfxzgsxxHtml, "var encrpripid = '.*'");
				String encrpripid = encrpripidlist.get(0);
				String encrpripidmy = encrpripid.substring(18, encrpripid.length() - 1);
				// System.out.println(encrpripidmy);
				List<String> enttypelist = new AbstractParser() {}.getSubStringByRegex(sfxzgsxxHtml, "var enttype='.*'");
				String enttype = enttypelist.get(0);
				String enttypemy = enttype.substring(13, enttype.length() - 1);
				// System.out.println(enttypemy);

				List<String> sfxzxxlist = new AbstractParser() {}.getSubStringByRegex(sfxzgsxxHtml, "var gqxxliststr ='\\[.*\\]");
				String sfxzgsxxzzfc = sfxzxxlist.get(0).substring(19,sfxzxxlist.get(0).length() - 1);
				if(!"".equals(sfxzgsxxzzfc)&&null!=sfxzgsxxzzfc)	{	
				// System.out.println(sfxzgsxxzzfc);
				String[] sfxzgsxxzzfcsplil = sfxzgsxxzzfc.split(",");				
				List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
				String xfgqdjxxurl = "http://218.57.139.24/pub/sfgsgqxxdetail/"
						+ encrpripidmy + "/" + enttypemy;
				JudasspubEEqufreezeInfo SfxzgsGqdjGqdjInfo = null;
				String num="";
				// System.out.println(xfgqdjxxurl);
				for (int m = 0; m < sfxzgsxxzzfcsplil.length; m++) {
					String strname = sfxzgsxxzzfcsplil[m];
					String strname1[] = strname.split(":");
					String myname = strname1[0];
					String strna = strname1[1];
					String namesss = "";
					if (strna != null && "".equals(strna)) {
						namesss = strna.substring(1, strna.length() - 1);
					}
					// System.out.println(myname);
					// System.out.println(namesss);
					// 被执行人
					if ("\"inv\"".equals(myname)) {
						namesss = strna.substring(1, strna.length() - 1);
						SfxzgsGqdjGqdjInfo.setExecutedPerson(namesss);

					}
					if ("\"froam\"".equals(myname)) {
						// 股权数额
						SfxzgsGqdjGqdjInfo = new JudasspubEEqufreezeInfo();
						SfxzgsGqdjGqdjInfo.setEquAmount(strna + "万元");
					}
					if ("\"froauth\"".equals(myname)) {
						// 执行法院
						SfxzgsGqdjGqdjInfo.setExeCourt(namesss);
						;
					}
					if ("\"froauth\"".equals(myname)) {
						// 协助公示通知书文号
						SfxzgsGqdjGqdjInfo.setAssistPubNoticeNum(namesss);
					}
					if ("\"frozstate\"".equals(myname)) {
						// 状态
						namesss = strna.substring(1, strna.length() - 1);
						 num = namesss;				

						if ("1".equals(num)) {
							SfxzgsGqdjGqdjInfo.setStatus("冻结");
						}
						if ("2".equals(num)) {
							SfxzgsGqdjGqdjInfo.setStatus("解除冻结");
						}
						if ("3".equals(num)) {
							SfxzgsGqdjGqdjInfo.setStatus("失效");
						}

					}
					if ("\"pid\"".equals(myname)) {
						// 协助公示通知书文号
						namesss = strna.substring(1, strna.length() - 1);
						xfgqdjxxurl = xfgqdjxxurl + "/" + namesss;
						xfgqdjxxurl = xfgqdjxxurl + "/" + num;			
						SfxzgsGqdjGqdjInfo.setDetail(xfgqdjxxurl);
						sfxzgsGqdjGqdjInfos.add(SfxzgsGqdjGqdjInfo);
					}

				}

				sfxzgsGqdjInfo.setEquFreezeInfos(sfxzgsGqdjGqdjInfos);
				   if(isDebug){
					   sfxzgsGqdjInfo.setHtml(sfxzgsxxHtml);
				   }
				   	   		   
				}
				}
				sfxzgsInfo.setEquFreezeInfo(sfxzgsGqdjInfo);
				// -----------------司法协助公示信息-->股权冻结信息 end-----------------------
				
				//-----------------司法协助公示信息-->股权变更信息 start-----------------------
				
				Document sfxzgsxxDoc = Jsoup.parse(sfxzgsxxHtml);
				
				//-----------------司法协助公示信息-->股权冻结信息 start-----------------------
			
				List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();		
				Element sfxzgsDiv = sfxzgsxxDoc.getElementById("EquityFreeze");
				if(null!=sfxzgsDiv){
					
				
				Element sfxzgsxxDiv = sfxzgsDiv.getElementById("EquityFreezeDiv");
				Elements sfxzgsxxTrs = sfxzgsxxDiv.select("tr");
				JudasspubStohrchangeInfo sfxzgsGdbgInfo = new JudasspubStohrchangeInfo();
				List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = new ArrayList<JudasspubSStohrchangeInfo>();
				Element sfxzgsGdbgxxDiv = sfxzgsxxDoc.getElementById("xzcfDiv");
				Elements sfxzgsGdbgxxTrs = sfxzgsGdbgxxDiv.select("tr");
				for (int j = 2; j < sfxzgsGdbgxxTrs.size(); j++) {
					Elements sfxzgsxxTds = sfxzgsxxTrs.get(j).select("td");
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
				
				if (isDebug) {
					sfxzgsGdbgInfo.setHtml(sfxzgsGdbgxxDiv.toString());
				}
				
				sfxzgsGdbgInfo.setStohrChangeInfos(sfxzgsGdbgGdbgInfos);
				
				sfxzgsInfo.setStohrChangeInfo(sfxzgsGdbgInfo);
				}
			//-----------------司法协助公示信息-->股权变更信息 end-----------------------
		
		
		gsxtFeedJson.setAicPubInfo(gsgsInfo);
		gsxtFeedJson.setEntPubInfo(qygsInfo);
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);
		gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);
		
		return gsxtFeedJson;
	}
	
	public static String dateper(String date){ 
		if(null==date&&"".equals(date)){
			return "";
		}else {		
		Gson gson = new GsonBuilder().create();		
		Type mapType = new TypeToken<Map<String,Long>>(){}.getType();
		Map<String,Long> map = gson.fromJson(date, mapType);
		Long time = map.get("time");
		Date date1 = new Date(time);
		String dateStr = new SimpleDateFormat("yyyy年MM月dd日").format(date1);
		return dateStr;
		}
	}
	
//	public static void main(String[] args) {
//		String str =  "{"
//			       +     "\"date\": 14,"
//			       +     "\"day\": 2,"
//			       +     "\"hours\": 0,"
//			       +     "\"minutes\": 0,"
//			       +     "\"month\": 6,"
//			       +     "\"seconds\": 0,"
//			       +     "\"time\": 1436803200000,"
//			       +	 "\"timezoneOffset\": -480,"
//			       +     "\"year\": 115"
//			       + "}";
//		 GsxtShandongParser.dateper(str);
//	}
}

package com.crawler.gsxt.htmlparser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crawler.gsxt.domain.json.AicFeedJson;
import com.crawler.gsxt.domain.json.AicpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.AicpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveBranchInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveInfo;
import com.crawler.gsxt.domain.json.AicpubArchivePrimemberInfo;
import com.crawler.gsxt.domain.json.AicpubChatMortgInfo;
import com.crawler.gsxt.domain.json.AicpubCheckInfo;
import com.crawler.gsxt.domain.json.AicpubEqumortgregInfo;
import com.crawler.gsxt.domain.json.AicpubInfo;
import com.crawler.gsxt.domain.json.AicpubOOperanomaInfo;
import com.crawler.gsxt.domain.json.AicpubOperanomaInfo;
import com.crawler.gsxt.domain.json.AicpubRegBaseInfo;
import com.crawler.gsxt.domain.json.AicpubRegChangeInfo;
import com.crawler.gsxt.domain.json.AicpubRegInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAssetInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportBaseInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportExtinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportModifyInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportWebsiteInfo;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo;
import com.crawler.gsxt.domain.json.AicpubAAdmpunishInfo.PunishDetail;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo.Detail;
import com.crawler.gsxt.domain.json.AicpubSerillegalInfo;
import com.crawler.gsxt.domain.json.EntpubAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportInfo;
import com.crawler.gsxt.domain.json.EntpubEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubInfo;
import com.crawler.gsxt.domain.json.EntpubIntellectualproregInfo;
import com.crawler.gsxt.domain.json.EntpubStohrinvestInfo;
import com.crawler.gsxt.domain.json.JudasspubEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubInfo;
import com.crawler.gsxt.domain.json.JudasspubStohrchangeInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtTianjinParser extends AbstractGsxtParser {

	private static final Logger log = LoggerFactory
			.getLogger(GsxtTianjinParser.class);

	public AicFeedJson tianjinParser(String html, boolean isDebug) {
		AicFeedJson aicFeedJson=new AicFeedJson();
		String statusCodeDef="{"+"\n"+"  "+"\"statusCodeDef\": \"0\""+"\n"+"}";
		if(html.trim().equals(statusCodeDef) || html.isEmpty()){
			aicFeedJson=null;
		}else{
			Gson gson = new GsonBuilder().create();
			Type htmlMapType = new TypeToken<Map<String,Object>>(){}.getType();
			Map<String,Object> resultHtmlMap = gson.fromJson(html, htmlMapType);
			
			
			//********************工商公示信息 开始********************
			AicpubInfo aicpubInfo=new AicpubInfo();
			
			//登记信息********************开始
			AicpubRegInfo aicpubRegInfo=new AicpubRegInfo();
			//基本信息
			AicpubRegBaseInfo aicpubRegBaseInfo=new AicpubRegBaseInfo();
			//股东信息
			List<AicpubRegStohrInfo> stohrInfos=new ArrayList<AicpubRegStohrInfo>();
			//变更信息（包括变更信息详细）
			List<AicpubRegChangeInfo> changeInfos=new ArrayList<AicpubRegChangeInfo>();
			
			if (resultHtmlMap.get("gsgsxx_djxx_jbxx")!=null) {	
			Elements ele_resultsTable = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_djxx_jbxx").toString()).select("table.result-table");
			
			if(ele_resultsTable==null || ele_resultsTable.isEmpty()) {
				aicpubRegInfo=null;
			}else{
				//基本信息
				Element eleDiv = ele_resultsTable.get(0);
				if (ele_resultsTable==null || ele_resultsTable.isEmpty()) {
					aicpubRegBaseInfo=null;
				}else{
					Element ele_resultJbxx=ele_resultsTable.get(0);//获取第一个table（基本信息）
					Elements tr_resultsJbxx = getElements(ele_resultJbxx, "tr");
					Elements gsgsxxDjjbThs = ele_resultJbxx.select("td");
					gsgsxxDjjbThs.remove(0);
					if (tr_resultsJbxx==null || tr_resultsJbxx.isEmpty()) {
						aicpubRegBaseInfo=null;
					}else{
						for (int i = 0; i < gsgsxxDjjbThs.size(); i++) {
							String gsgsxxDjjbTh = gsgsxxDjjbThs.get(i).text();
							if(gsgsxxDjjbThs.get(i).nextElementSibling()!=null){
								String gsgsxxDjjbTd = gsgsxxDjjbThs.get(i).nextElementSibling().text();
								switch (gsgsxxDjjbTh) {
								case "注册号":
									aicpubRegBaseInfo.setNum(gsgsxxDjjbTd);
									break;
								case "统一社会信用代码":
									aicpubRegBaseInfo.setCreditNum(gsgsxxDjjbTd);
									break;
								case "注册号/统一社会信用代码":
									aicpubRegBaseInfo.setNum(gsgsxxDjjbTd);
									break;
								case "营业执照注册号 统一社会信用代码":
									aicpubRegBaseInfo.setNum(gsgsxxDjjbTd);
									break;
								case "注册号/ 统一社会信用代码":
								    aicpubRegBaseInfo.setNum(gsgsxxDjjbTd);
								    break;
								case "名称":
									aicpubRegBaseInfo.setName(gsgsxxDjjbTd);
									break;
								case "类型":
									aicpubRegBaseInfo.setType(gsgsxxDjjbTd);
									break;
								case "法定代表人":
									aicpubRegBaseInfo.setLegalRepr(gsgsxxDjjbTd);
									break;
								case "负责人":
									aicpubRegBaseInfo.setLegalRepr(gsgsxxDjjbTd);
									break;
								case "注册资本":
									aicpubRegBaseInfo.setRegCapital(gsgsxxDjjbTd);
									break;
								case "成立日期":
									aicpubRegBaseInfo.setRegDateTime(gsgsxxDjjbTd);
									break;
								case "住所":
									aicpubRegBaseInfo.setAddress(gsgsxxDjjbTd);
									break;
								case "经营场所":
									aicpubRegBaseInfo.setAddress(gsgsxxDjjbTd);
									break;
								case "营业场所":
									aicpubRegBaseInfo.setAddress(gsgsxxDjjbTd);
									break;
								case "营业期限自":
									aicpubRegBaseInfo.setStartDateTime(gsgsxxDjjbTd);
									break;
								case "经营期限自":
									aicpubRegBaseInfo.setStartDateTime(gsgsxxDjjbTd);
									break;
								case "营业期限至":
									aicpubRegBaseInfo.setEndDateTime(gsgsxxDjjbTd);
									break;
								case "经营期限至":
									aicpubRegBaseInfo.setEndDateTime(gsgsxxDjjbTd);
									break;
								case "经营范围":
									aicpubRegBaseInfo.setBusinessScope(gsgsxxDjjbTd);
									break;
								case "登记机关":
									aicpubRegBaseInfo.setRegAuthority(gsgsxxDjjbTd);
									break;
								case "核准日期":
									aicpubRegBaseInfo.setApprovalDateTime(gsgsxxDjjbTd);
									break;
								case "登记状态":
									aicpubRegBaseInfo.setRegStatus(gsgsxxDjjbTd);
									break;
								case "吊销日期":
									aicpubRegBaseInfo.setRevokeDate(gsgsxxDjjbTd);
									break;
								default:
									break;
								}
							}
							
						}
					}
				}
				
				aicpubRegInfo.setBaseInfo(aicpubRegBaseInfo);//AicpubRegBaseInfo
				
				Elements ele_touzirenTable =Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_djxx_jbxx").toString()).select("table#touziren");
				//股东信息
				if(ele_touzirenTable!=null && ele_touzirenTable.size()!=0){
				if (ele_touzirenTable==null || ele_touzirenTable.isEmpty()) {
//					stohrInfos=null;
				}else{
					Element ele_resultGdxx=ele_touzirenTable.get(0);//获取第一个table（股东信息）
					Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
					if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
						stohrInfos=null;
					}else{
						if(tr_resultsGdxx.size()>2 && getElementByIndex(ele_resultGdxx, "tr",2)!=null && getElements(tr_resultsGdxx.get(2), "td").size()==5){
							for(int i=2;i<tr_resultsGdxx.size();i++){
								AicpubRegStohrInfo aicpubRegStohrInfo=new AicpubRegStohrInfo();
								Element ele_gdType = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",0);
								aicpubRegStohrInfo.setType(getElementText(ele_gdType));//股东类型
								Element ele_gdName = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);
								aicpubRegStohrInfo.setName(getElementText(ele_gdName));//股东名称
								Element ele_idType = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);
								aicpubRegStohrInfo.setIdType(getElementText(ele_idType));//证照/证件类型
								Element ele_idNum = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);
								aicpubRegStohrInfo.setIdNum(getElementText(ele_idNum));//证照/证件号码
								
								
								//股东信息详情
								if(resultHtmlMap.get("gsgsxx_djxx_gdxx")!=null){
									@SuppressWarnings("unchecked")
									List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_djxx_gdxx");
									if(detailMap.size()!=0){
									Elements tables= Jsoup.parseBodyFragment(detailMap.get(i-2).toString()).select("table.result-table");
									if(tables==null || tables.isEmpty()){}
									else{
										Element websiteInfo=tables.get(0);
										if(websiteInfo!=null){
										Elements tr_websiteInfo = getElements(websiteInfo, "tr");
										if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//											websiteInfos=null;
										}else{
											if(tr_websiteInfo.size()>3){
												//第一种情况格式
												if(getElementByIndex(websiteInfo, "tr",3)!=null && getElements(tr_websiteInfo.get(3), "td").size()==3){
													AicpubRegStohrStohrinvestInfo aicpubRegStohrStohrinvestInfo1=new AicpubRegStohrStohrinvestInfo();
													List<AmountDetail> subAmountDetails=new ArrayList<AmountDetail>();//认缴明细
													List<AmountDetail> paidAmountDetails=new ArrayList<AmountDetail>();//实缴明细
													for(int j=3;j<tr_websiteInfo.size();j++){
														if(j==3){
															Element ele_stockholder = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",0);
															aicpubRegStohrStohrinvestInfo1.setStockholder(getElementText(ele_stockholder));//股东
															Element ele_subAmount = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",1);
															aicpubRegStohrStohrinvestInfo1.setSubAmount(getElementText(ele_subAmount));//认缴额（万元）
															Element ele_paidAmount = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",2);
															aicpubRegStohrStohrinvestInfo1.setPaidAmount(getElementText(ele_paidAmount));//实缴额（万元）
														}
														if(j>3){
															AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail1=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
															Element ele_method1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",0);
															amountDetail1.investMethod=getElementText(ele_method1);//出资方式
															Element ele_amount1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",1);
															amountDetail1.investAmount=getElementText(ele_amount1);//出资额（万元）
															Element ele_czDate1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",2);
															amountDetail1.investDateTime=getElementText(ele_czDate1);//出资日期
															subAmountDetails.add(amountDetail1);
															AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail2=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
															Element ele_method2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",3);
															amountDetail2.investMethod=getElementText(ele_method2);//出资方式
															Element ele_amount2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",4);
															amountDetail2.investAmount=getElementText(ele_amount2);//出资额（万元）
															Element ele_czDate2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",j)), "td",5);
															amountDetail2.investDateTime=getElementText(ele_czDate2);//出资日期
															paidAmountDetails.add(amountDetail2);
														}
													}
													aicpubRegStohrStohrinvestInfo1.setSubAmountDetails(subAmountDetails);
													aicpubRegStohrStohrinvestInfo1.setPaidAmountDetails(paidAmountDetails);
													aicpubRegStohrInfo.setStohrInvestInfo(aicpubRegStohrStohrinvestInfo1);
												}
											}
										}
									}
									}
								}
								}
								stohrInfos.add(aicpubRegStohrInfo);
							}
							
						}
						
					}
				}//
				
			}
				
				aicpubRegInfo.setStohrInfos(stohrInfos);//List<AicpubRegStohrInfo>
				
				//变更信息（包括变更信息详细）
				Element ele_resultBgxx=null;
				if (ele_resultsTable==null || ele_resultsTable.isEmpty()) {
					changeInfos=null;
				}else{
					if(ele_touzirenTable!=null && ele_touzirenTable.size()!=0){
						ele_resultBgxx=ele_resultsTable.get(2);
					}else{
						ele_resultBgxx=ele_resultsTable.get(1);
					}
					Elements tr_resultsBgxx = getElements(ele_resultBgxx, "tr");
					if (tr_resultsBgxx==null || tr_resultsBgxx.isEmpty()) {
						changeInfos=null;
					}else{
						for(int i=0;i<(tr_resultsBgxx.size()-2);i++){
							AicpubRegChangeInfo changeInfo=new AicpubRegChangeInfo();
							Element ele_bgItem = getElementByIndex((getElementByIndex(ele_resultBgxx, "tr",i+2)), "td",0);
							if(!(getElementText(ele_bgItem).equals("无"))){
								changeInfo.setItem(getElementText(ele_bgItem));//变更事项
								Element ele_bgqContent = getElementByIndex((getElementByIndex(ele_resultBgxx, "tr",i+2)), "td",1);
								changeInfo.setPreContent(getElementText(ele_bgqContent));//变更前内容
								Element ele_bghContent = getElementByIndex((getElementByIndex(ele_resultBgxx, "tr",i+2)), "td",2);
								changeInfo.setPostContent(getElementText(ele_bghContent));//变更后内容
								Element ele_bgDate = getElementByIndex((getElementByIndex(ele_resultBgxx, "tr",i+2)), "td",3);
								changeInfo.setDateTime(getElementText(ele_bgDate));//变更日期
								changeInfos.add(changeInfo);
							}else{
								 break;
							}
							
						}
						
					}
				}
				aicpubRegInfo.setChangeInfos(changeInfos);//List<AicpubRegChangeInfo>
			}
			}
			aicpubInfo.setRegInfo(aicpubRegInfo);//增加登记信息   AicpubRegInfo
			//登记信息********************结束
			//备案信息********************开始
			AicpubArchiveInfo archiveInfo=new AicpubArchiveInfo();
			Elements ele_resultsTableBaxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_baxx_zyryxx").toString()).select("table.result-table");
			if (ele_resultsTableBaxx==null || ele_resultsTableBaxx.isEmpty()) {
				archiveInfo=null;
			}else{
				//主要人员信息
				Element ele_resultZyryxx=ele_resultsTableBaxx.get(0);//获取第一个table（主要人员信息）
				List<AicpubArchivePrimemberInfo> priMemberInfos=new ArrayList<AicpubArchivePrimemberInfo>();
				Elements tr_resultsZyryxx = getElements(ele_resultZyryxx, "tr");
				Element ele_name0 = getElementByIndex((getElementByIndex(ele_resultZyryxx, "tr",0)), "td",0);
				if(ele_name0!=null && getElementText(ele_name0).equals("主要人员信息")){
				tr_resultsZyryxx.remove(0);
				tr_resultsZyryxx.remove(0);
				if (tr_resultsZyryxx==null || tr_resultsZyryxx.isEmpty()) {
					priMemberInfos=null;
				}else{
					for(int i=0;i<tr_resultsZyryxx.size();i++){
						Elements td_results=getElements(tr_resultsZyryxx.get(i), "td");
						if(td_results.size()==6){
							AicpubArchivePrimemberInfo priMemberInfo=new AicpubArchivePrimemberInfo();
							Element ele_name1 = getElementByIndex((getElementByIndex(ele_resultZyryxx, "tr",i+2)), "td",1);
							priMemberInfo.setName(getElementText(ele_name1));//姓名
							Element ele_position1 = getElementByIndex((getElementByIndex(ele_resultZyryxx, "tr",i+2)), "td",2);
							priMemberInfo.setPosition(getElementText(ele_position1));//职务
							priMemberInfos.add(priMemberInfo);
							AicpubArchivePrimemberInfo priMemberInfo2=new AicpubArchivePrimemberInfo();
							Element ele_name2 = getElementByIndex((getElementByIndex(ele_resultZyryxx, "tr",i+2)), "td",4);
							priMemberInfo2.setName(getElementText(ele_name2));//姓名
							Element ele_position2 = getElementByIndex((getElementByIndex(ele_resultZyryxx, "tr",i+2)), "td",5);
							priMemberInfo2.setPosition(getElementText(ele_position2));//职务
							priMemberInfos.add(priMemberInfo2);
						}else{
							AicpubArchivePrimemberInfo priMemberInfo=new AicpubArchivePrimemberInfo();
							Element ele_name1 = getElementByIndex((getElementByIndex(ele_resultZyryxx, "tr",i+2)), "td",1);
							priMemberInfo.setName(getElementText(ele_name1));//姓名
							Element ele_position1 = getElementByIndex((getElementByIndex(ele_resultZyryxx, "tr",i+2)), "td",2);
							priMemberInfo.setPosition(getElementText(ele_position1));//职务
							priMemberInfos.add(priMemberInfo);
						}
						
					}
					archiveInfo.setPriMemberInfos(priMemberInfos);//AicpubArchivePrimemberInfo
				}
				
				//分支机构信息
				Element ele_resultFzjgxx=ele_resultsTableBaxx.get(1);//获取第二个table（分支机构信息）
				List<AicpubArchiveBranchInfo> branchInfos=new ArrayList<AicpubArchiveBranchInfo>();
				if(ele_resultFzjgxx!=null){
					Elements tr_results = getElements(ele_resultFzjgxx, "tr");
					if(tr_results!=null && tr_results.size()>0){
						Elements td_results=getElements(tr_results.get(0), "td");
						if(td_results!=null && td_results.size()==1 && getElementText(td_results.get(0)).equals("分支机构信息")){
							if(tr_results.size()>2 && getElementByIndex(ele_resultFzjgxx, "tr",2)!=null && getElements(tr_results.get(2), "td").size()==4){
								for(int i=2;i<tr_results.size();i++){
									Elements td_resultsGdxx = getElements(tr_results.get(i), "td");
									if(td_resultsGdxx!=null && td_resultsGdxx.size()==4){
										AicpubArchiveBranchInfo aicpubArchiveBranchInfo=new AicpubArchiveBranchInfo();
										Element ele_item = getElementByIndex((getElementByIndex(ele_resultFzjgxx, "tr",i)), "td",1);//统一社会信用代码/注册号
										aicpubArchiveBranchInfo.setNum(getElementText(ele_item));
										Element ele_preContent = getElementByIndex((getElementByIndex(ele_resultFzjgxx, "tr",i)), "td",2);//名称
										aicpubArchiveBranchInfo.setName(getElementText(ele_preContent));
										Element ele_postContent = getElementByIndex((getElementByIndex(ele_resultFzjgxx, "tr",i)), "td",3);
										aicpubArchiveBranchInfo.setRegAuthority(getElementText(ele_postContent));
										branchInfos.add(aicpubArchiveBranchInfo);
									}
							}
							}	
						}
					}
				}
				archiveInfo.setBranchInfos(branchInfos);
				//清算信息
				Element ele_resultQsxx=ele_resultsTableBaxx.get(2);//获取第三个table（清算信息）
			}
				
				
			}
			aicpubInfo.setArchiveInfo(archiveInfo);//增加备案信息 AicpubArchiveInfo
			//备案信息********************结束
			
			
			//动产抵押登记信息********************开始
			AicpubChatMortgInfo aicpubChatMortgInfo=new AicpubChatMortgInfo();
			if (resultHtmlMap.get("gsgsxx_dcdydjxx_dcdydjxx")!=null && isDebug) {
			aicpubChatMortgInfo.setHtml(resultHtmlMap.get("gsgsxx_dcdydjxx_dcdydjxx").toString());
			}
			aicpubInfo.setChatMortgInfo(aicpubChatMortgInfo);//AicpubChatMortgInfo
			//动产抵押登记信息********************结束
			
			//股权出质登记信息********************开始
			AicpubEqumortgregInfo aicpubEqumortgregInfo=new AicpubEqumortgregInfo();
			if (resultHtmlMap.get("gsgsxx_gqczdjxx_gqczdjxx")!=null && isDebug) {
			aicpubEqumortgregInfo.setHtml(resultHtmlMap.get("gsgsxx_gqczdjxx_gqczdjxx").toString());
			}
			aicpubInfo.setEquMortgRegInfo(aicpubEqumortgregInfo);//AicpubEqumortgregInfo
			//股权出质登记信息********************结束
			
			//行政处罚信息********************开始
			AicpubAdmpunishInfo aicpubAdmpunishInfo=new AicpubAdmpunishInfo();
			if (resultHtmlMap.get("gsgsxx_xzcfxx_xzcfxx")!=null) {
			Elements ele_resultsTableXzcfxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_xzcfxx_xzcfxx").toString()).select("table.result-table");
			if (ele_resultsTableXzcfxx==null || ele_resultsTableXzcfxx.isEmpty()) {
				aicpubAdmpunishInfo=null;
			}else{
				Element ele_resultXzcfxx=ele_resultsTableXzcfxx.get(0);
				Elements tr_resultsXzcfxx = getElements(ele_resultXzcfxx, "tr");
				if (tr_resultsXzcfxx==null || tr_resultsXzcfxx.isEmpty()) {
					aicpubAdmpunishInfo=null;
				}else{
					List<AicpubAAdmpunishInfo> admPunishInfos =new ArrayList<AicpubAAdmpunishInfo>();
					if(tr_resultsXzcfxx.size()>2 && getElementByIndex(ele_resultXzcfxx, "tr",2)!=null && getElements(tr_resultsXzcfxx.get(2), "td").size()==7){
						for(int i=2;i<tr_resultsXzcfxx.size();i++){
							AicpubAAdmpunishInfo aicpubAAdmpunishInfo=new AicpubAAdmpunishInfo();
							Element ele_1 = getElementByIndex((getElementByIndex(ele_resultXzcfxx, "tr",i)), "td",1);//行政处罚决定书文号
							aicpubAAdmpunishInfo.setPunishRepNum(getElementText(ele_1));
							Element ele_2 = getElementByIndex((getElementByIndex(ele_resultXzcfxx, "tr",i)), "td",2);//违法行为类型
							aicpubAAdmpunishInfo.setIllegalActType(getElementText(ele_2));
							Element ele_3 = getElementByIndex((getElementByIndex(ele_resultXzcfxx, "tr",i)), "td",3);//行政处罚内容
							aicpubAAdmpunishInfo.setPunishContent(getElementText(ele_3));
							Element ele_4 = getElementByIndex((getElementByIndex(ele_resultXzcfxx, "tr",i)), "td",4);//作出行政处罚决定机关名称
							aicpubAAdmpunishInfo.setDeciAuthority(getElementText(ele_4));
							Element ele_5 = getElementByIndex((getElementByIndex(ele_resultXzcfxx, "tr",i)), "td",5);//作出行政处罚决定日期
							aicpubAAdmpunishInfo.setDeciDateTime(getElementText(ele_5));
							
							
							//详情
							if(resultHtmlMap.get("gsgsxx_xzcfxx_detail")!=null){
								@SuppressWarnings("unchecked")
								List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_xzcfxx_detail");
								if(detailMap.size()!=0 && detailMap.size()>(i-2)){
								Elements tables= Jsoup.parseBodyFragment(detailMap.get(i-2).toString()).select("table.result-table");
								if(tables==null || tables.isEmpty()){}
								else{
									Element websiteInfo=tables.get(0);
									Elements gsgsXzcfxqTrs = websiteInfo.select("tr");
									gsgsXzcfxqTrs.remove(0);
									gsgsXzcfxqTrs.remove(0);
									AicpubAAdmpunishInfo.PunishDetail punishDetail = aicpubAAdmpunishInfo.new PunishDetail();
									for (Element gsgsXzcfxqTr : gsgsXzcfxqTrs) {
										Elements gsgsxxDjjbTds1 = gsgsXzcfxqTr.select("td");
										for(int c=0;c<gsgsxxDjjbTds1.size();c++){
											if(gsgsXzcfxqTr.select("td").get(c).nextElementSibling()!=null){
											String xqThContent = gsgsXzcfxqTr.select("td").get(c).text().trim();
											String xqTdContent = gsgsXzcfxqTr.select("td").get(c).nextElementSibling().text();
											switch (xqThContent) {
											case "行政处罚书文号":
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
										}
										}
									}
									aicpubAAdmpunishInfo.setPunishDetail(punishDetail);
								}
							}
							}
							admPunishInfos.add(aicpubAAdmpunishInfo);
						}
					}	
					
					aicpubAdmpunishInfo.setAdmPunishInfos(admPunishInfos);
				  }
				}
			}
			
			if (resultHtmlMap.get("gsgsxx_xzcfxx_xzcfxx")!=null && isDebug) {
			aicpubAdmpunishInfo.setHtml(resultHtmlMap.get("gsgsxx_xzcfxx_xzcfxx").toString());
			}
		
			aicpubInfo.setAdmPunishInfo(aicpubAdmpunishInfo);//AicpubAdmpunishInfo
			//行政处罚信息********************结束
			
			
			//经营异常信息********************开始
			AicpubOperanomaInfo operAnomaInfo=new AicpubOperanomaInfo();
			Elements ele_resultsTableJyycxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_jyycxx_jyycxx").toString()).select("table.result-table");
			if (ele_resultsTableJyycxx==null || ele_resultsTableJyycxx.isEmpty()) {
				operAnomaInfo=null;
			}
			
			//经营异常信息
			Element ele_resultJyycxx=ele_resultsTableJyycxx.get(0);//获取第一个table（经营异常信息）
			List<AicpubOOperanomaInfo> operAnomaInfos=new ArrayList<AicpubOOperanomaInfo>();
			Elements tr_resultsJyycxx = getElements(ele_resultJyycxx, "tr");
			if (tr_resultsJyycxx==null || tr_resultsJyycxx.isEmpty()) {
				operAnomaInfos=null;
			}else{
				for(int i=0;i<(tr_resultsJyycxx.size()-2);i++){
					AicpubOOperanomaInfo ooperAnomaInfo=new AicpubOOperanomaInfo();
					Element ele_one = getElementByIndex((getElementByIndex(ele_resultJyycxx, "tr",i+2)), "td",0);
					if(!(getElementText(ele_one).equals("无"))){
						Element ele_lrjyycmlCause = getElementByIndex((getElementByIndex(ele_resultJyycxx, "tr",i+2)), "td",1);
						ooperAnomaInfo.setIncludeCause(getElementText(ele_lrjyycmlCause));//列入经营异常名录原因
						Element ele_lrDate = getElementByIndex((getElementByIndex(ele_resultJyycxx, "tr",i+2)), "td",2);
						ooperAnomaInfo.setIncludeDateTime(getElementText(ele_lrDate));//列入日期
						Element ele_ycjyycmlCause = getElementByIndex((getElementByIndex(ele_resultJyycxx, "tr",i+2)), "td",4);
						ooperAnomaInfo.setRemoveCause(getElementText(ele_ycjyycmlCause));//移出经营异常名录原因
						Element ele_ycDate = getElementByIndex((getElementByIndex(ele_resultJyycxx, "tr",i+2)), "td",5);
						ooperAnomaInfo.setRemoveDateTime(getElementText(ele_ycDate));//移出日期
						Element ele_zcjdAuthority = getElementByIndex((getElementByIndex(ele_resultJyycxx, "tr",i+2)), "td",6);
						ooperAnomaInfo.setRemoveAuthority(getElementText(ele_zcjdAuthority));//作出决定机关(列入和移出 做出决定机关)
						operAnomaInfos.add(ooperAnomaInfo);
					}else{
						 break;
					}
					
				}
			
				operAnomaInfo.setOperAnomaInfos(operAnomaInfos);//AicpubOOperanomaInfo
			}
			aicpubInfo.setOperAnomaInfo(operAnomaInfo);//增加经营异常信息 AicpubOperanomaInfo
			//经营异常信息********************结束
			
			
			//严重违法信息********************开始
			AicpubSerillegalInfo aicpubSerillegalInfo=new AicpubSerillegalInfo();
			if (resultHtmlMap.get("gsgsxx_yzwfxx_yzwfxx")!=null && isDebug) {
			aicpubSerillegalInfo.setHtml(resultHtmlMap.get("gsgsxx_yzwfxx_yzwfxx").toString());
			}
			aicpubInfo.setSerIllegalInfo(aicpubSerillegalInfo);//AicpubSerillegalInfo
			//严重违法信息********************结束
			
			
			//抽查检查信息********************开始
			AicpubCheckInfo aicpubCheckInfo=new AicpubCheckInfo();
			if (resultHtmlMap.get("gsgsxx_ccjcxx_ccjcxx")!=null && isDebug) {
			aicpubCheckInfo.setHtml(resultHtmlMap.get("gsgsxx_ccjcxx_ccjcxx").toString());
			}
			aicpubInfo.setCheckInfo(aicpubCheckInfo);// AicpubCheckInfo
			//抽查检查信息********************结束
			//********************工商公示信息 结束********************
			//********************企业公示信息 开始********************
			//企业公示信息
			EntpubInfo entPubInfo=new EntpubInfo();
			//企业年报********************开始
			//企业年报列表
			List<EntpubAnnreportInfo> entpubAnnreportInfos=new ArrayList<EntpubAnnreportInfo>();
			if (resultHtmlMap.get("qygsxx_qynb_list")!=null){
				Elements elesqynb = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_qynb_list").toString()).select("table#touziren");
				if(elesqynb==null || elesqynb.isEmpty()) {
//					annReports=null;
				}else{
					Element ele_resultqynb=elesqynb.get(0);
					Elements tr_resultsqynb = getElements(ele_resultqynb, "tr");
					if (tr_resultsqynb==null || tr_resultsqynb.isEmpty()) {
//						annReports=null;
					}else{
						if(tr_resultsqynb.size()>2 && getElementByIndex(ele_resultqynb, "tr",2)!=null && getElements(tr_resultsqynb.get(2), "td").size()==4){
							for(int i=2;i<tr_resultsqynb.size();i++){
								EntpubAnnreportInfo annReports=new EntpubAnnreportInfo();
								Element ele_submitYear = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i)), "td",1);
								annReports.setSubmitYear(getElementText(ele_submitYear));//报送年度
								Element ele_pubDate = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i)), "td",2);
								annReports.setPubDateTime(getElementText(ele_pubDate));//发布日期  
								
								//详情
								if(resultHtmlMap.get("qygsxx_qynb_detail")!=null){
									@SuppressWarnings("unchecked")
									List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("qygsxx_qynb_detail");
									if(detailMap.size()!=0){
										Elements tables = Jsoup.parseBodyFragment(detailMap.get(i-2).toString()).select("table");
										if(tables==null || tables.isEmpty()){}
										else{
											for(int g=0;g<tables.size();g++){
												Element qygsxxQynb13Table=tables.get(g);
												if(getElements(qygsxxQynb13Table, "tr").size()>0){
												String qygsxxQynbTitle = qygsxxQynb13Table.select("th").get(0).text();
												
												if (qygsxxQynbTitle.indexOf("年报报告") > 0) {
													qygsxxQynbTitle = qygsxxQynb13Table.select("th").get(1).text();
												}
												
												Elements qygsxxQynb13Ths = qygsxxQynb13Table.select("th");
												Elements qygsxxQynb13Tds = qygsxxQynb13Table.select("td");
												qygsxxQynb13Ths.remove(0);
												
												if ("企业基本信息".equals(qygsxxQynbTitle) || "基本信息".equals(qygsxxQynbTitle)) {
													EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
													qygsxxQynb13Ths.remove(0);
													
													for (int a = 0; a < qygsxxQynb13Ths.size(); a++) {
														String qygsxxQynb13Th = qygsxxQynb13Ths.get(a).text().replace(" ", "");
														String qygsxxQynb13Td = qygsxxQynb13Tds.get(a).text();
														
														switch (qygsxxQynb13Th) {
															case "注册号/统一社会信用代码":
																qygsQynbJbInfo.setNum(qygsxxQynb13Td);
																break;
															case "注册号":
																qygsQynbJbInfo.setNum(qygsxxQynb13Td);
																break;
															case "企业名称":
																qygsQynbJbInfo.setName(qygsxxQynb13Td);
																break;
															case "企业联系电话":
																qygsQynbJbInfo.setTel(qygsxxQynb13Td);
																break;
															case "联系电话":
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
															case "合作社名称":
																qygsQynbJbInfo.setCooperativeName(qygsxxQynb13Td);
																break;
															case "成员人数":
																qygsQynbJbInfo.setMembersNum(qygsxxQynb13Td);
																break;
															default:
																break;
														}
													}
													
													annReports.setBaseInfo(qygsQynbJbInfo);
													
												} else if ("网站或网店信息".equals(qygsxxQynbTitle)) {
													List<EntpubAnnreportWebsiteInfo> websiteInfos=new ArrayList<EntpubAnnreportWebsiteInfo>();
													Elements tr_websiteInfo = getElements(qygsxxQynb13Table, "tr");
													if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
														websiteInfos=null;
													}else{
														for(int b=0;b<(tr_websiteInfo.size()-2);b++){
															EntpubAnnreportWebsiteInfo entpubAnnreportWebsiteInfo=new EntpubAnnreportWebsiteInfo();
															Element ele_type = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",0);//类型
															if(ele_type!=null && !getElementText(ele_type).isEmpty()){
															entpubAnnreportWebsiteInfo.setType(getElementText(ele_type));
															Element ele_name = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",1);//名称
															entpubAnnreportWebsiteInfo.setName(getElementText(ele_name));
															Element ele_website = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",2);//网址
															entpubAnnreportWebsiteInfo.setWebsite(getElementText(ele_website));
															websiteInfos.add(entpubAnnreportWebsiteInfo);
															}
														}
													}
													annReports.setWebsiteInfos(websiteInfos);
												}else if (qygsxxQynbTitle.contains("股东及出资信息")) {
													List<EntpubAnnreportStohrinvestInfo> stohrInvestInfos=new ArrayList<EntpubAnnreportStohrinvestInfo>();
													Elements tr_stohrInvestInfo = getElements(qygsxxQynb13Table, "tr");
													if (tr_stohrInvestInfo==null || tr_stohrInvestInfo.isEmpty()) {
														stohrInvestInfos=null;
													}else{
														for(int b=0;b<(tr_stohrInvestInfo.size()-2);b++){
															EntpubAnnreportStohrinvestInfo entpubAnnreportStohrinvestInfo=new EntpubAnnreportStohrinvestInfo();
															Element ele_Stockholder = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",0);//股东
															if( ele_Stockholder!=null && !getElementText(ele_Stockholder).isEmpty()){
															entpubAnnreportStohrinvestInfo.setStockholder(getElementText(ele_Stockholder));
															Element ele_SubAmount = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",1);//认缴出资额（万元）
															entpubAnnreportStohrinvestInfo.setSubAmount(getElementText(ele_SubAmount));
															Element ele_SubDateTime = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",2);//认缴出资时间
															entpubAnnreportStohrinvestInfo.setSubDateTime(getElementText(ele_SubDateTime));
															Element ele_SubMethod = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",3);//认缴出资方式
															entpubAnnreportStohrinvestInfo.setSubMethod(getElementText(ele_SubMethod));
															Element ele_PaidAmount = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",4);//实缴出资额（万元）
															entpubAnnreportStohrinvestInfo.setPaidAmount(getElementText(ele_PaidAmount));
															Element ele_PaidDateTime = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",5);//出资时间
															entpubAnnreportStohrinvestInfo.setPaidDateTime(getElementText(ele_PaidDateTime));
															Element ele_PaidMethod = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",6);//出资方式
															entpubAnnreportStohrinvestInfo.setPaidMethod(getElementText(ele_PaidMethod));
															stohrInvestInfos.add(entpubAnnreportStohrinvestInfo);
															}
														}
													}
													annReports.setStohrInvestInfos(stohrInvestInfos);	
													
												}else if ("企业资产状况信息".equals(qygsxxQynbTitle) || "生产经营情况".equals(qygsxxQynbTitle) || "资产状况信息".equals(qygsxxQynbTitle)) {
													EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
													
													for (int j = 0; j < qygsxxQynb13Ths.size(); j++) {
														String qygsxxQynb13Th = qygsxxQynb13Ths.get(j).text();
														String qygsxxQynb13Td = qygsxxQynb13Tds.get(j).text();
														
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
															case "销售额或营业收入":
																qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(qygsxxQynb13Td);
																break;
															case "盈余总额":
																qygsQynbQyzczkInfo.setProfitAmount(qygsxxQynb13Td);
																break;
															case "纳税金额":
																qygsQynbQyzczkInfo.setTaxesAmount(qygsxxQynb13Td);
																break;
															case "获得政府扶持资金、补助":
																qygsQynbQyzczkInfo.setGovernmentFunds(qygsxxQynb13Td);
																break;
															case "金融贷款":
																qygsQynbQyzczkInfo.setFinancialLoan(qygsxxQynb13Td);
																break;
															default:
																break;
														}
														
													}
													
													annReports.setAssetInfo(qygsQynbQyzczkInfo);
													
												}else if (qygsxxQynbTitle.contains("对外投资信息")) {
													List<EntpubAnnreportExtinvestInfo> extInvestInfos=new ArrayList<EntpubAnnreportExtinvestInfo>();
													Elements tr_stohrInvestInfo = getElements(qygsxxQynb13Table, "tr");
													if (tr_stohrInvestInfo==null || tr_stohrInvestInfo.isEmpty()) {
//														stohrInvestInfos=null;
													}else{
														if(tr_stohrInvestInfo.size()>2 && getElementByIndex(qygsxxQynb13Table, "tr",2)!=null && getElements(tr_stohrInvestInfo.get(2), "td").size()==2){
															for(int b=2;b<tr_stohrInvestInfo.size();b++){
																EntpubAnnreportExtinvestInfo entpubAnnreportExtinvestInfo=new EntpubAnnreportExtinvestInfo();
																
																Element ele1 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",0);
																entpubAnnreportExtinvestInfo.setEnterpriseName(getElementText(ele1));//投资设立企业或购买股权企业名称
																Element ele2 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",1);
																entpubAnnreportExtinvestInfo.setRegNum(getElementText(ele2));//注册号
																extInvestInfos.add(entpubAnnreportExtinvestInfo);
															}
														}			
													}
													annReports.setExtInvestInfos(extInvestInfos);
													
												}else if ("修改记录".equals(qygsxxQynbTitle)) {
													List<EntpubAnnreportModifyInfo> changeInfos2=new ArrayList<EntpubAnnreportModifyInfo>();
													Elements tr_websiteInfo = getElements(qygsxxQynb13Table, "tr");
													if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//														websiteInfos=null;
													}else{
														if(tr_websiteInfo.size()>2){
														Elements td_resultsGdxx1 = getElements(tr_websiteInfo.get(2), "td");
														if(td_resultsGdxx1.size()==5){
															for(int b=2;b<tr_websiteInfo.size();b++){
																if(getElements(tr_websiteInfo.get(b), "td")!=null && getElements(tr_websiteInfo.get(b), "td").size()==5){
																EntpubAnnreportModifyInfo entpubAnnreportModifyInfo=new EntpubAnnreportModifyInfo();
																Element ele1 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",1);//修改事项
																entpubAnnreportModifyInfo.setItem(getElementText(ele1));
																Element ele2 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",2);//修改前
																entpubAnnreportModifyInfo.setPreContent(getElementText(ele2));
																Element ele3 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",3);//修改后
																entpubAnnreportModifyInfo.setPostContent(getElementText(ele3));
																Element ele4 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",4);//修改日期
																entpubAnnreportModifyInfo.setDateTime(getElementText(ele4));
																changeInfos2.add(entpubAnnreportModifyInfo);
																}
															}
														}
													   }
															annReports.setChangeInfos(changeInfos2);
														}
													
												}
												
												
											}
										}
									}
								}
									
									
								}
								entpubAnnreportInfos.add(annReports);
							}
						}
					}
				}
			}
			
			entPubInfo.setAnnReports(entpubAnnreportInfos);//EntpubAnnreportInfo
			//企业年报********************结束
			
			
			//行政许可信息********************开始
			EntpubAdmlicInfo admLicInfo=new EntpubAdmlicInfo();
			if (resultHtmlMap.get("qygsxx_xzxkxx")!=null && isDebug) {
			admLicInfo.setHtml(resultHtmlMap.get("qygsxx_xzxkxx").toString());
			}
			entPubInfo.setAdmLicInfo(admLicInfo);//EntpubAdmlicInfo
			//行政许可信息********************结束
			
			//股东及出资信息********************开始
			EntpubStohrinvestInfo stohrInvestInfo=new EntpubStohrinvestInfo();
			
			if(resultHtmlMap.get("qygsxx_gdjczxx")!=null){
				Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_gdjczxx").toString()).select("table");
				if(elesGdxx==null || elesGdxx.isEmpty()) {
//					changeInfos=null;
				}else{
					if(elesGdxx.size()>0){
					Element ele_resultGdxx=elesGdxx.get(0);//获取第一个table
					Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
					if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//						changeInfos=null;
					}else{
	                  if(tr_resultsGdxx.size()>2 && getElementByIndex(ele_resultGdxx, "tr",2)!=null && getElements(tr_resultsGdxx.get(2), "td").size()==5){
	                	  List<EntpubSStohrinvestInfo> stohrInvestInfos=new ArrayList<EntpubSStohrinvestInfo>();
	                		for(int i=2;i<tr_resultsGdxx.size();i++){
								if(getElements(tr_resultsGdxx.get(i), "td")!=null && getElements(tr_resultsGdxx.get(i), "td").size()==5){
									EntpubSStohrinvestInfo entpubSStohrinvestInfo=new EntpubSStohrinvestInfo();
									List<Detail> subDetails=new ArrayList<Detail>();
									List<Detail> paidDetails=new ArrayList<Detail>();
									
									Element ele1 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",0);
									entpubSStohrinvestInfo.setStockholder(getElementText(ele1));//股东
									Element ele2 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);
									entpubSStohrinvestInfo.setInvestorsType(getElementText(ele2));//认缴额（万元）
								/*	Element ele3 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);
									entpubSStohrinvestInfo.setPaidAmount(getElementText(ele3));//实缴额（万元）
*/											
									EntpubSStohrinvestInfo.Detail detail1=entpubSStohrinvestInfo.new Detail();
									Element ele4 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);
									detail1.setMethod(getElementText(ele4));//出资方式
									Element ele5 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",4);
									detail1.setAmount(getElementText(ele5));//出资额（万元）
									Element ele6 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);
									detail1.setDateTime(getElementText(ele6));//出资日期
									subDetails.add(detail1);
									
	                                /*EntpubSStohrinvestInfo.Detail detail2=entpubSStohrinvestInfo.new Detail();
	                                Element ele7 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",6);
	                                detail2.setMethod(getElementText(ele7));//出资方式
	                                Element ele8 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",7);
	                                detail2.setAmount(getElementText(ele8));//出资额（万元）
	                                Element ele9 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",8);
	                                detail2.setDateTime(getElementText(ele9));//出资日期
	                                paidDetails.add(detail2);*/
	                                
	                                entpubSStohrinvestInfo.setSubDetails(subDetails);
	                                entpubSStohrinvestInfo.setPaidDetails(paidDetails);
	                                stohrInvestInfos.add(entpubSStohrinvestInfo);
								}
						  }
	                		stohrInvestInfo.setStohrInvestInfos(stohrInvestInfos);
						}
						
					}
				}
					
				}
			}
			
			if(resultHtmlMap.get("qygsxx_gdjczxx")!=null && isDebug){
			stohrInvestInfo.setHtml(resultHtmlMap.get("qygsxx_gdjczxx").toString());
			}
			entPubInfo.setStohrInvestInfo(stohrInvestInfo);
			//股东及出资信息********************结束
			
			//股权变更信息********************开始
			EntpubEquchangeInfo equChangeInfo=new EntpubEquchangeInfo();
			if (resultHtmlMap.get("qygsxx_gqbgxx")!=null && isDebug) {
			equChangeInfo.setHtml(resultHtmlMap.get("qygsxx_gqbgxx").toString());
			}
			entPubInfo.setEquChangeInfo(equChangeInfo);//EntpubEquchangeInfo
			//股权变更信息********************结束
			
			//知识产权出质登记信息********************开始
			EntpubIntellectualproregInfo intellectualProRegInfo=new EntpubIntellectualproregInfo();
			if (resultHtmlMap.get("qygsxx_zscqczdjxx")!=null && isDebug) {
			intellectualProRegInfo.setHtml(resultHtmlMap.get("qygsxx_zscqczdjxx").toString());
			}
			entPubInfo.setIntellectualProRegInfo(intellectualProRegInfo);//EntpubIntellectualproregInfo
			//知识产权出质登记信息********************结束
			
			//行政处罚信息********************开始
			EntpubAdmpunishInfo admPunishInfo=new EntpubAdmpunishInfo();
			if (resultHtmlMap.get("qygsxx_xzcfxx")!=null && isDebug) {
			admPunishInfo.setHtml(resultHtmlMap.get("qygsxx_xzcfxx").toString());
			}
			entPubInfo.setAdmPunishInfo(admPunishInfo);//EntpubAdmpunishInfo
			//行政处罚信息********************结束
			
			
			//********************企业公示信息 结束********************
			//********************司法协助公示信息 开始********************
			//司法协助公示信息
			JudasspubInfo judAssPubInfo=new JudasspubInfo();
			//股权冻结信息********************开始
			JudasspubEqufreezeInfo equFreezeInfo=new JudasspubEqufreezeInfo();
			if (resultHtmlMap.get("sfxzgsxx_gqdjxx_list")!=null && isDebug) {
			equFreezeInfo.setHtml(resultHtmlMap.get("sfxzgsxx_gqdjxx_list").toString());
			}
			judAssPubInfo.setEquFreezeInfo(equFreezeInfo);
			//股权冻结信息********************结束
			
			//股东变更信息********************开始
			JudasspubStohrchangeInfo stohrChangeInfo=new JudasspubStohrchangeInfo();
			if (resultHtmlMap.get("qygsxx_gdbgxx_list")!=null && isDebug) {
			stohrChangeInfo.setHtml(resultHtmlMap.get("qygsxx_gdbgxx_list").toString());
			}
			judAssPubInfo.setStohrChangeInfo(stohrChangeInfo);//JudasspubStohrchangeInfo
			//股东变更信息********************结束
			
			//********************司法协助公示信息 结束********************
			
			
			aicFeedJson.setAicPubInfo(aicpubInfo);//增加工商公示信息
			aicFeedJson.setEntPubInfo(entPubInfo);//增加企业公示信息
			aicFeedJson.setJudAssPubInfo(judAssPubInfo);//司法协助公示信息 JudasspubInfo
		}
			
		    return aicFeedJson;
			
		}
}
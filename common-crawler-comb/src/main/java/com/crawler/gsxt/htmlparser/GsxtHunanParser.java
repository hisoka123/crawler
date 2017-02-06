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
import com.crawler.gsxt.domain.json.AicpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveBranchInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveClearInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveInfo;
import com.crawler.gsxt.domain.json.AicpubArchivePrimemberInfo;
import com.crawler.gsxt.domain.json.AicpubCCheckInfo;
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
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.crawler.gsxt.domain.json.AicpubSerillegalInfo;
import com.crawler.gsxt.domain.json.EntpubAAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAssetInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportBaseInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportWebsiteInfo;
import com.crawler.gsxt.domain.json.EntpubEEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubInfo;
import com.crawler.gsxt.domain.json.EntpubIntellectualproregInfo;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo.Detail;
import com.crawler.gsxt.domain.json.EntpubStohrinvestInfo;
import com.crawler.gsxt.domain.json.JudasspubEEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubInfo;
import com.crawler.gsxt.domain.json.JudasspubStohrchangeInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtHunanParser extends AbstractGsxtParser {

	private static final Logger log = LoggerFactory
			.getLogger(GsxtHunanParser.class);

	public AicFeedJson hunanParser(String html, boolean isDebug) {
		AicFeedJson aicFeedJson = new AicFeedJson();
		if(html.equals("{}") || html.isEmpty()){
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
		
		Elements elesJbxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_jbxx").toString()).select("table");
		if(elesJbxx==null || elesJbxx.isEmpty()) {
			aicpubRegBaseInfo=null;
		}else{
			Element ele_resultJbxx=elesJbxx.get(0);//获取第一个table
			Elements tr_resultsJbxx = getElements(ele_resultJbxx, "tr");
			Elements gsgsxxDjjbThs = ele_resultJbxx.select("th");
			Elements gsgsxxDjjbTds = ele_resultJbxx.select("td");
			gsgsxxDjjbThs.remove(0);
			if (tr_resultsJbxx==null || tr_resultsJbxx.isEmpty()) {
				aicpubRegBaseInfo=null;
			}else{			
				for (int i = 0; i < gsgsxxDjjbThs.size(); i++) {
					String gsgsxxDjjbTh = gsgsxxDjjbThs.get(i).text();
					String gsgsxxDjjbTd = gsgsxxDjjbTds.get(i).text();
					
					switch (gsgsxxDjjbTh) {
					case "注册号":
						aicpubRegBaseInfo.setNum(gsgsxxDjjbTd);
						break;
					case "统一社会信用代码":
						aicpubRegBaseInfo.setNum(gsgsxxDjjbTd);
						break;
					case "注册号/统一社会信用代码":
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
			aicpubRegInfo.setBaseInfo(aicpubRegBaseInfo);//AicpubRegBaseInfo
		}
		
		
		//股东信息
		List<AicpubRegStohrInfo> stohrInfos=new ArrayList<AicpubRegStohrInfo>();
		if(resultHtmlMap.get("gsgsxx_gdxx")!=null){
			Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_gdxx").toString()).select("table");
			if(elesGdxx==null || elesGdxx.isEmpty()) {
				stohrInfos=null;
			}else{
				Element ele_resultGdxx=elesGdxx.get(0);//获取第一个table
				Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
				if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
					stohrInfos=null;
				}else{
					
					if(tr_resultsGdxx.size()>2 && getElementByIndex(ele_resultGdxx, "tr",2)!=null && getElements(tr_resultsGdxx.get(2), "td").size()==5){
						for(int i=2;i<tr_resultsGdxx.size();i++){
							if(getElements(tr_resultsGdxx.get(i), "td").size()==5){
								AicpubRegStohrInfo aicpubRegStohrInfo=new AicpubRegStohrInfo();
								Element ele_Type = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",0);
								aicpubRegStohrInfo.setType(getElementText(ele_Type));//股东类型
								Element ele_Name = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);
								aicpubRegStohrInfo.setName(getElementText(ele_Name));//股东名称
								Element ele_IdType = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);
								aicpubRegStohrInfo.setIdType(getElementText(ele_IdType));//证照/证件类型
								Element ele_IdNum = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);
								aicpubRegStohrInfo.setIdNum(getElementText(ele_IdNum));//证照/证件号码
								
								
								//详情gsgsxx_gdxx_detail
								
								//股东详情
								if(resultHtmlMap.get("gsgsxx_gdxx_detail")!=null){
									@SuppressWarnings("unchecked")
									List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_gdxx_detail");
									//System.out.println(detailMap);
									if(detailMap.size()!=0 && detailMap.size()>(i-2)){
										//System.out.println(detailMap.get(i-2).toString());
										Elements tables= Jsoup.parseBodyFragment(detailMap.get(i-2).toString()).select("table");
										if(tables==null || tables.isEmpty()){}
										else{
											Element websiteInfo=tables.get(0);
											Elements tr_websiteInfo = getElements(websiteInfo, "tr");
											if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//												websiteInfos=null;
											}else{
												AicpubRegStohrStohrinvestInfo aicpubRegStohrStohrinvestInfo1=new AicpubRegStohrStohrinvestInfo();
												List<AmountDetail> subAmountDetails=new ArrayList<AmountDetail>();//认缴明细
												List<AmountDetail> paidAmountDetails=new ArrayList<AmountDetail>();//实缴明细
												
												if(tr_websiteInfo.size()>3 && getElementByIndex(websiteInfo, "tr",3)!=null && getElements(tr_websiteInfo.get(3), "td").size()==9){
												for(int b=3;b<tr_websiteInfo.size();b++){
													if(getElementByIndex(websiteInfo, "tr",b)!=null && getElements(tr_websiteInfo.get(b), "td").size()==9){
														Element ele_stockholder = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",0);
														aicpubRegStohrStohrinvestInfo1.setStockholder(getElementText(ele_stockholder));//股东
														Element ele_subAmount = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",1);
														aicpubRegStohrStohrinvestInfo1.setSubAmount(getElementText(ele_subAmount));	//认缴额（万元）
														Element ele_paidAmount = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",2);
														aicpubRegStohrStohrinvestInfo1.setPaidAmount(getElementText(ele_paidAmount));//实缴额（万元）
														
														AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail1=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
														Element ele_method1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",3);
														amountDetail1.investMethod=getElementText(ele_method1);//出资方式
														Element ele_amount1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",4);
														amountDetail1.investAmount=getElementText(ele_amount1);//出资额（万元）
														Element ele_czDate1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",5);
														amountDetail1.investDateTime=getElementText(ele_czDate1);//出资日期
														subAmountDetails.add(amountDetail1);
														
														AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail2=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
														Element ele_method2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",6);
														amountDetail2.investMethod=getElementText(ele_method2);//出资方式
														Element ele_amount2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",7);
														amountDetail2.investAmount=getElementText(ele_amount2);//出资额（万元）
														Element ele_czDate2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",b)), "td",8);
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
								stohrInfos.add(aicpubRegStohrInfo);
							}
							
						}
						
					}
					
				}
			}
		}
		
		aicpubRegInfo.setStohrInfos(stohrInfos);
		
		
		//变更信息
		List<AicpubRegChangeInfo> changeInfos=new ArrayList<AicpubRegChangeInfo>();
		if(resultHtmlMap.get("gsgsxx_bgxx")!=null){
			Elements elesbgxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_bgxx").toString()).select("table");
			if(elesbgxx==null || elesbgxx.isEmpty()) {
				changeInfos=null;
			}else{
				Element ele_resultbgxx=elesbgxx.get(0);
				Elements tr_resultsbgxx = getElements(ele_resultbgxx, "tr");
				if (tr_resultsbgxx==null || tr_resultsbgxx.isEmpty()) {
					changeInfos=null;
				}else{
					if(tr_resultsbgxx.size()>2 && getElementByIndex(ele_resultbgxx, "tr",2)!=null && getElements(tr_resultsbgxx.get(2), "td").size()==4){
						for(int i=2;i<tr_resultsbgxx.size();i++){
							if(getElements(tr_resultsbgxx.get(i), "td").size()==4){
								AicpubRegChangeInfo aicpubRegChangeInfo=new AicpubRegChangeInfo();
								Element ele_item = getElementByIndex((getElementByIndex(ele_resultbgxx, "tr",i)), "td",0);//变更事项
								aicpubRegChangeInfo.setItem(getElementText(ele_item));
								Element ele_preContent = getElementByIndex((getElementByIndex(ele_resultbgxx, "tr",i)), "td",1);//变更前内容
								aicpubRegChangeInfo.setPreContent(getElementText(ele_preContent));
								Element ele_postContent = getElementByIndex((getElementByIndex(ele_resultbgxx, "tr",i)), "td",2);//变更后内容
								aicpubRegChangeInfo.setPostContent(getElementText(ele_postContent));
								Element ele_dateTime = getElementByIndex((getElementByIndex(ele_resultbgxx, "tr",i)), "td",3);//变更日期
								aicpubRegChangeInfo.setDateTime(getElementText(ele_dateTime));
								changeInfos.add(aicpubRegChangeInfo);
							}
						}
					}
				}
			}
		}
		if(resultHtmlMap.get("gsgsxx_bgxx")!=null && isDebug){
			AicpubRegChangeInfo aicpubRegChangeInfo=new AicpubRegChangeInfo();
			aicpubRegChangeInfo.setHtml(resultHtmlMap.get("gsgsxx_bgxx").toString());
			changeInfos.add(aicpubRegChangeInfo);
		}
//		changeInfos.add(aicpubRegChangeInfo);
		aicpubRegInfo.setChangeInfos(changeInfos);
		
		aicpubInfo.setRegInfo(aicpubRegInfo);//登记信息
		//登记信息********************结束
		
		//备案信息********************开始
		AicpubArchiveInfo archiveInfo=new AicpubArchiveInfo();
		//主要人员信息
		List<AicpubArchivePrimemberInfo> priMemberInfos=new ArrayList<AicpubArchivePrimemberInfo>();
		if(resultHtmlMap.get("gsgsxx_zyryxx")!=null){
			Elements eleszyryxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_zyryxx").toString()).select("table");
			if(eleszyryxx==null || eleszyryxx.isEmpty()) {
				priMemberInfos=null;
			}else{
				Element ele_resultzyryxx=eleszyryxx.get(0);//获取第一个table
				Elements tr_resultszyryxx = getElements(ele_resultzyryxx, "tr");
				if (tr_resultszyryxx==null || tr_resultszyryxx.isEmpty()) {
					priMemberInfos=null;
				}else{
					for(int i=0;i<(tr_resultszyryxx.size()-2);i++){
						AicpubArchivePrimemberInfo priMemberInfo=new AicpubArchivePrimemberInfo();
						Element ele_name1 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i+2)), "td",1);
						priMemberInfo.setName(getElementText(ele_name1));//姓名
						Element ele_position1 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i+2)), "td",2);
						priMemberInfo.setPosition(getElementText(ele_position1));//职务
						priMemberInfos.add(priMemberInfo);
						if((getElementByIndex(ele_resultzyryxx, "tr",i+2).children()).size()>3){
							AicpubArchivePrimemberInfo priMemberInfo2=new AicpubArchivePrimemberInfo();
							Element ele_name2 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i+2)), "td",4);
							priMemberInfo2.setName(getElementText(ele_name2));//姓名
							Element ele_position2 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i+2)), "td",5);
							priMemberInfo2.setPosition(getElementText(ele_position2));//职务
							priMemberInfos.add(priMemberInfo2);
						}
					}
				}
			}
		}
		
		archiveInfo.setPriMemberInfos(priMemberInfos);
		
		
		//分支机构信息
		List<AicpubArchiveBranchInfo> branchInfos=new ArrayList<AicpubArchiveBranchInfo>();
		if(resultHtmlMap.get("gsgsxx_fzjgxx")!=null){
			Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_fzjgxx").toString()).select("table");
			if(elesGdxx==null || elesGdxx.isEmpty()) {
//				changeInfos=null;
			}else{
				if(elesGdxx.size()>0){
				Element ele_resultGdxx=elesGdxx.get(0);//获取第一个table
				Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
				if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//					changeInfos=null;
				}else{
                  if(tr_resultsGdxx.size()>2 && getElementByIndex(ele_resultGdxx, "tr",2)!=null && getElements(tr_resultsGdxx.get(2), "td").size()==4){
                		for(int i=0;i<tr_resultsGdxx.size();i++){
							Elements td_resultsGdxx = getElements(tr_resultsGdxx.get(i), "td");
							if(td_resultsGdxx!=null && td_resultsGdxx.size()==4){
								AicpubArchiveBranchInfo aicpubArchiveBranchInfo=new AicpubArchiveBranchInfo();
								Element ele_item = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);//统一社会信用代码/注册号
								aicpubArchiveBranchInfo.setNum(getElementText(ele_item));
								Element ele_preContent = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);//名称
								aicpubArchiveBranchInfo.setName(getElementText(ele_preContent));
								Element ele_postContent = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);
								aicpubArchiveBranchInfo.setRegAuthority(getElementText(ele_postContent));
								branchInfos.add(aicpubArchiveBranchInfo);
							}
					}
					}
					
				}
			}
				
			}
		}
		
		archiveInfo.setBranchInfos(branchInfos);
		//清算信息
		AicpubArchiveClearInfo clearInfo=new AicpubArchiveClearInfo();
			if(resultHtmlMap.get("gsgsxx_qsxx")!=null && isDebug){
				clearInfo.setHtml(resultHtmlMap.get("gsgsxx_qsxx").toString());
		}
		archiveInfo.setClearInfo(clearInfo);

		aicpubInfo.setArchiveInfo(archiveInfo);//备案信息
		//备案信息********************结束
		
		
		//股权出质登记信息********************开始
		AicpubEqumortgregInfo equMortgRegInfo=new AicpubEqumortgregInfo();
			if(resultHtmlMap.get("gsgsxx_gqczdjxx")!=null && isDebug){
		equMortgRegInfo.setHtml(resultHtmlMap.get("gsgsxx_gqczdjxx").toString());
		}
		aicpubInfo.setEquMortgRegInfo(equMortgRegInfo); 
		//股权出质登记信息********************结束
		
		
		//动产抵押登记信息********************开始
		AicpubChatMortgInfo aicpubChatMortgInfo=new AicpubChatMortgInfo();
			if(resultHtmlMap.get("gsgsxx_dcdydjxx")!=null && isDebug){
		aicpubChatMortgInfo.setHtml(resultHtmlMap.get("gsgsxx_dcdydjxx").toString());
		}
		aicpubInfo.setChatMortgInfo(aicpubChatMortgInfo); //AicpubChatMortgInfo
		//动产抵押登记信息********************结束
		
		
		//经营异常信息********************开始
		AicpubOperanomaInfo aicpubOperanomaInfo=new AicpubOperanomaInfo();
		if(resultHtmlMap.get("gsgsxx_jyycxx")!=null){
			List<AicpubOOperanomaInfo> operAnomaInfos=new ArrayList<AicpubOOperanomaInfo>();
			Elements elesjyycxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_jyycxx").toString()).select("table");
			if(elesjyycxx==null || elesjyycxx.isEmpty()) {
				operAnomaInfos=null;
			}else{
				Element ele_resultjyycxx=elesjyycxx.get(0);
				Elements tr_resultsjyycxx = getElements(ele_resultjyycxx, "tr");
				if (tr_resultsjyycxx==null || tr_resultsjyycxx.isEmpty()) {
					operAnomaInfos=null;
				}else{
					for(int i=0;i<(tr_resultsjyycxx.size()-3);i++){
						AicpubOOperanomaInfo aicpubOOperanomaInfo=new AicpubOOperanomaInfo();
						Element ele_includeCause = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i+2)), "td",1);//列入经营异常名录原因
						aicpubOOperanomaInfo.setIncludeCause(getElementText(ele_includeCause));
						Element ele_includeDateTime = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i+2)), "td",2);//列入日期
						aicpubOOperanomaInfo.setIncludeDateTime(getElementText(ele_includeDateTime));
						Element ele_removeCause = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i+2)), "td",3);//移出经营异常名录原因
						aicpubOOperanomaInfo.setRemoveCause(getElementText(ele_removeCause));
						Element ele_removeDateTime = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i+2)), "td",4);//移出日期
						aicpubOOperanomaInfo.setRemoveDateTime(getElementText(ele_removeDateTime));
						Element ele_removeAuthority = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i+2)), "td",5);//作出决定机关(移出)
						aicpubOOperanomaInfo.setRemoveAuthority(getElementText(ele_removeAuthority));
						operAnomaInfos.add(aicpubOOperanomaInfo);
					}
				}
				aicpubOperanomaInfo.setOperAnomaInfos(operAnomaInfos);
			}
		}
		
		if(resultHtmlMap.get("gsgsxx_jyycxx")!=null && isDebug){
		aicpubOperanomaInfo.setHtml(resultHtmlMap.get("gsgsxx_jyycxx").toString());
		}
		aicpubInfo.setOperAnomaInfo(aicpubOperanomaInfo);//增加经营异常信息 AicpubOperanomaInfo
		//经营异常信息********************结束
		
		//严重违法信息********************开始
		AicpubSerillegalInfo serIllegalInfo=new AicpubSerillegalInfo();
			if(resultHtmlMap.get("gsgsxx_yzwfxx")!=null && isDebug){
		serIllegalInfo.setHtml(resultHtmlMap.get("gsgsxx_yzwfxx").toString());
		}
		aicpubInfo.setSerIllegalInfo(serIllegalInfo);//严重违法信息
		//严重违法信息********************结束
				
		//行政处罚信息********************开始
		AicpubAdmpunishInfo aicpubAdmpunishInfo=new AicpubAdmpunishInfo();
			if(resultHtmlMap.get("gsgsxx_xzcfxx")!=null && isDebug){
		aicpubAdmpunishInfo.setHtml(resultHtmlMap.get("gsgsxx_xzcfxx").toString());
		}
		aicpubInfo.setAdmPunishInfo(aicpubAdmpunishInfo);// AicpubAdmpunishInfo
		//行政处罚信息********************结束
		
		
		//抽查检查信息********************开始
		AicpubCheckInfo aicpubCheckInfo=new AicpubCheckInfo();
		if(resultHtmlMap.get("gsgsxx_ccjcxx")!=null){
			List<AicpubCCheckInfo> checkInfos=new ArrayList<AicpubCCheckInfo>();
			Elements elesccjcxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_ccjcxx").toString()).select("table");
			if(elesccjcxx==null || elesccjcxx.isEmpty()) {
				checkInfos=null;
			}else{
				Element ele_resultccjcxx=elesccjcxx.get(0);
				Elements tr_resultsccjcxx = getElements(ele_resultccjcxx, "tr");
				if (tr_resultsccjcxx==null || tr_resultsccjcxx.isEmpty()) {
					checkInfos=null;
				}else{
					for(int i=0;i<(tr_resultsccjcxx.size()-3);i++){
						AicpubCCheckInfo aicpubCCheckInfo=new AicpubCCheckInfo();
						Element ele_checkImplAuthority = getElementByIndex((getElementByIndex(ele_resultccjcxx, "tr",i+2)), "td",1);//检查实施机关
						aicpubCCheckInfo.setCheckImplAuthority(getElementText(ele_checkImplAuthority));
						Element ele_type = getElementByIndex((getElementByIndex(ele_resultccjcxx, "tr",i+2)), "td",2);//类型
						aicpubCCheckInfo.setType(getElementText(ele_type));
						Element ele_dateTime = getElementByIndex((getElementByIndex(ele_resultccjcxx, "tr",i+2)), "td",3);//日期
						aicpubCCheckInfo.setDateTime(getElementText(ele_dateTime));
						Element ele_result = getElementByIndex((getElementByIndex(ele_resultccjcxx, "tr",i+2)), "td",4);//结果
						aicpubCCheckInfo.setResult(getElementText(ele_result));
						checkInfos.add(aicpubCCheckInfo);
					}
				}
				aicpubCheckInfo.setCheckInfos(checkInfos);
			}
		}
		if(resultHtmlMap.get("gsgsxx_ccjcxx")!=null && isDebug){
		aicpubCheckInfo.setHtml(resultHtmlMap.get("gsgsxx_ccjcxx").toString()); 
		}
		aicpubInfo.setCheckInfo(aicpubCheckInfo);// AicpubCheckInfo
		//抽查检查信息********************结束
		//********************工商公示信息 结束********************
		
		//********************企业公示信息 开始********************
		//企业公示信息
		EntpubInfo entpubInfo=new EntpubInfo();
		//企业年报
		List<EntpubAnnreportInfo> entpubAnnreportInfos=new ArrayList<EntpubAnnreportInfo>();;
		if(resultHtmlMap.get("qygsxx_qynb")!=null){
			Elements elesqynb = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_qynb").toString()).select("table");
			if(elesqynb==null || elesqynb.isEmpty()) {
//				entpubAnnreportInfos=null;
			}else{
				Element ele_resultqynb=elesqynb.get(0);
				Elements tr_resultsqynb = getElements(ele_resultqynb, "tr");
				if (tr_resultsqynb==null || tr_resultsqynb.isEmpty()) {
//					entpubAnnreportInfos=null;
				}else{
					for(int i=0;i<(tr_resultsqynb.size()-2);i++){
						EntpubAnnreportInfo annReports=new EntpubAnnreportInfo();
						Element ele_submitYear = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i+2)), "td",1);
						annReports.setSubmitYear(getElementText(ele_submitYear));//报送年度
						Element ele_pubDate = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i+2)), "td",2);
						annReports.setPubDateTime(getElementText(ele_pubDate));//发布日期
						
						if(resultHtmlMap.get("qygsxx_qynb_detail")!=null){
							@SuppressWarnings("unchecked")
							List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("qygsxx_qynb_detail");
							if(detailMap.size()!=0 && (detailMap.size()-1)>=i){
							Elements tables = Jsoup.parseBodyFragment(detailMap.get(i).toString()).select("table");
							if(tables==null || tables.isEmpty()){}
							else{
								for (Element qygsxxQynb13Table : tables) {
									String qygsxxQynbTitle = qygsxxQynb13Table.select("th").get(0).text();
									
									if (qygsxxQynbTitle.indexOf("年度报告") > 0) {
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
												case "是否有网站或网点":
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
												case "隶属关系":
													qygsQynbJbInfo.setAffiliation(qygsxxQynb13Td);
													break;
												default:
													break;
											}
										}
										
										annReports.setBaseInfo(qygsQynbJbInfo);
										
									} else if ("企业资产状况信息".equals(qygsxxQynbTitle) || "生产经营情况".equals(qygsxxQynbTitle) || "资产状况信息".equals(qygsxxQynbTitle)) {
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
										
									} else if (qygsxxQynbTitle.contains("股东及出资信息") || qygsxxQynbTitle.contains("股东及出资信息（币种与注册资本一致）")) {
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
									}
									
								}
								
							}
						 }
						}
						
						if(resultHtmlMap.get("qygsxx_qynb")!=null && isDebug){
							annReports.setHtml(resultHtmlMap.get("qygsxx_qynb_detail").toString());
						}
						entpubAnnreportInfos.add(annReports);
					}
				}
				
			}
		}
		
		entpubInfo.setAnnReports(entpubAnnreportInfos);
		
		//股东及出资信息
		EntpubStohrinvestInfo stohrInvestInfo=new EntpubStohrinvestInfo();
		
		if(resultHtmlMap.get("qygsxx_gdjczxx")!=null){
			Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_gdjczxx").toString()).select("table");
			if(elesGdxx==null || elesGdxx.isEmpty()) {
//				changeInfos=null;
			}else{
				if(elesGdxx.size()>0){
				Element ele_resultGdxx=elesGdxx.get(0);//获取第一个table
				Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
				if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//					changeInfos=null;
				}else{
                  if(tr_resultsGdxx.size()>3 && getElementByIndex(ele_resultGdxx, "tr",3)!=null && getElements(tr_resultsGdxx.get(3), "td").size()==9){
                	  List<EntpubSStohrinvestInfo> stohrInvestInfos=new ArrayList<EntpubSStohrinvestInfo>();
                		for(int i=3;i<tr_resultsGdxx.size();i++){
							if(getElements(tr_resultsGdxx.get(i), "td")!=null && getElements(tr_resultsGdxx.get(i), "td").size()==9){
								EntpubSStohrinvestInfo entpubSStohrinvestInfo=new EntpubSStohrinvestInfo();
								List<Detail> subDetails=new ArrayList<Detail>();
								List<Detail> paidDetails=new ArrayList<Detail>();
								
								Element ele1 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",0);
								entpubSStohrinvestInfo.setStockholder(getElementText(ele1));//股东
								Element ele2 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);
								entpubSStohrinvestInfo.setSubAmount(getElementText(ele2));//认缴额（万元）
								Element ele3 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);
								entpubSStohrinvestInfo.setPaidAmount(getElementText(ele3));//实缴额（万元）
								
								EntpubSStohrinvestInfo.Detail detail1=entpubSStohrinvestInfo.new Detail();
								Element ele4 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);
								detail1.setMethod(getElementText(ele4));//出资方式
								Element ele5 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",4);
								detail1.setAmount(getElementText(ele5));//出资额（万元）
								Element ele6 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",5);
								detail1.setDateTime(getElementText(ele6));//出资日期
								subDetails.add(detail1);
								
                                EntpubSStohrinvestInfo.Detail detail2=entpubSStohrinvestInfo.new Detail();
                                Element ele7 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",6);
                                detail2.setMethod(getElementText(ele7));//出资方式
                                Element ele8 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",7);
                                detail2.setAmount(getElementText(ele8));//出资额（万元）
                                Element ele9 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",8);
                                detail2.setDateTime(getElementText(ele9));//出资日期
                                paidDetails.add(detail2);
                                
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
		entpubInfo.setStohrInvestInfo(stohrInvestInfo);
		
		//股权变更信息
		EntpubEquchangeInfo equChangeInfo=new EntpubEquchangeInfo();
		if(resultHtmlMap.get("qygsxx_gqbgxx")!=null){
			Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_gqbgxx").toString()).select("table");
			if(elesGdxx==null || elesGdxx.isEmpty()) {
//				changeInfos=null;
			}else{
				if(elesGdxx.size()>0){
				Element ele_resultGdxx=elesGdxx.get(0);//获取第一个table
				Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
				if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//					changeInfos=null;
				}else{
                  if(tr_resultsGdxx.size()>2 && getElementByIndex(ele_resultGdxx, "tr",2)!=null && getElements(tr_resultsGdxx.get(2), "td").size()==5){
                	  List<EntpubEEquchangeInfo> equChangeInfos=new ArrayList<EntpubEEquchangeInfo>();
                		for(int i=2;i<tr_resultsGdxx.size();i++){
							if(getElements(tr_resultsGdxx.get(i), "td")!=null && getElements(tr_resultsGdxx.get(i), "td").size()==5){
								EntpubEEquchangeInfo entpubEEquchangeInfo=new EntpubEEquchangeInfo();
								Element ele1 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);//股东
								entpubEEquchangeInfo.setStockholder(getElementText(ele1));
								Element ele2 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);//变更前股权比例
								entpubEEquchangeInfo.setPreOwnershipRatio(getElementText(ele2));
								Element ele3 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);//变更后股权比例
								entpubEEquchangeInfo.setPostOwnershipRatio(getElementText(ele3));
								Element ele4 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",4);//股权变更日期
								entpubEEquchangeInfo.setDateTime(getElementText(ele4));
								equChangeInfos.add(entpubEEquchangeInfo);
							}
					}
                		equChangeInfo.setEquChangeInfos(equChangeInfos);
					}
					
				}
			}
				
			}
		}
		
		if(resultHtmlMap.get("qygsxx_gqbgxx")!=null && isDebug){
		equChangeInfo.setHtml(resultHtmlMap.get("qygsxx_gqbgxx").toString());
		}
		entpubInfo.setEquChangeInfo(equChangeInfo);
		
		//行政许可信息
		EntpubAdmlicInfo admLicInfo=new EntpubAdmlicInfo();
		if(resultHtmlMap.get("qygsxx_xzxkxx")!=null){
			Elements elesxzxkxx = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_xzxkxx").toString()).select("table");
			if(elesxzxkxx==null || elesxzxkxx.isEmpty()) {
//				admLicInfo=null;
			}else{
				Element ele_resultxzxkxx=elesxzxkxx.get(0);
				Elements tr_resultsxzxkxx = getElements(ele_resultxzxkxx, "tr");
				if (tr_resultsxzxkxx==null || tr_resultsxzxkxx.isEmpty()) {
//					admLicInfo=null;
				}else{
					if(tr_resultsxzxkxx.size()>2 && getElementByIndex(ele_resultxzxkxx, "tr",2)!=null && getElements(tr_resultsxzxkxx.get(2), "td").size()==9){
						List<EntpubAAdmlicInfo> admlicInfos=new ArrayList<EntpubAAdmlicInfo>();
						for(int i=0;i<(tr_resultsxzxkxx.size()-2);i++){
							EntpubAAdmlicInfo entpubAAdmlicInfo=new EntpubAAdmlicInfo();
							Element ele_licenceNum = getElementByIndex((getElementByIndex(ele_resultxzxkxx, "tr",i+2)), "td",1);//许可文件编号
							entpubAAdmlicInfo.setLicenceNum(getElementText(ele_licenceNum));
							Element ele_licenceName = getElementByIndex((getElementByIndex(ele_resultxzxkxx, "tr",i+2)), "td",2);//许可文件名称
							entpubAAdmlicInfo.setLicenceName(getElementText(ele_licenceName));
							Element ele_startDateTime = getElementByIndex((getElementByIndex(ele_resultxzxkxx, "tr",i+2)), "td",3);//有效期自
							entpubAAdmlicInfo.setStartDateTime(getElementText(ele_startDateTime));
							Element ele_endDateTime = getElementByIndex((getElementByIndex(ele_resultxzxkxx, "tr",i+2)), "td",4);//有效期至
							entpubAAdmlicInfo.setEndDateTime(getElementText(ele_endDateTime));
							Element ele_deciAuthority = getElementByIndex((getElementByIndex(ele_resultxzxkxx, "tr",i+2)), "td",5);//许可机关
							entpubAAdmlicInfo.setDeciAuthority(getElementText(ele_deciAuthority));
							Element ele_content = getElementByIndex((getElementByIndex(ele_resultxzxkxx, "tr",i+2)), "td",6);//许可内容
							entpubAAdmlicInfo.setContent(getElementText(ele_content));
							Element ele_status = getElementByIndex((getElementByIndex(ele_resultxzxkxx, "tr",i+2)), "td",7);//状态
							entpubAAdmlicInfo.setStatus(getElementText(ele_status));
							admlicInfos.add(entpubAAdmlicInfo);
						}
						admLicInfo.setAdmlicInfos(admlicInfos);
					}
					
				}
			}
		}
		
		if(resultHtmlMap.get("qygsxx_xzxkxx")!=null && isDebug){
		admLicInfo.setHtml(resultHtmlMap.get("qygsxx_xzxkxx").toString());
		}
		entpubInfo.setAdmLicInfo(admLicInfo);
		
		//知识产权出质登记信息
		EntpubIntellectualproregInfo intellectualProRegInfo=new EntpubIntellectualproregInfo();
			if(resultHtmlMap.get("qygsxx_zscqczdj")!=null && isDebug){
		intellectualProRegInfo.setHtml(resultHtmlMap.get("qygsxx_zscqczdj").toString());
		}
		entpubInfo.setIntellectualProRegInfo(intellectualProRegInfo);
		
		//行政处罚信息
		EntpubAdmpunishInfo admPunishInfo=new EntpubAdmpunishInfo();
			if(resultHtmlMap.get("qygsxx_xzcf")!=null && isDebug){
		admPunishInfo.setHtml(resultHtmlMap.get("qygsxx_xzcf").toString());
		}
		entpubInfo.setAdmPunishInfo(admPunishInfo);
		//********************企业公示信息 结束********************
		
		//********************其他部门公示信息 开始********************
		//其他部门公示信息
		OthrdeptpubInfo othrdeptpubInfo=new OthrdeptpubInfo();
		//行政许可信息********************开始
		//行政许可信息
		OthrdeptpubAdmlicInfo othrdeptpubAdmlicInfo=new OthrdeptpubAdmlicInfo();
			if(resultHtmlMap.get("qtbmgsxx_xzxk")!=null && isDebug){
		othrdeptpubAdmlicInfo.setHtml(resultHtmlMap.get("qtbmgsxx_xzxk").toString());
		}
		othrdeptpubInfo.setAdmLicInfo(othrdeptpubAdmlicInfo);
		//行政许可信息********************结束
		
		//行政处罚信息********************开始
		//行政处罚信息
		OthrdeptpubAdmpunishInfo othrdeptpubAdmpunishInfo=new OthrdeptpubAdmpunishInfo();
			if(resultHtmlMap.get("qtbmgsxx_xzcf")!=null && isDebug){
		othrdeptpubAdmpunishInfo.setHtml(resultHtmlMap.get("qtbmgsxx_xzcf").toString());
		}
		othrdeptpubInfo.setAdmPunishInfo(othrdeptpubAdmpunishInfo);
		//行政处罚信息********************结束
		//********************其他部门公示信息 结束********************
		
		//********************司法协助公示信息 开始********************
		//司法协助公示信息
//		JudasspubInfo judAssPubInfo=new JudasspubInfo();
		
		//股权冻结信息
//		JudasspubEqufreezeInfo equFreezeInfo=new JudasspubEqufreezeInfo();
//			if(resultHtmlMap.get("sfxzgsxx_gqdj")!=null && isDebug){
//		equFreezeInfo.setHtml(resultHtmlMap.get("sfxzgsxx_gqdj").toString());
//		}
//		judAssPubInfo.setEquFreezeInfo(equFreezeInfo);
		
		JudasspubInfo judAssPubInfo=new JudasspubInfo();
		JudasspubEqufreezeInfo equFreezeInfo=new JudasspubEqufreezeInfo();
		if(resultHtmlMap.get("sfxzgsxx_gqdj")!=null && isDebug){
			Elements eles = Jsoup.parseBodyFragment(resultHtmlMap.get("sfxzgsxx_gqdj").toString()).select("table");	
				Element ele_resultGdxx=eles.get(0);//获取第一个table
				Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
				if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//					changeInfos=null;
				}else{
                  if(tr_resultsGdxx.size()>2){
                	  List<JudasspubEEqufreezeInfo> equFreezeInfos=new ArrayList<JudasspubEEqufreezeInfo>();
                		for(int i=2;i<tr_resultsGdxx.size();i++){
							if(getElements(tr_resultsGdxx.get(i), "td")!=null && getElements(tr_resultsGdxx.get(i), "td").size()==7){
								JudasspubEEqufreezeInfo judasspubEEqufreezeInfo=new JudasspubEEqufreezeInfo();
								Element ele1 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);
								judasspubEEqufreezeInfo.setExecutedPerson(getElementText(ele1));//被执行人
								Element ele2 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);
								judasspubEEqufreezeInfo.setEquAmount(getElementText(ele2));//股权数额
								Element ele3 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);
								judasspubEEqufreezeInfo.setExeCourt(getElementText(ele3));//执行法院
								Element ele4 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",4);
								judasspubEEqufreezeInfo.setAssistPubNoticeNum(getElementText(ele4));//协助公示通知书文号
								Element ele5 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",5);
								judasspubEEqufreezeInfo.setStatus(getElementText(ele5));//状态
								equFreezeInfos.add(judasspubEEqufreezeInfo);
							}
					}
                		equFreezeInfo.setEquFreezeInfos(equFreezeInfos);
					}
					
				
			}
				
			
		}
		judAssPubInfo.setEquFreezeInfo(equFreezeInfo);
		
		//股东变更信息
		JudasspubStohrchangeInfo stohrChangeInfo=new JudasspubStohrchangeInfo();
		if(resultHtmlMap.get("sfxzgsxx_sfgdbgdj")!=null && isDebug){
		stohrChangeInfo.setHtml(resultHtmlMap.get("sfxzgsxx_sfgdbgdj").toString());
		}
		judAssPubInfo.setStohrChangeInfo(stohrChangeInfo);
		
		//********************司法协助公示信息 结束********************
		
		aicFeedJson.setAicPubInfo(aicpubInfo);//增加工商公示信息   AicpubInfo
		aicFeedJson.setEntPubInfo(entpubInfo);//增加企业公示信息  EntpubInfo
		aicFeedJson.setOthrDeptPubInfo(othrdeptpubInfo);//其他部门信息  OthrdeptpubInfo
		aicFeedJson.setJudAssPubInfo(judAssPubInfo);//增加司法协助公示信息
		}
	    return aicFeedJson;
	}
}

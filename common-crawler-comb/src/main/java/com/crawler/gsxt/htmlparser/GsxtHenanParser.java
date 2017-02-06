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
import com.crawler.gsxt.domain.json.AicpubArchiveInfo;
import com.crawler.gsxt.domain.json.AicpubArchiveMainDeptInfo;
import com.crawler.gsxt.domain.json.AicpubArchivePrimemberInfo;
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
import com.crawler.gsxt.domain.json.EntpubAnnreportAdmlicenseInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAssetInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportBaseInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportModifyInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportWebsiteInfo;
import com.crawler.gsxt.domain.json.EntpubEEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubInfo;
import com.crawler.gsxt.domain.json.JudasspubEEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtHenanParser extends AbstractGsxtParser {

	private static final Logger log = LoggerFactory
			.getLogger(GsxtHenanParser.class);

	public AicFeedJson henanParser(String html, boolean isDebug) {
		AicFeedJson aicFeedJson = new AicFeedJson();
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
		
		Elements elesDjxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_djxx").toString()).select("div#jibenxinxi");
		if(elesDjxx==null || elesDjxx.isEmpty()) {
			aicpubRegBaseInfo=null;
		}
		
		Element eleDiv = elesDjxx.get(0);
		Elements ele_resultsTable = getElements(eleDiv, "table.detailsList");
		if (ele_resultsTable==null || ele_resultsTable.isEmpty()) {
			aicpubRegBaseInfo=null;
		}
		
		Element ele_resultJbxx=ele_resultsTable.get(0);//获取第一个table（基本信息）
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
					aicpubRegBaseInfo.setCreditNum(gsgsxxDjjbTd);
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
				case "经营者":
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
				case "注册日期":
					aicpubRegBaseInfo.setRegDateTime(gsgsxxDjjbTd);
					break;
				case "组成形式":
					aicpubRegBaseInfo.setFormType(gsgsxxDjjbTd);
					break;
				default:
					break;
				}
			}
		}
		aicpubRegInfo.setBaseInfo(aicpubRegBaseInfo);//AicpubRegBaseInfo

		//股东信息
				List<AicpubRegStohrInfo> stohrInfos=new ArrayList<AicpubRegStohrInfo>();
				Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_djxx").toString()).select("div#jibenxinxi").select("div#invDiv").select("table.detailsList");
				if(elesGdxx==null || elesGdxx.isEmpty()) {
					stohrInfos=null;
				}else{
					Element ele_resultGdxx=elesGdxx.get(0);//获取第一个table
					Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
					if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
						stohrInfos=null;
					}else{
						Elements td_resultsGdxx = getElements(tr_resultsGdxx.get(0), "td");
						if(td_resultsGdxx.size()>=4){
							for(int i=0;i<tr_resultsGdxx.size();i++){
								AicpubRegStohrInfo aicpubRegStohrInfo=new AicpubRegStohrInfo();
								Element ele_Type = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);
								aicpubRegStohrInfo.setType(getElementText(ele_Type));//股东类型
								Element ele_Name = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",0);
								aicpubRegStohrInfo.setName(getElementText(ele_Name));//股东名称
								Element ele_IdType = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);
								aicpubRegStohrInfo.setIdType(getElementText(ele_IdType));//证照/证件类型
								Element ele_IdNum = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);
								aicpubRegStohrInfo.setIdNum(getElementText(ele_IdNum));//证照/证件号码
								
								
								//股东详情
								if(resultHtmlMap.get("gsgsxx_gdxx_detail")!=null){
									@SuppressWarnings("unchecked")
									List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_gdxx_detail");
									if((detailMap.size()-1)>=i){
										Elements tables = Jsoup.parseBodyFragment(detailMap.get(i).toString()).select("div#details").select("table");
										if(tables==null || tables.isEmpty()){}
										else{
											//股东及出资信息 
											Element ele_resultGdjczxx=tables.get(0);//获取第一个table（股东及出资信息 ）
											Elements tr_resultsGdjczxx = getElements(ele_resultGdjczxx, "tr");
											if (tr_resultsGdjczxx==null || tr_resultsGdjczxx.isEmpty()) {
//												aicpubRegStohrStohrinvestInfo=null;
											}else{
												if(getElementByIndex(ele_resultGdjczxx, "tr",3)!=null){
													 AicpubRegStohrStohrinvestInfo aicpubRegStohrStohrinvestInfo1=new AicpubRegStohrStohrinvestInfo();
														Element ele_stockholder = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",0);
														aicpubRegStohrStohrinvestInfo1.setStockholder(getElementText(ele_stockholder));//股东
														Element ele_subAmount = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",1);
														aicpubRegStohrStohrinvestInfo1.setSubAmount(getElementText(ele_subAmount));//认缴额（万元）
														Element ele_paidAmount = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",2);
														aicpubRegStohrStohrinvestInfo1.setPaidAmount(getElementText(ele_paidAmount));//实缴额（万元）
														if(tr_resultsGdjczxx.size()>=4){
														List<AmountDetail> subAmountDetails=new ArrayList<AmountDetail>();//认缴明细
														AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail1=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
														Element ele_method1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",3);
														amountDetail1.investMethod=getElementText(ele_method1);//出资方式
														Element ele_amount1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",4);
														amountDetail1.investAmount=getElementText(ele_amount1);//出资额（万元）
														Element ele_czDate1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",5);
														amountDetail1.investDateTime=getElementText(ele_czDate1);//出资日期
														subAmountDetails.add(amountDetail1);
														aicpubRegStohrStohrinvestInfo1.setSubAmountDetails(subAmountDetails);
														List<AmountDetail> paidAmountDetails=new ArrayList<AmountDetail>();//实缴明细
														AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail2=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
														Element ele_method2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",6);
														amountDetail2.investMethod=getElementText(ele_method2);//出资方式
														Element ele_amount2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",7);
														amountDetail2.investAmount=getElementText(ele_amount2);//出资额（万元）
														Element ele_czDate2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",8);
														amountDetail2.investDateTime=getElementText(ele_czDate2);//出资日期
														paidAmountDetails.add(amountDetail2);
														aicpubRegStohrStohrinvestInfo1.setPaidAmountDetails(paidAmountDetails);
												         }
														aicpubRegStohrInfo.setStohrInvestInfo(aicpubRegStohrStohrinvestInfo1);//AicpubRegStohrStohrinvestInfo
												}
												   
											}
											}
										}
								}
								
								stohrInfos.add(aicpubRegStohrInfo);
								
							}
						}
			           if(td_resultsGdxx.size()==2){
			        	   for(int i=0;i<tr_resultsGdxx.size();i++){
			        		   AicpubRegStohrInfo aicpubRegStohrInfo=new AicpubRegStohrInfo();
			            	   Element ele_Name = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",0);
			    				aicpubRegStohrInfo.setName(getElementText(ele_Name));//股东名称
			    				Element ele_Sconform = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);
			    				aicpubRegStohrInfo.setSconform(getElementText(ele_Sconform));//出资方式
			        	   }
						}
					}
				
					
				}
				aicpubRegInfo.setStohrInfos(stohrInfos);
				
		//变更信息（包括变更信息详细）
		List<AicpubRegChangeInfo> changeInfos=new ArrayList<AicpubRegChangeInfo>();
		Elements elesBgxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_djxx").toString()).select("div#jibenxinxi").select("div#altDiv").select("table.detailsList");
		if(elesBgxx==null || elesBgxx.isEmpty()) {
			changeInfos=null;
		}else{
			Element ele_resultBgxx=elesBgxx.get(0);//获取第一个table
			Elements tr_resultsBgxx = getElements(ele_resultBgxx, "tr");
			if (tr_resultsBgxx==null || tr_resultsBgxx.isEmpty()) {
				changeInfos=null;
			}else{
				Elements td_resultsGdxx = getElements(tr_resultsBgxx.get(0), "td");
				if(td_resultsGdxx.size()==4){
					for(int i=0;i<tr_resultsBgxx.size();i++){
						AicpubRegChangeInfo aicpubRegChangeInfo=new AicpubRegChangeInfo();
						Element ele_item = getElementByIndex((getElementByIndex(ele_resultBgxx, "tr",i)), "td",0);//变更事项
						if(!getElementText(ele_item).equals("暂无数据") || ele_item!=null){
						aicpubRegChangeInfo.setItem(getElementText(ele_item));
						Element ele_preContent = getElementByIndex((getElementByIndex(ele_resultBgxx, "tr",i)), "td",1);//变更前内容
						aicpubRegChangeInfo.setPreContent(getElementText(ele_preContent));
						Element ele_postContent = getElementByIndex((getElementByIndex(ele_resultBgxx, "tr",i)), "td",2);//变更后内容
						aicpubRegChangeInfo.setPostContent(getElementText(ele_postContent));
						Element ele_dateTime = getElementByIndex((getElementByIndex(ele_resultBgxx, "tr",i)), "td",3);//变更日期
						aicpubRegChangeInfo.setDateTime(getElementText(ele_dateTime));
						changeInfos.add(aicpubRegChangeInfo);
						}
					  }
				}
				
			}
		}
		aicpubRegInfo.setChangeInfos(changeInfos);//AicpubRegChangeInfo
		aicpubInfo.setRegInfo(aicpubRegInfo);//增加登记信息 AicpubRegInfo
		//登记信息********************结束
		//备案信息********************开始
		AicpubArchiveInfo aicpubArchiveInfo=new AicpubArchiveInfo();
		//主要人员信息
		List<AicpubArchivePrimemberInfo> priMemberInfos=new ArrayList<AicpubArchivePrimemberInfo>();
		//主管部门（出资人）信息
		List<AicpubArchiveMainDeptInfo> mainDeptInfo=new ArrayList<AicpubArchiveMainDeptInfo>();
		if(resultHtmlMap.get("gsgsxx_baxx")!=null){
			Elements eleszyryxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_baxx").toString()).select("table");
			if(eleszyryxx==null || eleszyryxx.isEmpty()) {
				priMemberInfos=null;
				mainDeptInfo=null;
			}else{
				//主管部门（出资人）信息
				String titleString="主要人员信息";
				Element ele_resultzyryxx1=eleszyryxx.get(0);
				if(getElementByIndex(ele_resultzyryxx1, "tr",0)!=null){
					Element ele_title = getElementByIndex((getElementByIndex(ele_resultzyryxx1, "tr",0)), "th",0);
					if(ele_title!=null){
						titleString=getElementText(ele_title);
					}
				}
				if(titleString.equals("主要人员信息")){
					//主要人员信息
					Element ele_resultzyryxx=eleszyryxx.get(1);
					Elements tr_resultszyryxx = getElements(ele_resultzyryxx, "tr");
					if (tr_resultszyryxx==null || tr_resultszyryxx.isEmpty()) {
						priMemberInfos=null;
					}else{
						Elements td_resultsGdxx = getElements(tr_resultszyryxx.get(0), "td");
						if(td_resultsGdxx.size()==6){
						for(int i=0;i<tr_resultszyryxx.size();i++){
							AicpubArchivePrimemberInfo priMemberInfo=new AicpubArchivePrimemberInfo();
							Element ele_name1 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",1);
							priMemberInfo.setName(getElementText(ele_name1));//姓名
							Element ele_position1 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",2);
							priMemberInfo.setPosition(getElementText(ele_position1));//职务
							priMemberInfos.add(priMemberInfo);
							if((getElementByIndex(ele_resultzyryxx, "tr",i).children()).size()>3){
								AicpubArchivePrimemberInfo priMemberInfo2=new AicpubArchivePrimemberInfo();
								Element ele_name2 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",4);
								priMemberInfo2.setName(getElementText(ele_name2));//姓名
								Element ele_position2 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",5);
								priMemberInfo2.setPosition(getElementText(ele_position2));//职务
								priMemberInfos.add(priMemberInfo2);
							}
						}
						}
					}
					aicpubArchiveInfo.setPriMemberInfos(priMemberInfos);
				}else{
					//主管部门（出资人）信息
					Element ele_resultzyryxx=eleszyryxx.get(1);
					Elements tr_resultszyryxx = getElements(ele_resultzyryxx, "tr");
					if (tr_resultszyryxx==null || tr_resultszyryxx.isEmpty()) {
						mainDeptInfo=null;
					}else{
						Elements td_resultsGdxx = getElements(tr_resultszyryxx.get(0), "td");
						if(td_resultsGdxx.size()==6){
						for(int i=0;i<tr_resultszyryxx.size();i++){
							AicpubArchiveMainDeptInfo aicpubArchiveMainDeptInfo=new AicpubArchiveMainDeptInfo();
							Element ele1 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",1);
							aicpubArchiveMainDeptInfo.setType(getElementText(ele1));//出资人类型
							Element ele2 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",2);
							aicpubArchiveMainDeptInfo.setName(getElementText(ele2));//出资人
							Element ele3 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",3);
							aicpubArchiveMainDeptInfo.setIdType(getElementText(ele3));//证照/证件类型
							Element ele4 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",4);
							aicpubArchiveMainDeptInfo.setIdNum(getElementText(ele4));//证照/证件号码
							Element ele5 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",5);
							aicpubArchiveMainDeptInfo.setShowDate(getElementText(ele5));//公示日期(新加)
							mainDeptInfo.add(aicpubArchiveMainDeptInfo);
						}
						}
						aicpubArchiveInfo.setMainDeptInfo(mainDeptInfo);
						
				}
				}
				
				
			}
		}
		
		if (resultHtmlMap.get("gsgsxx_baxx")!=null && isDebug) {
		aicpubArchiveInfo.setHtml(resultHtmlMap.get("gsgsxx_baxx").toString());
		}
		aicpubInfo.setArchiveInfo(aicpubArchiveInfo);//增加备案信息 AicpubArchiveInfo
		//备案信息********************结束
		
		
		//动产抵押登记信息********************开始
		AicpubChatMortgInfo aicpubChatMortgInfo=new AicpubChatMortgInfo();
		if (isDebug) {
			aicpubChatMortgInfo.setHtml(resultHtmlMap.get("gsgsxx_dcdydjxx").toString());
		} 
		aicpubInfo.setChatMortgInfo(aicpubChatMortgInfo); //AicpubChatMortgInfo
		//动产抵押登记信息********************结束
		
		
		//股权出资信息********************开始
		 AicpubEqumortgregInfo equMortgRegInfo=new AicpubEqumortgregInfo();
		 if(resultHtmlMap.get("gsgsxx_gqczxx")!=null){
			 Elements elesgqczxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_gqczxx").toString()).select("table");
				if(elesgqczxx==null || elesgqczxx.isEmpty()) {
//					equMortgRegInfo=null;
				}else{
					Element ele_resultzyryxx=elesgqczxx.get(1);//获取第一个table
					Elements tr_resultszyryxx = getElements(ele_resultzyryxx, "tr");
					if (tr_resultszyryxx==null || tr_resultszyryxx.isEmpty()) {
//						equMortgRegInfo=null;
					}else{
						Elements td_resultsGdxx = getElements(tr_resultszyryxx.get(0), "td");
						if(td_resultsGdxx.size()>=11){
							//股权出质登记信息
							List<AicpubEEqumortgregInfo> equmortgregInfos=new ArrayList<AicpubEEqumortgregInfo>();
							for(int i=0;i<tr_resultszyryxx.size();i++){
								AicpubEEqumortgregInfo aicpubEEqumortgregInfo=new AicpubEEqumortgregInfo();
								Element ele1 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",1);
								aicpubEEqumortgregInfo.setRegNum(getElementText(ele1));//登记编号
								Element ele2 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",2);
								aicpubEEqumortgregInfo.setMortgagorName(getElementText(ele2));//出质人
								Element ele3 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",3);
								aicpubEEqumortgregInfo.setMortgagorIdNum(getElementText(ele3));//证照/证件号码（出质人）
								Element ele4 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",4);
								aicpubEEqumortgregInfo.setMortgAmount(getElementText(ele4));//出质股权数额
								Element ele5 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",5);
								aicpubEEqumortgregInfo.setMortgageeName(getElementText(ele5));//质权人
								Element ele6 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",6);
								aicpubEEqumortgregInfo.setMortgageeIdNum(getElementText(ele6));//证照/证件号码
								Element ele7 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",7);
								aicpubEEqumortgregInfo.setRegDateTime(getElementText(ele7));//股权出质设立登记日期
								Element ele8 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",8);
								aicpubEEqumortgregInfo.setStatus(getElementText(ele8));//状态
								Element ele9 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",9);
								aicpubEEqumortgregInfo.setPubDate(getElementText(ele9));//公示时间
								Element ele10 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",10);
								aicpubEEqumortgregInfo.setChangeSitu(getElementText(ele10));//变化情况
								equmortgregInfos.add(aicpubEEqumortgregInfo);
							}
							equMortgRegInfo.setEqumortgregInfos(equmortgregInfos);
						}
					}
				}
		 }
		 if (resultHtmlMap.get("gsgsxx_gqczxx")!=null && isDebug) {
			 equMortgRegInfo.setHtml(resultHtmlMap.get("gsgsxx_gqczxx").toString());
		 }
		 aicpubInfo.setEquMortgRegInfo(equMortgRegInfo);//AicpubEqumortgregInfo
		//股权出资信息********************结束
		
		
		//行政处罚信息********************开始
		AicpubAdmpunishInfo aicpubAdmpunishInfo=new AicpubAdmpunishInfo();
		if (isDebug) {
			aicpubAdmpunishInfo.setHtml(resultHtmlMap.get("gsgsxx_xzcfxx").toString());
		} 
		aicpubInfo.setAdmPunishInfo(aicpubAdmpunishInfo);// AicpubAdmpunishInfo
		//行政处罚信息********************结束
		
		
		//经营异常信息********************开始
		AicpubOperanomaInfo aicpubOperanomaInfo=new AicpubOperanomaInfo();
		if(resultHtmlMap.get("gsgsxx_jyycxx")!=null){
			List<AicpubOOperanomaInfo> operAnomaInfos=new ArrayList<AicpubOOperanomaInfo>();
			Elements elesjyycxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_jyycxx").toString()).select("table");
			if(elesjyycxx==null || elesjyycxx.isEmpty()) {
				operAnomaInfos=null;
			}else{
				Element ele_resultjyycxx=elesjyycxx.get(1);
				Elements tr_resultsjyycxx = getElements(ele_resultjyycxx, "tr");
				if (tr_resultsjyycxx==null || tr_resultsjyycxx.isEmpty()) {
//					operAnomaInfos=null;
				}else{
					if(tr_resultsjyycxx.size()>0){
					for(int i=0;i<tr_resultsjyycxx.size();i++){
						if(getElements(tr_resultsjyycxx.get(i), "td")!=null && getElements(tr_resultsjyycxx.get(i), "td").size()==6){
						AicpubOOperanomaInfo aicpubOOperanomaInfo=new AicpubOOperanomaInfo();
						Element ele_includeCause = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i)), "td",1);//列入经营异常名录原因
						aicpubOOperanomaInfo.setIncludeCause(getElementText(ele_includeCause));
						Element ele_includeDateTime = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i)), "td",2);//列入日期
						aicpubOOperanomaInfo.setIncludeDateTime(getElementText(ele_includeDateTime));
						Element ele_removeCause = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i)), "td",3);//移出经营异常名录原因
						aicpubOOperanomaInfo.setRemoveCause(getElementText(ele_removeCause));
						Element ele_removeDateTime = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i)), "td",4);//移出日期
						aicpubOOperanomaInfo.setRemoveDateTime(getElementText(ele_removeDateTime));
						Element ele_removeAuthority = getElementByIndex((getElementByIndex(ele_resultjyycxx, "tr",i)), "td",5);//作出决定机关(移出)
						aicpubOOperanomaInfo.setRemoveAuthority(getElementText(ele_removeAuthority));
						operAnomaInfos.add(aicpubOOperanomaInfo);
						}
				    	}
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
		
		//抽查检查信息********************开始
		AicpubCheckInfo aicpubCheckInfo=new AicpubCheckInfo();
		if (isDebug) {
		aicpubCheckInfo.setHtml(resultHtmlMap.get("gsgsxx_ccjcxx").toString());
		}
		aicpubInfo.setCheckInfo(aicpubCheckInfo);// AicpubCheckInfo
		//抽查检查信息********************结束
		//********************工商公示信息 结束********************
		//********************个体工商户公示信息 开始********************
		//企业公示信息
		EntpubInfo entpubInfo=new EntpubInfo();
		//个体工商户年报********************开始
		//个体工商户年报列表
		List<EntpubAnnreportInfo> entpubAnnreportInfos=new ArrayList<EntpubAnnreportInfo>();
		Elements elesqygsQynbxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gtgshgs_gtgshnb").toString()).select("div#details").select("div#detailsCon").select("div#qiyenianbao");
		if(elesqygsQynbxx==null || elesqygsQynbxx.isEmpty()) {
			entpubAnnreportInfos=null;
		}
		
		Element eleqygsQynbDiv = elesqygsQynbxx.get(0);
		Elements ele_resultsqygsQynbTable = getElements(eleqygsQynbDiv, "table.detailsList");
		if (ele_resultsqygsQynbTable==null || ele_resultsqygsQynbTable.isEmpty()) {
			entpubAnnreportInfos=null;
		}
		
		Element ele_resultqygsQynb=ele_resultsqygsQynbTable.get(0);//获取第一个table（个体工商户年报）
		Elements tr_resultsqygsQynb = getElements(ele_resultqygsQynb, "tr");
		if (tr_resultsqygsQynb==null || tr_resultsqygsQynb.isEmpty()) {
			entpubAnnreportInfos=null;
		}else{
			int ia=0;
			for(int i=0;i<(tr_resultsqygsQynb.size()-2);i++){
				EntpubAnnreportInfo annReports=new EntpubAnnreportInfo();
				Element ele_submitYear = getElementByIndex((getElementByIndex(ele_resultqygsQynb, "tr",i+2)), "td",1);
				annReports.setSubmitYear(getElementText(ele_submitYear));//报送年度
				Element ele_pubDate = getElementByIndex((getElementByIndex(ele_resultqygsQynb, "tr",i+2)), "td",2);
				annReports.setPubDateTime(getElementText(ele_pubDate));//发布日期
				Elements td_resultsGdxx = getElements(tr_resultsqygsQynb.get(i+2), "td");
				if(td_resultsGdxx.size()==4){
					Element ele_remarks = getElementByIndex((getElementByIndex(ele_resultqygsQynb, "tr",i+2)), "td",3);
					annReports.setRemarks(getElementText(ele_remarks));//备注  河南（济源市王屋镇新飞电器）
				}
				if(resultHtmlMap.get("gtgshgs_gtgshnb_detail")!=null){
					if(ele_submitYear.select("a").size()!=0){
					@SuppressWarnings("unchecked")
					List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gtgshgs_gtgshnb_detail");
					if(ia<detailMap.size()){
//					  for(int a=0;a<detailMap.size();a++){
						Elements tables = Jsoup.parseBodyFragment(detailMap.get(ia).toString()).select("table");
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
										
										if(qygsxxQynb13Th.equals("经营者姓名")){
											qygsQynbJbInfo.setOperator(qygsxxQynb13Td);
										}
										if(qygsxxQynb13Th.equals("资金数额")){
											qygsQynbJbInfo.setCapitalAmount(qygsxxQynb13Td);
										}
										
										switch (qygsxxQynb13Th) {
											case "注册号/统一社会信用代码":
												qygsQynbJbInfo.setNum(qygsxxQynb13Td);
												break;
											case "注册号":
												qygsQynbJbInfo.setNum(qygsxxQynb13Td);
												break;
											case "营业执照注册号":
												qygsQynbJbInfo.setNum(qygsxxQynb13Td);
												break;
											case "企业名称":
												qygsQynbJbInfo.setName(qygsxxQynb13Td);
												break;
											case "名称":
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
											case "经营者姓名 ":
												qygsQynbJbInfo.setOperator(qygsxxQynb13Td);
												break;
											case "资金数额 ":
												qygsQynbJbInfo.setCapitalAmount(qygsxxQynb13Td);
												break;
											default:
												break;
										}
									}
									
									annReports.setBaseInfo(qygsQynbJbInfo);
									
								} else if ("企业资产状况信息".equals(qygsxxQynbTitle) || "生产经营情况".equals(qygsxxQynbTitle) || "资产状况信息".equals(qygsxxQynbTitle) || "生产经营情况信息".equals(qygsxxQynbTitle)) {
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
											case "营业额或营业收入":
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
								}else if ("行政许可情况".equals(qygsxxQynbTitle)) {
									List<EntpubAnnreportAdmlicenseInfo> admlicenseInfos=new ArrayList<EntpubAnnreportAdmlicenseInfo>();
									Elements tr_websiteInfo = getElements(qygsxxQynb13Table, "tr");
									if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//										websiteInfos=null;
									}else{
										for(int b=0;b<(tr_websiteInfo.size()-2);b++){
											EntpubAnnreportAdmlicenseInfo entpubAnnreportAdmlicenseInfo=new EntpubAnnreportAdmlicenseInfo();
											Element ele1 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",0);//许可文件名称
											entpubAnnreportAdmlicenseInfo.setLicenseName(getElementText(ele1));
											Element ele2 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",1);//有效期至
											entpubAnnreportAdmlicenseInfo.setLicenseDate(getElementText(ele2));
											admlicenseInfos.add(entpubAnnreportAdmlicenseInfo);
											}
										}
									 annReports.setAdmlicenseInfos(admlicenseInfos);
									
								}else if ("修改记录".equals(qygsxxQynbTitle)) {
									List<EntpubAnnreportModifyInfo> changeInfos2=new ArrayList<EntpubAnnreportModifyInfo>();
									Elements tr_websiteInfo = getElements(qygsxxQynb13Table, "tr");
									if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//										websiteInfos=null;
									}else{
										if(tr_websiteInfo.size()>=4){
										Elements td_resultsGdxx1 = getElements(tr_websiteInfo.get(2), "td");
										if(td_resultsGdxx1.size()==5){
											for(int b=0;b<(tr_websiteInfo.size()-2);b++){
												if(b!=tr_websiteInfo.size()-3){
												EntpubAnnreportModifyInfo entpubAnnreportModifyInfo=new EntpubAnnreportModifyInfo();
												Element ele1 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",1);//修改事项
												entpubAnnreportModifyInfo.setItem(getElementText(ele1));
												Element ele2 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",2);//修改前
												entpubAnnreportModifyInfo.setPreContent(getElementText(ele2));
												Element ele3 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",3);//修改后
												entpubAnnreportModifyInfo.setPostContent(getElementText(ele3));
												Element ele4 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",4);//修改日期
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
//						}
					}
						ia=ia+1;
					}
					}
				}
			/*	if(resultHtmlMap.get("gtgshgs_gtgshnb")!=null && isDebug){
					annReports.setHtml(resultHtmlMap.get("gtgshgs_gtgshnb_detail").toString());
				}*/
				entpubAnnreportInfos.add(annReports);
			}
		}
		
		entpubInfo.setAnnReports(entpubAnnreportInfos);
		
		
		//股权变更信息
				EntpubEquchangeInfo equChangeInfo=new EntpubEquchangeInfo();
				if(resultHtmlMap.get("qygsxx_gqbgxx")!=null){
					Elements eles = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_gqbgxx").toString()).select("table");
					if(eles==null || eles.isEmpty()) {
//						changeInfos=null;
					}else{
						if(elesGdxx.size()>0){
						Element ele_resultGdxx=eles.get(0);//获取第一个table
						Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
						if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//							changeInfos=null;
						}else{
		                  if(tr_resultsGdxx.size()>2 && getElementByIndex(ele_resultGdxx, "tr",2)!=null && getElements(tr_resultsGdxx.get(2), "td").size()==6){
		                	  List<EntpubEEquchangeInfo> equChangeInfos=new ArrayList<EntpubEEquchangeInfo>();
		                		for(int i=2;i<tr_resultsGdxx.size();i++){
									if(getElements(tr_resultsGdxx.get(i), "td")!=null && getElements(tr_resultsGdxx.get(i), "td").size()==6){
										EntpubEEquchangeInfo entpubEEquchangeInfo=new EntpubEEquchangeInfo();
										Element ele1 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);//股东
										entpubEEquchangeInfo.setStockholder(getElementText(ele1));
										Element ele2 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);//变更前股权比例
										entpubEEquchangeInfo.setPreOwnershipRatio(getElementText(ele2));
										Element ele3 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);//变更后股权比例
										entpubEEquchangeInfo.setPostOwnershipRatio(getElementText(ele3));
										Element ele4 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",4);//股权变更日期
										entpubEEquchangeInfo.setDateTime(getElementText(ele4));
										Element ele5 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",5);
										entpubEEquchangeInfo.setShowTime(getElementText(ele5));
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
				
				
				
		//个体工商户年报********************结束
		
		//********************个体工商户公示信息 结束********************
		
		//********************其他部门公示信息 开始********************
		//其他部门公示信息
		OthrdeptpubInfo othrdeptpubInfo=new OthrdeptpubInfo();
		//行政许可信息********************开始
		//行政许可信息
		OthrdeptpubAdmlicInfo othrdeptpubAdmlicInfo=new OthrdeptpubAdmlicInfo();
		if (isDebug) {
		othrdeptpubAdmlicInfo.setHtml(resultHtmlMap.get("qtbmgsxx_xzxkxx").toString());
		}
		othrdeptpubInfo.setAdmLicInfo(othrdeptpubAdmlicInfo);
		//行政许可信息********************结束
		
		//行政处罚信息********************开始
		//行政处罚信息
		OthrdeptpubAdmpunishInfo othrdeptpubAdmpunishInfo=new OthrdeptpubAdmpunishInfo();
		if (isDebug) {
		othrdeptpubAdmpunishInfo.setHtml(resultHtmlMap.get("qtbmgsxx_xzcfxx").toString());
		}
		othrdeptpubInfo.setAdmPunishInfo(othrdeptpubAdmpunishInfo);
		//行政处罚信息********************结束
		//********************其他部门公示信息 开始********************
		
		//司法协助-股权冻结信息 
		JudasspubInfo judAssPubInfo=new JudasspubInfo();
		JudasspubEqufreezeInfo equFreezeInfo=new JudasspubEqufreezeInfo();
		if(resultHtmlMap.get("sfxz_gqdj")!=null){
			Elements eles = Jsoup.parseBodyFragment(resultHtmlMap.get("sfxz_gqdj").toString()).select("table");
			if(eles==null || eles.isEmpty()) {
//				changeInfos=null;
			}else{
				if(elesGdxx.size()>0){
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
				
			}
		}
		judAssPubInfo.setEquFreezeInfo(equFreezeInfo);
		
		
		
		aicFeedJson.setAicPubInfo(aicpubInfo);//增加工商公示信息   AicpubInfo
		aicFeedJson.setEntPubInfo(entpubInfo);//增加企业公示信息  EntpubInfo
		aicFeedJson.setOthrDeptPubInfo(othrdeptpubInfo);//其他部门信息  OthrdeptpubInfo
		aicFeedJson.setJudAssPubInfo(judAssPubInfo);
		}
	    return aicFeedJson;
	}
	
}

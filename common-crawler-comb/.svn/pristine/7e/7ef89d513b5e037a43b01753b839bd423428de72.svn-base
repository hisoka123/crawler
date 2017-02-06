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
import com.crawler.gsxt.domain.json.AicpubArchiveInfo;
import com.crawler.gsxt.domain.json.AicpubArchivePrimemberInfo;
import com.crawler.gsxt.domain.json.AicpubChatMortgInfo;
import com.crawler.gsxt.domain.json.AicpubCheckInfo;
import com.crawler.gsxt.domain.json.AicpubEEqumortgregInfo;
import com.crawler.gsxt.domain.json.AicpubEqumortgregInfo;
import com.crawler.gsxt.domain.json.AicpubInfo;
import com.crawler.gsxt.domain.json.AicpubOperanomaInfo;
import com.crawler.gsxt.domain.json.AicpubRegBaseInfo;
import com.crawler.gsxt.domain.json.AicpubRegChangeInfo;
import com.crawler.gsxt.domain.json.AicpubRegInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAssetInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportBaseInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportWebsiteInfo;
import com.crawler.gsxt.domain.json.EntpubInfo;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubStohrinvestInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubInfo;
import com.crawler.gsxt.domain.json.AicpubAAdmpunishInfo.PunishDetail;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo.Detail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtHubeiParser extends AbstractGsxtParser {

	private static final Logger log = LoggerFactory
			.getLogger(GsxtHubeiParser.class);

	public AicFeedJson hubeiParser(String html, boolean isDebug) {
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
				case "投资人":
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
				case "注册日期":
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
				case "组成形式":
					aicpubRegBaseInfo.setFormType(gsgsxxDjjbTd);
					break;
				case "经营者":
					aicpubRegBaseInfo.setLegalRepr(gsgsxxDjjbTd);
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
//										aicpubRegStohrStohrinvestInfo=null;
									}else{
										    AicpubRegStohrStohrinvestInfo aicpubRegStohrStohrinvestInfo1=new AicpubRegStohrStohrinvestInfo();
										    List<AmountDetail> subAmountDetails=new ArrayList<AmountDetail>();//认缴明细
										    List<AmountDetail> paidAmountDetails=new ArrayList<AmountDetail>();//实缴明细
										    
											Element ele_stockholder = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",0);
											aicpubRegStohrStohrinvestInfo1.setStockholder(getElementText(ele_stockholder));//股东
											Element ele_subAmount = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",1);
											aicpubRegStohrStohrinvestInfo1.setSubAmount(getElementText(ele_subAmount));//认缴额（万元）
											Element ele_paidAmount = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",2);
											aicpubRegStohrStohrinvestInfo1.setPaidAmount(getElementText(ele_paidAmount));//实缴额（万元）
											if(tr_resultsGdjczxx.size()>4){
											
											AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail1=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
											Element ele_method1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",4)), "td",0);
											amountDetail1.investMethod=getElementText(ele_method1);//出资方式
											Element ele_amount1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",4)), "td",1);
											amountDetail1.investAmount=getElementText(ele_amount1);//出资额（万元）
											Element ele_czDate1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",4)), "td",2);
											amountDetail1.investDateTime=getElementText(ele_czDate1);//出资日期
											subAmountDetails.add(amountDetail1);
											
											AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail2=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
											Element ele_method2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",4)), "td",3);
											amountDetail2.investMethod=getElementText(ele_method2);//出资方式
											Element ele_amount2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",4)), "td",4);
											amountDetail2.investAmount=getElementText(ele_amount2);//出资额（万元）
											Element ele_czDate2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",4)), "td",5);
											amountDetail2.investDateTime=getElementText(ele_czDate2);//出资日期
											paidAmountDetails.add(amountDetail2);
											
									         }else if(tr_resultsGdjczxx.size()>3 && getElementByIndex(ele_resultGdjczxx, "tr",3)!=null && getElements(tr_resultsGdjczxx.get(3), "td").size()==9){
									        	
									        	 AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail1=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
													Element ele_method1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",3);
													amountDetail1.investMethod=getElementText(ele_method1);//出资方式
													Element ele_amount1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",4);
													amountDetail1.investAmount=getElementText(ele_amount1);//出资额（万元）
													Element ele_czDate1 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",5);
													amountDetail1.investDateTime=getElementText(ele_czDate1);//出资日期
													subAmountDetails.add(amountDetail1);
													
													AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail2=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
													Element ele_method2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",6);
													amountDetail2.investMethod=getElementText(ele_method2);//出资方式
													Element ele_amount2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",7);
													amountDetail2.investAmount=getElementText(ele_amount2);//出资额（万元）
													Element ele_czDate2 = getElementByIndex((getElementByIndex(ele_resultGdjczxx, "tr",3)), "td",8);
													amountDetail2.investDateTime=getElementText(ele_czDate2);//出资日期
													paidAmountDetails.add(amountDetail2);
									        	 
									         }
											aicpubRegStohrStohrinvestInfo1.setSubAmountDetails(subAmountDetails);
											aicpubRegStohrStohrinvestInfo1.setPaidAmountDetails(paidAmountDetails);
											aicpubRegStohrInfo.setStohrInvestInfo(aicpubRegStohrStohrinvestInfo1);//AicpubRegStohrStohrinvestInfo
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
	    				 stohrInfos.add(aicpubRegStohrInfo);
	        	   }
	        	  
				}
			}
		
			
		}
		aicpubRegInfo.setStohrInfos(stohrInfos);
		
		
		//变更信息（包括变更信息详细）
		List<AicpubRegChangeInfo> changeInfos=new ArrayList<AicpubRegChangeInfo>();
		if(resultHtmlMap.get("gsgsxx_bgxx")!=null){
			@SuppressWarnings("unchecked")
			List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_bgxx");
			if(detailMap.size()>0){
				for(Object object:detailMap){
					Elements elesBgxx = Jsoup.parseBodyFragment(object.toString()).select("table");
					if(elesBgxx==null || elesBgxx.isEmpty()) {
//						changeInfos=null;
					}else{
						Element ele_resultBgxx=elesBgxx.get(0);//获取第一个table
						Elements tr_resultsBgxx = getElements(ele_resultBgxx, "tr");
						if (tr_resultsBgxx==null || tr_resultsBgxx.isEmpty()) {
//							changeInfos=null;
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
	if(resultHtmlMap.get("gsgsxx_zyryxx")!=null){
		@SuppressWarnings("unchecked")
		List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_zyryxx");
		if(detailMap.size()>0){
			for(Object object:detailMap){
				Elements eleszyryxx = Jsoup.parseBodyFragment(object.toString()).select("table");
				if(eleszyryxx==null || eleszyryxx.isEmpty()) {
					priMemberInfos=null;
				}else{
					Element ele_resultzyryxx=eleszyryxx.get(0);
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
				}
				
				}
			}
	}
	aicpubArchiveInfo.setPriMemberInfos(priMemberInfos);
	if (resultHtmlMap.get("gsgsxx_zyryxx")!=null && isDebug) {
	aicpubArchiveInfo.setHtml(resultHtmlMap.get("gsgsxx_zyryxx").toString());
	}
	aicpubInfo.setArchiveInfo(aicpubArchiveInfo);//增加备案信息 AicpubArchiveInfo
	//备案信息********************结束
	
	
	//动产抵押登记信息********************开始
	AicpubChatMortgInfo aicpubChatMortgInfo=new AicpubChatMortgInfo();
	if (resultHtmlMap.get("gsgsxx_dcdydjxx")!=null && isDebug) {
	aicpubChatMortgInfo.setHtml(resultHtmlMap.get("gsgsxx_dcdydjxx").toString());
	}
	aicpubInfo.setChatMortgInfo(aicpubChatMortgInfo); //AicpubChatMortgInfo
	//动产抵押登记信息********************结束
	

	//股权出资信息********************开始
	 AicpubEqumortgregInfo equMortgRegInfo=new AicpubEqumortgregInfo();
	 if(resultHtmlMap.get("gsgsxx_gqczxx")!=null){
		 Elements elesgqczxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_gqczxx").toString()).select("div#pledgeDiv").select("table.detailsList");
			if(elesgqczxx==null || elesgqczxx.isEmpty()) {
//				equMortgRegInfo=null;
			}else{
				Element ele_resultzyryxx=elesgqczxx.get(0);//获取第一个table
				Elements tr_resultszyryxx = getElements(ele_resultzyryxx, "tr");
				if (tr_resultszyryxx==null || tr_resultszyryxx.isEmpty()) {
//					equMortgRegInfo=null;
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
	AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
	 if(resultHtmlMap.get("gsgsxx_xzcfxx")!=null){
		 Elements elesgqczxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_xzcfxx").toString()).select("div#punDiv").select("table.detailsList");
			if(elesgqczxx==null || elesgqczxx.isEmpty()) {
//				equMortgRegInfo=null;
			}else{
				Element ele_resultzyryxx=elesgqczxx.get(0);//获取第一个table
				Elements tr_resultszyryxx = getElements(ele_resultzyryxx, "tr");
				if (tr_resultszyryxx==null || tr_resultszyryxx.isEmpty()) {
//					equMortgRegInfo=null;
				}else{
					Elements td_resultsGdxx = getElements(tr_resultszyryxx.get(0), "td");
					if(td_resultsGdxx.size()>=8){
						//行政处罚信息
						List<AicpubAAdmpunishInfo> equmortgregInfos = new ArrayList<AicpubAAdmpunishInfo>();
						for(int i=0;i<tr_resultszyryxx.size();i++){
							AicpubAAdmpunishInfo aicpubAAdmpunishInfo=new AicpubAAdmpunishInfo();
							Element ele1 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",1);
							aicpubAAdmpunishInfo.setPunishRepNum(getElementText(ele1));//行政处罚决定书文号
							Element ele2 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",2);
							aicpubAAdmpunishInfo.setIllegalActType(getElementText(ele2));//违法行为类型
							Element ele3 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",3);
							aicpubAAdmpunishInfo.setPunishContent(getElementText(ele3));//行政处罚内容
							Element ele4 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",4);
							aicpubAAdmpunishInfo.setDeciAuthority(getElementText(ele4));//作出行政处罚决定机关名称
							Element ele5 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",5);
							aicpubAAdmpunishInfo.setDeciDateTime(getElementText(ele5));//作出行政处罚决定日期
							
							
							//详情
							if(resultHtmlMap.get("gsgsxx_xzcfxx_detail")!=null){
								@SuppressWarnings("unchecked")
								List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_xzcfxx_detail");
								if((detailMap.size()-1)>=i){
									Elements tables = Jsoup.parseBodyFragment(detailMap.get(i).toString()).select("div#details").select("div#jibenxinxi").select("table");
									if(tables==null || tables.isEmpty()){}
									else{
										Element table=tables.get(0);//获取第一个table（基本信息）
										Elements tr_resultsJbxx1 = getElements(table, "tr");
										Elements gsgsxxDjjbThs1 = tr_resultsJbxx1.select("th");
										if (tr_resultsJbxx1==null || tr_resultsJbxx1.isEmpty()) {
//											aicpubRegBaseInfo=null;
										}else{
											AicpubAAdmpunishInfo.PunishDetail punishDetail = aicpubAAdmpunishInfo.new PunishDetail();
											for (int j = 0; j < gsgsxxDjjbThs1.size(); j++) {
												if(gsgsxxDjjbThs1.get(j)!=null){
												String gsgsxxDjjbTh = gsgsxxDjjbThs1.get(j).text();
												if(gsgsxxDjjbThs1.get(j).nextElementSibling()!=null){
													String xqTdContent = gsgsxxDjjbThs1.get(j).nextElementSibling().text();
													switch (gsgsxxDjjbTh) {
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
												}
											}
											}
											aicpubAAdmpunishInfo.setPunishDetail(punishDetail);
										}
										}
									}
							}
							
							equmortgregInfos.add(aicpubAAdmpunishInfo);
						}
						gsgsXzcfInfo.setAdmPunishInfos(equmortgregInfos);
					}
				}
			}
	 }
	
	if (resultHtmlMap.get("gsgsxx_xzcfxx")!=null && isDebug) {
		gsgsXzcfInfo.setHtml(resultHtmlMap.get("gsgsxx_xzcfxx").toString());
	}
	aicpubInfo.setAdmPunishInfo(gsgsXzcfInfo);// AicpubAdmpunishInfo
	//行政处罚信息********************结束
	
	
	//经营异常信息********************开始
	AicpubOperanomaInfo aicpubOperanomaInfo=new AicpubOperanomaInfo();
	if (resultHtmlMap.get("gsgsxx_jyycxx")!=null && isDebug) {
	aicpubOperanomaInfo.setHtml(resultHtmlMap.get("gsgsxx_jyycxx").toString());
	}
	aicpubInfo.setOperAnomaInfo(aicpubOperanomaInfo);//增加经营异常信息 AicpubOperanomaInfo
	//经营异常信息********************结束
	
	//抽查检查信息********************开始
	AicpubCheckInfo aicpubCheckInfo=new AicpubCheckInfo();
	if (resultHtmlMap.get("gsgsxx_ccjcxx")!=null && isDebug) {
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
			for(int i=0;i<(tr_resultsqygsQynb.size()-2);i++){
				EntpubAnnreportInfo annReports=new EntpubAnnreportInfo();
				Element ele_submitYear = getElementByIndex((getElementByIndex(ele_resultqygsQynb, "tr",i+2)), "td",1);
				annReports.setSubmitYear(getElementText(ele_submitYear));//报送年度
				Element ele_pubDate = getElementByIndex((getElementByIndex(ele_resultqygsQynb, "tr",i+2)), "td",2);
				annReports.setPubDateTime(getElementText(ele_pubDate));//发布日期
				
				if(resultHtmlMap.get("gtgshgs_gtgshnb_detail")!=null){
					@SuppressWarnings("unchecked")
					List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gtgshgs_gtgshnb_detail");
					if((detailMap.size()-1)>=i){
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
											case "企业电子邮箱":
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
								}else if ("股权变更信息".equals(qygsxxQynbTitle)) {
									List<EntpubAnnreportEquchangeInfo> equChangeInfos=new ArrayList<EntpubAnnreportEquchangeInfo>();
									Elements tr_websiteInfo = getElements(qygsxxQynb13Table, "tr");
									if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
										equChangeInfos=null;
									}else{
										if(tr_websiteInfo.size()>2 && getElementByIndex(qygsxxQynb13Table, "tr",2)!=null && getElements(tr_websiteInfo.get(2), "td").size()==4){
										for(int b=2;b<tr_websiteInfo.size();b++){
											EntpubAnnreportEquchangeInfo entpubAnnreportEquchangeInfo=new EntpubAnnreportEquchangeInfo();
											Element ele1 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",0);
											if(getElementText(ele1)!=null && !getElementText(ele1).isEmpty()){
												entpubAnnreportEquchangeInfo.setStockholder(getElementText(ele1));//股东（发起人）
												Element ele2 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",1);
												entpubAnnreportEquchangeInfo.setPreOwnershipRatio(getElementText(ele2));//变更前股权比例
												Element ele3 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",2);
												entpubAnnreportEquchangeInfo.setPostOwnershipRatio(getElementText(ele3));//变更后股权比例
												Element ele4 = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b)), "td",3);
												entpubAnnreportEquchangeInfo.setDateTime(getElementText(ele4));//股权变更日期
												equChangeInfos.add(entpubAnnreportEquchangeInfo);
											}
											}
										
										}
									}
									annReports.setEquChangeInfos(equChangeInfos);
								}
								
								
							}
						}
					}
				}
				
				if(resultHtmlMap.get("gtgshgs_gtgshnb")!=null && isDebug){
					annReports.setHtml(resultHtmlMap.get("gtgshgs_gtgshnb_detail").toString());
				}
				entpubAnnreportInfos.add(annReports);
			}
		}
		
		entpubInfo.setAnnReports(entpubAnnreportInfos);
		//个体工商户年报********************结束
		
		
		//股东及出资信息
				EntpubStohrinvestInfo stohrInvestInfo=new EntpubStohrinvestInfo();
				
				if(resultHtmlMap.get("qygsxx_gdjczxx")!=null){
					Elements elesGdxx2 = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_gdjczxx").toString()).select("div#gdDiv").select("table");
					if(elesGdxx2==null || elesGdxx2.isEmpty()) {
//						changeInfos=null;
					}else{
						if(elesGdxx2.size()>0){
						Element ele_resultGdxx=elesGdxx2.get(0);//获取第一个table
						Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
						if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//							changeInfos=null;
						}else{
		                  if(tr_resultsGdxx.size()>3 && getElementByIndex(ele_resultGdxx, "tr",3)!=null && getElements(tr_resultsGdxx.get(3), "td").size()==11){
		                	  List<EntpubSStohrinvestInfo> stohrInvestInfos=new ArrayList<EntpubSStohrinvestInfo>();
		                		for(int i=3;i<tr_resultsGdxx.size();i++){
									if(getElements(tr_resultsGdxx.get(i), "td")!=null && getElements(tr_resultsGdxx.get(i), "td").size()==11){
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
										Element ele61 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",6);
										detail1.setShowDate(getElementText(ele61));	//公示日期
										subDetails.add(detail1);
										
		                                EntpubSStohrinvestInfo.Detail detail2=entpubSStohrinvestInfo.new Detail();
		                                Element ele7 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",7);
		                                detail2.setMethod(getElementText(ele7));//出资方式
		                                Element ele8 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",8);
		                                detail2.setAmount(getElementText(ele8));//出资额（万元）
		                                Element ele9 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",9);
		                                detail2.setDateTime(getElementText(ele9));//出资日期
		                                Element ele91 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",10);
										detail2.setShowDate(getElementText(ele91));	//公示日期
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
				
		
		
		
		//********************个体工商户公示信息 结束********************
		
		//********************其他部门公示信息 开始********************
		//其他部门公示信息
		OthrdeptpubInfo othrdeptpubInfo=new OthrdeptpubInfo();
		//行政许可信息********************开始
		//行政许可信息
		OthrdeptpubAdmlicInfo othrdeptpubAdmlicInfo=new OthrdeptpubAdmlicInfo();
		if (resultHtmlMap.get("qtbmgsxx_xzxkxx")!=null && isDebug) {
		othrdeptpubAdmlicInfo.setHtml(resultHtmlMap.get("qtbmgsxx_xzxkxx").toString());
		}
		othrdeptpubInfo.setAdmLicInfo(othrdeptpubAdmlicInfo);
		//行政许可信息********************结束
		
		//行政处罚信息********************开始
		//行政处罚信息
		OthrdeptpubAdmpunishInfo othrdeptpubAdmpunishInfo=new OthrdeptpubAdmpunishInfo();
		if (resultHtmlMap.get("qtbmgsxx_xzcfxx")!=null && isDebug) {
		othrdeptpubAdmpunishInfo.setHtml(resultHtmlMap.get("qtbmgsxx_xzcfxx").toString());
		}
		othrdeptpubInfo.setAdmPunishInfo(othrdeptpubAdmpunishInfo);
		//行政处罚信息********************结束
		//********************其他部门公示信息 开始********************
		
		aicFeedJson.setAicPubInfo(aicpubInfo);//增加工商公示信息   AicpubInfo
		aicFeedJson.setEntPubInfo(entpubInfo);//增加企业公示信息  EntpubInfo
		aicFeedJson.setOthrDeptPubInfo(othrdeptpubInfo);//其他部门信息  OthrdeptpubInfo
		}
	    return aicFeedJson;
	}

}

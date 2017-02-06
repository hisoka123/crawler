package com.crawler.gsxt.htmlparser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import com.crawler.gsxt.domain.json.AicpubCChatMortgDetail;
import com.crawler.gsxt.domain.json.AicpubCChatMortgGoodsInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgGuaranteedInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgPersonInfo;
import com.crawler.gsxt.domain.json.AicpubCChatMortgRegInfo;
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
import com.crawler.gsxt.domain.json.AicpubSerillegalInfo;
import com.crawler.gsxt.domain.json.EntpubAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAdmlicenseInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAssetInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportBaseInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportExtinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportModifyInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportStohrinvestInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportWebsiteInfo;
import com.crawler.gsxt.domain.json.EntpubEquchangeInfo;
import com.crawler.gsxt.domain.json.EntpubInfo;
import com.crawler.gsxt.domain.json.EntpubIntellectualproregInfo;
import com.crawler.gsxt.domain.json.EntpubStohrinvestInfo;
import com.crawler.gsxt.domain.json.JudasspubEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubInfo;
import com.crawler.gsxt.domain.json.JudasspubStohrchangeInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtNeimengguParser extends AbstractGsxtParser {

	private static final Logger log = LoggerFactory
			.getLogger(GsxtNeimengguParser.class);

	public AicFeedJson neimengguParser(String html, boolean isDebug) {
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
		if(resultHtmlMap.get("gsgsxx_jbxx")!=null){
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
					case "经营者":
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
					case "组成形式":
						aicpubRegBaseInfo.setFormType(gsgsxxDjjbTd);
						break;
					case "注册日期":
						aicpubRegBaseInfo.setRegDateTime(gsgsxxDjjbTd);
						break;
					default:
						break;
					}
				}
			}
			aicpubRegInfo.setBaseInfo(aicpubRegBaseInfo);//AicpubRegBaseInfo
		}
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
							for(int i=0;i<tr_resultsGdxx.size();i++){
								AicpubRegStohrInfo aicpubRegStohrInfo=new AicpubRegStohrInfo();
								Element ele_Type = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",0);
								aicpubRegStohrInfo.setType(getElementText(ele_Type));//股东类型
								Element ele_Name = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);
								aicpubRegStohrInfo.setName(getElementText(ele_Name));//股东名称
								Element ele_IdType = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);
								aicpubRegStohrInfo.setIdType(getElementText(ele_IdType));//证照/证件类型
								Element ele_IdNum = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);
								aicpubRegStohrInfo.setIdNum(getElementText(ele_IdNum));//证照/证件号码
								
								//股东详情
								if(resultHtmlMap.get("gsgsxx_gdxx_detail")!=null){
									@SuppressWarnings("unchecked")
									List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_gdxx_detail");
//									System.out.println(detailMap.get(i).toString());
									Elements tables = Jsoup.parseBodyFragment(detailMap.get(i).toString()).select("div#details").select("div#jibenxinxi").select("table.detailsList");
									if(tables==null || tables.isEmpty()){}
									else{
										Element websiteInfo=tables.get(0);
										Elements tr_websiteInfo = getElements(websiteInfo, "tr");
										if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//											websiteInfos=null;
										}else{
											AicpubRegStohrStohrinvestInfo aicpubRegStohrStohrinvestInfo1=new AicpubRegStohrStohrinvestInfo();
											List<AmountDetail> subAmountDetails=new ArrayList<AmountDetail>();//认缴明细
											List<AmountDetail> paidAmountDetails=new ArrayList<AmountDetail>();//实缴明细
											
											for(int b=0;b<(tr_websiteInfo.size()-2);b++){
												if(getElementByIndex(websiteInfo, "tr",2)!=null){
													Element ele_stockholder = getElementByIndex((getElementByIndex(websiteInfo, "tr",2)), "td",0);
													aicpubRegStohrStohrinvestInfo1.setStockholder(getElementText(ele_stockholder));//股东
													Element ele_subAmount = getElementByIndex((getElementByIndex(websiteInfo, "tr",2)), "td",1);
													aicpubRegStohrStohrinvestInfo1.setStockType(getElementText(ele_subAmount));//类型
													
													AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail1=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
													Element ele_method1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",2)), "td",2);
													amountDetail1.investMethod=getElementText(ele_method1);//出资方式
													Element ele_amount1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",2)), "td",3);
													amountDetail1.investAmount=getElementText(ele_amount1);//出资额（万元）
													Element ele_czDate1 = getElementByIndex((getElementByIndex(websiteInfo, "tr",2)), "td",4);
													amountDetail1.investDateTime=getElementText(ele_czDate1);//出资日期
													subAmountDetails.add(amountDetail1);
													aicpubRegStohrStohrinvestInfo1.setSubAmountDetails(subAmountDetails);
													
													AicpubRegStohrStohrinvestInfo.AmountDetail amountDetail2=aicpubRegStohrStohrinvestInfo1.new AmountDetail();
													Element ele_method2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",2)), "td",5);
													amountDetail2.investMethod=getElementText(ele_method2);//出资方式
													Element ele_amount2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",2)), "td",6);
													amountDetail2.investAmount=getElementText(ele_amount2);//出资额（万元）
													Element ele_czDate2 = getElementByIndex((getElementByIndex(websiteInfo, "tr",2)), "td",7);
													amountDetail2.investDateTime=getElementText(ele_czDate2);//出资日期
													paidAmountDetails.add(amountDetail2);
													aicpubRegStohrStohrinvestInfo1.setPaidAmountDetails(paidAmountDetails);
													aicpubRegStohrInfo.setStohrInvestInfo(aicpubRegStohrStohrinvestInfo1);	
												}
											}
											
											aicpubRegStohrStohrinvestInfo1.setSubAmountDetails(subAmountDetails);
											aicpubRegStohrStohrinvestInfo1.setPaidAmountDetails(paidAmountDetails);
											aicpubRegStohrInfo.setStohrInvestInfo(aicpubRegStohrStohrinvestInfo1);
										}
										
										}
									}
								stohrInfos.add(aicpubRegStohrInfo);
							}
						}
					}
				}
				
				aicpubRegInfo.setStohrInfos(stohrInfos);
				
				
				//变更信息   gsgsxx_jbbgxx
				List<AicpubRegChangeInfo> changeInfos=new ArrayList<AicpubRegChangeInfo>();
				if(resultHtmlMap.get("gsgsxx_jbbgxx")!=null){
					String bgJsonString=resultHtmlMap.get("gsgsxx_jbbgxx").toString();
					 JSONObject jsonObject;
					try {
						jsonObject = new JSONObject(bgJsonString);
						if(jsonObject.getJSONArray("list")!=null){
							JSONArray jsonList= jsonObject.getJSONArray("list"); 
							if(jsonList.length()!=0){
								  for (int i = 0; i < jsonList.length(); i++) {   
							            jsonObject = jsonList.getJSONObject(i);   
							            AicpubRegChangeInfo aicpubRegChangeInfo=new AicpubRegChangeInfo();
							            if(jsonObject.has("altFiledName")){
							            aicpubRegChangeInfo.setItem(jsonObject.getString("altFiledName")); //变更事项
							            }
							            if(jsonObject.has("altBe")){
							            	aicpubRegChangeInfo.setPreContent(jsonObject.getString("altBe"));//变更前内容	
							            }
							            if(jsonObject.has("altAf")){
							            aicpubRegChangeInfo.setPostContent(jsonObject.getString("altAf"));//变更后内容
							            }
							            if(jsonObject.has("altDate")){
							            	String string=jsonObject.getString("altDate").toString();
											String y=string.split(",")[1].split(" ")[1];//年
											String m=string.split(",")[0].split(" ")[0];//月
											if(m.equals("Jun")){
												m="6";
											}else if (m.equals("Mar")) {
												m="3";
											}else if (m.equals("Jan")) {
												m="1";
											}else if (m.equals("Feb")) {
												m="2";
											}else if (m.equals("Apr")) {
												m="4";
											}else if (m.equals("May")) {
												m="5";
											}else if (m.equals("Jul")) {
												m="7";
											}else if (m.equals("Aug")) {
												m="8";
											}else if (m.equals("Sep")) {
												m="9";
											}else if (m.equals("Oct")) {
												m="10";
											}else if (m.equals("Nov")) {
												m="11";
											}else if (m.equals("Dec")) {
												m="12";
											}
											String d=string.split(",")[0].split(" ")[1];//日
										
							            aicpubRegChangeInfo.setDateTime(y+"年"+m+"月"+d+"日");//变更日期
							            }
							            changeInfos.add(aicpubRegChangeInfo);
							       }
							}
						}
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   
				     
				}
				aicpubRegInfo.setChangeInfos(changeInfos);
				
				/*List<AicpubRegChangeInfo> changeInfos=new ArrayList<AicpubRegChangeInfo>();
//				AicpubRegChangeInfo aicpubRegChangeInfo=new AicpubRegChangeInfo();
				if(resultHtmlMap.get("gsgsxx_bgxx")!=null){
					Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_bgxx").toString()).select("table");
					if(elesGdxx==null || elesGdxx.isEmpty()) {
//						changeInfos=null;
					}else{
						Element ele_resultGdxx=elesGdxx.get(0);//获取第一个table
						Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
						if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//							changeInfos=null;
						}else{
							//变更详情
							List<Element> tableList=new ArrayList<Element>();
							int t=0;
							if(resultHtmlMap.get("gsgsxx_bgxx_detail")!=null){
								@SuppressWarnings("unchecked")
								List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_bgxx_detail");
								if(detailMap!=null && detailMap.size()!=0){
									for(Object detail:detailMap){
										Elements tables = Jsoup.parseBodyFragment(detail.toString()).select("table.detailsList");
										if(tables==null || tables.isEmpty()){}
										else{
											Element table=tables.get(0);
											if (table!=null) {
												tableList.add(table);
											}
										}
									}
								}
								
							}
							
							
								for(int i=0;i<tr_resultsGdxx.size();i++){
									Elements td_resultsGdxx = getElements(tr_resultsGdxx.get(i), "td");
									if(td_resultsGdxx.size()==4){
										AicpubRegChangeInfo aicpubRegChangeInfo=new AicpubRegChangeInfo();
										Element ele_item = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",0);//变更事项
										aicpubRegChangeInfo.setItem(getElementText(ele_item));
										
										AicpubRegChangeInfo.ChangeDetail changeDetail=aicpubRegChangeInfo.new ChangeDetail();
										Element ele_preContent = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);//变更前内容
										if(getElementText(ele_preContent)!=null && getElementText(ele_preContent).equals("查看变更前资料")){
											if(tableList.size()!=0 && t<tableList.size()){
												Element table=tableList.get(t);
												Elements tr_results = getElements(table, "tr");
												if(tr_results.size()>2 && getElementByIndex(table, "tr",2)!=null && getElements(tr_results.get(2), "td").size()==2){
													
													List<AicpubRegChangeInfo.ChangeInfo> preInfos=new ArrayList<AicpubRegChangeInfo.ChangeInfo>();
													for(int a=2;a<tr_results.size();a++){
														if(getElementByIndex(table, "tr",a)!=null && getElements(tr_results.get(a), "td").size()==2){
															AicpubRegChangeInfo.ChangeInfo changeInfo=aicpubRegChangeInfo.new ChangeInfo();
															Element ele1 = getElementByIndex((getElementByIndex(table, "tr",a)), "td",0);//姓名
															changeInfo.setName(getElementText(ele1));
															Element ele2 = getElementByIndex((getElementByIndex(table, "tr",a)), "td",1);//证照号
															changeInfo.setIdNum(getElementText(ele2));
															preInfos.add(changeInfo);
														}
													}
													changeDetail.setPreInfos(preInfos);
												}
												t=t+1;
											}
										}else{
											Element ele_preContent2 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",1);//变更前内容
											aicpubRegChangeInfo.setPreContent(getElementText(ele_preContent2));
										}
										
										Element ele_postContent = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);//变更后内容
										if(getElementText(ele_postContent)!=null && getElementText(ele_postContent).equals("查看变更后资料")){
											if(tableList.size()!=0 && t<tableList.size()){
												Element table=tableList.get(t);
												Elements tr_results = getElements(table, "tr");
												if(tr_results.size()>2 && getElementByIndex(table, "tr",2)!=null && getElements(tr_results.get(2), "td").size()==2){
													
													List<AicpubRegChangeInfo.ChangeInfo> preInfos=new ArrayList<AicpubRegChangeInfo.ChangeInfo>();
													for(int a=2;a<tr_results.size();a++){
														if(getElementByIndex(table, "tr",a)!=null && getElements(tr_results.get(a), "td").size()==2){
															AicpubRegChangeInfo.ChangeInfo changeInfo=aicpubRegChangeInfo.new ChangeInfo();
															Element ele1 = getElementByIndex((getElementByIndex(table, "tr",a)), "td",0);//姓名
															changeInfo.setName(getElementText(ele1));
															Element ele2 = getElementByIndex((getElementByIndex(table, "tr",a)), "td",1);//证照号
															changeInfo.setIdNum(getElementText(ele2));
															preInfos.add(changeInfo);
														}
													}
													changeDetail.setPostInfos(preInfos);
												}
												t=t+1;
											}
										}else{
											Element ele_postContent2 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",2);//变更后内容
											aicpubRegChangeInfo.setPostContent(getElementText(ele_postContent2));
										}
										
										aicpubRegChangeInfo.setDetail(changeDetail);
										
										Element ele_dateTime = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i)), "td",3);//变更日期
										aicpubRegChangeInfo.setDateTime(getElementText(ele_dateTime));
										changeInfos.add(aicpubRegChangeInfo);
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
				aicpubRegInfo.setChangeInfos(changeInfos);*/
				
				aicpubInfo.setRegInfo(aicpubRegInfo);//登记信息
				
		//登记信息********************结束
		
				
		//备案信息********************开始
				
				AicpubArchiveInfo archiveInfo=new AicpubArchiveInfo();
				//主要人员信息
				List<AicpubArchivePrimemberInfo> priMemberInfos=new ArrayList<AicpubArchivePrimemberInfo>();
				if(resultHtmlMap.get("gsgsxx_zyryxx")!=null){
					JSONObject jsonObj;
					try {
						 jsonObj = new JSONObject(resultHtmlMap.get("gsgsxx_zyryxx").toString());
						 JSONArray jsonArray = new JSONArray(jsonObj.get("list").toString());
						 for (int i = 0; i < jsonArray.length(); i++) {
							 JSONObject jsonObj2 = jsonArray.getJSONObject(i);
							 AicpubArchivePrimemberInfo priMemberInfo=new AicpubArchivePrimemberInfo();
							 priMemberInfo.setName(jsonObj2.get("name").toString());//姓名
							 priMemberInfo.setPosition(jsonObj2.get("position").toString());//职务
							 priMemberInfos.add(priMemberInfo);
							 }
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				archiveInfo.setPriMemberInfos(priMemberInfos);
				
				//分支机构信息
				List<AicpubArchiveBranchInfo> branchInfos=new ArrayList<AicpubArchiveBranchInfo>();
				
				if(resultHtmlMap.get("gsgsxx_fzjgxx")!=null){
					Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_fzjgxx").toString()).select("table");
					if(elesGdxx==null || elesGdxx.isEmpty()) {
//						changeInfos=null;
					}else{
						
						if(elesGdxx.size()>1){
						Element ele_resultGdxx=elesGdxx.get(1);//获取第一个table
						Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
						if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//							changeInfos=null;
						}else{
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
				
				archiveInfo.setBranchInfos(branchInfos);
				//清算信息
				AicpubArchiveClearInfo clearInfo=new AicpubArchiveClearInfo();
				if (resultHtmlMap.get("gsgsxx_fzjgxx")!=null && isDebug) {
						clearInfo.setHtml(resultHtmlMap.get("gsgsxx_qsxx").toString());
				}
				archiveInfo.setClearInfo(clearInfo);

				aicpubInfo.setArchiveInfo(archiveInfo);//备案信息
				
				
		//备案信息********************结束
				
				
				//动产抵押登记信息********************开始
				AicpubChatMortgInfo aicpubChatMortgInfo=new AicpubChatMortgInfo();
				if(resultHtmlMap.get("gsgsxx_dcdydjxx")!=null){
					Elements elesGdxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_dcdydjxx").toString()).select("table");
					if(elesGdxx==null || elesGdxx.isEmpty()) {
//						changeInfos=null;
					}else{
						if(elesGdxx.size()>1){
							Element ele_resultGdxx=elesGdxx.get(0);//获取第一个table
							Elements tr_resultsGdxx = getElements(ele_resultGdxx, "tr");
							if (tr_resultsGdxx==null || tr_resultsGdxx.isEmpty()) {
//								changeInfos=null;
							}else{
								if(tr_resultsGdxx.size()>3){
									//动产抵押登记信息
									List<AicpubCChatMortgInfo> chatMortgInfos=new ArrayList<AicpubCChatMortgInfo>();
									for(int i=0;i<(tr_resultsGdxx.size()-2);i++){
										if((getElementByIndex(ele_resultGdxx, "tr",i+2)!=null && getElements(tr_resultsGdxx.get(i+2), "td").size()==7)){
											AicpubCChatMortgInfo aicpubCChatMortgInfo=new AicpubCChatMortgInfo();
											Element ele1 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i+2)), "td",1);
											aicpubCChatMortgInfo.setRegNum(getElementText(ele1));//登记编号
											Element ele2 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i+2)), "td",2);
											aicpubCChatMortgInfo.setRegDateTime(getElementText(ele2));//登记日期
											Element ele3 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i+2)), "td",3);
											aicpubCChatMortgInfo.setRegAuthority(getElementText(ele3));//登记机关
											Element ele4 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i+2)), "td",4);
											aicpubCChatMortgInfo.setGuaranteedDebtAmount(getElementText(ele4));//被担保债权数额
											Element ele5 = getElementByIndex((getElementByIndex(ele_resultGdxx, "tr",i+2)), "td",5);
											aicpubCChatMortgInfo.setStatus(getElementText(ele5));//状态
											
											
											//详情
											if(resultHtmlMap.get("gsgsxx_dcdydjxx_detail")!=null){
												@SuppressWarnings("unchecked")
												List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("gsgsxx_dcdydjxx_detail");
												Elements tables = Jsoup.parseBodyFragment(detailMap.get(i).toString()).select("div#details").select("table");
												if(tables==null || tables.isEmpty()){}
												else{
													AicpubCChatMortgDetail mortgDetail = new AicpubCChatMortgDetail();
													for(int g=0;g<tables.size();g++){
														
														Element gsgsDcdydjDetailTable=tables.get(g);
														
														if(getElements(gsgsDcdydjDetailTable, "tr")!=null && getElements(gsgsDcdydjDetailTable, "tr").size()>0){
														
														String gsgsDcdydjDetailTitle = gsgsDcdydjDetailTable.select("th").get(0).text();
														
														Elements gsgsDcdydjDetailThs = gsgsDcdydjDetailTable.select("th");
														Elements gsgsDcdydjDetailTds = gsgsDcdydjDetailTable.select("td");
														gsgsDcdydjDetailThs.remove(0);
														
														if ("动产抵押登记信息".equals(gsgsDcdydjDetailTitle)) {
															AicpubCChatMortgRegInfo mortgRegInfo = new AicpubCChatMortgRegInfo();
															
															for (int x = 0; x < gsgsDcdydjDetailThs.size(); x++) {
																String gsgsDcdydjDetailTh = gsgsDcdydjDetailThs.get(x).text().replace(" ", "");
																String gsgsDcdydjDetailTd = gsgsDcdydjDetailTds.get(x).text().replace(" ", "");
																
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
																	case "被担保债权种类":
																		mortgRegInfo.setGuaranteedDebtType(gsgsDcdydjDetailTd);
																		break;
																	case "被担保债权数额":
																		mortgRegInfo.setGuaranteedDebtAmount(gsgsDcdydjDetailTd);
																		break;
																	case "债务人履行债务的期限":
																		mortgRegInfo.setTerm(gsgsDcdydjDetailTd);
																		break;
																	case "担保范围":
																		mortgRegInfo.setGuaranteedScope(gsgsDcdydjDetailTd);
																		break;
																	case "备注":
																		mortgRegInfo.setNote(gsgsDcdydjDetailTd);
																		break;
																	default:
																		break;
																}
															}
															
															mortgDetail.setMortgRegInfo(mortgRegInfo);
															
														} else if ("被担保债权概况".equals(gsgsDcdydjDetailTitle)) {
															AicpubCChatMortgGuaranteedInfo mortgGuaranteedInfo = new AicpubCChatMortgGuaranteedInfo();
															
															for (int a = 0; a < gsgsDcdydjDetailThs.size(); a++) {
																String gsgsDcdydjDetailTh = gsgsDcdydjDetailThs.get(a).text().replace(" ", "");
																String gsgsDcdydjDetailTd = gsgsDcdydjDetailTds.get(a).text().replace(" ", "");
																
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
															
														} else if ("抵押权人概况".equals(gsgsDcdydjDetailTitle)) {
															List<AicpubCChatMortgPersonInfo> mortgPersonInfos=new ArrayList<AicpubCChatMortgPersonInfo>();
															Elements tr_stohrInvestInfo = getElements(gsgsDcdydjDetailTable, "tr");
															if (tr_stohrInvestInfo==null || tr_stohrInvestInfo.isEmpty()) {
//																stohrInvestInfos=null;
															}else{
																if(tr_stohrInvestInfo.size()>3 && getElementByIndex(gsgsDcdydjDetailTable, "tr",3)!=null && getElements(tr_stohrInvestInfo.get(3), "td").size()==4){
																	for(int b=0;b<(tr_stohrInvestInfo.size()-3);b++){
																		AicpubCChatMortgPersonInfo aicpubCChatMortgPersonInfo=new AicpubCChatMortgPersonInfo();
																		Element el1 = getElementByIndex((getElementByIndex(gsgsDcdydjDetailTable, "tr",b+3)), "td",1);
																		aicpubCChatMortgPersonInfo.setName(getElementText(el1));//抵押权人名称
																		Element el2 = getElementByIndex((getElementByIndex(gsgsDcdydjDetailTable, "tr",b+3)), "td",2);
																		aicpubCChatMortgPersonInfo.setType(getElementText(el2));//抵押权人证照/证件类型
																		Element el3 = getElementByIndex((getElementByIndex(gsgsDcdydjDetailTable, "tr",b+3)), "td",3);
																		aicpubCChatMortgPersonInfo.setNum(getElementText(el3));//证照/证件号码
																		mortgPersonInfos.add(aicpubCChatMortgPersonInfo);
																	}
																}
																mortgDetail.setMortgPersonInfos(mortgPersonInfos);
															}
															
														}else if ("抵押物概况".equals(gsgsDcdydjDetailTitle)) {
															List<AicpubCChatMortgGoodsInfo> mortgGoodsInfos=new ArrayList<AicpubCChatMortgGoodsInfo>();
															Elements tr_stohrInvestInfo = getElements(gsgsDcdydjDetailTable, "tr");
															if (tr_stohrInvestInfo==null || tr_stohrInvestInfo.isEmpty()) {
//																stohrInvestInfos=null;
															}else{
																if(tr_stohrInvestInfo.size()>3 && getElementByIndex(gsgsDcdydjDetailTable, "tr",3)!=null && getElements(tr_stohrInvestInfo.get(3), "td").size()==5){
																	for(int b=0;b<(tr_stohrInvestInfo.size()-3);b++){
																		AicpubCChatMortgGoodsInfo aicpubCChatMortgGoodsInfo=new AicpubCChatMortgGoodsInfo();
																		Element e1 = getElementByIndex((getElementByIndex(gsgsDcdydjDetailTable, "tr",b+3)), "td",1);
																		aicpubCChatMortgGoodsInfo.setName(getElementText(e1));//名称 
																		Element e2 = getElementByIndex((getElementByIndex(gsgsDcdydjDetailTable, "tr",b+3)), "td",2);
																		aicpubCChatMortgGoodsInfo.setOwnerShip(getElementText(e2));//所有权归属 
																		Element e3 = getElementByIndex((getElementByIndex(gsgsDcdydjDetailTable, "tr",b+3)), "td",3);
																		aicpubCChatMortgGoodsInfo.setGeneralSituation(getElementText(e3));//数量、质量、状况、所在地等情况 
																		Element e4 = getElementByIndex((getElementByIndex(gsgsDcdydjDetailTable, "tr",b+3)), "td",4);
																		aicpubCChatMortgGoodsInfo.setNote(getElementText(e4));//备注
																		mortgGoodsInfos.add(aicpubCChatMortgGoodsInfo);
																	}
																}
																mortgDetail.setMortgGoodsInfos(mortgGoodsInfos);
															}
															
														}
														
														
														}
													}
													aicpubCChatMortgInfo.setMortgDetail(mortgDetail);
												}
											}
											
											
											
											
											chatMortgInfos.add(aicpubCChatMortgInfo);
										}
									}
									aicpubChatMortgInfo.setChatMortgInfos(chatMortgInfos);
								}
								
							}
						}
						
						}
				}
				
					if(resultHtmlMap.get("gsgsxx_dcdydjxx")!=null && isDebug){
				aicpubChatMortgInfo.setHtml(resultHtmlMap.get("gsgsxx_dcdydjxx").toString());
					}
				aicpubInfo.setChatMortgInfo(aicpubChatMortgInfo); //AicpubChatMortgInfo
				//动产抵押登记信息********************结束
		
				
				//股权出质登记信息********************开始
				AicpubEqumortgregInfo equMortgRegInfo=new AicpubEqumortgregInfo();
				 if(resultHtmlMap.get("gsgsxx_gqczdjxx")!=null){
					 Elements elesgqczxx = Jsoup.parseBodyFragment(resultHtmlMap.get("gsgsxx_gqczdjxx").toString()).select("table.detailsList");
						if(elesgqczxx==null || elesgqczxx.isEmpty()) {
//							equMortgRegInfo=null;
						}else{
							Element ele_resultzyryxx=elesgqczxx.get(0);//获取第一个table
							Elements tr_resultszyryxx = getElements(ele_resultzyryxx, "tr");
							if (tr_resultszyryxx==null || tr_resultszyryxx.isEmpty()) {
//								equMortgRegInfo=null;
							}else{
								if(tr_resultszyryxx.size()>2 && getElementByIndex(ele_resultzyryxx, "tr",2)!=null && getElements(tr_resultszyryxx.get(2), "td").size()==10 ){
									List<AicpubEEqumortgregInfo> equmortgregInfos=new ArrayList<AicpubEEqumortgregInfo>();
									for(int i=2;i<tr_resultszyryxx.size();i++){
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
										/*Element ele9 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",9);
										aicpubEEqumortgregInfo.setPubDate(getElementText(ele9));//公示时间
*/										Element ele10 = getElementByIndex((getElementByIndex(ele_resultzyryxx, "tr",i)), "td",9);
										aicpubEEqumortgregInfo.setChangeSitu(getElementText(ele10));//变化情况
										equmortgregInfos.add(aicpubEEqumortgregInfo);
									}
									equMortgRegInfo.setEqumortgregInfos(equmortgregInfos);
								}
							}
						}
				}
				
				
				
					if(resultHtmlMap.get("gsgsxx_gqczdjxx")!=null && isDebug){
				equMortgRegInfo.setHtml(resultHtmlMap.get("gsgsxx_gqczdjxx").toString());
					}
				aicpubInfo.setEquMortgRegInfo(equMortgRegInfo); 
				//股权出质登记信息********************结束
				
				//行政处罚信息********************开始
				AicpubAdmpunishInfo aicpubAdmpunishInfo=new AicpubAdmpunishInfo();
					if(resultHtmlMap.get("gsgsxx_xzcfxx")!=null && isDebug){
				aicpubAdmpunishInfo.setHtml(resultHtmlMap.get("gsgsxx_xzcfxx").toString());
				}
				aicpubInfo.setAdmPunishInfo(aicpubAdmpunishInfo);// AicpubAdmpunishInfo
				//行政处罚信息********************结束
				
				//经营异常信息********************开始
				AicpubOperanomaInfo aicpubOperanomaInfo=new AicpubOperanomaInfo();
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
				
				//抽查检查信息********************开始
				AicpubCheckInfo aicpubCheckInfo=new AicpubCheckInfo();
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
				List<EntpubAnnreportInfo> entpubAnnreportInfos=new ArrayList<EntpubAnnreportInfo>();
				if(resultHtmlMap.get("qygsxx_qynb")!=null){
					Elements elesqynb = Jsoup.parseBodyFragment(resultHtmlMap.get("qygsxx_qynb").toString()).select("table");
					if(elesqynb==null || elesqynb.isEmpty()) {
//						annReports=null;
					}else{
						Element ele_resultqynb=elesqynb.get(0);
						Elements tr_resultsqynb = getElements(ele_resultqynb, "tr");
						if (tr_resultsqynb==null || tr_resultsqynb.isEmpty()) {
//							annReports=null;
						}else{
							for(int i=0;i<(tr_resultsqynb.size()-1);i++){
								Elements tds = getElements(tr_resultsqynb.get(1),"td");
								Elements ths = getElements(tr_resultsqynb.get(1),"th");
								if(tds.size()>=3 || ths.size()>=3){
									EntpubAnnreportInfo annReports=new EntpubAnnreportInfo();
								if(tds.size()==3){
									Element ele_submitYear = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i+1)), "td",1);//报送年度
									annReports.setSubmitYear(getElementText(ele_submitYear));
									Element ele_pubDateTime = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i+1)), "td",2);//发布日期
									annReports.setPubDateTime(getElementText(ele_pubDateTime));
								}else if(ths.size()==4 && getElements(tr_resultsqynb.get(2),"td").size()==4){
									if(i+2<tr_resultsqynb.size()){
										Element ele_submitYear = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i+2)), "td",1);//报送年度
										annReports.setSubmitYear(getElementText(ele_submitYear));
										Element ele_pubDateTime = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i+2)), "td",2);//发布日期
										annReports.setPubDateTime(getElementText(ele_pubDateTime));
										Element ele3 = getElementByIndex((getElementByIndex(ele_resultqynb, "tr",i+2)), "td",3);//发布日期
										annReports.setRemarks(getElementText(ele3));
									}
								}
							
								
								//年报详情
								if(resultHtmlMap.get("qygsxx_qynb_detail")!=null){
									@SuppressWarnings("unchecked")
									List<Object> detailMap = (ArrayList<Object>) resultHtmlMap.get("qygsxx_qynb_detail");
									if((detailMap.size()-1)>=i){
										Elements tables = Jsoup.parseBodyFragment(detailMap.get(i).toString()).select("table");
										if(tables==null || tables.isEmpty()){}
										else{
											for(int g=0;g<tables.size();g++){
												Element qygsxxQynb13Table=tables.get(g);
//											for (Element qygsxxQynb13Table : tables) {
												if(getElements(qygsxxQynb13Table, "tr").size()>0){
												String qygsxxQynbTitle = qygsxxQynb13Table.select("th").get(0).text();
												
												if (qygsxxQynbTitle.indexOf("年度报告") > 0) {
													qygsxxQynbTitle = qygsxxQynb13Table.select("th").get(1).text();
													qygsxxQynb13Table=qygsxxQynb13Table.nextElementSibling();
												}
												
												
												Elements qygsxxQynb13Ths = qygsxxQynb13Table.select("th");
												Elements qygsxxQynb13Tds = qygsxxQynb13Table.select("td");
//												qygsxxQynb13Ths.remove(0);
												
												if ("企业基本信息".equals(qygsxxQynbTitle) || "基本信息".equals(qygsxxQynbTitle)) {
													EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
//													qygsxxQynb13Ths.remove(0);
													for (int a = 0; a < qygsxxQynb13Ths.size(); a++) {
														String qygsxxQynb13Th = qygsxxQynb13Ths.get(a).text().replace(" ", "");
														if(qygsxxQynb13Ths.get(a).nextElementSibling()!=null){
														String qygsxxQynb13Td = qygsxxQynb13Ths.get(a).nextElementSibling().text();
														if(qygsxxQynb13Th.equals("资金数额")){
															qygsQynbJbInfo.setCapitalAmount(qygsxxQynb13Td);
														}else if(qygsxxQynb13Th.equals("经营者")){
															qygsQynbJbInfo.setOperator(qygsxxQynb13Td);
														}
														switch (qygsxxQynb13Th) {
															case "注册号/统一社会信用代码":
																qygsQynbJbInfo.setNum(qygsxxQynb13Td);
																break;
															case "统一社会信用代码":
																qygsQynbJbInfo.setNum(qygsxxQynb13Td);
																break;
															case "注册号":
																qygsQynbJbInfo.setBusinessLicenceNum(qygsxxQynb13Td);
																break;
															case "营业执照注册号":
																qygsQynbJbInfo.setBusinessLicenceNum(qygsxxQynb13Td);
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
															case "经营者 ":
																qygsQynbJbInfo.setOperator(qygsxxQynb13Td);
																break;
															case "资金数额 ":
																qygsQynbJbInfo.setCapitalAmount(qygsxxQynb13Td);
																break;
															case "企业是否有对外投资设立企业信息":
																qygsQynbJbInfo.setHasInvestInfoOrPurchOtherCorpEqu(qygsxxQynb13Td);
																break;
															default:
																break;
														}
													}
													}
													
													annReports.setBaseInfo(qygsQynbJbInfo);
													
												} else if ("企业资产状况信息".equals(qygsxxQynbTitle) || "企业资产状况信息(人民币)".equals(qygsxxQynbTitle) || "生产经营情况信息".equals(qygsxxQynbTitle) || "生产经营情况".equals(qygsxxQynbTitle) || "资产状况信息".equals(qygsxxQynbTitle)) {
													qygsxxQynb13Ths.remove(0);
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
													qygsxxQynb13Ths.remove(0);
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
													qygsxxQynb13Ths.remove(0);
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
													qygsxxQynb13Ths.remove(0);
													List<EntpubAnnreportAdmlicenseInfo> admlicenseInfos=new ArrayList<EntpubAnnreportAdmlicenseInfo>();
													Elements tr_websiteInfo = getElements(qygsxxQynb13Table, "tr");
													if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//														websiteInfos=null;
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
													qygsxxQynb13Ths.remove(0);
													List<EntpubAnnreportModifyInfo> changeInfos2=new ArrayList<EntpubAnnreportModifyInfo>();
													Elements tr_websiteInfo = getElements(qygsxxQynb13Table, "tr");
													if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//														websiteInfos=null;
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
													
												}else if ("对外投资信息".equals(qygsxxQynbTitle)) {
													qygsxxQynb13Ths.remove(0);
//													List<EntpubAnnreportWebsiteInfo> websiteInfos=new ArrayList<EntpubAnnreportWebsiteInfo>();
													List<EntpubAnnreportExtinvestInfo> extInvestInfos=new ArrayList<EntpubAnnreportExtinvestInfo>();
													Elements tr_websiteInfo = getElements(qygsxxQynb13Table, "tr");
													if (tr_websiteInfo==null || tr_websiteInfo.isEmpty()) {
//														websiteInfos=null;
													}else{
														for(int b=0;b<(tr_websiteInfo.size()-2);b++){
															Elements td_resultsGdxx1 = getElements(tr_websiteInfo.get(b+2), "td");
															if(td_resultsGdxx1.size()==2){
																EntpubAnnreportExtinvestInfo entpubAnnreportExtinvestInfo=new EntpubAnnreportExtinvestInfo();
																Element ele_type = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",0);//投资设立企业或购买股权企业名称
																entpubAnnreportExtinvestInfo.setEnterpriseName(getElementText(ele_type));
																Element ele_name = getElementByIndex((getElementByIndex(qygsxxQynb13Table, "tr",b+2)), "td",1);//注册号
																entpubAnnreportExtinvestInfo.setRegNum(getElementText(ele_name));
																extInvestInfos.add(entpubAnnreportExtinvestInfo);
															}
															
															
														}
													}
													annReports.setExtInvestInfos(extInvestInfos);
												}else if ("股权变更信息".equals(qygsxxQynbTitle)) {
													qygsxxQynb13Ths.remove(0);
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
									
									
								}
								entpubAnnreportInfos.add(annReports);	
							}//
							}
						}
						
					}
				}
				
				entpubInfo.setAnnReports(entpubAnnreportInfos);
				
				//股东及出资信息
				EntpubStohrinvestInfo stohrInvestInfo=new EntpubStohrinvestInfo();
				if(resultHtmlMap.get("qygsxx_gdjczxx")!=null && isDebug){
				stohrInvestInfo.setHtml(resultHtmlMap.get("qygsxx_gdjczxx").toString());
				}
				entpubInfo.setStohrInvestInfo(stohrInvestInfo);
				
				//股权变更信息
				EntpubEquchangeInfo equChangeInfo=new EntpubEquchangeInfo();
				if(resultHtmlMap.get("qygsxx_gqbgxx")!=null && isDebug){
				equChangeInfo.setHtml(resultHtmlMap.get("qygsxx_gqbgxx").toString());
				}
				entpubInfo.setEquChangeInfo(equChangeInfo);
				
				//行政许可信息
				EntpubAdmlicInfo admLicInfo=new EntpubAdmlicInfo();
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
				JudasspubInfo judAssPubInfo=new JudasspubInfo();
				
				//股权冻结信息
				JudasspubEqufreezeInfo equFreezeInfo=new JudasspubEqufreezeInfo();
					if(resultHtmlMap.get("sfxzgsxx_gqdj")!=null && isDebug){
				equFreezeInfo.setHtml(resultHtmlMap.get("sfxzgsxx_gqdj").toString());
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

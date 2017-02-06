package com.crawlermanage.service.transfer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

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
import com.crawler.gsxt.domain.json.AicpubRegRevokeInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrInfo;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo;
import com.crawler.gsxt.domain.json.AicpubSSerillegalInfo;
import com.crawler.gsxt.domain.json.AicpubSerillegalInfo;
import com.crawler.gsxt.domain.json.EntpubAAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAdmlicInfo;
import com.crawler.gsxt.domain.json.EntpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.EntpubAnnreportAdmlicenseInfo;
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
import com.crawler.gsxt.domain.json.EntpubStohrinvestChangeInfo;
import com.crawler.gsxt.domain.json.EntpubStohrinvestInfo;
import com.crawler.gsxt.domain.json.JudasspubEEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubEqufreezeInfo;
import com.crawler.gsxt.domain.json.JudasspubInfo;
import com.crawler.gsxt.domain.json.JudasspubSStohrchangeInfo;
import com.crawler.gsxt.domain.json.JudasspubStohrchangeInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAAdmlicInfoDetail;
import com.crawler.gsxt.domain.json.OthrdeptpubAAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmlicChangeInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmlicInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubAdmpunishInfo;
import com.crawler.gsxt.domain.json.OthrdeptpubInfo;
import com.crawler.gsxt.domain.json.AicpubAAdmpunishInfo.PunishDetail;
import com.crawler.gsxt.domain.json.AicpubRegChangeInfo.ChangeDetail;
import com.crawler.gsxt.domain.json.AicpubRegStohrStohrinvestInfo.AmountDetail;
import com.crawler.gsxt.domain.json.EntpubSStohrinvestInfo.Detail;
import com.module.dao.entity.gsxt.TBgxx;
import com.module.dao.entity.gsxt.TBgxxxq;
import com.module.dao.entity.gsxt.TCcjcxx;
import com.module.dao.entity.gsxt.TCxxx;
import com.module.dao.entity.gsxt.TDcdydjxx;
import com.module.dao.entity.gsxt.TDcdydjxxbg;
import com.module.dao.entity.gsxt.TDwtgbzdbxx;
import com.module.dao.entity.gsxt.TDwtzxx;
import com.module.dao.entity.gsxt.TDyrgk;
import com.module.dao.entity.gsxt.TDywgk;
import com.module.dao.entity.gsxt.TFzjgxx;
import com.module.dao.entity.gsxt.TGdbgxx;
import com.module.dao.entity.gsxt.TGdjczxx;
import com.module.dao.entity.gsxt.TGqbgxx;
import com.module.dao.entity.gsxt.TGqczdjxx;
import com.module.dao.entity.gsxt.TGqdjxx;
import com.module.dao.entity.gsxt.TGsxtHtml;
import com.module.dao.entity.gsxt.TJyychyzwfxx;
import com.module.dao.entity.gsxt.TMember;
import com.module.dao.entity.gsxt.TQsxx;
import com.module.dao.entity.gsxt.TQtbmgsXzxkxx;
import com.module.dao.entity.gsxt.TQygsGdjczxx;
import com.module.dao.entity.gsxt.TQyjbxx;
import com.module.dao.entity.gsxt.TQynb;
import com.module.dao.entity.gsxt.TRjhsjmx;
import com.module.dao.entity.gsxt.TScjyqk;
import com.module.dao.entity.gsxt.TWzhwdxx;
import com.module.dao.entity.gsxt.TXzcfxx;
import com.module.dao.entity.gsxt.TXzcfxxxq;
import com.module.dao.entity.gsxt.TXzxkxx;
import com.module.dao.entity.gsxt.TZgbmxx;
import com.module.dao.entity.gsxt.TZscqczdjxx;
import com.module.dao.entity.gsxt.TZyryxx;

/**
 * @author kingly
 * @date 2016年5月27日
 * 工商系统对象转化工具
 */
public class GsxtPoVoTransfer {
	private static final Logger LOGGER = Logger.getLogger(GsxtPoVoTransfer.class);
	
	//TQyjbxx-->AicFeedJson
	public static AicFeedJson poToVo(TQyjbxx tq) {
		
		   AicFeedJson aicFeedJson=new AicFeedJson();
		
		   /**工商公示信息**/
		   AicpubInfo aicPubInfo=new AicpubInfo();
		   
		   /*
		    *   登记信息
		    */
		   AicpubRegInfo regInfo=new AicpubRegInfo();
		   
		   //基本信息
		   AicpubRegBaseInfo baseInfo=new AicpubRegBaseInfo();
		   baseInfo.setNum(tq.getNum());
		   baseInfo.setName(tq.getName());
		   baseInfo.setType(tq.getType());
		   baseInfo.setLegalRepr(tq.getLegalrepr());
		   baseInfo.setRegCapital(tq.getRegisteredcapital());
		   baseInfo.setRegDateTime(tq.getRegistereddate());
		   baseInfo.setFormType(tq.getFormtype());
		   baseInfo.setAddress(tq.getAddress());
		   baseInfo.setStartDateTime(tq.getStartdate());
		   baseInfo.setEndDateTime(tq.getEnddate());
		   baseInfo.setBusinessScope(tq.getBusinessscope());
		   baseInfo.setRegAuthority(tq.getRegauthority());
		   baseInfo.setApprovalDateTime(tq.getApprovaldate());
		   baseInfo.setRegStatus(tq.getRegstatus());
		   baseInfo.setRevokeDate(tq.getRevokedate());
		   baseInfo.setHtml(tq.getHtml());
		   
		 //股东信息
		   Set<TMember> tMembers=tq.getTMembers();
		   List<AicpubRegStohrInfo> stohrInfos=new ArrayList<AicpubRegStohrInfo>();
		   if(tMembers!=null){
			       for(Iterator<TMember> iter=tMembers.iterator();iter.hasNext();){
				           TMember t=iter.next();
				           AicpubRegStohrInfo aicpubRegStohrInfo=new AicpubRegStohrInfo();
				          
				           aicpubRegStohrInfo.setType(t.getGdtype());
				           aicpubRegStohrInfo.setName(t.getName());
				           aicpubRegStohrInfo.setIdType(t.getIdtype());
				           aicpubRegStohrInfo.setIdNum(t.getIdnum());
				          
				           TGdjczxx t2=t.getTGdjczxx();
				           AicpubRegStohrStohrinvestInfo stohrInvestInfo=new AicpubRegStohrStohrinvestInfo();
				           if(t2!=null){
				        	   stohrInvestInfo.setStockholder(t2.getStockholder());
				        	   stohrInvestInfo.setSubAmount(t2.getRjczamount());
				        	   stohrInvestInfo.setPaidAmount(t2.getSjczamount());
				        	   
				        	   
				           }
				           
				           aicpubRegStohrInfo.setStohrInvestInfo(stohrInvestInfo);
				           
				          
				          
			       }
			   
			   
		   }
		   
		 //变更信息（包括变更信息详细）
		 //撤销信息
		   
		   
		   /*
		    *   备案信息
		    */
		   AicpubArchiveInfo archiveInfo=new AicpubArchiveInfo();
		   
		   /*
		    *   动产抵押登记信息
		    */
		   AicpubChatMortgInfo chatMortgInfo=new AicpubChatMortgInfo();
		   
		   /*
		    *   股权出资登记信息
		    */
		   AicpubEqumortgregInfo equMortgRegInfo=new AicpubEqumortgregInfo();
		   
		   /*
		    *   行政处罚信息
		    */
		   AicpubAdmpunishInfo admPunishInfo=new AicpubAdmpunishInfo();
		   
		   /*
		    *   经营异常信息
		    */
		   AicpubOperanomaInfo operAnomaInfo=new AicpubOperanomaInfo();
		   
		   /*
		    *  严重违法信息
		    */
		   AicpubSerillegalInfo serIllegalInfo=new AicpubSerillegalInfo();
		   
		   
		   /*
		    *   抽查检查信息
		    */
		   AicpubCheckInfo checkInfo=new AicpubCheckInfo();
		   
		   
		   
		   /*
		    * 生成  工商公示信息  aicPubInfo
		    * 
		    * */
		   aicPubInfo.setRegInfo(regInfo);
		   aicPubInfo.setArchiveInfo(archiveInfo);
		   aicPubInfo.setChatMortgInfo(chatMortgInfo);
		   aicPubInfo.setEquMortgRegInfo(equMortgRegInfo);
		   aicPubInfo.setAdmPunishInfo(admPunishInfo);
		   aicPubInfo.setOperAnomaInfo(operAnomaInfo);
		   aicPubInfo.setSerIllegalInfo(serIllegalInfo);
		   aicPubInfo.setCheckInfo(checkInfo);
		   
		   /**企业公示信息**/
		   EntpubInfo entPubInfo=new EntpubInfo();
		   
		   /**其他部门公示信息**/
		   OthrdeptpubInfo othrDeptPubInfo=new OthrdeptpubInfo();
		   
		  /**司法协助公示信息**/
		   JudasspubInfo judAssPubInfo=new JudasspubInfo();
		
		
		
		
		
		/**组合成接口json对象**/
		   aicFeedJson.setAicPubInfo(aicPubInfo);
		   aicFeedJson.setEntPubInfo(entPubInfo);
		   aicFeedJson.setOthrDeptPubInfo(othrDeptPubInfo);
		   aicFeedJson.setJudAssPubInfo(judAssPubInfo);
		
		
		   return aicFeedJson;
	}
	
	
	
	//AicFeedJson-->TQyjbxx
	public static TQyjbxx voToPo(AicFeedJson aicFeedJson) {
		TQyjbxx qyjbxx = null;
		
		if (aicFeedJson != null) {
			qyjbxx = new TQyjbxx();
			
			TGsxtHtml gsxtHtml = new TGsxtHtml();
			gsxtHtml.setTQyjbxx(qyjbxx);
			qyjbxx.setTGsxtHtml(gsxtHtml);
			//工商公示信息
			AicpubInfo gsgsInfo = aicFeedJson.getAicPubInfo();

			if (gsgsInfo != null) {
				//登记信息
				AicpubRegInfo gsgsDjInfo = gsgsInfo.getRegInfo();

				if (gsgsDjInfo != null) {

					//基本信息
					AicpubRegBaseInfo gsgsDjJbInfo = gsgsDjInfo
							.getBaseInfo();
					if (gsgsDjJbInfo != null) {
						//信息类型
						qyjbxx.setInfotype("工商公示信息-登记信息-基本信息");
						//注册号 或 信用代码
						String num = gsgsDjJbInfo.getNum();
						String creditNum = gsgsDjJbInfo.getCreditNum();
						if (StringUtils.isEmpty(creditNum)) {
							qyjbxx.setNum(num);
						} else if (StringUtils.isEmpty(num)) {
							qyjbxx.setNum(creditNum);
						} else if (!StringUtils.isEmpty(creditNum) && !StringUtils.isEmpty(num)) {
							qyjbxx.setNum(num + "/" + creditNum);
						}

						//名称
						String name = gsgsDjJbInfo.getName();
						qyjbxx.setName(name);

						//类型
						String type = gsgsDjJbInfo.getType();
						qyjbxx.setType(type);

						//法定代表人/经营者
						String legalRepr = gsgsDjJbInfo.getLegalRepr();
						qyjbxx.setLegalrepr(legalRepr);

						//注册资本
						String registeredcapital = gsgsDjJbInfo
								.getRegCapital();
						qyjbxx.setRegisteredcapital(registeredcapital);

						//成立日期
						String registeredDate = gsgsDjJbInfo
								.getRegDateTime();
						qyjbxx.setRegistereddate(registeredDate);

						//组成形式
						String formType = gsgsDjJbInfo.getFormType();
						qyjbxx.setFormtype(formType);
						
						//经营场所/住所
						String address = gsgsDjJbInfo.getAddress();
						qyjbxx.setAddress(address);

						//营业期限自（即营业开始日期）
						String startDate = gsgsDjJbInfo.getStartDateTime();
						qyjbxx.setStartdate(startDate);

						//营业期限至（即营业结束日期）
						String endDate = gsgsDjJbInfo.getEndDateTime();
						qyjbxx.setEnddate(endDate);

						//经营范围
						String businessScope = gsgsDjJbInfo
								.getBusinessScope();
						qyjbxx.setBusinessscope(businessScope);

						//登记机关
						String regAuthority = gsgsDjJbInfo
								.getRegAuthority();
						qyjbxx.setRegauthority(regAuthority);

						//核准日期
						String approvalDate = gsgsDjJbInfo
								.getApprovalDateTime();
						qyjbxx.setApprovaldate(approvalDate);

						//登记状态
						String regStatus = gsgsDjJbInfo.getRegStatus();
						qyjbxx.setRegstatus(regStatus);

						//吊销日期
						String revokeDate = gsgsDjJbInfo
								.getRevokeDate();
						qyjbxx.setRevokedate(revokeDate);
						//html内容
						String html = gsgsDjJbInfo.getHtml();
						qyjbxx.setHtml(html);

					}

					//股东信息
					List<AicpubRegStohrInfo> gsgsDjGdInfos = null;
					if (gsgsDjInfo!=null) {
						gsgsDjGdInfos = gsgsDjInfo.getStohrInfos();
					}
					if (gsgsDjGdInfos != null) {
						Set<TMember> tMembers = new LinkedHashSet<TMember>();
						for (AicpubRegStohrInfo gsgsDjGdInfo : gsgsDjGdInfos) {

							TMember member = new TMember();

							member.setTQyjbxx(qyjbxx);
							//信息类型
							member.setInfotype("工商公示信息-登记信息-股东信息");
							//股东类型
							String gdType = gsgsDjGdInfo.getType();
							member.setGdtype(gdType);

							//股东名称
							String gdName = gsgsDjGdInfo.getName();
							member.setName(gdName);

							//是否是股东
							member.setIsgd("是");

							//证照/证件类型
							String idType = gsgsDjGdInfo.getIdType();
							member.setIdtype(idType);

							//证照/证件号码
							String idNum = gsgsDjGdInfo.getIdNum();
							member.setIdnum(idNum);

							//投资方式
							String sconform = gsgsDjGdInfo.getSconform();
							member.setSconform(sconform);
							
							//股东及出资信息
							AicpubRegStohrStohrinvestInfo gsgsDjGdGdjczInfo = gsgsDjGdInfo
									.getStohrInvestInfo();
							if (gsgsDjGdGdjczInfo != null) {

								TGdjczxx gdjczxx = new TGdjczxx();
								gdjczxx.setTMember(member);
								//信息类型
								gdjczxx.setInfotype("工商公示信息-登记信息-股东信息-股东及出资信息");

								//股东
								String stockholder = gsgsDjGdGdjczInfo
										.getStockholder();
								gdjczxx.setStockholder(stockholder);
								
								//发起人类型
								String stockType = gsgsDjGdGdjczInfo.getStockType();
								gdjczxx.setStockType(stockType);

								//认缴额（万元）
								String subAmount = gsgsDjGdGdjczInfo
										.getSubAmount();
								gdjczxx.setRjczamount(subAmount);

								//实缴额（万元）
								String paidAmount = gsgsDjGdGdjczInfo
										.getPaidAmount();
								gdjczxx.setSjczamount(paidAmount);

								List<AmountDetail> subAmountDetails = gsgsDjGdGdjczInfo
										.getSubAmountDetails();

								List<AmountDetail> paidAmountDetails = gsgsDjGdGdjczInfo
										.getPaidAmountDetails();

								if (subAmountDetails != null
										|| paidAmountDetails != null) {

									Set<TRjhsjmx> rjhsjmxs = new LinkedHashSet<TRjhsjmx>();
									if (subAmountDetails != null) {
										for (AmountDetail amountDetail : subAmountDetails) {

											TRjhsjmx rje = new TRjhsjmx();
											rje.setTGdjczxx(gdjczxx);
											//类型
											rje.setType("认缴");
											//出资方式
											String method = amountDetail.investMethod;
											rje.setMethod(method);
											//出资额（万元）
											String amount = amountDetail.investAmount;
											rje.setAmount(amount);
											//出资日期
											String czDate = amountDetail.investDateTime;
											rje.setCzdate(czDate);
											rjhsjmxs.add(rje);
										}
									}
									if (paidAmountDetails != null) {
										for (AmountDetail amountDetail : paidAmountDetails) {

											TRjhsjmx sje = new TRjhsjmx();
											sje.setTGdjczxx(gdjczxx);
											//类型
											sje.setType("实缴");
											//出资方式
											String method = amountDetail.investMethod;
											sje.setMethod(method);
											//出资额（万元）
											String amount = amountDetail.investAmount;
											sje.setAmount(amount);
											//出资日期
											String czDate = amountDetail.investDateTime;
											sje.setCzdate(czDate);

											rjhsjmxs.add(sje);

										}
									}
									gdjczxx.setTRjhsjmxs(rjhsjmxs);
								}
								//html内容
								String html = gsgsDjGdGdjczInfo
										.getHtml();
								gdjczxx.setHtml(html);

								member.setTGdjczxx(gdjczxx);

							}
							//html内容
							String html = gsgsDjGdInfo.getHtml();
							member.setHtml(html);
							tMembers.add(member);
						}
						qyjbxx.setTMembers(tMembers);
					}
					//变更信息（包括变更信息详细）
					List<AicpubRegChangeInfo> gsgsDjBgInfos = gsgsDjInfo
							.getChangeInfos();
					if (gsgsDjBgInfos != null) {
						Set<TBgxx> tBgxxs = new LinkedHashSet<TBgxx>();
						for (AicpubRegChangeInfo gsgsDjBgInfo : gsgsDjBgInfos) {

							TBgxx bgxx = new TBgxx();
							bgxx.setTQyjbxx(qyjbxx);

							//类型
							bgxx.setInfotype("工商公示信息-登记信息-变更信息");
							//变更事项
							String bgItem = gsgsDjBgInfo.getItem();
							bgxx.setBgitem(bgItem);

							//变更前内容
							String bgqContent = gsgsDjBgInfo
									.getPreContent();
							bgxx.setBgqcontent(bgqContent);

							//变更后内容
							String bghContent = gsgsDjBgInfo
									.getPostContent();
							bgxx.setBghcontent(bghContent);

							//变更日期
							String bgDate = gsgsDjBgInfo.getDateTime();
							bgxx.setBgdate(bgDate);

							ChangeDetail bgDetail = gsgsDjBgInfo
									.getDetail();
							if (bgDetail != null) {
								//变更前
								List<AicpubRegChangeInfo.ChangeInfo> bgqInfos = bgDetail.preInfos;
								Set<TBgxxxq> tBgxxxqs = new LinkedHashSet<TBgxxxq>();
								if (bgqInfos != null) {
									for (AicpubRegChangeInfo.ChangeInfo bgInfo : bgqInfos) {
										TBgxxxq bgxxxq = new TBgxxxq();
										bgxxxq.setTBgxx(bgxx);
										bgxxxq.setStatus(0);//0表示变更前 1表示变更后	
										//姓名
										String name = bgInfo.name;
										bgxxxq.setName(name);
										//投资人类型/职位
										String typeOrPosition = bgInfo.typeOrPosition;
										bgxxxq.setTypeorposition(typeOrPosition);
										tBgxxxqs.add(bgxxxq);
									}
								}
								//变更后
								List<AicpubRegChangeInfo.ChangeInfo> bghInfos = bgDetail.postInfos;
								if (bghInfos != null) {
									for (AicpubRegChangeInfo.ChangeInfo bgInfo : bghInfos) {
										TBgxxxq bgxxxq = new TBgxxxq();
										bgxxxq.setTBgxx(bgxx);
										bgxxxq.setStatus(1);//0表示变更前 1表示变更后											
										//姓名
										String name = bgInfo.name;
										bgxxxq.setName(name);
										//投资人类型/职位
										String typeOrPosition = bgInfo.typeOrPosition;
										bgxxxq.setTypeorposition(typeOrPosition);
										tBgxxxqs.add(bgxxxq);
									}
								}

								bgxx.setTBgxxxqs(tBgxxxqs);
							}

							//html内容
							String html = gsgsDjBgInfo.getHtml();
							bgxx.setHtml(html);
							tBgxxs.add(bgxx);
						}
						qyjbxx.setTBgxxs(tBgxxs);
					}
					
					//撤销信息
					List<AicpubRegRevokeInfo> revokeInfos = gsgsDjInfo.getRevokeInfos();
					if (revokeInfos!=null) {
						Set<TCxxx> tCxxxs = new LinkedHashSet<TCxxx>();
						for (AicpubRegRevokeInfo revokeInfo : revokeInfos) {
							TCxxx cxxx = new TCxxx();
							cxxx.setTQyjbxx(qyjbxx);
							
							//信息类型
							cxxx.setInfoType("工商公示信息-登记信息-撤销信息");
							
							//撤销事项
							String revokeItem = revokeInfo.getRevokeItem();	
							cxxx.setRevokeItem(revokeItem);
							
							//撤销前内容
							String revokePreContent = revokeInfo.getRevokePreContent();
							cxxx.setRevokePreContent(revokePreContent);
							
							//撤销后内容
							String revokePostContent = revokeInfo.getRevokePostContent();
							cxxx.setRevokePostContent(revokePostContent);
							
							//撤销日期
							String revokeDate = revokeInfo.getRevokeDate();
							cxxx.setRevokeDateTime(revokeDate);
							
							//html内容
							String html = revokeInfo.getHtml();
							cxxx.setHtml(html);
							tCxxxs.add(cxxx);
						}
						qyjbxx.setTCxxxs(tCxxxs);
					}
				}

				//备案信息
				AicpubArchiveInfo gsgsBaInfo = gsgsInfo.getArchiveInfo();

				if (gsgsBaInfo != null) {
					//主要人员信息
					List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = gsgsBaInfo
							.getPriMemberInfos();
					if (gsgsBaZyryInfos != null) {
						Set<TZyryxx> tZyryxxs = new LinkedHashSet<TZyryxx>();
						for (AicpubArchivePrimemberInfo gsgsBaZyryInfo : gsgsBaZyryInfos) {
							TZyryxx zyryxx = new TZyryxx();
							zyryxx.setTQyjbxx(qyjbxx);
							//信息类型
							zyryxx.setInfotype("工商公示信息-备案信息-主要人员信息");
							//姓名
							String name = gsgsBaZyryInfo.getName();
							zyryxx.setName(name);

							//职务
							String position = gsgsBaZyryInfo
									.getPosition();
							zyryxx.setPosition(position);

							//html内容
							String html = gsgsBaZyryInfo.getHtml();
							zyryxx.setHtml(html);

							tZyryxxs.add(zyryxx);
						}
						qyjbxx.setTZyryxxs(tZyryxxs);
					}
					//分支机构信息
					List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = gsgsBaInfo
							.getBranchInfos();
					if (gsgsBaFzjgInfos != null) {
						Set<TFzjgxx> tFzjgxxs = new LinkedHashSet<TFzjgxx>();
						for (AicpubArchiveBranchInfo gsgsBaFzjgInfo : gsgsBaFzjgInfos) {

							TFzjgxx fzjgxx = new TFzjgxx();
							fzjgxx.setTQyjbxx(qyjbxx);

							//信息类型
							fzjgxx.setInfotype("工商公示信息-备案信息-分支机构信息");
							//统一社会信用代码/注册号
							String num = gsgsBaFzjgInfo.getNum();
							fzjgxx.setNum(num);

							//名称
							String name = gsgsBaFzjgInfo.getName();
							fzjgxx.setName(name);

							//登记机关
							String regAuthority = gsgsBaFzjgInfo
									.getRegAuthority();
							fzjgxx.setRegauthority(regAuthority);

							//html内容
							String html = gsgsBaFzjgInfo.getHtml();
							fzjgxx.setHtml(html);

							tFzjgxxs.add(fzjgxx);
						}
						qyjbxx.setTFzjgxxs(tFzjgxxs);
					}
					//清算信息
					AicpubArchiveClearInfo gsgsBaQsInfo = gsgsBaInfo
							.getClearInfo();
					if (gsgsBaQsInfo != null) {

						TQsxx qsxx = new TQsxx();
						qsxx.setTQyjbxx(qyjbxx);
						//信息类型
						qsxx.setInfotype("工商公示信息-备案信息-清算信息");
						//清算组负责人
						String leader = gsgsBaQsInfo.getLeader();
						qsxx.setLeader(leader);

						//清算组成员
						List<String> members = gsgsBaQsInfo
								.getMembers();
						if (members != null) {
							StringBuffer sb = new StringBuffer();
							for (int i = 0; i < members.size(); i++) {
								if (i == 0) {
									sb.append(members.get(0));
								}
								sb.append("," + members.get(i));
							}
							qsxx.setMembers(sb.toString());
						}

						//html内容
						String html = gsgsBaQsInfo.getHtml();
						qsxx.setHtml(html);

						qyjbxx.setTQsxx(qsxx);
					}
					
					//主管部门（出资人）信息
					List<AicpubArchiveMainDeptInfo> mainDeptInfos = gsgsBaInfo.getMainDeptInfo();
					if (mainDeptInfos!=null) {
						Set<TZgbmxx> tZgbmxxs = new LinkedHashSet<TZgbmxx>();
						for (AicpubArchiveMainDeptInfo mainDeptInfo : mainDeptInfos) {
							TZgbmxx zgbmxx = new TZgbmxx();
							zgbmxx.setTQyjbxx(qyjbxx);
							
							//信息类型
							zgbmxx.setInfoType("工商公示信息-备案信息-主管部门（出资人）信息");
							
							//type 出资人类型
							String type = mainDeptInfo.getType();
							zgbmxx.setType(type);
							
							//name 出资人
							String name = mainDeptInfo.getName();
							zgbmxx.setName(name);
							
							//idType 证照/证件类型
							String idType = mainDeptInfo.getIdType();
							zgbmxx.setIdType(idType);
							
							//idNum 证照/证件号码
							String idNum = mainDeptInfo.getIdNum();
							zgbmxx.setIdNum(idNum);
							
							//showDate 公示日期(新加)
							String showDate = mainDeptInfo.getShowDate();
							zgbmxx.setShowDateTime(showDate);
							
							//html html内容
							String html = mainDeptInfo.getHtml();
							zgbmxx.setHtml(html);
							
							tZgbmxxs.add(zgbmxx);
						}
						qyjbxx.setTZgbmxxs(tZgbmxxs);
					}
					
					//html内容
					String html = gsgsBaInfo.getHtml();
					gsxtHtml.setGsgsbainfo(html);
				}

				//动产抵押登记信息
				AicpubChatMortgInfo gsgsDcdydjInfo = gsgsInfo
						.getChatMortgInfo();

				if (gsgsDcdydjInfo != null) {
					//动产抵押登记信息
					List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = gsgsDcdydjInfo
							.getChatMortgInfos();
					if (gsgsDcdydjDcdydjInfos != null) {
						Set<TDcdydjxx> tDcdydjxxs = new LinkedHashSet<TDcdydjxx>();
						for (AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo : gsgsDcdydjDcdydjInfos) {
							TDcdydjxx dcdydjxx = new TDcdydjxx();
							dcdydjxx.setTQyjbxx(qyjbxx);
							//信息类型
							dcdydjxx.setInfotype("工商公示信息-动产抵押登记信息-动产抵押登记信息");
							//登记编号
							String regNum = gsgsDcdydjDcdydjInfo
									.getRegNum();
							dcdydjxx.setRegnum(regNum);
							//登记日期
							String regDate = gsgsDcdydjDcdydjInfo
									.getRegDateTime();
							dcdydjxx.setRegdate(regDate);
							//登记机关
							String regAuthority = gsgsDcdydjDcdydjInfo
									.getRegAuthority();
							dcdydjxx.setRegauthority(regAuthority);
							//被担保债权数额
							String bdbzqAmount = gsgsDcdydjDcdydjInfo
									.getGuaranteedDebtAmount();
							dcdydjxx.setBdbzqamount(bdbzqAmount);
							//状态
							String status = gsgsDcdydjDcdydjInfo
									.getStatus();
							dcdydjxx.setStatus(status);
							//公示时间
							String pubDate = gsgsDcdydjDcdydjInfo
									.getPubDateTime();
							dcdydjxx.setPubdate(pubDate);
							//详情
							String detail = gsgsDcdydjDcdydjInfo
									.getDetail();
							
							//详情实体
							AicpubCChatMortgDetail mortgDetail = gsgsDcdydjDcdydjInfo.getMortgDetail();
							if (mortgDetail!=null) {
								//动产抵押登记信息
								AicpubCChatMortgRegInfo mortgRegInfo = mortgDetail.getMortgRegInfo();
								if (mortgRegInfo!=null) {
									//登记编号
									String regNum2 = mortgRegInfo.getRegNum();
									if (StringUtils.isEmpty(dcdydjxx.getRegnum())) {
										dcdydjxx.setRegnum(regNum2);
									}
									
									//登记日期
									String regDate2 = mortgRegInfo.getRegDate();
									if (StringUtils.isEmpty(dcdydjxx.getRegdate())) {
										dcdydjxx.setRegdate(regDate2);
									}
									
									//登记机关
									String regAuthority2 = mortgRegInfo.getRegAuthority();
									if (StringUtils.isEmpty(dcdydjxx.getRegauthority())) {
										dcdydjxx.setRegauthority(regAuthority2);
									}
									
									//被担保债权种类
									String guaranteedDebtType2 = mortgRegInfo.getGuaranteedDebtType();
									dcdydjxx.setGuaranteeddebttype(guaranteedDebtType2);
									
									//被担保债权数额
									String guaranteedDebtAmount2 = mortgRegInfo.getGuaranteedDebtAmount();
									if (StringUtils.isEmpty(dcdydjxx.getBdbzqamount())) {
										dcdydjxx.setBdbzqamount(guaranteedDebtAmount2);
									}
									
									//债务人履行债务的期限
									String term2 = mortgRegInfo.getTerm();
									dcdydjxx.setTerm(term2);
									
									//担保范围
									String guaranteedScope2 = mortgRegInfo.getGuaranteedScope();
									dcdydjxx.setGuaranteedscope(guaranteedScope2);
									
									//备注
									String note2 = mortgRegInfo.getNote();
									dcdydjxx.setNote(note2);
								}
								
								//抵押权人概况
								List<AicpubCChatMortgPersonInfo> mortgPersonInfos = mortgDetail.getMortgPersonInfos();
								if (mortgPersonInfos!=null) {
									Set<TDyrgk> tDyrgks = new LinkedHashSet<TDyrgk>();
									for (AicpubCChatMortgPersonInfo mortgPersonInfo : mortgPersonInfos) {
										TDyrgk dyrgk = new TDyrgk();
										dyrgk.setTDcdydjxx(dcdydjxx);
										
										//信息类型 
										dyrgk.setInfoType("工商公示信息-动产抵押登记信息-动产抵押登记信息-详情-抵押权人概况");
										
										//抵押权人名称
										String name2 = mortgPersonInfo.getName();
										dyrgk.setName(name2);
										
										//抵押权人证照/证件类型
										String type2 = mortgPersonInfo.getType();
										dyrgk.setIdType(type2);
										
										//证照/证件号码
										String num2 = mortgPersonInfo.getNum();
										dyrgk.setIdNum(num2);
										
										//html内容
										String html2 = mortgPersonInfo.getHtml();
										dyrgk.setHtml(html2);
										tDyrgks.add(dyrgk);
									}
									dcdydjxx.setTDyrgks(tDyrgks);
								}
								
								//被担保债权概况
								AicpubCChatMortgGuaranteedInfo mortgGuaranteedInfo = mortgDetail.getMortgGuaranteedInfo();
								if (mortgGuaranteedInfo!=null) {
									//category 种类
									String category2 = mortgGuaranteedInfo.getCategory();
									if (StringUtils.isEmpty(dcdydjxx.getGuaranteeddebttype())) {
										dcdydjxx.setGuaranteeddebttype(category2);
									}
									
									//amount 数额
									String amount2 = mortgGuaranteedInfo.getAmount();
									if (StringUtils.isEmpty(dcdydjxx.getBdbzqamount())) {
										dcdydjxx.setBdbzqamount(amount2);
									}
									
									//guarantyScope 担保的范围
									String guarantyScope = mortgGuaranteedInfo.getGuarantyScope();
									if (StringUtils.isEmpty(dcdydjxx.getGuaranteedscope())) {
										dcdydjxx.setGuaranteedscope(guarantyScope);
									}
									
									//term 债务人履行债务的期限
									String term2 = mortgGuaranteedInfo.getTerm();
									if (StringUtils.isEmpty(dcdydjxx.getTerm())) {
										dcdydjxx.setTerm(term2);
									}
									
									//note 备注
									String note2 = mortgGuaranteedInfo.getNote();
									if (StringUtils.isEmpty(dcdydjxx.getNote())) {
										dcdydjxx.setNote(note2);
									}
								}
								
								//抵押物概况
								List<AicpubCChatMortgGoodsInfo> mortgGoodsInfos = mortgDetail.getMortgGoodsInfos();
								if (mortgGoodsInfos!=null) {
									Set<TDywgk> tDywgks = new LinkedHashSet<TDywgk>();
									for (AicpubCChatMortgGoodsInfo mortgGoodsInfo : mortgGoodsInfos) {
										TDywgk dywgk = new TDywgk();
										dywgk.setTDcdydjxx(dcdydjxx);
										
										//信息类型
										dywgk.setInfoType("工商公示信息-动产抵押登记信息-动产抵押登记信息-详情-抵押物概况");
										
										//name 名称 
										String name2 = mortgGoodsInfo.getName();
										dywgk.setName(name2);
										
										//ownerShip 所有权归属 
										String ownerShip2 = mortgGoodsInfo.getOwnerShip();
										dywgk.setOwnerShip(ownerShip2);
										
										//generalSituation 数量、质量、状况、所在地等情况 
										String generalSituation2 = mortgGoodsInfo.getGeneralSituation();
										dywgk.setGeneralSituation(generalSituation2);
										
										//note 备注
										String note2 = mortgGoodsInfo.getNote();
										dywgk.setNote(note2);
										
										//html html内容
										String html = mortgGoodsInfo.getHtml();
										dywgk.setHtml(html);
										tDywgks.add(dywgk);
									}
									dcdydjxx.setTDywgks(tDywgks);
								}
								
								//变更
								List<AicpubCChatMortgChangeInfo> mortgChangeInfos = mortgDetail.getMortgChangeInfos();
								if (mortgChangeInfos!=null) {
									Set<TDcdydjxxbg> tDcdydjxxbgs = new LinkedHashSet<TDcdydjxxbg>();
									for (AicpubCChatMortgChangeInfo mortgChangeInfo : mortgChangeInfos) {
										TDcdydjxxbg dcdydjxxbg = new TDcdydjxxbg();
										dcdydjxxbg.setTDcdydjxx(dcdydjxx);

										//信息类型
										dcdydjxxbg.setInfoType("信息类型 工商公示信息-动产抵押登记信息-动产抵押登记信息-详情-变更");
										
										//changeDate 变更日期
										String changeDateTime2 = mortgChangeInfo.getChangeDate();
										dcdydjxxbg.setChangeDateTime(changeDateTime2);
										
										//changeContent 变更内容
										String changeContent2 = mortgChangeInfo.getChangeContent();
										dcdydjxxbg.setChangeContent(changeContent2);
										
										//html html内容
										String html = mortgChangeInfo.getHtml();
										dcdydjxxbg.setHtml(html);
										tDcdydjxxbgs.add(dcdydjxxbg);
									}
									dcdydjxx.setTDcdydjxxbgs(tDcdydjxxbgs);
								}
								
								//注销
								AicpubCChatMortgRevokeInfo mortgRevokeInfo = mortgDetail.getMortgRevokeInfo();
								if (mortgRevokeInfo!=null) {
									//注销日期
									String revokeDateTime2 = mortgRevokeInfo.getRevokeDate();
									dcdydjxx.setRevokedate(revokeDateTime2);
									
									//注销原因
									String revokeReason2 = mortgRevokeInfo.getRevokeReason();
									dcdydjxx.setRevokereason(revokeReason2);
								}
							}
							
							dcdydjxx.setDetail(detail);
							//html内容
							String html = gsgsDcdydjDcdydjInfo
									.getHtml();
							dcdydjxx.setHtml(html);

							tDcdydjxxs.add(dcdydjxx);
						}
						qyjbxx.setTDcdydjxxs(tDcdydjxxs);
					}
					//html内容
					String html = gsgsDcdydjInfo.getHtml();
					gsxtHtml.setGsgsdcdydjinfo(html);

				}

				//股权出资登记信息
				AicpubEqumortgregInfo gsgsGqczdjInfo = gsgsInfo
						.getEquMortgRegInfo();

				if (gsgsGqczdjInfo != null) {
					//股权出质登记信息
					List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = gsgsGqczdjInfo
							.getEqumortgregInfos();
					if (gsgsGqczdjGqczdjInfos != null) {
						Set<TGqczdjxx> tGqczdjxxs = new LinkedHashSet<TGqczdjxx>();
						for (AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo : gsgsGqczdjGqczdjInfos) {
							TGqczdjxx gqczdjxx = new TGqczdjxx();
							gqczdjxx.setTQyjbxx(qyjbxx);

							//信息类型
							gqczdjxx.setInfotype("工商公示信息-股权出质登记信息-股权出质登记信息");
							//登记编号
							String regNum = gsgsGqczdjGqczdjInfo
									.getRegNum();
							gqczdjxx.setRegnum(regNum);
							//出质人
							String czr = gsgsGqczdjGqczdjInfo.getMortgagorName();
							gqczdjxx.setCzr(czr);
							//证照/证件号码（出质人）
							String czrIdNum = gsgsGqczdjGqczdjInfo
									.getMortgagorIdNum();
							gqczdjxx.setCzridnum(czrIdNum);
							//出质股权数额
							String czgqAmount = gsgsGqczdjGqczdjInfo
									.getMortgAmount();
							gqczdjxx.setCzgqamount(czgqAmount);
							//质权人
							String zqr = gsgsGqczdjGqczdjInfo.getMortgageeName();
							gqczdjxx.setZqr(zqr);
							//证照/证件号码
							String zqrIdNum = gsgsGqczdjGqczdjInfo
									.getMortgageeIdNum();
							gqczdjxx.setZqridnum(zqrIdNum);
							//股权出质设立登记日期
							String gqczsldjDate = gsgsGqczdjGqczdjInfo
									.getRegDateTime();
							gqczdjxx.setGqczsldjdate(gqczsldjDate);
							//状态
							String status = gsgsGqczdjGqczdjInfo
									.getStatus();
							gqczdjxx.setStatus(status);
							//公示时间
							String pubDate = gsgsGqczdjGqczdjInfo
									.getPubDate();
							gqczdjxx.setPubdate(pubDate);
							//变化情况
							String changeSitu = gsgsGqczdjGqczdjInfo
									.getChangeSitu();
							gqczdjxx.setChangesitu(changeSitu);
							//html内容
							String html = gsgsGqczdjGqczdjInfo
									.getHtml();
							gqczdjxx.setHtml(html);

							tGqczdjxxs.add(gqczdjxx);
						}
						qyjbxx.setTGqczdjxxs(tGqczdjxxs);
					}
					//html内容
					String html = gsgsGqczdjInfo.getHtml();
					gsxtHtml.setGsgsgqczdjinfo(html);
				}

				//行政处罚信息
				AicpubAdmpunishInfo gsgsXzcfInfo = gsgsInfo.getAdmPunishInfo();
				if (gsgsXzcfInfo != null) {
					//行政处罚信息列表
					List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = gsgsXzcfInfo
							.getAdmPunishInfos();
					if (gsgsXzcfXzcfInfos != null) {
						Set<TXzcfxx> tXzcfxxs = new LinkedHashSet<TXzcfxx>();
						for (AicpubAAdmpunishInfo gsgsXzcfXzcfInfo : gsgsXzcfXzcfInfos) {
							TXzcfxx xzcfxx = new TXzcfxx();
							xzcfxx.setTQyjbxx(qyjbxx);
							//信息类型
							xzcfxx.setInfotype("工商公示信息-行政处罚信息-行政处罚信息");
							//行政处罚决定书文号
							String xzcfjdsNum = gsgsXzcfXzcfInfo
									.getPunishRepNum();
							xzcfxx.setXzcfjdsnum(xzcfjdsNum);

							//违法行为类型
							String wfxwType = gsgsXzcfXzcfInfo
									.getIllegalActType();
							xzcfxx.setWfxwtype(wfxwType);

							//行政处罚内容
							String xzcfContent = gsgsXzcfXzcfInfo
									.getPunishContent();
							xzcfxx.setXzcfcontent(xzcfContent);

							//作出行政处罚决定机关名称
							String zcxzcfjdjgName = gsgsXzcfXzcfInfo
									.getDeciAuthority();
							xzcfxx.setZcxzcfjdjgname(zcxzcfjdjgName);

							//作出行政处罚决定日期
							String zcxzcfjdDate = gsgsXzcfXzcfInfo
									.getDeciDateTime();
							xzcfxx.setZcxzcfjddate(zcxzcfjdDate);

							PunishDetail xzcfDetail = gsgsXzcfXzcfInfo
									.getPunishDetail();
							if (xzcfDetail != null) {
								TXzcfxxxq xzcfxxxq = new TXzcfxxxq();
								xzcfxxxq.setTXzcfxx(xzcfxx);

								//行政处罚决定书文号
								String xzcfjdsNum1 = xzcfDetail.punishRepNum;
								xzcfxxxq.setXzcfjdsnum(xzcfjdsNum1);
								//名称
								String name = xzcfDetail.name;
								xzcfxxxq.setName(name);
								//注册号
								String regNum = xzcfDetail.regNum;
								xzcfxxxq.setRegnum(regNum);
								//法定代表人（负责人）姓名
								String legalReprName = xzcfDetail.legalReprName;
								xzcfxxxq.setLegalreprname(legalReprName);
								//违法行为类型
								String wfxwType1 = xzcfDetail.illegalActType;
								xzcfxxxq.setWfxwtype(wfxwType1);
								//行政处罚内容
								String xzcfContent1 = xzcfDetail.punishContent;
								xzcfxxxq.setXzcfcontent(xzcfContent1);
								//作出行政处罚决定机关名称
								String zcxzcfjdjgName1 = xzcfDetail.deciAuthority;
								xzcfxxxq.setZcxzcfjdjgname(zcxzcfjdjgName1);
								//作出行政处罚决定日期
								String zcxzcfjdDate1 = xzcfDetail.deciDateTime;
								xzcfxxxq.setZcxzcfjddate(zcxzcfjdDate1);
								//行政处罚决定书
								String xzcfjds = xzcfDetail.punishRep;
								xzcfxxxq.setXzcfjds(xzcfjds);
								xzcfxx.setXzcfjds(xzcfjds);

								xzcfxx.setTXzcfxxxq(xzcfxxxq);
							}

							//html内容
							String html = gsgsXzcfXzcfInfo.getHtml();
							xzcfxx.setHtml(html);

							tXzcfxxs.add(xzcfxx);
						}
						qyjbxx.setTXzcfxxs(tXzcfxxs);
					}
					//html内容
					String html = gsgsXzcfInfo.getHtml();
					gsxtHtml.setGsgsxzcfinfo(html);
				}

				AicpubOperanomaInfo gsgsJyycInfo = gsgsInfo.getOperAnomaInfo();
				AicpubSerillegalInfo gsgsYzwfInfo = gsgsInfo.getSerIllegalInfo();
				if (gsgsJyycInfo != null || gsgsYzwfInfo != null) {
					Set<TJyychyzwfxx> tJyychyzwfxxs = new LinkedHashSet<TJyychyzwfxx>();
					//经营异常信息
					if (gsgsJyycInfo != null) {
						//经营异常信息列表
						List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = gsgsJyycInfo
								.getOperAnomaInfos();
						if (gsgsJyycJyycInfos != null) {
							for (AicpubOOperanomaInfo gsgsJyycJyycInfo : gsgsJyycJyycInfos) {
								TJyychyzwfxx jyychyzwfxx = new TJyychyzwfxx();
								jyychyzwfxx.setTQyjbxx(qyjbxx);

								//信息类型
								jyychyzwfxx.setInfotype("工商公示信息-经营异常信息-经营异常信息");
								//是否为经营异常
								jyychyzwfxx.setIsjyyc(true);
								//列入经营异常名录原因
								String lrjyycmlCause = gsgsJyycJyycInfo
										.getIncludeCause();
								jyychyzwfxx.setLrcause(lrjyycmlCause);

								//原因详情
								String includeCauseDetail = gsgsJyycJyycInfo.getIncludeCauseDetail();
								jyychyzwfxx.setLrcauseDetail(includeCauseDetail);
								
								//文书编号
								String serialNumber = gsgsJyycJyycInfo.getSerialNumber();
								jyychyzwfxx.setSerialNumber(serialNumber);
								
								//列入日期
								String lrDate = gsgsJyycJyycInfo
										.getIncludeDateTime();
								jyychyzwfxx.setLrdate(lrDate);
								//作出决定机关(列入)
								String lrzcjdAuthority = gsgsJyycJyycInfo
										.getIncludeAuthority();
								jyychyzwfxx
										.setLrzcjdauthority(lrzcjdAuthority);
								//移出经营异常名录原因
								String ycjyycmlCause = gsgsJyycJyycInfo
										.getRemoveCause();
								jyychyzwfxx.setYccause(ycjyycmlCause);
								//移出日期
								String ycDate = gsgsJyycJyycInfo
										.getRemoveDateTime();
								jyychyzwfxx.setYcdate(ycDate);
								//作出决定机关(移出)
								String yczcjdAuthority = gsgsJyycJyycInfo
										.getRemoveAuthority();
								jyychyzwfxx
										.setYczcjdauthority(yczcjdAuthority);
								//作出决定机关(列入和移出 做出决定机关)
								String zcjdAuthority = gsgsJyycJyycInfo
										.getAuthority();
								jyychyzwfxx
										.setZcjdauthority(zcjdAuthority);
								//html内容
								String html = gsgsJyycJyycInfo
										.getHtml();
								jyychyzwfxx.setHtml(html);
								tJyychyzwfxxs.add(jyychyzwfxx);
							}
						}
						//html内容
						String html = gsgsJyycInfo.getHtml();
						gsxtHtml.setGsgsjyycinfo(html);
					}
					//严重违法信息
					if (gsgsYzwfInfo != null) {
						//严重违法信息列表
						List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = gsgsYzwfInfo
								.getSerIllegalInfos();
						if (gsgsYzwfYzwfInfos != null) {
							for (AicpubSSerillegalInfo gsgsYzwfYzwfInfo : gsgsYzwfYzwfInfos) {
								TJyychyzwfxx jyychyzwfxx = new TJyychyzwfxx();
								jyychyzwfxx.setTQyjbxx(qyjbxx);
								//信息类型
								jyychyzwfxx
										.setInfotype("工商公示信息-严重违法信息-严重违法信息");
								//是否为经营异常
								jyychyzwfxx.setIsjyyc(false);
								//列入严重违法企业名单原因
								String lryzwfqymdCause = gsgsYzwfYzwfInfo
										.getIncludeCause();
								jyychyzwfxx.setLrcause(lryzwfqymdCause);
								//列入日期
								String lrDate = gsgsYzwfYzwfInfo
										.getIncludeDateTime();
								jyychyzwfxx.setLrdate(lrDate);
								//移出严重违法企业名单原因
								String ycyzwfqymdCause = gsgsYzwfYzwfInfo
										.getRemoveCause();
								jyychyzwfxx.setYccause(ycyzwfqymdCause);
								//移出日期
								String ycDate = gsgsYzwfYzwfInfo
										.getRemoveDateTime();
								jyychyzwfxx.setYcdate(ycDate);
								//作出决定机关
								String zcjdAuthority = gsgsYzwfYzwfInfo
										.getDeciAuthority();
								jyychyzwfxx
										.setZcjdauthority(zcjdAuthority);
								//html内容
								String html = gsgsYzwfYzwfInfo
										.getHtml();
								jyychyzwfxx.setHtml(html);
								tJyychyzwfxxs.add(jyychyzwfxx);
							}
						}
						//html内容
						String html = gsgsYzwfInfo.getHtml();
						gsxtHtml.setGsgsyzwfinfo(html);
					}
					qyjbxx.setTJyychyzwfxxs(tJyychyzwfxxs);
				}
				//抽查检查信息
				AicpubCheckInfo gsgsCcjcInfo = gsgsInfo.getCheckInfo();
				if (gsgsCcjcInfo != null) {
					//抽查检查信息列表
					List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = gsgsCcjcInfo
							.getCheckInfos();
					if (gsgsCcjcCcjcInfos != null) {
						Set<TCcjcxx> tCcjcxxs = new LinkedHashSet<TCcjcxx>();
						for (AicpubCCheckInfo gsgsCcjcCcjcInfo : gsgsCcjcCcjcInfos) {
							TCcjcxx ccjcxx = new TCcjcxx();
							ccjcxx.setTQyjbxx(qyjbxx);
							//信息类型
							ccjcxx.setInfotype("工商公示信息-抽查检查信息-抽查检查信息");
							//检查实施机关
							String jcssAuthority = gsgsCcjcCcjcInfo
									.getCheckImplAuthority();
							ccjcxx.setJcssauthority(jcssAuthority);
							//类型
							String type = gsgsCcjcCcjcInfo.getType();
							ccjcxx.setType(type);
							//日期
							String date = gsgsCcjcCcjcInfo.getDateTime();
							ccjcxx.setDate(date);
							//结果
							String result11 = gsgsCcjcCcjcInfo
									.getResult();
							ccjcxx.setResult(result11);
							//html内容
							String html = gsgsCcjcCcjcInfo.getHtml();
							ccjcxx.setHtml(html);

							tCcjcxxs.add(ccjcxx);
						}
						qyjbxx.setTCcjcxxs(tCcjcxxs);
					}
					//html内容
					String html = gsgsCcjcInfo.getHtml();
					gsxtHtml.setGsgsccjcinfo(html);
				}

			}

			//企业公示信息
			EntpubInfo qygsInfo = aicFeedJson.getEntPubInfo();
			if (qygsInfo != null) {
				//企业年报
				List<EntpubAnnreportInfo> qygsQynbInfos = qygsInfo
						.getAnnReports();
				if (qygsQynbInfos != null) {

					LOGGER.info("===================企业年报　if===================="
							+ qygsQynbInfos.size());
					Set<TQynb> qynbs = new LinkedHashSet<TQynb>();
					for (EntpubAnnreportInfo qygsQynbInfo : qygsQynbInfos) {
						LOGGER.info("===================企业年报　for====================");
						TQynb qynb = new TQynb();

						qynb.setTQyjbxx(qyjbxx);
						//信息类型
						qynb.setInfotype("企业公示信息-企业年报-企业年报-企业基本信息");

						//报送年度
						String submitYear = qygsQynbInfo
								.getSubmitYear();
						qynb.setSubmityear(submitYear);
						//发布日期
						String pubDate = qygsQynbInfo.getPubDateTime();
						qynb.setPubdate(pubDate);
						
						//首次发布日期
						String firstDate = qygsQynbInfo.getFirstDate();
						qynb.setFirstdate(firstDate);
						
						//企业基本信息
						EntpubAnnreportBaseInfo qygsQynbJbInfo = qygsQynbInfo
								.getBaseInfo();
						if (qygsQynbJbInfo != null) {
							LOGGER.info("===================qygsQynbJbInfofor===================="
									+ qygsQynbJbInfo.toString());
							//注册号/统一社会信用代码
							String num = qygsQynbJbInfo.getNum();
							qynb.setNum(num);
							//企业名称
							String name = qygsQynbJbInfo.getName();
							qynb.setName(name);
							
							//经营者姓名
							String operator = qygsQynbJbInfo.getOperator();
							qynb.setLegalrepr(operator);
							
							//营业执照注册号
							String businessLicenceNum = qygsQynbJbInfo.getBusinessLicenceNum();
							qynb.setNum(businessLicenceNum);
							
							//资金数额
							String capitalAmount = qygsQynbJbInfo.getCapitalAmount();
							qynb.setCapitalamount(capitalAmount);
							
							//企业联系电话
							String tel = qygsQynbJbInfo.getTel();
							qynb.setTel(tel);
							//邮政编码
							String zipCode = qygsQynbJbInfo
									.getZipCode();
							qynb.setZipcode(zipCode);
							//企业通信地址
							String address = qygsQynbJbInfo
									.getAddress();
							qynb.setAddress(address);
							//电子邮箱
							String email = qygsQynbJbInfo.getEmail();
							qynb.setEmail(email);
							//有限责任公司本年度是否发生股东股权转让
							String isYxzrgsbndgdgqzr = qygsQynbJbInfo
									.getIsStohrEquTransferred();
							qynb.setIsyxzrgsbndgdgqzr(isYxzrgsbndgdgqzr);
							//企业经营状态
							String operatingStatus = qygsQynbJbInfo
									.getOperatingStatus();
							qynb.setOperatingStatus(operatingStatus);
							//是否有网站或网店
							String hasWzhwd = qygsQynbJbInfo
									.getHasWebsiteOrStore();
							qynb.setHaswzhwd(hasWzhwd);
							//企业是否有投资信息或购买其他公司股权
							String isTzxxhgmqtgsgq = qygsQynbJbInfo
									.getHasInvestInfoOrPurchOtherCorpEqu();
							qynb.setIstzxxhgmqtgsgq(isTzxxhgmqtgsgq);
							//从业人数
							String empNum = qygsQynbJbInfo.getEmpNum();
							qynb.setEmpnum(empNum);
							
							//隶属关系
							String affiliation = qygsQynbJbInfo.getAffiliation();
							qynb.setAffiliation(affiliation);
							
							//合作社名称
							String cooperativeName = qygsQynbJbInfo.getCooperativeName();
							qynb.setCooperativeName(cooperativeName);
							
							//成员人数
							String membersNum = qygsQynbJbInfo.getMembersNum();
							qynb.setMembersNum(membersNum);
							
							//html内容
							String htmlJbxx = qygsQynbJbInfo.getHtml();
							qynb.setHtmlJbxx(htmlJbxx);
						}
						//网站或网店信息
						List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdInfos = qygsQynbInfo
								.getWebsiteInfos();
						if (qygsQynbWzhwdInfos != null) {
							Set<TWzhwdxx> tWzhwdxxs = new LinkedHashSet<TWzhwdxx>();
							for (EntpubAnnreportWebsiteInfo qygsQynbWzhwdInfo : qygsQynbWzhwdInfos) {
								TWzhwdxx wzhwdxx = new TWzhwdxx();
								wzhwdxx.setTQynb(qynb);
								//类型
								String type = qygsQynbWzhwdInfo
										.getType();
								wzhwdxx.setType(type);
								//名称
								String name = qygsQynbWzhwdInfo
										.getName();
								wzhwdxx.setName(name);
								//网址
								String website = qygsQynbWzhwdInfo
										.getWebsite();
								wzhwdxx.setWebsite(website);
								//html内容
								String html = qygsQynbWzhwdInfo
										.getHtml();
								wzhwdxx.setHtml(html);
								tWzhwdxxs.add(wzhwdxx);
							}
							qynb.setTWzhwdxxs(tWzhwdxxs);
						}
						//股东及出资信息
						List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = qygsQynbInfo
								.getStohrInvestInfos();
						if (qygsQynbGdjczInfos != null) {
							Set<TGdjczxx> tGdjczxxs = new LinkedHashSet<TGdjczxx>();
							for (EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo : qygsQynbGdjczInfos) {
								TGdjczxx gdjczxx = new TGdjczxx();
								gdjczxx.setTQynb(qynb);
								//信息类型
								gdjczxx.setInfotype("企业公示信息-企业 年报-企业年报-公洞及出资信息");
								//股东（发起人）
								String stockholder = qygsQynbGdjczInfo
										.getStockholder();
								gdjczxx.setStockholder(stockholder);
								//认缴出资额（万元）
								String rjczAmount = qygsQynbGdjczInfo
										.getSubAmount();
								gdjczxx.setRjczamount(rjczAmount);
								//认缴出资时间
								String rjczDate = qygsQynbGdjczInfo
										.getSubDateTime();
								gdjczxx.setRjczdate(rjczDate);
								//认缴出资方式
								String rjczMethod = qygsQynbGdjczInfo
										.getSubMethod();
								gdjczxx.setRjczmethod(rjczMethod);
								//实缴出资额（万元）
								String sjczAmount = qygsQynbGdjczInfo
										.getPaidAmount();
								gdjczxx.setSjczamount(sjczAmount);
								//实缴出资时间
								String sjczDate = qygsQynbGdjczInfo
										.getPaidDateTime();
								gdjczxx.setSjczdate(sjczDate);
								//实缴出资方式
								String sjczMethod = qygsQynbGdjczInfo
										.getPaidMethod();
								gdjczxx.setSjczmethod(sjczMethod);
								//html内容
								String html = qygsQynbGdjczInfo
										.getHtml();
								gdjczxx.setHtml(html);

								tGdjczxxs.add(gdjczxx);
							}
							qynb.setTGdjczxxs(tGdjczxxs);
						}
						//对外投资信息
						List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = qygsQynbInfo
								.getExtInvestInfos();
						if (qygsQynbDwtzInfos != null) {
							Set<TDwtzxx> tDwtzxxs = new LinkedHashSet<TDwtzxx>();
							for (EntpubAnnreportExtinvestInfo qygsQynbDwtzInfo : qygsQynbDwtzInfos) {
								TDwtzxx dwtzxx = new TDwtzxx();
								dwtzxx.setTQynb(qynb);
								//信息类型
								dwtzxx.setInfotype("企业公示信息-企业年报-企业年报-对外投资信息");
								//投资设立企业或购买股权企业名称
								String tzslqyhgmgqqyName = qygsQynbDwtzInfo
										.getEnterpriseName();
								dwtzxx.setTzslqyhgmgqqyname(tzslqyhgmgqqyName);
								//注册号
								String regNum = qygsQynbDwtzInfo
										.getRegNum();
								dwtzxx.setRegnum(regNum);
								//html内容
								String html = qygsQynbDwtzInfo
										.getHtml();
								dwtzxx.setHtml(html);
								tDwtzxxs.add(dwtzxx);
							}
							qynb.setTDwtzxxs(tDwtzxxs);
						}
						//企业资产状况信息
						EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = qygsQynbInfo
								.getAssetInfo();
						if (qygsQynbQyzczkInfo != null) {
							//资产总额
							String assetAmount = qygsQynbQyzczkInfo
									.getAssetAmount();
							qynb.setAssetamount(assetAmount);
							//负债总额
							String liabilityAmount = qygsQynbQyzczkInfo
									.getLiabilityAmount();
							qynb.setLiabilityamount(liabilityAmount);
							//销售总额
							String salesAmount = qygsQynbQyzczkInfo
									.getSalesAmount();
							qynb.setSalesamount(salesAmount);
							//利润总额
							String profitAmount = qygsQynbQyzczkInfo
									.getProfitAmount();
							qynb.setProfitamount(profitAmount);
							//销售总额中主营业务收入
							String xszezzyywsr = qygsQynbQyzczkInfo
									.getPriBusiIncomeInSalesAmount();
							qynb.setXszezzyywsr(xszezzyywsr);
							//净利润
							String netProfit = qygsQynbQyzczkInfo
									.getNetProfit();
							qynb.setNetprofit(netProfit);
							//纳税总额
							String taxesAmount = qygsQynbQyzczkInfo
									.getTaxesAmount();
							qynb.setTaxesamount(taxesAmount);
							//所有者权益合计
							String syzqyhj = qygsQynbQyzczkInfo
									.getTotalEquity();
							qynb.setSyzqyhj(syzqyhj);
							
							//获得政府扶持资金、补助
							String governmentFunds = qygsQynbQyzczkInfo.getGovernmentFunds();
							qynb.setGovernmentFunds(governmentFunds);
							
							//金融贷款
							String financialLoan = qygsQynbQyzczkInfo.getFinancialLoan();
							qynb.setFinancialLoan(financialLoan);
							
							//html内容
							String htmlQyzczkxx = qygsQynbQyzczkInfo
									.getHtml();
							qynb.setHtmlQyzczkxx(htmlQyzczkxx);
						}
						//对外提供保证担保信息
						List<EntpubAnnreportExtguaranteeInfo> qygsQynbDwtgbzdbInfos = qygsQynbInfo
								.getExtGuaranteeInfos();
						if (qygsQynbDwtgbzdbInfos != null) {
							Set<TDwtgbzdbxx> tDwtgbzdbxxs = new LinkedHashSet<TDwtgbzdbxx>();
							for (EntpubAnnreportExtguaranteeInfo qygsQynbDwtgbzdbInfo : qygsQynbDwtgbzdbInfos) {
								TDwtgbzdbxx dwtgbzdbxx = new TDwtgbzdbxx();
								dwtgbzdbxx.setTQynb(qynb);
								//信息类型
								dwtgbzdbxx
										.setInfotype("企业公示信息-企业年报 -企业年报-对外提供保证担保信息");
								//债权人
								String creditor = qygsQynbDwtgbzdbInfo
										.getCreditor();
								dwtgbzdbxx.setCreditor(creditor);
								//债务人
								String debtor = qygsQynbDwtgbzdbInfo
										.getDebtor();
								dwtgbzdbxx.setDebtor(debtor);
								//主债权种类
								String priCredRightType = qygsQynbDwtgbzdbInfo
										.getPriCredRightType();
								dwtgbzdbxx
										.setPricredrighttype(priCredRightType);
								//主债权数额
								String priCredRightAmount = qygsQynbDwtgbzdbInfo
										.getPriCredRightAmount();
								dwtgbzdbxx
										.setPricredrightamount(priCredRightAmount);
								//履行债务的期限
								String ExeDebtDeadline = qygsQynbDwtgbzdbInfo
										.getExeDebtDeadline();
								dwtgbzdbxx
										.setExedebtdeadline(ExeDebtDeadline);
								//保证的期间
								String guaranteePeriod = qygsQynbDwtgbzdbInfo
										.getGuaranteePeriod();
								dwtgbzdbxx
										.setGuaranteeperiod(guaranteePeriod);
								//保证的方式
								String guaranteeMethod = qygsQynbDwtgbzdbInfo
										.getGuaranteeMethod();
								dwtgbzdbxx
										.setGuaranteemethod(guaranteeMethod);
								//保证担保的范围
								String guaranteeScope = qygsQynbDwtgbzdbInfo
										.getGuaranteeScope();
								dwtgbzdbxx
										.setGuaranteescope(guaranteeScope);
								//html内容
								String html = qygsQynbDwtgbzdbInfo
										.getHtml();
								dwtgbzdbxx.setHtml(html);
								tDwtgbzdbxxs.add(dwtgbzdbxx);
							}
							qynb.setTDwtgbzdbxxs(tDwtgbzdbxxs);
						}
						//股权变更信息
						List<EntpubAnnreportEquchangeInfo> qygsQynbGqbgInfos = qygsQynbInfo
								.getEquChangeInfos();
						if (qygsQynbGqbgInfos != null) {
							Set<TGqbgxx> tGqbgxxs = new LinkedHashSet<TGqbgxx>();
							for (EntpubAnnreportEquchangeInfo qygsQynbGqbgInfo : qygsQynbGqbgInfos) {
								TGqbgxx gqbgxx = new TGqbgxx();
								gqbgxx.setTQynb(qynb);
								gqbgxx.setInfotype("企业公示信息-企业年报-企业年报-股权变更信息");
								//股东（发起人）
								String stockholder = qygsQynbGqbgInfo
										.getStockholder();
								gqbgxx.setStockholder(stockholder);
								//变更前股权比例
								String bgqOwnershipRatio = qygsQynbGqbgInfo
										.getPreOwnershipRatio();
								gqbgxx.setBgqownershipratio(bgqOwnershipRatio);
								//变更后股权比例
								String bghOwnershipRatio = qygsQynbGqbgInfo
										.getPostOwnershipRatio();
								gqbgxx.setBghownershipratio(bghOwnershipRatio);
								//股权变更日期
								String bgDate = qygsQynbGqbgInfo
										.getDateTime();
								gqbgxx.setBgdate(bgDate);
								//html内容
								String html = qygsQynbGqbgInfo
										.getHtml();
								gqbgxx.setHtml(html);
								tGqbgxxs.add(gqbgxx);
							}
							qynb.setTGqbgxxs(tGqbgxxs);
						}
						//修改记录
						List<EntpubAnnreportModifyInfo> qygsQynbXgjlInfos = qygsQynbInfo
								.getChangeInfos();
						if (qygsQynbXgjlInfos != null) {
							Set<TBgxx> tBgxxs = new LinkedHashSet<TBgxx>();
							for (EntpubAnnreportModifyInfo qygsQynbXgjlInfo : qygsQynbXgjlInfos) {
								TBgxx bgxx = new TBgxx();
								bgxx.setTQynb(qynb);
								bgxx.setInfotype("企业公示信息-企业年报-企业年报-修改记录");
								//修改事项
								String xgItem = qygsQynbXgjlInfo
										.getItem();
								bgxx.setBgitem(xgItem);
								//修改前
								String xgqContent = qygsQynbXgjlInfo
										.getPreContent();
								bgxx.setBgqcontent(xgqContent);
								//修改后
								String xghContent = qygsQynbXgjlInfo
										.getPostContent();
								bgxx.setBghcontent(xghContent);
								//修改日期
								String xgDate = qygsQynbXgjlInfo
										.getDateTime();
								bgxx.setBgdate(xgDate);
								//html内容
								String html = qygsQynbXgjlInfo
										.getHtml();
								bgxx.setHtml(html);
								tBgxxs.add(bgxx);
							}
							qynb.setTBgxxs(tBgxxs);
						}
						
						//行政許可情況
						List<EntpubAnnreportAdmlicenseInfo> admlicenseInfos = qygsQynbInfo.getAdmlicenseInfos();
						if (admlicenseInfos!=null) {
							Set<TXzxkxx> tXzxkxxs = new HashSet<TXzxkxx>();
							for (EntpubAnnreportAdmlicenseInfo admlicenseInfo : admlicenseInfos) {
								TXzxkxx xzxkxx = new TXzxkxx();
								xzxkxx.setTQynb(qynb);
								
								//信息类型
								xzxkxx.setInfotype("企业公示信息-企业年报-企业年报-行政許可情況");
								
								//许可文件名称
								String licenseName = admlicenseInfo.getLicenseName();
								xzxkxx.setXkwjname(licenseName);
								
								//有效期至
								String licenseDate = admlicenseInfo.getLicenseDate();
								xzxkxx.setEnddate(licenseDate);
								
								//html内容
								String html = admlicenseInfo.getHtml();
								xzxkxx.setHtml(html);
								tXzxkxxs.add(xzxkxx);
							}
							qynb.setTXzxkxxs(tXzxkxxs);
						}
						
						//生产经营情况
						List<EntpubAnnreportManageInfo> manageInfos = qygsQynbInfo.getManageInfos();
						if (manageInfos!=null) {
							Set<TScjyqk> tScjyqks = new HashSet<TScjyqk>();
							for (EntpubAnnreportManageInfo manageInfo : manageInfos) {
								TScjyqk scjyqk = new TScjyqk();
								scjyqk.setTQynb(qynb);
								
								//信息类型
								scjyqk.setInfoType("企业公示信息-企业年报-企业年报-生产经营情况");
								
								//saleSum 营业额或营业收入
								String saleSum = manageInfo.getSaleSum();
								scjyqk.setSalarySum(saleSum);
								
								//salarySum 纳税总额
								String salarySum = manageInfo.getSalarySum();
								scjyqk.setSalarySum(salarySum);
								
								//netProfit 净利润
								String netProfit = manageInfo.getNetProfit();
								scjyqk.setNetProfit(netProfit);
								
								//html html内容
								String html = manageInfo.getHtml();
								scjyqk.setHtml(html);
								tScjyqks.add(scjyqk);
							}
							qynb.setTScjyqks(tScjyqks);
						}
						
						//html内容
						String html = qygsQynbInfo.getHtml();
						qynb.setHtml(html);
						qynbs.add(qynb);
						LOGGER.info("===================企业年报===================="
								+ qynb.toString());
					}
					qyjbxx.setTQynbs(qynbs);
				}
				//股东及出资信息
				EntpubStohrinvestInfo qygsGdjczInfo = qygsInfo
						.getStohrInvestInfo();
				if (qygsGdjczInfo != null) {

					List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = qygsGdjczInfo
							.getStohrInvestInfos();
					List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = qygsGdjczInfo
							.getChangeInfos();
					if (qygsGdjczGdjczInfos != null
							|| qygsGdjczBgInfos != null) {
						TQygsGdjczxx tQygsGdjczxx = new TQygsGdjczxx();
						tQygsGdjczxx.setTQyjbxx(qyjbxx);
						//信息类型
						tQygsGdjczxx.setInfotype("企业公示信息-股东及出资信息");
						//股东及出资信息
						if (qygsGdjczGdjczInfos != null) {
							Set<TGdjczxx> tGdjczxxs = new LinkedHashSet<TGdjczxx>();
							for (EntpubSStohrinvestInfo qygsGdjczGdjczInfo : qygsGdjczGdjczInfos) {
								TGdjczxx gdjczxx = new TGdjczxx();
								gdjczxx.setTQygsGdjczxx(tQygsGdjczxx);
								//信息类型
								gdjczxx.setInfotype("企业公示信息-股东及出资信息-股东及出资信息");
								//股东
								String stockholder = qygsGdjczGdjczInfo
										.getStockholder();
								gdjczxx.setStockholder(stockholder);
								//认缴额（万元）
								String rjAmount = qygsGdjczGdjczInfo
										.getSubAmount();
								gdjczxx.setRjczamount(rjAmount);
								//实缴额（万元）
								String sjAmount = qygsGdjczGdjczInfo
										.getPaidAmount();
								gdjczxx.setSjczamount(sjAmount);
								
								//投资人类型
								String investorsType = qygsGdjczGdjczInfo.getInvestorsType();
								gdjczxx.setInvestorsType(investorsType);
								
								//认缴明细
								List<Detail> subDetails = qygsGdjczGdjczInfo.getSubDetails();
								//实缴明细
								List<Detail> paidDetails = qygsGdjczGdjczInfo.getPaidDetails();
								if (subDetails!=null || paidDetails!=null) {
									Set<TRjhsjmx> TRjhsjmxs = new HashSet<TRjhsjmx>();
									if (subDetails!=null) {
										for (Detail subDetail : subDetails) {
											TRjhsjmx TRjhsjmx = new TRjhsjmx();
											//type
											TRjhsjmx.setType("企业公示信息-股东及出资信息-股东及出资信息-认缴明细");
											//出资方式
											String method = subDetail.method;
											TRjhsjmx.setMethod(method);
											//出资额
											String amount = subDetail.amount;
											TRjhsjmx.setAmount(amount);
											//出资日期
											String dateTime = subDetail.dateTime;
											TRjhsjmx.setCzdate(dateTime);
											//公示日期
											String showDate = subDetail.showDate;
											TRjhsjmx.setGsdate(showDate);
											
											TRjhsjmx.setTGdjczxx(gdjczxx);
											TRjhsjmxs.add(TRjhsjmx);
										}
									}
									
									if (paidDetails!=null) {
										for (Detail paidDetail : paidDetails) {
											TRjhsjmx TRjhsjmx = new TRjhsjmx();
											//type
											TRjhsjmx.setType("企业公示信息-股东及出资信息-股东及出资信息-实缴明细");
											//出资方式
											String method = paidDetail.method;
											TRjhsjmx.setMethod(method);
											//出资额
											String amount = paidDetail.amount;
											TRjhsjmx.setAmount(amount);
											//出资日期
											String dateTime = paidDetail.dateTime;
											TRjhsjmx.setCzdate(dateTime);
											//公示日期
											String showDate = paidDetail.showDate;
											TRjhsjmx.setGsdate(showDate);
											
											TRjhsjmx.setTGdjczxx(gdjczxx);
											TRjhsjmxs.add(TRjhsjmx);
										}
									}
									gdjczxx.setTRjhsjmxs(TRjhsjmxs);
								}
								
								//html内容
								String html = qygsGdjczGdjczInfo.getHtml();
								gdjczxx.setHtml(html);
								tGdjczxxs.add(gdjczxx);
							}
							tQygsGdjczxx.setTGdjczxxs(tGdjczxxs);
						}
						//变更信息
						if (qygsGdjczBgInfos != null) {
							Set<TBgxx> tBgxxs = new LinkedHashSet<TBgxx>();
							for (EntpubStohrinvestChangeInfo qygsGdjczBgInfo : qygsGdjczBgInfos) {
								TBgxx bgxx = new TBgxx();
								bgxx.setTQygsGdjczxx(tQygsGdjczxx);
								//信息类型
								bgxx.setInfotype("企业公示信息-股东及出资信息-变更信息");
								//变更事项
								String bgItem = qygsGdjczBgInfo
										.getItem();
								bgxx.setBgitem(bgItem);
								//变更时间
								String bgDate = qygsGdjczBgInfo
										.getDateTime();
								bgxx.setBgdate(bgDate);
								//变更前
								String bgqContent = qygsGdjczBgInfo
										.getPreContent();
								bgxx.setBgqcontent(bgqContent);
								//变更后
								String bghContent = qygsGdjczBgInfo
										.getPostContent();
								bgxx.setBghcontent(bghContent);
								//html内容
								String html = qygsGdjczBgInfo.getHtml();
								bgxx.setHtml(html);
								tBgxxs.add(bgxx);
							}
							tQygsGdjczxx.setTBgxxs(tBgxxs);
							tQygsGdjczxx.setHtml(qygsGdjczInfo
									.getHtml());
						}
						qyjbxx.setTQygsGdjczxx(tQygsGdjczxx);
					}
					//html内容
					String html = qygsGdjczInfo.getHtml();
					gsxtHtml.setQygsgdjczinfo(html);
				}
				//股权变更信息
				EntpubEquchangeInfo qygsGqbgInfo = qygsInfo.getEquChangeInfo();
				if (qygsGqbgInfo != null) {
					//股权变更信息列表
					List<EntpubEEquchangeInfo> qygsGqbgGqbgInfos = qygsGqbgInfo
							.getEquChangeInfos();
					if (qygsGqbgGqbgInfos != null) {
						Set<TGqbgxx> tGqbgxxs = new LinkedHashSet<TGqbgxx>();
						for (EntpubEEquchangeInfo qygsGqbgGqbgInfo : qygsGqbgGqbgInfos) {
							TGqbgxx gqbgxx = new TGqbgxx();
							gqbgxx.setTQyjbxx(qyjbxx);
							//信息 类型
							gqbgxx.setInfotype("企业公示信息-股权变更信息-股权变更信息");
							//股东
							String stockholder = qygsGqbgGqbgInfo
									.getStockholder();
							gqbgxx.setStockholder(stockholder);
							//变更前股权比例
							String bgqOwnershipRatio = qygsGqbgGqbgInfo
									.getPreOwnershipRatio();
							gqbgxx.setBgqownershipratio(bgqOwnershipRatio);
							//变更后股权比例
							String bghOwnershipRatio = qygsGqbgGqbgInfo
									.getPostOwnershipRatio();
							gqbgxx.setBghownershipratio(bghOwnershipRatio);
							//股权变更日期
							String bgDate = qygsGqbgGqbgInfo
									.getDateTime();
							gqbgxx.setBgdate(bgDate);
							//html内容
							String html = qygsGqbgGqbgInfo.getHtml();
							gqbgxx.setHtml(html);
							tGqbgxxs.add(gqbgxx);
						}
						qyjbxx.setTGqbgxxs(tGqbgxxs);
					}
					//html内容
					String html = qygsGqbgInfo.getHtml();
					gsxtHtml.setQygsgqbginfo(html);
				}
				//行政许可信息
				EntpubAdmlicInfo qygsXzxkInfo = qygsInfo.getAdmLicInfo();
				if (qygsXzxkInfo != null) {
					//行政许可信息列表
					List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = qygsXzxkInfo
							.getAdmlicInfos();
					if (qygsXzxkXzxkInfos != null) {
						Set<TXzxkxx> tXzxkxxs = new LinkedHashSet<TXzxkxx>();
						for (EntpubAAdmlicInfo qygsXzxkXzxkInfo : qygsXzxkXzxkInfos) {
							TXzxkxx xzxkxx = new TXzxkxx();
							xzxkxx.setTQyjbxx(qyjbxx);
							//信息类型
							xzxkxx.setInfotype("企业公示信息-行政许可信息-行政许可信息");
							//许可文件编号
							String xkwjNum = qygsXzxkXzxkInfo
									.getLicenceNum();
							xzxkxx.setXkwjnum(xkwjNum);
							//许可文件名称
							String xkwjName = qygsXzxkXzxkInfo
									.getLicenceName();
							xzxkxx.setXkwjname(xkwjName);
							//有效期自
							String startDate = qygsXzxkXzxkInfo
									.getStartDateTime();
							xzxkxx.setStartdate(startDate);
							//有效期至
							String endDate = qygsXzxkXzxkInfo
									.getEndDateTime();
							xzxkxx.setEnddate(endDate);
							//许可机关
							String xkAuthority = qygsXzxkXzxkInfo
									.getDeciAuthority();
							xzxkxx.setXkauthority(xkAuthority);
							//许可内容
							String xkContent = qygsXzxkXzxkInfo
									.getContent();
							xzxkxx.setXkcontent(xkContent);
							//状态
							String status = qygsXzxkXzxkInfo
									.getStatus();
							xzxkxx.setStatus(status);
							
							//公示日期
							String pubDate = qygsXzxkXzxkInfo.getPubDate();
							xzxkxx.setPubDateTime(pubDate);
							
							//详情
							String detail = qygsXzxkXzxkInfo
									.getDetail();
							
							//详情实体
							List<EntpubAAdmlicInfo.Detail> admlicDetails = qygsXzxkXzxkInfo.getAdmlicDetail();
							if (admlicDetails!=null) {
								Set<TBgxx> tBgxxs = new LinkedHashSet<TBgxx>();
								for (EntpubAAdmlicInfo.Detail admlicDetail : admlicDetails) {
									TBgxx tBgxx = new TBgxx();
									tBgxx.setTXzxkxx(xzxkxx);
									
									//信息类型
									tBgxx.setInfotype("企业公示信息-行政许可信息-行政许可信息-详情");
									
									//changeItem 变更事项
									String changeItem = admlicDetail.getChangeItem();
									tBgxx.setBgitem(changeItem);
									
									//changeDateTime 变更时间
									String changeDateTime = admlicDetail.getChangeDateTime();
									tBgxx.setBgdate(changeDateTime);
									
									//changePreContent 变更前内容
									String changePreContent = admlicDetail.getChangePreContent();
									tBgxx.setBgqcontent(changePreContent);
									
									//changePostContent 变更后内容
									String changePostContent = admlicDetail.getChangePostContent();
									tBgxx.setBghcontent(changePostContent);
									
									tBgxxs.add(tBgxx);
								}
								xzxkxx.setTBgxxs(tBgxxs);
							}
							
							xzxkxx.setDetail(detail);
							//html内容
							String html = qygsXzxkXzxkInfo.getHtml();
							xzxkxx.setHtml(html);
							tXzxkxxs.add(xzxkxx);
						}
						qyjbxx.setTXzxkxxs(tXzxkxxs);
					}
					//html内容
					String html = qygsXzxkInfo.getHtml();
					gsxtHtml.setQygsxzxkinfo(html);
				}
				//知识产权出质登记信息
				EntpubIntellectualproregInfo qygsZscqczdjInfo = qygsInfo
						.getIntellectualProRegInfo();
				if (qygsZscqczdjInfo != null) {
					//知识产权出质登记信息列表
					List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = qygsZscqczdjInfo
							.getIntellectualProRegInfos();
					if (qygsZscqczdjZscqczdjInfos != null) {
						Set<TZscqczdjxx> tZscqczdjxxs = new LinkedHashSet<TZscqczdjxx>();
						for (EntpubIIntellectualproregInfo qygsZscqczdjZscqczdjInfo : qygsZscqczdjZscqczdjInfos) {
							TZscqczdjxx zscqczdjxx = new TZscqczdjxx();
							zscqczdjxx.setTQyjbxx(qyjbxx);
							//信息类型
							zscqczdjxx
									.setInfotype("企业公示信息-知识产权出质登记信息-知识产权出质登记信息");
							//注册号
							String regNum = qygsZscqczdjZscqczdjInfo
									.getRegNum();
							zscqczdjxx.setRegnum(regNum);
							//名称
							String name = qygsZscqczdjZscqczdjInfo
									.getName();
							zscqczdjxx.setName(name);
							//种类
							String type = qygsZscqczdjZscqczdjInfo
									.getType();
							zscqczdjxx.setType(type);
							//出质人名称
							String czrName = qygsZscqczdjZscqczdjInfo
									.getMortgagorName();
							zscqczdjxx.setCzrname(czrName);
							//质权人名称
							String zqrName = qygsZscqczdjZscqczdjInfo
									.getMortgageeName();
							zscqczdjxx.setZqrname(zqrName);
							//质权登记期限
							String zqdjDeadline = qygsZscqczdjZscqczdjInfo
									.getPledgeRegDeadline();

							zscqczdjxx.setZqdjdeadline(zqdjDeadline);

							//状态
							String status = qygsZscqczdjZscqczdjInfo
									.getStatus();
							zscqczdjxx.setStatus(status);
							//变化情况
							String changeSitu = qygsZscqczdjZscqczdjInfo
									.getChangeSitu();
							zscqczdjxx.setChangesitu(changeSitu);
							//html内容
							String html = qygsZscqczdjZscqczdjInfo
									.getHtml();
							zscqczdjxx.setHtml(html);
							tZscqczdjxxs.add(zscqczdjxx);
						}
						qyjbxx.setTZscqczdjxxs(tZscqczdjxxs);
					}
					//html内容
					String html = qygsZscqczdjInfo.getHtml();
					gsxtHtml.setQygszscqczdjinfo(html);
				}
				//行政处罚信息
				EntpubAdmpunishInfo qygsXzcfInfo = qygsInfo.getAdmPunishInfo();
				if (qygsXzcfInfo != null) {
					//行政处罚信息列表
					List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = qygsXzcfInfo
							.getAdmPunishInfos();
					if (qygsXzcfXzcfInfos != null) {
						Set<TXzcfxx> tXzcfxxs = new LinkedHashSet<TXzcfxx>();
						for (EntpubAAdmpunishInfo qygsXzcfXzcfInfo : qygsXzcfXzcfInfos) {
							TXzcfxx xzcfxx = new TXzcfxx();
							xzcfxx.setTQyjbxx(qyjbxx);
							//信息类型
							xzcfxx.setInfotype("企业公示信息-行政处罚信息-行政处罚信息");
							//行政处罚决定书文号
							String xzcfjdsNum = qygsXzcfXzcfInfo
									.getPunishRepNum();
							xzcfxx.setXzcfjdsnum(xzcfjdsNum);
							//行政处罚内容
							String xzcfContent = qygsXzcfXzcfInfo
									.getPunishContent();
							xzcfxx.setXzcfcontent(xzcfContent);
							//作出行政处罚决定机关名称
							String zcxzcfjdjgName = qygsXzcfXzcfInfo
									.getDeciAuthority();
							xzcfxx.setZcxzcfjdjgname(zcxzcfjdjgName);
							//作出行政处罚决定日期
							String zcxzcfjdDate = qygsXzcfXzcfInfo
									.getDeciDateTime();
							xzcfxx.setZcxzcfjddate(zcxzcfjdDate);
							//违法行为类型
							String wfxwType = qygsXzcfXzcfInfo
									.getIllegalActType();
							xzcfxx.setWfxwtype(wfxwType);
							//备注
							String note = qygsXzcfXzcfInfo.getNote();
							xzcfxx.setNote(note);
							
							//公示信息
							String showDate = qygsXzcfXzcfInfo.getShowDate();
							xzcfxx.setShowDateTime(showDate);
							
							//html内容
							String html = qygsXzcfXzcfInfo.getHtml();
							xzcfxx.setHtml(html);
							tXzcfxxs.add(xzcfxx);
						}
						qyjbxx.setTXzcfxxs(tXzcfxxs);
					}
					//html内容
					String html = qygsXzcfInfo.getHtml();
					gsxtHtml.setQygsxzcfinfo(html);
				}
			}

			//其他部门公示信息
			OthrdeptpubInfo qtbmgsInfo = aicFeedJson.getOthrDeptPubInfo();
			if (qtbmgsInfo != null) {
				//行政许可信息
				OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = qtbmgsInfo
						.getAdmLicInfo();
				if (qtbmgsXzxkInfo != null) {
					List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = qtbmgsXzxkInfo
							.getAdmLicInfos();
					List<OthrdeptpubAdmlicChangeInfo> qtbmgsXzxkBgInfos = qtbmgsXzxkInfo
							.getChangeInfos();
					if (qtbmgsXzxkXzxkInfos != null
							|| qtbmgsXzxkBgInfos != null) {
						TQtbmgsXzxkxx tQtbmgsXzxkxx = new TQtbmgsXzxkxx();
						tQtbmgsXzxkxx.setTQyjbxx(qyjbxx);
						qyjbxx.setTQtbmgsXzxkxx(tQtbmgsXzxkxx);
						//信息类型
						tQtbmgsXzxkxx.setInfotype("其他部门公示信息-行政许可信息");
						tQtbmgsXzxkxx.setHtml(qtbmgsXzxkInfo.getHtml());
						//行政许可信息列表
						if (qtbmgsXzxkXzxkInfos != null) {
							Set<TXzxkxx> tXzxkxxs = new LinkedHashSet<TXzxkxx>();
							for (OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo : qtbmgsXzxkXzxkInfos) {
								TXzxkxx xzxkxx = new TXzxkxx();
								xzxkxx.setTQtbmgsXzxkxx(tQtbmgsXzxkxx);
								//信息类型
								xzxkxx.setInfotype("其他部门公示信息-行政许可信息-行政许可信息");
								//许可文件编号
								String xkwjNum = qtbmgsXzxkXzxkInfo
										.getLicenceNum();
								xzxkxx.setXkwjnum(xkwjNum);
								//许可文件名称
								String xkwjName = qtbmgsXzxkXzxkInfo
										.getLicenceName();
								xzxkxx.setXkwjname(xkwjName);
								//有效期自
								String startDate = qtbmgsXzxkXzxkInfo
										.getStartDateTime();
								xzxkxx.setStartdate(startDate);
								//有效期至
								String endDate = qtbmgsXzxkXzxkInfo
										.getEndDateTime();
								xzxkxx.setEnddate(endDate);
								//许可机关
								String xkAuthority = qtbmgsXzxkXzxkInfo
										.getDeciAuthority();
								xzxkxx.setXkauthority(xkAuthority);
								//许可内容
								String xkContent = qtbmgsXzxkXzxkInfo
										.getContent();
								xzxkxx.setXkcontent(xkContent);
								//状态
								String status = qtbmgsXzxkXzxkInfo
										.getStatus();
								xzxkxx.setStatus(status);
								//详情
								String detail = qtbmgsXzxkXzxkInfo
										.getDetail();
								xzxkxx.setDetail(detail);
								
								//有效日期
								String expiryDate = qtbmgsXzxkXzxkInfo.getExpiryDate();
								xzxkxx.setExpiryDate(expiryDate);
								
								//详情列表对象
								List<OthrdeptpubAAdmlicInfoDetail> othrdeptpubAAdmlicInfoDetails = qtbmgsXzxkXzxkInfo.getOthrdeptpubAAdmlicInfoDetails();
								if (othrdeptpubAAdmlicInfoDetails!=null) {
									Set<TBgxx> tBgxxs = new LinkedHashSet<TBgxx>();
									for (OthrdeptpubAAdmlicInfoDetail othrdeptpubAAdmlicInfoDetail : othrdeptpubAAdmlicInfoDetails) {
										TBgxx bgxx = new TBgxx();
										bgxx.setTXzxkxx(xzxkxx);
										
										//信息类型
										bgxx.setInfotype("其他部门公示信息-行政许可信息-行政许可信息-详情");
										
										//变更事项
										String alterItem = othrdeptpubAAdmlicInfoDetail.getAlterItem();
										bgxx.setBgitem(alterItem);
										
									    //变更日期
										String alterDate = othrdeptpubAAdmlicInfoDetail.getAlterDate();
										bgxx.setBgdate(alterDate);
										
										//变更前
										String preAlter = othrdeptpubAAdmlicInfoDetail.getPreAlter();
										bgxx.setBgqcontent(preAlter);
										
										//变更后
										String postAlter = othrdeptpubAAdmlicInfoDetail.getPostAlter();
										bgxx.setBghcontent(postAlter);
										
										tBgxxs.add(bgxx);
									}
									xzxkxx.setTBgxxs(tBgxxs);
								}
								
								//html内容
								String html = qtbmgsXzxkXzxkInfo
										.getHtml();
								xzxkxx.setHtml(html);
								tXzxkxxs.add(xzxkxx);
							}
							tQtbmgsXzxkxx.setTXzxkxxs(tXzxkxxs);
						}
						//变更信息列表
						if (qtbmgsXzxkBgInfos != null) {
							Set<TBgxx> tBgxxs = new LinkedHashSet<TBgxx>();
							for (OthrdeptpubAdmlicChangeInfo qtbmgsXzxkBgInfo : qtbmgsXzxkBgInfos) {
								TBgxx bgxx = new TBgxx();
								bgxx.setTQtbmgsXzxkxx(tQtbmgsXzxkxx);
								//信息类型
								bgxx.setInfotype("其他部门管公示信息-行政许可信息-变更信息");
								//变更事项
								String bgItem = qtbmgsXzxkBgInfo
										.getItem();

								bgxx.setBgitem(bgItem);
								//变更时间
								String bgDate = qtbmgsXzxkBgInfo
										.getDateTime();
								bgxx.setBgdate(bgDate);
								//变更前内容
								String bgqContent = qtbmgsXzxkBgInfo
										.getPreContent();
								bgxx.setBgqcontent(bgqContent);
								//变更后内容
								String bghContent = qtbmgsXzxkBgInfo
										.getPostContent();
								bgxx.setBghcontent(bghContent);

								//html内容
								String html = qtbmgsXzxkBgInfo
										.getHtml();
								bgxx.setHtml(html);
								tBgxxs.add(bgxx);
							}
							tQtbmgsXzxkxx.setTBgxxs(tBgxxs);
						}
					}
					//html内容
					String html = qtbmgsXzxkInfo.getHtml();
					gsxtHtml.setQtbmgsxzxkxzxkinfo(html);
				}
				//行政处罚信息
				OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = qtbmgsInfo
						.getAdmPunishInfo();
				if (qtbmgsXzcfInfo != null) {
					//行政处罚信息列表
					List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = qtbmgsXzcfInfo
							.getAdmPunishInfos();
					if (qtbmgsXzcfXzcfInfos != null) {
						Set<TXzcfxx> tXzcfxxs = new LinkedHashSet<TXzcfxx>();
						for (OthrdeptpubAAdmpunishInfo qtbmgsXzcfXzcfInfo : qtbmgsXzcfXzcfInfos) {
							TXzcfxx xzcfxx = new TXzcfxx();
							xzcfxx.setTQyjbxx(qyjbxx);
							//信息类型
							xzcfxx.setInfotype("其他部门公示信息-行政处罚信息-行政处罚信息");
							//行政处罚决定书文号
							String xzcfjdsNum = qtbmgsXzcfXzcfInfo
									.getPunishRepNum();
							xzcfxx.setXzcfjdsnum(xzcfjdsNum);
							//违法行为类型
							String wfxwType = qtbmgsXzcfXzcfInfo
									.getIllegalActType();
							xzcfxx.setWfxwtype(wfxwType);
							//行政处罚内容
							String xzcfContent = qtbmgsXzcfXzcfInfo
									.getPunishContent();
							xzcfxx.setXzcfcontent(xzcfContent);
							//作出行政处罚决定机关名称
							String zcxzcfjdjgName = qtbmgsXzcfXzcfInfo
									.getDeciAuthority();
							xzcfxx.setZcxzcfjdjgname(zcxzcfjdjgName);
							//作出行政处罚决定日期
							String zcxzcfjdDate = qtbmgsXzcfXzcfInfo
									.getDeciDateTime();
							xzcfxx.setZcxzcfjddate(zcxzcfjdDate);
							//详情
							String detail = qtbmgsXzcfXzcfInfo
									.getDetail();
							xzcfxx.setDetail(detail);
							//备注
							String note = qtbmgsXzcfXzcfInfo.getNote();
							xzcfxx.setNote(note);
							//html内容
							String html = qtbmgsXzcfXzcfInfo.getHtml();
							xzcfxx.setHtml(html);
							tXzcfxxs.add(xzcfxx);
						}
						qyjbxx.setTXzcfxxs(tXzcfxxs);
					}
					//html内容
					String html = qtbmgsXzcfInfo.getHtml();
					gsxtHtml.setQtbmgsxzcfxzcfinfo(html);
				}
			}

			//司法协助公示信息
			JudasspubInfo sfxzgsInfo = aicFeedJson.getJudAssPubInfo();
			if (sfxzgsInfo != null) {
				//股权冻结信息
				JudasspubEqufreezeInfo sfxzgsGqdjInfo = sfxzgsInfo
						.getEquFreezeInfo();
				if (sfxzgsGqdjInfo != null) {
					//股权冻结信息列表
					List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = sfxzgsGqdjInfo
							.getEquFreezeInfos();
					if (sfxzgsGqdjGqdjInfos != null) {
						Set<TGqdjxx> tGqdjxxs = new LinkedHashSet<TGqdjxx>();
						for (JudasspubEEqufreezeInfo sfxzgsGqdjGqdjInfo : sfxzgsGqdjGqdjInfos) {
							TGqdjxx gqdjxx = new TGqdjxx();
							gqdjxx.setTQyjbxx(qyjbxx);
							//信息类型
							gqdjxx.setInfotype("司法协助公示信息-股权冻结信息-司法股权冻结信息");
							//被执行人
							String bzxPerson = sfxzgsGqdjGqdjInfo
									.getExecutedPerson();
							gqdjxx.setBzxperson(bzxPerson);
							//股权数额
							String gqAmount = sfxzgsGqdjGqdjInfo
									.getEquAmount();
							gqdjxx.setGqamount(gqAmount);
							//执行法院
							String exeCourt = sfxzgsGqdjGqdjInfo
									.getExeCourt();
							gqdjxx.setExecourt(exeCourt);
							//协助公示通知书文号
							String xzgstzsNum = sfxzgsGqdjGqdjInfo
									.getAssistPubNoticeNum();
							gqdjxx.setXzgstzsnum(xzgstzsNum);
							//状态
							String status = sfxzgsGqdjGqdjInfo
									.getStatus();
							gqdjxx.setStatus(status);
							//详情
							String detail = sfxzgsGqdjGqdjInfo
									.getDetail();
							gqdjxx.setDetail(detail);
							//html内容
							String html = sfxzgsGqdjGqdjInfo.getHtml();
							gqdjxx.setHtml(html);
							tGqdjxxs.add(gqdjxx);
						}
						qyjbxx.setTGqdjxxs(tGqdjxxs);
					}
					//html内容
					String html = sfxzgsGqdjInfo.getHtml();
					gsxtHtml.setSfxzgsgqdjinfo(html);
				}
				//股东变更信息
				JudasspubStohrchangeInfo sfxzgsGdbgInfo = sfxzgsInfo
						.getStohrChangeInfo();
				if (sfxzgsGdbgInfo != null) {
					//股东变更信息列表
					List<JudasspubSStohrchangeInfo> sfxzgsGdbgGdbgInfos = sfxzgsGdbgInfo
							.getStohrChangeInfos();
					if (sfxzgsGdbgGdbgInfos != null) {
						Set<TGdbgxx> tGdbgxxs = new LinkedHashSet<TGdbgxx>();
						for (JudasspubSStohrchangeInfo sfxzgsGdbgGdbgInfo : sfxzgsGdbgGdbgInfos) {
							TGdbgxx gdbgxx = new TGdbgxx();
							gdbgxx.setTQyjbxx(qyjbxx);
							//信息类型
							gdbgxx.setInfotype("司法协助公示信息-股东变更信息-司法股东变更登记信息");
							//被执行人
							String bzxPerson = sfxzgsGdbgGdbgInfo
									.getExecutedPerson();
							gdbgxx.setBzxperson(bzxPerson);
							//股权数额
							String gqAmount = sfxzgsGdbgGdbgInfo
									.getEquAmount();
							gdbgxx.setGqamount(gqAmount);
							//受让人
							String srPerson = sfxzgsGdbgGdbgInfo
									.getAssignee();
							gdbgxx.setSrperson(srPerson);
							//执行法院
							String exeCourt = sfxzgsGdbgGdbgInfo
									.getExeCourt();
							gdbgxx.setExecourt(exeCourt);
							//详情
							String detail = sfxzgsGdbgGdbgInfo
									.getDetail();
							gdbgxx.setDetail(detail);
							//html内容
							String html = sfxzgsGdbgGdbgInfo.getHtml();
							gdbgxx.setHtml(html);
							tGdbgxxs.add(gdbgxx);
						}
						qyjbxx.setTGdbgxxs(tGdbgxxs);
					}
					//html内容
					String html = sfxzgsGdbgInfo.getHtml();
					gsxtHtml.setSfxzgsgdbginfo(html);
				}
			}

			//为企业基本信息设置查询时间
			qyjbxx.setExecutetime(new Date());
			
			//为企业企业基本信息设置外键company_id
			//qyjbxx.setCompany(company);
		}
		
		return qyjbxx;
	}
	
}
package com.crawler.gsxt.htmlparser;

import com.crawler.gsxt.domain.json.*;
import com.crawler.gsxt.domain.json.chongqing.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class GsxtChongqingParser {

	public AicFeedJson chongqingResultParser(String resultHtmls, Boolean isDebug) {
		//解析result
		Gson gson = new Gson();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>() {
		}.getType());

		String saicStr = resultHtmlMap.get("saicData").toString();
		SaicData saicData = gson.fromJson(saicStr, SaicData.class);

		String yearReportStr = resultHtmlMap.get("yearReport").toString();
		YearReport yearReport = gson.fromJson(yearReportStr, YearReport.class);

		List<AnnulReport> annulReports = new ArrayList<>();
		List<History> histories = yearReport.getHistory();
		for(History history : histories){
			String annulReportStr = resultHtmlMap.get(history.getYear() + "年度报告").toString();
			AnnulReport annulReport = gson.fromJson(annulReportStr,AnnulReport.class);
			
			/*if (!StringUtils.isEmpty(annulReportStr)) {
				JsonElement meansJe = null;
				try {
					meansJe = new JsonParser().parse(annulReportStr).getAsJsonObject().get("means");
				} catch(Exception e) {
					e.printStackTrace();
				}
				if (meansJe!=null) {
					String meansStr = meansJe.toString();
					Mean mean = gson.fromJson(meansStr, Mean.class);
					annulReport.setMeans(mean);
				}
			}
			*/
			annulReports.add(annulReport);
		}

		String dailyInvsubStr = resultHtmlMap.get("dailyInvsub").toString();
		Invsub invsub = gson.fromJson(dailyInvsubStr, Invsub.class);

		String dailyTransinfoStr = resultHtmlMap.get("dailyTransinfo").toString();
		Transinfo transinfo = gson.fromJson(dailyTransinfoStr, Transinfo.class);

		String dailyLicinfoStr = resultHtmlMap.get("dailyLicinfo").toString();
		Licinfo licinfo = gson.fromJson(dailyLicinfoStr, Licinfo.class);

		String dailyPleinfoStr = resultHtmlMap.get("dailyPleinfo").toString();
		PleInfo pleInfo = gson.fromJson(dailyPleinfoStr, PleInfo.class);

		String dailyPeninfoStr = resultHtmlMap.get("dailyPeninfo").toString();
		Peninfo peninfo = gson.fromJson(dailyPeninfoStr, Peninfo.class);

		String qlicinfoStr = resultHtmlMap.get("qlicinfo").toString();
		ArrayList<Qlicinfo> qlicinfos = gson.fromJson(qlicinfoStr, new TypeToken<ArrayList<Qlicinfo>>() {
		}.getType());

		String qpeninfoStr = resultHtmlMap.get("qpeninfo").toString();
		ArrayList<Qpeninfo> qpeninfos = gson.fromJson(qpeninfoStr, new TypeToken<ArrayList<Qpeninfo>>(){}.getType());

		String SFXZStr = resultHtmlMap.get("SFXZ").toString();
		ArrayList<SFXZ> SFXZs = gson.fromJson(SFXZStr, new TypeToken<ArrayList<SFXZ>>(){}.getType());

		String SFXZGDBGStr = resultHtmlMap.get("SFXZGDBG").toString();
		ArrayList<SFXZGDBG> SFXZGDBGs = gson.fromJson(SFXZGDBGStr, new TypeToken<ArrayList<SFXZGDBG>>(){}.getType());




		AicFeedJson gsxtFeedJson = new AicFeedJson();

		//一、工商公示信息
		AicpubInfo gsgsInfo = new AicpubInfo();

		//----------工商公示信息-->登记信息   start-------------
		AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();

		//基本信息-----------------------start-----------------------
		AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();

		Base base = saicData.getBase();
		if(null != base.getCreditcode() && !"".equals(base.getCreditcode())){
			gsgsDjJbInfo.setNum(base.getCreditcode());
		}else{
			gsgsDjJbInfo.setNum(base.getRegno());
		}
		gsgsDjJbInfo.setName(base.getEntname());
		gsgsDjJbInfo.setType(base.getEnttype());

		if(null == base.getLerep() || "".equals(base.getLerep())){
			if(null == base.getPril() || "".equals(base.getPril())){
				gsgsDjJbInfo.setLegalRepr(base.getName());
			}else{
				gsgsDjJbInfo.setLegalRepr(base.getPril());
			}
		}else{
			gsgsDjJbInfo.setLegalRepr(base.getLerep());
		}
		if(null != base.getRegcap() && !"".equals(base.getRegcap())){
			gsgsDjJbInfo.setRegCapital(base.getRegcap() + " 万元人民币");
		}

		if(null == base.getEstdate() || "".equals(base.getEstdate())){
			gsgsDjJbInfo.setRegDateTime(base.getRegdate());
		}else{
			gsgsDjJbInfo.setRegDateTime(base.getEstdate());
		}

		if(null == base.getDom() || "".equals(base.getDom())){
			gsgsDjJbInfo.setAddress(base.getOploc());
		}else{
			gsgsDjJbInfo.setAddress(base.getDom());
		}

		gsgsDjJbInfo.setStartDateTime(base.getOpfrom());
		if(/*(base.getOpfrom() != null && !"".equals(base.getOpfrom())) && */(null == base.getOpto() || "".equals(base.getOpto()))){
			gsgsDjJbInfo.setEndDateTime("永久");
		}else{
			gsgsDjJbInfo.setEndDateTime(base.getOpto());
		}

		if(null != base.getOpscope() && !"".equals(base.getOpscope())){
			gsgsDjJbInfo.setBusinessScope(base.getOpscope());
		}else{
			gsgsDjJbInfo.setBusinessScope(base.getOpscoandform());
		}
		gsgsDjJbInfo.setRegAuthority(base.getRegorg());
		gsgsDjJbInfo.setApprovalDateTime(base.getApprdate());
		gsgsDjJbInfo.setRegStatus(base.getOpstate());
		gsgsDjJbInfo.setRevokeDate(base.getRevdate());

		gsgsDjJbInfo.setFormType(base.getCompform());


		gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);


		if(isDebug){
			gsgsDjJbInfo.setHtml(saicStr);
		}
		//基本信息-----------------------start-----------------------



		//股东信息-----------------------start-----------------------

		List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<AicpubRegStohrInfo>();

		List<Investor> investors = saicData.getInvestors();
		//Iterator<Investor> iterator = investors.iterator();

		if(null != investors && investors.size() > 0){
			for(Investor investor : investors){
				AicpubRegStohrInfo gsgsdjgdInfo = new AicpubRegStohrInfo();

				if(null == investor.getInv() || "".equals(investor.getInv())){
					gsgsdjgdInfo.setName(investor.getName());
				}else{
					gsgsdjgdInfo.setName(investor.getInv());
				}

				gsgsdjgdInfo.setSconform(investor.getSconform());

				gsgsdjgdInfo.setType(investor.getInvtype());

				if(null == investor.getCertype() || "".equals(investor.getCertype())){
					gsgsdjgdInfo.setIdType(investor.getBlictype());
				}else{
					gsgsdjgdInfo.setIdType(investor.getCertype());
				}

				if(null != investor.getCerno() && !"".equals(investor.getCerno())){
					gsgsdjgdInfo.setIdNum(investor.getCerno());
				}else{
					gsgsdjgdInfo.setIdNum(investor.getBlicno());
				}



				//股东及出资信息 start
				List<GInvaccon>  gInvaccons = investor.getgInvaccon();
				List<GInvsubcon> gInvsubcons  = investor.getgInvsubcon();

				int size = 0;
				if(null != gInvaccons && null != gInvsubcons){
					size = gInvaccons.size() > gInvsubcons.size() ? gInvaccons.size() : gInvsubcons.size();
				}
				AicpubRegStohrStohrinvestInfo aicpubRegStohrStohrinvestInfo =
						new AicpubRegStohrStohrinvestInfo();

				List<AicpubRegStohrStohrinvestInfo.AmountDetail> subAmountDetails = new ArrayList<>();
				List<AicpubRegStohrStohrinvestInfo.AmountDetail> paidAmountDetails = new ArrayList<>();

				for(int i =  0 ; i < size ; i++){

					if(i < gInvsubcons.size()){
						GInvsubcon gInvsubcon = gInvsubcons.get(i);
						AicpubRegStohrStohrinvestInfo.AmountDetail subamountDetail = aicpubRegStohrStohrinvestInfo.new AmountDetail();

						if(null != gInvsubcon){
							subamountDetail.setInvestAmount(gInvsubcon.getConform());
							subamountDetail.setInvestMethod(gInvsubcon.getSubconam());
							subamountDetail.setInvestDateTime(gInvsubcon.getCondate());
							subAmountDetails.add(subamountDetail);
						}
					}


					if(i < gInvaccons.size()){
						GInvaccon invaccon = gInvaccons.get(i);
						AicpubRegStohrStohrinvestInfo.AmountDetail paidAmountDetail = aicpubRegStohrStohrinvestInfo.new AmountDetail();

						if(null != invaccon){
							paidAmountDetail.setInvestAmount(invaccon.getAcconam());
							paidAmountDetail.setInvestMethod(invaccon.getAcconform());
							paidAmountDetail.setInvestDateTime(invaccon.getAccondate());
							paidAmountDetails.add(paidAmountDetail);
						}
					}
				}
				aicpubRegStohrStohrinvestInfo.setSubAmountDetails(subAmountDetails);
				aicpubRegStohrStohrinvestInfo.setPaidAmountDetails(paidAmountDetails);
				aicpubRegStohrStohrinvestInfo.setStockholder(investor.getInv());
				aicpubRegStohrStohrinvestInfo.setSubAmount(investor.getLisubconam());
				aicpubRegStohrStohrinvestInfo.setPaidAmount(investor.getLiacconam());
				gsgsdjgdInfo.setStohrInvestInfo(aicpubRegStohrStohrinvestInfo);
				//股东及出资信息 end
				gsgsDjGdInfos.add(gsgsdjgdInfo);
			}
		}

		gsgsDjInfo.setStohrInfos(gsgsDjGdInfos);

			//详情
			//List<AicpubRegStohrStohrinvestInfo> gsgsDjGdGdjczInfos = new ArrayList<AicpubRegStohrStohrinvestInfo>();

		//股东信息-----------------------end-----------------------

		//变更信息-----------------------start-----------------------
		List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<AicpubRegChangeInfo>();
		List<Alter> alters = saicData.getAlters();
		if(alters != null){
			for(Alter alter : alters){
				AicpubRegChangeInfo aicpubRegChangeInfo = new AicpubRegChangeInfo();
				aicpubRegChangeInfo.setItem(alter.getAltitem());
				aicpubRegChangeInfo.setPreContent(alter.getAltbe());
				aicpubRegChangeInfo.setPostContent(alter.getAltaf());
				aicpubRegChangeInfo.setDateTime(alter.getAltdate());

				gsgsDjBgInfos.add(aicpubRegChangeInfo);
			}
		}

		gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);
		//变更信息-----------------------end-----------------------


		gsgsInfo.setRegInfo(gsgsDjInfo);
		//----------工商公示信息-->登记信息  end-------------


		//----------工商公示信息-->备案信息  start-------------
		AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();

		//备案信息-->主要人员信息
		List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<AicpubArchivePrimemberInfo>();
		List<Member> members = saicData.getMembers();
		if(null != members){
			for (Member member : members) {
				AicpubArchivePrimemberInfo aicpubArchivePrimemberInfo = new AicpubArchivePrimemberInfo();
				aicpubArchivePrimemberInfo.setName(member.getName());
				aicpubArchivePrimemberInfo.setPosition(member.getPosition());
				gsgsBaZyryInfos.add(aicpubArchivePrimemberInfo);
			}
		}
		gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);

		//备案信息-->分支机构信息
		List<AicpubArchiveBranchInfo> gsgsBaFzjgInfos = new ArrayList<AicpubArchiveBranchInfo>();

		List<Brunch> brunches = saicData.getBrunchs();
		if(null != brunches){
			for(Brunch brunch : brunches){
				AicpubArchiveBranchInfo gsgsBaFzjgInfo = new AicpubArchiveBranchInfo();
//			gsgsBaFzjgInfo.setNum(fzjg_num);
//			gsgsBaFzjgInfo.setName(fzjg_name);
//			gsgsBaFzjgInfo.setRegAuthority(fzjg_regAuthority);
				gsgsBaFzjgInfos.add(gsgsBaFzjgInfo);
			}
		}

		gsgsBaInfo.setBranchInfos(gsgsBaFzjgInfos);

		//备案信息-->清算信息
		AicpubArchiveClearInfo gsgsBaQsInfo = new AicpubArchiveClearInfo();
		List<Account> accounts = saicData.getAccounts();

		if(null != accounts){
			for(Account account : accounts){
//			gsgsBaQsInfo.setLeader();
//			gsgsBaQsInfo.setMembers();
			}
		}

		gsgsBaInfo.setClearInfo(gsgsBaQsInfo);

		//主管部门（出资人）信息表
		List<AicpubArchiveMainDeptInfo> mainDeptInfo = new ArrayList<>();
		/*List<Member> members = saicData.getMembers();
		if(null != members){
			for(Member member : members){
				AicpubArchiveMainDeptInfo aicpubArchiveMainDeptInfo =
						new AicpubArchiveMainDeptInfo();
				aicpubArchiveMainDeptInfo.setName(member.getName());
				aicpubArchiveMainDeptInfo.setType(member.getInvtype());
				aicpubArchiveMainDeptInfo.setIdType(member.getCertype());
				aicpubArchiveMainDeptInfo.setIdNum(member.getCerno());
//			aicpubArchiveMainDeptInfo.setShowDate(member.get);
				mainDeptInfo.add(aicpubArchiveMainDeptInfo);
			}
		}*/
		gsgsBaInfo.setMainDeptInfo(mainDeptInfo);

		gsgsInfo.setArchiveInfo(gsgsBaInfo);
		//----------工商公示信息-->备案信息  end-------------

		//-----------------工商公示信息-->动产抵押登记信息 start-----------------------
		AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
		List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
		List<Motage> motages = saicData.getMotage();

		if(null != motages){
			for(Motage motage : motages){
				AicpubCChatMortgInfo aicpubCChatMortgInfo = new AicpubCChatMortgInfo();

				aicpubCChatMortgInfo.setRegNum(motage.getMorregcno());
				aicpubCChatMortgInfo.setRegDateTime(motage.getRegidate());
				aicpubCChatMortgInfo.setRegAuthority(motage.getRegorg());
				aicpubCChatMortgInfo.setGuaranteedDebtAmount(motage.getPriclasecam());
				aicpubCChatMortgInfo.setStatus(motage.getType());

				gsgsDcdydjDcdydjInfos.add(aicpubCChatMortgInfo);
			}
		}

		gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);
		gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);
		//-----------------工商公示信息-->动产抵押登记信息 end-----------------------

		//-----------------工商公示信息-->股权出质登记信息 start-----------------------
		AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
		List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();

		gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);
		gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);
		//-----------------工商公示信息-->股权出质登记信息 end-----------------------

		//-----------------工商公示信息-->行政处罚信息 start-----------------------
		AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
		List<AicpubAAdmpunishInfo> aicpubAAdmpunishInfos = new ArrayList<>();

		List<Punishment> punishments = saicData.getPunishments();
		if(null != punishments){
			for(Punishment punishment : punishments){
				AicpubAAdmpunishInfo aicpubAAdmpunishInfo = new AicpubAAdmpunishInfo();

				aicpubAAdmpunishInfo.setPunishRepNum(punishment.getPendecno());
				aicpubAAdmpunishInfo.setIllegalActType(punishment.getIllegacttype());
				aicpubAAdmpunishInfo.setPunishContent(punishment.getAuthcontent());
				aicpubAAdmpunishInfo.setDeciAuthority(punishment.getPenauth());
				aicpubAAdmpunishInfo.setDeciDateTime(punishment.getPendecissdate());

				aicpubAAdmpunishInfos.add(aicpubAAdmpunishInfo);
			}
		}

		gsgsXzcfInfo.setAdmPunishInfos(aicpubAAdmpunishInfos);
		gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);
		//-----------------工商公示信息-->行政处罚信息 end-----------------------

		//-----------------工商公示信息-->经营异常信息 start-----------------------
		AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
		List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();

		List<Qyjy> qyjies = saicData.getQyjy();
		List<Gtjnz> gtjnzs = saicData.getGtjnz();
		if(null != qyjies && qyjies.size() > 0){
			for(Qyjy qyjy : qyjies){
				AicpubOOperanomaInfo aicpubOOperanomaInfo = new AicpubOOperanomaInfo();

				aicpubOOperanomaInfo.setIncludeCause(qyjy.getSpecause());
				aicpubOOperanomaInfo.setIncludeDateTime(qyjy.getAbntime());
				aicpubOOperanomaInfo.setAuthority(qyjy.getDecorg());
//				aicpubOOperanomaInfo.setRemoveDateTime();
				aicpubOOperanomaInfo.setRemoveCause(qyjy.getRemexcpres());

				gsgsJyycJyycInfos.add(aicpubOOperanomaInfo);
			}
		}else if(null != gtjnzs && gtjnzs.size() > 0){
			for(Gtjnz gtjnz : gtjnzs){
				AicpubOOperanomaInfo aicpubOOperanomaInfo = new AicpubOOperanomaInfo();

				aicpubOOperanomaInfo.setIncludeCause(gtjnz.getExcpstares());
				aicpubOOperanomaInfo.setIncludeDateTime(gtjnz.getCogdate());
				aicpubOOperanomaInfo.setAuthority(gtjnz.getDecorg());
//				aicpubOOperanomaInfo.setRemoveDateTime(gtjnz.get);
//				aicpubOOperanomaInfo.setRemoveCause(qyjy.getRemexcpres());

				gsgsJyycJyycInfos.add(aicpubOOperanomaInfo);
			}
		}

		gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);
		gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);
		//-----------------工商公示信息-->经营异常信息 end-----------------------

		//-----------------工商公示信息-->严重违法信息 start-----------------------
		AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
		List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
		List<Illegal> illegals = saicData.getIllegals();
		if(null != illegals){
			for(Illegal illegal : illegals){
				AicpubSSerillegalInfo aicpubSSerillegalInfo = new AicpubSSerillegalInfo();
				aicpubSSerillegalInfo.setIncludeCause(illegal.getSerillrea());
				aicpubSSerillegalInfo.setIncludeDateTime(illegal.getLisdate());
				aicpubSSerillegalInfo.setRemoveCause(illegal.getRemexcpres());
				aicpubSSerillegalInfo.setRemoveDateTime(illegal.getRemdate());
				aicpubSSerillegalInfo.setDeciAuthority(illegal.getDecorg());

				gsgsYzwfYzwfInfos.add(aicpubSSerillegalInfo);
			}
		}

		gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);
		gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);

		//-----------------工商公示信息-->严重违法信息 end-----------------------

		//-----------------工商公示信息-->抽查检查信息 start-----------------------
		AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
		List<AicpubCCheckInfo> gsgsCcjcInfos = new ArrayList<AicpubCCheckInfo>();

		List<Ccjc> ccjcs = saicData.getCcjc();
		if(null != ccjcs){
			for(Ccjc ccjc : ccjcs){
				AicpubCCheckInfo aicpubCCheckInfo = new AicpubCCheckInfo();

				//TODO add 抽查检查信息

				gsgsCcjcInfos.add(aicpubCCheckInfo);
			}
		}

		gsgsCcjcInfo.setCheckInfos(gsgsCcjcInfos);
		gsgsInfo.setCheckInfo(gsgsCcjcInfo);
		//-----------------工商公示信息-->抽查检查信息 end-----------------------



		//企业公示信息 -------------------start-----------------
		//二、企业公示信息
		EntpubInfo qygsInfo = new EntpubInfo();

		//-----------------企业公示信息-->企业年报 start-----------------------
		//企业年报列表信息--报送年度和发布日期

		List<EntpubAnnreportInfo> qygsQynbInfos = new ArrayList<EntpubAnnreportInfo>();


		if(null != annulReports){
			for(int i = 0 ; i < annulReports.size()  ; i++){
				AnnulReport annulReport = annulReports.get(i);
				EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();
				if(null != histories){
					//企业年报表--------------------start------------------------
					History history = histories.get(i);
					if(null != history){
						qygsQynbInfo.setSubmitYear(history.getYear());
						qygsQynbInfo.setPubDateTime(history.getPubDate());
						qygsQynbInfo.setFirstDate(history.getFirstDate());
					}
					//企业年报表--------------------end------------------------
				}


				//企业基本信息 --------------------start------------------------
				EntpubAnnreportBaseInfo qygsQynbJbInfo = new EntpubAnnreportBaseInfo();
				AnnulBase annulBase = annulReport.getBase();
				if(null != annulBase){
					qygsQynbJbInfo.setNum(annulBase.getRegno());

					if(null != base.getCreditcode() && "".equals(base.getCreditcode())){
						qygsQynbJbInfo.setNum(base.getCreditcode());
					}else{
						qygsQynbJbInfo.setNum(base.getRegno());
					}

					qygsQynbJbInfo.setName(annulBase.getEntname());
					qygsQynbJbInfo.setTel(annulBase.getTel());
					qygsQynbJbInfo.setZipCode(annulBase.getPostalcode());
					qygsQynbJbInfo.setAddress(annulBase.getAddr());
					qygsQynbJbInfo.setEmail(annulBase.getEmail());

					if("1".equals(annulBase.getIstransfer())){
						qygsQynbJbInfo.setIsStohrEquTransferred("是");
					}else if("0".equals(annulBase.getIstransfer())){
						qygsQynbJbInfo.setIsStohrEquTransferred("否");
					}

					if("1".equals(annulBase.getOpstate())){
						qygsQynbJbInfo.setOperatingStatus("开业");
					}else if("2".equals(annulBase.getOpstate())){
						qygsQynbJbInfo.setOperatingStatus(annulBase.getOpstate());
					}else if("3".equals(annulBase.getOpstate())){
						qygsQynbJbInfo.setOperatingStatus(annulBase.getOpstate());
					}else{
						qygsQynbJbInfo.setOperatingStatus(annulBase.getOpstate());
					}
					qygsQynbJbInfo.setHasWebsiteOrStore("0".equals(annulBase.getHaswebsite()) ? "否" : "是");
					qygsQynbJbInfo.setHasInvestInfoOrPurchOtherCorpEqu("0".equals(annulBase.getHasbrothers()) ? "否" : "是");
					if("0".equals(annulBase.getEmpnumispublish())){
						qygsQynbJbInfo.setEmpNum("企业选择不公示");
					}else if("1".equals(annulBase.getEmpnumispublish())){
						qygsQynbJbInfo.setEmpNum(annulBase.getEmpnum());
					}
					qygsQynbJbInfo.setHasExternalSecurity("0".equals(annulBase.getHasexternalsecurity()) ? "否" : "是");
					qygsQynbJbInfo.setAffiliation(annulBase.getMembershipfun());
				}
				qygsQynbInfo.setBaseInfo(qygsQynbJbInfo);
				//企业基本信息 --------------------start------------------------

				//网站或网店信息 ------------start----------------------
				List<EntpubAnnreportWebsiteInfo> qygsQynbWzhwdInfos = new ArrayList<EntpubAnnreportWebsiteInfo>();
				List<WebSite> websites = annulReport.getWebSites();
				if(null != websites){
					for(WebSite website : websites){
						EntpubAnnreportWebsiteInfo qygsQynbWzhwdInfo = new EntpubAnnreportWebsiteInfo();
						qygsQynbWzhwdInfo.setType(website.getWebtypename());
						qygsQynbWzhwdInfo.setName(website.getWebsitname());
						qygsQynbWzhwdInfo.setWebsite(website.getDomain());
						qygsQynbWzhwdInfos.add(qygsQynbWzhwdInfo);
					}
				}
				qygsQynbInfo.setWebsiteInfos(qygsQynbWzhwdInfos);
				//网站或网店信息 ------------end----------------------

				//生产经营情况
				MNFzentproinfo mnFzentproinfo = annulReport.getmNFzentproinfo();
				List<EntpubAnnreportManageInfo> entpubAnnreportManageInfos = new ArrayList<>();
				if(null != mnFzentproinfo ){
					EntpubAnnreportManageInfo entpubAnnreportManageInfo = new EntpubAnnreportManageInfo();
					if(null != mnFzentproinfo.getVendinc() && !"".equals(mnFzentproinfo.getVendinc())){
						entpubAnnreportManageInfo.setSaleSum(mnFzentproinfo.getVendinc() + " 万元");
					}

					if(null != mnFzentproinfo.getRatgro() && !"".equals(mnFzentproinfo.getRatgro())){
						entpubAnnreportManageInfo.setSalarySum(mnFzentproinfo.getRatgro() + " 万元");
					}

					if(null != mnFzentproinfo.getNetinc()  && !"".equals(mnFzentproinfo.getNetinc() )){
						entpubAnnreportManageInfo.setNetProfit(mnFzentproinfo.getNetinc() + " 万元");
					}
					entpubAnnreportManageInfos.add(entpubAnnreportManageInfo);
				}
				qygsQynbInfo.setManageInfos(entpubAnnreportManageInfos);
				//生产经营情况

				//股东及出资信息  ------------start----------------------
				List<EntpubAnnreportStohrinvestInfo> qygsQynbGdjczInfos = new ArrayList<EntpubAnnreportStohrinvestInfo>();
				List<MNGsentinv> mNGsentinvs = annulReport.getmNGsentinv();
				if(null != mNGsentinvs){
					for(MNGsentinv mNGsentinv : mNGsentinvs){
						EntpubAnnreportStohrinvestInfo qygsQynbGdjczInfo = new EntpubAnnreportStohrinvestInfo();

						qygsQynbGdjczInfo.setStockholder(mNGsentinv.getInv());
						qygsQynbGdjczInfo.setSubAmount(mNGsentinv.getmNGsentinvsubcon().getSubconam());
						qygsQynbGdjczInfo.setSubDateTime(mNGsentinv.getmNGsentinvsubcon().getCondate());
						qygsQynbGdjczInfo.setSubMethod(mNGsentinv.getmNGsentinvsubcon().getConform());
						qygsQynbGdjczInfo.setPaidAmount(mNGsentinv.getmNGsentinvaccon().getAcconam());
						qygsQynbGdjczInfo.setPaidDateTime(mNGsentinv.getmNGsentinvaccon().getAccondate());
						qygsQynbGdjczInfo.setPaidMethod(mNGsentinv.getmNGsentinvaccon().getAcconform());

						qygsQynbGdjczInfos.add(qygsQynbGdjczInfo);

					}
				}
				qygsQynbInfo.setStohrInvestInfos(qygsQynbGdjczInfos);;
				//股东及出资信息  ------------end----------------------

				//对外投资信息-------------------start-----------------
				List<EntpubAnnreportExtinvestInfo> qygsQynbDwtzInfos = new ArrayList<EntpubAnnreportExtinvestInfo>();
				List<Ngstzentinfo> ngstzentinfos = annulReport.getNgstzentinfos();
				if (ngstzentinfos!=null) {
					for (Ngstzentinfo ngstzentinfo : ngstzentinfos) {
						EntpubAnnreportExtinvestInfo entpubAnnreportExtinvestInfo = new EntpubAnnreportExtinvestInfo();
						
						entpubAnnreportExtinvestInfo.setEnterpriseName(ngstzentinfo.getEntname());
						entpubAnnreportExtinvestInfo.setRegNum(ngstzentinfo.getTzregno());
						qygsQynbDwtzInfos.add(entpubAnnreportExtinvestInfo);
					}
				}
				qygsQynbInfo.setExtInvestInfos(qygsQynbDwtzInfos);
				//对外投资信息-------------------end-----------------

				//企业资产状况信息-------------------start-----------------
				EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();
				Mean means = annulReport.getMeans();
				if(means != null){
					qygsQynbQyzczkInfo.setAssetAmount("0".equals( means.getAssgroispublish()) ? "企业选择不公示" : means.getAssgro());
					qygsQynbQyzczkInfo.setTotalEquity("0".equals( means.getTotequispublish()) ? "企业选择不公示" : means.getTotequ());
					qygsQynbQyzczkInfo.setSalesAmount("0".equals( means.getVendincispublish()) ? "企业选择不公示" : means.getVendinc());
					qygsQynbQyzczkInfo.setProfitAmount("0".equals( means.getProgroispublish()) ? "企业选择不公示" :means.getProgro());
					qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount("0".equals(means.getMaibusincispublish()) ? "企业选择不公示" :means.getMaibusinc());
					qygsQynbQyzczkInfo.setNetProfit("0".equals(means.getNetincispublish()) ? "企业选择不公示" :means.getNetinc());
					qygsQynbQyzczkInfo.setTaxesAmount("0".equals(means.getRatgroispublish()) ? "企业选择不公示" :means.getRatgro());
					qygsQynbQyzczkInfo.setLiabilityAmount("0".equals(means.getLiagroispublish()) ? "企业选择不公示" :means.getLiagro());
				}

				qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);
				//企业资产状况信息-------------------end-----------------

				//股权变更信息-------------------start-----------------
				List<EntpubAnnreportEquchangeInfo> entpubAnnreportEquchangeInfos = new ArrayList<>();
				List<AnnulStock> stocks = annulReport.getStocks();
				if(null != stocks){
					for(AnnulStock annulStock : stocks){
						EntpubAnnreportEquchangeInfo entpubAnnreportEquchangeInfo = new EntpubAnnreportEquchangeInfo();
						entpubAnnreportEquchangeInfo.setStockholder(annulStock.getStockholder());
						entpubAnnreportEquchangeInfo.setPreOwnershipRatio(annulStock.getStockrightbeforebl());
						entpubAnnreportEquchangeInfo.setPostOwnershipRatio(annulStock.getStockrightafterbl());
						entpubAnnreportEquchangeInfo.setDateTime(annulStock.getStockrightchangedate());

						entpubAnnreportEquchangeInfos.add(entpubAnnreportEquchangeInfo);
					}
				}
				qygsQynbInfo.setEquChangeInfos(entpubAnnreportEquchangeInfos);
				//股权变更信息-------------------end-----------------

				//修改记录-------------------start-----------------
				List<Modify> modifies = annulReport.getModifies();
				List<EntpubAnnreportModifyInfo> changeInfos = new ArrayList<>();
				if(null != modifies && modifies.size() > 0){
					for(Modify modify : modifies){
						EntpubAnnreportModifyInfo entpubAnnreportModifyInfo = new EntpubAnnreportModifyInfo();
						entpubAnnreportModifyInfo.setDateTime(modify.getModidate());
						entpubAnnreportModifyInfo.setItem(modify.getModiitem());
						entpubAnnreportModifyInfo.setPreContent(modify.getModibe());
						entpubAnnreportModifyInfo.setPostContent(modify.getModiaf());
						changeInfos.add(entpubAnnreportModifyInfo);
					}
				}
				qygsQynbInfo.setChangeInfos(changeInfos);

				//修改记录-------------------end-----------------
				qygsQynbInfos.add(qygsQynbInfo);
			}
		}

		qygsInfo.setAnnReports(qygsQynbInfos);

		//股东及出资信息  股东及出资信息 -------------------start-----------------
		EntpubStohrinvestInfo entpubStohrinvestInfo = new EntpubStohrinvestInfo();

		List<EntpubSStohrinvestInfo> stohrInvestInfos = new ArrayList<>();
		List<JInvinfo> jInvinfoList = invsub.getJInvinfoList();
		if(null != jInvinfoList){
			for(JInvinfo jInvinfo : jInvinfoList){
				EntpubSStohrinvestInfo entpubSStohrinvestInfo = new EntpubSStohrinvestInfo();
//			entpubSStohrinvestInfo.setStockholder(jInvinfo);
//			entpubSStohrinvestInfo.setSubAmount();
//			entpubSStohrinvestInfo.setSubDetails();
//			entpubSStohrinvestInfo.setPaidAmount();
//			entpubSStohrinvestInfo.setPaidDetails();
				stohrInvestInfos.add(entpubSStohrinvestInfo);
			}
		}
		entpubStohrinvestInfo.setStohrInvestInfos(stohrInvestInfos);
		qygsInfo.setStohrInvestInfo(entpubStohrinvestInfo);
		//股东及出资信息 股东及出资信息 -------------------end-----------------

		//股东及出资信息 变更信息 -------------------start-----------------
		List<EntpubStohrinvestChangeInfo> changeInfos = new ArrayList<>();
		List<Modifie> modifies = invsub.getModifies();
		if(null != modifies){
			for(Modifie modifie : modifies){
				EntpubStohrinvestChangeInfo entpubStohrinvestChangeInfo = new EntpubStohrinvestChangeInfo();
//			entpubStohrinvestChangeInfo.setItem();
//			entpubStohrinvestChangeInfo.setPreContent();
//			entpubStohrinvestChangeInfo.setPostContent();
//			entpubStohrinvestChangeInfo.setDateTime();
				changeInfos.add(entpubStohrinvestChangeInfo);
			}
		}
		entpubStohrinvestInfo.setChangeInfos(changeInfos);
		qygsInfo.setStohrInvestInfo(entpubStohrinvestInfo);
		//股东及出资信息 变更信息 -------------------end-----------------


		//股权变更信息 -------------------start-----------------
		List<ListInfo> listInfos = transinfo.getList();
		EntpubEquchangeInfo equChangeInfo = new EntpubEquchangeInfo();
		List<EntpubEEquchangeInfo> equChangeInfos = new ArrayList<>();
		if(null != listInfos){
			for(ListInfo listInfo : listInfos){
				EntpubEEquchangeInfo entpubEEquchangeInfo = new EntpubEEquchangeInfo();
//			entpubEEquchangeInfo.setStockholder();
//			entpubEEquchangeInfo.setPreOwnershipRatio();
//			entpubEEquchangeInfo.setPostOwnershipRatio();
//			entpubEEquchangeInfo.setDateTime();
				equChangeInfos.add(entpubEEquchangeInfo);
			}
		}
		equChangeInfo.setEquChangeInfos(equChangeInfos);
		qygsInfo.setEquChangeInfo(equChangeInfo);

		//股权变更信息 -------------------end-----------------

		//行政许可信息 -------------------start---------------
		List<ListInfo2> listInfo2s = licinfo.getList();
		EntpubAdmlicInfo admLicInfo = new EntpubAdmlicInfo();
		List<EntpubAAdmlicInfo> admlicInfos = new ArrayList<>();
		if(null != listInfo2s){
			for(ListInfo2 listInfo2 : listInfo2s){
				EntpubAAdmlicInfo entpubAAdmlicInfo = new EntpubAAdmlicInfo();
//			entpubAAdmlicInfo.setAdmlicDetail();
//			entpubAAdmlicInfo.setContent();
//			entpubAAdmlicInfo.setDeciAuthority();
//			entpubAAdmlicInfo.setDetail();
//			entpubAAdmlicInfo.setEndDateTime();
//			entpubAAdmlicInfo.setLicenceName();
//			entpubAAdmlicInfo.setLicenceNum();
//			entpubAAdmlicInfo.setStartDateTime();
//			entpubAAdmlicInfo.setStatus();

				admlicInfos.add(entpubAAdmlicInfo);
			}
		}
		admLicInfo.setAdmlicInfos(admlicInfos);
		qygsInfo.setAdmLicInfo(admLicInfo);
		//行政许可信息 -------------------end---------------

		//知识产权出质登记信息表 -------------------end---------------
		List<PleInnerInfo> pleinfos = pleInfo.getPleinfolist();

		EntpubIntellectualproregInfo intellectualProRegInfo = new EntpubIntellectualproregInfo();
		List<EntpubIIntellectualproregInfo> intellectualProRegInfos = new ArrayList<>();
		if(null != pleinfos){
			for(PleInnerInfo pleinfo : pleinfos){
				EntpubIIntellectualproregInfo entpubIIntellectualproregInfo =
						new EntpubIIntellectualproregInfo();
//			entpubIIntellectualproregInfo.setChangeSitu();
//			entpubIIntellectualproregInfo.setMortgageeName();
//			entpubIIntellectualproregInfo.setMortgagorName();
//			entpubIIntellectualproregInfo.setName();
//			entpubIIntellectualproregInfo.setPledgeRegDeadline();
//			entpubIIntellectualproregInfo.setRegNum();
//			entpubIIntellectualproregInfo.setStatus();
//			entpubIIntellectualproregInfo.setType();
				intellectualProRegInfos.add(entpubIIntellectualproregInfo);
			}
		}
		intellectualProRegInfo.setIntellectualProRegInfos(intellectualProRegInfos);
		qygsInfo.setIntellectualProRegInfo(intellectualProRegInfo);
		//知识产权出质登记信息表 -------------------end---------------

		//行政处罚信息 -------------------start---------------
		EntpubAdmpunishInfo admPunishInfo = new EntpubAdmpunishInfo();
		List<EntpubAAdmpunishInfo> admPunishInfos = new ArrayList<>();
		List<JPeninfo> jPeninfos = peninfo.getjPeninfos();
		if(null != jPeninfos){
			for (JPeninfo jPeninfo : jPeninfos){
				EntpubAAdmpunishInfo admpunishInfo = new EntpubAAdmpunishInfo();
//			admpunishInfo.setDeciAuthority();
//			admpunishInfo.setDeciDateTime();
//			admpunishInfo.setIllegalActType();
//			admpunishInfo.setNote();
//			admpunishInfo.setPunishContent();
//			admpunishInfo.setPunishRepNum();
				admPunishInfos.add(admpunishInfo);
			}
		}
		admPunishInfo.setAdmPunishInfos(admPunishInfos);
		qygsInfo.setAdmPunishInfo(admPunishInfo);
		//行政处罚信息 -------------------end---------------
		//企业公示信息 -------------------end-----------------

		//其他部门公示信息 -------------------start-----------------
		OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();
		//行政许可信息------------------start------------------
		OthrdeptpubAdmlicInfo othrdeptpubAdmlicInfo = new OthrdeptpubAdmlicInfo();
		List<OthrdeptpubAAdmlicInfo> admLicInfos = new ArrayList<>();
		if(null != qlicinfos){
			for(Qlicinfo qlicinfo : qlicinfos){
				OthrdeptpubAAdmlicInfo othrdeptpubAAdmlicInfo = new OthrdeptpubAAdmlicInfo();
				othrdeptpubAAdmlicInfo.setContent(qlicinfo.getLicitem());
				othrdeptpubAAdmlicInfo.setDeciAuthority(qlicinfo.getLicanth());
//			othrdeptpubAAdmlicInfo.setDetail();
				othrdeptpubAAdmlicInfo.setEndDateTime(qlicinfo.getValto());
				othrdeptpubAAdmlicInfo.setLicenceName(qlicinfo.getLicname());
				othrdeptpubAAdmlicInfo.setLicenceNum(qlicinfo.getLicno());
				othrdeptpubAAdmlicInfo.setStartDateTime(qlicinfo.getValfrom());
				if(null == qlicinfo.getType() || "1".equals(qlicinfo.getType()) ){
					othrdeptpubAAdmlicInfo.setStatus("有效");
				}else if(null == qlicinfo.getType() || "1".equals(qlicinfo.getType()) ){
					othrdeptpubAAdmlicInfo.setStatus(qlicinfo.getType());
				}else if("2".equals(qlicinfo.getType()) ){
					othrdeptpubAAdmlicInfo.setStatus(qlicinfo.getType());
				}else if("3".equals(qlicinfo.getType()) ){
					othrdeptpubAAdmlicInfo.setStatus(qlicinfo.getType());
				}else if("4".equals(qlicinfo.getType()) ){
					othrdeptpubAAdmlicInfo.setStatus(qlicinfo.getType());
				}

				List<QlicinfoAlter> qlicinfoAlters = qlicinfo.getAlters();
				List<OthrdeptpubAAdmlicInfoDetail> othrdeptpubAAdmlicInfoDetails =
						new ArrayList<>();
				if(null != qlicinfoAlters){
					for(QlicinfoAlter qlicinfoAlter : qlicinfoAlters){
						OthrdeptpubAAdmlicInfoDetail othrdeptpubAAdmlicInfoDetail =
								new OthrdeptpubAAdmlicInfoDetail();
						//设置字段值
//						othrdeptpubAAdmlicInfoDetail.setAlterItem();
//						othrdeptpubAAdmlicInfoDetail.setAlterDate();
//						othrdeptpubAAdmlicInfoDetail.setPreAlter();
//						othrdeptpubAAdmlicInfoDetail.setPostAlter();
						othrdeptpubAAdmlicInfoDetails.add(othrdeptpubAAdmlicInfoDetail);
					}
				}
				othrdeptpubAAdmlicInfo.setOthrdeptpubAAdmlicInfoDetails(othrdeptpubAAdmlicInfoDetails);
				admLicInfos.add(othrdeptpubAAdmlicInfo);
			}
		}
		othrdeptpubAdmlicInfo.setAdmLicInfos(admLicInfos);
		qtbmgsInfo.setAdmLicInfo(othrdeptpubAdmlicInfo);
		//行政许可信息------------------end------------------

		//行政处罚信息------------------start------------------
		OthrdeptpubAdmpunishInfo othrdeptpubAdmpunishInfo = new OthrdeptpubAdmpunishInfo();
		List<OthrdeptpubAAdmpunishInfo> othrdeptpubAAdmpunishInfos = new ArrayList<>();

		if(null != qpeninfos){
			for(Qpeninfo qpeninfo : qpeninfos){
				OthrdeptpubAAdmpunishInfo othrdeptpubAAdmpunishInfo = new OthrdeptpubAAdmpunishInfo();
//			othrdeptpubAAdmpunishInfo.setDeciAuthority();
//			othrdeptpubAAdmpunishInfo.setDeciDateTime();
//			othrdeptpubAAdmpunishInfo.setDetail();
//			othrdeptpubAAdmpunishInfo.setIllegalActType();
//			othrdeptpubAAdmpunishInfo.setNote();
//			othrdeptpubAAdmpunishInfo.setPunishContent();
//			othrdeptpubAAdmpunishInfo.setPunishRepNum();
				othrdeptpubAAdmpunishInfos.add(othrdeptpubAAdmpunishInfo);
			}
		}
		othrdeptpubAdmpunishInfo.setAdmPunishInfos(othrdeptpubAAdmpunishInfos);
		qtbmgsInfo.setAdmPunishInfo(othrdeptpubAdmpunishInfo);
		//行政处罚信息------------------end------------------

		//其他部门公示信息 -------------------end-----------------

		//司法协助公示信息 -------------------start-----------------
		JudasspubInfo judasspubInfo = new JudasspubInfo();
		//股权冻结信息-----------------------start---------------------
		JudasspubEqufreezeInfo equFreezeInfo = new JudasspubEqufreezeInfo();
		List<JudasspubEEqufreezeInfo> equFreezeInfos = new ArrayList<>();
		if(null != SFXZs){
			for(SFXZ sfxz : SFXZs){
				JudasspubEEqufreezeInfo judasspubEEqufreezeInfo = new JudasspubEEqufreezeInfo();

//			judasspubEEqufreezeInfo.setAssistPubNoticeNum();
//			judasspubEEqufreezeInfo.setDetail();
//			judasspubEEqufreezeInfo.setEquAmount();
//			judasspubEEqufreezeInfo.setExeCourt();
//			judasspubEEqufreezeInfo.setExecutedPerson();
//			judasspubEEqufreezeInfo.setStatus();

				equFreezeInfos.add(judasspubEEqufreezeInfo);
			}
		}
		equFreezeInfo.setEquFreezeInfos(equFreezeInfos);
		judasspubInfo.setEquFreezeInfo(equFreezeInfo);
		//股权冻结信息-----------------------end---------------------
		//股东变更信息-----------------------start---------------------
		JudasspubStohrchangeInfo stohrChangeInfo = new JudasspubStohrchangeInfo();
		List<JudasspubSStohrchangeInfo> stohrChangeInfos = new ArrayList<>();

		if(null != SFXZGDBGs){
			for(SFXZGDBG sfxzgdbg : SFXZGDBGs){
				JudasspubSStohrchangeInfo judasspubSStohrchangeInfo = new JudasspubSStohrchangeInfo();

//			judasspubSStohrchangeInfo.setAssignee();
//			judasspubSStohrchangeInfo.setDetail();
//			judasspubSStohrchangeInfo.setEquAmount();
//			judasspubSStohrchangeInfo.setExeCourt();
//			judasspubSStohrchangeInfo.setExecutedPerson();

				stohrChangeInfos.add(judasspubSStohrchangeInfo);
			}
		}
		stohrChangeInfo.setStohrChangeInfos(stohrChangeInfos);
		judasspubInfo.setStohrChangeInfo(stohrChangeInfo);
		//股东变更信息-----------------------end---------------------
		//司法协助公示信息 -------------------end-----------------

		gsxtFeedJson.setAicPubInfo(gsgsInfo);
		gsxtFeedJson.setEntPubInfo(qygsInfo);
		gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);
		
		return gsxtFeedJson;
	}
}

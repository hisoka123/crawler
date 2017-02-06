package com.storm.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.crawler.storm.def.SearchDetail;
import com.google.gson.GsonBuilder;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.storm.def.FunctionDefine;
import com.storm.domian.CreditchinaParam;
import com.storm.domian.CustomsParam;
import com.storm.domian.ExecptionMessage;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.JsonParam;
import com.storm.domian.LogonParam;
import com.storm.domian.PbccrcParam;
import com.storm.domian.RssParam;
import com.storm.domian.WebParam;
import com.storm.domian.WeiboApiParam;
import com.storm.domian.WeiboFriendShipApiParam;
import com.storm.domian.WeiboHandleParam;
import com.storm.domian.WeiboLogonParam;
import com.storm.function.CommonFunction;
import com.storm.function.CreditchinaFunction;
import com.storm.function.CustomsFunction;
import com.storm.function.DaiLianMengFunction;
import com.storm.function.FahaiccFunction;
import com.storm.function.GsxtFunction;
import com.storm.function.IecmsFunction;
import com.storm.function.JsonEngineFunction;
import com.storm.function.LogonFunction;
import com.storm.function.NacaoFunction;
import com.storm.function.PbccrcFunction;
import com.storm.function.QiyezhengxinApiFunction;
import com.storm.function.RenFaWangFunction;
import com.storm.function.RssEngineFunction;
import com.storm.function.ShixinWebFunction;
import com.storm.function.SxjlcxptFunction;
import com.storm.function.WebEngineFunction;
import com.storm.function.WeiboApiFriendShipFunction;
import com.storm.function.WeiboApiFunction;
import com.storm.function.WeiboHandleFunction;
import com.storm.function.WeiboLogonFunction;
import com.storm.function.ZjsfgkwCreditCompanyFunction;
import com.storm.function.ZjsfgkwCreditPersonalFunction;
import com.storm.function.ZjsfgkwExecuteCaseFunction;
import com.storm.function.ZjsfgkwLimitBiddingFunction;
import com.storm.function.ZjsfgkwLimitExitFunction;
import com.storm.function.ZjsfgkwLimitHighConsumFunction;
import com.storm.util.IPUtil;

public class CrawlerBolt extends BaseRichBolt {
  
	private static final long serialVersionUID = -4214010828683807700L;

	private OutputCollector collector; 

	public void execute(Tuple tuple) {
		long startTime = System.currentTimeMillis();
		
		ChannelLogger log = null;
		String logback = null;
		String input = tuple.getString(1);
		
		try { 
			FunctionCallParam fcm = new FunctionCallParam();
			fcm = fcm.fromParam(input);
			
			WebParam wep = fcm.getWebEngineParam();
			if (wep!=null) {
				logback = wep.getLogback();
			} else {
				wep = new WebParam();
			}
			
			log = ChannelLoggerFactory.getLogger(CrawlerBolt.class, logback); ////
			log.info("-------logback: " + wep.getLogback() + "------------");
			log.info("-------CrawlerBolt-------execute-----start------------");
			log.info("-------fcm-------\n" + fcm.toString());
			
			String function = fcm.getFunction();
			switch (function)
		      {
		         case FunctionDefine.CRAWLERENGINE ://爬取页面  支持动态、静态页面
		        	log.info("FunctionDefine.CRAWLERENGINE------"+FunctionDefine.CRAWLERENGINE);
		        	wep = fcm.getWebEngineParam();
		        	log.info("param:"+wep.toString());
		        	WebEngineFunction wrf = new WebEngineFunction(); 
		        	collector.emit(new Values(tuple.getValue(0), wrf.getData(wep)));
		        	collector.ack(tuple);
		            break;
		         case FunctionDefine.WEIBO_HANDLE_GETPAGE ://微博页面获取，WebClient处于登录状态
		        	log.info("FunctionDefine.WEIBO_HANDLE_GETPAGE------"+FunctionDefine.WEIBO_HANDLE_GETPAGE);
		        	WeiboLogonParam wlp = fcm.getWeiboLogonParam();
		        	WebParam webEngineParam = fcm.getWebEngineParam();
		        	log.info("param:"+wlp.toString());
		        	WeiboLogonFunction wlf = new WeiboLogonFunction();  
		        	collector.emit(new Values(tuple.getValue(0), wlf.getHtml(wlp, webEngineParam)));
		        	collector.ack(tuple);
		            break;
		         case FunctionDefine.WEIBO_HANDLE_USERACTION ://微博用户的关注或取消关注，WebClient处于登录状态
		        	log.info("FunctionDefine.WEIBO_HANDLE_USERACTION------"+FunctionDefine.WEIBO_HANDLE_USERACTION);
		        	WeiboHandleParam whep = fcm.getWeiboHandleEngineParam();
		        	log.info("param:"+whep.toString());
		        	WeiboHandleFunction whf = new WeiboHandleFunction();
		        	collector.emit(new Values(tuple.getValue(0), whf.userAction(whep)));
		        	collector.ack(tuple);
		            break;
		         case FunctionDefine.WEIBO_API_HOMETIMELINE ://微博API调用，需要传入API名称和AccessToken
		        	log.info("FunctionDefine.WEIBO_API_HOMETIMELINE------"+FunctionDefine.WEIBO_API_HOMETIMELINE);
		        	WeiboApiParam aep = fcm.getApiEngineParam();
		        	log.info("param:"+aep.toString());
		        	WeiboApiFunction waf = new WeiboApiFunction();
		        	collector.emit(new Values(tuple.getValue(0), waf.getHomeTimelineJson(aep)));
		        	collector.ack(tuple);
		            break;
		         case FunctionDefine.WEIBO_API_FRIENDSHIPS ://微博朋友关系
		        	log.info("FunctionDefine.WEIBO_API_FRIENDSHIPS------"+FunctionDefine.WEIBO_API_FRIENDSHIPS);
		        	WeiboFriendShipApiParam param = fcm.getWeiboFriendShipApiParam();
		        	log.info("param:"+param.toString());
		        	WeiboApiFriendShipFunction wafsf = new WeiboApiFriendShipFunction();
		        	collector.emit(new Values(tuple.getValue(0), wafsf.getFriendShipJson(param)));
		        	collector.ack(tuple);
			        break;
		         case FunctionDefine.RSS_FETCH ://RSS采集，需要传入RSS订阅的URL
		        	log.info("FunctionDefine.RSS_FETCH------"+FunctionDefine.RSS_FETCH);
		        	WebParam rssEngineParam = fcm.getWebEngineParam();
		        	RssParam rp = fcm.getRssParam();
		        	log.info("param:"+rp.toString());
		        	RssEngineFunction wef = new RssEngineFunction();
		        	collector.emit(new Values(tuple.getValue(0), wef.getRss(rp, rssEngineParam)));
		        	collector.ack(tuple);
		            break;
		         case FunctionDefine.JSON_FETCH ://Json采集，需要传入Json的URL
		        	log.info("FunctionDefine.JSON_FETCH------"+FunctionDefine.JSON_FETCH);
		        	JsonParam jp = fcm.getJsonParam();
		        	log.info("param:"+jp.toString());
		        	JsonEngineFunction jef = new JsonEngineFunction();
		        	collector.emit(new Values(tuple.getValue(0), jef.getJson(jp)));
		        	collector.ack(tuple);
		            break;
		         case FunctionDefine.COOKIE_HANDLE_GETPAGE ://登录页面获取，WebClient处于登录状态
			        log.info("FunctionDefine.COOKIE_HANDLE_GETPAGE------"+FunctionDefine.COOKIE_HANDLE_GETPAGE);
			        WebParam webEngineParam2 = fcm.getWebEngineParam();
			        LogonParam lp = fcm.getLogonParam();
			        log.info("param:"+lp.toString());
			        LogonFunction lf = new LogonFunction();  
			        collector.emit(new Values(tuple.getValue(0), lf.getHtml(lp, webEngineParam2)));
			        collector.ack(tuple);
			        break;
		         case FunctionDefine.PBCCRC_TOLOGINPAGE : //获取人行征信中心的登录页， 输入：null | 发射：登录页cookies、验证码图片URL
		        	 log.info("FunctionDefine.PBCCRC_TOLOGINPAGE------"+FunctionDefine.PBCCRC_TOLOGINPAGE);
		        	 PbccrcFunction pf1 = new PbccrcFunction();
		        	 WebParam pbccrcWebParam1 = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), pf1.loginPageHandle(pbccrcWebParam1)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.PBCCRC_LOGIN: //人行征信中心登录， 输入：登陆页cookies、username、password、imageCode | 发射：登录是否成功相关参数、登录操作得到的cookies
		        	 log.info("FunctionDefine.PBCCRC_LOGIN------"+FunctionDefine.PBCCRC_LOGIN);
		        	 PbccrcParam pp2 = fcm.getPbccrcParam();
		        	 log.info("param:"+pp2.toString());
		        	 PbccrcFunction pf2 = new PbccrcFunction();
		        	 WebParam pbccrcWebParam2 = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), pf2.login(pp2, pbccrcWebParam2)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.PBCCRC_GETREPORT: //获取征信报告，输入：登录成功的cookies，tradeCode | 输出：是否成功的相关参数，[获取操作得到的征信报告页]
		        	 log.info("FunctionDefine.PBCCRC_GETREPORT------"+FunctionDefine.PBCCRC_GETREPORT);
		        	 PbccrcParam pp3 = fcm.getPbccrcParam();
		        	 log.info("param:"+pp3.toString());
		        	 PbccrcFunction pf3 = new PbccrcFunction();
		        	 WebParam pbccrcWebParam = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), pf3.getReport(pp3, pbccrcWebParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.PBCCRC_GETPDFREPORT_IMAGECODE: //获取PDF征信报告的验证码图片，输入：登录成功的cookies | 输出：PDF征信报告的验证码图片url及相关参数
		        	 log.info("FunctionDefine.PBCCRC_GETPDFREPORT_IMAGECODE------"+FunctionDefine.PBCCRC_GETPDFREPORT_IMAGECODE);
		        	 PbccrcParam pp4 = fcm.getPbccrcParam();
		        	 log.info("param:"+pp4.toString());
		        	 PbccrcFunction pf4 = new PbccrcFunction();
		        	 WebParam pbccrcWebParam4 = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), pf4.getPDFReportImageCode(pp4,pbccrcWebParam4)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.PBCCRC_GETPDFREPORT: //获取PDF征信报告，输入：登录成功的cookies，PDF征信报告的imageCode | 输出：PDF征信报告的url及相关参数
		        	 log.info("FunctionDefine.PBCCRC_GETPDFREPORT------"+FunctionDefine.PBCCRC_GETPDFREPORT);
		        	 PbccrcParam pp5 = fcm.getPbccrcParam();
		        	 log.info("param:"+pp5.toString());
		        	 PbccrcFunction pf5 = new PbccrcFunction();
		        	 WebParam pbccrcWebParam5 = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), pf5.getPDFReport(pp5, pbccrcWebParam5)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.FAHAICC_LOGIN: //法海网 登录   输入：username password  输出：登陆成功的cookies	 
		        	 log.info("FunctionDefine.FAHAICC_LOGIN------"+FunctionDefine.FAHAICC_LOGIN);
		        	 Map<String, String> loginParamMap = fcm.getWebEngineParam().getParams();
		        	 log.info("param:"+loginParamMap);
		        	 FahaiccFunction fahaiccFunction1 = new FahaiccFunction();
		        	 collector.emit(new Values(tuple.getValue(0), fahaiccFunction1.login(loginParamMap, fcm.getWebEngineParam())));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.FAHAICC_SEARCH: //法海网 查询   输入：关键字 页码  输出：查询列表
		        	 log.info("FunctionDefine.FAHAICC_SEARCH------"+FunctionDefine.FAHAICC_SEARCH);
		        	 WebParam webParam = fcm.getWebEngineParam();
		        	 log.info("webParam:"+webParam);
		        	 FahaiccFunction fahaiccFunction2 = new FahaiccFunction();
		        	 collector.emit(new Values(tuple.getValue(0), fahaiccFunction2.search(webParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.GSXT_GETSERIALIZEDWEBRESPONSE:
		        	 log.info("FunctionDefine.GSXT_GETSERIALIZEDWEBRESPONSE------"+FunctionDefine.GSXT_GETSERIALIZEDWEBRESPONSE);
		        	 WebParam gsxtWebParam1 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+gsxtWebParam1);
		        	 GsxtFunction gsxtFunction1 = new GsxtFunction();
		        	 collector.emit(new Values(tuple.getValue(0), gsxtFunction1.getSerializedAllIn(gsxtWebParam1)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.GSXT_SEARCH:
		        	 log.info("FunctionDefine.GSXT_SEARCH------"+FunctionDefine.GSXT_SEARCH);
		        	 WebParam gsxtWebParam2 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+gsxtWebParam2);
		        	 GsxtFunction gsxtFunction2 = new GsxtFunction();
		        	 String result2 = gsxtFunction2.search(gsxtWebParam2);
		        	 log.info("============[result]===============：" + result2);
		        	 collector.emit(new Values(tuple.getValue(0), result2));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.GSXT_SEARCH_WITH_OCR:
		        	 log.info("FunctionDefine.GSXT_SEARCH_WITH_OCR------"+FunctionDefine.GSXT_SEARCH_WITH_OCR);
		        	 WebParam gsxtWebParam3 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+gsxtWebParam3);
		        	 GsxtFunction gsxtFunction3 = new GsxtFunction();
		        	 String result3 = gsxtFunction3.searchWithOCR(gsxtWebParam3);
		        	 log.info("============[result]===============：" + result3);
		        	 collector.emit(new Values(tuple.getValue(0), result3));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.DAILIANMENG_GETSERIALIZEDWEBRESPONSE:
		        	 log.info("FunctionDefine.DAILIANMENG_GETSERIALIZEDWEBRESPONSE------"+FunctionDefine.DAILIANMENG_GETSERIALIZEDWEBRESPONSE);
		        	 WebParam dlmParam = fcm.getWebEngineParam();
		        	 log.info("webParam:"+dlmParam);
		        	 DaiLianMengFunction dlmFunction = new DaiLianMengFunction();
		        	 collector.emit(new Values(tuple.getValue(0), dlmFunction.getSerializedAllIn(dlmParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.DAILIANMENG_SEARCH:
		        	 log.info("FunctionDefine.DAILIANMENG_SEARCH------"+FunctionDefine.DAILIANMENG_SEARCH);
		        	 WebParam dlmWebParam2 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+dlmWebParam2);
		        	 DaiLianMengFunction dlmFunction2 = new DaiLianMengFunction();
		        	 collector.emit(new Values(tuple.getValue(0), dlmFunction2.search(dlmWebParam2)));
		        	 collector.ack(tuple);	
		        	 break;
		         case FunctionDefine.DAILIANMENG_DETAIL:
		        	 log.info("FunctionDefine.DAILIANMENG_DETAIL------"+FunctionDefine.DAILIANMENG_DETAIL);
		        	 WebParam dlmWebParam3 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+dlmWebParam3);
		        	 DaiLianMengFunction dlmFunction3 = new DaiLianMengFunction();
		        	 collector.emit(new Values(tuple.getValue(0), dlmFunction3.getDetailHtml(dlmWebParam3)));
		        	 collector.ack(tuple);	
		        	 break;	
		         case FunctionDefine.DAILIANMENG_SEARCHWITCHOCR:
		        	 log.info("FunctionDefine.DAILIANMENG_SEARCHWITCHOCR------"+FunctionDefine.DAILIANMENG_SEARCHWITCHOCR);
		        	 WebParam dlmWebParam4 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+dlmWebParam4);
		        	 DaiLianMengFunction dlmFunction4 = new DaiLianMengFunction();
		        	 collector.emit(new Values(tuple.getValue(0), dlmFunction4.searchWitchOCR(dlmWebParam4)));
		        	 collector.ack(tuple);	
		        	 break;	
		         case FunctionDefine.ZJSFGKWEXECUTECASE_SEARCH://浙法网-执行案件信息查询
		        	 log.info("FunctionDefine.ZJSFGKWEXECUTECASE_SEARCH------"+FunctionDefine.ZJSFGKWEXECUTECASE_SEARCH);
		        	 WebParam executeCaseWebParam = fcm.getWebEngineParam();
		        	 log.info("webParam:"+executeCaseWebParam);
		        	 ZjsfgkwExecuteCaseFunction executeCaseFunction = new ZjsfgkwExecuteCaseFunction();
		        	 collector.emit(new Values(tuple.getValue(0), executeCaseFunction.search(executeCaseWebParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.ZJSFGKWCREDIT://浙法网-曝光台-个人未履行生效裁判信息
		        	 log.info("FunctionDefine.ZJSFGKWCREDIT_SEARCH------"+FunctionDefine.ZJSFGKWCREDIT);
		        	 WebParam executeCaseWebParam1 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+executeCaseWebParam1);
		        	 ZjsfgkwCreditPersonalFunction zjsfgkwCreditPersonalFunction = new ZjsfgkwCreditPersonalFunction();
		        	 collector.emit(new Values(tuple.getValue(0), zjsfgkwCreditPersonalFunction.search(executeCaseWebParam1)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.ZJSFGKWCREDITCOMPANY://浙法网-曝光台-单位未履行生效裁判信息
		        	 log.info("FunctionDefine.ZJSFGKWCREDITCOMPANY_SEARCH------"+FunctionDefine.ZJSFGKWCREDITCOMPANY);
		        	 WebParam executeCaseWebParam2 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+executeCaseWebParam2);
		        	 ZjsfgkwCreditCompanyFunction zjsfgkwCreditCompanyFunction = new ZjsfgkwCreditCompanyFunction();
		        	 collector.emit(new Values(tuple.getValue(0), zjsfgkwCreditCompanyFunction.search(executeCaseWebParam2)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.ZJSFGKWLIMITHIGHCONSUM://浙法网-执行惩戒-限制高消费
		        	 log.info("FunctionDefine.ZJSFGKWCREDITCOMPANY_SEARCH------"+FunctionDefine.ZJSFGKWCREDITCOMPANY);
		        	 WebParam executeCaseWebParam3 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+executeCaseWebParam3);
		        	 ZjsfgkwLimitHighConsumFunction zjsfgkwLimitHighConsumFunction = new ZjsfgkwLimitHighConsumFunction();
		        	 collector.emit(new Values(tuple.getValue(0), zjsfgkwLimitHighConsumFunction.search(executeCaseWebParam3)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.ZJSFGKWLIMITEXIT://浙法网-执行惩戒-限制出境
		        	 log.info("FunctionDefine.ZJSFGKWCREDITCOMPANY_SEARCH------"+FunctionDefine.ZJSFGKWCREDITCOMPANY);
		        	 WebParam executeCaseWebParam4 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+executeCaseWebParam4);
		        	 ZjsfgkwLimitExitFunction zjsfgkwLimitExitFunction = new ZjsfgkwLimitExitFunction();
		        	 collector.emit(new Values(tuple.getValue(0), zjsfgkwLimitExitFunction.search(executeCaseWebParam4)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.ZJSFGKWLIMITBIDDING://浙法网-执行惩戒-限制招投标
		        	 log.info("FunctionDefine.ZJSFGKWCREDITCOMPANY_SEARCH------"+FunctionDefine.ZJSFGKWCREDITCOMPANY);
		        	 WebParam executeCaseWebParam5 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+executeCaseWebParam5);
		        	 ZjsfgkwLimitBiddingFunction zjsfgkwLimitBiddingFunction = new ZjsfgkwLimitBiddingFunction();
		        	 collector.emit(new Values(tuple.getValue(0), zjsfgkwLimitBiddingFunction.search(executeCaseWebParam5)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.CREDITCHINA_SEARCH:
		        	 log.info("FunctionDefine.CREDITCHINA_SEARCH------"+FunctionDefine.CREDITCHINA_SEARCH);
		        	 WebParam creditChinaWebParam = fcm.getWebEngineParam();
		        	 CreditchinaParam ccp = fcm.getCreditchinaParam();
		        	 log.info("param:"+ccp.toString());
		        	 CreditchinaFunction ccf2 = new CreditchinaFunction();
		        	 collector.emit(new Values(tuple.getValue(0), ccf2.search(ccp, creditChinaWebParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.CREDITCHINA_OCR:// 信用中国
		        	 log.info("FunctionDefine.CREDITCHINA_OCR------"+FunctionDefine.CREDITCHINA_OCR);
		        	 WebParam creditChinaWebParam1 = fcm.getWebEngineParam();
		        	 CreditchinaParam ccp1 = fcm.getCreditchinaParam();
		        	 log.info("param:"+ccp1.toString());
		        	 CreditchinaFunction ccf3 = new CreditchinaFunction();
		        	 collector.emit(new Values(tuple.getValue(0), ccf3.searchWithOCR(ccp1, creditChinaWebParam1)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.IECMS_GETGNAMEPAGE://添加贸易备案
		        	 log.info("FunctionDefine.IECMS_GETGNAMEpage------"+FunctionDefine.IECMS_GETGNAMEPAGE);
		        	// IecmsParam iecmsParam = new IecmsParam();
		        	 WebParam iecmsWebParampage = fcm.getWebEngineParam();
		        	 log.info("webParam:"+iecmsWebParampage);
		        	 IecmsFunction iccmsFunctionpage = new IecmsFunction();
		        	 collector.emit(new Values(tuple.getValue(0), iccmsFunctionpage.getSerializedAllIn(iecmsWebParampage)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.IECMS_GETGNAME://添加贸易备案
		        	 log.info("FunctionDefine.IECMS_GETGNAME------"+FunctionDefine.IECMS_GETGNAME);	
		        	 WebParam iecmsWebParam = fcm.getWebEngineParam();
		        	 log.info("webParam:"+iecmsWebParam);
		        	 IecmsFunction iccmsFunction = new IecmsFunction();
		        	 collector.emit(new Values(tuple.getValue(0), iccmsFunction.search(iecmsWebParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.IECMS_GETGNAMEONEC://添加贸易备案
		        	 log.info("FunctionDefine.IECMS_GETGNAMEONEC------"+FunctionDefine.IECMS_GETGNAMEONEC);	
		        	 WebParam iecmsWebParamo = fcm.getWebEngineParam();
		        	 log.info("webParam:"+iecmsWebParamo);
		        	 IecmsFunction iccmsFunctiono = new IecmsFunction();
		        	 collector.emit(new Values(tuple.getValue(0), iccmsFunctiono.searchWithOCR(iecmsWebParamo)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.RENFAWANG_GETSERIALIZEDWEBRESPONSE:
		        	 log.info("FunctionDefine.RENFAWANG_GETSERIALIZEDWEBRESPONSE------"+FunctionDefine.RENFAWANG_GETSERIALIZEDWEBRESPONSE);
		        	// IecmsParam iecmsParam = new IecmsParam();
		        	 WebParam rfwParam = fcm.getWebEngineParam();
		        	 log.info("webParam:"+rfwParam);
		        	 RenFaWangFunction rfwFunction = new RenFaWangFunction();
		        	 collector.emit(new Values(tuple.getValue(0), rfwFunction.getSerializedAllIn(rfwParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.RENFAWANG_SEARCH:
		        	 log.info("FunctionDefine.RENFAWANG_SEARCH------"+FunctionDefine.RENFAWANG_SEARCH);	
		        	 WebParam rfwParam2 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+rfwParam2);
		        	 RenFaWangFunction rfwFunction2 = new RenFaWangFunction();
		        	 collector.emit(new Values(tuple.getValue(0), rfwFunction2.search(rfwParam2)));
		        	 collector.ack(tuple);
		        	 break;		
		         case FunctionDefine.RENFAWANG_DETAIL:
		        	 log.info("FunctionDefine.RENFAWANG_DETAIL------"+FunctionDefine.RENFAWANG_DETAIL);
		        	 WebParam rfwParam3 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+rfwParam3);
		        	 RenFaWangFunction rfwFunction3 = new RenFaWangFunction();
		        	 collector.emit(new Values(tuple.getValue(0), rfwFunction3.getDetailData(rfwParam3)));
		        	 collector.ack(tuple);	
		        	 break;
		         case FunctionDefine.RENFAWANG_SEARCHWITCHOCR:
		        	 log.info("FunctionDefine.RENFAWANG_SEARCHWITCHOCR------"+FunctionDefine.RENFAWANG_SEARCHWITCHOCR);
		        	 WebParam rfwParam4 = fcm.getWebEngineParam();
		        	 log.info("webParam:"+rfwParam4);
		        	 RenFaWangFunction rfwFunction4 = new RenFaWangFunction();
		        	 collector.emit(new Values(tuple.getValue(0), rfwFunction4.searchWitchOCR(rfwParam4)));
		        	 collector.ack(tuple);	
		        	 break;			        	 
		         case FunctionDefine.SHIXINPCODE://失信验证码
		        	 log.info("FunctionDefine.SHIXINPCODE------"+FunctionDefine.SHIXINPCODE);
		        	 WebParam pCodeWebParampage = fcm.getWebEngineParam();
		        	 log.info("webParam:"+pCodeWebParampage);
		        	 ShixinWebFunction shixinWebFunction = new ShixinWebFunction();
		        	 collector.emit(new Values(tuple.getValue(0), shixinWebFunction.getSerializedAllIn(pCodeWebParampage)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.SHIXIN://失信
		        	 log.info("FunctionDefine.SHIXIN------"+FunctionDefine.SHIXIN);
		        	 WebParam shixinWebParampage = fcm.getWebEngineParam();
		        	 log.info("webParam:"+shixinWebParampage);
		        	 ShixinWebFunction shixinWebFunction2 = new ShixinWebFunction();
		        	 collector.emit(new Values(tuple.getValue(0), shixinWebFunction2.search(shixinWebParampage)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.SHIXINDATAONCE://失信定时任务
		        	 log.info("FunctionDefine.SHIXINDATAONCE------"+FunctionDefine.SHIXINDATAONCE);
		        	 WebParam shixinDataOnceParampage = fcm.getWebEngineParam();
		        	 log.info("webParam:"+shixinDataOnceParampage);
		        	 ShixinWebFunction shixinWebFunction3 = new ShixinWebFunction();
		        	 collector.emit(new Values(tuple.getValue(0), shixinWebFunction3.searchDataOnce(shixinDataOnceParampage)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.CUSTOMS_TOSEARCHPAGE://中国海关网（获取cookies和验证码）
		        	 log.info("FunctionDefine.CUSTOMS_TOSEARCHPAGE--------------" + FunctionDefine.CUSTOMS_TOSEARCHPAGE);
		        	 CustomsFunction cufu = new CustomsFunction();
		        	 WebParam customWebParam = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), cufu.searchPageHandle(customWebParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.CUSTOMS_SEARCH://中国海关网（获取列表页）
		        	 log.info("FunctionDefine.CUSTOMS_SEARCH--------------" + FunctionDefine.CUSTOMS_SEARCH);
		        	 CustomsParam cupa = fcm.getCustomsParam();
		        	 log.info("function:"+cupa.toString());
		        	 CustomsFunction cufu2 = new CustomsFunction();
		        	 WebParam customWebParam2 = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), cufu2.search(cupa, customWebParam2)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.CUSTOMS_DETAIL://中国海关网（获取详情页）
		        	 log.info("FunctionDefine.CUSTOMS_DETAIL--------------" + FunctionDefine.CUSTOMS_DETAIL);
		        	 SearchDetail searchDetail = fcm.getSearchDetail();
		        	 log.info("function:"+searchDetail.toString());
		        	 CustomsFunction cufu3 = new CustomsFunction();
		        	 WebParam customWebParam3 = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), cufu3.searchDetail(searchDetail, customWebParam3)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.CUSTOMS_WITH_OCR://中国海关网
		        	 log.info("FunctionDefine.CUSTOMS_WITH_OCR--------------" + FunctionDefine.CUSTOMS_WITH_OCR);
		        	 CustomsFunction cufu4 = new CustomsFunction();
		        	 WebParam customWebParam4 = fcm.getWebEngineParam();
		        	 collector.emit(new Values(tuple.getValue(0), cufu4.searchWithOCR(customWebParam4)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.SXJLCXPTQUERYKEY://失信
		        	 log.info("FunctionDefine.SXJLCXPTQUERYKEY------"+FunctionDefine.SXJLCXPTQUERYKEY);
		        	 WebParam sxjlcxptquerykey = fcm.getWebEngineParam();
		        	 log.info("webParam:"+sxjlcxptquerykey);
		        	 SxjlcxptFunction sxjlcxptFunction = new SxjlcxptFunction();
		        	 collector.emit(new Values(tuple.getValue(0), sxjlcxptFunction.getHtml(sxjlcxptquerykey)));
		        	 collector.ack(tuple);
		        	 break;	
		         case FunctionDefine.NACAO_GETSERIALIZEDWEBRESPONSE://组织机构代码管理
		        	 log.info("FunctionDefine.NACAO_GETSERIALIZEDWEBRESPONSE------"+FunctionDefine.NACAO_GETSERIALIZEDWEBRESPONSE);
		        	 WebParam nacaofirstpagekey = fcm.getWebEngineParam();
		        	 log.info("webParam:"+nacaofirstpagekey);
		        	 NacaoFunction nacaoFunction = new NacaoFunction();
		        	 collector.emit(new Values(tuple.getValue(0), nacaoFunction.getSerializedAllIn(nacaofirstpagekey)));
		        	 collector.ack(tuple);
		         case FunctionDefine.NACAO_GETSERIALIZEDWEBRESPONSEQURY://组织机构查询
		        	 log.info("FunctionDefine.NACAO_GETSERIALIZEDWEBRESPONSEQURY------"+FunctionDefine.NACAO_GETSERIALIZEDWEBRESPONSEQURY);
		        	 WebParam nacaofirstpagekeyqury = fcm.getWebEngineParam();
		        	 log.info("webParam:"+nacaofirstpagekeyqury);
		        	 NacaoFunction nacaoFunctionqury = new NacaoFunction();
		        	 collector.emit(new Values(tuple.getValue(0), nacaoFunctionqury.search(nacaofirstpagekeyqury)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.COMMON_TOSEARCHPAGE:
		        	 log.info("FunctionDefine.COMMON_TOSEARCHPAGE------" + FunctionDefine.COMMON_TOSEARCHPAGE);
		        	 WebParam commonParam = fcm.getWebEngineParam();
		        	 log.info("webParam:" + commonParam);
		        	 CommonFunction commonFunction = new CommonFunction();
		        	 collector.emit(new Values(tuple.getValue(0), commonFunction.searchPageHandle(commonParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.ENTERPRISE_CREDIT://企业征信
		        	 log.info("FunctionDefine.ENTERPRISE_CREDIT------" + FunctionDefine.ENTERPRISE_CREDIT);
		        	 WebParam qiyezhengxinApiParam = fcm.getWebEngineParam();
		        	 log.info("qiyezhengxinApiParam:" + qiyezhengxinApiParam);
		        	 QiyezhengxinApiFunction qiyezhengxinApiFunction = new QiyezhengxinApiFunction();
		        	 collector.emit(new Values(tuple.getValue(0), qiyezhengxinApiFunction.search(qiyezhengxinApiParam)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.COMMON_SEARCH:
		        	 log.info("FunctionDefine.COMMON_SEARCH------" + FunctionDefine.COMMON_SEARCH);
		        	 WebParam commonParam2 = fcm.getWebEngineParam();
		        	 log.info("webParam:" + commonParam2);
		        	 CommonFunction commonFunction2 = new CommonFunction();
		        	 collector.emit(new Values(tuple.getValue(0), commonFunction2.search(commonParam2)));
		        	 collector.ack(tuple);
		        	 break;
		         case FunctionDefine.COMMON_OCR:
		        	 log.info("FunctionDefine.COMMON_OCR------" + FunctionDefine.COMMON_OCR);
		        	 WebParam commonParam3 = fcm.getWebEngineParam();
		        	 log.info("webParam:" + commonParam3);
		        	 CommonFunction commonFunction3 = new CommonFunction();
		        	 collector.emit(new Values(tuple.getValue(0), commonFunction3.searchWithOCR(commonParam3)));
		        	 collector.ack(tuple);
		        	 break;
		         default :
		        	 log.info("-----  FunctionCallMessage Error  -----function:"+function);
		      }
			log.info("========================================storm end!============================================"); 
			log.info("=======================================input: " + input);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("============= CrawlerBolt emitException  ============="); 
			String ip = IPUtil.getIp();
			String hostName = IPUtil.getHostName();
			
			ExecptionMessage em = new ExecptionMessage();
			em.setIp(ip);
			em.setHostName(hostName);
			//ExceptionName
			em.setExceptionName(e.toString()); 
			//ExceptionMsg
			String separator = System.getProperty("line.separator");
			StringBuffer sb = new StringBuffer(e.getMessage() + separator);
			//sb.append(IPUtil.getHostAndIpStr() + " ");
			StackTraceElement[] stackTraceElements = e.getStackTrace();
			for (StackTraceElement stackTraceElement : stackTraceElements) {
				sb.append(stackTraceElement.toString() + separator);
			}
			em.setExceptionMsg(sb.toString()); 
			log.info("input:"+input);
			log.info("ExecptionMessage:"+em.toString());
			log.info("============= CrawlerBolt emitException  ============="); 
			emitException(tuple, new GsonBuilder().create().toJson(em)); 
			collector.ack(tuple);
		} finally {
			log.returnRedisResource();
			long endTime = System.currentTimeMillis();
			log.info("====================[storm end]======================总耗时：" + (endTime-startTime) + "ms");
			
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) { 
		declarer.declare(new Fields("id", "result"));
	}
	
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}
	 
	protected void emitException(Tuple tuple, String exception) {
		getCollector().emit(tuple, new Values(tuple.getValue(0),exception));
	}
	 
	protected OutputCollector getCollector() {
		return collector;
	}
	
}

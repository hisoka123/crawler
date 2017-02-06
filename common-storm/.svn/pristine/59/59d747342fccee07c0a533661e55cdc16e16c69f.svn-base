package com.storm.topology;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.LocalDRPC;
import backtype.storm.StormSubmitter;
import backtype.storm.drpc.LinearDRPCTopologyBuilder;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;

import com.google.gson.GsonBuilder;
import com.module.htmlunit.definition.UtilDefinition;
import com.storm.bolt.CrawlerBolt;
import com.storm.def.FunctionDefine;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.LogonParam;
import com.storm.domian.PbccrcParam;
import com.storm.domian.RssParam;
import com.storm.domian.WebParam;
import com.storm.domian.WeiboLogonParam;

public class LocalTestCrawlerTopology implements Serializable {
 
	private static final long serialVersionUID = -3618574854098918415L;
	
	private static final Logger log = LoggerFactory.getLogger(LocalTestCrawlerTopology.class);
	
	public static void deploy(String[] args) throws AlreadyAliveException,InvalidTopologyException, InterruptedException {
		LinearDRPCTopologyBuilder builder = new LinearDRPCTopologyBuilder(StormTopologyConfig.getCrawlerenginetopology());
		builder.addBolt(new CrawlerBolt(), StormTopologyConfig.getCrawlerbolt_parallelismnum());
		HashMap env = new HashMap();
		//env.put(Config.NIMBUS_TASK_TIMEOUT_SECS, 150) ; //超时为150s useless
		Config conf = new Config(); 
		conf.setDebug(false);
		
		//Storm default is 30s
		conf.setMessageTimeoutSecs(300); //90->120
		
		//conf.setEnvironment(env); 
		conf.setNumWorkers(StormTopologyConfig.getWorkernum());
		StormSubmitter.submitTopology(StormTopologyConfig.getCrawlerenginetopology(), conf, builder.createRemoteTopology());

	}
	
	public static void localtest(String[] args) throws AlreadyAliveException,InvalidTopologyException, InterruptedException { 
		LinearDRPCTopologyBuilder builder = new LinearDRPCTopologyBuilder(StormTopologyConfig.getCrawlerenginetopology());
		builder.addBolt(new CrawlerBolt(), StormTopologyConfig.getCrawlerbolt_parallelismnum());
		Config conf = new Config(); 
		conf.setDebug(false);
		conf.setNumWorkers(StormTopologyConfig.getWorkernum());
		LocalDRPC drpc = new LocalDRPC();
        LocalCluster cluster = new LocalCluster(); 
        cluster.submitTopology(StormTopologyConfig.getCrawlerenginetopology(), conf, builder.createLocalTopology(drpc)); 
        
        log.info("------------drpc.execute---------------"); 
        //FunctionCallParam fcm = new FunctionCallParam();
        /* 
		fcm.setFunction(FunctionDefine.CRAWLERENGINE); 
		WebParam webParam = new WebParam();
		webParam.setUrl("https://www.baidu.com/link?url=CPZVj9CoDm8DGIwvjUXq0K0M5zSC4bzjMRQTzohrQDQrS2CB-BrEqSsdmlnXr7223tNoGJ_5S14VqigoGph0k_&amp;wd=&amp;eqid=c74f6e75000053c9000000025698b084"); 
		webParam.setUnit(UtilDefinition.HTMLUNIT);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
        */
        /*fcm.setFunction("crawlerengine"); 
        WebEngineParam webEngineParam = new WebEngineParam();
        webEngineParam.setUrl("http://www.zhihu.com/");
        webEngineParam.setUnit(UtilDefinition.HTMLUNIT);
        fcm.setWebEngineParam(webEngineParam); 
        */
        /*
        fcm.setFunction(FunctionDefine.WEIBO_API_HOMETIMELINE); 
        ApiEngineParam apiEngineParam = new ApiEngineParam();
        apiEngineParam.setAccessToken("2.00oTwkUFpHMPpDe31b060d84HBVE1E");
        apiEngineParam.setApi("HomeTimeline");
        fcm.setApiEngineParam(apiEngineParam);
        */
  
       /* fcm.setFunction(FunctionDefine.WEIBO_HANDLE_GETPAGE); 
        WeiboLogonParam weiboLogonParam = new WeiboLogonParam();
        weiboLogonParam.setUrl("http://s.weibo.com/user/36氪"); // or http://weibo.com/yangmiblog  or http://weibo.com/1645101450/info
        weiboLogonParam.setJsEnable(false);
        fcm.setWeiboLogonParam(weiboLogonParam);*/
        
        /*fcm.setFunction(FunctionDefine.PBCCRC_TOLOGINPAGE);*/
        /*fcm.setFunction(FunctionDefine.PBCCRC_GETPDFREPORT_IMAGECODE);
        String pbccrcParamStr = null;
        try {
			pbccrcParamStr = FileUtils.readFileToString(new File("C:/Users/Administrator/Desktop/aaaa.txt"), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
        PbccrcParam pbccrcParam = new GsonBuilder().create().fromJson(pbccrcParamStr, PbccrcParam.class);
        fcm.setPbccrcParam(pbccrcParam);*/
        
        /*
        fcm.setFunction(FunctionDefine.WEIBO_HANDLE_USERACTION); 
        WeiboHandleParam weiboHandleEngineParam = new WeiboHandleParam();
        weiboHandleEngineParam.setHandle(FunctionDefine.USER_DELATTENTION);// or FunctionDefine.USER_ATTENTION
        weiboHandleEngineParam.setUid("1195242865");//杨幂
        fcm.setWeiboHandleEngineParam(weiboHandleEngineParam);
        */
       
       /*fcm.setFunction(FunctionDefine.RSS_FETCH); 
        RssParam rssParam = new RssParam();
        rssParam.setUrl("http://news.baidu.com/ns?word=%D0%A1%C3%D7note+site%3A36kr.com&tn=newsrss&sr=0&cl=2&rn=20&ct=0");
        fcm.setRssParam(rssParam);*/
        
       /* fcm.setFunction(FunctionDefine.WEIBO_API_FRIENDSHIPS); 
        WeiboFriendShipApiParam weiboFriendShipApiParam = new WeiboFriendShipApiParam();
        weiboFriendShipApiParam.setAccessToken("2.00oTwkUF0gTxeW299bd4ce30PTpJEC");//userfetch / vcspark13520800817
        weiboFriendShipApiParam.setSourceId("1642351362");//angelababy
        weiboFriendShipApiParam.setTargetId("1763582395");//韩庚
        fcm.setWeiboFriendShipApiParam(weiboFriendShipApiParam);*/
     /*
        fcm.setFunction(FunctionDefine.COOKIE_HANDLE_GETPAGE); 
        LogonParam logonParam = new LogonParam();
        logonParam.setUrl("http://www.linkedin.com/vsearch/f?type=all&keywords=joe"); // or http://weibo.com/yangmiblog  or http://weibo.com/1645101450/info
        logonParam.setUnit(UtilDefinition.JSOUP);
        Set<String> domains = new HashSet<String>();
        domains.add(".linkedin.com");
        domains.add("www.linkedin.com");
        domains.add(".www.linkedin.com");
        logonParam.setDomains(domains);
        fcm.setLogonParam(logonParam);
       */
       /* fcm.setFunction(FunctionDefine.JSON_FETCH); 
        JsonParam jsonParam = new JsonParam();
        jsonParam.setUrl("http://www.zhihu.com/r/search?q=%E8%B0%A2%E7%86%8A%E7%8C%AB%E5%90%9B&type=people&offset=10");
        fcm.setJsonParam(jsonParam);*/
        
        /*fcm.setFunction(FunctionDefine.CRAWLERENGINE);
        WebParam webParam = new WebParam();
		webParam.setUrl("http://weixin.sogou.com/weixin?type=1&query=liangbo");
		webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);*/
		
         
        /*log.info("Json---->"+param);  
        String data = drpc.execute(stormTopologyConfig.getCrawlerenginetopology(), param);
        log.info("data---->"+data); */
        
        FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		webParam.setLogback(null);
		webParam.setSearchPage("http://gsxt.gdgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='vimg']");
		webParam.addParam("area", "guangdong");//
		webParam.addParam("keyword", "信和");	
		webParam.addParam("keywordXpath", "//input[@id='textfield']");
		webParam.addParam("imagecodeXpath", "//input[@id='code']");
		webParam.addParam("loginBtnXpath", "//a[@id='checkBtn']");
		fcm.setWebEngineParam(webParam);
		String param = new GsonBuilder().create().toJson(fcm);
		String data = drpc.execute(StormTopologyConfig.getCrawlerenginetopology(), param);
		log.info("data---->"+data);
        
	}
	
	public static void main(String[] args) throws AlreadyAliveException,InvalidTopologyException, InterruptedException {
		deploy(args);
		//localtest(args);
	}
	

}

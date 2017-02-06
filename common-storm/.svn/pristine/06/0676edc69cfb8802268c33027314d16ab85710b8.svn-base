package test;

import org.apache.thrift7.TException;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

import com.module.htmlunit.definition.UtilDefinition;
import com.storm.def.FunctionDefine;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.WebParam;

public class WeiboHotTopic {
	
	public static void main(String[] args) throws TException, DRPCExecutionException {
		DRPCClient client = null;  
        client = new DRPCClient("10.168.250.21", 3772);  
        System.out.println("--------DRCP Start---------");  
        
        FunctionCallParam fcm = new FunctionCallParam();
        fcm.setFunction(FunctionDefine.CRAWLERENGINE);
        WebParam wep = new WebParam();
        wep.setUrl("http://s.weibo.com/weibo/%2523%25E7%25AA%2581%25E5%258F%2591%2523?topnav=1&wvr=6&b=1&rd=newTips");
        wep.setUnit(UtilDefinition.JSOUP);
        fcm.setWebEngineParam(wep);
        String json = fcm.toJson(); 
        System.out.println(json);
        String html = client.execute("CrawlerEngineTopology", json);
		System.out.println("------------------------------------------------------------------------------------------");
	    System.out.println(html); 
        
	}

}

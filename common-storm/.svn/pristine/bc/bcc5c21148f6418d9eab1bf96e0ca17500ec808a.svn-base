package test;

import org.apache.thrift7.TException;

import com.module.htmlunit.definition.UtilDefinition;
import com.storm.def.FunctionDefine;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.WebParam;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

public class DrpcTest2 {
	
	public static void main(String[] args) throws TException, DRPCExecutionException {
		DRPCClient client = null;  
        //client = new DRPCClient("54.222.138.137", 3772); 
		client = new DRPCClient("10.168.250.21", 3772); 
        System.out.println("--------DRCP Start---------");  
        
        FunctionCallParam fcm = new FunctionCallParam();
        fcm.setFunction(FunctionDefine.CRAWLERENGINE);
        WebParam wep = new WebParam();
        //wep.setUrl("http://zhixing.court.gov.cn/search/");
        wep.setUrl("http://zhixing.court.gov.cn/search/");
        wep.setUnit(UtilDefinition.HTMLUNIT);
        fcm.setWebEngineParam(wep);
        String json = fcm.toJson(); 
        System.out.println(json);
        String html = client.execute("CrawlerEngineTopology", json);
		System.out.println("------------------------------------------------------------------------------------------");
	    System.out.println(html); 
        
	}

}

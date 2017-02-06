package test;

import java.util.HashSet;
import java.util.Set;

import org.apache.thrift7.TException;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

import com.module.htmlunit.definition.UtilDefinition;
import com.storm.def.FunctionDefine;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.LogonParam;

public class CookieUnitTest {

	public static void main(String[] args) throws TException, DRPCExecutionException {
		DRPCClient client = null;  
        client = new DRPCClient("10.168.250.21", 3772);   
        System.out.println("--------DRCP Start---------");  
         
        FunctionCallParam fcm = new FunctionCallParam();
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
        
        String json = fcm.toJson();  
        System.out.println(json);
          
		String html = client.execute("CrawlerEngineTopology", json);
		System.out.println("------------------------------------------------------------------------------------------");
	    System.out.println(html); 

	}

}

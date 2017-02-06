package test;

import org.apache.thrift7.TException;

import com.storm.def.FunctionDefine;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.RssParam;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

public class RssTest {

	public static void main(String[] args) throws TException, DRPCExecutionException {
		// TODO Auto-generated method stub
		DRPCClient client = null;  
        client = new DRPCClient("10.168.250.21", 3772);  
        System.out.println("--------DRCP Start---------");  
        
        FunctionCallParam fcm = new FunctionCallParam();
        fcm.setFunction(FunctionDefine.RSS_FETCH); 
        RssParam rssParam = new RssParam();
        rssParam.setUrl("http://news.baidu.com/ns?word=%D0%A1%C3%D7note+site%3A36kr.com&tn=newsrss&sr=0&cl=2&rn=20&ct=0");
        fcm.setRssParam(rssParam);
        
        String json = fcm.toJson();
        System.out.println(json);
          
        	  String html = client.execute("CrawlerEngineTopology", json);
        	  System.out.println("------------------------------------------------------------------------------------------");
              System.out.println(html); 
        
	}

}

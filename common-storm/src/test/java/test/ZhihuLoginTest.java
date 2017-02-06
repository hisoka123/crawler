package test;

import org.apache.thrift7.TException;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

import com.storm.def.FunctionDefine;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.LogonParam;

public class ZhihuLoginTest {

	public static void test(String url) throws TException, DRPCExecutionException {
		// TODO Auto-generated method stub
		DRPCClient client = null;  
       // client = new DRPCClient("10.168.250.21", 3772);  
		 client = new DRPCClient("54.223.185.78", 3772);  
        System.out.println("--------DRCP Start---------");   
        
        FunctionCallParam fcm = new FunctionCallParam();
        fcm.setFunction(FunctionDefine.COOKIE_HANDLE_GETPAGE); 
        LogonParam param = new LogonParam();
        param.setUrl(url); 
        fcm.setLogonParam(param); 
        
        String json = fcm.toJson(); 
        System.out.println(json);
          
		String html = client.execute("CrawlerEngineTopology", json);
		System.out.println("------------------------------------------------------------------------------------------");
	    System.out.println(html);  
	     
	}
	
	public static void main(String[] args) throws TException, DRPCExecutionException {
		String url = "http://www.zhihu.com/people/xiepanda/followees";
		test(url);   
	}
	 
	public static void main1(String[] args) throws TException, DRPCExecutionException {
		String[] list = {"http://www.zhihu.com/search?type=people&q=%E5%91%A8%E5%8D%9A%E8%B6%85",
	    		   "http://www.zhihu.com/search?type=people&q=%E4%B8%96%E7%BA%AA%E6%9C%AB%E8%8F%9C%E5%88%80%E5%B0%91%E5%A5%B3",
	    		   "http://www.zhihu.com/search?type=people&q=%E5%B0%8F%E5%B0%8F%E9%A5%AD",
	    		   "http://www.zhihu.com/question/29635673/answer/45093627"
	    };  
		   
		for(String url:list){
		 	System.out.println("=========================================");
			test(url);
       	  	System.out.println("----------------"+url+"-----------------");
           
        } 
	}
	
	
}

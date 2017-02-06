package test;

import backtype.storm.utils.DRPCClient;

import com.module.htmlunit.definition.UtilDefinition;
import com.storm.def.FunctionDefine;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.LogonParam;
import com.storm.domian.WebParam;
import com.storm.domian.WeiboLogonParam;

public class DrpcTest {
	
	public static void main(String[] args) throws Exception {
		String[] list = {"http://weibo.com/1662084003/follow",
	    		//   "http://weibo.com/1645101450/info"
				//    "http://s.weibo.com/user/36氪",
	    };  
		     
		for(String url:list){
		 	System.out.println("=========================================");
		 	main3(url);
    	  	System.out.println("----------------"+url+"-----------------");
        
		} 
	} 
	
	public static void main3(String  url) throws Exception {  
 
		DRPCClient client = null;  
        client = new DRPCClient("10.168.250.21", 3772);  
		// client = new DRPCClient("54.223.185.78", 3772); 
        System.out.println("--------DRCP Start---------");  
         
        FunctionCallParam fcm = new FunctionCallParam();
        fcm.setFunction(FunctionDefine.WEIBO_HANDLE_GETPAGE);    
        WeiboLogonParam weiboLogonParam = new WeiboLogonParam();
        weiboLogonParam.setUrl(url); // or http://weibo.com/yangmiblog  or http://weibo.com/1645101450/info
        weiboLogonParam.setJsEnable(false);
        fcm.setWeiboLogonParam(weiboLogonParam); 
        
        String json = fcm.toJson();  
        System.out.println(json);
          
		String html = client.execute("CrawlerEngineTopology", json);
		System.out.println("------------------------------------------------------------------------------------------");
	    System.out.println(html); 
    }   
	
	public static void main1(String[] args) throws Exception {  
		String url = "http://www.linkedin.com/profile/view?id=251749025&authType=name&authToken=sSHS&trk=prof-sb-browse_map-name";
		//String url = "http://www.zhihu.com/people/xiepanda/followees";
		// TODO Auto-generated method stub
				DRPCClient client = null;  
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
 

}

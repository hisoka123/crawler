package test;

import java.util.List;

import org.apache.thrift7.TException;

import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawler.weibo.htmlparser.WeiboUserFollowerListParser;
import com.crawler.weibo.htmlparser.WeiboUserFeedListParser;
import com.storm.def.FunctionDefine;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.RssParam;
import com.storm.domian.WeiboFriendShipApiParam;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

public class FollowerParser {
	
	public static void main(String[] args) throws TException, DRPCExecutionException {
		// TODO Auto-generated method stub
		DRPCClient client = null;  
        client = new DRPCClient("10.168.250.21", 3772);  
        System.out.println("--------DRCP Start---------");  
        
        FunctionCallParam fcm = new FunctionCallParam();
        fcm.setFunction(FunctionDefine.WEIBO_API_FRIENDSHIPS); 
        WeiboFriendShipApiParam param = new WeiboFriendShipApiParam();
        param.setAccessToken("2.00oTwkUF0gTxeW299bd4ce30PTpJEC");//userfetch / vcspark13520800817
        param.setSourceId("1642351362");//angelababy
        param.setTargetId("1763582395");//韩庚
        fcm.setWeiboFriendShipApiParam(param); 
        
        String json = fcm.toJson();
        System.out.println(json);
          
		  String html = client.execute("CrawlerEngineTopology", json);
		  System.out.println("------------------------------------------------------------------------------------------");
	      System.out.println(html); 
		
	}

}

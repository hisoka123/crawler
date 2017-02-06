package test;

import org.apache.thrift7.TException;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

import com.storm.def.FunctionDefine;
import com.storm.domian.FunctionCallParam;
import com.storm.domian.JsonParam;

public class ZhihuJsonTest {

	public static void main(String[] args) throws TException, DRPCExecutionException {
		DRPCClient client = null;  
        client = new DRPCClient("10.168.250.21", 3772);  
        System.out.println("--------DRCP Start---------");  
        String url = "http://www.zhihu.com/r/search?q=%E8%B0%A2%E7%86%8A%E7%8C%AB%E5%90%9B&type=people&offset=10";
        FunctionCallParam fcm = new FunctionCallParam();
        fcm.setFunction(FunctionDefine.JSON_FETCH); 
        JsonParam param = new JsonParam();
        param.setUrl(url);
        fcm.setJsonParam(param);  
        
        String json = fcm.toJson(); 
        System.out.println(json);
          
		String html = client.execute("CrawlerEngineTopology", json);
		System.out.println("------------------------------------------------------------------------------------------");
	    System.out.println(html); 
	}
}

package test;

import org.apache.thrift7.TException;

import weibo4j.model.StatusWapper;

import com.google.gson.Gson;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

public class DrpcHomeTimelineTest {

	public static void main(String[] args) throws TException,DRPCExecutionException {
		DRPCClient client = new DRPCClient("10.168.250.21", 3772);
		System.out.println("--------DRCP Start---------");

		/**
    	 * account:13520800817/12qwaszx
    	 * app:vcsparkSNSdata
    	 * App Key:3505613165 
    	 * App Secret:872304208be3d3041e4ce74f7b896023
    	 * */
		String access_token = "2.00oTwkUFpHMPpDe31b060d84HBVE1E";
		String html = client.execute("HomeTimelineTopology", access_token);
		System.out.println("---->" + html);
		Gson gson = new Gson(); 
		StatusWapper statuses = gson.fromJson(html, StatusWapper.class);  
		
		System.out.println(statuses.getStatuses().get(0).getText());
		
		System.out.println(statuses.getStatuses().get(0).getCreatedAt());
		
		
	}

}

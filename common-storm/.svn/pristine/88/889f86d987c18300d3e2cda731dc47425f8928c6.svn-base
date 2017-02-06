package ship;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawler.weibo.domain.json.UserTarget;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShipTest {
	
	static int i = 0;
	
	static int t = 0;
	
	static String kingId = "1826792401";
	static String token = "2.00oTwkUF0gTxeW299bd4ce30PTpJEC";//userfetch / vcspark13520800817
	//static String token = "2.00oTwkUFpHMPpDe31b060d84HBVE1E";//vcsparkSNSdata / vcspark13520800817
	//static String token = "2.00wl5bTCqVLMpC9720db5d75MF4yeD";//VCSparkData / 梅荻
	//static String token = "2.00wl5bTC09bAomc96cfd845fboKoLE";//vcspark / 梅荻
	//static String token = "2.00wl5bTCDU9d3Eecc802df951MQX5B";//SApool / 梅荻
	//static String token = "2.00wl5bTC01maH956342dd79dW5FYUD";//SA_OAuth2.0 / 梅荻
	//static String token = "2.00wl5bTC86rXjCc9c6c70f574Ssf5B";//SATest / 梅荻
	//static String token = "2.00wl5bTCv2hrXE8b2af5e703j6T6sB";//meidiApp / 梅荻
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		System.out.println("------------Start--------------");
		String[] list = {"http://54.223.194.5:8080/data/api/weibo/follower?url=http://weibo.com/"+kingId+"/follow?page=1",
		"http://54.223.194.5:8080/data/api/weibo/follower?url=http://weibo.com/"+kingId+"/follow?page=2", 
		"http://54.223.194.5:8080/data/api/weibo/follower?url=http://weibo.com/"+kingId+"/follow?page=3",
		"http://54.223.194.5:8080/data/api/weibo/follower?url=http://weibo.com/"+kingId+"/follow?page=4",
		"http://54.223.194.5:8080/data/api/weibo/follower?url=http://weibo.com/"+kingId+"/follow?page=5"
   		}; 
		
		for(String url:list){
			System.out.println("url"+url);
			List<String> uid = getUserId(url);
			for(String id:uid){
				getShip(id);
			} 
		}
		
		System.out.println("相互关注====="+i);
		System.out.println("总数====="+t);
	}
	
	public static void getShip(String uid) throws MalformedURLException, IOException{
		 
		
		String url = "https://api.weibo.com/2/friendships/show.json?source_id="+kingId+"&target_id="+uid+"&access_token="+token;
		System.out.println(url);
		Reader reader = new InputStreamReader(new URL(url).openStream());
		
		Gson gson = new GsonBuilder().create();
		
		UserTarget obj = gson.fromJson(reader, UserTarget.class);
		
		Boolean b1 = obj.getSource().getFollowing();
		
		Boolean b2 = obj.getSource().getFollowed_by();
		
		if(b1&&b2){
			i++;
			System.out.println(obj.getSource().getScreen_name()+"----kingId---"+kingId+"----uid---"+uid+"-----"+obj.getTarget().getScreen_name());
		}
		
		t++;
		
		
	}
	
	
	public static  List<String> getUserId(String url) throws MalformedURLException, IOException{
		List<String> userid = null;
	
		
		//String url = "http://54.223.194.5:8080/data/api/weibo/follower?url=http://weibo.com/1826792401/follow?page=1";
		
		Reader reader = new InputStreamReader(new URL(url).openStream());
		
		Gson gson = new GsonBuilder().create();
		
		UserFeedJson[] obj = gson.fromJson(reader, UserFeedJson[].class);

		if(obj!=null){
			userid = new ArrayList<String>();
			for(UserFeedJson user:obj){
				String id = user.getUid();
				userid.add(id);
				//System.out.println(user.getUid());
			}
		}
		
		return userid;
	}

}

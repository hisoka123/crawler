package test;

import java.io.IOException;
import java.util.List;

import org.apache.thrift7.TException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.domain.json.HtmlJson;
import com.crawler.weibo.domain.json.UserFeedJson; 
import com.crawler.weibo.htmlparser.WeiboUserFeedListParser; 

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

public class HtmlParser {

	public static void main(String[] args) throws TException,DRPCExecutionException, IOException {
		String url = "http://s.weibo.com/user/36%25E6%25B0%25AA";
		//DRPCClient client = new DRPCClient("10.168.250.21", 3772);
		//String html = client.execute("WebEngineTopology", url);
		//System.out.println(html);
		Response rep = Jsoup.connect(url).timeout(10000).execute(); 
		Elements eles = Jsoup.parseBodyFragment(rep.body()).select("script");
		
		System.out.println(eles.size());
		 
		//Elements eles = Jsoup.connect(url).execute()parse(html).select("script");
		
		for(Element e:eles){
			String text = e.html(); 
			if(text.indexOf("\"pid\":\"pl_user_feedList\"")!=-1){
				System.out.println(text); 
				WeiboUserFeedListParser weiboUserFeedList = new WeiboUserFeedListParser();
				text = weiboUserFeedList.STKParser(text);
				System.out.println(text);
				HtmlJson userFeedList = weiboUserFeedList.parser(text);
				System.out.println(userFeedList.getHtml()); 
				List<UserFeedJson> userfeeds = weiboUserFeedList.userScriptParser(userFeedList.getHtml());
				System.out.println("userfeeds.size--------"+userfeeds.size());
				for(UserFeedJson uf:userfeeds){
					System.out.println(uf.getUid());
					System.out.println(uf.getNickname());
					System.out.println(uf.getFollow());
					System.out.println(uf.getFans());
					System.out.println(uf.getStatuses());
					System.out.println(uf.getFollow_url());
					System.out.println(uf.getFans_url());
					System.out.println(uf.getStatuses_url());
					System.out.println(uf.getProfile()); 
					
					System.out.println("======================"); 
				}
				
			} 
		}
		  
	}
	
	 
	
	 
}

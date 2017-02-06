package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;
import com.storm.def.StormTopologyConfig;

public class Test {
	
	public static void main(String[] args) throws Exception {
		String url = "http://www.zhihu.com/search?q=%E8%B0%A2%E7%86%8A%E7%8C%AB%E5%90%9B&type=people&offset=10";
		WebPageResponse re = WebCrawler.getInstance().getPage(url);
		System.out.println(re.getHtml());
	}

	public static void main1(String[] args) throws Exception {
		 Gson gson = new Gson();
		 String urlString = "http://www.zhihu.com/r/search?q=%E8%B0%A2%E7%86%8A%E7%8C%AB%E5%90%9B&type=people&offset=10";
		 JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL(urlString))).nextValue();
		 // String json = readUrl(urlString);
		 System.out.println(jo.getString("htmls")); 
		 
	}
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}

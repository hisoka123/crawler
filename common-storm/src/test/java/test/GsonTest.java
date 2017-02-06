package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;

import com.google.gson.Gson;

public class GsonTest {
	
	public static void main1(String[] args) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		String url = "http://localhost:8080/data/api/news/feed";
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();  
        formParams.add(new BasicNameValuePair("q", "小米note"));  
        //formParams.add(new BasicNameValuePair("password", "123456"));  
        UrlEncodedFormEntity uefEntity;  
         
        uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");  
        httpPost.setEntity(uefEntity);  
        System.out.println("executing request " + httpPost.getURI());  
        CloseableHttpResponse response = httpclient.execute(httpPost);  
        
		HttpEntity entity = response.getEntity();
		InputStream stream = entity.getContent();
		int b;
		while ((b = stream.read()) != -1) {
			stringBuilder.append((char) b);
		}
		
		System.out.println(stringBuilder.toString());

	}

	public static void main2(String[] args) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		String url = "http://localhost:8080/data/api/news/feed?q=小米note";
		HttpPost httpGet = new HttpPost(url);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream stream = entity.getContent();
		int b;
		while ((b = stream.read()) != -1) {
			stringBuilder.append((char) b);
		}
		
		System.out.println(stringBuilder.toString());

	}
	
	public static void main3(String[] args) throws MalformedURLException, IOException {
		String urlString = "http://www.zhihu.com/r/search?q=%E8%B0%A2%E7%86%8A%E7%8C%AB%E5%90%9B&type=people&offset=10";
		String json = IOUtils.toString(new URL(urlString)); 
		System.out.println(json);
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		String urlString = "http://www.zhihu.com/r/search?q=%E8%B0%A2%E7%86%8A%E7%8C%AB%E5%90%9B&type=people&offset=10";
		String json = null; 
		json = readJSONFeed(urlString);
		
		System.out.println(json);
	}
	
	
	public static String readJSONFeed(String URL) {
	    StringBuilder stringBuilder = new StringBuilder();
	    HttpClient httpClient = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet(URL);

	    try {

	        HttpResponse response = httpClient.execute(httpGet);
	        StatusLine statusLine = response.getStatusLine();
	        int statusCode = statusLine.getStatusCode();

	        if (statusCode == 200) {

	            HttpEntity entity = response.getEntity();
	            InputStream inputStream = entity.getContent();
	            BufferedReader reader = new BufferedReader(
	                    new InputStreamReader(inputStream));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                stringBuilder.append(line);
	            }

	            inputStream.close();

	        } else {
	        	System.out.println("Failed to download file");
	        }
	    } catch (Exception e) {
	    	System.out.println( e.getLocalizedMessage());
	    }
	    return stringBuilder.toString();
	}
	

}

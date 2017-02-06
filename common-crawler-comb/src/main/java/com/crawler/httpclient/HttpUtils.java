/**
 * 
 */
package com.crawler.httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static CloseableHttpClient getHttpclient() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		return httpclient;
	}

	public static String post(HttpClient httpclient, String url, Map<String, String> paramMap) {
		HttpPost httppost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Set<String> keyset = paramMap.keySet();
		for (String key : keyset) {
			params.add(new BasicNameValuePair(key, paramMap.get(key)));
		}

		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {

		} finally {
			httppost.releaseConnection();
		}
		return null;
	}

	public static String get(HttpClient httpclient, String url) {
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {

		} finally {
			httpget.releaseConnection();
		}
		return null;
	}

	public static byte[] get(HttpClient httpclient, String url, String cookies) {
		HttpGet httpget = new HttpGet(url);
		try {
			if (cookies != null)
				httpget.setHeader("Cookie", cookies);
			HttpResponse response = httpclient.execute(httpget);
			return EntityUtils.toByteArray(response.getEntity());
		} catch (Exception e) {

		} finally {
			httpget.releaseConnection();
		}
		return null;
	}

	/**
	 * 测试
	 */
	public static void main(String[] args) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = "http://10.168.250.21:8080/api/v1/cluster/summary";
		String aaa = get(httpclient, url);
		System.out.println(aaa);
	}

}

package gsxt;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;


public class WebTest {

	public static void main(String[] args) throws Exception {
		String url = "http://54.223.194.5:8080/data/api/gsxt/beijing/getData?"
				+ "serializedFileName=9352eb44-48e9-4f1e-9b70-85fed89942cb"
				+ "&verifycode=18"
				+ "&keyword=北京京澳毛纺有限公司";
				//+ "&isDebug=true";

//		HttpClient client=new DefaultHttpClient();
//		
//		HttpGet httpGet=new HttpGet(url);
//
//		HttpResponse response = client.execute(httpGet);
//
//		HttpEntity entity = response.getEntity();
//		
//		String xmlContent = EntityUtils.toString(entity);
//		
//		System.out.println(xmlContent);
		
		
		URL urlObj = new URL(url);
		InputStream is = urlObj.openStream();
		System.out.println(IOUtils.toString(is, "utf-8"));
	}
}

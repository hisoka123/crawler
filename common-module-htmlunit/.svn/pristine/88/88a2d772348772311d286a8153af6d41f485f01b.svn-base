package test.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultPageCreator;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.PageCreator;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;

public class GSzhejiang {

	private static WebClient webClient = getWebClient();

	private static Gson gson = new Gson();

	private static String zhejiangurl = "http://gsxt.zjaic.gov.cn/search/doGetAppSearchResult.do";

	private static String file = "AllIn.out";
	
	private static String company = "杭州钢铁集团公司";
	
	public static void main(String[] args) throws Exception{
		double i =  (Math.random()*100000);  
		URL gsUrl  = new URL(zhejiangurl); 
		WebRequest  requestSettings = new WebRequest(gsUrl, HttpMethod.GET); 
		HtmlPage page = webClient.getPage(requestSettings); 
		WebResponse wr = page.getWebResponse();
		System.out.println(wr.getContentAsString());
		HtmlImage image = page.getFirstByXPath("//img[@id='kaptchaImg']");  
		File imageFile = new File("D:\\eclipse\\zhejiang"+i+".jpg");
		image.saveAs(imageFile); 
 
        System.out.println("-----------------------post("+company+")----------------------"); 
 
		
		HtmlHiddenInput inputtoken = (HtmlHiddenInput)page.querySelector("input[name=token]"); 
		String token = inputtoken.getValueAttribute();
		System.out.println("token----------"+token);
		
		
		System.out.print("verfiy_Code.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		
		zhejiangurl = zhejiangurl+"?name="+URLEncoder.encode(company)+"&verifyCode="+URLEncoder.encode(code)
				+"&struts.token.name=token&token="+token;
	 
		System.out.println("zhejiangurl--->"+zhejiangurl);
		URL url  = new URL(zhejiangurl);
		
		/*WebRequest webRequest = new WebRequest(new URL(url"?keyword="+URLEncoder.encode(keyword)+"&checkcode="+URLEncoder.encode(imagecode)
				+"&credit_ticket="+credit_ticket+"&currentTimeMillis="+currentTimeMillis), HttpMethod.POST);
		webRequest.setCharset("utf-8");*/

		WebRequest  zjrequestSettings = new WebRequest(url, HttpMethod.POST);
		zjrequestSettings.setCharset("utf-8");
		/*
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("name", company));
		requestSettings.getRequestParameters().add(new NameValuePair("verifyCode", code)); 
		requestSettings.getRequestParameters().add(new NameValuePair("struts.token.name", "token")); 
		requestSettings.getRequestParameters().add(new NameValuePair("token", token)); 
	 
		requestSettings.setAdditionalHeader("Host", "gsxt.zjaic.gov.cn");
		requestSettings.setAdditionalHeader("Origin", "http://gsxt.zjaic.gov.cn");
		requestSettings.setAdditionalHeader("Referer", "http://gsxt.zjaic.gov.cn/search/doGetAppSearchResult.do");*/
		
		HtmlPage page3 = webClient.getPage(zjrequestSettings); 
		
		
		System.out.println(page3.asXml());
		
	}
	
	
	
	
	
	

	public static WebClient getWebClient() {
		if (webClient == null) {
			webClient = new WebClient(BrowserVersion.CHROME);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(30000);
			webClient
					.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getJavaScriptEngine().getContextFactory().enterContext()
					.setOptimizationLevel(9);
			webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {

				@Override
				public void scriptException(HtmlPage htmlPage,
						ScriptException scriptException) {
					// TODO Auto-generated method stub
					System.out.println(htmlPage.getUrl().toString()
							+ "---scriptException----"
							+ scriptException.getMessage());

				}

				@Override
				public void timeoutError(HtmlPage htmlPage, long allowedTime,
						long executionTime) {
					System.out.println(htmlPage.getUrl().toString()
							+ "---timeoutError----" + allowedTime
							+ "-----executionTime----" + executionTime);
				}

				@Override
				public void malformedScriptURL(HtmlPage htmlPage, String url,
						MalformedURLException malformedURLException) {
					System.out.println(htmlPage.getUrl().toString()
							+ "---malformedScriptURL----" + url
							+ "-----executionTime----"
							+ malformedURLException.getMessage());
				}

				@Override
				public void loadScriptError(HtmlPage htmlPage, URL scriptUrl,
						Exception exception) {
					System.out.println(htmlPage.getUrl().toString()
							+ "---loadScriptError----" + scriptUrl.toString()
							+ "-----executionTime----" + exception.getMessage());
				}

			});
			webClient.setIncorrectnessListener(new IncorrectnessListener() {
				@Override
				public void notify(String arg0, Object arg1) {
					System.out.println(arg0 + "-----IncorrectnessListener-----"
							+ arg1);
				}
			});
		}
		return webClient;
	}

}

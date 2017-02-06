package test.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultPageCreator;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.PageCreator;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;

public class HtmlUnitGongShang2 {

	private static WebClient webClient = getWebClient();

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException,IOException {

		URL url  = new URL("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!toIndex.dhtml");

		WebRequest  requestSettings = new WebRequest(url, HttpMethod.GET);
	 
		HtmlPage page = webClient.getPage(requestSettings);
		
		WebResponse wr = page.getWebResponse();
		
		System.out.println(wr.getContentAsString()); 
		Gson gson = new Gson();
		System.out.println(gson.toJson(wr)); 
		System.out.println("---------------"); 
		System.out.println(gson.toJson(requestSettings)); 
	 
		
		HtmlImage image = page.getFirstByXPath("//img[@id='MzImgExpPwd']");
		File imageFile = new File("D:\\eclipse\\MzImgExpPwd.jpg");
		image.saveAs(imageFile);
		
		HtmlHiddenInput inputcurrentTimeMillis = (HtmlHiddenInput)page.querySelector("#currentTimeMillis"); 
		String currentTimeMillis = inputcurrentTimeMillis.getValueAttribute();
		System.out.println("currentTimeMillis----------"+currentTimeMillis);
		
		HtmlHiddenInput inputcredit_ticket = (HtmlHiddenInput)page.querySelector("#credit_ticket"); 
		String credit_ticket = inputcredit_ticket.getValueAttribute();
		System.out.println("credit_ticket----------"+credit_ticket);
		
		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
		for(Cookie coo:cookies){
			System.out.println(coo.getDomain()+"------"+coo.getName()+"-------"+coo.getValue());
		}
		 
		
		postRquest(cookies,currentTimeMillis,credit_ticket,page);
		  
	}
	
 
	
	 
	
	 
	public static void postRquest(Set<Cookie> cookies,String currentTimeMillis,String credit_ticket,HtmlPage page1) throws IOException{
		
		String keyword = "北京星流航太科技有限公司";//湖南上容信息技术有限公司
		//String keyword = java.net.URLEncoder.encode("湖南上容信息技术有限公司", "UTF-8"); 
		
		System.out.print("verfiy_Code.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		
		URL url  = new URL("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml");
		//URL url  = new URL("http://qyxy.baic.gov.cn/beijing");

		WebRequest  requestSettings = new WebRequest(url, HttpMethod.POST);
		
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("checkcode", code));
		requestSettings.getRequestParameters().add(new NameValuePair("keyword", keyword));
		requestSettings.getRequestParameters().add(new NameValuePair("currentTimeMillis", currentTimeMillis));
		requestSettings.getRequestParameters().add(new NameValuePair("credit_ticket", credit_ticket));
		 
		
		webClient = getWebClient();
		webClient.getCookieManager().clearCookies(); 
		for(Cookie co:cookies){
			webClient.getCookieManager().addCookie(co);
		}
		  
		HtmlPage page = webClient.getPage(requestSettings); 
		String html = page.getWebResponse().getContentAsString();  
		System.out.println("----------------------------------------------------------------------------"); 
		System.err.println(html); 
		System.out.println("----------------------------------------------------------------------------");
		
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
			webClient.getOptions().setTimeout(10000);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			//webClient.getJavaScriptEngine().getContextFactory().enterContext().setOptimizationLevel(9);

		}
		return webClient;
	}

}

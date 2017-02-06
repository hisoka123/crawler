package test.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class HtmlUnitGongShang {

	private static WebClient webClient = getWebClient();

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException,IOException {

		URL url  = new URL("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!toIndex.dhtml");

		WebRequest  requestSettings = new WebRequest(url, HttpMethod.GET);
	 
		HtmlPage page = webClient.getPage(requestSettings);
		WebResponse wr = page.getWebResponse();
		System.out.println(wr.getContentAsString());
		
		HtmlImage image = page.getFirstByXPath("//img[@id='MzImgExpPwd']");
		File imageFile = new File("D:\\eclipse\\MzImgExpPwd.jpg");
		image.saveAs(imageFile);
		
		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
		for(Cookie coo:cookies){
			System.out.println(coo.getDomain()+"------"+coo.getName()+"-------"+coo.getValue());
		}
		
		//postRquest(cookies);
		
		//String keyword = "IBM";
		String keyword = "湖南上容信息技术有限公司";
		HtmlTextInput inputkeyword = (HtmlTextInput)page.querySelector("#keyword");
		inputkeyword.setText(keyword);
		
		System.out.print("verfiy_Code.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		
		HtmlTextInput inputUserName = (HtmlTextInput)page.querySelector("#checkcodeAlt");
		inputUserName.setText(code);
		
		
		HtmlElement loginButton = (HtmlElement)page.querySelector("a[onclick*=fn_search]");
		
		System.out.println("-----------------------11111----------------------");
		
		System.out.println(loginButton.asXml());
		
		HtmlPage loggedPage = loginButton.click();
		
		System.out.println("-----------------------22222----------------------");
		
		System.err.println(loggedPage.asXml());
		
		System.out.println("-----------------------33333----------------------");
		
		System.err.println(loggedPage.getUrl().toString());
		 
	}
	
	 
	
	 
	public static void postRquest(Set<Cookie> cookies) throws IOException{
		
		String keyword = "湖南上容信息技术有限公司";
		
		System.out.print("verfiy_Code.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		
		URL url  = new URL("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml");

		WebRequest  requestSettings = new WebRequest(url, HttpMethod.POST);
		
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("checkcode", code));
		requestSettings.getRequestParameters().add(new NameValuePair("keyword", keyword));
		requestSettings.getRequestParameters().add(new NameValuePair("currentTimeMillis", "1456380980815"));
		
		requestSettings.setAdditionalHeader("Host", "qyxy.baic.gov.cn");
		requestSettings.setAdditionalHeader("Origin", "http://qyxy.baic.gov.cn");
		requestSettings.setAdditionalHeader("Referer", "http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml");
		
		webClient = getWebClient();
		webClient.getCookieManager().clearCookies(); 
		for(Cookie co:cookies){
			webClient.getCookieManager().addCookie(co);
		}
		
		HtmlPage page = webClient.getPage(requestSettings); 
		String html = page.getWebResponse().getContentAsString();  
		System.out.println("----------------------------------------------------------------------------"); 
		System.out.println(html); 
		System.out.println("----------------------------------------------------------------------------");
		
	} 

	public static WebClient getWebClient() {
		if (webClient == null) {
			//String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";		 
			//BrowserVersion bv = new BrowserVersion("Netscape", userAgent, userAgent, 49);
			//webClient = new WebClient(bv);
			webClient = new WebClient(BrowserVersion.CHROME);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(10000);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			ProxyConfig proxyConfig = new ProxyConfig("116.255.162.135", 16816);
			webClient.getOptions().setProxyConfig(proxyConfig);

		}
		return webClient;
	}

}

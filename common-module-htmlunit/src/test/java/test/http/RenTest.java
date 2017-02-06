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
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class RenTest {
	
	private static WebClient webClient = null;

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url = "https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp";
		if(webClient==null){
			webClient = getWebClient();
			webClient.getOptions().setUseInsecureSSL(true);
		}  
		HtmlPage htmlPage = null;
		htmlPage = webClient.getPage(url);
		
		String result = htmlPage.getWebResponse().getContentAsString(); 

		Set<Cookie> coos1 = webClient.getCookieManager().getCookies();
		
		System.out.println("============================================");
		
		for(Cookie coo:coos1){
			System.out.println(coo.toString());
		}
		
		System.out.println("============================================");
		
		System.out.println(result);
		
		HtmlForm form = htmlPage.getFormByName("loginForm");
		HtmlInput nameInput = form.getInputByName("loginname");
		HtmlInput passInput = form.getInputByName("password");
		HtmlInput codeImageInput = form.getInputByName("_@IMGRC@_");
		HtmlInput submitBtn = form.getFirstByXPath("//*[@id='main']/div[1]/form/div[5]/div[2]/input");
		nameInput.setValueAttribute("md19841002");
		passInput.setValueAttribute("md87315450");
		
		HtmlImage image = htmlPage.getFirstByXPath("//img[@id='imgrc']");
		File imageFile = new File("D:\\eclipse\\imgrc.jpg");
		image.saveAs(imageFile);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		
		codeImageInput.setValueAttribute(code);
		
		System.out.println("---------------------------------------------");
		
		HtmlPage loggedPage = submitBtn.click(); //点击登录按钮
		
		System.out.println("---------------------------------------------");
		
		System.err.println(loggedPage.asXml());
		
		System.out.println("---------------------------------------------");
		
		
		Set<Cookie> coos = webClient.getCookieManager().getCookies();
		
		for(Cookie coo:coos){
			System.out.println(coo.toString());
		}
		
		System.out.println("----------------------------------------------------------------------------");
		WebRequest request = new WebRequest(new URL("https://ipcrs.pbccrc.org.cn/simpleReport.do?method=viewReport"), HttpMethod.POST);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("reportformat", "21"));
		nameValuePairs.add(new NameValuePair("tradeCode", "66ba88")); //身份验证码
		request.setRequestParameters(nameValuePairs);
		HtmlPage reportPage = webClient.getPage(request);
		
		System.out.println(reportPage.asXml());
		
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
			
		}
		return webClient;
	}


}

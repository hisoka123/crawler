package test.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.google.gson.Gson;

public class Shandong {
	
	private static WebClient webClient = getWebClient();

	private static Gson gson = new Gson();
	
	private static String guangdong = "http://218.57.139.24/";
	
	private static String company = "海尔集团公司";
	
	public static void main(String[] args) throws Exception {
		double i =  (Math.random()*100000);    
		System.out.println("================"+i);
		URL gsUrl  = new URL(guangdong); 
		WebRequest  requestSettings = new WebRequest(gsUrl, HttpMethod.GET); 
		HtmlPage page = webClient.getPage(requestSettings);    
		WebResponse wr = page.getWebResponse();
		System.out.println(wr.getContentAsString());
		 
		HtmlTextInput inputtoken = (HtmlTextInput)page.querySelector("#searchtxt"); 
		inputtoken.setText(company);
		System.out.println("inputtoken----------"+inputtoken.asXml());
		
		HtmlElement zdmButton = (HtmlElement)page.querySelector("a[onclick='zdm()']");
		System.out.println("zdmButton----------"+zdmButton.asXml());
		zdmButton.click();
		
		HtmlImage image2 = page.getFirstByXPath("//img[@id='secimg']");  
		String src2 = image2.getAttribute("src");
		System.out.println("src2----------"+src2);
		
		//searchPage.executeJavaScript("$('#secode').val(toMD5Str("+imagecode+"));");

		//image.click();
		File imageFile = new File("D:\\eclipse\\shandong"+i+".jpg"); 
		image2.saveAs(imageFile); 
	    System.out.println("-----------------------post("+company+")----------------------"); 
	    System.out.print("verfiy_Code.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		  
		HtmlTextInput inputVerifyCode = (HtmlTextInput)page.querySelector("#yzminput"); 
		inputVerifyCode.setText(code);
		System.out.println("inputVerifyCode----------"+inputVerifyCode.asXml());
	    
		HtmlElement loginButton = (HtmlElement)page.querySelector("#searchbtn");
		
		System.out.println("-----------------------11111----------------------");
		
		System.out.println(loginButton.asXml());
		
		HtmlPage loggedPage = loginButton.click();
		
		System.out.println("-----------------------22222----------------------");
		
		loggedPage.executeJavaScript("loadres();");

		System.err.println(loggedPage.asXml());
		
		System.out.println("-----------------------333333----------------------");
		Thread.sleep(2000L);
		System.err.println(loggedPage.asXml());
		
		System.out.println("-----------------------4444444----------------------");
		Thread.sleep(2000L);
		System.err.println(loggedPage.asXml());
		
		System.out.println("-----------------------555555555----------------------");
		
		
		/* 
		System.out.println("-----------------------33333----------------------"+loggedPage.getUrl().toString());
		
		System.out.print("enckeyword.[Enter]:");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		String param = br2.readLine(); 
		System.out.println("param: " + param);
	 
		
		String url = "http://218.57.139.24/pub/search?param=" + param;
		WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
		webRequest.setCharset("utf-8");
		webRequest.setAdditionalHeader("Host", "218.57.139.24");
		webRequest.setAdditionalHeader("Origin", "http://218.57.139.24");
		webRequest.setAdditionalHeader("Referer", "http://218.57.139.24/pub/indsearch"); 
		//webRequest.setAdditionalHeader("X-CSRF-TOKEN", "_csrf");
		HtmlPage firstInfoPage = webClient.getPage(webRequest);
		
		System.err.println(loggedPage.asXml()); */
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
			webClient .setAjaxController(new NicelyResynchronizingAjaxController());
			//webClient.getOptions().setProxyConfig(new ProxyConfig("54.222.219.159",808));
			/*webClient.setAjaxController(new AjaxController(){
		        @Override
		        public boolean processSynchron(HtmlPage page, WebRequest request, boolean async)
		        {
		            return true;
		        }
		    });*/
			webClient.getJavaScriptEngine().getContextFactory().enterContext()
					.setOptimizationLevel(9);
			//webClient.waitForBackgroundJavaScriptStartingBefore(10000);
			//webClient.waitForBackgroundJavaScript(10000);
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
		/*	
			webClient.setAjaxController(new AjaxController(){
			    @Override
			    public boolean processSynchron(HtmlPage page, WebRequest request, boolean async)
			    {
			        return true;
			    }
			});*/
			
		}
		return webClient;
	}
}

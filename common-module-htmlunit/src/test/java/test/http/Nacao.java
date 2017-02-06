package test.http;

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
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.google.gson.Gson;

public class Nacao {
	
	private static WebClient webClient = getWebClient();
	
	private static Gson gson = new Gson();
	
	private static String url = "https://s.nacao.org.cn/specialResult.html?x=nPVoZ0kAOuZhYnkbVOaaU/efsG8=&k=gTXO4XEtZOcnaJO2vkr8Pzc=&s=Yqi8nbOpMKWcrwK8sht1ItDaj/QfpKNp0MkASHl/3eot+99Qs+w6kpLzKOznVwuyDYwlGQ==&y=XfR8u2vLSE8O6yZXgwIxazpn6wx5lZcUJD6Flw==";
	
	private static String imageurl = "https://s.nacao.org.cn/servlet/ValidateCode?time=0.33329392249063194";
	private static String company = "松迪科技";
	
	
	public static void main(String[] args) throws Exception{
		URL gsUrl  = new URL(url); 
		WebRequest  requestSettings = new WebRequest(gsUrl, HttpMethod.GET); 
		HtmlPage page = webClient.getPage(requestSettings); 
		WebResponse wr = page.getWebResponse();
		System.out.println(wr.getContentAsString());
		/*
		System.out.println("---------------1111---------------");
		HtmlTextInput inputtoken = (HtmlTextInput)page.querySelector("#tit2");  
		inputtoken.setText(company); 
		System.out.println(inputtoken.asXml());
		
		System.out.println("---------------22222---------------");
		HtmlElement loginButton = (HtmlElement)page.querySelector("input[onclick='submitForm(2)']");
		System.out.println(loginButton.asXml());
		HtmlPage loggedPage = loginButton.click();
		System.out.println("---------------3333333---------------");
		System.out.println(loggedPage.asXml());
		*/
		
		
		
		
		
		
		
		
		
		
		
		
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
			webClient.getOptions().setTimeout(90000);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController()); 
			webClient.getJavaScriptEngine().getContextFactory().enterContext().setOptimizationLevel(9);
			webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {

				@Override
				public void scriptException(HtmlPage htmlPage,
						ScriptException scriptException) {
					// TODO Auto-generated method stub
					System.out.println(htmlPage.getUrl().toString()+"---scriptException----"+scriptException.getMessage());
					
				}

				@Override
				public void timeoutError(HtmlPage htmlPage, long allowedTime,
						long executionTime) { 
					System.out.println(htmlPage.getUrl().toString()+"---timeoutError----"+allowedTime+"-----executionTime----"+executionTime);
				}

				@Override
				public void malformedScriptURL(HtmlPage htmlPage, String url,
						MalformedURLException malformedURLException) {
					System.out.println(htmlPage.getUrl().toString()+"---malformedScriptURL----"+url+"-----executionTime----"+malformedURLException.getMessage());
				}

				@Override
				public void loadScriptError(HtmlPage htmlPage, URL scriptUrl, Exception exception) {
					System.out.println(htmlPage.getUrl().toString()+"---loadScriptError----"+scriptUrl.toString()+"-----executionTime----"+exception.getMessage());
				}
				
			}); 
			webClient.setIncorrectnessListener(new IncorrectnessListener() { 
			        @Override
			        public void notify(String arg0, Object arg1) {
			            System.out.println(arg0+"-----IncorrectnessListener-----"+arg1);
			        }
			}); 
		}
		return webClient;
	}

}

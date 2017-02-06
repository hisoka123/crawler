package test.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class FaYuanTest {
	
	private static WebClient webClient = getWebClient();

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException,IOException {
		
		URL url  = new URL("http://zhixing.court.gov.cn/search/");

		WebRequest  requestSettings = new WebRequest(url, HttpMethod.GET); 
		//pname  captchaImg
		HtmlPage page = webClient.getPage(requestSettings);
		WebResponse wr = page.getWebResponse();
		System.out.println(wr.getContentAsString());
		
		HtmlImage image = page.getFirstByXPath("//img[@id='captchaImg']");
		File imageFile = new File("D:\\eclipse\\captchaImg.jpg");
		image.saveAs(imageFile);
		
		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
		for(Cookie coo:cookies){
			System.out.println(coo.getDomain()+"------"+coo.getName()+"-------"+coo.getValue());
		}
		
		//postRquest(cookies);
		
		//String keyword = "IBM";
		String keyword = "湖南上容信息技术有限公司";
		HtmlTextInput inputkeyword = (HtmlTextInput)page.querySelector("#pname");
		inputkeyword.setText(keyword);
		
		System.out.print("verfiy_Code.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine(); 
		System.out.println("code: " + code);
		
		HtmlTextInput inputUserName = (HtmlTextInput)page.querySelector("#j_captcha");
		inputUserName.setText(code);
		
		HtmlElement loginButton = (HtmlElement)page.querySelector("#button");
		
		System.out.println("-----------------------11111----------------------");
		
		System.out.println(loginButton.asXml());
		
		HtmlPage loggedPage = loginButton.click();
		
		System.out.println("-----------------------22222----------------------");
		
		System.err.println(loggedPage.asXml());
		
		System.out.println("-----------------------33333----------------------");
		
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

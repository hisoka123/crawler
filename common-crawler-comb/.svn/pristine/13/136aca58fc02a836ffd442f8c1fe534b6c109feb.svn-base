/**
 * 
 */
package com.crawler.linkedin.login;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawler.linkedin.util.LoginUsersHelper;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;
import com.module.htmlunit.definition.UtilDefinition;

/**
 * @author kingly
 * @date 2015年9月9日
 * 
 */
public class LinkedinLogin {
	private static final String loginPage = "https://www.linkedin.com/uas/login";
	private static LinkedinLoginUser loginUser = new LinkedinLoginUser();
	private static String selector_username = "#session_key-login";
	private static String selector_password = "#session_password-login";
	private static String selector_loginbutton = "#btn-primary";
	private static WebClient wc;
	private static LinkedinLogin instance;
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkedinLogin.class);
	
	public static LinkedinLoginUser getLoginUser() {
		return loginUser;
	}
	public static void setLoginUser(LinkedinLoginUser loginUser) {
		LinkedinLogin.loginUser = loginUser;
	}
	public static void nextUser() {
		LinkedinLogin.loginUser = LoginUsersHelper.nextUser();
	}

	public static LinkedinLogin getInstance() {
		if (instance == null) {
			synchronized (LinkedinLogin.class) {
				if (instance == null) {
					instance = new LinkedinLogin();
				}
			}
		}
		return instance;
	}
	
	public LinkedinLogin() {
		wc = WebCrawler.getInstance().getWebClient(); 
	}
	
	public WebClient getWebClient(boolean jsEnable) {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.setRefreshHandler(new ThreadedRefreshHandler());
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(jsEnable);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setTimeout(6000);   
		webClient.waitForBackgroundJavaScript(6000);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());  
		return webClient;
	}
	
	/**
	 * 判断首页是否包含nickname
	 * 有：表示登录成功
	 * 没有：表示登录失败
	 * */
	public boolean isLogin(WebPageResponse wr){
		LOGGER.info("=============  isLogin  判断是否登录成功    =============");
		boolean bool = false;  
		String html = wr.getHtml();
		LOGGER.info(html);
		if(html.indexOf(loginUser.getNickname())==-1){
			bool = false;
		}
		return bool;
	}
	
	public synchronized WebClient userLogin(WebClient webClient) throws Exception {
		LOGGER.info("=============  用户登录   ============="+loginPage); 
		
		webClient.getOptions().setJavaScriptEnabled(true);
		HtmlPage htmlpage = webClient.getPage(loginPage); 
		
		//Select UserName TextArea
		HtmlTextInput inputUserName = (HtmlTextInput)htmlpage.querySelector(selector_username);
		if(inputUserName==null){
			LOGGER.info(htmlpage.asXml());
			throw new Exception("username input text can not found :"+selector_username); 
		}else{
			//Set UserName Value
			LOGGER.info(inputUserName.asXml()+"      "+loginUser.getUsername()); 
			inputUserName.reset();
			inputUserName.setText(loginUser.getUsername());
		}
		
		//Select Password testArea
		HtmlPasswordInput inputPassword = (HtmlPasswordInput)htmlpage.querySelector(selector_password);
		if(inputPassword==null){
			throw new Exception("password input text can not found :"+selector_password);
		}else{
			//Set Password Value
			LOGGER.info(inputPassword.asXml()+"      "+loginUser.getPassword());
			inputPassword.reset();
			inputPassword.setText(loginUser.getPassword());
		} 
		
		//Select Submit Button 
		HtmlElement loginButton = (HtmlElement)htmlpage.querySelector(selector_loginbutton);
		
		if(loginButton==null){
			throw new Exception("login button can not found :"+selector_loginbutton);
		}else{
			//Click Submit Button 
			LOGGER.info(loginButton.asXml()+"      "+selector_loginbutton); 
			loginButton.click();
		} 
		return webClient;
	}
	
	public synchronized WebPageResponse getPageInlogin(String url,boolean jsEnable) throws Exception {
		LOGGER.info("getPageInlogin: {}", url);
		WebPageResponse wr = getPage(wc,url,jsEnable);
		boolean islogin = LinkedinLogin.getInstance().isLogin(wr);
		if(islogin){
			LOGGER.info("=============  已经登录    =============");
			return wr;
		}else{
			LOGGER.info("=============  没有登录    =============");  
			wc = userLogin(wc);
			LOGGER.info("=============  登录了   ============="); 
			WebPageResponse response = getPage(wc,url,jsEnable);
			wc = getWebClient(jsEnable);
			return response;
		} 
	}
	
	public synchronized Page getPageInlogin(WebRequest request) throws Exception {
		URL url = request.getUrl();
		LOGGER.info("getPageInlogin: {}", url.toString());
		Page p = wc.getPage(request); 
		return p;
	}
	
	public synchronized WebPageResponse getPage(WebClient webClient, URL url, boolean jsEnable) throws Exception {
		LOGGER.info("Htmlunit Send Request: {} jsEnable:{}", url, jsEnable); 
		WebPageResponse wr = new WebPageResponse();
		
		HtmlPage page = null;
		String result = null;
		WebResponse response = null;
		
		try { 
			webClient.getOptions().setJavaScriptEnabled(jsEnable);
			page = webClient.getPage(url); 
			response = page.getWebResponse();
			result = response.getContentAsString(); 
			int httpStatusCode = response.getStatusCode();
			wr.setHtml(result); 
			wr.setHttpStatusCode(httpStatusCode);
			wr.setUrl(page.getUrl()+"");
			wr.setUnit(UtilDefinition.HTMLUNIT);
			wr.setCookies(webClient.getCookieManager().getCookies());  //////////////////////////////////////////////
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(wc!=null){
				//webClient.getCache().clear();
				//webClient.getCookieManager().clearCookies();
				wc.closeAllWindows();  
			}  
		}  
		return wr;
	}
	
	public synchronized WebPageResponse getPage(WebClient webClient, String url, boolean jsEnable) throws Exception { 
		URL link = new URL(url);
		return getPage(webClient,link,jsEnable);
	}
	
	public WebClient insertCookie(WebClient webClient,Collection<Cookie> cookies) {
		for(Cookie e :cookies){
			webClient.getCookieManager().addCookie(e);
		}
		return webClient;
	}
	
}

package com.crawler.weibo.login;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
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

public class WeiboLogin {
	
	private static final Logger log = LoggerFactory.getLogger(WeiboLogin.class);
	
	private static final String loginPage = "http://login.sina.com.cn/";
	
	private static final String checkPage = "http://weibo.com/";
	
	private static String username = "13520800817";
	
	private static String uid = "5035142128";
	
	private static String nickname = "vcspark13520800817";
	
	private static String password = "12qwaszx";
	
	private static String selector_username = "#username";
	
	private static String selector_password = "#password";
			 
	private static String selector_loginbutton=".smb_btn";
	
	private static WebClient wc ;
	
	private static WeiboLogin instance;
	
	public static WeiboLogin getInstance() {
		if (instance == null) {
			synchronized (WeiboLogin.class) {
				if (instance == null) {
					instance = new WeiboLogin();
				}
			}
		}
		return instance;
	}
	
	public WeiboLogin(){
		wc = WebCrawler.getInstance().getWebClient(); 
	}
	  

	/**
	 * 判断首页是否包含nickname
	 * 有：表示登录成功
	 * 没有：表示登录失败
	 * */
	public boolean isLogin(WebPageResponse wr){
		log.info("=============  isLogin  判断是否登录成功    =============");
		boolean bool = true;  
		String html = wr.getHtml();
		log.info(html);
		if(html.indexOf(nickname)==-1&&html.indexOf(uid)==-1){
			bool = false;
		} 
		return bool;
	}
	
	
	
	 
	public synchronized WebClient userLogin(WebClient webClient) throws Exception{
		
		log.info("=============  用户登录   ============="+loginPage); 
		
		webClient.getOptions().setJavaScriptEnabled(true);
		
		HtmlPage htmlpage = webClient.getPage(loginPage); 
		 
		//Select UserName TextArea
		HtmlTextInput InputUserName = (HtmlTextInput)htmlpage.querySelector(selector_username);
		
		if(InputUserName==null){
			log.info(htmlpage.asXml());
			throw new Exception("username input text can not found :"+selector_username); 
		}else{
			//Set UserName Value
			log.info(InputUserName.asXml()+"      "+username); 

			InputUserName.setText(username);
			
		}
		
		//Select Password testArea
		HtmlPasswordInput InputPassword = (HtmlPasswordInput)htmlpage.querySelector(selector_password);
		
		if(InputPassword==null){
			throw new Exception("password input text can not found :"+selector_password);
		}else{
			//Set Password Value
			log.info(InputPassword.asXml()+"      "+password);
			
			InputPassword.setText(password);
		} 
		
		//Select Submit Button 
		HtmlElement loginButton = (HtmlElement)htmlpage.querySelector(selector_loginbutton);
		
		if(loginButton==null){
			throw new Exception("login button can not found :"+selector_loginbutton);
		}else{
			//Click Submit Button 
			log.info(loginButton.asXml()+"      "+selector_loginbutton); 
			loginButton.click();
		} 
		
	
		return webClient;
		
	}
 
	
	public synchronized WebPageResponse getPageInlogin(String url,boolean jsEnable) throws Exception{
		log.info("getPageInlogin: {}", url);
		WebPageResponse wr = getPage(wc,url,jsEnable);
		boolean islogin = WeiboLogin.getInstance().isLogin(wr);
		if(islogin){
			log.info("=============  已经登录    =============");
			return wr;
		}else{
			log.info("=============  没有登录    =============");   
			wc = userLogin(wc);
			log.info("=============  登录了   ============="); 
			WebPageResponse response = getPage(wc,url,jsEnable);
			return response;
		} 
	}
	  
	public synchronized Page getPageInlogin(WebRequest request) throws Exception{
		URL url = request.getUrl();
		log.info("getPageInlogin: {}", url.toString());
		Page p = wc.getPage(request); 
		return p;
	}
	
	public synchronized WebPageResponse getPage(WebClient webClient,URL url,boolean jsEnable) throws Exception{
		log.info("Htmlunit Send Request: {} jsEnable:{}", url,jsEnable); 
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
			wr.setCookies(webClient.getCookieManager().getCookies()); 
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
	 
	public synchronized WebPageResponse getPage(WebClient webClient,String url,boolean jsEnable) throws Exception{ 
		URL link = new URL(url);
		return getPage(webClient,link,jsEnable);
	}
	
	public WebClient insertCookie(WebClient webClient,Collection<Cookie> cookies){
		for(Cookie e :cookies){
			webClient.getCookieManager().addCookie(e);
		}
		return webClient;
	}

}

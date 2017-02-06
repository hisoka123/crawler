package com.crawler.zhihu.login;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;
import com.module.htmlunit.definition.UtilDefinition;
import com.gargoylesoftware.htmlunit.util.Cookie;


public class ZhihuLogin {

	private static final Logger log = LoggerFactory.getLogger(ZhihuLogin.class);
	
	private static final String loginPage = "http://www.zhihu.com/";
	
	private static String username = "443256240@qq.com";
	
	private static String password = "md87315450";
	
	private static String nickname = "梅荻";
	 
	private static String selector_username = "form[novalidate=novalidate] input[name=account]";
	
	private static String selector_password = "form[novalidate=novalidate] input[name=password]";
			 
	private static String selector_loginbutton = "form[novalidate=novalidate] button[type=submit]";
	
	private static WebClient wc ;
	
	private static ZhihuLogin instance;
	
	public static ZhihuLogin getInstance() {
		if (instance == null) {
			synchronized (ZhihuLogin.class) {
				if (instance == null) {
					instance = new ZhihuLogin();
				}
			}
		}
		return instance;
	}
	
	public ZhihuLogin(){
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
		if(html.indexOf(nickname)==-1){
			bool = false;
		}
		return bool;
	}
	
	public synchronized WebPageResponse getPageInlogin(String url,List<Cookie> cookies) throws Exception{
		log.info("getPageInlogin: {}", url);
		WebPageResponse wr = getPage(wc,url);
		boolean islogin = ZhihuLogin.getInstance().isLogin(wr);
		if(islogin){
			log.info("=============  已经登录    =============");
			return wr;
		}else{
			log.info("=============  没有登录    =============");   
			//wc = userLogin(wc);
			wc = insertCookie(wc,cookies);
			log.info("=============  注入cookies   ============="); 
			WebPageResponse response = getPage(wc,url);
			return response;
		} 
	}
	
	public synchronized WebClient userLogin(WebClient webClient) throws Exception{
		
		log.info("=============  用户登录   ============="); 
		
		HtmlPage htmlpage = webClient.getPage(loginPage);  
		System.out.println(htmlpage.asXml());
		HtmlTextInput InputUserName = (HtmlTextInput)htmlpage.querySelector(selector_username);
		
		if(InputUserName==null){ 
			log.info(htmlpage.asXml());
			throw new Exception("username input text can not found :"+selector_username);
		}else{ 
			log.info(InputUserName.asXml()+"      "+username);  
			System.out.println(InputUserName.asXml()+"      "+username);
			InputUserName.setText(username);
			
		}
		
		//Select Password testArea
		HtmlPasswordInput InputPassword = (HtmlPasswordInput)htmlpage.querySelector(selector_password);
		
		if(InputPassword==null){
			throw new Exception("password input text can not found :"+selector_password);
		}else{ 
			log.info(InputPassword.asXml()+"      "+password); 
			System.out.println(InputPassword.asXml()+"      "+password);
			InputPassword.setText(password);
		} 
		
		//Select Submit Button 
		HtmlElement loginButton = (HtmlElement)htmlpage.querySelector(selector_loginbutton);
		
		if(loginButton==null){
			throw new Exception("login button can not found :"+selector_loginbutton);
		}else{ 
			log.info(loginButton.asXml()+"      "+selector_loginbutton); 
			System.out.println(loginButton.asXml()+"      "+selector_loginbutton);
			loginButton.click();
		} 
		
	
		return webClient;
		
	}
	
	public synchronized WebPageResponse getPage(WebClient webClient,URL url) throws Exception{
		
		log.info("Using Htmlunit to Send GET Request: {}", url);
		
		WebPageResponse wr = new WebPageResponse();
		
		HtmlPage page = null;
		String result = null;
		WebResponse response = null;
		
		try { 
		
			page = webClient.getPage(url); 
			response = page.getWebResponse();
			result = response.getContentAsString(); 
			int httpStatusCode = response.getStatusCode();
			wr.setHtml(result); 
			wr.setHttpStatusCode(httpStatusCode);
			wr.setUrl(page.getUrl()+"");
			wr.setUnit(UtilDefinition.HTMLUNIT);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(webClient!=null){
				//webClient.getCache().clear();
				//webClient.getCookieManager().clearCookies();
				webClient.closeAllWindows();  
			}  
		}  
		 
		return wr;
	}
	
	public synchronized WebPageResponse getPage(WebClient webClient,String url) throws Exception{ 
		URL link = new URL(url);
		return getPage(webClient,link);
	}
	
	public WebClient insertCookie(WebClient webClient,List<Cookie> cookies){
		for(Cookie e :cookies){
			webClient.getCookieManager().addCookie(e);
		}
		return webClient;
	}
	
	
}

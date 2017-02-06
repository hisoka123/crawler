package test.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import com.gargoylesoftware.htmlunit.AlertHandler;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultPageCreator;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.PageCreator;
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
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.module.ocr.service.GsxtBeijingOCRService;
import com.module.ocr.utils.IVerifycodeHandler;

public class GongShang2 {
	
	private static WebClient webClient = getWebClient();
	
	private static Gson gson = new Gson();
	
	private static String url = "http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!toIndex.dhtml";
	
	private static String file = "AllIn.out";
	 
	private static String imagePath = "D:\\eclipse\\MzImgExpPwd.jpg";
	
	public static void main(String[] args) throws Exception{
		
		
		String company = "北京天和房地产开发有限责任公司";
		
	   //  get();
	  click(company);
		
	    //String[]  strs = {"北京天和房地产开发有限责任公司","北京阳益房地产开发有限公司","北京市鲁艺房地产开发有限责任公司","保利建设开发总公司北京装饰工程分公司","北京哈工大北方科技有限公司"};
	    
		/* for(String str:strs){
			 System.out.println("-----------------------"+str+"----------------------");
			 all(str); 
			 Thread.sleep(30000);
			 System.out.println("-----------------------Thread.sleep(30000);----------------------");
		 }*/
		  
		
	}
	
	 
	
	
	
	
	public static void get() throws Exception{
		 System.out.println("-----------------------get()----------------------");
		URL gsUrl  = new URL(url);
		
		WebRequest  requestSettings = new WebRequest(gsUrl, HttpMethod.GET); 
		HtmlPage page = webClient.getPage(requestSettings); 
		WebResponse wr = page.getWebResponse();
		
		HtmlImage image = page.getFirstByXPath("//img[@id='MzImgExpPwd']");
		File imageFile = new File(imagePath);
		image.saveAs(imageFile);
		
		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
		
		AllIn ai = new AllIn();
		ai.setCookies(cookies);
		ai.setWebResponse(wr);
		//序列化操作1--FileOutputStream
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(file)); 
        oos1.writeObject(ai);//必须所有引用的对象都实现序列化（本例终究是Data这个类），否则抛出有java.io.NotSerializableException:这个异常
        oos1.close();
	}
	
	public static void click(String company) throws Exception{
		 System.out.println("-----------------------click(String company)----------------------");
		URL gsUrl  = new URL(url);
		PageCreator dpc = new DefaultPageCreator(); 
		
		//反序列化操作1---FileInputStream
        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(file)); 
        AllIn ai2 = (AllIn)ois1.readObject();
        ois1.close();
          
        WebResponse webResponse = ai2.getWebResponse();
        
        Set<Cookie> cookies = ai2.getCookies();
        for(Cookie coo:cookies){
        	webClient.getCookieManager().addCookie(coo);
        }
        
        
        		
		HtmlPage page = (HtmlPage)dpc.createPage(webResponse, webClient.openWindow(gsUrl, "windowName"));
		
		
		//String keyword = "IBM";
		String keyword = company;//北京星流航太科技有限公司
		HtmlTextInput inputkeyword = (HtmlTextInput)page.querySelector("#keyword");
		inputkeyword.setText(keyword);
		
		IVerifycodeHandler verifycodeHandler = new GsxtBeijingOCRService();
		File imageFile = new File(imagePath);
		String imageCodeByOCR = verifycodeHandler.getVerifycode(imageFile);
		System.out.println(imagePath+"-----imageCodeByOCR-------------"+imageCodeByOCR);
		
		HtmlTextInput inputUserName = (HtmlTextInput)page.querySelector("#checkcodeAlt");
		inputUserName.setText(imageCodeByOCR);
		
		System.out.println("-----------------------1111111----------------------");
		HtmlElement loginButton = (HtmlElement)page.querySelector("a[onclick*=fn_search]"); 
		
		HtmlPage loggedPage = loginButton.click();
		
		
		
		System.out.println("-----------------------22222----------------------");
		HtmlElement firstByXPath = ((HtmlElement)loggedPage.getFirstByXPath("//div[@class='list-a']"));
		if(firstByXPath==null){
			System.out.println("--------------------firstByXPath==null---33333----------------------"+loggedPage.asXml());
		}else{
			String textContent = firstByXPath.getTextContent();
			System.err.println(firstByXPath.asXml());
			
			System.out.println("-----------------------33333----------------------"+textContent);
			
		}
		
		System.err.println(loggedPage.getUrl().toString());
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
			
			webClient.setAlertHandler(new AlertHandler(){

				@Override
				public void handleAlert(Page page, String message) { 
					 System.out.println(page.getUrl()+"-----setAlertHandler-----"+message);
					 
					 
					 System.out.println(page.getWebResponse().getContentAsString());
					 
					 
					 System.out.println("---------------handleAlert--------------------");
				}
				
			});
			
		}
		return webClient;
	}
	
	
	

}

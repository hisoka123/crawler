package test.http;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.UUID;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.module.htmlunit.WebCrawler;

public class Shandong2 {
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		//获取验证码
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		HtmlPage page1 = webClient.getPage("http://218.57.139.24/");
		HtmlImage image = page1.getFirstByXPath("//img[@id='secimg']");
		File imageFile = new File("C:/Users/Administrator/Desktop/" + UUID.randomUUID() + ".jpg");
		System.out.println("imageFile：" + imageFile.getName());
		image.saveAs(imageFile);
		Scanner sc = new Scanner(System.in);
		String imagecode = sc.next();
		System.out.println("imagecode：" + imagecode);
		
		//输入关键字  验证码，点击查询按钮
		HtmlElement keywordEle = page1.getFirstByXPath("//input[@id='searchtxt']");
		keywordEle.setAttribute("value", "海尔集团公司");
		HtmlElement imagecodeEle = page1.getFirstByXPath("//input[@id='yzminput']");
		imagecodeEle.setAttribute("value", imagecode);
		HtmlElement searchbtn = page1.getFirstByXPath("//a[@id='searchbtn']");
		HtmlPage page2 = searchbtn.click();
		Thread.sleep(2000); ///////////
		
		//
		System.out.println(page2.asXml());
	}
}

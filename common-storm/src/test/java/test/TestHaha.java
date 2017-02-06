package test;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.module.htmlunit.WebCrawler;

public class TestHaha {
	public static void main(String[] args) {
		for (int i=0; i<5; i++) {
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=1", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=2", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=3", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/#/image", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/#/article", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=pic&a=allPic", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=pic&a=allPic&type=3", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo&type=3", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo&status=2", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php#/index", "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=1&name="+Math.random(), "get", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php#/newContent?articleType=1&id=57", "get", "jsoup")).start();
			
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=1", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=2", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=3", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/#/image", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/#/article", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=pic&a=allPic", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=pic&a=allPic&type=3", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo&type=3", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo&status=2", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php#/index", "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=1&name="+Math.random(), "post", "jsoup")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php#/newContent?articleType=1&id=57", "post", "jsoup")).start();
		
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=1", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=2", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=3", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/#/image", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/#/article", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=pic&a=allPic", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=pic&a=allPic&type=3", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo&type=3", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo&status=2", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php#/index", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=1&name="+Math.random(), "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php#/newContent?articleType=1&id=57", "get", "htmlunit")).start();
			
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=1", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=2", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=3", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/#/image", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/#/article", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/", "get", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=pic&a=allPic", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=pic&a=allPic&type=3", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo&type=3", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo&status=2", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=video&a=allVideo", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php#/index", "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php?c=article&a=allArticle&type=0&status=1&name="+Math.random(), "post", "htmlunit")).start();
			new Thread(new MyThread1("http://121.42.168.234/admin.php#/newContent?articleType=1&id=57", "post", "htmlunit")).start();
		}
		System.err.println("====================================init end================================================");
	}
}

class MyThread1 implements Runnable {
	private String url;
	private String method;
	private String webEngine;
	static int i = 0;
	static int ii = 0;
	static int iii = 0;
	static int iiii = 0;
	
	public MyThread1(String url, String method, String webEngine) {
		this.url = url;
		this.method = method;
		this.webEngine = webEngine;
	}


	@Override
	public void run() {
		while (true) {
			try {
				if ("post".equals(method) && "jsoup".equals(webEngine)) {
					Document document = Jsoup.connect(url).timeout(60000).post();
					i++;
					if (i%100==0) {
						System.out.println("post jsoup");
						System.out.println(Thread.currentThread().getName() + ": post jsoup 已爬取" + i + "次");
						System.err.println(document.html());
					}
				} else if ("get".equals(method) && "jsoup".equals(webEngine)) {
					Document document = Jsoup.connect(url).timeout(60000).get();
					ii++;
					if (ii%100==0) {
						System.out.println("get jsoup");
						System.out.println(Thread.currentThread().getName() + ": get jsoup 已爬取" + ii + "次");
						System.err.println(document.html());
					}
				} else if ("get".equals(method) && "htmlunit".equals(webEngine)) {
					WebCrawler.getInstance().getWebClient().getOptions().setJavaScriptEnabled(false);
					Page page = WebCrawler.getInstance().getWebClient().getPage(url);
					iii++;
					if (iii%100==0) {
						System.out.println("get htmlunit");
						System.out.println(Thread.currentThread().getName() + ": get htmlunit 已爬取" + iii + "次");
						System.err.println(page.getWebResponse().getContentAsString());
					}
				} else if ("post".equals(method) && "htmlunit".equals(webEngine)) {
					WebCrawler.getInstance().getWebClient().getOptions().setJavaScriptEnabled(false);
					Page page =  WebCrawler.getInstance().getWebClient().getPage(new WebRequest(new URL(url), HttpMethod.POST));
					iiii++;
					if (iiii%100==0) {
						System.out.println("post htmlunit");
						System.out.println(Thread.currentThread().getName() + ": post htmlunit 已爬取" + iiii + "次");
						System.err.println(page.getWebResponse().getContentAsString());
					}
				}
				Thread.sleep((int)(5*Math.random()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}

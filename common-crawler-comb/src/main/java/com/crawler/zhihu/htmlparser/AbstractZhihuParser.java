package com.crawler.zhihu.htmlparser;

import java.util.HashSet;
import java.util.Set;

import com.crawler.htmlparser.AbstractParser;

public abstract class AbstractZhihuParser extends AbstractParser{

	public static String ZHIHU = "http://www.zhihu.com";
	
	//ZhihuUserUpdation.operation
	public static String AGREE = "agree"; //赞同回答
	public static String FOLLOW = "follow"; //关注问题
	public static String ANSWER = "answer"; //回答问题
	public static String ASK = "ask";
	public static String POST = "post"; 	//发表文章
	
	public static final Set<String> COOKIE_DOMAINS;
	static {
		COOKIE_DOMAINS = new HashSet<String>();
		COOKIE_DOMAINS.add(".zhihu.com");
		COOKIE_DOMAINS.add("www.zhihu.com");
		//COOKIE_DOMAINS.add("zhuanlan.zhihu.com");
	}
	
}

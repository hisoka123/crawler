package com.crawler.linkedin.htmlparser;

import java.util.HashSet;
import java.util.Set;

import com.crawler.htmlparser.AbstractParser;

public abstract class AbstractLinkedinParser extends AbstractParser {
	public static final String LINKEDIN = "http://www.linkedin.com";
	public static final String UTF8_MARK = "utf8_mark";
	
	public static final Set<String> COOKIE_DOMAINS;
	static {
		COOKIE_DOMAINS = new HashSet<String>();
		COOKIE_DOMAINS.add(".linkedin.com");
		COOKIE_DOMAINS.add("www.linkedin.com");
		COOKIE_DOMAINS.add(".www.linkedin.com");
	}
}

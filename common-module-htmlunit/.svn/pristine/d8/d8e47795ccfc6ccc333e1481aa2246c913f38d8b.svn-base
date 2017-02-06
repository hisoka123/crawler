package com.module.htmlunit.definition;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lewis
 * 
 */
public enum UserAgent {
	LINUX_MOZILLA5("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1) Gecko/20061129 BonEcho/2.0 "), MAC_SAFARI5_1_5(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/534.55.3 (KHTML, like Gecko) Version/5.1.5 Safari/534.55.3"), WIN_MOZILLA5(
			"Mozilla/5.0 (Windows; U; Win98; en-US; rv:x.xx) Gecko/20030423 Firebird Browser/0.6 "), WIN_OPERA9_6(
			"Opera/9.60 (Windows NT 5.1; U; de) Presto/2.1.1 ");

	private static final Logger log = LoggerFactory.getLogger(UserAgent.class);
	private String agentString;

	/**
	 * @param agentString
	 */
	private UserAgent(String agentString) {
		this.agentString = agentString;
	}

	/**
	 * @return the agentString
	 */
	public String toString() {
		return agentString;
	}

	/**
	 * Get random user agent
	 * 
	 * @return
	 */
	public static UserAgent getRandomUserAgent() {
		final Random random = new Random();
		UserAgent userAgent = UserAgent.values()[random.nextInt(UserAgent.values().length)];
		log.debug("Random agent: {}", userAgent.toString());
		return userAgent;
	}
}

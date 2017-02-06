package com.sns.weibo.definition;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 


public enum AccessToken {

	ACCESSTOKEN_vcspark13520800817_userfetch("2.00oTwkUF0gTxeW299bd4ce30PTpJEC"),
	ACCESSTOKEN_vcspark13520800817_vcsparkSNSdata("2.00oTwkUFpHMPpDe31b060d84HBVE1E"),
	ACCESSTOKEN_meidi_VCSparkData("2.00wl5bTCqVLMpC9720db5d75MF4yeD"),
	ACCESSTOKEN_meidi_vcspark("2.00wl5bTC09bAomc96cfd845fboKoLE"),
	ACCESSTOKEN_meidi_SApool("2.00wl5bTCDU9d3Eecc802df951MQX5B"),
	ACCESSTOKEN_meidi_SA_OAuth2("2.00wl5bTC01maH956342dd79dW5FYUD"),
	ACCESSTOKEN_meidi_SATest("2.00wl5bTC86rXjCc9c6c70f574Ssf5B"),
	ACCESSTOKEN_meidi_meidiApp("2.00wl5bTCv2hrXE8b2af5e703j6T6sB");
	
	
	private static final Logger log = LoggerFactory.getLogger(AccessToken.class);
	
	private String accesstoken;
	
	/**
	 * @param agentString
	 */
	private AccessToken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	
	/**
	 * @return the accesstoken
	 */
	public String toString() {
		return accesstoken;
	}

	/**
	 * Get random user agent 
	 * 
	 * @return
	 */
	public static AccessToken getRandomUserAgent() {
		final Random random = new Random();
		AccessToken accesstoken = AccessToken.values()[random.nextInt(AccessToken.values().length)];
		log.debug("Random agent: {}", accesstoken.toString());
		return accesstoken;
	}
}

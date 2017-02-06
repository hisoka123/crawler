package com.sns.oauth;

import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.SinaWeiboApi20;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class SinaWeiboOAuthService {

	private static final String NETWORK_NAME = "SinaWeibo";
	private static final String PROTECTED_RESOURCE_URL = "https://api.weibo.com/2/statuses/home_timeline.json";
	private static final Token EMPTY_TOKEN = null;
	
	/*
	 * account:13520800817 / 12qwaszx
	 * nickname:vcpsark13520800817
	 * app:vcsparkSNSdata
	 * App Key:3505613165
	 * App Secret:872304208be3d3041e4ce74f7b896023
	 * access_token: "2.00oTwkUFpHMPpDe31b060d84HBVE1E"
	 * */
	 
	public static void main(String[] args) {
		String apiKey = "3505613165";
		String apiSecret = "872304208be3d3041e4ce74f7b896023";
		OAuthService service = new ServiceBuilder()
				.provider(SinaWeiboApi20.class).apiKey(apiKey)
				.apiSecret(apiSecret).callback("http://open.weibo.com").build();
		Scanner in = new Scanner(System.in);

		System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
		System.out.println();
		
		
		// Obtain the Authorization URL
	    System.out.println("Fetching the Authorization URL...");
	    String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
	    System.out.println("Got the Authorization URL!");
	    System.out.println("Now go and authorize Scribe here:");
	    System.out.println(authorizationUrl);
	    System.out.println("And paste the authorization code here");
	    System.out.print(">>");
	    Verifier verifier = new Verifier(in.nextLine());
	    System.out.println();
		
	 // Trade the Request Token and Verifier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();

	    // Now let's go and ask for a protected resource!
	    System.out.println("Now we're going to access a protected resource...");
	    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
	    service.signRequest(accessToken, request);
	    Response response = request.send();
	    System.out.println("Got it! Lets see what we found...");
	    System.out.println();
	    System.out.println(response.getCode());
	    System.out.println(response.getBody());

	    System.out.println();
	    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
	    
		
		
		
		
		
		
		
		
		
	}

}

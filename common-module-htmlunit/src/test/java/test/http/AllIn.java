package test.http;

import java.io.Serializable;
import java.util.Set;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class AllIn implements Serializable{
	private static final long serialVersionUID = -1987208388307645537L;

	private WebResponse webResponse;
	
	private Set<Cookie> cookies ;

	public WebResponse getWebResponse() {
		return webResponse;
	}

	public void setWebResponse(WebResponse webResponse) {
		this.webResponse = webResponse;
	}

	public Set<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}
	
	

}

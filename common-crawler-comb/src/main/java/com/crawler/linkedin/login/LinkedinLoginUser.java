/**
 * 
 */
package com.crawler.linkedin.login;

/**
 * @author kingly
 * @date 2015年9月11日
 * 
 */
public class LinkedinLoginUser {
	private String nickname;
	private String username;
	private String password;
	
	public LinkedinLoginUser() {
		this.nickname = "曾荷瑶";
		this.username = "cppkyvvude@sina.com";
		this.password = "password";
	}
	public LinkedinLoginUser(String nickname, String username, String password) {
		super();
		this.nickname = nickname;
		this.username = username;
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

/**
 * 
 */
package com.crawler.linkedin.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.crawler.linkedin.login.LinkedinLoginUser;

/**
 * @author kingly
 * @date 2015年9月11日
 * 
 */
public class LoginUsersHelper {
	private static Document document = null;
	private static int index = 0;
	
	static {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(LoginUsersHelper.class.getResourceAsStream("/linkedinLoginUsers.xml")); 
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static LinkedinLoginUser nextUser() {
		NodeList nodeList = document.getElementsByTagName("user");
		if (nodeList==null || nodeList.getLength()==0) return new LinkedinLoginUser();
		if (index==nodeList.getLength()) index=0;
		
		Element ele = (Element)nodeList.item(index);
		NodeList nicknameEles = ele.getElementsByTagName("nickname");
		NodeList usernameEles = ele.getElementsByTagName("username");
		NodeList passwordEles = ele.getElementsByTagName("password");
		if (nicknameEles==null || usernameEles==null || passwordEles==null) return new LinkedinLoginUser();
		
		String nickname = nicknameEles.item(0).getTextContent();
		String username = usernameEles.item(0).getTextContent();
		String password = passwordEles.item(0).getTextContent();
		
		if ("".equals(nickname) || "".equals(username) || "".equals(password)) return new LinkedinLoginUser(); 
		index++;
		return new LinkedinLoginUser(nickname, username, password);
	}
	
}

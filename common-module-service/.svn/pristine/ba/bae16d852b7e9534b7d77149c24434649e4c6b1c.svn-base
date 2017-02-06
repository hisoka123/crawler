/**
 * 
 */
package com.crawlermanage.service.linkedin.task;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.crawler.linkedin.util.LoginUsersHelper;

/**
 * @author kingly
 * @date 2015年9月21日
 * 
 */
@Component
@Scope("singleton")
public class XmlListenerContext {
	@Autowired
	public LinkedinUserProducer linkedinUserProducer;
	private static Document document = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlListenerContext.class);
	
	static {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(LoginUsersHelper.class.getResourceAsStream("/listeners.xml"));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Listener> getCrawlerTaskListeners() {
		NodeList nodeList = document.getElementsByTagName("crawlertaskListener");
		if (nodeList==null || nodeList.getLength()==0) return null;
		Element ele = (Element)nodeList.item(0);
		
		nodeList = ele.getElementsByTagName("className");
		if (nodeList==null || nodeList.getLength()==0) return null;
		Element ele_class_name = (Element)nodeList.item(0);
		String className = ele_class_name.getTextContent();
		
		nodeList = ele.getElementsByTagName("number");
		String numberStr = "1";
		if (nodeList!=null && nodeList.getLength()!=0) {
			Element ele_number = (Element)nodeList.item(0);
			numberStr = ele_number.getTextContent();
		}
		int num = Integer.parseInt(numberStr);
		
		List<Listener> listeners = num<1 ? null : new ArrayList<Listener>();
		try {
			for (int i = 0; i < num; i++) {
				Class<?> clazz = Class.forName(className);
				Constructor<?> constructor = clazz.getDeclaredConstructor(LinkedinUserProducer.class);
				Listener listener = (Listener) constructor.newInstance(linkedinUserProducer);
				listeners.add(listener);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return listeners;
	}

}

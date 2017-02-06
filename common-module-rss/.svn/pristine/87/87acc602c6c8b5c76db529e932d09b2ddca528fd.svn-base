package com.module.rss.fetch;

import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class RssXMLFetch {

	private static RssXMLFetch instance;
	
	private DocumentBuilderFactory dbf;
	
	private TransformerFactory tf;

	public RssXMLFetch() {
		dbf = DocumentBuilderFactory.newInstance();
		tf = TransformerFactory.newInstance();
	}

	public static RssXMLFetch getInstance() {
		if (instance == null) {
			synchronized (RssXMLFetch.class) {
				if (instance == null) {
					instance = new RssXMLFetch();
				}
			}
		}
		return instance;
	}
	
	public String fetch(String feedurl) throws Exception{
		URL url = new URL(feedurl);  
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(url.openStream());   
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer); 
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		return writer.toString();
	}

}

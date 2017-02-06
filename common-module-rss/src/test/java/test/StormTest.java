package test;

import java.io.StringWriter;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class StormTest {

	public static void main(String[] args) throws Exception{
		//String feedUrl ="http://news.baidu.com/ns?word=%D0%A1%C3%D7note+site%3A36kr.com&tn=newsrss&sr=0&cl=2&rn=20&ct=0";
		String feedUrl = "https://news.google.com/news/feeds?q=%E5%B0%8F%E7%B1%B3note%20site:36kr.com";
		URL url = new URL(feedUrl); 
		 
		/*HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml"); 
		InputStream xml = connection.getInputStream(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(xml));
		StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();
        System.out.println(sb.toString());*/
		/*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(xml);
		System.out.println(doc.getXmlEncoding());  */
		/*XmlReader reader = new XmlReader(url);   
		if (reader != null){
            StringBuilder sb = new StringBuilder();  
        }*/
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(url.openStream());  
		System.out.println(doc.getXmlEncoding());  
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		System.out.println(writer.toString());
		/*DOMImplementationLS domImplLS = (DOMImplementationLS) doc.getImplementation();
		LSSerializer serializer = domImplLS.createLSSerializer();
		String str = serializer.writeToString(node);*/
		
		/*String source = "Fri, 29 May 2015 18:16:36";
		
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss",Locale.US);  
		
		Date date = formatter.parse(source);*/
		
		//System.out.println(date.toString());
		
	}

}

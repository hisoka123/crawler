package com.crawler.htmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.domain.json.HtmlJson;
import com.google.gson.Gson;

public abstract class AbstractParser {
	
	public HtmlJson parser(String json){
		HtmlJson htmlJson = null;
		if(json!=null){
			Gson gson = new Gson();  
			htmlJson = gson.fromJson(json, HtmlJson.class);
		}
		return htmlJson;
	}
	
	public Elements getElements(Element e,String selector){
		Elements es = null;
		if(e!=null){
			es = e.select(selector);
		} 
		return es;
	}
	
	public Element getfirstChildElement(Element e,String selector){
		Element childElement = null;
		Elements es = getElements(e,selector);
		if(es!=null&&es.size()>0){
			childElement = es.get(0);
		}
		return childElement;
	}
	
	public Element getElementByIndex(Element e,String selector,int index){
		Element childElement = null;
		Elements es = getElements(e,selector);
		if(es!=null&&es.size()>0){
			childElement = es.get(index);
		}
		return childElement;
	}
	
	public String[] getElementText(Elements es){
		String[] texts = null;
		if(es!=null&&es.size()>0){
			 texts = new String[es.size()];
			 for(int i=0;i<es.size();i++){
				 Element e = es.get(i);
				 String text = getElementText(e);
				 texts[i] = text;
			 }
		}
		return texts;
	}
	
	public String[] getElementAttr(Elements es,String attr){
		String[] texts = null;
		if(es!=null&&es.size()>0){
			 texts = new String[es.size()];
			 for(int i=0;i<es.size();i++){
				 Element e = es.get(i);
				 String text = getElementAttr(e,attr);
				 texts[i] = text;
			 }
		}
		return texts;
	}
	
	public String getElementText(Element e){
		String text = null;
		if(e!=null){
			text = e.text();
		}
		return text;
	}
	
	public String getElementHtml(Element e){
		String text = null;
		if(e!=null){
			text = e.outerHtml();
		}
		return text;
	}
	
	public String getElementAttr(Element e,String attr){
		String text = null;
		if(e!=null){
			text = e.hasAttr(attr)?e.attr(attr):null;
		}
		return text;
	}
	
	public String getHtml(Element e){
		String html = null;
		if(e!=null){
			html = e.outerHtml();
		}
		return html;
	}
	
	
	public List<String> getSubStringByRegex(String str, String regex) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}

}

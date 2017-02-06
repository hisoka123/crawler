package com.crawler.zjsfgkw.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crawler.htmlparser.AbstractParser;
import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawler.zjsfgkw.domain.json.ExecuteCaseSearchJson;

@Component
public class ExecuteCaseSearchFeedListParser extends AbstractParser{
	
	private static final Logger log = LoggerFactory.getLogger(ExecuteCaseSearchFeedListParser.class);
	
	public List<ExecuteCaseSearchJson> executeCaseSearchParser(String html) {
		
		List<ExecuteCaseSearchJson> executeCaseSearchs=new ArrayList<ExecuteCaseSearchJson>();
		if(html!=null){
		Elements eles = Jsoup.parseBodyFragment(html).select("div.checkCase-box");
		if(eles==null || eles.isEmpty()) {
			return null;
		}
		
		Element ele = eles.get(0);
		Elements ele_results = getElements(ele, "table.dept_basic");
		if (ele_results==null || ele_results.isEmpty()) {
			return null;
		}
		
		Element ele_result=ele_results.get(0);
		Elements tr_results =getElements(ele_result, "tr");
		if (tr_results==null || tr_results.isEmpty()) {
			executeCaseSearchs=null;
		}
		
		int j=(tr_results.size())/3;
		for(int i=0;i<j;i++){
			int trNo=i*3;
			
			ExecuteCaseSearchJson executeCaseSearch=new ExecuteCaseSearchJson();
			Element ele_tr0 = getElementByIndex(ele_result, "tr",trNo);
			Element ele_caseNo = getfirstChildElement(ele_tr0, "div.left_case_number");
			String caseNo =getElementText(ele_caseNo);
			executeCaseSearch.setCaseNo(caseNo);//案号
			
			Element ele_tr1 =getElementByIndex(ele_result, "tr",trNo+1);
			Element ele_td1 = getElementByIndex(ele_tr1, "td",1);
			String court = getElementText(ele_td1);
			executeCaseSearch.setCourt(court);//法院
			
			Element ele_td2 = getElementByIndex(ele_tr1, "td",3);
			String caseDate = getElementText(ele_td2);
			executeCaseSearch.setCaseDate(caseDate);//立案日期
			
			Element ele_tr2 = getElementByIndex(ele_result, "tr",trNo+2);
			Element ele_td3 = getElementByIndex(ele_tr2, "td",1);
			String caseState = getElementText(ele_td3);
			executeCaseSearch.setCaseState(caseState);//案件状态
			
			Element ele_td4 = getElementByIndex(ele_tr2, "td",3);
			String principal = getElementText(ele_td4);
			executeCaseSearch.setPrincipal(principal);//当事人
			
			executeCaseSearchs.add(executeCaseSearch);
		}
		 }else{
				executeCaseSearchs=null;
		 }
		return executeCaseSearchs;
	}

	
	public List<CreditJson> executeCaseSearchParser2(String html) {
		
		List<CreditJson> executeCaseSearchs=new ArrayList<CreditJson>();
		Elements eles = Jsoup.parseBodyFragment(html).select("div.checkCase-box");
		if(eles==null || eles.isEmpty()) {
			return null;
		}
		
		Element ele = eles.get(0);
		Elements ele_results = getElements(ele, "table.dept_basic");
		if (ele_results==null || ele_results.isEmpty()) {
			return null;
		}
		
		Element ele_result=ele_results.get(0);
		Elements tr_results =getElements(ele_result, "tr");
		if (tr_results==null || tr_results.isEmpty()) {
			executeCaseSearchs=null;
		}
		
		int j=(tr_results.size())/3;
		for(int i=0;i<j;i++){
			int trNo=i*3;
			
			CreditJson executeCaseSearch=new CreditJson();
			Element ele_tr0 = getElementByIndex(ele_result, "tr",trNo);
			Element ele_caseNo = getfirstChildElement(ele_tr0, "div.left_case_number");
			String caseNo =getElementText(ele_caseNo);
			executeCaseSearch.setCaseNo(caseNo);//案号
			
			Element ele_tr1 =getElementByIndex(ele_result, "tr",trNo+1);
			Element ele_td1 = getElementByIndex(ele_tr1, "td",1);
			String court = getElementText(ele_td1);
			executeCaseSearch.setCourt(court);//法院
			
			Element ele_td2 = getElementByIndex(ele_tr1, "td",3);
			String caseDate = getElementText(ele_td2);
			executeCaseSearch.setCaseDate(caseDate);//立案日期
			
			Element ele_tr2 = getElementByIndex(ele_result, "tr",trNo+2);
			Element ele_td3 = getElementByIndex(ele_tr2, "td",1);
			String caseState = getElementText(ele_td3);
//			executeCaseSearch.setCaseState(caseState);//案件状态
			
			Element ele_td4 = getElementByIndex(ele_tr2, "td",3);
			String principal = getElementText(ele_td4);
			executeCaseSearch.setName(principal);//当事人
			
			executeCaseSearchs.add(executeCaseSearch);
		}
		return executeCaseSearchs;
	}
}

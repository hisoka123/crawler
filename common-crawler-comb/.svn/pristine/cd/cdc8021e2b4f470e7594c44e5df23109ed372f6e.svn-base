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

@Component
public class CreditFeedListParser extends AbstractParser{
	
	private static final Logger log = LoggerFactory.getLogger(CreditFeedListParser.class);
	
	public List<CreditJson> creditParser(String html) {
	
		List<CreditJson> credits=new ArrayList<CreditJson>();
		if(html!=null){
		Elements eles = Jsoup.parseBodyFragment(html).select("div.informationcheck-box");
		if(eles==null || eles.isEmpty()) {
			credits=null;
		}
		
		Element ele = eles.get(0);//eles.get(0) 个人信息 ；eles.get(1) 单位信息
		Elements ele_results = getElements(ele, "table.dept_basic");
		if (ele_results==null || ele_results.isEmpty()) {
			credits=null;
		}
		
		Element ele_result=ele_results.get(0);
		Elements tr_results = getElements(ele_result, "tr");
		if (tr_results==null || tr_results.isEmpty()) {
			credits=null;
		}
		
		int j=(tr_results.size())/5;
		for(int i=0;i<j;i++){
			int trNo=i*5;
			CreditJson credit= new CreditJson();
			
			Element ele_tr0 = getElementByIndex(ele_result, "tr",trNo);//第一行数据
			
			Element ele_th0 = getElementByIndex(ele_tr0, "th",0);
			Element ele_name = getElementByIndex(ele_th0, "span",0);
			String name = getElementText(ele_name);
			credit.setName(name);//姓名
			
			Element ele_idNo = getElementByIndex(ele_th0, "span",1);
			String idNo = getElementText(ele_idNo);
			credit.setIdNo(idNo);//证件号码
			
			Element ele_creditDate = getElementByIndex(ele_th0, "span",2);
			String creditDate = getElementText(ele_creditDate);
			credit.setCreditDate(creditDate);//曝光日期
			
			Element ele_tr1 = getElementByIndex(ele_result, "tr",trNo+1);//第二行数据
			
			Element ele_address = getElementByIndex(ele_tr1, "td",1);
			String address = getElementText(ele_address);
			credit.setAddress(address);//地址
			
			Element ele_enforceBasis = getElementByIndex(ele_tr1, "td",3);
			String enforceBasis = getElementText(ele_enforceBasis);
			credit.setEnforceBasis(enforceBasis);//执行依据
			
			Element ele_tr2 = getElementByIndex(ele_result, "tr",trNo+2);//第三行数据
			
			Element ele_caseNo = getElementByIndex(ele_tr2, "td",1);
			String caseNo = getElementText(ele_caseNo);
			credit.setCaseNo(caseNo);//案号
			
			Element ele_executReason = getElementByIndex(ele_tr2, "td",3);
			String executReason = getElementText(ele_executReason);
			credit.setExecutReason(executReason);//执行案由
			
			Element ele_tr3 = getElementByIndex(ele_result, "tr",trNo+3);//第四行数据
			
			Element ele_court = getElementByIndex(ele_tr3, "td",1);
			String court = getElementText(ele_court);
			credit.setCourt(court);//执行法院
			
			Element ele_amountNotExecuted = getElementByIndex(ele_tr3, "td",3);
			String amountNotExecuted = getElementText(ele_amountNotExecuted);
			credit.setAmountNotExecuted(amountNotExecuted);//未执行金额
			
			Element ele_tr4 = getElementByIndex(ele_result, "tr",trNo+4);//第五行数据
			
			Element ele_caseDate = getElementByIndex(ele_tr4, "td",1);
			String caseDate = getElementText(ele_caseDate);
			credit.setCaseDate(caseDate);//立案日期
			
			Element ele_targetAmount = getElementByIndex(ele_tr4, "td",3);
			String targetAmount = getElementText(ele_targetAmount);
			credit.setTargetAmount(targetAmount);//标的金额
			
			credits.add(credit);
		}
		}else{
			credits=null;
		}
		return credits;
	}

}

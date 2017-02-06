package com.crawler.dailianmeng.htmlparser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crawler.dailianmeng.domain.json.LoanUnionFeedJson;
import com.crawler.dailianmeng.domain.json.UserFeedJson;
import com.crawler.domain.json.Result;
import com.crawler.htmlparser.AbstractParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class DaiLianMengUserInfoParser extends AbstractParser{

	private static final Logger logger = LoggerFactory.getLogger(DaiLianMengUserInfoParser.class);
	
	public UserFeedJson parseResultHtml(String html) {
		logger.info("===========DaiLianMengUserInfoParser.parseResultHtml  start==========");
		UserFeedJson userFeedJson = new UserFeedJson();
		
		List<Map<String, String>> tbodyList = new ArrayList<Map<String, String>>();
		
		Elements eles = Jsoup.parseBodyFragment(html).select("table");
		if(eles==null || eles.isEmpty()) {
        	userFeedJson.setTipMessage("此名称暂时没有找到相关的信用记录!");
        } else {
        	//解析table中的数据
        	Element tbody = Jsoup.parseBodyFragment(html).select("tbody").get(0);
        	
        	Elements trElements = tbody.select("tr");
        	for (Element trElement : trElements) {
            	Elements tdElement = trElement.select("td");
            	
            	Element element1 = tdElement.get(1);
            	Element element2 = tdElement.get(2);
            	Element element3 = tdElement.get(3);
            	Element element4 = tdElement.get(4);
            	Element element5 = tdElement.get(5);
            	Element element6 = tdElement.get(6);
            	Element element7 = tdElement.get(7);
            	
        		Element aElement = element7.select("a").get(0);
        		String hrefElement = getElementAttr(aElement, "href");
        		
        		Map<String, String> map = new TreeMap<String, String>();
        		
        		map.put("name", element1.text());
        		map.put("lenderMoney", element2.text());
        		map.put("lenderDate", element3.text());
        		map.put("lenderMethod", element4.text());
        		map.put("InfoSource", element5.text());
        		map.put("updateDate", element6.text());
        		map.put("detailUrl", "http://www.dailianmeng.com"+hrefElement);
				
        		tbodyList.add(map);
			}
        	
        }
		
		userFeedJson.setData(tbodyList);
		
		return userFeedJson;
	}
	
	public Result<Map<String,String>> parseDetailHtml(String html) {
		logger.info("===========DaiLianMengUserInfoParser.parseDetailHtml  start==========");
		
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		Map<String, String> map = new TreeMap<String, String>();
		
    	Elements tbody = Jsoup.parseBodyFragment(html).select("tbody");
    	
    	if(tbody==null || tbody.isEmpty()) {
    		map.put("tips", "没有详情信息");
    	} else {
    		Elements trElements = tbody.select("tr");
    		for (Element trElement : trElements) {
    			Element thElement = trElement.select("th").get(0);
    			Element tdElement = trElement.select("td").get(0);
				switch (thElement.text()) {
					case "被执行人姓名/名称":
						map.put("name", tdElement.text());
						break;
					case "债务人的姓名":
						map.put("name", tdElement.text());
						break;
					case "案号":
						map.put("caseNum", tdElement.text());
						break;
					case "年龄":
						map.put("age", tdElement.text());
						break;
					case "性别":
						map.put("sex", tdElement.text());
						break;
					case "债务人的身份证号":
						map.put("cardID", tdElement.text());
						break;
					case "身份证号/组织机构代码":
						map.put("cardID", tdElement.text());
						break;
					case "法定代表人或者负责人":
						map.put("legalPerson", tdElement.text());
						break;
					case "执行法院":
						map.put("executeCourt", tdElement.text());
						break;
					case "省份":
						map.put("province", tdElement.text());
						break;
					case "执行依据文号":
						map.put("executeNum", tdElement.text());
						break;
					case "立案时间":
						map.put("caseDate", tdElement.text());
						break;
					case "做出执行依据单位":
						map.put("dependCourt", tdElement.text());
						break;
					case "生效法律文书确定的义务":
						map.put("effectNum", tdElement.text());
						break;
					case "被执行人的履行情况":
						map.put("executeSituation", tdElement.text());
						break;
					case "已履行":
						map.put("alreadyExecute", tdElement.text());
						break;
					case "未履行":
						map.put("noExecute", tdElement.text());
						break;
					case "失信被执行人行为情形":
						map.put("behaviorSituation", tdElement.text());
						break;
					case "发布时间":
						map.put("pubDate", tdElement.text());
						break;
					case "更新时间":
						map.put("updateDate", tdElement.text());
						break;
					default:
						break;
				}
			}
    	}
    	
    	result.setData(map);
    	
		return result;
	}
	
	public List<LoanUnionFeedJson> parseDetailPage(String resultHtmls) {
		logger.info("===========DaiLianMengUserInfoParser.parseDetailHtml  start==========");
		//解析result
		Gson gson = new Gson();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		
		List<LoanUnionFeedJson> loanUnionFeedJsons = new ArrayList<LoanUnionFeedJson>();
		
		Object detailResults = resultHtmlMap.get("detailPages");
		if (detailResults != null) {
			List<String> detailResultHtmls = (List<String>)detailResults;
			for (String detailResultHtml : detailResultHtmls) {
				Document detailResultDoc = Jsoup.parse(detailResultHtml);
				Element tableElement = detailResultDoc.getElementById("yw0");
				LoanUnionFeedJson feedJson = new LoanUnionFeedJson();
				String describe = "";
				if (tableElement != null) {
					Elements trElements = tableElement.select("tr");
					for (Element trElement : trElements) {
						Element thElement = trElement.select("th").get(0);
						Element tdElement = trElement.select("td").get(0);
						String tdText = tdElement.text();
						switch (thElement.text()) {
						case "被执行人姓名/名称":
							feedJson.setName(tdText);
							break;
						case "债务人的姓名":
							feedJson.setName(tdText);
							break;
						case "诈骗人的姓名":
							feedJson.setName(tdText);
							break;
						case "案号":
							feedJson.setCaseNum(tdText);
							break;
						case "年龄":
							feedJson.setAge(tdText);
							break;
						case "性别":
							feedJson.setSex(tdText);
							break;
						case "身份证号/组织机构代码":
							feedJson.setCardID(tdText);
							break;
						case "债务人的身份证号":
							feedJson.setCardID(tdText);
							break;
						case "诈骗人的身份证号":
							feedJson.setCardID(tdText);
							break;
						case "法定代表人或者负责人":
							feedJson.setLegalPerson(tdText);
							break;
						case "执行法院":
							feedJson.setExecuteCourt(tdText);
							break;
						case "省份":
							feedJson.setProvince(tdText);
							break;
						case "执行依据文号":
							feedJson.setExecuteNum(tdText);
							break;
						case "立案时间":
							feedJson.setCaseDate(tdText);
							break;
						case "做出执行依据单位":
							feedJson.setDependCourt(tdText);
							break;
						case "生效法律文书确定的义务":
							feedJson.setEffectNum(tdText);
							break;
						case "被执行人的履行情况":
							feedJson.setExecuteSituation(tdText);
							break;
						case "已履行":
							feedJson.setAlreadyExecute(tdText);
							break;
						case "未履行":
							feedJson.setNoExecute(tdText);
							break;
						case "失信被执行人行为情形":
							feedJson.setBehaviorSituation(tdText);
							break;
						case "发布时间":
							feedJson.setPubDate(tdText);
							break;
						case "更新时间":
							feedJson.setUpdateDate(tdText);
							break;
						case "债务的金额":
							feedJson.setDebtMoney(tdText);
							break;
						case "诈骗的金额":
							feedJson.setDebtMoney(tdText);
							break;
						case "贷款日期":
							feedJson.setLoanDate(tdText);
							break;
						case "诈骗日期":
							feedJson.setLoanDate(tdText);
							break;
						case "贷款期限":
							feedJson.setLoanTerm(tdText);
							break;
						case "名单类型":
							feedJson.setListType(tdText);
							break;
						case "借款状态":
							feedJson.setLoanState(tdText);
							break;
						case "描述":
							feedJson.setDescribe(tdText);
							break;
						default:
							if (thElement.text().contains("身份证号")) {
								feedJson.setCardID(tdText);
							} else if(thElement.text().contains("姓名")) {
								feedJson.setName(tdText);
							} else {
								if (!"".equals(tdText) && !"信息来源网址".equals(thElement.text())) {
									describe += thElement.text() + "："+tdText + "；";
								}
							}
							break;
						}
					}
				}
				
				describe = feedJson.getDescribe() == null ? describe : describe +feedJson.getDescribe();
				feedJson.setDescribe(describe);
				
				Elements h1Elements = detailResultDoc.select("h1");
				for (Element h1Element : h1Elements) {
					String h1Text = h1Element.text();
					if (h1Text.contains("身份证号")) {
						Element nextElement = h1Element.nextElementSibling();
						String nextElementText = nextElement.text();
						if (nextElementText.contains("发证地点")) {
							String[] contentArr = nextElementText.split("，");
							for (String content : contentArr) {
								String[] splitArr = content.split("：");
								if (splitArr.length == 2) {
									switch (splitArr[0].trim()) {
									case "身份证号":
										feedJson.setCardID(splitArr[1]);
										break;
									case "性别":
										feedJson.setSex(splitArr[1]);
										break;
									case "出生时间":
										feedJson.setBirthday(splitArr[1]);
										break;
									case "年龄":
										feedJson.setAge(splitArr[1]);
										break;
									case "发证地点":
										feedJson.setIssuePlace(splitArr[1]);
										break;
									default:
										break;
									}
								}
							}
						}
					}
				}
				
				loanUnionFeedJsons.add(feedJson);
			}
		}
    	
		return loanUnionFeedJsons;
	}
}

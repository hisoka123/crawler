package com.crawler.sipo.htmlparse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.crawler.htmlparser.AbstractParser;
import com.crawler.sipo.domain.json.TransactionDataSipo;



@Component
public class TransactionDataParser extends AbstractParser {

//解析html文件，返回对象
	public List<TransactionDataSipo>  transactionParser(String html){				

		List<TransactionDataSipo> trans = new ArrayList<TransactionDataSipo>(); 
		Document doc = null;
		doc = Jsoup.parse(html,"utf-8");
		Element div_main = doc.getElementsByAttributeValue("class","art_rbox").first();	
	   if (null !=div_main) {		
		Elements tables=div_main.getElementsByAttributeValue("class","table_flztxx");	
		if (tables==null || tables.isEmpty()) {
			return trans;
		}
		for (Element table:tables) {
			TransactionDataSipo tran=new TransactionDataSipo();
			Elements  tds_table=table.select("table").select("tbody").select("tr").
					select("table").select("tbody").select("tr").select("td");
			String num=tds_table.get(1).text();
			String date=tds_table.get(5).text();
			String type=tds_table.get(7).text();
			String content=tds_table.get(8).text();
			tran.setNum(num);
			tran.setDate(date);
			tran.setType(type);
			tran.setContent(content);
			trans.add(tran);
		}
	}
		return trans;
	}

}

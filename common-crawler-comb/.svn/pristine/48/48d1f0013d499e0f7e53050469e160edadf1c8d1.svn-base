package com.crawler.sipo.htmlparse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.crawler.htmlparser.AbstractParser;
import com.crawler.sipo.domain.json.SearchEngineSipo;



@Component
public class SipoSearchParser extends AbstractParser {

//解析html文件，返回对象
	public List<SearchEngineSipo>  sipoListParser(String html){					
	
	List<SearchEngineSipo> users = new ArrayList<SearchEngineSipo>(); 
	Document doc = null;
	doc = Jsoup.parse(html,"utf-8");
	Element div_main = doc.getElementsByAttributeValue("class","w790 right").first();	
	if (null !=div_main) {
		Elements  divs=div_main.getElementsByAttributeValue("class","cp_box");
		if (divs==null || divs.isEmpty()) {
			return users;
		}
		 for (Element div : divs) {
				SearchEngineSipo user = new SearchEngineSipo();		
				//专利名称
				Element div_name=div.getElementsByAttributeValue("class","cp_linr").first();
				Element name=div_name.select("h1").first();	
			   String name_str=name.toString().replace("&nbsp;","");
	
				String[] s_name=name_str.split("]");
				String licenseName=s_name[1].replace("</h1>", "");
				user.setLicenseName(licenseName);
				
				Element div_content=div_name.select("ul").first();
				Elements li_content=div_content.select("li");
				//授权公告号
				String licenseNum_text=li_content.get(0).text();
				String[] s_licenseNum=licenseNum_text.split("：");
				String licenseNum=s_licenseNum[1].trim();
				user.setLicenseNum(licenseNum);
			
				//授权公告日
				String licenseDate_text=li_content.get(1).text();
				String[] s_licenseDate=licenseDate_text.split("：");
				String licenseDate=s_licenseDate[1].trim();			
				user.setLicenseDate(licenseDate);
				//申请号
				String applicationNum_text=li_content.get(2).text();
				String[] s_applicationNum=applicationNum_text.split("：");
				String applicationNum=s_applicationNum[1].trim();
				user.setApplicationNum(applicationNum);
				//申请日
				String applicationDate_text=li_content.get(3).text();
				String[] s_applicationDate=applicationDate_text.split("：");
				String applicationDate=s_applicationDate[1].trim();
				user.setApplicationDate(applicationDate);
				//专利权人
				String patentHolder_text=li_content.get(4).text();
				String[] s_patentHolder=patentHolder_text.split("：");
				String patentHolder=s_patentHolder[1].trim();
				user.setPatentHolder(patentHolder);
				//发明人
				String inventor_text=li_content.get(5).text();
				String[] s_inventor=inventor_text.split("：");
				String inventor=s_inventor[1].trim();
				user.setInventor(inventor);
				//地址
				String address_text=li_content.get(7).text();
				String[] s_address=address_text.split("：");
				String address=s_address[1].trim();
				user.setAddress(address);
				//分类号
				String classNumber_text=li_content.get(8).text();
				String[] s_classNumber=classNumber_text.split("：");
				String classNumber=s_classNumber[1].trim().replace("全部", "").replace("专利代理机构", "").trim();
				user.setClassNumber(classNumber);
				//摘要
				Element div_summary=div_name.getElementsByAttributeValue("class","cp_jsh").first();				
				String summary= div_summary.text().replace("全部", "").trim();
				user.setSummary(summary);
				
				//图片
				Element div_img=div.getElementsByAttributeValue("class","cp_img").first();
				String img = div_img.getElementsByTag("img").attr("src");
				user.setImg("http://epub.sipo.gov.cn/"+img);
				
				//二维码图片
				Element div_qrcode=div.getElementsByTag("a").last();
				String qrcode = getElementAttr(div_qrcode,"href");
				user.setQrcode("http://epub.sipo.gov.cn/"+qrcode);
				
				//事务数据
				
				Element div_tran=div_name.getElementsByAttributeValue("class","cp_botsm").first();
				Elements div_a=div_tran.select("a");	
				String  a_text=div_a.last().getElementsByTag("a").attr("href");
				String[] s_an=a_text.split("'");
				String num=s_an[1].trim();	
				user.setNum(num);
				users.add(user);
		}
	}
		
		return users;
	}
}

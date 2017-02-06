package com.crawlermanage.controller.api.zjsfgkw;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawler.zjsfgkw.domain.json.ExecuteCaseSearchJson;
import com.crawler.zjsfgkw.domain.json.ZjsfgkwJson;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwCreditCompanyService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwCreditService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwExecuteCaseSearchService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwLimitBiddingService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwLimitExitService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwLimitHighConsumService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/api/zjsfgkw")
public class ZjsfgkwAPIController {
	
	@Autowired
    private ZjsfgkwExecuteCaseSearchService zjsfgkwExecuteCaseSearchService;
	@Autowired
	private ZjsfgkwCreditCompanyService zjsfgkwCreditCompanyService;
	@Autowired
	private ZjsfgkwLimitHighConsumService zjsfgkwLimitHighConsumService;
	@Autowired
	private ZjsfgkwLimitExitService zjsfgkwLimitExitService;
	@Autowired
	private ZjsfgkwLimitBiddingService zjsfgkwLimitBiddingService;
	
	@Autowired
	private ZjsfgkwCreditService zjsfgkwCreditService;
	
	
	@RequestMapping(value = "/getZjsfgkwDataOnce")
	@ResponseBody
    public String getShixinDataOnce(@RequestParam("type")String type, @RequestParam("value")String value) throws IOException {  
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<ZjsfgkwJson> listzjsfgkwJson=new ArrayList<ZjsfgkwJson>();
        Result<List<ZjsfgkwJson>> resultResult = new Result<List<ZjsfgkwJson>>();
        String resultJson =null;
		String txtReallyName="";
		String txtCredentialsNumber="";
		String txtAH="";
		String drpZXFY="";
		String txtStartLARQ="";
		String txtEndLARQ="";
		
		  if(type.equals("姓名")){
			  txtReallyName=value;
		}else if(type.equals("证件号码")){
	    	 txtCredentialsNumber=value;
	     }else if(type.equals("案号")){
	    	 txtAH=value;
	    }else if(type.equals("执行法院")){
	    	 drpZXFY=value;
	     }else if(type.equals("立案开始日期")){
	    	 txtStartLARQ=value;
	     }else if(type.equals("立案结束日期")){
	    	 txtEndLARQ=value;
	     }
		//执行案件信息
		  String  url =  "http://www.zjsfgkw.cn/Execute/ExecuteCaseSearch?dSR="+URLDecoder.decode(txtReallyName,"utf-8")+"&aH_BH="+URLDecoder.decode(txtAH,"utf-8")+"&aH_NH="+URLDecoder.decode("2016","utf-8");   
		  Result<List<ExecuteCaseSearchJson>> result = zjsfgkwExecuteCaseSearchService.searchExecuteCase(url,true,""); 
		  
		  if(result.getError()!=null && result.getError().getErrorMsg() !=null && result.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
			  resultResult.setData(listzjsfgkwJson);
			  ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				resultResult.setHtml(result.getHtml());
				resultResult.setData(null);
				resultResult.setError(errorMsg);
			  resultJson = gson.toJson(resultResult);
			 return resultJson;
		  }
		  
		  
		  if(result.getData()!=null && result.getData().size()!=0){
			  for(ExecuteCaseSearchJson creditJson:result.getData()){
				  ZjsfgkwJson zjsfgkwJson=new ZjsfgkwJson();
				  zjsfgkwJson.setCaseNo(creditJson.getCaseNo());
				  zjsfgkwJson.setCourt(creditJson.getCourt());
				  zjsfgkwJson.setCaseState(creditJson.getCaseState());
				  zjsfgkwJson.setCaseDate(creditJson.getCaseDate());
				  zjsfgkwJson.setName(creditJson.getPrincipal());
				  zjsfgkwJson.setPropertyType("执行案件信息");
				  listzjsfgkwJson.add(zjsfgkwJson);
			  }
		  }
		  
		  //曝光台
		  String  url2 =   "http://www.zjsfgkw.cn/Execute/CreditPersonal?txtReallyName="
				  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
				  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
				  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8"); 
		  Result<List<CreditJson>> result2 = zjsfgkwCreditService.searchCredit(url2,true,"");
		  
		  if(result2.getError()!=null && result2.getError().getErrorMsg() !=null && result2.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
			  resultResult.setData(listzjsfgkwJson);
			  ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				resultResult.setHtml(result2.getHtml());
				resultResult.setData(null);
				resultResult.setError(errorMsg);
			  resultJson = gson.toJson(resultResult);
			 return resultJson;
		  }
		  
		  if(result2.getData()!=null && result2.getData().size()!=0){
			  for(CreditJson creditJson:result2.getData()){
				  ZjsfgkwJson zjsfgkwJson=creditJsonToZjsfgkwJson(creditJson);
				  zjsfgkwJson.setPropertyType("个人未履行生效裁判");
				  listzjsfgkwJson.add(zjsfgkwJson);
			  }
		  }
		  
		  
		  String url3 =  "http://www.zjsfgkw.cn/Execute/CreditPersonal?txtComReallyName="
				  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtComCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
						  +"&txtComAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpComZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
						  +"&txtComStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtComEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8"); 
		  Result<List<CreditJson>> result3 =zjsfgkwCreditCompanyService.searchCreditCompany(url3,true,"");
		  
		  if(result3.getError()!=null && result3.getError().getErrorMsg() !=null && result3.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
			  resultResult.setData(listzjsfgkwJson);
			  ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				resultResult.setHtml(result3.getHtml());
				resultResult.setData(null);
				resultResult.setError(errorMsg);
			  resultJson = gson.toJson(resultResult);
			 return resultJson;
		  }
		  
		  if(result3.getData()!=null && result3.getData().size()!=0){
			  for(CreditJson creditJson:result3.getData()){
				  ZjsfgkwJson zjsfgkwJson=creditJsonToZjsfgkwJson(creditJson);
				  zjsfgkwJson.setPropertyType("单位未履行生效裁判");
				  listzjsfgkwJson.add(zjsfgkwJson);
			  }
		  }
		  
		  //执行惩戒
		  String  url4 =  "http://www.zjsfgkw.cn/Execute/LimitInfo?txtReallyName="
				  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
						  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
						  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8"); 
		  Result<List<CreditJson>> result4 = zjsfgkwLimitHighConsumService.searchLimitHighConsum(url4,true,"");
		  
		  if(result4.getError()!=null && result4.getError().getErrorMsg() !=null && result4.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
			  resultResult.setData(listzjsfgkwJson);
			  ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				resultResult.setHtml(result4.getHtml());
				resultResult.setData(null);
				resultResult.setError(errorMsg);
			  resultJson = gson.toJson(resultResult);
			 return resultJson;
		  }
		  
		  if(result4.getData()!=null && result4.getData().size()!=0){
			  for(CreditJson creditJson:result4.getData()){
				  ZjsfgkwJson zjsfgkwJson=creditJsonToZjsfgkwJson(creditJson);
				  zjsfgkwJson.setPropertyType("限制高消费");
				  listzjsfgkwJson.add(zjsfgkwJson);
			  }
		  }
		  
		  String url5 =  "http://www.zjsfgkw.cn/Execute/LimitInfo?txtReallyName="
				  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
						  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
						  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8"); 
		  Result<List<CreditJson>> result5 = zjsfgkwLimitExitService.searchLimitExit(url5,true,"");
		  
		  if(result5.getError()!=null && result5.getError().getErrorMsg() !=null && result5.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
			  resultResult.setData(listzjsfgkwJson);
			  ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				resultResult.setHtml(result5.getHtml());
				resultResult.setData(null);
				resultResult.setError(errorMsg);
			  resultJson = gson.toJson(resultResult);
			 return resultJson;
		  }
		  
		  if(result5.getData()!=null && result5.getData().size()!=0){
			  for(CreditJson creditJson:result5.getData()){
				  ZjsfgkwJson zjsfgkwJson=creditJsonToZjsfgkwJson(creditJson);
				  zjsfgkwJson.setPropertyType("限制出境");
				  listzjsfgkwJson.add(zjsfgkwJson);
			  }
		  }
		  
		  String  url6 =  "http://www.zjsfgkw.cn/Execute/LimitInfo?txtReallyName="
				  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
						  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
						  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8");
		  Result<List<CreditJson>> result6 = zjsfgkwLimitBiddingService.searchLimitBidding(url6,true,"");
		  
		  if(result6.getError()!=null && result6.getError().getErrorMsg() !=null && result6.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
			  resultResult.setData(listzjsfgkwJson);
			  ErrorMsg errorMsg = new ErrorMsg();
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				resultResult.setHtml(result6.getHtml());
				resultResult.setData(null);
				resultResult.setError(errorMsg);
			  resultJson = gson.toJson(resultResult);
			 return resultJson;
		  }
		  
		  if(result6.getData()!=null && result6.getData().size()!=0){
			  for(CreditJson creditJson:result6.getData()){
				  ZjsfgkwJson zjsfgkwJson=creditJsonToZjsfgkwJson(creditJson);
				  zjsfgkwJson.setPropertyType("限制招投标");
				  listzjsfgkwJson.add(zjsfgkwJson);
			  }
		  }
	
		  resultResult.setData(listzjsfgkwJson);
		  resultJson = gson.toJson(resultResult);
		return resultJson;
	}
	
	
	public  ZjsfgkwJson creditJsonToZjsfgkwJson(CreditJson creditJson){
		ZjsfgkwJson zjsfgkwJson=new ZjsfgkwJson();
		if(creditJson!=null){
		zjsfgkwJson.setName(creditJson.getName());
		zjsfgkwJson.setIdNo(creditJson.getIdNo());
		zjsfgkwJson.setAddress(creditJson.getAddress());
		zjsfgkwJson.setEnforceBasis(creditJson.getEnforceBasis());
		zjsfgkwJson.setCaseNo(creditJson.getCaseNo());
		zjsfgkwJson.setExecutReason(creditJson.getExecutReason());
		zjsfgkwJson.setCourt(creditJson.getCourt());
		zjsfgkwJson.setAmountNotExecuted(creditJson.getAmountNotExecuted());
		zjsfgkwJson.setCaseDate(creditJson.getCaseDate());
		zjsfgkwJson.setTargetAmount(creditJson.getTargetAmount());
		zjsfgkwJson.setCreditDate(creditJson.getCreditDate());
		}
		return zjsfgkwJson;
	}
}

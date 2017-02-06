package com.crawlermanage.service.zjsfgkw;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawler.zjsfgkw.domain.json.ExecuteCaseSearchJson;
import com.crawler.zjsfgkw.domain.json.ZjsfgkwJson;
import com.crawlermanage.service.transfer.ZjsfgkwPoVoTransfer;
import com.module.dao.entity.zjsfgkw.Zjsfgkw;
import com.module.dao.entity.zjsfgkw.ZjsfgkwKeyword;
import com.module.dao.repository.zjsfgkw.ZjsfgkwKeywordRepository;
import com.module.dao.repository.zjsfgkw.ZjsfgkwRepository;

@Service
public class ZjsfgkwTaskService {
	@Autowired
    private ZjsfgkwRepository zjsfgkwRepository;
	
	@Autowired
    private ZjsfgkwKeywordRepository zjsfgkwKeywordRepository;
	
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
	
	private static final boolean IS_DEBUG = true;
	private static final Logger Log = Logger.getLogger(ZjsfgkwTaskService.class);
	
	@Transactional
	public Map<String, Object> excOneceTask() throws IOException, JSONException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ZjsfgkwKeyword zjsfgkwKeyword=zjsfgkwKeywordRepository.findTopByStateInAndNumLessThanOrderByPriorityDesc(StatusCodeDef.NEED_QUERY_STATES,StatusCodeDef.UPPER_LIMIT_NUM);
		List<ZjsfgkwJson> listzjsfgkwJson=new ArrayList<ZjsfgkwJson>();
		Result<List<ZjsfgkwJson>> resultResult = new Result<List<ZjsfgkwJson>>();
		Boolean errorFlag=false;
		if(zjsfgkwKeyword!=null){
			try {	
			String valeuString="";
			Long idString=null;
			String type=zjsfgkwKeyword.getType();
	    	if (type!=null && !type.isEmpty()) {
			
			String value=zjsfgkwKeyword.getValue();
			valeuString=zjsfgkwKeyword.getValue();
			idString=zjsfgkwKeyword.getId();
			
			if(type.equals("1-1") || type.equals("1-2")){//执行案件信息
			String url ="";
			if(type.equals("1-2")){//根据当事人查询执行案件信息
				url =  "http://www.zjsfgkw.cn/Execute/ExecuteCaseSearch?dSR="+URLDecoder.decode(value,"utf-8")+"&aH_BH="+URLDecoder.decode("","utf-8")+"&aH_NH="+URLDecoder.decode("2016","utf-8"); 
			}else if(type.equals("1-1")){//根据案号查询执行案件信息
				url =  "http://www.zjsfgkw.cn/Execute/ExecuteCaseSearch?dSR="+URLDecoder.decode("","utf-8")+"&aH_BH="+URLDecoder.decode(value,"utf-8")+"&aH_NH="+URLDecoder.decode("2016","utf-8"); 
			}
			
			Result<List<ExecuteCaseSearchJson>> result = zjsfgkwExecuteCaseSearchService.searchExecuteCase(url,true,"");  
			
			  if(result.getError()!=null && result.getError().getErrorMsg() !=null && result.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
				  errorFlag=true;
				  resultResult.setHtml(result.getHtml());
				  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.FREQUENCY_LIMITED));
				  
					//改变执行次数属性
					Integer num = zjsfgkwKeyword.getNum() == null ? 1 : (zjsfgkwKeyword.getNum() + 1);
					zjsfgkwKeyword.setNum(num);
					Integer totalNum = zjsfgkwKeyword.getTotalNum() == null ? 1 : (zjsfgkwKeyword.getTotalNum() + 1);
					zjsfgkwKeyword.setTotalNum(totalNum);
					
					zjsfgkwKeyword=zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
			  }else{
				  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.DOING_STATE));
					zjsfgkwKeyword=zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
					resultResult.setHtml(result.getHtml());
				  if(result.getData()!=null && result.getData().size()!=0){
						//改变状态属性
						zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
						Set<Zjsfgkw> zjsfgkws=new HashSet<Zjsfgkw>();
						for(int i=0;i<result.getData().size();i++){
							Zjsfgkw zjsfgkw=ZjsfgkwPoVoTransfer.voToExecuteCaseSearchPo(result.getData().get(i));
							zjsfgkw.setZjsfgkwKeyword(zjsfgkwKeyword);
							zjsfgkws.add(zjsfgkw);
							
							 ZjsfgkwJson zjsfgkwJson=new ZjsfgkwJson();
							  zjsfgkwJson.setCaseNo(zjsfgkw.getCaseNo());
							  zjsfgkwJson.setCourt(zjsfgkw.getCourt());
							  zjsfgkwJson.setCaseState(zjsfgkw.getCaseState());
							  zjsfgkwJson.setCaseDate(zjsfgkw.getCaseDate());
							  zjsfgkwJson.setName(zjsfgkw.getName());
//							  zjsfgkwJson.setPropertyType("执行案件信息");
							  listzjsfgkwJson.add(zjsfgkwJson);
						}
						zjsfgkwKeyword.setZjsfgkws(zjsfgkws);
					}else{
						  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.NO_DATA_FOUND));
					}
					
					//改变执行次数属性
					Integer num = zjsfgkwKeyword.getNum() == null ? 1 : (zjsfgkwKeyword.getNum() + 1);
					zjsfgkwKeyword.setNum(num);
					Integer totalNum = zjsfgkwKeyword.getTotalNum() == null ? 1 : (zjsfgkwKeyword.getTotalNum() + 1);
					zjsfgkwKeyword.setTotalNum(totalNum);
					
					zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
			  }
			
			}
			if(type.equals("2-1-1") || type.equals("2-1-2") || type.equals("2-1-3") || type.equals("2-1-4")|| type.equals("2-1-5")|| type.equals("2-1-6") || 
					type.equals("2-2-1") || type.equals("2-2-2") || type.equals("2-2-3") || type.equals("2-2-4")|| type.equals("2-2-5")|| type.equals("2-2-6")){
				String txtReallyName="";
				String txtCredentialsNumber="";
				String txtAH="";
				String drpZXFY="";
				String txtStartLARQ="";
				String txtEndLARQ="";
				
				String txtComReallyName="";
				String txtComCredentialsNumber="";
				String txtComAH="";
				String drpComZXFY="";
				String txtComStartLARQ="";
				String txtComEndLARQ="";
				if(type.equals("2-1-1") || type.equals("2-2-1")){
					txtReallyName=value;
					txtComReallyName=value;
				}else if(type.equals("2-1-2") || type.equals("2-2-2")){
					txtCredentialsNumber=value;
					txtComCredentialsNumber=value;
				}else if(type.equals("2-1-3") || type.equals("2-2-3")){
					txtAH=value;
					txtComAH=value;
				}else if(type.equals("2-1-4") || type.equals("2-2-4")){
					drpZXFY=value;
					drpComZXFY=value;
				}else if(type.equals("2-1-5") || type.equals("2-2-4")){
					txtStartLARQ=value;
					txtComStartLARQ=value;
				}else if(type.equals("2-1-6") || type.equals("2-2-6")){
					txtEndLARQ=value;
					txtComEndLARQ=value;
				}
				
				
				String url ="";
				Result<List<CreditJson>> result = new Result<List<CreditJson>>();
				if(type.equals("2-1-1") || type.equals("2-1-2") || type.equals("2-1-3") || type.equals("2-1-4")|| type.equals("2-1-5")|| type.equals("2-1-6")){//个人未履行生效裁判信息
					url =   "http://www.zjsfgkw.cn/Execute/CreditPersonal?txtReallyName="
							  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
							  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
							  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8");
					result = zjsfgkwCreditService.searchCredit(url,true,"");
				}else if(type.equals("2-2-1") || type.equals("2-2-2") || type.equals("2-2-3") || type.equals("2-2-4")|| type.equals("2-2-5")|| type.equals("2-2-6")){//根据案号查询执行案件信息
					url =  "http://www.zjsfgkw.cn/Execute/CreditPersonal?txtComReallyName="
							  +URLDecoder.decode(txtComReallyName,"utf-8")+"&txtComCredentialsNumber="+URLDecoder.decode(txtComCredentialsNumber,"utf-8")
									  +"&txtComAH="+URLDecoder.decode(txtComAH,"utf-8")+"&drpComZXFY="+URLDecoder.decode(drpComZXFY,"utf-8")
									  +"&txtComStartLARQ="+URLDecoder.decode(txtComStartLARQ,"utf-8")+"&txtComEndLARQ="+URLDecoder.decode(txtComEndLARQ,"utf-8"); 
					result = zjsfgkwCreditCompanyService.searchCreditCompany(url,true,"");
				}
				
				  if(result.getError()!=null && result.getError().getErrorMsg() !=null && result.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
					  errorFlag=true;
					  resultResult.setHtml(result.getHtml());
					//改变执行次数属性
						Integer num = zjsfgkwKeyword.getNum() == null ? 1 : (zjsfgkwKeyword.getNum() + 1);
						zjsfgkwKeyword.setNum(num);
						Integer totalNum = zjsfgkwKeyword.getTotalNum() == null ? 1 : (zjsfgkwKeyword.getTotalNum() + 1);
						zjsfgkwKeyword.setTotalNum(totalNum);
						
					  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.FREQUENCY_LIMITED));
						zjsfgkwKeyword=zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
				  }else{
					  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.DOING_STATE));
						zjsfgkwKeyword=zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
						resultResult.setHtml(result.getHtml());
					  if(result.getData()!=null && result.getData().size()!=0){
							//改变状态属性
							zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
							Set<Zjsfgkw> zjsfgkws=new HashSet<Zjsfgkw>();
							for(int i=0;i<result.getData().size();i++){
								Zjsfgkw zjsfgkw=ZjsfgkwPoVoTransfer.voToCreditSearchPo(result.getData().get(i));
								zjsfgkw.setZjsfgkwKeyword(zjsfgkwKeyword);
								zjsfgkws.add(zjsfgkw);
								
								  ZjsfgkwJson zjsfgkwJson=creditJsonToZjsfgkwJson(zjsfgkw);
//								  zjsfgkwJson.setPropertyType("个人未履行生效裁判");
								  listzjsfgkwJson.add(zjsfgkwJson);
							}
							zjsfgkwKeyword.setZjsfgkws(zjsfgkws);
						}else{
							  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.NO_DATA_FOUND));
						}
					 
					//改变执行次数属性
						Integer num = zjsfgkwKeyword.getNum() == null ? 1 : (zjsfgkwKeyword.getNum() + 1);
						zjsfgkwKeyword.setNum(num);
						Integer totalNum = zjsfgkwKeyword.getTotalNum() == null ? 1 : (zjsfgkwKeyword.getTotalNum() + 1);
						zjsfgkwKeyword.setTotalNum(totalNum);
						
						zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
				  }
			}

			
			if(type.equals("3-1-1") || type.equals("3-1-2") || type.equals("3-1-3") || type.equals("3-1-4")|| type.equals("3-1-5")|| type.equals("3-1-6") || 
					type.equals("3-2-1") || type.equals("3-2-2") || type.equals("3-2-3") || type.equals("3-2-4")|| type.equals("3-2-5")|| type.equals("3-2-6") ||
					type.equals("3-3-1") || type.equals("3-3-2") || type.equals("3-3-3") || type.equals("3-3-4")|| type.equals("3-3-5")|| type.equals("3-3-6")){
				String txtReallyName="";
				String txtCredentialsNumber="";
				String txtAH="";
				String drpZXFY="";
				String txtStartLARQ="";
				String txtEndLARQ="";
			
				if(type.equals("3-1-1") || type.equals("3-2-1") || type.equals("3-3-1")){
					txtReallyName=value;
				}else if(type.equals("3-1-2") || type.equals("3-2-2") || type.equals("3-3-2")){
					txtCredentialsNumber=value;
				}else if(type.equals("3-1-3") || type.equals("3-2-3") || type.equals("3-3-3")){
					txtAH=value;
				}else if(type.equals("3-1-4") || type.equals("3-2-4") || type.equals("3-3-4")){
					drpZXFY=value;
				}else if(type.equals("3-1-5") || type.equals("3-2-4") || type.equals("3-3-5")){
					txtStartLARQ=value;
				}else if(type.equals("3-1-6") || type.equals("3-2-6") || type.equals("3-3-6")){
					txtEndLARQ=value;
				}
				
				String url ="";
				Result<List<CreditJson>> result = new Result<List<CreditJson>>();
				if(type.equals("3-1-1") || type.equals("3-1-2") || type.equals("3-1-3") || type.equals("3-1-4")|| type.equals("3-1-5")|| type.equals("3-1-6")){//执行惩戒-限制高消费
					url =  "http://www.zjsfgkw.cn/Execute/LimitInfo?txtReallyName="
							  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
									  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
									  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8"); 
					result = zjsfgkwLimitHighConsumService.searchLimitHighConsum(url,true,"");
				}else if(type.equals("3-2-1") || type.equals("3-2-2") || type.equals("3-2-3") || type.equals("3-2-4")|| type.equals("3-2-5")|| type.equals("3-2-6")){//执行惩戒-限制出境
					url =  "http://www.zjsfgkw.cn/Execute/LimitInfo?txtReallyName="
							  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
									  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
									  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8"); 
					result = zjsfgkwLimitExitService.searchLimitExit(url,true,"");
					
				}else if(type.equals("3-3-1") || type.equals("3-3-2") || type.equals("3-3-3") || type.equals("3-3-4")|| type.equals("3-3-5")|| type.equals("3-3-6")){//执行惩戒-限制招投标
					url =  "http://www.zjsfgkw.cn/Execute/LimitInfo?txtReallyName="
							  +URLDecoder.decode(txtReallyName,"utf-8")+"&txtCredentialsNumber="+URLDecoder.decode(txtCredentialsNumber,"utf-8")
									  +"&txtAH="+URLDecoder.decode(txtAH,"utf-8")+"&drpZXFY="+URLDecoder.decode(drpZXFY,"utf-8")
									  +"&txtStartLARQ="+URLDecoder.decode(txtStartLARQ,"utf-8")+"&txtEndLARQ="+URLDecoder.decode(txtEndLARQ,"utf-8"); 
					result = zjsfgkwLimitBiddingService.searchLimitBidding(url,true,"");
				}
				
				 if(result.getError()!=null && result.getError().getErrorMsg() !=null && result.getError().getErrorMsg().equals("可能访问过于频繁或非正常访问")){
					  errorFlag=true;
					  resultResult.setHtml(result.getHtml());
					//改变执行次数属性
						Integer num = zjsfgkwKeyword.getNum() == null ? 1 : (zjsfgkwKeyword.getNum() + 1);
						zjsfgkwKeyword.setNum(num);
						Integer totalNum = zjsfgkwKeyword.getTotalNum() == null ? 1 : (zjsfgkwKeyword.getTotalNum() + 1);
						zjsfgkwKeyword.setTotalNum(totalNum);
						
					  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.FREQUENCY_LIMITED));
						zjsfgkwKeyword=zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
				  }else{
					  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.DOING_STATE));
						zjsfgkwKeyword=zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
						resultResult.setHtml(result.getHtml());
					  if(result.getData()!=null && result.getData().size()!=0){
							//改变状态属性
							zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
							Set<Zjsfgkw> zjsfgkws=new HashSet<Zjsfgkw>();
							for(int i=0;i<result.getData().size();i++){
								Zjsfgkw zjsfgkw=ZjsfgkwPoVoTransfer.voToCreditSearchPo(result.getData().get(i));
								zjsfgkw.setZjsfgkwKeyword(zjsfgkwKeyword);
								zjsfgkws.add(zjsfgkw);
								
								 ZjsfgkwJson zjsfgkwJson=creditJsonToZjsfgkwJson(zjsfgkw);
								  zjsfgkwJson.setPropertyType("限制招投标");
								  listzjsfgkwJson.add(zjsfgkwJson);
							}
							zjsfgkwKeyword.setZjsfgkws(zjsfgkws);
						}else{
							  zjsfgkwKeyword.setState(Integer.parseInt(StatusCodeDef.NO_DATA_FOUND));
						}
					 
					//改变执行次数属性
						Integer num = zjsfgkwKeyword.getNum() == null ? 1 : (zjsfgkwKeyword.getNum() + 1);
						zjsfgkwKeyword.setNum(num);
						Integer totalNum = zjsfgkwKeyword.getTotalNum() == null ? 1 : (zjsfgkwKeyword.getTotalNum() + 1);
						zjsfgkwKeyword.setTotalNum(totalNum);
						
						zjsfgkwKeywordRepository.save(zjsfgkwKeyword);
				  }
			
			     }
		       }
		 
		
		//返回输入参数
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("keyword", valeuString);
				resultMap.put("arguments", arguments);
				resultMap.put("keyword", valeuString);
				resultMap.put("cId", idString);	
				if(errorFlag==true){
					ErrorMsg errorMsg = new ErrorMsg();
					errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
					errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
					errorMsg.setErrorName("可能访问过于频繁或非正常访问");
					resultResult.setError(errorMsg);
				    resultMap.put("APIresult", resultResult);
				}else{
				 resultResult.setData(listzjsfgkwJson);
				 resultMap.put("APIresult", resultResult);
				}
		
		      }catch (Exception e) {
			Map<String, Object> arguments = new HashMap<String, Object>();
			arguments.put("keyword", "");
			resultMap.put("arguments", arguments);
			resultMap.put("keyword", "");
			resultMap.put("cId", "");	
			
			StringWriter sw = new StringWriter();   
			PrintWriter pw = new PrintWriter(sw, true);   
			//Gson gson = new Gson();
			sw.append("-------------------"); 
			sw.append(""); 
			
			e.printStackTrace(pw);   
			pw.flush();   
			sw.flush(); 
			
			resultMap.put("Exception", sw.toString());
		   } 
		}
		
		return resultMap;
	}
	
	public  ZjsfgkwJson creditJsonToZjsfgkwJson(Zjsfgkw creditJson){
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

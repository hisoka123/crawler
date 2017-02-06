package com.crawlermanage.controller.modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.zhengxin.domain.json.ZhengxinJson;
import com.crawler.zjsfgkw.domain.json.ZjsfgkwJson;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwExecuteCaseSearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.zjsfgkw.Zjsfgkw;
import com.module.dao.entity.zjsfgkw.ZjsfgkwKeyword;

@Controller
@RequestMapping("/modules/zjsfgkw")
public class ZjsfgkwController {
	
	@Autowired
	private Env env;

	@Autowired
	private ZjsfgkwExecuteCaseSearchService zjsfgkwExecuteCaseSearchService;
	
	@RequestMapping("/zjsfgkwExecuteCaseSearch")
	  public String toExecuteCaseSearch(Model model){
		     model.addAttribute("env",env.getEnv());
		     return "/modules/zjsfgkw/executeCaseSearch";
	  }
	
	
	 @RequestMapping(value="/zjsfgkwDetailByInterface",method=RequestMethod.POST)
     public String toRenFaWangDetailByInterface(@RequestParam("renfawangDetail") String renfawangDetail,Model model){
 		
 		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
 		   List<ZjsfgkwJson> pcs=gson.fromJson(renfawangDetail,new TypeToken<ArrayList<ZjsfgkwJson>>(){}.getType());
 		   model.addAttribute("peopleCourts",pcs);
 		  
 		   return "/modules/zjsfgkw/zjsfgkwDetail";
 	   }
	 
	 @RequestMapping(value="/zjsfgkwDetailByInterface2",method=RequestMethod.POST)
     public String toRenFaWangDetailByInterface2(@RequestParam("type") String type,@RequestParam("value") String value,Model model){
 		
		 List<ZjsfgkwKeyword> pcList=zjsfgkwExecuteCaseSearchService.checkIsExistBYTypeAndName(type, value);
		 List<Long> idList=new ArrayList<Long>();
		 List<ZjsfgkwJson> listzjsfgkwJson=new ArrayList<ZjsfgkwJson>();
		 if(pcList.size()!=0){
		 String typeString=pcList.get(0).getPropertyType(); 
         for(int i=0;i<pcList.size();i++){
       	      idList.add(pcList.get(i).getId());
         }
         List<Zjsfgkw>	zjsfgkwList = zjsfgkwExecuteCaseSearchService.getDetailByCOI(idList);
			 for(Zjsfgkw zjsfgkw:zjsfgkwList){
				  ZjsfgkwJson zjsfgkwJson=creditJsonToZjsfgkwJson(zjsfgkw);
				  zjsfgkwJson.setPropertyType(typeString);
				  listzjsfgkwJson.add(zjsfgkwJson);
			 }
			 model.addAttribute("peopleCourts",listzjsfgkwJson);
		 }else{
			 model.addAttribute("peopleCourts",null);
		 }
 		   return "/modules/zjsfgkw/zjsfgkwDetail";
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
		
		 //调试时用
	   	@RequestMapping(value="/zjsfgkwJson",method=RequestMethod.POST)
	       public String toRenFaWangJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
	   		   
	   		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
	   		   
	   		   Result<List<ZjsfgkwJson>> result=new Result<List<ZjsfgkwJson>>();
	   		   
	   		   if(error.equals("eNull")){
	   			   
	   			   result.setData((List<ZjsfgkwJson>)gson.fromJson(data,new TypeToken<List<ZjsfgkwJson>>(){}.getType()));
	   			   
	   		   }else if(data.equals("dNull")){
	   			    
	   			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
	   		   }
	   		  
	   		   model.addAttribute("zjsfgkwDetail",gson.toJson(result));
	   		   
	   		   return "/modules/zjsfgkw/zjsfgkwJson";
	   	}
}

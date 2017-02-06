package com.crawlermanage.controller.modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawlermanage.dao.result.TimeTaskSearchResult;
import com.crawlermanage.service.cnca.CncaDBService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.dao.entity.cnca.Certificate;
import com.module.dao.entity.cnca.CertificateCompany;

@Controller
@RequestMapping("/modules/cncaController")
public class CncaController {
	@Autowired
	CncaDBService cncaDBService;

    @Autowired
	private Env env;
    
    @RequestMapping("")
    public String toCncaSearch1(Model model){
 	      model.addAttribute("env",env.getEnv());
 	      return "/modules/cnca/cncaSearch";
    }
 
    @RequestMapping("/cncaSearch")
    public String toCncaSearch2(Model model){
 	      model.addAttribute("env",env.getEnv());
 	      return "/modules/cnca/cncaSearch";
    }

    @RequestMapping(value="/cncaDetailByInterface",method=RequestMethod.POST)
    public String toCncaDetailByInterface(@RequestParam("cncaDetail") String cncaDetail,Model model){
		
		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
		   List<Certificate> pcs=gson.fromJson(cncaDetail,new TypeToken<ArrayList<Certificate>>(){}.getType());
		   model.addAttribute("cncaDatas",pcs);
		  
		   return "/modules/cnca/cncaDetail";
	   }
    
    @RequestMapping(value="/cncaDetailByInterface2",method=RequestMethod.POST)
    public String toCncaDetailByInterface2(@RequestParam("cncaDetail") String cncaDetail,Model model){
		
		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
		   List<com.crawler.cnca.domain.json.Certificate> pcs=gson.fromJson(cncaDetail,new TypeToken<ArrayList<com.crawler.cnca.domain.json.Certificate>>(){}.getType());
		   model.addAttribute("cncaDatas",pcs);
		  
		   return "/modules/cnca/cncaDetail2";
	   }

    //调试时用
  	@RequestMapping(value="/cncaJson",method=RequestMethod.POST)
    public String toCncaJson(@RequestParam("data") String data,@RequestParam("error") String error,Model model){
  		   
  		   Gson gson=new GsonBuilder().setPrettyPrinting().create();
  		   
  		   Result<List<com.crawler.cnca.domain.json.Certificate>> result=new Result<List<com.crawler.cnca.domain.json.Certificate>>();
  		   
  		   if(error.equals("eNull")){
  			   
  			   result.setData((List<com.crawler.cnca.domain.json.Certificate>)gson.fromJson(data,new TypeToken<List<com.crawler.cnca.domain.json.Certificate>>(){}.getType()));
  			   
  		   }else if(data.equals("dNull")){
  			    
  			   result.setError((ErrorMsg)gson.fromJson(error,new TypeToken<ErrorMsg>(){}.getType()));
  		   }
  		  
  		   model.addAttribute("cncaDetail",gson.toJson(result));
  		   
  		   return "/modules/cnca/cncaJson";
  	}
  	
	//核查名称、号码
	@RequestMapping(value="/checkIsExist",method=RequestMethod.POST)
	public @ResponseBody TimeTaskSearchResult<List<Certificate>>  checkIsExist(@RequestParam("content") String content){
   
		TimeTaskSearchResult<List<Certificate>> result=new TimeTaskSearchResult<List<Certificate>>();
       
		List<CertificateCompany> coiList = cncaDBService.checkIsExistBYName(content);
	          
		if(coiList.size()>0){
	   
			result.setExistCode(1);
  
			List<Long> idList=new ArrayList<Long>();
			  
			for(int i=0;i<coiList.size();i++){
				idList.add(coiList.get(i).getId());
			}
	    
			result.setTimetask_id(coiList.get(0).getId());
	          
			List<Certificate> pcList = cncaDBService.getDetailByCompany(idList);
	   
			if(pcList.size()>0){
				result.setState(1);
				result.setTtSearchResult(pcList);
			}else{
				if(coiList.get(0).getState()==7){
					result.setState(coiList.get(0).getState());
				}else{
					result.setState(0);
				}
			}
		}else{
	        result.setExistCode(0);
	        result.setState(0);
		}
	      
		return result;
   }
}

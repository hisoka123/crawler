package com.crawlermanage.service.dailianmeng;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.dailianmeng.CompanyOrIdentity;
import com.module.dao.entity.dailianmeng.LoanUnion;
import com.module.dao.repository.dailianmeng.CompanyOrIdentityRepository;
import com.module.dao.repository.dailianmeng.LoanUnionRepository;

@Component("daiLianMengDBService")
public class DaiLianMengDBService {
	
	
	    @Autowired
	    CompanyOrIdentityRepository companyOrIDRepository;
	    
	    @Autowired
	    LoanUnionRepository loanUinonRepository;
	
	   //检查是定时任务中是否存在该任务
	   public List<CompanyOrIdentity>  checkIsExistBYName(String queryType,String content){
		   
		     return companyOrIDRepository.findByTypeAndKeyword(queryType, content);
	   }
	   
	   //通过id查询定时任务
	   public CompanyOrIdentity  getTimeTask(long id){
		      return companyOrIDRepository.findOne(id);
	   }
	   
	   //查询详情
	   public List<LoanUnion> getDetailByCOI(List<Long> idList){
		      
		      return loanUinonRepository.findByCOI(idList);
	   }
	   
	   //将新任务保存到定时任务表中
	   public CompanyOrIdentity saveTimeTask(CompanyOrIdentity companyOrID){
		      return companyOrIDRepository.save(companyOrID);
	   }

	   
}

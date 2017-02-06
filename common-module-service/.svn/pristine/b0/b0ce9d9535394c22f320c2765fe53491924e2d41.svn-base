package com.crawlermanage.service.sxjlcxpt;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.crqp.CreditrRecordQueryPlatform;
import com.module.dao.entity.crqp.CreditrRecordQueryPlatformCompany;
import com.module.dao.repository.crqp.CreditrRecordQueryPlatformCompanyRepository;
import com.module.dao.repository.crqp.CreditrRecordQueryPlatformRepository;

@Component("sxjlcxptDBService")
public class SxjlcxptDBService {
	
	
	    @Autowired
	    CreditrRecordQueryPlatformCompanyRepository creditrRecordQueryPlatformCompanyRepository;
	    
	    @Autowired
	    CreditrRecordQueryPlatformRepository creditrRecordQueryPlatformRepository;
	
	   //检查是定时任务中是否存在该任务
	   public List<CreditrRecordQueryPlatformCompany>  checkIsExistBYName(String content){
		   
		     return creditrRecordQueryPlatformCompanyRepository.findByKeyword(content);
	   }
	   
	   //通过id查询定时任务
	   public CreditrRecordQueryPlatformCompany  getTimeTask(long id){
		      return creditrRecordQueryPlatformCompanyRepository.findOne(id);
	   }
	   
	   //查询详情
	   public List<CreditrRecordQueryPlatform> getDetailByCOI(List<Long> idList){
		      
		      return creditrRecordQueryPlatformRepository.findByCOI(idList);
	   }
	   
	   //将新任务保存到定时任务表中
	   public CreditrRecordQueryPlatformCompany saveTimeTask(CreditrRecordQueryPlatformCompany companyOrID){
		      return creditrRecordQueryPlatformCompanyRepository.save(companyOrID);
	   }

	   
}

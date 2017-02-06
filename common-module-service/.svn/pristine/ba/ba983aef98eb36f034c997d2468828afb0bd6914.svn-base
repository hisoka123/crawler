package com.crawlermanage.service.iecms;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.iecms.Iecms;
import com.module.dao.entity.iecms.IecmsCompany;
import com.module.dao.entity.renfawang.CompanyOrID;
import com.module.dao.repository.iecms.IecmsCompanyRepository;
import com.module.dao.repository.iecms.IecmsRepository;

@Component("iecmsDBService")
public class IecmsDBService {
	
	
	    @Autowired
	    IecmsCompanyRepository iecmsCompanyRepository;
	    
	    @Autowired
	    IecmsRepository iecmsRepository;
	
	   //检查是定时任务中是否存在该任务
	   public List<IecmsCompany>  checkIsExistBYName(String queryType,String content){
		   
		     return iecmsCompanyRepository.findByTypeAndKeyword(queryType, content);
	   }
	   
	   //通过id查询定时任务
	   public IecmsCompany  getTimeTask(long id){
		      return iecmsCompanyRepository.findOne(id);
	   }
	   
	   //查询详情
	   public List<Iecms> getDetailByCOI(List<Long> idList){
		      
		      return iecmsRepository.findByCOI(idList);
	   }
	   
	   //将新任务保存到定时任务表中
	   public IecmsCompany saveTimeTask(IecmsCompany companyOrID){
		      return iecmsCompanyRepository.save(companyOrID);
	   }

	   
}

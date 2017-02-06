package com.crawlermanage.service.renfawang;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.renfawang.CompanyOrID;
import com.module.dao.entity.renfawang.PeopleCourt;
import com.module.dao.repository.renfawang.CompanyOrIDRepository;
import com.module.dao.repository.renfawang.PeopleCourtRepository;

@Component("renFaWangDBService")
public class RenFaWangDBService {
	
	
	    @Autowired
	    CompanyOrIDRepository companyOrIDRepository;
	    
	    @Autowired
	    PeopleCourtRepository peopleCourtRepository;
	
	   //检查是定时任务中是否存在该任务
	   public List<CompanyOrID>  checkIsExistBYName(String queryType,String content){
		   
		     return companyOrIDRepository.findByTypeAndKeyword(queryType, content);
	   }
	   
	   //通过id查询定时任务
	   public CompanyOrID  getTimeTask(long id){
		      return companyOrIDRepository.findOne(id);
	   }
	   
	   //查询详情
	   public List<PeopleCourt> getDetailByCOI(List<Long> idList){
		      
		      return peopleCourtRepository.findByCOI(idList);
	   }
	   
	   //将新任务保存到定时任务表中
	   public CompanyOrID saveTimeTask(CompanyOrID companyOrID){
		      return companyOrIDRepository.save(companyOrID);
	   }

	   
}

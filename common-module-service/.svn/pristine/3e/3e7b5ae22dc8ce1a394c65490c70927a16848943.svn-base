package com.crawlermanage.service.sipo;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.sipo.Sipo;
import com.module.dao.entity.sipo.SipoKeyword;
import com.module.dao.entity.sipo.TransactionData;
import com.module.dao.repository.sipo.SipoKeywordRepository;
import com.module.dao.repository.sipo.SipoRepository;
import com.module.dao.repository.sipo.TransactionDataRepository;

@Component("sipoDBService")
public class SipoDBService {
	
	
	    @Autowired
	    SipoKeywordRepository sipoKeywordRepository;
	    
	    @Autowired
	    SipoRepository sipoRepository;
	    
	    @Autowired
	    TransactionDataRepository transactionDataRepository;
	
	   //检查是定时任务中是否存在该任务
	   public List<SipoKeyword>  checkIsExistBYName(String type,String name){
		   
		     return sipoKeywordRepository.findByTypeAndName(type, name);
	   }
	   
	   //通过id查询定时任务
	   public SipoKeyword  getTimeTask(long id){
		      return sipoKeywordRepository.findOne(id);
	   }
	   
		// 查询详情
		public List<Sipo> getDetailByCOI(List<Long> idList) {
	
			List<Sipo> sipoList = sipoRepository.findByCOI(idList);
	
			for (Iterator<Sipo> iter = sipoList.iterator(); iter.hasNext();) {
				Sipo sipo = iter.next();
				List<TransactionData> transactionDataslist = transactionDataRepository.findBySipoID(sipo.getId());
				sipo.setTransactionDatas(transactionDataslist);
			}
	
			return sipoList;
		}
	   
	   //将新任务保存到定时任务表中
	   public SipoKeyword saveTimeTask(SipoKeyword sipoKeyword){
		      return sipoKeywordRepository.save(sipoKeyword);
	   }

	   
}

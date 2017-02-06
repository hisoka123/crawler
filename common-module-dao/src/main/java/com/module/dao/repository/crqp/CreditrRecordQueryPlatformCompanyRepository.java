package com.module.dao.repository.crqp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.crqp.CreditrRecordQueryPlatformCompany;
 

@Repository
public interface CreditrRecordQueryPlatformCompanyRepository  extends JpaRepository<CreditrRecordQueryPlatformCompany,Long> {
	
	//@Query("select o from Company o where o.city=?1 and o.state in(?2) and priority=(select max(priority) from Company)")
//	Company findTopByCityAndStateInOrderByPriorityDesc(String city,List<Integer> states);
//	IecmsCompany findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(String type, List<Integer> states, Integer num);
	//select * from xxxcompany where type=? and state in(2,0,...) and num<? order by priority limit 1;
//	CreditrRecordQueryPlatformCompany findTopByKeywordAndStateInAndNumLessThanOrderByPriorityDesc(String keyword, List<Integer> states, Integer num);
	CreditrRecordQueryPlatformCompany findTopByStateInAndNumLessThanOrderByPriorityDesc( List<Integer> states, Integer num);
	
	   @Query("select c from CreditrRecordQueryPlatformCompany c where keyword=?")
	   List<CreditrRecordQueryPlatformCompany> findByKeyword(String keyword);
//	
//	Company findByCityAndName(String city,String name);

}

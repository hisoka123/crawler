package com.module.dao.repository.iecms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.iecms.IecmsCompany;
 

@Repository
public interface IecmsCompanyRepository  extends JpaRepository<IecmsCompany,Long> {
		
	IecmsCompany findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(String type, List<Integer> states, Integer num);
	//select * from xxxcompany where type=? and state in(2,0,...) and num<? order by priority limit 1;
	
	   
	   @Query("select c from IecmsCompany c where type=?1 and keyword=?2")
	   List<IecmsCompany> findByTypeAndKeyword(String type,String keyword);
//	Company findTop1ByName(String name);
//	
//	Company findByCityAndName(String city,String name);

}

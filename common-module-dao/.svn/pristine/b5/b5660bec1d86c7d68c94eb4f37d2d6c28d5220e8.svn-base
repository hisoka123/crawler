package com.module.dao.repository.gsxt;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.gsxt.Company;
 

@Repository
public interface CompanyRepository  extends JpaRepository<Company,Long> {
	
	//@Query("select o from Company o where o.city=?1 and o.state in(?2) and priority=(select max(priority) from Company)")
	Company findTopByCityAndStateInAndNumLessThanOrderByIdAscPriorityDesc(String city,List<Integer> states, Integer num);
	
	Company findTop1ByName(String name);
	
	Company findByCityAndName(String city,String name);

}

package com.module.dao.repository.iautos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.iautos.IautosKeyword;
import com.module.dao.entity.renfawang.CompanyOrID;

@Repository
public interface IautosKeywordRepository extends JpaRepository<IautosKeyword,Long>{
	IautosKeyword findTop1ByStateInAndNumLessThanOrderByPriorityDesc(List<Integer> states, Integer num);
	
	
	  
   @Query("select c from IautosKeyword c where city=?1 and name=?2")
   List<IautosKeyword> findByCityAndName(String city,String name);

}

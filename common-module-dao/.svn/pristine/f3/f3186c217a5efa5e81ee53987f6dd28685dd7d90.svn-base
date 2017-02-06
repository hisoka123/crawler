package com.module.dao.repository.renfawang;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.renfawang.CompanyOrID;

@Repository
public interface CompanyOrIDRepository  extends JpaRepository<CompanyOrID,Long> {
	   CompanyOrID findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(String type, List<Integer> states, Integer num);
	
	   
	   @Query("select c from CompanyOrID c where type=?1 and keyword=?2")
	   List<CompanyOrID> findByTypeAndKeyword(String type,String keyword);


	
}

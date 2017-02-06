package com.module.dao.repository.sipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.sipo.SipoKeyword;

@Repository
public interface SipoKeywordRepository extends JpaRepository<SipoKeyword,Long>{
	SipoKeyword findTopByStateInAndNumLessThanOrderByPriorityDesc(List<Integer> states, Integer num);
	
	   @Query("select c from SipoKeyword c where type=?1 and name=?2")
	   List<SipoKeyword> findByTypeAndName(String type,String name);
}

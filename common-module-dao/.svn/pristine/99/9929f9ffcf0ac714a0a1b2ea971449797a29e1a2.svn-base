package com.module.dao.repository.zjsfgkw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.module.dao.entity.zjsfgkw.ZjsfgkwKeyword;

@Repository
public interface ZjsfgkwKeywordRepository extends JpaRepository<ZjsfgkwKeyword,Long>{

	ZjsfgkwKeyword findTopByStateInAndNumLessThanOrderByPriorityDesc(List<Integer> states, Integer num);
	
/*	@Query("select o from ZjsfgkwKeyword o where o.state in (0,2,5,100004,100013,100014,100015,100017) and o.num<3 order by o.priority limit 1")
	ZjsfgkwKeyword findZjsfgkwKeywordByState();*/
	
	 @Query("select c from ZjsfgkwKeyword c where propertyType=?1 and value=?2")
	   List<ZjsfgkwKeyword> findByTypeAndValue(String type,String keyword);
	 
	 @Query("select c from ZjsfgkwKeyword c where type=?1 and value=?2")
	   List<ZjsfgkwKeyword> findByTypeAndValue2(String type,String keyword);
}

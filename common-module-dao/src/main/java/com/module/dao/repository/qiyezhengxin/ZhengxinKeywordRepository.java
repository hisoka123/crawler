package com.module.dao.repository.qiyezhengxin;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.qiyezhengxin.ZhengxinKeyword;

@Repository
public interface ZhengxinKeywordRepository extends JpaRepository<ZhengxinKeyword,Long>{
	ZhengxinKeyword findTopByStateInAndNumLessThanOrderByPriorityDesc(List<Integer> states, Integer num);
	
	/*@Query("select o from ZhengxinKeyword o where o.state in (0,2,5,100004,100013,100014,100015,100017) and o.num<3 order by o.priority limit 1")
	ZhengxinKeyword findZhengxinKeywordByState();*/
	
	@Query("select c from ZhengxinKeyword c where company=?1 ")
	   List<ZhengxinKeyword> findByTypeAndValue(String keyword);
}
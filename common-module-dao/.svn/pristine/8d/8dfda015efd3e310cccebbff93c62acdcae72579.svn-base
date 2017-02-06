package com.module.dao.repository.shixin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.shixin.ShixinKeyword;

@Repository
public interface ShixinKeywordRepository extends JpaRepository<ShixinKeyword,Long>{
	
	ShixinKeyword findTopByStateInAndNumLessThanOrderByPriorityDesc(List<Integer> states, Integer num);
	
	/*@Query("select o from ShixinKeyword o where o.state in (0,2,5,100004,100013,100014,100015,100017) and o.num<3 order by o.priority limit 1")
	ShixinKeyword findShixinKeywordByState();*/

	ShixinKeyword findTopByName(String name);
	
	//依据type、keyword查询
	List<ShixinKeyword> findByTypeAndKeyword(String type,String keyword);
	
	//依据type、cardNum、keyword查询
	List<ShixinKeyword> findByTypeAndKeywordAndCardNum(String type,String keyword,String cardNum);
}
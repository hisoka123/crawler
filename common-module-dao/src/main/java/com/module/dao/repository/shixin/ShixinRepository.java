package com.module.dao.repository.shixin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.renfawang.PeopleCourt;
import com.module.dao.entity.shixin.Shixin;

@Repository
public interface ShixinRepository extends JpaRepository<Shixin,Long>{

	  //根据定时任务id查询
	  @Query("select new Shixin(s.name,s.sexy,s.age,s.cardNum,s.courtName,s.areaName,s.gistId,s.regDate,s.caseCode,s.gistUnit,"
	                           +"s.duty,s.performance,s.disruptTypeName,s.publishDate) "
			                   +"from Shixin s where s.executetime=(select max(x.executetime) from Shixin x where x.shixinKeyword.id in(?1))")
	  List<Shixin> findByTTId(List<Long> idList);
	  
	 
}
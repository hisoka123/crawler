package com.module.dao.repository.sipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.sipo.Sipo;

@Repository
public interface SipoRepository extends JpaRepository<Sipo,Long>{


	
	   @Query("select new Sipo(p.id,p.licenseName,p.applicationNum,p.patentHolder,p.inventor,p.classNumber,p.summary,p.num,p.img) from Sipo p where p.executetime=(select max(c.executetime) from Sipo c where c.sipoKeyword.id in(?1))")
	   List<Sipo> findByCOI(List<Long> idList);
}

package com.module.dao.repository.fahaicc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.fahaicc.FahaiccResult;

@Repository
public interface FahaiccResultRepository extends JpaRepository<FahaiccResult,Integer> {
	
	   
	   @Query("select new FahaiccResult(f.title,f.linkUrl,f.filingDate,f.courtDate,f.conclusionDate,f.pubDate,f.authority,f.type) "
	                  +"from FahaiccResult f where f.executeTime=(select max(h.executeTime) from FahaiccResult h where h.fahaiccRoot.id in(?1))")
	   List<FahaiccResult> findByRootId(List<Long> idList);

}

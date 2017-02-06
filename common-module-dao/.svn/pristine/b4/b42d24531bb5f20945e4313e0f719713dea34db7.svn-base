package com.module.dao.repository.customs;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.customs.CUResultJson;

@Repository
public interface CustomsResultJsonRepository extends
		JpaRepository<CUResultJson, Integer> {

	public Set<CUResultJson> findByCustomsCompany_Id(Long id);
	
	@Query("select new CUResultJson(c.cname,c.result,c.executetime) from CUResultJson c where c.executetime=(select max(r.executetime) from CUResultJson r where r.customsCompany.id  in(?1))")
	List<CUResultJson> findByCcId(List<Long> idList);

}

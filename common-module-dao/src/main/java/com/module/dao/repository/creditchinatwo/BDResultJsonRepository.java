package com.module.dao.repository.creditchinatwo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.creditchinatwo.BDResultJson;

@Repository
public interface BDResultJsonRepository extends
		JpaRepository<BDResultJson, Integer> {

	public Set<BDResultJson> findByCreditCompany_Id(Long id);

	public BDResultJson findTopByCreditCompany_IdOrderByExecutetimeDesc(Long id);

}

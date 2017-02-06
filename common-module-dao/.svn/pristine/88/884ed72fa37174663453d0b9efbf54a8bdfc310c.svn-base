package com.module.dao.repository.cnca;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.cnca.CFResultJson;

@Repository
public interface CertificateResultJsonRepository extends
		JpaRepository<CFResultJson, Integer> {

	public Set<CFResultJson> findByCertificateCompany_Id(Long id);

}

package com.module.dao.repository.cnca;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.cnca.CertificateCompany;

@Repository
public interface CertificateCompanyRepository extends
		JpaRepository<CertificateCompany, Long> {

	CertificateCompany findTopByStateInOrderByPriorityDesc(List<Integer> states);

	CertificateCompany findTopByStateInAndNumLessThanOrderByPriorityDesc(
			List<Integer> states, Integer num);

	CertificateCompany findTop1ByName(String name);

	List<CertificateCompany> findByName(String name);

}

package com.module.dao.repository.customs;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.module.dao.entity.customs.RegInformation;

@Repository
public interface RegInformationRepository extends
		JpaRepository<RegInformation, Long> {

	public Set<RegInformation> findByCustomsCompany_Id(Long cid);

}

package com.module.dao.repository.creditchinatwo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.creditchinatwo.CreditCompany;

@Repository
public interface CreditCompanyRepository extends
		JpaRepository<CreditCompany, Long> {

	CreditCompany findTopByStateInOrderByPriorityDesc(List<Integer> states);

	CreditCompany findTopByStateInAndNumLessThanOrderByPriorityDesc(
			List<Integer> states, Integer num);

	CreditCompany findTop1ByName(String name);

	CreditCompany findTopByNameAndObjectType(String name, String objectType);

}

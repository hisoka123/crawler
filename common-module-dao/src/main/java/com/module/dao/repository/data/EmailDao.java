package com.module.dao.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.module.dao.entity.data.Email;

public interface EmailDao extends PagingAndSortingRepository<Email, Long>,JpaSpecificationExecutor<Email>, JpaRepository<Email, Long> {

}

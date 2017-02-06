package com.module.dao.repository.timeTask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExceptionDao extends PagingAndSortingRepository<com.module.dao.entity.timeTask.Exception, Long>,JpaSpecificationExecutor<Exception>, JpaRepository<com.module.dao.entity.timeTask.Exception, Long>{

}

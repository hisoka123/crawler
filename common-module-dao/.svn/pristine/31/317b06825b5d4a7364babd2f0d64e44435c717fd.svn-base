package com.module.dao.repository.pbccrc;


import com.module.dao.entity.pbccrc.PbccrcAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PbccrcAccountRepository extends JpaRepository<PbccrcAccount,Long> {


    @Query("select l from PbccrcAccount l where l.username=:username")
    List<PbccrcAccount> findByUsername(@Param("username")String username);
}

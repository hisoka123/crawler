package com.module.dao.repository.pbccrc;


import com.module.dao.entity.pbccrc.PlainPbccrcJson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlainPbccrcJsonRepository extends JpaRepository<PlainPbccrcJson,Long> {


    PlainPbccrcJson findByTsf75e5b(String tsf75e5b);


//    @Query(value = "select l.* from t_plain_pbccrc_json l left join t_rela_copy rc on rc.copy_id = rc.id where rc.id = :copyId " +
//            "order by l.create_time desc limit 1 offset 0",nativeQuery = true)
//    List<PlainPbccrcJson> findByCopyId(@Param("copyId") Long copyId);
}

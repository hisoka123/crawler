package com.module.dao.repository.sipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.sipo.TransactionData;

@Repository
public interface TransactionDataRepository extends JpaRepository<TransactionData,Long>{
	
       @Query("select new TransactionData(t.id,t.num,t.date,t.type,t.content) from TransactionData t "
                     +"where t.sipo.id=?1 and t.executetime=(select max(d.executetime) from TransactionData d where d.sipo.id=?1 )")
       List<TransactionData> findBySipoID(Long sid);
}

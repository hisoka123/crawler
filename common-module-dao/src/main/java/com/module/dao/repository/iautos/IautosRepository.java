package com.module.dao.repository.iautos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.iautos.Iautos;

@Repository
public interface IautosRepository extends JpaRepository<Iautos,Long>{
	

	   @Query("select new Iautos(p.name,p.timekm,p.price,p.profile_image,p.url,p.certificate) from Iautos p where p.executetime=(select max(c.executetime) from Iautos c where c.iautosKeyword.id in(?1))")
	   List<Iautos> findByCOI(List<Long> idList);
	
}

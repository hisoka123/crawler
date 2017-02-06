package com.module.dao.repository.qiyezhengxin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.qiyezhengxin.Zhengxin;

@Repository
public interface ZhengxinRepository extends JpaRepository<Zhengxin,Long>{
	
	 @Query("select new Zhengxin(p.company,p.date,p.content,p.type,p.title,p.link,p.property) from Zhengxin p where p.executetime=(select max(c.executetime) from Zhengxin c where c.zhengxinKeyword.id in(?1))")
	   List<Zhengxin> findByCOI(List<Long> idList);

}
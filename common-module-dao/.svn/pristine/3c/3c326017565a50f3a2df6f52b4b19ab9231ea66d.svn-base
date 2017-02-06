package com.module.dao.repository.fahaicc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.fahaicc.FahaiccRoot;

@Repository
public interface FahaiccRootRepository extends JpaRepository<FahaiccRoot,Long> {
	public static final String TYPE_NUM = "身份证号码/组织机构代码";
	public static final String TYPE_NAME = "个人/企业名称";
	
	FahaiccRoot findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(String type, List<Integer> states, Integer num);
	
	@Query("select new FahaiccRoot(f.id,f.keyword,f.type,f.num,f.totalNum,f.priority,f.state) from FahaiccRoot f where f.type=?1 and f.keyword=?2")
	List<FahaiccRoot> findByTypeAndKeyword(String type,String keyword);
	
	@Query("select new FahaiccRoot(f.id,f.keyword,f.type,f.num,f.totalNum,f.priority,f.state) from FahaiccRoot f where f.id=?1")
	FahaiccRoot findById(Long id);
}

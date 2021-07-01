package com.kosta.springbootproject.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Users;

public interface ClassHistoryRepository extends CrudRepository<ClassHistory, Long>,QuerydslPredicateExecutor<ClassHistory>{
	
	public List<ClassHistory> findByClasses(Classes classes); 
	
	//유저, 강의로 강의신청 찾기
	public Optional<ClassHistory> findByUserAndClasses(Users user, Classes classes);
	
	public List<ClassHistory> findByUser(Users user);
	
	// 미수료, 확정, 대기, 취소 인원 카운트를 위한 natviequery사용
	@Query(value = " SELECT"
			+ " SUM(if(class_history_state='UNCOMPLETED',1,0)) AS 'uncompletedcount',"
			+ " SUM(if(class_history_state='COMMIT',1,0)) AS 'commitcount',"
			+ " SUM(if(class_history_state='WAIT',1,0)) AS 'waitcount',"
			+ " SUM(if(class_history_state='CANCEL',1,0)) AS 'cancelcount'"
			+ " FROM class_history"
			+ " WHERE user_user_no = ?1", nativeQuery = true)
  
	public List<Object[]> findClassHistoryCountByUser(Long userNo);
	
	@Query("select count(*) from ClassHistory where classHistoryState='wait'")
	public int classHistoryWaitCount();
	
	@Query("select count(*) from ClassHistory where classHistoryState='completed'")
	public int classHistoryCompletedCount();
  
}

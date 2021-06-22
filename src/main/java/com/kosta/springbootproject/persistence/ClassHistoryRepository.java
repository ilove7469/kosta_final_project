package com.kosta.springbootproject.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Users;

public interface ClassHistoryRepository extends CrudRepository<ClassHistory, Long>{
	
	public List<ClassHistory> findByClasses(Classes classes); 
	
	//유저, 강의로 강의신청 찾기
	public Optional<ClassHistory> findByUserAndClasses(Users user, Classes classes);
}

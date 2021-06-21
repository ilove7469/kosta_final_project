package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.Classes;

public interface ClassHistoryRepository extends CrudRepository<ClassHistory, Long>{
	
	public List<ClassHistory> findByClasses(Classes classes); 
	
	//유저, 강의로 강의신청 찾기
}

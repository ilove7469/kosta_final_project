package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.Classes;

public interface ClassHistoryRepository extends CrudRepository<ClassHistory, Long>{
	
	public List<ClassHistory> findByClasses(Classes classes); 
	
}

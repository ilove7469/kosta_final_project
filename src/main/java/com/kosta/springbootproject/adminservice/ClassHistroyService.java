package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.persistence.ClassHistoryRepository;

@Service
public class ClassHistroyService {
	
	@Autowired
	ClassHistoryRepository repo;
	
	public List<ClassHistory> selectAll(){
		return (List<ClassHistory>) repo.findAll();
	}
	

}

package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.persistence.ClassesRepository;

@Service
public class AdminManageClassService {
	
	@Autowired
	ClassesRepository repo;
	
	public List<Classes> selectAll(){
		return (List<Classes>) repo.findAll();
	}
	
}

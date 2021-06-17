package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Admin;
import com.kosta.springbootproject.persistence.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository repo;
	
	public List<Admin> selectAll(){
		return (List<Admin>) repo.findAll();
	}
	
}

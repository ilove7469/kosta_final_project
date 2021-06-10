package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.User;
import com.kosta.springbootproject.persistence.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	
	public List<User> selectAll(){
		return (List<User>) repo.findAll();
	}
}

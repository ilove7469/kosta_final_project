package com.kosta.springbootproject.userservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.MemberReposiroty;
import com.kosta.springbootproject.persistence.UserRepository;

@Service
public class UserPageUserService {

	@Autowired
	MemberReposiroty mrepo;
	@Autowired
	UserRepository urepo;
	
	
	public Optional<MemberDTO> findMemberIdByUserId(String userId){
		return mrepo.findById(userId); 
	}
	
	public void insertUser(Users user) {
		urepo.save(user);
	}

	/*
	 * public Users findUser() { //return urepo.save(user); }
	 */
}

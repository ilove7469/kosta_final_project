package com.kosta.springbootproject.userservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.persistence.MemberReposiroty;

@Service
public class UserPageUserService {

	@Autowired
	MemberReposiroty mrepo;
	
	public Optional<MemberDTO> findMemberIdByUserId(String userId){
		return mrepo.findById(userId); 
	}
}

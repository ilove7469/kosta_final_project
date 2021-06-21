package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.UserRepository;
import com.querydsl.core.types.Predicate;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	
	public List<Users> selectAll(){
		return (List<Users>) repo.findAll();
	}
	
	public Page<Users> selectAll(PageVO pvo) {
		Predicate p = repo.makePredicate(pvo.getType(),pvo.getKeyword());
		
		Pageable pageable = pvo.makePaging(0, "userNo");
		Page<Users> result = repo.findAll(p, pageable);
		return result;

	}
	
}

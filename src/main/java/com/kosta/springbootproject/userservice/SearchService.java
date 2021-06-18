package com.kosta.springbootproject.userservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.persistence.ClassesRepository;
import com.querydsl.core.types.Predicate;

@Service
public class SearchService {
	
	@Autowired
	ClassesRepository crepo;
	
	public List<Classes> selectOption(String keyword){
		Predicate p = crepo.makePredicate(keyword);
		List<Classes> result = (List<Classes>)crepo.findAll(p);
		return result;
	}
	
	public List<Classes> selectSubClasses(String keyword, Long subNo){
		Predicate p = crepo.makePredicateSubClasses(keyword, subNo);
		List<Classes> result = (List<Classes>)crepo.findAll(p);
		return result;
	} 

}

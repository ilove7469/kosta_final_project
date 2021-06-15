package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.EducationTime;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.persistence.EducationTimeRepository;
import com.querydsl.core.types.Predicate;

@Service
public class EducationTimeService {

	@Autowired
	EducationTimeRepository repo;
	
	public Page<EducationTime> selectAll(PageVO pvo) {
		Predicate p = repo.makePredicate();
		Pageable pageable = pvo.makePaging(0, "educationTimeNo");
		Page<EducationTime> result = repo.findAll(p, pageable);
		return result;

	}
	
}

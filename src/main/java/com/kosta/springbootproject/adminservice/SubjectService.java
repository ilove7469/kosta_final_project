package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.persistence.SubjectRepository;
import com.querydsl.core.types.Predicate;

@Service
public class SubjectService {

	@Autowired
	SubjectRepository repo;
	
	public Page<Subject> selectAll(PageVO pvo) {
		Predicate p = repo.makePredicate();
		Pageable pageable = pvo.makePaging(0, "subjectNo");
		Page<Subject> result = repo.findAll(p, pageable);
		return result;

	}
	
	public Subject updateOrInsert(Subject subject) {
		return repo.save(subject);
	}
	
	public Subject selectById(Long id) {
		return repo.findById(id).get();
	}
	
	public List<Subject> selectAll(){
		return (List<Subject>) repo.findAll();
	}
	
	public Subject findSubjectBySubjectNo(Long subjectNo){
		Subject subject = repo.findById(subjectNo).get();
		return subject;
	}
	
	public int deleteSubject(Long no) {
		
		int result=0;
		
		try {
		repo.deleteById(no);
		result=1;
		}catch(Exception ex) {
		
		}
		return result;
	}
	
}

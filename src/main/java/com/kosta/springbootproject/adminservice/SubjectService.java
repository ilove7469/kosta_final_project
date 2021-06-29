package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.persistence.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	SubjectRepository repo;
	
	
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

package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.EducationTime;
import com.kosta.springbootproject.persistence.EducationTimeRepository;

@Service
public class EducationTimeService {

	@Autowired
	EducationTimeRepository repo;

	
	public EducationTime updateOrInsert(EducationTime educationTime) {
		return repo.save(educationTime);
	}
	
	public List<EducationTime> selectAll(){
		return (List<EducationTime>) repo.findAll();
	}
	
	public int deleteEducationTime(Long no) {
		
		int result=0;
		
		try {
		repo.deleteById(no);
		result=1;
		}catch(Exception ex) {
		
		}
		return result;
	}
	
	public EducationTime findEducationTimeByNo(Long educationTimeNo){
		EducationTime educationTime = repo.findById(educationTimeNo).get();
		return educationTime;
	}	

	
}

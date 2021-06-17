package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.model.LectureHall;
import com.kosta.springbootproject.persistence.LectureHallRepository;

@Service
public class LectureHallService {

	@Autowired
	LectureHallRepository repo;
	
	public List<LectureHall> selectAll(){
		return (List<LectureHall>) repo.findAll();
	}
	
	public LectureHall updateOrInsert(LectureHall lectureHall) {
		return repo.save(lectureHall);
	}
	
	public int deleteLectureHall(Long no) {
		
		int result=0;
		
		try {
		repo.deleteById(no);
		result=1;
		}catch(Exception ex) {
		
		}
		return result;
	}
}

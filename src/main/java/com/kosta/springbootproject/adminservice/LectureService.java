package com.kosta.springbootproject.adminservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.springbootproject.persistence.LectureRepository;

public class LectureService {

	@Autowired
	LectureRepository lectureRepo;
	
}

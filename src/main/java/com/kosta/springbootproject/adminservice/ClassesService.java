package com.kosta.springbootproject.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.persistence.ClassesRepository;

@Service
public class ClassesService {

	@Autowired
	ClassesRepository classesRepo;
	
}

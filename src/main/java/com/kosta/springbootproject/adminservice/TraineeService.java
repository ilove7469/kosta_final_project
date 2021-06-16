package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.persistence.TraineeRepository;

@Service
public class TraineeService {
	
	@Autowired
	TraineeRepository traineerepository;


	public List<Trainee> selectAll(){
		return (List<Trainee>) traineerepository.findAll();
	}
}

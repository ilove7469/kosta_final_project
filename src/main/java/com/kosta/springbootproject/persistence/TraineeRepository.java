package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Trainee;

public interface TraineeRepository extends CrudRepository<Trainee, Long>{
	
	public Trainee findBytraineeName(String traineeName);
	
}

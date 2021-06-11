package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Trainee;

public interface SubjectRepository extends CrudRepository<Subject, Long>{

	public List<Subject> findByTraineeOrderBySubPriorityAsc(Trainee trainee);
	
}

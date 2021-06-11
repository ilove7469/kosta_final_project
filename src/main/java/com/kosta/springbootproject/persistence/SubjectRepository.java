package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long>{

	public List<Subject> findByTraineeOrderBySubPriorityAsc(Long traineeNo);
	
}

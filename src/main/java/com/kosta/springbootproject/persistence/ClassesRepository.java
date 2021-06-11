package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Lecture;

public interface ClassesRepository extends CrudRepository<Classes,Long>{
	
	public List<Classes> findByLecture(Lecture lecture);
}

package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Lecture;

public interface LectureRepository extends CrudRepository<Lecture, Long>{

}

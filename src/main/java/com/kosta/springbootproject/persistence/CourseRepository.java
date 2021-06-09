package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{

}

package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long>{

}

package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long>{

}

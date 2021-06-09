package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.EducationTime;

public interface EducationTimeRepository extends CrudRepository<EducationTime,Long>{

}

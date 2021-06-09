package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Classes;

public interface ClassesRepository extends CrudRepository<Classes,Long>{

}

package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.ClassHistory;

public interface ClassHistoryRepository extends CrudRepository<ClassHistory, Long>{

}

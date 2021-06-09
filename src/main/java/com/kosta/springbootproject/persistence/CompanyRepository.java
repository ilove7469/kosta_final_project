package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>{

}

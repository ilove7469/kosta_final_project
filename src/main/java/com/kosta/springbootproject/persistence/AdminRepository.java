package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Admin;

public interface AdminRepository extends CrudRepository<Admin,Long>{

}

package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}

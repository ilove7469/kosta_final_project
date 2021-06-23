package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.MemberDTO;

public interface MemberReposiroty extends CrudRepository<MemberDTO, String> {

}

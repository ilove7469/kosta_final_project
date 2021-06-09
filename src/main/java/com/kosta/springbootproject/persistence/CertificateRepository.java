package com.kosta.springbootproject.persistence;

import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Certificate;

public interface CertificateRepository extends CrudRepository<Certificate, Long>{

}

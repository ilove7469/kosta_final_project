package com.kosta.springbootproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.persistence.CertificateRepository;

@EnableJpaAuditing
@SpringBootTest
public class CertificateTest {

	@Autowired
	CertificateRepository repo;
	
	//@Test
	public void insertCertificate() {
		Certificate certi = Certificate
				.builder()
				.certiName("테스트")
				.certiComment("테스트")
				.build();
		
		repo.save(certi);

	}
	
	//@Test
	public void searchAllTest() {
		repo.findAll().forEach(certi->{
			System.out.println(certi.toString());
		});
	}
	
	//@Test
	public void updateTest() {
		 repo.findById(55L).ifPresent(d ->{
			 d.setCertiName("산업계주도 재직자");
			 repo.save(d);
		 });
	}
	
	//@Test
	public void deleteTest() {
		 repo.findById(177L).ifPresent(d ->{
			 repo.delete(d);
		 });
	}
	
}

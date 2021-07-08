package com.kosta.springbootproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.ConventionEnumType;
import com.kosta.springbootproject.persistence.CompanyRepository;


import lombok.extern.java.Log;

@Log
@SpringBootTest
public class CompanyTest {

	@Autowired
	CompanyRepository repo;
	
	//@Test
	public void insertCompany() {
		IntStream.range(1, 20).forEach(i -> {
			Company c = Company.builder()
					.companyName("회사"+i)
					.companyLicense("000"+i+"3355"+i)
					.companyBoss("사장" + i)
					.companyConvention(ConventionEnumType.Convention)
					.build();
			repo.save(c);
		});

	}
	
//	@Test
	public void selectAll() {
		repo.findAll().forEach(c -> {
			System.out.println(c);
		});
	}
	
//	@Test
	public void selectById() {
		Company c = repo.findById(17L).get();
		System.out.println(c);
	}
	
//	@Test
	public void updateCompany() {
		repo.findById(44L).ifPresent(c -> {
			c.setCompanyBoss("newBoss");
			repo.save(c);
		});
	}
	
	//@Test
	public void delete() {
		repo.deleteById(44L);
	}
}



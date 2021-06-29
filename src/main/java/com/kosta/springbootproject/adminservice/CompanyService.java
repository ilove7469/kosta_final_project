package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.persistence.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyrepo;
	
	
	public Company insertCompany(Company company) {
		return companyrepo.save(company);
	}
	
	public Company selectById(Long companyNo) {
		return companyrepo.findById(companyNo).get();
	}
	
	public int deleteCompany(Long cno) {
		
		int ret=0;
		
		try {
		companyrepo.deleteById(cno);
		ret=1;
		}catch(Exception ex) {
		
		}
		return ret;
	}


	public Company updateCourse(Company company) {
		return companyrepo.save(company);
	}
	
	public List<Company> selectAll(){
		return (List<Company>)companyrepo.findAll();
	}
	
}

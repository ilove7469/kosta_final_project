package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.persistence.CompanyRepository;
import com.querydsl.core.types.Predicate;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyrepo;
	
	
	public Page<Company> selectAll(PageVO pvo) { 
		Predicate p = companyrepo.makePredicate(pvo.getType(),pvo.getKeyword()); 
	
		Pageable pageable = pvo.makePaging(0, "companyNo");
		
		Page<Company> result = companyrepo.findAll(p, pageable);
		return result;
		 
	}
	
	
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

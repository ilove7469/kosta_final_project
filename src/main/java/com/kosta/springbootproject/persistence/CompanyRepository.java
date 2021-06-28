package com.kosta.springbootproject.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.QCompany;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface CompanyRepository extends CrudRepository<Company, Long>,
QuerydslPredicateExecutor<Company>
{
 
	
	public default Predicate makePredicate(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QCompany company = QCompany.company;
		builder.and(company.companyNo.gt(0));
		if(type==null) return builder;
		switch (type) {
		case "companyName":
			builder.and(company.companyName.like("%" + keyword + "%"));
			break;	
		case "companyLicense":
			builder.and(company.companyLicense.like("%" + keyword + "%"));
			break;
		case "companyBoss":
			builder.and(company.companyBoss.like("%" + keyword + "%")); 
			break;

		default:
			break;
		}
		return builder;
		
	}
	
	@Query("select count(*) from Company WHERE company_convention='Convention'")
	public int companyCount();
	
}

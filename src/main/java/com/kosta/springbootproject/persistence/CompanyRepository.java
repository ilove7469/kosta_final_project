package com.kosta.springbootproject.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.ConventionEnumType;
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
			builder.and(company.companyName.like("%" + keyword + "%"));  //and title like '%?%'
			break;	
		case "companyLicense":
			builder.and(company.companyLicense.like("%" + keyword + "%"));  //and title like '%?%'
			break;
		case "companyBoss":
			builder.and(company.companyBoss.like("%" + keyword + "%"));  //and title like '%?%'
			break;

		default:
			break;
		}
		//System.out.println("----------------"builder);
		return builder;
		
	}
	
}

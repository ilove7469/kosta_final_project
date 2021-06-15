package com.kosta.springbootproject.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.EducationTime;
import com.kosta.springbootproject.model.QEducationTime;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface EducationTimeRepository extends CrudRepository<EducationTime,Long>,QuerydslPredicateExecutor<EducationTime>{
	
	public default Predicate makePredicate() {
		BooleanBuilder builder = new BooleanBuilder();
		QEducationTime educationTime = QEducationTime.educationTime;
		builder.and(educationTime.educationTimeNo.gt(0)); //and educationTimeNo>0
		return builder;
	}
}

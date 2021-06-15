package com.kosta.springbootproject.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.QCompany;
import com.kosta.springbootproject.model.QTeacher;
import com.kosta.springbootproject.model.Teacher;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface TeacherRepository extends CrudRepository<Teacher, Long>,
QuerydslPredicateExecutor<Teacher>
{
	
	public default Predicate makePredicate(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QTeacher company = QTeacher.teacher;
		builder.and(company.teacherNo.gt(0));
		if(type==null) return builder;
		switch (type) {
		case "teacherId":
			builder.and(company.teacherId.like("%" + keyword + "%"));
			break;	
		case "teacherName":
			builder.and(company.teacherName.like("%" + keyword + "%"));
			break;
		case "teacherPhone":
			builder.and(company.teacherPhone.like("%" + keyword + "%")); 
			break;
		case "teacherEmail":
			builder.and(company.teacherEmail.like("%" + keyword + "%")); 
			break;

		default:
			break;
		}
		return builder;
		
	}

}

package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;


import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.QClasses;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface ClassesRepository
	extends CrudRepository<Classes,Long>,
	QuerydslPredicateExecutor<Classes>
{
	
	public List<Classes> findByLecture(Lecture lecture);
	
	public default Predicate makePredicate(String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QClasses classes = QClasses.classes;
		//강사명
		builder.or(classes.teacher.teacherName.contains(keyword));
		//과정명
		builder.or(classes.lecture.course.courseName.contains(keyword));
		//주제
		builder.or(classes.lecture.course.subject.subName.contains(keyword));
		
		return builder;
		
	}
}

package com.kosta.springbootproject.persistence;

import java.util.Optional;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.QLecture;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface LectureRepository extends CrudRepository<Lecture, Long>, QuerydslPredicateExecutor<Lecture>{
	
	public Optional<Lecture> findByCourseAndLecturePlanYear(Course course, int lecturePlanYear);
	
	public default Predicate makePredicate(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();

		QLecture lecture = QLecture.lecture;
		builder.and(lecture.lecturePlanNo.gt(0));
		if(type==null) return builder;
		builder.and(lecture.course.courseName.like("%" + keyword + "%"));
		return builder;
		
	}
	
}

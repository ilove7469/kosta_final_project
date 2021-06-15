package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.QSubject;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Trainee;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface SubjectRepository extends CrudRepository<Subject, Long>,QuerydslPredicateExecutor<Subject>{

	public List<Subject> findByTraineeOrderBySubPriorityAsc(Trainee trainee);
	
	public default Predicate makePredicate() {
		BooleanBuilder builder = new BooleanBuilder();
		QSubject subject = QSubject.subject;
		builder.and(subject.subjectNo.gt(0)); //and userNo>0
		return builder;
	}
}

package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.QCompany;
import com.kosta.springbootproject.model.QCourse;
import com.kosta.springbootproject.model.Subject;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface CourseRepository extends CrudRepository<Course, Long>, QuerydslPredicateExecutor<Course>{
	
	@Query(value="select c.courseName, l.lectureOpenCount, c.courseTotalTrainTime, c.courseNo, l.lecturePlanYear from Course c left outer join Lecture l on (l.course = c.courseNo) where c.subject = ?1 AND l.lecturePlanYear=YEAR(CURDATE())")
	public List<Object[]> getCourseWithLecture(Subject subjectNo);
	
	
	//관리자과정페이지 조건검색과 페이징 처리
	public default Predicate makePredicate(String type, String keyword) {

		BooleanBuilder builder = new BooleanBuilder();
		QCourse course = QCourse.course;
		builder.and(course.courseNo.gt(0));
		if (type == null)
			return builder;
		switch (type) {
		case "courseName":
			builder.and(course.courseName.like("%" + keyword + "%"));
			break;
		case "subName":
			builder.and(course.subject.subName.like("%" + keyword + "%"));
			break;
		case "courseTotalTrainTime":
			builder.and(course.courseTotalTrainTime.like("%" + keyword + "%"));
			break;
		case "courseTotalDay":
			builder.and(course.courseTotalDay.like("%" + keyword + "%"));
			break;
		case "coursePrice":
			builder.and(course.coursePrice.like("%" + keyword + "%"));
			break;
		case "courseCapacity":
			builder.and(course.courseCapacity.like("%" + keyword + "%"));
			break;

		default:
			break;
		}
		return builder;

	}
}

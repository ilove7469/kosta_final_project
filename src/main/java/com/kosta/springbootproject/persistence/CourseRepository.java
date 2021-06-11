package com.kosta.springbootproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Subject;

public interface CourseRepository extends CrudRepository<Course, Long>{
	
	@Query(value="select distinct c.courseName, l.lectureOpenCount, c.courseTotalTrainTime from Course c left outer join Lecture l on (l.course = c.courseNo) where c.subject = ?1")
	public List<Object[]> getCourseWithLecture(Subject subjectNo);
	
	
}

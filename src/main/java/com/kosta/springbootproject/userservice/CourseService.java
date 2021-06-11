package com.kosta.springbootproject.userservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.persistence.ClassesRepository;
import com.kosta.springbootproject.persistence.CourseRepository;
import com.kosta.springbootproject.persistence.LectureRepository;
import com.kosta.springbootproject.persistence.SubjectRepository;

@Service
public class CourseService {
	
	@Autowired
	SubjectRepository subjectRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	LectureRepository lectureRepo;
	@Autowired
	ClassesRepository classRepo;
	
	public List<Object[]> findCourseWithLecture(Long subjectNo){
		
		//주제id로 course(join lecture)값 검색
		return courseRepo.getCourseWithLecture(subjectRepo.findById(subjectNo).get());
	}
	
	public Subject findSubjectById(Long subjectId) {
		Subject subject = subjectRepo.findById(subjectId).get();
		return subject;
	}
	
	public Lecture findLecturByCourse(Course course) {
		Lecture lecture = lectureRepo.findByCourse(course);
		return lecture;
	}
	public List<Classes> findClassByLecture(Lecture lecture){
		List<Classes> classList = classRepo.findByLecture(lecture);
		return classList;
		
	}
	
}

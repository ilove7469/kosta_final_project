package com.kosta.springbootproject.userservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.persistence.ClassesRepository;
import com.kosta.springbootproject.persistence.CourseRepository;
import com.kosta.springbootproject.persistence.LectureRepository;
import com.kosta.springbootproject.persistence.SubjectRepository;
import com.kosta.springbootproject.persistence.TraineeRepository;

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
	@Autowired
	TraineeRepository traineeRepo;
	
	
	public List<Trainee> findTraineeAll() {
		return (List<Trainee>)traineeRepo.findAll();
	}
	public List<Subject> findSubjectByTraineeNo(Long traineeNo) {
		Trainee trainee = Trainee.builder()
				.traineeNo(traineeNo)
				.build();
		 
		return subjectRepo.findByTraineeOrderBySubPriorityAsc(trainee);
	}
	public Subject findSubjectById(Long subjectId) {
		Subject subject = subjectRepo.findById(subjectId).get();
		return subject;
	}
	
	// >> /course/{subjectNo}
	public List<Object[]> findCourseWithLecture(Long subjectNo){
		//주제id로 course(join lecture)값 검색
		return courseRepo.getCourseWithLecture(subjectRepo.findById(subjectNo).get());
	}
	
	//>>/courseInfo/{courseNo}/{lectureYear}
	public Lecture findLecturByCourse(Long courseNo, int lectureYear) {
		Course course = Course.builder()
				.courseNo(courseNo)
				.build();
		Lecture lecture = lectureRepo.findByCourseAndLecturePlanYearGreaterThanEqual(course,lectureYear);
		System.out.println(lecture);
		return lecture;
	}
	public List<Classes> findClassByLecture(Lecture lecture){
		List<Classes> classList = classRepo.findByLecture(lecture);
		return classList;
		
	}
	
}

package com.kosta.springbootproject.userservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.ClassStateEnumType;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.ClassHistoryRepository;
import com.kosta.springbootproject.persistence.ClassesRepository;
import com.kosta.springbootproject.persistence.CourseRepository;
import com.kosta.springbootproject.persistence.LectureRepository;
import com.kosta.springbootproject.persistence.SubjectRepository;
import com.kosta.springbootproject.persistence.TraineeRepository;
import com.kosta.springbootproject.persistence.UserRepository;

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
	@Autowired
	UserRepository userRepo;
	@Autowired
	ClassHistoryRepository classHistoryRepo;
	
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
	public Lecture findLecturByCourse(Course course, int lectureYear) {
		Lecture lecture = lectureRepo.findByCourseAndLecturePlanYear(course,lectureYear).get();
		return lecture;
	}
	
	public List<Classes> findClassByLecture(Lecture lecture){
		List<Classes> classList = classRepo.findByLecture(lecture);
		for(Classes classes:classList) {
			Integer capa= classes.getLecture().getCourse().getCourseCapacity();
			Date Today = new Date();
			// 강의상태 = APPLY(OPENREADY때문에 넣어주었다.) && 목표정원 <= 확정인원 && 개강일 > 오늘날짜
 			if(classes.getClassState()==ClassStateEnumType.APPLY
 			   &&capa <= classes.getCommitCount()
 			   &&classes.getClassOpenDate().compareTo(Today)>0) {
					classes.setClassState(ClassStateEnumType.END);
					classRepo.save(classes);
			} else if(classes.getClassState()==ClassStateEnumType.END
		 			   &&capa > classes.getCommitCount()
		 			   &&classes.getClassOpenDate().compareTo(Today)>0){
					classes.setClassState(ClassStateEnumType.APPLY);
					classRepo.save(classes);
			}
		}
		return classList;
	}
	
	//>>/enroll/{classNo}
	//>>/search/searchSubject
	public Classes findClassByClassNO(Long classNo) {
		return classRepo.findById(classNo).get();
	}
	
	//>>/course/enroll/info/{classNo}
	public Users findUserByUserID(String userId) {
		return userRepo.findByUserId(userId);
	}
	
	@Transactional
	public Boolean updateClassHistory(ClassHistory ch) {
		Boolean check = classHistoryRepo.findByUserAndClasses(ch.getUser(),ch.getClasses()).isEmpty();
		Boolean result = false; 
		
		//classHistory 없으면 생성 후 카운트 증가
		if(check) {
			classHistoryRepo.save(ch);
			classRepo.findById(ch.getClasses().getClassNo()).ifPresent(cInfo->{
				cInfo.setWaitCount(cInfo.getWaitCount()+1);
				classRepo.save(cInfo);
			});
			result = true;
		}
		return result;
	}
	
}

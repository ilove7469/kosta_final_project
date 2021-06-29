package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.persistence.CourseRepository;
 
@Service
public class AdminCourseService {

	@Autowired
	CourseRepository courseRepo;

	
	public Course insertCourse(Course course) {
		return courseRepo.save(course);
	}

	public int deleteCourse(Long cno) {
		int ret=0;
		
		try {
			courseRepo.deleteById(cno);
		ret=1;
		}catch(Exception ex) {
		
		}
		return ret;
	}
	

	public List<Course> courseSelectAll(){
		return (List<Course>)courseRepo.findAll();
	}
	
	public Course selectById(Long courseNo) {
		return courseRepo.findById(courseNo).get();
	}
	
	public Course updateOrInsert(Course course) {
		return courseRepo.save(course);
	}

	public Course updateCourse(Course course) {
		return courseRepo.save(course);
	}
	
	
}

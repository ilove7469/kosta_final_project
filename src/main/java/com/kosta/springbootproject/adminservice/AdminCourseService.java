package com.kosta.springbootproject.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.persistence.CourseRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
 
@Service
public class AdminCourseService {

	@Autowired
	CourseRepository courseRepo;
	
	public Page<Course> selectAll(PageVO pvo) { 
		Predicate p = courseRepo.makePredicate(pvo.getType(),pvo.getKeyword()); 
	
		Pageable pageable = pvo.makePaging(0, "courseNo");
		
		Page<Course> result = courseRepo.findAll(p, pageable);
		return result;
		 
	}
	
	public Course insertCourse(Course course) {
		return courseRepo.save(course);
	}

	public int deleteCompany(Long cno) {
		int ret=0;
		
		try {
			courseRepo.deleteById(cno);
		ret=1;
		}catch(Exception ex) {
		
		}
		return ret;
	}
	

//	public List<Course> courseSelectAll(){
//		return (List<Course>)courseRepo.findAll();
//	}
}

package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Teacher;
import com.kosta.springbootproject.persistence.TeacherRepository;
import com.querydsl.core.types.Predicate;

@Service
public class TeacherService {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	public Page<Teacher> selectAll(PageVO pvo){
		Predicate p = teacherRepository.makePredicate(pvo.getType(),pvo.getKeyword()); 
		
		Pageable pageable = pvo.makePaging(0, "teacherNo");
		
		Page<Teacher> result = teacherRepository.findAll(p, pageable);
		return result;
		
	}
	
	public Teacher insertTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public int deleteteacher(Long tno) {
		
		int ret=0;
		
		try {
			teacherRepository.deleteById(tno);
		ret=1;
		}catch(Exception ex) {
		
		}
		return ret;
	}
	
	public Teacher updateTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	public Teacher selectById(Long id) {
		return teacherRepository.findById(id).get();
	}
	
	
	
//	teacher List 전체조회	
	public List<Teacher> selectAll(){
		return (List<Teacher>) teacherRepository.findAll();
	}
}

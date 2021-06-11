package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.Teacher;
import com.kosta.springbootproject.persistence.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	public List<Teacher> selectAll(){
		return (List<Teacher>)teacherRepository.findAll();
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
}

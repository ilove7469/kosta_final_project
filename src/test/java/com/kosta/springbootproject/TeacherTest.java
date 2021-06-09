package com.kosta.springbootproject;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.kosta.springbootproject.model.Teacher;
import com.kosta.springbootproject.persistence.TeacherRepository;

@EnableJpaAuditing
@SpringBootTest
public class TeacherTest {
	
	@Autowired
	TeacherRepository repo;
	
	//@Test
	public void insertTeacher() {
		IntStream.range(1, 10).forEach(i->{
			Teacher teacher = Teacher.builder()
					.teacherId("teacher"+i)
					.teacherName("선생님"+i)
					.teacherPhone("010-4140-402"+i)
					.teacherEmail("email"+i+"@naver.com")
					.build();
			repo.save(teacher);
		});
	}
	
	//@Test
	public void selectTeacher() {
		repo.findAll().forEach(teacher->{
			System.out.println(teacher);
		});
	}
	
	//@Test
	public void updateTeacher() {
		repo.findById(5L).ifPresent(teacher->{
			teacher.setTeacherName("선생님수정");
			repo.save(teacher);
		});
	}
	//@Test
	public void deleteTeacher() {
//		repo.findById(6L).ifPresent(teacher->{
//			repo.delete(teacher);
//		});
		repo.deleteAll();
	}
}

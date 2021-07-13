package com.kosta.springbootproject;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.ClassStateEnumType;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.persistence.AdminRepository;
import com.kosta.springbootproject.persistence.ClassRoomRepository;
import com.kosta.springbootproject.persistence.ClassesRepository;
import com.kosta.springbootproject.persistence.EducationTimeRepository;
import com.kosta.springbootproject.persistence.LectureRepository;
import com.kosta.springbootproject.persistence.TeacherRepository;

@SpringBootTest
public class ClassesTest {
	
	@Autowired
	ClassesRepository crepo;
	@Autowired
	LectureRepository lrepo;
	@Autowired
	TeacherRepository trepo;
	@Autowired
	ClassRoomRepository rrepo;
	@Autowired
	EducationTimeRepository erepo;
	@Autowired
	AdminRepository arepo;
	
	//@Test
	public void test() {
		String[] s = new String[ClassStateEnumType.values().length];
		ClassStateEnumType[] list = ClassStateEnumType.values();
		for(ClassStateEnumType si : list) {
			switch (si) {
			case APPLY:
				System.out.println("등록");
				break;
			case END:
				System.out.println("등록");
				break;		
			default:
				break;
			}
		}
	}
	//@Test
	public void insertClasses() {
		Long lectureId = 58L;	//198
		Long teacherId = 9L;	//70~78
		Long classRoomId = 29L;	//185~190
		Long educationTimeId = 2L;	//80~85
		Long adminId = 59L;		//88~96
		Classes classes = Classes.builder()
				.classOpenDate(Date.valueOf("2021-07-05"))	//시간은 안나오고 날짜만 들어감
				.classCloseDate(Date.valueOf("2021-08-05"))
				.classState(ClassStateEnumType.APPLY)
				.lecture(lrepo.findById(lectureId).get())
				.teacher(trepo.findById(teacherId).get())
				.classRoom(rrepo.findById(classRoomId).get())
				.educationTime(erepo.findById(educationTimeId).get())
				.admin(arepo.findById(adminId).get())
				.build();
		crepo.save(classes);
	}
	
	//@Test
	public void selectClasses() {
		//select문이 몇개나 나올까??(조인이 여러개있으니까???)
		crepo.findAll().forEach(c->{
			System.out.println(c);
		});
	}
	
	//@Test
	public void updateClasses() {
		Long classesId = 203L; //입력
		Long lectureId = 11L;	//다시입력
		Long teacherId = 70L;	//70~78
		Long classRoomId = 185L;	//185~190
		Long educationTimeId = 80L;	//80~85
		Long adminId = 88L;		//88~96
		crepo.findById(classesId).ifPresent(classes->{
			//classes.setClassOpenDate(Date.valueOf("2021-01-11"));
			//classes.setClassDescription("설명입니다!");
			classes.setClassState(ClassStateEnumType.END);
			//classes.setLecture(lrepo.findById(lectureId).get());
			//classes.setTeacher(trepo.findById(teacherId).get());
			//classes.setClassRoom(rrepo.findById(classRoomId).get());
			//classes.setEducationTime(erepo.findById(educationTimeId).get());
			//classes.setAdmin(arepo.findById(adminId).get());
			crepo.save(classes);
		});
	}
	
	//@Test
	public void deleteClasses() {
		Long classesId = 195L;
		crepo.deleteById(classesId);
	}
}

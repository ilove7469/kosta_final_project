package com.kosta.springbootproject;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.persistence.CourseRepository;
import com.kosta.springbootproject.persistence.SubjectRepository;

@SpringBootTest
public class CourseTest {
	
	@Autowired
	CourseRepository repo;
	@Autowired
	SubjectRepository srepo;
	//@Test
	public void selectCourseWithLecture() {
		Subject s = srepo.findById(79L).get();
		
		
		repo.getCourseWithLecture(s).forEach(i->{
			System.out.println(Arrays.toString(i));
		});
	}
	
	//@Test
	public void insertCourse() {
		Subject sub = new Subject();
		sub.setSubjectNo(15L);
		
		Certificate certi = new Certificate();
		certi.setCertiNo(4L);
		
		Course course = Course.builder()
				.courseName("대용량 웹서비스를 위한 MSA Full-Stack SW 개발자 양성과정")
				.courseTotalTrainTime(808)
				.courseTotalDay(101)
				.coursePrice(1000000)
				.courseCapacity(25)
				.coursePurpose("")
				.courseContent("")
				.courseTarget("")
				.certificate(certi)
				.subject(sub)
				.build();
			repo.save(course);
	}
	
	
	//@Test
	public void searchAllTest() {
		repo.findAll().forEach(course->{
			System.out.println(course.toString());
		});
	}
	
	//@Test
	public void updateTest() {
		 repo.findById(168L).ifPresent(d ->{
				Subject sub = new Subject();
				sub.setSubjectNo(131L);
			 
			 d.setSubject(sub);
			 d.setCourseName("[비대면]딥러닝 생성 모델의 이해와 구현 [컨소시엄-무료]");
			 d.setCourseTotalTrainTime(15);
			 d.setCoursePurpose("인공지능 플랫폼을 구축하기 위하여인공지능 학습 기능을 구현하는 능력을 함양함으로써 현업 개발능력을 극대화할 수 있음");
			 d.setCourseContent("Generative Models 개요\n"
			 		+ "AE(AutoEncoder) 구조의 이해와 기능 구현\n"
			 		+ "VAE(Variational AutoEncoder) 구조의 이해와 기능 구현\n"
			 		+ "GAN 알고리즘의 이해와 기능 구현\n"
			 		+ "CycleGAN 알고리즘 이해와 기능 구현\n"
			 		+ "Sketch-RNN 구조와 알고리즘의 이해");
			 d.setCourseTarget("수강 대상\n"
			 		+ "- 네트워크 기반 응용소프트웨어 분야로 직무능력향상을 희망하는 재직자\n"
			 		+ "\n"
			 		+ "신청 자격\n"
			 		+ "협약기업 재직자(고용보험 납부) 및 회원사 재직자 지원가능\n"
			 		+ "\n"
			 		+ "- 교육신청은 대기상태로 협회의 교육 승인 후 수강이 가능합니다.\n"
			 		+ "- 교육 대기상태에서는 수강에 제한이 있을 수 있으니 이 점 참고하시기 바랍니다.\n"
			 		+ "- 개강안내 메일 및 문자는 교육 승인되신 분들에 한하여 개강 일주일 전에 발송됩니다.\n"
			 		+ "- 과정별 모집인원에 따라 변동 및 폐강 될 수 있으며, 이는 개강 일주일 전에 확정됩니다.");
			 repo.save(d);
		 });
	}
	
	//@Test
	public void deleteTest() {
		 repo.findById(171L).ifPresent(d ->{
			 repo.delete(d);
		 });
		 repo.findById(170L).ifPresent(d ->{
			 repo.delete(d);
		 });
	}
	
	
	
}

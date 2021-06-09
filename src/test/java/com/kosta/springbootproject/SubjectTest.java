package com.kosta.springbootproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.persistence.SubjectRepository;

@SpringBootTest
public class SubjectTest {
	
	@Autowired
	SubjectRepository repo;
	
	//@Test
	public void SubjectCourse() {
		
		Trainee tr = new Trainee();
		tr.setTraineeNo(2L);
		IntStream.range(1, 3).forEach(i->{ //for(int i=1; i<11; i++){} 와 같다.
		Subject sub = Subject.builder()
				.subPriority(0)
				.subName("파이썬"+i)
				.subExplain("파이썬 좋습니다. 많이 들어주세요."+i)
				.trainee(tr)
				.build();
		repo.save(sub);
		});
	}
	
	//@Test
	public void searchAllTest() {
		repo.findAll().forEach(d->{
			System.out.println(d.toString());
		});
	}
	
	//@Test
	public void updateTest() {
		 repo.findById(133L).ifPresent(d ->{
			 d.setSubPriority(2);
			 repo.save(d);
		 });
	}
	
	//@Test
	public void deleteTest() {
		 repo.findById(135L).ifPresent(d ->{
			 repo.delete(d);
		 });
	}
}

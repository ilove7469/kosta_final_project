package com.kosta.springbootproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.EducationPartEnumType;
import com.kosta.springbootproject.model.EducationTime;
import com.kosta.springbootproject.persistence.EducationTimeRepository;

@SpringBootTest
public class EducationTimeTest {
	
	@Autowired
	EducationTimeRepository repo;
	
	//@Test
	public void insertET() {
		IntStream.range(5, 9).forEach(i->{
			EducationTime et = EducationTime.builder()
					.educationTimeName("주말("+i+"시간)")
					.educationPartType(EducationPartEnumType.MORNING)
					.educationTimeType(i+"시~"+(i+3)+"시")
					.build();
			repo.save(et);
		});
	}
	
	//@Test
	public void updateET() {
		Long id = 80L;
		repo.findById(id).ifPresent(et->{
			et.setEducationPartType(EducationPartEnumType.EVENING);
			repo.save(et);
		});
	}
	
	//@Test
	public void selectET() {
		repo.findAll().forEach(et->{
			System.out.println(et);
		});
	}
	
	//@Test
	public void deleteET() {
		repo.deleteById(81L);
	}
}

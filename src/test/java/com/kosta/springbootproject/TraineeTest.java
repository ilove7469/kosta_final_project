package com.kosta.springbootproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.persistence.TraineeRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class TraineeTest {

	@Autowired
	TraineeRepository repo;
	
//	@Test
	public void traineeInsert() {
		Trainee t1 = new Trainee();
		t1.setTraineeName("재직자");
		repo.save(t1);
		
		Trainee t2 = new Trainee();
		t2.setTraineeName("채용예정자");
		repo.save(t2);
	}
	
//	@Test
	public void selectAll() {
		repo.findAll().forEach(t -> {
			System.out.println(t);
		});
	}
}

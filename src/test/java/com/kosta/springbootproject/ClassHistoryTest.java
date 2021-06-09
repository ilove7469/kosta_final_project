package com.kosta.springbootproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.ClassHistoryEnumType;
import com.kosta.springbootproject.model.ClassStateEnumType;
import com.kosta.springbootproject.persistence.ClassHistoryRepository;
import com.kosta.springbootproject.persistence.ClassesRepository;
import com.kosta.springbootproject.persistence.UserRepository;


@SpringBootTest
@EnableJpaAuditing
public class ClassHistoryTest {
	
	@Autowired
	ClassHistoryRepository repo;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	ClassesRepository crepo;
	
//	@Test
	public void insertClassHistory() {
		IntStream.range(1, 11).forEach(i -> {
			ClassHistory ch = ClassHistory.builder()
					.classHistoryState(ClassHistoryEnumType.WAIT)
					.user(urepo.findById(140L + i).get())
					.classes(crepo.findById(203L).get()) //class만들어지면
					.build();
			repo.save(ch);
			
		});
	}

//	@Test
	public void selectAll() {
		repo.findAll().forEach(c -> {
			System.out.println(c);
		});
	}
	
//	@Test
	public void updateClassHistory() {
		repo.findById(205L).ifPresent(ch -> {
			ch.setClassHistoryState(ClassHistoryEnumType.COMMIT);
			repo.save(ch);
		});
	}
	
	@Test
	public void delete() {
		repo.deleteById(214L);
	}
}

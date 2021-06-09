package com.kosta.springbootproject;

import java.sql.Date;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.kosta.springbootproject.model.User;
import com.kosta.springbootproject.persistence.CompanyRepository;
import com.kosta.springbootproject.persistence.TraineeRepository;
import com.kosta.springbootproject.persistence.UserRepository;

import lombok.extern.java.Log;

@Log
@EnableJpaAuditing
@SpringBootTest
public class UserTest {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	CompanyRepository crepo;
	
	@Autowired
	TraineeRepository trepo;
	
//	@Test
	public void insertUser() {
		IntStream.range(1, 21).forEach(i -> {
			User user = User.builder()
					.userName("이름"+i)
					.userId("아이디"+i)
					.userPw("비밀번호"+i)
					.userPhone("전화번호"+i)
					.userEmail("이메일"+i)
					.userAddress("서울시")
					.userBirth(Date.valueOf("2000-06-"+i))
					.company(crepo.findById(20L).get())
					.trainee(trepo.findById(2L).get())
					.build();
			repo.save(user);
		});
		IntStream.range(21, 31).forEach(i -> {
			User user = User.builder()
					.userName("이름"+i)
					.userId("아이디"+i)
					.userPw("비밀번호"+i)
					.userPhone("전화번호"+i)
					.userEmail("이메일"+i)
					.userAddress("부산시")
					.userBirth(Date.valueOf("2012-07-"+i))
					.company(crepo.findById(36L).get())
					.trainee(trepo.findById(3L).get())
					.build();
			repo.save(user);
		});
	}
	
//	@Test
	public void selectAll() {
		repo.findAll().forEach(c -> {
			System.out.println(c);
		});
	}

//	@Test
	public void delete() {
		repo.deleteById(167L);
	}
	
	@Test
	public void update() {
		repo.findById(138L).ifPresent(user -> {
			user.setUserAddress("인천시");
			user.setUserEmail("new@mail");
			user.setCompany(crepo.findById(36L).get());
			repo.save(user);
		});
	}

}

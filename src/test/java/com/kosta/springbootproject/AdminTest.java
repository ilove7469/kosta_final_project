package com.kosta.springbootproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.Admin;
import com.kosta.springbootproject.persistence.AdminRepository;

@SpringBootTest
public class AdminTest {

	@Autowired
	AdminRepository repo;
	
	//@Test
	public void insertAdmin() {
		IntStream.range(1, 10).forEach(i->{
			Admin admin = Admin.builder()
					.adminName("관리자"+i)
					.adminId("gwanri"+(i+10))
					.adminPw("1234")
					.adminPhone("010-2223-123"+i)
					.adminEmail("bibi"+i+"@naver.com")
					.build();
			repo.save(admin);
		});
	}
	
	//@Test
	public void selectAdmin() {
		repo.findAll().forEach(admin->{
			System.out.println(admin);
		});
	}
	
	//@Test
	public void updateAdmin() {
		repo.findById(88L).ifPresent(admin->{
			admin.setAdminName("관리자수정");
			repo.save(admin);
		});
	}
	//@Test
	public void deleteAdmin() {
		repo.findById(90L).ifPresent(teacher->{
			repo.delete(teacher);
		});
		//repo.deleteAll();
	}
}

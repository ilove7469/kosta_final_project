package com.kosta.springbootproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.ClassRoom;
import com.kosta.springbootproject.persistence.ClassRoomRepository;
import com.kosta.springbootproject.persistence.LectureHallRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class ClassRoomTests {
	
	@Autowired
	ClassRoomRepository classRoomRepo;
	@Autowired
	LectureHallRepository lectureHallRepo;
	
	//@Test
	public void insertClassRoom0() {
		ClassRoom c = new ClassRoom();
		c.setClassRoomName("11강의실");
		c.setClassRoomCapacity(25);
		c.setLectureHall(lectureHallRepo.findById(61L).get());
		classRoomRepo.save(c);
	}
	//@Test
	public void insertClassRoom1() {
		ClassRoom c = new ClassRoom();
		c.setClassRoomName("12강의실");
		c.setClassRoomCapacity(20);
		c.setLectureHall(lectureHallRepo.findById(60L).get());
		classRoomRepo.save(c);
	}
	//@Test
	public void insertClassRoom2() {
		ClassRoom c = new ClassRoom();
		c.setClassRoomName("6강의실");
		c.setClassRoomCapacity(25);
		c.setLectureHall(lectureHallRepo.findById(59L).get());
		classRoomRepo.save(c);
	}
	//@Test
	public void insertClassRoom3() {
		ClassRoom c = new ClassRoom();
		c.setClassRoomName("7강의실");
		c.setClassRoomCapacity(20);
		c.setLectureHall(lectureHallRepo.findById(59L).get());
		classRoomRepo.save(c);
	}
	
	//@Test
	public void update() {
		classRoomRepo.findById(137L).ifPresent(c->{
			c.setClassRoomName("6강의실");
			c.setClassRoomCapacity(20);
			c.setLectureHall(lectureHallRepo.findById(59L).get());
			classRoomRepo.save(c);
		});
		
	}
	
	
	//@Test
	public void deleteClassRoom() {
		Long id=128L;
		classRoomRepo.deleteById(id);
	}
	
	//@Test
	public void selectAll() {
		classRoomRepo.findAll().forEach(all->{
			log.info("강의실 정보" +all.toString());
		});
	}
}

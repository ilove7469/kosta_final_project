package com.kosta.springbootproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.kosta.springbootproject.model.LectureHall;
import com.kosta.springbootproject.persistence.LectureHallRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class LectureHallTests {
	
	@Autowired
	LectureHallRepository lectureHallRepo;
	
	
	//@Test
	public void insertLectureHall1() {
		LectureHall h = new LectureHall();
		h.setLectureHallAddress("(13637) 경기도 성남시 분당구 성남대로 34, 6층(구미동, 하나프라자빌딩)");
		h.setLectureHallName("오리");	
		h.setLectureHallPhone("031-606-9304");
		lectureHallRepo.save(h);
	}
	//@Test
	public void insertLectureHall2() {
		LectureHall h = new LectureHall();
		h.setLectureHallAddress("(08506) 서울특별시 금천구 가산디지털1로 151 이노플렉스 1차 2층");
		h.setLectureHallName("가산");	
		h.setLectureHallPhone("031-606-9304");
		lectureHallRepo.save(h);
	}
	//@Test
	public void insertLectureHall3() {
		LectureHall h = new LectureHall();
		h.setLectureHallAddress("(08503) 서울 금천구 가산디지털1로 181 가산더블유센터 3층, 308~310호");
		h.setLectureHallName("가산2");	
		h.setLectureHallPhone("031-606-9304");
		lectureHallRepo.save(h);
	}
	
	//@Test
	public void selectById() {
		LectureHall h = lectureHallRepo.findById(4L).get();
	 	log.info("LectureHall :" + h.toString());
	}
	


	//@Test
	public void update() {
		lectureHallRepo.findById(4L).ifPresent(h->{
			h.setLectureHallAddress("(08503) 서울 금천구 가산디지털1로 181 가산더블유센터 3층, 308~310호");
			h.setLectureHallName("가산2");	
			h.setLectureHallPhone("031-606-9304");
			lectureHallRepo.save(h);
		});
	}
	
	//@Test
	public void deleteLectureHall() {
		Long id=4L;
		lectureHallRepo.deleteById(id);
	}

}

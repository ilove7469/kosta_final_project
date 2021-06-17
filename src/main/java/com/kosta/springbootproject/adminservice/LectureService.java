package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.persistence.LectureRepository;
import com.querydsl.core.types.Predicate;

@Service
public class LectureService {

	@Autowired
	LectureRepository lectureRepo;

	public List<Lecture> selectAll() {
		return (List<Lecture>) lectureRepo.findAll();
	}

	public Page<Lecture> selectAll(PageVO pvo) {
		Predicate p = lectureRepo.makePredicate(pvo.getType(), pvo.getKeyword());

		Pageable pageable = pvo.makePaging(0, "lecturePlanNo");

		Page<Lecture> result = lectureRepo.findAll(p, pageable);
		return result;

	}

	public Lecture insertLecture(Lecture lecture) {
		return lectureRepo.save(lecture);
	}

	public int deleteLecture(Long cno) {
		int ret = 0;

		try {
			lectureRepo.deleteById(cno);
			ret = 1;
		} catch (Exception ex) {

		}
		return ret;
	}

}

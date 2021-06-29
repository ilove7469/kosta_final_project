package com.kosta.springbootproject.adminservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.persistence.LectureRepository;

@Service
public class LectureService {

	@Autowired
	LectureRepository lectureRepo;

	public List<Lecture> selectAll() {
		return (List<Lecture>) lectureRepo.findAll();
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
	
	public Lecture selectById(Long lecturePlanNo) {
		return lectureRepo.findById(lecturePlanNo).get();
	}
	
	public boolean insertOrUpdate(Lecture lecture) {
		boolean check = lectureRepo.findByCourseAndLecturePlanYear(lecture.getCourse(), lecture.getLecturePlanYear())
						.isEmpty();
		boolean result = false;
		if(check) {
			//디비에 자료 없으면
			lectureRepo.save(lecture);
			result=true;
		}else {
			//디비에 자료 있으면
			if(lecture.getLecturePlanNo()!=null) {
				check =lectureRepo.findById(lecture.getLecturePlanNo()).isPresent();
				if(check) {
					lectureRepo.save(lecture);	
				}
			}
		}
		return result;
	}

}

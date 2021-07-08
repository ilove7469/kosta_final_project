package com.kosta.springbootproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.persistence.CourseRepository;
import com.kosta.springbootproject.persistence.LectureRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class LectureTests {
	
	@Autowired
	LectureRepository lectureRepo;
	@Autowired
	CourseRepository courseRepo;
	
//	@Test
	public void insertClassRoom() {
		Lecture lecture = new Lecture();
		//강의계획년
		lecture.setLecturePlanYear(2021);
		//연간개설횟수
		lecture.setLectureOpenCount(4); 
		//목표정원
		lecture.setLectureCapacity(100);
		//교육대상
		lecture.setLectureTarget("<교육대상>\r\n"
				+ "협약기업 재직자(고용보험 납부) 및 회원사 재직자 지원가능\n"
				+ "\n"
				+ "※ 교육신청은 대기상태로 협회의 교육 승인 후 수강이 가능합니다.\n"
				+ "※ 교육 대기상태에서는 수강에 제한이 있을 수 있으니 이 점 참고하시기 바랍니다.\n"
				+ "※ 개강안내 메일 및 문자는 교육 승인되신 분들에 한하여 개강 일주일 전에 발송됩니다.\n"
				+ "※ 과정별 모집인원에 따라 변동 및 폐강 될 수 있으며, 이는 개강 일주일 전에 확정됩니다.\n"
				+ "\n"
				+ "<훈련대상 요건>\n"
				+ "- 개발 언어 알고 있는 사람 (Java, C/C++, Python, R 무관)\n"
				+ "- 데이터 분석에 관심이 많은 사람\n"
				+ "- 기초 통계 지식 보유 (옵션)\n"
				+ "\n"
				+ "<선수 과목>\n"
				+ "- R을 이용한 데이터 탐색 및 시각화\n"
				+ "\n"
				+ "<패널티 안내>\n"
				+ "선착순으로 마감되는 교육과정일 경우 교육을 받고 싶어도 받지 못하는 분들이 계실 수 있습니다. 원활한 강의 진행을 위해 아래 경우에 패널티가 부여됨을 알려드립니다.\n"
				+ "\n"
				+ "- 신청 후 개인사정에 의해 교육 당일 사전 연락없이 3회 이상 결석 시.\n"
				+ "- 신청 후 개강 3일 이내에 수강 취소한 경우가 3회 이상일 시.\n"
				+ "- 강좌 미수료 3회 이상시.\n"
				+ "- 교육담당자에게 별도 전달 없이 20분 이상 자리를 이탈하는 경우\n"
				+ "\n"
				+ "패널티를 3회 이상 부여받은 수강생은 3개월 간 수강제한 처리됩니다.\n"
				+ "이러한 수강생이 3분 이상일 경우 개인 포함 재직 중이신 회사의 모든 임직원은 3개월 수강제한을 받습니다."); 
		//패널티
		lecture.setLecturePenalty("선착순으로 마감되는 교육과정일 경우 교육을 받고 싶어도 받지 못하는 분들이 계실 수 있습니다.\n"
				+ "원활한 강의 진행을 위해 아래 경우에 패널티가 부여됨을 알려드립니다.\n"
				+ "\r\n"
				+ "- 신청 후 개인사정에 의해 교육 당일 사전 연락없이 결석 시.\n"
				+ "- 신청 후 개강 3일 이내에 수강 취소한 경우.\n"
				+ "- 강좌 미수료 시.\n"
				+ "- 교육담당자에게 별도 전달 없이 20분 이상 자리를 이탈하는 경우\n"
				+ "\n"
				+ "패널티를 3회 이상 부여받은 수강생은 3개월 간 수강제한 처리됩니다.\n"
				+ "패널티 5회 누적 시 개인 포함 재직 중이신 회사의 모든 임직원은 3개월 수강제한을 받습니다."); 
		//비대면
		lecture.setLectureOnline("해당 과정은 Zoom을 통한 비대면 과정입니다.\n"
				+ "교육 수강 시 교육생 캠 활성화가 필수 이오니 과정 개강 전까지 확인 부탁드립니다.");  
		//문의
		lecture.setLectureAsk("[판교 교육장] 031-606-9337, [가산 교육장] 02-6278-9352");  
		//코스 아이디 87,136,168,169
		lecture.setCourse(courseRepo.findById(36L).get());
		
		lectureRepo.save(lecture);
	
	}
	
	
	//@Test
	public void updateClassRoom() {
		lectureRepo.findById(198L).ifPresent(lecture->{
		//Lecture lecture = new Lecture();
		//강의계획년
		lecture.setLecturePlanYear(2021);
		//연간개설횟수
		lecture.setLectureOpenCount(5); 
		//목표정원
		lecture.setLectureCapacity(100);
		//교육대상
		lecture.setLectureTarget("수강 대상\r\n"
				+ "- 네트워크 기반 응용소프트웨어 분야로 직무능력향상을 희망하는 재직자\n"
				+ "\n"
				+ "신청 자격\r\n"
				+ "협약기업 재직자(고용보험 납부) 및 회원사 재직자 지원가능\n"
				+ "\n"
				+ "- 교육신청은 대기상태로 협회의 교육 승인 후 수강이 가능합니다.\n"
				+ "- 교육 대기상태에서는 수강에 제한이 있을 수 있으니 이 점 참고하시기 바랍니다.\n"
				+ "- 개강안내 메일 및 문자는 교육 승인되신 분들에 한하여 개강 일주일 전에 발송됩니다.\n"
				+ "- 과정별 모집인원에 따라 변동 및 폐강 될 수 있으며, 이는 개강 일주일 전에 확정됩니다."); 
		//패널티
		lecture.setLecturePenalty("선착순으로 마감되는 교육과정일 경우 교육을 받고 싶어도 받지 못하는 분들이 계실 수 있습니다.\n"
				+ "원활한 강의 진행을 위해 아래 경우에 패널티가 부여됨을 알려드립니다.\n"
				+ "\r\n"
				+ "- 신청 후 개인사정에 의해 교육 당일 사전 연락없이 결석 시.\n"
				+ "- 신청 후 개강 3일 이내에 수강 취소한 경우.\n"
				+ "- 강좌 미수료 시.\n"
				+ "- 교육담당자에게 별도 전달 없이 20분 이상 자리를 이탈하는 경우\n"
				+ "\n"
				+ "패널티를 3회 이상 부여받은 수강생은 3개월 간 수강제한 처리됩니다.\n"
				+ "패널티 5회 누적 시 개인 포함 재직 중이신 회사의 모든 임직원은 3개월 수강제한을 받습니다."); 
		//비대면
		lecture.setLectureOnline("해당 과정은 Zoom을 통한 비대면 과정입니다.\n"
				+ "교육 수강 시 교육생 캠 활성화가 필수 이오니 과정 개강 전까지 확인 부탁드립니다.");  
		//문의
		lecture.setLectureAsk("[판교 교육장] 031-606-9337, [가산 교육장] 02-6278-9352");  
		//코스 아이디 87,136,168,169
		lecture.setCourse(courseRepo.findById(87L).get());
		
		lectureRepo.save(lecture);
		});
	}
	
	//@Test
	public void deleteClassRoom() {
		Long id=197L;
		lectureRepo.deleteById(id);
	}
	
	//@Test
	public void selectAll() {
		lectureRepo.findAll().forEach(all->{
			log.info("강의정보" +all.toString());
		});
	}
	
	
}

package com.kosta.springbootproject.usercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.userservice.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	CourseService cservice;
	
	//해더
	@ResponseBody
	@GetMapping("/fragments/headerUser")
	public List<Trainee> userHeader() {
		List<Trainee> traineeList = cservice.findTraineeAll();
		return traineeList;
	}
	
	@ResponseBody
	@GetMapping("/user/userMain/{traineeNo}")
	public ResponseEntity<List<Subject>> userMain(@PathVariable Long traineeNo) {
		return new ResponseEntity<>(cservice.findSubjectByTraineeNo(traineeNo),HttpStatus.OK);
	}
	
	//메인
	@GetMapping("/user/userMain")
	public void userMain(Model model) {
		
	}
	
	//과목
	@GetMapping("/courseInfo/{subjectNo}")	//주소 네이밍 어떻게 할지
	public ModelAndView searchLecture(@PathVariable Long subjectNo) {
		ModelAndView mv = new ModelAndView("/user/userCourse");
		List<Object[]> CourseList = cservice.findCourseWithLecture(subjectNo); 
		mv.addObject("CourseList", CourseList);
		return mv;
	}
	
	//과목 디테일
	@GetMapping("/courseInfo/{courseNo}/{lectureYear}")
	public ModelAndView searchCourseInfo(@PathVariable Course courseNo, @PathVariable int lectureYear) {
		ModelAndView mv = new ModelAndView("/user/userCourseInfo");
		Lecture lecture = cservice.findLecturByCourse(courseNo,lectureYear);
		List<Classes> classList = cservice.findClassByLecture(lecture);
		mv.addObject("lecture", lecture);
		mv.addObject("classList", classList);
		return mv;
	}
	
	//강의신청 1단계(신청하기)
	@GetMapping("/course/enroll/{classNo}")
	public ModelAndView searchEnroll(@PathVariable Long classNo) {
		ModelAndView mv = new ModelAndView("/user/userEnroll");
		Classes classInfo = cservice.findClassByClassNO(classNo);
		mv.addObject("class",classInfo);
		return mv;
	}
	
	//강의신청 2단계(접수하기) (enrollDetail)
	@GetMapping("/course/enroll/info/{classNo}")
	public ModelAndView searchEnrollDetail(@PathVariable Long classNo) {
		ModelAndView mv;
		//id체크
		//id있으면
		mv = new ModelAndView("/user/userEnrollDetail");
		Classes classInfo = cservice.findClassByClassNO(classNo);
		mv.addObject("class",classInfo);
		//id로 유저찾기 >>repo에서 유저정보 찾아서 뿌리기 밑에꺼 시큐리티만들어지면 바꿔야함
		//Principal principal을 매개변수로 받아서 정보 가져오면 된다고 함
		String userId = "SpringVeryHard";
		Users userInfo = cservice.findUserByUserID(userId);
		mv.addObject("user",userInfo);
		//mv.addObject("userNo",userNo);
		//id 없으면
		//mv = new ModelAndView("/user/login");
		return mv;
	}
	
	@PostMapping("/course/enroll/info")
	public String insertClassHistory(ClassHistory ch) {
		//System.out.println(ch.getUser());
		Boolean check = cservice.updateClassHistory(ch);
		if(!check) {
			//주소 변경 고려(나중에 수강신청 이미 했다고 메세지 창 or 다른 페이지) 
			return "redirect:/course/enroll/info/"+ch.getClasses().getClassNo();
		}
		return "/user/userEnrollSuccess";
	}
	
	@GetMapping("/user/userEnrollSidebar")
	public String enrollSidebar(Model model,Long classNo) {
		Classes classInfo = cservice.findClassByClassNO(classNo);
		model.addAttribute("class",classInfo);
		return "/user/userEnrollSidebar";
	}
	@GetMapping("/user/userInsert")
	public void userInsert() {
		
	}
	@GetMapping("/user/userInfo")
	public void userInfo() {
		
	}
}

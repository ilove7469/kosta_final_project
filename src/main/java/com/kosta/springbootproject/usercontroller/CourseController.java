package com.kosta.springbootproject.usercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.userservice.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	CourseService cservice;
	
	@GetMapping("/user/userMain")
	public void userMain(Model model) {
		List<Trainee> traineeList = cservice.findTraineeAll();
		model.addAttribute("traineeList", traineeList);
	}
	
	@ResponseBody
	@GetMapping("/user/userMain/{traineeNo}")
	public ResponseEntity<List<Subject>> userMain(@PathVariable Long traineeNo) {
		System.out.println("왔니?");	
		return new ResponseEntity<>(cservice.findSubjectByTraineeNo(traineeNo),HttpStatus.OK);
			
	}
	
	@GetMapping("/course/{subjectNo}")	//주소 네이밍 어떻게 할지
	public ModelAndView searchLecture(@PathVariable Long subjectNo) {
		ModelAndView mv = new ModelAndView("/user/userCourse");
		List<Object[]> CourseList = cservice.findCourseWithLecture(subjectNo); 
		mv.addObject("CourseList", CourseList);
		return mv;
	}
	@GetMapping("/courseInfo/{courseNo}/{lectureYear}")
	public ModelAndView searchCourseInfo(@PathVariable Long courseNo, @PathVariable int lectureYear) {
		ModelAndView mv = new ModelAndView("/user/userCourseInfo");
		Lecture lecture = cservice.findLecturByCourse(courseNo,lectureYear);
		List<Classes> classList = cservice.findClassByLecture(lecture);
		mv.addObject("lecture", lecture);
		mv.addObject("classList", classList);
		return mv;
	}
}

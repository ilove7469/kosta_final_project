package com.kosta.springbootproject.admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.springbootproject.adminservice.AdminManageClassService;
import com.kosta.springbootproject.adminservice.CertificateService;
import com.kosta.springbootproject.adminservice.ClassHistroyService;
import com.kosta.springbootproject.adminservice.ClassRoomService;
import com.kosta.springbootproject.adminservice.EducationTimeService;
import com.kosta.springbootproject.adminservice.LectureHallService;
import com.kosta.springbootproject.adminservice.SubjectService;
import com.kosta.springbootproject.adminservice.TraineeService;
import com.kosta.springbootproject.adminservice.UserService;
import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.ClassRoom;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.EducationTime;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.LectureHall;
import com.kosta.springbootproject.model.PageMaker;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.User;

@Controller
public class HyunController {
	
	@Autowired
	UserService userService;
	@Autowired
	CertificateService certiService;
	@Autowired
	LectureHallService lectureHallService;
	@Autowired
	ClassRoomService classRoomService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	EducationTimeService educationTimeService;
	@Autowired
	AdminManageClassService adminManageClassService;
	@Autowired
	ClassHistroyService classHistroyService;
	@Autowired
	TraineeService traineeService;

//	강의운영 - 수강신청 관리메인
	@GetMapping("/admin/manageclassmain")
	public void selectAllClassHistory(Model model) {

		List<Classes> classesResult = adminManageClassService.selectAll();
		List<ClassHistory> classHistroyResult = classHistroyService.selectAll();
		model.addAttribute("ClassesList",classesResult);
		for(ClassHistory classhistroy : classHistroyResult) {
			classhistroy.getClasses().getClassNo();
		}
		model.addAttribute("ClassHistroyList",classHistroyResult);
	}
	
//	강의운영 - 수강신청 관리 강의 상세페이지
	@GetMapping("/admin/manageclassdetail/{classNo}")
	public ModelAndView searchClassDetail(@PathVariable Long classNo) {
		ModelAndView mv = new ModelAndView("/admin/manageClassDetail");
		List<ClassHistory> classhistorylist = adminManageClassService.findClassHistoryByClasses(classNo);
		mv.addObject("classHistoryList", classhistorylist);
		return mv;
	}
	
//	메타정보 - 주제 메인
	@GetMapping("/admin/subjectmain")
	public void selectAllSubject(Model model, PageVO pagevo) {
		Page<Subject> result = subjectService.selectAll(pagevo);
		model.addAttribute("SubjectResult",result);
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
	}
	
//	메타정보 - 주제 추가 Get
	@GetMapping("/admin/subjectadd")
	public String insertSubject(Model model) {
		model.addAttribute("traineeList",traineeService.selectAll());
		return "/admin/subjectInsert";
	}
	
//	메타정보 - 주제 추가 Post
	@PostMapping("/admin/subjectadd")
	public String insertSubject(Subject subject) {

		subjectService.updateOrInsert(subject);
		return "redirect:/admin/subjectmain";
	}	


//	메타정보 - 교육시간 메인
	@GetMapping("/admin/educationtimemain")
	public void selectAllEducationTime(Model model, PageVO pagevo) {
		Page<EducationTime> result = educationTimeService.selectAll(pagevo);
		model.addAttribute("EducationTimeResult",result);
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
	}
	
//	메타정보 - 교육시간 추가 Get
	@GetMapping("/admin/educationtimeadd")
	public String insertEducationTime() {
		return "/admin/educationTimeInsert";
	}
	
//	메타정보 - 교육시간 추가 Post
	@PostMapping("/admin/educationtimeadd")
	public String insertEducationTime(EducationTime educationTime) {

		educationTimeService.updateOrInsert(educationTime);
		return "redirect:/admin/educationtimemain";
	}		

	
//	기본정보 - 회원 메인
	@GetMapping("/admin/usermain")
	public void selectAllUser(Model model, PageVO pagevo) {
		Page<User> result = userService.selectAll(pagevo);
		model.addAttribute("UserResult",result);
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
	}

//	기본정보 - 수료증 메인	
	@GetMapping("/admin/certificatemain")
	public void selectAllCerti(Model model) {
		model.addAttribute("certiList",certiService.selectAll());
	}

//	기본정보 - 수료증 추가 Get
	@GetMapping("/admin/certificateadd")
	public String insertCerti() {
		return "/admin/certificatedetail";
	}
	
//	기본정보 - 수료증 추가 Post
	@PostMapping("/admin/certificateadd")
	public String insertCerti(Certificate certi) {
		certiService.updateOrInsert(certi);
		return "redirect:/admin/certificatemain";
	}
	
	/*
	@RequestMapping("/admin/certificatemodify")
	public String updateCerti() {
		return "/admin/certificatedetail";
	}
	*/

//	기본정보 - 강의장 메인
	@GetMapping("/admin/lecturehallmain")
	public void selectAllLectureHall(Model model) {
		model.addAttribute("lectureHallList",lectureHallService.selectAll());
	}

//	기본정보 - 강의장 추가 Get
	@GetMapping("/admin/lecturehalladd")
	public String insertLectureHall() {
		return "/admin/lecturehalldetail";
	}
	
//	기본정보 - 강의장 추가 Post
	@PostMapping("/admin/lecturehalladd")
	public String insertLectureHall(LectureHall lectureHall) {
		lectureHallService.updateOrInsert(lectureHall);
		return "redirect:/admin/lecturehallmain";
	}
	
	
//	기본정보 - 강의실 메인
	@RequestMapping("/admin/classroommain")
	public void selectAllClassRoom(Model model) {
		model.addAttribute("classRoomList",classRoomService.selectAll());
	}

//	기본정보 - 강의실 추가 Get
	@GetMapping("/admin/classroomadd")
	public String insertClassRoom(Model model) {
		List<LectureHall> lectureHallList = lectureHallService.selectAll();
		model.addAttribute("lectureHallList",lectureHallList);
		return "/admin/classroomdetail";
	}

//	기본정보 - 강의실 추가 Post
	@PostMapping("/admin/classroomadd")
	public String insertClassRoom(ClassRoom classRoom) {
		classRoomService.updateOrInsert(classRoom);
		return "redirect:/admin/classroommain";
	}
	
	
	
	

	
	
	
}

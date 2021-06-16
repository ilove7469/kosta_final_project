package com.kosta.springbootproject.admincontroller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.springbootproject.adminservice.ClassesService;
import com.kosta.springbootproject.adminservice.CompanyService;
import com.kosta.springbootproject.adminservice.AdminCourseService;
import com.kosta.springbootproject.adminservice.CertificateService;
import com.kosta.springbootproject.adminservice.LectureService;
import com.kosta.springbootproject.adminservice.SubjectService;
import com.kosta.springbootproject.adminservice.TeacherService;
import com.kosta.springbootproject.adminservice.TraineeService;
import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.PageMaker;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.model.Teacher;


@Controller
public class TaeController {
	
	@Autowired
	CompanyService companyService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	AdminCourseService courseService;
	//@Autowired
	ClassesService classesService;
	@Autowired
	LectureService lectureService;
	
	@Autowired
	SubjectService subjectservice;
	@Autowired
	CertificateService certificateservice;
	@Autowired
	TraineeService traineeservice;

	

//회사main
	@RequestMapping("/admin/companyList")
	public void companySelectAll(Model model, PageVO pagevo, HttpServletRequest request ) {
		Page<Company> result = companyService.selectAll(pagevo);
		
		model.addAttribute("companylist", result);
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
	}
	
//회사추가
	@GetMapping("/admin/companyInsert")
	public void companyInsert() {
		
	}
	 
	@PostMapping("/admin/companyInsert")
	public String companyInsertPost(Company company, RedirectAttributes rttr) {
		
	
		
		Company ins_company = companyService.insertCompany(company);
		
		rttr.addFlashAttribute("resultMessage", ins_company==null?"입력실패":"입력성공");
		return "redirect:/admin/companyList";
	}
	
//회사삭제
	@GetMapping("/admin/companyDelete")
	public String companyDelete(Long cno,  RedirectAttributes rttr) {
		int ret = companyService.deleteCompany(cno);
		System.out.println("삭제:" + ret);
		rttr.addFlashAttribute("resultMessage", ret==0?"삭제실패":"삭제성공");
		return "redirect:/admin/companyList";
	}
	
	
	
//강사main
	@RequestMapping("/admin/teacherList")
	public void teacherSelectAll(Model model, PageVO pagevo, HttpServletRequest request) {
		
		Page<Teacher> result = teacherService.selectAll(pagevo);

		
		model.addAttribute("teacherlist", result);
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
		
	}

//강사추가
	@GetMapping("/admin/teacherInsert")
	public void teacherInsert() {
		
	} 
	 
	@PostMapping("/admin/teacherInsert")
	public String teacherInsertPost(Teacher teacher, RedirectAttributes rttr) {
		
		
		Teacher ins_teacher = teacherService.insertTeacher(teacher);
		
		rttr.addFlashAttribute("resultMessage", ins_teacher==null?"입력실패":"입력성공");
		return "redirect:/admin/teacherList";
	}
	
//강사삭제
	@GetMapping("/admin/teacherDelete")
	public String teacherDelete(Long tno,  RedirectAttributes rttr) {
		int ret = teacherService.deleteteacher(tno);
		System.out.println("삭제:" + ret);
		rttr.addFlashAttribute("resultMessage", ret==0?"삭제실패":"삭제성공");
		return "redirect:/admin/teacherList";
	}
	
	
//과정main 
	@RequestMapping("/admin/courseList")
	public void courseSelectAll(Model model, PageVO pagevo, HttpServletRequest request ) {
		Page<Course> result = courseService.selectAll(pagevo);

		
		model.addAttribute("courselist", result);
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
	}
//		@RequestMapping("/admin/courseList")
//		public void courseSelectAll(Model model, HttpServletRequest request ) {
//		model.addAttribute("courselist", courseService.courseSelectAll());
//		
//	}
	
//과정삭제
	@GetMapping("/admin/courseDelete")
	public String courseDelete(Long cno,  RedirectAttributes rttr) {
		int ret = courseService.deleteCourse(cno);
		System.out.println("삭제:" + ret);
		rttr.addFlashAttribute("resultMessage", ret==0?"삭제실패":"삭제성공");
		return "redirect:/admin/courseList";
	}
//과정추가
	@GetMapping("/admin/courseInsert")
	public void courseInsert(Model model) {
		model.addAttribute("certificatelist", certificateservice.selectAll());
		model.addAttribute("subjectlist", subjectservice.selectAll());
		model.addAttribute("traineelist", traineeservice.selectAll());

	}
	 
	@PostMapping("/admin/courseInsert")
	public String courseInsertPost(@ModelAttribute Course course, Long certiNo, Long subjectNo, RedirectAttributes rttr) {
	
		System.out.println("회원가입 : " + course);
		System.out.println("certiNo : " + certiNo);
		System.out.println("subject_subject_no : " + subjectNo);
		
		course.setCertificate(certificateservice.selectById(certiNo)); 
		course.setSubject(subjectservice.selectById(subjectNo));
		
		Course ins_course = courseService.insertCourse(course);
		System.out.println("incousrse : " + ins_course);
		
		rttr.addFlashAttribute("resultMessage", ins_course==null?"입력실패":"입력성공");
		return "redirect:/admin/courseList";
	}
	
//강의계획main
	@RequestMapping("/admin/lectureList")
	public void lectureSelectAll(Model model, PageVO pagevo, HttpServletRequest request) {
		
		Page<Lecture> result = lectureService.selectAll(pagevo);
		
		
		model.addAttribute("lecturelist", result);
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
		
	}
//강의계획삭제
	
//강의계획 추가
	
//강의main

//강의삭제
	
//강의추가


	
}

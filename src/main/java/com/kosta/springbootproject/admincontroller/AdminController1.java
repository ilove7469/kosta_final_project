package com.kosta.springbootproject.admincontroller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.springbootproject.adminservice.ClassesService;
import com.kosta.springbootproject.adminservice.CompanyService;
import com.kosta.springbootproject.adminservice.AdminCourseService;
import com.kosta.springbootproject.adminservice.CertificateService;
import com.kosta.springbootproject.adminservice.LectureService;
import com.kosta.springbootproject.adminservice.SubjectService;
import com.kosta.springbootproject.adminservice.TeacherService;
import com.kosta.springbootproject.adminservice.TraineeService;
import com.kosta.springbootproject.model.Admin;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.Lecture;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.model.Teacher;
import com.kosta.springbootproject.persistence.AdminRepository;
import com.kosta.springbootproject.persistence.ClassHistoryRepository;
import com.kosta.springbootproject.persistence.ClassesRepository;
import com.kosta.springbootproject.persistence.CompanyRepository;
import com.kosta.springbootproject.persistence.TeacherRepository;
import com.kosta.springbootproject.persistence.UserRepository;
import com.kosta.springbootproject.adminservice.AdminService;
import com.kosta.springbootproject.adminservice.EducationTimeService;
import com.kosta.springbootproject.adminservice.ClassRoomService;

@Controller
public class AdminController1 {
	
	@Autowired
	ClassesRepository classesRepo;

	@Autowired
	CompanyService companyService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	AdminCourseService courseService;
	@Autowired
	ClassesService classesService;
	@Autowired
	LectureService lectureService;

	@Autowired
	SubjectService subjectservice;
	@Autowired
	CertificateService certificateservice;
	@Autowired
	TraineeService traineeservice;

	@Autowired
	AdminService adminService;
	@Autowired
	EducationTimeService educationTimeService;
	@Autowired
	ClassRoomService classRoomService;

	@Autowired
	UserRepository userrepository;
	@Autowired
	TeacherRepository teacherrepository;
	@Autowired
	CompanyRepository companyrepository;
	@Autowired
	ClassHistoryRepository classhistoryrepository;

//main 통계
	@RequestMapping("/admin/adminMain")
	public void adminMain(Model model) {
		model.addAttribute("usercount", userrepository.userCount());
		model.addAttribute("teachercount", teacherrepository.teacherCount());
		model.addAttribute("companycount", companyrepository.companyCount());

		model.addAttribute("classhistorywaitcount", classhistoryrepository.classHistoryWaitCount());
		model.addAttribute("classhistorycompletedcount", classhistoryrepository.classHistoryCompletedCount());
		model.addAttribute("traineecount", userrepository.traineeCount());
		model.addAttribute("traineeworkercount", userrepository.traineeworkerCount());

		model.addAttribute("openExpectedList", classesService.selectRecentOpenClasses());
		model.addAttribute("closeExpectedList", classesService.selectRecentCloseClasses());
	}

//직원 주소록
	@RequestMapping("/admin/adminList")
	public void adminList(Model model, HttpServletRequest request) {
		List<Admin> result = adminService.selectAll();
		model.addAttribute("adminlist", result);
	}

//회사 main
	@RequestMapping("/admin/companyList")
	public void companySelectAll(Model model, HttpServletRequest request) {
		List<Company> result = companyService.selectAll();
		model.addAttribute("companylist", result);
	}

//회사 상세보기
	@GetMapping("/admin/companyDetail/{cno}")
	public ModelAndView companySelectById(@PathVariable Long cno) {
		ModelAndView mv = new ModelAndView("admin/companyDetail");
		Company company = companyService.selectById(cno);
		mv.addObject("company", company);
		return mv;
	}

//회사 수정
	@PostMapping("/admin/companyUpdate")
	public String companyUpdate(Company company, RedirectAttributes rttr) {
		Company update_company = companyService.updateCourse(company);
		rttr.addFlashAttribute("resultMessage", update_company == null ? "수정실패" : "수정성공");

		return "redirect:/admin/companyList";
	}

//회사 추가
	@GetMapping("/admin/companyInsert")
	public void companyInsert() {
	}

	@PostMapping("/admin/companyInsert")
	public String companyInsertPost(Company company, RedirectAttributes rttr) {

		Company ins_company = companyService.insertCompany(company);

		rttr.addFlashAttribute("resultMessage", ins_company == null ? "입력실패" : "입력성공");
		return "redirect:/admin/companyList";
	}

//회사 삭제
	@GetMapping("/admin/companyDelete")
	public String companyDelete(Long cno, RedirectAttributes rttr) {
		int ret = companyService.deleteCompany(cno);

		rttr.addFlashAttribute("resultMessage", ret == 0 ? "삭제실패" : "삭제성공");
		return "redirect:/admin/companyList";
	}

//강사 main
	@RequestMapping("/admin/teacherList")
	public void teacherSelectAll(Model model, HttpServletRequest request) {
		List<Teacher> result = teacherService.selectAll();
		model.addAttribute("teacherlist", result);
	}

//강사 상세보기
	@GetMapping("/admin/teacherDetail/{tno}")
	public ModelAndView teacherSelectById(@PathVariable Long tno) {
		ModelAndView mv = new ModelAndView("admin/teacherDetail");
		Teacher teacher = teacherService.selectById(tno);
		mv.addObject("teacher", teacher);
		return mv;
	}

//강사 수정	
	@PostMapping("/admin/teacherUpdate")
	public String teacherUpdate(Teacher teacher, RedirectAttributes rttr) {
		Teacher update_teacher = teacherService.updateTeacher(teacher);
		rttr.addFlashAttribute("resultMessage", update_teacher == null ? "수정실패" : "수정성공");
		return "redirect:/admin/teacherList";
	}

//강사 추가
	@GetMapping("/admin/teacherInsert")
	public void teacherInsert() {
	}

	@PostMapping("/admin/teacherInsert")
	public String teacherInsertPost(Teacher teacher, RedirectAttributes rttr) {
		Teacher ins_teacher = teacherService.insertTeacher(teacher);
		rttr.addFlashAttribute("resultMessage", ins_teacher == null ? "입력실패" : "입력성공");
		return "redirect:/admin/teacherList";
	}

//강사 삭제
	@GetMapping("/admin/teacherDelete")
	public String teacherDelete(Long tno, RedirectAttributes rttr) {
		int ret = teacherService.deleteteacher(tno);
		rttr.addFlashAttribute("resultMessage", ret == 0 ? "삭제실패" : "삭제성공");
		return "redirect:/admin/teacherList";
	}

//과정 main 
	@RequestMapping("/admin/courseList")
	public void courseSelectAll(Model model, HttpServletRequest request) {
		List<Course> result = courseService.courseSelectAll();

		model.addAttribute("courselist", result);
	}

//과정 상세보기
	@GetMapping("/admin/courseDetail")
	public void selectById(Model model, Long cno) {
		Course course = courseService.selectById(cno);
		model.addAttribute("courselist", course);

		model.addAttribute("certificatelist", certificateservice.selectById(course.getCertificate().getCertiNo()));
		model.addAttribute("certificatelistall", certificateservice.selectAll());

		model.addAttribute("subjectlist", subjectservice.selectById(course.getSubject().getSubjectNo()));
		model.addAttribute("subjectlistall", subjectservice.selectAll());
	}

//과정 삭제
	@GetMapping("/admin/courseDelete")
	public String courseDelete(Long cno, RedirectAttributes rttr) {
		int ret = courseService.deleteCourse(cno);

		rttr.addFlashAttribute("resultMessage", ret == 0 ? "삭제실패" : "삭제성공");
		return "redirect:/admin/courseList";
	}

//과정 추가
	@GetMapping("/admin/courseInsert")
	public void courseInsert(Model model) {
		model.addAttribute("certificatelist", certificateservice.selectAll());
		model.addAttribute("subjectlist", subjectservice.selectAll());
	}

//과정추가 훈련대상 찾기위한 ajax 메소드
	@ResponseBody
	@GetMapping("/admin/courseInsert1")
	public Subject findtraineeName(Long no) {
		Subject traineeName = subjectservice.selectById(no);
		return traineeName;
	}

//과정 추가
	@PostMapping("/admin/courseInsert")
	public String courseInsertPost(Course course, RedirectAttributes rttr) {

		Course ins_course = courseService.insertCourse(course);

		rttr.addFlashAttribute("resultMessage", ins_course == null ? "입력실패" : "입력성공");
		return "redirect:/admin/courseList";
	}

//과정 수정
	@PostMapping("/admin/courseUpdate")
	public String courseUpdate(Course course, RedirectAttributes rttr) {
		Course update_course = courseService.updateCourse(course);
		rttr.addFlashAttribute("resultMessage", update_course == null ? "수정실패" : "수정성공");
		return "redirect:/admin/courseList";
	}

//강의계획 main
	@RequestMapping("/admin/lectureList")
	public void lectureSelectAll(Model model, HttpServletRequest request) {
		List<Lecture> result = lectureService.selectAll();
		model.addAttribute("lecturelist", result);
	}

//강의계획 삭제
	@GetMapping("/admin/lectureDelete")
	public String lectureDelete(Long cno, RedirectAttributes rttr) {
		int ret = lectureService.deleteLecture(cno);

		rttr.addFlashAttribute("resultMessage", ret == 0 ? "삭제실패" : "삭제성공");
		return "redirect:/admin/lectureList";
	}

//강의계획 추가
	@GetMapping("/admin/lectureInsert")
	public void lectureInsert(Lecture lecture, Model model) {
		model.addAttribute("courselist", courseService.courseSelectAll());
		model.addAttribute("lecture", lecture);
	}

//강의 계획 추가 목표정원 계산을 위한 ajax 메소드
	@ResponseBody
	@GetMapping("/admin/lectureInsert1")
	public int resultLectureCapacity(Long no) {
		int coursecapacity = courseService.selectById(no).getCourseCapacity();

		return coursecapacity;
		// 연간 개설횟수 곱하기 과정정원 는 목표정원
		// lectureOpenCount * courseCapacity = lectureCapacity
	}

	@PostMapping("/admin/lectureInsert")
	public String lectureInsertPost(Lecture lecture, RedirectAttributes rttr) {
		boolean lecture_check = lectureService.insertOrUpdate(lecture);

		String rd = "";
		String message = "";
		if (!lecture_check) {
			message = "해당년도에 이미 있는 과정입니다";
			rttr.addFlashAttribute("resultMessage", message);
			rttr.addFlashAttribute("lecture", lecture);
			rd = "redirect:/admin/lectureInsert";
		} else {
			message = "생성이 가능합니다"; // 인서트, 업데이트 성공
			rttr.addFlashAttribute("resultMessage", message);
			rd = "redirect:/admin/lectureList";
		}
		return rd;
	}

//강의계획 상세보기
	@GetMapping("/admin/lectureDetail/{lno}")
	public ModelAndView selectById(@PathVariable Long lno) {
		ModelAndView mv = new ModelAndView("admin/lectureDetail");
		Lecture lectrue = lectureService.selectById(lno);
		// Course course = courseService.selectById();
		mv.addObject("lecture", lectrue);
		mv.addObject("courselist", courseService.courseSelectAll());
		return mv;
	}

//강의계획 수정
	@PostMapping("/admin/lectureUpdate")
	public String lectureUpdate(Lecture lecture, RedirectAttributes rttr) {
		lectureService.insertOrUpdate(lecture);
		return "redirect:/admin/lectureList";
	}

//강의 main
	@RequestMapping("/admin/classesList")
	public void classesSelectAll(Model model, HttpServletRequest request) {
		List<Classes> result = classesService.selectAll();
		model.addAttribute("classeslist", result);
	}

//강의 상세보기
	@GetMapping("/admin/classesDetail/{cno}")
	public ModelAndView SelectByIdteacher(@PathVariable Long cno) {
		ModelAndView mv = new ModelAndView("admin/classesDetail");
		Classes classes = classesService.selectById(cno);
		mv.addObject("classes", classes);

		mv.addObject("teacherlist", teacherService.selectAll());
		mv.addObject("classRoomlist", classRoomService.selectAll());
		mv.addObject("lecturelist", lectureService.selectAll());
		mv.addObject("educationTimelist", educationTimeService.selectAll());
		mv.addObject("adminlist", adminService.selectAll());
		return mv;
	}

//강의 수정
	@PostMapping("/admin/classesUpdate")
	public String classesUpdate(Classes classes, RedirectAttributes rttr) {
		Classes update_classes = classesService.updateClasses(classes);
		rttr.addFlashAttribute("resultMessage", update_classes == null ? "수정실패" : "수정성공");
		return "redirect:/admin/classesList";
	}

//강의 삭제
	@GetMapping("/admin/classesDelete")
	public String classesDelete(Long cno, RedirectAttributes rttr) {
		int ret = classesService.deleteClasses(cno);

		rttr.addFlashAttribute("resultMessage", ret == 0 ? "삭제실패" : "삭제성공");
		return "redirect:/admin/classesList";
	}

//강의 추가
	@GetMapping("/admin/classesInsert")
	public void classesInsert(Model model) {
		model.addAttribute("lecturelist", lectureService.selectAll());
		model.addAttribute("teacherlist", teacherService.selectAll());
		model.addAttribute("adminlist", adminService.selectAll());
		model.addAttribute("educationTimelist", educationTimeService.selectAll());
		model.addAttribute("classRoomlist", classRoomService.selectAll());
	}

	@PostMapping("/admin/classesInsert")
	public String classesInsertPost(Classes classes) {
		classesService.updateOrInsert(classes);
		return "redirect:/admin/classesList";
	}

//사이드바에 현재 로그인 사용자 이름 확인용 ajax 메소드
	@Autowired
	AdminRepository adminrepository;

	@ResponseBody
	@GetMapping("/admin/loggingId")
	public Map<String, String> loggingId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;

		String mid = userDetails.getUsername();
		Admin admin = adminrepository.findByAdminId(mid);
		String loggingId = admin.getAdminName();
		Map<String, String> loggingid2 = new HashMap<>();
		loggingid2.put("naver", loggingId);

		return loggingid2;
	}

//강의내역 엑셀다운로드
	@RequestMapping("/admin/exceldownload")
	public void excelDownload(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		OutputStream out = null;

		try {
			List<Classes> list = (List<Classes>) classesRepo.findAll();

			XSSFWorkbook workbook = classesService.listExcelDownload(list);

			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=kosta_history.xls"); // 엑셀 파일 명
			response.setContentType("application/vnd.ms-excel");
			out = new BufferedOutputStream(response.getOutputStream());

			workbook.write(out);
			out.flush();

		} catch (Exception e) {
			// logger.error("exception during downloading excel file : {}", e);
		} finally {
			if (out != null)
				out.close();
		}
	}

}

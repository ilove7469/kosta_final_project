package com.kosta.springbootproject.admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.springbootproject.adminservice.CertificateService;
import com.kosta.springbootproject.adminservice.ClassRoomService;
import com.kosta.springbootproject.adminservice.LectureHallService;
import com.kosta.springbootproject.adminservice.UserService;
import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.model.ClassRoom;
import com.kosta.springbootproject.model.LectureHall;

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
	
	@GetMapping("/admin/usermain")
	public void selectAllUser(Model model) {
		model.addAttribute("userList",userService.selectAll());
	}
	
	@GetMapping("/admin/certificatemain")
	public void selectAllCerti(Model model) {
		model.addAttribute("certiList",certiService.selectAll());
	}
	
	@GetMapping("/admin/certificateadd")
	public String insertCerti() {
		return "/admin/certificatedetail";
	}
	
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

	@GetMapping("/admin/lecturehallmain")
	public void selectAllLectureHall(Model model) {
		model.addAttribute("lectureHallList",lectureHallService.selectAll());
	}
	
	@GetMapping("/admin/lecturehalladd")
	public String insertLectureHall() {
		return "/admin/lecturehalldetail";
	}
	
	@PostMapping("/admin/lecturehalladd")
	public String insertLectureHall(LectureHall lectureHall) {
		lectureHallService.updateOrInsert(lectureHall);
		return "redirect:/admin/lecturehallmain";
	}
	
	@RequestMapping("/admin/classroommain")
	public void selectAllClassRoom(Model model) {
		System.out.println(classRoomService.selectAll());
		model.addAttribute("classRoomList",classRoomService.selectAll());
	}
	
	@GetMapping("/admin/classroomadd")
	public String insertClassRoom(Model model) {
		List<LectureHall> lectureHallList = lectureHallService.selectAll();
		model.addAttribute("lectureHallList",lectureHallList);
		return "/admin/classroomdetail";
	}
	
	@PostMapping("/admin/classroomadd")
	public String insertClassRoom(ClassRoom classRoom) {
		classRoomService.updateOrInsert(classRoom);
		return "redirect:/admin/classroommain";
	}
	
	
	
	

	
	
	
}

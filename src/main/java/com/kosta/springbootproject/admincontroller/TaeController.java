package com.kosta.springbootproject.admincontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.springbootproject.adminservice.CompanyService;
import com.kosta.springbootproject.adminservice.TeacherService;
import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.PageMaker;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.model.Teacher;


@Controller
public class TaeController {
	
	@Autowired
	CompanyService companyservice;
	@Autowired
	TeacherService teacherservice;
	

	
	@RequestMapping("/admin/companyList")
	public void companySelectAll(Model model, PageVO pagevo, HttpServletRequest request ) {
		Page<Company> result = companyservice.selectAll(pagevo);
		List<Company> companylist = result.getContent();
		
		model.addAttribute("companylist", result);
		
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
	}
	
	@GetMapping("/admin/companyInsert")
	public void companyInsert() {
		
	}
	 
	@PostMapping("/admin/companyInsert")
	public String companyInsertPost(Company company, RedirectAttributes rttr) {
		
	
		
		Company ins_company = companyservice.insertCompany(company);
		
		rttr.addFlashAttribute("resultMessage", ins_company==null?"입력실패":"입력성공");
		return "redirect:/admin/companyList";
	}
	
	@GetMapping("/admin/companyDelete")
	public String companyDelete(Long cno,  RedirectAttributes rttr) {
		int ret = companyservice.deleteCompany(cno);
		System.out.println("삭제:" + ret);
		rttr.addFlashAttribute("resultMessage", ret==0?"삭제실패":"삭제성공");
		return "redirect:/admin/companyList";
	}
	
	
	
	
	@RequestMapping("/admin/teacherList")
	public void teacherSelectAll(Model model) {
		model.addAttribute("teacherlist", teacherservice.selectAll());
	}
	
	@GetMapping("/admin/teacherInsert")
	public void teacherInsert() {
		
	} 
	 
	@PostMapping("/admin/teacherInsert")
	public String teacherInsertPost(Teacher teacher, RedirectAttributes rttr) {
		
		
		Teacher ins_teacher = teacherservice.insertTeacher(teacher);
		
		rttr.addFlashAttribute("resultMessage", ins_teacher==null?"입력실패":"입력성공");
		return "redirect:/admin/teacherList";
	}
	
	
	@GetMapping("/admin/teacherDelete")
	public String teacherDelete(Long tno,  RedirectAttributes rttr) {
		int ret = teacherservice.deleteteacher(tno);
		System.out.println("삭제:" + ret);
		rttr.addFlashAttribute("resultMessage", ret==0?"삭제실패":"삭제성공");
		return "redirect:/admin/teacherList";
	}
}

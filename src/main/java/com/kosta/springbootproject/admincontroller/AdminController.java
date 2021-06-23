package com.kosta.springbootproject.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.springbootproject.model.Admin;
import com.kosta.springbootproject.persistence.AdminRepository;
import com.kosta.springbootproject.persistence.MemberReposiroty;
import com.kosta.springbootproject.security.MemberService;

@Controller
public class AdminController {

	@Autowired
	MemberService mservice;
	
	@Autowired
	MemberReposiroty mrepo;
	
	@Autowired
	AdminRepository repo;
	
	@GetMapping("/admin/adminInfo")
	public void adminInfo(Model model ) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails)principal;
		
		String mid = userDetails.getUsername();
		Admin admin = repo.findByAdminId(mid);
		
		model.addAttribute("admin", admin);
	}
	
	@PostMapping("/admin/update")
	public String adminUpdate(Admin admin) {
		repo.findById(admin.getAdminNo()).ifPresent(ad -> {
			ad.setAdminPw(admin.getAdminPw());
			ad.setAdminName(admin.getAdminName());
			ad.setAdminPhone(admin.getAdminPhone());
			ad.setAdminEmail(admin.getAdminEmail());
			repo.save(ad);
		});
		
		mrepo.findById(admin.getAdminId()).ifPresent(member -> {
			member.setMname(admin.getAdminName());
			member.setMpassword(admin.getAdminPw());
			mservice.joinUser(member);
		});
		
		return "redirect:/admin/adminInfo";
	}
	
}

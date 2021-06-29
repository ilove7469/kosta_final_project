package com.kosta.springbootproject.admincontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.springbootproject.model.Admin;
import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.MemberRoleEnumType;
import com.kosta.springbootproject.persistence.AdminRepository;
import com.kosta.springbootproject.persistence.MemberReposiroty;
import com.kosta.springbootproject.security.MemberService;

@Controller
public class AdminUserController {

	@Autowired
	MemberService mservice;
	
	@Autowired
	MemberReposiroty mrepo;
	
	@Autowired
	AdminRepository repo;
	
	@GetMapping("/admin/adminInsert")
	public void adminInsert() {
		
	}
	
	@PostMapping("/admin/adminInsert")
	public String adminInsertPost(Admin admin, HttpServletResponse response) {
		//Optinal이 null을 가지면 에러가 난다. 그래서 isEmpty()를 사용한다.
		Boolean flag = mrepo.findById(admin.getAdminId()).isEmpty();
		
		if(!flag) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('이미 있는 아이디입니다.'); location.href='/admin/adminInsert';</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}
		MemberDTO new_member = MemberDTO.builder()
				.mid(admin.getAdminId())
				.mname(admin.getAdminName())
				.mpassword(admin.getAdminPw())
				.mrole(MemberRoleEnumType.ADMIN)
				.build();
		mservice.joinUser(new_member);
		
		Admin new_admin = Admin.builder()
				.adminEmail(admin.getAdminEmail())
				.adminName(admin.getAdminName())
				.adminId(admin.getAdminId())
				.adminPhone(admin.getAdminPhone())
				.adminPw(admin.getAdminPw())
				.build();
		repo.save(new_admin);
		
		return "redirect:/admin/courseList";
	}
	
	
	@GetMapping("/admin/adminInfo")
	public void adminInfo(Model model ) {
		//로그인 정보를 SecurityContextHolder가 가지고있다.
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

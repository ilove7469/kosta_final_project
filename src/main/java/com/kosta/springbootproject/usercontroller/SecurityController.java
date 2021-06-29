package com.kosta.springbootproject.usercontroller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.security.MemberService;

@Controller
public class SecurityController {
	
	@Autowired
	MemberService service;

	@GetMapping("/auth/login")
	public void login() {
	}
	
	@GetMapping("/loginSuccess")
	public String loginSuccess() {
		Collection<? extends GrantedAuthority> authorities;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		authorities = auth.getAuthorities();
		String Role = authorities.toArray()[0].toString();
		
		if(Role.equals("ROLE_ADMIN")) {
			return "redirect:/admin/adminMain";
		}
		return "redirect:/user/userMain";
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
}

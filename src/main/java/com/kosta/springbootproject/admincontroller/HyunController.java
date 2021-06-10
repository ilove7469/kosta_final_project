package com.kosta.springbootproject.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.springbootproject.adminservice.UserService;

@Controller
public class HyunController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/userlist")
	public void test1() {

	}
	
	
}

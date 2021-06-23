package com.kosta.springbootproject.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.springbootproject.adminservice.UserService;
import com.kosta.springbootproject.model.Users;

@RestController
public class HyunRestController {

	@Autowired
	UserService userService;
	
	@GetMapping("/admin/changetrainee")
	public void changeTrainee(@RequestParam Long userNo) {
		Users user = userService.findUsersByUsersNo(userNo);
		String userTraineeName = user.getTrainee().getTraineeName();
		if(userTraineeName.equals("채용예정자")) {
			userService.changeToEmp(userNo);
		}else if(userTraineeName.equals("재직자")){
			userService.changeToUnemp(userNo);
		}
	}
}

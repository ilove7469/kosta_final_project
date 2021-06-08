package com.kosta.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Admin {
	@Id
	private int adminNo;
	
	@Column(nullable = false, unique = true)
	private String adminName;
	
	@Column(nullable = false)
	private String adminId;
	
	@Column(nullable = false)
	private String adminPw;
	
	@Column(nullable = false)
	private String adminPhone;
	
	@Column(nullable = false)
	private String adminEmail;
}

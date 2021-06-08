package com.kosta.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class EducationTime {
	@Id
	private int educationTimeNo;
	
	@Column(nullable = false)
	private String educationTimeName;
	
	@Column(nullable = false)
	private String educationPartType;
	
	@Column(nullable = false)
	private String educationTimeType;
}

package com.kosta.springbootproject.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

public class Classes {
	@Id
	private int classNo;
	
	@Column(nullable = false)
	private Date classOpenDate;
	
	@Column(length = 1000)
	private String classDescription;
	
	@ColumnDefault("false")
	private Boolean classRecommend;
	
	private String classState;
	
	//ManyToOne
	private int lecturePlanNo;
	private int teacherNo;
	private int classRoomNo;
	private int educationTimeNo;
	private int adminNo;
	
}

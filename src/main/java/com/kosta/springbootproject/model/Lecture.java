package com.kosta.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Lecture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lecturePlanNo;
	
	@Column(nullable = false)
	private int lecturePlanYear;
	
	private int lectureOpenCount;
	
	private int lectureCapacity;
	
	@Column(length = 1000)
	private String lectureTarget;
	
	@Column(length = 1000)
	private String lecturePenalty;
	
	@Column(length = 1000)
	private String lectureOnline;
	
	private String lectureAsk;
	
	@ManyToOne
	private Course course;
	
}

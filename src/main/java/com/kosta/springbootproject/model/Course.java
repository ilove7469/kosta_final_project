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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseNo;
	
	@Column(nullable = false)
	private String courseName;
	
	@Column(nullable = false)
	private int courseTotalTrainTime;
	
	@Column(nullable = false)
	private int courseTotalDay;
	
	private int coursePrice;
	
	@Column(nullable = false)
	private int courseCapacity;

	@Column(length = 1000)
	private String courseTarget;
	
	@Column(length = 1000)
	private String coursePurpose;
	
	@Column(length = 1000)
	private String courseContent;
	
	@ManyToOne
	private Subject subject;

	@ManyToOne
	private Certificate certificate;
}

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
	int courseNo;
	@Column(nullable = false)
	String courseName;
	
	@ManyToOne
	Subject subject;
	@Column(nullable = false)
	int courseTotalTrainTime;
	@Column(nullable = false)
	int courseTotalDay;
	int coursePrice;
	@Column(nullable = false)
	int courseCapacity;

	@Column(length = 1000)
	String courseTarget;
	@Column(length = 1000)
	String coursePurpose;
	@Column(length = 1000)
	String courseContent;
	
	@ManyToOne
	Certificate certificate;
}

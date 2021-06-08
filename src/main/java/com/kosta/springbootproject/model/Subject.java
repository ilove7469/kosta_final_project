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
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int subjectNo;
	int subPriority;
	@Column(nullable = false)
	String subName;
	
	@ManyToOne
	Trainee trainee;
	@Column(length = 1000)
	String subExplain;
	
}
